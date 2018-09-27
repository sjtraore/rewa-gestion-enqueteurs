use rewa;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: rewa
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `EMP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMP_NAME` varchar(45) DEFAULT NULL,
  `EMP_HIRE_DATE` datetime DEFAULT NULL,
  `EMP_SALARY` decimal(11,4) DEFAULT NULL,
  PRIMARY KEY (`EMP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Jacque Traore','2018-09-21 00:00:00',110.0000),(2,'Jacque Traore','2018-09-21 00:00:00',110.0000),(3,'john doe','2018-09-12 00:00:00',0.0000),(4,'john wick','2018-09-10 00:00:00',5600.0000),(5,'jay','2018-09-25 00:00:00',0.0000),(6,'jojo','2018-09-03 00:00:00',250.0000),(7,'best user','2018-09-06 00:00:00',0.0000),(8,'jonnah sloach','2018-09-03 00:00:00',56000.0000);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewa_comment`
--

DROP TABLE IF EXISTS `rewa_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewa_comment` (
  `idComment` int(11) NOT NULL AUTO_INCREMENT,
  `idAuthor` int(11) DEFAULT NULL,
  `commentDate` datetime NOT NULL,
  `comment` varchar(500) NOT NULL,
  PRIMARY KEY (`idComment`),
  KEY `FK_Comment_Author_idx` (`idAuthor`),
  CONSTRAINT `FK_Comment_Author` FOREIGN KEY (`idAuthor`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_comment`
--

LOCK TABLES `rewa_comment` WRITE;
/*!40000 ALTER TABLE `rewa_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `rewa_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewa_coordinate`
--

DROP TABLE IF EXISTS `rewa_coordinate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewa_coordinate` (
  `idcoordinate` int(11) NOT NULL AUTO_INCREMENT,
  `coordinate` varchar(200) DEFAULT NULL,
  `coordinateTypeId` int(11) DEFAULT NULL,
  `statusId` int(11) DEFAULT NULL,
  `personId` int(11) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `createdBy` int(11) DEFAULT NULL,
  `modifiedBy` int(11) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcoordinate`),
  KEY `FK_Coordinate_Type_idx` (`coordinateTypeId`),
  KEY `FK_Coordintate_Status_idx` (`statusId`),
  KEY `FK_Coordinate_Person_idx` (`personId`),
  KEY `FK_Coordinate_Cretedby_idx` (`createdBy`),
  KEY `FK_Coordinate_Modifiedby_idx` (`modifiedBy`),
  KEY `FK_Coodinate_Customer_idx` (`customerId`),
  CONSTRAINT `FK_Coodinate_Customer` FOREIGN KEY (`customerId`) REFERENCES `rewa_customer` (`idCustomer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Coordinate_Cretedby` FOREIGN KEY (`createdBy`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Coordinate_Modifiedby` FOREIGN KEY (`modifiedBy`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Coordinate_Person` FOREIGN KEY (`personId`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Coordinate_Type` FOREIGN KEY (`coordinateTypeId`) REFERENCES `rewa_coordinate_type` (`idCoordinateType`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Coordintate_Status` FOREIGN KEY (`statusId`) REFERENCES `rewa_status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_coordinate`
--

LOCK TABLES `rewa_coordinate` WRITE;
/*!40000 ALTER TABLE `rewa_coordinate` DISABLE KEYS */;
/*!40000 ALTER TABLE `rewa_coordinate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewa_coordinate_type`
--

DROP TABLE IF EXISTS `rewa_coordinate_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewa_coordinate_type` (
  `idCoordinateType` int(11) NOT NULL,
  `type` varchar(20) NOT NULL,
  PRIMARY KEY (`idCoordinateType`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_coordinate_type`
--

LOCK TABLES `rewa_coordinate_type` WRITE;
/*!40000 ALTER TABLE `rewa_coordinate_type` DISABLE KEYS */;
INSERT INTO `rewa_coordinate_type` VALUES (1,'Telephone Pimaire'),(2,'Telephone Secondaire'),(3,'Email'),(4,'Adresse'),(5,'Facebook ID');
/*!40000 ALTER TABLE `rewa_coordinate_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewa_customer`
--

DROP TABLE IF EXISTS `rewa_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewa_customer` (
  `idCustomer` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `idStatus` int(11) NOT NULL,
  `createdDate` datetime NOT NULL,
  `modifiedDate` datetime NOT NULL,
  `createdBy` int(11) DEFAULT NULL,
  `modifiedBy` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCustomer`),
  KEY `FK_Customer_Createdby_idx` (`createdBy`),
  KEY `FK_Customer_Modifiedby_idx` (`modifiedBy`),
  KEY `FK_Coordinate_Status_idx` (`idStatus`),
  CONSTRAINT `FK_Coordinate_Status` FOREIGN KEY (`idStatus`) REFERENCES `rewa_status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Customer_Createdby` FOREIGN KEY (`createdBy`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Customer_Modifiedby` FOREIGN KEY (`modifiedBy`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_customer`
--

LOCK TABLES `rewa_customer` WRITE;
/*!40000 ALTER TABLE `rewa_customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `rewa_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewa_field`
--

DROP TABLE IF EXISTS `rewa_field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewa_field` (
  `idField` int(11) NOT NULL,
  `field` varchar(45) NOT NULL,
  PRIMARY KEY (`idField`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_field`
--

LOCK TABLES `rewa_field` WRITE;
/*!40000 ALTER TABLE `rewa_field` DISABLE KEYS */;
/*!40000 ALTER TABLE `rewa_field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewa_person`
--

DROP TABLE IF EXISTS `rewa_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewa_person` (
  `idPerson` int(11) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `createdDate` datetime NOT NULL,
  `modifiedDate` datetime NOT NULL,
  `createdBy` int(11) DEFAULT NULL,
  `modifiedBy` int(11) DEFAULT NULL,
  `picture` blob,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`idPerson`),
  KEY `FK_Person_Status_idx` (`status`),
  KEY `FK_Person_Createdby_idx` (`createdBy`),
  KEY `FK_Person_Modifiedby_idx` (`modifiedBy`),
  CONSTRAINT `FK_Person_Createdby` FOREIGN KEY (`createdBy`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Person_Modifiedby` FOREIGN KEY (`modifiedBy`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Person_Status` FOREIGN KEY (`status`) REFERENCES `rewa_status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_person`
--

LOCK TABLES `rewa_person` WRITE;
/*!40000 ALTER TABLE `rewa_person` DISABLE KEYS */;
/*!40000 ALTER TABLE `rewa_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewa_person_level`
--

DROP TABLE IF EXISTS `rewa_person_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewa_person_level` (
  `idPerson` int(11) NOT NULL,
  `idField` int(11) NOT NULL,
  `notation` int(11) NOT NULL,
  `dateLevel` datetime NOT NULL,
  `baseNotation` int(11) NOT NULL DEFAULT '3',
  `evaluatedBy` int(11) DEFAULT NULL,
  `observation` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPerson`,`idField`,`dateLevel`),
  KEY `FK_PersonLevelField_idx` (`idField`),
  KEY `FK_PersonLevelEvaluatedby_idx` (`evaluatedBy`),
  CONSTRAINT `FK_PersonLevelEvaluatedby` FOREIGN KEY (`evaluatedBy`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PersonLevelField` FOREIGN KEY (`idField`) REFERENCES `rewa_field` (`idField`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PersonLevelPerson` FOREIGN KEY (`idPerson`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_person_level`
--

LOCK TABLES `rewa_person_level` WRITE;
/*!40000 ALTER TABLE `rewa_person_level` DISABLE KEYS */;
/*!40000 ALTER TABLE `rewa_person_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewa_person_role`
--

DROP TABLE IF EXISTS `rewa_person_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewa_person_role` (
  `idPerson` int(11) NOT NULL,
  `idRole` int(11) NOT NULL,
  `idStatus` int(11) NOT NULL,
  PRIMARY KEY (`idPerson`,`idRole`),
  KEY `FK_PersonRole_Role_idx` (`idRole`),
  KEY `FK_PersonRole_Status_idx` (`idStatus`),
  CONSTRAINT `FK_PersonRole_Person` FOREIGN KEY (`idPerson`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PersonRole_Role` FOREIGN KEY (`idRole`) REFERENCES `rewa_role` (`idRole`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PersonRole_Status` FOREIGN KEY (`idStatus`) REFERENCES `rewa_status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_person_role`
--

LOCK TABLES `rewa_person_role` WRITE;
/*!40000 ALTER TABLE `rewa_person_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `rewa_person_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewa_role`
--

DROP TABLE IF EXISTS `rewa_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewa_role` (
  `idRole` int(11) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`idRole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_role`
--

LOCK TABLES `rewa_role` WRITE;
/*!40000 ALTER TABLE `rewa_role` DISABLE KEYS */;
INSERT INTO `rewa_role` VALUES (1,'Enqueteur'),(2,'Superviseur'),(3,'Controlleur'),(10,'Admin');
/*!40000 ALTER TABLE `rewa_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewa_status`
--

DROP TABLE IF EXISTS `rewa_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewa_status` (
  `idStatus` int(11) NOT NULL,
  `Status` varchar(45) NOT NULL,
  PRIMARY KEY (`idStatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_status`
--

LOCK TABLES `rewa_status` WRITE;
/*!40000 ALTER TABLE `rewa_status` DISABLE KEYS */;
INSERT INTO `rewa_status` VALUES (1,'Actif'),(2,'Inactif');
/*!40000 ALTER TABLE `rewa_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewa_study`
--

DROP TABLE IF EXISTS `rewa_study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewa_study` (
  `idStudy` int(11) NOT NULL,
  `idCustomer` int(11) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `idStatus` int(11) DEFAULT NULL,
  `idTeam` int(11) DEFAULT NULL,
  PRIMARY KEY (`idStudy`),
  KEY `FK_Study_Customer_idx` (`idCustomer`),
  KEY `FK_Study_Status_idx` (`idStatus`),
  KEY `FK_Study_Team_idx` (`idTeam`),
  CONSTRAINT `FK_Study_Customer` FOREIGN KEY (`idCustomer`) REFERENCES `rewa_customer` (`idCustomer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Study_Status` FOREIGN KEY (`idStatus`) REFERENCES `rewa_status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Study_Team` FOREIGN KEY (`idTeam`) REFERENCES `rewa_team` (`idTeam`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_study`
--

LOCK TABLES `rewa_study` WRITE;
/*!40000 ALTER TABLE `rewa_study` DISABLE KEYS */;
/*!40000 ALTER TABLE `rewa_study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewa_team`
--

DROP TABLE IF EXISTS `rewa_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewa_team` (
  `idTeam` int(11) NOT NULL,
  `idSupervisor` int(11) DEFAULT NULL,
  `validated` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTeam`),
  KEY `FK_Team_Supervison_idx` (`idSupervisor`),
  CONSTRAINT `FK_Team_Supervison` FOREIGN KEY (`idSupervisor`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_team`
--

LOCK TABLES `rewa_team` WRITE;
/*!40000 ALTER TABLE `rewa_team` DISABLE KEYS */;
/*!40000 ALTER TABLE `rewa_team` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-26 12:56:57
