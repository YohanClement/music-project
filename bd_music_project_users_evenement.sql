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
-- Table structure for table `users_evenement`
--

DROP TABLE IF EXISTS `users_evenement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_evenement` (
  `Users_id` int NOT NULL,
  `Evenement_id` int NOT NULL,
  `Users_Eventkey` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Users_Eventkey`),
  KEY `users_id` (`Users_id`),
  KEY `evenement_id` (`Evenement_id`),
  CONSTRAINT `evenement_id` FOREIGN KEY (`Evenement_id`) REFERENCES `evenement` (`Evenement_id`),
  CONSTRAINT `users_id` FOREIGN KEY (`Users_id`) REFERENCES `users` (`Users_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_evenement`
--

LOCK TABLES `users_evenement` WRITE;
/*!40000 ALTER TABLE `users_evenement` DISABLE KEYS */;
INSERT INTO `users_evenement` VALUES (44,22,25),(44,23,26),(45,22,27),(53,22,28),(49,24,29),(48,23,30),(55,25,31),(44,25,32),(45,23,33),(46,23,34),(49,23,37),(53,23,38),(57,26,39),(57,27,40),(44,27,41),(44,28,42);
/*!40000 ALTER TABLE `users_evenement` ENABLE KEYS */;
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
