DROP SCHEMA IF EXISTS `mmtdb`;
CREATE SCHEMA IF NOT EXISTS `mmtdb` DEFAULT CHARACTER SET latin1;
USE `mmtdb`;

-- -----------------------------------------------------
-- Table `mmtdb`.`service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mmtdb`.`service` (
  `service_type` VARCHAR(40) NOT NULL,
  `service_desc` VARCHAR(100) NOT NULL,
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