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
    `slot1_id` smallint(5) DEFAULT NULL,
    `slot2_id` smallint(5) DEFAULT NULL,
    `slot3_id` smallint(5) DEFAULT NULL,
    `slot4_id` smallint(5) DEFAULT NULL,
    PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `characters` (
    `character_id` smallint(5) NOT NULL AUTO_INCREMENT,
    `owner_id` smallint (5) NOT NULL,
    `name` varchar(30) NOT NULL,
    `class` varchar(16) NOT NULL,
    `LVL` smallint(2) NOT NULL DEFAULT 1,
    `ATR` char(3) NOT NULL,
    `STR` smallint(2) NOT NULL DEFAULT 0,
    `AGI` smallint(2) NOT NULL DEFAULT 0,
    `INT` smallint(2) NOT NULL DEFAULT 0,
    `modelPath` varchar(10) NOT NULL,
    PRIMARY KEY (`character_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `accounts` (account_id, username, password, wins, loses, slot1_id, slot2_id, slot3_id, slot4_id)
    VALUES (1, 'admin', 'meow6969', 6, 9, 1, 2, 3, 4),
           (2, 'test', 'case12345', 9, 6, 5, 6, 7, 8);

INSERT INTO `characters` (`owner_id`, `name`, class, LVL, ATR, STR, AGI, `INT`, modelPath)
    VALUES (1, 'Johny Cocaine',  'Crusader',    18, 'STR', 16, 9,  7,  'Snow'),
           (1, 'Commander',      'Commander',   8,  'STR', 11, 7,  3,  'Commander'),
           (1, 'Emils',          'Knight',      7,  'STR', 7,  4,  4,  'Warrior'),
           (1, 'Emils',          'Knight',      6,  'STR', 8,  3,  3,  'Warrior'),
           (2, 'The Night King', 'Necromancer', 30, 'INT', 9,  14, 25, 'King'),
           (2, 'Kevin',          'Necromancer', 9,  'INT', 12, 7,  15, 'Walker'),
           (2, 'Dead Body',      'Zombie',      3,  'STR', 4,  9,  3,  'Zombie'),
           (2, 'Old Woman',      'Zombie',      14, 'STR', 7,  14, 4,  'Zombie');