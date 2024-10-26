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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cantidadtipo`
--

LOCK TABLES `cantidadtipo` WRITE;
/*!40000 ALTER TABLE `cantidadtipo` DISABLE KEYS */;
INSERT INTO `cantidadtipo` VALUES (2,1,1,2),(3,1,11,20),(4,4,13,50),(5,2,15,50);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centrosrecoleccion`
--

LOCK TABLES `centrosrecoleccion` WRITE;
/*!40000 ALTER TABLE `centrosrecoleccion` DISABLE KEYS */;
INSERT INTO `centrosrecoleccion` VALUES (1,'Centro Sur','Avenida Siempre Viva 456',100),(4,'Centro Norte','Calle Principal 789',500),(6,'Centro Norte','Calle Principal 789',500),(8,'Centro Norte','Calle Principal 789',500),(9,'0','0',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitudes`
--

LOCK TABLES `solicitudes` WRITE;
/*!40000 ALTER TABLE `solicitudes` DISABLE KEYS */;
INSERT INTO `solicitudes` VALUES (1,1,1,'Ingresada'),(3,5,4,'Ingresada'),(5,7,6,'Ingresada'),(7,9,8,'Ingresada'),(9,1,1,'Ingresada'),(11,1,4,'Ingresada'),(12,1,4,'Ingresada'),(13,1,4,'Ingresada'),(14,7,4,'Ingresada'),(15,9,4,'Ingresada');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiporaee`
--

LOCK TABLES `tiporaee` WRITE;
/*!40000 ALTER TABLE `tiporaee` DISABLE KEYS */;
INSERT INTO `tiporaee` VALUES (1,'Computadoras (desktop/laptop)'),(2,'Teclados y mouses'),(3,'Impresoras y escáneres'),(4,'Teléfonos móviles'),(5,'Otro');
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
  `rol` varchar(10) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Juan Perez','Calle Falsa 123','juan.perez@gmail.com','1234567890','ADMIN'),(5,'Ana Lopez','Av. Libertador 123','ana@mail.com','0987654321','ADMIN'),(7,'Juan Pedro','Av. Libertador 123','ana@mail.com','0987654321','USER'),(9,'María','Av. Libertador 123','ana@mail.com','0987654321','USER');
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

-- Dump completed on 2024-10-26 16:57:29
