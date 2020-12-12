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
CREATE TABLE `resources` (
  `rid` int(4) NOT NULL,
  `resname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `restype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
   `path` varchar(255) DEFAULT NULL,
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'A',
  `createdt` datetime NOT NULL,
  `updatedt` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `version` int(4) NOT NULL,
  `deleted` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N',
  PRIMARY KEY (`rid`) USING BTREE,
  UNIQUE KEY `res_uk_resname` (`resname`) USING BTREE,
  UNIQUE KEY `res_uk_name_type` (`resname`,`restype`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
-- ----------------------------
-- Table structure for subject_login
-- ----------------------------
-- DROP TABLE IF EXISTS `subject_login`;
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
-- DROP TABLE IF EXISTS `login_token`;
CREATE TABLE `login_token`  (
  `tid` int(4) NOT NULL,
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'login name',
  `createdt` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for res2res
-- ----------------------------
CREATE TABLE `securitydemo`.`res2res` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'table id',
  `resid` INT NOT NULL COMMENT 'resource id: function, resource',
  `parentid` INT NOT NULL COMMENT 'resource id : function , role',
  `createdt` DATETIME NOT NULL,
  `updatedt` DATETIME NOT NULL,
  `version` INT NOT NULL,
  PRIMARY KEY (`id`))
COMMENT = 'mapping table: role -function, function - resource';

-- ----------------------------
-- Table structure for user2role
-- ----------------------------
CREATE TABLE `securitydemo`.`user2role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userid` INT NOT NULL,
  `roleid` INT NOT NULL,
  `createdt` DATETIME NOT NULL,
  `updatedt` DATETIME NOT NULL,
  `version` INT NOT NULL,
  PRIMARY KEY (`id`))
COMMENT = 'user mapping role.';



SET FOREIGN_KEY_CHECKS = 1;
