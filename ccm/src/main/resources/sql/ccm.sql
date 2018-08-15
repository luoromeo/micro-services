/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : ccm

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 08/05/2018 15:59:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for credit_account
-- ----------------------------
DROP TABLE IF EXISTS `credit_account`;
CREATE TABLE `credit_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(125) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '银行',
  `account` varchar(125) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '账户',
  `credit_limit` double(9,2) DEFAULT '0.00' COMMENT '信用额度',
  `available_credit` double(9,2) DEFAULT '0.00' COMMENT '可用信用额度',
  `due_date` int(2) NOT NULL DEFAULT '0' COMMENT '还款日',
  `version` bigint(20) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for credit_card_bill
-- ----------------------------
DROP TABLE IF EXISTS `credit_card_bill`;
CREATE TABLE `credit_card_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `credit_card_id` bigint(20) NOT NULL COMMENT '银行卡id',
  `statement_cycle_start` timestamp NULL DEFAULT NULL COMMENT '账单周期开始',
  `statement_cycle_end` timestamp NULL DEFAULT NULL COMMENT '账单周期将结束',
  `new_balance` double(9,2) DEFAULT NULL COMMENT '本期还款总额',
  `min_payment` double(9,2) DEFAULT NULL COMMENT '本期最低还款额',
  `payment_due_date` timestamp NULL DEFAULT NULL COMMENT '到期还款日',
  `bill_month` timestamp NULL DEFAULT NULL COMMENT '账单月份',
  `version` bigint(20) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`,`gmt_create`) USING BTREE,
  KEY `FK_ID` (`credit_card_id`),
  CONSTRAINT `FK_ID` FOREIGN KEY (`credit_card_id`) REFERENCES `credit_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for payment_record
-- ----------------------------
DROP TABLE IF EXISTS `payment_record`;
CREATE TABLE `payment_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `credit_card_bill_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '账单id',
  `payment_money` double(9,2) DEFAULT '0.00' COMMENT '还款金额',
  `payment_date` timestamp NULL DEFAULT NULL COMMENT '还款日',
  `type` int(1) NOT NULL DEFAULT '0' COMMENT '还款方式',
  `interest` double(9,2) DEFAULT '0.00' COMMENT '利息',
  `version` bigint(20) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for borrow_record
-- ----------------------------
DROP TABLE IF EXISTS `borrow_record`;
CREATE TABLE `borrow_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `credit_account_id` bigint(20) NOT NULL COMMENT '信用账户id',
  `borrow_channels_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '借款渠道id',
  `money` double(9,2) DEFAULT '0.00' COMMENT '金额',
  `borrowing_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '借款日期',
  `payment_date` timestamp NULL DEFAULT NULL COMMENT '还款日期',
  `borrowing_day` int(3) NOT NULL DEFAULT '1' COMMENT '借款天数',
  `interest` double(9,5) DEFAULT '0.00000' COMMENT '利息',
  `fee` double(9,2) DEFAULT '0.00' COMMENT '手续费',
  `version` bigint(20) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for borrow_channels
-- ----------------------------
DROP TABLE IF EXISTS `borrow_channels`;
CREATE TABLE `borrow_channels` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) not null default '' comment '渠道名称',
  `interest_accrual_type` int(1) NOT NULL DEFAULT '0' COMMENT '计息方式0按天,1按次',
  `interest_rate` double(9,2) DEFAULT '0.00' COMMENT '利率',
  `fee` double(9,2) DEFAULT '0.00' COMMENT '手续费',
  `status` int(1) not null default 0 comment '状态1启用,0未启用,2弃用',
  `version` bigint(20) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;
SET FOREIGN_KEY_CHECKS = 1;


-- -------------------------------------------------
CREATE TABLE `dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dict_code` varchar(20) NOT NULL DEFAULT '' COMMENT '字典code',
  `dict_value` varchar(50) NOT NULL DEFAULT '' comment '字典value',
  `version` bigint(20) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB default charset = utf8 collate = utf8_bin ROW_FORMAT=COMPACT;

CREATE TABLE `dict_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dict_value` varchar(50) NOT NULL DEFAULT '' comment '字典value',
  `dictdata_name` varchar(50) NOT NULL DEFAULT '' comment '字典数据表名称',
  `dictdata_value` varchar(50) NOT NULL DEFAULT '' comment '字典数据表值',
  `version` bigint(20) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB default charset = utf8 collate = utf8_bin ROW_FORMAT=COMPACT;

DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dict_code` varchar(20) NOT NULL DEFAULT '' COMMENT '字典code',
  `dict_value` varchar(50) NOT NULL DEFAULT '' comment '字典value',
  `up_key` bigint(20) NOT NULL DEFAULT 0 comment  '上级Key 0 为顶级',
  `version` bigint(20) DEFAULT NULL,
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB default charset = utf8 collate = utf8_bin ROW_FORMAT=COMPACT;