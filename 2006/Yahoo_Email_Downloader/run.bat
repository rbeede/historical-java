set CLASSPATH=.;./classes;./lib/commons-httpclient-2.0.1.jar;./lib/commons-logging.jar;./lib/commons-logging-api.jar;./lib/log4j-1.2.9.jar

rem java -Dorg.apache.commons.logging.simplelog.defaultlog=debug -Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.SimpleLog -Dorg.apache.commons.logging.simplelog.showlogname=true -Dorg.apache.commons.logging.simplelog.showdatetime=true -classpath %CLASSPATH% Yahoo_Email_Downloader rodneybeede %1

java -classpath %CLASSPATH% Yahoo_Email_Downloader log4j.prop rodneybeede %1