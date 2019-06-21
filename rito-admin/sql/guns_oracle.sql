-- 部门
CREATE TABLE sys_dept (
  dept_id NUMBER(20, 0) NOT NULL,
  pid NUMBER(20, 0) NOT NULL,
  pids varchar2(512),
  simple_name varchar2(45),
  full_name varchar2(255),
  description varchar2(255),
  version int,
  sort int,
  create_time date,
  update_time date,
  create_user NUMBER(20, 0),
  update_user NUMBER(20, 0),
  PRIMARY KEY (dept_id)
);

INSERT INTO sys_dept VALUES (24, 0, '[0],', '总公司', '总公司', '', NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO sys_dept VALUES (25, 24, '[0],[24],', '开发部', '开发部', '', NULL, 2, NULL, NULL, NULL, NULL);
INSERT INTO sys_dept VALUES (26, 24, '[0],[24],', '运营部', '运营部', '', NULL, 3, NULL, NULL, NULL, NULL);
INSERT INTO sys_dept VALUES (27, 24, '[0],[24],', '战略部', '战略部', '', NULL, 4, NULL, NULL, NULL, NULL);

-- 字典
CREATE TABLE sys_dict (
  dict_id NUMBER(20, 0) NOT NULL,
  dict_type_id NUMBER(20, 0) NOT NULL,
  code varchar2(50),
  name varchar2(45),
  parent_id varchar2(255),
  parent_ids varchar2(255),
  status varchar2(10),
  sort int,
  description varchar2(1000),
  create_time date,
  update_time date,
  create_user NUMBER(20, 0),
  update_user NUMBER(20, 0),
  PRIMARY KEY (dict_id)
);

INSERT INTO sys_dict VALUES (1106120532442595330, 1106120208097067009, 'M', '男', 0, '[0]', 'ENABLE', NULL, '', to_date('2019-03-14 17:11:00', 'yyyy-mm-dd hh24:mi:ss'), NULL, 1, NULL);
INSERT INTO sys_dict VALUES (1106120574163337218, 1106120208097067009, 'F', '女', 0, '[0]', 'ENABLE', NULL, '', to_date('2019-03-14 17:11:10', 'yyyy-mm-dd hh24:mi:ss'), NULL, 1, NULL);
INSERT INTO sys_dict VALUES (1106120645697191938, 1106120265689055233, 'ENABLE', '启用', 0, '[0]', 'ENABLE', NULL, '', to_date('2019-03-14 17:11:27', 'yyyy-mm-dd hh24:mi:ss'), NULL, 1, NULL);
INSERT INTO sys_dict VALUES (1106120699468169217, 1106120265689055233, 'DISABLE', '禁用', 0, '[0]', 'ENABLE', NULL, '', to_date('2019-03-14 17:11:40', 'yyyy-mm-dd hh24:mi:ss'), NULL, 1, NULL);
INSERT INTO sys_dict VALUES (1106120784318939137, 1106120322450571266, 'ENABLE', '启用', 0, '[0]', 'ENABLE', NULL, '', to_date('2019-03-14 17:12:00', 'yyyy-mm-dd hh24:mi:ss'), NULL, 1, NULL);
INSERT INTO sys_dict VALUES (1106120825993543682, 1106120322450571266, 'FREEZE', '冻结', 0, '[0]', 'ENABLE', 1, '', to_date('2019-03-14 17:12:10', 'yyyy-mm-dd hh24:mi:ss'), to_date('2019-03-16 10:56:36', 'yyyy-mm-dd hh24:mi:ss'), 1, 1);
INSERT INTO sys_dict VALUES (1106120875872206849, 1106120322450571266, 'DELETED', '已删除', 0, '[0]', 'ENABLE', -1221, '', to_date('2019-03-14 17:12:22', 'yyyy-mm-dd hh24:mi:ss'), to_date('2019-03-16 10:56:53', 'yyyy-mm-dd hh24:mi:ss'), 1, 1);
INSERT INTO sys_dict VALUES (1106120935070613505, 1106120388036902914, 'Y', '删除', 0, '[0]', 'ENABLE', 23333, '', to_date('2019-03-14 17:12:36', 'yyyy-mm-dd hh24:mi:ss'), to_date('2019-03-16 10:58:53', 'yyyy-mm-dd hh24:mi:ss'), 1, 1);
INSERT INTO sys_dict VALUES (1106120968910258177, 1106120388036902914, 'N', '未删除', 0, '[0]', 'ENABLE', 1212211221, '', to_date('2019-03-14 17:12:44', 'yyyy-mm-dd hh24:mi:ss'), to_date('2019-03-16 10:59:03', 'yyyy-mm-dd hh24:mi:ss'), 1, 1);
INSERT INTO sys_dict VALUES (1106751061042974722, 1106120322450571266, '11212', '122', 0, '[0]', 'ENABLE', 1212, '122112', to_date('2019-03-16 10:56:30', 'yyyy-mm-dd hh24:mi:ss'), NULL, 1, NULL);
INSERT INTO sys_dict VALUES (1106751747772166145, 1106120388036902914, '12312', '3123123', 1106120968910258177, '[0],[1106120968910258177]', 'ENABLE', 123, '', to_date('2019-03-16 10:59:14', 'yyyy-mm-dd hh24:mi:ss'), NULL, 1, NULL);


-- 字典类型
CREATE TABLE sys_dict_type (
  dict_type_id NUMBER(20, 0) NOT NULL,
  code varchar2(50),
  name varchar2(45),
  description varchar2(1000),
  system_flag varchar2(1),
  status varchar2(10),
  sort int,
  create_time date,
  create_user NUMBER(20, 0),
  update_time date,
  update_user NUMBER(20, 0),
  PRIMARY KEY (dict_type_id)
);

INSERT INTO sys_dict_type VALUES (1106120208097067009, 'SEX', '性别', '', 'Y', 'ENABLE', 4, to_date('2019-03-14 17:09:43', 'yyyy-mm-dd hh24:mi:ss'), 1, NULL, NULL);
INSERT INTO sys_dict_type VALUES (1106120265689055233, 'STATUS', '状态', '', 'Y', 'ENABLE', 3, to_date('2019-03-14 17:09:57', 'yyyy-mm-dd hh24:mi:ss'), 1, NULL, NULL);
INSERT INTO sys_dict_type VALUES (1106120322450571266, 'ACCOUNT_STATUS', '账号状态', '', 'Y', 'ENABLE', 21112, to_date('2019-03-14 17:10:10', 'yyyy-mm-dd hh24:mi:ss'), 1, to_date('2019-03-16 10:56:15', 'yyyy-mm-dd hh24:mi:ss'), 1);
INSERT INTO sys_dict_type VALUES (1106120388036902914, 'DEL_FLAG', '是否删除', '', 'Y', 'ENABLE', 2, to_date('2019-03-14 17:10:26', 'yyyy-mm-dd hh24:mi:ss'), 1, to_date('2019-03-27 16:26:31', 'yyyy-mm-dd hh24:mi:ss'), 1);



-- 文件信息
CREATE TABLE sys_file_info (
  file_id NUMBER(20, 0) NOT NULL,
  file_data varchar2(50),
  create_time date,
  update_time date,
  create_user NUMBER(20, 0),
  update_user NUMBER(20, 0),
  PRIMARY KEY (file_id)
);

-- 登录日志
CREATE TABLE sys_login_log (
  login_log_id NUMBER(20, 0) NOT NULL,
  log_name varchar2(255),
  user_id NUMBER(20, 0),
  create_time date,
  succeed varchar2(255),
  message varchar2(1000),
  ip_address varchar2(1000),
  PRIMARY KEY (login_log_id)
);

-- 菜单
CREATE TABLE sys_menu (
  menu_id NUMBER(20, 0) NOT NULL,
  code varchar2(255),
  pcode varchar2(255),
  pcodes varchar2(255),
  name varchar2(255),
  icon varchar2(255),
  url varchar2(255),
  sort int,
  levels int,
  menu_flag varchar2(32),
  description varchar2(255),
  status varchar2(32),
  new_page_flag varchar2(32),
  open_flag varchar2(32),
  create_time date,
  update_time date,
  create_user NUMBER(20, 0),
  update_user NUMBER(20, 0),
  PRIMARY KEY (menu_id)
);

INSERT INTO sys_menu VALUES (105, 'system', '0', '[0],', '系统管理', 'layui-icon layui-icon-set', '#', 20, 1, 'Y', NULL, 'ENABLE', NULL, '1', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (106, 'mgr', 'system', '[0],[system],', '用户管理', '', '/mgr', 1, 2, 'Y', NULL, 'ENABLE', NULL, '0', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (107, 'mgr_add', 'mgr', '[0],[system],[mgr],', '添加用户', NULL, '/mgr/add', 1, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (108, 'mgr_edit', 'mgr', '[0],[system],[mgr],', '修改用户', NULL, '/mgr/edit', 2, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (109, 'mgr_delete', 'mgr', '[0],[system],[mgr],', '删除用户', NULL, '/mgr/delete', 3, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (110, 'mgr_reset', 'mgr', '[0],[system],[mgr],', '重置密码', NULL, '/mgr/reset', 4, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (111, 'mgr_freeze', 'mgr', '[0],[system],[mgr],', '冻结用户', NULL, '/mgr/freeze', 5, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (112, 'mgr_unfreeze', 'mgr', '[0],[system],[mgr],', '解除冻结用户', NULL, '/mgr/unfreeze', 6, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (113, 'mgr_setRole', 'mgr', '[0],[system],[mgr],', '分配角色', NULL, '/mgr/setRole', 7, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (114, 'role', 'system', '[0],[system],', '角色管理', NULL, '/role', 2, 2, 'Y', NULL, 'ENABLE', NULL, '0', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (115, 'role_add', 'role', '[0],[system],[role],', '添加角色', NULL, '/role/add', 1, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (116, 'role_edit', 'role', '[0],[system],[role],', '修改角色', NULL, '/role/edit', 2, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (117, 'role_remove', 'role', '[0],[system],[role],', '删除角色', NULL, '/role/remove', 3, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (118, 'role_setAuthority', 'role', '[0],[system],[role],', '配置权限', NULL, '/role/setAuthority', 4, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (119, 'menu', 'system', '[0],[system],', '菜单管理', NULL, '/menu', 4, 2, 'Y', NULL, 'ENABLE', NULL, '0', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (120, 'menu_add', 'menu', '[0],[system],[menu],', '添加菜单', NULL, '/menu/add', 1, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (121, 'menu_edit', 'menu', '[0],[system],[menu],', '修改菜单', NULL, '/menu/edit', 2, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (122, 'menu_remove', 'menu', '[0],[system],[menu],', '删除菜单', NULL, '/menu/remove', 3, 3, 'N', NULL, 'ENABLE', NULL, '0', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (128, 'log', 'system', '[0],[system],', '业务日志', NULL, '/log', 6, 2, 'Y', NULL, 'ENABLE', NULL, '0', NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (130, 'druid', 'system', '[0],[system],', '监控管理', NULL, '/druid', 7, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (131, 'dept', 'system', '[0],[system],', '部门管理', NULL, '/dept', 3, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (132, 'dict', 'system', '[0],[system],', '字典管理', '', '/dictType', 4, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (133, 'loginLog', 'system', '[0],[system],', '登录日志', NULL, '/loginLog', 6, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (134, 'log_clean', 'log', '[0],[system],[log],', '清空日志', NULL, '/log/delLog', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (135, 'dept_add', 'dept', '[0],[system],[dept],', '添加部门', NULL, '/dept/add', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (136, 'dept_update', 'dept', '[0],[system],[dept],', '修改部门', NULL, '/dept/update', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (137, 'dept_delete', 'dept', '[0],[system],[dept],', '删除部门', NULL, '/dept/delete', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (138, 'dict_add', 'dict', '[0],[system],[dict],', '添加字典', NULL, '/dictType/addItem', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (139, 'dict_update', 'dict', '[0],[system],[dict],', '修改字典', NULL, '/dictType/editItem', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (140, 'dict_delete', 'dict', '[0],[system],[dict],', '删除字典', NULL, '/dictType/delete', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (141, 'notice', 'system', '[0],[system],', '通知管理', NULL, '/notice', 9, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (142, 'notice_add', 'notice', '[0],[system],[notice],', '添加通知', NULL, '/notice/add', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (143, 'notice_update', 'notice', '[0],[system],[notice],', '修改通知', NULL, '/notice/update', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (144, 'notice_delete', 'notice', '[0],[system],[notice],', '删除通知', NULL, '/notice/delete', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (145, 'sys_message', 'dashboard', '[0],[dashboard],', '消息页', 'layui-icon layui-icon-tips', '/system/notice', 30, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (149, 'api_mgr', 'dev_tools', '[0],[dev_tools],', '接口文档', 'fa-leaf', '/swagger-ui.html', 2, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (150, 'to_menu_edit', 'menu', '[0],[system],[menu],', '菜单编辑跳转', '', '/menu/menu_edit', 4, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (151, 'menu_list', 'menu', '[0],[system],[menu],', '菜单列表', '', '/menu/list', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (152, 'to_dept_update', 'dept', '[0],[system],[dept],', '修改部门跳转', '', '/dept/dept_update', 4, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (153, 'dept_list', 'dept', '[0],[system],[dept],', '部门列表', '', '/dept/list', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (154, 'dept_detail', 'dept', '[0],[system],[dept],', '部门详情', '', '/dept/detail', 6, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (155, 'to_dict_edit', 'dict', '[0],[system],[dict],', '修改菜单跳转', '', '/dict/dict_edit', 4, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (156, 'dict_list', 'dict', '[0],[system],[dict],', '字典列表', '', '/dict/list', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (157, 'dict_detail', 'dict', '[0],[system],[dict],', '字典详情', '', '/dict/detail', 6, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (158, 'log_list', 'log', '[0],[system],[log],', '日志列表', '', '/log/list', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (159, 'log_detail', 'log', '[0],[system],[log],', '日志详情', '', '/log/detail', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (160, 'del_login_log', 'loginLog', '[0],[system],[loginLog],', '清空登录日志', '', '/loginLog/delLoginLog', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (161, 'login_log_list', 'loginLog', '[0],[system],[loginLog],', '登录日志列表', '', '/loginLog/list', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (162, 'to_role_edit', 'role', '[0],[system],[role],', '修改角色跳转', '', '/role/role_edit', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (163, 'to_role_assign', 'role', '[0],[system],[role],', '角色分配跳转', '', '/role/role_assign', 6, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (164, 'role_list', 'role', '[0],[system],[role],', '角色列表', '', '/role/list', 7, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (165, 'to_assign_role', 'mgr', '[0],[system],[mgr],', '分配角色跳转', '', '/mgr/role_assign', 8, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (166, 'to_user_edit', 'mgr', '[0],[system],[mgr],', '编辑用户跳转', '', '/mgr/user_edit', 9, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (167, 'mgr_list', 'mgr', '[0],[system],[mgr],', '用户列表', '', '/mgr/list', 10, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (171, 'dev_tools', '0', '[0],', 'API管理', 'layui-icon layui-icon-app', '#', 30, 1, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (172, 'dashboard', '0', '[0],', 'Dashboard', 'layui-icon layui-icon-home', '#', 10, 1, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, null, NULL, 1);
INSERT INTO sys_menu VALUES (1110777136265838594, 'demos_show', '0', '[0],', 'demos展示', 'layui-icon layui-icon-fire', '#', 40, 1, 'Y', NULL, 'ENABLE', NULL, NULL, null, null, 1, 1);
INSERT INTO sys_menu VALUES (1110777366856089602, 'excel_import', 'demos_show', '[0],[demos_show],', 'excel导入', '', '/excel/import', 10, 2, 'Y', NULL, 'ENABLE', NULL, NULL, null, NULL, 1, NULL);
INSERT INTO sys_menu VALUES (1110777491464667137, 'excel_export', 'demos_show', '[0],[demos_show],', 'excel导出', '', '/excel/export', 20, 2, 'Y', NULL, 'ENABLE', NULL, NULL, null, NULL, 1, NULL);
INSERT INTO sys_menu VALUES (1110787391943098370, 'advanced_form', 'demos_show', '[0],[demos_show],', '高级表单', '', '/egForm', 30, 2, 'Y', NULL, 'ENABLE', NULL, NULL, null, NULL, 1, NULL);
INSERT INTO sys_menu VALUES (1110839216310329346, 'pdf_view', 'demos_show', '[0],[demos_show],', 'pdf预览', '', '/loadPdfFile', 40, 2, 'Y', NULL, 'ENABLE', NULL, NULL, null, NULL, 1, NULL);
INSERT INTO sys_menu VALUES (1111545968697860098, 'console', 'dashboard', '[0],[dashboard],', '控制台', '', '/system/console', 10, 2, 'Y', NULL, 'ENABLE', NULL, NULL, null, NULL, 1, NULL);
INSERT INTO sys_menu VALUES (1111546189892870145, 'console2', 'dashboard', '[0],[dashboard],', '分析页', '', '/system/console2', 20, 2, 'Y', NULL, 'ENABLE', NULL, NULL, null, NULL, 1, NULL);


-- 通知表
CREATE TABLE sys_notice (
  notice_id NUMBER(20, 0) NOT NULL,
  title varchar2(255),
  content varchar2(1000),
  create_time date,
  create_user NUMBER(20, 0),
  update_time date,
  update_user NUMBER(20, 0),
  PRIMARY KEY (notice_id)
);

INSERT INTO sys_notice VALUES (6, '欢迎', 'hi，Guns旗舰版发布了！', to_date('2017-01-11 08:53:20', 'yyyy-mm-dd hh24:mi:ss'), 1, to_date('2018-12-28 23:24:48', 'yyyy-mm-dd hh24:mi:ss'), 1);
INSERT INTO sys_notice VALUES (8, '你好', '你好，世界！', to_date('2017-05-10 19:28:57', 'yyyy-mm-dd hh24:mi:ss'), 1, to_date('2018-12-28 23:28:26', 'yyyy-mm-dd hh24:mi:ss'), 1);

-- 操作日志
CREATE TABLE sys_operation_log (
  operation_log_id NUMBER(20, 0) NOT NULL,
  log_type varchar2(32),
  log_name varchar2(255),
  user_id NUMBER(20, 0),
  class_name varchar2(255),
  method varchar2(255),
  create_time date,
  succeed varchar2(32),
  message varchar2(1000),
  PRIMARY KEY (operation_log_id)
);

-- 角色和菜单关联表
CREATE TABLE sys_relation (
  relation_id NUMBER(20, 0) NOT NULL,
  menu_id NUMBER(20, 0) NOT NULL,
  role_id NUMBER(20, 0) NOT NULL,
  PRIMARY KEY (relation_id)
);

INSERT INTO sys_relation VALUES (1071348922291826689, 105, 5);
INSERT INTO sys_relation VALUES (1071348922308603906, 106, 5);
INSERT INTO sys_relation VALUES (1071348922316992514, 107, 5);
INSERT INTO sys_relation VALUES (1071348922321186818, 108, 5);
INSERT INTO sys_relation VALUES (1071348922329575426, 109, 5);
INSERT INTO sys_relation VALUES (1071348922337964034, 110, 5);
INSERT INTO sys_relation VALUES (1071348922342158337, 111, 5);
INSERT INTO sys_relation VALUES (1071348922350546946, 112, 5);
INSERT INTO sys_relation VALUES (1071348922354741249, 113, 5);
INSERT INTO sys_relation VALUES (1071348922363129858, 165, 5);
INSERT INTO sys_relation VALUES (1071348922371518465, 166, 5);
INSERT INTO sys_relation VALUES (1071348922375712770, 167, 5);
INSERT INTO sys_relation VALUES (1071348922384101377, 114, 5);
INSERT INTO sys_relation VALUES (1071348922388295681, 115, 5);
INSERT INTO sys_relation VALUES (1071348922396684289, 116, 5);
INSERT INTO sys_relation VALUES (1071348922405072897, 117, 5);
INSERT INTO sys_relation VALUES (1071348922413461505, 118, 5);
INSERT INTO sys_relation VALUES (1071348922417655810, 162, 5);
INSERT INTO sys_relation VALUES (1071348922426044418, 163, 5);
INSERT INTO sys_relation VALUES (1071348922430238722, 164, 5);
INSERT INTO sys_relation VALUES (1071348922430238723, 119, 5);
INSERT INTO sys_relation VALUES (1071348922447015937, 120, 5);
INSERT INTO sys_relation VALUES (1071348922451210242, 121, 5);
INSERT INTO sys_relation VALUES (1071348922459598850, 122, 5);
INSERT INTO sys_relation VALUES (1071348922463793154, 150, 5);
INSERT INTO sys_relation VALUES (1071348922472181762, 151, 5);
INSERT INTO sys_relation VALUES (1071348922476376065, 128, 5);
INSERT INTO sys_relation VALUES (1071348922480570369, 134, 5);
INSERT INTO sys_relation VALUES (1071348922488958977, 158, 5);
INSERT INTO sys_relation VALUES (1071348922497347586, 159, 5);
INSERT INTO sys_relation VALUES (1071348922501541890, 130, 5);
INSERT INTO sys_relation VALUES (1071348922501541891, 131, 5);
INSERT INTO sys_relation VALUES (1071348922518319106, 135, 5);
INSERT INTO sys_relation VALUES (1071348922526707713, 136, 5);
INSERT INTO sys_relation VALUES (1071348922530902017, 137, 5);
INSERT INTO sys_relation VALUES (1071348922535096321, 152, 5);
INSERT INTO sys_relation VALUES (1071348922543484930, 153, 5);
INSERT INTO sys_relation VALUES (1071348922547679233, 154, 5);
INSERT INTO sys_relation VALUES (1071348922556067841, 132, 5);
INSERT INTO sys_relation VALUES (1071348922560262146, 138, 5);
INSERT INTO sys_relation VALUES (1071348922564456450, 139, 5);
INSERT INTO sys_relation VALUES (1071348922568650754, 140, 5);
INSERT INTO sys_relation VALUES (1071348922577039361, 155, 5);
INSERT INTO sys_relation VALUES (1071348922577039362, 156, 5);
INSERT INTO sys_relation VALUES (1071348922577039363, 157, 5);
INSERT INTO sys_relation VALUES (1071348922602205185, 133, 5);
INSERT INTO sys_relation VALUES (1071348922610593794, 160, 5);
INSERT INTO sys_relation VALUES (1071348922610593795, 161, 5);
INSERT INTO sys_relation VALUES (1071348922618982402, 141, 5);
INSERT INTO sys_relation VALUES (1071348922627371009, 142, 5);
INSERT INTO sys_relation VALUES (1071348922631565313, 143, 5);
INSERT INTO sys_relation VALUES (1071348922639953922, 144, 5);
INSERT INTO sys_relation VALUES (1111546349351919618, 105, 1);
INSERT INTO sys_relation VALUES (1111546349364502529, 106, 1);
INSERT INTO sys_relation VALUES (1111546349381279746, 107, 1);
INSERT INTO sys_relation VALUES (1111546349393862658, 108, 1);
INSERT INTO sys_relation VALUES (1111546349410639873, 109, 1);
INSERT INTO sys_relation VALUES (1111546349427417089, 110, 1);
INSERT INTO sys_relation VALUES (1111546349440000001, 111, 1);
INSERT INTO sys_relation VALUES (1111546349460971521, 112, 1);
INSERT INTO sys_relation VALUES (1111546349473554433, 113, 1);
INSERT INTO sys_relation VALUES (1111546349490331650, 165, 1);
INSERT INTO sys_relation VALUES (1111546349502914561, 166, 1);
INSERT INTO sys_relation VALUES (1111546349519691778, 167, 1);
INSERT INTO sys_relation VALUES (1111546349532274689, 114, 1);
INSERT INTO sys_relation VALUES (1111546349544857601, 115, 1);
INSERT INTO sys_relation VALUES (1111546349557440514, 116, 1);
INSERT INTO sys_relation VALUES (1111546349570023425, 117, 1);
INSERT INTO sys_relation VALUES (1111546349586800641, 118, 1);
INSERT INTO sys_relation VALUES (1111546349599383554, 162, 1);
INSERT INTO sys_relation VALUES (1111546349616160769, 163, 1);
INSERT INTO sys_relation VALUES (1111546349628743682, 164, 1);
INSERT INTO sys_relation VALUES (1111546349645520897, 119, 1);
INSERT INTO sys_relation VALUES (1111546349662298113, 120, 1);
INSERT INTO sys_relation VALUES (1111546349679075329, 121, 1);
INSERT INTO sys_relation VALUES (1111546349700046849, 122, 1);
INSERT INTO sys_relation VALUES (1111546349716824065, 150, 1);
INSERT INTO sys_relation VALUES (1111546349737795586, 151, 1);
INSERT INTO sys_relation VALUES (1111546349758767106, 128, 1);
INSERT INTO sys_relation VALUES (1111546349779738626, 134, 1);
INSERT INTO sys_relation VALUES (1111546349800710146, 158, 1);
INSERT INTO sys_relation VALUES (1111546349821681666, 159, 1);
INSERT INTO sys_relation VALUES (1111546349846847490, 130, 1);
INSERT INTO sys_relation VALUES (1111546349863624706, 131, 1);
INSERT INTO sys_relation VALUES (1111546349884596226, 135, 1);
INSERT INTO sys_relation VALUES (1111546349901373442, 136, 1);
INSERT INTO sys_relation VALUES (1111546349922344962, 137, 1);
INSERT INTO sys_relation VALUES (1111546349939122178, 152, 1);
INSERT INTO sys_relation VALUES (1111546349960093697, 153, 1);
INSERT INTO sys_relation VALUES (1111546349976870914, 154, 1);
INSERT INTO sys_relation VALUES (1111546349993648129, 132, 1);
INSERT INTO sys_relation VALUES (1111546350010425346, 138, 1);
INSERT INTO sys_relation VALUES (1111546350031396865, 139, 1);
INSERT INTO sys_relation VALUES (1111546350048174081, 140, 1);
INSERT INTO sys_relation VALUES (1111546350069145602, 155, 1);
INSERT INTO sys_relation VALUES (1111546350085922818, 156, 1);
INSERT INTO sys_relation VALUES (1111546350106894338, 157, 1);
INSERT INTO sys_relation VALUES (1111546350127865858, 133, 1);
INSERT INTO sys_relation VALUES (1111546350144643073, 160, 1);
INSERT INTO sys_relation VALUES (1111546350169808898, 161, 1);
INSERT INTO sys_relation VALUES (1111546350186586113, 141, 1);
INSERT INTO sys_relation VALUES (1111546350211751937, 142, 1);
INSERT INTO sys_relation VALUES (1111546350232723458, 143, 1);
INSERT INTO sys_relation VALUES (1111546350262083585, 144, 1);
INSERT INTO sys_relation VALUES (1111546350278860801, 171, 1);
INSERT INTO sys_relation VALUES (1111546350299832322, 149, 1);
INSERT INTO sys_relation VALUES (1111546350324998146, 172, 1);
INSERT INTO sys_relation VALUES (1111546350345969665, 145, 1);
INSERT INTO sys_relation VALUES (1111546350371135489, 1111545968697860098, 1);
INSERT INTO sys_relation VALUES (1111546350387912705, 1111546189892870145, 1);
INSERT INTO sys_relation VALUES (1111546350404689921, 1110777136265838594, 1);
INSERT INTO sys_relation VALUES (1111546350421467137, 1110777366856089602, 1);
INSERT INTO sys_relation VALUES (1111546350446632962, 1110777491464667137, 1);
INSERT INTO sys_relation VALUES (1111546350459215873, 1110787391943098370, 1);
INSERT INTO sys_relation VALUES (1111546350471798786, 1110839216310329346, 1);

-- 角色表
CREATE TABLE sys_role (
  role_id NUMBER(20, 0) NOT NULL,
  pid NUMBER(20, 0),
  name varchar2(255),
  description varchar2(255),
  sort int,
  version int,
  create_time date,
  update_time date,
  create_user NUMBER(20, 0),
  update_user NUMBER(20, 0),
  PRIMARY KEY (role_id)
);

INSERT INTO sys_role VALUES (1, 0, '超级管理员', 'administrator', 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO sys_role VALUES (5, 1, '临时', 'temp', 2, NULL, NULL, NULL, NULL, NULL);

-- 管理员表
CREATE TABLE sys_user (
  user_id NUMBER(20, 0) NOT NULL,
  avatar varchar2(255),
  account varchar2(45),
  password varchar2(45),
  salt varchar2(45),
  name varchar2(45),
  birthday date,
  sex varchar2(32),
  email varchar2(45),
  phone varchar2(45),
  role_id varchar2(255),
  dept_id NUMBER(20, 0),
  status varchar2(32),
  create_time date,
  create_user NUMBER(20, 0),
  update_time date,
  update_user NUMBER(20, 0),
  version int,
  PRIMARY KEY (user_id)
);

INSERT INTO sys_user VALUES (1, '1', 'admin', '1d6b1208c7d151d335790276a18e3d99', 'q6taw', 'stylefeng', to_date('2018-11-16 00:00:00', 'yyyy-mm-dd hh24:mi:ss'), 'M', 'sn93@qq.com', '18200000000', '1', 27, 'ENABLE', to_date('2016-01-29 08:49:53', 'yyyy-mm-dd hh24:mi:ss'), NULL, to_date('2018-12-28 22:52:24', 'yyyy-mm-dd hh24:mi:ss'), 24, 25);