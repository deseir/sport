/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost
 Source Database       : sportQc

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : utf-8

 Date: 10/21/2018 22:43:13 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `c_apply_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_apply_info`;
CREATE TABLE `c_apply_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户ID',
  `channel_id` bigint(21) DEFAULT NULL COMMENT '客户渠道ID',
  `product_type` int(11) DEFAULT '0' COMMENT '产品类型 0--抵押 1--质押',
  `apply_amount` decimal(10,2) DEFAULT NULL COMMENT '申请额度 单位(元)',
  `left_amount` decimal(10,2) DEFAULT NULL,
  `apply_period` int(11) DEFAULT '36' COMMENT '申请期限  12/24/36',
  `repayment_type` int(11) DEFAULT '0' COMMENT '还款方式 0--等额本息 1--先息后本',
  `loan_usage` int(11) DEFAULT NULL COMMENT '还款用途 1--教育支出 2--医疗 4--生意周转 8--装修 16--其他（可多选）',
  `loan_usage_other` varchar(128) DEFAULT NULL COMMENT '还款用途其他情况说明',
  `partner_know` int(11) DEFAULT NULL COMMENT '0--未婚 1--离异 2--丧偶 3--已婚不知晓 4--已婚知晓不能签字 5--已婚知晓可签字',
  `dept_id` bigint(21) DEFAULT NULL COMMENT '所属部门',
  `status` int(11) DEFAULT NULL COMMENT '主流程状态',
  `status_desc` varchar(128) DEFAULT NULL COMMENT '主流程状态描述',
  `sfrz_status` int(11) DEFAULT NULL COMMENT '身份认证',
  `nqlr_status` int(11) DEFAULT '0' COMMENT '内勤资料录入状态 0--未提交 1--已提交',
  `yc_status` int(11) DEFAULT '0' COMMENT '验车师验车状态 0--未提交 1--已提交',
  `gps_install_status` int(11) DEFAULT '0' COMMENT '安装GPS流程状态  0--未开始 1--发起安装申请 2--安装GPS成功 3--确认安装成功',
  `gps_uninstall_status` int(11) DEFAULT '0' COMMENT '拆卸GPS流程状态 0--未开始 1--发起拆卸申请 2--拆卸GPS成功',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-未删除 ；1-已删除；2-已拒绝',
  `isCollection` int(1) DEFAULT NULL,
  `serviceItem` int(1) DEFAULT NULL,
  `percent` decimal(10,2) DEFAULT NULL,
  `moneyAmount` decimal(10,2) DEFAULT NULL,
  `serviceCharge` decimal(10,2) DEFAULT NULL,
  `feeInstallment` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `INX_CUST_ID` (`cust_id`),
  KEY `INX_CHANNEL_ID` (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
--  Table structure for `c_apply_operator`
-- ----------------------------
DROP TABLE IF EXISTS `c_apply_operator`;
CREATE TABLE `c_apply_operator` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款单ID',
  `role_id` bigint(21) DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint(21) DEFAULT NULL COMMENT '用户ID',
  `process_node_id` bigint(21) DEFAULT NULL COMMENT '流程节点ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`),
  KEY `INX_APPLY_ID` (`apply_id`),
  KEY `INX_ROLE_ID` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单操作员表';

-- ----------------------------
--  Table structure for `c_bankcard_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_bankcard_info`;
CREATE TABLE `c_bankcard_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `card_no` varchar(32) NOT NULL COMMENT '银行卡号',
  `bank_name` varchar(30) DEFAULT NULL COMMENT '银行名',
  `province_city` varchar(50) DEFAULT NULL COMMENT '开户省市',
  `bank_subbranch` varchar(100) DEFAULT NULL COMMENT '开户支行',
  `card_status` int(3) DEFAULT '0' COMMENT '银行卡状态 0--失败 1--成功 2--创建 3--发送验证码成功 4--发送验证码失败 5--验证失败',
  `binding_time` datetime DEFAULT NULL COMMENT '绑卡时间',
  `binding_desc` varchar(50) DEFAULT NULL COMMENT '绑卡描述',
  `sequence_no` varchar(50) DEFAULT NULL COMMENT '流水号',
  `is_default_card` int(3) DEFAULT '0' COMMENT '是否是默认银行卡',
  `binding_mobile` varchar(50) DEFAULT NULL COMMENT '银行预留手机号',
  `pay_platform` varchar(50) DEFAULT NULL COMMENT '支付平台',
  `result_code` varchar(20) DEFAULT NULL COMMENT '支付平台返回码',
  `result_msg` varchar(50) DEFAULT NULL COMMENT '支付平台返回消息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`cust_id`),
  KEY `index_card_no` (`card_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户银行卡绑定信息';

