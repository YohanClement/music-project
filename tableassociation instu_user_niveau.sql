SELECT * FROM bd_music_project.users_instruments;

ALTER TABLE `bd_music_project`.`users_instruments` 
CHANGE COLUMN `Niveau` `Niveau` INT(11) NOT NULL ;

UPDATE users_instruments SET Niveau = 1 WHERE users_id=2 and Instr_id=25;
UPDATE users_instruments SET Niveau = 1 WHERE users_id=15 and Instr_id=28;
UPDATE users_instruments SET Niveau = 1 WHERE users_id=1 and Instr_id=16;
UPDATE users_instruments SET Niveau = 2 WHERE users_id=3 and Instr_id=30;
UPDATE users_instruments SET Niveau = 2 WHERE users_id=4 and Instr_id=33;
UPDATE users_instruments SET Niveau = 2 WHERE users_id=5 and Instr_id=34;
UPDATE users_instruments SET Niveau = 3 WHERE users_id=6 and Instr_id=4;
UPDATE users_instruments SET Niveau = 3 WHERE users_id=8 and Instr_id=11;
UPDATE users_instruments SET Niveau = 3 WHERE users_id=9 and Instr_id=2;
UPDATE users_instruments SET Niveau = 3 WHERE users_id=6 and Instr_id=4;
UPDATE users_instruments SET Niveau = 3 WHERE users_id=8 and Instr_id=11;
UPDATE users_instruments SET Niveau = 4 WHERE users_id=10 and Instr_id=37;
UPDATE users_instruments SET Niveau = 4 WHERE users_id=11 and Instr_id=16;
UPDATE users_instruments SET Niveau = 4 WHERE users_id=12 and Instr_id=2;
UPDATE users_instruments SET Niveau = 5 WHERE users_id=13 and Instr_id=9;
UPDATE users_instruments SET Niveau = 5 WHERE users_id=14 and Instr_id=37;
UPDATE users_instruments SET Niveau = 5 WHERE users_id=15 and Instr_id=4;

INSERT INTO users_instruments VALUES ('1', '11', '5');
INSERT INTO users_instruments VALUES ('1', '2', '3');