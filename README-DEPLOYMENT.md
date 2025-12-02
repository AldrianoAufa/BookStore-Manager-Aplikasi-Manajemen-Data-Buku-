# Panduan Deployment BookStore Manager

## Cara Menjalankan Aplikasi dari Folder Dist

### Langkah 1: Build Project di NetBeans
1. Buka project di NetBeans
2. Klik kanan project → **Clean and Build**
3. Tunggu sampai build selesai

### Langkah 2: Setup Folder Dist
Jalankan script setup untuk copy JDBC driver:
```powershell
.\setup-dist.ps1
```

Script ini akan:
- Membuat folder `dist\lib`
- Copy JDBC driver ke `dist\lib`
- Validasi file JAR utama

### Langkah 3: Jalankan Aplikasi
```powershell
.\run-app.ps1
```

## Cara Manual (Tanpa Script)

### 1. Copy JDBC Driver
```powershell
# Buat folder lib
mkdir dist\lib

# Copy JDBC driver
copy src\Connector\mysql-connector-java-8.0.17.jar dist\lib\
```

### 2. Jalankan dengan Java
```powershell
java -cp "dist\BookStore_Manager.jar;dist\lib\*" bookstore.manager.BookStoreManager
```

## Troubleshooting

### Error: "Cannot invoke createStatement() because 'c' is null"
**Penyebab**: JDBC driver tidak ditemukan dalam classpath

**Solusi**:
1. Pastikan file `dist\lib\mysql-connector-java-8.0.17.jar` ada
2. Jalankan ulang `setup-dist.ps1`
3. Cek apakah MySQL server sudah berjalan

### Error: "ClassNotFoundException: com.mysql.cj.jdbc.Driver"
**Penyebab**: JDBC driver tidak ter-load

**Solusi**:
1. Pastikan menjalankan dengan classpath yang benar: `-cp "dist\BookStore_Manager.jar;dist\lib\*"`
2. Gunakan script `run-app.ps1` untuk otomatis

### Error: "Communications link failure"
**Penyebab**: Tidak bisa connect ke MySQL

**Solusi**:
1. Pastikan MySQL server sudah berjalan
2. Cek username/password di `Koneksi.java`
3. Pastikan database `bookstore` sudah dibuat

## Distribusi ke User Lain

Jika ingin memberikan aplikasi ke orang lain:

1. **Copy folder dist** beserta isinya:
   ```
   dist/
   ├── BookStore_Manager.jar
   └── lib/
       └── mysql-connector-java-8.0.17.jar
   ```

2. **Berikan instruksi**:
   ```powershell
   java -cp "BookStore_Manager.jar;lib\*" bookstore.manager.BookStoreManager
   ```

3. **Atau buat file .bat** untuk Windows:
   ```batch
   @echo off
   java -cp "BookStore_Manager.jar;lib\*" bookstore.manager.BookStoreManager
   pause
   ```

## Catatan Penting

- **Java Version**: Pastikan menggunakan Java 8 atau lebih tinggi
- **MySQL**: Aplikasi membutuhkan MySQL server yang berjalan
- **Database**: Database `bookstore` harus sudah dibuat dan tabel sudah di-import dari `bookstore.sql`