-- ----------------------------
--  Table structure for `c_car_base_params`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_base_params`;
CREATE TABLE `c_car_base_params` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `base_config` varchar(2) DEFAULT NULL COMMENT '基本参数配置 1-标配 2-选配 3-无',
  `productFactory` varchar(255) DEFAULT NULL COMMENT '厂商',
  `jibie` varchar(50) DEFAULT NULL COMMENT '级别',
  `engine` varchar(100) DEFAULT NULL COMMENT '发动机',
  `bsx` varchar(50) DEFAULT NULL COMMENT '变速箱',
  `ckg` varchar(50) DEFAULT NULL COMMENT '长宽高',
  `csxs` varchar(100) DEFAULT NULL COMMENT '车身型式',
  `highspeed` varchar(10) DEFAULT NULL COMMENT '最高速度',
  `rlxs` varchar(50) DEFAULT NULL COMMENT '燃料形式',
  `gxbzhyh` varchar(10) DEFAULT NULL COMMENT '工信部综合油耗',
  `outcolor` varchar(255) DEFAULT NULL COMMENT '外部颜色',
  `innercolor` varchar(255) DEFAULT NULL COMMENT '内饰颜色',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`),
  KEY `INX_CAR_ID` (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆详细配置_基本参数表';

-- ----------------------------
--  Table structure for `c_car_body_config`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_body_config`;
CREATE TABLE `c_car_body_config` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `carbody_config` varchar(2) DEFAULT NULL COMMENT '车身配置 1-标配 2-选配 3-无',
  `zj` varchar(10) DEFAULT NULL COMMENT '轴距(mm)',
  `qlj` varchar(10) DEFAULT NULL COMMENT '前轮距(mm)',
  `hlj` varchar(10) DEFAULT NULL COMMENT '后轮距(mm)',
  `minldjx` varchar(10) DEFAULT NULL COMMENT '最小离地间隙',
  `zczl` varchar(10) DEFAULT NULL COMMENT '整车质量(kg)',
  `csjg` varchar(100) DEFAULT NULL COMMENT '车身结构',
  `yxrl` varchar(10) DEFAULT NULL COMMENT '邮箱容量(L)',
  `xlxrl` varchar(50) DEFAULT NULL COMMENT '行李箱容量(L)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`),
  KEY `INX_CAR_ID` (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆详细配置_车身配置';

-- ----------------------------
--  Table structure for `c_car_buss_insure_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_buss_insure_info`;
CREATE TABLE `c_car_buss_insure_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `inst_full_name` varchar(255) DEFAULT NULL COMMENT '购买机构全称',
  `bussiness_source` varchar(128) DEFAULT NULL COMMENT '业务来源',
  `proxy_name` varchar(255) DEFAULT NULL COMMENT '代理点名称',
  `insure_number` varchar(128) DEFAULT NULL COMMENT '保单号',
  `insure_person` varchar(64) DEFAULT NULL COMMENT '被保人',
  `insure_begin_time` datetime DEFAULT NULL COMMENT '保险起始日期',
  `insure_end_time` datetime DEFAULT NULL COMMENT '保险到期日期',
  `float_prop` decimal(10,4) DEFAULT NULL COMMENT '浮动比例',
  `total_amount` decimal(12,2) DEFAULT NULL COMMENT '保费合计',
  `sign_date` datetime DEFAULT NULL COMMENT '签约日期',
  `special_agreement` varchar(512) DEFAULT NULL COMMENT '特别约定',
  `photo_url` varchar(255) DEFAULT NULL COMMENT '照片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `INX_UID` (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆商业险信息表';

-- ----------------------------
--  Table structure for `c_car_buss_mortgage_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_buss_mortgage_info`;
CREATE TABLE `c_car_buss_mortgage_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `cust_name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `cust_sex` int(11) DEFAULT NULL COMMENT '性别 0--女 1--男',
  `cust_id_no` varchar(32) DEFAULT NULL COMMENT '身份证号',
  `register_photo_url1` varchar(255) DEFAULT NULL COMMENT '车辆登记证书1-2页照片地址',
  `register_photo_url2` varchar(255) DEFAULT NULL COMMENT '车辆登记证书3-4页照片地址',
  `register_photo_url3` varchar(255) DEFAULT NULL COMMENT '车辆登记证书5-6页照片地址',
  `register_photo_url4` varchar(255) DEFAULT NULL COMMENT '车辆登记证书7-8页照片地址',
  `proxy_book_url` varchar(255) DEFAULT NULL COMMENT '委托书（客户签字授权）照片地址',
  `mortgage_contract_url` varchar(255) DEFAULT NULL COMMENT '抵押合同(车管所）照片地址',
  `cert_photo_url` varchar(255) DEFAULT NULL COMMENT '身份证复印件照片地址',
  `recv_confirm` int(11) DEFAULT '0' COMMENT '接收资料确认 0--否 1--是 2--驳回',
  `recv_confirm_date` datetime DEFAULT NULL COMMENT '接收资料确认时间',
  `refuse_reason` varchar(255) DEFAULT NULL COMMENT '驳回原因',
  `accept_confirm` int(11) DEFAULT '0' COMMENT '抵押是否受理 0--否 1--是',
  `accept_confirm_date` datetime DEFAULT NULL COMMENT '抵押受理时间',
  `bill_attach_url` varchar(255) DEFAULT NULL COMMENT '抵押受理小票附件',
  `complete_confirm` int(11) DEFAULT '0' COMMENT '抵押业务是否完成 0--否 1--是',
  `register_photo_url5` varchar(255) DEFAULT NULL COMMENT '抵押完成后车辆登记证书1-2页照片地址',
  `register_photo_url6` varchar(255) DEFAULT NULL COMMENT '抵押完成后车辆登记证书3-4页照片地址',
  `register_photo_url7` varchar(255) DEFAULT NULL COMMENT '抵押完成后车辆登记证书5-6页照片地址',
  `register_photo_url8` varchar(255) DEFAULT NULL COMMENT '抵押完成后车辆登记证书7-8页照片地址',
  `complete_confirm_date` datetime DEFAULT NULL COMMENT '抵押业务完成时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_car_id` (`car_id`),
  KEY `index_apply_id` (`apply_id`),
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆抵押登记信息表';

-- ----------------------------
--  Table structure for `c_car_chassis_steering`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_chassis_steering`;
CREATE TABLE `c_car_chassis_steering` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `apply_id` bigint(21) NOT NULL COMMENT '订单ID',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `car_chassis_steering` varchar(255) DEFAULT NULL COMMENT '底盘转向 1标配 2选配 3无',
  `driving_model` varchar(255) DEFAULT NULL COMMENT '驱动方式',
  `front_suspension` varchar(255) DEFAULT NULL COMMENT '前悬架类型',
  `rear_suspension` varchar(255) DEFAULT NULL COMMENT '后悬架类型',
  `power_steering` varchar(255) DEFAULT NULL COMMENT '助力类型',
  `car_body_structure` varchar(255) DEFAULT NULL COMMENT '车体结构',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `c_car_detention_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_detention_info`;
CREATE TABLE `c_car_detention_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `cust_name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `cust_sex` int(11) DEFAULT NULL COMMENT '性别 0--女 1--男',
  `cust_id_no` varchar(32) DEFAULT NULL COMMENT '身份证号',
  `is_settle` int(11) DEFAULT '0' COMMENT '财务是否结清 0--否 1--是',
  `settle_attach_url` varchar(255) DEFAULT NULL COMMENT '财务结清附件地址',
  `settle_date` datetime DEFAULT NULL COMMENT '财务结清时间',
  `settle_confirem` int(11) DEFAULT '0' COMMENT '内勤结清确认 0--否 1--确认',
  `proxy_book_url` varchar(255) DEFAULT NULL COMMENT '委托书（客户签字授权）照片地址',
  `cert_photo_url` varchar(255) DEFAULT NULL COMMENT '身份证复印件照片地址',
  `recv_confirm` int(11) DEFAULT '0' COMMENT '接收资料确认 0--否 1--是 2--驳回',
  `recv_confirm_date` datetime DEFAULT NULL COMMENT '接收资料确认时间',
  `refuse_reason` varchar(255) DEFAULT NULL COMMENT '驳回原因',
  `accept_confirm` int(11) DEFAULT '0' COMMENT '解押是否受理 0--否 1--是',
  `accept_confirm_date` datetime DEFAULT NULL COMMENT '解押受理时间',
  `bill_attach_url` varchar(255) DEFAULT NULL COMMENT '解押受理小票附件',
  `complete_confirm` int(11) DEFAULT '0' COMMENT '解押业务是否完成 0--否 1--是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_car_id` (`car_id`),
  KEY `index_apply_id` (`apply_id`),
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆解押登记信息表';

-- ----------------------------
--  Table structure for `c_car_driver_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_driver_info`;
CREATE TABLE `c_car_driver_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(21) NOT NULL COMMENT '用户id',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `vehicle_valid_date` datetime DEFAULT NULL COMMENT '行驶证年检有效期',
  `vehicle_front_photo` varchar(255) DEFAULT NULL COMMENT '行驶证正面照片',
  `vehicle_back_photo` varchar(255) DEFAULT NULL COMMENT '行驶证背面照片',
  `is_driver_lic` int(11) DEFAULT NULL COMMENT '是否有驾驶证 0--无 1--有',
  `driver_no` varchar(32) DEFAULT NULL COMMENT '驾驶证档案编号',
  `first_driver_date` datetime DEFAULT NULL COMMENT '初次领取驾驶证时间',
  `permit_type` varchar(32) DEFAULT NULL COMMENT '准驾车型',
  `driver_begin_date` datetime DEFAULT NULL COMMENT '驾驶证有效期开始时间',
  `driver_end_date` datetime DEFAULT NULL COMMENT '驾驶证有效期结束时间',
  `is_self` int(11) DEFAULT NULL COMMENT '是否申请人持有 0--否 1--是',
  `driver_relation` varchar(32) DEFAULT NULL COMMENT '持有人与申请人关系',
  `driver_remark` varchar(255) DEFAULT NULL COMMENT '持有人备注',
  `driver_front_photo` varchar(255) DEFAULT NULL COMMENT '驾驶证正面照片',
  `driver_back_photo` varchar(255) DEFAULT NULL COMMENT '驾驶证背面照片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`),
  KEY `INX_USER_ID` (`user_id`),
  KEY `INX_CAR_ID` (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='行驶证驾驶证信息表';

-- ----------------------------
--  Table structure for `c_car_engine_config`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_engine_config`;
CREATE TABLE `c_car_engine_config` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `carengine_config` varchar(15) DEFAULT NULL COMMENT '发动机配置 1-标配 2-选配 3-无',
  `fdjxh` varchar(10) DEFAULT NULL COMMENT '发动机型号',
  `pailiang` varchar(10) DEFAULT NULL COMMENT '排量(ml)',
  `jqxs` varchar(100) DEFAULT NULL COMMENT '进气形式',
  `qgplxs` varchar(100) DEFAULT NULL COMMENT '气缸排列形式',
  `qggs` varchar(5) DEFAULT NULL COMMENT '气缸个数',
  `mgqms` varchar(5) DEFAULT NULL COMMENT '每缸气门数',
  `pqjg` varchar(50) DEFAULT NULL COMMENT '配气机构',
  `gangjing` varchar(50) DEFAULT NULL COMMENT '缸径(mm)',
  `maxml` varchar(10) DEFAULT NULL COMMENT '最大马力',
  `maxgl` varchar(10) DEFAULT NULL COMMENT '最大功率',
  `maxglzs` varchar(10) DEFAULT NULL COMMENT '最大功率转速',
  `maxnz` varchar(50) DEFAULT NULL COMMENT '最大扭转',
  `maxnzzs` varchar(50) DEFAULT NULL COMMENT '最大扭转转速',
  `fdjtyjs` varchar(100) DEFAULT NULL COMMENT '发动机特有技术',
  `rlxs` varchar(50) DEFAULT NULL COMMENT '燃料形式',
  `rybh` varchar(50) DEFAULT NULL COMMENT '燃油标号',
  `gyfs` varchar(50) DEFAULT NULL COMMENT '供油方式',
  `ggcl` varchar(50) DEFAULT NULL COMMENT '缸盖材料',
  `gtcl` varchar(50) DEFAULT NULL COMMENT '缸体材料',
  `hbcl` varchar(50) DEFAULT NULL COMMENT '环保材料',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`),
  KEY `INX_CAR_ID` (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆详细配置_车身配置';

-- ----------------------------
--  Table structure for `c_car_gear_box`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_gear_box`;
CREATE TABLE `c_car_gear_box` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `apply_id` bigint(21) NOT NULL COMMENT '订单id',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `car_gear_box` int(1) NOT NULL COMMENT '变速箱配置（1:标配2:选配3：无）',
  `car_gear_num` int(4) DEFAULT NULL COMMENT '档位个数',
  `car_gear_type` varchar(255) DEFAULT NULL COMMENT '变速箱类型',
  `car_gear_desc` varchar(255) DEFAULT NULL COMMENT '变速箱描述',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT '0' COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆详细配置_变速箱配置';

-- ----------------------------
--  Table structure for `c_car_gps_detail_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_gps_detail_info`;
CREATE TABLE `c_car_gps_detail_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `is_wiredless` int(11) DEFAULT NULL COMMENT '是否无线 0--否 1--是 2--其他',
  `gps_wired_no` varchar(255) DEFAULT NULL COMMENT 'GPS串码',
  `gps_photo_url` varchar(255) DEFAULT NULL COMMENT 'GPS安装位置图片地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_car_id` (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆GPS详情信息表';

-- ----------------------------
--  Table structure for `c_car_gps_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_gps_info`;
CREATE TABLE `c_car_gps_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `gps_install_date` datetime DEFAULT NULL COMMENT '车辆GPS安装时间 YYYYMMDD',
  `group_photo_url` varchar(255) DEFAULT NULL COMMENT '人车合影图片地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_car_id` (`car_id`),
  KEY `index_apply_id` (`apply_id`),
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆GPS信息表';

-- ----------------------------
--  Table structure for `c_car_gps_uninstall_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_gps_uninstall_info`;
CREATE TABLE `c_car_gps_uninstall_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `bigclass_id` int(11) DEFAULT NULL COMMENT '图片类型id',
  `photo_name` varchar(255) DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL COMMENT 'GPS卸载图片地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_car_id` (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆卸载GPS详情信息表';

-- ----------------------------
--  Table structure for `c_car_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_info`;
CREATE TABLE `c_car_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `car_num` varchar(32) DEFAULT NULL COMMENT '车牌号',
  `car_config_name` varchar(255) DEFAULT NULL COMMENT '车辆通俗配置名称',
  `car_type` varchar(64) DEFAULT NULL COMMENT '车辆类型 小型轿车 小型越野客车',
  `car_brand` varchar(64) DEFAULT NULL COMMENT '车辆品牌',
  `car_model` varchar(64) DEFAULT NULL COMMENT '车辆型号',
  `vin` varchar(64) DEFAULT NULL COMMENT '车辆识别号',
  `engine_no` varchar(64) DEFAULT NULL COMMENT '发动机号',
  `car_color` varchar(64) DEFAULT NULL COMMENT '车身颜色',
  `car_import_type` int(1) DEFAULT NULL COMMENT '国产/进口  0-国产  1-进口',
  `fuel_type` int(1) DEFAULT NULL COMMENT '燃料种类 0-汽油  1-柴油  2-油电混合  3-纯电动 4--其他',
  `mileage` int(11) DEFAULT NULL,
  `displacement` int(11) DEFAULT NULL COMMENT '排量 （单位：ml)',
  `manufacturer` varchar(128) DEFAULT NULL COMMENT '制造厂名称',
  `car_usage` int(1) DEFAULT NULL COMMENT '使用性质 0-非运营   1-运营   2-营转非',
  `get_type` int(1) DEFAULT NULL COMMENT '车辆获得方式 0-自购  2-赠与',
  `product_date` datetime DEFAULT NULL COMMENT '出厂日期 YYYYMMDD',
  `first_lic_date` datetime DEFAULT NULL COMMENT '首次上牌日期 YYYYMMDD',
  `current_lic_date` datetime DEFAULT NULL COMMENT '本次上牌日期 YYYYMMDD',
  `register_photo_url1` varchar(255) DEFAULT NULL COMMENT '车辆登记证书1-2页照片地址',
  `register_photo_url2` varchar(255) DEFAULT NULL COMMENT '车辆登记证书3-4页照片地址',
  `register_photo_url3` varchar(255) DEFAULT NULL COMMENT '车辆登记证书5-6页照片地址',
  `register_photo_url4` varchar(255) DEFAULT NULL COMMENT '车辆登记证书7-8页照片地址',
  `carLocationId` varchar(255) DEFAULT '' COMMENT '车辆所在地省id',
  `carLocationName` varchar(255) DEFAULT '' COMMENT '车辆所在地省name',
  `carCityId` varchar(255) DEFAULT NULL COMMENT '车辆所在地城市id',
  `carCityName` varchar(255) DEFAULT NULL COMMENT '车辆所在地城市name',
  `carSeriesId` varchar(255) DEFAULT NULL COMMENT '车系id',
  `carSeriesName` varchar(255) DEFAULT NULL COMMENT '车系name',
  `carModelId` varchar(255) DEFAULT NULL COMMENT '车型id',
  `carModelName` varchar(255) DEFAULT NULL COMMENT '车型name',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `registrationCarType` varchar(255) DEFAULT NULL COMMENT '登记证书车辆型号',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`),
  KEY `INX_CUST_ID` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆基本信息表';

-- ----------------------------
--  Table structure for `c_car_insure_detail_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_insure_detail_info`;
CREATE TABLE `c_car_insure_detail_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `insure_id` bigint(21) NOT NULL COMMENT '保险id',
  `type` int(11) DEFAULT NULL COMMENT '险种 0--车辆损失险 1--第三者责任险 2--车上人员责任险：司机 3--车上人员责任险：乘客 4--车身划痕险 5--涉水险 6--自燃损失险 7--玻璃单独破碎险（国产） 8--玻璃单独破碎险（进口） ',
  `is_no_deduct` int(11) DEFAULT NULL COMMENT '是否有不计免赔 0--否 1--是',
  `max_pay_amount` decimal(12,2) DEFAULT NULL COMMENT '最高赔付金额',
  `amount` decimal(12,2) DEFAULT NULL COMMENT '保费金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `INX_UID` (`insure_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆保险险种详细信息表';

-- ----------------------------
--  Table structure for `c_car_mortgage_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_mortgage_info`;
CREATE TABLE `c_car_mortgage_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `name` varchar(32) DEFAULT NULL COMMENT '抵押姓名',
  `cert_id` varchar(32) DEFAULT NULL COMMENT '抵押身份证号',
  `reg_date` datetime DEFAULT NULL COMMENT '抵押日期',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`),
  KEY `INX_CAR_ID` (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆抵押记录表';

-- ----------------------------
--  Table structure for `c_car_operation_configuration`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_operation_configuration`;
CREATE TABLE `c_car_operation_configuration` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `apply_id` bigint(21) NOT NULL COMMENT '订单id',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `car_operation_configuration` int(1) NOT NULL COMMENT '操作配置  1标配 2选配 3无',
  `car_front_parking_radar` int(1) DEFAULT NULL COMMENT '前驻车雷达0:无 1:有',
  `car_rear_parking_radar` int(1) DEFAULT NULL COMMENT '后驻车雷达0:无 1:有',
  `car_reversing_video` int(1) DEFAULT NULL COMMENT '倒车视频影像0:无 1:有',
  `car_panoramic_camera` int(1) DEFAULT NULL COMMENT '全景摄像头0:无 1:有',
  `car_cruise_control` int(1) DEFAULT NULL COMMENT '定速巡航0:无 1:有',
  `car_adaptive_cruise` int(1) DEFAULT NULL COMMENT '自适应巡航0:无 1:有',
  `car_auto_matic_park` int(1) DEFAULT NULL COMMENT '自动泊车入位0:无 1:有',
  `car_motor_start_end` int(1) DEFAULT NULL COMMENT '发动机启停技术0:无 1:有',
  `car_hill_start_assist` int(1) DEFAULT NULL COMMENT '上坡辅助0:无 1:有',
  `car_auto_parking` int(1) DEFAULT NULL COMMENT '自动驻车0:无 1:有',
  `car_hdc` int(1) DEFAULT NULL COMMENT '陡坡缓降0:无 1:有',
  `car_variable_supension` int(1) DEFAULT NULL COMMENT '可变悬架0:无 1:有',
  `car_air_supension` int(1) DEFAULT NULL COMMENT '空气悬架0:无 1:有',
  `car_variable_steering` int(1) DEFAULT NULL COMMENT '可变转向比0:无 1:有',
  `car_active_dynamic` int(1) DEFAULT NULL COMMENT '整体主动转向系统0:无 1:有',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `c_car_peccancy_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_peccancy_info`;
CREATE TABLE `c_car_peccancy_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `total_num` int(11) DEFAULT NULL COMMENT '累计违章次数',
  `total_money` decimal(10,2) DEFAULT NULL COMMENT '累计违章罚款',
  `total_value` int(11) DEFAULT NULL COMMENT '累计扣分',
  `total_full_num` int(11) DEFAULT NULL COMMENT '累计扣12分次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  `violationAttachmentPhotol` varchar(255) DEFAULT NULL,
  `violationAttachmentPhotol2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `INX_CAR_ID` (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆违章信息表';

-- ----------------------------
--  Table structure for `c_car_photo_bigclass_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_photo_bigclass_info`;
CREATE TABLE `c_car_photo_bigclass_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `class_name` varchar(128) DEFAULT NULL COMMENT '大类名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  `car_id` bigint(21) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `INX_CAR_BIGCLASS_ID` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆照片大类表';

-- ----------------------------
--  Table structure for `c_car_photo_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_photo_info`;
CREATE TABLE `c_car_photo_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `bigclass_id` bigint(21) DEFAULT NULL COMMENT '图片大类id',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `photo_name` varchar(128) DEFAULT NULL COMMENT '图片名称',
  `photo_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`),
  KEY `INX_CAR_ID` (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆照片表';

-- ----------------------------
--  Table structure for `c_car_price_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_price_info`;
CREATE TABLE `c_car_price_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `che300_price` decimal(10,2) DEFAULT NULL COMMENT '车300评估价',
  `che300_thprice` decimal(10,2) DEFAULT NULL COMMENT '车300同行交易价(批发价)元',
  `che300_attach_url` varchar(255) DEFAULT NULL COMMENT '车300评估价附件地址',
  `jingzhengu_price` decimal(10,2) DEFAULT NULL COMMENT '精真估评估价',
  `jingzhengu_attach_url` varchar(255) DEFAULT NULL COMMENT '精真估评估价附件地址',
  `nake_price` decimal(10,2) DEFAULT NULL COMMENT '裸车价',
  `depreciation_base` int(11) DEFAULT NULL COMMENT '折旧基数 (车龄月数 申请年月日减首次上户年月日 不足1月的按1月算）',
  `depreciation_ratio` decimal(10,4) DEFAULT NULL COMMENT '折旧系数 0--6‰(千分之6) 每月包括按揭房、抵押房、全款房、寿险保单、公积金  1--8‰（千分之8) 每月包括流水结息、他行车贷、打卡工资； ',
  `credit_ratio` decimal(10,4) DEFAULT NULL COMMENT '授信成数',
  `tsingnuo_price` decimal(10,2) DEFAULT NULL COMMENT '氢诺拟可贷金额（=裸车价*折旧基数*折旧系数*授信成数)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_apply_id` (`apply_id`),
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆评估价格表';

-- ----------------------------
--  Table structure for `c_car_safety_equipment`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_safety_equipment`;
CREATE TABLE `c_car_safety_equipment` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `apply_id` bigint(21) NOT NULL COMMENT '订单id',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `car_safety_equipment` int(1) NOT NULL COMMENT '安全装备 1标配 2选配 3无',
  `car_main_air_bag` int(1) DEFAULT NULL COMMENT '主驾驶座安全气囊 0:无 1:有',
  `car_fu_air_bag` int(1) DEFAULT NULL COMMENT '副驾驶座安全气囊0:无 1:有',
  `car_front_air_bag` int(1) DEFAULT NULL COMMENT '前排侧气囊0:无 1:有',
  `car_rear_air_bag` int(1) DEFAULT NULL COMMENT '后排侧气囊0:无 1:有',
  `car_front_head_air_bag` int(1) DEFAULT NULL COMMENT '前排头部气囊(气帘)0:无 1:有',
  `car_rear_head_air_bag` int(1) DEFAULT NULL COMMENT '后排头部气囊(气帘)0:无 1:有',
  `car_knee_air_bag` int(1) DEFAULT NULL COMMENT '膝部气囊0:无 1:有',
  `pressure_monitoring` int(1) DEFAULT NULL COMMENT '胎压监测装置0:无 1:有',
  `zero_tire_run` int(1) DEFAULT NULL COMMENT '零胎压继续行驶0:无 1:有',
  `seat_belt_not_prompted` int(1) DEFAULT NULL COMMENT '安全带未系提示0:无 1:有',
  `car_isofix` int(1) DEFAULT NULL COMMENT 'ISOFIX儿童座椅接口0:无 1:有',
  `car_abs` int(1) DEFAULT NULL COMMENT 'ABS防抱死0:无 1:有',
  `car_ebd` int(1) DEFAULT NULL COMMENT '制动力分配(EBD/CBC等)0:无 1:有',
  `car_ebab` int(1) DEFAULT NULL COMMENT '刹车辅助(EBAB/BA等)0:无 1:有',
  `car_asr` int(1) DEFAULT NULL COMMENT '牵引力控制(ASR/TCS/TRC等)0:无 1:有',
  `car_esc` int(1) DEFAULT NULL COMMENT '车身稳定控制(ESC/ESP/DSC等)0:无 1:有',
  `parallel_auxiliary` int(1) DEFAULT NULL COMMENT '并线辅助   0:无 1:有',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `c_car_traffic_insure_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_traffic_insure_info`;
CREATE TABLE `c_car_traffic_insure_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `inst_full_name` varchar(255) DEFAULT NULL COMMENT '购买机构全称',
  `bussiness_source` varchar(128) DEFAULT NULL COMMENT '业务来源',
  `proxy_name` varchar(255) DEFAULT NULL COMMENT '代理点名称',
  `insure_number` varchar(128) DEFAULT NULL COMMENT '保单号',
  `insure_person` varchar(64) DEFAULT NULL COMMENT '被保人',
  `insure_begin_time` datetime DEFAULT NULL COMMENT '保险起始日期',
  `insure_end_time` datetime DEFAULT NULL COMMENT '保险到期日期',
  `float_prop` decimal(10,4) DEFAULT NULL COMMENT '浮动比例',
  `total_amount` decimal(12,2) DEFAULT NULL COMMENT '保费合计',
  `vehicle_tax` decimal(12,2) DEFAULT NULL COMMENT '代收车船税',
  `sign_date` datetime DEFAULT NULL COMMENT '签约日期',
  `special_agreement` varchar(512) DEFAULT NULL COMMENT '特别约定',
  `photo_url` varchar(255) DEFAULT NULL COMMENT '照片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `INX_UID` (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆交强险信息表';

-- ----------------------------
--  Table structure for `c_car_transfer_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_transfer_info`;
CREATE TABLE `c_car_transfer_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `name` varchar(32) DEFAULT NULL COMMENT '转移登记姓名',
  `cert_id` varchar(32) DEFAULT NULL COMMENT '转移登记身份证号',
  `get_type` varchar(32) DEFAULT NULL COMMENT '获得方式',
  `reg_date` datetime DEFAULT NULL COMMENT '转移登记日期',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`),
  KEY `INX_CAR_ID` (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆转移登记记录表';

-- ----------------------------
--  Table structure for `c_car_validatecar_boli_configuration`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_validatecar_boli_configuration`;
CREATE TABLE `c_car_validatecar_boli_configuration` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` bigint(11) DEFAULT NULL COMMENT '车辆ID',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '订单id',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `bl_hbl` int(1) DEFAULT NULL COMMENT '玻璃 后玻璃 0无 1标配 2选配',
  `q_ddcc` int(1) DEFAULT NULL COMMENT '前电动车窗0无1有',
  `h_ddcc` int(1) DEFAULT NULL COMMENT '后电动车窗0无1有',
  `ccfjs` int(1) DEFAULT NULL COMMENT '车窗防夹手0无1有',
  `hsjddtj` int(1) DEFAULT NULL COMMENT '后视镜电动调节0无1有',
  `hsjjr` int(1) DEFAULT NULL COMMENT '后视镜加热0无1有',
  `n_hsjzdfxm` int(1) DEFAULT NULL COMMENT '内后视镜自动防眩目0无1有',
  `w_hsjzdfxm` int(1) DEFAULT NULL COMMENT '外后视镜自动防眩目0无1有',
  `hsjddzd` int(1) DEFAULT NULL COMMENT '后视镜电动折叠0无1有',
  `zybhzj` int(1) DEFAULT NULL COMMENT '遮阳板化妆镜0无1有',
  `hys` int(1) DEFAULT NULL COMMENT '后雨刷0无1有',
  `gyys` int(1) DEFAULT NULL COMMENT '感应雨刷0无1有',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆详细配置_玻璃/后玻璃';

-- ----------------------------
--  Table structure for `c_car_validatecar_exterior_specification`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_validatecar_exterior_specification`;
CREATE TABLE `c_car_validatecar_exterior_specification` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` bigint(11) DEFAULT NULL COMMENT '车辆ID',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '订单id',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `exterior_specification` int(1) DEFAULT NULL COMMENT '外部配置 0无 1标配 2选配',
  `power_sunroof` int(1) DEFAULT NULL COMMENT '电动天窗 0无1有',
  `sport_appearance_kit` int(1) DEFAULT NULL COMMENT '运动外观套件 0无1有',
  `electric_operated_closing_door` int(1) DEFAULT NULL COMMENT '电动吸合门 0无1有',
  `electric_trunk` int(1) DEFAULT NULL COMMENT '电动后备箱 0无1有',
  `roof_rack` int(1) DEFAULT NULL COMMENT '车顶行李架 0无1有',
  `internally_operatedcentral_door_locking` int(1) DEFAULT NULL COMMENT '车内中控锁 0无1有',
  `keyless_start_system` int(1) DEFAULT NULL COMMENT '无钥匙启动系统 0无1有',
  `panorama_sunroof` int(1) DEFAULT NULL COMMENT '全景天窗 0无1有',
  `aluminum_alloy_rim` int(1) DEFAULT NULL COMMENT '铝合金轮圈 0无1有',
  `sideslip` int(1) DEFAULT NULL COMMENT '侧滑门 0无1有',
  `induction_trunk` int(1) DEFAULT NULL COMMENT '感应后备箱 0无1有',
  `emmo` int(1) DEFAULT NULL COMMENT '发动机电子防盗 0无1有',
  `remote_key` int(1) DEFAULT NULL COMMENT '遥控钥匙 0无1有',
  `wysjrxt` int(1) DEFAULT NULL COMMENT '无钥匙进入系统 0无1有',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆详细配置_外部配置表';

-- ----------------------------
--  Table structure for `c_car_validatecar_interior_collocation`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_validatecar_interior_collocation`;
CREATE TABLE `c_car_validatecar_interior_collocation` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` bigint(11) DEFAULT NULL COMMENT '车辆ID',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '订单id',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `interior_collocation` int(1) DEFAULT NULL COMMENT '内部配置 0无 1标配 2选配',
  `leather_steering_wheel` int(1) DEFAULT NULL COMMENT '真皮方向盘 0无1有',
  `electric_steering_wheel_adjustment` int(1) DEFAULT NULL COMMENT '方向盘电动调节 0前后调节 1上下调节 2上下+前后调节',
  `steering_wheel_shift` int(1) DEFAULT NULL COMMENT '方向盘换挡 0无 1标配 2选配',
  `steering_wheel_memory` int(1) DEFAULT NULL COMMENT '方向盘记忆 0无1有',
  `full_lcd_panel` int(1) DEFAULT NULL COMMENT '全液晶仪表盘  0无1有',
  `adjustable_steering_wheel` int(1) DEFAULT NULL COMMENT '方向盘调节 0有1无',
  `mfl` int(1) DEFAULT NULL COMMENT '多功能方向盘  0无1有',
  `heated_steering_wheel` int(1) DEFAULT NULL COMMENT '方向盘加热  0无1有',
  `driving_computer_screen` int(1) DEFAULT NULL COMMENT '行车电脑显示屏 0有1无',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆详细配置_内部配置表';

-- ----------------------------
--  Table structure for `c_car_validatecar_kongtiao_configuration`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_validatecar_kongtiao_configuration`;
CREATE TABLE `c_car_validatecar_kongtiao_configuration` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` bigint(11) DEFAULT NULL COMMENT '车辆ID',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '订单id',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `bx_kt` int(1) DEFAULT NULL COMMENT '冰箱、空调 0无 1标配 2选配',
  `ktkzfs` int(1) DEFAULT NULL COMMENT '空调控制方式0无 1自动 2手动',
  `hpdlkt` int(1) DEFAULT NULL COMMENT '后排独立空调0无 1自动 2手动',
  `wdfqkz` int(1) DEFAULT NULL COMMENT '温度分区控制0无1有',
  `hzcfk` int(1) DEFAULT NULL COMMENT '后座出风口0无1有',
  `cnkqtj_hfgl` int(1) DEFAULT NULL COMMENT '车内空气调节/花粉过滤0无1有',
  `czbx` int(1) DEFAULT NULL COMMENT '车载冰箱0无1有',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆详细配置_冰箱、空调表';

-- ----------------------------
--  Table structure for `c_car_validatecar_light_configuration`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_validatecar_light_configuration`;
CREATE TABLE `c_car_validatecar_light_configuration` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` bigint(11) DEFAULT NULL COMMENT '车辆ID',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '订单id',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `dgpz` int(1) DEFAULT NULL COMMENT '灯光配置 0无 1标配 2选配',
  `ygd` varchar(255) DEFAULT NULL COMMENT '远光灯',
  `jgd` varchar(255) DEFAULT NULL COMMENT '近光灯',
  `ledrxd` int(1) DEFAULT NULL COMMENT 'LED日间行车灯0无1有',
  `zsyyjg` int(1) DEFAULT NULL COMMENT '自适应远近光0无1有',
  `zdtd` int(1) DEFAULT NULL COMMENT '自动头灯0无1有',
  `zxfzd` int(1) DEFAULT NULL COMMENT '转向辅助灯0无1有',
  `zxtd` int(1) DEFAULT NULL COMMENT '转向头灯0无1有',
  `qwd` int(1) DEFAULT NULL COMMENT '前雾灯0无1有',
  `ddgdkt` int(1) DEFAULT NULL COMMENT '大灯高度可调0无1有',
  `ddqxzz` int(1) DEFAULT NULL COMMENT '大灯清洗装置0无1有',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆详细配置_灯光配置表';

-- ----------------------------
--  Table structure for `c_car_validatecar_multimedia_configuration`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_validatecar_multimedia_configuration`;
CREATE TABLE `c_car_validatecar_multimedia_configuration` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` bigint(11) DEFAULT NULL COMMENT '车辆ID',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '订单id',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `dmtpz` int(1) DEFAULT NULL COMMENT '多媒体配置 0无 1标配 2选配',
  `gps` int(1) DEFAULT NULL COMMENT 'gps 0无 1标配 2选配',
  `dwhdfw` int(1) DEFAULT NULL COMMENT '定位互动服务 0无 1标配 2选配',
  `zkcsdp` int(1) DEFAULT NULL COMMENT '中控彩色大屏 0有1无',
  `lydh` int(1) DEFAULT NULL COMMENT '蓝牙/车载电话 0有1无',
  `wjyyjk` varchar(255) DEFAULT NULL COMMENT '外接音源接口',
  `cd_dvd` varchar(255) DEFAULT NULL COMMENT '多媒体系统(cd/dvd',
  `ysqsl` varchar(255) DEFAULT NULL COMMENT '扬声器数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆详细配置_多媒体配置表';

-- ----------------------------
--  Table structure for `c_car_validatecar_seat_configuration`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_validatecar_seat_configuration`;
CREATE TABLE `c_car_validatecar_seat_configuration` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` bigint(11) DEFAULT NULL COMMENT '车辆ID',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '订单id',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `zypz` int(1) DEFAULT NULL COMMENT '座椅配置0无 1标配 2选配',
  `zycz` varchar(255) DEFAULT NULL COMMENT '座椅材质',
  `ydfgzy` int(1) DEFAULT NULL COMMENT '运动风格座椅 0无 1标配 2选配',
  `zygdtj` int(1) DEFAULT NULL COMMENT '座椅高低调节 0无1有',
  `ybzctj` int(1) DEFAULT NULL COMMENT '腰部支撑调节 0无1有',
  `jbzctj` int(1) DEFAULT NULL COMMENT '肩部支撑调节 0无 1标配 2选配',
  `zjszddtj` int(1) DEFAULT NULL COMMENT '主驾驶座电动调节 0无1有',
  `fjszddtj` int(1) DEFAULT NULL COMMENT '副驾驶座电动调节 0无1有',
  `hpzddtj` int(1) DEFAULT NULL COMMENT '后排座电动调节 0无1有',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆详细配置_座椅配置表';

-- ----------------------------
--  Table structure for `c_car_verify_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_verify_info`;
CREATE TABLE `c_car_verify_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `car_cond` varchar(128) DEFAULT NULL COMMENT '车况反馈',
  `suggestion` varchar(512) DEFAULT NULL COMMENT '车况描述意见',
  `config_table_photo` varchar(255) DEFAULT NULL COMMENT '配置表附件地址',
  `maintain_photo` varchar(255) DEFAULT NULL COMMENT '维修保养状况附件地址',
  `car_assessment_price` decimal(10,2) DEFAULT NULL COMMENT '二手车市场评估价格',
  `qmzk` varchar(10) DEFAULT NULL COMMENT '漆面状况（优良中差）',
  `nszk` varchar(10) DEFAULT NULL COMMENT '内饰状况（优良中差）',
  `gkzk` varchar(10) DEFAULT NULL COMMENT '工况状况（优良中差）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INX_CAR_ID` (`car_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='验车信息表';

-- ----------------------------
--  Table structure for `c_car_wheel_brakes`
-- ----------------------------
DROP TABLE IF EXISTS `c_car_wheel_brakes`;
CREATE TABLE `c_car_wheel_brakes` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `apply_id` bigint(21) NOT NULL COMMENT '订单id',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `whell_brakes` int(1) DEFAULT NULL COMMENT '车轮制动 1标配 2选配 3无',
  `front_brake_type` varchar(255) DEFAULT NULL COMMENT '前制动器类型',
  `rear_brake_type` varchar(255) DEFAULT NULL COMMENT '后制动器类型',
  `parking_braking` varchar(255) DEFAULT NULL COMMENT '驻车制动类型',
  `front_tyre_size` varchar(255) DEFAULT NULL COMMENT '前轮胎规格',
  `rear_tyre_size` varchar(255) DEFAULT NULL COMMENT '后轮胎规格',
  `spare_tire` varchar(255) DEFAULT NULL COMMENT '备胎规格',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `c_channel_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_channel_info`;
CREATE TABLE `c_channel_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `channel_name` varchar(128) DEFAULT NULL COMMENT '渠道名称',
  `city` varchar(128) DEFAULT NULL COMMENT '所在城市区域',
  `address` varchar(256) DEFAULT NULL COMMENT '渠道联系地址',
  `fanyong_rate` decimal(10,4) DEFAULT NULL COMMENT '返佣比例',
  `account_name` varchar(64) DEFAULT NULL COMMENT '返佣账户户名',
  `account_bank` varchar(128) DEFAULT NULL COMMENT '返佣开户行信息',
  `account_cardno` varchar(64) DEFAULT NULL COMMENT '返佣账户卡号',
  `join_person` varchar(64) DEFAULT NULL COMMENT '对接人名称',
  `join_mobile` varchar(32) DEFAULT NULL COMMENT '对接人联系方式',
  `create_user_id` bigint(21) DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(32) DEFAULT NULL COMMENT '创建人姓名',
  `buss_name` varchar(32) DEFAULT NULL COMMENT '与渠道对接业务人员姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='渠道表';

-- ----------------------------
--  Table structure for `c_contract_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_contract_info`;
CREATE TABLE `c_contract_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `contract_no` varchar(32) DEFAULT NULL COMMENT '合同编号',
  `contract_title1` varchar(64) DEFAULT NULL COMMENT '合同标题1',
  `contract_url1` varchar(64) DEFAULT NULL COMMENT '合同地址1',
  `contract_title2` varchar(64) DEFAULT NULL COMMENT '合同标题2',
  `contract_url2` varchar(100) DEFAULT NULL COMMENT '合同地址2',
  `contract_title3` varchar(64) DEFAULT NULL COMMENT '合同标题3',
  `contract_url3` varchar(100) DEFAULT NULL COMMENT '合同地址3',
  `contract_title4` varchar(64) DEFAULT NULL COMMENT '合同标题4',
  `contract_url4` varchar(64) DEFAULT NULL COMMENT '合同地址4',
  `contract_title5` varchar(64) DEFAULT NULL COMMENT '合同标题5',
  `contract_url5` varchar(64) DEFAULT NULL COMMENT '合同地址5',
  `contract_title6` varchar(64) DEFAULT NULL COMMENT '合同标题6',
  `contract_url6` varchar(64) DEFAULT NULL COMMENT '合同地址6',
  `contract_sign_date` datetime DEFAULT NULL COMMENT '线下合同签订时间',
  `contract_scene_url` varchar(255) DEFAULT NULL COMMENT '合同签署现场照片地址',
  `contract_attach_url` varchar(255) DEFAULT NULL COMMENT '合同签订附件地址',
  `contract_xcqy_url` varchar(255) DEFAULT NULL COMMENT '现场签约照片',
  `contract_dyysl_url` varchar(255) DEFAULT NULL COMMENT '抵押已受理照片',
  `contract_htfj_url` varchar(255) DEFAULT NULL COMMENT '合同附件',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_apply_id` (`apply_id`),
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户合同信息表';

-- ----------------------------
--  Table structure for `c_credit_auth_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_credit_auth_info`;
CREATE TABLE `c_credit_auth_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键ID自增',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款申请ID',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '用户ID',
  `type` int(11) DEFAULT NULL COMMENT '类型 0--主借人 1--共借人',
  `td_score` int(11) DEFAULT NULL COMMENT '同盾贷前审核评分',
  `td_score_attach_url` varchar(255) DEFAULT NULL COMMENT '同盾贷前审核评分附件地址',
  `td_risk_attach_url` varchar(255) DEFAULT NULL COMMENT '同盾贷前审核风险情况附件地址',
  `br_rule_score` int(11) DEFAULT NULL COMMENT '百融贷前审核规则集分数',
  `br_credit_score` int(11) DEFAULT NULL COMMENT '百融代签审核信用评分分数',
  `br_attach_url` varchar(255) DEFAULT NULL COMMENT '百融贷前审核报告附件地址',
  `has_judgement` int(11) DEFAULT '0' COMMENT '有无裁判文书（裁判文书网） 0--无 1--有',
  `judgement_attach_url` varchar(255) DEFAULT NULL COMMENT '裁判文书网附件',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `IND_APPLY_ID` (`apply_id`),
  KEY `IND_CUST_ID` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信贷认证';

-- ----------------------------
--  Table structure for `c_credit_buss_query_record`
-- ----------------------------
DROP TABLE IF EXISTS `c_credit_buss_query_record`;
CREATE TABLE `c_credit_buss_query_record` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `type` int(11) DEFAULT NULL COMMENT '类型 0--主借人 1--共借人',
  `query_date` datetime DEFAULT NULL COMMENT '查询时间',
  `query_org` varchar(64) DEFAULT NULL COMMENT '查询机构',
  `query_reason` int(11) DEFAULT NULL COMMENT '查询原因 0--贷后管理 1--信用卡审批 2--保前查询 3--贷款审批 4--担保资格查询',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_apply_id` (`apply_id`),
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户央行征信机构查询记录表';

-- ----------------------------
--  Table structure for `c_credit_card_detail`
-- ----------------------------
DROP TABLE IF EXISTS `c_credit_card_detail`;
CREATE TABLE `c_credit_card_detail` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `type` int(11) DEFAULT NULL COMMENT '类型 0--主借人 1--共借人',
  `card_org` varchar(64) DEFAULT NULL COMMENT '信用卡发卡机构',
  `card_amount` decimal(10,2) DEFAULT NULL COMMENT '授信额度',
  `card_share_amount` decimal(10,2) DEFAULT NULL COMMENT '共享授信额度',
  `card_type` int(11) DEFAULT NULL COMMENT '类型 0--抵押担保 1--信用免担保 2--组合认证',
  `account_status` int(11) DEFAULT NULL COMMENT '账户状态 0--正常 1--冻结 2--止付 3--呆帐',
  `used_amount` decimal(10,2) DEFAULT NULL COMMENT '已用额度',
  `avg_used_amount` decimal(10,2) DEFAULT NULL COMMENT '近6个月平均使用额度',
  `max_used_amount` decimal(10,2) DEFAULT NULL COMMENT '最大使用额度',
  `cur_overdue_num` int(11) DEFAULT NULL COMMENT '当前逾期期数',
  `cur_overdue_amount` decimal(10,2) DEFAULT NULL COMMENT '当前逾期金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_apply_id` (`apply_id`),
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户央行征信信用卡交易信息明细表';

-- ----------------------------
--  Table structure for `c_credit_loan_detail`
-- ----------------------------
DROP TABLE IF EXISTS `c_credit_loan_detail`;
CREATE TABLE `c_credit_loan_detail` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `type` int(11) DEFAULT NULL COMMENT '类型 0--主借人 1--共借人',
  `loan_org` varchar(64) DEFAULT NULL COMMENT '贷款机构',
  `loan_amount` decimal(10,2) DEFAULT NULL COMMENT '贷款金额',
  `loan_type` int(11) DEFAULT NULL COMMENT '贷款类型 0--抵押担保 1--信用免担保 2--组合认证',
  `loan_period` int(11) DEFAULT NULL COMMENT '贷款期数',
  `loan_begin_time` datetime DEFAULT NULL COMMENT '贷款开始时间',
  `loan_end_time` datetime DEFAULT NULL COMMENT '贷款到期时间',
  `account_status` int(11) DEFAULT NULL COMMENT '账户状态 0--正常 1--逾期',
  `five_class_status` int(11) DEFAULT NULL COMMENT '五级分类 0--正常 1--关注 2--次级  3--可疑 4--损失',
  `capital_amount` decimal(10,2) DEFAULT NULL COMMENT '本金金额',
  `left_period` int(11) DEFAULT NULL COMMENT '剩余期数',
  `cur_month_predict_amount` decimal(10,2) DEFAULT NULL COMMENT '本月应还款金额',
  `cur_month_date` datetime DEFAULT NULL COMMENT '本次应还款日',
  `cur_month_actural_amount` decimal(10,2) DEFAULT NULL COMMENT '本月实还款金额',
  `last_repayment_datge` datetime DEFAULT NULL COMMENT '最近一次还款日期',
  `cur_overdue_num` int(11) DEFAULT NULL COMMENT '当前逾期期数',
  `cur_overdue_amount` decimal(10,2) DEFAULT NULL COMMENT '当前逾期金额',
  `overdue_m2_capital` decimal(10,2) DEFAULT NULL COMMENT '逾期31-60天未还本金',
  `overdue_m3_capital` decimal(10,2) DEFAULT NULL COMMENT '逾期61-90天未还本金',
  `overdue_m45_capital` decimal(10,2) DEFAULT NULL COMMENT '逾期91-180天未还本金',
  `overdue_m6_capital` decimal(10,2) DEFAULT NULL COMMENT '逾期180天以上未还本金',
  `repayment_info` varchar(1000) DEFAULT NULL COMMENT '两年前还款记录情况（文字描述）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_apply_id` (`apply_id`),
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户央行征信贷款交易信息明细表';

-- ----------------------------
--  Table structure for `c_credit_personal_query_record`
-- ----------------------------
DROP TABLE IF EXISTS `c_credit_personal_query_record`;
CREATE TABLE `c_credit_personal_query_record` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `type` int(11) DEFAULT NULL COMMENT '类型 0--主借人 1--共借人',
  `query_date` datetime DEFAULT NULL COMMENT '查询时间',
  `query_org` varchar(64) DEFAULT NULL COMMENT '查询机构',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_apply_id` (`apply_id`),
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户央行征信个人查询记录表';

-- ----------------------------
--  Table structure for `c_credit_report`
-- ----------------------------
DROP TABLE IF EXISTS `c_credit_report`;
CREATE TABLE `c_credit_report` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `type` int(11) DEFAULT NULL COMMENT '类型 0--主借人 1--共借人',
  `collection_date` datetime DEFAULT NULL COMMENT '征信报告采集时间',
  `house_loan_num` int(11) DEFAULT NULL COMMENT '个人住房贷款笔数 (信用提示模块)',
  `house_buss_loan_num` int(11) DEFAULT NULL COMMENT '个人商用房贷款笔数 (信用提示模块)',
  `other_loan_num` int(11) DEFAULT NULL COMMENT '其他贷款笔数 (信用提示模块)',
  `first_loan_start_date` varchar(20) DEFAULT NULL COMMENT '首笔贷款发放月份 YYYYMM (信用提示模块)',
  `credit_card_num` int(11) DEFAULT NULL COMMENT '贷记卡账户数 (信用提示模块)',
  `first_credit_card_start_date` varchar(20) DEFAULT NULL COMMENT '首张借记卡发卡月份 YYYYMM (信用提示模块)',
  `semi_credit_card_num` int(11) DEFAULT NULL COMMENT '准贷记卡账户数 (信用提示模块)',
  `first_semi_credit_card_start_date` varchar(20) DEFAULT NULL COMMENT '首张准贷记卡发卡月份 YYYYMM (信用提示模块)',
  `self_declare_num` int(11) DEFAULT NULL COMMENT '本人声明数量 (信用提示模块)',
  `objection_num` int(11) DEFAULT NULL COMMENT '异议标注数量 (信用提示模块)',
  `loan_overdue_num` int(11) DEFAULT NULL COMMENT '贷款逾期笔数 (逾期违约模块)',
  `loan_overdue_month_num` int(11) DEFAULT NULL COMMENT '贷款逾期月份数 (逾期违约模块)',
  `loan_max_amount` decimal(10,2) DEFAULT NULL COMMENT '贷款逾期单月最高逾期总额 (逾期违约模块)',
  `loan_max_month_num` int(11) DEFAULT NULL COMMENT '最长逾期月数 (逾期违约模块)',
  `card_overdue_num` int(11) DEFAULT NULL COMMENT '贷记卡逾期账户数 (逾期违约模块)',
  `card_month_num` int(11) DEFAULT NULL COMMENT '贷记卡逾期月份数 (逾期违约模块)',
  `card_overdue_max_amount` decimal(10,2) DEFAULT NULL COMMENT '贷记卡单月最高逾期总额 (逾期违约模块)',
  `card_max_month_num` int(11) DEFAULT NULL COMMENT '贷记卡最长逾期月份数 (逾期违约模块)',
  `semi_card_overdue_num` int(11) DEFAULT NULL COMMENT '准贷记卡60天以上透支账户数 (逾期违约模块)',
  `semi_card_month_num` int(11) DEFAULT NULL COMMENT '准贷记卡60天以上透支月份数 (逾期违约模块)',
  `semi_card_max_amount` int(11) DEFAULT NULL COMMENT '准贷记卡60天以上透支单月最高透支金额 (逾期违约模块)',
  `semi_card_max_month_num` int(11) DEFAULT NULL COMMENT '准贷记卡60天以上透支最长透支月数 (逾期违约模块)',
  `loan_legal_org_num` int(11) DEFAULT NULL COMMENT '贷款法人机构数 (授信概要模块)',
  `loan_org_num` int(11) DEFAULT NULL COMMENT '贷款机构数 (授信概要模块)',
  `loan_num` int(11) DEFAULT NULL COMMENT '贷款笔数 (授信概要模块)',
  `loan_total_amount` decimal(10,2) DEFAULT NULL COMMENT '合同总金额 (授信概要模块)',
  `loan_left_amount` decimal(10,2) DEFAULT NULL COMMENT '贷款余额 (授信概要模块)',
  `loan_total_month_amount` decimal(10,2) DEFAULT NULL COMMENT '总月还款 (授信概要模块)',
  `card_legal_org_num` int(11) DEFAULT NULL COMMENT '发卡法人机构数 (授信概要模块)',
  `card_org_num` int(11) DEFAULT NULL COMMENT '发卡机构数 (授信概要模块)',
  `card_account_num` int(11) DEFAULT NULL COMMENT '账户数 (授信概要模块)',
  `card_total_amount` decimal(10,2) DEFAULT NULL COMMENT '授信总额 (授信概要模块)',
  `card_max_amount` decimal(10,2) DEFAULT NULL COMMENT '单家行最高授信额 (授信概要模块)',
  `card_min_amount` decimal(10,2) DEFAULT NULL COMMENT '单家行最低授信额 (授信概要模块)',
  `card_used_amount` decimal(10,2) DEFAULT NULL COMMENT '已用额度 (授信概要模块)',
  `card_avg_amount` decimal(10,2) DEFAULT NULL COMMENT '近6个月平均使用额度 (授信概要模块)',
  `history_query_num` int(11) DEFAULT NULL COMMENT '近两个月征信历史查询次数 (根据记录表 自动算出)',
  `card_month_amount` decimal(10,2) DEFAULT NULL COMMENT '信用卡月还款（信用卡已用额度/10)',
  `credit_loan_month_amount` decimal(10,2) DEFAULT NULL COMMENT '信用贷款月还款金额 (信用贷款月还款+信用卡月还款)',
  `report_attach_url` varchar(255) DEFAULT NULL COMMENT '征信报告附件地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_apply_id` (`apply_id`),
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户央行征信信息表';

-- ----------------------------
--  Table structure for `c_cust_company_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_cust_company_info`;
CREATE TABLE `c_cust_company_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `type` int(11) DEFAULT NULL COMMENT '类型 0--主借人 1--共借人',
  `company_name` varchar(128) DEFAULT NULL COMMENT '企业名称',
  `industry` varchar(128) DEFAULT NULL COMMENT '企业所属行业',
  `found_time` datetime DEFAULT NULL COMMENT '企业成立时间',
  `gongshang_enterprise_photo` varchar(255) DEFAULT NULL COMMENT '工商信息查询（企业）附件地址',
  `court_enterprise` int(11) DEFAULT NULL COMMENT '全国法院被执行人查询（企业） 0--正常 1--异常',
  `court_enterprise_photo` varchar(255) DEFAULT NULL COMMENT '全国法院被执行人查询（企业）附件地址',
  `zhixing_enterprise` int(11) DEFAULT NULL COMMENT '中国执行信息公开网查询（企业） 0--正常 1--异常',
  `zhixing_enterprise_photo` varchar(255) DEFAULT NULL COMMENT '中国执行信息公开网查询（企业）附件地址',
  `risk_enterprise` int(11) DEFAULT NULL COMMENT '风险信息网查询（企业） 0--正常 1--异常',
  `risk_enterprise_photo` varchar(255) DEFAULT NULL COMMENT '风险信息网查询（企业） 附件地址',
  `warn_enterprise` int(11) DEFAULT NULL COMMENT '风险预警网查询（企业） 0--正常 1--异常',
  `warn_enterprise_photo` varchar(255) DEFAULT NULL COMMENT '风险预警网查询（企业） 附件地址',
  `enterprise_status` int(11) DEFAULT NULL COMMENT '工商注册企业状态 0--存续 1--吊销 2--注销 3--无法核实',
  `enterprise_remark` varchar(512) DEFAULT NULL COMMENT '企业备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_apply_id` (`apply_id`),
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户关联企业信息表';

-- ----------------------------
--  Table structure for `c_cust_finance_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_cust_finance_info`;
CREATE TABLE `c_cust_finance_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `type` int(11) DEFAULT NULL COMMENT '类型 0--主借人 1--共借人',
  `fin_type` int(11) DEFAULT NULL COMMENT '财力证明类型 0--房产 1--车辆',
  `property` int(11) DEFAULT NULL COMMENT '性质 0--安置房 1--商品房 2--自建房福利房 3--写字楼 4--商铺',
  `status` int(11) DEFAULT NULL COMMENT '状态 0--全款 1--抵押 2--按揭',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `attachurl` varchar(255) DEFAULT NULL COMMENT '附件地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_apply_id` (`apply_id`),
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户财力证明表';

-- ----------------------------
--  Table structure for `c_cust_income_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_cust_income_info`;
CREATE TABLE `c_cust_income_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `income_type` int(11) DEFAULT NULL COMMENT '认收方案类别 0--按揭房贷方案 1--抵押房贷方案 2--全款房方案 3--寿险保单方案 4--他行车贷方案 5--流水结息方案 6--公积金方案 7--打卡工资方案',
  `income_amount` decimal(10,2) DEFAULT NULL COMMENT '收入金额',
  `income_confirm_amount` decimal(10,2) DEFAULT NULL COMMENT '收入认定金额 （收入金额*对应方案类别的系数)',
  `loan_amount` decimal(10,2) DEFAULT NULL COMMENT '拟定可贷金额',
  `dti` decimal(10,4) DEFAULT NULL COMMENT 'DTI 收入负债比 = 收入认定金额/(征信认定的负债+本次拟贷款月供)',
  `apply_amount` decimal(10,2) DEFAULT NULL COMMENT '申请额度 单位(元)',
  `apply_period` int(11) DEFAULT '36' COMMENT '申请期限  12/24/36',
  `attach_url` varchar(255) DEFAULT NULL COMMENT '附件地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_apply_id` (`apply_id`),
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户收入认定方式表';

-- ----------------------------
--  Table structure for `c_cust_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_cust_info`;
CREATE TABLE `c_cust_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(64) NOT NULL COMMENT '姓名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `sex` int(1) DEFAULT NULL COMMENT '性别 1-女 0-男',
  `nation` varchar(20) DEFAULT NULL COMMENT '民族',
  `birthday` datetime DEFAULT NULL COMMENT '出生年月日 YYYYMMDD',
  `cert_id` varchar(32) NOT NULL COMMENT '身份证号码',
  `validate_begin` datetime DEFAULT NULL COMMENT '身份证有效期起始日期',
  `validate_end` datetime DEFAULT NULL COMMENT '身份证有效期结束日期',
  `sign_org` varchar(128) DEFAULT NULL COMMENT '签发机关',
  `education` int(11) DEFAULT NULL COMMENT '教育程度 0--文盲 1--小学 2--初中 3--高中 4--专科 5--本科 6--硕士 7--博士',
  `id_front_photo_url` varchar(255) DEFAULT NULL COMMENT '身份证正面图片地址',
  `id_back_photo_url` varchar(255) DEFAULT NULL COMMENT '身份证背面图片地址',
  `proof_Of_Residence` varchar(255) DEFAULT NULL COMMENT '居住证明材料图片地址',
  `child_num` int(11) DEFAULT NULL COMMENT '子女人数',
  `child_adult` int(11) DEFAULT NULL COMMENT '子女情况 1--无子女 2--有子女未成年 4--有子女已成年（可多选）',
  `live_address` varchar(255) DEFAULT NULL COMMENT '居住地址',
  `live_type` int(11) DEFAULT NULL COMMENT '居住地性质  1--自有商品房全款 2--自有商业房按揭 4--自有商业房全款已抵押 8--自有自建房 16--自有安置房 32--单位宿舍 64--租用 128--父母房产 256--亲属房产（可多选）',
  `together_live` int(11) DEFAULT NULL COMMENT '合住情况 1--单人居住 2--配偶合住 4--父母合住 8--亲属合住 16--子女合住 32--同事合住 64--朋友合住（可多选）',
  `spouse_name` varchar(64) DEFAULT NULL COMMENT '配偶姓名（如果是已婚，再婚）',
  `spouse_phone` varchar(32) DEFAULT NULL COMMENT '配偶手机号',
  `contract_name1` varchar(64) DEFAULT NULL COMMENT '紧急联系人1姓名',
  `contract_phone1` varchar(32) DEFAULT NULL COMMENT '紧急联系人1电话',
  `contract_relation1` varchar(64) DEFAULT NULL COMMENT '关系1',
  `contract_name2` varchar(64) DEFAULT NULL COMMENT '紧急联系人2姓名',
  `contract_phone2` varchar(32) DEFAULT NULL COMMENT '紧急联系人2电话',
  `contract_relation2` varchar(64) DEFAULT NULL COMMENT '关系2',
  `contract_name3` varchar(64) DEFAULT NULL COMMENT '紧急联系人3姓名',
  `contract_phone3` varchar(32) DEFAULT NULL COMMENT '紧急联系人3电话',
  `contract_relation3` varchar(64) DEFAULT NULL COMMENT '关系3',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户基本信息表';

-- ----------------------------
--  Table structure for `c_cust_interview_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_cust_interview_info`;
CREATE TABLE `c_cust_interview_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `overview` varchar(2000) DEFAULT NULL COMMENT '面审意见综述',
  `interview_result` int(11) DEFAULT NULL COMMENT '面审意见 0--拒绝 1--通过',
  `rejection_reason` varchar(1000) DEFAULT NULL COMMENT '拒绝原因',
  `loan_amount` decimal(10,2) DEFAULT NULL COMMENT '通过的金额',
  `loan_period` int(11) DEFAULT NULL COMMENT '通过的期数',
  `scene_evidence_url1` varchar(255) DEFAULT NULL COMMENT '现场证据附件地址1',
  `scene_evidence_url2` varchar(255) DEFAULT NULL COMMENT '现场证据附件地址2',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_apply_id` (`apply_id`) USING BTREE,
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='面审最终意见表';

-- ----------------------------
--  Table structure for `c_cust_work_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_cust_work_info`;
CREATE TABLE `c_cust_work_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户ID',
  `income_type` int(11) DEFAULT NULL COMMENT '收入来源 1--自雇有执照 2--自雇无执照 4--受薪 8--待业 16--自由职业者',
  `company_name` varchar(255) DEFAULT NULL COMMENT '单位名称 如果收入来源是 1，2，4的话',
  `company_type` int(11) DEFAULT NULL COMMENT '单位性质 0--国有企业 1--国有控股企业 2--外资企业 3--合资企业 4--私营业务 5--事业单位/政府',
  `company_address` varchar(255) DEFAULT NULL COMMENT '单位地址',
  `company_tel` varchar(16) DEFAULT NULL COMMENT '单位电话',
  `department` varchar(32) DEFAULT NULL COMMENT '部门',
  `job` varchar(32) DEFAULT NULL COMMENT '职务',
  `work_age` decimal(3,1) DEFAULT NULL COMMENT '入职年限（单位：年）',
  `month_income` decimal(10,1) DEFAULT NULL COMMENT '月均收入（单位：万元）',
  `spouse_income_type` int(11) DEFAULT NULL COMMENT '配偶收入来源 1--自雇有执照 2--自雇无执照 4--受薪 8--待业 16--自由职业者',
  `spouse_company_name` varchar(255) DEFAULT NULL COMMENT '配偶单位名称 如果收入来源是 1，2，4的话',
  `spouse_company_type` int(11) DEFAULT NULL COMMENT '配偶单位性质 0--国有企业 1--国有控股企业 2--外资企业 3--合资企业 4--私营业务 5--事业单位/政府',
  `spouse_company_address` varchar(255) DEFAULT NULL COMMENT '配偶单位地址',
  `spouse_company_tel` varchar(16) DEFAULT NULL COMMENT '配偶单位电话',
  `spouse_department` varchar(32) DEFAULT NULL COMMENT '配偶部门',
  `spouse_job` varchar(32) DEFAULT NULL COMMENT '配偶职务',
  `spouse_work_age` decimal(3,1) DEFAULT NULL COMMENT '配偶入职年限（单位：年）',
  `spouse_month_income` decimal(10,1) DEFAULT NULL COMMENT '配偶月均收入（单位：万元）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`),
  KEY `INX_CUST_ID` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户工作信息表';

-- ----------------------------
--  Table structure for `c_data_keep_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_data_keep_info`;
CREATE TABLE `c_data_keep_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `car_id` bigint(21) NOT NULL COMMENT '车辆id',
  `paper_data_num` int(11) DEFAULT NULL COMMENT '纸质资料份数',
  `contract_num` int(11) DEFAULT NULL COMMENT '纸质合同份数',
  `is_car_register` int(11) DEFAULT NULL COMMENT '是否存留抵押登记证书 0--否 1--是',
  `is_car_key` int(11) DEFAULT NULL COMMENT '是否存有客户备用钥匙 0--否 1--是',
  `keep_date` datetime DEFAULT NULL COMMENT '存档时间',
  `unkeep_date` datetime DEFAULT NULL COMMENT '归还时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_car_id` (`car_id`),
  KEY `index_apply_id` (`apply_id`),
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户资料存留信息表';

-- ----------------------------
--  Table structure for `c_family_book_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_family_book_info`;
CREATE TABLE `c_family_book_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户ID',
  `relationship` varchar(64) NOT NULL COMMENT '申请人与户主关系',
  `master_name` varchar(64) NOT NULL COMMENT '户主姓名',
  `master_sex` int(1) DEFAULT NULL COMMENT '性别 0-女 1-男',
  `cert_id` varchar(32) NOT NULL COMMENT '户主身份证号码',
  `first_page_photo_url` varchar(255) DEFAULT NULL COMMENT '户口本首页照片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`),
  KEY `INX_CUST_ID` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户户口本信息表';

-- ----------------------------
--  Table structure for `c_family_book_sub_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_family_book_sub_info`;
CREATE TABLE `c_family_book_sub_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `book_id` bigint(21) NOT NULL COMMENT '户口本ID',
  `relationship` varchar(64) NOT NULL COMMENT '申请人与关联人关系',
  `name` varchar(64) NOT NULL COMMENT '关联人姓名',
  `sex` int(1) DEFAULT NULL COMMENT '关联人性别 0-女 1-男',
  `cert_id` varchar(32) NOT NULL COMMENT '关联人身份证号码',
  `book_photo_url` varchar(255) DEFAULT NULL COMMENT '关联人户口页照片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`),
  KEY `INX_book_id` (`book_id`),
  KEY `INX_CERT_ID` (`cert_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户户口本关联关系信息表';

-- ----------------------------
--  Table structure for `c_final_judgement_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_final_judgement_info`;
CREATE TABLE `c_final_judgement_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `judgement_result` int(11) DEFAULT NULL COMMENT '终审意见 0--通过 1--拒绝 2--补充资料',
  `rejection_reason` varchar(1000) DEFAULT NULL COMMENT '拒绝原因',
  `loan_amount` decimal(10,2) DEFAULT NULL COMMENT '通过的金额',
  `loan_period` int(11) DEFAULT NULL COMMENT '通过的期数',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_apply_id` (`apply_id`) USING BTREE,
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='终审意见表';

-- ----------------------------
--  Table structure for `c_gongjie_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_gongjie_info`;
CREATE TABLE `c_gongjie_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `name` varchar(64) NOT NULL COMMENT '姓名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `sex` int(1) DEFAULT NULL COMMENT '性别 0-女 1-男',
  `cert_id` varchar(32) NOT NULL COMMENT '身份证号码',
  `id_front_photo_url` varchar(255) DEFAULT NULL COMMENT '身份证正面图片地址',
  `id_back_photo_url` varchar(255) DEFAULT NULL COMMENT '身份证背面图片地址',
  `marry_status` int(11) NOT NULL DEFAULT '0' COMMENT '婚姻状况 0--已婚 1--未婚 2--再婚 3--离异 4--丧偶',
  `relation` int(11) DEFAULT NULL COMMENT '与主借人关系 0-配偶 1-父母 2-子女 3-亲属 4-股东 5-朋友',
  `live_address` varchar(255) DEFAULT NULL COMMENT '居住地址',
  `occupation_type` int(11) DEFAULT NULL COMMENT '职业性质 1--自雇2--受薪 3--自由职业',
  `company_name` varchar(255) DEFAULT NULL COMMENT '单位名称 如果收入来源是 1，2，4的话',
  `company_type` int(11) DEFAULT NULL COMMENT '单位性质 0--国有企业 1--国有控股企业 2--外资企业 3--合资企业 4--私营业务 5--事业单位/政府',
  `company_address` varchar(255) DEFAULT NULL COMMENT '单位地址',
  `company_tel` varchar(16) DEFAULT NULL COMMENT '单位电话',
  `department` varchar(32) DEFAULT NULL COMMENT '部门',
  `job` varchar(32) DEFAULT NULL COMMENT '职务',
  `month_income` decimal(10,2) DEFAULT NULL COMMENT '月均收入（单位：万元）',
  `company_attach_url` varchar(255) DEFAULT NULL COMMENT '单位网查信息附件',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`),
  KEY `INX_CUST_ID` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='共借人信息表';

-- ----------------------------
--  Table structure for `c_id_auth_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_id_auth_info`;
CREATE TABLE `c_id_auth_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `type` int(11) DEFAULT NULL COMMENT '类型 0--主借人 1--共借人',
  `gongan_photol_id` varchar(100) DEFAULT NULL COMMENT '公安部预留用户图片id',
  `is_id_auth` int(1) DEFAULT NULL COMMENT '是否通过认证 0-否 1-是',
  `id_front_photo_url` varchar(255) DEFAULT NULL COMMENT '身份证正面图片地址',
  `id_back_photo_url` varchar(255) DEFAULT NULL COMMENT '身份证背面图片地址',
  `hold_identify_photo` varchar(255) DEFAULT NULL COMMENT '手持身份证照片地址',
  `auth_time` datetime DEFAULT NULL COMMENT '认证时间',
  `user_name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `id_number` varchar(20) DEFAULT NULL COMMENT '身份证号码',
  `nation` varchar(20) DEFAULT NULL COMMENT '民族',
  `address` varchar(255) DEFAULT NULL COMMENT '住址',
  `sign_orgaization` varchar(200) DEFAULT NULL COMMENT '发证机关',
  `validity_period` varchar(100) DEFAULT NULL COMMENT '有效期',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `INX_UID` (`cust_id`),
  KEY `INX_APPLY_ID` (`apply_id`),
  KEY `INX_IDN` (`id_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户身份证信息表';

-- ----------------------------
--  Table structure for `c_judicial_auth_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_judicial_auth_info`;
CREATE TABLE `c_judicial_auth_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `type` int(11) DEFAULT NULL COMMENT '类型 0--主借人 1--共借人',
  `court_personal` int(11) DEFAULT NULL COMMENT '全国法院被执行人查询（个人） 0--正常 1--异常',
  `court_personal_photo` varchar(255) DEFAULT NULL COMMENT '全国法院被执行人查询（个人）附件地址',
  `zhixing_personal` int(11) DEFAULT NULL COMMENT '中国执行信息公开网查询（个人） 0--正常 1--异常',
  `zhixing_personal_photo` varchar(255) DEFAULT NULL COMMENT '中国执行信息公开网查询（个人）附件地址',
  `risk_personal` int(11) DEFAULT NULL COMMENT '风险信息网查询（个人） 0--正常 1--异常',
  `risk_personal_photo` varchar(255) DEFAULT NULL COMMENT '风险信息网查询（个人） 附件地址',
  `warn_personal` int(11) DEFAULT NULL COMMENT '风险预警网查询（个人） 0--正常 1--异常',
  `warn_personal_photo` varchar(255) DEFAULT NULL COMMENT '风险预警网查询（个人） 附件地址',
  `personal_remark` varchar(512) DEFAULT NULL COMMENT '个人备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INX_APPLY_ID` (`apply_id`,`type`) USING BTREE,
  KEY `INX_CUST_ID` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='司法认证表';

-- ----------------------------
--  Table structure for `c_liveness_auth_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_liveness_auth_info`;
CREATE TABLE `c_liveness_auth_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键 自增',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `type` int(11) DEFAULT NULL COMMENT '类型 0--主借人 1--共借人',
  `is_liveness_auth` int(1) DEFAULT NULL COMMENT '是否认证 0--否 1--是',
  `liveness_blink_photo_url` varchar(255) DEFAULT NULL COMMENT '活体眨眼图片地址',
  `liveness_nod_photo_url` varchar(255) DEFAULT NULL COMMENT '活体上下点头图片地址',
  `liveness_mouth_photo_url` varchar(255) DEFAULT NULL COMMENT '活体张嘴图片地址',
  `liveness_yaw_photo_url` varchar(255) DEFAULT NULL COMMENT '活体左右摇头图片地址',
  `liveness_file_url` varchar(255) DEFAULT NULL,
  `liveness_auth_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `INX_UID` (`cust_id`),
  KEY `INX_APPLY_ID` (`apply_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户活体数据认证信息表';

-- ----------------------------
--  Table structure for `c_main_approve_record`
-- ----------------------------
DROP TABLE IF EXISTS `c_main_approve_record`;
CREATE TABLE `c_main_approve_record` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '订单id',
  `operator_id` bigint(21) DEFAULT NULL COMMENT '操作员id',
  `operator_name` varchar(25) DEFAULT NULL COMMENT '操作员姓名',
  `operator_time` datetime DEFAULT NULL COMMENT '操作时间',
  `process_node_id` bigint(21) DEFAULT NULL COMMENT '当前节点ID',
  `process_node_desc` varchar(128) DEFAULT NULL COMMENT '当前节点名称',
  `audit_remark` varchar(512) DEFAULT NULL COMMENT '审核意见',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_apply_id` (`apply_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主流程审批记录表';

-- ----------------------------
--  Table structure for `c_marry_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_marry_info`;
CREATE TABLE `c_marry_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户ID',
  `marry_status` int(11) NOT NULL DEFAULT '0' COMMENT '婚姻状况 0--已婚 1--未婚 2--再婚 3--离异 4--丧偶',
  `marry_date` datetime DEFAULT NULL COMMENT '婚姻登记日期（如果是已婚，再婚，丧偶）',
  `spouse_name` varchar(64) DEFAULT NULL COMMENT '配偶姓名（如果是已婚，再婚，丧偶）',
  `spouse_sex` int(1) DEFAULT NULL COMMENT '配偶性别 0-女 1-男（如果是已婚，再婚，丧偶）',
  `spouse_cert_id` varchar(32) DEFAULT NULL COMMENT '配偶身份证号码（如果是已婚，再婚，丧偶）',
  `validate_begin` datetime DEFAULT NULL COMMENT '配偶身份证有效期起始日期',
  `validate_end` datetime DEFAULT NULL COMMENT '配偶身份证有效期结束日期',
  `sign_org` varchar(128) DEFAULT NULL COMMENT '配偶签发机关',
  `id_front_photo_url` varchar(255) DEFAULT NULL COMMENT '配偶身份证正面图片地址',
  `id_back_photo_url` varchar(255) DEFAULT NULL COMMENT '配偶身份证背面图片地址',
  `marry_photo_url` varchar(255) DEFAULT NULL COMMENT '结婚证照片地址',
  `divorce_date` datetime DEFAULT NULL COMMENT '离婚登记日期（如果是离异）',
  `divorce_name` varchar(64) DEFAULT NULL COMMENT '原配偶名称（如果是离异）',
  `divorce_sex` int(1) DEFAULT NULL COMMENT '原配偶性别 0-女 1-男（如果是离异）',
  `divorce_cert_id` varchar(32) DEFAULT NULL COMMENT '原配偶身份证号码（如果是离异）',
  `divorce_photo_url` varchar(255) DEFAULT NULL COMMENT '离婚证照片(如果是离异)',
  `death_cert_photo_url` varchar(255) DEFAULT NULL COMMENT '死亡证明照片地址(如果是丧偶)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  `spouse_phone` varchar(32) DEFAULT NULL COMMENT '配偶手机号',
  PRIMARY KEY (`id`),
  KEY `INX_CUST_ID` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户婚姻信息表';

-- ----------------------------
--  Table structure for `c_once_early_repayment_record`
-- ----------------------------
DROP TABLE IF EXISTS `c_once_early_repayment_record`;
CREATE TABLE `c_once_early_repayment_record` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `repayment_id` bigint(21) DEFAULT NULL COMMENT '还款总表id',
  `once_repayment_capital` decimal(16,2) DEFAULT '0.00' COMMENT '一次性提前还款剩余本金',
  `once_repayment_charge` decimal(16,2) DEFAULT '0.00' COMMENT '一次性提前还款剩余手续费',
  `once_repayment_breach` decimal(16,2) DEFAULT '0.00' COMMENT '一次性提前还款罚金',
  `cur_period_num` int(11) DEFAULT '0' COMMENT '当期期数',
  `cur_period_amount` decimal(16,2) DEFAULT '0.00' COMMENT '当期还款金额',
  `once_repayment_total` decimal(16,2) DEFAULT '0.00' COMMENT '一次性提前还款总额',
  `appoint_date` datetime NOT NULL COMMENT '约定提前还款时间',
  `cur_status` int(11) DEFAULT '1' COMMENT '当前还款状态 1--未开始  2--还款中  3--部分还款  10--申请提前还款 20--业务经理审批 30--财务确认 40--财务经理审核  50--已还完',
  `cur_status_desc` varchar(128) DEFAULT NULL COMMENT '当前状态描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_repayment_id` (`repayment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提前还款记录表';

-- ----------------------------
--  Table structure for `c_pay_approve_record`
-- ----------------------------
DROP TABLE IF EXISTS `c_pay_approve_record`;
CREATE TABLE `c_pay_approve_record` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pay_id` bigint(21) DEFAULT NULL COMMENT '放款总表id',
  `operator_id` bigint(21) DEFAULT NULL COMMENT '操作员id',
  `operator_name` varchar(25) DEFAULT NULL COMMENT '操作员姓名',
  `operator_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operator_result` int(11) DEFAULT NULL COMMENT '审批结果 0--未通过 1--通过',
  `operator_tip` varchar(255) DEFAULT NULL COMMENT '审批意见',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_pay_id` (`pay_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='放款审批记录表';

-- ----------------------------
--  Table structure for `c_pay_detail_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_pay_detail_info`;
CREATE TABLE `c_pay_detail_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pay_id` bigint(21) NOT NULL COMMENT '放款总表id',
  `dept_id` bigint(21) DEFAULT NULL COMMENT '部门id',
  `pay_num` int(11) DEFAULT NULL COMMENT '放款次数',
  `cust_mobile` varchar(16) DEFAULT NULL COMMENT '客户手机号',
  `cur_pay_amount` decimal(10,2) DEFAULT NULL COMMENT '本次放款金额',
  `bank_name` varchar(64) DEFAULT NULL COMMENT '客户开户行',
  `bank_card_no` varchar(32) DEFAULT NULL COMMENT '客户放款账号',
  `pay_status` int(11) DEFAULT NULL COMMENT '放款状态 1-创建 4-失败 5-支付处理中 6-支付成功',
  `pay_type` int(11) DEFAULT NULL COMMENT '放款类型 0-系统放款 1--手动代付 2--手动冲账',
  `paying_num` int(11) DEFAULT NULL COMMENT '放款处理中查询次数',
  `serial_no` varchar(32) DEFAULT NULL COMMENT '放款流水号',
  `pay_code` varchar(20) DEFAULT NULL COMMENT '支付中心code',
  `pay_msg` varchar(200) DEFAULT NULL COMMENT '支付消息',
  `pay_channel` varchar(50) DEFAULT NULL COMMENT '支付渠道',
  `operator_id` bigint(21) DEFAULT NULL COMMENT '财务id',
  `operator_date` datetime DEFAULT NULL COMMENT '本次放款时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_pay_id` (`pay_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='放款详情表';

-- ----------------------------
--  Table structure for `c_pay_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_pay_info`;
CREATE TABLE `c_pay_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `dept_id` bigint(21) DEFAULT NULL COMMENT '所属部门id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `contract_no` varchar(32) DEFAULT NULL COMMENT '合同编号',
  `cust_name` varchar(32) DEFAULT NULL COMMENT '客户姓名',
  `cust_mobile` varchar(16) DEFAULT NULL COMMENT '客户手机号',
  `cust_id_no` varchar(32) DEFAULT NULL COMMENT '客户身份证号',
  `reception_depart` varchar(32) DEFAULT NULL COMMENT '前台部门',
  `reception_manager` varchar(32) DEFAULT NULL COMMENT '前台部门经理',
  `trans_source` varchar(64) DEFAULT NULL COMMENT '业务来源',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '放款总额',
  `period_num` int(11) DEFAULT NULL COMMENT '放款期数',
  `payed_amount` decimal(10,2) DEFAULT NULL COMMENT '已放款金额',
  `not_pay_amount` decimal(10,2) DEFAULT NULL COMMENT '未放款金额',
  `pay_status` int(11) DEFAULT NULL COMMENT '放款状态',
  `pay_status_desc` varchar(64) DEFAULT NULL COMMENT '放款状态描述',
  `reception_amount` decimal(10,2) DEFAULT '0.00' COMMENT '前台手续费',
  `is_replace_cost` int(11) DEFAULT '1' COMMENT '是否代收前台手续费 0-否 1-是',
  `is_per_charge` int(11) DEFAULT '0' COMMENT '是否分期手续费 0--分期 1--全部',
  `bank_name` varchar(64) DEFAULT NULL COMMENT '客户开户行',
  `bank_card_no` varchar(32) DEFAULT NULL COMMENT '客户放款账号',
  `approve_amount` decimal(10,2) DEFAULT NULL COMMENT '审批金额',
  `first_buss_id` bigint(21) DEFAULT NULL COMMENT '业务员',
  `second_buss_id` bigint(21) DEFAULT NULL COMMENT '业务员2',
  `buss_manager_id` bigint(21) DEFAULT NULL COMMENT '业务经理',
  `first_finance_id` bigint(21) DEFAULT NULL COMMENT '首次放款财务',
  `first_finance_manager_id` bigint(21) DEFAULT NULL COMMENT '首次放款财务经理',
  `second_finance_id` bigint(21) DEFAULT NULL COMMENT '二次放款财务',
  `second_finance_manager_id` bigint(21) DEFAULT NULL COMMENT '二次放款财务经理',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_apply_id` (`apply_id`),
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='放款总表';

-- ----------------------------
--  Table structure for `c_pay_statis_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_pay_statis_info`;
CREATE TABLE `c_pay_statis_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dept_id` bigint(21) DEFAULT NULL COMMENT '部门id',
  `statis_date` date DEFAULT NULL COMMENT '统计日期 YYYY-MM-DD',
  `pay_total_amount` decimal(16,2) DEFAULT '0.00' COMMENT '放款总计',
  `repayment_total_amount` decimal(16,2) DEFAULT '0.00' COMMENT '还款总计',
  `total_capital` decimal(16,2) DEFAULT '0.00' COMMENT '还款总本金',
  `total_interest` decimal(16,2) DEFAULT '0.00' COMMENT '还款总利息',
  `total_service_charge` decimal(16,2) DEFAULT '0.00' COMMENT '还款总服务费',
  `total_charge` decimal(16,2) DEFAULT '0.00' COMMENT '还款总手续费',
  `total_penalty` decimal(16,2) DEFAULT '0.00' COMMENT '还款总罚息',
  `total_breach` decimal(16,2) DEFAULT '0.00' COMMENT '还款总违约金',
  `total_pre_fee` decimal(16,2) DEFAULT '0.00' COMMENT '还款总前期费用',
  `not_repay_amount` decimal(16,2) DEFAULT '0.00' COMMENT '未还总金额',
  `not_repay_capital` decimal(16,2) DEFAULT '0.00' COMMENT '未还总本金',
  `not_repay_interest` decimal(16,2) DEFAULT '0.00' COMMENT '未还总利息',
  `not_repay_service_charge` decimal(16,2) DEFAULT '0.00' COMMENT '未还总服务费',
  `not_repay_charge` decimal(16,2) DEFAULT '0.00' COMMENT '未还总手续费',
  `not_repay_penalty` decimal(16,2) DEFAULT '0.00' COMMENT '未还总罚息',
  `cur_pay` decimal(16,2) DEFAULT '0.00' COMMENT '当日放款总计',
  `cur_repayment` decimal(16,2) DEFAULT '0.00' COMMENT '当日还款总计',
  `cur_pre_fee` decimal(16,2) DEFAULT '0.00' COMMENT '当日前期费用总计',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='财务统计表';

-- ----------------------------
--  Table structure for `c_process_engine`
-- ----------------------------
DROP TABLE IF EXISTS `c_process_engine`;
CREATE TABLE `c_process_engine` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cur_node_id` bigint(21) DEFAULT NULL COMMENT '当前ID',
  `next_node_id` varchar(256) DEFAULT NULL COMMENT '前置ID 可多个',
  `type` int(11) DEFAULT NULL COMMENT '类型 0--后续成功节点 1--后续失败节点 2--后续驳回节点 3--前置节点 4--处理中 5--后续同步节点',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程引擎表';

-- ----------------------------
--  Table structure for `c_process_node`
-- ----------------------------
DROP TABLE IF EXISTS `c_process_node`;
CREATE TABLE `c_process_node` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` bigint(21) DEFAULT NULL COMMENT '角色ID',
  `process_name` varchar(128) DEFAULT NULL COMMENT '流程名称',
  `process_status` int(11) DEFAULT NULL COMMENT '流程对应的状态',
  `process_status_desc` varchar(128) DEFAULT NULL COMMENT '流程对应的状态描述',
  `is_back` int(11) DEFAULT '0' COMMENT '是否是驳回节点 0--否 其他--是 表示驳回的角色',
  `is_sync` int(11) DEFAULT '0' COMMENT '是否是同步流程0--否 1--是',
  `sync_field_name` varchar(128) DEFAULT NULL COMMENT '同步字段名称',
  `show_address` varchar(255) DEFAULT '/cust/submitCommon.html' COMMENT '跳转页面地址',
  `interface_address` varchar(255) DEFAULT NULL COMMENT '提交接口地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0-否 1-是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程节点表';

-- ----------------------------
--  Table structure for `c_repayment_approve_record`
-- ----------------------------
DROP TABLE IF EXISTS `c_repayment_approve_record`;
CREATE TABLE `c_repayment_approve_record` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `repayment_id` bigint(21) DEFAULT NULL COMMENT '还款总表id',
  `operator_id` bigint(21) DEFAULT NULL COMMENT '操作员id',
  `operator_name` varchar(25) DEFAULT NULL COMMENT '操作员姓名',
  `operator_time` datetime DEFAULT NULL COMMENT '操作时间',
  `operator_result` int(11) DEFAULT NULL COMMENT '审批结果 0--未通过 1--通过',
  `operator_tip` varchar(255) DEFAULT NULL COMMENT '审批意见',
  `pre_status` int(11) NOT NULL COMMENT '前置状态',
  `pre_status_desc` varchar(128) DEFAULT NULL COMMENT '前置状态描述',
  `after_status` int(11) NOT NULL COMMENT '操作后状态',
  `after_status_desc` varchar(128) DEFAULT NULL COMMENT '操作后状态描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_repayment_id` (`repayment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提前还款审批记录表';

-- ----------------------------
--  Table structure for `c_repayment_change_record`
-- ----------------------------
DROP TABLE IF EXISTS `c_repayment_change_record`;
CREATE TABLE `c_repayment_change_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `repayment_id` bigint(20) NOT NULL COMMENT '还款信息ID',
  `repayment_plan_id` bigint(20) DEFAULT NULL COMMENT '原还款计划',
  `change_type` int(3) DEFAULT '0' COMMENT '变更类型 0--逾期  1--一次性提前还款',
  `change_desc` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '变更描述',
  `old_overdue_days` int(3) DEFAULT '0' COMMENT '原逾期天数',
  `old_overdue_penalty` decimal(16,2) DEFAULT '0.00' COMMENT '原逾期罚金',
  `new_overdue_days` int(3) DEFAULT '0' COMMENT '现逾期天数',
  `new_overdue_penalty` decimal(16,2) DEFAULT '0.00' COMMENT '现逾期罚金',
  `once_repayment_capital` decimal(16,0) DEFAULT '0' COMMENT '一次性提前还款剩余本金',
  `once_repayment_charge` decimal(16,2) DEFAULT '0.00' COMMENT '一次性提前还款剩余手续费',
  `once_repayment_breach` decimal(16,2) DEFAULT '0.00' COMMENT '一次性提前还款罚金',
  `once_repayment_total` decimal(16,2) DEFAULT '0.00' COMMENT '一次性提前还款总额',
  `cur_period_num` int(11) DEFAULT '0' COMMENT '当期期数',
  `cur_period_amount` decimal(16,2) DEFAULT '0.00' COMMENT '当期还款金额',
  `old_amount` decimal(10,2) DEFAULT '0.00' COMMENT '原总金额',
  `old_interest` decimal(10,2) DEFAULT '0.00' COMMENT '原总利息',
  `old_service_charge` decimal(10,2) DEFAULT '0.00' COMMENT '原总服务费',
  `old_month_lease_total` decimal(10,2) DEFAULT '0.00' COMMENT '原租赁合同总计',
  `old_month_service_total` decimal(10,2) DEFAULT '0.00' COMMENT '原服务合同总计',
  `old_lease_capital_total` decimal(10,2) DEFAULT '0.00' COMMENT '原租赁本金总计',
  `old_lease_interest_total` decimal(10,2) DEFAULT '0.00' COMMENT '原租赁利息总计',
  `new_amount` decimal(10,2) DEFAULT '0.00' COMMENT '现总金额',
  `new_interest` decimal(10,2) DEFAULT '0.00' COMMENT '现总利息',
  `new_service_charge` decimal(10,2) DEFAULT '0.00' COMMENT '现总服务费',
  `new_month_lease_total` decimal(10,2) DEFAULT '0.00' COMMENT '原租赁合同总计',
  `new_month_service_total` decimal(10,2) DEFAULT '0.00' COMMENT '原服务合同总计',
  `new_lease_capital_total` decimal(10,2) DEFAULT '0.00' COMMENT '原租赁本金总计',
  `new_lease_interest_total` decimal(10,2) DEFAULT '0.00' COMMENT '原租赁利息总计',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_repayment_id` (`repayment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='还款变更记录表';

-- ----------------------------
--  Table structure for `c_repayment_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_repayment_info`;
CREATE TABLE `c_repayment_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `dept_id` bigint(21) DEFAULT NULL COMMENT '部门id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款申请id',
  `contract_no` varchar(32) DEFAULT NULL COMMENT '合同编号',
  `pay_id` bigint(21) DEFAULT NULL COMMENT '放款ID',
  `cust_name` varchar(32) DEFAULT NULL COMMENT '客户姓名',
  `cust_mobile` varchar(16) DEFAULT NULL COMMENT '客户手机号',
  `cust_id_no` varchar(32) DEFAULT NULL COMMENT '客户身份证号',
  `bank_name` varchar(64) DEFAULT NULL COMMENT '客户开户行',
  `bank_card_no` varchar(32) DEFAULT NULL COMMENT '客户银行账号',
  `loan_amount` decimal(10,2) DEFAULT '0.00' COMMENT '借款金额',
  `loan_period` int(11) DEFAULT '0' COMMENT '借款期限',
  `year_rate` decimal(10,4) DEFAULT '0.0000' COMMENT '年化利率',
  `month_rate` decimal(10,4) DEFAULT '0.0000' COMMENT '月利率',
  `pre_service_rate` decimal(10,4) DEFAULT '0.0000' COMMENT '手续费率',
  `once_repayment_rate` decimal(10,4) DEFAULT '0.0000' COMMENT '一次性还款违约金费率',
  `pre_fee` decimal(10,2) DEFAULT '0.00' COMMENT '前期费用',
  `reception_amount` decimal(10,2) DEFAULT '0.00' COMMENT '前台手续费',
  `other_fee` decimal(10,2) DEFAULT '0.00' COMMENT '其他费用',
  `breach_amount` decimal(10,2) DEFAULT '0.00' COMMENT '一次性提前还款违约金',
  `month_lease_total` decimal(10,2) DEFAULT '0.00' COMMENT '租赁合同总计',
  `month_service_total` decimal(10,2) DEFAULT '0.00' COMMENT '服务合同总计',
  `lease_capital_total` decimal(10,2) DEFAULT '0.00' COMMENT '租赁本金总计',
  `lease_interest_total` decimal(10,2) DEFAULT '0.00' COMMENT '租赁利息总计',
  `interest_begin_time` datetime DEFAULT NULL COMMENT '计息开始时间',
  `interest_end_time` datetime DEFAULT NULL COMMENT '计息结束时间',
  `loan_days_num` int(11) DEFAULT '0' COMMENT '总借款天数',
  `predict_amount` decimal(10,2) DEFAULT '0.00' COMMENT '应还总金额',
  `predict_capital` decimal(10,2) DEFAULT '0.00' COMMENT '应还本金',
  `predict_interest` decimal(10,2) DEFAULT '0.00' COMMENT '应还利息',
  `predict_service_charge` decimal(10,2) DEFAULT '0.00' COMMENT '应还服务费',
  `predict_charge` decimal(10,2) DEFAULT '0.00' COMMENT '应还手续费',
  `predict_penalty` decimal(10,2) DEFAULT '0.00' COMMENT '应还罚息',
  `actual_amount` decimal(10,2) DEFAULT '0.00' COMMENT '实还总金额',
  `actual_capital` decimal(10,2) DEFAULT '0.00' COMMENT '实还本金',
  `actual_interest` decimal(10,2) DEFAULT '0.00' COMMENT '实还利息',
  `actual_service_charge` decimal(10,2) DEFAULT '0.00' COMMENT '实还服务费',
  `actual_charge` decimal(10,2) DEFAULT '0.00' COMMENT '实还手续费',
  `actual_penalty` decimal(10,2) DEFAULT '0.00' COMMENT '实还罚息',
  `cur_status` int(11) DEFAULT '1' COMMENT '当前还款状态 1--未开始  2--还款中  3--部分还款  10--申请提前还款 20--业务经理审批 30--财务确认 40--财务经理审核  50--已还完',
  `cur_status_desc` varchar(128) DEFAULT NULL COMMENT '当前状态描述',
  `is_once_early_repayment` int(11) DEFAULT '0' COMMENT '是否是提前还款  0--否 1--是',
  `overdue_times` int(11) DEFAULT NULL COMMENT '逾期次数',
  `current_period_num` int(11) DEFAULT NULL COMMENT '当前还款期号',
  `version` int(11) DEFAULT NULL COMMENT '版本',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_apply_id` (`apply_id`),
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='还款总表';

-- ----------------------------
--  Table structure for `c_repayment_pay_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_repayment_pay_info`;
CREATE TABLE `c_repayment_pay_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `dept_id` bigint(21) DEFAULT NULL COMMENT '部门id',
  `repayment_id` bigint(21) DEFAULT NULL COMMENT '还款id',
  `repayment_plan_id` bigint(21) DEFAULT NULL COMMENT '还款计划id',
  `pay_type` int(11) DEFAULT NULL COMMENT '类型 0-自动还款 1--手动代扣 2--对公转账 3--扣取前期费用 4--手工冲账',
  `pay_type_desc` varchar(128) DEFAULT NULL COMMENT '类型描述',
  `repayment_period_num` int(11) DEFAULT NULL COMMENT '还款期数',
  `serial_no` varchar(32) DEFAULT NULL COMMENT '流水号',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '支付金额',
  `binding_mobile` varchar(18) DEFAULT NULL COMMENT '绑定手机号',
  `pay_status` int(3) DEFAULT '0' COMMENT '支付状态',
  `paying_num` int(11) DEFAULT NULL COMMENT '还款处理中查询次数',
  `pay_code` varchar(20) DEFAULT NULL COMMENT '支付中心code',
  `pay_msg` varchar(200) DEFAULT NULL COMMENT '支付消息',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_channel` varchar(50) DEFAULT NULL COMMENT '支付渠道',
  `pay_bank` varchar(50) DEFAULT NULL COMMENT '支付银行',
  `pay_card` varchar(50) DEFAULT NULL COMMENT '支付银行卡',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_repayment_id` (`repayment_id`),
  KEY `index_repayment_plan_id` (`repayment_plan_id`),
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='还款支付表';

-- ----------------------------
--  Table structure for `c_repayment_plan_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_repayment_plan_info`;
CREATE TABLE `c_repayment_plan_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `cust_name` varchar(32) DEFAULT NULL COMMENT '客户姓名',
  `dept_id` bigint(21) DEFAULT NULL COMMENT '部门id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `repayment_id` bigint(21) DEFAULT NULL COMMENT '还款id',
  `period_num` int(11) DEFAULT NULL COMMENT '期数',
  `period_status` int(11) DEFAULT NULL COMMENT '当期状态 1--未开始  2--还款中  3--部分还款  4--已逾期  5--已还完',
  `period_begin_time` datetime DEFAULT NULL COMMENT '当期开始时间',
  `period_end_time` datetime DEFAULT NULL COMMENT '当期结束时间',
  `interest_days` int(11) DEFAULT NULL COMMENT '计息天数',
  `begin_amount` decimal(10,2) DEFAULT '0.00' COMMENT '期初余额',
  `end_amount` decimal(10,2) DEFAULT '0.00' COMMENT '期末余额',
  `pay_amount` decimal(10,2) DEFAULT '0.00' COMMENT '月还款',
  `begin_amount_lease` decimal(10,2) DEFAULT '0.00' COMMENT '租赁期初余额',
  `end_amount_lease` decimal(10,2) DEFAULT '0.00' COMMENT '租赁期末余额',
  `capital_lease` decimal(10,2) DEFAULT '0.00' COMMENT '租赁本金',
  `interest_lease` decimal(10,2) DEFAULT '0.00' COMMENT '租赁利息',
  `lease_total` decimal(10,2) DEFAULT '0.00' COMMENT '租赁合同金额',
  `service_total` decimal(10,2) DEFAULT '0.00' COMMENT '服务合同金额',
  `predict_amount` decimal(10,2) DEFAULT '0.00' COMMENT '当期应还总金额',
  `predict_capital` decimal(10,2) DEFAULT '0.00' COMMENT '当期应还本金',
  `predict_interest` decimal(10,2) DEFAULT '0.00' COMMENT '当期应还利息',
  `predict_service_charge` decimal(10,2) DEFAULT '0.00' COMMENT '当期应还服务费',
  `predict_charge` decimal(10,2) DEFAULT '0.00' COMMENT '当期应收手续费',
  `predict_penalty` decimal(10,2) DEFAULT '0.00' COMMENT '当期应还罚息',
  `actual_amount` decimal(10,2) DEFAULT '0.00' COMMENT '当期实还总金额',
  `actual_capital` decimal(10,2) DEFAULT '0.00' COMMENT '当期实还本金',
  `actual_interest` decimal(10,2) DEFAULT '0.00' COMMENT '当期实还利息',
  `actual_service_charge` decimal(10,2) DEFAULT '0.00' COMMENT '当期实还服务费',
  `actual_charge` decimal(10,2) DEFAULT '0.00' COMMENT '当期实还手续费',
  `actual_penalty` decimal(10,2) DEFAULT '0.00' COMMENT '当期实还罚息',
  `is_overdue` int(1) DEFAULT '0' COMMENT '是否逾期 0-否 1-是',
  `is_lock` int(1) DEFAULT '0' COMMENT '是否被锁定 0--否 1--是',
  `overdue_days` int(11) DEFAULT '0' COMMENT '逾期天数',
  `overdue_penalty` decimal(10,2) DEFAULT '0.00' COMMENT '逾期罚息金额',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_apply_id` (`apply_id`),
  KEY `index_repayment_id` (`repayment_id`),
  KEY `index_cust_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='还款计划表';

-- ----------------------------
--  Table structure for `c_supplement_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_supplement_info`;
CREATE TABLE `c_supplement_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) NOT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `supplement_desc` varchar(255) DEFAULT NULL COMMENT '补充材料说明',
  `initiator` bigint(21) DEFAULT NULL COMMENT '发起人id',
  `nominee` bigint(21) DEFAULT NULL COMMENT '指定人id',
  `supplement_attach_url` varchar(255) DEFAULT NULL COMMENT '补充材料附件地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(1) DEFAULT '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `index_apply_id` (`apply_id`),
  KEY `index_user_id` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='补充材料表';

-- ----------------------------
--  Table structure for `c_telecom_auth_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_telecom_auth_info`;
CREATE TABLE `c_telecom_auth_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `type` int(11) DEFAULT NULL COMMENT '类型 0--主借人 1--共借人',
  `is_real_auth` int(11) DEFAULT NULL COMMENT '是否实名认证 0--否 1--是',
  `native_place` varchar(128) DEFAULT NULL COMMENT '手机归属地',
  `in_time` int(11) DEFAULT NULL COMMENT '入网时长（单位：月）',
  `active_3m` int(11) DEFAULT NULL COMMENT '近三个月通话活跃天数(单位：天)',
  `active_6m` int(11) DEFAULT NULL COMMENT '近六个月通话活跃天数(单位：天)',
  `bill_detail_url` varchar(255) DEFAULT NULL COMMENT '魔蝎账单详情附件地址',
  `report_url` varchar(255) DEFAULT NULL COMMENT '魔蝎报告附件地址',
  `compatible_report_url` varchar(255) DEFAULT NULL COMMENT '兼容报告附件地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `IND_APPLY_ID` (`apply_id`),
  KEY `IND_USER_ID` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户运营商认证表';

-- ----------------------------
--  Table structure for `c_telecom_basic_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_telecom_basic_info`;
CREATE TABLE `c_telecom_basic_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '客户id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款id',
  `type` int(11) DEFAULT NULL COMMENT '类型 0--主借人 1--共借人',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `audit_task_id` varchar(200) DEFAULT NULL COMMENT '运营商认证task_id',
  `audit_status` int(2) DEFAULT NULL COMMENT '运营商认证状态 2--认证中 3--认证完成 4--认证失败 5--认证失效',
  `audit_time` datetime DEFAULT NULL COMMENT '认证时间',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `audit_result` longtext COMMENT '运营商认证结果',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `IND_APPLY_ID` (`apply_id`),
  KEY `IND_USER_ID` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户运营商信息表';

-- ----------------------------
--  Table structure for `c_telecom_call_contact_detail`
-- ----------------------------
DROP TABLE IF EXISTS `c_telecom_call_contact_detail`;
CREATE TABLE `c_telecom_call_contact_detail` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '序号id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款申请ID',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '用户ID',
  `type` int(11) DEFAULT NULL COMMENT '类型 0--主借人 1--共借人',
  `city` varchar(256) DEFAULT NULL COMMENT '联系人号码归属地',
  `peer_num` varchar(256) DEFAULT NULL COMMENT '联系人号码',
  `is_emergency` int(11) DEFAULT '0' COMMENT '是否是紧急联系人 0--否 1--是',
  `p_relation` varchar(256) DEFAULT NULL COMMENT '与联系人关系',
  `group_name` varchar(256) DEFAULT NULL COMMENT '号码类型',
  `company_name` varchar(256) DEFAULT NULL COMMENT '号码标识',
  `call_cnt_1w` varchar(20) DEFAULT NULL COMMENT '近一周通话次数',
  `call_cnt_1m` varchar(20) DEFAULT NULL COMMENT '近一月通话次数',
  `call_cnt_3m` varchar(20) DEFAULT NULL COMMENT '近三月通话次数',
  `call_cnt_6m` varchar(20) DEFAULT NULL COMMENT '近六月通话次数',
  `call_time_3m` varchar(20) DEFAULT NULL COMMENT '近三月通话时长(秒)',
  `call_time_6m` varchar(25) DEFAULT NULL COMMENT '近六月通话时长(秒)',
  `dial_cnt_3m` varchar(20) DEFAULT NULL COMMENT '近三月主叫次数',
  `dial_cnt_6m` varchar(20) DEFAULT NULL COMMENT '近六月主叫次数',
  `dial_time_3m` varchar(25) DEFAULT NULL COMMENT '近三月主叫时长(秒)',
  `dial_time_6m` varchar(20) DEFAULT NULL COMMENT '近六月主叫时长(秒)',
  `dialed_cnt_3m` varchar(20) DEFAULT NULL COMMENT '近三月被叫次数',
  `dialed_cnt_6m` varchar(20) DEFAULT NULL COMMENT '近六月被叫次数',
  `dialed_time_3m` varchar(25) DEFAULT NULL COMMENT '近三月被叫时长(秒)',
  `dialed_time_6m` varchar(25) DEFAULT NULL COMMENT '近六月被叫时长(秒)',
  `call_cnt_morning_3m` varchar(20) DEFAULT NULL COMMENT '近三月早晨通话次数',
  `call_cnt_morning_6m` varchar(20) DEFAULT NULL COMMENT '近六月早晨通话次数',
  `call_cnt_noon_3m` varchar(20) DEFAULT NULL COMMENT '近三月中午通话次数',
  `call_cnt_noon_6m` varchar(20) DEFAULT NULL COMMENT '近六月中午通话次数',
  `call_cnt_afternoon_3m` varchar(20) DEFAULT NULL COMMENT '近三月下午通话次数',
  `call_cnt_afternoon_6m` varchar(20) DEFAULT NULL COMMENT '近六月下午通话次数',
  `call_cnt_evening_3m` varchar(20) DEFAULT NULL COMMENT '近三月晚上通话次数',
  `call_cnt_evening_6m` varchar(20) DEFAULT NULL COMMENT '近六月晚上通话次数',
  `call_cnt_night_3m` varchar(20) DEFAULT NULL COMMENT '近三月凌晨通话次数',
  `call_cnt_night_6m` varchar(20) DEFAULT NULL COMMENT '近六月凌晨通话次数',
  `call_cnt_weekday_3m` varchar(20) DEFAULT NULL COMMENT '近三月工作日通话次数',
  `call_cnt_weekday_6m` varchar(20) DEFAULT NULL COMMENT '近六月工作日通话次数',
  `call_cnt_weekend_3m` varchar(20) DEFAULT NULL COMMENT '近三月周末通话次数',
  `call_cnt_weekend_6m` varchar(20) DEFAULT NULL COMMENT '近六月周末通话次数',
  `call_cnt_holiday_3m` varchar(20) DEFAULT NULL COMMENT '近三月节假日通话次数',
  `call_cnt_holiday_6m` varchar(20) DEFAULT NULL COMMENT '近六月节假日通话次数',
  `call_if_whole_day_3m` varchar(20) DEFAULT NULL COMMENT '近三月是否有全天联系',
  `call_if_whole_day_6m` varchar(20) DEFAULT NULL COMMENT '近六月是否有全天联系',
  `trans_start` varchar(20) DEFAULT NULL COMMENT '第一次通话时间',
  `trans_end` varchar(20) DEFAULT NULL COMMENT '最近一次通话时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `IND_APPLY_ID` (`apply_id`),
  KEY `IND_CUST_ID` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商详细通话记录';

-- ----------------------------
--  Table structure for `c_telecom_call_risk_analysis`
-- ----------------------------
DROP TABLE IF EXISTS `c_telecom_call_risk_analysis`;
CREATE TABLE `c_telecom_call_risk_analysis` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '运营商风险分析表自增id',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款申请ID',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '用户ID',
  `type` int(11) DEFAULT NULL COMMENT '类型 0--主借人 1--共借人',
  `analysis_item` varchar(20) DEFAULT NULL COMMENT '风险项',
  `analysis_desc` varchar(20) DEFAULT NULL COMMENT '风险项描述',
  `call_cnt_1m` int(10) DEFAULT NULL COMMENT '近一月通话次数',
  `call_cnt_3m` int(10) DEFAULT NULL COMMENT '近三月通话次数',
  `call_cnt_6m` int(10) DEFAULT NULL COMMENT '近六月通话次数',
  `avg_call_cnt_3m` float DEFAULT NULL COMMENT '近三月平均通话次数',
  `avg_call_cnt_6m` float DEFAULT NULL COMMENT '近六月平均通话次数',
  `call_time_1m` int(10) DEFAULT NULL COMMENT '近一月通话时长(秒)',
  `call_time_3m` int(10) DEFAULT NULL COMMENT '近三月通话时长(秒)',
  `call_time_6m` int(10) DEFAULT NULL COMMENT '近六月通话时长(秒)',
  `avg_call_time_3m` float DEFAULT NULL COMMENT '近三月平均通话时长(秒)',
  `avg_call_time_6m` float DEFAULT NULL COMMENT '近六月平均通话时长(秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `IND_APPLY_ID` (`apply_id`),
  KEY `IND_CUST_ID` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商风险分析表';

-- ----------------------------
--  Table structure for `c_telecom_friend_circle`
-- ----------------------------
DROP TABLE IF EXISTS `c_telecom_friend_circle`;
CREATE TABLE `c_telecom_friend_circle` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '朋友圈ID',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款申请ID',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '用户ID',
  `type` int(11) DEFAULT NULL COMMENT '类型 0--主借人 1--共借人',
  `peer_number` varchar(20) DEFAULT NULL COMMENT '对方号码',
  `peer_num_loc` varchar(256) DEFAULT NULL COMMENT '联系人号码归属地',
  `group_name` varchar(20) DEFAULT NULL COMMENT '号码类型',
  `company_name` varchar(20) DEFAULT NULL COMMENT '号码标识',
  `call_cnt` varchar(20) DEFAULT NULL COMMENT '通话次数',
  `call_time` varchar(25) DEFAULT NULL COMMENT '通话时长(秒)',
  `dial_cnt` varchar(20) DEFAULT NULL COMMENT '主叫次数',
  `dialed_cnt` varchar(20) DEFAULT NULL COMMENT '被叫次数',
  `dial_time` varchar(25) DEFAULT NULL COMMENT '主叫时长(秒)',
  `dialed_time` varchar(25) DEFAULT NULL COMMENT '被叫时长(秒)',
  `key_top` varchar(20) DEFAULT NULL COMMENT '6月top3或者3月top3  peer_num_top3_3m或者peer_num_top3_6m',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `IND_APPLY_ID` (`apply_id`),
  KEY `IND_CUST_ID` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商通讯录朋友圈top项';

-- ----------------------------
--  Table structure for `c_telecom_roam_info`
-- ----------------------------
DROP TABLE IF EXISTS `c_telecom_roam_info`;
CREATE TABLE `c_telecom_roam_info` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '借款申请ID',
  `cust_id` bigint(21) DEFAULT NULL COMMENT '用户ID',
  `type` int(11) DEFAULT NULL COMMENT '类型 0--主借人 1--共借人',
  `location` varchar(128) DEFAULT NULL COMMENT '漫游地址',
  `roam_day_cnt_3m` int(11) DEFAULT NULL COMMENT '近三月漫游天数',
  `roam_day_cnt_6m` int(11) DEFAULT NULL COMMENT '近六月漫游天数',
  `continue_roam_cnt_3m` int(11) DEFAULT NULL COMMENT '近3月最大连续漫游天数',
  `continue_roam_cnt_6m` int(11) DEFAULT NULL COMMENT '近6月最大连续漫游天数',
  `max_roam_day_cnt_3m` int(11) DEFAULT NULL COMMENT '近3月连续漫游1天以上的次数',
  `max_roam_day_cnt_6m` int(11) DEFAULT NULL COMMENT '近6月连续漫游1天以上的次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (`id`),
  KEY `IND_APPLY_ID` (`apply_id`),
  KEY `IND_CUST_ID` (`cust_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户运营商漫游信息表';

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
--  Records of `dept`
-- ----------------------------
BEGIN;
INSERT INTO `dept` VALUES ('1', '1', '1', '[0],[1],[1],', '山东省', '山东省', '111', null), ('9', '1', '1', '[0],[1],[1],', '济南市', '济南市', '', null), ('10', '1', '9', '[0],[1],[1],[9],', '平阴县', '平阴县', '', null), ('11', '1', '10', '[0],[1],[1],[9],[10],', '锦水街道乡镇', '锦水街道乡镇', '', null), ('12', '1', '11', '[0],[1],[1],[9],[10],[11],', '前寨村委会', '前寨村委会', '', null), ('13', '2', '11', '[0],[1],[1],[9],[10],[11],', '山头村委会', '山头村委会', '', null), ('14', '3', '11', '[0],[1],[1],[9],[10],[11],', '凌庄村委会', '凌庄村委会', '', null), ('15', '4', '11', '[0],[1],[1],[9],[10],[11],', '西子顺村委会', '西子顺村委会', '', null);
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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
--  Records of `dict`
-- ----------------------------
BEGIN;
INSERT INTO `dict` VALUES ('16', '0', '0', '状态', null), ('17', '1', '16', '启用', null), ('18', '2', '16', '禁用', null), ('29', '0', '0', '性别', null), ('30', '1', '29', '男', null), ('31', '2', '29', '女', null), ('35', '0', '0', '账号状态', null), ('36', '1', '35', '启用', null), ('37', '2', '35', '冻结', null), ('38', '3', '35', '已删除', null), ('39', '0', '0', '调用车300接口token', null), ('40', '300', '43', '5614a745174c4aa880ed42e907c5147c', null), ('41', '0', '0', '调用运营商认证接口token', null), ('42', '100', '47', '8c341f8c37b680dc356a0b936f33b9d7', null);
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
) ENGINE=InnoDB AUTO_INCREMENT=12953 DEFAULT CHARSET=utf8 COMMENT='登录记录';

-- ----------------------------
--  Records of `login_log`
-- ----------------------------
BEGIN;
INSERT INTO `login_log` VALUES ('12823', '登录日志', '58', '2018-09-29 21:25:03', '成功', null, '0:0:0:0:0:0:0:1'), ('12824', '登录日志', '1', '2018-10-03 20:57:13', '成功', null, '192.168.1.3'), ('12825', '退出日志', '58', '2018-10-06 11:58:26', '成功', null, '0:0:0:0:0:0:0:1'), ('12826', '登录日志', '1', '2018-10-06 11:58:32', '成功', null, '0:0:0:0:0:0:0:1'), ('12827', '退出日志', '1', '2018-10-06 12:35:53', '成功', null, '0:0:0:0:0:0:0:1'), ('12828', '登录日志', '1', '2018-10-06 12:35:55', '成功', null, '0:0:0:0:0:0:0:1'), ('12829', '退出日志', '1', '2018-10-06 12:40:53', '成功', null, '0:0:0:0:0:0:0:1'), ('12830', '登录日志', '1', '2018-10-06 12:40:54', '成功', null, '0:0:0:0:0:0:0:1'), ('12831', '退出日志', '1', '2018-10-06 13:38:50', '成功', null, '0:0:0:0:0:0:0:1'), ('12832', '登录日志', '1', '2018-10-06 13:48:12', '成功', null, '0:0:0:0:0:0:0:1'), ('12833', '登录日志', '1', '2018-10-06 13:49:19', '成功', null, '0:0:0:0:0:0:0:1'), ('12834', '退出日志', '1', '2018-10-06 13:49:32', '成功', null, '0:0:0:0:0:0:0:1'), ('12835', '登录日志', '1', '2018-10-06 13:49:42', '成功', null, '0:0:0:0:0:0:0:1'), ('12836', '登录日志', '1', '2018-10-06 13:50:08', '成功', null, '0:0:0:0:0:0:0:1'), ('12837', '登录日志', '1', '2018-10-06 13:51:18', '成功', null, '0:0:0:0:0:0:0:1'), ('12838', '退出日志', '1', '2018-10-06 14:00:48', '成功', null, '0:0:0:0:0:0:0:1'), ('12839', '登录日志', '1', '2018-10-06 14:02:10', '成功', null, '0:0:0:0:0:0:0:1'), ('12840', '退出日志', '1', '2018-10-06 22:12:05', '成功', null, '0:0:0:0:0:0:0:1'), ('12841', '登录日志', '1', '2018-10-06 22:12:16', '成功', null, '0:0:0:0:0:0:0:1'), ('12842', '退出日志', '1', '2018-10-06 22:12:32', '成功', null, '0:0:0:0:0:0:0:1'), ('12843', '登录日志', '1', '2018-10-06 22:12:38', '成功', null, '0:0:0:0:0:0:0:1'), ('12844', '退出日志', '1', '2018-10-07 20:41:48', '成功', null, '0:0:0:0:0:0:0:1'), ('12845', '登录日志', '1', '2018-10-07 20:41:52', '成功', null, '0:0:0:0:0:0:0:1'), ('12846', '登录日志', '1', '2018-10-09 15:18:18', '成功', null, '0:0:0:0:0:0:0:1'), ('12847', '退出日志', '1', '2018-10-11 09:10:35', '成功', null, '0:0:0:0:0:0:0:1'), ('12848', '登录日志', '1', '2018-10-11 09:10:43', '成功', null, '0:0:0:0:0:0:0:1'), ('12849', '退出日志', '1', '2018-10-11 09:49:17', '成功', null, '0:0:0:0:0:0:0:1'), ('12850', '登录日志', '1', '2018-10-11 09:49:25', '成功', null, '0:0:0:0:0:0:0:1'), ('12851', '退出日志', '1', '2018-10-11 10:25:50', '成功', null, '0:0:0:0:0:0:0:1'), ('12852', '登录日志', '1', '2018-10-11 10:25:58', '成功', null, '0:0:0:0:0:0:0:1'), ('12853', '退出日志', '1', '2018-10-11 10:31:03', '成功', null, '0:0:0:0:0:0:0:1'), ('12854', '登录日志', '1', '2018-10-11 10:31:10', '成功', null, '0:0:0:0:0:0:0:1'), ('12855', '退出日志', '1', '2018-10-11 10:44:11', '成功', null, '0:0:0:0:0:0:0:1'), ('12856', '登录日志', '1', '2018-10-11 10:44:19', '成功', null, '0:0:0:0:0:0:0:1'), ('12857', '退出日志', '1', '2018-10-11 10:51:14', '成功', null, '0:0:0:0:0:0:0:1'), ('12858', '登录日志', '1', '2018-10-11 10:51:21', '成功', null, '0:0:0:0:0:0:0:1'), ('12859', '退出日志', '1', '2018-10-11 10:57:39', '成功', null, '0:0:0:0:0:0:0:1'), ('12860', '登录日志', '1', '2018-10-11 10:57:45', '成功', null, '0:0:0:0:0:0:0:1'), ('12861', '退出日志', '1', '2018-10-11 11:07:32', '成功', null, '0:0:0:0:0:0:0:1'), ('12862', '登录日志', '1', '2018-10-11 11:07:39', '成功', null, '0:0:0:0:0:0:0:1'), ('12863', '退出日志', '1', '2018-10-11 14:27:48', '成功', null, '0:0:0:0:0:0:0:1'), ('12864', '登录日志', '1', '2018-10-11 14:27:56', '成功', null, '0:0:0:0:0:0:0:1'), ('12865', '退出日志', '1', '2018-10-11 14:29:48', '成功', null, '0:0:0:0:0:0:0:1'), ('12866', '登录日志', '1', '2018-10-11 14:29:56', '成功', null, '0:0:0:0:0:0:0:1'), ('12867', '退出日志', '1', '2018-10-11 14:54:10', '成功', null, '0:0:0:0:0:0:0:1'), ('12868', '登录日志', '1', '2018-10-11 14:54:19', '成功', null, '0:0:0:0:0:0:0:1'), ('12869', '退出日志', '1', '2018-10-11 15:47:18', '成功', null, '0:0:0:0:0:0:0:1'), ('12870', '登录日志', '1', '2018-10-11 15:47:26', '成功', null, '0:0:0:0:0:0:0:1'), ('12871', '登录日志', '1', '2018-10-11 17:27:14', '成功', null, '172.16.0.90'), ('12872', '退出日志', '1', '2018-10-11 19:45:07', '成功', null, '0:0:0:0:0:0:0:1'), ('12873', '登录日志', '1', '2018-10-11 19:45:13', '成功', null, '0:0:0:0:0:0:0:1'), ('12874', '退出日志', '1', '2018-10-11 20:23:13', '成功', null, '0:0:0:0:0:0:0:1'), ('12875', '登录日志', '1', '2018-10-11 20:23:21', '成功', null, '0:0:0:0:0:0:0:1'), ('12876', '退出日志', '1', '2018-10-12 09:56:28', '成功', null, '0:0:0:0:0:0:0:1'), ('12877', '登录日志', '1', '2018-10-12 09:56:36', '成功', null, '0:0:0:0:0:0:0:1'), ('12878', '退出日志', '1', '2018-10-12 11:21:34', '成功', null, '0:0:0:0:0:0:0:1'), ('12879', '登录日志', '1', '2018-10-12 11:21:42', '成功', null, '0:0:0:0:0:0:0:1'), ('12880', '退出日志', '1', '2018-10-12 13:48:14', '成功', null, '0:0:0:0:0:0:0:1'), ('12881', '登录日志', '1', '2018-10-12 13:48:25', '成功', null, '0:0:0:0:0:0:0:1'), ('12882', '退出日志', '1', '2018-10-12 15:55:36', '成功', null, '0:0:0:0:0:0:0:1'), ('12883', '登录日志', '1', '2018-10-12 15:55:43', '成功', null, '0:0:0:0:0:0:0:1'), ('12884', '退出日志', '1', '2018-10-12 16:49:05', '成功', null, '0:0:0:0:0:0:0:1'), ('12885', '登录日志', '1', '2018-10-12 16:49:14', '成功', null, '0:0:0:0:0:0:0:1'), ('12886', '退出日志', '1', '2018-10-12 17:07:35', '成功', null, '0:0:0:0:0:0:0:1'), ('12887', '登录日志', '1', '2018-10-12 17:07:42', '成功', null, '0:0:0:0:0:0:0:1'), ('12888', '退出日志', '1', '2018-10-12 17:17:05', '成功', null, '0:0:0:0:0:0:0:1'), ('12889', '登录日志', '1', '2018-10-12 17:17:11', '成功', null, '0:0:0:0:0:0:0:1'), ('12890', '退出日志', '1', '2018-10-12 20:41:43', '成功', null, '0:0:0:0:0:0:0:1'), ('12891', '登录日志', '1', '2018-10-12 20:41:46', '成功', null, '0:0:0:0:0:0:0:1'), ('12892', '退出日志', '1', '2018-10-12 21:47:36', '成功', null, '0:0:0:0:0:0:0:1'), ('12893', '登录日志', '1', '2018-10-12 21:47:39', '成功', null, '0:0:0:0:0:0:0:1'), ('12894', '退出日志', '1', '2018-10-12 21:56:09', '成功', null, '0:0:0:0:0:0:0:1'), ('12895', '登录日志', '1', '2018-10-12 21:56:13', '成功', null, '0:0:0:0:0:0:0:1'), ('12896', '退出日志', '1', '2018-10-12 21:59:07', '成功', null, '0:0:0:0:0:0:0:1'), ('12897', '登录日志', '1', '2018-10-12 21:59:10', '成功', null, '0:0:0:0:0:0:0:1'), ('12898', '退出日志', '1', '2018-10-12 22:10:59', '成功', null, '0:0:0:0:0:0:0:1'), ('12899', '登录日志', '1', '2018-10-12 22:11:22', '成功', null, '0:0:0:0:0:0:0:1'), ('12900', '退出日志', '1', '2018-10-12 22:24:16', '成功', null, '0:0:0:0:0:0:0:1'), ('12901', '登录日志', '1', '2018-10-12 22:24:19', '成功', null, '0:0:0:0:0:0:0:1'), ('12902', '退出日志', '1', '2018-10-12 22:50:54', '成功', null, '0:0:0:0:0:0:0:1'), ('12903', '登录日志', '1', '2018-10-12 22:50:57', '成功', null, '0:0:0:0:0:0:0:1'), ('12904', '退出日志', '1', '2018-10-12 22:54:53', '成功', null, '0:0:0:0:0:0:0:1'), ('12905', '登录日志', '1', '2018-10-12 22:54:56', '成功', null, '0:0:0:0:0:0:0:1'), ('12906', '退出日志', '1', '2018-10-13 10:18:10', '成功', null, '0:0:0:0:0:0:0:1'), ('12907', '登录日志', '1', '2018-10-13 10:18:13', '成功', null, '0:0:0:0:0:0:0:1'), ('12908', '退出日志', '1', '2018-10-13 10:31:30', '成功', null, '0:0:0:0:0:0:0:1'), ('12909', '登录日志', '1', '2018-10-13 10:31:33', '成功', null, '0:0:0:0:0:0:0:1'), ('12910', '退出日志', '1', '2018-10-13 10:48:31', '成功', null, '0:0:0:0:0:0:0:1'), ('12911', '登录日志', '1', '2018-10-13 10:48:41', '成功', null, '0:0:0:0:0:0:0:1'), ('12912', '退出日志', '1', '2018-10-13 11:01:10', '成功', null, '0:0:0:0:0:0:0:1'), ('12913', '登录日志', '1', '2018-10-13 11:01:14', '成功', null, '0:0:0:0:0:0:0:1'), ('12914', '退出日志', '1', '2018-10-13 14:37:45', '成功', null, '0:0:0:0:0:0:0:1'), ('12915', '登录日志', '1', '2018-10-13 14:37:48', '成功', null, '0:0:0:0:0:0:0:1'), ('12916', '退出日志', '1', '2018-10-13 22:38:00', '成功', null, '0:0:0:0:0:0:0:1'), ('12917', '登录日志', '1', '2018-10-13 22:38:17', '成功', null, '0:0:0:0:0:0:0:1'), ('12918', '登录日志', '1', '2018-10-13 22:47:28', '成功', null, '192.168.1.101'), ('12919', '退出日志', '1', '2018-10-14 09:00:18', '成功', null, '0:0:0:0:0:0:0:1'), ('12920', '登录日志', '1', '2018-10-14 09:00:22', '成功', null, '0:0:0:0:0:0:0:1'), ('12921', '退出日志', '1', '2018-10-14 09:21:52', '成功', null, '0:0:0:0:0:0:0:1'), ('12922', '登录日志', '1', '2018-10-14 09:21:55', '成功', null, '0:0:0:0:0:0:0:1'), ('12923', '退出日志', '1', '2018-10-14 10:05:56', '成功', null, '0:0:0:0:0:0:0:1'), ('12924', '登录日志', '1', '2018-10-14 10:05:59', '成功', null, '0:0:0:0:0:0:0:1'), ('12925', '退出日志', '1', '2018-10-14 14:12:23', '成功', null, '0:0:0:0:0:0:0:1'), ('12926', '登录日志', '1', '2018-10-14 14:12:27', '成功', null, '0:0:0:0:0:0:0:1'), ('12927', '退出日志', '1', '2018-10-14 14:19:39', '成功', null, '0:0:0:0:0:0:0:1'), ('12928', '登录日志', '1', '2018-10-14 14:19:43', '成功', null, '0:0:0:0:0:0:0:1'), ('12929', '退出日志', '1', '2018-10-14 14:33:24', '成功', null, '0:0:0:0:0:0:0:1'), ('12930', '登录日志', '1', '2018-10-14 14:33:27', '成功', null, '0:0:0:0:0:0:0:1'), ('12931', '退出日志', '1', '2018-10-14 20:33:16', '成功', null, '0:0:0:0:0:0:0:1'), ('12932', '登录日志', '1', '2018-10-14 20:33:22', '成功', null, '0:0:0:0:0:0:0:1'), ('12933', '退出日志', '1', '2018-10-18 20:33:13', '成功', null, '0:0:0:0:0:0:0:1'), ('12934', '登录日志', '1', '2018-10-18 20:33:16', '成功', null, '0:0:0:0:0:0:0:1'), ('12935', '登录日志', '1', '2018-10-18 21:04:37', '成功', null, '192.168.1.101'), ('12936', '登录日志', '1', '2018-10-18 21:07:20', '成功', null, '192.168.1.100'), ('12937', '登录日志', '1', '2018-10-20 07:59:01', '成功', null, '192.168.1.102'), ('12938', '退出日志', '1', '2018-10-20 08:57:30', '成功', null, '192.168.1.102'), ('12939', '登录日志', '1', '2018-10-20 10:45:07', '成功', null, '192.168.1.102'), ('12940', '退出日志', '1', '2018-10-20 10:56:36', '成功', null, '192.168.1.102'), ('12941', '登录失败日志', null, '2018-10-20 11:08:02', '成功', '账号:admin,账号密码错误', '192.168.1.102'), ('12942', '登录日志', '1', '2018-10-20 11:08:14', '成功', null, '192.168.1.102'), ('12943', '退出日志', '1', '2018-10-20 11:13:56', '成功', null, '192.168.1.102'), ('12944', '登录日志', '1', '2018-10-20 11:14:25', '成功', null, '192.168.1.102'), ('12945', '退出日志', '1', '2018-10-21 21:12:05', '成功', null, '0:0:0:0:0:0:0:1'), ('12946', '登录日志', '1', '2018-10-21 21:12:07', '成功', null, '0:0:0:0:0:0:0:1'), ('12947', '退出日志', '1', '2018-10-21 21:50:53', '成功', null, '0:0:0:0:0:0:0:1'), ('12948', '登录日志', '1', '2018-10-21 21:50:55', '成功', null, '0:0:0:0:0:0:0:1'), ('12949', '登录日志', '1', '2018-10-21 21:51:15', '成功', null, '0:0:0:0:0:0:0:1'), ('12950', '退出日志', '1', '2018-10-21 21:51:43', '成功', null, '0:0:0:0:0:0:0:1'), ('12951', '登录日志', '1', '2018-10-21 21:51:53', '成功', null, '0:0:0:0:0:0:0:1'), ('12952', '登录日志', '1', '2018-10-21 21:53:44', '成功', null, '0:0:0:0:0:0:0:1');
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
) ENGINE=InnoDB AUTO_INCREMENT=229 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
--  Records of `menu`
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES ('1', 'system', '0', '[0],', '系统管理', 'fa-user', '', '3', '1', '1', null, '1', '1'), ('2', 'mgr', 'system', '[0],[system],', '用户管理', '', '/mgr', '1', '2', '1', null, '1', '0'), ('3', 'mgr_add', 'mgr', '[0],[system],[mgr],', '添加用户', null, '/mgr/add', '1', '3', '0', null, '1', '0'), ('4', 'mgr_edit', 'mgr', '[0],[system],[mgr],', '修改用户', null, '/mgr/edit', '2', '3', '0', null, '1', '0'), ('5', 'mgr_delete', 'mgr', '[0],[system],[mgr],', '删除用户', null, '/mgr/delete', '3', '3', '0', null, '1', '0'), ('6', 'mgr_reset', 'mgr', '[0],[system],[mgr],', '重置密码', null, '/mgr/reset', '4', '3', '0', null, '1', '0'), ('7', 'mgr_freeze', 'mgr', '[0],[system],[mgr],', '冻结用户', null, '/mgr/freeze', '5', '3', '0', null, '1', '0'), ('8', 'mgr_unfreeze', 'mgr', '[0],[system],[mgr],', '解除冻结用户', null, '/mgr/unfreeze', '6', '3', '0', null, '1', '0'), ('9', 'mgr_setRole', 'mgr', '[0],[system],[mgr],', '分配角色', null, '/mgr/setRole', '7', '3', '0', null, '1', '0'), ('10', 'role', 'system', '[0],[system],', '角色管理', null, '/role', '2', '2', '1', null, '1', '0'), ('11', 'role_add', 'role', '[0],[system],[role],', '添加角色', null, '/role/add', '1', '3', '0', null, '1', '0'), ('12', 'role_edit', 'role', '[0],[system],[role],', '修改角色', null, '/role/edit', '2', '3', '0', null, '1', '0'), ('13', 'role_remove', 'role', '[0],[system],[role],', '删除角色', null, '/role/remove', '3', '3', '0', null, '1', '0'), ('14', 'role_setAuthority', 'role', '[0],[system],[role],', '配置权限', null, '/role/setAuthority', '4', '3', '0', null, '1', '0'), ('15', 'menu', 'system', '[0],[system],', '菜单管理', null, '/menu', '4', '2', '1', null, '1', '0'), ('16', 'menu_add', 'menu', '[0],[system],[menu],', '添加菜单', null, '/menu/add', '1', '3', '0', null, '1', '0'), ('17', 'menu_edit', 'menu', '[0],[system],[menu],', '修改菜单', null, '/menu/edit', '2', '3', '0', null, '1', '0'), ('18', 'menu_remove', 'menu', '[0],[system],[menu],', '删除菜单', null, '/menu/remove', '3', '3', '0', null, '1', '0'), ('19', 'log', 'system', '[0],[system],', '业务日志', null, '/log', '6', '2', '1', null, '1', '0'), ('20', 'druid', 'system', '[0],[system],', '监控管理', null, '/druid', '7', '2', '1', null, '1', null), ('21', 'dept', 'system', '[0],[system],', '部门管理', null, '/dept', '3', '2', '1', null, '1', null), ('22', 'dict', 'system', '[0],[system],', '字典管理', null, '/dict', '4', '2', '1', null, '1', null), ('23', 'loginLog', 'system', '[0],[system],', '登录日志', null, '/loginLog', '6', '2', '1', null, '1', null), ('24', 'log_clean', 'log', '[0],[system],[log],', '清空日志', null, '/log/delLog', '3', '3', '0', null, '1', null), ('25', 'dept_add', 'dept', '[0],[system],[dept],', '添加部门', null, '/dept/add', '1', '3', '0', null, '1', null), ('26', 'dept_update', 'dept', '[0],[system],[dept],', '修改部门', null, '/dept/update', '1', '3', '0', null, '1', null), ('27', 'dept_delete', 'dept', '[0],[system],[dept],', '删除部门', null, '/dept/delete', '1', '3', '0', null, '1', null), ('28', 'dict_add', 'dict', '[0],[system],[dict],', '添加字典', null, '/dict/add', '1', '3', '0', null, '1', null), ('29', 'dict_update', 'dict', '[0],[system],[dict],', '修改字典', null, '/dict/update', '1', '3', '0', null, '1', null), ('30', 'dict_delete', 'dict', '[0],[system],[dict],', '删除字典', null, '/dict/delete', '1', '3', '0', null, '1', null), ('36', 'code', 'system', '[0],[system],', '代码生成', 'fa-user', '/code', '10', '2', '1', null, '0', null), ('37', 'api_mgr', '0', '[0],', '接口文档', 'fa-leaf', '/swagger-ui.html', '2', '1', '1', null, '0', null), ('38', 'to_menu_edit', 'menu', '[0],[system],[menu],', '菜单编辑跳转', '', '/menu/menu_edit', '4', '3', '0', null, '1', null), ('39', 'menu_list', 'menu', '[0],[system],[menu],', '菜单列表', '', '/menu/list', '5', '3', '0', null, '1', null), ('40', 'to_dept_update', 'dept', '[0],[system],[dept],', '修改部门跳转', '', '/dept/dept_update', '4', '3', '0', null, '1', null), ('41', 'dept_list', 'dept', '[0],[system],[dept],', '部门列表', '', '/dept/list', '5', '3', '0', null, '1', null), ('42', 'dept_detail', 'dept', '[0],[system],[dept],', '部门详情', '', '/dept/detail', '6', '3', '0', null, '1', null), ('43', 'to_dict_edit', 'dict', '[0],[system],[dict],', '修改菜单跳转', '', '/dict/dict_edit', '4', '3', '0', null, '1', null), ('44', 'dict_list', 'dict', '[0],[system],[dict],', '字典列表', '', '/dict/list', '5', '3', '0', null, '1', null), ('45', 'dict_detail', 'dict', '[0],[system],[dict],', '字典详情', '', '/dict/detail', '6', '3', '0', null, '1', null), ('46', 'log_list', 'log', '[0],[system],[log],', '日志列表', '', '/log/list', '2', '3', '0', null, '1', null), ('47', 'log_detail', 'log', '[0],[system],[log],', '日志详情', '', '/log/detail', '3', '3', '0', null, '1', null), ('48', 'del_login_log', 'loginLog', '[0],[system],[loginLog],', '清空登录日志', '', '/loginLog/delLoginLog', '1', '3', '0', null, '1', null), ('49', 'login_log_list', 'loginLog', '[0],[system],[loginLog],', '登录日志列表', '', '/loginLog/list', '2', '3', '0', null, '1', null), ('50', 'to_role_edit', 'role', '[0],[system],[role],', '修改角色跳转', '', '/role/role_edit', '5', '3', '0', null, '1', null), ('51', 'to_role_assign', 'role', '[0],[system],[role],', '角色分配跳转', '', '/role/role_assign', '6', '3', '0', null, '1', null), ('52', 'role_list', 'role', '[0],[system],[role],', '角色列表', '', '/role/list', '7', '3', '0', null, '1', null), ('53', 'to_assign_role', 'mgr', '[0],[system],[mgr],', '分配角色跳转', '', '/mgr/role_assign', '8', '3', '0', null, '1', null), ('54', 'to_user_edit', 'mgr', '[0],[system],[mgr],', '编辑用户跳转', '', '/mgr/user_edit', '9', '3', '0', null, '1', null), ('55', 'mgr_list', 'mgr', '[0],[system],[mgr],', '用户列表', '', '/mgr/list', '10', '3', '0', null, '1', null), ('227', 'tjManager', '0', '[0],', '数据统计', 'fa-user', '/', '1', '1', '1', null, '1', null), ('228', 'payStatisInfo', 'tjManager', '[0],[tjManager]', '日报表统计', null, '/payStatisInfo/index', '1', '2', '1', null, '1', null);
COMMIT;

-- ----------------------------
--  Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `recuserid` int(11) NOT NULL DEFAULT '0' COMMENT '接受用户id',
  `senuserid` int(11) NOT NULL DEFAULT '0' COMMENT '发送用户id',
  `recusername` varchar(10) NOT NULL DEFAULT '' COMMENT '接受用户姓名',
  `senusername` varchar(10) DEFAULT '' COMMENT '发用户姓名',
  `title` varchar(10) DEFAULT '' COMMENT '标题',
  `content` varchar(255) DEFAULT '' COMMENT '内容',
  `status` int(1) DEFAULT '0' COMMENT '1启用 2禁用',
  `type` int(1) DEFAULT '0' COMMENT '类型 详情待定',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT '' COMMENT '备用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='通知表';

-- ----------------------------
--  Records of `notice`
-- ----------------------------
BEGIN;
INSERT INTO `notice` VALUES ('6', '世界', '10', '欢迎使用CIS', '2017-01-11 08:53:20', '1'), ('8', '你好', null, '你好', '2017-05-10 19:28:57', '1'), ('9', '问问', null, '问问', '2018-07-05 13:59:02', '1');
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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- ----------------------------
--  Records of `operation_log`
-- ----------------------------
BEGIN;
INSERT INTO `operation_log` VALUES ('1', '业务日志', '修改部门', '1', 'com.moerlong.carloan.modular.system.controller.DeptController', 'update', '2018-10-06 11:59:32', '成功', '部门简称=山东省;;;'), ('2', '业务日志', '删除部门', '1', 'com.moerlong.carloan.modular.system.controller.DeptController', 'delete', '2018-10-06 11:59:44', '成功', '部门名称=UAT'), ('3', '业务日志', '添加部门', '1', 'com.moerlong.carloan.modular.system.controller.DeptController', 'add', '2018-10-06 12:00:05', '成功', '部门简称=济南市'), ('4', '业务日志', '添加部门', '1', 'com.moerlong.carloan.modular.system.controller.DeptController', 'add', '2018-10-06 12:00:31', '成功', '部门简称=平阴县'), ('5', '业务日志', '删除部门', '1', 'com.moerlong.carloan.modular.system.controller.DeptController', 'delete', '2018-10-06 12:00:40', '成功', '部门名称=成都分公司'), ('6', '业务日志', '删除部门', '1', 'com.moerlong.carloan.modular.system.controller.DeptController', 'delete', '2018-10-06 12:00:46', '成功', '部门名称=北京分公司'), ('7', '业务日志', '删除部门', '1', 'com.moerlong.carloan.modular.system.controller.DeptController', 'delete', '2018-10-06 12:00:51', '成功', '部门名称=重庆分公司'), ('8', '业务日志', '删除部门', '1', 'com.moerlong.carloan.modular.system.controller.DeptController', 'delete', '2018-10-06 12:00:55', '成功', '部门名称=技术研发部'), ('9', '业务日志', '删除部门', '1', 'com.moerlong.carloan.modular.system.controller.DeptController', 'delete', '2018-10-06 12:00:59', '成功', '部门名称=产品验收'), ('10', '业务日志', '添加部门', '1', 'com.moerlong.carloan.modular.system.controller.DeptController', 'add', '2018-10-06 12:02:21', '成功', '部门简称=锦水街道乡镇'), ('11', '业务日志', '添加部门', '1', 'com.moerlong.carloan.modular.system.controller.DeptController', 'add', '2018-10-06 12:02:56', '成功', '部门简称=前寨村委会'), ('12', '业务日志', '添加部门', '1', 'com.moerlong.carloan.modular.system.controller.DeptController', 'add', '2018-10-06 12:03:21', '成功', '部门简称=山头村委会'), ('13', '业务日志', '添加部门', '1', 'com.moerlong.carloan.modular.system.controller.DeptController', 'add', '2018-10-06 12:03:45', '成功', '部门简称=凌庄村委会'), ('14', '业务日志', '添加部门', '1', 'com.moerlong.carloan.modular.system.controller.DeptController', 'add', '2018-10-06 12:04:14', '成功', '部门简称=西子顺村委会'), ('15', '异常日志', '', '1', null, null, '2018-10-06 12:05:05', '失败', 'com.moerlong.carloan.common.exception.BussinessException: 权限异常\n	at com.moerlong.carloan.modular.system.controller.UserMgrController.assertAuth(UserMgrController.java:370)\n	at com.moerlong.carloan.modular.system.controller.UserMgrController.delete(UserMgrController.java:245)\n	at com.moerlong.carloan.modular.system.controller.UserMgrControllerTTFastClassBySpringCGLIBTTfdaaf5d2.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:738)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\n	at com.moerlong.carloan.core.aop.PermissionAop.doPermission(PermissionAop.java:52)\n	at sun.reflect.GeneratedMethodAccessor182.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\n	at com.moerlong.carloan.core.aop.LogAop.recordSysLog(LogAop.java:46)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:673)\n	at com.moerlong.carloan.modular.system.controller.UserMgrControllerTTEnhancerBySpringCGLIBTTd93cb2e4.delete(<generated>)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:97)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:967)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:901)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970)\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.moerlong.carloan.core.util.xss.XssFilter.doFilter(XssFilter.java:22)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:108)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:478)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:868)\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:624)\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\n	at java.lang.Thread.run(Thread.java:748)\n'), ('16', '业务日志', '修改管理员', '1', 'com.moerlong.carloan.modular.system.controller.UserMgrController', 'edit', '2018-10-06 12:05:40', '成功', '账号=admin;;;'), ('17', '业务日志', '修改管理员', '1', 'com.moerlong.carloan.modular.system.controller.UserMgrController', 'edit', '2018-10-06 12:06:10', '成功', '账号=admin;;;'), ('18', '异常日志', '', '1', null, null, '2018-10-06 12:06:19', '失败', 'com.moerlong.carloan.common.exception.BussinessException: 权限异常\n	at com.moerlong.carloan.modular.system.controller.UserMgrController.assertAuth(UserMgrController.java:370)\n	at com.moerlong.carloan.modular.system.controller.UserMgrController.delete(UserMgrController.java:245)\n	at com.moerlong.carloan.modular.system.controller.UserMgrControllerTTFastClassBySpringCGLIBTTfdaaf5d2.invoke(<generated>)\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\n	at org.springframework.aop.framework.CglibAopProxyTCglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:738)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\n	at com.moerlong.carloan.core.aop.PermissionAop.doPermission(PermissionAop.java:52)\n	at sun.reflect.GeneratedMethodAccessor182.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\n	at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:85)\n	at com.moerlong.carloan.core.aop.LogAop.recordSysLog(LogAop.java:46)\n	at sun.reflect.GeneratedMethodAccessor217.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:629)\n	at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:618)\n	at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:168)\n	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\n	at org.springframework.aop.framework.CglibAopProxyTDynamicAdvisedInterceptor.intercept(CglibAopProxy.java:673)\n	at com.moerlong.carloan.modular.system.controller.UserMgrControllerTTEnhancerBySpringCGLIBTTd93cb2e4.delete(<generated>)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:498)\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:97)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:967)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:901)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970)\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.moerlong.carloan.core.util.xss.XssFilter.doFilter(XssFilter.java:22)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:108)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:478)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:868)\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:624)\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\n	at java.lang.Thread.run(Thread.java:748)\n'), ('19', '业务日志', '修改管理员', '1', 'com.moerlong.carloan.modular.system.controller.UserMgrController', 'edit', '2018-10-06 12:36:32', '成功', '账号=admin;;;字段名称:部门名称,旧值:总公司,新值:山东省,平阴县'), ('20', '业务日志', '修改管理员', '1', 'com.moerlong.carloan.modular.system.controller.UserMgrController', 'edit', '2018-10-06 12:36:50', '成功', '账号=admin;;;字段名称:部门名称,旧值:山东省,平阴县,新值:山东省,济南市,平阴县'), ('21', '业务日志', '修改管理员', '1', 'com.moerlong.carloan.modular.system.controller.UserMgrController', 'edit', '2018-10-06 12:37:12', '成功', '账号=admin;;;字段名称:性别,旧值:女,新值:男'), ('22', '业务日志', '删除菜单', '1', 'com.moerlong.carloan.modular.system.controller.MenuController', 'remove', '2018-10-06 12:38:49', '成功', '菜单名称=还款管理'), ('23', '业务日志', '删除菜单', '1', 'com.moerlong.carloan.modular.system.controller.MenuController', 'remove', '2018-10-06 12:39:11', '成功', '菜单名称=放款管理'), ('24', '业务日志', '删除菜单', '1', 'com.moerlong.carloan.modular.system.controller.MenuController', 'remove', '2018-10-06 12:39:25', '成功', '菜单名称=车辆信息管理'), ('25', '业务日志', '删除菜单', '1', 'com.moerlong.carloan.modular.system.controller.MenuController', 'remove', '2018-10-06 12:39:35', '成功', '菜单名称=提前还款申请'), ('26', '业务日志', '删除菜单', '1', 'com.moerlong.carloan.modular.system.controller.MenuController', 'remove', '2018-10-06 12:39:49', '成功', '菜单名称=客户信息管理'), ('27', '业务日志', '删除菜单', '1', 'com.moerlong.carloan.modular.system.controller.MenuController', 'remove', '2018-10-06 12:40:01', '成功', '菜单名称=合同管理'), ('28', '业务日志', '删除菜单', '1', 'com.moerlong.carloan.modular.system.controller.MenuController', 'remove', '2018-10-06 12:40:13', '成功', '菜单名称=渠道管理'), ('29', '业务日志', '删除菜单', '1', 'com.moerlong.carloan.modular.system.controller.MenuController', 'remove', '2018-10-06 12:40:28', '成功', '菜单名称=流程管理'), ('30', '业务日志', '删除菜单', '1', 'com.moerlong.carloan.modular.system.controller.MenuController', 'remove', '2018-10-06 12:40:38', '成功', '菜单名称=通知'), ('31', '业务日志', '删除菜单', '1', 'com.moerlong.carloan.modular.system.controller.MenuController', 'remove', '2018-10-06 12:40:51', '成功', '菜单名称=通知管理'), ('32', '异常日志', '', '1', null, null, '2018-10-12 16:05:17', '失败', 'com.alibaba.fastjson.JSONException: syntax error, expect {, actual error, pos 0\n	at com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.deserialze(JavaBeanDeserializer.java:367)\n	at com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.parseRest(JavaBeanDeserializer.java:1010)\n	at com.alibaba.fastjson.parser.deserializer.FastjsonASMDeserializer_1_SCdssCd.deserialze(Unknown Source)\n	at com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.deserialze(JavaBeanDeserializer.java:208)\n	at com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(DefaultJSONParser.java:642)\n	at com.alibaba.fastjson.JSON.parseObject(JSON.java:350)\n	at com.alibaba.fastjson.JSON.parseObject(JSON.java:318)\n	at com.alibaba.fastjson.JSON.parseObject(JSON.java:281)\n	at com.alibaba.fastjson.JSON.parseObject(JSON.java:381)\n	at com.alibaba.fastjson.JSON.parseObject(JSON.java:463)\n	at com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4.read(FastJsonHttpMessageConverter4.java:69)\n	at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver.readWithMessageConverters(AbstractMessageConverterMethodArgumentResolver.java:201)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.readWithMessageConverters(RequestResponseBodyMethodProcessor.java:150)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.resolveArgument(RequestResponseBodyMethodProcessor.java:128)\n	at org.springframework.web.method.support.HandlerMethodArgumentResolverComposite.resolveArgument(HandlerMethodArgumentResolverComposite.java:121)\n	at org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues(InvocableHandlerMethod.java:158)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:128)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:97)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:967)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:901)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970)\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.moerlong.carloan.core.util.xss.XssFilter.doFilter(XssFilter.java:22)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:108)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:478)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:868)\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:624)\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\n	at java.lang.Thread.run(Thread.java:748)\n'), ('33', '异常日志', '', '1', null, null, '2018-10-12 17:10:20', '失败', 'org.springframework.web.method.annotation.MethodArgumentTypeMismatchException: Failed to convert value of type \'java.lang.String\' to required type \'java.lang.Integer\'; nested exception is java.lang.NumberFormatException: For input string: \"undefined\"\n	at org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver.resolveArgument(AbstractNamedValueMethodArgumentResolver.java:128)\n	at org.springframework.web.method.support.HandlerMethodArgumentResolverComposite.resolveArgument(HandlerMethodArgumentResolverComposite.java:121)\n	at org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues(InvocableHandlerMethod.java:158)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:128)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:97)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:967)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:901)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970)\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:861)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:635)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.moerlong.carloan.core.util.xss.XssFilter.doFilter(XssFilter.java:22)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:108)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:478)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:868)\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:624)\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\n	at java.lang.Thread.run(Thread.java:748)\nCaused by: java.lang.NumberFormatException: For input string: \"undefined\"\n	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)\n	at java.lang.Integer.parseInt(Integer.java:580)\n	at java.lang.Integer.valueOf(Integer.java:766)\n	at org.springframework.util.NumberUtils.parseNumber(NumberUtils.java:208)\n	at org.springframework.beans.propertyeditors.CustomNumberEditor.setAsText(CustomNumberEditor.java:113)\n	at org.springframework.beans.TypeConverterDelegate.doConvertTextValue(TypeConverterDelegate.java:468)\n	at org.springframework.beans.TypeConverterDelegate.doConvertValue(TypeConverterDelegate.java:441)\n	at org.springframework.beans.TypeConverterDelegate.convertIfNecessary(TypeConverterDelegate.java:199)\n	at org.springframework.beans.TypeConverterDelegate.convertIfNecessary(TypeConverterDelegate.java:108)\n	at org.springframework.beans.TypeConverterSupport.doConvert(TypeConverterSupport.java:64)\n	at org.springframework.beans.TypeConverterSupport.convertIfNecessary(TypeConverterSupport.java:47)\n	at org.springframework.validation.DataBinder.convertIfNecessary(DataBinder.java:713)\n	at org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver.resolveArgument(AbstractNamedValueMethodArgumentResolver.java:120)\n	... 71 more\n'), ('34', '异常日志', '', '1', null, null, '2018-10-12 17:11:46', '失败', 'org.springframework.web.method.annotation.MethodArgumentTypeMismatchException: Failed to convert value of type \'java.lang.String\' to required type \'java.lang.Integer\'; nested exception is java.lang.NumberFormatException: For input string: \"undefined\"\n	at org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver.resolveArgument(AbstractNamedValueMethodArgumentResolver.java:128)\n	at org.springframework.web.method.support.HandlerMethodArgumentResolverComposite.resolveArgument(HandlerMethodArgumentResolverComposite.java:121)\n	at org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues(InvocableHandlerMethod.java:158)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:128)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:97)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:967)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:901)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970)\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:861)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:635)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.moerlong.carloan.core.util.xss.XssFilter.doFilter(XssFilter.java:22)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:108)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:478)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:868)\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:624)\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\n	at java.lang.Thread.run(Thread.java:748)\nCaused by: java.lang.NumberFormatException: For input string: \"undefined\"\n	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)\n	at java.lang.Integer.parseInt(Integer.java:580)\n	at java.lang.Integer.valueOf(Integer.java:766)\n	at org.springframework.util.NumberUtils.parseNumber(NumberUtils.java:208)\n	at org.springframework.beans.propertyeditors.CustomNumberEditor.setAsText(CustomNumberEditor.java:113)\n	at org.springframework.beans.TypeConverterDelegate.doConvertTextValue(TypeConverterDelegate.java:468)\n	at org.springframework.beans.TypeConverterDelegate.doConvertValue(TypeConverterDelegate.java:441)\n	at org.springframework.beans.TypeConverterDelegate.convertIfNecessary(TypeConverterDelegate.java:199)\n	at org.springframework.beans.TypeConverterDelegate.convertIfNecessary(TypeConverterDelegate.java:108)\n	at org.springframework.beans.TypeConverterSupport.doConvert(TypeConverterSupport.java:64)\n	at org.springframework.beans.TypeConverterSupport.convertIfNecessary(TypeConverterSupport.java:47)\n	at org.springframework.validation.DataBinder.convertIfNecessary(DataBinder.java:713)\n	at org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver.resolveArgument(AbstractNamedValueMethodArgumentResolver.java:120)\n	... 71 more\n'), ('35', '异常日志', '', '1', null, null, '2018-10-12 17:12:44', '失败', 'org.springframework.web.method.annotation.MethodArgumentTypeMismatchException: Failed to convert value of type \'java.lang.String\' to required type \'java.lang.Integer\'; nested exception is java.lang.NumberFormatException: For input string: \"undefined\"\n	at org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver.resolveArgument(AbstractNamedValueMethodArgumentResolver.java:128)\n	at org.springframework.web.method.support.HandlerMethodArgumentResolverComposite.resolveArgument(HandlerMethodArgumentResolverComposite.java:121)\n	at org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues(InvocableHandlerMethod.java:158)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:128)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:97)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:967)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:901)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970)\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:861)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:635)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.moerlong.carloan.core.util.xss.XssFilter.doFilter(XssFilter.java:22)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:108)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:478)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:868)\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:624)\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\n	at java.lang.Thread.run(Thread.java:748)\nCaused by: java.lang.NumberFormatException: For input string: \"undefined\"\n	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)\n	at java.lang.Integer.parseInt(Integer.java:580)\n	at java.lang.Integer.valueOf(Integer.java:766)\n	at org.springframework.util.NumberUtils.parseNumber(NumberUtils.java:208)\n	at org.springframework.beans.propertyeditors.CustomNumberEditor.setAsText(CustomNumberEditor.java:113)\n	at org.springframework.beans.TypeConverterDelegate.doConvertTextValue(TypeConverterDelegate.java:468)\n	at org.springframework.beans.TypeConverterDelegate.doConvertValue(TypeConverterDelegate.java:441)\n	at org.springframework.beans.TypeConverterDelegate.convertIfNecessary(TypeConverterDelegate.java:199)\n	at org.springframework.beans.TypeConverterDelegate.convertIfNecessary(TypeConverterDelegate.java:108)\n	at org.springframework.beans.TypeConverterSupport.doConvert(TypeConverterSupport.java:64)\n	at org.springframework.beans.TypeConverterSupport.convertIfNecessary(TypeConverterSupport.java:47)\n	at org.springframework.validation.DataBinder.convertIfNecessary(DataBinder.java:713)\n	at org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver.resolveArgument(AbstractNamedValueMethodArgumentResolver.java:120)\n	... 71 more\n'), ('36', '业务日志', '修改管理员', '1', 'com.moerlong.carloan.modular.system.controller.UserMgrController', 'edit', '2018-10-12 21:58:36', '成功', '账号=admin;;;字段名称:部门名称,旧值:山东省,济南市,平阴县,新值:锦水街道乡镇'), ('37', '异常日志', '', '1', null, null, '2018-10-13 15:04:31', '失败', 'com.alibaba.fastjson.JSONException: syntax error, expect {, actual error, pos 0\n	at com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.deserialze(JavaBeanDeserializer.java:367)\n	at com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.parseRest(JavaBeanDeserializer.java:1010)\n	at com.alibaba.fastjson.parser.deserializer.FastjsonASMDeserializer_1_SCdssQt.deserialze(Unknown Source)\n	at com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.deserialze(JavaBeanDeserializer.java:208)\n	at com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(DefaultJSONParser.java:642)\n	at com.alibaba.fastjson.JSON.parseObject(JSON.java:350)\n	at com.alibaba.fastjson.JSON.parseObject(JSON.java:318)\n	at com.alibaba.fastjson.JSON.parseObject(JSON.java:281)\n	at com.alibaba.fastjson.JSON.parseObject(JSON.java:381)\n	at com.alibaba.fastjson.JSON.parseObject(JSON.java:463)\n	at com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4.read(FastJsonHttpMessageConverter4.java:69)\n	at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver.readWithMessageConverters(AbstractMessageConverterMethodArgumentResolver.java:201)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.readWithMessageConverters(RequestResponseBodyMethodProcessor.java:150)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.resolveArgument(RequestResponseBodyMethodProcessor.java:128)\n	at org.springframework.web.method.support.HandlerMethodArgumentResolverComposite.resolveArgument(HandlerMethodArgumentResolverComposite.java:121)\n	at org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues(InvocableHandlerMethod.java:158)\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:128)\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:97)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827)\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738)\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:967)\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:901)\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970)\n	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:449)\n	at org.apache.shiro.web.servlet.AbstractShiroFilterT1.call(AbstractShiroFilter.java:365)\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:362)\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:125)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.moerlong.carloan.core.util.xss.XssFilter.doFilter(XssFilter.java:22)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:108)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197)\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:199)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:478)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803)\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)\n	at org.apache.coyote.AbstractProtocolTConnectionHandler.process(AbstractProtocol.java:868)\n	at org.apache.tomcat.util.net.NioEndpointTSocketProcessor.doRun(NioEndpoint.java:1459)\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\n	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n	at java.util.concurrent.ThreadPoolExecutorTWorker.run(ThreadPoolExecutor.java:624)\n	at org.apache.tomcat.util.threads.TaskThreadTWrappingRunnable.run(TaskThread.java:61)\n	at java.lang.Thread.run(Thread.java:748)\n');
INSERT INTO `operation_log` VALUES ('38', '业务日志', '修改部门', '1', 'com.moerlong.carloan.modular.system.controller.DeptController', 'update', '2018-10-21 21:54:33', '成功', '部门简称=山东省;;;字段名称:备注,旧值:,新值:111');
COMMIT;

-- ----------------------------
--  Table structure for `order_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `order_sequence`;
CREATE TABLE `order_sequence` (
  `seq_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `order_type` bigint(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`seq_id`)
) ENGINE=InnoDB AUTO_INCREMENT=336 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `order_sequence`
-- ----------------------------
BEGIN;
INSERT INTO `order_sequence` VALUES ('1', '1'), ('2', '1'), ('3', '1'), ('4', '1'), ('5', '1'), ('6', '1'), ('7', '1'), ('8', '1'), ('9', '1'), ('10', '1'), ('11', '1'), ('12', '1'), ('13', '1'), ('14', '1'), ('15', '1'), ('16', '1'), ('17', '1'), ('18', '1'), ('19', '1'), ('20', '1'), ('21', '1'), ('22', '1'), ('23', '1'), ('24', '1'), ('25', '1'), ('26', '1'), ('27', '1'), ('28', '1'), ('29', '1'), ('30', '1'), ('31', '1'), ('32', '1'), ('33', '1'), ('34', '1'), ('35', '1'), ('36', '1'), ('37', '1'), ('38', '1'), ('39', '1'), ('44', '1'), ('68', '1'), ('69', '1'), ('70', '1'), ('71', '1'), ('72', '1'), ('73', '1'), ('74', '1'), ('75', '1'), ('76', '1'), ('77', '1'), ('78', '1'), ('79', '1'), ('80', '1'), ('81', '1'), ('82', '1'), ('83', '1'), ('84', '1'), ('85', '1'), ('86', '1'), ('87', '1'), ('88', '1'), ('89', '1'), ('90', '1'), ('91', '1'), ('92', '1'), ('93', '1'), ('94', '1'), ('95', '1'), ('96', '1'), ('97', '1'), ('98', '1'), ('99', '1'), ('100', '1'), ('101', '1'), ('102', '1'), ('103', '1'), ('104', '1'), ('105', '1'), ('106', '1'), ('107', '1'), ('108', '1'), ('109', '1'), ('110', '1'), ('111', '1'), ('112', '1'), ('113', '1'), ('114', '1'), ('115', '1'), ('116', '1'), ('117', '1'), ('118', '1'), ('119', '1'), ('120', '1'), ('121', '1'), ('122', '1'), ('123', '1'), ('124', '1'), ('125', '1'), ('126', '1'), ('127', '1'), ('128', '1'), ('129', '1'), ('130', '1'), ('131', '1'), ('132', '1'), ('133', '1'), ('134', '1'), ('135', '1'), ('136', '1'), ('137', '1'), ('138', '1'), ('139', '1'), ('140', '1'), ('141', '1'), ('142', '1'), ('143', '1'), ('144', '1'), ('154', '1'), ('155', '1'), ('156', '1'), ('157', '1'), ('168', '1'), ('169', '1'), ('170', '1'), ('171', '1'), ('235', '1'), ('236', '1'), ('237', '1'), ('238', '1'), ('307', '1'), ('308', '1'), ('309', '1'), ('310', '1'), ('311', '1'), ('312', '1'), ('313', '1'), ('314', '1'), ('328', '1'), ('329', '1'), ('330', '1'), ('331', '1'), ('332', '1'), ('333', '1'), ('334', '1'), ('335', '1');
COMMIT;

-- ----------------------------
--  Table structure for `relation`
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menuid` int(11) DEFAULT NULL COMMENT '菜单id',
  `roleid` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7420 DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
--  Records of `relation`
-- ----------------------------
BEGIN;
INSERT INTO `relation` VALUES ('5762', '66', '9'), ('5765', '66', '10'), ('5768', '64', '12'), ('5770', '66', '12'), ('7008', '1', '13'), ('7009', '2', '13'), ('7010', '3', '13'), ('7011', '4', '13'), ('7012', '5', '13'), ('7013', '6', '13'), ('7014', '7', '13'), ('7015', '8', '13'), ('7016', '9', '13'), ('7017', '53', '13'), ('7018', '54', '13'), ('7019', '55', '13'), ('7020', '10', '13'), ('7021', '11', '13'), ('7022', '12', '13'), ('7023', '13', '13'), ('7024', '14', '13'), ('7025', '50', '13'), ('7026', '51', '13'), ('7027', '52', '13'), ('7028', '15', '13'), ('7029', '16', '13'), ('7030', '17', '13'), ('7031', '18', '13'), ('7032', '38', '13'), ('7033', '39', '13'), ('7034', '19', '13'), ('7035', '24', '13'), ('7036', '46', '13'), ('7037', '47', '13'), ('7038', '20', '13'), ('7039', '21', '13'), ('7040', '25', '13'), ('7041', '26', '13'), ('7042', '27', '13'), ('7043', '40', '13'), ('7044', '41', '13'), ('7045', '42', '13'), ('7046', '22', '13'), ('7047', '28', '13'), ('7048', '29', '13'), ('7049', '30', '13'), ('7050', '43', '13'), ('7051', '44', '13'), ('7052', '45', '13'), ('7053', '23', '13'), ('7054', '48', '13'), ('7055', '49', '13'), ('7060', '36', '13'), ('7062', '37', '13'), ('7064', '64', '13'), ('7073', '66', '13'), ('7282', '227', '2'), ('7283', '228', '2'), ('7335', '1', '1'), ('7336', '2', '1'), ('7337', '3', '1'), ('7338', '4', '1'), ('7339', '5', '1'), ('7340', '6', '1'), ('7341', '7', '1'), ('7342', '8', '1'), ('7343', '9', '1'), ('7344', '53', '1'), ('7345', '54', '1'), ('7346', '55', '1'), ('7347', '10', '1'), ('7348', '11', '1'), ('7349', '12', '1'), ('7350', '13', '1'), ('7351', '14', '1'), ('7352', '50', '1'), ('7353', '51', '1'), ('7354', '52', '1'), ('7355', '15', '1'), ('7356', '16', '1'), ('7357', '17', '1'), ('7358', '18', '1'), ('7359', '38', '1'), ('7360', '39', '1'), ('7361', '19', '1'), ('7362', '24', '1'), ('7363', '46', '1'), ('7364', '47', '1'), ('7365', '20', '1'), ('7366', '21', '1'), ('7367', '25', '1'), ('7368', '26', '1'), ('7369', '27', '1'), ('7370', '40', '1'), ('7371', '41', '1'), ('7372', '42', '1'), ('7373', '22', '1'), ('7374', '28', '1'), ('7375', '29', '1'), ('7376', '30', '1'), ('7377', '43', '1'), ('7378', '44', '1'), ('7379', '45', '1'), ('7380', '23', '1'), ('7381', '48', '1'), ('7382', '49', '1'), ('7387', '36', '1'), ('7390', '37', '1'), ('7418', '227', '1'), ('7419', '228', '1');
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
INSERT INTO `role` VALUES ('1', '1', '0', 'administrator', '1', '超级管理员', '1'), ('2', '2', '1', '内勤主管', '2', '内勤主管', null), ('3', '3', '2', '内勤', '2', '内勤', null), ('4', '4', '1', '验车师', '2', '验车师', null), ('5', '5', '1', '面审主管', '2', '面审主管', null), ('6', '6', '5', '面审', '2', '面审', null), ('7', '7', '1', '终审', '2', '终审', null), ('8', '8', '1', '抵押专员', '2', '抵押专员', null), ('9', '9', '1', '业务经理', '2', '业务经理', null), ('10', '10', '1', '财务经理', '2', '财务经理', null), ('11', '11', '10', '财务', '2', '财务', null), ('12', '12', '1', '前台财务', '1', '前台财务', null), ('13', null, '0', 'admin', null, '管理员', null);
COMMIT;

-- ----------------------------
--  Table structure for `s_attach`
-- ----------------------------
DROP TABLE IF EXISTS `s_attach`;
CREATE TABLE `s_attach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(2) DEFAULT NULL COMMENT '类型：1-工程概况中全景图片；2-场地类图片；3-健身路径类图片；4-其他类图片；5-场地类器材图片；6-健身路径类器材图片；7-其他类器材图片',
  `prjid` int(11) DEFAULT NULL,
  `prjtype` int(2) DEFAULT NULL COMMENT '项目类型；左侧菜单编号',
  `cdsscdid` int(11) DEFAULT NULL,
  `cdssjsljid` int(11) DEFAULT NULL,
  `cdssqtid` int(11) DEFAULT NULL,
  `qccdid` int(11) DEFAULT NULL,
  `qcjsljid` int(11) DEFAULT NULL,
  `qcqtid` int(11) DEFAULT NULL,
  `picname` varchar(255) DEFAULT NULL COMMENT '图片名称',
  `picurl` varchar(255) DEFAULT NULL COMMENT '图片上传路径',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `isdelete` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `s_attach`
-- ----------------------------
BEGIN;
INSERT INTO `s_attach` VALUES ('1', '1', '15', '1', null, null, null, null, null, null, null, '/resource/pics/2018-10-21/1c792109-3546-45b0-8427-003ba3cbf76a.jpg', '2018-10-21 11:24:44', null, '0'), ('2', '1', '15', '1', null, null, null, null, null, null, null, '/resource/pics/2018-10-21/7c1be6d9-1648-44db-81f3-cdba1e8fc005.jpg', '2018-10-21 12:35:18', null, '0'), ('3', '1', '15', '1', null, null, null, null, null, null, null, '/resource/pics/2018-10-21/bfbb7bec-bdd9-450a-bc72-147168b425a4.jpeg', '2018-10-21 12:35:42', null, '0'), ('4', '1', '15', '1', null, null, null, null, null, null, null, '/resource/pics/2018-10-21/bef06033-e4d7-4f01-8eae-a9bb05eeebcc.jpeg', '2018-10-21 12:35:50', null, '0'), ('11', '2', '11', '1', '9', null, null, null, null, null, null, '/resource/pics/2018-10-21/18bd7c4b-f180-41e6-a44a-cac8a9c37674.jpg', '2018-10-21 15:17:08', null, '0'), ('12', '2', '11', '1', '9', null, null, null, null, null, null, '/resource/pics/2018-10-21/2b5e7970-7513-470c-bb74-8782547f0574.jpg', '2018-10-21 15:21:29', null, '0'), ('13', '2', '11', '1', '9', null, null, null, null, null, null, '/resource/pics/2018-10-21/e51ca461-caa2-4d41-ac41-1491dc7e947b.jpeg', '2018-10-21 15:21:59', null, '0'), ('14', '2', '11', '1', '9', null, null, null, null, null, null, '/resource/pics/2018-10-21/c5ef2715-0605-45f0-9c6f-91b4d19ca41c.jpg', '2018-10-21 15:24:23', null, '0'), ('15', '2', '11', '1', '9', null, null, null, null, null, null, '/resource/pics/2018-10-21/c873ceeb-ca86-45a2-8cf3-c0c4f728f8a5.jpg', '2018-10-21 15:24:41', null, '0'), ('22', '2', '12', '1', '11', null, null, null, null, null, null, '/resource/pics/2018-10-21/8d9d4798-3d5d-423e-a973-6cd2a610d52c.jpeg', '2018-10-21 15:37:33', null, '0'), ('23', '2', '12', '1', '11', null, null, null, null, null, null, '/resource/pics/2018-10-21/95a68bc8-deda-4775-ba34-468fad0ab621.jpg', '2018-10-21 15:37:41', null, '0'), ('24', '3', '11', '1', null, '3', null, null, null, null, null, '/resource/pics/2018-10-21/629d1061-5576-4f72-bfba-4b81f69c9cb4.jpg', '2018-10-21 15:53:31', null, '0'), ('25', '3', '11', '1', null, '3', null, null, null, null, null, '/resource/pics/2018-10-21/7f598229-49c2-46a4-93f8-41aa978055d1.jpg', '2018-10-21 16:04:03', null, '0'), ('26', '4', '11', '1', null, null, '3', null, null, null, null, '/resource/pics/2018-10-21/ef5456c0-7651-4f14-ad0c-d2721fc0c78e.jpg', '2018-10-21 16:32:00', null, '0'), ('27', '5', '11', '1', '9', null, null, '6', null, null, null, '/resource/pics/2018-10-21/fc42a5ba-9be4-4648-a91a-8aee1c00f446.jpg', '2018-10-21 17:15:15', null, '0'), ('28', '5', '11', '1', '9', null, null, '6', null, null, null, '/resource/pics/2018-10-21/dc95ce98-d83b-44c9-b524-5ef48acd9ea1.jpg', '2018-10-21 17:18:13', null, '0'), ('29', '5', '11', '1', '9', null, null, '9', null, null, null, '/resource/pics/2018-10-21/0ae96744-22f7-4c7f-a1b0-e976be2a5f3d.jpg', '2018-10-21 17:19:53', null, '0'), ('30', '5', '11', '1', '9', null, null, '9', null, null, null, '/resource/pics/2018-10-21/e4f52ef5-75ae-48b8-9743-a9a35cdd8bef.jpg', '2018-10-21 17:19:56', null, '0'), ('31', '5', '11', '1', '9', null, null, '9', null, null, null, '/resource/pics/2018-10-21/6fad111d-7654-4223-86d7-14bb95f4693a.jpg', '2018-10-21 17:20:00', null, '0'), ('32', '6', '11', '1', null, '3', null, null, '8', null, null, '/resource/pics/2018-10-21/6f3c9aee-f2cf-496d-bccc-75e381a0b463.jpg', '2018-10-21 17:59:26', null, '0'), ('33', '6', '11', '1', null, '3', null, null, '8', null, null, '/resource/pics/2018-10-21/891d3b76-3477-4632-bd0a-e5e55ee569f7.jpg', '2018-10-21 18:01:19', null, '0'), ('34', '6', '11', '1', null, '3', null, null, '8', null, null, '/resource/pics/2018-10-21/9ee563a7-5588-44a7-a70c-8d4fa72e8960.jpeg', '2018-10-21 18:02:55', null, '0'), ('35', '6', '11', '1', null, '3', null, null, '3', null, null, '/resource/pics/2018-10-21/2578114b-68d4-4bf0-b922-19f34ed21a86.jpg', '2018-10-21 18:03:56', null, '0'), ('36', '7', '11', '3', null, null, null, null, null, '1', null, '/resource/pics/2018-10-21/d2927dec-fcbe-4276-a4e9-41900cd85505.jpg', '2018-10-21 19:41:47', null, '0'), ('37', '7', '11', '1', null, null, '3', null, null, '2', null, '/resource/pics/2018-10-21/339d8be9-52f1-4b09-936b-5bdfb831501b.jpg', '2018-10-21 19:50:49', null, '0'), ('38', '7', '11', '1', null, null, '3', null, null, '2', null, '/resource/pics/2018-10-21/50f67610-cef1-4782-a2c4-fc9652d94cde.jpg', '2018-10-21 20:05:47', null, '0'), ('39', '1', '16', '1', null, null, null, null, null, null, null, '/resource/pics/2018-10-21/aa49dbfb-3832-4cba-85c8-19fe0e864aec.jpg', '2018-10-21 20:14:25', null, '0'), ('40', '1', '18', '2', null, null, null, null, null, null, null, '/resource/pics/2018-10-21/0300be93-8cdb-4ec4-8188-add41a9b2cc1.jpg', '2018-10-21 21:22:32', null, '0');
COMMIT;

-- ----------------------------
--  Table structure for `s_cdss_cd`
-- ----------------------------
DROP TABLE IF EXISTS `s_cdss_cd`;
CREATE TABLE `s_cdss_cd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prjid` int(11) DEFAULT NULL COMMENT '项目id',
  `prjtype` varchar(2) CHARACTER SET utf8 DEFAULT NULL COMMENT '工程类型',
  `cdname` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '场地名称',
  `chang` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '长',
  `kuan` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '宽',
  `area` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '面积',
  `dimian` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '地面',
  `cddbqk` varchar(6) CHARACTER SET utf8 DEFAULT NULL COMMENT '场地达标情况',
  `beizhu` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `pic` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '图片',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `isdelete` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `s_cdss_cd`
-- ----------------------------
BEGIN;
INSERT INTO `s_cdss_cd` VALUES ('9', '11', '1', '羽毛球场', '10', '10', '100', '', '是', '场地很好', '1111111', '2018-10-12 21:21:23', '2018-10-21 15:22:41', '0'), ('10', '10', '2', '橄榄球场', '10', '10', '100', '混凝土', '是', '备注', '11111111111', '2018-10-12 21:26:05', null, '0'), ('11', '12', '1', '乒乓球场1', '1', '1', '11', '1', '是1', '', '1', '2018-10-13 22:39:45', null, '0');
COMMIT;

-- ----------------------------
--  Table structure for `s_cdss_jslj`
-- ----------------------------
DROP TABLE IF EXISTS `s_cdss_jslj`;
CREATE TABLE `s_cdss_jslj` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prjid` int(11) DEFAULT NULL COMMENT '项目id',
  `prjtype` varchar(2) CHARACTER SET utf8 DEFAULT NULL COMMENT '工程类型',
  `mc` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '名称',
  `chang` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '长',
  `kuan` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '宽',
  `area` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '面积',
  `dbqk` varchar(2) CHARACTER SET utf8 DEFAULT NULL COMMENT '达标情况',
  `cllx` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '材料类型',
  `pic` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '图片',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `isdelete` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `s_cdss_jslj`
-- ----------------------------
BEGIN;
INSERT INTO `s_cdss_jslj` VALUES ('1', '10', '2', '健身路径', '11', '11', '1000', '否', '合成材料', '2222', '2018-10-13 10:21:18', '2018-10-13 10:25:11', '1'), ('2', '11', '1', '健身路径', '10', '10', '100', '是', '花砖', '111', '2018-10-14 14:14:46', null, '0'), ('3', '11', '1', '健身路径测试', '10', '10', '100', '是', '花砖', null, '2018-10-21 15:53:16', null, '0');
COMMIT;

-- ----------------------------
--  Table structure for `s_cdss_qc_cd`
-- ----------------------------
DROP TABLE IF EXISTS `s_cdss_qc_cd`;
CREATE TABLE `s_cdss_qc_cd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prjid` int(11) DEFAULT NULL COMMENT '项目id',
  `prjtype` int(11) DEFAULT NULL COMMENT '项目类型',
  `cdid` int(11) DEFAULT NULL COMMENT '场地id',
  `jssb` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '健身设备',
  `bh` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '编号',
  `provider` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '供应商',
  `azsj` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '安装时间',
  `qcxz` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '器材现状',
  `sfbf` varchar(50) CHARACTER SET utf8 DEFAULT '否' COMMENT '是否报废',
  `gzms` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '故障描述',
  `hcc` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '缓冲层',
  `mfwhqx` varchar(3) CHARACTER SET utf8 DEFAULT NULL COMMENT '免费维护期限',
  `aqsyqx` varchar(3) CHARACTER SET utf8 DEFAULT NULL COMMENT '安全使用期限',
  `createtime` datetime DEFAULT NULL,
  `isdelete` int(1) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `xh` varchar(1) DEFAULT NULL COMMENT '该字段无用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `s_cdss_qc_cd`
-- ----------------------------
BEGIN;
INSERT INTO `s_cdss_qc_cd` VALUES ('6', '11', '1', '9', '1', '1', '1', '', '1', null, '1', '1', '1', '1', '2018-10-14 10:29:01', '0', null, null), ('7', '12', '1', '11', '1', '1', '1', '1111-01-01', '1', null, '1', '1', '1', '1', '2018-10-14 14:13:43', '0', null, null), ('8', '11', '1', '2', '1', '1', '1', '2018-01-01', '1', null, '1', '1', '1', '1', '2018-10-14 14:40:24', '0', null, null), ('9', '11', '1', '9', '篮球架', '001', '供应商', '2018-10-17', '正常使用', null, '是', '是', '1', '1', '2018-10-21 17:19:38', '0', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `s_cdss_qc_jslj`
-- ----------------------------
DROP TABLE IF EXISTS `s_cdss_qc_jslj`;
CREATE TABLE `s_cdss_qc_jslj` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prjid` int(11) DEFAULT NULL COMMENT '项目id',
  `prjtype` int(11) DEFAULT NULL COMMENT '项目类型',
  `jsljid` int(11) DEFAULT NULL COMMENT '健身路径id',
  `xh` varchar(3) CHARACTER SET utf8 DEFAULT NULL COMMENT '序号',
  `jssb` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '健身设备',
  `bh` varchar(3) CHARACTER SET utf8 DEFAULT NULL COMMENT '编号',
  `provider` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '供应商',
  `azsj` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '安装时间',
  `qcxz` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '器材现状',
  `sfbf` varchar(50) CHARACTER SET utf8 DEFAULT '否' COMMENT '是否报废',
  `gzms` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '故障描述',
  `hcc` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '缓冲层',
  `mfwhqx` varchar(3) CHARACTER SET utf8 DEFAULT NULL COMMENT '免费维护期限',
  `aqsyqx` varchar(3) CHARACTER SET utf8 DEFAULT NULL COMMENT '安全使用期限',
  `createtime` datetime DEFAULT NULL,
  `isdelete` int(1) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `s_cdss_qc_jslj`
-- ----------------------------
BEGIN;
INSERT INTO `s_cdss_qc_jslj` VALUES ('2', '11', '1', '2', null, '1', '1', '1', '2018-01-01', '1', null, '1', '1', '1', '1', '2018-10-14 20:43:42', '0', null), ('3', '11', '1', '3', null, '健身车', '001', '供应商', '2018-10-12', '正常使用', null, '1', '是', '1', '1', '2018-10-21 17:49:02', '0', null), ('4', '11', '1', '3', null, '健身车', '001', '供应商', '2018-10-12', '正常使用', null, '1', '是', '1', '1', '2018-10-21 17:50:06', '0', null), ('5', '11', '1', '3', null, '篮球架', '001', '1', '', '正常使用', null, '', '是', '1', '', '2018-10-21 17:54:25', '0', null), ('6', '11', '1', '3', null, '篮球架', '001', '1', '', '正常使用', null, '', '是', '1', '', '2018-10-21 17:55:22', '0', null), ('7', '11', '1', '3', null, '自行车', '001', '1', '2018-10-03', '存在一般安全隐患', null, '', '是', '1', '11', '2018-10-21 17:57:25', '0', null), ('8', '11', '1', '3', null, '肋木架', '12', '', '', '', null, '', '', '', '', '2018-10-21 17:59:06', '0', null);
COMMIT;

-- ----------------------------
--  Table structure for `s_cdss_qc_qt`
-- ----------------------------
DROP TABLE IF EXISTS `s_cdss_qc_qt`;
CREATE TABLE `s_cdss_qc_qt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prjid` int(11) DEFAULT NULL COMMENT '项目id',
  `prjtype` int(11) DEFAULT NULL COMMENT '项目类型',
  `qtid` int(11) DEFAULT NULL COMMENT '其他类id',
  `xh` varchar(3) CHARACTER SET utf8 DEFAULT NULL COMMENT '序号',
  `jssb` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '健身设备',
  `bh` varchar(3) CHARACTER SET utf8 DEFAULT NULL COMMENT '编号',
  `provider` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '供应商',
  `azsj` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '安装时间',
  `qcxz` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '器材现状',
  `sfbf` varchar(50) CHARACTER SET utf8 DEFAULT '否' COMMENT '是否报废',
  `gzms` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '故障描述',
  `hcc` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '缓冲层',
  `mfwhqx` varchar(3) CHARACTER SET utf8 DEFAULT NULL COMMENT '免费维护期限',
  `aqsyqx` varchar(3) CHARACTER SET utf8 DEFAULT NULL COMMENT '安全使用期限',
  `createtime` datetime DEFAULT NULL,
  `isdelete` int(1) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `s_cdss_qc_qt`
-- ----------------------------
BEGIN;
INSERT INTO `s_cdss_qc_qt` VALUES ('2', '11', '1', '3', null, '单杠', '001', 'q', '', '正常使用', null, '1', '是', '', '', '2018-10-21 19:50:20', '0', null);
COMMIT;

-- ----------------------------
--  Table structure for `s_cdss_qt`
-- ----------------------------
DROP TABLE IF EXISTS `s_cdss_qt`;
CREATE TABLE `s_cdss_qt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prjid` int(11) DEFAULT NULL,
  `prjtype` varchar(2) CHARACTER SET utf8 DEFAULT NULL COMMENT '工程类型',
  `jsss` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '健身设施',
  `num` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '数量',
  `area` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '面积',
  `wz` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '位置',
  `beizhu` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `pic` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '图片',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `isdelete` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `s_cdss_qt`
-- ----------------------------
BEGIN;
INSERT INTO `s_cdss_qt` VALUES ('1', '10', '2', '单杠', '1', '100', '东子顺村', '备注', '11111111', '2018-10-13 15:19:50', '2018-10-13 15:25:02', '0'), ('2', '10', '2', '健身设施', '1', '100', '东子顺村', '备注', '11111111', '2018-10-13 15:23:10', '2018-10-13 15:30:54', '1'), ('3', '11', '1', '健身设施', '1', '100', '室内', '', null, '2018-10-21 16:31:56', null, '0');
COMMIT;

-- ----------------------------
--  Table structure for `s_prj_base`
-- ----------------------------
DROP TABLE IF EXISTS `s_prj_base`;
CREATE TABLE `s_prj_base` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prjtype` varchar(2) CHARACTER SET utf8 DEFAULT NULL COMMENT '工程类型',
  `local` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '地区',
  `prjname` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '工程名称',
  `place` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '建设地点',
  `area` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '占地面积(平方米)',
  `begintime` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '开工时间',
  `endtime` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '完工时间',
  `provider` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '供应商',
  `jsfa` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '建设方案',
  `dljd` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '地理经度',
  `dlwd` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '地理纬度',
  `qjpic1` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '全景照片1',
  `qjpic2` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '全景照片2',
  `qjpic3` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '全景照片3',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `isdelete` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `s_prj_base`
-- ----------------------------
BEGIN;
INSERT INTO `s_prj_base` VALUES ('10', '2', '济南市平阴县', '社区体育健身工程', '东子顺村', '100', '2018-01-01', '2018-09-01', '山东省济南市永康健身器材供应商', '篮球场加乒乓球台加健身路径', '10.34567890', '11.34567890', '1111111111111111111111111', '222222222222222222', '333333333333333333', '2018-10-12 21:02:12', null, '0'), ('11', '1', '山东省济南市平阴县锦水街道村委会', '农民健身工程', '寨前村', '100', '2018-01-01', '2018-09-01', '山东省济南市永康健身器材供应商', '篮球场加健身路径加乒乓球场', '10.1231213', '10.12312312313', '1111111111111111', '12222222222222', '2223333333333', '2018-10-12 21:19:25', '2018-10-12 21:19:30', '0'), ('12', '1', '锦水街道乡镇', '11111', '1', '1', '2018-10-13', '2018-10-06', '1', '1', '1', '1', '1', '1', '1', '2018-10-13 22:39:11', null, '0'), ('13', '1', '锦水街道乡镇', '工程名称1', '黄巢村', '100', '2018-10-13', '2018-10-13', '供应商1', '建设大年哈', '10.1111114', '10.2255555', '111111111', '222222222', '3333333', '2018-10-13 22:50:48', null, '0'), ('15', '1', '锦水街道乡镇', '测试工程', '北京市', '100', '', '', '', '', '东经：116.0° 23.0& #39; 20.088000000005195', '北纬：36.0° 18.0& #39; 13.60800000001177', null, null, null, '2018-10-21 10:25:21', '2018-10-21 13:10:41', '0'), ('16', '1', '锦水街道乡镇', '农民健身工程锦水街道', '东子顺村委会', '100', '2018-10-10', '2019-03-06', '供应商', '1', '东经：116.0° 23.0& #39; 20.088000000005195\"', '北纬：36.0° 18.0& #39; 13.60800000001177\"', null, null, null, '2018-10-21 20:14:20', null, '0'), ('17', '1', '锦水街道乡镇', '123', '123', '123', '', '', '123', '123', '', '', null, null, null, '2018-10-21 20:25:10', null, '0'), ('18', '2', '锦水街道乡镇', '', '', '', '', '', '', '', '东经：116.0° 23.0& #39; 20.088000000005195\"', '北纬：36.0° 18.0& #39; 13.60800000001177\"', null, null, null, '2018-10-21 21:22:26', null, '0'), ('19', '2', '锦水街道乡镇', 'shequjianshen', '1', '1', '', '', '', '1', '', '', null, null, null, '2018-10-21 21:23:51', null, '0');
COMMIT;

-- ----------------------------
--  Table structure for `s_provider`
-- ----------------------------
DROP TABLE IF EXISTS `s_provider`;
CREATE TABLE `s_provider` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `providername` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '供应商名称',
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `isdelete` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(255) DEFAULT 'girl.gif' COMMENT '头像',
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
  `id_code` varchar(255) DEFAULT NULL COMMENT '员工工号',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `status` int(11) DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'girl.gif', 'admin', 'ecfadcde9305f8891bcfe5a1e28c253e', '8pgby', '超级管理员', '2017-05-05 00:00:00', '1', 'sn93@qq.com', '18888888888', '1', '11', null, null, '1', '2016-01-29 08:49:53', '25');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
