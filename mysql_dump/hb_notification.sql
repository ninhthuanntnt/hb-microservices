-- MySQL dump 10.13  Distrib 8.0.28, for macos12.2 (arm64)
--
-- Host: 127.0.0.1    Database: hb_notification
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `DATABASECHANGELOG`
--

DROP TABLE IF EXISTS `DATABASECHANGELOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `AUTHOR` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `FILENAME` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int NOT NULL,
  `EXECTYPE` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MD5SUM` varchar(35) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `COMMENTS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TAG` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `LIQUIBASE` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CONTEXTS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `LABELS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOG`
--

LOCK TABLES `DATABASECHANGELOG` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOG` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOG` (`ID`, `AUTHOR`, `FILENAME`, `DATEEXECUTED`, `ORDEREXECUTED`, `EXECTYPE`, `MD5SUM`, `DESCRIPTION`, `COMMENTS`, `TAG`, `LIQUIBASE`, `CONTEXTS`, `LABELS`, `DEPLOYMENT_ID`) VALUES ('HB-20210416130900-1','ninhthuanntnt','config/liquibase_ext/changelog/20210428165000_changelog.xml','2022-02-07 15:37:52',1,'EXECUTED','8:9767dfcefc31129de0f3a0c47a6ffce7','createTable tableName=hb_notifications; createIndex indexName=idx_notification_notification_id, tableName=hb_notifications','',NULL,'4.3.1',NULL,NULL,'4248270974'),('HB-20210416130900-2','ninhthuanntnt','config/liquibase_ext/changelog/20210428165000_changelog.xml','2022-02-07 15:37:54',2,'EXECUTED','8:c4ea9a73786f06574a8130d132fbe6cc','createTable tableName=hb_user_notifications; createIndex indexName=idx_user_notification_user_id, tableName=hb_user_notifications; createIndex indexName=idx_user_notification_notification_id, tableName=hb_user_notifications','',NULL,'4.3.1',NULL,NULL,'4248270974');
/*!40000 ALTER TABLE `DATABASECHANGELOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DATABASECHANGELOGLOCK`
--

DROP TABLE IF EXISTS `DATABASECHANGELOGLOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DATABASECHANGELOGLOCK`
--

LOCK TABLES `DATABASECHANGELOGLOCK` WRITE;
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` DISABLE KEYS */;
INSERT INTO `DATABASECHANGELOGLOCK` (`ID`, `LOCKED`, `LOCKGRANTED`, `LOCKEDBY`) VALUES (1,_binary '\0',NULL,NULL);
/*!40000 ALTER TABLE `DATABASECHANGELOGLOCK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hb_notifications`
--

DROP TABLE IF EXISTS `hb_notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hb_notifications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sender_id` bigint DEFAULT NULL,
  `source_id` bigint NOT NULL,
  `content` varchar(40) NOT NULL,
  `type` varchar(100) NOT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_notification_notification_id` (`source_id`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hb_notifications`
--

LOCK TABLES `hb_notifications` WRITE;
/*!40000 ALTER TABLE `hb_notifications` DISABLE KEYS */;
INSERT INTO `hb_notifications` (`id`, `sender_id`, `source_id`, `content`, `type`, `deleted`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (4,1,1014,'Test notification','POST',_binary '\0','user','2021-05-10 02:35:43.261','user','2021-05-26 13:53:44.788'),(25,1,0,'This is thong bao','ADMIN',_binary '\0','user','2021-06-01 09:22:31.023','user','2021-06-01 09:22:31.023'),(26,1,0,'this is noti','ADMIN',_binary '\0','user','2021-06-01 10:01:22.521','user','2021-06-01 10:01:22.521'),(68,1,0,'sfhjgm','ADMIN',_binary '\0','user','2021-06-02 06:53:32.963','user','2021-06-02 06:53:32.963'),(69,1,0,'asdasd','ADMIN',_binary '\0','user','2021-06-02 09:01:34.958','user','2021-06-02 09:01:34.958'),(70,1,0,'àhgfffff','ADMIN',_binary '\0','user','2021-06-02 15:00:31.240','user','2021-06-02 15:00:31.240'),(71,1,0,'sfghhjk','ADMIN',_binary '\0','user','2021-06-02 15:10:47.079','user','2021-06-02 15:10:47.079'),(72,1,0,'ssghdfgdfg','ADMIN',_binary '\0','user','2021-06-02 15:14:45.021','user','2021-06-02 15:14:45.021'),(73,1,0,'test','ADMIN',_binary '\0','user','2021-06-02 15:17:18.360','user','2021-06-02 15:17:18.360'),(74,1,0,'Xin chao cac con vo','ADMIN',_binary '\0','user','2021-06-04 04:11:35.835','user','2021-06-04 04:11:35.835'),(75,1,0,'Hello world','ADMIN',_binary '\0','user','2021-06-04 04:50:02.213','user','2021-06-04 04:50:02.213'),(76,1,0,'Hello world','ADMIN',_binary '\0','user','2021-06-04 04:50:19.991','user','2021-06-04 04:50:19.991'),(77,1,0,'hello wrld','ADMIN',_binary '\0','user','2021-06-04 06:45:46.260','user','2021-06-04 06:45:46.260'),(78,1,0,'Thong bao quang cao','ADMIN',_binary '\0','user','2021-06-04 06:56:54.874','user','2021-06-04 06:56:54.874'),(79,1,0,'asdasd','ADMIN',_binary '\0','user','2021-06-04 06:57:09.925','user','2021-06-04 06:57:09.925'),(80,1,0,'sdfsdf','ADMIN',_binary '\0','user','2021-06-04 07:10:06.286','user','2021-06-04 07:10:06.286'),(81,1,0,'ewrwer','ADMIN',_binary '\0','user','2021-06-04 07:13:02.483','user','2021-06-04 07:13:02.483'),(82,1,0,'asd','ADMIN',_binary '\0','user','2021-06-04 07:14:11.516','user','2021-06-04 07:14:11.516'),(83,1,0,'werdsfsdf','ADMIN',_binary '\0','user','2021-06-04 07:34:41.615','user','2021-06-04 07:34:41.615'),(84,1,0,'asfghjg','ADMIN',_binary '\0','user','2021-06-04 07:34:58.890','user','2021-06-04 07:34:58.890'),(85,1,0,'asd','ADMIN',_binary '\0','user','2021-06-04 07:36:11.994','user','2021-06-04 07:36:11.994'),(86,1,0,'asd','ADMIN',_binary '\0','user','2021-06-04 07:36:13.571','user','2021-06-04 07:36:13.571'),(87,1,0,'asd','ADMIN',_binary '\0','user','2021-06-04 07:37:24.117','user','2021-06-04 07:37:24.117'),(88,1,0,'hello world','ADMIN',_binary '\0','user','2021-06-04 07:39:33.439','user','2021-06-04 07:39:33.439'),(89,1,0,'a','ADMIN',_binary '\0','user','2021-06-04 07:39:58.089','user','2021-06-04 07:39:58.089'),(90,1,0,'sdfsdf','ADMIN',_binary '\0','user','2021-06-04 07:41:58.179','user','2021-06-04 07:41:58.179'),(91,1,0,'asdafasd','ADMIN',_binary '\0','user','2021-06-04 07:42:26.966','user','2021-06-04 07:42:26.966'),(92,1,0,'fasdfgdsf','ADMIN',_binary '\0','user','2021-06-04 07:56:44.174','user','2021-06-04 07:56:44.174'),(93,1,0,'Hello các bé','ADMIN',_binary '\0','user','2021-06-04 09:57:35.085','user','2021-06-04 09:57:35.085'),(95,1,0,'hello cac ban tre','ADMIN',_binary '\0','user','2021-06-06 08:34:18.806','user','2021-06-06 08:34:18.806'),(96,1,0,'ok ','ADMIN',_binary '\0','user','2021-06-06 08:35:09.149','user','2021-06-06 08:35:09.149'),(97,1,0,'test','ADMIN',_binary '\0','user','2021-06-06 08:36:34.975','user','2021-06-06 08:36:34.975'),(98,1,0,'asa','ADMIN',_binary '\0','user','2021-06-06 08:36:58.762','user','2021-06-06 08:36:58.762'),(99,1,0,'test','ADMIN',_binary '\0','user','2021-06-06 09:17:41.102','user','2021-06-06 09:17:41.102'),(100,1,0,'Tét','ADMIN',_binary '\0','user','2021-06-06 10:24:19.224','user','2021-06-06 10:24:19.224'),(102,1,0,'test test','ADMIN',_binary '\0','user','2021-06-06 15:34:38.825','user','2021-06-06 15:34:38.825'),(106,1,0,'sdfdsfdf','ADMIN',_binary '\0','user','2021-06-07 09:26:24.273','user','2021-06-07 09:26:24.273'),(107,977,1079,'Bài viết mới','POST',_binary '\0','user2','2021-06-12 15:42:57.890','user2','2021-06-12 15:42:57.890'),(108,1,1080,'BBBB','POST',_binary '\0','user','2021-06-12 15:47:00.580','user','2021-06-12 15:47:00.580'),(109,977,1153,'test','POST',_binary '\0','user2','2022-02-18 16:24:56.074','user2','2022-02-18 16:24:56.074'),(110,977,1154,'test','POST',_binary '\0','user2','2022-02-18 16:25:25.426','user2','2022-02-18 16:25:25.426'),(111,977,1155,'test','POST',_binary '\0','user2','2022-02-18 16:26:37.633','user2','2022-02-18 16:26:37.633'),(112,977,1157,'test','POST',_binary '\0','user2','2022-02-18 16:30:06.095','user2','2022-02-18 16:30:06.095'),(113,977,1158,'test2','POST',_binary '\0','user2','2022-02-18 16:43:10.410','user2','2022-02-18 16:43:10.410'),(114,977,1159,'test3','POST',_binary '\0','user2','2022-02-18 16:47:20.457','user2','2022-02-18 16:47:20.457'),(115,977,1160,'test4','POST',_binary '\0','user2','2022-02-18 16:49:28.835','user2','2022-02-18 16:49:28.835'),(116,977,1161,'test5','POST',_binary '\0','user2','2022-02-18 17:04:15.110','user2','2022-02-18 17:04:15.110'),(117,977,1162,'test 6','POST',_binary '\0','user2','2022-02-18 17:24:19.200','user2','2022-02-18 17:24:19.200'),(118,977,1163,'test7','POST',_binary '\0','user2','2022-02-19 14:26:01.859','user2','2022-02-19 14:26:01.859'),(119,977,1164,'test8','POST',_binary '\0','user2','2022-02-19 14:27:45.177','user2','2022-02-19 14:27:45.177');
/*!40000 ALTER TABLE `hb_notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hb_user_notifications`
--

DROP TABLE IF EXISTS `hb_user_notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hb_user_notifications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `notification_id` bigint NOT NULL,
  `seen` bit(1) NOT NULL DEFAULT b'0',
  `sent` bit(1) NOT NULL DEFAULT b'0',
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_notification_user_id` (`user_id`),
  KEY `idx_user_notification_notification_id` (`notification_id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hb_user_notifications`
--

LOCK TABLES `hb_user_notifications` WRITE;
/*!40000 ALTER TABLE `hb_user_notifications` DISABLE KEYS */;
INSERT INTO `hb_user_notifications` (`id`, `user_id`, `notification_id`, `seen`, `sent`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES (1,977,4,_binary '\0',_binary '','user','2021-05-10 02:35:43.301','user','2021-05-10 02:35:43.301'),(6,977,9,_binary '\0',_binary '\0','user','2021-05-30 16:05:48.336','user','2021-05-30 16:05:48.336'),(7,977,10,_binary '\0',_binary '\0','user','2021-05-30 16:06:18.471','user','2021-05-30 16:06:18.471'),(8,977,11,_binary '\0',_binary '\0','user','2021-05-31 10:11:25.870','user','2021-05-31 10:11:25.870'),(9,977,12,_binary '\0',_binary '\0','user','2021-05-31 10:12:10.833','user','2021-05-31 10:12:10.833'),(10,977,13,_binary '\0',_binary '\0','user','2021-05-31 10:16:23.883','user','2021-05-31 10:16:23.883'),(11,977,14,_binary '\0',_binary '\0','user','2021-05-31 10:16:57.960','user','2021-05-31 10:16:57.960'),(12,977,15,_binary '\0',_binary '\0','user','2021-05-31 10:32:46.102','user','2021-05-31 10:32:46.102'),(13,977,16,_binary '\0',_binary '\0','user','2021-05-31 10:34:33.454','user','2021-05-31 10:34:33.454'),(14,977,17,_binary '\0',_binary '\0','user','2021-05-31 10:40:00.026','user','2021-05-31 10:40:00.026'),(15,977,18,_binary '\0',_binary '\0','user','2021-05-31 10:42:32.037','user','2021-05-31 10:42:32.037'),(16,977,19,_binary '\0',_binary '\0','user','2021-05-31 10:45:15.746','user','2021-05-31 10:45:15.746'),(17,977,21,_binary '\0',_binary '\0','user','2021-05-31 13:40:24.808','user','2021-05-31 13:40:24.808'),(18,977,22,_binary '\0',_binary '\0','user','2021-05-31 14:30:51.939','user','2021-05-31 14:30:51.939'),(19,977,23,_binary '\0',_binary '\0','user','2021-05-31 14:38:20.206','user','2021-05-31 14:38:20.206'),(20,977,24,_binary '\0',_binary '\0','user','2021-05-31 14:39:26.793','user','2021-05-31 14:39:26.793'),(21,718,25,_binary '\0',_binary '\0','user','2021-06-01 09:22:31.052','user','2021-06-01 09:22:31.052'),(22,6,26,_binary '\0',_binary '\0','user','2021-06-01 10:01:22.525','user','2021-06-01 10:01:22.525'),(23,977,27,_binary '\0',_binary '\0','user','2021-06-01 14:43:23.878','user','2021-06-01 14:43:23.878'),(24,977,28,_binary '\0',_binary '\0','user','2021-06-01 14:43:25.811','user','2021-06-01 14:43:25.811'),(25,977,29,_binary '\0',_binary '\0','user','2021-06-01 14:43:26.693','user','2021-06-01 14:43:26.693'),(26,977,30,_binary '\0',_binary '\0','user','2021-06-01 14:43:27.570','user','2021-06-01 14:43:27.570'),(27,977,31,_binary '\0',_binary '\0','user','2021-06-01 14:43:28.270','user','2021-06-01 14:43:28.270'),(28,977,32,_binary '\0',_binary '\0','user','2021-06-01 14:43:29.003','user','2021-06-01 14:43:29.003'),(29,977,33,_binary '\0',_binary '\0','user','2021-06-01 14:43:29.547','user','2021-06-01 14:43:29.547'),(30,977,34,_binary '\0',_binary '\0','user','2021-06-01 14:43:30.231','user','2021-06-01 14:43:30.231'),(31,977,35,_binary '\0',_binary '\0','user','2021-06-01 14:43:30.893','user','2021-06-01 14:43:30.893'),(32,977,36,_binary '\0',_binary '\0','user','2021-06-01 15:00:43.150','user','2021-06-01 15:00:43.150'),(33,977,37,_binary '\0',_binary '\0','user','2021-06-01 15:00:44.396','user','2021-06-01 15:00:44.396'),(34,977,38,_binary '\0',_binary '\0','user','2021-06-01 15:00:45.325','user','2021-06-01 15:00:45.325'),(35,977,39,_binary '\0',_binary '\0','user','2021-06-01 15:00:46.502','user','2021-06-01 15:00:46.502'),(36,977,40,_binary '\0',_binary '\0','user','2021-06-01 15:00:47.402','user','2021-06-01 15:00:47.402'),(37,977,41,_binary '\0',_binary '\0','user','2021-06-01 15:00:48.257','user','2021-06-01 15:00:48.257'),(38,977,42,_binary '\0',_binary '\0','user','2021-06-01 15:00:49.120','user','2021-06-01 15:00:49.120'),(39,977,43,_binary '\0',_binary '\0','user','2021-06-01 15:00:49.991','user','2021-06-01 15:00:49.991'),(40,977,44,_binary '\0',_binary '\0','user','2021-06-01 15:02:23.750','user','2021-06-01 15:02:23.750'),(41,977,45,_binary '\0',_binary '\0','user','2021-06-01 15:02:24.543','user','2021-06-01 15:02:24.543'),(42,977,46,_binary '\0',_binary '\0','user','2021-06-01 15:02:25.271','user','2021-06-01 15:02:25.271'),(43,977,47,_binary '\0',_binary '\0','user','2021-06-01 15:02:26.080','user','2021-06-01 15:02:26.080'),(44,977,48,_binary '\0',_binary '\0','user','2021-06-01 15:02:26.760','user','2021-06-01 15:02:26.760'),(45,977,49,_binary '\0',_binary '\0','user','2021-06-01 15:02:27.468','user','2021-06-01 15:02:27.468'),(46,977,50,_binary '\0',_binary '\0','user','2021-06-01 15:02:28.161','user','2021-06-01 15:02:28.161'),(47,977,51,_binary '\0',_binary '\0','user','2021-06-01 15:02:28.826','user','2021-06-01 15:02:28.826'),(48,977,52,_binary '\0',_binary '\0','user','2021-06-01 15:02:29.511','user','2021-06-01 15:02:29.511'),(49,977,53,_binary '\0',_binary '\0','user','2021-06-01 15:02:30.210','user','2021-06-01 15:02:30.210'),(50,977,54,_binary '\0',_binary '\0','user','2021-06-01 15:02:30.916','user','2021-06-01 15:02:30.916'),(51,977,55,_binary '\0',_binary '\0','user','2021-06-01 15:02:31.660','user','2021-06-01 15:02:31.660'),(52,977,56,_binary '\0',_binary '\0','user','2021-06-01 15:02:32.265','user','2021-06-01 15:02:32.265'),(53,977,57,_binary '\0',_binary '\0','user','2021-06-01 15:02:32.878','user','2021-06-01 15:02:32.878'),(54,977,58,_binary '\0',_binary '\0','user','2021-06-01 15:04:22.504','user','2021-06-01 15:04:22.504'),(55,977,59,_binary '\0',_binary '\0','user','2021-06-01 15:05:02.912','user','2021-06-01 15:05:02.912'),(56,977,60,_binary '\0',_binary '\0','user','2021-06-02 01:48:12.009','user','2021-06-02 01:48:12.009'),(57,977,61,_binary '\0',_binary '\0','user','2021-06-02 01:49:01.271','user','2021-06-02 01:49:01.271'),(58,977,62,_binary '\0',_binary '\0','user','2021-06-02 01:51:24.707','user','2021-06-02 01:51:24.707'),(59,977,63,_binary '\0',_binary '\0','user','2021-06-02 02:30:39.709','user','2021-06-02 02:30:39.709'),(60,977,64,_binary '\0',_binary '\0','user','2021-06-02 02:31:09.400','user','2021-06-02 02:31:09.400'),(61,1000,68,_binary '\0',_binary '\0','user','2021-06-02 06:53:32.966','user','2021-06-02 06:53:32.966'),(62,1,69,_binary '\0',_binary '','user','2021-06-02 09:01:34.962','user','2022-02-18 17:14:54.424'),(63,1006,70,_binary '\0',_binary '\0','user','2021-06-02 15:00:31.249','user','2021-06-02 15:00:31.249'),(64,983,71,_binary '\0',_binary '\0','user','2021-06-02 15:10:47.088','user','2021-06-02 15:10:47.088'),(65,1006,72,_binary '\0',_binary '\0','user','2021-06-02 15:14:45.027','user','2021-06-02 15:14:45.027'),(66,1006,73,_binary '\0',_binary '\0','user','2021-06-02 15:17:18.363','user','2021-06-02 15:17:18.363'),(67,1006,74,_binary '\0',_binary '\0','user','2021-06-04 04:11:35.847','user','2021-06-04 04:11:35.847'),(68,1005,75,_binary '\0',_binary '\0','user','2021-06-04 04:50:02.220','user','2021-06-04 04:50:02.220'),(69,1005,76,_binary '\0',_binary '\0','user','2021-06-04 04:50:19.994','user','2021-06-04 04:50:19.994'),(70,1006,77,_binary '\0',_binary '\0','user','2021-06-04 06:45:46.279','user','2021-06-04 06:45:46.279'),(71,1001,78,_binary '\0',_binary '\0','user','2021-06-04 06:56:54.878','user','2021-06-04 06:56:54.878'),(72,1006,79,_binary '\0',_binary '\0','user','2021-06-04 06:57:09.928','user','2021-06-04 06:57:09.928'),(73,1006,80,_binary '\0',_binary '\0','user','2021-06-04 07:10:06.293','user','2021-06-04 07:10:06.293'),(74,1006,81,_binary '\0',_binary '\0','user','2021-06-04 07:13:02.486','user','2021-06-04 07:13:02.486'),(75,1006,82,_binary '\0',_binary '\0','user','2021-06-04 07:14:11.519','user','2021-06-04 07:14:11.519'),(76,1006,83,_binary '\0',_binary '\0','user','2021-06-04 07:34:41.618','user','2021-06-04 07:34:41.618'),(77,1006,84,_binary '\0',_binary '\0','user','2021-06-04 07:34:58.894','user','2021-06-04 07:34:58.894'),(78,1006,85,_binary '\0',_binary '\0','user','2021-06-04 07:36:11.996','user','2021-06-04 07:36:11.996'),(79,1006,86,_binary '\0',_binary '\0','user','2021-06-04 07:36:13.574','user','2021-06-04 07:36:13.574'),(80,1006,87,_binary '\0',_binary '\0','user','2021-06-04 07:37:24.121','user','2021-06-04 07:37:24.121'),(81,1006,88,_binary '\0',_binary '\0','user','2021-06-04 07:39:33.442','user','2021-06-04 07:39:33.442'),(82,1006,89,_binary '\0',_binary '\0','user','2021-06-04 07:39:58.092','user','2021-06-04 07:39:58.092'),(83,1006,90,_binary '\0',_binary '\0','user','2021-06-04 07:41:58.182','user','2021-06-04 07:41:58.182'),(84,1006,91,_binary '\0',_binary '\0','user','2021-06-04 07:42:26.969','user','2021-06-04 07:42:26.969'),(85,1006,92,_binary '\0',_binary '\0','user','2021-06-04 07:56:44.179','user','2021-06-04 07:56:44.179'),(86,994,93,_binary '\0',_binary '\0','user','2021-06-04 09:57:35.094','user','2021-06-04 09:57:35.094'),(87,977,94,_binary '\0',_binary '\0','user','2021-06-06 07:05:40.264','user','2021-06-06 07:05:40.264'),(88,1005,95,_binary '\0',_binary '\0','user','2021-06-06 08:34:18.876','user','2021-06-06 08:34:18.876'),(89,1006,96,_binary '\0',_binary '\0','user','2021-06-06 08:35:09.153','user','2021-06-06 08:35:09.153'),(90,1001,97,_binary '\0',_binary '\0','user','2021-06-06 08:36:34.979','user','2021-06-06 08:36:34.979'),(91,1004,98,_binary '\0',_binary '\0','user','2021-06-06 08:36:58.767','user','2021-06-06 08:36:58.767'),(92,1005,99,_binary '\0',_binary '\0','user','2021-06-06 09:17:41.106','user','2021-06-06 09:17:41.106'),(93,1006,100,_binary '\0',_binary '\0','user','2021-06-06 10:24:19.228','user','2021-06-06 10:24:19.228'),(94,11,102,_binary '\0',_binary '\0','user','2021-06-06 15:34:38.830','user','2021-06-06 15:34:38.830'),(95,977,103,_binary '\0',_binary '\0','user','2021-06-06 15:48:06.507','user','2021-06-06 15:48:06.507'),(96,977,104,_binary '\0',_binary '\0','user','2021-06-07 02:33:14.054','user','2021-06-07 02:33:14.054'),(97,977,105,_binary '\0',_binary '\0','user','2021-06-07 08:51:35.798','user','2021-06-07 08:51:35.798'),(98,1006,106,_binary '\0',_binary '\0','user','2021-06-07 09:26:24.284','user','2021-06-07 09:26:24.284'),(99,1,107,_binary '\0',_binary '','user2','2021-06-12 15:42:57.939','user','2022-02-18 17:14:54.424'),(100,977,108,_binary '\0',_binary '','user','2021-06-12 15:47:00.586','user','2021-06-12 15:47:00.586'),(101,1,109,_binary '\0',_binary '','user2','2022-02-18 16:24:56.486','user','2022-02-18 17:14:54.478'),(102,124,109,_binary '\0',_binary '\0','user2','2022-02-18 16:24:56.504','user2','2022-02-18 16:24:56.504'),(103,1034,109,_binary '\0',_binary '\0','user2','2022-02-18 16:24:56.514','user2','2022-02-18 16:24:56.514'),(104,1,110,_binary '\0',_binary '','user2','2022-02-18 16:25:25.520','user','2022-02-18 17:14:54.424'),(105,124,110,_binary '\0',_binary '\0','user2','2022-02-18 16:25:25.532','user2','2022-02-18 16:25:25.532'),(106,1034,110,_binary '\0',_binary '\0','user2','2022-02-18 16:25:25.542','user2','2022-02-18 16:25:25.542'),(107,1,111,_binary '\0',_binary '','user2','2022-02-18 16:26:37.840','user','2022-02-18 17:14:54.424'),(108,124,111,_binary '\0',_binary '\0','user2','2022-02-18 16:26:37.850','user2','2022-02-18 16:26:37.850'),(109,1034,111,_binary '\0',_binary '\0','user2','2022-02-18 16:26:37.858','user2','2022-02-18 16:26:37.858'),(110,1,112,_binary '\0',_binary '','user2','2022-02-18 16:30:06.305','user','2022-02-18 17:14:54.476'),(111,124,112,_binary '\0',_binary '\0','user2','2022-02-18 16:30:06.316','user2','2022-02-18 16:30:06.316'),(112,1034,112,_binary '\0',_binary '\0','user2','2022-02-18 16:30:06.321','user2','2022-02-18 16:30:06.321'),(113,1,113,_binary '\0',_binary '','user2','2022-02-18 16:43:10.582','user','2022-02-18 17:14:54.476'),(114,124,113,_binary '\0',_binary '\0','user2','2022-02-18 16:43:10.605','user2','2022-02-18 16:43:10.605'),(115,1034,113,_binary '\0',_binary '\0','user2','2022-02-18 16:43:10.625','user2','2022-02-18 16:43:10.625'),(116,1,114,_binary '\0',_binary '','user2','2022-02-18 16:47:20.637','user','2022-02-18 17:14:54.477'),(117,124,114,_binary '\0',_binary '\0','user2','2022-02-18 16:47:20.652','user2','2022-02-18 16:47:20.652'),(118,1034,114,_binary '\0',_binary '\0','user2','2022-02-18 16:47:20.661','user2','2022-02-18 16:47:20.661'),(119,1,115,_binary '\0',_binary '','user2','2022-02-18 16:49:28.974','user','2022-02-18 17:14:54.424'),(120,124,115,_binary '\0',_binary '\0','user2','2022-02-18 16:49:28.981','user2','2022-02-18 16:49:28.981'),(121,1034,115,_binary '\0',_binary '\0','user2','2022-02-18 16:49:28.990','user2','2022-02-18 16:49:28.990'),(122,1,116,_binary '\0',_binary '','user2','2022-02-18 17:04:15.282','user','2022-02-18 17:14:54.424'),(123,124,116,_binary '\0',_binary '\0','user2','2022-02-18 17:04:15.307','user2','2022-02-18 17:04:15.307'),(124,1034,116,_binary '\0',_binary '\0','user2','2022-02-18 17:04:15.320','user2','2022-02-18 17:04:15.320'),(125,1,117,_binary '\0',_binary '','user2','2022-02-18 17:24:19.345','user','2022-02-18 17:24:19.732'),(126,124,117,_binary '\0',_binary '\0','user2','2022-02-18 17:24:19.362','user2','2022-02-18 17:24:19.362'),(127,1034,117,_binary '\0',_binary '\0','user2','2022-02-18 17:24:19.368','user2','2022-02-18 17:24:19.368'),(128,1,118,_binary '\0',_binary '\0','user2','2022-02-19 14:26:02.117','user2','2022-02-19 14:26:02.117'),(129,124,118,_binary '\0',_binary '\0','user2','2022-02-19 14:26:02.141','user2','2022-02-19 14:26:02.141'),(130,1034,118,_binary '\0',_binary '\0','user2','2022-02-19 14:26:02.146','user2','2022-02-19 14:26:02.146'),(131,1,119,_binary '\0',_binary '\0','user2','2022-02-19 14:27:45.325','user2','2022-02-19 14:27:45.325'),(132,124,119,_binary '\0',_binary '\0','user2','2022-02-19 14:27:45.351','user2','2022-02-19 14:27:45.351'),(133,1034,119,_binary '\0',_binary '\0','user2','2022-02-19 14:27:45.361','user2','2022-02-19 14:27:45.361');
/*!40000 ALTER TABLE `hb_user_notifications` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-06  1:35:06
