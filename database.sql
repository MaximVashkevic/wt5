-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`currencies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`currencies` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` CHAR(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`addresses` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(100) NOT NULL,
  `region` VARCHAR(50) NOT NULL,
  `district` VARCHAR(50) NOT NULL,
  `settlement_type` VARCHAR(20) NOT NULL,
  `settlement_name` VARCHAR(50) NOT NULL,
  `street_type` VARCHAR(20) NOT NULL,
  `street_name` VARCHAR(50) NOT NULL,
  `house` SMALLINT(2) UNSIGNED NOT NULL,
  `building` TINYINT(1) UNSIGNED NULL,
  `flat` INT UNSIGNED NULL,
  `zipcode` VARCHAR(10) NOT NULL,
  `phone_number` VARCHAR(20) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`passport_data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`passport_data` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `series` CHAR(2) NOT NULL,
  `number` INT UNSIGNED NOT NULL,
  `personal_number` CHAR(14) NOT NULL,
  `issue_date` DATE NOT NULL,
  `validity_date` DATE NOT NULL,
  `birth_date` DATE NOT NULL,
  `sex` ENUM('man', 'woman') NOT NULL,
  `authority` VARCHAR(100) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `surname` VARCHAR(50) NOT NULL,
  `middlename` VARCHAR(30) NOT NULL,
  `name_eng` VARCHAR(50) NOT NULL,
  `surname_eng` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `personal_number_UNIQUE` (`personal_number` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`roles` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `role_UNIQUE` (`role` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `id` INT UNSIGNED ZEROFILL NOT NULL,
  `login` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `role_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  INDEX `role_id_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `mydb`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`accounts` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `currency_id` INT UNSIGNED NOT NULL,
  `account_number` CHAR(28) NOT NULL,
  `is_locked` TINYINT NOT NULL,
  `amount` DECIMAL(65,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `account_number_UNIQUE` (`account_number` ASC) VISIBLE,
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `currency_id_idx` (`currency_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `currency_id`
    FOREIGN KEY (`currency_id`)
    REFERENCES `mydb`.`currencies` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cards` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `account_id` INT UNSIGNED NOT NULL,
  `number_hash` VARCHAR(255) NOT NULL,
  `expiration_date` DATE NOT NULL,
  `last_digits` SMALLINT(2) UNSIGNED NOT NULL,
  `pin_hash` VARCHAR(255) NOT NULL,
  `cvv_hash` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `account_id_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `account_id`
    FOREIGN KEY (`account_id`)
    REFERENCES `mydb`.`accounts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`transactions` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `card_id` INT UNSIGNED NOT NULL,
  `amount` DECIMAL(65,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `card_id_idx` (`card_id` ASC) VISIBLE,
  CONSTRAINT `card_id`
    FOREIGN KEY (`card_id`)
    REFERENCES `mydb`.`cards` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`client_data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`client_data` (
  `id` INT UNSIGNED NOT NULL,
  `client_id` INT UNSIGNED NOT NULL,
  `passport_id` INT UNSIGNED NOT NULL,
  `address_id` INT UNSIGNED NOT NULL,
  `codeword` VARCHAR(45) NOT NULL,
  INDEX `passport_id_idx` (`passport_id` ASC) VISIBLE,
  INDEX `address_id_idx` (`address_id` ASC) VISIBLE,
  UNIQUE INDEX `client_id_UNIQUE` (`client_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `passport_id`
    FOREIGN KEY (`passport_id`)
        REFERENCES `mydb`.`passport_data` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
  CONSTRAINT `address_id`
      FOREIGN KEY (`address_id`)
          REFERENCES `mydb`.`addresses` (`id`)
          ON DELETE NO ACTION
          ON UPDATE NO ACTION,
  CONSTRAINT `client_id`
      FOREIGN KEY (`client_id`)
          REFERENCES `mydb`.`users` (`id`)
          ON DELETE NO ACTION
          ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `mydb`.`roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`roles` (`id`, `role`)
VALUES (1, 'User');

COMMIT;

