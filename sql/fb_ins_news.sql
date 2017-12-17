/*
Navicat MySQL Data Transfer

Source Server         : LOCALHOST
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : fb_ins_news

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2017-10-19 23:34:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for fb_feed
-- ----------------------------
DROP TABLE IF EXISTS `fb_feed`;
CREATE TABLE `fb_feed` (
  `id` varchar(100) NOT NULL,
  `created_time` varchar(100) DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fb_feed_picture
-- ----------------------------
DROP TABLE IF EXISTS `fb_feed_picture`;
CREATE TABLE `fb_feed_picture` (
  `id` varchar(255) NOT NULL,
  `full_picture` varchar(255) DEFAULT NULL,
  `pic_real_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for instagram_feed
-- ----------------------------
DROP TABLE IF EXISTS `instagram_feed`;
CREATE TABLE `instagram_feed` (
  `date` varchar(100) NOT NULL,
  `caption` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for instagram_feed_picture
-- ----------------------------
DROP TABLE IF EXISTS `instagram_feed_picture`;
CREATE TABLE `instagram_feed_picture` (
  `date` varchar(100) NOT NULL,
  `thumbnail_src` varchar(255) DEFAULT NULL,
  `pic_real_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
