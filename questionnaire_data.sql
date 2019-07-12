/*
Navicat MySQL Data Transfer

Source Server         : 自己服务器
Source Server Version : 50642
Source Host           : 120.78.136.100:3306
Source Database       : questionnaire_data

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2019-07-10 13:19:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_questionnaire
-- ----------------------------
DROP TABLE IF EXISTS `t_questionnaire`;
CREATE TABLE `t_questionnaire` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `user_id` varchar(64) NOT NULL COMMENT '问卷创建者ID',
  `num` varchar(64) DEFAULT NULL COMMENT '问卷被答数',
  `tips` varchar(64) NOT NULL COMMENT '问卷调查背景',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `title` varchar(64) DEFAULT NULL COMMENT '问卷标题',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问卷表';

-- ----------------------------
-- Table structure for t_questionnaire_answer
-- ----------------------------
DROP TABLE IF EXISTS `t_questionnaire_answer`;
CREATE TABLE `t_questionnaire_answer` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `questionnaire_id` varchar(64) DEFAULT NULL COMMENT '问卷ID',
  `questio_id` varchar(64) NOT NULL COMMENT '题目ID',
  `answer` varchar(64) DEFAULT NULL COMMENT '答案内容',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `type` varchar(64) DEFAULT NULL COMMENT '题目类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问卷-回答';

-- ----------------------------
-- Table structure for t_questionnaire_choose
-- ----------------------------
DROP TABLE IF EXISTS `t_questionnaire_choose`;
CREATE TABLE `t_questionnaire_choose` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `questionnaire_id` varchar(64) NOT NULL COMMENT '问卷ID',
  `question_id` varchar(64) NOT NULL COMMENT '题目ID',
  `answer` varchar(640) DEFAULT NULL COMMENT '问题选项内容',
  `placeholder` varchar(640) DEFAULT NULL COMMENT '提示文字',
  `choose` varchar(10) DEFAULT NULL COMMENT '选项（A，b,c）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问卷-选项';

-- ----------------------------
-- Table structure for t_questionnaire_ip
-- ----------------------------
DROP TABLE IF EXISTS `t_questionnaire_ip`;
CREATE TABLE `t_questionnaire_ip` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `questionnaire_id` varchar(64) NOT NULL COMMENT '问卷ID',
  `ip_address` varchar(64) NOT NULL COMMENT 'IP地址（IP-问卷一对一）',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问卷-IP';

-- ----------------------------
-- Table structure for t_questionnaire_question
-- ----------------------------
DROP TABLE IF EXISTS `t_questionnaire_question`;
CREATE TABLE `t_questionnaire_question` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `questionnaire_id` varchar(64) NOT NULL COMMENT '问卷ID',
  `type` varchar(64) NOT NULL COMMENT '题目类型',
  `tips` varchar(64) DEFAULT NULL COMMENT '题目内容',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `placeholder` varchar(640) DEFAULT NULL COMMENT '提示文字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问卷-问题';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `status` char(1) DEFAULT '0' COMMENT '正常(0),停用(1),',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `username` bigint(17) NOT NULL AUTO_INCREMENT COMMENT '账号',
  PRIMARY KEY (`username`,`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
