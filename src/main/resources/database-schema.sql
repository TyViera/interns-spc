-- CREATE DATABASE spc;
-- USE spc;


CREATE SCHEMA IF NOT EXISTS `spc`;

CREATE TABLE IF NOT EXISTS `spc`.`segments` (
  `id` VARCHAR(40) NOT NULL,
  `airline_code` CHAR(2) NOT NULL,
  `departure_airport_code` CHAR(3) NOT NULL,
  `departure_date` TIMESTAMP NOT NULL,
  `departure_terminal` VARCHAR(3) NULL,
  `arrival_airport_code` CHAR(3) NOT NULL,
  `arrival_date` TIMESTAMP NOT NULL,
  `arrival_terminal` VARCHAR(3) NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `spc`.`fares` (
  `id` VARCHAR(40) NOT NULL,
  `price` DECIMAL(16, 4) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
);