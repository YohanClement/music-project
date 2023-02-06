CREATE DATABASE IF NOT EXISTS bd_music_project;  
use bd_music_project;


create table if not exists Users
(
Users_id int auto_increment primary key,
Users_first_name varchar(500) NOT null,
Users_last_name varchar(500) NOT null,
Users_Adress varchar(1000),
Users_Email varchar(500) NOT null unique,
Users_bio varchar(2000),
Users_Zip varchar (50),
Users_City varchar(200),
Users_date_crea datetime not null default  CURRENT_TIMESTAMP, 
Users_Linked_Accounts_Nmbr int default 0
);


create table if not exists MusicInstruments
(
Instr_id int auto_increment primary key,
Instr_name varchar(500) NOT null,
Instr_type varchar(500) 
);

create table if not exists Users_Instruments
(
Users_id int not null,
Instr_id int not null,
FOREIGN KEY (Users_id) REFERENCES Users(Users_id),
FOREIGN KEY (Instr_id) REFERENCES MusicInstruments(Instr_id)
);

create table if not exists Groupe
(
Groupe_id int auto_increment primary key,
Groupe_name varchar(500) NOT null,
Groupe_Email varchar(500) NOT null unique,
Groupe_members_nmb int default 1, -- ici ajouter une value qui = somme de toutes les ligne de table Groupe_members où groupe id = groupe id de celles la
Creator_id int not null,
FOREIGN KEY (Creator_id) REFERENCES Users(Users_id),
Groupe_isRecruting tinyint default 0
);

create table if not exists Groupe_Members
(
Groupe_id int not null,
Users_members int not null,
FOREIGN KEY (Groupe_id) REFERENCES Groupe(Groupe_id),
FOREIGN KEY (Users_members) REFERENCES Users(Users_id)
);


create table if not exists Genre_Music
(
Genre_id int auto_increment primary key,
Genre_name varchar(200),
Genre_parent int not null,
FOREIGN KEY (Genre_parent) REFERENCES Genre_music(Genre_id)
);



create table if not exists Genre_Groupe
(
Groupe_id int not null,
Genre_id int not null,
FOREIGN KEY (Groupe_id) REFERENCES Groupe(Groupe_id),
FOREIGN KEY (Genre_id) REFERENCES Genre_Music(Genre_id)
);


create table if not exists Evenement -- event est un mot de mysql obligé de lecrire en francais
(
Evenement_id int auto_increment primary key,
Evenement_name varchar(500) NOT null,
Users_crea int not null,
FOREIGN KEY (Users_crea) REFERENCES Users(Users_id),
Groupe_crea int not null,
FOREIGN KEY (Groupe_crea) REFERENCES Groupe(Groupe_id),
Evenement_bio varchar(2000),
Evenement_Adress varchar(1000) not null,
Evenement_Zip varchar (50) not null,
Evenement_City varchar(200) not null,
Evenement_date datetime not null, 
Evenement_genre int not null,
FOREIGN KEY (Evenement_genre) REFERENCES Genre_music(Genre_id)

);



create table if not exists Groupe_Evenement -- la liste des evenements ou les groupes sont allés / ont participés 
(
Groupe_id int not null, 
Evenement_id int not null,
FOREIGN KEY (Groupe_id) REFERENCES Groupe(Groupe_id),
FOREIGN KEY (Evenement_id) REFERENCES Evenement(Evenement_id)
);


create table if not exists Users_Evenement -- la liste des evenements ou les users sont allés / ont participés 
(
Users_id int not null, 
Evenement_id int not null,
FOREIGN KEY (Users_id) REFERENCES Users(Users_id),
FOREIGN KEY (Evenement_id) REFERENCES Evenement(Evenement_id)
);

create table if not exists Users_Genre -- les genres de références d'un user
(
Users_id int not null,
Genre_id int not null, 
FOREIGN KEY (Users_id) REFERENCES Users(Users_id),
FOREIGN KEY (Genre_id) REFERENCES Genre_music(Genre_id)
);

