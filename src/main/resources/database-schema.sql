-- CREATE DATABASE spc;
USE spc;

CREATE SCHEMA IF NOT EXISTS `spc`;

CREATE TABLE IF NOT EXISTS `spc`.`segments`
(
    `id`                     VARCHAR(40) NOT NULL,
    `segment_code`           VARCHAR(10) NOT NULL,
    `airline_code`           CHAR(2)     NOT NULL,
    `departure_airport_code` CHAR(3)     NOT NULL,
    `departure_date`         TIMESTAMP   NOT NULL,
    `departure_terminal`     VARCHAR(3)  NULL,
    `arrival_airport_code`   CHAR(3)     NOT NULL,
    `arrival_date`           TIMESTAMP   NOT NULL,
    `arrival_terminal`       VARCHAR(3)  NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `spc`.`fares`
(
    `id`         VARCHAR(40)    NOT NULL,
    `price`      DECIMAL(16, 4) NOT NULL,
    `name`       VARCHAR(50)    NOT NULL,
    `fare_class` VARCHAR(2)     NOT NULL,
    `segment_id` VARCHAR(40)    NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`segment_id`) REFERENCES `spc`.`segments` (`id`)
);

CREATE TABLE IF NOT EXISTS `spc`.`fares_properties`
(
    `id`      VARCHAR(40)  NOT NULL,
    `key`     VARCHAR(150) NOT NULL,
    `value`   VARCHAR(250) NULL,
    `fare_id` VARCHAR(40)  NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`fare_id`) REFERENCES `spc`.`fares` (`id`)
);

CREATE TABLE IF NOT EXISTS `spc`.`flights`
(
    `id`                     VARCHAR(40) NOT NULL,
    `departure_airport_code` CHAR(3)     NOT NULL,
    `arrival_airport_code`   CHAR(3)     NOT NULL,
    `airline_code`           CHAR(2)     NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `spc`.`rules`
(
    `id`        VARCHAR(40)  NOT NULL,
    `key`       VARCHAR(150) NOT NULL,
    `value`     VARCHAR(250) NOT NULL,
    `flight_id` VARCHAR(40)  NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`flight_id`) REFERENCES `spc`.`flights` (`id`)
);
CREATE TABLE IF NOT EXISTS `spc`.`flights_segments`
(
    `id`         VARCHAR(40) NOT NULL,
    `flight_id`  VARCHAR(40) NOT NULL,
    `segment_id` VARCHAR(40) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`flight_id`) REFERENCES `spc`.`flights` (`id`),
    FOREIGN KEY (`segment_id`) REFERENCES `spc`.`segments` (`id`)
);