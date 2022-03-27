@echo off
@set PATH=%PATH%;C:\j2sdk1.4.2\bin

@echo Deleting old classes
@deltree /y .\class > nul
@mkdir .\class
@deltree /y .\lib\org\apache\commons\httpclient\contrib\ssl > nul
@mkdir .\lib\org\apache\commons\httpclient\contrib\ssl > nul


@echo Compiling SSL Easy...
@rem We also move the classes into the proper lib directory
@javac EasyX509TrustManager.java -classpath ".\lib;.\lib\commons-logging.jar;.\lib\commons-logging-api.jar" -d ".\class"
@move .\class\org\apache\commons\httpclient\contrib\ssl\EasyX509TrustManager.class .\lib\org\apache\commons\httpclient\contrib\ssl > nul
@javac EasySSLProtocolSocketFactory.java -classpath ".\lib;.\lib\commons-logging.jar;.\lib\commons-logging-api.jar" -d ".\class"
@move .\class\org\apache\commons\httpclient\contrib\ssl\*.class .\lib\org\apache\commons\httpclient\contrib\ssl > nul
@echo DONE compiling SSL Easy

@rem Remove extra directory we don't need now
@deltree /y .\class\org > nul


@echo Compiling Downloader...
javac MyLDSMail_Downloader.java -classpath .\lib;.\lib\commons-logging.jar;.\lib\commons-logging-api.jar -d .\class
@echo DONE compiling Downloader