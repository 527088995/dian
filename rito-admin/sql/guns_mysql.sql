DROP DATABASE IF EXISTS guns;
CREATE DATABASE IF NOT EXISTS guns DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE guns;

/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : guns

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 11/05/2019 13:51:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for database_info
-- ----------------------------
DROP TABLE IF EXISTS `database_info`;
CREATE TABLE `database_info`  (
  `db_id` bigint(20) NOT NULL COMMENT '主键id',
  `db_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库名称',
  `jdbc_driver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'jdbc的驱动类型',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库连接的账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据库连接密码',
  `jdbc_url` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'jdbc的url',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`db_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据库信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of database_info
-- ----------------------------
INSERT INTO `database_info` VALUES (1, '默认数据库', 'com.mysql.jdbc.Driver', 'root', 'root', 'jdbc:mysql://127.0.0.1:3306/guns?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=CTT', '2019-05-11 13:51:19');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL COMMENT '主键id',
  `pid` bigint(20) DEFAULT 0 COMMENT '父部门id',
  `pids` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '父级ids',
  `simple_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '简称',
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '全称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `version` int(11) DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (24, 0, '[0],', '总公司', '总公司', '', NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (25, 24, '[0],[24],', '开发部', '开发部', '', NULL, 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (26, 24, '[0],[24],', '运营部', '运营部', '', NULL, 3, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (27, 24, '[0],[24],', '战略部', '战略部', '', NULL, 4, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `dict_id` bigint(20) NOT NULL COMMENT '字典id',
  `dict_type_id` bigint(20) NOT NULL COMMENT '所属字典类型的id',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '字典编码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '字典名称',
  `parent_id` bigint(20) NOT NULL COMMENT '上级代码id',
  `parent_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '所有上级id',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'ENABLE' COMMENT '状态（字典）',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '字典的描述',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '基础字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1106120532442595330, 1106120208097067009, 'M', '男', 0, '[0]', 'ENABLE', NULL, '', '2019-03-14 17:11:00', NULL, 1, NULL);
INSERT INTO `sys_dict` VALUES (1106120574163337218, 1106120208097067009, 'F', '女', 0, '[0]', 'ENABLE', NULL, '', '2019-03-14 17:11:10', NULL, 1, NULL);
INSERT INTO `sys_dict` VALUES (1106120645697191938, 1106120265689055233, 'ENABLE', '启用', 0, '[0]', 'ENABLE', NULL, '', '2019-03-14 17:11:27', NULL, 1, NULL);
INSERT INTO `sys_dict` VALUES (1106120699468169217, 1106120265689055233, 'DISABLE', '禁用', 0, '[0]', 'ENABLE', NULL, '', '2019-03-14 17:11:40', NULL, 1, NULL);
INSERT INTO `sys_dict` VALUES (1106120784318939137, 1106120322450571266, 'ENABLE', '启用', 0, '[0]', 'ENABLE', NULL, '', '2019-03-14 17:12:00', NULL, 1, NULL);
INSERT INTO `sys_dict` VALUES (1106120825993543682, 1106120322450571266, 'FREEZE', '冻结', 0, '[0]', 'ENABLE', 1, '', '2019-03-14 17:12:10', '2019-03-16 10:56:36', 1, 1);
INSERT INTO `sys_dict` VALUES (1106120875872206849, 1106120322450571266, 'DELETED', '已删除', 0, '[0]', 'ENABLE', -1221, '', '2019-03-14 17:12:22', '2019-03-16 10:56:53', 1, 1);
INSERT INTO `sys_dict` VALUES (1106120935070613505, 1106120388036902914, 'Y', '删除', 0, '[0]', 'ENABLE', 23333, '', '2019-03-14 17:12:36', '2019-03-16 10:58:53', 1, 1);
INSERT INTO `sys_dict` VALUES (1106120968910258177, 1106120388036902914, 'N', '未删除', 0, '[0]', 'ENABLE', 1212211221, '', '2019-03-14 17:12:44', '2019-03-16 10:59:03', 1, 1);
INSERT INTO `sys_dict` VALUES (1106751061042974722, 1106120322450571266, '11212', '122', 0, '[0]', 'ENABLE', 1212, '122112', '2019-03-16 10:56:30', NULL, 1, NULL);
INSERT INTO `sys_dict` VALUES (1106751747772166145, 1106120388036902914, '12312', '3123123', 1106120968910258177, '[0],[1106120968910258177]', 'ENABLE', 123, '', '2019-03-16 10:59:14', NULL, 1, NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_type_id` bigint(20) NOT NULL COMMENT '字典类型id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '字典类型编码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '字典类型名称',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '字典描述',
  `system_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '是否是系统字典，Y-是，N-否',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'ENABLE' COMMENT '状态(字典)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) DEFAULT NULL COMMENT '添加时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`dict_type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1106120208097067009, 'SEX', '性别', '', 'Y', 'ENABLE', 4, '2019-03-14 17:09:43', 1, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (1106120265689055233, 'STATUS', '状态', '', 'Y', 'ENABLE', 3, '2019-03-14 17:09:57', 1, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (1106120322450571266, 'ACCOUNT_STATUS', '账号状态', '', 'Y', 'ENABLE', 21112, '2019-03-14 17:10:10', 1, '2019-03-16 10:56:15', 1);
INSERT INTO `sys_dict_type` VALUES (1106120388036902914, 'DEL_FLAG', '是否删除', '', 'Y', 'ENABLE', 2, '2019-03-14 17:10:26', 1, '2019-03-27 16:26:31', 1);

-- ----------------------------
-- Table structure for sys_file_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_info`;
CREATE TABLE `sys_file_info`  (
  `file_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键id',
  `file_bucket` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '文件仓库（oss仓库）',
  `file_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文件名称',
  `file_suffix` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '文件后缀',
  `file_size_kb` bigint(20) DEFAULT NULL COMMENT '文件大小kb',
  `final_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文件唯一标识id',
  `file_path` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '存储路径',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文件信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `login_log_id` bigint(20) NOT NULL COMMENT '主键',
  `log_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '日志名称',
  `user_id` bigint(20) DEFAULT NULL COMMENT '管理员id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '是否执行成功',
  `message` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '具体消息',
  `ip_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`login_log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '登录记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL COMMENT '主键id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单编号',
  `pcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单父编号',
  `pcodes` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '当前菜单的所有父菜单编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'url地址',
  `sort` int(65) DEFAULT NULL COMMENT '菜单排序号',
  `levels` int(65) DEFAULT NULL COMMENT '菜单层级',
  `menu_flag` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '是否是菜单(字典)',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'ENABLE' COMMENT '菜单状态(字典)',
  `new_page_flag` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '是否打开新页面的标识(字典)',
  `open_flag` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '是否打开(字典)',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (105, 'system', '0', '[0],', '系统管理', 'layui-icon layui-icon-set', '#', 20, 1, 'Y', NULL, 'ENABLE', NULL, '1', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (106, 'mgr', 'system', '[0],[system],', '用户管理', '', '/mgr', 1, 2, 'Y', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (107, 'mgr_add', 'mgr', '[0],[system],[mgr],', '添加用户', NULL, '/mgr/add', 1, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (108, 'mgr_edit', 'mgr', '[0],[system],[mgr],', '修改用户', NULL, '/mgr/edit', 2, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (109, 'mgr_delete', 'mgr', '[0],[system],[mgr],', '删除用户', NULL, '/mgr/delete', 3, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (110, 'mgr_reset', 'mgr', '[0],[system],[mgr],', '重置密码', NULL, '/mgr/reset', 4, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (111, 'mgr_freeze', 'mgr', '[0],[system],[mgr],', '冻结用户', NULL, '/mgr/freeze', 5, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (112, 'mgr_unfreeze', 'mgr', '[0],[system],[mgr],', '解除冻结用户', NULL, '/mgr/unfreeze', 6, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (113, 'mgr_setRole', 'mgr', '[0],[system],[mgr],', '分配角色', NULL, '/mgr/setRole', 7, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (114, 'role', 'system', '[0],[system],', '角色管理', NULL, '/role', 2, 2, 'Y', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (115, 'role_add', 'role', '[0],[system],[role],', '添加角色', NULL, '/role/add', 1, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (116, 'role_edit', 'role', '[0],[system],[role],', '修改角色', NULL, '/role/edit', 2, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (117, 'role_remove', 'role', '[0],[system],[role],', '删除角色', NULL, '/role/remove', 3, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (118, 'role_setAuthority', 'role', '[0],[system],[role],', '配置权限', NULL, '/role/setAuthority', 4, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (119, 'menu', 'system', '[0],[system],', '菜单管理', NULL, '/menu', 4, 2, 'Y', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (120, 'menu_add', 'menu', '[0],[system],[menu],', '添加菜单', NULL, '/menu/add', 1, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (121, 'menu_edit', 'menu', '[0],[system],[menu],', '修改菜单', NULL, '/menu/edit', 2, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (122, 'menu_remove', 'menu', '[0],[system],[menu],', '删除菜单', NULL, '/menu/remove', 3, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (128, 'log', 'system', '[0],[system],', '业务日志', NULL, '/log', 6, 2, 'Y', NULL, 'ENABLE', NULL, '0', NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (130, 'druid', 'system', '[0],[system],', '监控管理', NULL, '/druid', 7, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (131, 'dept', 'system', '[0],[system],', '部门管理', NULL, '/dept', 3, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (132, 'dict', 'system', '[0],[system],', '字典管理', '', '/dictType', 4, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (133, 'loginLog', 'system', '[0],[system],', '登录日志', NULL, '/loginLog', 6, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (134, 'log_clean', 'log', '[0],[system],[log],', '清空日志', NULL, '/log/delLog', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (135, 'dept_add', 'dept', '[0],[system],[dept],', '添加部门', NULL, '/dept/add', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (136, 'dept_update', 'dept', '[0],[system],[dept],', '修改部门', NULL, '/dept/update', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (137, 'dept_delete', 'dept', '[0],[system],[dept],', '删除部门', NULL, '/dept/delete', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (138, 'dict_add', 'dict', '[0],[system],[dict],', '添加字典', NULL, '/dictType/addItem', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (139, 'dict_update', 'dict', '[0],[system],[dict],', '修改字典', NULL, '/dictType/editItem', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (140, 'dict_delete', 'dict', '[0],[system],[dict],', '删除字典', NULL, '/dictType/delete', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (141, 'notice', 'system', '[0],[system],', '通知管理', NULL, '/notice', 9, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (142, 'notice_add', 'notice', '[0],[system],[notice],', '添加通知', NULL, '/notice/add', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (143, 'notice_update', 'notice', '[0],[system],[notice],', '修改通知', NULL, '/notice/update', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (144, 'notice_delete', 'notice', '[0],[system],[notice],', '删除通知', NULL, '/notice/delete', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (145, 'sys_message', 'dashboard', '[0],[dashboard],', '消息通知', 'layui-icon layui-icon-tips', '/system/notice', 30, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, '2019-04-08 22:49:39', NULL, 1);
INSERT INTO `sys_menu` VALUES (149, 'api_mgr', 'dev_tools', '[0],[dev_tools],', '接口文档', 'fa-leaf', '/swagger-ui.html', 10, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, '2019-05-11 13:40:51', NULL, 1);
INSERT INTO `sys_menu` VALUES (150, 'to_menu_edit', 'menu', '[0],[system],[menu],', '菜单编辑跳转', '', '/menu/menu_edit', 4, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (151, 'menu_list', 'menu', '[0],[system],[menu],', '菜单列表', '', '/menu/list', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (152, 'to_dept_update', 'dept', '[0],[system],[dept],', '修改部门跳转', '', '/dept/dept_update', 4, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (153, 'dept_list', 'dept', '[0],[system],[dept],', '部门列表', '', '/dept/list', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (154, 'dept_detail', 'dept', '[0],[system],[dept],', '部门详情', '', '/dept/detail', 6, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (155, 'to_dict_edit', 'dict', '[0],[system],[dict],', '修改菜单跳转', '', '/dict/dict_edit', 4, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (156, 'dict_list', 'dict', '[0],[system],[dict],', '字典列表', '', '/dict/list', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (157, 'dict_detail', 'dict', '[0],[system],[dict],', '字典详情', '', '/dict/detail', 6, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (158, 'log_list', 'log', '[0],[system],[log],', '日志列表', '', '/log/list', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (159, 'log_detail', 'log', '[0],[system],[log],', '日志详情', '', '/log/detail', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (160, 'del_login_log', 'loginLog', '[0],[system],[loginLog],', '清空登录日志', '', '/loginLog/delLoginLog', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (161, 'login_log_list', 'loginLog', '[0],[system],[loginLog],', '登录日志列表', '', '/loginLog/list', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (162, 'to_role_edit', 'role', '[0],[system],[role],', '修改角色跳转', '', '/role/role_edit', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (163, 'to_role_assign', 'role', '[0],[system],[role],', '角色分配跳转', '', '/role/role_assign', 6, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (164, 'role_list', 'role', '[0],[system],[role],', '角色列表', '', '/role/list', 7, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (165, 'to_assign_role', 'mgr', '[0],[system],[mgr],', '分配角色跳转', '', '/mgr/role_assign', 8, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (166, 'to_user_edit', 'mgr', '[0],[system],[mgr],', '编辑用户跳转', '', '/mgr/user_edit', 9, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (167, 'mgr_list', 'mgr', '[0],[system],[mgr],', '用户列表', '', '/mgr/list', 10, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, '2019-03-29 16:32:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (171, 'dev_tools', '0', '[0],', '开发工具', 'layui-icon layui-icon-code-circle', '#', 30, 1, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, '2019-05-11 13:40:27', NULL, 1);
INSERT INTO `sys_menu` VALUES (172, 'dashboard', '0', '[0],', '主控面板', 'layui-icon layui-icon-home', '#', 10, 1, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, '2019-04-08 22:48:15', NULL, 1);
INSERT INTO `sys_menu` VALUES (1110777136265838594, 'demos_show', '0', '[0],', '模板页面', 'layui-icon layui-icon-template', '#', 40, 1, 'Y', NULL, 'ENABLE', NULL, NULL, '2019-03-27 13:34:41', '2019-04-08 22:53:20', 1, 1);
INSERT INTO `sys_menu` VALUES (1110777366856089602, 'excel_import', 'demos_show', '[0],[demos_show],', 'excel导入', '', '/excel/import', 10, 2, 'Y', NULL, 'ENABLE', NULL, NULL, '2019-03-27 13:35:36', '2019-04-08 22:53:20', 1, 1);
INSERT INTO `sys_menu` VALUES (1110777491464667137, 'excel_export', 'demos_show', '[0],[demos_show],', 'excel导出', '', '/excel/export', 20, 2, 'Y', NULL, 'ENABLE', NULL, NULL, '2019-03-27 13:36:06', '2019-04-08 22:53:20', 1, 1);
INSERT INTO `sys_menu` VALUES (1110787391943098370, 'advanced_form', 'demos_show', '[0],[demos_show],', '高级表单', '', '/egForm', 30, 2, 'Y', NULL, 'ENABLE', NULL, NULL, '2019-03-27 14:15:26', '2019-04-08 22:53:20', 1, 1);
INSERT INTO `sys_menu` VALUES (1110839216310329346, 'pdf_view', 'demos_show', '[0],[demos_show],', '文档预览', '', '/loadPdfFile', 40, 2, 'Y', NULL, 'ENABLE', NULL, NULL, '2019-03-27 17:41:22', '2019-04-08 22:55:53', 1, 1);
INSERT INTO `sys_menu` VALUES (1111545968697860098, 'console', 'dashboard', '[0],[dashboard],', '项目介绍', '', '/system/console', 10, 2, 'Y', NULL, 'ENABLE', NULL, NULL, '2019-03-29 16:29:45', '2019-04-09 20:57:08', 1, 1);
INSERT INTO `sys_menu` VALUES (1111546189892870145, 'console2', 'dashboard', '[0],[dashboard],', '统计报表', '', '/system/console2', 20, 2, 'Y', NULL, 'ENABLE', NULL, NULL, '2019-03-29 16:30:38', '2019-04-08 22:49:48', 1, 1);
INSERT INTO `sys_menu` VALUES (1127085735660421122, 'code_generate', 'dev_tools', '[0],[dev_tools],', '代码生成', '', '/gen', 1, 2, 'Y', NULL, 'ENABLE', NULL, NULL, '2019-05-11 13:39:14', '2019-05-11 13:41:00', 1, 1);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` bigint(20) NOT NULL COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '内容',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '通知表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (6, '欢迎', 'hi，Guns旗舰版发布了！', '2017-01-11 08:53:20', 1, '2018-12-28 23:24:48', 1);
INSERT INTO `sys_notice` VALUES (8, '你好', '你好，世界！', '2017-05-10 19:28:57', 1, '2018-12-28 23:28:26', 1);

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log`  (
  `operation_log_id` bigint(20) NOT NULL COMMENT '主键',
  `log_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '日志类型(字典)',
  `log_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '日志名称',
  `user_id` bigint(65) DEFAULT NULL COMMENT '用户id',
  `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '类名称',
  `method` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '方法名称',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '是否成功(字典)',
  `message` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '备注',
  PRIMARY KEY (`operation_log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_relation`;
CREATE TABLE `sys_relation`  (
  `relation_id` bigint(20) NOT NULL COMMENT '主键',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`relation_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_relation
-- ----------------------------
INSERT INTO `sys_relation` VALUES (1071348922291826689, 105, 5);
INSERT INTO `sys_relation` VALUES (1071348922308603906, 106, 5);
INSERT INTO `sys_relation` VALUES (1071348922316992514, 107, 5);
INSERT INTO `sys_relation` VALUES (1071348922321186818, 108, 5);
INSERT INTO `sys_relation` VALUES (1071348922329575426, 109, 5);
INSERT INTO `sys_relation` VALUES (1071348922337964034, 110, 5);
INSERT INTO `sys_relation` VALUES (1071348922342158337, 111, 5);
INSERT INTO `sys_relation` VALUES (1071348922350546946, 112, 5);
INSERT INTO `sys_relation` VALUES (1071348922354741249, 113, 5);
INSERT INTO `sys_relation` VALUES (1071348922363129858, 165, 5);
INSERT INTO `sys_relation` VALUES (1071348922371518465, 166, 5);
INSERT INTO `sys_relation` VALUES (1071348922375712770, 167, 5);
INSERT INTO `sys_relation` VALUES (1071348922384101377, 114, 5);
INSERT INTO `sys_relation` VALUES (1071348922388295681, 115, 5);
INSERT INTO `sys_relation` VALUES (1071348922396684289, 116, 5);
INSERT INTO `sys_relation` VALUES (1071348922405072897, 117, 5);
INSERT INTO `sys_relation` VALUES (1071348922413461505, 118, 5);
INSERT INTO `sys_relation` VALUES (1071348922417655810, 162, 5);
INSERT INTO `sys_relation` VALUES (1071348922426044418, 163, 5);
INSERT INTO `sys_relation` VALUES (1071348922430238722, 164, 5);
INSERT INTO `sys_relation` VALUES (1071348922430238723, 119, 5);
INSERT INTO `sys_relation` VALUES (1071348922447015937, 120, 5);
INSERT INTO `sys_relation` VALUES (1071348922451210242, 121, 5);
INSERT INTO `sys_relation` VALUES (1071348922459598850, 122, 5);
INSERT INTO `sys_relation` VALUES (1071348922463793154, 150, 5);
INSERT INTO `sys_relation` VALUES (1071348922472181762, 151, 5);
INSERT INTO `sys_relation` VALUES (1071348922476376065, 128, 5);
INSERT INTO `sys_relation` VALUES (1071348922480570369, 134, 5);
INSERT INTO `sys_relation` VALUES (1071348922488958977, 158, 5);
INSERT INTO `sys_relation` VALUES (1071348922497347586, 159, 5);
INSERT INTO `sys_relation` VALUES (1071348922501541890, 130, 5);
INSERT INTO `sys_relation` VALUES (1071348922501541891, 131, 5);
INSERT INTO `sys_relation` VALUES (1071348922518319106, 135, 5);
INSERT INTO `sys_relation` VALUES (1071348922526707713, 136, 5);
INSERT INTO `sys_relation` VALUES (1071348922530902017, 137, 5);
INSERT INTO `sys_relation` VALUES (1071348922535096321, 152, 5);
INSERT INTO `sys_relation` VALUES (1071348922543484930, 153, 5);
INSERT INTO `sys_relation` VALUES (1071348922547679233, 154, 5);
INSERT INTO `sys_relation` VALUES (1071348922556067841, 132, 5);
INSERT INTO `sys_relation` VALUES (1071348922560262146, 138, 5);
INSERT INTO `sys_relation` VALUES (1071348922564456450, 139, 5);
INSERT INTO `sys_relation` VALUES (1071348922568650754, 140, 5);
INSERT INTO `sys_relation` VALUES (1071348922577039361, 155, 5);
INSERT INTO `sys_relation` VALUES (1071348922577039362, 156, 5);
INSERT INTO `sys_relation` VALUES (1071348922577039363, 157, 5);
INSERT INTO `sys_relation` VALUES (1071348922602205185, 133, 5);
INSERT INTO `sys_relation` VALUES (1071348922610593794, 160, 5);
INSERT INTO `sys_relation` VALUES (1071348922610593795, 161, 5);
INSERT INTO `sys_relation` VALUES (1071348922618982402, 141, 5);
INSERT INTO `sys_relation` VALUES (1071348922627371009, 142, 5);
INSERT INTO `sys_relation` VALUES (1071348922631565313, 143, 5);
INSERT INTO `sys_relation` VALUES (1071348922639953922, 144, 5);
INSERT INTO `sys_relation` VALUES (1127085983283740673, 105, 1);
INSERT INTO `sys_relation` VALUES (1127085983292129281, 106, 1);
INSERT INTO `sys_relation` VALUES (1127085983304712193, 107, 1);
INSERT INTO `sys_relation` VALUES (1127085983313100801, 108, 1);
INSERT INTO `sys_relation` VALUES (1127085983321489410, 109, 1);
INSERT INTO `sys_relation` VALUES (1127085983329878017, 110, 1);
INSERT INTO `sys_relation` VALUES (1127085983334072321, 111, 1);
INSERT INTO `sys_relation` VALUES (1127085983338266625, 112, 1);
INSERT INTO `sys_relation` VALUES (1127085983350849537, 113, 1);
INSERT INTO `sys_relation` VALUES (1127085983355043842, 165, 1);
INSERT INTO `sys_relation` VALUES (1127085983367626753, 166, 1);
INSERT INTO `sys_relation` VALUES (1127085983367626754, 167, 1);
INSERT INTO `sys_relation` VALUES (1127085983384403969, 114, 1);
INSERT INTO `sys_relation` VALUES (1127085983392792577, 115, 1);
INSERT INTO `sys_relation` VALUES (1127085983396986881, 116, 1);
INSERT INTO `sys_relation` VALUES (1127085983405375489, 117, 1);
INSERT INTO `sys_relation` VALUES (1127085983413764098, 118, 1);
INSERT INTO `sys_relation` VALUES (1127085983422152706, 162, 1);
INSERT INTO `sys_relation` VALUES (1127085983426347009, 163, 1);
INSERT INTO `sys_relation` VALUES (1127085983434735617, 164, 1);
INSERT INTO `sys_relation` VALUES (1127085983438929922, 119, 1);
INSERT INTO `sys_relation` VALUES (1127085983451512834, 120, 1);
INSERT INTO `sys_relation` VALUES (1127085983455707137, 121, 1);
INSERT INTO `sys_relation` VALUES (1127085983464095746, 122, 1);
INSERT INTO `sys_relation` VALUES (1127085983468290049, 150, 1);
INSERT INTO `sys_relation` VALUES (1127085983476678658, 151, 1);
INSERT INTO `sys_relation` VALUES (1127085983485067265, 128, 1);
INSERT INTO `sys_relation` VALUES (1127085983489261570, 134, 1);
INSERT INTO `sys_relation` VALUES (1127085983493455874, 158, 1);
INSERT INTO `sys_relation` VALUES (1127085983501844481, 159, 1);
INSERT INTO `sys_relation` VALUES (1127085983514427394, 130, 1);
INSERT INTO `sys_relation` VALUES (1127085983514427395, 131, 1);
INSERT INTO `sys_relation` VALUES (1127085983527010305, 135, 1);
INSERT INTO `sys_relation` VALUES (1127085983535398913, 136, 1);
INSERT INTO `sys_relation` VALUES (1127085983535398914, 137, 1);
INSERT INTO `sys_relation` VALUES (1127085983543787521, 152, 1);
INSERT INTO `sys_relation` VALUES (1127085983552176130, 153, 1);
INSERT INTO `sys_relation` VALUES (1127085983552176131, 154, 1);
INSERT INTO `sys_relation` VALUES (1127085983564759042, 132, 1);
INSERT INTO `sys_relation` VALUES (1127085983568953346, 138, 1);
INSERT INTO `sys_relation` VALUES (1127085983573147649, 139, 1);
INSERT INTO `sys_relation` VALUES (1127085983581536258, 140, 1);
INSERT INTO `sys_relation` VALUES (1127085983594119170, 155, 1);
INSERT INTO `sys_relation` VALUES (1127085983594119171, 156, 1);
INSERT INTO `sys_relation` VALUES (1127085983606702082, 157, 1);
INSERT INTO `sys_relation` VALUES (1127085983610896386, 133, 1);
INSERT INTO `sys_relation` VALUES (1127085983615090690, 160, 1);
INSERT INTO `sys_relation` VALUES (1127085983623479297, 161, 1);
INSERT INTO `sys_relation` VALUES (1127085983631867906, 141, 1);
INSERT INTO `sys_relation` VALUES (1127085983636062210, 142, 1);
INSERT INTO `sys_relation` VALUES (1127085983644450817, 143, 1);
INSERT INTO `sys_relation` VALUES (1127085983652839426, 144, 1);
INSERT INTO `sys_relation` VALUES (1127085983661228033, 171, 1);
INSERT INTO `sys_relation` VALUES (1127085983665422338, 149, 1);
INSERT INTO `sys_relation` VALUES (1127085983673810946, 1127085735660421122, 1);
INSERT INTO `sys_relation` VALUES (1127085983678005250, 172, 1);
INSERT INTO `sys_relation` VALUES (1127085983686393858, 145, 1);
INSERT INTO `sys_relation` VALUES (1127085983690588161, 1111545968697860098, 1);
INSERT INTO `sys_relation` VALUES (1127085983698976769, 1111546189892870145, 1);
INSERT INTO `sys_relation` VALUES (1127085983703171074, 1110777136265838594, 1);
INSERT INTO `sys_relation` VALUES (1127085983711559681, 1110777366856089602, 1);
INSERT INTO `sys_relation` VALUES (1127085983719948290, 1110777491464667137, 1);
INSERT INTO `sys_relation` VALUES (1127085983728336897, 1110787391943098370, 1);
INSERT INTO `sys_relation` VALUES (1127085983736725506, 1110839216310329346, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL COMMENT '主键id',
  `pid` bigint(20) DEFAULT NULL COMMENT '父角色id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '提示',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `version` int(11) DEFAULT NULL COMMENT '乐观锁',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 0, '超级管理员', 'administrator', 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (5, 1, '临时', 'temp', 2, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL COMMENT '主键id',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `account` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '账号',
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '名字',
  `birthday` datetime(0) DEFAULT NULL COMMENT '生日',
  `sex` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '性别(字典)',
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色id(多个逗号隔开)',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门id(多个逗号隔开)',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '状态(字典)',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
  `version` int(11) DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '1124606971782160385', 'admin', '1d6b1208c7d151d335790276a18e3d99', 'q6taw', 'stylefeng', '2018-11-16 00:00:00', 'M', 'sn93@qq.com', '18200000000', '1', 27, 'ENABLE', '2016-01-29 08:49:53', NULL, '2019-05-04 17:29:31', 24, 25);

SET FOREIGN_KEY_CHECKS = 1;
