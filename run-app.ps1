# Script untuk menjalankan aplikasi BookStore Manager
# Pastikan sudah menjalankan setup-dist.ps1 terlebih dahulu

Write-Host "=== Running BookStore Manager ===" -ForegroundColor Cyan

# Cek apakah file JAR ada
$mainJar = "dist\BookStore_Manager.jar"
if (-not (Test-Path $mainJar)) {
    Write-Host "[ERROR] File JAR tidak ditemukan: $mainJar" -ForegroundColor Red
    Write-Host "Silakan build project di NetBeans (Clean & Build) dan jalankan setup-dist.ps1" -ForegroundColor Yellow
    exit 1
}

# Cek apakah JDBC driver ada
$jdbcDriver = "dist\lib\mysql-connector-java-8.0.17.jar"
if (-not (Test-Path $jdbcDriver)) {
    Write-Host "[ERROR] JDBC driver tidak ditemukan: $jdbcDriver" -ForegroundColor Red
    Write-Host "Silakan jalankan setup-dist.ps1 terlebih dahulu" -ForegroundColor Yellow
    exit 1
}

Write-Host "[OK] Semua file ditemukan, menjalankan aplikasi..." -ForegroundColor Green
Write-Host ""

# Jalankan aplikasi dengan classpath yang benar
java -cp "$mainJar;dist\lib\*" bookstore.manager.BookStoreManager

# Jika ada error
if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "[ERROR] Aplikasi berhenti dengan error" -ForegroundColor Red
    Write-Host "Pastikan MySQL server sudah berjalan dan database 'bookstore' sudah dibuat" -ForegroundColor Yellow
}
