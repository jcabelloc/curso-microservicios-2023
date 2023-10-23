-- MySQL Workbench Forward Engineering
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
-- -----------------------------------------------------
-- Schema prestamos
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Table `prestamo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prestamo` (
  `nro_prestamo` INT NOT NULL AUTO_INCREMENT,
  `monto_desembolso` DECIMAL(10,2) NOT NULL,
  `tea` DECIMAL(6,2) NOT NULL,
  `nro_cuotas` INT NOT NULL,
  `frecuencia` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`nro_prestamo`))
ENGINE = InnoDB;
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;