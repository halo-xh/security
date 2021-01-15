-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: localhost    Database: securitydemo
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
-- Table structure for table `login_token`
--

DROP TABLE IF EXISTS `login_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_token` (
  `tid` int(4) NOT NULL AUTO_INCREMENT,
  `token` varchar(1000) NOT NULL,
  `user` varchar(255) NOT NULL COMMENT 'login name',
  `createdt` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `expiredt` datetime NOT NULL,
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_token`
--

LOCK TABLES `login_token` WRITE;
/*!40000 ALTER TABLE `login_token` DISABLE KEYS */;
INSERT INTO `login_token` VALUES (1,'2','3','2020-12-12 02:05:21','2020-12-12 19:05:23'),(2,'jwtToken','123','2020-12-12 19:46:59','2020-12-12 19:47:00'),(3,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsb2dpbk5hbWUiLCJhdXRoIjoiMiIsInVzZXJuYW1lIjoidGVzdCIsImV4cCI6MTYwNzgwOTY2OX0.xRz8yEToubSDNn8B7nroMspNvTXYoGhxfsP7jhS7yR9keI0JgOUM34hyvzz1HwOf2V9sLeYRvgBLNOAWONSBIQ','test','2020-12-12 19:47:49','2020-12-13 05:47:49'),(8,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiYXV0aCI6InJvbGUtdGVzdCIsImV4cCI6MTYxMDc1NTE3N30.J4g0YGrALObQ7Zz4OD-tlt77BzG80WUnflVvt93JW5s525d578JLinMQNjMaA9jLm8S9VZh7WQA3S4BGcT3pbw','test','2021-01-15 21:59:37','2021-01-16 07:59:37');
/*!40000 ALTER TABLE `login_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `res2res`
--

DROP TABLE IF EXISTS `res2res`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `res2res` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'table id',
  `resid` int(11) NOT NULL COMMENT 'resource id: function, resource',
  `parentid` int(11) NOT NULL COMMENT 'resource id : function , role',
  `createdt` datetime NOT NULL,
  `updatedt` datetime NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` char(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='mapping table: role -function, function - resource';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `res2res`
--

LOCK TABLES `res2res` WRITE;
/*!40000 ALTER TABLE `res2res` DISABLE KEYS */;
INSERT INTO `res2res` VALUES (1,1,2,'2020-12-11 20:07:11','2020-12-11 20:07:13',1,'N');
/*!40000 ALTER TABLE `res2res` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resources`
--

DROP TABLE IF EXISTS `resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resources` (
  `rid` int(4) NOT NULL,
  `resname` varchar(255) NOT NULL,
  `restype` varchar(255) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  `createdt` datetime NOT NULL,
  `updatedt` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `version` int(4) NOT NULL,
  `deleted` char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`rid`) USING BTREE,
  UNIQUE KEY `res_uk_resname` (`resname`) USING BTREE,
  UNIQUE KEY `res_uk_name_type` (`resname`,`restype`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources`
--

LOCK TABLES `resources` WRITE;
/*!40000 ALTER TABLE `resources` DISABLE KEYS */;
INSERT INTO `resources` VALUES (1,'res-test','api','/api/test','A','2020-12-11 20:02:18','2020-12-11 20:02:20',1,'N'),(2,'role-test','role',NULL,'A','2020-12-11 20:06:52','2020-12-11 20:06:52',1,'N'),(3,'res-test2','api','[POST]/api/test','A','2021-01-15 20:11:32','2021-01-15 20:11:32',1,'N');
/*!40000 ALTER TABLE `resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject_login`
--

DROP TABLE IF EXISTS `subject_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject_login` (
  `sid` int(4) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  `createdt` datetime NOT NULL,
  `updatedt` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `deleted` char(1) NOT NULL DEFAULT 'N',
  `version` int(4) NOT NULL,
  PRIMARY KEY (`sid`) USING BTREE,
  UNIQUE KEY `uk_username` (`loginname`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_login`
--

LOCK TABLES `subject_login` WRITE;
/*!40000 ALTER TABLE `subject_login` DISABLE KEYS */;
INSERT INTO `subject_login` VALUES (1,'test','$2a$10$Ij6k841hTug0jx25koayi.JMUypRxw00wIkptl./pqG/mIhwR2u5m','A','2020-12-11 20:04:37','2020-12-11 23:05:33','N',1);
/*!40000 ALTER TABLE `subject_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user2role`
--

DROP TABLE IF EXISTS `user2role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user2role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `createdt` datetime NOT NULL,
  `updatedt` datetime NOT NULL,
  `version` int(11) NOT NULL,
  `deleted` char(1) DEFAULT 'N',
  `rolename` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='user mapping role.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user2role`
--

LOCK TABLES `user2role` WRITE;
/*!40000 ALTER TABLE `user2role` DISABLE KEYS */;
INSERT INTO `user2role` VALUES (1,1,'2020-12-11 20:07:34','2020-12-11 20:07:36',1,'N','role-test');
/*!40000 ALTER TABLE `user2role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'securitydemo'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-15 22:02:53
