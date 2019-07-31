/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 50639
 Source Host           : localhost:3306
 Source Schema         : coupons-keeper

 Target Server Type    : MySQL
 Target Server Version : 50639
 File Encoding         : 65001

 Date: 31/07/2019 16:02:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coupons
-- ----------------------------
DROP TABLE IF EXISTS `coupons`;
CREATE TABLE `coupons` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `merchant_id` int(10) NOT NULL COMMENT '商户 ID',
  `title` varchar(64) NOT NULL COMMENT '优惠券标题',
  `summary` varchar(255) DEFAULT NULL COMMENT '优惠券摘要',
  `desc` varchar(255) DEFAULT NULL COMMENT '详细描述',
  `limit` int(12) DEFAULT NULL COMMENT '领取上限',
  `has_token` tinyint(1) DEFAULT NULL COMMENT '是否有 token',
  `background` tinyint(1) DEFAULT NULL COMMENT '优惠券背景色',
  `start` datetime DEFAULT NULL COMMENT '有效起始时间',
  `end` datetime DEFAULT NULL COMMENT '有效截止时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for merchants
-- ----------------------------
DROP TABLE IF EXISTS `merchants`;
CREATE TABLE `merchants` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商户名称',
  `logo_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商户 logo',
  `business_license_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商户营业执照',
  `phone` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商户联系电话',
  `address` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商户地址',
  `is_audit` tinyint(1) NOT NULL COMMENT '是否通过审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_coupons
-- ----------------------------
DROP TABLE IF EXISTS `user_coupons`;
CREATE TABLE `user_coupons` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL COMMENT '用户 ID',
  `coupon_id` int(10) DEFAULT NULL COMMENT '优惠券 ID',
  `token` varchar(36) DEFAULT NULL COMMENT 'Token 值',
  `assign_date` datetime DEFAULT NULL COMMENT '领取日期',
  `consume_date` datetime DEFAULT NULL COMMENT '消费日期',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`) USING BTREE COMMENT '用户 ID 索引'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL COMMENT '用户名',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `sex` varchar(3) DEFAULT NULL COMMENT '性别',
  `phone` varchar(12) DEFAULT NULL COMMENT '电话',
  `address` varchar(64) DEFAULT NULL COMMENT '住址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
