use rewa;

--
-- Table structure for table `employee`
--
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `EMP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMP_NAME` varchar(45) DEFAULT NULL,
  `EMP_HIRE_DATE` datetime DEFAULT NULL,
  `EMP_SALARY` decimal(11,4) DEFAULT NULL,
  PRIMARY KEY (`EMP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Table structure for table `rewa_coordinate_type`
--
DROP TABLE IF EXISTS `rewa_coordinate_type`;
CREATE TABLE `rewa_coordinate_type` (
  `idCoordinateType` int(11) NOT NULL,
  `type` varchar(20) NOT NULL,
  PRIMARY KEY (`idCoordinateType`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `rewa_field`
--
DROP TABLE IF EXISTS `rewa_field`;

CREATE TABLE `rewa_field` (
  `idField` int(11) NOT NULL AUTO_INCREMENT ,
  `field` varchar(45) NOT NULL,
  PRIMARY KEY (`idField`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rewa_coordinate_type`
--

LOCK TABLES `rewa_coordinate_type` WRITE;
INSERT INTO `rewa_coordinate_type` VALUES (1,'Telephone Pimaire'),(2,'Telephone Secondaire'),(3,'Email'),(4,'Adresse'),(5,'Facebook ID');
UNLOCK TABLES;

--
-- Table structure for table `rewa_role`
--
DROP TABLE IF EXISTS `rewa_role`;
CREATE TABLE `rewa_role` (
  `idRole` int(11) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`idRole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rewa_role`
--

LOCK TABLES `rewa_role` WRITE;
INSERT INTO `rewa_role` VALUES (1,'Enqueteur'),(2,'Superviseur'),(3,'Controlleur'),(10,'Admin');
UNLOCK TABLES;

--
-- Table structure for table `rewa_status`
--
DROP TABLE IF EXISTS `rewa_status`;
CREATE TABLE `rewa_status` (
  `idStatus` int(11) NOT NULL,
  `Status` varchar(45) NOT NULL,
  PRIMARY KEY (`idStatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rewa_status`
--
LOCK TABLES `rewa_status` WRITE;
INSERT INTO `rewa_status` VALUES (1,'Actif'),(2,'Inactif');
UNLOCK TABLES;

--
-- Table structure for table `rewa_person`
--

DROP TABLE IF EXISTS `rewa_person`;
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
  PRIMARY KEY (`idPerson`),
  KEY `FK_Person_Status_idx` (`status`),
  KEY `FK_Person_Createdby_idx` (`createdBy`),
  KEY `FK_Person_Modifiedby_idx` (`modifiedBy`),
  CONSTRAINT `FK_Person_Createdby` FOREIGN KEY (`createdBy`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Person_Modifiedby` FOREIGN KEY (`modifiedBy`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Person_Status` FOREIGN KEY (`status`) REFERENCES `rewa_status` (`idStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `rewa_comment`
--
DROP TABLE IF EXISTS `rewa_comment`;
CREATE TABLE `rewa_comment` (
  `idComment` int(11) NOT NULL AUTO_INCREMENT,
  `idAuthor` int(11) DEFAULT NULL,
  `commentDate` datetime NOT NULL,
  `comment` varchar(500) NOT NULL,
  PRIMARY KEY (`idComment`),
  KEY `FK_Comment_Author_idx` (`idAuthor`),
  CONSTRAINT `FK_Comment_Author` FOREIGN KEY (`idAuthor`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


--
-- Table structure for table `rewa_customer`
--
DROP TABLE IF EXISTS `rewa_customer`;
CREATE TABLE `rewa_customer` (
  `idCustomer` int(11) NOT NULL AUTO_INCREMENT,
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

--
-- Table structure for table `rewa_coordinate`
--
DROP TABLE IF EXISTS `rewa_coordinate`;
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

--
-- Table structure for table `rewa_person_level`
--
DROP TABLE IF EXISTS `rewa_person_level`;
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

--
-- Table structure for table `rewa_person_role`
--
DROP TABLE IF EXISTS `rewa_person_role`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `rewa_team`
--
DROP TABLE IF EXISTS `rewa_team`;
CREATE TABLE `rewa_team` (
  `idTeam` int(11) NOT NULL AUTO_INCREMENT,
  `idSupervisor` int(11) DEFAULT NULL,
  `validated` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTeam`),
  KEY `FK_Team_Supervison_idx` (`idSupervisor`),
  CONSTRAINT `FK_Team_Supervison` FOREIGN KEY (`idSupervisor`) REFERENCES `rewa_person` (`idPerson`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Table structure for table `rewa_study`
--
DROP TABLE IF EXISTS `rewa_study`;
CREATE TABLE `rewa_study` (
  `idStudy` int(11) NOT NULL AUTO_INCREMENT,
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


