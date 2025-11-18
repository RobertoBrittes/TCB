-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema estrovaRun
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema estrovaRun
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `estrovaRun` DEFAULT CHARACTER SET utf8 ;
USE `estrovaRun` ;

-- -----------------------------------------------------
-- Table `estrovaRun`.`treinador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estrovaRun`.`treinador` (
  `cref` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `data_nasc` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cref`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estrovaRun`.`plano_treino`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estrovaRun`.`plano_treino` (
  `idplano_treino` INT NOT NULL,
  `dist_max_treino_longo` FLOAT NOT NULL,
  `duracao_plano` INT NOT NULL,
  `qtd_treino_semanal` INT NOT NULL,
  `treinador_cref` VARCHAR(45) NOT NULL,
  `is_ativo` TINYINT NOT NULL,
  PRIMARY KEY (`idplano_treino`, `treinador_cref`),
  INDEX `fk_plano_treino_treinador1_idx` (`treinador_cref` ASC) VISIBLE,
  CONSTRAINT `fk_plano_treino_treinador1`
    FOREIGN KEY (`treinador_cref`)
    REFERENCES `estrovaRun`.`treinador` (`cref`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estrovaRun`.`aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estrovaRun`.`aluno` (
  `idaluno` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `data_nasc` DATE NOT NULL,
  `plano_treino_idplano_treino` INT NOT NULL,
  PRIMARY KEY (`idaluno`, `plano_treino_idplano_treino`),
  INDEX `fk_aluno_plano_treino_idx` (`plano_treino_idplano_treino` ASC) VISIBLE,
  CONSTRAINT `fk_aluno_plano_treino`
    FOREIGN KEY (`plano_treino_idplano_treino`)
    REFERENCES `estrovaRun`.`plano_treino` (`idplano_treino`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estrovaRun`.`treino`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estrovaRun`.`treino` (
  `idtreino` INT NOT NULL,
  `duracao` VARCHAR(45) NOT NULL,
  `tipo_treino` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`idtreino`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `estrovaRun`.`treino_has_plano_treino`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estrovaRun`.`treino_has_plano_treino` (
  `treino_idtreino` INT NOT NULL,
  `plano_treino_idplano_treino` INT NOT NULL,
  PRIMARY KEY (`treino_idtreino`, `plano_treino_idplano_treino`),
  INDEX `fk_treino_has_plano_treino_plano_treino1_idx` (`plano_treino_idplano_treino` ASC) VISIBLE,
  INDEX `fk_treino_has_plano_treino_treino1_idx` (`treino_idtreino` ASC) VISIBLE,
  CONSTRAINT `fk_treino_has_plano_treino_treino1`
    FOREIGN KEY (`treino_idtreino`)
    REFERENCES `estrovaRun`.`treino` (`idtreino`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_treino_has_plano_treino_plano_treino1`
    FOREIGN KEY (`plano_treino_idplano_treino`)
    REFERENCES `estrovaRun`.`plano_treino` (`idplano_treino`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
