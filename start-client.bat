@echo off
setlocal
call "%~dp0gradlew.bat" runClient
if errorlevel 1 pause
