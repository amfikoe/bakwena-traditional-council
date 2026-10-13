# Bakwena Traditional Council - cleaned Maven project

This repository contains a cleaned and simplified Java Swing application (Maven) for the Bakwena Traditional Council. It uses JDBC (MySQL) with PreparedStatements and safe resource handling.

Database setup (example SQL):

CREATE DATABASE IF NOT EXISTS bakwena;
USE bakwena;

CREATE TABLE IF NOT EXISTS admin (
  id INT AUTO_INCREMENT PRIMARY KEY,
  Username VARCHAR(100) NOT NULL,
  Password VARCHAR(100) NOT NULL,
  User_Type VARCHAR(20) NOT NULL
);

INSERT INTO admin (Username, Password, User_Type) VALUES ('admin','admin123','ADMIN'),('user1','user123','USER');

CREATE TABLE IF NOT EXISTS members (
  Member_id INT AUTO_INCREMENT PRIMARY KEY,
  Doc_No VARCHAR(100),
  Ref_No VARCHAR(100),
  Allocation_Date VARCHAR(100),
  Last_Name VARCHAR(100),
  First_Name VARCHAR(100),
  Identity_No VARCHAR(100),
  Gender VARCHAR(20),
  P_Address VARCHAR(255),
  C_Details VARCHAR(255),
  M_Status VARCHAR(50),
  Kgoro VARCHAR(100),
  Village VARCHAR(100),
  Section VARCHAR(100),
  Extension VARCHAR(100),
  Stand_No VARCHAR(100),
  Stand_Size VARCHAR(50),
  F_Name1 VARCHAR(100),
  ID_No1 VARCHAR(100),
  Relationship1 VARCHAR(100)
  -- expand columns as needed
);
