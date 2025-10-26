#!/bin/bash
# Exit immediately if any command fails
set -e

if [ -n "$PORT" ]; then
    echo "Dynamic port \$PORT=$PORT detected. Configuring Tomcat's server.xml..."
    
    sed -i "s/port=\"8080\"/port=\"$PORT\"/" $CATALINA_HOME/conf/server.xml
    
    echo "Tomcat connector configured to listen on port $PORT."
fi

export JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8"
echo "Java opts for UTF-8 is set : $JAVA_OPTS"

echo "Starting Tomcat..."
exec $CATALINA_HOME/bin/catalina.sh run
