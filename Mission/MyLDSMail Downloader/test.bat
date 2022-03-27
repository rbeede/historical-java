@echo off
@set PATH=%PATH%;C:\j2sdk1.4.2\bin

@echo Deleting old class
@del test.class

@echo Compiling...
@set CLASSPATH=.\lib;.\lib\commons-logging.jar;.\lib\commons-logging-api.jar;.
javac test.java
@echo DONE...Running

java test