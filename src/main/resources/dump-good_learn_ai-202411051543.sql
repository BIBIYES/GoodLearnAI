-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: good_learn_ai
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course_created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `course_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int NOT NULL,
  PRIMARY KEY (`course_id`),
  KEY `idx_course_user_id` (`user_id`),
  CONSTRAINT `fk_course_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (17,'mysql基础课程','2024-10-21 06:25:22','2024-10-21 06:25:22',9);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_paper`
--

DROP TABLE IF EXISTS `exam_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam_paper` (
  `exam_paper_id` int NOT NULL AUTO_INCREMENT,
  `exam_paper_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `exam_paper_created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `exam_paper_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int NOT NULL,
  PRIMARY KEY (`exam_paper_id`),
  KEY `idx_exam_paper_user_id` (`user_id`),
  CONSTRAINT `fk_exam_paper_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_paper`
--

LOCK TABLES `exam_paper` WRITE;
/*!40000 ALTER TABLE `exam_paper` DISABLE KEYS */;
INSERT INTO `exam_paper` VALUES (26,'mysql测试试卷1','2024-10-21 06:28:00','2024-10-21 06:28:00',9);
/*!40000 ALTER TABLE `exam_paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_paper_question`
--

DROP TABLE IF EXISTS `exam_paper_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam_paper_question` (
  `exam_paper_id` int NOT NULL,
  `question_id` int NOT NULL,
  `exam_paper_question_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`exam_paper_question_id`),
  KEY `idx_epq_question_id` (`question_id`),
  KEY `idx_epq_exam_paper_id` (`exam_paper_id`),
  CONSTRAINT `fk_epq_exam_paper` FOREIGN KEY (`exam_paper_id`) REFERENCES `exam_paper` (`exam_paper_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_epq_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=304 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_paper_question`
--

LOCK TABLES `exam_paper_question` WRITE;
/*!40000 ALTER TABLE `exam_paper_question` DISABLE KEYS */;
INSERT INTO `exam_paper_question` VALUES (26,24,299),(26,25,300),(26,26,301),(26,129,303);
/*!40000 ALTER TABLE `exam_paper_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `question_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `question_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `question_created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `question_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `course_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`question_id`),
  KEY `idx_question_course_id` (`course_id`),
  KEY `idx_question_user_id` (`user_id`),
  CONSTRAINT `fk_question_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_question_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (24,' 什么是外键？','在 MySQL 中使用外键有什么好处和注意事项','2024-10-21 06:27:30','2024-10-21 06:27:30',17,9),(25,'MySQL 中的索引有什么作用？有哪些常见的索引类型？','MySQL 中的索引有什么作用？有哪些常见的索引类型？','2024-10-21 06:27:38','2024-10-21 06:27:38',17,9),(26,'在 MySQL 中，如何优化一条查询语句？请列举几种常见的优化方法。','在 MySQL 中，如何优化一条查询语句？请列举几种常见的优化方法。','2024-10-21 06:27:45','2024-10-21 06:27:45',17,9),(129,'自定义题目','![骆子豪](https://s21.ax1x.com/2024/10/05/pA8a5C9.jpg)\n\n\n# 一个简单的题目\n你知道如何去制作吗？\n\n\n1. 现在好助学支持markdown语法啦！！！\n2. 还支持什么呢？\n','2024-10-24 04:52:52','2024-10-24 04:52:52',17,9);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_answer`
--

DROP TABLE IF EXISTS `student_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_answer` (
  `answer_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `exam_paper_id` int NOT NULL,
  `exam_paper_question_id` int NOT NULL,
  `answer_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `answer_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ai_answer` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  PRIMARY KEY (`answer_id`),
  UNIQUE KEY `unique_exam_paper_question` (`exam_paper_id`,`exam_paper_question_id`),
  KEY `idx_student_answer_user_id` (`user_id`),
  KEY `idx_student_answer_exam_paper_id` (`exam_paper_id`),
  KEY `idx_student_answer_epq_id` (`exam_paper_question_id`),
  CONSTRAINT `fk_student_answer_epq` FOREIGN KEY (`exam_paper_question_id`) REFERENCES `exam_paper_question` (`question_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_student_answer_exam_paper` FOREIGN KEY (`exam_paper_id`) REFERENCES `exam_paper` (`exam_paper_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_student_answer_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_answer`
--

LOCK TABLES `student_answer` WRITE;
/*!40000 ALTER TABLE `student_answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_exam_paper`
--

DROP TABLE IF EXISTS `student_exam_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_exam_paper` (
  `student_exam_paper_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `exam_paper_id` int NOT NULL,
  `join_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`student_exam_paper_id`),
  UNIQUE KEY `unique_student_exam` (`user_id`,`exam_paper_id`),
  KEY `fk_sep_exam_paper` (`exam_paper_id`),
  CONSTRAINT `fk_sep_exam_paper` FOREIGN KEY (`exam_paper_id`) REFERENCES `exam_paper` (`exam_paper_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sep_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_exam_paper`
--

LOCK TABLES `student_exam_paper` WRITE;
/*!40000 ALTER TABLE `student_exam_paper` DISABLE KEYS */;
INSERT INTO `student_exam_paper` VALUES (42,11,26,'2024-10-22 10:12:54','未完成');
/*!40000 ALTER TABLE `student_exam_paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (9,'傅顺','1329987218@qq.com','e10adc3949ba59abbe56e057f20f883e','teacher','2024-10-21 06:25:02','2024-10-21 06:25:02'),(11,'骆子豪','3203727672@qq.com','e10adc3949ba59abbe56e057f20f883e','student','2024-10-22 10:12:29','2024-10-24 12:54:08');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'good_learn_ai'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-05 15:43:03
