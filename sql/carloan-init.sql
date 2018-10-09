

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `dept`
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) DEFAULT NULL COMMENT '排序',
  `pid` int(11) DEFAULT NULL COMMENT '父部门id',
  `pids` varchar(255) DEFAULT NULL COMMENT '父级ids',
  `simplename` varchar(45) DEFAULT NULL COMMENT '简称',
  `fullname` varchar(255) DEFAULT NULL COMMENT '全称',
  `tips` varchar(255) DEFAULT NULL COMMENT '提示',
  `version` int(11) DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
--  Records of `dept`
-- ----------------------------
BEGIN;
INSERT INTO `dept` VALUES ('1', '1', '0', '[0],', '总公司', '总公司', '', null),
   ('2', '1', '1', '[0],[1],', '成都分公司', '成都分公司', '', null),
   ('3', '2', '1', '[0],[1],', '北京分公司', '北京分公司', '', null),
   ('4', '3', '1', '[0],[1],', '重庆分公司', '重庆分公司', '', null),
   ('5', '4', '1', '[0],[1],', '技术研发部', '技术研发部', '', null);
COMMIT;

-- ----------------------------
--  Table structure for `dict`
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) DEFAULT NULL COMMENT '排序',
  `pid` int(11) DEFAULT NULL COMMENT '父级字典',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `tips` varchar(255) DEFAULT NULL COMMENT '提示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
--  Records of `dict`
-- ----------------------------
BEGIN;
INSERT INTO `dict` VALUES ('16', '0', '0', '状态', null);
INSERT INTO `dict` VALUES ('17', '1', '16', '启用', null);
INSERT INTO `dict` VALUES ('18', '2', '16', '禁用', null);
INSERT INTO `dict` VALUES ('29', '0', '0', '性别', null);
INSERT INTO `dict` VALUES ('30', '1', '29', '男', null);
INSERT INTO `dict` VALUES ('31', '2', '29', '女', null);
INSERT INTO `dict` VALUES ('35', '0', '0', '账号状态', null);
INSERT INTO `dict` VALUES ('36', '1', '35', '启用', null);
INSERT INTO `dict` VALUES ('37', '2', '35', '冻结', null);
INSERT INTO `dict` VALUES ('38', '3', '35', '已删除', null);
INSERT INTO `dict` VALUES ('39', '0', '0', '调用车300接口token', null);
INSERT INTO `dict` VALUES ('40', '300', '39', '5614a745174c4aa880ed42e907c5147c', null);
INSERT INTO `dict` VALUES ('41', '0', '0', '调用运营商认证接口token', null);
INSERT INTO `dict` VALUES ('42', '100', '41', '8c341f8c37b680dc356a0b936f33b9d7', null);
                          ;
COMMIT;

