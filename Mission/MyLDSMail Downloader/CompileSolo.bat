@echo off
@set PATH=%PATH%;c:\j2sdk1.4.2\bin

@echo Deleting old classes
@deltree /y .\class > nul
@mkdir .\class


@echo Compiling Downloader...
set CLASSPATH=.\lib;.\lib\commons-logging.jar;.\lib\commons-logging-api.jar
javac MyLDSMail_Downloader.java -d .\class
@echo DONE compiling Downloader
@pause