CREATE DATABASE  IF NOT EXISTS `confectionery`;
    -- /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE USER IF NOT EXISTS 'user'@'%' IDENTIFIED BY 'pass';
GRANT all privileges ON confectionery.* TO 'user'@'%' with grant option;
FLUSH PRIVILEGES;

USE `confectionery`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: confectionery
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cost` int DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `ready` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg5uhi8vpsuy0lgloxk2h4w5o6` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,1500,'27.09.2022 02:45:54',_binary '',1,_binary ''),(2,1500,'04.11.2022 20:26:24',_binary '',1,_binary ''),(3,2700,'28.09.2022 20:21:44',_binary '',2,_binary ''),(4,0,NULL,_binary '\0',2,_binary '\0'),(5,3300,'15.11.2022 13:58:48',_binary '',1,_binary ''),(6,3000,NULL,_binary '\0',1,_binary '\0'),(7,1700,'11.12.2022 17:18:40',_binary '',4,_binary ''),(8,3400,'11.12.2022 17:20:17',_binary '',4,_binary '\0'),(9,0,NULL,_binary '\0',4,_binary '\0');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cart_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  KEY `FKjcyd5wv4igqnw413rgxbfu4nv` (`product_id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES (1,1,83),(2,3,83),(3,3,27),(5,2,45),(10,5,3),(12,5,6),(13,6,4),(17,7,3),(20,8,7),(19,7,16),(21,8,8),(24,6,10);
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `product_type_id` bigint DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlabq3c2e90ybbxk58rc48byqo` (`product_type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (3,'cake1.png','Бенто торт с пейзажем',1500,2,_binary ''),(4,'cake7.jpg','Бенто торт с рисунком',1500,2,_binary ''),(5,'bigcake4.jpg','Двухъярусный торт с фруктами',1800,3,_binary ''),(6,'bigcake7.jpg','Шоколадный торт',1800,3,_binary ''),(7,'cake6.jpg','Бенто торт с надписью',1500,2,_binary ''),(8,'bigcake9.jpg','Торт с объемным декором',1900,3,_binary ''),(9,'cupcake2.jpg','Капкейк с декором радуги',120,5,_binary ''),(10,'cupcake8.jpg','Капкейки 9 шт',1500,5,_binary ''),(11,'cake8.jpg','Бенто торт с рисунком',1500,2,_binary ''),(12,'bigcake5.jpg','Фруктовый торт',1500,3,_binary ''),(13,'bigcake3.jpg','Торт с объемным декором',1900,3,_binary ''),(14,'set2.jpeg','Набор из торта и капкейков',2200,7,_binary ''),(15,'set1.jpg','Набор из капкейков и макаронс',800,7,_binary ''),(16,'minicake7.jpg','Пироженое',200,4,_binary ''),(17,'minicake8.jpg','Пироженое Медовик',200,4,_binary ''),(18,'minicake4.jpg','Пироженые 12 шт',1700,4,_binary ''),(19,'cupcake1.jpg','Пироженые 9 шт',1500,5,_binary ''),(20,'minicake1.jpeg','Пироженые 4 шт',800,4,_binary ''),(21,'mac1.jpg','Макаронсы 12 шт',800,6,_binary ''),(22,'mac2.jpg','Макаронс',80,6,_binary ''),(23,'cake4.jpg','Бенто торт с надписью',1500,2,_binary ''),(24,'cake5.jpg','Бенто торт с надписью',1500,2,_binary ''),(25,'cake9.jpg','Бенто торт с надписью',1500,2,_binary ''),(26,'mac4.jpg','Макаронсы ',2500,6,_binary ''),(27,'cupcake2.jpg','Капкейки 12 шт',1200,5,_binary ''),(28,'cupcake4.jpg','Капкейки 3 шт',400,5,_binary ''),(29,'cupcake5.jpg','Капкейк',120,5,_binary ''),(30,'bigcake1.jpg','Торт с фруктами',1800,3,_binary ''),(31,'bigcake2.jpg','Двухъярусный торт с фруктами',1800,3,_binary ''),(32,'bigcake6.jpg','Двухъярусный торт с фруктами',1500,3,_binary ''),(33,'set3.jpg','Набор из торта и капкейков 18 шт',3000,7,_binary ''),(34,'set4.jpg','Макаронсы 16 шт',1100,6,_binary ''),(35,'minicake2.jpg','Шоколадное пироженое',150,4,_binary ''),(36,'minicake3.jpg','Пироженое Наполеон',150,4,_binary ''),(37,'minicake5.jpg','Пироженое',150,4,_binary ''),(45,'cake3.jpg','Бенто торт в форме сердца',1500,2,_binary ''),(83,'cake11.jpg','Бенто торт с надписью',1500,2,_binary ''),(84,'джони.jpg','джони',10000,2,_binary '\0'),(85,'дикаприо.jpg','',0,2,_binary '\0');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_type`
--

DROP TABLE IF EXISTS `product_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_type`
--

LOCK TABLES `product_type` WRITE;
/*!40000 ALTER TABLE `product_type` DISABLE KEYS */;
INSERT INTO `product_type` VALUES (1,'Все десерты'),(2,'Бенто торты'),(3,'Торты'),(4,'Пироженые'),(5,'Капкейки'),(6,'Макаронсы');
/*!40000 ALTER TABLE `product_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'USER'),(2,'USER'),(3,'ADMIN'),(4,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `roles_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`roles_id`),
  KEY `FKdbv8tdyltxa1qjmfnj9oboxse` (`roles_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(2,2),(3,3),(4,4);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Ульяна','$2a$10$2O1BWPphYCiCxLPye6C1XOZtllP/GbC2VqFudR1XyHaMeOCUKI2z.','89184777274'),(2,'Цветана','$2a$10$5epSDnya6YwraHNdSCpU0egcAhFygs.bBpdj3T9VYSx3k60z0Qdtu','89186422149'),(3,'Администратор','$2a$12$FJBS1Dd6xGuDSQgY4tpNROZQSy4ds8d2MRyCP5CAwa4TLcrzGo712','89180000000'),(4,'Мария','$2a$10$wP386xwCW/7SLH25j8RNLOF0syDchm4SY8nJ9OCLuCsTaYgjcoi..','89182222222');
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

-- Dump completed on 2022-12-24 23:46:55
