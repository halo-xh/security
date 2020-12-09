/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : securitydemo

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 09/12/2020 21:00:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources`  (
  `rid` int(4) NOT NULL,
  `resname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `restype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'A',
  `createdt` datetime(0) NOT NULL,
  `updatedt` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `version` int(4) NOT NULL,
  `deleted` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N',
  PRIMARY KEY (`rid`) USING BTREE,
  UNIQUE INDEX `res_uk_resname`(`resname`) USING BTREE,
  UNIQUE INDEX `res_uk_name_type`(`resname`, `restype`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for subject_login
-- ----------------------------
DROP TABLE IF EXISTS `subject_login`;
CREATE TABLE `subject_login`  (
  `sid` int(4) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'A',
  `createdt` datetime(0) NOT NULL,
  `updatedt` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `deleted` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N',
  `version` int(4) NOT NULL,
  PRIMARY KEY (`sid`) USING BTREE,
  UNIQUE INDEX `uk_username`(`loginname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for login_token
-- ----------------------------
DROP TABLE IF EXISTS `login_token`;
CREATE TABLE `login_token`  (
  `tid` int(4) NOT NULL,
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'login name',
  `createdt` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
