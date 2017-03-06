SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrcode_t
-- ----------------------------
DROP TABLE IF EXISTS `qrcode_t`;
CREATE TABLE `qrcode_t` (
  `key_id` varchar(200) NOT NULL,
  `ios_url` varchar(255) NOT NULL,
  `android_url` varchar(255) NOT NULL,
  PRIMARY KEY  (`key_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
