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
-- Table structure for table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evenement` (
  `Evenement_id` int NOT NULL AUTO_INCREMENT,
  `Evenement_name` varchar(500) NOT NULL,
  `Users_crea` int NOT NULL,
  `Groupe_crea` int DEFAULT NULL,
  `Evenement_bio` varchar(2000) DEFAULT NULL,
  `Evenement_Adress` varchar(1000) NOT NULL,
  `Evenement_Zip` varchar(50) NOT NULL,
  `Evenement_City` varchar(200) NOT NULL,
  `Evenement_datefin` datetime NOT NULL,
  `Evenement_genre` int NOT NULL,
  `Evenement_datedebut` datetime NOT NULL,
  `Evenement_lieu` varchar(45) DEFAULT NULL,
  `Evenement_type` varchar(45) NOT NULL,
  `Evenement_photos` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Evenement_id`),
  KEY `Users_crea` (`Users_crea`),
  KEY `Evenement_genre` (`Evenement_genre`),
  KEY `evenement_ibfk_2` (`Groupe_crea`),
  CONSTRAINT `evenement_ibfk_1` FOREIGN KEY (`Users_crea`) REFERENCES `users` (`Users_id`),
  CONSTRAINT `evenement_ibfk_2` FOREIGN KEY (`Groupe_crea`) REFERENCES `groupe` (`Groupe_id`),
  CONSTRAINT `evenement_ibfk_3` FOREIGN KEY (`Evenement_genre`) REFERENCES `genre_music` (`Genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evenement`
--

LOCK TABLES `evenement` WRITE;
/*!40000 ALTER TABLE `evenement` DISABLE KEYS */;
INSERT INTO `evenement` VALUES (22,'Session d\'entrainement',44,NULL,'Venez vous entrainer avant le concert de la semaine prochaine ','56 rue de springfield','31000','Toulouse','2023-02-11 19:37:00',1,'2023-02-11 15:58:00','Chez tonton','Carritatif',''),(23,'Concert au profit des restos du coeur ',44,NULL,'Venez on va chanter, danser, s\'amuser !','Place du capitole','31000','Toulouse','2023-02-25 17:57:00',2,'2023-02-25 15:39:00','Place du Capitole','Concert',''),(24,'Festival du rock',45,NULL,'A l\'occasion du 13ieme festival de rock de Toulouse au parc Wilson, Britney Spears sera présente !','Place Wilson ','31000','Toulouse','2023-02-25 12:50:00',3,'2023-02-25 22:57:00','Place Wilson','Festival',NULL),(25,'Evenement de jazz',46,NULL,'Session jam de jazz','Champ de Mars','75000','Paris','2023-02-14 12:50:00',1,'2023-02-25 12:50:00','Champ de Mars','Jam session',NULL),(26,'Fete de la bière',57,NULL,'Venez vous amusez entre amis, il y aura de la bière ! Miam','12 cours de la somme','33000','Bordeaux','2023-02-05 02:17:00',1,'2023-02-06 17:21:00','Bar Barracuda','Concert','garorock.jpg'),(27,'Garorock',57,NULL,'Venez à Garorock il y aura plein d\'artistes géniaux ! ','12 cours de la somme','33000','Bordeaux','2023-02-28 14:35:00',7,'2023-02-27 14:35:00','Prairie ','Concert',''),(28,'Rock en Seine',44,NULL,'Il y aura plein de monde et de musique','25 rue de paradoux','31000','Toulouse','2023-02-22 09:33:00',8,'2023-02-20 09:33:00','Borriquito loco','Concert','');
/*!40000 ALTER TABLE `evenement` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-14 16:29:19
