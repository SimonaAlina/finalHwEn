CREATE TABLE IF NOT EXISTS `wikiindexer`.`articles` (
  `article_id` INT(11) NOT NULL AUTO_INCREMENT,
  `article_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `article_name_UNIQUE` (`article_name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8