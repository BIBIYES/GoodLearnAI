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

 Date: 23/09/2024 10:30:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_id` bigint NOT NULL AUTO_INCREMENT,
  `course_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `teacher_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`course_id`) USING BTREE,
  INDEX `teacher_id`(`teacher_id` ASC) USING BTREE,
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (5, 'mysql基础课程', 1);
INSERT INTO `course` VALUES (12, '数学', 1);

-- ----------------------------
-- Table structure for exam_paper
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper`;
CREATE TABLE `exam_paper`  (
  `exam_paper_id` bigint NOT NULL AUTO_INCREMENT,
  `exam_paper_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `creation_date` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `teacher_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`exam_paper_id`) USING BTREE,
  INDEX `teacher_id`(`teacher_id` ASC) USING BTREE,
  CONSTRAINT `exam_paper_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam_paper
-- ----------------------------
INSERT INTO `exam_paper` VALUES (9, '简单的mysql试卷', '2024-09-11 20:03:27', 1);

-- ----------------------------
-- Table structure for exam_paper_question
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper_question`;
CREATE TABLE `exam_paper_question`  (
  `exam_paper_question_id` bigint NOT NULL AUTO_INCREMENT,
  `exam_paper_id` bigint NULL DEFAULT NULL,
  `question_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`exam_paper_question_id`) USING BTREE,
  INDEX `exam_paper_id`(`exam_paper_id` ASC) USING BTREE,
  INDEX `question_id`(`question_id` ASC) USING BTREE,
  CONSTRAINT `exam_paper_question_ibfk_1` FOREIGN KEY (`exam_paper_id`) REFERENCES `exam_paper` (`exam_paper_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `exam_paper_question_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 210 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam_paper_question
-- ----------------------------
INSERT INTO `exam_paper_question` VALUES (205, 9, 14);
INSERT INTO `exam_paper_question` VALUES (206, 9, 15);
INSERT INTO `exam_paper_question` VALUES (207, 9, 18);
INSERT INTO `exam_paper_question` VALUES (208, 9, 19);
INSERT INTO `exam_paper_question` VALUES (209, 9, 20);

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `question_id` bigint NOT NULL AUTO_INCREMENT,
  `question_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `question_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `creation_date` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `course_id` bigint NULL DEFAULT NULL,
  `teacher_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`question_id`) USING BTREE,
  INDEX `course_id`(`course_id` ASC) USING BTREE,
  INDEX `question_ibfk_2`(`teacher_id` ASC) USING BTREE,
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `question_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (12, '查询所有数据', '在同一张 employees 表中，如何查询所有员工的数据？\n\n问题： 请写出相应的 SQL 查询语句。', '2024-09-20 09:47:13', 5, 1);
INSERT INTO `question` VALUES (13, '查询特定字段', '如果你只想查询 employees 表中的 name 和 department 两个字段的数据，该怎么做？\n\n问题： 请写出相应的 SQL 查询语句。', '2024-09-20 09:47:29', 5, 1);
INSERT INTO `question` VALUES (14, '更新数据', '假设你想要更新 employees 表中 id 为 1 的员工信息，将其 age 更新为 35。\n\n问题： 请写出相应的 SQL 更新语句。', '2024-09-20 09:47:42', 5, 1);
INSERT INTO `question` VALUES (15, '删除数据', '假设你想要删除 employees 表中 id 为 2 的员工记录。\n\n问题： 请写出相应的 SQL 删除语句。', '2024-09-20 09:47:55', 5, 1);
INSERT INTO `question` VALUES (16, '条件查询', '你需要查询所有在 \"IT\" 部门工作的员工信息。\n\n问题： 请写出相应的 SQL 查询语句。', '2024-09-20 09:48:08', 5, 1);
INSERT INTO `question` VALUES (17, '排序查询', '查询 employees 表中的所有员工信息，并按 age 降序排列。\n\n问题： 请写出相应的 SQL 查询语句。', '2024-09-20 09:48:17', 5, 1);
INSERT INTO `question` VALUES (18, ' 分页查询', '假设员工表中有大量数据，你需要按每页 10 条数据分页显示，查询第 2 页的数据。\n\n问题： 请写出相应的 SQL 查询语句。', '2024-09-20 09:48:25', 5, 1);
INSERT INTO `question` VALUES (19, '模糊查询', '你需要查询所有名字中包含 \"John\" 的员工。', '2024-09-20 09:48:33', 5, 1);
INSERT INTO `question` VALUES (20, '多条件查询', '你想要查询所有年龄在 25 到 40 岁之间，且部门为 \"Sales\" 的员工。\n\n问题： 请写出相应的 SQL 查询语句。', '2024-09-20 09:48:45', 5, 1);
INSERT INTO `question` VALUES (28, '数学题1', '123123123', '2024-09-21 15:37:27', 12, 1);

-- ----------------------------
-- Table structure for student_answer
-- ----------------------------
DROP TABLE IF EXISTS `student_answer`;
CREATE TABLE `student_answer`  (
  `answer_id` bigint NOT NULL AUTO_INCREMENT,
  `student_id` bigint NULL DEFAULT NULL,
  `exam_paper_question_id` bigint NULL DEFAULT NULL,
  `student_answer` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  PRIMARY KEY (`answer_id`) USING BTREE,
  INDEX `student_id`(`student_id` ASC) USING BTREE,
  INDEX `exam_paper_question_id`(`exam_paper_question_id` ASC) USING BTREE,
  CONSTRAINT `student_answer_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `student_answer_ibfk_2` FOREIGN KEY (`exam_paper_question_id`) REFERENCES `exam_paper_question` (`exam_paper_question_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_answer
-- ----------------------------

-- ----------------------------
-- Table structure for student_exam_paper
-- ----------------------------
DROP TABLE IF EXISTS `student_exam_paper`;
CREATE TABLE `student_exam_paper`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_id` bigint NOT NULL,
  `exam_paper_id` bigint NOT NULL,
  `joined_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `student_id`(`student_id` ASC, `exam_paper_id` ASC) USING BTREE,
  INDEX `exam_paper_id`(`exam_paper_id` ASC) USING BTREE,
  CONSTRAINT `student_exam_paper_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `student_exam_paper_ibfk_2` FOREIGN KEY (`exam_paper_id`) REFERENCES `exam_paper` (`exam_paper_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1837756616277553154 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_exam_paper
-- ----------------------------
INSERT INTO `student_exam_paper` VALUES (1837749134276751362, 1, 9, '2024-09-22 15:01:52');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` enum('teacher','student') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `email`(`email` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '骆子豪', 'mousehaozi@outlook.com', '211021d2b119d78fe0e0d4d29eeff687', 'teacher', '2024-08-25 22:06:12', '2024-08-25 22:06:12');
INSERT INTO `user` VALUES (2, '张三', 'zhangsan@student.com', '5f4dcc3b5aa765d61d8327deb882cf99', 'student', '2024-08-26 10:15:00', '2024-08-26 10:15:00');

SET FOREIGN_KEY_CHECKS = 1;
