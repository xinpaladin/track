/*
Navicat MySQL Data Transfer

Source Server         : locus
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : locus

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2016-05-29 22:16:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for data_detail
-- ----------------------------
DROP TABLE IF EXISTS `data_detail`;
CREATE TABLE `data_detail` (
  `id` bigint(20) NOT NULL COMMENT '主键id自增长',
  `longitude` double NOT NULL COMMENT '经度',
  `latitude` double NOT NULL COMMENT '纬度',
  `height` int(11) NOT NULL COMMENT '高度',
  `vel_e` double NOT NULL COMMENT '东向速度',
  `vel_n` double NOT NULL COMMENT '北向速度',
  `vel_u` double NOT NULL COMMENT '天向速度',
  `roll` double NOT NULL COMMENT '横滚角',
  `pitch` double NOT NULL COMMENT '俯仰角',
  `course` double NOT NULL COMMENT '惯导航向角',
  `log_acce` double NOT NULL COMMENT ' 纵向加速度',
  `lateral_acce` double NOT NULL COMMENT '横向加速度',
  `nor_acce` double NOT NULL COMMENT '法向加速度',
  `time_year` int(11) NOT NULL COMMENT '北京时间年',
  `time_month` int(11) NOT NULL COMMENT '北京时间月',
  `time_day` int(11) NOT NULL COMMENT '北京时间日',
  `time_hour` int(11) NOT NULL COMMENT '北京时间时',
  `time_minute` int(11) NOT NULL COMMENT '北京时间分',
  `time_second` double NOT NULL COMMENT '北京时间秒',
  `data_item_id` bigint(20) DEFAULT NULL COMMENT '数据条目表id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='一个具体动作的时间参数序列';

-- ----------------------------
-- Table structure for data_detail_baidu
-- ----------------------------
DROP TABLE IF EXISTS `data_detail_baidu`;
CREATE TABLE `data_detail_baidu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id自增长',
  `longitude` double NOT NULL COMMENT '经度',
  `latitude` double NOT NULL COMMENT '纬度',
  `data_item_id` bigint(20) NOT NULL COMMENT '数据条目表id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='一个具体动作的经纬度序列转换的百度数据';

-- ----------------------------
-- Table structure for data_item
-- ----------------------------
DROP TABLE IF EXISTS `data_item`;
CREATE TABLE `data_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 自增长',
  `driver_name` varchar(20) DEFAULT NULL COMMENT '驾驶员名字',
  `file_name` varchar(80) DEFAULT NULL COMMENT '文件名',
  `type` int(11) DEFAULT NULL COMMENT '数据的动作类型',
  `is_available` bit(1) DEFAULT NULL COMMENT '是否可用',
  `is_tran_baidu` bit(1) DEFAULT NULL,
  `comfortable` varchar(1) DEFAULT NULL COMMENT '舒适方面',
  `speed` varchar(1) DEFAULT NULL COMMENT '速度方面',
  `locus` varchar(1) DEFAULT NULL COMMENT '轨迹方面',
  `overall_eval` varchar(1) DEFAULT NULL COMMENT '总体评价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for data_origin
-- ----------------------------
DROP TABLE IF EXISTS `data_origin`;
CREATE TABLE `data_origin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id自增长',
  `syn_word1` varchar(10) NOT NULL COMMENT '同步字1',
  `syn_word2` varchar(10) NOT NULL COMMENT '同步字2',
  `frame_num` varchar(10) NOT NULL COMMENT '帧序号',
  `state` varchar(10) NOT NULL COMMENT '状态字',
  `def_word1` varchar(10) NOT NULL COMMENT '故障字1',
  `def_word2` varchar(10) NOT NULL COMMENT '故障字1',
  `longitude` varchar(40) NOT NULL COMMENT '经度',
  `latitude` varchar(40) NOT NULL COMMENT '纬度',
  `height` varchar(20) NOT NULL COMMENT '高度',
  `vel_e` varchar(20) NOT NULL COMMENT '东向速度',
  `vel_n` varchar(20) NOT NULL COMMENT '北向速度',
  `vel_u` varchar(20) NOT NULL COMMENT '天向速度',
  `roll` varchar(20) NOT NULL COMMENT '横滚角',
  `pitch` varchar(20) NOT NULL COMMENT '俯仰角',
  `course` varchar(20) NOT NULL COMMENT '惯导航向角',
  `ali_time` varchar(20) NOT NULL COMMENT '对准时间',
  `ali_state_word` varchar(10) NOT NULL COMMENT '对准状态字',
  `log_acce` varchar(20) NOT NULL COMMENT ' 纵向加速度',
  `lateral_acce` varchar(20) NOT NULL COMMENT '横向加速度',
  `nor_acce` varchar(20) NOT NULL COMMENT '法向加速度',
  `time_year` varchar(10) NOT NULL COMMENT '北京时间年',
  `time_month` varchar(10) NOT NULL COMMENT '北京时间月',
  `time_day` varchar(10) NOT NULL COMMENT '北京时间日',
  `time_hour` varchar(10) NOT NULL COMMENT '北京时间时',
  `time_minute` varchar(10) NOT NULL COMMENT '北京时间分',
  `time_second` varchar(20) NOT NULL COMMENT '北京时间秒',
  `effi_word` varchar(10) NOT NULL COMMENT '有效字',
  `mag_vir` varchar(20) NOT NULL COMMENT '磁差',
  `check_sum` varchar(20) NOT NULL COMMENT '校验和',
  `data_item_id` bigint(20) DEFAULT NULL COMMENT '数据条目表id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41419 DEFAULT CHARSET=utf8 COMMENT='一个具体动作的时间参数序列';
