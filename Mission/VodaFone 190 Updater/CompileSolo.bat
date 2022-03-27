@echo off
@set PATH=%PATH%;C:\j2sdk1.4.2\bin

@echo Deleting old classes
@deltree /y .\class > nul
@mkdir .\class

@rem Remove extra directory we don't need now
@deltree /y .\class\org > nul


@echo Compiling Downloader...
set CLASSPATH=.\lib;.\lib\commons-logging.jar;.\lib\commons-logging-api.jar
javac Downloader.java -d .\class
@echo DONE compiling Downloader