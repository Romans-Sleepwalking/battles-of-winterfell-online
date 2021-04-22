DROP DATABASE IF EXISTS `sql_Battles_of_Winterfell_Online`;
CREATE DATABASE `sql_Battles_of_Winterfell_Online`;
USE `sql_Battles_of_Winterfell_Online`;

SET NAMES utf8 ;
SET character_set_client = utf8mb4 ;

CREATE TABLE `accounts` (
    `account_id` smallint(5) NOT NULL AUTO_INCREMENT,
    `username` varchar(10) NOT NULL,
    `password` varchar(20) NOT NULL,
    `wins` smallint(2) NOT NULL DEFAULT 0,
    `loses` smallint(2) NOT NULL DEFAULT 0,
    PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `accounts` (username, password, wins, loses)
    VALUES ('admin', 'admin', 6, 9),('test', 'case12345', 0, 5);
