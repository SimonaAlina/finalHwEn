CREATE TABLE IF NOT EXISTS `wikiindexer`.`article_top_words` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `word` VARCHAR(45) NULL DEFAULT NULL,
  `count` INT(11) NULL DEFAULT NULL,
  `art_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UNIQUE` (`word` ASC, `art_id` ASC),
  INDEX `FK_ART_ID_idx` (`art_id` ASC),
  CONSTRAINT `FK_ART_ID`
    FOREIGN KEY (`art_id`)
    REFERENCES `wikiindexer`.`articles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8