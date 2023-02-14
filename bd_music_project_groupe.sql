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
-- Table structure for table `groupe`
--

DROP TABLE IF EXISTS `groupe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `groupe` (
  `Groupe_id` int NOT NULL AUTO_INCREMENT,
  `Groupe_name` varchar(500) NOT NULL,
  `Groupe_Email` varchar(500) NOT NULL,
  `Groupe_members_nmb` int DEFAULT '1',
  `Creator_id` int DEFAULT NULL,
  `Groupe_isrecruting` varchar(3) DEFAULT NULL,
  `Groupe_password` varchar(500) NOT NULL,
  `Groupe_description` varchar(2000) DEFAULT NULL,
  `Groupe_frequence` varchar(100) DEFAULT NULL,
  `Groupe_photos` varchar(100) DEFAULT NULL,
  `Groupe_typemusique` varchar(45) DEFAULT NULL,
  `audio` varchar(100) DEFAULT NULL,
  `photos` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Groupe_id`),
  UNIQUE KEY `Groupe_Email` (`Groupe_Email`),
  KEY `groupe_ibfk_1` (`Creator_id`),
  CONSTRAINT `groupe_ibfk_1` FOREIGN KEY (`Creator_id`) REFERENCES `users` (`Users_id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupe`
--

LOCK TABLES `groupe` WRITE;
/*!40000 ALTER TABLE `groupe` DISABLE KEYS */;
INSERT INTO `groupe` VALUES (72,'Toulousaing','toulousaing@gmail.com',NULL,44,'Oui','toulouse','Groupe de musique sur Toulouse, venez nous rejoindre place Saint pierre tous les jeudis, pour terminer Chez Tonton ensuite !','Une fois par semaine',NULL,NULL,'',NULL),(73,'SopraSteria','sopra@gmail.com',NULL,44,'Oui','sopra','Nouveaux dans l\'entreprise, on forme une petite team de jeunes pour se décompresser après le taff','Autre',NULL,NULL,'',NULL),(74,'Infotel','infotel@gmail.com',1,45,'Oui','infotel','Cherche guitariste urgence','Deux fois par semaine',NULL,NULL,NULL,NULL),(91,'Lille','lille',1,53,'Non','lille','Venez nous détendre avec nous en musique','Tous les jours',NULL,NULL,NULL,NULL),(92,'MetalHead','metalhead@gmail.com',NULL,50,'Oui','metal','Groupe de metal qui est la pour foutre le feu sur scène le samedi soir !!!','Deux fois par mois',NULL,NULL,'','singe.jpg'),(93,'BangkokStreet','bangkok@gmail.com',NULL,57,'Non','bangkok','Musique du monde, on se produit sur la place des villages','Deux fois par mois',NULL,NULL,'',NULL);
/*!40000 ALTER TABLE `groupe` ENABLE KEYS */;
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
