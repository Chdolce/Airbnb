/*
Navicat MySQL Data Transfer

Source Server         : centerdata
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : airbnb

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-12-14 17:52:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
`accound_id`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`accound_phone`  varchar(11) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL ,
`account_password`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL ,
`account_email`  varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL ,
`account_name`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`account_image`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL ,
PRIMARY KEY (`accound_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci

;

-- ----------------------------
-- Records of account
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house` (
`house_id`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`house_type`  int(4) NULL DEFAULT NULL COMMENT '公寓 普通民宅 附属单元 特色房源 住宿加早餐 精品酒店' ,
`house_coutry`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`house_province`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`house_city`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`house_address`  varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`house_longtitude`  int(11) NOT NULL ,
`house_lantitude`  int(11) NOT NULL ,
`house_name`  varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`house_fee`  int(100) NOT NULL ,
`house_capacity`  int(10) NOT NULL ,
PRIMARY KEY (`house_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci

;

-- ----------------------------
-- Records of house
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for house_host
-- ----------------------------
DROP TABLE IF EXISTS `house_host`;
CREATE TABLE `house_host` (
`accound_id`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`house_id`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
PRIMARY KEY (`accound_id`, `house_id`),
FOREIGN KEY (`accound_id`) REFERENCES `account` (`accound_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`house_id`) REFERENCES `house` (`house_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `house_houseif` (`house_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci

;

-- ----------------------------
-- Records of house_host
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
`order_id`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`guest_id`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`house_id`  varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`start_time`  datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP ,
`finish_time`  datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP ,
`order_state`  int(10) NOT NULL DEFAULT 2 COMMENT '有效订单 未成功订单 待付款' ,
PRIMARY KEY (`order_id`),
FOREIGN KEY (`guest_id`) REFERENCES `account` (`accound_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`house_id`) REFERENCES `house` (`house_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `guest_accountid` (`guest_id`) USING BTREE ,
INDEX `house_houseid` (`house_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci

;

-- ----------------------------
-- Records of order
-- ----------------------------
BEGIN;
COMMIT;
