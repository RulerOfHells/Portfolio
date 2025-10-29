FROM maven:3.9.6-eclipse-temurin-17 AS build
# Build
WORKDIR /app

COPY pom.xml .
COPY .mvn .mvn/

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests

# Run
FROM tomcat:10.1-jre17-temurin-focal

ENV CATALINA_HOME /usr/local/tomcat

RUN rm -rf $CATALINA_HOME/webapps/*

COPY --from=build /app/target/Portfolio.war $CATALINA_HOME/webapps/ROOT.war

COPY entrypoint.sh /usr/local/bin/

RUN chmod +x /usr/local/bin/entrypoint.sh

ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]

EXPOSE 8080
