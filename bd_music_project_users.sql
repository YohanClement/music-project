-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: bd_music_project
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `Users_id` int NOT NULL AUTO_INCREMENT,
  `Password` varchar(200) NOT NULL DEFAULT 'Abc123!',
  `Users_firstname` varchar(500) NOT NULL,
  `Users_lastname` varchar(500) NOT NULL,
  `Users_Adress` varchar(1000) DEFAULT NULL,
  `Users_Email` varchar(500) NOT NULL,
  `Users_bio` varchar(2000) DEFAULT NULL,
  `Users_Zip` varchar(50) DEFAULT NULL,
  `Users_City` varchar(200) DEFAULT NULL,
  `Users_date_crea` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Users_Linked_Accounts_Nmbr` int DEFAULT '0',
  `photos` varchar(200) DEFAULT NULL,
  `audio` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`Users_id`),
  UNIQUE KEY `Users_Email` (`Users_Email`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'$2a$10$WTxadg99cAUOo2GmjGcZgeV/pH92bsjcZFpuwZ5QxHRtPr3YHvZQq','Logan','Lassere','56 rue Marx Dormoy','logan1998@hotmail.fr','guitariste depuis peu je cherche a faire des rencontres pour pratiquer et devenir meilleur','31000','Toulouse','2023-02-03 08:51:54',0,NULL,NULL),(2,'Abc123!','Marion','Choupinax','27 rue de lespoir','marion@hotmail.fr','pianiste depuis peu je cherche a faire des rencontres pour pratiquer et devenir meilleur','31000','Toulouse','2023-02-03 08:51:54',0,NULL,NULL),(3,'Abc123!','Frédéric','Dupont','45 rue voltaire','fdupont@hotmail.fr','Trompetiste depuis peu je cherche a faire des rencontres pour pratiquer et devenir meilleur','93120','La Courneuve','2023-02-03 08:51:54',0,NULL,NULL),(4,'Abc123!','Fatimah','Baker','29 rue montrouge','fbeks@hotmail.fr','violoniste depuis peu je cherche a faire des rencontres pour pratiquer et devenir meilleur','34000','Montpellier','2023-02-03 08:51:54',0,NULL,NULL),(5,'Abc123!','Rayan','Rabehi','12 rue rateau','r.rabehi@hotmail.fr','violoncelliste depuis peu je cherche a faire des rencontres pour pratiquer et devenir meilleur','93700','Saint Denis','2023-02-03 08:51:54',0,NULL,NULL),(6,'Abc123!','Feriel','Hamadene','2bis rue des petits fours','f.hamdne@hotmail.fr','percursionniste depuis peu je cherche a faire des rencontres pour pratiquer et devenir meilleur','38000','Grenoble','2023-02-03 08:51:54',0,NULL,NULL),(7,'Abc123!','Fernand','Lux','3 rue des amours','f.lux@hotmail.fr','percursionniste depuis peu je cherche a faire des rencontres pour pratiquer et devenir meilleur','38000','Grenoble','2023-02-03 08:51:54',0,NULL,NULL),(8,'Abc123!','Killyan','Gruyere','56 cours berriat','killyan2000@hotmail.fr','drummer depuis peu je cherche a faire des rencontres pour pratiquer et devenir meilleur','38000','Grenoble','2023-02-03 08:51:54',0,NULL,NULL),(9,'Abc123!','Mathéo','Moreau','3 rue thiers','m.moreau@hotmail.fr','bassistee depuis peu je cherche a faire des rencontres pour pratiquer et devenir meilleur','38000','Grenoble','2023-02-03 08:51:54',0,NULL,NULL),(10,'Abc123!','Jeya','Niska','4 rue des arts','j.Nisks@hotmail.fr','guitariste depuis peu je cherche a faire des rencontres pour pratiquer et devenir meilleur','38000','Grenoble','2023-02-03 08:51:54',0,NULL,NULL),(11,'Abc123!','Martin','Matin','65 bis boulevard ney','m.matin@hotmail.fr','Je cherche un groupe ! Batteur confirmé','75018','Paris','2023-02-03 08:51:54',0,NULL,NULL),(12,'Abc123!','Constance','Chaintron','4 passage du poteau','c.chaintron@hotmail.fr','cherche petit groupe pour chant lyrique','75018','Paris','2023-02-03 08:51:54',0,NULL,NULL),(13,'Abc123!','Tom','Memmi','115 rue de la tour','t.memi@hotmail.fr','bassiste, cherche partenaire pour jam','75016','Paris','2023-02-03 08:51:54',0,NULL,NULL),(14,'Abc123!','Myriam','Ragot','42 boulevard lannes','myr.ragot@hotmail.fr','cherches copains our sessions percu','75016','Paris','2023-02-03 08:51:54',0,NULL,NULL),(15,'Abc123!','Jess','Martin','56 rue de la pompe','jmrtn@hotmail.fr','cherche copains pour faire des concerts !','75016','Paris','2023-02-03 08:51:54',0,NULL,NULL),(44,'$2a$10$9F6k8SKNJ5rmF8lAayUc0u/I1uWXxZOShDrkKcnvxn3eIvgnaGzky','Florian','Aguirre',NULL,'florianaguirre@gmail.com','Jeune amateur de violoncelle, je souhaite intégrer un groupe de musique afin de progresser et faire la fête','31000','Toulouse','2023-02-09 23:12:18',NULL,'florian.png','FAIRE UNE VIDEO COMPLETE EN 10SECONDE.mp3'),(45,'$2a$10$p8bWSSCxS6F4pEjdPpBLbuci5QoRBGbDFd5MD.mOtTj6s2vWQAd0u','Logan','Lassere',NULL,'loganlassere@gmail.com','J\'adore jouer de la guitare tous les jours, vous voulez qu\'on se rencontre pour que vous écoutiez ?','31000','Toulouse','2023-02-09 23:13:35',NULL,'logan.jpg','FAIRE UNE VIDEO COMPLETE EN 10SECONDE.mp3'),(46,'$2a$10$jre3fFV/dIm43RV61rjmSei1NlnRBcFdAuo3Di1yU3.Pbq7QPf0a2','Yohan','Clément',NULL,'yohanclement@gmail.com','J\'adore les pokémons, mais ma vrai passion dans la vie c\'est Mozart.','75000','Paris','2023-02-09 23:14:37',NULL,'yohan.jpg','FAIRE UNE VIDEO COMPLETE EN 10SECONDE.mp3'),(48,'$2a$10$dQjrjbhXetdvEAtGSED8UOVTPEBPleFWhufgg0Iqxtu3CS1zfLBTG','Suzanne','Marques',NULL,'suzannemarques@gmail.com','A coté d\'un doctorat en biologie (oui madame), je joue du violon et souhaite progresser','31000','Toulouse','2023-02-09 23:18:42',NULL,'suzanne2.jpg','FAIRE UNE VIDEO COMPLETE EN 10SECONDE.mp3'),(49,'$2a$10$gMZ5mN45GGcuSf2QgSReX.KCLP.UN09O0j5etR2u307k3/DZNV6N2','Roxane','Orain',NULL,'roxaneorain@gmail.com','Bassiste depuis peu, je cherche à intégrer un groupe sur Nantes','44000','Nantes','2023-02-09 23:19:55',NULL,'roxane.jpg','FAIRE UNE VIDEO COMPLETE EN 10SECONDE.mp3'),(50,'$2a$10$Yl5zxKHEKTMqZR9zExEl4OwVxcW2JukxLuKnKCW8vZAcdCR7zyW8q','Sabrina ','Toofany',NULL,'sabrinatoofany@gmail.com','J\'adore jouer des percussions, venez au club des Haricots, on recrute','75000','Paris','2023-02-09 23:21:14',NULL,'sabrina.jpg','FAIRE UNE VIDEO COMPLETE EN 10SECONDE.mp3'),(51,'$2a$10$3JDhINBBDFOmdjmdUafA1umLbkBsDeBGzVBVstUmdYIsNnU6c77XC','Luc','Trabelsi',NULL,'luctrabelsi@gmail.com','Outre la fonction de team leader au travail, je suis aussi team leader de mon groupe de rock','67000','Strasbourg','2023-02-09 23:22:29',NULL,'luc.jpg','FAIRE UNE VIDEO COMPLETE EN 10SECONDE.mp3'),(52,'$2a$10$2Q5O.6cH7cxV.5vjMJ0/werYIVctHfcYPXQIBUbq1R2S29qdn4SGW','Titouan','Faure',NULL,'titouanfaure@gmail.com','J\'adore Lyon et les maracas','69000','Lyon','2023-02-09 23:23:21',NULL,'titouan.jpg','FAIRE UNE VIDEO COMPLETE EN 10SECONDE.mp3'),(53,'$2a$10$MpbApU3G7Op78kmI1/Xw.uSCNLpwUGLdtB2kI9G4dl/eGR3n2FT6u','Karim','Kherzane',NULL,'karimkherzane@gmail.com','Je souhaite développer mes compétences en guitare et en triangle, qui pour m\'aider ?','59000','Lille','2023-02-09 23:24:36',NULL,'karim.jpg','FAIRE UNE VIDEO COMPLETE EN 10SECONDE.mp3'),(55,'$2a$10$Lb5xLZIwt.M1pkr32LpWM.BRs/Ndy5PNFFztXhVDd6EXToYNRyVmu','Shaima','Es-Saidi',NULL,'shaimaes@gmail.com','J\'adore le trombone et les instruments à vent ! Venez apprendre avec moi','31000','Toulouse','2023-02-09 23:27:18',NULL,'shaima.jpg','FAIRE UNE VIDEO COMPLETE EN 10SECONDE.mp3'),(57,'$2a$10$yBK8Y2jKJQ8BIDiHnE6WCujVp93enF0vtpYI.gzEIvlSauEoi97Eq','Mira','Lecocq',NULL,'miralecocq@gmail.com','Intégrée dans une chorale depuis ma naissance, je voudrais à la fois être chanteuse et musicienne','31000','Toulouse','2023-02-09 23:29:22',NULL,'mira.jpg','FAIRE UNE VIDEO COMPLETE EN 10SECONDE.mp3'),(58,'$2a$10$hX1e6BzgxFCInjwvRgzsw.INZf51k6Lg7oo2MZY7igKMAer6P/Cmm','Hugo','Palazzi',NULL,'hugopalazzi@gmail.com','Je suis passionné de Piano','75000','Paris','2023-02-09 23:30:20',NULL,'hugo.jpg','FAIRE UNE VIDEO COMPLETE EN 10SECONDE.mp3'),(59,'$2a$10$oHImbqDidd7WR0WNjN/Q/OD31yfPXafoPun0eyYQPkye/P6jgkbYy','Luca ','Favetti',NULL,'lucafavetti@gmail.com','Je suis un adepte du violon et de l\'harmonica','75000','Paris','2023-02-09 23:31:09',NULL,'luca.jpg','FAIRE UNE VIDEO COMPLETE EN 10SECONDE.mp3'),(61,'$2a$10$V.Y342iMZvRRuAV.gwhVc.cFTyXLODdBtZ1k.unYOI6Id2HwIZxcm','Karim','Kherzane',NULL,'karimkherzane2@gmail.com','Je souhaite développer mes compétences en guitare et en triangle, qui pour m\'aider ?','59000','Lille','2023-02-10 00:13:09',NULL,'karim.jpg','FAIRE UNE VIDEO COMPLETE EN 10SECONDE.mp3'),(63,'$2a$10$8wpssXwI12//PdNOOu4cgeVIoR.bcWDu2iIz6KLdFKFSNpCIGeMHG','yahya','abdellaoui',NULL,'yahyaabdel@gmail.com','Je suis pianiste','75000','Paris','2023-02-10 15:02:50',NULL,'fleur.jpg','FAIRE UNE VIDEO COMPLETE EN 10SECONDE.mp3'),(65,'$2a$10$uxa6yqfJQdC/a7na/gnoGO8m2O1Os/6MsTTulvZUjwun.LjwqRptu','sds','sds',NULL,'florianaguirre64@gmail.com','','','','2023-02-10 17:51:23',NULL,'',''),(68,'$2a$10$1aFEh8qXr978jUvumn/QEun.BvbRHEbGlTpF4QUa0OTUi/Q01VGXu','Yahya','Abdellaoui',NULL,'yahyaabdellaoui@gmail.com','J\'adore le pain','59000','Lille','2023-02-13 09:19:04',NULL,'fleur.jpg','FAIRE UNE VIDEO COMPLETE EN 10SECONDE.mp3');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-14 16:29:18
