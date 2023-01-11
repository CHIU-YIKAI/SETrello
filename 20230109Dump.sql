-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gras_test
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `gitrepository`
--

DROP TABLE IF EXISTS `gitrepository`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gitrepository` (
  `repo_id` varchar(50) NOT NULL,
  `reponame` varchar(50) DEFAULT NULL,
  `ownername` varchar(50) DEFAULT NULL,
  `project_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`repo_id`),
  KEY `project_id_idx` (`project_id`),
  CONSTRAINT `project_id_by_gitrepository` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gitrepository`
--

LOCK TABLES `gitrepository` WRITE;
/*!40000 ALTER TABLE `gitrepository` DISABLE KEYS */;
INSERT INTO `gitrepository` VALUES ('8460a96a-8a62-4169-ab20-766b45c94b46','GitRepositoryAnalysisSystem','allen880612','4ef8e372-87c8-4a6c-99d6-98106c91cd3e'),('c0f9938f-7e3c-4383-83f2-569ac926e327','SETeam2','moon110598096','683b3bf7-bd5d-4529-9d08-8ebde5af5094'),('cf4528de-a8f0-4538-90f9-83faf3f90777','GitRepositoryAnalysisSystem','allen880612','e42ad4ce-bfe8-4075-8611-4bbca3107239'),('d3df6e5e-1eed-4509-b7b6-bf28b266ac40','GitRepositoryAnalysisSystem','allen880612','2831eaf7-1b48-4bd8-97fb-fb26bc4c1e11'),('d7c915cd-5d5b-4741-ab5e-29d922f73387','GitRepositoryAnalysisSystem','allen880612','93d07697-594a-4fe3-88d9-e0e84ad6d9df'),('testRepo','GitRepositoryAnalysisSystem','allen880612','projectID');
/*!40000 ALTER TABLE `gitrepository` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `project_id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `starttime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES ('2831eaf7-1b48-4bd8-97fb-fb26bc4c1e11','GSAS','GitHub SonarQube Analysis System','2022-01-07 17:01:03'),('4ef8e372-87c8-4a6c-99d6-98106c91cd3e','test_project','SE_Project','2022-01-11 12:01:12'),('683b3bf7-bd5d-4529-9d08-8ebde5af5094','SETeam2','SETeam2','2022-01-07 13:43:02'),('93d07697-594a-4fe3-88d9-e0e84ad6d9df','GSAS','GSAS','2022-01-07 13:23:09'),('ad31a255-fd13-4e38-9c40-cbcb7cd2e6b0','GSAS','SE_Project','2022-01-10 09:27:39'),('cafb1806-4337-4674-aa5d-4109f4c56220','MakeBigMoney','abc','2022-01-11 11:35:54'),('e42ad4ce-bfe8-4075-8611-4bbca3107239','SEProject','GSAS','2022-01-11 05:41:42'),('fcd5ff74-2231-4600-ae50-444df983d097','GSAS','SE_Project','2022-01-10 09:26:38'),('projectID','haha','dddd','2022-01-03 06:51:41');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sonarproject`
--

DROP TABLE IF EXISTS `sonarproject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sonarproject` (
  `sonar_project_id` varchar(50) NOT NULL,
  `host_url` varchar(200) DEFAULT NULL,
  `token` varchar(200) DEFAULT NULL,
  `project_key` varchar(200) DEFAULT NULL,
  `project_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sonar_project_id`),
  KEY `project_id_idx` (`project_id`),
  CONSTRAINT `project_id_by_sonarproject` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sonarproject`
--

LOCK TABLES `sonarproject` WRITE;
/*!40000 ALTER TABLE `sonarproject` DISABLE KEYS */;
INSERT INTO `sonarproject` VALUES ('4fb77c33-3c08-425f-b840-01b45c6b0bdf','140.124.181.17:9000','a53369b9a02a246677d37ba6bca10b233456b2b4','GSAS','93d07697-594a-4fe3-88d9-e0e84ad6d9df'),('9a3fec64-8a16-4561-a88f-2bccb7e130e7','140.124.181.17:9000','a53369b9a02a246677d37ba6bca10b233456b2b4','GSAS','2831eaf7-1b48-4bd8-97fb-fb26bc4c1e11'),('9bbb3e7c-8219-4f2d-830a-0f9430366525','140.124.181.10:9000','11e13ca642192adc569273621c0cd49b97e4e2b6','GitRepositoryAnalysisSystem','683b3bf7-bd5d-4529-9d08-8ebde5af5094'),('a99e06e5-a63a-4cbb-8ffc-a6bae6d83a5b','140.124.181.17:9000','a53369b9a02a246677d37ba6bca10b233456b2b4','GSAS','e42ad4ce-bfe8-4075-8611-4bbca3107239'),('cadfea30-fbbe-4bc9-b2f3-09e62afff081','140.124.181.17:9000','a53369b9a02a246677d37ba6bca10b233456b2b4','GSAS','4ef8e372-87c8-4a6c-99d6-98106c91cd3e');
/*!40000 ALTER TABLE `sonarproject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trelloproject`
--

DROP TABLE IF EXISTS `trelloproject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trelloproject` (
  `trelloProjectID` varchar(50) NOT NULL,
  `userID` varchar(50) NOT NULL,
  `trelloBoardID` varchar(200) NOT NULL,
  `BoardName` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `starttime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`trelloProjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trelloproject`
--

LOCK TABLES `trelloproject` WRITE;
/*!40000 ALTER TABLE `trelloproject` DISABLE KEYS */;
/*!40000 ALTER TABLE `trelloproject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` varchar(50) NOT NULL,
  `name` varchar(200) NOT NULL,
  `account` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `github_id` varchar(200) DEFAULT NULL,
  `trelloKey` varchar(200) DEFAULT NULL,
  `trelloToken` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('59200efc-a9a0-4946-b1ce-267aee0a2d64','allen880612','allen880612@gmail.com','','29161791',NULL,NULL),('b92498c7-fce8-41d6-a3a3-d1677eace8a0','demo','demo','test','',NULL,NULL),('dfac35ed-6921-470b-9150-ce9d652516c4','110598071','null','','88961674',NULL,NULL),('test','test','test','test',NULL,NULL,NULL),('testUser','fish han','account','password','',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_project`
--

DROP TABLE IF EXISTS `user_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_project` (
  `user_id` varchar(50) DEFAULT NULL,
  `project_id` varchar(50) DEFAULT NULL,
  KEY `user_id_idx` (`user_id`),
  KEY `project_id_idx` (`project_id`),
  CONSTRAINT `project_id_by_user_project` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id_by_user_project` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_project`
--

LOCK TABLES `user_project` WRITE;
/*!40000 ALTER TABLE `user_project` DISABLE KEYS */;
INSERT INTO `user_project` VALUES ('testUser','projectID'),('59200efc-a9a0-4946-b1ce-267aee0a2d64','93d07697-594a-4fe3-88d9-e0e84ad6d9df'),('test','683b3bf7-bd5d-4529-9d08-8ebde5af5094'),('test','2831eaf7-1b48-4bd8-97fb-fb26bc4c1e11'),('dfac35ed-6921-470b-9150-ce9d652516c4','4ef8e372-87c8-4a6c-99d6-98106c91cd3e');
/*!40000 ALTER TABLE `user_project` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-09 10:26:23
