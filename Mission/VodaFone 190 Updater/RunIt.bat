@echo off
set PATH=%PATH%;C:\j2sdk1.4.2\bin

set CLASSPATH=.\lib;.\lib\commons-logging.jar;.\lib\commons-logging-api.jar;.\class

@deltree /y DUMP

@echo RUNNING...
@java -Dorg.apache.commons.httpclient.HttpMethodBase=fatal Downloader


