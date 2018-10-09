
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

DROP TABLE IF EXISTS c_apply_info;
create table c_apply_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   cust_id              bigint(21) not null comment '客户ID',
   channel_id           bigint(21) not null comment '客户渠道ID',
   product_type         int default 0 comment '产品类型 0--抵押 1--质押',
   apply_amount         decimal(10,2) comment '申请额度 单位(元)',
   apply_period         int default 36 comment '申请期限  12/24/36',
   repayment_type       int default 0 comment '还款方式 0--等额本息 1--先息后本',
   loan_usage           int comment '还款用途 1--教育支出 2--医疗 4--生意周转 8--装修 16--其他（可多选）',
   loan_usage_other     varchar(128) comment '还款用途其他情况说明',
   partner_know         int comment '0--未婚 1--离异 2--丧偶 3--已婚不知晓 4--已婚知晓不能签字 5--已婚知晓可签字',
   nq_id                bigint(21) comment '内勤id',
   nqzg_id              bigint(21) comment '内勤主管ID',
   ms_id                bigint(21) comment '面审id',
   mszg_id              bigint(21) comment '面审主管id',
   ycs_id               bigint(21) comment '验车师id',
   dyzy_id              bigint(21) comment '抵押专员id',
   ywjl_id              bigint(21) comment '业务经理id',
   zs_id                bigint(21) comment '终审ID',
   cw_id                bigint(21) comment '财务id',
   cwjl_id              bigint(21) comment '财务经理id',
   status               int comment '主流程状态',
   status_desc          varchar(128) comment '主流程状态描述',
   nqlr_status          int default 0 comment '内勤资料录入状态 0--未提交 1--已提交',
   yc_status            int default 0 comment '验车师验车状态 0--未提交 1--已提交',
   gps_install_status   int default 0 comment '安装GPS流程状态  0--未开始 1--发起安装申请 2--安装GPS成功 3--确认安装成功',
   gps_uninstall_status int default 0 comment '拆卸GPS流程状态 0--未开始 1--发起拆卸申请 2--拆卸GPS成功',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   key INX_CUST_ID(cust_id),
   KEY INX_CHANNEL_ID(channel_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '订单表';

DROP TABLE IF EXISTS c_main_approve_record;
create table c_main_approve_record
(
   id                    bigint(21) not null auto_increment comment '主键',
   apply_id              bigint(21) comment '订单id',
   operator_id           bigint(21) comment '操作员id',
   operator_name         varchar(25) comment '操作员姓名',
   operator_time         datetime comment '操作时间',
   pre_status            int not null comment '前置状态',
   pre_status_desc       varchar(128) comment '前置状态描述',
   after_status          int not null comment '操作后状态',
   after_status_desc     varchar(128) comment '操作后状态描述',
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
   mobile               varchar(32) not null comment '手机号',
   sex                  int(1) comment '性别 0-女 1-男',
   nation               varchar(20) comment '民族',
   birthday             varchar(18) comment '出生年月日 YYYYMMDD',
   cert_id              varchar(32) not null comment '身份证号码',
   validate_begin       datetime comment '身份证有效期起始日期',
   validate_end         datetime comment '身份证有效期结束日期',
   sign_org             varchar(128) comment '签发机关',
   education            int comment '教育程度 0--文盲 1--小学 2--初中 3--高中 4--专科 5--本科 6--硕士 7--博士',
   id_front_photo_url   varchar(255) default null comment '身份证正面图片地址',
   id_back_photo_url    varchar(255) default null comment '身份证背面图片地址',
   child_num            int comment '子女人数',
   child_adult          int comment '子女情况 1--无子女 2--有子女未成年 4--有子女已成年（可多选）',
   live_address         int comment '居住地址',
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
   KEY INX_book_id(book_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '客户户口本关联关系信息表';

DROP TABLE IF EXISTS c_marry_info;
create table c_marry_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   cust_id              bigint(21) not null comment '客户ID',
   marry_status         int not null default 0 comment '婚姻状况 0--已婚 1--未婚 2--再婚 3--离异 4--丧偶',
   marry_date           datetime comment '婚姻登记日期（如果是已婚，再婚，丧偶）',
   spouse_name          varchar(64) comment '配偶姓名（如果是已婚，再婚，丧偶）',
   spouse_sex           int(1) comment '配偶性别 0-女 1-男（如果是已婚，再婚，丧偶）',
   spouse_cert_id       varchar(32) not null comment '配偶身份证号码（如果是已婚，再婚，丧偶）',
   validate_begin       datetime comment '配偶身份证有效期起始日期',
   validate_end         datetime comment '配偶身份证有效期结束日期',
   sign_org             varchar(128) comment '配偶签发机关',
   id_front_photo_url   varchar(255) default null comment '配偶身份证正面图片地址',
   id_back_photo_url    varchar(255) default null comment '配偶身份证背面图片地址',
   marry_photo_url      varchar(255) default null comment '结婚证照片地址',
   divorce_date         datetime comment '离婚登记日期（如果是离异）',
   divorce_name         varchar(64) default null comment '原配偶名称（如果是离异）',
   divorce_sex          int(1) comment '原配偶性别 0-女 1-男（如果是离异）',
   divorce_cert_id      varchar(32) not null comment '原配偶身份证号码（如果是离异）',
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
   month_income         decimal(3,1) comment '月均收入（单位：万元）',
   spouse_income_type   int comment '配偶收入来源 1--自雇有执照 2--自雇无执照 4--受薪 8--待业 16--自由职业者',
   spouse_company_name  varchar(255) comment '配偶单位名称 如果收入来源是 1，2，4的话',
   spouse_company_type  int comment '配偶单位性质 0--国有企业 1--国有控股企业 2--外资企业 3--合资企业 4--私营业务 5--事业单位/政府',
   spouse_company_address varchar(255) comment '配偶单位地址',
   spouse_company_tel   varchar(16) comment '配偶单位电话',
   spouse_department    varchar(32) comment '配偶部门',
   spouse_job           varchar(32) comment '配偶职务',
   spouse_work_age      decimal(3,1) comment '配偶入职年限（单位：年）',
   spouse_month_income  decimal(3,1) comment '配偶月均收入（单位：万元）',
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
   get_type             int(1) comment '车辆获得方式 0-自购  2-赠与',
   product_date         datetime comment '出厂日期 YYYYMMDD',
   first_lic_date       datetime comment '首次上牌日期 YYYYMMDD',
   current_lic_date     datetime comment '本次上牌日期 YYYYMMDD',
   register_photo_url1  varchar(255) comment '车辆登记证书1-2页照片地址',
   register_photo_url2  varchar(255) comment '车辆登记证书3-4页照片地址',
   register_photo_url3  varchar(255) comment '车辆登记证书5-6页照片地址',
   register_photo_url4  varchar(255) comment '车辆登记证书7-8页照片地址',
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

DROP TABLE IF EXISTS c_id_auth_info;
create table c_id_auth_info
(
   id                   bigint(21) not null auto_increment comment '主键，自增',
   cust_id              bigint(21) not null comment '客户id',
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
   key INX_IDN(id_number)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '客户身份证信息表';

DROP TABLE IF EXISTS c_liveness_auth_info;
create table c_liveness_auth_info
(
   id                   bigint(21) not null auto_increment comment '主键 自增',
   cust_id              bigint(21) not null comment '客户id 外键',
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
   key INX_UID(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '用户活体数据认证信息表';

DROP TABLE IF EXISTS c_judicial_auth_info;
create table c_judicial_auth_info
(
   id                   bigint(21) not null auto_increment comment '自增主键',
   user_id              bigint(21) not null comment '用户id',
   court_personal       int comment '全国法院被执行人查询（个人） 0--正常 1--异常',
   court_personal_photo varchar(255) comment '全国法院被执行人查询（个人）附件地址',
   zhixing_personal     int comment '中国执行信息公开网查询（个人） 0--正常 1--异常',
   zhixing_personal_photo varchar(255) comment '中国执行信息公开网查询（个人）附件地址',
   risk_personal        int comment '风险信息网查询（个人） 0--正常 1--异常',
   risk_personal_photo  varchar(255) comment '风险信息网查询（个人） 附件地址',
   warn_personal        int comment '风险预警网查询（个人） 0--正常 1--异常',
   warn_personal_photo  varchar(255) comment '风险预警网查询（个人） 附件地址',
   personal_remark      varchar(512) comment '个人备注',
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
   remark               varchar(255) comment '备注，说明',
   is_deleted           int(1) comment '是否删除 0-否 1-是',
   primary key (id),
   KEY INX_USER_ID(user_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 comment '司法认证表';

DROP TABLE IF EXISTS c_telecom_basic_info;
create table c_telecom_basic_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) comment '客户id',
   apply_id             bigint(21) comment '借款id',
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
   collection_date      int comment '征信报告采集时间',
   house_loan_num       int comment '个人住房贷款笔数 (信用提示模块)',
   house_buss_loan_num  int comment '个人商用房贷款笔数 (信用提示模块)',
   other_loan_num       int comment '其他贷款笔数 (信用提示模块)',
   first_loan_start_date datetime comment '首笔贷款发放月份 YYYYMM (信用提示模块)',
   credit_card_num      int comment '贷记卡账户数 (信用提示模块)',
   first_credit_card_start_date datetime comment '首张借记卡发卡月份 YYYYMM (信用提示模块)',
   semi_credit_card_num int comment '准贷记卡账户数 (信用提示模块)',
   first_semi_credit_card_start_date datetime comment '首张准贷记卡发卡月份 YYYYMM (信用提示模块)',
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
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
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
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
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
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
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
   query_date           datetime comment '查询时间',
   query_org            varchar(64) comment '查询机构',
   query_reason         int comment '查询原因 0--贷后管理 1--信用卡审批 2--保前查询 3--贷款审批 4--担保资格查询',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
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
   query_date           datetime comment '查询时间',
   query_org            varchar(64) comment '查询机构',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='客户央行征信个人查询记录表';

DROP TABLE IF EXISTS c_card_price_info;
create table c_card_price_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   che300_price         decimal(10,2) comment '车300评估价',
   che300_attach_url    varchar(255) comment '车300评估价附件地址',
   jingzhengu_price     decimal(10,2) comment '精真估评估价',
   jingzhengu_attach_url varchar(255) comment '精真估评估价附件地址',
   nake_price           decimal(10,2) comment '裸车价',
   depreciation_base    int comment '折旧基数 0--6‰(千分之6) 每月包括按揭房、抵押房、全款房、寿险保单、公积金  1--8‰（千分之8) 每月包括流水结息、他行车贷、打卡工资；',
   depreciation_ratio   int comment '折旧系数 (车龄月数 申请年月日减首次上户年月日 不足1月的按1月算）',
   credit_ratio         decimal(10,4) comment '授信成数',
   tsingnuo_price       decimal(10,2) comment '氢诺拟可贷金额（=裸车价*折旧基数*折旧系数*授信成数)',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
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
   attach_url           varchar(255) comment '附件地址',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
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
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_apply_id(apply_id),
   KEY index_user_id(cust_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='面审最终意见表';

DROP TABLE IF EXISTS c_final_judgment_info;
create table c_final_judgment_info
(
   id                   bigint(21) not null auto_increment comment '主键',
   cust_id              bigint(21) not null comment '客户id',
   apply_id             bigint(21) comment '借款id',
   judgement_result     int comment '终审意见 0--通过 1--拒绝 2--补充资料',
   rejection_reason     varchar(1000) comment '拒绝原因',
   loan_amount          decimal(10,2) comment '通过的金额',
   loan_period          int comment '通过的期数',
   audit_time           datetime comment '审核时间',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
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
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
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
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
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
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
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
   is_wiredless         int comment '是否无线 0--否 1--是',
   gps_wired_no         varchar(255) comment 'GPS串码',
   gps_photo_url        varchar(255) comment 'GPS安装位置图片地址',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   is_deleted           int(1) default '0' comment '是否删除 0--否 1--是',
   remark               varchar(255) comment '备注，说明',
   primary key (id),
   key index_car_id(car_id)
)ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='车辆GPS详情信息表';

DROP TABLE IF EXISTS c_car_mortgage_info;
create table c_car_mortgage_info
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
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
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
   cur_status           int DEFAULT '1' comment '当前还款状态 1--未开始  2--还款中  3--部分还款  10--申请提前还款 20--业务经理审批 30--财务确认 40--财务经理审核  50--已还完',
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
   cur_status           int DEFAULT '1' comment '当前还款状态 1--未开始  2--还款中  3--部分还款  10--申请提前还款 20--业务经理审批 30--财务确认 40--财务经理审核  50--已还完',
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


