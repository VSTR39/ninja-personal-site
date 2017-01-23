CREATE DATABASE self_explanatory_site;

USE self_explanatory_site;

CREATE TABLE registered_users(
id INT AUTO_INCREMENT,
username VARCHAR(50) UNIQUE NOT NULL,
password VARCHAR(50) NOT NULL,
email VARCHAR(50) UNIQUE NOT NULL,
PRIMARY KEY (id)
)Engine = InnoDB;

CREATE TABLE admins(
id INT AUTO_INCREMENT,
registered_users_id INT NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(registered_users_id) REFERENCES registered_users(id)
)Engine = InnoDB;

CREATE TABLE sections(
id INT AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
PRIMARY KEY (id)
)Engine = InnoDB;

CREATE TABLE articles(
id INT AUTO_INCREMENT,
author_id INT NOT NULL,
section_id INT NOT NULL,
timestamp TIMESTAMP,
title VARCHAR(128) UNIQUE NOT NULL,
content TEXT NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY (author_id) REFERENCES admins(id),
FOREIGN KEY(section_id) REFERENCES sections(id)
)Engine = InnoDB;


