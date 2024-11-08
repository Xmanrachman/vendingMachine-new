
#!/bin/bash
## Memory settings. Comment out if not exists
XMS="-Xms1g"
XMX="-Xmx1g"

## Change dir to app folder
SCRIPT_DIR="$(cd "$(dirname "$0")"; pwd)"
APP_DIR="$(cd "$(dirname "$SCRIPT_DIR")"; pwd)"
CONF_DIR=$APP_DIR/conf
LIB_DIR=$APP_DIR/lib
LOG_DIR=$APP_DIR/log
LOGS_DIR=$APP_DIR/logs

STDOUT_FILE=$LOGS_DIR/stdout.log

## LOGGING SETUP
#LOG_FILE=$LOG_DIR/startup."$(date +%Y%m%d%H%M%S)"

if [ ! -d $LOG_DIR ]; then
    mkdir $LOG_DIR
fi

#exec 1>>$LOG_FILE

## STARTING THE APP
for file in "${LIB_DIR}"/* ; do
   if [[ ${file: -4} == ".jar" ]]; then
      APP_JAR=${file}
      break
   fi
done;

SERVER_NAME=`sed '/server.name/!d;s/.*=//' $CONF_DIR/application.properties | tr -d '\r'`
SERVER_PROTOCOL="HTTP"
SERVER_PORT=`sed '/server.port/!d;s/.*=//' $CONF_DIR/application.properties | tr -d '\r'`

echo "project name:$SERVER_NAME"
echo "server protocol:$SERVER_PROTOCOL"
echo "server port:$SERVER_PORT"

if [ -z "$SERVER_NAME" ]; then
    SERVER_NAME=`hostname`
fi

PIDS=`ps aux | grep java | grep "$CONF_DIR" |awk '{print $2}'`
if [ -n "$PIDS" ]; then
    echo "ERROR: The $SERVER_NAME already started!"
    echo "PID: $PIDS"
    exit 1
fi

if [ -n "$SERVER_PORT" ]; then
    SERVER_PORT_COUNT=`netstat -tln | grep $SERVER_PORT | wc -l`
    if [ $SERVER_PORT_COUNT -gt 0 ]; then
        echo "ERROR: The $SERVER_NAME port $SERVER_PORT already used!"
        exit 1
    fi
fi

JAVA_OPTS="-Ddubbo.properties.file=$CONF_DIR/dubbo.properties -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Dlogging.config=$CONF_DIR/logback.xml -Dspring.config.location=$CONF_DIR/application.properties -Dlog.root.path=$LOG_DIR"

JAVA_DEBUG_OPTS=""
if [ "$1" = "debug" ]; then
    JAVA_DEBUG_OPTS=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n "
fi

JAVA_JMX_OPTS=""
if [ "$1" = "jmx" ]; then
    JAVA_JMX_OPTS=" -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false "
fi

JAVA_MEM_OPTS=""
BITS=`java -version 2>&1 | grep -i 64-bit`
if [ -n "$BITS" ]; then
    JAVA_MEM_OPTS=" -server -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "
else
    JAVA_MEM_OPTS=" -server -XX:PermSize=128m -XX:SurvivorRatio=2 -XX:+UseParallelGC "
fi

JAVA_HEAP_OPTS=""
if [ ! -z "$XMS" ] && [ ! -z "$XMX" ]; then
	JAVA_HEAP_OPTS="$XMS $XMX"
fi

echo -e "Starting $SERVER_NAME ...\c"
#java $JAVA_OPTS $JAVA_HEAP_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS -jar $APP_JAR > /dev/null 2>&1 &
java $JAVA_OPTS $JAVA_HEAP_OPTS $JAVA_MEM_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS -jar $APP_JAR > $STDOUT_FILE 2>&1 &

echo "Waiting for the server to start..."

COUNT=0
while [ $COUNT -lt 1 ]; do    
    echo -e ".\c"
    sleep 1 
    COUNT=`ps aux | grep java | grep "$DEPLOY_DIR" | awk '{print $2}' | wc -l`
    if [ $COUNT -gt 0 ]; then
        break
    fi
done

echo "Server started!"
PIDS=`ps aux | grep java | grep "$DEPLOY_DIR" | awk '{print $2}'`
echo "PID: $PIDS"
