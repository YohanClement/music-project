use bd_music_project;
ALTER TABLE MusicInstruments MODIFY Instr_type INT DEFAULT null;

ALTER TABLE `bd_music_project`.`musicinstruments` 
CHANGE COLUMN `Instr_type` `Instr_type` VARCHAR(500) NULL DEFAULT NULL ;


ALTER TABLE `bd_music_project`.`musicinstruments` 
ADD UNIQUE INDEX `Instr_name_UNIQUE` (`Instr_name` ASC) VISIBLE;
;

-- j'avais mis not null au genre parent 
ALTER TABLE `bd_music_project`.`genre_music` 
DROP FOREIGN KEY `genre_music_ibfk_1`;
ALTER TABLE `bd_music_project`.`genre_music` 
CHANGE COLUMN `Genre_parent` `Genre_parent` INT(11) NULL ;
ALTER TABLE `bd_music_project`.`genre_music` 
ADD CONSTRAINT `genre_music_ibfk_1`
  FOREIGN KEY (`Genre_parent`)
  REFERENCES `bd_music_project`.`genre_music` (`Genre_id`);
