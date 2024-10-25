CREATE DATABASE  IF NOT EXISTS `gestionraee` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gestionraee`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: gestionraee
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
-- Table structure for table `cantidadtipo`
--

DROP TABLE IF EXISTS `cantidadtipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cantidadtipo` (
  `idCantidadTipo` int NOT NULL AUTO_INCREMENT,
  `idTipo` int DEFAULT NULL,
  `idSolicitud` int DEFAULT NULL,
  `cantidad` float DEFAULT NULL,
  PRIMARY KEY (`idCantidadTipo`),
  KEY `idTipo` (`idTipo`),
  KEY `idSolicitud` (`idSolicitud`),
  CONSTRAINT `cantidadtipo_ibfk_1` FOREIGN KEY (`idTipo`) REFERENCES `tiporaee` (`idTipo`),
  CONSTRAINT `cantidadtipo_ibfk_2` FOREIGN KEY (`idSolicitud`) REFERENCES `solicitudes` (`idSolicitud`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cantidadtipo`
--

LOCK TABLES `cantidadtipo` WRITE;
/*!40000 ALTER TABLE `cantidadtipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `cantidadtipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `centrosrecoleccion`
--

DROP TABLE IF EXISTS `centrosrecoleccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `centrosrecoleccion` (
  `idCentro` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `direccion` varchar(150) DEFAULT NULL,
  `capacidad` int DEFAULT NULL,
  PRIMARY KEY (`idCentro`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centrosrecoleccion`
--

LOCK TABLES `centrosrecoleccion` WRITE;
/*!40000 ALTER TABLE `centrosrecoleccion` DISABLE KEYS */;
INSERT INTO `centrosrecoleccion` VALUES (1,'Centro Sur','Avenida Siempre Viva 456',100),(4,'Centro Norte','Calle Principal 789',500),(6,'Centro Norte','Calle Principal 789',500),(8,'Centro Norte','Calle Principal 789',500);
/*!40000 ALTER TABLE `centrosrecoleccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitudes`
--

DROP TABLE IF EXISTS `solicitudes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitudes` (
  `idSolicitud` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int DEFAULT NULL,
  `idCentro` int DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idSolicitud`),
  KEY `idUsuario` (`idUsuario`),
  KEY `idCentro` (`idCentro`),
  CONSTRAINT `solicitudes_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`),
  CONSTRAINT `solicitudes_ibfk_2` FOREIGN KEY (`idCentro`) REFERENCES `centrosrecoleccion` (`idCentro`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitudes`
--

LOCK TABLES `solicitudes` WRITE;
/*!40000 ALTER TABLE `solicitudes` DISABLE KEYS */;
INSERT INTO `solicitudes` VALUES (1,1,1,'Ingresada'),(3,5,4,'Ingresada'),(5,7,6,'Ingresada'),(7,9,8,'Ingresada');
/*!40000 ALTER TABLE `solicitudes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiporaee`
--

DROP TABLE IF EXISTS `tiporaee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiporaee` (
  `idTipo` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idTipo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiporaee`
--

LOCK TABLES `tiporaee` WRITE;
/*!40000 ALTER TABLE `tiporaee` DISABLE KEYS */;
/*!40000 ALTER TABLE `tiporaee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `direccion` varchar(150) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Juan Perez','Calle Falsa 123','juan.perez@gmail.com','1234567890'),(5,'Ana Lopez','Av. Libertador 123','ana@mail.com','0987654321'),(7,'Ana Lopez','Av. Libertador 123','ana@mail.com','0987654321'),(9,'Ana Lopez','Av. Libertador 123','ana@mail.com','0987654321');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'gestionraee'
--

--
-- Dumping routines for database 'gestionraee'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-25 15:45:42
