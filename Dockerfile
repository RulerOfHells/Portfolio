# ----------------------------------------------------------------------------------
# STAGE 1: BUILD - Compiles the Spring WAR file
# Base image includes JDK 17 and Maven
# ----------------------------------------------------------------------------------
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy Maven setup files and pom.xml first for efficient caching
COPY pom.xml .
COPY .mvn .mvn/

# Download dependencies (only if pom.xml changed)
RUN mvn dependency:go-offline -B

# Copy the rest of the source code (src/ folder)
COPY src ./src

# Build the WAR file, skipping tests. Output is target/Portfolio.war
RUN mvn clean package -DskipTests

# ----------------------------------------------------------------------------------
# STAGE 2: RUNTIME - Deploys the WAR file using a lightweight Tomcat image
# ----------------------------------------------------------------------------------
# Use a Tomcat image with JRE 17
FROM tomcat:10.1-jre17-temurin-focal

# Tomcat configuration environment variable
ENV CATALINA_HOME /usr/local/tomcat

# Remove the default Tomcat webapps (The default "ROOT" application)
RUN rm -rf $CATALINA_HOME/webapps/ROOT/

# Copy the built WAR file from the build stage into Tomcat's webapps directory.
# We rename it to ROOT.war so Tomcat serves the application from the root path ("/")
COPY --from=build /app/target/Portfolio.war $CATALINA_HOME/webapps/ROOT.war

# Tomcat runs on port 8080 by default
EXPOSE 8080

# The base image already has a CMD defined to start Tomcat.
