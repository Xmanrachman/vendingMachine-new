echo off
set JAVA_HOME=C:\Java\jdk1.8.0_172

set APP_JAR="aca-customer-service-0.0.1-SNAPSHOT"
set LIB_JARS=""
cd ..\lib
for %%i in (*) do set LIB_JARS=!LIB_JARS!;..\lib\%%i
cd ..\bin

if ""%1"" == ""debug"" goto debug
if ""%1"" == ""jmx"" goto jmx

java -Xms256m -Xmx512m -server -Xloggc:../logs/gc.log -verbose:gc -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=../logs -Dlogback.configurationFile=../conf/logback.xml -Dspring.config.location=../conf/application.properties -jar ../lib/aca-customer-service-0.0.1-SNAPSHOT.jar
goto end

:debug
java -Xms256m -Xmx512m -server -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n -Xloggc:../logs/gc.log -verbose:gc -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=../logs -Dlogback.configurationFile=../conf/logback.xml -Dspring.config.location=../conf/application.properties,../conf/disconf.properties -jar ../lib/aca-customer-service-0.0.1-SNAPSHOT.jar
goto end

:jmx
java -Xms256m -Xmx512m -server -Xloggc:../logs/gc.log -verbose:gc -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=../logs -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.ssl=FALSE -Dcom.sun.management.jmxremote.authenticate=FALSE -Dlogback.configurationFile=../conf/logback.xml -Dspring.config.location=../conf/application.properties,../conf/disconf.properties -jar ../lib/aca-customer-service-0.0.1-SNAPSHOT.jar

:end
pause