@echo off
@set PATH=%PATH%;C:\j2sdk1.4.2\bin

@echo Deleting TrimSpace.class
@del TrimSpace.class

@echo Compiling...
@set CLASSPATH=.\lib;.\lib\commons-logging.jar;.\lib\commons-logging-api.jar;.
javac TrimSpace.java
@echo DONE...Running

rem del "C:\anzbidi\MyLDSMail Downloader\DUMP\FOLDERS\BACKUP\test.txt.dmp"
java TrimSpace "C:\\WINDOWS\\Desktop\\daone.txt"