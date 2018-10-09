alter table c_cust_info add proof_Of_Residence VARCHAR (255)/*c_cust_info表增加 居住证明材料图片地址(proof_Of_Residence) 字段,2018-09-25*/
alter table c_cust_company_info add gongshang_enterprise_photo VARCHAR (255)/*c_cust_company_info 工商信息查询（企业）附件地址(c_cust_company_info) 字段,2018-09-25*/
alter table c_marry_info add spouse_phone VARCHAR (32)/*c_marry_info表增加 配偶手机号(spouse_phone) 字段,2018-09-26*/
alter table c_car_photo_bigclass_info add car_id bigint (21);/*c_car_photo_bigclass_info表增加 车辆外键ID(car_id) 字段,2018-09-26*/
alter table c_car_peccancy_info add violationAttachmentPhotol VARCHAR (255)/*c_car_peccancy_info表增加 违章附件1(violationAttachmentPhotol) 字段,2018-09-26*/
alter table c_car_peccancy_info add violationAttachmentPhotol2 VARCHAR (255)/*c_car_peccancy_info表增加 违章附件2(violationAttachmentPhotol2) 字段,2018-09-26*/
alter table c_contract_info add contract_xcqy_url VARCHAR (255)/*c_contract_info表增加 现场签约照片(contract_xcqy_url) 字段,2018-09-26*/
alter table c_contract_info add contract_dyysl_url VARCHAR (255)/*c_contract_info表增加 抵押已受理照片(contract_dyysl_url) 字段,2018-09-26*/
alter table c_contract_info add contract_htfj_url VARCHAR (255)/*c_contract_info表增加 合同附件(contract_htfj_url) 字段,2018-09-26*/

