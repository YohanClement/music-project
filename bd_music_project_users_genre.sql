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
-- Table structure for table `users_genre`
--

DROP TABLE IF EXISTS `users_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_genre` (
  `Users_id` int NOT NULL,
  `Genre_id` int NOT NULL,
  `Users_genrekey` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Users_genrekey`),
  KEY `Users_id` (`Users_id`),
  KEY `Genre_id` (`Genre_id`),
  CONSTRAINT `users_genre_ibfk_1` FOREIGN KEY (`Users_id`) REFERENCES `users` (`Users_id`),
  CONSTRAINT `users_genre_ibfk_2` FOREIGN KEY (`Genre_id`) REFERENCES `genre_music` (`Genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_genre`
--

LOCK TABLES `users_genre` WRITE;
/*!40000 ALTER TABLE `users_genre` DISABLE KEYS */;
INSERT INTO `users_genre` VALUES (1,5,1),(1,6,2),(1,7,3),(2,8,4),(2,2,5),(2,1,6),(3,2,7),(3,3,8),(3,4,9),(4,5,10),(4,7,11),(5,6,12),(5,1,13),(5,5,14),(6,6,15),(8,1,16),(8,4,17),(9,7,18),(10,4,19),(11,2,20),(11,3,21),(11,5,22),(12,6,23),(12,4,24),(13,2,25),(14,3,26),(14,6,27),(14,1,28),(15,7,29),(15,6,30),(63,3,32),(65,1,33),(68,1,35);
/*!40000 ALTER TABLE `users_genre` ENABLE KEYS */;
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
