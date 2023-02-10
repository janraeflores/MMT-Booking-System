DROP SCHEMA IF EXISTS `mmtdb`;
CREATE SCHEMA IF NOT EXISTS `mmtdb` DEFAULT CHARACTER SET latin1;
USE `mmtdb`;

-- -----------------------------------------------------
-- Table `mmtdb`.`service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmtdb`.`service` (
  `service_type` VARCHAR(40) NOT NULL,
  `service_desc` VARCHAR(200) NOT NULL,
  `service_cost` DOUBLE NOT NULL,
  PRIMARY KEY (`service_type`));

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
  `email` VARCHAR(40) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT '1',
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(30) NOT NULL,
  `role` INT(11) NOT NULL,
  PRIMARY KEY (`email`),
  CONSTRAINT `fk_account_role`
    FOREIGN KEY (`role`)
    REFERENCES `mmtdb`.`role` (`role_id`));

-- -----------------------------------------------------
-- Table `mmtdb`.`emergency_contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmtdb`.`emergency_contact` (
  `ec_name` VARCHAR(40) NOT NULL,
  `ec_phone` INT(10) NOT NULL,
  `ec_email` VARCHAR(40),
  PRIMARY KEY (`ec_name`));

-- -----------------------------------------------------
-- Table `mmtdb`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmtdb`.`client` (
  `client_id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(20) NOT NULL,
  `last_name` VARCHAR(20) NOT NULL,
  `contact_email` VARCHAR(40) NOT NULL,
  `phone` INT(10) NOT NULL,
  `birthdate` DATE,
  `address` VARCHAR(50),
  `ec_contact` VARCHAR(40),
  `medical_info` VARCHAR(100),
  PRIMARY KEY (`client_id`),
  CONSTRAINT `fk_client_ec`
    FOREIGN KEY (`ec_contact`)
    REFERENCES `mmtdb`.`emergency_contact` (`ec_name`),
  CONSTRAINT `fk_client_contact_email`
    FOREIGN KEY (`contact_email`)
    REFERENCES `mmtdb`.`account` (`email`));

-- -----------------------------------------------------
-- Table `mmtdb`.`appointment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmtdb`.`appointment` (
  `client` INT(11) NOT NULL,
  `service` VARCHAR(40) NOT NULL,
  `appointment_address` VARCHAR(50),
  `appointment_date` DATE NOT NULL,
  `start_time` TIME NOT NULL,
  `end_time` TIME NOT NULL,
  `status` BOOLEAN NOT NULL,
  `additional_info` VARCHAR(100),
  CONSTRAINT `fk_appointment_client`
    FOREIGN KEY (`client`)
    REFERENCES `mmtdb`.`client` (`client_id`),
  CONSTRAINT `fk_appointment_service`
    FOREIGN KEY (`service`)
    REFERENCES `mmtdb`.`service` (`service_type`));
  `service_desc` VARCHAR(200) NOT NULL,
  `service_cost` DOUBLE NOT NULL,
  PRIMARY KEY (`service_type`));

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
  `email` VARCHAR(40) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT '1',
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(30) NOT NULL,
  `role` INT(11) NOT NULL,
  PRIMARY KEY (`email`),
  CONSTRAINT `fk_account_role`
    FOREIGN KEY (`role`)
    REFERENCES `mmtdb`.`role` (`role_id`));

-- -----------------------------------------------------
-- Table `mmtdb`.`emergency_contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmtdb`.`emergency_contact` (
  `ec_name` VARCHAR(40) NOT NULL,
  `ec_phone` INT(10) NOT NULL,
  `ec_email` VARCHAR(40),
  PRIMARY KEY (`ec_name`));

-- -----------------------------------------------------
-- Table `mmtdb`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmtdb`.`client` (
  `client_id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(20) NOT NULL,
  `last_name` VARCHAR(20) NOT NULL,
  `contact_email` VARCHAR(40) NOT NULL,
  `phone` INT(10) NOT NULL,
  `birthdate` DATE,
  `address` VARCHAR(50),
  `ec_contact` VARCHAR(40),
  `medical_info` VARCHAR(100),
  PRIMARY KEY (`client_id`),
  CONSTRAINT `fk_client_ec`
    FOREIGN KEY (`ec_contact`)
    REFERENCES `mmtdb`.`emergency_contact` (`ec_name`),
  CONSTRAINT `fk_client_contact_email`
    FOREIGN KEY (`contact_email`)
    REFERENCES `mmtdb`.`account` (`email`));

-- -----------------------------------------------------
-- Table `mmtdb`.`appointment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmtdb`.`appointment` (
  `client` INT(11) NOT NULL,
  `service` VARCHAR(40) NOT NULL,
  `appointment_address` VARCHAR(50),
  `appointment_date` DATE NOT NULL,
  `start_time` TIME NOT NULL,
  `end_time` TIME NOT NULL,
  `status` BOOLEAN NOT NULL,
  `additional_info` VARCHAR(100),
  CONSTRAINT `fk_appointment_client`
    FOREIGN KEY (`client`)
    REFERENCES `mmtdb`.`client` (`client_id`),
  CONSTRAINT `fk_appointment_service`
    FOREIGN KEY (`service`)
    REFERENCES `mmtdb`.`service` (`service_type`));

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

INSERT INTO `account` (`email`, `active` ,`username`, `password`, `role`)
  VALUES ('bob@gmail.com', 1 ,'bobson1', 'password', 2);