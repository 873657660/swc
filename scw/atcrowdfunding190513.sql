/*
Navicat MySQL Data Transfer

Source Server         : 39.106.91.206-3306裴世康
Source Server Version : 50725
Source Host           : 39.106.91.206:3306
Source Database       : atcrowdfunding190513

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-09-05 16:54:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_account_type_cert
-- ----------------------------
DROP TABLE IF EXISTS `t_account_type_cert`;
CREATE TABLE `t_account_type_cert` (
  `id` int(11) NOT NULL,
  `accttype` char(1) DEFAULT NULL COMMENT '�˻�����',
  `certid` int(11) DEFAULT NULL COMMENT '��������',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_19` (`certid`) USING BTREE,
  CONSTRAINT `t_account_type_cert_ibfk_1` FOREIGN KEY (`certid`) REFERENCES `t_cert` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�˻����ʹ�ϵ��';

-- ----------------------------
-- Records of t_account_type_cert
-- ----------------------------
INSERT INTO `t_account_type_cert` VALUES ('4', '0', '1');
INSERT INTO `t_account_type_cert` VALUES ('5', '0', '2');
INSERT INTO `t_account_type_cert` VALUES ('6', '0', '3');
INSERT INTO `t_account_type_cert` VALUES ('7', '0', '5');
INSERT INTO `t_account_type_cert` VALUES ('8', '1', '1');
INSERT INTO `t_account_type_cert` VALUES ('9', '1', '6');
INSERT INTO `t_account_type_cert` VALUES ('10', '2', '6');
INSERT INTO `t_account_type_cert` VALUES ('11', '2', '7');
INSERT INTO `t_account_type_cert` VALUES ('12', '3', '5');
INSERT INTO `t_account_type_cert` VALUES ('13', '3', '4');

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginacct` varchar(64) NOT NULL,
  `userpswd` char(64) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `createtime` char(19) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginacct` (`loginacct`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='����Ա��';

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'superadmin', '$2a$10$O6cP9KARKopY63MEOqdy2uf0bbBHk5KkV6b12tVEWHZi3ujgRoyqi', '超级管理员', 'admin@atguigu.com', '2019-01-12 17:18:00');
INSERT INTO `t_admin` VALUES ('2', 'zhangsan', '$2a$10$O6cP9KARKopY63MEOqdy2uf0bbBHk5KkV6b12tVEWHZi3ujgRoyqi', 'zhangsan', 'test@atguigu.com', '2019-01-12 17:18:00');
INSERT INTO `t_admin` VALUES ('3', 'lisi', '$2a$10$O6cP9KARKopY63MEOqdy2uf0bbBHk5KkV6b12tVEWHZi3ujgRoyqi', 'lisi', 'lisi@atguigu.com', '2019-01-12 17:18:00');
INSERT INTO `t_admin` VALUES ('4', 'wangwu', '$2a$10$O6cP9KARKopY63MEOqdy2uf0bbBHk5KkV6b12tVEWHZi3ujgRoyqi', 'wangwu', 'wangwu@atguigu.com', '2019-01-12 17:18:00');
INSERT INTO `t_admin` VALUES ('14', 'zhangyu', '$2a$10$O6cP9KARKopY63MEOqdy2uf0bbBHk5KkV6b12tVEWHZi3ujgRoyqi', '张宇锋', 'jlsp-zy@163.com', '2019-08-24 09:13:19');

-- ----------------------------
-- Table structure for t_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role`;
CREATE TABLE `t_admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`adminid`) USING BTREE,
  KEY `FK_Reference_2` (`roleid`) USING BTREE,
  CONSTRAINT `t_admin_role_ibfk_1` FOREIGN KEY (`adminid`) REFERENCES `t_admin` (`id`),
  CONSTRAINT `t_admin_role_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='����Ա��ɫ��';

-- ----------------------------
-- Records of t_admin_role
-- ----------------------------
INSERT INTO `t_admin_role` VALUES ('8', '14', '2');
INSERT INTO `t_admin_role` VALUES ('10', '14', '4');
INSERT INTO `t_admin_role` VALUES ('11', '2', '3');
INSERT INTO `t_admin_role` VALUES ('13', '2', '2');
INSERT INTO `t_admin_role` VALUES ('14', '3', '2');

-- ----------------------------
-- Table structure for t_advertisement
-- ----------------------------
DROP TABLE IF EXISTS `t_advertisement`;
CREATE TABLE `t_advertisement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `iconpath` varchar(255) DEFAULT NULL,
  `status` char(1) DEFAULT NULL COMMENT '0 - �ݸ壬 1 - δ��ˣ� 2 - �����ɣ� 3 - ����',
  `url` varchar(255) DEFAULT NULL,
  `adminid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='����';

-- ----------------------------
-- Records of t_advertisement
-- ----------------------------

-- ----------------------------
-- Table structure for t_cert
-- ----------------------------
DROP TABLE IF EXISTS `t_cert`;
CREATE TABLE `t_cert` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '��������',
  `name` varchar(255) DEFAULT NULL COMMENT '��������',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='���ʱ�';

-- ----------------------------
-- Records of t_cert
-- ----------------------------
INSERT INTO `t_cert` VALUES ('1', '营业执照副本');
INSERT INTO `t_cert` VALUES ('2', '税务登记证');
INSERT INTO `t_cert` VALUES ('3', '组织机构代码证');
INSERT INTO `t_cert` VALUES ('4', '单位登记证件');
INSERT INTO `t_cert` VALUES ('5', '法定代表人证件');
INSERT INTO `t_cert` VALUES ('6', '经营者证件');
INSERT INTO `t_cert` VALUES ('7', '手执身份证照片');

-- ----------------------------
-- Table structure for t_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_dictionary`;
CREATE TABLE `t_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `subcode` varchar(255) DEFAULT NULL,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='�����ֵ�';

-- ----------------------------
-- Records of t_dictionary
-- ----------------------------
INSERT INTO `t_dictionary` VALUES ('1', '性别', 'sex', '男', '0');
INSERT INTO `t_dictionary` VALUES ('2', '性别', 'sex', '女', '1');
INSERT INTO `t_dictionary` VALUES ('3', '实名认证状态', 'authstatus', '未实名认证', '0');
INSERT INTO `t_dictionary` VALUES ('4', '实名认证状态', 'authstatus', '实名认证审核中', '1');
INSERT INTO `t_dictionary` VALUES ('5', '实名认证状态', 'authstatus', '已实名认证', '2');
INSERT INTO `t_dictionary` VALUES ('6', '账户类型', 'accttype', '企业', '0');
INSERT INTO `t_dictionary` VALUES ('7', '账户类型', 'accttype', '个体', '1');
INSERT INTO `t_dictionary` VALUES ('8', '账户类型', 'accttype', '个人', '2');
INSERT INTO `t_dictionary` VALUES ('9', '账户类型', 'accttype', '政府', '3');

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginacct` varchar(255) NOT NULL COMMENT '��¼�˺�',
  `userpswd` char(64) NOT NULL COMMENT '��¼����',
  `username` varchar(255) DEFAULT NULL COMMENT '�û�����',
  `email` varchar(255) NOT NULL COMMENT '��������',
  `authstatus` char(1) NOT NULL COMMENT 'ʵ����֤״̬ 0 - δʵ����֤�� 1 - ʵ����֤�����У� 2 - ��ʵ����֤',
  `usertype` char(1) NOT NULL COMMENT ' �û�����: 0 - ���ˣ� 1 - ��ҵ',
  `realname` varchar(255) DEFAULT NULL COMMENT '��ʵ����',
  `cardnum` varchar(255) DEFAULT NULL COMMENT '���֤����',
  `accttype` char(1) DEFAULT NULL COMMENT '�˻�����: 0 - ��ҵ�� 1 - ���壬 2 - ���ˣ� 3 - ����',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='��Ա��';

-- ----------------------------
-- Records of t_member
-- ----------------------------
INSERT INTO `t_member` VALUES ('1', '12345678900', '$2a$10$O6cP9KARKopY63MEOqdy2uf0bbBHk5KkV6b12tVEWHZi3ujgRoyqi', '', 'test@163.com', '0', '1', null, null, null);

-- ----------------------------
-- Table structure for t_member_address
-- ----------------------------
DROP TABLE IF EXISTS `t_member_address`;
CREATE TABLE `t_member_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `memberid` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_13` (`memberid`) USING BTREE,
  CONSTRAINT `t_member_address_ibfk_1` FOREIGN KEY (`memberid`) REFERENCES `t_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�ջ���ַ��';

-- ----------------------------
-- Records of t_member_address
-- ----------------------------

-- ----------------------------
-- Table structure for t_member_cert
-- ----------------------------
DROP TABLE IF EXISTS `t_member_cert`;
CREATE TABLE `t_member_cert` (
  `id` int(11) NOT NULL,
  `memberid` int(11) DEFAULT NULL COMMENT '��ԱID',
  `certid` int(11) DEFAULT NULL COMMENT '����ID',
  `iconpath` varchar(255) DEFAULT NULL COMMENT 'ͼƬ·��',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_14` (`memberid`) USING BTREE,
  KEY `FK_Reference_15` (`certid`) USING BTREE,
  CONSTRAINT `t_member_cert_ibfk_1` FOREIGN KEY (`memberid`) REFERENCES `t_member` (`id`),
  CONSTRAINT `t_member_cert_ibfk_2` FOREIGN KEY (`certid`) REFERENCES `t_cert` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��Ա���ʱ�';

-- ----------------------------
-- Records of t_member_cert
-- ----------------------------

-- ----------------------------
-- Table structure for t_member_project_follow
-- ----------------------------
DROP TABLE IF EXISTS `t_member_project_follow`;
CREATE TABLE `t_member_project_follow` (
  `id` int(11) NOT NULL,
  `projectid` int(11) DEFAULT NULL,
  `memberid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_11` (`projectid`) USING BTREE,
  KEY `FK_Reference_12` (`memberid`) USING BTREE,
  CONSTRAINT `t_member_project_follow_ibfk_1` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`),
  CONSTRAINT `t_member_project_follow_ibfk_2` FOREIGN KEY (`memberid`) REFERENCES `t_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��Ŀ��ע��';

-- ----------------------------
-- Records of t_member_project_follow
-- ----------------------------

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='�˵���';

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '0', '控制面板', 'glyphicon glyphicon-dashboard', 'main.html');
INSERT INTO `t_menu` VALUES ('2', '0', '权限管理', 'glyphicon glyphicon glyphicon-tasks', null);
INSERT INTO `t_menu` VALUES ('3', '2', '用户维护', 'glyphicon glyphicon-user', 'admin/index');
INSERT INTO `t_menu` VALUES ('4', '2', '角色维护', 'glyphicon glyphicon-king', 'role/index');
INSERT INTO `t_menu` VALUES ('5', '2', '权限维护', 'glyphicon glyphicon-lock', 'permission/index');
INSERT INTO `t_menu` VALUES ('6', '2', '菜单维护', 'glyphicon glyphicon-th-list', 'menu/index');
INSERT INTO `t_menu` VALUES ('7', '0', '业务审核', 'glyphicon glyphicon-ok', null);
INSERT INTO `t_menu` VALUES ('8', '7', '实名认证审核', 'glyphicon glyphicon-check', 'auth_cert/index.html');
INSERT INTO `t_menu` VALUES ('9', '7', '广告审核', 'glyphicon glyphicon-check', 'auth_adv/index.html');
INSERT INTO `t_menu` VALUES ('10', '7', '项目审核', 'glyphicon glyphicon-check', 'auth_project/index.html');
INSERT INTO `t_menu` VALUES ('11', '0', '业务管理', 'glyphicon glyphicon-th-large', null);
INSERT INTO `t_menu` VALUES ('12', '11', '资质维护', 'glyphicon glyphicon-picture', 'cert/index.html');
INSERT INTO `t_menu` VALUES ('13', '11', '分类管理', 'glyphicon glyphicon-equalizer', 'certtype/index.html');
INSERT INTO `t_menu` VALUES ('14', '11', '流程管理', 'glyphicon glyphicon-random', 'process/index.html');
INSERT INTO `t_menu` VALUES ('15', '11', '广告管理', 'glyphicon glyphicon-hdd', 'advert/index.html');
INSERT INTO `t_menu` VALUES ('16', '11', '消息模板', 'glyphicon glyphicon-comment', 'message/index.html');
INSERT INTO `t_menu` VALUES ('17', '11', '项目分类', 'glyphicon glyphicon-list', 'projectType/index.html');
INSERT INTO `t_menu` VALUES ('18', '11', '项目标签', 'glyphicon glyphicon-tags', 'tag/index.html');
INSERT INTO `t_menu` VALUES ('19', '0', '参数管理', 'glyphicon glyphicon-list-alt', 'param/index.html');
INSERT INTO `t_menu` VALUES ('22', '0', '课程评比', 'glyphicon glyphicon-cloud', 'test/index');
INSERT INTO `t_menu` VALUES ('23', '22', '众筹项目', 'glyphicon glyphicon-file', 'scw/index');

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `memberid` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `senddate` char(19) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��Ϣ��';

-- ----------------------------
-- Records of t_message
-- ----------------------------

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `memberid` int(11) DEFAULT NULL COMMENT '��ԱID',
  `projectid` int(11) DEFAULT NULL COMMENT '��ĿID',
  `returnid` int(11) DEFAULT NULL COMMENT '�ر�ID',
  `ordernum` varchar(255) DEFAULT NULL COMMENT '�������',
  `createdate` char(19) DEFAULT NULL COMMENT '����ʱ��',
  `money` int(11) DEFAULT NULL COMMENT '֧�ֽ��',
  `rtncount` int(11) DEFAULT NULL COMMENT '�ر�����',
  `status` char(1) DEFAULT NULL COMMENT '0 - ����� 1 - ������ɣ� 9 - ���׹ر�',
  `address` varchar(255) DEFAULT NULL COMMENT '�ջ���ַ',
  `invoice` char(1) DEFAULT NULL COMMENT '0 - ������Ʊ�� 1 - ����Ʊ',
  `invoictitle` varchar(255) DEFAULT NULL COMMENT '��Ʊ̧ͷ',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_16` (`returnid`) USING BTREE,
  KEY `FK_Reference_17` (`projectid`) USING BTREE,
  KEY `FK_Reference_18` (`memberid`) USING BTREE,
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`returnid`) REFERENCES `t_return` (`id`),
  CONSTRAINT `t_order_ibfk_2` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`),
  CONSTRAINT `t_order_ibfk_3` FOREIGN KEY (`memberid`) REFERENCES `t_member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='������';

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', '1', '1', '1', '48194a41047f43a9a2288ab86ec3e712', '2019-09-04 17:13:21', '1025', '5', '0', '北京尚硅谷', '1', '尚硅谷公司', '周四送货');
INSERT INTO `t_order` VALUES ('23', '1', '1', '1', '3eabb31442724fd1a977c69f4da4ae7c', '2019-09-05 11:05:33', '1025', '5', '0', '深圳尚硅谷', '1', '尚硅谷公司', 'aaa');
INSERT INTO `t_order` VALUES ('24', '1', '1', '1', 'e6683580444143fc8599a66b3a728d8d', '2019-09-05 11:13:22', '1025', '5', '0', '深圳尚硅谷', '1', '尚硅谷公司', 'aaa');
INSERT INTO `t_order` VALUES ('25', '1', '1', '1', '5f9ee403424d49888a2f5ff381e65992', '2019-09-05 11:15:57', '1025', '5', '0', '深圳尚硅谷', '1', '尚硅谷公司', 'aaa');
INSERT INTO `t_order` VALUES ('26', '1', '1', '1', '7cb775bb663a4a6583c56a30847e9b05', '2019-09-05 11:19:26', '1025', '5', '0', '深圳尚硅谷', '1', '尚硅谷公司', 'aaa');
INSERT INTO `t_order` VALUES ('27', '1', '1', '1', 'e530cbfd83444e09a54a1930fb1e34c9', '2019-09-05 11:19:35', '1025', '5', '0', '深圳尚硅谷', '1', '尚硅谷公司', 'aaa');
INSERT INTO `t_order` VALUES ('28', '1', '1', '1', 'ccc9ddd81df94f14a5e0046f2e36ecbe', '2019-09-05 11:20:00', '229', '1', '0', '深圳尚硅谷', '0', 'yy', 'yy');
INSERT INTO `t_order` VALUES ('29', '1', '1', '1', '1e00edb1596744b9a90d99e464431692', '2019-09-05 11:21:26', '229', '1', '0', '深圳尚硅谷', '0', 'yy', 'yy');
INSERT INTO `t_order` VALUES ('30', '1', '1', '1', 'a070c3af002d4b55a4f845d9376cf970', '2019-09-05 11:30:14', '229', '1', '0', '北京尚硅谷', '0', '5', '5');
INSERT INTO `t_order` VALUES ('31', '1', '1', '1', 'bd78e39be21c424bbb985419ebe964a1', '2019-09-05 11:33:13', '229', '1', '0', '北京尚硅谷', '0', '', '');
INSERT INTO `t_order` VALUES ('32', '1', '1', '1', '63068c59a7e441dfb839d5bb951f070a', '2019-09-05 11:36:26', '1025', '5', '0', '北京尚硅谷', '1', '666', '666666');
INSERT INTO `t_order` VALUES ('33', '1', '1', '1', 'd19026cfc87b421087c2e0b9278830aa', '2019-09-05 16:49:32', '1025', '5', '0', '北京尚硅谷', '1', '尚硅谷公司', '大功告成');
INSERT INTO `t_order` VALUES ('34', '1', '1', '1', '1f066b447aea40c785c904007b981b21', '2019-09-05 16:49:58', '1025', '5', '0', '北京尚硅谷', '1', '尚硅谷公司', '大功告成');

-- ----------------------------
-- Table structure for t_param
-- ----------------------------
DROP TABLE IF EXISTS `t_param`;
CREATE TABLE `t_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `val` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='������';

-- ----------------------------
-- Records of t_param
-- ----------------------------

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����',
  `name` varchar(255) DEFAULT NULL COMMENT '��ɱ�ʶ',
  `title` varchar(255) DEFAULT NULL COMMENT '��ʶ˵��',
  `icon` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL COMMENT '��Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='��ɣ�Ȩ�ޣ���';

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', 'user', '用户模块', 'glyphicon glyphicon-user', '0');
INSERT INTO `t_permission` VALUES ('2', 'user:add', '新增', 'glyphicon glyphicon-plus', '1');
INSERT INTO `t_permission` VALUES ('3', 'user:delete', '删除', 'glyphicon glyphicon-remove', '1');
INSERT INTO `t_permission` VALUES ('4', 'user:update', '更新', 'glyphicon glyphicon-pencil', '1');
INSERT INTO `t_permission` VALUES ('5', 'user:get', '查询', 'glyphicon glyphicon-zoom-in', '1');
INSERT INTO `t_permission` VALUES ('6', 'user:assign:role', '授予角色', 'glyphicon glyphicon-user', '1');
INSERT INTO `t_permission` VALUES ('7', 'role', '角色模块', 'glyphicon glyphicon-heart', '0');
INSERT INTO `t_permission` VALUES ('8', 'role:add', '新增', 'glyphicon glyphicon-plus', '7');
INSERT INTO `t_permission` VALUES ('9', 'role:delete', '删除', 'glyphicon glyphicon-remove', '7');
INSERT INTO `t_permission` VALUES ('10', 'role:get', '查询', 'glyphicon glyphicon-zoom-in', '7');
INSERT INTO `t_permission` VALUES ('11', 'role:update', '修改', 'glyphicon glyphicon-pencil', '7');
INSERT INTO `t_permission` VALUES ('12', 'role:assign:permission', '授予权限', 'glyphicon glyphicon-user', '7');
INSERT INTO `t_permission` VALUES ('13', 'menu', '菜单模块', 'glyphicon glyphicon-th-list', '0');
INSERT INTO `t_permission` VALUES ('14', 'menu:add', '新增', 'glyphicon glyphicon-plus', '13');
INSERT INTO `t_permission` VALUES ('15', 'menu:delete', '删除', 'glyphicon glyphicon-remove', '13');
INSERT INTO `t_permission` VALUES ('16', 'menu:update', '修改', 'glyphicon glyphicon-pencil', '13');
INSERT INTO `t_permission` VALUES ('17', 'menu:get', '查询', 'glyphicon glyphicon-zoom-in', '13');
INSERT INTO `t_permission` VALUES ('18', 'menu:assign:permission', '授予权限', 'glyphicon glyphicon-user', '13');
INSERT INTO `t_permission` VALUES ('22', 'course:score', '课程评比', 'glyphicon glyphicon-flag', '0');

-- ----------------------------
-- Table structure for t_permission_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_permission_menu`;
CREATE TABLE `t_permission_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuid` int(11) DEFAULT NULL,
  `permissionid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_10` (`menuid`) USING BTREE,
  KEY `FK_Reference_9` (`permissionid`) USING BTREE,
  CONSTRAINT `t_permission_menu_ibfk_1` FOREIGN KEY (`menuid`) REFERENCES `t_menu` (`id`),
  CONSTRAINT `t_permission_menu_ibfk_2` FOREIGN KEY (`permissionid`) REFERENCES `t_permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='��ɲ˵���';

-- ----------------------------
-- Records of t_permission_menu
-- ----------------------------
INSERT INTO `t_permission_menu` VALUES ('8', '3', '1');
INSERT INTO `t_permission_menu` VALUES ('9', '3', '5');

-- ----------------------------
-- Table structure for t_permission_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_permission_resource`;
CREATE TABLE `t_permission_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resourceid` int(11) DEFAULT NULL,
  `permissionid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_20` (`permissionid`) USING BTREE,
  KEY `FK_Reference_21` (`resourceid`) USING BTREE,
  CONSTRAINT `t_permission_resource_ibfk_1` FOREIGN KEY (`permissionid`) REFERENCES `t_permission` (`id`),
  CONSTRAINT `t_permission_resource_ibfk_2` FOREIGN KEY (`resourceid`) REFERENCES `t_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�����Դ��';

-- ----------------------------
-- Records of t_permission_resource
-- ----------------------------

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '��Ŀ����',
  `remark` varchar(255) DEFAULT NULL COMMENT '��Ŀ���',
  `money` bigint(11) DEFAULT NULL COMMENT '���ʽ��',
  `day` int(11) DEFAULT NULL COMMENT '��������',
  `status` char(1) DEFAULT NULL COMMENT '0 - ������ʼ�� 1 - �ڳ��У� 2 - �ڳ�ɹ��� 3 - �ڳ�ʧ��',
  `deploydate` char(10) DEFAULT NULL COMMENT '��������',
  `supportmoney` bigint(11) DEFAULT NULL COMMENT '֧�ֽ��',
  `supporter` int(11) DEFAULT NULL COMMENT '֧��������',
  `completion` int(3) DEFAULT NULL COMMENT '��ɶ�',
  `memberid` int(11) DEFAULT NULL COMMENT '������ID',
  `createdate` char(19) DEFAULT NULL COMMENT '��������',
  `follower` int(11) DEFAULT NULL COMMENT '��ע������',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='��Ŀ��';

-- ----------------------------
-- Records of t_project
-- ----------------------------
INSERT INTO `t_project` VALUES ('1', '智能高清监控机器人', '可爱的造型，摄像安防远程互联的全能设计，让你随时随地守护您的家人，陪伴你的生活。', '1000000', '30', '5', null, '0', '0', '0', '1', '2019-09-02 11:53:17', '0');
INSERT INTO `t_project` VALUES ('2', 'NEOKA智能手环', '要运动更要安全，这款、名为“蝶舞”的NEOKA-V9100智能运动手环为“安全运动而生”。', '1000000', '22', '5', null, '0', '0', '0', '1', '2019-09-02 11:53:17', '200');
INSERT INTO `t_project` VALUES ('3', '驱蚊扣', '随处使用的驱蚊纽扣，\r\n解决夏季蚊虫问题。', '1000000', '22', '5', null, '0', '0', '0', '1', '2019-09-02 11:53:17', '300');

-- ----------------------------
-- Table structure for t_project_images
-- ----------------------------
DROP TABLE IF EXISTS `t_project_images`;
CREATE TABLE `t_project_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectid` int(11) DEFAULT NULL,
  `imgurl` varchar(255) DEFAULT NULL,
  `imgtype` tinyint(4) DEFAULT NULL COMMENT '0-头部图片 1-详情图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project_images
-- ----------------------------
INSERT INTO `t_project_images` VALUES ('1', '1', 'https://scw190831.oss-cn-beijing.aliyuncs.com/pic/ca3fd1e1da2f496d94f7ab5db94d6ea9_p1.jpg', '0');
INSERT INTO `t_project_images` VALUES ('2', '1', 'https://atcrowdfunding20190809.oss-cn-beijing.aliyuncs.com/pic/cdf2369bade646fca3d0dc3ba59f46ea_product_detail_body.jpg', '1');
INSERT INTO `t_project_images` VALUES ('3', '1', 'https://atcrowdfunding20190809.oss-cn-beijing.aliyuncs.com/pic/cdf2369bade646fca3d0dc3ba59f46ea_product_detail_body.jpg', '1');
INSERT INTO `t_project_images` VALUES ('4', '2', 'https://scw190831.oss-cn-beijing.aliyuncs.com/pic/ca3fd1e1da2f496d94f7ab5db94d6ea9_p1.jpg', '0');
INSERT INTO `t_project_images` VALUES ('5', '2', 'https://atcrowdfunding20190809.oss-cn-beijing.aliyuncs.com/pic/cdf2369bade646fca3d0dc3ba59f46ea_product_detail_body.jpg', '1');
INSERT INTO `t_project_images` VALUES ('6', '3', 'https://scw190831.oss-cn-beijing.aliyuncs.com/pic/ca3fd1e1da2f496d94f7ab5db94d6ea9_p1.jpg', '0');
INSERT INTO `t_project_images` VALUES ('7', '3', 'https://atcrowdfunding20190809.oss-cn-beijing.aliyuncs.com/pic/cdf2369bade646fca3d0dc3ba59f46ea_product_detail_body.jpg', '1');

-- ----------------------------
-- Table structure for t_project_initiator
-- ----------------------------
DROP TABLE IF EXISTS `t_project_initiator`;
CREATE TABLE `t_project_initiator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `selfintroduction` varchar(255) DEFAULT NULL,
  `detailselfintroduction` varchar(255) DEFAULT NULL,
  `telphone` varchar(13) DEFAULT NULL,
  `hotline` varchar(13) DEFAULT NULL,
  `projectid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project_initiator
-- ----------------------------

-- ----------------------------
-- Table structure for t_project_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_project_tag`;
CREATE TABLE `t_project_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectid` int(11) DEFAULT NULL,
  `tagid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_7` (`projectid`) USING BTREE,
  KEY `FK_Reference_8` (`tagid`) USING BTREE,
  CONSTRAINT `t_project_tag_ibfk_1` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`),
  CONSTRAINT `t_project_tag_ibfk_2` FOREIGN KEY (`tagid`) REFERENCES `t_tag` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='��Ŀ��ǩ��ϵ��';

-- ----------------------------
-- Records of t_project_tag
-- ----------------------------
INSERT INTO `t_project_tag` VALUES ('1', '1', '2');
INSERT INTO `t_project_tag` VALUES ('2', '1', '6');
INSERT INTO `t_project_tag` VALUES ('3', '2', '2');
INSERT INTO `t_project_tag` VALUES ('4', '3', '2');

-- ----------------------------
-- Table structure for t_project_type
-- ----------------------------
DROP TABLE IF EXISTS `t_project_type`;
CREATE TABLE `t_project_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectid` int(11) DEFAULT NULL,
  `typeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_5` (`projectid`) USING BTREE,
  KEY `FK_Reference_6` (`typeid`) USING BTREE,
  CONSTRAINT `t_project_type_ibfk_1` FOREIGN KEY (`projectid`) REFERENCES `t_project` (`id`),
  CONSTRAINT `t_project_type_ibfk_2` FOREIGN KEY (`typeid`) REFERENCES `t_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='��Ŀ�����ϵ��';

-- ----------------------------
-- Records of t_project_type
-- ----------------------------
INSERT INTO `t_project_type` VALUES ('1', '1', '1');
INSERT INTO `t_project_type` VALUES ('2', '1', '2');
INSERT INTO `t_project_type` VALUES ('3', '2', '1');
INSERT INTO `t_project_type` VALUES ('4', '3', '1');

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `createat` varchar(19) DEFAULT NULL,
  `updateat` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��Դ��';

-- ----------------------------
-- Records of t_resource
-- ----------------------------

-- ----------------------------
-- Table structure for t_return
-- ----------------------------
DROP TABLE IF EXISTS `t_return`;
CREATE TABLE `t_return` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����',
  `projectid` int(11) DEFAULT NULL COMMENT '��ĿID',
  `type` char(1) DEFAULT NULL COMMENT '0 - ʵ��ر��� 1 ������Ʒ�ر�',
  `supportmoney` int(11) DEFAULT NULL COMMENT '֧�ֽ��',
  `content` varchar(255) DEFAULT NULL COMMENT '�ر�����',
  `count` int(11) DEFAULT NULL COMMENT '0 Ϊ���޻ر�����',
  `signalpurchase` int(11) DEFAULT NULL COMMENT '�����޹�',
  `purchase` int(11) DEFAULT NULL COMMENT '�޹�����',
  `freight` int(11) DEFAULT NULL COMMENT '�˷�',
  `invoice` char(1) DEFAULT NULL COMMENT '0 - ������Ʊ�� 1 - ����Ʊ',
  `rtndate` int(11) DEFAULT NULL COMMENT '�ر�ʱ��,�ڳ�ɹ����������лر�',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='�ر���';

-- ----------------------------
-- Records of t_return
-- ----------------------------
INSERT INTO `t_return` VALUES ('1', '1', '1', '199', '感谢您的支持，在众筹开始后，您将以199元的优惠价格获得“遇见彩虹?”智能插座一件（参考价288元）。', '1', '5', '2000', '30', '1', '30');
INSERT INTO `t_return` VALUES ('2', '1', '1', '299', '感谢您的支持，在众筹开始后，您将以299元的优惠价格获得“遇见彩虹?”智能插座一件（参考价388元）。', '1', '5', '2000', '30', '1', '30');
INSERT INTO `t_return` VALUES ('3', '1', '1', '399', '感谢您的支持，在众筹开始后，您将以399元的优惠价格获得“遇见彩虹?”智能插座一件（参考价488元）。', '1', '5', '2000', '30', '1', '30');
INSERT INTO `t_return` VALUES ('4', '2', '1', '111', '感谢您的支持，在众筹开始后，您将以399元的优惠价格获得“遇见彩虹?”智能插座一件（参考价488元）', '1', '5', '55', '22', '1', '33');
INSERT INTO `t_return` VALUES ('5', '3', '1', '222', '感谢您的支持，在众筹开始后，您将以399元的优惠价格获得“遇见彩虹?”智能插座一件（参考价488元）', '1', '55', '555', '22', '1', '33');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='��ɫ��';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'PM - 项目经理');
INSERT INTO `t_role` VALUES ('2', 'SE - 软件工程师');
INSERT INTO `t_role` VALUES ('3', 'PG - 程序员');
INSERT INTO `t_role` VALUES ('4', 'TL - 组长');
INSERT INTO `t_role` VALUES ('5', 'GL - 组长2');
INSERT INTO `t_role` VALUES ('6', 'QA - 品质保证');
INSERT INTO `t_role` VALUES ('7', 'QC - 品质控制');
INSERT INTO `t_role` VALUES ('8', 'SA - 软件架构师');
INSERT INTO `t_role` VALUES ('9', 'CMO / CMS - 配置管理员');
INSERT INTO `t_role` VALUES ('11', '讲师');
INSERT INTO `t_role` VALUES ('12', 'qqqq');
INSERT INTO `t_role` VALUES ('13', 'aaa');
INSERT INTO `t_role` VALUES ('14', 'dddd');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) DEFAULT NULL,
  `permissionid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_3` (`roleid`) USING BTREE,
  KEY `FK_Reference_4` (`permissionid`) USING BTREE,
  CONSTRAINT `t_role_permission_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `t_role` (`id`),
  CONSTRAINT `t_role_permission_ibfk_2` FOREIGN KEY (`permissionid`) REFERENCES `t_permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='��ɫ��ɱ�';

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('17', '1', '1');
INSERT INTO `t_role_permission` VALUES ('18', '1', '2');
INSERT INTO `t_role_permission` VALUES ('19', '1', '3');
INSERT INTO `t_role_permission` VALUES ('20', '1', '6');
INSERT INTO `t_role_permission` VALUES ('21', '1', '7');
INSERT INTO `t_role_permission` VALUES ('22', '1', '8');
INSERT INTO `t_role_permission` VALUES ('23', '1', '13');
INSERT INTO `t_role_permission` VALUES ('24', '3', '2');
INSERT INTO `t_role_permission` VALUES ('25', '3', '1');
INSERT INTO `t_role_permission` VALUES ('29', '2', '1');
INSERT INTO `t_role_permission` VALUES ('30', '2', '2');
INSERT INTO `t_role_permission` VALUES ('31', '2', '5');
INSERT INTO `t_role_permission` VALUES ('32', '2', '7');
INSERT INTO `t_role_permission` VALUES ('33', '2', '8');

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='��Ŀ��ǩ';

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES ('1', null, '颜色');
INSERT INTO `t_tag` VALUES ('2', '1', '红色');
INSERT INTO `t_tag` VALUES ('3', '1', '白色');
INSERT INTO `t_tag` VALUES ('4', null, '高度');
INSERT INTO `t_tag` VALUES ('5', '4', '1.2米');
INSERT INTO `t_tag` VALUES ('6', '4', '1.5米');

-- ----------------------------
-- Table structure for t_transaction
-- ----------------------------
DROP TABLE IF EXISTS `t_transaction`;
CREATE TABLE `t_transaction` (
  `id` int(11) NOT NULL,
  `ordersn` varchar(255) DEFAULT NULL,
  `paysn` varchar(255) DEFAULT NULL,
  `memberid` int(11) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `paystate` tinyint(4) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `completiontime` varchar(19) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `createat` varchar(19) DEFAULT NULL,
  `updateat` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='֧����';

-- ----------------------------
-- Records of t_transaction
-- ----------------------------

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='��Ŀ����';

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES ('1', '科技类', '科技类');
INSERT INTO `t_type` VALUES ('2', '设计类', '设计类');
INSERT INTO `t_type` VALUES ('3', '公益类', '公益类');
INSERT INTO `t_type` VALUES ('4', '农业类', '农业类');