-- ----------------------------
--  Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(255) DEFAULT NULL COMMENT '菜单编号',
  `pcode` varchar(255) DEFAULT NULL COMMENT '菜单父编号',
  `pcodes` varchar(255) DEFAULT NULL COMMENT '当前菜单的所有父菜单编号',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) DEFAULT NULL COMMENT 'url地址',
  `num` int(65) DEFAULT NULL COMMENT '菜单排序号',
  `levels` int(65) DEFAULT NULL COMMENT '菜单层级',
  `ismenu` int(11) DEFAULT NULL COMMENT '是否是菜单（1：是  0：不是）',
  `tips` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` int(65) DEFAULT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  `isopen` int(11) DEFAULT NULL COMMENT '是否打开:    1:打开   0:不打开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
--  Records of `menu`
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES
  ('1', 'system', '0', '[0],', '系统管理', 'fa-user', '', '3', '1', '1', null, '1', '1'),
  ('2', 'mgr', 'system', '[0],[system],', '用户管理', '', '/mgr', '1', '2', '1', null, '1', '0'),
  ('3', 'mgr_add', 'mgr', '[0],[system],[mgr],', '添加用户', null, '/mgr/add', '1', '3', '0', null, '1', '0'),
  ('4', 'mgr_edit', 'mgr', '[0],[system],[mgr],', '修改用户', null, '/mgr/edit', '2', '3', '0', null, '1', '0'),
  ('5', 'mgr_delete', 'mgr', '[0],[system],[mgr],', '删除用户', null, '/mgr/delete', '3', '3', '0', null, '1', '0'),
  ('6', 'mgr_reset', 'mgr', '[0],[system],[mgr],', '重置密码', null, '/mgr/reset', '4', '3', '0', null, '1', '0'),
  ('7', 'mgr_freeze', 'mgr', '[0],[system],[mgr],', '冻结用户', null, '/mgr/freeze', '5', '3', '0', null, '1', '0'),
  ('8', 'mgr_unfreeze', 'mgr', '[0],[system],[mgr],', '解除冻结用户', null, '/mgr/unfreeze', '6', '3', '0', null, '1', '0'),
  ('9', 'mgr_setRole', 'mgr', '[0],[system],[mgr],', '分配角色', null, '/mgr/setRole', '7', '3', '0', null, '1', '0'),
  ('10', 'role', 'system', '[0],[system],', '角色管理', null, '/role', '2', '2', '1', null, '1', '0'),
  ('11', 'role_add', 'role', '[0],[system],[role],', '添加角色', null, '/role/add', '1', '3', '0', null, '1', '0'),
  ('12', 'role_edit', 'role', '[0],[system],[role],', '修改角色', null, '/role/edit', '2', '3', '0', null, '1', '0'),
  ('13', 'role_remove', 'role', '[0],[system],[role],', '删除角色', null, '/role/remove', '3', '3', '0', null, '1', '0'),
  ('14', 'role_setAuthority', 'role', '[0],[system],[role],', '配置权限', null, '/role/setAuthority', '4', '3', '0', null, '1', '0'),
  ('15', 'menu', 'system', '[0],[system],', '菜单管理', null, '/menu', '4', '2', '1', null, '1', '0'),
  ('16', 'menu_add', 'menu', '[0],[system],[menu],', '添加菜单', null, '/menu/add', '1', '3', '0', null, '1', '0'),
  ('17', 'menu_edit', 'menu', '[0],[system],[menu],', '修改菜单', null, '/menu/edit', '2', '3', '0', null, '1', '0'),
  ('18', 'menu_remove', 'menu', '[0],[system],[menu],', '删除菜单', null, '/menu/remove', '3', '3', '0', null, '1', '0'),
  ('19', 'log', 'system', '[0],[system],', '业务日志', null, '/log', '6', '2', '1', null, '1', '0'),
  ('20', 'druid', 'system', '[0],[system],', '监控管理', null, '/druid', '7', '2', '1', null, '1', null),
  ('21', 'dept', 'system', '[0],[system],', '部门管理', null, '/dept', '3', '2', '1', null, '1', null),
  ('22', 'dict', 'system', '[0],[system],', '字典管理', null, '/dict', '4', '2', '1', null, '1', null),
  ('23', 'loginLog', 'system', '[0],[system],', '登录日志', null, '/loginLog', '6', '2', '1', null, '1', null),
  ('24', 'log_clean', 'log', '[0],[system],[log],', '清空日志', null, '/log/delLog', '3', '3', '0', null, '1', null),
  ('25', 'dept_add', 'dept', '[0],[system],[dept],', '添加部门', null, '/dept/add', '1', '3', '0', null, '1', null),
  ('26', 'dept_update', 'dept', '[0],[system],[dept],', '修改部门', null, '/dept/update', '1', '3', '0', null, '1', null),
  ('27', 'dept_delete', 'dept', '[0],[system],[dept],', '删除部门', null, '/dept/delete', '1', '3', '0', null, '1', null),
  ('28', 'dict_add', 'dict', '[0],[system],[dict],', '添加字典', null, '/dict/add', '1', '3', '0', null, '1', null),
  ('29', 'dict_update', 'dict', '[0],[system],[dict],', '修改字典', null, '/dict/update', '1', '3', '0', null, '1', null),
  ('30', 'dict_delete', 'dict', '[0],[system],[dict],', '删除字典', null, '/dict/delete', '1', '3', '0', null, '1', null),
  ('31', 'notice', 'system', '[0],[system],', '通知管理', null, '/notice', '9', '2', '1', null, '1', null),
  ('32', 'notice_add', 'notice', '[0],[system],[notice],', '添加通知', null, '/notice/add', '1', '3', '0', null, '1', null),
  ('33', 'notice_update', 'notice', '[0],[system],[notice],', '修改通知', null, '/notice/update', '2', '3', '0', null, '1', null),
  ('34', 'notice_delete', 'notice', '[0],[system],[notice],', '删除通知', null, '/notice/delete', '3', '3', '0', null, '1', null),
  ('35', 'hello', '0', '[0],', '通知', 'fa-rocket', '/notice/hello', '1', '1', '1', null, '1', null),
  ('36', 'code', 'system', '[0],[system],', '代码生成', 'fa-user', '/code', '10', '2', '1', null, '1', null),
  ('37', 'api_mgr', '0', '[0],', '接口文档', 'fa-leaf', '/swagger-ui.html', '2', '1', '1', null, '1', null),
  ('38', 'to_menu_edit', 'menu', '[0],[system],[menu],', '菜单编辑跳转', '', '/menu/menu_edit', '4', '3', '0', null, '1', null),
  ('39', 'menu_list', 'menu', '[0],[system],[menu],', '菜单列表', '', '/menu/list', '5', '3', '0', null, '1', null),
  ('40', 'to_dept_update', 'dept', '[0],[system],[dept],', '修改部门跳转', '', '/dept/dept_update', '4', '3', '0', null, '1', null),
  ('41', 'dept_list', 'dept', '[0],[system],[dept],', '部门列表', '', '/dept/list', '5', '3', '0', null, '1', null),
  ('42', 'dept_detail', 'dept', '[0],[system],[dept],', '部门详情', '', '/dept/detail', '6', '3', '0', null, '1', null),
  ('43', 'to_dict_edit', 'dict', '[0],[system],[dict],', '修改菜单跳转', '', '/dict/dict_edit', '4', '3', '0', null, '1', null),
  ('44', 'dict_list', 'dict', '[0],[system],[dict],', '字典列表', '', '/dict/list', '5', '3', '0', null, '1', null),
  ('45', 'dict_detail', 'dict', '[0],[system],[dict],', '字典详情', '', '/dict/detail', '6', '3', '0', null, '1', null),
  ('46', 'log_list', 'log', '[0],[system],[log],', '日志列表', '', '/log/list', '2', '3', '0', null, '1', null),
  ('47', 'log_detail', 'log', '[0],[system],[log],', '日志详情', '', '/log/detail', '3', '3', '0', null, '1', null),
  ('48', 'del_login_log', 'loginLog', '[0],[system],[loginLog],', '清空登录日志', '', '/loginLog/delLoginLog', '1', '3', '0', null, '1', null),
  ('49', 'login_log_list', 'loginLog', '[0],[system],[loginLog],', '登录日志列表', '', '/loginLog/list', '2', '3', '0', null, '1', null),
  ('50', 'to_role_edit', 'role', '[0],[system],[role],', '修改角色跳转', '', '/role/role_edit', '5', '3', '0', null, '1', null),
  ('51', 'to_role_assign', 'role', '[0],[system],[role],', '角色分配跳转', '', '/role/role_assign', '6', '3', '0', null, '1', null),
  ('52', 'role_list', 'role', '[0],[system],[role],', '角色列表', '', '/role/list', '7', '3', '0', null, '1', null),
  ('53', 'to_assign_role', 'mgr', '[0],[system],[mgr],', '分配角色跳转', '', '/mgr/role_assign', '8', '3', '0', null, '1', null),
  ('54', 'to_user_edit', 'mgr', '[0],[system],[mgr],', '编辑用户跳转', '', '/mgr/user_edit', '9', '3', '0', null, '1', null),
  ('55', 'mgr_list', 'mgr', '[0],[system],[mgr],', '用户列表', '', '/mgr/list', '10', '3', '0', null, '1', null);

insert into `carloanfront`.`menu` ( `id`, `ismenu`, `url`, `code`, `tips`, `levels`, `num`, `pcode`, `icon`, `isopen`, `pcodes`, `name`, `status`) values ( '56', '1', '/', 'orderManage', null, '1', '6', '0', 'fa-user', null, '[0],', '订单管理', '1');
insert into `carloanfront`.`menu` ( `id`, `ismenu`, `url`, `code`, `tips`, `levels`, `num`, `pcode`, `icon`, `isopen`, `pcodes`, `name`, `status`) values ( '57', '1', '/cust/custNqDataAddPage', 'custNqDataAdd', null, '2', '1', 'orderManage', '', null, '[0],[orderManage],', '内勤资料录入', '1');
insert into `carloanfront`.`menu` ( `id`, `ismenu`, `url`, `code`, `tips`, `levels`, `num`, `pcode`, `icon`, `isopen`, `pcodes`, `name`, `status`) values ( '58', '1', '/', 'channelManage', null, '1', '7', '0', 'fa-user', null, '[0],', '渠道管理', '1');
insert into `carloanfront`.`menu` ( `id`, `ismenu`, `url`, `code`, `tips`, `levels`, `num`, `pcode`, `icon`, `isopen`, `pcodes`, `name`, `status`) values ( '59', '1', '/cust/channelInfo/getPage', 'getChannelPage', null, '2', '1', 'channelManage', '', null, '[0],[channelManage],', '渠道列表', '1');
insert into `carloanfront`.`menu` ( `id`, `ismenu`, `url`, `code`, `tips`, `levels`, `num`, `pcode`, `icon`, `isopen`, `pcodes`, `name`, `status`) values ( '60', '0', '/cust/channelInfo/add', '/cust/channelInfo/add', null, '3', '1', 'getChannelPage', '', null, '[0],[channelManage],[getChannelPage],', '新增渠道', '1');
insert into `carloanfront`.`menu` ( `id`, `ismenu`, `url`, `code`, `tips`, `levels`, `num`, `pcode`, `icon`, `isopen`, `pcodes`, `name`, `status`) values ( '61', '0', '/cust/channelInfo/edit', '/cust/channelInfo/edit', null, '3', '2', 'getChannelPage', '', null, '[0],[channelManage],[getChannelPage],', '修改渠道', '1');
insert into `carloanfront`.`menu` ( `id`, `ismenu`, `url`, `code`, `tips`, `levels`, `num`, `pcode`, `icon`, `isopen`, `pcodes`, `name`, `status`) values ( '62', '0', '/cust/channelInfo/remove', '/cust/channelInfo/remove', null, '3', '3', 'getChannelPage', '', null, '[0],[channelManage],[getChannelPage],', '删除渠道', '1');
insert into `carloanfront`.`menu` ( `id`, `ismenu`, `url`, `code`, `tips`, `levels`, `num`, `pcode`, `icon`, `isopen`, `pcodes`, `name`, `status`) values ( '63', '1', '/custAdd/getPage', 'custAddGetPage', null, '2', '1', 'orderManage', '', null, '[0],[orderManage],', '客户新增', '1');
insert into `carloanfront`.`menu` ( `id`, `ismenu`, `url`, `code`, `tips`, `levels`, `num`, `pcode`, `icon`, `isopen`, `pcodes`, `name`, `status`) values ( '64', '1', '/loanApply/getPage', 'loanApplyList', null, '2', '2', 'orderManage', '', null, '[0],[orderManage],', '订单列表', '1');
insert into `carloanfront`.`menu` ( `id`, `ismenu`, `url`, `code`, `tips`, `levels`, `num`, `pcode`, `icon`, `isopen`, `pcodes`, `name`, `status`) values ( '65', '1', '/', 'myWork', null, '1', '1', '0', 'fa-user', null, '[0],', '我的工作台', '1');
insert into `carloanfront`.`menu` ( `id`, `ismenu`, `url`, `code`, `tips`, `levels`, `num`, `pcode`, `icon`, `isopen`, `pcodes`, `name`, `status`) values ( '66', '1', '/myWork/todoWorkListPage', 'myTodoWorkList', null, '2', '1', 'myWork', '', null, '[0],[myWork],', '待办列表', '1');
COMMIT;


-- ----------------------------
--  Table structure for `order_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `order_sequence`;
CREATE TABLE `order_sequence` (
  `seq_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `order_type` bigint(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`seq_id`)
) ENGINE=InnoDB AUTO_INCREMENT=706 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `relation`
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menuid` int(11) DEFAULT NULL COMMENT '菜单id',
  `roleid` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5657 DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
--  Records of `relation`
-- ----------------------------
BEGIN;
INSERT INTO `relation` VALUES
  ('1', '1', '1'),  ('2', '2', '1'),  ('3', '3', '1'),
  ('4', '4', '1'),  ('5', '5', '1'),  ('6', '6', '1'),
  ('7', '7', '1'),  ('8', '8', '1'),  ('9', '9', '1'),
  ('10', '10', '1'),  ('11', '11', '1'),  ('12', '12', '1'),
  ('13', '13', '1'),  ('14', '14', '1'),  ('15', '15', '1'),
  ('16', '16', '1'),  ('17', '17', '1'),  ('18', '18', '1'),
  ('19', '19', '1'),  ('20', '20', '1'),  ('21', '21', '1'),
  ('22', '22', '1'),  ('23', '23', '1'),  ('24', '24', '1'),
  ('25', '25', '1'),  ('26', '26', '1'),  ('27', '27', '1'),
  ('28', '28', '1'),  ('29', '29', '1'),  ('30', '30', '1'),
  ('31', '31', '1'),  ('32', '32', '1'),  ('33', '33', '1'),
  ('34', '34', '1'),  ('35', '35', '1'),  ('36', '36', '1'),
  ('37', '37', '1'),  ('38', '38', '1'),  ('39', '39', '1'),
  ('40', '40', '1'),  ('41', '41', '1'),  ('42', '42', '1'),
  ('43', '43', '1'),  ('44', '44', '1'),  ('45', '45', '1'),
  ('46', '46', '1'),  ('47', '47', '1'),  ('48', '48', '1'),
  ('49', '49', '1'),  ('50', '50', '1'),  ('51', '51', '1'),
  ('52', '52', '1'),  ('53', '53', '1'),  ('54', '54', '1'),
  ('55', '55', '1'),  ('56', '56', '1'),  ('57', '57', '1'),
  ('58', '58', '1'),  ('59', '59', '1'),  ('60', '60', '1'),
  ('61', '61', '1'),  ('62', '62', '1'),  ('63', '63', '1'),
  ('64', '64', '1'),  ('65', '65', '1'),  ('66', '66', '1');
COMMIT;


-- ----------------------------
--  Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `num` int(11) DEFAULT NULL COMMENT '序号',
  `pid` int(11) DEFAULT NULL COMMENT '父角色id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `deptid` int(11) DEFAULT NULL COMMENT '部门名称',
  `tips` varchar(255) DEFAULT NULL COMMENT '提示',
  `version` int(11) DEFAULT NULL COMMENT '保留字段(暂时没用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
--  Records of `role`
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES
  ('1', '1', '0', 'administrator', '1', '超级管理员', '1'),
  ('2', '2', '1', '内勤主管', '2', '内勤主管', null),
  ('3', '3', '2', '内勤', '2', '内勤', null),
  ('4', '4', '1', '验车师', '2', '验车师', null),
  ('5', '5', '1', '面审主管', '2', '面审主管', null),
  ('6', '6', '5', '面审', '2', '面审', null),
  ('7', '7', '1', '终审', '2', '终审', null),
  ('8', '8', '1', '抵押专员', '2', '抵押专员', null),
  ('9', '9', '1', '业务经理', '2', '业务经理', null),
  ('10', '10', '1', '财务经理', '2', '财务经理', null),
  ('11', '11', '10', '财务', '2', '财务', null),
  ('12', '12', '1', '前台财务', '1', '前台财务', null);
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `account` varchar(45) DEFAULT NULL COMMENT '账号',
  `password` varchar(45) DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(45) DEFAULT NULL COMMENT '名字',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` int(11) DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `roleid` varchar(255) DEFAULT NULL COMMENT '角色id',
  `deptid` varchar(255) DEFAULT NULL COMMENT '部门id',
  `status` int(11) DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES
  ('1', 'girl.gif', 'admin', 'ecfadcde9305f8891bcfe5a1e28c253e', '8pgby', '超级管理员', '2017-05-05 00:00:00', '2', 'sn93@qq.com', '18200000000', '1', '1', '1', '2016-01-29 08:49:53', '25');
COMMIT;


-- ----------------------------
--  Table structure for `login_log`
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logname` varchar(255) DEFAULT NULL COMMENT '日志名称',
  `userid` int(65) DEFAULT NULL COMMENT '管理员id',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) DEFAULT NULL COMMENT '是否执行成功',
  `message` text COMMENT '具体消息',
  `ip` varchar(255) DEFAULT NULL COMMENT '登录ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=352 DEFAULT CHARSET=utf8 COMMENT='登录记录';


-- ----------------------------
--  Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `content` text COMMENT '内容',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` int(11) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='通知表';

-- ----------------------------
--  Records of `notice`
-- ----------------------------
BEGIN;
INSERT INTO `notice` VALUES
  ('6', '世界', '10', '欢迎使用CIS', '2017-01-11 08:53:20', '1'),
  ('8', '你好', null, '你好', '2017-05-10 19:28:57', '1');
COMMIT;

-- ----------------------------
--  Table structure for `operation_log`
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log` (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `logtype` varchar(255) DEFAULT NULL COMMENT '日志类型',
  `logname` varchar(255) DEFAULT NULL COMMENT '日志名称',
  `userid` int(65) DEFAULT NULL COMMENT '用户id',
  `classname` varchar(255) DEFAULT NULL COMMENT '类名称',
  `method` text COMMENT '方法名称',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) DEFAULT NULL COMMENT '是否成功',
  `message` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=617 DEFAULT CHARSET=utf8 COMMENT='操作日志';



SET FOREIGN_KEY_CHECKS = 1;