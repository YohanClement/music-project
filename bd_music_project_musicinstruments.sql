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
-- Table structure for table `musicinstruments`
--

DROP TABLE IF EXISTS `musicinstruments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `musicinstruments` (
  `Instr_id` int NOT NULL AUTO_INCREMENT,
  `Instr_name` varchar(500) NOT NULL,
  `Instr_type` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`Instr_id`),
  UNIQUE KEY `Instr_name_UNIQUE` (`Instr_name`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musicinstruments`
--

LOCK TABLES `musicinstruments` WRITE;
/*!40000 ALTER TABLE `musicinstruments` DISABLE KEYS */;
INSERT INTO `musicinstruments` VALUES (1,'accordéon','vent'),(2,'batterie','percussion'),(3,'banjo','cordes'),(4,'cajon','percussion'),(5,'cithare','corde pincées'),(6,'clarinette','bois'),(7,'contrebasse','cordes'),(8,'Dixianqin','corde'),(9,'Voix','chant'),(10,'dizi','bois'),(11,'djembé','percussion'),(12,'euphonium','cuivre'),(13,'flûte','vent'),(14,'flûte de pan','bois'),(15,'flûte traversière','bois'),(16,'guitare','corde'),(17,'harpe','corde'),(18,'sumagoto','corde'),(19,'junjung ','percussion'),(20,'luth','corde'),(21,'lyre','corde'),(22,'mandoline','corde'),(23,'ocarina','vent'),(24,'orgue','vent'),(25,'piano','corde'),(26,'saxophone','cuivre'),(27,'tam-tam','percussion'),(28,'timbales','percussion'),(29,'Musique assistée par ordinateur','analogique'),(30,'trompette','cuivre'),(31,'trombonne','cuivre'),(32,'ukulélé','corde'),(33,'violon','corde'),(34,'violoncelle','corde'),(35,'xylophone','percussion'),(36,'yuka','percussion'),(37,'basse','corde');
/*!40000 ALTER TABLE `musicinstruments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-14 16:29:20
