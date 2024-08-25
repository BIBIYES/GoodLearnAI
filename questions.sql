/*
 Navicat Premium Dump SQL

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : localhost:3306
 Source Schema         : good_learn_ai

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 25/08/2024 11:05:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions`  (
  `id` int NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `prompt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `uploadDate` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of questions
-- ----------------------------
INSERT INTO `questions` VALUES (0, 'mysql查询', '假设你有一个名为 students 的表，它有以下结构：\n\nsql\nCREATE TABLE students (\n    id INT AUTO_INCREMENT PRIMARY KEY,\n    name VARCHAR(100) NOT NULL,\n    age INT,\n    grade VARCHAR(5),\n    class VARCHAR(50)\n);\n表中已经有一些数据。\n\n问题：\n写一个 SQL 查询语句，用来找出所有年龄大于 18 岁的学生的名字和年龄。', '假设你有一个名为 students 的表，它有以下结构：  sql CREATE TABLE students (     id INT AUTO_INCREMENT PRIMARY KEY,     name VARCHAR(100) NOT NULL,     age INT,     grade VARCHAR(5),     class VARCHAR(50) ); 表中已经有一些数据。  问题： 写一个 SQL 查询语句，用来找出所有年龄大于 18 岁的学生的名字和年龄。 我的答案是$', NULL);

SET FOREIGN_KEY_CHECKS = 1;
