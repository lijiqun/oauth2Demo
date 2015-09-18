/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50535
Source Host           : localhost:3306
Source Database       : favoauth2

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2015-09-18 17:35:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for fav_client
-- ----------------------------
DROP TABLE IF EXISTS `fav_client`;
CREATE TABLE `fav_client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_name` varchar(100) DEFAULT NULL,
  `client_id` varchar(100) DEFAULT NULL,
  `client_secret` varchar(100) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_oauth2_client_client_id` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fav_client
-- ----------------------------
INSERT INTO `fav_client` VALUES ('1', 'test', '8f56573c-18ed-401e-a5cc-117efcb7b9de', 'bd8cb4ca-3eb2-4312-a28a-3f1074ba1dda', '0');

-- ----------------------------
-- Table structure for fav_user
-- ----------------------------
DROP TABLE IF EXISTS `fav_user`;
CREATE TABLE `fav_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_oauth2_user_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fav_user
-- ----------------------------

-- ----------------------------
-- Table structure for fav_web_app
-- ----------------------------
DROP TABLE IF EXISTS `fav_web_app`;
CREATE TABLE `fav_web_app` (
  `webKey` varchar(100) DEFAULT NULL,
  `webName` varchar(100) DEFAULT NULL,
  `webProfile` varchar(100) DEFAULT NULL,
  `webCategory` varchar(100) DEFAULT NULL,
  `webUrl` varchar(100) DEFAULT NULL,
  `webState` varchar(100) DEFAULT NULL,
  `webId` bigint(100) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`webId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fav_web_app
-- ----------------------------
INSERT INTO `fav_web_app` VALUES ('204751da-9e41-482c-97bd-fb851ceddf03', '啊啊啊啊', 'aaaa', '1', 'http://localhost:8080/test', '1', '1');
INSERT INTO `fav_web_app` VALUES ('204751da-9e41-482c-97bd-fb851ceddf03', '啊啊啊啊', 'aaaa', '1', 'http://localhost:8080/test', null, '2');
INSERT INTO `fav_web_app` VALUES ('204751da-9e41-482c-97bd-fb851ceddf03', '啊啊啊啊', 'aaaa', '1', 'http://localhost:8080/test', null, '3');
INSERT INTO `fav_web_app` VALUES ('da16a812-244e-497c-9b3a-c9313f4a4964', 'aaaaa', '', '1', 'http://localhost:8080/test', null, '4');

-- ----------------------------
-- Table structure for oauth2_client
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_client`;
CREATE TABLE `oauth2_client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_name` varchar(100) DEFAULT NULL,
  `client_id` varchar(100) DEFAULT NULL,
  `client_secret` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_oauth2_client_client_id` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth2_client
-- ----------------------------
INSERT INTO `oauth2_client` VALUES ('1', 'chapter17-client', 'c1ebe466-1cdc-4bd3-ab69-77c3561b9dee', 'd8346ea2-6017-43ed-ad68-19c0f971738b');
INSERT INTO `oauth2_client` VALUES ('3', 'bbbb', 'ff5832f3-6dc2-480b-a28a-d79376d5f05a', '82fce159-ed17-4bec-8cd9-4e1168a91811');
INSERT INTO `oauth2_client` VALUES ('4', 'nnnnnn', 'dd3f341b-f3eb-4473-9c17-8f9696f3449d', 'ac89d1f0-1127-4af2-8140-0d2ff8c0ba1b');
INSERT INTO `oauth2_client` VALUES ('5', 'xxxxxx', '8f56573c-18ed-401e-a5cc-117efcb7b9de', 'bd8cb4ca-3eb2-4312-a28a-3f1074ba1dda');

-- ----------------------------
-- Table structure for oauth2_user
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_user`;
CREATE TABLE `oauth2_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_oauth2_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth2_user
-- ----------------------------
INSERT INTO `oauth2_user` VALUES ('1', 'admin', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f');
INSERT INTO `oauth2_user` VALUES ('2', 'test', '005715db1dbf78a3c605fbce26859aec', '98e24093a09c342558586ba37243d969');
