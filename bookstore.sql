-- Create Database
CREATE DATABASE IF NOT EXISTS bookstore;
USE bookstore;

-- Create Users Table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Insert Default Admin User
-- Password is 'admin' (in a real app, this should be hashed)
INSERT INTO users (username, password) VALUES ('admin', 'admin');

-- Create Buku Table
CREATE TABLE IF NOT EXISTS buku (
    id INT AUTO_INCREMENT PRIMARY KEY,
    judul VARCHAR(100) NOT NULL,
    penulis VARCHAR(100) NOT NULL,
    penerbit VARCHAR(100) NOT NULL,
    harga INT NOT NULL,
    tahun INT NOT NULL
);

-- Insert Dummy Data for Buku
INSERT INTO buku (judul, penulis, penerbit, harga, tahun) VALUES 
('Laskar Pelangi', 'Andrea Hirata', 'Bentang Pustaka', 75000, 2005),
('Bumi Manusia', 'Pramoedya Ananta Toer', 'Hasta Mitra', 120000, 1980),
('Filosofi Kopi', 'Dee Lestari', 'Truedee', 65000, 2006);
