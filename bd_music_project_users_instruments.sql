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
-- Table structure for table `users_instruments`
--

DROP TABLE IF EXISTS `users_instruments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_instruments` (
  `Users_id` int NOT NULL,
  `Instr_id` int NOT NULL,
  `Niveau` int NOT NULL DEFAULT '1',
  `users_instrument_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`users_instrument_id`),
  KEY `Users_id` (`Users_id`),
  KEY `Instr_id` (`Instr_id`),
  CONSTRAINT `users_instruments_ibfk_1` FOREIGN KEY (`Users_id`) REFERENCES `users` (`Users_id`),
  CONSTRAINT `users_instruments_ibfk_2` FOREIGN KEY (`Instr_id`) REFERENCES `musicinstruments` (`Instr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_instruments`
--

LOCK TABLES `users_instruments` WRITE;
/*!40000 ALTER TABLE `users_instruments` DISABLE KEYS */;
INSERT INTO `users_instruments` VALUES (44,25,2,35),(45,16,4,36),(46,25,3,37),(48,33,1,38),(49,25,5,39),(50,13,4,40),(51,16,3,41),(52,25,2,42),(53,14,4,43),(55,33,2,44),(57,16,1,46),(58,25,5,47),(59,33,3,48),(61,25,5,49),(44,5,3,51),(45,18,5,52),(45,12,4,53),(44,22,2,54),(63,25,3,55),(65,25,1,56),(68,25,3,58);
/*!40000 ALTER TABLE `users_instruments` ENABLE KEYS */;
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
