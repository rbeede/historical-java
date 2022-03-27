@ECHO OFF

REM Good for Windows Vista, 7, ...

@REM Remember no quoting, done for you later
SET BackupDestination=W:\Shauna's Laptop Backup\BackupArchiveFiles

SET LogFilePathname=W:\Shauna's Laptop Backup\BackupLogs\backup


@REM Must set these first thing due to bug in Windows 7 when batch script filename has spaces in it
SET BATCH_SCRIPT_DRIVE=%~d0
SET BATCH_SCRIPT_FOLDER_PATHNAME=%~dp0


SET ANY_ERRORS=0

java -jar "%BATCH_SCRIPT_FOLDER_PATHNAME%historical-backup--SNAPSHOT-jar-with-dependencies.jar"  "%LogFilePathname%_Documents" "%USERPROFILE%\Documents" "%BackupDestination%\Documents"
IF ERRORLEVEL 1 THEN SET ANY_ERRORS=1
java -jar "%BATCH_SCRIPT_FOLDER_PATHNAME%historical-backup--SNAPSHOT-jar-with-dependencies.jar"  "%LogFilePathname%_Music" "%USERPROFILE%\Music" "%BackupDestination%\Music"
IF ERRORLEVEL 1 THEN SET ANY_ERRORS=1
java -jar "%BATCH_SCRIPT_FOLDER_PATHNAME%historical-backup--SNAPSHOT-jar-with-dependencies.jar"  "%LogFilePathname%_Pictures" "%USERPROFILE%\Pictures" "%BackupDestination%\Pictures"
IF ERRORLEVEL 1 THEN SET ANY_ERRORS=1
java -jar "%BATCH_SCRIPT_FOLDER_PATHNAME%historical-backup--SNAPSHOT-jar-with-dependencies.jar"  "%LogFilePathname%_Videos" "%USERPROFILE%\Videos" "%BackupDestination%\Videos"
IF ERRORLEVEL 1 THEN SET ANY_ERRORS=1
java -jar "%BATCH_SCRIPT_FOLDER_PATHNAME%historical-backup--SNAPSHOT-jar-with-dependencies.jar"  "%LogFilePathname%_Desktop" "%USERPROFILE%\Desktop" "%BackupDestination%\Desktop"
IF ERRORLEVEL 1 THEN SET ANY_ERRORS=1


java -jar "%BATCH_SCRIPT_FOLDER_PATHNAME%historical-backup--SNAPSHOT-jar-with-dependencies.jar"  "%LogFilePathname%_PublicDocuments" "%PUBLIC%\Documents" "%BackupDestination%\Public-Documents"
IF ERRORLEVEL 1 THEN SET ANY_ERRORS=1
java -jar "%BATCH_SCRIPT_FOLDER_PATHNAME%historical-backup--SNAPSHOT-jar-with-dependencies.jar"  "%LogFilePathname%_PublicMusic" "%PUBLIC%\Music" "%BackupDestination%\Public-Music"
IF ERRORLEVEL 1 THEN SET ANY_ERRORS=1
java -jar "%BATCH_SCRIPT_FOLDER_PATHNAME%historical-backup--SNAPSHOT-jar-with-dependencies.jar"  "%LogFilePathname%_PublicPictures" "%PUBLIC%\Pictures" "%BackupDestination%\Public-Pictures"
IF ERRORLEVEL 1 THEN SET ANY_ERRORS=1
java -jar "%BATCH_SCRIPT_FOLDER_PATHNAME%historical-backup--SNAPSHOT-jar-with-dependencies.jar"  "%LogFilePathname%_PublicVideos" "%PUBLIC%\Videos" "%BackupDestination%\Public-Videos"
IF ERRORLEVEL 1 THEN SET ANY_ERRORS=1
java -jar "%BATCH_SCRIPT_FOLDER_PATHNAME%historical-backup--SNAPSHOT-jar-with-dependencies.jar"  "%LogFilePathname%_PublicDesktop" "%PUBLIC%\Desktop" "%BackupDestination%\Public-Desktop"
IF ERRORLEVEL 1 THEN SET ANY_ERRORS=1


@REM Valid only for U.S. (English) locale
FOR /F "tokens=2-4 delims=/ " %%i IN ('echo %date%') DO SET MONTH=%%i& SET DAY=%%j& SET YEAR=%%k

@REM Valid only for U.S. (English) locale
FOR /F "tokens=1-3 delims=:." %%i IN ('echo %time: =0%') DO SET HOUR=%%i& SET MINUTE=%%j& SET SECOND=%%k


IF "%ANY_ERRORS%"=="0" THEN echo "Got to end of all on %YEAR%-%MONTH%-%DAY%  %HOUR%-%MINUTE%-%SECOND%" > "%LogFilePathname%\..\LAST_COMPLETED.LOG"
