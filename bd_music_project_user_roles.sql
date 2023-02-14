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
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `Rolesid` int NOT NULL AUTO_INCREMENT,
  `Users_email` varchar(255) DEFAULT NULL,
  `USERS_ROLE` varchar(255) DEFAULT NULL,
  `usersid` int DEFAULT NULL,
  PRIMARY KEY (`Rolesid`),
  KEY `usersid_idx` (`usersid`),
  CONSTRAINT `usersid` FOREIGN KEY (`usersid`) REFERENCES `users` (`Users_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (22,'florianaguirre@gmail.com','USER',44),(23,'loganlassere@gmail.com','USER',45),(24,'yohanclement@gmail.com','USER',46),(25,'suzannemarques@gmail.com','USER',48),(26,'roxaneorain@gmail.com','USER',49),(27,'sabrinatoofany@gmail.com','USER',50),(28,'luctrabelsi@gmail.com','USER',51),(29,'titouanfaure@gmail.com','USER',52),(30,'karimkherzane@gmail.com','USER',53),(31,'shaimaes@gmail.com','USER',55),(33,'miralecocq@gmail.com','USER',57),(34,'hugopalazzi@gmail.com','USER',58),(35,'lucafavetti@gmail.com','USER',59),(36,'karimkherzane2@gmail.com','USER',61),(38,'yahyaabdel@gmail.com','USER',63),(39,'florianaguirre64@gmail.com','USER',65),(41,'yahyaabdellaoui@gmail.com','USER',68);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
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
