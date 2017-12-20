-- ----------------------------
-- Table structure for fb_feed
-- ----------------------------
DROP TABLE IF EXISTS `fb_feed`;
CREATE TABLE `fb_feed` (
  `id` varchar(100) NOT NULL,
  `created_time` varchar(100) DEFAULT NULL,
  `message` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fb_feed_picture
-- ----------------------------
DROP TABLE IF EXISTS `fb_feed_picture`;
CREATE TABLE `fb_feed_picture` (
  `id` varchar(255) NOT NULL,
  `full_picture` varchar(500) DEFAULT NULL,
  `pic_real_path` varchar(500) DEFAULT NULL,
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
  `thumbnail_src` varchar(500) DEFAULT NULL,
  `pic_real_path` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
