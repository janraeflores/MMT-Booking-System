DROP SCHEMA IF EXISTS `mmtdb`;
CREATE SCHEMA IF NOT EXISTS `mmtdb` DEFAULT CHARACTER SET latin1;
USE `mmtdb`;

-- -----------------------------------------------------
-- Table `mmtdb`.`service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmtdb`.`service` (
  `service_id` INT(11) NOT NULL AUTO_INCREMENT,
  `service_type` VARCHAR(40) NOT NULL,
  `service_desc` VARCHAR(200) NOT NULL,
  `service_cost` DOUBLE NOT NULL,
  PRIMARY KEY (`service_id`));


-- -----------------------------------------------------
-- Table `mmtdb`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmtdb`.`role` (
  `role_id` INT(11) NOT NULL,
  `role_name` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`role_id`));


-- -----------------------------------------------------
-- Table `mmtdb`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmtdb`.`account` (
  `username` VARCHAR(20) NOT NULL,
  `full_name` VARCHAR(20) NOT NULL,
  `email` VARCHAR(40) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT '1',
  `password` VARCHAR(30) NOT NULL,
  `phone` VARCHAR(16) NOT NULL,
  `role` INT(11) NOT NULL,
  `birthdate` DATETIME,
  `address` VARCHAR(50) NOT NULL,
  `medical_info` VARCHAR(100),
  PRIMARY KEY (`username`),
  CONSTRAINT `fk_account_role`
    FOREIGN KEY (`role`)
    REFERENCES `mmtdb`.`role` (`role_id`));

-- -----------------------------------------------------
-- Table `mmtdb`.`emergency_contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmtdb`.`emergency_contact` (
  `ec_id` INT(11) NOT NULL AUTO_INCREMENT,
  `ec_name` VARCHAR(40),
  `ec_phone` VARCHAR(16),
  `ec_email` VARCHAR(40),
  `ec_relation` VARCHAR(30),
  `fk_account` VARCHAR(20),
  PRIMARY KEY (`ec_id`),
  CONSTRAINT `fk_account_ec`
    FOREIGN KEY (`fk_account`) REFERENCES `mmtdb`.`account` (`username`));


-- -----------------------------------------------------
-- Table `mmtdb`.`appointment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmtdb`.`appointment` (
  `appointment_id` INT(11) NOT NULL AUTO_INCREMENT,
  `account` VARCHAR(20) NOT NULL,
  `service` INT(11) NOT NULL,
  `appointment_address` VARCHAR(50) NOT NULL,
  `appointment_date` DATETIME NOT NULL,
  `status` BOOLEAN NOT NULL,
  `additional_info` VARCHAR(100),
  `duration` INT(11) NOT NULL,
  PRIMARY KEY (`appointment_id`),
  CONSTRAINT `fk_appointment_account`
    FOREIGN KEY (`account`)
    REFERENCES `mmtdb`.`account` (`username`),
  CONSTRAINT `fk_appointment_service`
    FOREIGN KEY (`service`)
    REFERENCES `mmtdb`.`service` (`service_id`));

-- ------------
-- CREATE ROLES
-- ------------
INSERT INTO `role` VALUES (1, 'administrator');
INSERT INTO `role` VALUES (2, 'client');


INSERT INTO `service` (`service_type`,`service_desc`,`service_cost`)
  VALUES ('Therapeutic Massage','A therapeutic massage is a type of massage that aims to relieve pain, reduce stress, and improve physical and mental well-being.',0.00);
INSERT INTO `service` (`service_type`,`service_desc`,`service_cost`)
  VALUES ('Deep Tissue Massage','A deep tissue massage is a type of massage therapy that focuses on realigning deeper layers of muscles and connective tissue.',0.00);
INSERT INTO `service` (`service_type`,`service_desc`,`service_cost`)
  VALUES ('Relaxation Massage','A relaxation massage, also known as a Swedish massage, is a type of therapeutic massage designed to promote overall relaxation and well-being.',0.00);
INSERT INTO `service` (`service_type`,`service_desc`,`service_cost`)
  VALUES ('Lymphatic Massage','Lymphatic massage is a therapeutic massage technique that focuses on the network of vessels and tissues in the body responsible for filtering waste and excess fluids from the tissues',0.00);
INSERT INTO `service` (`service_type`,`service_desc`,`service_cost`)
  VALUES ('Hot Stone Massage','Hot stone massage is a type of massage therapy that uses smooth, heated stones, usually made of basalt, a type of volcanic rock that retains heat well.',0.00);
INSERT INTO `service` (`service_type`,`service_desc`,`service_cost`)
  VALUES ('Cupping','Cupping is a traditional Chinese therapy that involves placing suction cups on the skin to create a vacuum effect.',0.00);
INSERT INTO `service` (`service_type`,`service_desc`,`service_cost`)
  VALUES ('Facial','A facial is a cosmetic treatment for the face that is designed to cleanse, exfoliate, and nourish the skin.',0.00);



INSERT INTO `account` (`full_name`, `email`, `active` ,`username`, `password`, `phone`, `role`, `birthdate`, `address`)
  VALUES ('bob vance', 'bob@gmail.com', true, 'bob', 'password', '(403) 777-9999', 2, '1997-03-25', '808 Sumwhere St Bobtown, BOB');
INSERT INTO `account` (`full_name`, `email`, `active` ,`username`, `password`, `phone`, `role`, `address`)
  VALUES ('admin one', 'admin@gmail.com', true, 'admin', 'password', '(111) 000-2222', 1, '58 Fredson Dr SE Calgary, AB T2H 1E1');

INSERT INTO `emergency_contact` (`ec_name`, `ec_phone`, `ec_email`, `ec_relation`, `fk_account`)
  VALUES ('Bobby Vance', '(999) 888-7777', 'Bobby@gmail.com', 'Sibling', 'bob');

