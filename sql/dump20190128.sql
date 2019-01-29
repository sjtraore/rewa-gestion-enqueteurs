-- MySQL dump 10.13  Distrib 5.6.31, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: rewa
-- ------------------------------------------------------
-- Server version	5.6.31

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_coordinate`
--

LOCK TABLES `rewa_coordinate` WRITE;
/*!40000 ALTER TABLE `rewa_coordinate` DISABLE KEYS */;
INSERT INTO `rewa_coordinate` VALUES (1,'1234567',1,2,2,1,'2018-10-13 14:38:28','2018-10-14 15:10:44',1,1,NULL),(3,'',1,1,2,1,'2018-10-14 15:10:44','2019-01-14 01:18:47',1,1,NULL),(6,'33-44-55-66',1,1,17,1,'2018-10-14 21:47:18','2019-01-08 10:40:08',1,1,NULL),(8,'',1,1,4,1,'2018-10-15 23:39:52','2018-10-15 23:39:58',1,1,NULL),(9,'',3,1,4,1,'2018-10-15 23:39:52','2018-10-15 23:39:52',1,1,NULL),(10,'',3,1,2,1,'2018-10-15 23:40:17','2019-01-14 01:18:48',1,1,NULL),(11,'',1,1,1,1,'2018-10-26 21:50:19','2018-10-26 21:50:19',1,1,NULL),(12,'',3,1,1,1,'2018-10-26 21:50:19','2018-10-26 21:50:19',1,1,NULL),(13,'',5,1,2,1,'2018-10-26 22:16:04','2019-01-14 01:18:48',1,1,NULL),(14,'',2,1,2,1,'2018-10-26 22:25:50','2019-01-14 01:18:48',1,1,NULL),(15,'testfab',NULL,1,2,1,'2018-10-26 23:32:58','2018-10-26 23:32:58',1,1,NULL),(16,'testfab2',NULL,1,2,1,'2018-10-26 23:35:24','2018-10-26 23:35:24',1,1,NULL),(17,'123 rue de la RépubliqueBamako, Mali',4,1,2,1,'2018-10-26 23:47:49','2018-10-26 23:47:49',1,1,NULL),(18,'manna@gmail.com',NULL,1,2,1,'2018-10-26 23:47:49','2018-10-26 23:47:49',1,1,NULL),(19,'manna@gmail.com',NULL,1,2,1,'2018-10-26 23:48:08','2018-10-26 23:48:08',1,1,NULL),(20,'+33 7 34 56 79 10',NULL,1,2,1,'2018-10-26 23:50:41','2018-10-26 23:50:41',1,1,NULL),(21,'+33 7 34 56 79 10',NULL,1,2,1,'2018-10-26 23:51:58','2018-10-26 23:51:58',1,1,NULL),(22,'isylla@gmail.com',3,1,17,1,'2018-12-05 22:57:47','2018-12-05 22:58:01',1,1,NULL),(23,'66-55-44-33',1,1,18,1,'2019-01-04 16:45:07','2019-01-04 16:45:07',1,1,NULL),(24,'77-66-55-44',2,1,18,1,'2019-01-04 16:45:07','2019-01-04 16:45:07',1,1,NULL),(25,'mohamed@yahoo.fr',3,1,18,1,'2019-01-04 16:45:07','2019-01-25 12:04:42',1,1,NULL),(26,'mohamedfb',5,1,18,1,'2019-01-04 16:45:07','2019-01-25 12:04:51',1,1,NULL),(28,'11-22-33-44',1,1,37,1,'2019-01-17 23:45:57','2019-01-22 10:36:45',1,1,NULL),(29,'22-34-45-55',2,1,37,1,'2019-01-17 23:46:02','2019-01-17 23:46:02',1,1,NULL),(30,'jtraore@gmail.com',3,1,37,1,'2019-01-17 23:46:06','2019-01-17 23:46:06',1,1,NULL),(31,'facebookjt2',5,1,37,1,'2019-01-17 23:46:09','2019-01-22 10:44:40',1,1,NULL),(32,'11-22-33-44',1,1,38,1,'2019-01-28 09:27:45','2019-01-28 09:27:45',1,1,NULL),(33,'22-33-44-55',2,1,38,1,'2019-01-28 09:27:45','2019-01-28 09:27:45',1,1,NULL),(34,'m.boubacar@researchwestafrica.com',3,1,38,1,'2019-01-28 09:27:45','2019-01-28 09:27:45',1,1,NULL),(35,'m.boubacaFB',5,1,38,1,'2019-01-28 09:27:45','2019-01-28 09:27:45',1,1,NULL);
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
  `idCustomer` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `idStatus` int(11) NOT NULL,
  `createdDate` datetime NOT NULL,
  `modifiedDate` datetime NOT NULL,
  `createdBy` int(11) DEFAULT NULL,
  `modifiedBy` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCustomer`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `FK_Customer_Createdby_idx` (`createdBy`),
  KEY `FK_Customer_Modifiedby_idx` (`modifiedBy`),
  KEY `FK_Coordinate_Status_idx` (`idStatus`),
  CONSTRAINT `FK_Coordinate_Status` FOREIGN KEY (`idStatus`) REFERENCES `rewa_status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Customer_Createdby` FOREIGN KEY (`createdBy`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Customer_Modifiedby` FOREIGN KEY (`modifiedBy`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_customer`
--

LOCK TABLES `rewa_customer` WRITE;
/*!40000 ALTER TABLE `rewa_customer` DISABLE KEYS */;
INSERT INTO `rewa_customer` VALUES (3,'Orange Mali',1,'2019-01-14 15:05:22','2019-01-14 15:05:22',1,1),(4,'Malitel',1,'2019-01-14 23:23:28','2019-01-14 23:23:28',1,1),(5,'Client 3',2,'2019-01-14 23:28:08','2019-01-28 01:19:37',1,1),(6,'Cabinet Test',1,'2019-01-28 01:22:06','2019-01-28 01:22:06',1,1),(7,'BOA',1,'2019-01-28 09:33:17','2019-01-28 09:33:17',1,1);
/*!40000 ALTER TABLE `rewa_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewa_diploma`
--

DROP TABLE IF EXISTS `rewa_diploma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewa_diploma` (
  `idDiploma` int(11) NOT NULL,
  `diploma` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idDiploma`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_diploma`
--

LOCK TABLES `rewa_diploma` WRITE;
/*!40000 ALTER TABLE `rewa_diploma` DISABLE KEYS */;
INSERT INTO `rewa_diploma` VALUES (1,'Baccalauréat'),(2,'Brevet de Technicien Supérieur (BTS)'),(3,'Licence	'),(4,'Master et plus');
/*!40000 ALTER TABLE `rewa_diploma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewa_field`
--

DROP TABLE IF EXISTS `rewa_field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewa_field` (
  `idField` int(11) NOT NULL AUTO_INCREMENT,
  `field` varchar(45) NOT NULL,
  PRIMARY KEY (`idField`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_field`
--

LOCK TABLES `rewa_field` WRITE;
/*!40000 ALTER TABLE `rewa_field` DISABLE KEYS */;
INSERT INTO `rewa_field` VALUES (1,'Français écrit'),(2,'Français oral'),(3,'Langues locales'),(4,'MS Excel'),(5,'MS PowerPoint'),(6,'MS Word'),(50,'Ponctualité'),(51,'Assiduité');
/*!40000 ALTER TABLE `rewa_field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewa_person`
--

DROP TABLE IF EXISTS `rewa_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewa_person` (
  `idPerson` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `createdDate` datetime NOT NULL,
  `modifiedDate` datetime NOT NULL,
  `createdBy` int(11) DEFAULT NULL,
  `modifiedBy` int(11) DEFAULT NULL,
  `picture` blob,
  `status` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `idTeam` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPerson`),
  KEY `FK_Person_Status_idx` (`status`),
  KEY `FK_Person_Createdby_idx` (`createdBy`),
  KEY `FK_Person_Modifiedby_idx` (`modifiedBy`),
  CONSTRAINT `FK_Person_Createdby` FOREIGN KEY (`createdBy`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Person_Modifiedby` FOREIGN KEY (`modifiedBy`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Person_Status` FOREIGN KEY (`status`) REFERENCES `rewa_status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_person`
--

LOCK TABLES `rewa_person` WRITE;
/*!40000 ALTER TABLE `rewa_person` DISABLE KEYS */;
INSERT INTO `rewa_person` VALUES (1,'John','Doe','jd','2018-09-27 14:52:50','2019-01-25 12:07:06',NULL,NULL,NULL,1,'jd',NULL),(2,'Anna','Maria','amaria','2018-09-27 15:05:37','2019-01-14 01:18:48',NULL,NULL,NULL,1,'amaria',NULL),(4,'Richard','Ferdinand',NULL,'2018-09-27 16:36:02','2019-01-08 13:09:31',NULL,NULL,NULL,1,'',NULL),(5,'Louis','Richard',NULL,'2018-09-27 16:48:21','2019-01-25 12:02:56',NULL,NULL,NULL,2,'',NULL),(17,'Idriss','Sylla','idriss','2018-10-14 21:46:48','2019-01-08 10:40:08',NULL,NULL,NULL,1,'idriss',NULL),(18,'Doumbia','Mohamed','mohamed','2018-11-27 23:31:50','2019-01-25 12:04:51',NULL,NULL,NULL,1,'mohamed',NULL),(37,'Jacques','Traoré','jd','2019-01-17 23:24:03','2019-01-22 11:05:31',NULL,NULL,NULL,1,'jt',NULL),(38,'Mohomodou','Boubacar','m.boubacar','2019-01-28 09:24:58','2019-01-28 09:27:45',NULL,NULL,NULL,1,'m.boubacar',NULL);
/*!40000 ALTER TABLE `rewa_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewa_person_diploma`
--

DROP TABLE IF EXISTS `rewa_person_diploma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewa_person_diploma` (
  `idPerson` int(11) NOT NULL DEFAULT '0',
  `idDiploma` int(11) NOT NULL,
  PRIMARY KEY (`idPerson`,`idDiploma`),
  KEY `FK_Diploma_PersonDiploma_idx` (`idDiploma`),
  CONSTRAINT `FK_Diploma_PersonDiploma` FOREIGN KEY (`idDiploma`) REFERENCES `rewa_diploma` (`idDiploma`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Person_PersonDiploma` FOREIGN KEY (`idPerson`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_person_diploma`
--

LOCK TABLES `rewa_person_diploma` WRITE;
/*!40000 ALTER TABLE `rewa_person_diploma` DISABLE KEYS */;
INSERT INTO `rewa_person_diploma` VALUES (2,1),(37,1),(4,3),(18,3),(4,4),(37,4);
/*!40000 ALTER TABLE `rewa_person_diploma` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_person_level`
--

LOCK TABLES `rewa_person_level` WRITE;
/*!40000 ALTER TABLE `rewa_person_level` DISABLE KEYS */;
INSERT INTO `rewa_person_level` VALUES (1,1,0,'2019-01-13 23:59:12',3,NULL,NULL),(1,2,0,'2019-01-13 23:59:12',3,NULL,NULL),(1,3,0,'2019-01-13 23:59:12',3,NULL,''),(2,1,0,'2019-01-11 00:02:18',3,NULL,NULL),(2,2,0,'2019-01-11 00:02:18',3,NULL,NULL),(2,3,0,'2019-01-11 00:02:18',3,NULL,''),(5,1,0,'2019-01-15 01:09:08',3,NULL,NULL),(5,2,0,'2019-01-15 01:09:08',3,NULL,NULL),(5,3,0,'2019-01-15 01:09:08',3,NULL,''),(18,1,2,'2019-01-25 12:04:42',3,NULL,NULL),(18,2,2,'2019-01-25 12:04:42',3,NULL,NULL),(18,3,3,'2019-01-25 12:04:42',3,NULL,'Bambara, Poular'),(18,4,2,'2019-01-25 12:04:42',3,NULL,NULL),(18,5,3,'2019-01-25 12:04:42',3,NULL,NULL),(18,6,1,'2019-01-25 12:04:42',3,NULL,NULL),(37,1,3,'2019-01-18 00:35:41',3,NULL,NULL),(37,1,3,'2019-01-18 21:28:34',3,NULL,NULL),(37,1,3,'2019-01-22 10:36:45',3,NULL,NULL),(37,1,3,'2019-01-22 10:43:33',3,NULL,NULL),(37,2,2,'2019-01-18 00:35:41',3,NULL,NULL),(37,2,2,'2019-01-18 21:28:34',3,NULL,NULL),(37,2,2,'2019-01-22 10:36:45',3,NULL,NULL),(37,2,2,'2019-01-22 10:43:33',3,NULL,NULL),(37,3,1,'2019-01-18 00:35:41',3,NULL,'bambara'),(37,4,1,'2019-01-22 11:00:52',3,NULL,NULL),(37,5,2,'2019-01-22 10:54:25',3,NULL,NULL),(37,6,3,'2019-01-22 11:00:52',3,NULL,NULL);
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
  PRIMARY KEY (`idPerson`,`idRole`),
  KEY `FK_PersonRole_Role_idx` (`idRole`),
  CONSTRAINT `FK_PersonRole_Person` FOREIGN KEY (`idPerson`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PersonRole_Role` FOREIGN KEY (`idRole`) REFERENCES `rewa_role` (`idRole`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_person_role`
--

LOCK TABLES `rewa_person_role` WRITE;
/*!40000 ALTER TABLE `rewa_person_role` DISABLE KEYS */;
INSERT INTO `rewa_person_role` VALUES (2,1),(4,1),(5,1),(17,1),(18,1),(37,1),(1,2),(2,2),(5,2),(18,2),(1,3),(2,3),(18,3),(1,10),(17,10),(38,10);
/*!40000 ALTER TABLE `rewa_person_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewa_person_study`
--

DROP TABLE IF EXISTS `rewa_person_study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewa_person_study` (
  `idPerson` int(11) DEFAULT NULL,
  `idStudy` int(11) DEFAULT NULL,
  `markPunctuality` decimal(2,1) DEFAULT NULL,
  `markDiligence` decimal(2,1) DEFAULT NULL,
  `idPersonStudy` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idPersonStudy`),
  KEY `FK_Person_Study_PersonID_idx` (`idPerson`),
  KEY `FK_Person_Study_StudyID` (`idStudy`),
  CONSTRAINT `FK_Person_Study_PersonID` FOREIGN KEY (`idPerson`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Person_Study_StudyID` FOREIGN KEY (`idStudy`) REFERENCES `rewa_study` (`idStudy`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_person_study`
--

LOCK TABLES `rewa_person_study` WRITE;
/*!40000 ALTER TABLE `rewa_person_study` DISABLE KEYS */;
INSERT INTO `rewa_person_study` VALUES (4,2,5.0,4.0,8),(18,2,4.0,3.5,9),(2,2,4.5,5.0,10),(37,2,3.0,0.0,11),(18,1,5.0,3.5,25),(4,1,3.0,NULL,26),(17,1,NULL,NULL,27),(2,1,3.0,3.0,28),(18,9,NULL,NULL,35),(4,9,NULL,NULL,36),(2,9,NULL,NULL,37),(18,10,5.0,3.5,38),(17,10,3.5,3.0,39);
/*!40000 ALTER TABLE `rewa_person_study` ENABLE KEYS */;
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
  `idStudy` int(11) NOT NULL AUTO_INCREMENT,
  `idCustomer` int(11) NOT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `idStatus` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `createDate` datetime NOT NULL,
  `idCreatedBy` int(11) NOT NULL,
  `idModifiedBy` int(11) NOT NULL,
  `modifiedDate` datetime NOT NULL,
  `idSupervisor` int(11) DEFAULT NULL,
  `idValidator` int(11) DEFAULT NULL,
  `validateDate` datetime DEFAULT NULL,
  `idCloser` int(11) DEFAULT NULL,
  `closeDate` datetime DEFAULT NULL,
  PRIMARY KEY (`idStudy`),
  KEY `FK_Study_Customer_idx` (`idCustomer`),
  KEY `FK_Study_Status_idx` (`idStatus`),
  KEY `FK_Study_Supervisor_idx` (`idSupervisor`),
  KEY `FK_Study_CreatedBy_idx` (`idCreatedBy`),
  KEY `FK_Study_ModifiedBy_idx` (`idModifiedBy`),
  KEY `FK_Study_Validator_idx` (`idValidator`),
  KEY `FK_Study_Closer_idx` (`idCloser`),
  CONSTRAINT `FK_Study_Closer` FOREIGN KEY (`idCloser`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Study_CreatedBy` FOREIGN KEY (`idCreatedBy`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Study_Customer` FOREIGN KEY (`idCustomer`) REFERENCES `rewa_customer` (`idCustomer`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Study_ModifiedBy` FOREIGN KEY (`idModifiedBy`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Study_Status` FOREIGN KEY (`idStatus`) REFERENCES `rewa_status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Study_Supervisor` FOREIGN KEY (`idSupervisor`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Study_Validator` FOREIGN KEY (`idValidator`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewa_study`
--

LOCK TABLES `rewa_study` WRITE;
/*!40000 ALTER TABLE `rewa_study` DISABLE KEYS */;
INSERT INTO `rewa_study` VALUES (1,3,'2019-01-16 00:00:00','2019-01-24 00:00:00',1,'Étude de cas 3','2019-01-28 02:18:48',1,1,'2019-01-28 02:18:48',2,1,'2019-01-27 23:25:38',1,'2019-01-28 02:18:42'),(2,4,'2019-01-21 00:00:00','2019-01-31 00:00:00',2,'Étude de cas 2','2019-01-28 01:57:45',1,1,'2019-01-28 01:57:45',1,1,'2019-01-23 01:48:23',1,'2019-01-28 01:57:45'),(9,6,'2019-01-28 00:00:00','2019-01-31 00:00:00',1,'Étude de cas 4','2019-01-28 10:02:07',1,1,'2019-01-28 10:02:06',18,1,'2019-01-28 10:02:06',NULL,NULL),(10,7,'2019-01-28 00:00:00','2019-01-31 00:00:00',2,'Étude de cas 5','2019-01-28 09:36:33',1,1,'2019-01-28 09:36:33',5,1,'2019-01-28 09:34:51',1,'2019-01-28 09:36:33');
/*!40000 ALTER TABLE `rewa_study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewa_team`
--

DROP TABLE IF EXISTS `rewa_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rewa_team` (
  `idTeam` int(11) NOT NULL AUTO_INCREMENT,
  `idSupervisor` int(11) DEFAULT NULL,
  `validated` int(11) DEFAULT NULL,
  `idStudy` int(11) DEFAULT NULL,
  `createdBy` int(11) NOT NULL,
  `modifiedBy` int(11) NOT NULL,
  `createdDate` datetime NOT NULL,
  `modifiedDate` datetime NOT NULL,
  PRIMARY KEY (`idTeam`),
  KEY `FK_Team_Supervison_idx` (`idSupervisor`),
  CONSTRAINT `FK_Team_Supervison` FOREIGN KEY (`idSupervisor`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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

-- Dump completed on 2019-01-28 20:27:14
