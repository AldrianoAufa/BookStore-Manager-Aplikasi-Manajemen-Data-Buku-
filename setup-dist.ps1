# Script untuk setup folder dist dengan JDBC driver
# Jalankan script ini setelah build di NetBeans

Write-Host "=== Setup Dist Folder ===" -ForegroundColor Cyan

# 1. Buat folder lib di dist jika belum ada
$distLibPath = "dist\lib"
if (-not (Test-Path $distLibPath)) {
    New-Item -ItemType Directory -Path $distLibPath -Force | Out-Null
    Write-Host "[OK] Folder dist\lib dibuat" -ForegroundColor Green
} else {
    Write-Host "[OK] Folder dist\lib sudah ada" -ForegroundColor Green
}

# 2. Copy JDBC driver ke dist\lib
$jdbcSource = "src\Connector\mysql-connector-java-8.0.17.jar"
$jdbcDest = "dist\lib\mysql-connector-java-8.0.17.jar"

if (Test-Path $jdbcSource) {
    Copy-Item -Path $jdbcSource -Destination $jdbcDest -Force
    Write-Host "[OK] JDBC driver berhasil dicopy ke dist\lib" -ForegroundColor Green
} else {
    Write-Host "[ERROR] JDBC driver tidak ditemukan di: $jdbcSource" -ForegroundColor Red
    exit 1
}

# 3. Cek apakah JAR utama ada
$mainJar = "dist\BookStore_Manager.jar"
if (-not (Test-Path $mainJar)) {
    Write-Host "[WARNING] File JAR utama tidak ditemukan: $mainJar" -ForegroundColor Yellow
    Write-Host "Silakan build project di NetBeans terlebih dahulu (Clean & Build)" -ForegroundColor Yellow
} else {
    Write-Host "[OK] File JAR utama ditemukan" -ForegroundColor Green
}

Write-Host ""
Write-Host "=== Setup Selesai ===" -ForegroundColor Cyan
Write-Host "Untuk menjalankan aplikasi, gunakan: .\run-app.ps1" -ForegroundColor Yellow
