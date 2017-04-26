-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema arch_inc
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema arch_inc
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `arch_inc` DEFAULT CHARACTER SET utf8 ;

GRANT ALL PRIVILEGES ON arch_inc.* TO 'jack_veromeev'@'localhost'
  IDENTIFIED BY 'veromeev9111996' ;

USE `arch_inc` ;

-- -----------------------------------------------------
-- Table `arch_inc`.`Country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `arch_inc`.`Country` (
  `id_Country` INT(11) NOT NULL AUTO_INCREMENT,
  `Country` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_Country`),
  UNIQUE INDEX `id_Country_UNIQUE` (`id_Country` ASC),
  UNIQUE INDEX `Name_UNIQUE` (`Country` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `arch_inc`.`Region`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `arch_inc`.`Region` (
  `id_Region` INT(11) NOT NULL AUTO_INCREMENT,
  `Region` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_Region`),
  UNIQUE INDEX `id_Region_UNIQUE` (`id_Region` ASC),
  UNIQUE INDEX `Name_UNIQUE` (`Region` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `arch_inc`.`Town`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `arch_inc`.`Town` (
  `id_Town` INT(11) NOT NULL AUTO_INCREMENT,
  `Town` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_Town`),
  UNIQUE INDEX `id_Town_UNIQUE` (`id_Town` ASC),
  UNIQUE INDEX `Name_UNIQUE` (`Town` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `arch_inc`.`Addres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `arch_inc`.`Addres` (
  `id_Addres` INT(11) NOT NULL AUTO_INCREMENT,
  `id_Town` INT(11) NOT NULL,
  `Street` VARCHAR(255) NULL DEFAULT NULL,
  `House` INT(11) NOT NULL,
  `id_Country` INT(11) NOT NULL,
  `id_Region` INT(11) NOT NULL,
  PRIMARY KEY (`id_Addres`, `id_Town`, `id_Region`, `id_Country`),
  INDEX `fk_Town_idx` (`id_Town` ASC),
  INDEX `fk_Region_idx` (`id_Region` ASC),
  INDEX `fk_Country_idx` (`id_Country` ASC),
  CONSTRAINT `fk_Country`
    FOREIGN KEY (`id_Country`)
    REFERENCES `arch_inc`.`Country` (`id_Country`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Region`
    FOREIGN KEY (`id_Region`)
    REFERENCES `arch_inc`.`Region` (`id_Region`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Town`
    FOREIGN KEY (`id_Town`)
    REFERENCES `arch_inc`.`Town` (`id_Town`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `arch_inc`.`Usertype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `arch_inc`.`Usertype` (
  `id_Usertype` INT(11) NOT NULL AUTO_INCREMENT,
  `Type` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_Usertype`),
  UNIQUE INDEX `id_Usertype_UNIQUE` (`id_Usertype` ASC),
  UNIQUE INDEX `Type_UNIQUE` (`Type` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `arch_inc`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `arch_inc`.`User` (
  `id_User` INT(11) NOT NULL AUTO_INCREMENT,
  `id_Usertype` INT(11) NOT NULL,
  `Login` VARCHAR(255) NOT NULL,
  `Password` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id_User`, `id_Usertype`),
  UNIQUE INDEX `id_User_UNIQUE` (`id_User` ASC),
  UNIQUE INDEX `Name_UNIQUE` (`Login` ASC),
  INDEX `fk_Usertype_idx` (`id_Usertype` ASC),
  CONSTRAINT `fk_Usertype`
    FOREIGN KEY (`id_Usertype`)
    REFERENCES `arch_inc`.`Usertype` (`id_Usertype`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `arch_inc`.`CustomerOrder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `arch_inc`.`CustomerOrder` (
  `id_Order` INT(11) NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(255) NOT NULL,
  `Description` VARCHAR(1000) NOT NULL,
  `id_User` INT(11) NOT NULL,
  `OrderState` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`id_Order`, `id_User`),
  UNIQUE INDEX `id_Order_UNIQUE` (`id_Order` ASC),
  UNIQUE INDEX `Title_UNIQUE` (`Title` ASC),
  INDEX `fk_Manager_ID_idx` (`id_User` ASC),
  CONSTRAINT `fk_User_ID`
    FOREIGN KEY (`id_User`)
    REFERENCES `arch_inc`.`User` (`id_User`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `arch_inc`.`Education`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `arch_inc`.`Education` (
  `id_Education` INT(11) NOT NULL AUTO_INCREMENT,
  `Education` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_Education`),
  UNIQUE INDEX `id_Education_UNIQUE` (`id_Education` ASC),
  UNIQUE INDEX `Status_UNIQUE` (`Education` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `arch_inc`.`Qualification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `arch_inc`.`Qualification` (
  `id_Qualification` INT(11) NOT NULL AUTO_INCREMENT,
  `Qualification` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_Qualification`),
  UNIQUE INDEX `id_Qualification_UNIQUE` (`id_Qualification` ASC),
  UNIQUE INDEX `Status_UNIQUE` (`Qualification` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `arch_inc`.`Employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `arch_inc`.`Employee` (
  `id_Employee` INT(11) NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(40) NOT NULL,
  `SecondName` VARCHAR(40) NOT NULL,
  `LastName` VARCHAR(40) NOT NULL,
  `Age` INT(11) NOT NULL,
  `Gender` VARCHAR(1) NOT NULL,
  `id_Qualification` INT(11) NOT NULL,
  `ExperienceInYears` INT(11) NOT NULL,
  `id_Education` INT(11) NOT NULL,
  `Phone_number` VARCHAR(20) NOT NULL,
  `id_Addres` INT(11) NOT NULL,
  `SalaryPerDay` INT(11) NOT NULL,
  `id_Order` INT(11) NOT NULL,
  PRIMARY KEY (`id_Employee`, `id_Qualification`, `id_Education`, `id_Addres`, `id_Order`),
  UNIQUE INDEX `Phone_number_UNIQUE` (`Phone_number` ASC),
  UNIQUE INDEX `id_Builder_UNIQUE` (`id_Employee` ASC),
  INDEX `fk_Education_idx` (`id_Education` ASC),
  INDEX `fk_Qualification_idx` (`id_Qualification` ASC),
  INDEX `fk_Addres_idx` (`id_Addres` ASC),
  CONSTRAINT `fk_Addres`
    FOREIGN KEY (`id_Addres`)
    REFERENCES `arch_inc`.`Addres` (`id_Addres`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Education`
    FOREIGN KEY (`id_Education`)
    REFERENCES `arch_inc`.`Education` (`id_Education`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Qualification`
    FOREIGN KEY (`id_Qualification`)
    REFERENCES `arch_inc`.`Qualification` (`id_Qualification`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `arch_inc`.`OrderedMan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `arch_inc`.`OrderedMan` (
  `id_OrderedMan` INT(11) NOT NULL AUTO_INCREMENT,
  `id_Qualification` INT(11) NOT NULL,
  `Amount` INT(11) NOT NULL,
  `id_Order` INT(11) NOT NULL,
  PRIMARY KEY (`id_OrderedMan`, `id_Qualification`, `id_Order`),
  UNIQUE INDEX `id_Ordered_men_UNIQUE` (`id_OrderedMan` ASC),
  INDEX `fk_Qual_idx` (`id_Qualification` ASC),
  INDEX `fk_Ordered` (`id_Order` ASC),
  CONSTRAINT `fk_Ordered`
    FOREIGN KEY (`id_Order`)
    REFERENCES `arch_inc`.`CustomerOrder` (`id_Order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Qual`
    FOREIGN KEY (`id_Qualification`)
    REFERENCES `arch_inc`.`Qualification` (`id_Qualification`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- ---------------------
-- INSERTION REGION
-- ---------------------

INSERT INTO Usertype (id_Usertype, Type) VALUES
  (1, 'ADMIN'),
  (2, 'CUSTOMER'),
  (3, 'MANAGER'),
  (4, 'HUMAN_RESOURCE');

  -- login: admin, pass: admin, type: ADMIN
INSERT INTO User (id_Usertype, Login, Password) VALUE
  (1, 'admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec');

INSERT INTO Education (Education) VALUES
  ('BASIC'),
  ('AVERAGE'),
  ('SPECIAL_AVERAGE'),
  ('PROFESSIONAL_TECH'),
  ('HIGH');