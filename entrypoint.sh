#!/bin/bash
# Exit immediately if any command fails
set -e

if [ -n "$PORT" ]; then
    echo "Dynamic port \$PORT=$PORT detected. Configuring Tomcat's server.xml..."
    
    sed -i "s/port=\"8080\"/port=\"$PORT\"/" $CATALINA_HOME/conf/server.xml
    
    echo "Tomcat connector configured to listen on port $PORT."
fi

echo "Starting Tomcat..."
exec $CATALINA_HOME/bin/catalina.sh run
