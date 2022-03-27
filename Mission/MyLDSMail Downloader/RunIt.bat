set PATH=%PATH%;c:\j2sdk1.4.2\bin

set CLASSPATH=.\lib;.\lib\commons-logging.jar;.\lib\commons-logging-api.jar;.\class

@deltree /y DUMP
@rem For Win2K
@rmdir /s /q DUMP

@echo RUNNING...
@java -Dorg.apache.commons.httpclient.HttpMethodBase=fatal MyLDSMail_Downloader rodneyb pass nodebug
@pause


