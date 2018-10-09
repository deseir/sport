
-- ----------------------------
-- Table structure for c_car_validatecar_boli_configuration
-- ----------------------------
DROP TABLE IF EXISTS `c_car_validatecar_boli_configuration`;
CREATE TABLE `c_car_validatecar_boli_configuration` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` int(11) DEFAULT NULL COMMENT '车辆ID',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '订单id',
  `cust_id` int(21) DEFAULT NULL COMMENT '客户id',
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
-- Table structure for c_car_validatecar_exterior_specification
-- ----------------------------
DROP TABLE IF EXISTS `c_car_validatecar_exterior_specification`;
CREATE TABLE `c_car_validatecar_exterior_specification` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` int(11) DEFAULT NULL COMMENT '车辆ID',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '订单id',
  `cust_id` int(21) DEFAULT NULL COMMENT '客户id',
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
  `PKE
PKE
pke` int(1) DEFAULT NULL COMMENT '无钥匙进入系统 0无1有',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆详细配置_外部配置表';

-- ----------------------------
-- Table structure for c_car_validatecar_interior_collocation
-- ----------------------------
DROP TABLE IF EXISTS `c_car_validatecar_interior_collocation`;
CREATE TABLE `c_car_validatecar_interior_collocation` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` int(11) DEFAULT NULL COMMENT '车辆ID',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '订单id',
  `cust_id` int(21) DEFAULT NULL COMMENT '客户id',
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
-- Table structure for c_car_validatecar_kongtiao_configuration
-- ----------------------------
DROP TABLE IF EXISTS `c_car_validatecar_kongtiao_configuration`;
CREATE TABLE `c_car_validatecar_kongtiao_configuration` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` int(11) DEFAULT NULL COMMENT '车辆ID',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '订单id',
  `cust_id` int(21) DEFAULT NULL COMMENT '客户id',
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
-- Table structure for c_car_validatecar_light_configuration
-- ----------------------------
DROP TABLE IF EXISTS `c_car_validatecar_light_configuration`;
CREATE TABLE `c_car_validatecar_light_configuration` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` int(11) DEFAULT NULL COMMENT '车辆ID',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '订单id',
  `cust_id` int(21) DEFAULT NULL COMMENT '客户id',
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
-- Table structure for c_car_validatecar_multimedia_configuration
-- ----------------------------
DROP TABLE IF EXISTS `c_car_validatecar_multimedia_configuration`;
CREATE TABLE `c_car_validatecar_multimedia_configuration` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` int(11) DEFAULT NULL COMMENT '车辆ID',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '订单id',
  `cust_id` int(21) DEFAULT NULL COMMENT '客户id',
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
-- Table structure for c_car_validatecar_seat_configuration
-- ----------------------------
DROP TABLE IF EXISTS `c_car_validatecar_seat_configuration`;
CREATE TABLE `c_car_validatecar_seat_configuration` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` int(11) DEFAULT NULL COMMENT '车辆ID',
  `apply_id` bigint(21) DEFAULT NULL COMMENT '订单id',
  `cust_id` int(21) DEFAULT NULL COMMENT '客户id',
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


DROP TABLE IF EXISTS c_channel_info;
create table c_channel_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   channel_name         varchar(128) comment '渠道名称',
   city                 varchar(128) comment '所在城市区域',
   address              varchar(256) comment '渠道联系地址',
   fanyong_rate         decimal(10,4) comment '返佣比例',
   account_name         varchar(64) comment '返佣账户户名',
   account_bank         varchar(128) comment '返佣开户行信息',
   account_cardno       varchar(64) comment '返佣账户卡号',
   join_person          varchar(64) comment '对接人名称',
   join_mobile          varchar(32) comment '对接人联系方式',
   create_user_id       bigint(21) comment '创建人ID',
   create_user_name     varchar(32) comment '创建人姓名',
   buss_name            varchar(32) comment '与渠道对接业务人员姓名',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '渠道表';

DROP TABLE IF EXISTS c_process_node;
create table c_process_node
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   role_id              bigint(21) comment '角色ID',
   process_name         varchar(128) comment '流程名称',
   process_status       int comment '流程对应的状态',
   process_status_desc  varchar(128) comment '流程对应的状态描述',
   is_back               int default 0 comment '是否是驳回节点 0--否 其他--是 表示驳回的角色',
   is_sync              int default 0 comment '是否是同步流程0--否 1--是',
   sync_field_name      varchar(128) comment '同步字段名称',
   show_address         varchar(255) default '/cust/submitCommon.html' comment '跳转页面地址',
   interface_address    varchar(255) comment '提交接口地址',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '流程节点表';

DROP TABLE IF EXISTS c_process_engine;
create table c_process_engine
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   cur_node_id          bigint(21) comment '当前ID',
   next_node_id         varchar(256) comment '前置ID 可多个',
   type                 int comment '类型 0--后续成功节点 1--后续失败节点 2--后续驳回节点 3--前置节点 4--处理中 5--后续同步节点',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '流程引擎表';

DROP TABLE IF EXISTS c_apply_info;
create table c_apply_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   cust_id              bigint(21) not null comment '客户ID',
   channel_id           bigint(21) comment '客户渠道ID',
   product_type         int default 0 comment '产品类型 0--抵押 1--质押',
   apply_amount         decimal(10,2) comment '申请额度 单位(元)',
   left_amount          decimal(10,2) comment '剩余额度 单位(元)',
   apply_period         int default 36 comment '申请期限  12/24/36',
   repayment_type       int default 0 comment '还款方式 0--等额本息 1--先息后本',
   loan_usage           int comment '还款用途 1--教育支出 2--医疗 4--生意周转 8--装修 16--其他（可多选）',
   loan_usage_other     varchar(128) comment '还款用途其他情况说明',
   partner_know         int comment '0--未婚 1--离异 2--丧偶 3--已婚不知晓 4--已婚知晓不能签字 5--已婚知晓可签字',
   dept_id              bigint(21) comment '所属部门',
   status               int comment '主流程状态',
   status_desc          varchar(128) comment '主流程状态描述',
   sfrz_status          int default 0 comment '身份认证 0--未认证 1--已认证',
   nqlr_status          int default 0 comment '内勤资料录入状态 0--未提交 1--已提交',
   yc_status            int default 0 comment '验车师验车状态 0--未提交 1--已提交',
   gps_install_status   int default 0 comment '安装GPS流程状态  0--未开始 1--发起安装申请 2--安装GPS成功 3--确认安装成功',
   gps_uninstall_status int default 0 comment '拆卸GPS流程状态 0--未开始 1--发起拆卸申请 2--拆卸GPS成功',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   isCollection         int(1) comment '是否代收 0-是 1-否',
   serviceItem           int(1) comment '前台服务费服务费方式 0-按百分比 1-按金额',
   percent         decimal(10,2) comment '金额 单位(元)',
   moneyAmount         decimal(10,2) comment '百分比(%)',
   serviceCharge         decimal(10,2) comment '服务费(元)',
   feeInstallment           int(1) comment '手续费分期 0-分期 1-全部',
   primary key (id),
   key INX_CUST_ID(cust_id),
   KEY INX_CHANNEL_ID(channel_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '订单表';

DROP TABLE IF EXISTS c_apply_operator;
create table c_apply_operator
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   apply_id             bigint(21) comment '借款单ID',
   role_id              bigint(21) comment '角色ID',
   user_id              bigint(21) comment '用户ID',
   process_node_id      bigint(21) comment '流程节点ID',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   key INX_APPLY_ID(apply_id),
   KEY INX_ROLE_ID(role_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '订单操作员表';

DROP TABLE IF EXISTS c_main_approve_record;
create table c_main_approve_record
(
   id                    bigint(21) not null auto_increment comment '主键',
   apply_id              bigint(21) comment '订单id',
   operator_id           bigint(21) comment '操作员id',
   operator_name         varchar(25) comment '操作员姓名',
   operator_time         datetime comment '操作时间',
   process_node_id       bigint(21) comment '当前节点ID',
   process_node_desc     varchar(128) comment '当前节点名称',
   audit_remark          varchar(512) comment '审核意见',
   create_time           datetime not null comment '创建时间',
   update_time           datetime not null comment '更新时间',
   is_deleted            int(1) default '0' comment '是否删除 0--否 1--是',
   remark                varchar(255) comment '备注，说明',
   primary key (id),
   KEY index_apply_id(apply_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='主流程审批记录表';


DROP TABLE IF EXISTS c_cust_info;
create table c_cust_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   name                 varchar(64) not null comment '姓名',
   mobile               varchar(32) comment '手机号',
   sex                  int(1) comment '性别 0-女 1-男',
   nation               varchar(20) comment '民族',
   birthday             datetime comment '出生年月日 YYYYMMDD',
   cert_id              varchar(32) not null comment '身份证号码',
   validate_begin       datetime comment '身份证有效期起始日期',
   validate_end         datetime comment '身份证有效期结束日期',
   sign_org             varchar(128) comment '签发机关',
   education            int comment '教育程度 0--文盲 1--小学 2--初中 3--高中 4--专科 5--本科 6--硕士 7--博士',
   id_front_photo_url   varchar(255) default null comment '身份证正面图片地址',
   id_back_photo_url    varchar(255) default null comment '身份证背面图片地址',
   proof_Of_Residence    varchar(255) default null comment '居住证明材料图片地址',
   child_num            int comment '子女人数',
   child_adult          int comment '子女情况 1--无子女 2--有子女未成年 4--有子女已成年（可多选）',
   live_address          varchar(255) comment '居住地址',
   live_type            int comment '居住地性质  1--自有商品房全款 2--自有商业房按揭 4--自有商业房全款已抵押 8--自有自建房 16--自有安置房 32--单位宿舍 64--租用 128--父母房产 256--亲属房产（可多选）',
   together_live        int comment '合住情况 1--单人居住 2--配偶合住 4--父母合住 8--亲属合住 16--子女合住 32--同事合住 64--朋友合住（可多选）',
   spouse_name          varchar(64) comment '配偶姓名（如果是已婚，再婚）',
   spouse_phone         varchar(32) comment '配偶手机号',
   contract_name1       varchar(64) comment '紧急联系人1姓名',
   contract_phone1      varchar(32) comment '紧急联系人1电话',
   contract_relation1   varchar(64) comment '关系1',
   contract_name2       varchar(64) comment '紧急联系人2姓名',
   contract_phone2      varchar(32) comment '紧急联系人2电话',
   contract_relation2   varchar(64) comment '关系2',
   contract_name3       varchar(64) comment '紧急联系人3姓名',
   contract_phone3      varchar(32) comment '紧急联系人3电话',
   contract_relation3   varchar(64) comment '关系3',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '客户基本信息表';

DROP TABLE IF EXISTS c_gongjie_info;
create table c_gongjie_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   cust_id              bigint(21) not null comment '客户id',
   name                 varchar(64) not null comment '姓名',
   mobile               varchar(32) comment '手机号',
   sex                  int(1) comment '性别 0-女 1-男',
   cert_id              varchar(32) not null comment '身份证号码',
   id_front_photo_url   varchar(255) default null comment '身份证正面图片地址',
   id_back_photo_url    varchar(255) default null comment '身份证背面图片地址',
   marry_status         int not null default 0 comment '婚姻状况 0--已婚 1--未婚 2--再婚 3--离异 4--丧偶',
   relation             int comment '与主借人关系 0-配偶 1-父母 2-子女 3-亲属 4-股东 5-朋友',
   live_address         varchar(255) comment '居住地址',
   occupation_type      int comment '职业性质 1--自雇2--受薪 3--自由职业',
   company_name         varchar(255) comment '单位名称 如果收入来源是 1，2，4的话',
   company_type         int comment '单位性质 0--国有企业 1--国有控股企业 2--外资企业 3--合资企业 4--私营业务 5--事业单位/政府',
   company_address      varchar(255) comment '单位地址',
   company_tel          varchar(16) comment '单位电话',
   department           varchar(32) comment '部门',
   job                  varchar(32) comment '职务',
   month_income         decimal(8,2) comment '月均收入（单位：元）',
   company_attach_url   varchar(255) comment '单位网查信息附件',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   KEY INX_CUST_ID(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '共借人信息表';

DROP TABLE IF EXISTS c_family_book_info;
create table c_family_book_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   cust_id              bigint(21) not null comment '客户ID',
   relationship         varchar(64) not null comment '申请人与户主关系',
   master_name          varchar(64) not null comment '户主姓名',
   master_sex           int(1) comment '性别 0-女 1-男',
   cert_id              varchar(32) not null comment '户主身份证号码',
   first_page_photo_url varchar(255) comment '户口本首页照片',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   KEY INX_CUST_ID(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '客户户口本信息表';

DROP TABLE IF EXISTS c_family_book_sub_info;
create table c_family_book_sub_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   book_id              bigint(21) not null comment '户口本ID',
   relationship         varchar(64) not null comment '申请人与关联人关系',
   name                 varchar(64) not null comment '关联人姓名',
   sex                  int(1) comment '关联人性别 0-女 1-男',
   cert_id              varchar(32) not null comment '关联人身份证号码',
   book_photo_url       varchar(255) comment '关联人户口页照片',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   KEY INX_book_id(book_id),
   KEY INX_CERT_ID(cert_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '客户户口本关联关系信息表';

DROP TABLE IF EXISTS c_marry_info;
create table c_marry_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   cust_id              bigint(21) not null comment '客户ID',
   marry_status         int not null default 0 comment '婚姻状况 0--已婚 1--未婚 2--再婚 3--离异 4--丧偶',
   marry_date           datetime comment '婚姻登记日期（如果是已婚，再婚，丧偶）',
   spouse_name          varchar(64) comment '配偶姓名（如果是已婚，再婚，丧偶）',
   spouse_phone         varchar(32) comment '配偶手机号',
   spouse_sex           int(1) comment '配偶性别 0-女 1-男（如果是已婚，再婚，丧偶）',
   spouse_cert_id       varchar(32) comment '配偶身份证号码（如果是已婚，再婚，丧偶）',
   validate_begin       datetime comment '配偶身份证有效期起始日期',
   validate_end         datetime comment '配偶身份证有效期结束日期',
   sign_org             varchar(128) comment '配偶签发机关',
   id_front_photo_url   varchar(255) default null comment '配偶身份证正面图片地址',
   id_back_photo_url    varchar(255) default null comment '配偶身份证背面图片地址',
   marry_photo_url      varchar(255) default null comment '结婚证照片地址',
   divorce_date         datetime comment '离婚登记日期（如果是离异）',
   divorce_name         varchar(64) default null comment '原配偶名称（如果是离异）',
   divorce_sex          int(1) comment '原配偶性别 0-女 1-男（如果是离异）',
   divorce_cert_id      varchar(32) comment '原配偶身份证号码（如果是离异）',
   divorce_photo_url    varchar(255) comment '离婚证照片(如果是离异)',
   death_cert_photo_url varchar(255) comment '死亡证明照片地址(如果是丧偶)',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   KEY INX_CUST_ID(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '客户婚姻信息表';

DROP TABLE IF EXISTS c_cust_work_info;
create table c_cust_work_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   cust_id              bigint(21) not null comment '客户ID',
   income_type          int comment '收入来源 1--自雇有执照 2--自雇无执照 4--受薪 8--待业 16--自由职业者',
   company_name         varchar(255) comment '单位名称 如果收入来源是 1，2，4的话',
   company_type         int comment '单位性质 0--国有企业 1--国有控股企业 2--外资企业 3--合资企业 4--私营业务 5--事业单位/政府',
   company_address      varchar(255) comment '单位地址',
   company_tel          varchar(16) comment '单位电话',
   department           varchar(32) comment '部门',
   job                  varchar(32) comment '职务',
   work_age             decimal(3,1) comment '入职年限（单位：年）',
   month_income         decimal(10,1) comment '月均收入（单位：万元）',
   spouse_income_type   int comment '配偶收入来源 1--自雇有执照 2--自雇无执照 4--受薪 8--待业 16--自由职业者',
   spouse_company_name  varchar(255) comment '配偶单位名称 如果收入来源是 1，2，4的话',
   spouse_company_type  int comment '配偶单位性质 0--国有企业 1--国有控股企业 2--外资企业 3--合资企业 4--私营业务 5--事业单位/政府',
   spouse_company_address varchar(255) comment '配偶单位地址',
   spouse_company_tel   varchar(16) comment '配偶单位电话',
   spouse_department    varchar(32) comment '配偶部门',
   spouse_job           varchar(32) comment '配偶职务',
   spouse_work_age      decimal(3,1) comment '配偶入职年限（单位：年）',
   spouse_month_income  decimal(10,1) comment '配偶月均收入（单位：万元）',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   KEY INX_CUST_ID(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '客户工作信息表';

DROP TABLE IF EXISTS c_car_info;
create table c_car_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   cust_id              bigint(21) not null comment '客户id',
   car_num              varchar(32) comment '车牌号',
   car_config_name      varchar(255) comment '车辆通俗配置名称',
   car_type             varchar(64) comment '车辆类型 小型轿车 小型越野客车',
   registrationCarType  varchar(64) comment '登记证书车辆型号',
   car_brand            varchar(64) comment '车辆品牌',
   car_model            varchar(64) comment '车辆型号',
   vin                  varchar(64) DEFAULT NULL COMMENT '车辆识别号',
   engine_no            varchar(64) comment '发动机号',
   car_color            varchar(64) comment '车身颜色',
   car_import_type      int(1) comment '国产/进口  0-国产  1-进口',
   fuel_type            int(1) comment '燃料种类 0-汽油  1-柴油  2-油电混合  3-纯电动 4--其他',
   displacement         int comment '排量 （单位：ml)',
   manufacturer         varchar(128) comment '制造厂名称',
   car_usage            int(1) comment '使用性质 0-非运营   1-运营   2-营转非',
   mileage              int  comment '行驶里程数(公里)',
   get_type             int(1) comment '车辆获得方式 0-自购  2-赠与',
   product_date         datetime comment '出厂日期 YYYYMMDD',
   first_lic_date       datetime comment '首次上牌日期 YYYYMMDD',
   current_lic_date     datetime comment '本次上牌日期 YYYYMMDD',
   register_photo_url1  varchar(255) comment '车辆登记证书1-2页照片地址',
   register_photo_url2  varchar(255) comment '车辆登记证书3-4页照片地址',
   register_photo_url3  varchar(255) comment '车辆登记证书5-6页照片地址',
   register_photo_url4  varchar(255) comment '车辆登记证书7-8页照片地址',
   carLocationId        varchar(255) comment '车辆所在地省id',
   carLocationName      varchar(255) comment '车辆所在地省name',
   carCityId            varchar(255) comment '车辆所在地城市id',
   carCityName          varchar(255) comment '车辆所在地城市name',
   carSeriesId          varchar(255) comment '车系id',
   carSeriesName        varchar(255) comment '车系name',
   carModelId           varchar(255) comment '车型id',
   carModelName         varchar(255) comment '车型name',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   KEY INX_CUST_ID(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '车辆基本信息表';

DROP TABLE IF EXISTS c_car_transfer_info;
create table c_car_transfer_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   car_id               bigint(21) not null comment '车辆id',
   name                 varchar(32) comment '转移登记姓名',
   cert_id              varchar(32) comment '转移登记身份证号',
   get_type             varchar(32) comment '获得方式',
   reg_date             datetime comment '转移登记日期',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   KEY INX_CAR_ID(car_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '车辆转移登记记录表';

DROP TABLE IF EXISTS c_car_mortgage_info;
create table c_car_mortgage_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   car_id               bigint(21) not null comment '车辆id',
   name                 varchar(32) comment '抵押姓名',
   cert_id              varchar(32) comment '抵押身份证号',
   reg_date             datetime comment '抵押日期',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   KEY INX_CAR_ID(car_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '车辆抵押记录表';

DROP TABLE IF EXISTS c_car_driver_info;
create table c_car_driver_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   user_id              bigint(21) not null comment '用户id',
   car_id               bigint(21) not null comment '车辆id',
   vehicle_valid_date   datetime comment '行驶证年检有效期',
   vehicle_front_photo  varchar(255) comment '行驶证正面照片',
   vehicle_back_photo   varchar(255) comment '行驶证背面照片',
   is_driver_lic        int comment '是否有驾驶证 0--无 1--有',
   driver_no            varchar(32) comment '驾驶证档案编号',
   first_driver_date    datetime comment '初次领取驾驶证时间',
   permit_type          varchar(32) comment '准驾车型',
   driver_begin_date    datetime comment '驾驶证有效期开始时间',
   driver_end_date      datetime comment '驾驶证有效期结束时间',
   is_self              int comment '是否申请人持有 0--否 1--是',
   driver_relation      varchar(32) comment '持有人与申请人关系',
   driver_remark        varchar(255) comment '持有人备注',
   driver_front_photo   varchar(255) comment '驾驶证正面照片',
   driver_back_photo    varchar(255) comment '驾驶证背面照片',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   KEY INX_USER_ID(user_id),
   KEY INX_CAR_ID(car_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '行驶证驾驶证信息表';

DROP TABLE IF EXISTS c_car_peccancy_info;
create table c_car_peccancy_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   car_id               bigint(21) not null comment '车辆id',
   total_num            int comment '累计违章次数',
   total_money          decimal(10,2) comment '累计违章罚款',
   total_value          int comment '累计扣分',
   total_full_num       int comment '累计扣12分次数',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   violationAttachmentPhotol               varchar(255) comment '违章附件1',
   violationAttachmentPhotol2               varchar(255) comment '违章附件2',
   primary key (id),
   KEY INX_CAR_ID(car_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '车辆违章信息表';

DROP TABLE IF EXISTS c_car_traffic_insure_info;
create table c_car_traffic_insure_info
(
   id                   bigint(21) not null auto_increment comment '主键，自增',
   car_id               bigint(21) not null comment '车辆id',
   inst_full_name       varchar(255) comment '购买机构全称',
   bussiness_source     varchar(128) comment '业务来源',
   proxy_name           varchar(255) comment '代理点名称',
   insure_number        varchar(128) comment '保单号',
   insure_person        varchar(64)  comment '被保人',
   insure_begin_time    datetime comment '保险起始日期',
   insure_end_time      datetime comment '保险到期日期',
   float_prop           decimal(10,4) comment '浮动比例',
   total_amount         decimal(12,2) comment '保费合计',
   vehicle_tax          decimal(12,2) comment '代收车船税',
   sign_date            datetime comment '签约日期',
   special_agreement    varchar(512) comment '特别约定',
   photo_url            varchar(255) comment '照片',
   create_time          datetime default null comment '创建时间',
   update_time          datetime default null comment '更新时间',
   is_deleted           int(1) default null comment '是否删除 0-否 1-是',
   remark               varchar(300) comment '备注，说明',
   primary key (id),
   key INX_UID(car_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '车辆交强险信息表';

DROP TABLE IF EXISTS c_car_buss_insure_info;
create table c_car_buss_insure_info
(
   id                   bigint(21) not null auto_increment comment '主键，自增',
   car_id               bigint(21) not null comment '车辆id',
   inst_full_name       varchar(255) comment '购买机构全称',
   bussiness_source     varchar(128) comment '业务来源',
   proxy_name           varchar(255) comment '代理点名称',
   insure_number        varchar(128) comment '保单号',
   insure_person        varchar(64)  comment '被保人',
   insure_begin_time    datetime comment '保险起始日期',
   insure_end_time      datetime comment '保险到期日期',
   float_prop           decimal(10,4) comment '浮动比例',
   total_amount         decimal(12,2) comment '保费合计',
   sign_date            datetime comment '签约日期',
   special_agreement    varchar(512) comment '特别约定',
   photo_url            varchar(255) comment '照片',
   create_time          datetime default null comment '创建时间',
   update_time          datetime default null comment '更新时间',
   is_deleted           int(1) default null comment '是否删除 0-否 1-是',
   remark               varchar(300) comment '备注，说明',
   primary key (id),
   key INX_UID(car_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '车辆商业险信息表';

DROP TABLE IF EXISTS c_car_insure_detail_info;
create table c_car_insure_detail_info
(
   id                   bigint(21) not null auto_increment comment '主键，自增',
   insure_id            bigint(21) not null comment '保险id',
   type                 int comment '险种 0--车辆损失险 1--第三者责任险 2--车上人员责任险：司机 3--车上人员责任险：乘客 4--车身划痕险 5--涉水险 6--自燃损失险 7--玻璃单独破碎险（国产） 8--玻璃单独破碎险（进口） ',
   is_no_deduct         int comment '是否有不计免赔 0--否 1--是',
   max_pay_amount       decimal(12,2) comment '最高赔付金额',
   amount               decimal(12,2) comment '保费金额',
   create_time          datetime default null comment '创建时间',
   update_time          datetime default null comment '更新时间',
   is_deleted           int(1) default null comment '是否删除 0-否 1-是',
   remark               varchar(300) comment '备注，说明',
   primary key (id),
   key INX_UID(insure_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '车辆保险险种详细信息表';

DROP TABLE IF EXISTS c_car_verify_info;
create table c_car_verify_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   car_id               bigint(21) not null comment '车辆id',
   car_cond             varchar(128) comment '车况反馈',
   suggestion           varchar(512) comment '车况描述意见',
   config_table_photo   varchar(255) comment '配置表附件地址',
   maintain_photo       varchar(255) comment '维修保养状况附件地址',
   car_assessment_price decimal(10,2) comment '二手车市场评估价格',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   KEY INX_CAR_ID(car_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '验车信息表';

DROP TABLE IF EXISTS c_car_photo_info;
create table c_car_photo_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   bigclass_id          bigint(21) not null comment '图片大类id',
   car_id               bigint(21) not null comment '车辆id',
   photo_name           varchar(128) comment '图片名称',
   photo_url            varchar(255) comment '图片地址',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   KEY INX_CAR_ID(car_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '车辆照片表';

DROP TABLE IF EXISTS c_car_photo_bigclass_info;
create table c_car_photo_bigclass_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   class_name           varchar(128) comment '大类名称',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   KEY INX_CAR_BIGCLASS_ID(id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '车辆照片大类表';

DROP TABLE IF EXISTS c_id_auth_info;
create table c_id_auth_info
(
   id                   bigint(21) not null auto_increment comment '主键，自增',
   cust_id              bigint(21) comment '客户id',
   apply_id             bigint(21) comment '借款id',
   type                 int comment '类型 0--主借人 1--共借人',
   gongan_photol_id     varchar(100) default null comment '公安部预留用户图片id',
   is_id_auth           int(1) default null comment '是否通过认证',
   id_front_photo_url   varchar(255) default null comment '身份证正面图片地址',
   id_back_photo_url    varchar(255) default null comment '身份证背面图片地址',
   hold_identify_photo  varchar(255) default null comment '手持身份证照片地址',
   auth_time            datetime default null comment '认证时间',
   user_name            varchar(32) default null comment '姓名',
   id_number            varchar(20) default null comment '身份证号码',
   nation               varchar(20) default null comment '民族',
   address              varchar(255) default null comment '住址',
   sign_orgaization     varchar(200) default null comment '发证机关',
   validity_period      varchar(100) default null comment '有效期',
   create_time          datetime default null comment '创建时间',
   update_time          datetime default null comment '更新时间',
   is_deleted           int(1) default null comment '是否删除 0-否 1-是',
   remark               varchar(300) comment '备注，说明',
   primary key (id),
   key INX_UID(cust_id),
   KEY INX_APPLY_ID(apply_id),
   key INX_IDN(id_number)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '客户身份证信息表';

DROP TABLE IF EXISTS c_liveness_auth_info;
create table c_liveness_auth_info
(
   id                   bigint(21) not null auto_increment comment '主键 自增',
   cust_id              bigint(21) comment '客户id',
   apply_id             bigint(21) comment '借款id',
   type                 int comment '类型 0--主借人 1--共借人',
   is_liveness_auth     int(1) comment '是否认证 0--否 1--是',
   liveness_blink_photo_url varchar(255) comment '活体眨眼图片地址',
   liveness_nod_photo_url varchar(255) comment '活体上下点头图片地址',
   liveness_mouth_photo_url varchar(255) comment '活体张嘴图片地址',
   liveness_yaw_photo_url varchar(255) comment '活体左右摇头图片地址',
   liveness_file_url    varchar(255),
   liveness_auth_time   datetime,
   create_time          datetime default null comment '创建时间',
   update_time          datetime default null comment '更新时间',
   is_deleted           int(1) default null comment '是否删除 0-否 1-是',
   remark               varchar(300) comment '备注，说明',
   primary key (id),
   key INX_UID(cust_id),
   KEY INX_APPLY_ID(apply_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '用户活体数据认证信息表';

DROP TABLE IF EXISTS c_judicial_auth_info;
create table c_judicial_auth_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   cust_id              bigint(21) comment '客户id',
   apply_id             bigint(21) comment '借款id',
   type                 int comment '类型 0--主借人 1--共借人',
   court_personal       int comment '全国法院被执行人查询（个人） 0--正常 1--异常',
   court_personal_photo varchar(255) comment '全国法院被执行人查询（个人）附件地址',
   zhixing_personal     int comment '中国执行信息公开网查询（个人） 0--正常 1--异常',
   zhixing_personal_photo varchar(255) comment '中国执行信息公开网查询（个人）附件地址',
   risk_personal        int comment '风险信息网查询（个人） 0--正常 1--异常',
   risk_personal_photo  varchar(255) comment '风险信息网查询（个人） 附件地址',
   warn_personal        int comment '风险预警网查询（个人） 0--正常 1--异常',
   warn_personal_photo  varchar(255) comment '风险预警网查询（个人） 附件地址',
   personal_remark      varchar(512) comment '个人备注',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   KEY INX_CUST_ID(cust_id),
   KEY INX_APPLY_ID(apply_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '司法认证表';

DROP TABLE IF EXISTS c_telecom_basic_info;
create table c_telecom_basic_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) comment '客户id',
   apply_id             bigint(21) comment '借款id',
   type                 int comment '类型 0--主借人 1--共借人',
   mobile               varchar(16) comment '手机号',
   audit_task_id        varchar(200) comment '运营商认证task_id',
   audit_status         int(2) comment '运营商认证状态 2--认证中 3--认证完成 4--认证失败 5--认证失效',
   audit_time           datetime comment '认证时间',
   expire_time          datetime comment '过期时间',
   audit_result         longtext comment '运营商认证结果',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '修改时间',
   is_deleted           int(1) comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key IND_APPLY_ID(apply_id),
   key IND_USER_ID(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '客户运营商信息表';

DROP TABLE IF EXISTS c_telecom_auth_info;
create table c_telecom_auth_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) comment '客户id',
   apply_id             bigint(21) comment '借款id',
   type                 int comment '类型 0--主借人 1--共借人',
   is_real_auth         int comment '是否实名认证 0--否 1--是',
   native_place         varchar(128) comment '手机归属地',
   in_time              int comment '入网时长（单位：月）',
   active_3m            int comment '近三个月通话活跃天数(单位：天)',
   active_6m            int comment '近六个月通话活跃天数(单位：天)',
   bill_detail_url      varchar(255) comment '魔蝎账单详情附件地址',
   report_url           varchar(255) comment '魔蝎报告附件地址',
   compatible_report_url varchar(255) comment '兼容报告附件地址',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '修改时间',
   is_deleted           int(1) comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key IND_APPLY_ID(apply_id),
   key IND_USER_ID(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '客户运营商认证表';

DROP TABLE IF EXISTS c_telecom_roam_info;
create table c_telecom_roam_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   apply_id             bigint(21) DEFAULT NULL COMMENT '借款申请ID',
   cust_id              bigint(21) DEFAULT NULL COMMENT '用户ID',
   type                 int comment '类型 0--主借人 1--共借人',
   location             varchar(128) comment '漫游地址',
   roam_day_cnt_3m      int comment '近三月漫游天数',
   roam_day_cnt_6m      int comment '近六月漫游天数',
   continue_roam_cnt_3m int comment '近3月最大连续漫游天数',
   continue_roam_cnt_6m int comment '近6月最大连续漫游天数',
   max_roam_day_cnt_3m  int comment '近3月连续漫游1天以上的次数',
   max_roam_day_cnt_6m  int comment '近6月连续漫游1天以上的次数',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '修改时间',
   is_deleted           int(1) comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   KEY IND_APPLY_ID(apply_id),
   KEY IND_CUST_ID(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '客户运营商漫游信息表';

DROP TABLE IF EXISTS c_telecom_call_risk_analysis;
CREATE TABLE c_telecom_call_risk_analysis (
   id                   bigint(21) NOT NULL AUTO_INCREMENT COMMENT '运营商风险分析表自增id',
   apply_id             bigint(21) DEFAULT NULL COMMENT '借款申请ID',
   cust_id              bigint(21) DEFAULT NULL COMMENT '用户ID',
   type                 int comment '类型 0--主借人 1--共借人',
   analysis_item        varchar(20) DEFAULT NULL COMMENT '风险项',
   analysis_desc        varchar(20) DEFAULT NULL COMMENT '风险项描述',
   call_cnt_1m          int(10) DEFAULT NULL COMMENT '近一月通话次数',
   call_cnt_3m          int(10) DEFAULT NULL COMMENT '近三月通话次数',
   call_cnt_6m          int(10) DEFAULT NULL COMMENT '近六月通话次数',
   avg_call_cnt_3m      float DEFAULT NULL COMMENT '近三月平均通话次数',
   avg_call_cnt_6m      float DEFAULT NULL COMMENT '近六月平均通话次数',
   call_time_1m         int(10) DEFAULT NULL COMMENT '近一月通话时长(秒)',
   call_time_3m         int(10) DEFAULT NULL COMMENT '近三月通话时长(秒)',
   call_time_6m         int(10) DEFAULT NULL COMMENT '近六月通话时长(秒)',
   avg_call_time_3m     float DEFAULT NULL COMMENT '近三月平均通话时长(秒)',
   avg_call_time_6m     float DEFAULT NULL COMMENT '近六月平均通话时长(秒)',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '修改时间',
   is_deleted           int(1) comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   PRIMARY KEY (id),
   KEY IND_APPLY_ID(apply_id),
   KEY IND_CUST_ID(cust_id)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT='运营商风险分析表';

DROP TABLE IF EXISTS c_telecom_friend_circle;
CREATE TABLE c_telecom_friend_circle (
   id                   bigint(21) NOT NULL AUTO_INCREMENT COMMENT '朋友圈ID',
   apply_id             bigint(21) DEFAULT NULL COMMENT '借款申请ID',
   cust_id              bigint(21) DEFAULT NULL COMMENT '用户ID',
   type                 int comment '类型 0--主借人 1--共借人',
   peer_number          varchar(20) DEFAULT NULL COMMENT '对方号码',
   peer_num_loc         varchar(256) DEFAULT NULL COMMENT '联系人号码归属地',
   group_name           varchar(20) DEFAULT NULL COMMENT '号码类型',
   company_name         varchar(20) DEFAULT NULL COMMENT '号码标识',
   call_cnt             varchar(20) DEFAULT NULL COMMENT '通话次数',
   call_time            varchar(25) DEFAULT NULL COMMENT '通话时长(秒)',
   dial_cnt             varchar(20) DEFAULT NULL COMMENT '主叫次数',
   dialed_cnt           varchar(20) DEFAULT NULL COMMENT '被叫次数',
   dial_time            varchar(25) DEFAULT NULL COMMENT '主叫时长(秒)',
   dialed_time          varchar(25) DEFAULT NULL COMMENT '被叫时长(秒)',
   key_top              varchar(20) DEFAULT NULL COMMENT '6月top3或者3月top3  peer_num_top3_3m或者peer_num_top3_6m',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '修改时间',
   is_deleted           int(1) comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   PRIMARY KEY (id),
   KEY IND_APPLY_ID(apply_id),
   KEY IND_CUST_ID(cust_id)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT='运营商通讯录朋友圈top项';

DROP TABLE IF EXISTS c_telecom_call_contact_detail;
CREATE TABLE c_telecom_call_contact_detail (
   id                   bigint(21) NOT NULL AUTO_INCREMENT COMMENT '序号id',
   apply_id             bigint(21) DEFAULT NULL COMMENT '借款申请ID',
   cust_id              bigint(21) DEFAULT NULL COMMENT '用户ID',
   type                 int comment '类型 0--主借人 1--共借人',
   city                 varchar(256) DEFAULT NULL COMMENT '联系人号码归属地',
   peer_num             varchar(256) DEFAULT NULL COMMENT '联系人号码',
   is_emergency         int DEFAULT 0 comment '是否是紧急联系人 0--否 1--是',
   p_relation           varchar(256) DEFAULT NULL COMMENT '与联系人关系',
   group_name           varchar(256) DEFAULT NULL COMMENT '号码类型',
   company_name         varchar(256) DEFAULT NULL COMMENT '号码标识',
   call_cnt_1w          varchar(20) DEFAULT NULL COMMENT '近一周通话次数',
   call_cnt_1m          varchar(20) DEFAULT NULL COMMENT '近一月通话次数',
   call_cnt_3m          varchar(20) DEFAULT NULL COMMENT '近三月通话次数',
   call_cnt_6m          varchar(20) DEFAULT NULL COMMENT '近六月通话次数',
   call_time_3m         varchar(20) DEFAULT NULL COMMENT '近三月通话时长(秒)',
   call_time_6m         varchar(25) DEFAULT NULL COMMENT '近六月通话时长(秒)',
   dial_cnt_3m          varchar(20) DEFAULT NULL COMMENT '近三月主叫次数',
   dial_cnt_6m          varchar(20) DEFAULT NULL COMMENT '近六月主叫次数',
   dial_time_3m         varchar(25) DEFAULT NULL COMMENT '近三月主叫时长(秒)',
   dial_time_6m         varchar(20) DEFAULT NULL COMMENT '近六月主叫时长(秒)',
   dialed_cnt_3m        varchar(20) DEFAULT NULL COMMENT '近三月被叫次数',
   dialed_cnt_6m        varchar(20) DEFAULT NULL COMMENT '近六月被叫次数',
   dialed_time_3m       varchar(25) DEFAULT NULL COMMENT '近三月被叫时长(秒)',
   dialed_time_6m       varchar(25) DEFAULT NULL COMMENT '近六月被叫时长(秒)',
   call_cnt_morning_3m  varchar(20) DEFAULT NULL COMMENT '近三月早晨通话次数',
   call_cnt_morning_6m  varchar(20) DEFAULT NULL COMMENT '近六月早晨通话次数',
   call_cnt_noon_3m     varchar(20) DEFAULT NULL COMMENT '近三月中午通话次数',
   call_cnt_noon_6m     varchar(20) DEFAULT NULL COMMENT '近六月中午通话次数',
   call_cnt_afternoon_3m varchar(20) DEFAULT NULL COMMENT '近三月下午通话次数',
   call_cnt_afternoon_6m varchar(20) DEFAULT NULL COMMENT '近六月下午通话次数',
   call_cnt_evening_3m  varchar(20) DEFAULT NULL COMMENT '近三月晚上通话次数',
   call_cnt_evening_6m  varchar(20) DEFAULT NULL COMMENT '近六月晚上通话次数',
   call_cnt_night_3m    varchar(20) DEFAULT NULL COMMENT '近三月凌晨通话次数',
   call_cnt_night_6m    varchar(20) DEFAULT NULL COMMENT '近六月凌晨通话次数',
   call_cnt_weekday_3m  varchar(20) DEFAULT NULL COMMENT '近三月工作日通话次数',
   call_cnt_weekday_6m  varchar(20) DEFAULT NULL COMMENT '近六月工作日通话次数',
   call_cnt_weekend_3m  varchar(20) DEFAULT NULL COMMENT '近三月周末通话次数',
   call_cnt_weekend_6m  varchar(20) DEFAULT NULL COMMENT '近六月周末通话次数',
   call_cnt_holiday_3m  varchar(20) DEFAULT NULL COMMENT '近三月节假日通话次数',
   call_cnt_holiday_6m  varchar(20) DEFAULT NULL COMMENT '近六月节假日通话次数',
   call_if_whole_day_3m varchar(20) DEFAULT NULL COMMENT '近三月是否有全天联系',
   call_if_whole_day_6m varchar(20) DEFAULT NULL COMMENT '近六月是否有全天联系',
   trans_start          varchar(20) DEFAULT NULL COMMENT '第一次通话时间',
   trans_end            varchar(20) DEFAULT NULL COMMENT '最近一次通话时间',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '修改时间',
   is_deleted           int(1) comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   PRIMARY KEY (id),
   KEY IND_APPLY_ID(apply_id),
   KEY IND_CUST_ID(cust_id)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT='运营商详细通话记录';

DROP TABLE IF EXISTS c_credit_auth_info;
CREATE TABLE c_credit_auth_info (
   id                   bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键ID自增',
   apply_id             bigint(21) DEFAULT NULL COMMENT '借款申请ID',
   cust_id              bigint(21) DEFAULT NULL COMMENT '用户ID',
   type                 int comment '类型 0--主借人 1--共借人',
   td_score             int comment '同盾贷前审核评分',
   td_score_attach_url  varchar(255) comment '同盾贷前审核评分附件地址',
   td_risk_attach_url   varchar(255) comment '同盾贷前审核风险情况附件地址',
   br_rule_score        int comment '百融贷前审核规则集分数',
   br_credit_score      int comment '百融代签审核信用评分分数',
   br_attach_url        varchar(255) comment '百融贷前审核报告附件地址',
   has_judgement        int default 0 comment '有无裁判文书（裁判文书网） 0--无 1--有',
   judgement_attach_url varchar(255) comment '裁判文书网附件',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '修改时间',
   is_deleted           int(1) comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   PRIMARY KEY (id),
   KEY IND_APPLY_ID(apply_id),
   KEY IND_CUST_ID(cust_id)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT='信贷认证';

DROP TABLE IF EXISTS c_credit_report;
create table c_credit_report
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   type                 int comment '类型 0--主借人 1--共借人',
   collection_date      datetime comment '征信报告采集时间',
   house_loan_num       int comment '个人住房贷款笔数 (信用提示模块)',
   house_buss_loan_num  int comment '个人商用房贷款笔数 (信用提示模块)',
   other_loan_num       int comment '其他贷款笔数 (信用提示模块)',
   first_loan_start_date varchar(20) comment '首笔贷款发放月份 YYYYMM (信用提示模块)',
   credit_card_num      int comment '贷记卡账户数 (信用提示模块)',
   first_credit_card_start_date varchar(20) comment '首张借记卡发卡月份 YYYYMM (信用提示模块)',
   semi_credit_card_num int comment '准贷记卡账户数 (信用提示模块)',
   first_semi_credit_card_start_date varchar(20) comment '首张准贷记卡发卡月份 YYYYMM (信用提示模块)',
   self_declare_num     int comment '本人声明数量 (信用提示模块)',
   objection_num        int comment '异议标注数量 (信用提示模块)',
   loan_overdue_num     int comment '贷款逾期笔数 (逾期违约模块)',
   loan_overdue_month_num int comment '贷款逾期月份数 (逾期违约模块)',
   loan_max_amount      decimal(10,2) comment '贷款逾期单月最高逾期总额 (逾期违约模块)',
   loan_max_month_num   int comment '最长逾期月数 (逾期违约模块)',
   card_overdue_num     int comment '贷记卡逾期账户数 (逾期违约模块)',
   card_month_num       int comment '贷记卡逾期月份数 (逾期违约模块)',
   card_overdue_max_amount decimal(10,2) comment '贷记卡单月最高逾期总额 (逾期违约模块)',
   card_max_month_num   int comment '贷记卡最长逾期月份数 (逾期违约模块)',
   semi_card_overdue_num int comment '准贷记卡60天以上透支账户数 (逾期违约模块)',
   semi_card_month_num  int comment '准贷记卡60天以上透支月份数 (逾期违约模块)',
   semi_card_max_amount int comment '准贷记卡60天以上透支单月最高透支金额 (逾期违约模块)',
   semi_card_max_month_num int comment '准贷记卡60天以上透支最长透支月数 (逾期违约模块)',
   loan_legal_org_num   int comment '贷款法人机构数 (授信概要模块)',
   loan_org_num         int comment '贷款机构数 (授信概要模块)',
   loan_num             int comment '贷款笔数 (授信概要模块)',
   loan_total_amount    decimal(10,2) comment '合同总金额 (授信概要模块)',
   loan_left_amount     decimal(10,2) comment '贷款余额 (授信概要模块)',
   loan_total_month_amount decimal(10,2) comment '总月还款 (授信概要模块)',
   card_legal_org_num   int comment '发卡法人机构数 (授信概要模块)',
   card_org_num         int comment '发卡机构数 (授信概要模块)',
   card_account_num     int comment '账户数 (授信概要模块)',
   card_total_amount    decimal(10,2) comment '授信总额 (授信概要模块)',
   card_max_amount      decimal(10,2) comment '单家行最高授信额 (授信概要模块)',
   card_min_amount      decimal(10,2) comment '单家行最低授信额 (授信概要模块)',
   card_used_amount     decimal(10,2) comment '已用额度 (授信概要模块)',
   card_avg_amount      decimal(10,2) comment '近6个月平均使用额度 (授信概要模块)',
   history_query_num    int comment '近两个月征信历史查询次数 (根据记录表 自动算出)',
   card_month_amount    decimal(10,2) comment '信用卡月还款（信用卡已用额度/10)',
   credit_loan_month_amount decimal(10,2) comment '信用贷款月还款金额 (信用贷款月还款+信用卡月还款)',
   report_attach_url    varchar(255) comment '征信报告附件地址',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='客户央行征信信息表';

DROP TABLE IF EXISTS c_credit_loan_detail;
create table c_credit_loan_detail
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   type                 int comment '类型 0--主借人 1--共借人',
   loan_org             varchar(64) comment '贷款机构',
   loan_amount          decimal(10,2) comment '贷款金额',
   loan_type            int comment '贷款类型 0--抵押担保 1--信用免担保 2--组合认证',
   loan_period          int comment '贷款期数',
   loan_begin_time      datetime comment '贷款开始时间',
   loan_end_time        datetime comment '贷款到期时间',
   account_status       int comment '账户状态 0--正常 1--逾期',
   five_class_status    int comment '五级分类 0--正常 1--关注 2--次级  3--可疑 4--损失',
   capital_amount       decimal(10,2) comment '本金金额',
   left_period          int comment '剩余期数',
   cur_month_predict_amount decimal(10,2) comment '本月应还款金额',
   cur_month_date       datetime comment '本次应还款日',
   cur_month_actural_amount decimal(10,2) comment '本月实还款金额',
   last_repayment_datge datetime comment '最近一次还款日期',
   cur_overdue_num      int comment '当前逾期期数',
   cur_overdue_amount   decimal(10,2) comment '当前逾期金额',
   overdue_m2_capital   decimal(10,2) comment '逾期31-60天未还本金',
   overdue_m3_capital   decimal(10,2) comment '逾期61-90天未还本金',
   overdue_m45_capital  decimal(10,2) comment '逾期91-180天未还本金',
   overdue_m6_capital   decimal(10,2) comment '逾期180天以上未还本金',
   repayment_info       varchar(1000) comment '两年前还款记录情况（文字描述）',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='客户央行征信贷款交易信息明细表';

DROP TABLE IF EXISTS c_credit_card_detail;
create table c_credit_card_detail
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   type                 int comment '类型 0--主借人 1--共借人',
   card_org             varchar(64) comment '信用卡发卡机构',
   card_amount          decimal(10,2) comment '授信额度',
   card_share_amount    decimal(10,2) comment '共享授信额度',
   card_type            int comment '类型 0--抵押担保 1--信用免担保 2--组合认证',
   account_status       int comment '账户状态 0--正常 1--冻结 2--止付 3--呆帐',
   used_amount          decimal(10,2) comment '已用额度',
   avg_used_amount      decimal(10,2) comment '近6个月平均使用额度',
   max_used_amount      decimal(10,2) comment '最大使用额度',
   cur_overdue_num      int comment '当前逾期期数',
   cur_overdue_amount   decimal(10,2) comment '当前逾期金额',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='客户央行征信信用卡交易信息明细表';

DROP TABLE IF EXISTS c_credit_buss_query_record;
create table c_credit_buss_query_record
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   type                 int comment '类型 0--主借人 1--共借人',
   query_date           datetime comment '查询时间',
   query_org            varchar(64) comment '查询机构',
   query_reason         int comment '查询原因 0--贷后管理 1--信用卡审批 2--保前查询 3--贷款审批 4--担保资格查询',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='客户央行征信机构查询记录表';

DROP TABLE IF EXISTS c_credit_personal_query_record;
create table c_credit_personal_query_record
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   type                 int comment '类型 0--主借人 1--共借人',
   query_date           datetime comment '查询时间',
   query_org            varchar(64) comment '查询机构',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='客户央行征信个人查询记录表';

DROP TABLE IF EXISTS c_cust_finance_info;
create table c_cust_finance_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   type                 int comment '类型 0--主借人 1--共借人',
   fin_type             int comment '财力证明类型 0--房产 1--车辆',
   property             int comment '性质 0--安置房 1--商品房 2--自建房福利房 3--写字楼 4--商铺',
   status               int comment '状态 0--全款 1--抵押 2--按揭',
   num                  int comment '数量',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='客户财力证明表';

DROP TABLE IF EXISTS c_cust_company_info;
create table c_cust_company_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   type                 int comment '类型 0--主借人 1--共借人',
   company_name         varchar(128) comment '企业名称',
   industry             varchar(128) comment '企业所属行业',
   found_time           datetime comment '企业成立时间',
   gongshang_enterprise_photo varchar(255) comment '工商信息查询（企业）附件地址',
   court_enterprise     int comment '全国法院被执行人查询（企业） 0--正常 1--异常',
   court_enterprise_photo varchar(255) comment '全国法院被执行人查询（企业）附件地址',
   zhixing_enterprise   int comment '中国执行信息公开网查询（企业） 0--正常 1--异常',
   zhixing_enterprise_photo varchar(255) comment '中国执行信息公开网查询（企业）附件地址',
   risk_enterprise      int comment '风险信息网查询（企业） 0--正常 1--异常',
   risk_enterprise_photo varchar(255) comment '风险信息网查询（企业） 附件地址',
   warn_enterprise      int comment '风险预警网查询（企业） 0--正常 1--异常',
   warn_enterprise_photo varchar(255) comment '风险预警网查询（企业） 附件地址',
   enterprise_status    int comment '工商注册企业状态 0--存续 1--吊销 2--注销 3--无法核实',
   enterprise_remark    varchar(512) comment '企业备注',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='客户关联企业信息表';

DROP TABLE IF EXISTS c_car_price_info;
create table c_car_price_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   che300_price         decimal(10,2) comment '车300收车价(元)',
   che300_thprice       decimal(10,2) comment '车300同行交易价(批发价)(元)',
   che300_attach_url    varchar(255) comment '车300评估价附件地址',
   jingzhengu_price     decimal(10,2) comment '精真估评估价',
   jingzhengu_attach_url varchar(255) comment '精真估评估价附件地址',
   nake_price           decimal(10,2) comment '裸车价',
   depreciation_base    int comment '折旧基数 (车龄月数 申请年月日减首次上户年月日 不足1月的按1月算）',
   depreciation_ratio   decimal(10,4) comment '折旧系数 0--6‰(千分之6) 每月包括按揭房、抵押房、全款房、寿险保单、公积金  1--8‰（千分之8) 每月包括流水结息、他行车贷、打卡工资； ',
   credit_ratio         decimal(10,4) comment '授信成数',
   tsingnuo_price       decimal(10,2) comment '氢诺拟可贷金额（=裸车价*折旧基数*折旧系数*授信成数)',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='车辆评估价格表';

DROP TABLE IF EXISTS c_cust_income_info;
create table c_cust_income_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   income_type          int comment '认收方案类别 0--按揭房贷方案 1--抵押房贷方案 2--全款房方案 3--寿险保单方案 4--他行车贷方案 5--流水结息方案 6--公积金方案 7--打卡工资方案',
   income_amount        decimal(10,2) comment '收入金额',
   income_confirm_amount decimal(10,2) comment '收入认定金额 （收入金额*对应方案类别的系数)',
   loan_amount          decimal(10,2) comment '拟定可贷金额',
   dti                  decimal(10,4) comment 'DTI 收入负债比 = 收入认定金额/(征信认定的负债+本次拟贷款月供)',
   apply_amount         decimal(10,2) comment '申请额度 单位(元)',
   apply_period         int default 36 comment '申请期限  12/24/36',
   attach_url           varchar(255) comment '附件地址',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='客户收入认定方式表';

DROP TABLE IF EXISTS c_cust_interview_info;
create table c_cust_interview_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   overview             varchar(2000) comment '面审意见综述',
   interview_result     int comment '面审意见 0--拒绝 1--通过',
   rejection_reason     varchar(1000) comment '拒绝原因',
   loan_amount          decimal(10,2) comment '通过的金额',
   loan_period          int comment '通过的期数',
   scene_evidence_url1  varchar(255) comment '现场证据附件地址1',
   scene_evidence_url2  varchar(255) comment '现场证据附件地址2',
   create_time          datetime  comment '创建时间',
   update_time          datetime  comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='面审最终意见表';

DROP TABLE IF EXISTS c_final_judgement_info;
create table c_final_judgement_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   judgement_result     int comment '终审意见 0--通过 1--拒绝 2--补充资料',
   rejection_reason     varchar(1000) comment '拒绝原因',
   loan_amount          decimal(10,2) comment '通过的金额',
   loan_period          int comment '通过的期数',
   audit_time           datetime comment '审核时间',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='终审意见表';

DROP TABLE IF EXISTS c_supplement_info;
create table c_supplement_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   supplement_desc      varchar(255) comment '补充材料说明',
   initiator            bigint(21) comment '发起人id',
   nominee              bigint(21) comment '指定人id',
   supplement_attach_url varchar(255) comment '补充材料附件地址',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='补充材料表';

DROP TABLE IF EXISTS c_bankcard_info;
create table c_bankcard_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   card_no              varchar(32) not null comment '银行卡号',
   bank_name            varchar(30) default null comment '银行名',
   province_city        varchar(50) default null comment '开户省市',
   bank_subbranch       varchar(100) default null comment '开户支行',
   card_status          int(3) default '0' comment '银行卡状态 0--失败 1--成功 2--创建 3--发送验证码成功 4--发送验证码失败 5--验证失败',
   binding_time         datetime default null comment '绑卡时间',
   binding_desc         varchar(50) default null comment '绑卡描述',
   sequence_no          varchar(50) default null comment '流水号',
   is_default_card      int(3) default '0' comment '是否是默认银行卡',
   binding_mobile       varchar(50) default null comment '银行预留手机号',
   pay_platform         varchar(50) default null comment '支付平台',
   result_code          varchar(20) default null comment '支付平台返回码',
   result_msg           varchar(50) default null comment '支付平台返回消息',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   KEY index_user_id(cust_id),
   KEY index_card_no(card_no)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='客户银行卡绑定信息';

DROP TABLE IF EXISTS c_contract_info;
create table c_contract_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   contract_no          varchar(32) comment '合同编号',
   contract_title1      varchar(64) comment '合同标题1',
   contract_url1        varchar(64) comment '合同地址1',
   contract_title2      varchar(64) comment '合同标题2',
   contract_url2        varchar(64) comment '合同地址2',
   contract_title3      varchar(64) comment '合同标题3',
   contract_url3        varchar(64) comment '合同地址3',
   contract_title4      varchar(64) comment '合同标题4',
   contract_url4        varchar(64) comment '合同地址4',
   contract_title5      varchar(64) comment '合同标题5',
   contract_url5        varchar(64) comment '合同地址5',
   contract_title6      varchar(64) comment '合同标题6',
   contract_url6        varchar(64) comment '合同地址6',
   contract_sign_date   datetime comment '线下合同签订时间',
   contract_scene_url   varchar(255) comment '合同签署现场照片地址',
   contract_attach_url  varchar(255) comment '合同签订附件地址',
   contract_xcqy_url  varchar(255) comment '现场签约照片',
   contract_dyysl_url  varchar(255) comment '抵押已受理照片',
   contract_htfj_url  varchar(255) comment '合同附件',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='客户合同信息表';

DROP TABLE IF EXISTS c_car_gps_info;
create table c_car_gps_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   car_id               bigint(21) not null comment '车辆id',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   gps_install_date     datetime comment '车辆GPS安装时间 YYYYMMDD',
   group_photo_url      varchar(255) comment '人车合影图片地址',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_car_id(car_id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='车辆GPS信息表';

DROP TABLE IF EXISTS c_car_gps_detail_info;
create table c_car_gps_detail_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   car_id               bigint(21) not null comment '车辆id',
   is_wiredless         int comment '是否无线 0--否 1--是 2--其他',
   gps_wired_no         varchar(255) comment 'GPS串码',
   gps_photo_url        varchar(255) comment 'GPS安装位置图片地址',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_car_id(car_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='车辆GPS详情信息表';

DROP TABLE IF EXISTS c_car_gps_uninstall_info;
CREATE TABLE c_car_gps_uninstall_info (
  id bigint(21) NOT NULL AUTO_INCREMENT COMMENT '主键',
  car_id bigint(21) NOT NULL COMMENT '车辆id',
  bigclass_id int(11) DEFAULT NULL COMMENT '图片类型id',
  photo_name varchar(255) DEFAULT NULL,
  photo_url varchar(255) DEFAULT NULL COMMENT 'GPS卸载图片地址',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  is_deleted int(1) DEFAULT 0 COMMENT '是否删除 0--否 1--是',
  remark varchar(255) DEFAULT NULL COMMENT '备注，说明',
  PRIMARY KEY (id),
  KEY index_car_id (car_id)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT='车辆卸载GPS详情信息表';

DROP TABLE IF EXISTS c_car_buss_mortgage_info;
create table c_car_buss_mortgage_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   car_id               bigint(21) not null comment '车辆id',
   cust_name            varchar(32) comment '姓名',
   cust_sex             int comment '性别 0--女 1--男',
   cust_id_no           varchar(32) comment '身份证号',
   register_photo_url1  varchar(255) comment '车辆登记证书1-2页照片地址',
   register_photo_url2  varchar(255) comment '车辆登记证书3-4页照片地址',
   register_photo_url3  varchar(255) comment '车辆登记证书5-6页照片地址',
   register_photo_url4  varchar(255) comment '车辆登记证书7-8页照片地址',
   proxy_book_url       varchar(255) comment '委托书（客户签字授权）照片地址',
   mortgage_contract_url varchar(255) comment '抵押合同(车管所）照片地址',
   cert_photo_url       varchar(255) comment '身份证复印件照片地址',
   recv_confirm         int default 0 comment '接收资料确认 0--否 1--是 2--驳回',
   recv_confirm_date    datetime comment '接收资料确认时间',
   refuse_reason        varchar(255) comment '驳回原因',
   accept_confirm       int default 0 comment '抵押是否受理 0--否 1--是',
   accept_confirm_date  datetime comment '抵押受理时间',
   bill_attach_url      varchar(255) comment '抵押受理小票附件',
   complete_confirm     int default 0 comment '抵押业务是否完成 0--否 1--是',
   register_photo_url5  varchar(255) comment '抵押完成后车辆登记证书1-2页照片地址',
   register_photo_url6  varchar(255) comment '抵押完成后车辆登记证书3-4页照片地址',
   register_photo_url7  varchar(255) comment '抵押完成后车辆登记证书5-6页照片地址',
   register_photo_url8  varchar(255) comment '抵押完成后车辆登记证书7-8页照片地址',
   complete_confirm_date datetime comment '抵押业务完成时间',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
    key index_car_id(car_id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='车辆抵押登记信息表';


DROP TABLE IF EXISTS c_car_detention_info;
create table c_car_detention_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   car_id               bigint(21) not null comment '车辆id',
   cust_name            varchar(32) comment '姓名',
   cust_sex             int comment '性别 0--女 1--男',
   cust_id_no           varchar(32) comment '身份证号',
   is_settle            int default 0 comment '财务是否结清 0--否 1--是',
   settle_attach_url    varchar(255) comment '财务结清附件地址',
   settle_date          datetime comment '财务结清时间',
   settle_confirem      int default 0 comment '内勤结清确认 0--否 1--确认',
   proxy_book_url       varchar(255) comment '委托书（客户签字授权）照片地址',
   cert_photo_url       varchar(255) comment '身份证复印件照片地址',
   recv_confirm         int default 0 comment '接收资料确认 0--否 1--是 2--驳回',
   recv_confirm_date    datetime comment '接收资料确认时间',
   refuse_reason        varchar(255) comment '驳回原因',
   accept_confirm       int default 0 comment '解押是否受理 0--否 1--是',
   accept_confirm_date  datetime comment '解押受理时间',
   bill_attach_url      varchar(255) comment '解押受理小票附件',
   complete_confirm     int default 0 comment '解押业务是否完成 0--否 1--是',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_car_id(car_id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='车辆解押登记信息表';

DROP TABLE IF EXISTS c_data_keep_info;
create table c_data_keep_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   car_id               bigint(21) not null comment '车辆id',
   paper_data_num       int comment '纸质资料份数',
   contract_num         int comment '纸质合同份数',
   is_car_register      int comment '是否存留抵押登记证书 0--否 1--是',
   is_car_key           int comment '是否存有客户备用钥匙 0--否 1--是',
   keep_date            datetime comment '存档时间',
   unkeep_date          datetime comment '归还时间',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_car_id(car_id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='客户资料存留信息表';

DROP TABLE IF EXISTS c_pay_info;
create table c_pay_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) comment '客户id',
   apply_id             bigint(21) comment '借款id',
   contract_no          varchar(32) comment '合同编号',
   cust_name            varchar(32) comment '客户姓名',
   cust_mobile          varchar(16) comment '客户手机号',
   cust_id_no           varchar(32) comment '客户身份证号',
   reception_depart     varchar(32) comment '前台部门',
   reception_manager    varchar(32) comment '前台部门经理',
   trans_source         varchar(64) comment '业务来源',
   total_amount         decimal(10,2) comment '放款总额',
   period_num           int comment '放款期数',
   payed_amount         decimal(10,2) comment '已放款金额',
   not_pay_amount       decimal(10,2) comment '未放款金额',
   pay_status           int comment '放款状态',
   pay_status_desc      varchar(64) comment '放款状态描述',
   reception_amount     decimal(10,2) DEFAULT '0.00' comment '前台手续费',
   is_replace_cost      int default 1 comment '是否代收前台手续费 0-否 1-是',
   is_per_charge        int default 0 comment '是否分期手续费 0--分期 1--全部',
   bank_name            varchar(64) comment '客户开户行',
   bank_card_no         varchar(32) comment '客户放款账号',
   approve_amount       decimal(10,2) comment '审批金额',
   first_buss_id        bigint(21) comment '业务员',
   second_buss_id       bigint(21) comment '业务员2',
   buss_manager_id      bigint(21) comment '业务经理',
   first_finance_id     bigint(21) comment '首次放款财务',
   first_finance_manager_id bigint(21) comment '首次放款财务经理',
   second_finance_id     bigint(21) comment '二次放款财务',
   second_finance_manager_id bigint(21) comment '二次放款财务经理',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   KEY index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='放款总表';

DROP TABLE IF EXISTS c_pay_approve_record;
create table c_pay_approve_record
(
   id                    bigint(21) not null auto_increment comment '主键',
   pay_id                bigint(21) comment '放款总表id',
   operator_id           bigint(21) comment '操作员id',
   operator_name         varchar(25) comment '操作员姓名',
   operator_time         datetime comment '操作时间',
   operator_result       int comment '审批结果 0--未通过 1--通过',
   operator_tip          varchar(255) comment '审批意见',
   create_time           datetime not null comment '创建时间',
   update_time           datetime not null comment '更新时间',
   is_deleted            int(1) default '0' comment '是否删除 0--否 1--是',
   remark                varchar(255) comment '备注，说明',
   primary key (id),
   KEY index_pay_id(pay_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='放款审批记录表';

DROP TABLE IF EXISTS c_pay_detail_info;
create table c_pay_detail_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   pay_id               bigint(21) not null comment '放款总表id',
   pay_num              int comment '放款次数',
   cust_mobile          varchar(16) comment '客户手机号',
   cur_pay_amount       decimal(10,2) comment '本次放款金额',
   bank_name            varchar(64) comment '客户开户行',
   bank_card_no         varchar(32) comment '客户放款账号',
   pay_status           int comment '放款状态 1-创建 4-失败 5-支付处理中 6-支付成功',
   pay_type             int comment '放款类型 0-系统放款 1--手动代付 2--手动冲账',
   paying_num           int comment '放款处理中查询次数',
   serial_no            varchar(32) comment '放款流水号',
   pay_code             varchar(20) DEFAULT NULL COMMENT '支付中心code',
   pay_msg              varchar(200) DEFAULT NULL COMMENT '支付消息',
   pay_channel          varchar(50) DEFAULT NULL COMMENT '支付渠道',
   operator_id          bigint(21) comment '财务id',
   operator_date        datetime comment '本次放款时间',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   KEY index_pay_id(pay_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='放款详情表';

DROP TABLE IF EXISTS c_repayment_info;
create table c_repayment_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) comment '客户id',
   apply_id             bigint(21) comment '借款申请id',
   contract_no          varchar(32) comment '合同编号',
   pay_id               bigint(21) comment '放款ID',
   cust_name            varchar(32) comment '客户姓名',
   cust_mobile          varchar(16) comment '客户手机号',
   cust_id_no           varchar(32) comment '客户身份证号',
   bank_name            varchar(64) comment '客户开户行',
   bank_card_no         varchar(32) comment '客户银行账号',
   loan_amount          decimal(10,2) DEFAULT '0.00' comment '借款金额',
   loan_period          int DEFAULT '0' comment '借款期限',
   year_rate            decimal(10,4) DEFAULT '0.0000' comment '年化利率',
   month_rate           decimal(10,4) DEFAULT '0.0000' comment '月利率',
   pre_service_rate     decimal(10,4) DEFAULT '0.0000' comment '手续费率',
   once_repayment_rate  decimal(10,4) DEFAULT '0.0000' COMMENT '一次性还款违约金费率',
   pre_fee              decimal(10,2) DEFAULT '0.00' comment '前期费用',
   reception_amount     decimal(10,2) DEFAULT '0.00' comment '前台手续费',
   other_fee            decimal(10,2) DEFAULT '0.00' comment '其他费用',
   breach_amount        decimal(10,2) DEFAULT '0.00' comment '一次性提前还款违约金',
   month_lease_total    decimal(10,2) DEFAULT '0.00' comment '租赁合同总计',
   month_service_total  decimal(10,2) DEFAULT '0.00' comment '服务合同总计',
   lease_capital_total  decimal(10,2) DEFAULT '0.00' comment '租赁本金总计',
   lease_interest_total  decimal(10,2) DEFAULT '0.00' comment '租赁利息总计',
   interest_begin_time  datetime comment '计息开始时间',
   interest_end_time    datetime comment '计息结束时间',
   loan_days_num        int DEFAULT '0' comment '总借款天数',
   predict_amount       decimal(10,2) DEFAULT '0.00' comment '应还总金额',
   predict_capital      decimal(10,2) DEFAULT '0.00' comment '应还本金',
   predict_interest     decimal(10,2) DEFAULT '0.00' comment '应还利息',
   predict_service_charge decimal(10,2) DEFAULT '0.00' comment '应还服务费',
   predict_charge       decimal(10,2) DEFAULT '0.00' comment '应还手续费',
   predict_penalty      decimal(10,2) DEFAULT '0.00' comment '应还罚息',
   actual_amount        decimal(10,2) DEFAULT '0.00' comment '实还总金额',
   actual_capital       decimal(10,2) DEFAULT '0.00' comment '实还本金',
   actual_interest      decimal(10,2) DEFAULT '0.00' comment '实还利息',
   actual_service_charge decimal(10,2) DEFAULT '0.00' comment '实还服务费',
   actual_charge        decimal(10,2) DEFAULT '0.00' comment '实还手续费',
   actual_penalty       decimal(10,2) DEFAULT '0.00' comment '实还罚息',
   cur_status           int DEFAULT '1' comment '当前还款状态 1--未开始还款  2--还款中  3--部分还款  10--申请提前还款 20--业务经理审批 30--财务确认 40--财务经理审核  50--已还完',
   cur_status_desc      varchar(128) comment '当前状态描述',
   is_once_early_repayment int DEFAULT '0' comment '是否是提前还款  0--否 1--是',
   overdue_times        int comment '逾期次数',
   current_period_num   int comment '当前还款期号',
   version              int comment '版本',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   KEY index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='还款总表';

DROP TABLE IF EXISTS c_repayment_plan_info;
create table c_repayment_plan_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) comment '客户id',
   cust_name            varchar(32) comment '客户姓名',
   apply_id             bigint(21) comment '借款id',
   repayment_id         bigint(21) comment '还款id',
   period_num           int comment '期数',
   period_status        int comment '当期状态 1--未开始  2--还款中  3--部分还款  4--已逾期  5--已还完',
   period_begin_time    datetime comment '当期开始时间',
   period_end_time      datetime comment '当期结束时间',
   interest_days        int comment '计息天数',
   begin_amount         decimal(10,2) DEFAULT '0.00' comment '期初余额',
   end_amount           decimal(10,2) DEFAULT '0.00' comment '期末余额',
   pay_amount           decimal(10,2) DEFAULT '0.00' comment '月还款',
   begin_amount_lease   decimal(10,2) DEFAULT '0.00' comment '租赁期初余额',
   end_amount_lease     decimal(10,2) DEFAULT '0.00' comment '租赁期末余额',
   capital_lease        decimal(10,2) DEFAULT '0.00' comment '租赁本金',
   interest_lease       decimal(10,2) DEFAULT '0.00' comment '租赁利息',
   lease_total          decimal(10,2) DEFAULT '0.00' comment '租赁合同金额',
   service_total        decimal(10,2) DEFAULT '0.00' comment '服务合同金额',
   predict_amount       decimal(10,2) DEFAULT '0.00' comment '当期应还总金额',
   predict_capital      decimal(10,2) DEFAULT '0.00' comment '当期应还本金',
   predict_interest     decimal(10,2) DEFAULT '0.00' comment '当期应还利息',
   predict_service_charge decimal(10,2) DEFAULT '0.00' comment '当期应还服务费',
   predict_charge       decimal(10,2) DEFAULT '0.00' comment '当期应收手续费',
   predict_penalty      decimal(10,2) DEFAULT '0.00' comment '当期应还罚息',
   actual_amount        decimal(10,2) DEFAULT '0.00' comment '当期实还总金额',
   actual_capital       decimal(10,2) DEFAULT '0.00' comment '当期实还本金',
   actual_interest      decimal(10,2) DEFAULT '0.00' comment '当期实还利息',
   actual_service_charge decimal(10,2) DEFAULT '0.00' comment '当期实还服务费',
   actual_charge        decimal(10,2) DEFAULT '0.00' comment '当期实还手续费',
   actual_penalty       decimal(10,2) DEFAULT '0.00' comment '当期实还罚息',
   is_overdue           int(1) DEFAULT '0' comment '是否逾期 0-否 1-是',
   is_lock              int(1) DEFAULT '0' comment '是否被锁定 0--否 1--是',
   overdue_days         int DEFAULT '0' comment '逾期天数',
   overdue_penalty      decimal(10,2) DEFAULT '0.00' comment '逾期罚息金额',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   KEY index_apply_id(apply_id),
   KEY index_repayment_id(repayment_id),
   KEY index_cust_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='还款计划表';

DROP TABLE IF EXISTS c_repayment_pay_info;
create table c_repayment_pay_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) comment '客户id',
   repayment_id         bigint(21) comment '还款id',
   repayment_plan_id    bigint(21) comment '还款计划id',
   pay_type             int comment '类型 0-自动还款 1--手动代扣 2--对公转账 3--扣取前期费用 4--手工冲账',
   pay_type_desc        varchar(128) comment '类型描述',
   repayment_period_num int comment '还款期数',
   serial_no            varchar(32) comment '流水号',
   amount               decimal(10,2) comment '支付金额',
   binding_mobile       varchar(18) comment '绑定手机号',
   pay_status           int(3) DEFAULT '0' COMMENT '支付状态',
   paying_num           int comment '还款处理中查询次数',
   pay_code             varchar(20) DEFAULT NULL COMMENT '支付中心code',
   pay_msg              varchar(200) DEFAULT NULL COMMENT '支付消息',
   pay_time             datetime DEFAULT NULL COMMENT '支付时间',
   pay_channel          varchar(50) DEFAULT NULL COMMENT '支付渠道',
   pay_bank             varchar(50) DEFAULT NULL COMMENT '支付银行',
   pay_card             varchar(50) DEFAULT NULL COMMENT '支付银行卡',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   KEY index_repayment_id(repayment_id),
   KEY index_repayment_plan_id(repayment_plan_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='还款支付表';

DROP TABLE IF EXISTS c_repayment_change_record;
CREATE TABLE c_repayment_change_record (
  id                  bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  repayment_id        bigint(20) NOT NULL COMMENT '还款信息ID',
  repayment_plan_id   bigint(20) COMMENT '原还款计划',
  change_type         int(3) DEFAULT '0' COMMENT '变更类型 0--逾期  1--一次性提前还款',
  change_desc         varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '变更描述',
  old_overdue_days    int(3)  DEFAULT '0' COMMENT '原逾期天数',
  old_overdue_penalty decimal(16,2)  DEFAULT '0.00' COMMENT '原逾期罚金',
  new_overdue_days    int(3)  DEFAULT '0' COMMENT '现逾期天数',
  new_overdue_penalty decimal(16,2)  DEFAULT '0.00' COMMENT '现逾期罚金',
  once_repayment_capital decimal(16.2) DEFAULT '0.00' COMMENT '一次性提前还款剩余本金',
  once_repayment_charge decimal(16,2) DEFAULT '0.00' COMMENT '一次性提前还款剩余手续费',
  once_repayment_breach decimal(16,2) DEFAULT '0.00' COMMENT '一次性提前还款罚金',
  once_repayment_total  decimal(16,2) DEFAULT '0.00' COMMENT '一次性提前还款总额',
  cur_period_num        int default '0' comment '当期期数',
  cur_period_amount     decimal(16,2) DEFAULT '0.00' comment '当期还款金额',
  old_amount            decimal(10,2) DEFAULT '0.00' comment '原总金额',
  old_interest          decimal(10,2) DEFAULT '0.00' comment '原总利息',
  old_service_charge    decimal(10,2) DEFAULT '0.00' comment '原总服务费',
  old_month_lease_total    decimal(10,2) DEFAULT '0.00' comment '原租赁合同总计',
  old_month_service_total  decimal(10,2) DEFAULT '0.00' comment '原服务合同总计',
  old_lease_capital_total  decimal(10,2) DEFAULT '0.00' comment '原租赁本金总计',
  old_lease_interest_total  decimal(10,2) DEFAULT '0.00' comment '原租赁利息总计',
  new_amount            decimal(10,2) DEFAULT '0.00' comment '现总金额',
  new_interest          decimal(10,2) DEFAULT '0.00' comment '现总利息',
  new_service_charge    decimal(10,2) DEFAULT '0.00' comment '现总服务费',
  new_month_lease_total    decimal(10,2) DEFAULT '0.00' comment '原租赁合同总计',
  new_month_service_total  decimal(10,2) DEFAULT '0.00' comment '原服务合同总计',
  new_lease_capital_total  decimal(10,2) DEFAULT '0.00' comment '原租赁本金总计',
  new_lease_interest_total  decimal(10,2) DEFAULT '0.00' comment '原租赁利息总计',
  create_time         datetime not null comment '创建时间',
  update_time         datetime not null comment '更新时间',
  is_deleted          int(1) default '0' comment '是否删除 0--否 1--是',
  remark              varchar(255) comment '备注，说明',
  PRIMARY KEY (id),
  KEY index_repayment_id(repayment_id)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='还款变更记录表';

DROP TABLE IF EXISTS c_repayment_approve_record;
create table c_repayment_approve_record
(
   id                    bigint(21) not null auto_increment comment '主键',
   repayment_id          bigint(21) comment '还款总表id',
   operator_id           bigint(21) comment '操作员id',
   operator_name         varchar(25) comment '操作员姓名',
   operator_time         datetime comment '操作时间',
   operator_result       int comment '审批结果 0--未通过 1--通过',
   operator_tip          varchar(255) comment '审批意见',
   pre_status            int not null comment '前置状态',
   pre_status_desc       varchar(128) comment '前置状态描述',
   after_status          int not null comment '操作后状态',
   after_status_desc     varchar(128) comment '操作后状态描述',
   create_time           datetime not null comment '创建时间',
   update_time           datetime not null comment '更新时间',
   is_deleted            int(1) default '0' comment '是否删除 0--否 1--是',
   remark                varchar(255) comment '备注，说明',
   primary key (id),
   KEY index_repayment_id(repayment_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='提前还款审批记录表';

DROP TABLE IF EXISTS c_once_early_repayment_record;
create table c_once_early_repayment_record
(
   id                    bigint(21) not null auto_increment comment '主键',
   repayment_id          bigint(21) comment '还款总表id',
   once_repayment_capital decimal(16,2) DEFAULT '0.00' COMMENT '一次性提前还款剩余本金',
   once_repayment_charge decimal(16,2) DEFAULT '0.00' COMMENT '一次性提前还款剩余手续费',
   once_repayment_breach decimal(16,2) DEFAULT '0.00' COMMENT '一次性提前还款罚金',
   cur_period_num        int default '0' comment '当期期数',
   cur_period_amount     decimal(16,2) DEFAULT '0.00' comment '当期还款金额',
   once_repayment_total  decimal(16,2) DEFAULT '0.00' COMMENT '一次性提前还款总额',
   appoint_date          datetime not null comment '约定提前还款时间',
   cur_status           int DEFAULT '1' comment '当前还款状态 1--未开始  2--还款中  3--部分还款  10--申请提前还款 15--内勤主管审核 20--业务经理审批 30--财务确认 40--财务经理审核  50--已还完',
   cur_status_desc      varchar(128) comment '当前状态描述',
   create_time           datetime not null comment '创建时间',
   update_time           datetime not null comment '更新时间',
   is_deleted            int(1) default '0' comment '是否删除 0--否 1--是',
   remark                varchar(255) comment '备注，说明',
   primary key (id),
   KEY index_repayment_id(repayment_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='提前还款记录表';

DROP TABLE IF EXISTS c_pay_statis_info;
create table c_pay_statis_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   statis_date          date comment '统计日期 YYYY-MM-DD',
   pay_total_amount     decimal(16,2) DEFAULT '0.00' comment '放款总计',
   repayment_total_amount  decimal(16,2) DEFAULT '0.00' comment '还款总计',
   total_capital        decimal(16,2) DEFAULT '0.00' comment '还款总本金',
   total_interest       decimal(16,2) DEFAULT '0.00' comment '还款总利息',
   total_service_charge decimal(16,2) DEFAULT '0.00' comment '还款总服务费',
   total_charge         decimal(16,2) DEFAULT '0.00' comment '还款总手续费',
   total_penalty        decimal(16,2) DEFAULT '0.00' comment '还款总罚息',
   total_breach         decimal(16,2) DEFAULT '0.00' comment '还款总违约金',
   total_pre_fee        decimal(16,2) DEFAULT '0.00' comment '还款总前期费用',
   not_repay_amount     decimal(16,2) DEFAULT '0.00' comment '未还总金额',
   not_repay_capital    decimal(16,2) DEFAULT '0.00' comment '未还总本金',
   not_repay_interest   decimal(16,2) DEFAULT '0.00' comment '未还总利息',
   not_repay_service_charge decimal(16,2) DEFAULT '0.00' comment '未还总服务费',
   not_repay_charge     decimal(16,2) DEFAULT '0.00' comment '未还总手续费',
   not_repay_penalty    decimal(16,2) DEFAULT '0.00' comment '未还总罚息',
   cur_pay              decimal(16,2) DEFAULT '0.00' comment '当日放款总计',
   cur_repayment        decimal(16,2) DEFAULT '0.00' comment '当日还款总计',
   cur_pre_fee          decimal(16,2) DEFAULT '0.00' comment '当日前期费用总计',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='财务统计表';

/*车辆详细配置_操作配置 */

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
  `is_delete` int(1) default '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='车辆详细配置_操作配置';

/*车辆详细配置_底盘转向 */

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
  `is_delete` int(1) default '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='车辆详细配置_底盘转向';
/*车辆详细配置_变速箱配置 */
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
  `is_delete` int(1) default '0' COMMENT '是否删除 0--否 1--是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='车辆详细配置_变速箱配置';

/*车辆详细配置_安全装备  */

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
  `is_delete` int(1) default '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='车辆详细配置_安全装备';

/*车辆详细配置_车轮制动*/
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
  `is_delete` int(1) default '0' COMMENT '是否删除 0--否 1--是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='车辆详细配置_车轮制动';

DROP TABLE IF EXISTS `c_car_base_params`;
create table c_car_base_params
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   cust_id              bigint(21) comment '客户id',
   apply_id             bigint(21) comment '借款id',
   car_id               bigint(21) not null comment '车辆id',
   base_config          varchar(2) comment '基本参数配置 1-标配 2-选配 3-无',
   productFactory       varchar(255) comment '厂商',
	 jibie       					varchar(50) comment '级别',
	 engine               varchar(100) comment '发动机',
   bsx                  varchar(50) comment '变速箱',
   ckg                  varchar(50) comment '长宽高',
	 csxs                 varchar(100) comment '车身型式',
   highspeed            varchar(10) comment '最高速度',
   rlxs                 varchar(50) comment '燃料形式',
   gxbzhyh              varchar(10) comment '工信部综合油耗',
   outcolor       varchar(255) comment '外部颜色',
   innercolor       varchar(255) comment '内饰颜色',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   KEY INX_CAR_ID(car_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '车辆详细配置_基本参数表';

DROP TABLE IF EXISTS c_car_body_config;
create table c_car_body_config
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   cust_id              bigint(21) comment '客户id',
   apply_id             bigint(21) comment '借款id',
   car_id               bigint(21) not null comment '车辆id',
   carbody_config          varchar(2) comment '车身配置 1-标配 2-选配 3-无',
   zj                   varchar(10) comment '轴距(mm)',
	 qlj       				 varchar(10) comment '前轮距(mm)',
	 hlj               varchar(00) comment '后轮距(mm)',
   minldjx           varchar(10) comment '最小离地间隙',
   zczl              varchar(10) comment '整车质量(kg)',
	 csjg              varchar(100) comment '车身结构',
   yxrl              varchar(10) comment '邮箱容量(L)',
   xlxrl             varchar(50) comment '行李箱容量(L)',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   KEY INX_CAR_ID(car_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '车辆详细配置_车身配置';

DROP TABLE IF EXISTS c_car_engine_config;
create table c_car_engine_config
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   cust_id              bigint(21) comment '客户id',
   apply_id             bigint(21) comment '借款id',
   car_id               bigint(21) not null comment '车辆id',
   carengine_config          varchar(15) comment '发动机配置 1-标配 2-选配 3-无',
   fdjxh                     varchar(10) comment '发动机型号',
	 pailiang       				   varchar(10) comment '排量(ml)',
	 jqxs               varchar(100) comment '进气形式',
   qgplxs             varchar(100) comment '气缸排列形式',
   qggs               varchar(5) comment '气缸个数',
	 mgqms               varchar(5) comment '每缸气门数',
   pqjg                varchar(50) comment '配气机构',
   gangjing             varchar(50) comment '缸径(mm)',
	 maxml               varchar(10) comment '最大马力',
	 maxgl               varchar(10) comment '最大功率',
	 maxglzs             varchar(10) comment '最大功率转速',
	 maxnz               varchar(50) comment '最大扭转',
	 maxnzzs             varchar(50) comment '最大扭转转速',
	 fdjtyjs             varchar(100) comment '发动机特有技术',
	 rlxs             varchar(50) comment '燃料形式',
	 rybh             varchar(50) comment '燃油标号',
	 gyfs             varchar(50) comment '供油方式',
	 ggcl             varchar(50) comment '缸盖材料',
	 gtcl             varchar(50) comment '缸体材料',
	 hbcl             varchar(50) comment '环保材料',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   KEY INX_CAR_ID(car_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '车辆详细配置_发动机配置';

INSERT INTO `c_process_node` VALUES
('10001', '2', '新增客户', '1000', '新增客户', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10002', '6', '面审', '2000', '面审', '0', '0', null, '/cust/showMsCommon.html', '/process/msSubmit', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10003', '3', '内勤资料录入', '9999', '内勤资料录入', '0', '1', 'nqlr_status', '/cust/showNq.html', '/process/nqDataSaveSubmit', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10004', '4', '验车', '9999', '验车', '0', '1', 'yc_status', '/cust/yancheshi.html', '/process/ycSubmit', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10005', '5', '面审主管审核', '3000', '面审主管审核', '0', '0', null, '/cust/mianshenzhuguan.html', '/process/msZgSubmit', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10006', '5', '面审主管拒绝', '3100', '面审主管拒绝', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10007', '5', '面审主管驳回', '3200', '面审主管驳回', '6', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10008', null, '机器风控', '4000', '机器风控', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10009', '7', '总部终审', '5000', '总部终审', '0', '0', null, '/cust/showZsCommon.html', '/process/zsSubmit', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10010', '7', '总部终审拒绝', '5100', '总部终审拒绝', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10011', '7', '总部终审驳回', '5200', '总部终审驳回', '5', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10012', '3', '签订合同', '6000', '签订合同', '0', '0', null, '/cust/signContract.html', '/process/signContract', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10013', '3', 'GPS安装申请', '2', 'GPS安装申请', '0', '1', 'gps_install_status', '/cust/gpsInstallApply.html', '/process/gpsInstallApply', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10014', '4', 'GPS安装', '3', 'GPS安装', '0', '1', 'gps_install_status', '/cust/gpsinstallcomplete.html', '/process/gpsInstallComplete', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10015', '5', 'GPS安装确认', '9999', 'GPS安装确认', '0', '1', 'gps_install_status', '/cust/gpsInstallConfirm.html', '/process/gpsInstallConfirm', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10016', '3', '抵押申请', '7100', '抵押申请', '0', '0', null, '/cust/mortgageCarApply.html', '/process/mortgageCarApply', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10017', '8', '确认抵押申请材料', '7200', '确认抵押申请材料', '0', '0', null, '/cust/mortgageCarConfirmApply.html', '/process/mortgageCarConfirmApply', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10018', '8', '驳回抵押申请材料', '7250', '驳回抵押申请材料', '3', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10019', '8', '接收抵押资料确认', '7300', '接收抵押资料确认', '0', '0', null, '/cust/receiveMortgageCar.html', '/process/mortgageCarConfirmPaper', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10020', '8', '抵押已受理', '7400', '抵押已受理', '0', '0', null, '/cust/mortgageCarBussHanding.html', '/process/mortgageCarBussHanding', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10021', '8', '抵押办理成功', '7500', '抵押办理成功', '0', '0', null, '/cust/mortgageCarBussFinish.html', '/process/mortgageCarBussFinish', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10022', '8', '抵押办理失败', '7600', '抵押办理失败', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10023', '3', '资料存留', '8000', '资料存留', '0', '0', null, '/cust/dataKeep.html', '/process/dataKeep', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10024', '3', '请款申请', '9000', '请款申请', '0', '0', '', '/cust/payApply.html', '/process/payApply', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10025', '2', '请款内勤主管审核', '9010', '请款内勤主管审核', '0', '0', null, '/cust/payNqzgAudit.html', '/process/payNqzgAudit', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10026', '2', '请款内勤主管审核失败', '9015', '请款内勤主管审核失败', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10027', '9', '请款业务经理审核', '9020', '请款业务经理审核', '0', '0', null, '/cust/payBussAudit.html', '/process/payBussAudit', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10028', '9', '请款业务经理审核失败', '9025', '请款业务经理审核失败', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10029', '11', '请款财务首次审核', '9030', '请款财务首次审核', '0', '0', null, '/cust/payFinFirstAudit.html', '/process/payFinFirstAudit', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10030', '11', '请款财务首次审核失败', '9035', '请款财务首次审核失败', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10031', '10', '请款财务经理首次审核', '9040', '请款财务经理首次审核', '0', '0', null, '/cust/payFinBussFirstAudit.html', '/process/payFinBussFirstAudit', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10032', '10', '请款财务经理首次审核失败', '9045', '请款财务经理首次审核失败', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10033', null, '首次放款', '9050', '首次放款成功', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10034', null, '首次放款失败', '9051', '首次放款失败', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10035', null, '首次放款处理中', '9052', '首次放款处理中', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10036', null, '扣取前期费用', '9060', '扣取前期费用成功', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10037', null, '扣取前期费用失败', '9061', '扣取前期费用失败', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10038', null, '扣取前期费用处理中', '9062', '扣取前期费用处理中', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10039', null, '扣取前台手续费', '9070', '扣取前台手续费成功', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10040', null, '扣取前台手续费失败', '9071', '扣取前台手续费失败', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10041', null, '扣取前台手续费处理中', '9072', '扣取前台手续费处理中', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10042', '12', '前台财务确认', '9075', '前台财务确认', '0', '0', null, '/cust/payReceptionConfirm.html', '/process/payReceptionConfirm', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10043', '11', '请款财务二次审核', '9080', '请款财务二次审核成功', '0', '0', null, '/cust/payFinSecondAudit.html', '/process/payFinSecondAudit', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10044', '11', '请款财务二次审核失败', '9085', '请款财务二次审核失败', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10045', '10', '请款财务经理二次审核', '9090', '请款财务经理二次审核成功（放款完成）', '0', '0', null, '/cust/payFinBussSecondAudit.html', '/process/payFinBussSecondAudit', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10046', '10', '请款财务经理二次审核失败', '9095', '请款财务经理二次审核失败', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10047', null, '二次放款', '9100', '二次放款成功', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10048', null, '二次放款失败', '9101', '二次放款失败', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10049', null, '二次放款处理中', '9102', '二次放款处理中', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10050', '3', '放款完成', '9200', '放款完成', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10051', '3', '提前还款申请', '9300', '提前还款申请', '0', '0', null, '/cust/submitCommon.html', '/process/onceEarlyApply', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10052', '2', '提前还款内勤主管审核', '9310', '提前还款内勤主管审核成功', '0', '0', null, '/cust/onceEarlyNqzgAudit.html', '/process/onceEarlyNqzgAudit', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10053', '2', '提前还款内勤主管审核失败', '9315', '提前还款内勤主管审核失败', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10054', '9', '提前还款业务经理审核', '9320', '提前还款业务经理审核成功', '0', '0', null, '/cust/onceEarlyBussApprove.html', '/process/onceEarlyBussApprove', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10055', '9', '提前还款业务经理审核失败', '9325', '提前划款业务经理审核失败', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10056', '11', '提前还款财务确认', '9330', '提前还款财务确认', '0', '0', null, '/cust/submitCommon.html', '/process/onceEarlyFinConfirm', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10057', '10', '提前还款财务经理确认', '9340', '提前还款财务经理确认', '0', '0', null, '/cust/submitCommon.html', '/process/onceEarlyFinBussConfirm', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10058', null, '还款完成', '10000', '还款完成', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10059', '3', '解押申请', '11000', '解押申请', '0', '0', null, '/cust/detentionCarApply.html', '/process/detentionCarApply', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10060', '11', '结清确认', '11010', '结清确认', '0', '0', null, '/cust/detentionFinSettleConfirm.html', '/process/detentionFinSettleConfirm', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10061', '8', '确认解押申请材料', '11020', '确认解押申请材料', '0', '0', null, '/cust/detentionConfirmApply.html', '/process/detentionConfirmApply', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10062', '8', '驳回解押申请材料', '11030', '驳回解押申请材料', '3', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10063', '8', '接收解押材料确认', '11040', '接收解押材料确认', '0', '0', null, '/cust/detentionConfirmPaper.html', '/process/detentionConfirmPaper', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10064', '8', '解押已受理', '11050', '解押已受理', '0', '0', null, '/cust/detentionBussHanding.html', '/process/detentionBussHanding', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10065', '8', '解押办理成功', '11060', '解押办理成功', '0', '0', null, '/cust/detetionBussFinish.html', '/process/detetionBussFinish', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10066', '3', '拆卸GPS申请', '2', '拆卸GPS申请', '0', '1', 'gps_uninstall_status', '/cust/gpsUninstallApply.html', '/process/gpsUninstallApply', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10067', '4', '拆卸GPS', '3', '拆卸GPS', '0', '1', 'gps_uninstall_status', '/cust/gpsUnistallFinish.html', '/process/gpsUnistallFinish', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10068', '5', '拆卸GPS确认', '9999', '拆卸GPS确认', '0', '1', 'gps_uninstall_status', '/cust/gpsUninstallConfirm.html', '/process/gpsUninstallConfirm', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10069', '3', '资料移交', '12000', '资料移交(债务完结)', '0', '0', null, '/cust/dataTranfser.html', '/process/dataTranfser', '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10070', null, '债务完结', '13000', '债务完结', '0', '0', null, '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10071', null, '内勤资料录入准备', '1', '内勤资料录入准备', '0', '1', 'nqlr_status', '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10072', null, '验车准备', '1', '验车准备', '0', '1', 'yc_status', '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10073', null, 'GPS安装申请准备', '1', 'GPS安装申请准备', '0', '1', 'gps_install_status', '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0'),
('10074', null, '拆卸GPS申请准备', '1', '拆卸GPS申请准备', '0', '1', 'gps_uninstall_status', '/cust/submitCommon.html', null, '2018-06-20 12:00:00', '2018-06-20 12:00:00', null, '0');



INSERT INTO `c_process_engine` VALUES
('10001', '10001', '10002', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '客户新增-面审', '0'),
('10002', '10001', '10003', '5', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '客户新增-内勤资料录入', '0'),
('10003', '10001', '10004', '5', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '客户新增-验车', '0'),
('10004', '10002', '10005', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '面审-资料提交', '0'),
('10005', '10002', '10003', '3', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '面审前置', '0'),
('10006', '10002', '10004', '3', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '面审前置', '0'),
('10007', '10005', '10008', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '资料提交-机器风控', '0'),
('10008', '10002', '10006', '1', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '面审主管拒绝', '0'),
('10009', '10002', '10007', '2', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '面审主管驳回', '0'),
('10010', '10008', '10009', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '机器风控-总部终审', '0'),
('10011', '10009', '10012', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '总部终审-签合同', '0'),
('10012', '10012', '10013', '5', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '签合同-发起GPS安装', '0'),
('10013', '10008', '10010', '1', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '总部终审拒绝', '0'),
('10014', '10008', '10011', '2', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '总部终审驳回', '0'),
('10015', '10012', '10016', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '签合同-抵押申请', '0'),
('10016', '10016', '10017', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '抵押申请-确认抵押申请材料', '0'),
('10017', '10016', '10018', '2', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '确认抵押申请驳回', '0'),
('10018', '10017', '10019', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '确认抵押材料-接收抵押材料', '0'),
('10019', '10019', '10020', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '接收抵押材料-抵押已受理', '0'),
('10020', '10020', '10021', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '抵押已受理-抵押完成', '0'),
('10021', '10020', '10022', '1', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '抵押办理失败', '0'),
('10022', '10021', '10023', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '抵押完成-资料存留', '0'),
('10023', '10023', '10024', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '资料存留-请款申请', '0'),
('10024', '10013', '10014', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '发起GPS安装-安装GPS完成', '0'),
('10025', '10014', '10015', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '安装GPS完成-安装GPS确认', '0'),
('10026', '10024', '10015', '3', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '请款申请前置', '0'),
('10027', '10024', '10025', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '放款申请-内勤主管审核', '0'),
('10028', '10024', '10026', '1', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '内勤主管审核失败', '0'),
('10029', '10025', '10027', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '内勤主管审核-业务经理审核', '0'),
('10030', '10027', '10029', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '业务经理审核-财务首次审核', '0'),
('10031', '10025', '10028', '1', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '业务经理审核失败', '0'),
('10032', '10029', '10031', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '财务首次审核-财务经理首次审核', '0'),
('10033', '10027', '10030', '1', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '财务首次审核失败', '0'),
('10034', '10031', '10042', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '财务经理首次审核-首次放款-前台财务确认', '0'),
('10035', '10029', '10032', '1', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '财务经理首次审核失败', '0'),
('10036', '10033', '10042', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '首次放款-前台财务确认', '0'),
('10037', '10031', '10034', '1', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '首次放款失败', '0'),
('10038', '10031', '10035', '4', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '首次放款处理中', '0'),
('10039', '10036', '10039', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '扣取前期费用-扣取前台手续费', '0'),
('10040', '10033', '10037', '1', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '扣取前期费用失败', '0'),
('10041', '10033', '10038', '4', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '扣取前期费用处理中', '0'),
('10042', '10039', '10042', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '扣取前台手续费-前台财务确认', '0'),
('10043', '10036', '10040', '1', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '扣取前台手续费失败', '0'),
('10044', '10036', '10041', '4', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '扣取前台手续费处理中', '0'),
('10045', '10042', '10043', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '前台财务确认-财务二次审批', '0'),
('10046', '10042', '10044', '1', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '财务二次审核失败', '0'),
('10047', '10043', '10045', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '财务二次审核-财务经理二次审核', '0'),
('10048', '10043', '10046', '1', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '财务经理二次审核失败', '0'),
('10049', '10045', '10050', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '财务二次审核-放款完成', '0'),
('10050', '10047', '10050', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '二次放款-放款完成', '0'),
('10051', '10045', '10048', '1', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '二次放款失败', '0'),
('10052', '10045', '10049', '4', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '二次放款处理中', '0'),
('10053', '10051', '10050', '3', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '提前还款申请前置', '0'),
('10054', '10051', '10052', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '提前还款申请-提前还款内勤主管审核', '0'),
('10055', '10051', '10053', '1', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '提前还款内勤主管审批失败', '0'),
('10056', '10052', '10054', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '提前还款内勤主管审批-提前还款业务经理审批', '0'),
('10057', '10052', '10055', '1', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '提前还款业务经理审批失败', '0'),
('10058', '10054', '10056', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '提前还款业务经理审批-提前还款财务确认', '0'),
('10059', '10056', '10057', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '提前还款财务确认-提前还款财务经理确认', '0'),
('10060', '10058', '10050', '3', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '还款完成前置', '0'),
('10061', '10058', '10059', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '还款完成-解押申请', '0'),
('10062', '10059', '10066', '5', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '解押申请-拆卸GPS申请', '0'),
('10063', '10059', '10060', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '解押申请-结清证明', '0'),
('10064', '10060', '10061', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '结清证明-确认解押申请材料', '0'),
('10065', '10060', '10062', '2', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '确认解押申请驳回', '0'),
('10066', '10061', '10063', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '确认解押材料申请-接受解押材料', '0'),
('10067', '10063', '10064', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '接收解押材料确认-解押已受理', '0'),
('10068', '10064', '10065', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '解押已受理-解押完成', '0'),
('10069', '10066', '10067', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '拆卸GPS申请-拆卸GPS完成', '0'),
('10070', '10067', '10068', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '拆卸GPS完成-拆卸GPS确认', '0'),
('10071', '10065', '10069', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '解押完成-资料移交', '0'),
('10072', '10069', '10068', '3', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '资料移交前置', '0'),
('10073', '10069', '10070', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '资料移交-债务完结', '0'),
('10074', '10007', '10002', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '面审主管驳回-面审', '0'),
('10075', '10011', '10005', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '终审驳回-面审主管资料审核', '0'),
('10076', '10071', '10003', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '内勤录入资料准备', '0'),
('10077', '10072', '10004', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '验车准备', '0'),
('10078', '10073', '10013', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', 'GPS安装申请准备', '0'),
('10079', '10074', '10066', '0', '2018-06-20 12:00:00', '2018-06-20 12:00:00', 'GPS拆卸申请准备', '0'),
('10080', '10011', '10007', '2', '2018-06-20 12:00:00', '2018-06-20 12:00:00', '总部终审驳回-面审主管驳回', '0');

