/*
 Navicat MySQL Data Transfer

 Source Server         : LocalDB
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : lims

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 28/05/2019 19:59:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_tbl
-- ----------------------------
DROP TABLE IF EXISTS `admin_tbl`;
CREATE TABLE `admin_tbl` (
  `adm_id` int(11) NOT NULL AUTO_INCREMENT,
  `adm_username` varchar(255) DEFAULT NULL,
  `adm_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`adm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_tbl
-- ----------------------------
BEGIN;
INSERT INTO `admin_tbl` VALUES (1, 'admin', 'admin');
COMMIT;

-- ----------------------------
-- Table structure for course_tbl
-- ----------------------------
DROP TABLE IF EXISTS `course_tbl`;
CREATE TABLE `course_tbl` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `teacher_id` (`teacher_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `course_tbl_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher_tbl` (`teacher_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `course_tbl_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `group_tbl` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_tbl
-- ----------------------------
BEGIN;
INSERT INTO `course_tbl` VALUES (1, 'A课程', 1, 1);
INSERT INTO `course_tbl` VALUES (2, 'A课程', 1, 2);
INSERT INTO `course_tbl` VALUES (3, 'A课程', 1, 3);
INSERT INTO `course_tbl` VALUES (4, 'A课程', 1, 4);
INSERT INTO `course_tbl` VALUES (5, 'A课程', 2, 5);
INSERT INTO `course_tbl` VALUES (6, 'A课程', 2, 6);
INSERT INTO `course_tbl` VALUES (7, 'A课程', 2, 7);
INSERT INTO `course_tbl` VALUES (8, 'A课程', 2, 8);
INSERT INTO `course_tbl` VALUES (9, 'B课程', 3, 1);
INSERT INTO `course_tbl` VALUES (10, 'B课程', 3, 2);
INSERT INTO `course_tbl` VALUES (11, 'C课程', 4, 5);
INSERT INTO `course_tbl` VALUES (12, 'C课程', 4, 6);
INSERT INTO `course_tbl` VALUES (13, 'D课程', 3, 3);
INSERT INTO `course_tbl` VALUES (14, 'D课程', 3, 4);
INSERT INTO `course_tbl` VALUES (15, 'E课程', 4, 7);
INSERT INTO `course_tbl` VALUES (16, 'E课程', 4, 8);
INSERT INTO `course_tbl` VALUES (17, 'F课程', 1, 5);
INSERT INTO `course_tbl` VALUES (18, 'F课程', 1, 6);
INSERT INTO `course_tbl` VALUES (19, 'G课程', 1, 7);
INSERT INTO `course_tbl` VALUES (20, 'G课程', 1, 8);
COMMIT;

-- ----------------------------
-- Table structure for group_tbl
-- ----------------------------
DROP TABLE IF EXISTS `group_tbl`;
CREATE TABLE `group_tbl` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_tbl
-- ----------------------------
BEGIN;
INSERT INTO `group_tbl` VALUES (1, 'A班');
INSERT INTO `group_tbl` VALUES (2, 'B班');
INSERT INTO `group_tbl` VALUES (3, 'C班');
INSERT INTO `group_tbl` VALUES (4, 'D班');
INSERT INTO `group_tbl` VALUES (5, 'E班');
INSERT INTO `group_tbl` VALUES (6, 'F班');
INSERT INTO `group_tbl` VALUES (7, 'G班');
INSERT INTO `group_tbl` VALUES (8, 'H班');
COMMIT;

-- ----------------------------
-- Table structure for lab_tbl
-- ----------------------------
DROP TABLE IF EXISTS `lab_tbl`;
CREATE TABLE `lab_tbl` (
  `lab_id` int(11) NOT NULL AUTO_INCREMENT,
  `lab_name` varchar(255) DEFAULT NULL,
  `lab_address` varchar(255) DEFAULT NULL,
  `adm_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`lab_id`),
  KEY `adm_id` (`adm_id`),
  CONSTRAINT `lab_tbl_ibfk_1` FOREIGN KEY (`adm_id`) REFERENCES `admin_tbl` (`adm_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lab_tbl
-- ----------------------------
BEGIN;
INSERT INTO `lab_tbl` VALUES (1, 'A实验室', 'E501', 1);
INSERT INTO `lab_tbl` VALUES (2, 'B实验室', 'E502', 1);
INSERT INTO `lab_tbl` VALUES (3, 'C实验室', 'E503', 1);
INSERT INTO `lab_tbl` VALUES (4, 'D实验室', 'E504', 1);
INSERT INTO `lab_tbl` VALUES (5, 'E实验室', 'E505', 1);
INSERT INTO `lab_tbl` VALUES (6, 'F实验室', 'E601', 1);
INSERT INTO `lab_tbl` VALUES (7, 'G实验室', 'E602', 1);
INSERT INTO `lab_tbl` VALUES (8, 'H实验室', 'E603', 1);
INSERT INTO `lab_tbl` VALUES (9, 'I实验室', 'E604', 1);
INSERT INTO `lab_tbl` VALUES (10, 'J实验室', 'E605', 1);
COMMIT;

-- ----------------------------
-- Table structure for order_tbl
-- ----------------------------
DROP TABLE IF EXISTS `order_tbl`;
CREATE TABLE `order_tbl` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_date` varchar(255) DEFAULT NULL,
  `order_time` int(11) DEFAULT NULL,
  `order_status` int(11) DEFAULT NULL,
  `order_message` varchar(255) DEFAULT NULL,
  `lab_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `lab_id` (`lab_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `order_tbl_ibfk_1` FOREIGN KEY (`lab_id`) REFERENCES `lab_tbl` (`lab_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_tbl_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course_tbl` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_tbl
-- ----------------------------
BEGIN;
INSERT INTO `order_tbl` VALUES (1, '2019-05-13', 0, 0, '', 1, NULL);
INSERT INTO `order_tbl` VALUES (2, '2019-05-13', 0, 1, '', 3, 3);
INSERT INTO `order_tbl` VALUES (3, '2019-05-13', 0, 2, '', 5, 5);
INSERT INTO `order_tbl` VALUES (4, '2019-05-13', 0, 3, '', 7, 7);
INSERT INTO `order_tbl` VALUES (5, '2019-05-13', 0, 0, '', 9, NULL);
INSERT INTO `order_tbl` VALUES (6, '2019-05-13', 1, 0, '', 2, NULL);
INSERT INTO `order_tbl` VALUES (7, '2019-05-13', 1, 1, '', 4, 4);
INSERT INTO `order_tbl` VALUES (8, '2019-05-13', 1, 2, '', 6, 6);
INSERT INTO `order_tbl` VALUES (9, '2019-05-13', 1, 3, '', 8, 8);
INSERT INTO `order_tbl` VALUES (10, '2019-05-13', 1, 0, '', 10, NULL);
INSERT INTO `order_tbl` VALUES (11, '2019-05-14', 0, 0, '', 4, NULL);
INSERT INTO `order_tbl` VALUES (12, '2019-05-14', 0, 0, '', 7, NULL);
INSERT INTO `order_tbl` VALUES (13, '2019-05-14', 1, 0, '', 9, NULL);
INSERT INTO `order_tbl` VALUES (14, '2019-05-14', 1, 0, '', 10, NULL);
INSERT INTO `order_tbl` VALUES (15, '2019-05-15', 0, 0, '', 1, NULL);
INSERT INTO `order_tbl` VALUES (16, '2019-05-15', 0, 0, '', 2, NULL);
INSERT INTO `order_tbl` VALUES (17, '2019-05-15', 1, 0, '', 3, NULL);
INSERT INTO `order_tbl` VALUES (18, '2019-05-16', 0, 0, '', 4, NULL);
INSERT INTO `order_tbl` VALUES (19, '2019-05-16', 1, 0, '', 5, NULL);
INSERT INTO `order_tbl` VALUES (20, '2019-05-17', 0, 0, '', 6, NULL);
INSERT INTO `order_tbl` VALUES (21, '2019-05-17', 1, 0, '', 7, NULL);
INSERT INTO `order_tbl` VALUES (22, '2019-05-20', 0, 0, '', 8, NULL);
INSERT INTO `order_tbl` VALUES (23, '2019-05-20', 0, 0, '', 9, NULL);
INSERT INTO `order_tbl` VALUES (24, '2019-05-20', 1, 0, '', 10, NULL);
INSERT INTO `order_tbl` VALUES (25, '2019-05-20', 1, 0, '', 2, NULL);
INSERT INTO `order_tbl` VALUES (26, '2019-05-20', 1, 0, '', 4, NULL);
INSERT INTO `order_tbl` VALUES (27, '2019-05-21', 0, 0, '', 6, NULL);
INSERT INTO `order_tbl` VALUES (28, '2019-05-21', 0, 0, '', 8, NULL);
INSERT INTO `order_tbl` VALUES (29, '2019-05-22', 0, 0, '', 10, NULL);
INSERT INTO `order_tbl` VALUES (30, '2019-05-22', 1, 0, '', 1, NULL);
INSERT INTO `order_tbl` VALUES (31, '2019-05-22', 1, 0, '', 3, NULL);
INSERT INTO `order_tbl` VALUES (32, '2019-05-23', 0, 0, '', 5, NULL);
INSERT INTO `order_tbl` VALUES (33, '2019-05-23', 0, 0, '', 7, NULL);
INSERT INTO `order_tbl` VALUES (34, '2019-05-23', 0, 0, '', 9, NULL);
INSERT INTO `order_tbl` VALUES (35, '2019-05-23', 0, 0, '', 2, NULL);
INSERT INTO `order_tbl` VALUES (36, '2019-05-23', 1, 0, '', 4, NULL);
INSERT INTO `order_tbl` VALUES (37, '2019-05-23', 1, 0, '', 6, NULL);
INSERT INTO `order_tbl` VALUES (38, '2019-05-23', 1, 0, '', 8, NULL);
INSERT INTO `order_tbl` VALUES (39, '2019-05-23', 1, 0, '', 10, NULL);
INSERT INTO `order_tbl` VALUES (40, '2019-05-24', 0, 0, '', 1, NULL);
INSERT INTO `order_tbl` VALUES (41, '2019-05-24', 0, 0, '', 2, NULL);
INSERT INTO `order_tbl` VALUES (42, '2019-05-24', 0, 0, '', 3, NULL);
INSERT INTO `order_tbl` VALUES (43, '2019-05-24', 0, 0, '', 4, NULL);
INSERT INTO `order_tbl` VALUES (44, '2019-05-24', 1, 0, '', 5, NULL);
INSERT INTO `order_tbl` VALUES (45, '2019-05-24', 1, 0, '', 6, NULL);
INSERT INTO `order_tbl` VALUES (46, '2019-05-26', 0, 0, '', 7, NULL);
INSERT INTO `order_tbl` VALUES (47, '2019-05-26', 0, 0, '', 8, NULL);
INSERT INTO `order_tbl` VALUES (48, '2019-05-26', 0, 0, '', 9, NULL);
INSERT INTO `order_tbl` VALUES (49, '2019-05-26', 0, 0, '', 10, NULL);
INSERT INTO `order_tbl` VALUES (50, '2019-05-26', 1, 0, '', 7, NULL);
INSERT INTO `order_tbl` VALUES (51, '2019-05-26', 1, 0, '', 8, NULL);
INSERT INTO `order_tbl` VALUES (52, '2019-05-26', 1, 0, '', 9, NULL);
INSERT INTO `order_tbl` VALUES (53, '2019-05-27', 0, 0, '', 1, NULL);
INSERT INTO `order_tbl` VALUES (54, '2019-05-27', 0, 0, '', 3, NULL);
INSERT INTO `order_tbl` VALUES (55, '2019-05-27', 0, 0, '', 5, NULL);
INSERT INTO `order_tbl` VALUES (56, '2019-05-27', 1, 0, '', 6, NULL);
INSERT INTO `order_tbl` VALUES (57, '2019-05-27', 1, 0, '', 7, NULL);
INSERT INTO `order_tbl` VALUES (58, '2019-05-28', 1, 0, '', 2, NULL);
INSERT INTO `order_tbl` VALUES (59, '2019-05-28', 1, 0, '', 3, NULL);
INSERT INTO `order_tbl` VALUES (60, '2019-05-28', 1, 0, '', 4, NULL);
INSERT INTO `order_tbl` VALUES (61, '2019-05-29', 1, 0, '', 5, NULL);
INSERT INTO `order_tbl` VALUES (62, '2019-05-29', 1, 0, '', 6, NULL);
INSERT INTO `order_tbl` VALUES (63, '2019-05-29', 1, 0, '', 7, NULL);
INSERT INTO `order_tbl` VALUES (64, '2019-05-30', 0, 0, '', 9, NULL);
INSERT INTO `order_tbl` VALUES (65, '2019-05-30', 0, 0, '', 10, NULL);
INSERT INTO `order_tbl` VALUES (66, '2019-05-30', 0, 0, '', 4, NULL);
INSERT INTO `order_tbl` VALUES (67, '2019-05-30', 0, 0, '', 5, NULL);
INSERT INTO `order_tbl` VALUES (68, '2019-05-30', 1, 0, '', 6, NULL);
INSERT INTO `order_tbl` VALUES (69, '2019-05-30', 1, 0, '', 2, NULL);
INSERT INTO `order_tbl` VALUES (70, '2019-05-30', 1, 0, '', 4, NULL);
INSERT INTO `order_tbl` VALUES (71, '2019-05-30', 1, 0, '', 5, NULL);
INSERT INTO `order_tbl` VALUES (72, '2019-05-31', 0, 0, '', 1, NULL);
INSERT INTO `order_tbl` VALUES (73, '2019-05-31', 0, 0, '', 2, NULL);
INSERT INTO `order_tbl` VALUES (74, '2019-05-31', 0, 0, '', 3, NULL);
INSERT INTO `order_tbl` VALUES (75, '2019-05-31', 0, 0, '', 4, NULL);
COMMIT;

-- ----------------------------
-- Table structure for report_tbl
-- ----------------------------
DROP TABLE IF EXISTS `report_tbl`;
CREATE TABLE `report_tbl` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT,
  `report_content_part1` varchar(255) DEFAULT NULL,
  `report_content_part2` varchar(255) DEFAULT NULL,
  `report_content_part3` varchar(255) DEFAULT NULL,
  `report_status` int(11) DEFAULT NULL,
  `report_score_part1` double DEFAULT NULL,
  `report_score_part2` double DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `stu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`report_id`),
  KEY `order_id` (`order_id`),
  KEY `stu_id` (`stu_id`),
  CONSTRAINT `report_tbl_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order_tbl` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `report_tbl_ibfk_2` FOREIGN KEY (`stu_id`) REFERENCES `student_tbl` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report_tbl
-- ----------------------------
BEGIN;
INSERT INTO `report_tbl` VALUES (1, '实验内容', '实验源程序/步骤', '实验心得', 0, NULL, NULL, 2, 8);
INSERT INTO `report_tbl` VALUES (2, '实验内容', '实验源程序/步骤', '实验心得', 1, 85, 75, 2, 9);
INSERT INTO `report_tbl` VALUES (3, '', '', '', 2, NULL, NULL, 7, 10);
INSERT INTO `report_tbl` VALUES (4, '', '', '', 2, NULL, NULL, 7, 11);
COMMIT;

-- ----------------------------
-- Table structure for student_tbl
-- ----------------------------
DROP TABLE IF EXISTS `student_tbl`;
CREATE TABLE `student_tbl` (
  `stu_id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_number` varchar(255) DEFAULT NULL,
  `stu_password` varchar(255) DEFAULT NULL,
  `stu_name` varchar(255) DEFAULT NULL,
  `stu_phone` varchar(255) DEFAULT NULL,
  `stu_email` varchar(255) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`stu_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `student_tbl_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `group_tbl` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_tbl
-- ----------------------------
BEGIN;
INSERT INTO `student_tbl` VALUES (1, '201503010101', '201503010101', 'A班1号同学', '18163988300', '18163988300@qq.com', 1);
INSERT INTO `student_tbl` VALUES (2, '201503010102', '201503010102', 'A班2号同学', '18163988301', '18163988301@qq.com', 1);
INSERT INTO `student_tbl` VALUES (3, '201503010103', '201503010103', 'A班3号同学', '18163988302', '18163988302@qq.com', 1);
INSERT INTO `student_tbl` VALUES (4, '201503010104', '201503010104', 'A班4号同学', '18163988303', '18163988303@qq.com', 1);
INSERT INTO `student_tbl` VALUES (5, '201503010105', '201503010105', 'A班5号同学', '18163988304', '18163988304@qq.com', 1);
INSERT INTO `student_tbl` VALUES (6, '201503010201', '201503010201', 'B班1号同学', '18163988305', '18163988305@qq.com', 2);
INSERT INTO `student_tbl` VALUES (7, '201503010202', '201503010202', 'B班2号同学', '18163988306', '18163988306@qq.com', 2);
INSERT INTO `student_tbl` VALUES (8, '201503020101', '201503020101', 'C班1号同学', '18163988307', '18163988307@qq.com', 3);
INSERT INTO `student_tbl` VALUES (9, '201503020102', '201503020102', 'C班2号同学', '18163988308', '18163988308@qq.com', 3);
INSERT INTO `student_tbl` VALUES (10, '201503020201', '201503020201', 'D班1号同学', '18163988309', '18163988309@qq.com', 4);
INSERT INTO `student_tbl` VALUES (11, '201503020202', '201503020202', 'D班2号同学', '18163988310', '18163988310@qq.com', 4);
INSERT INTO `student_tbl` VALUES (12, '201503030101', '201503030101', 'E班1号同学', '18163988311', '18163988311@qq.com', 5);
INSERT INTO `student_tbl` VALUES (13, '201503030102', '201503030102', 'E班2号同学', '18163988312', '18163988312@qq.com', 5);
INSERT INTO `student_tbl` VALUES (14, '201503030201', '201503030201', 'F班1号同学', '18163988313', '18163988313@qq.com', 6);
INSERT INTO `student_tbl` VALUES (15, '201503030202', '201503030202', 'F班2号同学', '18163988314', '18163988314@qq.com', 6);
INSERT INTO `student_tbl` VALUES (16, '201503040101', '201503040101', 'G班1号同学', '18163988315', '18163988315@qq.com', 7);
INSERT INTO `student_tbl` VALUES (17, '201503040102', '201503040102', 'G班2号同学', '18163988316', '18163988316@qq.com', 7);
INSERT INTO `student_tbl` VALUES (18, '201503040201', '201503040201', 'H班1号同学', '18163988317', '18163988317@qq.com', 8);
INSERT INTO `student_tbl` VALUES (19, '201503040202', '201503040202', 'H班2号同学', '18163988318', '18163988318@qq.com', 8);
COMMIT;

-- ----------------------------
-- Table structure for teacher_tbl
-- ----------------------------
DROP TABLE IF EXISTS `teacher_tbl`;
CREATE TABLE `teacher_tbl` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_number` varchar(255) DEFAULT NULL,
  `teacher_password` varchar(255) DEFAULT NULL,
  `teacher_name` varchar(255) DEFAULT NULL,
  `teacher_phone` varchar(255) DEFAULT NULL,
  `teacher_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_tbl
-- ----------------------------
BEGIN;
INSERT INTO `teacher_tbl` VALUES (1, '1001', '1001', '1号教工', '17352621001', '17352621001@qq.com');
INSERT INTO `teacher_tbl` VALUES (2, '1002', '1002', '2号教工', '17352621002', '17352621002@qq.com');
INSERT INTO `teacher_tbl` VALUES (3, '1003', '1003', '3号教工', '17352621003', '17352621003@qq.com');
INSERT INTO `teacher_tbl` VALUES (4, '1004', '1004', '4号教工', '17352621004', '17352621004@qq.com');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
