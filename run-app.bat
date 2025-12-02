@echo off
REM Script untuk menjalankan BookStore Manager
REM Untuk Windows Command Prompt

echo === Running BookStore Manager ===
echo.

REM Cek apakah JAR ada
if not exist "dist\BookStore_Manager.jar" (
    echo [ERROR] File JAR tidak ditemukan!
    echo Silakan build project di NetBeans terlebih dahulu
    pause
    exit /b 1
)

REM Cek apakah JDBC driver ada
if not exist "dist\lib\mysql-connector-java-8.0.17.jar" (
    echo [ERROR] JDBC driver tidak ditemukan!
    echo Silakan jalankan setup-dist.ps1 terlebih dahulu
    pause
    exit /b 1
)

echo [OK] Menjalankan aplikasi...
echo.

REM Jalankan aplikasi
java -cp "dist\BookStore_Manager.jar;dist\lib\*" bookstore.manager.BookStoreManager

echo.
echo Aplikasi ditutup.
pause
