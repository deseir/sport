package com.moerlong.carloan.modular.loan.bussiness.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.common.persistence.dao.DeptMapper;
import com.moerlong.carloan.common.persistence.dao.UserMapper;
import com.moerlong.carloan.common.persistence.model.Dept;
import com.moerlong.carloan.common.persistence.model.User;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.car.entity.CarInsureDetailInfo;
import com.moerlong.carloan.modular.car.entity.CarMortgageInfo;
import com.moerlong.carloan.modular.car.entity.CarTransferInfo;
import com.moerlong.carloan.modular.car.service.CarInfoService;
import com.moerlong.carloan.modular.cust.entity.*;
import com.moerlong.carloan.modular.cust.service.ContractInfoService;
import com.moerlong.carloan.modular.cust.service.CustomerInfoService;
import com.moerlong.carloan.modular.cust.service.GetDataService;
import com.moerlong.carloan.modular.loan.bussiness.ProcessBussiness;
import com.moerlong.carloan.modular.loan.dao.ApplyOperatorDao;
import com.moerlong.carloan.modular.loan.dao.ProcessNodeDao;
import com.moerlong.carloan.modular.loan.entity.*;
import com.moerlong.carloan.modular.loan.entity.enums.ProcessType;
import com.moerlong.carloan.modular.loan.entity.vo.SendMsgVo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import com.moerlong.carloan.modular.loan.service.BankcardInfoService;
import com.moerlong.carloan.modular.loan.service.FinalJudgementInfoService;
import com.moerlong.carloan.modular.loan.service.MainApproveRecordService;
import com.moerlong.carloan.modular.payMgr.entity.PayDetailInfo;
import com.moerlong.carloan.modular.payMgr.entity.PayInfo;
import com.moerlong.carloan.modular.payMgr.entity.enums.PayApproveStatus;
import com.moerlong.carloan.modular.payMgr.entity.enums.PayType;
import com.moerlong.carloan.modular.payMgr.service.PayDetailInfoService;
import com.moerlong.carloan.modular.payMgr.service.PayInfoService;
import com.moerlong.carloan.modular.paybackMgr.dao.RepaymentPlanInfoDao;
import com.moerlong.carloan.modular.paybackMgr.entity.OnceEarlyRepaymentRecord;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentChangeRecord;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPayInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPlanInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.PayStatus;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.RepaymentApproveStatus;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.RepaymentChangeType;
import com.moerlong.carloan.modular.paybackMgr.service.IOrderSequenceService;
import com.moerlong.carloan.modular.paybackMgr.service.OnceEarlyRepaymentRecordService;
import com.moerlong.carloan.modular.paybackMgr.service.PayCenterService;
import com.moerlong.carloan.modular.paybackMgr.service.RepaymentChangeRecordService;
import com.moerlong.carloan.modular.paybackMgr.service.RepaymentInfoService;
import com.moerlong.carloan.modular.paybackMgr.txservice.RepaymentTxService;
import com.moerlong.carloan.modular.system.dao.UserMgrDao;
import com.moerlong.carloan.modular.payMgr.service.SMSService;
import com.moerlong.carloan.util.ParamConstants;
import com.moerlong.carloan.modular.paybackMgr.service.RepaymentPayInfoService;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.RepaymentType;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class ProcessBussinessImpl implements ProcessBussiness{
    private final Logger log = LoggerFactory.getLogger(ProcessBussinessImpl.class);

    @Resource
    private ApplyInfoService applyInfoService;
    @Resource
    private ProcessNodeDao processNodeDao;
    @Resource
    private ApplyOperatorDao applyOperatorDao;
    @Resource
    private MainApproveRecordService mainApproveRecordService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private ContractInfoService contractInfoService;
    @Resource
    private BankcardInfoService bankcardInfoService;
    @Resource
    private FinalJudgementInfoService finalJudgementInfoService;
    @Resource
	private PayCenterService payCenterService;
    @Resource
	private SMSService smsService;
    
    @Resource
	private IOrderSequenceService orderSequenceService;
    
    @Autowired
	RepaymentChangeRecordService repaymentChangeRecordService;

    @Resource
	private PayDetailInfoService payDetailInfoService;
    
    @Autowired
	RepaymentPlanInfoDao repaymentPlanInfoDao;

    @Resource
	private RepaymentInfoService repaymentInfoService;

    @Resource
	private RepaymentPayInfoService repaymentPayInfoService;
    
    @Resource
    private OnceEarlyRepaymentRecordService onceEarlyRepaymentRecordService;
    
    @Autowired
	RepaymentTxService repaymentTxService;

    @Autowired
	PayInfoService payInfoService;

    @Autowired
    GetDataService getDataService;
    @Autowired
    CarInfoService carInfoService;
    
    @Autowired
    private UserMgrDao managerDao;
    @Resource
    DeptMapper deptMapper;
    /**
     * 通用流程节点处理
     * @param applyId
     * @param operatorId
     * @param result
     * @param resultTip
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    private ResultVO<Object> processProc(Integer isMain, Long applyId, Long operatorId, Integer result, String resultTip){
        try{
            boolean isSync = false;
            Date now = new Date();
            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            User user = null;
            if(operatorId != null){
                user = userMapper.selectById(operatorId);
            }
            
            if(result==1) {//拒绝
            	 // 写入借款操作员表
                ApplyOperator applyOperator = new ApplyOperator();
                applyOperator.setApplyId(applyId);
                applyOperator.setUserId(operatorId);
                applyOperator.setCreateTime(now);
                applyOperator.setUpdateTime(now);
                applyOperator.setIsDeleted(0);
                applyOperatorDao.save(applyOperator);
                // 写入审批记录表
                MainApproveRecord record = new MainApproveRecord();
                record.setApplyId(applyId);
                record.setOperatorId(operatorId);
                record.setOperatorName(user==null?null:user.getName());
                record.setOperatorTime(now);
                record.setProcessNodeDesc("订单被拒绝");
                record.setProcessNodeId(0L);
                record.setAuditRemark(resultTip);
                record.setIsDeleted(0);
                record.setCreateTime(now);
                record.setUpdateTime(now);
                mainApproveRecordService.save(record);
                //
                applyInfo.setIsDeleted(2);//拒绝状态 0-未删除；1-已删除；2-已拒绝
                applyInfo.setStatus(0);
                applyInfo.setStatusDesc("订单被拒绝");
                applyInfoService.updateWithOutNull(applyInfo);
                
                return ResultVO.build(ErrorCode.SUCCESS);
                
            }

            ProcessNode nextNode = null;
            List<ProcessNode> nextNodeList = new ArrayList<>();
            ProcessNode mainNextNode = processNodeDao.selectNextNode(applyInfo.getStatus(), result);
            if(mainNextNode == null){
                log.error("===>[processProc] get next main process node error,status={} result={} user roleId={}",
                        applyInfo.getStatus(), result, user==null?"null":user.getRoleid());
                return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
            }

//            List<ProcessNode> nextNodeListSub = new ArrayList<>();
//            List<Map<String, Object>> mapList = processNodeDao.selectAllField();
//            for (Map<String, Object> map : mapList) {
//                ProcessNode syncTempNode = processNodeDao.selectSyncNextNode(applyId, result, map.get("syncFieldName").toString());
//                if(syncTempNode != null){
//                    nextNodeListSub.add(syncTempNode);
//                }
//            }
            //List<ProcessNode> nextNodeListSub = processNodeDao.selectNodeByType(applyInfo.getStatus(), ProcessType.SYNC.getValue());

//            if(isMain != null){
//                if(isMain == 1) {
//                    nextNodeList.add(mainNextNode);
//                }else{
//                    nextNodeList.addAll(nextNodeListSub);
//                }
//            }else{
//                nextNodeList.add(mainNextNode);
//                nextNodeList.addAll(nextNodeListSub);
//            }

//            for (ProcessNode node : nextNodeList) {
//                if (user != null && node.getRoleId() != null) {
//                    if (user.getRoleid().indexOf(node.getRoleId().toString()) >= 0) {
//                        if(node.getIsSync() == 1) {
//                            isSync = true;
//                            nextNode = node;
//                            break;
//                        }
//                    }
//                }
//            }

            if(isSync == false){
                //获取下一个节点（根据result 来获取）（成功=0 失败=1 驳回=2）
                nextNode = mainNextNode;
                if (user != null && nextNode.getRoleId() != null) {
                    if (user.getRoleid().indexOf(nextNode.getRoleId().toString()) < 0) {
                        log.error("===>[processProc] get next process node role error ,nodeId={} status={} result={} user roleId={}",
                                nextNode.getId(), nextNode.getProcessStatus(), result, user==null?"null":user.getRoleid());
                        return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
                    }
                }
            }

            if(nextNode == null){
                log.error("===>[processProc] get next process node error,status={} result={} user roleId={}",
                        applyInfo.getStatus(), result, user==null?"null":user.getRoleid());
                return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
            }

            //验证前置同步节点是否完成
            List<ProcessNode> preNodeList = processNodeDao.selectNodeByType(nextNode.getProcessStatus(), ProcessType.PRE.getValue());
            /*if(preNodeList != null && preNodeList.size() != 0){
                for (ProcessNode node : preNodeList) {
                    if(node.getIsSync() == 1){
                        if(applyInfoService.isComplateSyncTask(applyId, node.getSyncFieldName(), node.getProcessStatus()) <= 0){
                            log.error("===>[processProc] pre node not finish, pre processId={} pre processNodeName={}", node.getId(), node.getProcessName());
                            if(node.getSyncFieldName().equals("nqlr_status")) {
                            	return ResultVO.build(ErrorCode.NQLR_STATUS);
                            }else if(node.getSyncFieldName().equals("yc_status")) {
                            	return ResultVO.build(ErrorCode.YC_STATUS);
                            }else if(node.getSyncFieldName().equals("gps_install_status")) {
                            	return ResultVO.build(ErrorCode.GPS_INSTALL_STATUS);
                            }else if(node.getSyncFieldName().equals("gps_uninstall_status")) {
                            	return ResultVO.build(ErrorCode.GPS_UNINSTALL_STATUS);
                            }
                            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
                        }
                    }
                }
            }*/

            ApplyInfo tempInfo = new ApplyInfo();
            tempInfo.setId(applyId);

            if(isSync == true){
                if(applyInfoService.isComplateSyncTask(applyId, nextNode.getSyncFieldName(), nextNode.getProcessStatus()) > 0){
                    log.error("===>[processProc] already finish this process node, processId={} processNodeName={}", nextNode.getId(), nextNode.getProcessName());
                    return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
                }
                applyInfoService.setSyncStatus(applyId, nextNode.getSyncFieldName(), nextNode.getProcessStatus());
            }else{
                tempInfo.setStatus(nextNode.getProcessStatus());
                tempInfo.setStatusDesc(nextNode.getProcessStatusDesc());
                tempInfo.setUpdateTime(now);
                applyInfoService.updateWithOutNull(tempInfo);
            }

            // 写入借款操作员表
            ApplyOperator applyOperator = new ApplyOperator();
            applyOperator.setApplyId(applyId);
            applyOperator.setRoleId(nextNode.getRoleId());
            applyOperator.setUserId(operatorId);
            applyOperator.setProcessNodeId(nextNode.getId());
            applyOperator.setCreateTime(now);
            applyOperator.setUpdateTime(now);
            applyOperator.setIsDeleted(0);
            applyOperatorDao.save(applyOperator);

            // 写入审批记录表
            MainApproveRecord record = new MainApproveRecord();

            record.setApplyId(applyId);
            record.setOperatorId(operatorId);
            record.setOperatorName(user==null?null:user.getName());
            record.setOperatorTime(now);
            record.setProcessNodeId(nextNode.getId());
            record.setProcessNodeDesc(nextNode.getProcessStatusDesc());
            record.setAuditRemark(resultTip);
            record.setIsDeleted(0);
            record.setCreateTime(now);
            record.setUpdateTime(now);
            mainApproveRecordService.save(record);

            if(isSync == false){
                //设置开启同步节点
                List<ProcessNode> syncNodeList = processNodeDao.selectNodeByType(nextNode.getProcessStatus(), ProcessType.SYNC.getValue());
                for (ProcessNode node : syncNodeList) {
                    applyInfoService.setSyncStatus(applyId, node.getSyncFieldName(), 1);
                }
            }

            return ResultVO.build(ErrorCode.SUCCESS);

        }catch(Exception e){
            log.error("===>[processProc] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }


    /**
     * 机器风控调用接口
     * @param applyId
     * @return
     */
    private ResultVO<Object> callAutoRisk(Long applyId) throws Exception{
        /***以下调用大数据机器发风控内容暂时注掉，由于业务问题可能导致后台报错，面审主管提交之后风控流程不走，所以提到前面来。*/
       ApplyInfo applyInfo = applyInfoService.selectById(applyId);
       // TODO 调用接口
      //查询数据,拼接成层级结构的json
       /* Map<String, Object> machineRiskControlMap=new HashMap<>();
        Map<String, Object> machineRiskControlv=processNodeDao.machineRiskControlv(applyId,applyInfo.getCustId());
        Map<String, Object>  c_apply_info=new HashMap<>();
        Map<String, Object> c_bankcard_info=new HashMap<>();
        Map<String, Object> c_car_info=new HashMap<>();
        Map<String, Object> c_car_price_info=new HashMap<>();
        Map<String, Object> c_channel_info=new HashMap<>();
        Map<String, Object> c_credit_auth_info=new HashMap<>();
        Map<String, Object> c_marry_info=new HashMap<>();
        Map<String, Object> c_judicial_auth_info=new HashMap<>();
        Map<String, Object> c_id_auth_info=new HashMap<>();
        Map<String, Object> c_gongjie_info=new HashMap<>();
        Map<String, Object> c_family_book_info=new HashMap<>();
        Map<String, Object> c_cust_info=new HashMap<>();
        Map<String, Object> c_cust_income_info=new HashMap<>();
        List c_family_book_sub_infoList =new ArrayList();
        List c_cust_finance_infoList =new ArrayList();
        Map<String, Object> c_cust_company_info=new HashMap<>();
        Map<String, Object> c_credit_report=new HashMap<>();
        List c_credit_personal_query_recordList =new ArrayList();
        List c_credit_loan_detailList =new ArrayList();
        List c_credit_card_detailList =new ArrayList();
        Map<String, Object> c_car_verify_info=new HashMap<>();
        List c_credit_buss_query_recordlList =new ArrayList();
        Map<String, Object> c_car_peccancy_info=new HashMap<>();
        Map<String, Object> c_car_driver_info=new HashMap<>();
        Map<String, Object> c_car_traffic_insure_info=new HashMap<>();
        Map<String, Object> c_car_buss_insure_info=new HashMap<>();
        List c_car_transfer_infoList =new ArrayList();
        List c_car_mortgage_infoList =new ArrayList();
        List c_car_insure_detail_infoList =new ArrayList();
        List<CarTransferInfo> machineRiskControlCarTramsferImfo=new ArrayList();
        List<CarMortgageInfo> machineRiskControlCarMortgageInfo=new ArrayList();
        if (machineRiskControlv!=null){
            for ( Object key: machineRiskControlv.keySet()){
                if (machineRiskControlv.get(key).equals("")||machineRiskControlv.get(key)==""){
                    machineRiskControlv.put(key.toString(),"null");
                }
            }
            c_apply_info.put("id",applyId);
            c_apply_info.put("cust_id",applyInfo.getCustId());
            c_apply_info.put("channel_id",machineRiskControlv.get("channel_id"));
            c_apply_info.put("apply_amount",machineRiskControlv.get("apply_amount"));
            c_apply_info.put("apply_period",machineRiskControlv.get("apply_period"));
            c_apply_info.put("repayment_type",machineRiskControlv.get("repayment_type"));
            String loanUsage="";
            if(!"".equals(machineRiskControlv.get("loan_usage"))&&machineRiskControlv.get("loan_usage")!=""&&machineRiskControlv.get("loan_usage")!="null"){
                String Str = machineRiskControlv.get("loan_usage").toString();
                if((Integer.valueOf(Str)&1)==1){
                    loanUsage+="1,";
                }
                if((Integer.valueOf(Str)&2)==2){
                    loanUsage +="2,";
                }
                if((Integer.valueOf(Str)&4)==4){
                    loanUsage +="4,";
                }
                if((Integer.valueOf(Str)&8)==8){
                    loanUsage +="8,";
                }
                if((Integer.valueOf(Str)&16)==16){
                    loanUsage +="16,";
                }
                loanUsage =","+loanUsage;
            }
            c_apply_info.put("loan_usage",loanUsage);
            c_apply_info.put("loan_usage_other",machineRiskControlv.get("loan_usage_other"));
            c_apply_info.put("partner_know",machineRiskControlv.get("partner_know"));
            c_apply_info.put("dept_id",machineRiskControlv.get("dept_id"));

            c_bankcard_info.put("cust_id",applyInfo.getCustId());
            c_bankcard_info.put("card_no",machineRiskControlv.get("card_no"));
            c_bankcard_info.put("bank_name",machineRiskControlv.get("bank_name"));
            c_bankcard_info.put("province_city",machineRiskControlv.get("province_city"));
            c_bankcard_info.put("bank_subbranch",machineRiskControlv.get("bank_subbranch"));
            c_bankcard_info.put("card_status",machineRiskControlv.get("card_status"));
            c_bankcard_info.put("binding_time",machineRiskControlv.get("binding_time"));
            c_bankcard_info.put("binding_desc",machineRiskControlv.get("binding_desc"));
            c_bankcard_info.put("binding_mobile",machineRiskControlv.get("binding_mobile"));

            c_car_info.put("cust_id",applyInfo.getCustId());
            c_car_info.put("car_num",machineRiskControlv.get("car_num"));
            c_car_info.put("car_config_name",machineRiskControlv.get("car_config_name"));
            c_car_info.put("car_type",machineRiskControlv.get("car_type"));
            c_car_info.put("car_brand",machineRiskControlv.get("car_brand"));
            c_car_info.put("car_model",machineRiskControlv.get("car_model"));
            c_car_info.put("vin",machineRiskControlv.get("vin"));
            c_car_info.put("engine_no",machineRiskControlv.get("engine_no"));
            c_car_info.put("car_color",machineRiskControlv.get("car_color"));
            c_car_info.put("car_import_type",machineRiskControlv.get("car_import_type"));
            c_car_info.put("fuel_type",machineRiskControlv.get("fuel_type"));
            c_car_info.put("displacement",machineRiskControlv.get("displacement"));
            c_car_info.put("manufacturer",machineRiskControlv.get("manufacturer"));
            c_car_info.put("car_usage",machineRiskControlv.get("car_usage"));
            c_car_info.put("get_type",machineRiskControlv.get("get_type"));
            c_car_info.put("product_date",machineRiskControlv.get("product_date"));
            c_car_info.put("first_lic_date",machineRiskControlv.get("first_lic_date"));
            c_car_info.put("current_lic_date",machineRiskControlv.get("current_lic_date"));
            c_car_info.put("register_photo_url1",machineRiskControlv.get("register_photo_url1"));
            c_car_info.put("register_photo_url2",machineRiskControlv.get("register_photo_url2"));
            c_car_info.put("register_photo_url3",machineRiskControlv.get("register_photo_url3"));
            c_car_info.put("register_photo_url4",machineRiskControlv.get("register_photo_url4"));

            c_car_price_info.put("cust_id",applyInfo.getCustId());
            c_car_price_info.put("apply_id",applyId);
            c_car_price_info.put("che300_price",machineRiskControlv.get("che300_price"));
            c_car_price_info.put("che300_attach_url",machineRiskControlv.get("che300_attach_url"));
            c_car_price_info.put("jingzhengu_price",machineRiskControlv.get("jingzhengu_price"));
            c_car_price_info.put("jingzhengu_attach_url",machineRiskControlv.get("jingzhengu_attach_url"));
            c_car_price_info.put("nake_price",machineRiskControlv.get("nake_price"));
            c_car_price_info.put("depreciation_base",machineRiskControlv.get("depreciation_base"));
            c_car_price_info.put("depreciation_ratio",machineRiskControlv.get("depreciation_ratio"));
            c_car_price_info.put("credit_ratio",machineRiskControlv.get("credit_ratio"));
            c_car_price_info.put("tsingnuo_price",machineRiskControlv.get("tsingnuo_price"));

            c_channel_info.put("channel_name",machineRiskControlv.get("channel_name"));
            c_channel_info.put("city",machineRiskControlv.get("city"));
            c_channel_info.put("address",machineRiskControlv.get("address"));
            c_channel_info.put("fanyong_rate",machineRiskControlv.get("fanyong_rate"));
            c_channel_info.put("account_name",machineRiskControlv.get("account_name"));
            c_channel_info.put("account_bank",machineRiskControlv.get("account_bank"));
            c_channel_info.put("account_cardno",machineRiskControlv.get("account_cardno"));
            c_channel_info.put("join_person",machineRiskControlv.get("join_person"));
            c_channel_info.put("join_mobile",machineRiskControlv.get("join_mobile"));
            c_channel_info.put("create_user_id",machineRiskControlv.get("create_user_id"));
            c_channel_info.put("create_user_name",machineRiskControlv.get("create_user_name"));
            c_channel_info.put("buss_name",machineRiskControlv.get("buss_name"));

            c_credit_auth_info.put("apply_id",applyId);
            c_credit_auth_info.put("cust_id",applyInfo.getCustId());
            c_credit_auth_info.put("type",machineRiskControlv.get("type"));
            c_credit_auth_info.put("td_score",machineRiskControlv.get("td_score"));
            c_credit_auth_info.put("td_score_attach_url",machineRiskControlv.get("td_score_attach_url"));
            c_credit_auth_info.put("td_risk_attach_url",machineRiskControlv.get("td_risk_attach_url"));
            c_credit_auth_info.put("br_rule_score",machineRiskControlv.get("br_rule_score"));
            c_credit_auth_info.put("br_credit_score",machineRiskControlv.get("br_credit_score"));
            c_credit_auth_info.put("br_attach_url",machineRiskControlv.get("br_attach_url"));
            c_credit_auth_info.put("has_judgement",machineRiskControlv.get("has_judgement"));
            c_credit_auth_info.put("judgement_attach_url",machineRiskControlv.get("judgement_attach_url"));

            c_marry_info.put("cust_id",applyInfo.getCustId());
            c_marry_info.put("marry_status",machineRiskControlv.get("marry_status"));
            c_marry_info.put("marry_date",machineRiskControlv.get("marry_date"));
            c_marry_info.put("spouse_name",machineRiskControlv.get("spouse_name"));
            c_marry_info.put("spouse_sex",machineRiskControlv.get("spouse_sex"));
            c_marry_info.put("spouse_cert_id",machineRiskControlv.get("spouse_cert_id"));
            c_marry_info.put("validate_begin",machineRiskControlv.get("validate_begin"));
            c_marry_info.put("validate_end",machineRiskControlv.get("validate_end"));
            c_marry_info.put("sign_org",machineRiskControlv.get("sign_org"));
            c_marry_info.put("id_front_photo_url",machineRiskControlv.get("id_front_photo_url"));
            c_marry_info.put("id_back_photo_url",machineRiskControlv.get("id_back_photo_url"));
            c_marry_info.put("marry_photo_url",machineRiskControlv.get("marry_photo_url"));
            c_marry_info.put("divorce_date",machineRiskControlv.get("divorce_date"));
            c_marry_info.put("divorce_name",machineRiskControlv.get("divorce_name"));
            c_marry_info.put("divorce_sex",machineRiskControlv.get("divorce_sex"));
            c_marry_info.put("divorce_cert_id",machineRiskControlv.get("divorce_cert_id"));
            c_marry_info.put("divorce_photo_url",machineRiskControlv.get("divorce_photo_url"));
            c_marry_info.put("death_cert_photo_url",machineRiskControlv.get("death_cert_photo_url"));

            c_judicial_auth_info.put("type",machineRiskControlv.get("type6"));
            c_judicial_auth_info.put("court_personal",machineRiskControlv.get("court_personal"));
            c_judicial_auth_info.put("zhixing_personal",machineRiskControlv.get("zhixing_personal"));
            c_judicial_auth_info.put("risk_personal",machineRiskControlv.get("risk_personal"));
            c_judicial_auth_info.put("warn_personal",machineRiskControlv.get("warn_personal"));

            c_id_auth_info.put("cust_id",applyInfo.getCustId());
            c_id_auth_info.put("apply_id",applyId);
            c_id_auth_info.put("type",machineRiskControlv.get("type7"));
            c_id_auth_info.put("gongan_photol_id",machineRiskControlv.get("gongan_photol_id"));
            c_id_auth_info.put("is_id_auth",machineRiskControlv.get("is_id_auth"));
            c_id_auth_info.put("id_front_photo_url",machineRiskControlv.get("id_front_photo_url1"));
            c_id_auth_info.put("id_back_photo_url",machineRiskControlv.get("id_back_photo_url1"));
            c_id_auth_info.put("hold_identify_photo",machineRiskControlv.get("hold_identify_photo"));
            c_id_auth_info.put("auth_time",machineRiskControlv.get("auth_time"));
            c_id_auth_info.put("user_name",machineRiskControlv.get("user_name"));
            c_id_auth_info.put("id_number",machineRiskControlv.get("id_number"));
            c_id_auth_info.put("nation",machineRiskControlv.get("nation"));
            c_id_auth_info.put("address",machineRiskControlv.get("address1"));
            c_id_auth_info.put("sign_orgaization",machineRiskControlv.get("sign_orgaization"));
            c_id_auth_info.put("validity_period",machineRiskControlv.get("validity_period"));

            c_gongjie_info.put("cust_id",applyInfo.getCustId());
            c_gongjie_info.put("name",machineRiskControlv.get("name"));
            c_gongjie_info.put("mobile",machineRiskControlv.get("mobile"));
            c_gongjie_info.put("sex",machineRiskControlv.get("sex"));
            c_gongjie_info.put("cert_id",machineRiskControlv.get("cert_id"));
            c_gongjie_info.put("id_front_photo_url",machineRiskControlv.get("id_front_photo_url2"));
            c_gongjie_info.put("id_back_photo_url",machineRiskControlv.get("id_back_photo_url2"));
            c_gongjie_info.put("marry_status",machineRiskControlv.get("marry_status1"));
            c_gongjie_info.put("relation",machineRiskControlv.get("relation"));
            c_gongjie_info.put("live_address",machineRiskControlv.get("live_address"));
            c_gongjie_info.put("occupation_type",machineRiskControlv.get("occupation_type"));
            c_gongjie_info.put("company_name",machineRiskControlv.get("company_name"));
            c_gongjie_info.put("company_type",machineRiskControlv.get("company_type"));
            c_gongjie_info.put("company_address",machineRiskControlv.get("company_address"));
            c_gongjie_info.put("company_tel",machineRiskControlv.get("company_tel"));
            c_gongjie_info.put("department",machineRiskControlv.get("department"));
            c_gongjie_info.put("job",machineRiskControlv.get("job"));
            c_gongjie_info.put("month_income",machineRiskControlv.get("month_income"));
            c_gongjie_info.put("company_attach_url",machineRiskControlv.get("company_attach_url"));

            c_family_book_info.put("cust_id",applyInfo.getCustId());
            c_family_book_info.put("relationship",machineRiskControlv.get("relationship"));
            c_family_book_info.put("master_name",machineRiskControlv.get("master_name"));
            c_family_book_info.put("master_sex",machineRiskControlv.get("master_sex"));
            c_family_book_info.put("cert_id1",machineRiskControlv.get("cert_id1"));
            c_family_book_info.put("first_page_photo_url",machineRiskControlv.get("first_page_photo_url"));

            c_cust_info.put("id",applyInfo.getCustId());
            c_cust_info.put("name",machineRiskControlv.get("name1"));
            c_cust_info.put("mobile",machineRiskControlv.get("mobile1"));
            c_cust_info.put("sex",machineRiskControlv.get("sex1"));
            c_cust_info.put("nation",machineRiskControlv.get("nation1"));
            c_cust_info.put("birthday",machineRiskControlv.get("birthday"));
            c_cust_info.put("cert_id",machineRiskControlv.get("cert_id2"));
            c_cust_info.put("validate_begin",machineRiskControlv.get("validate_begin1"));
            c_cust_info.put("validate_end",machineRiskControlv.get("validate_end1"));
            c_cust_info.put("sign_org",machineRiskControlv.get("sign_org1"));
            c_cust_info.put("education",machineRiskControlv.get("education"));
            c_cust_info.put("child_num",machineRiskControlv.get("child_num"));
            String childAdult="";
            if(!"".equals(machineRiskControlv.get("child_adult"))&&machineRiskControlv.get("child_adult")!=""&&machineRiskControlv.get("child_adult")!="null"){
                String Str = machineRiskControlv.get("child_adult").toString();
                if((Integer.valueOf(Str)&1)==1){
                    childAdult="1,";
                }
                if((Integer.valueOf(Str)&2)==2){
                    childAdult +="2,";
                }
                if((Integer.valueOf(Str)&4)==4){
                    childAdult +="4,";
                }
                childAdult=","+childAdult;
            }else{
                childAdult="null";
            }
            c_cust_info.put("child_adult",childAdult);
            c_cust_info.put("live_address",machineRiskControlv.get("live_address"));
            String liveType="";
            if(!"".equals(machineRiskControlv.get("live_type"))&&machineRiskControlv.get("live_type")!=""&&machineRiskControlv.get("live_type")!="null"){
                String Str = machineRiskControlv.get("live_type").toString();
                if((Integer.valueOf(Str)&1)==1){
                    liveType="1,";
                }
                if((Integer.valueOf(Str)&2)==2){
                    liveType +="2,";
                }
                if((Integer.valueOf(Str)&4)==4){
                    liveType +="4,";
                }
                if((Integer.valueOf(Str)&8)==8){
                    liveType +="8,";
                }
                if((Integer.valueOf(Str)&16)==16){
                    liveType +="16,";
                }
                if((Integer.valueOf(Str)&32)==32){
                    liveType +="32,";
                }
                if((Integer.valueOf(Str)&64)==64){
                    liveType +="64,";
                }
                if((Integer.valueOf(Str)&128)==128){
                    liveType +="128,";
                }
                if((Integer.valueOf(Str)&256)==256){
                    liveType +="256,";
                }
                liveType=","+liveType;
            }
            c_cust_info.put("live_type",liveType);
            String togetherLive="";
            if(!"".equals(machineRiskControlv.get("together_live"))&&machineRiskControlv.get("together_live")!=""&&machineRiskControlv.get("together_live")!="null"){
                String Str = machineRiskControlv.get("together_live").toString();
                if((Integer.valueOf(Str)&1)==1){
                    togetherLive+="1,";
                }
                if((Integer.valueOf(Str)&2)==2){
                    togetherLive +="2,";
                }
                if((Integer.valueOf(Str)&4)==4){
                    togetherLive +="4,";
                }
                if((Integer.valueOf(Str)&8)==8){
                    togetherLive +="8,";
                }
                if((Integer.valueOf(Str)&16)==16){
                    togetherLive +="16,";
                }
                if((Integer.valueOf(Str)&32)==32){
                    togetherLive +="32,";
                }
                if((Integer.valueOf(Str)&64)==64){
                    togetherLive +="64,";
                }
                togetherLive=","+togetherLive;
            }
            c_cust_info.put("together_live",togetherLive);
            c_cust_info.put("spouse_name",machineRiskControlv.get("spouse_name1"));
            c_cust_info.put("spouse_phone",machineRiskControlv.get("spouse_phone"));
            c_cust_info.put("contract_name1",machineRiskControlv.get("contract_name1"));
            c_cust_info.put("contract_phone1",machineRiskControlv.get("contract_phone1"));
            c_cust_info.put("contract_relation1",machineRiskControlv.get("contract_relation1"));
            c_cust_info.put("contract_name2",machineRiskControlv.get("contract_name2"));
            c_cust_info.put("contract_phone2",machineRiskControlv.get("contract_phone2"));
            c_cust_info.put("contract_relation2",machineRiskControlv.get("contract_relation2"));
            c_cust_info.put("contract_name3",machineRiskControlv.get("contract_name3"));
            c_cust_info.put("contract_phone3",machineRiskControlv.get("contract_phone3"));
            c_cust_info.put("contract_relation3",machineRiskControlv.get("contract_relation3"));

            c_cust_income_info.put("cust_id",applyInfo.getCustId());
            c_cust_income_info.put("apply_id",applyId);
            c_cust_income_info.put("income_type",machineRiskControlv.get("income_type"));
            c_cust_income_info.put("income_amount",machineRiskControlv.get("income_amount"));
            c_cust_income_info.put("income_confirm_amount",machineRiskControlv.get("income_confirm_amount"));
            c_cust_income_info.put("loan_amount",machineRiskControlv.get("loan_amount"));
            c_cust_income_info.put("dti",machineRiskControlv.get("dti"));
            if(machineRiskControlv.get("familyBookInfoId")!=null&&!"null".equals(machineRiskControlv.get("familyBookInfoId"))){
                List<FamilyBookSubInfo> familyBookSubInfolist =processNodeDao.machineRiskControlvx(Long.valueOf(machineRiskControlv.get("familyBookInfoId").toString()));
                if(familyBookSubInfolist.size()>0){
                    for (FamilyBookSubInfo familyBookSubInfo : familyBookSubInfolist) {
                        Map<String, Object> c_family_book_sub_info=new HashMap<>();
                        c_family_book_sub_info.put("book_id",familyBookSubInfo.getBookId());
                        c_family_book_sub_info.put("relationship",familyBookSubInfo.getRelationship());
                        c_family_book_sub_info.put("name",familyBookSubInfo.getName());
                        c_family_book_sub_info.put("sex",familyBookSubInfo.getSex());
                        c_family_book_sub_info.put("cert_id",familyBookSubInfo.getCertId());
                        c_family_book_sub_info.put("book_photo_url",familyBookSubInfo.getBookPhotoUrl());
                        c_family_book_sub_infoList.add(c_family_book_sub_info);
                    }
                }else{
                    Map<String, Object> c_family_book_sub_info=new HashMap<>();
                    c_family_book_sub_info.put("book_id","null");
                    c_family_book_sub_info.put("relationship","null");
                    c_family_book_sub_info.put("name","null");
                    c_family_book_sub_info.put("sex","null");
                    c_family_book_sub_info.put("cert_id","null");
                    c_family_book_sub_info.put("book_photo_url","null");
                    c_family_book_sub_infoList.add(c_family_book_sub_info);
                }
            }else{
                Map<String, Object> c_family_book_sub_info=new HashMap<>();
                c_family_book_sub_info.put("book_id","null");
                c_family_book_sub_info.put("relationship","null");
                c_family_book_sub_info.put("name","null");
                c_family_book_sub_info.put("sex","null");
                c_family_book_sub_info.put("cert_id","null");
                c_family_book_sub_info.put("book_photo_url","null");
                c_family_book_sub_infoList.add(c_family_book_sub_info);
            }
            List<CustFinanceInfo> custFinanceInfolist =processNodeDao.machineRiskControlCustFinanceInfo(applyId,applyInfo.getCustId());
            if(custFinanceInfolist.size()>0){
                for (CustFinanceInfo custFinanceInfo : custFinanceInfolist) {
                    Map<String, Object> c_cust_finance_info=new HashMap<>();
                    c_cust_finance_info.put("cust_id",applyInfo.getCustId());
                    c_cust_finance_info.put("apply_id",applyId);
                    c_cust_finance_info.put("type",custFinanceInfo.getType());
                    c_cust_finance_info.put("fin_type",custFinanceInfo.getFinType());
                    c_cust_finance_info.put("property",custFinanceInfo.getProperty());
                    c_cust_finance_info.put("status",custFinanceInfo.getStatus());
                    c_cust_finance_info.put("num",custFinanceInfo.getNum());
                    c_cust_finance_infoList.add(c_cust_finance_info);
                }
            }else{
                Map<String, Object> c_cust_finance_info=new HashMap<>();
                c_cust_finance_info.put("cust_id",applyInfo.getCustId());
                c_cust_finance_info.put("apply_id",applyId);
                c_cust_finance_info.put("type","null");
                c_cust_finance_info.put("fin_type","null");
                c_cust_finance_info.put("property","null");
                c_cust_finance_info.put("status","null");
                c_cust_finance_info.put("num","null");
                c_cust_finance_infoList.add(c_cust_finance_info);
            }
            c_cust_company_info.put("cust_id",applyInfo.getCustId());
            c_cust_company_info.put("apply_id",applyId);
            c_cust_company_info.put("type",machineRiskControlv.get("type8"));
            c_cust_company_info.put("company_name",machineRiskControlv.get("company_name1"));
            c_cust_company_info.put("industry",machineRiskControlv.get("industry"));
            c_cust_company_info.put("found_time",machineRiskControlv.get("found_time"));
            c_cust_company_info.put("court_enterprise",machineRiskControlv.get("court_enterprise"));
            c_cust_company_info.put("zhixing_enterprise",machineRiskControlv.get("zhixing_enterprise"));
            c_cust_company_info.put("risk_enterprise",machineRiskControlv.get("risk_enterprise"));
            c_cust_company_info.put("warn_enterprise",machineRiskControlv.get("warn_enterprise"));
            c_cust_company_info.put("enterprise_status",machineRiskControlv.get("enterprise_status"));
            c_credit_report.put("cust_id",applyInfo.getCustId());
            c_credit_report.put("apply_id",applyId);
            c_credit_report.put("type",machineRiskControlv.get("type5"));
            c_credit_report.put("collection_date",machineRiskControlv.get("collection_date"));
            c_credit_report.put("house_loan_num",machineRiskControlv.get("house_loan_num"));
            c_credit_report.put("house_buss_loan_num",machineRiskControlv.get("house_buss_loan_num"));
            c_credit_report.put("other_loan_num",machineRiskControlv.get("other_loan_num"));
            c_credit_report.put("first_loan_start_date",machineRiskControlv.get("first_loan_start_date"));
            c_credit_report.put("credit_card_num",machineRiskControlv.get("credit_card_num"));
            c_credit_report.put("first_credit_card_start_date",machineRiskControlv.get("first_credit_card_start_date"));
            c_credit_report.put("semi_credit_card_num",machineRiskControlv.get("semi_credit_card_num"));
            c_credit_report.put("first_semi_credit_card_start_date",machineRiskControlv.get("first_semi_credit_card_start_date"));
            c_credit_report.put("self_declare_num",machineRiskControlv.get("self_declare_num"));
            c_credit_report.put("objection_num",machineRiskControlv.get("objection_num"));
            c_credit_report.put("loan_overdue_num",machineRiskControlv.get("loan_overdue_num"));
            c_credit_report.put("loan_overdue_month_num",machineRiskControlv.get("loan_overdue_month_num"));
            c_credit_report.put("loan_max_amount",machineRiskControlv.get("loan_max_amount"));
            c_credit_report.put("loan_max_month_num",machineRiskControlv.get("loan_max_month_num"));
            c_credit_report.put("card_overdue_num",machineRiskControlv.get("card_overdue_num"));
            c_credit_report.put("card_month_num",machineRiskControlv.get("card_month_num"));
            c_credit_report.put("card_overdue_max_amount",machineRiskControlv.get("card_overdue_max_amount"));
            c_credit_report.put("card_max_month_num",machineRiskControlv.get("card_max_month_num"));
            c_credit_report.put("semi_card_overdue_num",machineRiskControlv.get("semi_card_overdue_num"));
            c_credit_report.put("semi_card_month_num",machineRiskControlv.get("semi_card_month_num"));
            c_credit_report.put("semi_card_max_amount",machineRiskControlv.get("semi_card_max_amount"));
            c_credit_report.put("semi_card_max_month_num",machineRiskControlv.get("semi_card_max_month_num"));
            c_credit_report.put("loan_legal_org_num",machineRiskControlv.get("loan_legal_org_num"));
            c_credit_report.put("loan_org_num",machineRiskControlv.get("loan_org_num"));
            c_credit_report.put("loan_num",machineRiskControlv.get("loan_num"));
            c_credit_report.put("loan_total_amount",machineRiskControlv.get("loan_total_amount"));
            c_credit_report.put("loan_left_amount",machineRiskControlv.get("loan_left_amount"));
            c_credit_report.put("loan_total_month_amount",machineRiskControlv.get("loan_total_month_amount"));
            c_credit_report.put("card_legal_org_num",machineRiskControlv.get("card_legal_org_num"));
            c_credit_report.put("card_org_num",machineRiskControlv.get("card_org_num"));
            c_credit_report.put("card_account_num",machineRiskControlv.get("card_account_num"));
            c_credit_report.put("card_total_amount",machineRiskControlv.get("card_total_amount"));
            c_credit_report.put("card_max_amount",machineRiskControlv.get("card_max_amount"));
            c_credit_report.put("card_min_amount",machineRiskControlv.get("card_min_amount"));
            c_credit_report.put("card_used_amount",machineRiskControlv.get("card_used_amount"));
            c_credit_report.put("card_avg_amount",machineRiskControlv.get("card_avg_amount"));
            c_credit_report.put("history_query_num",machineRiskControlv.get("history_query_num"));
            c_credit_report.put("card_month_amount",machineRiskControlv.get("card_month_amount"));
            c_credit_report.put("credit_loan_month_amount",machineRiskControlv.get("credit_loan_month_amount"));

            List<CreditPersonalQueryRecord> CreditPersonalQueryRecordList =processNodeDao.machineRiskControlCreditPersonalQueryRecord(applyId,applyInfo.getCustId());
            if(CreditPersonalQueryRecordList.size()>0){
                for (CreditPersonalQueryRecord creditPersonalQueryRecord : CreditPersonalQueryRecordList) {
                    Map<String, Object> c_credit_personal_query_record=new HashMap<>();
                    c_credit_personal_query_record.put("apply_id",applyId);
                    c_credit_personal_query_record.put("cust_id",applyInfo.getCustId());
                    c_credit_personal_query_record.put("type",creditPersonalQueryRecord.getType());
                    c_credit_personal_query_record.put("query_date",creditPersonalQueryRecord.getQueryDate());
                    c_credit_personal_query_record.put("query_org",creditPersonalQueryRecord.getQueryOrg());
                    c_credit_personal_query_recordList.add(c_credit_personal_query_record);
                }
            }else{
                Map<String, Object> c_credit_personal_query_record=new HashMap<>();
                c_credit_personal_query_record.put("apply_id",applyId);
                c_credit_personal_query_record.put("cust_id",applyInfo.getCustId());
                c_credit_personal_query_record.put("type","null");
                c_credit_personal_query_record.put("query_date","null");
                c_credit_personal_query_record.put("query_org","null");
                c_credit_personal_query_recordList.add(c_credit_personal_query_record);
            }
            List<CreditLoanDetail> CreditLoanDetailList =processNodeDao.machineRiskControlCreditLoanDetail(applyId,applyInfo.getCustId());
            if (CreditLoanDetailList.size()>0){
                for (CreditLoanDetail creditLoanDetail : CreditLoanDetailList) {
                    Map<String, Object> c_credit_loan_detail=new HashMap<>();
                    c_credit_loan_detail.put("apply_id",applyId);
                    c_credit_loan_detail.put("cust_id",applyInfo.getCustId());
                    c_credit_loan_detail.put("type",creditLoanDetail.getType());
                    c_credit_loan_detail.put("loan_org",creditLoanDetail.getLoanOrg());
                    c_credit_loan_detail.put("loan_amount",creditLoanDetail.getLoanAmount());
                    c_credit_loan_detail.put("loan_type",creditLoanDetail.getLoanType());
                    c_credit_loan_detail.put("loan_period",creditLoanDetail.getLoanPeriod());
                    c_credit_loan_detail.put("loan_begin_time",creditLoanDetail.getLoanBeginTime());
                    c_credit_loan_detail.put("loan_end_time",creditLoanDetail.getLoanEndTime());
                    c_credit_loan_detail.put("account_status",creditLoanDetail.getAccountStatus());
                    c_credit_loan_detail.put("five_class_status",creditLoanDetail.getFiveClassStatus());
                    c_credit_loan_detail.put("capital_amount",creditLoanDetail.getCapitalAmount());
                    c_credit_loan_detail.put("left_period",creditLoanDetail.getLeftPeriod());
                    c_credit_loan_detail.put("cur_month_predict_amount",creditLoanDetail.getCurMonthActuralAmount());
                    c_credit_loan_detail.put("cur_month_date",creditLoanDetail.getCurMonthDate());
                    c_credit_loan_detail.put("cur_month_actural_amount",creditLoanDetail.getCurMonthActuralAmount());
                    c_credit_loan_detail.put("last_repayment_datge",creditLoanDetail.getLastRepaymentDatge());
                    c_credit_loan_detail.put("cur_overdue_num",creditLoanDetail.getCurOverdueNum());
                    c_credit_loan_detail.put("cur_overdue_amount",creditLoanDetail.getCurOverdueAmount());
                    c_credit_loan_detail.put("overdue_m2_capital",creditLoanDetail.getOverdueM2Capital());
                    c_credit_loan_detail.put("overdue_m3_capital",creditLoanDetail.getOverdueM3Capital());
                    c_credit_loan_detail.put("overdue_m45_capital",creditLoanDetail.getOverdueM45Capital());
                    c_credit_loan_detail.put("overdue_m6_capital",creditLoanDetail.getOverdueM6Capital());
                    c_credit_loan_detail.put("repayment_info",creditLoanDetail.getRepaymentInfo());
                    c_credit_loan_detailList.add(c_credit_loan_detail);
                }
            }else{
                Map<String, Object> c_credit_loan_detail=new HashMap<>();
                c_credit_loan_detail.put("apply_id",applyId);
                c_credit_loan_detail.put("cust_id",applyInfo.getCustId());
                c_credit_loan_detail.put("type","null");
                c_credit_loan_detail.put("loan_org","null");
                c_credit_loan_detail.put("loan_amount","null");
                c_credit_loan_detail.put("loan_type","null");
                c_credit_loan_detail.put("loan_period","null");
                c_credit_loan_detail.put("loan_begin_time","null");
                c_credit_loan_detail.put("loan_end_time","null");
                c_credit_loan_detail.put("account_status","null");
                c_credit_loan_detail.put("five_class_status","null");
                c_credit_loan_detail.put("capital_amount","null");
                c_credit_loan_detail.put("left_period","null");
                c_credit_loan_detail.put("cur_month_predict_amount","null");
                c_credit_loan_detail.put("cur_month_date","null");
                c_credit_loan_detail.put("cur_month_actural_amount","null");
                c_credit_loan_detail.put("last_repayment_datge","null");
                c_credit_loan_detail.put("cur_overdue_num","null");
                c_credit_loan_detail.put("cur_overdue_amount","null");
                c_credit_loan_detail.put("overdue_m2_capital","null");
                c_credit_loan_detail.put("overdue_m3_capital","null");
                c_credit_loan_detail.put("overdue_m45_capital","null");
                c_credit_loan_detail.put("overdue_m6_capital","null");
                c_credit_loan_detail.put("repayment_info","null");
                c_credit_loan_detailList.add(c_credit_loan_detail);
            }
            List<CreditCardDetail> CreditCardDetailList =processNodeDao.machineRiskControlCreditCardDetail(applyId,applyInfo.getCustId());
            if(CreditCardDetailList.size()>0){
                for (CreditCardDetail creditCardDetail : CreditCardDetailList) {
                    Map<String, Object> c_credit_card_detail=new HashMap<>();
                    c_credit_card_detail.put("apply_id",applyId);
                    c_credit_card_detail.put("cust_id",applyInfo.getCustId());
                    c_credit_card_detail.put("type",creditCardDetail.getType());
                    c_credit_card_detail.put("card_org",creditCardDetail.getCardOrg());
                    c_credit_card_detail.put("card_amount",creditCardDetail.getCardAmount());
                    c_credit_card_detail.put("card_share_amount",creditCardDetail.getCardShareAmount());
                    c_credit_card_detail.put("card_type",creditCardDetail.getCardType());
                    c_credit_card_detail.put("account_status",creditCardDetail.getAccountStatus());
                    c_credit_card_detail.put("used_amount",creditCardDetail.getUsedAmount());
                    c_credit_card_detail.put("avg_used_amount",creditCardDetail.getAvgUsedAmount());
                    c_credit_card_detail.put("max_used_amount",creditCardDetail.getMaxUsedAmount());
                    c_credit_card_detail.put("cur_overdue_num",creditCardDetail.getCurOverdueNum());
                    c_credit_card_detail.put("cur_overdue_amount",creditCardDetail.getCurOverdueAmount());
                    c_credit_card_detailList.add(c_credit_card_detail);
                }
            }else{
                Map<String, Object> c_credit_card_detail=new HashMap<>();
                c_credit_card_detail.put("apply_id",applyId);
                c_credit_card_detail.put("cust_id",applyInfo.getCustId());
                c_credit_card_detail.put("type","null");
                c_credit_card_detail.put("card_org","null");
                c_credit_card_detail.put("card_amount","null");
                c_credit_card_detail.put("card_share_amount","null");
                c_credit_card_detail.put("card_type","null");
                c_credit_card_detail.put("account_status","null");
                c_credit_card_detail.put("used_amount","null");
                c_credit_card_detail.put("avg_used_amount","null");
                c_credit_card_detail.put("max_used_amount","null");
                c_credit_card_detail.put("cur_overdue_num","null");
                c_credit_card_detail.put("cur_overdue_amount","null");
                c_credit_card_detailList.add(c_credit_card_detail);
            }
            List<CreditBussQueryRecord> CreditBussQueryRecordList =processNodeDao.machineRiskControlCreditBussQueryRecord(applyId,applyInfo.getCustId());
            if(CreditBussQueryRecordList.size()>0){
                for (CreditBussQueryRecord creditBussQueryRecord : CreditBussQueryRecordList) {
                    Map<String, Object> c_credit_buss_query_record=new HashMap<>();
                    c_credit_buss_query_record.put("apply_id",applyId);
                    c_credit_buss_query_record.put("cust_id",applyInfo.getCustId());
                    c_credit_buss_query_record.put("type",creditBussQueryRecord.getType());
                    c_credit_buss_query_record.put("query_date",creditBussQueryRecord.getQueryDate());
                    c_credit_buss_query_record.put("query_org",creditBussQueryRecord.getQueryOrg());
                    if(creditBussQueryRecord.getQueryReason()!=null){
                        c_credit_buss_query_record.put("query_reason",creditBussQueryRecord.getQueryReason());
                    }else{
                        c_credit_buss_query_record.put("query_reason","null");
                    }

                    c_credit_buss_query_recordlList.add(c_credit_buss_query_record);
                }
            }else{
                Map<String, Object> c_credit_buss_query_record=new HashMap<>();
                c_credit_buss_query_record.put("apply_id",applyId);
                c_credit_buss_query_record.put("cust_id",applyInfo.getCustId());
                c_credit_buss_query_record.put("type","null");
                c_credit_buss_query_record.put("query_date","null");
                c_credit_buss_query_record.put("query_org","null");
                c_credit_buss_query_record.put("query_reason","null");
                c_credit_buss_query_recordlList.add(c_credit_buss_query_record);
            }
            if(machineRiskControlv.get("car_id")!=null){
                Long carId=Long.valueOf(machineRiskControlv.get("car_id").toString());
                Map<String, Object> machineRiskControlCar=processNodeDao.machineRiskControlCar(carId);
                if(machineRiskControlCar!=null){
                    for ( Object key: machineRiskControlCar.keySet()){
                        if (machineRiskControlCar.get(key).equals("")||machineRiskControlCar.get(key)==""){
                            machineRiskControlCar.put(key.toString(),"null");
                        }
                    }
                    c_car_verify_info.put("car_id",carId);
                    c_car_verify_info.put("car_cond",machineRiskControlCar.get("car_cond"));
                    c_car_verify_info.put("suggestion",machineRiskControlCar.get("suggestion"));
                    c_car_verify_info.put("config_table_photo",machineRiskControlCar.get("config_table_photo"));
                    c_car_verify_info.put("maintain_photo",machineRiskControlCar.get("maintain_photo"));
                    c_car_verify_info.put("car_assessment_price",machineRiskControlCar.get("car_assessment_price"));

                    c_car_traffic_insure_info.put("car_id",carId);
                    c_car_traffic_insure_info.put("inst_full_name",machineRiskControlCar.get("inst_full_name"));
                    c_car_traffic_insure_info.put("bussiness_source",machineRiskControlCar.get("bussiness_source"));
                    c_car_traffic_insure_info.put("proxy_name",machineRiskControlCar.get("proxy_name"));
                    c_car_traffic_insure_info.put("insure_number",machineRiskControlCar.get("insure_number"));
                    c_car_traffic_insure_info.put("insure_person",machineRiskControlCar.get("insure_person"));
                    c_car_traffic_insure_info.put("insure_begin_time",machineRiskControlCar.get("insure_begin_time"));
                    c_car_traffic_insure_info.put("insure_end_time",machineRiskControlCar.get("insure_end_time"));
                    c_car_traffic_insure_info.put("float_prop",machineRiskControlCar.get("float_prop"));
                    c_car_traffic_insure_info.put("total_amount",machineRiskControlCar.get("total_amount"));
                    c_car_traffic_insure_info.put("vehicle_tax",machineRiskControlCar.get("vehicle_tax"));
                    c_car_traffic_insure_info.put("sign_date",machineRiskControlCar.get("sign_date"));
                    c_car_traffic_insure_info.put("special_agreement",machineRiskControlCar.get("special_agreement"));

                    c_car_peccancy_info.put("car_id",carId);
                    c_car_peccancy_info.put("total_num",machineRiskControlCar.get("total_num"));
                    c_car_peccancy_info.put("total_money",machineRiskControlCar.get("total_money"));
                    c_car_peccancy_info.put("total_value",machineRiskControlCar.get("total_value"));
                    c_car_peccancy_info.put("total_full_num",machineRiskControlCar.get("total_full_num"));

                    c_car_driver_info.put("car_id",carId);
                    c_car_driver_info.put("user_id",machineRiskControlCar.get("user_id"));
                    c_car_driver_info.put("vehicle_valid_date",machineRiskControlCar.get("vehicle_valid_date"));
                    c_car_driver_info.put("vehicle_front_photo",machineRiskControlCar.get("vehicle_front_photo"));
                    c_car_driver_info.put("vehicle_back_photo",machineRiskControlCar.get("vehicle_back_photo"));
                    c_car_driver_info.put("is_driver_lic",machineRiskControlCar.get("is_driver_lic"));
                    c_car_driver_info.put("driver_no",machineRiskControlCar.get("driver_no"));
                    c_car_driver_info.put("first_driver_date",machineRiskControlCar.get("first_driver_date"));
                    c_car_driver_info.put("permit_type",machineRiskControlCar.get("permit_type"));
                    c_car_driver_info.put("driver_begin_date",machineRiskControlCar.get("driver_begin_date"));
                    c_car_driver_info.put("driver_end_date",machineRiskControlCar.get("driver_end_date"));
                    c_car_driver_info.put("is_self",machineRiskControlCar.get("is_self"));
                    c_car_driver_info.put("driver_relation",machineRiskControlCar.get("driver_relation"));
                    c_car_driver_info.put("driver_remark",machineRiskControlCar.get("driver_remark"));

                    c_car_buss_insure_info.put("car_id",carId);
                    c_car_buss_insure_info.put("inst_full_name",machineRiskControlCar.get("inst_full_name1"));
                    c_car_buss_insure_info.put("bussiness_source",machineRiskControlCar.get("bussiness_source1"));
                    c_car_buss_insure_info.put("proxy_name",machineRiskControlCar.get("proxy_name1"));
                    c_car_buss_insure_info.put("insure_number",machineRiskControlCar.get("insure_number1"));
                    c_car_buss_insure_info.put("insure_person",machineRiskControlCar.get("insure_person1"));
                    c_car_buss_insure_info.put("insure_begin_time",machineRiskControlCar.get("insure_begin_time1"));
                    c_car_buss_insure_info.put("insure_end_time",machineRiskControlCar.get("insure_end_time1"));
                    c_car_buss_insure_info.put("float_prop",machineRiskControlCar.get("float_prop1"));
                    c_car_buss_insure_info.put("total_amount",machineRiskControlCar.get("total_amount1"));
                    c_car_buss_insure_info.put("vehicle_tax",machineRiskControlCar.get("vehicle_tax1"));
                    c_car_buss_insure_info.put("sign_date",machineRiskControlCar.get("sign_date1"));
                    c_car_buss_insure_info.put("special_agreement",machineRiskControlCar.get("special_agreement1"));
                    if(machineRiskControlCar.get("carBussInsureInfoId")!=null){
                        List<CarInsureDetailInfo> machineRiskControlCarInsureDetailInfo=processNodeDao.machineRiskControlCarInsureDetailInfo(Long.valueOf(machineRiskControlCar.get("carBussInsureInfoId").toString()));
                        if (machineRiskControlCarInsureDetailInfo.size()>0){
                            for (CarInsureDetailInfo carInsureDetailInfo : machineRiskControlCarInsureDetailInfo) {
                                Map<String, Object> c_car_insure_detail_info =new HashMap<>();
                                c_car_insure_detail_info.put("insure_id",Long.valueOf(machineRiskControlCar.get("carBussInsureInfoId").toString()));
                                c_car_insure_detail_info.put("type",carInsureDetailInfo.getType());
                                c_car_insure_detail_info.put("is_no_deduct",carInsureDetailInfo.getIsNoDeduct());
                                c_car_insure_detail_info.put("max_pay_amount",carInsureDetailInfo.getMaxPayAmount());
                                c_car_insure_detail_info.put("amount",carInsureDetailInfo.getAmount());
                                c_car_insure_detail_infoList.add(c_car_insure_detail_info);
                            }
                        }
                    }else{
                        Map<String, Object> c_car_insure_detail_info =new HashMap<>();
                        c_car_insure_detail_info.put("insure_id",Long.valueOf(machineRiskControlCar.get("carBussInsureInfoId").toString()));
                        c_car_insure_detail_info.put("type","null");
                        c_car_insure_detail_info.put("is_no_deduct","null");
                        c_car_insure_detail_info.put("max_pay_amount","null");
                        c_car_insure_detail_info.put("amount","null");
                        c_car_insure_detail_infoList.add(c_car_insure_detail_info);
                    }
                }
                machineRiskControlCarTramsferImfo=processNodeDao.machineRiskControlCarTramsferImfo(carId);
                if(machineRiskControlCarTramsferImfo.size()>0){
                    for (CarTransferInfo carTransferInfo : machineRiskControlCarTramsferImfo) {
                        Map<String, Object> c_car_transfer_info=new HashMap<>();
                        c_car_transfer_info.put("car_id",carId);
                        c_car_transfer_info.put("name",carTransferInfo.getName());
                        c_car_transfer_info.put("cert_id",carTransferInfo.getCertId());
                        c_car_transfer_info.put("get_type",carTransferInfo.getGetType());
                        c_car_transfer_info.put("reg_date",carTransferInfo.getRegDate());
                        c_car_transfer_infoList.add(c_car_transfer_info);
                    }
                }else{
                    Map<String, Object> c_car_transfer_info=new HashMap<>();
                    c_car_transfer_info.put("car_id",carId);
                    c_car_transfer_info.put("name","null");
                    c_car_transfer_info.put("cert_id","null");
                    c_car_transfer_info.put("get_type","null");
                    c_car_transfer_info.put("reg_date","null");
                    c_car_transfer_infoList.add(c_car_transfer_info);
                }
                machineRiskControlCarMortgageInfo=processNodeDao.machineRiskControlCarMortgageInfo(carId);
                if(machineRiskControlCarMortgageInfo.size()>0){
                    for (CarMortgageInfo carMortgageInfo : machineRiskControlCarMortgageInfo) {
                        Map<String, Object> c_car_mortgage_info=new HashMap<>();
                        c_car_mortgage_info.put("car_id",carId);
                        c_car_mortgage_info.put("name",carMortgageInfo.getName());
                        c_car_mortgage_info.put("cert_id",carMortgageInfo.getCertId());
                        c_car_mortgage_info.put("reg_date",carMortgageInfo.getRegDate());
                        c_car_mortgage_infoList.add(c_car_mortgage_info);
                    }

                }else{
                    Map<String, Object> c_car_mortgage_info=new HashMap<>();
                    c_car_mortgage_info.put("car_id",carId);
                    c_car_mortgage_info.put("name","null");
                    c_car_mortgage_info.put("cert_id","null");
                    c_car_mortgage_info.put("reg_date","null");
                    c_car_mortgage_infoList.add(c_car_mortgage_info);
                }
            }
        }
        Dept dept = deptMapper.selectById(applyInfo.getDeptId());
        machineRiskControlMap.put("dept",dept);
        machineRiskControlMap.put("c_apply_info",c_apply_info);
        machineRiskControlMap.put("c_bankcard_info",c_bankcard_info);
        machineRiskControlMap.put("c_car_info",c_car_info);
        machineRiskControlMap.put("c_car_price_info",c_car_price_info);
        machineRiskControlMap.put("c_channel_info",c_channel_info);
        machineRiskControlMap.put("c_credit_auth_info",c_credit_auth_info);
        machineRiskControlMap.put("c_marry_info",c_marry_info);
        machineRiskControlMap.put("c_judicial_auth_info",c_judicial_auth_info);
        machineRiskControlMap.put("c_id_auth_info",c_id_auth_info);
        machineRiskControlMap.put("c_gongjie_info",c_gongjie_info);
        machineRiskControlMap.put("c_family_book_info",c_family_book_info);
        machineRiskControlMap.put("c_cust_info",c_cust_info);
        machineRiskControlMap.put("c_cust_income_info",c_cust_income_info);
        machineRiskControlMap.put("c_family_book_sub_info",c_family_book_sub_infoList);
        machineRiskControlMap.put("c_cust_finance_info",c_cust_finance_infoList);
        machineRiskControlMap.put("c_cust_company_info",c_cust_company_info);
        machineRiskControlMap.put("c_credit_report",c_credit_report);
        machineRiskControlMap.put("c_credit_personal_query_record",c_credit_personal_query_recordList);
        machineRiskControlMap.put("c_credit_loan_detail",c_credit_loan_detailList);
        machineRiskControlMap.put("c_credit_card_detail",c_credit_card_detailList);
        machineRiskControlMap.put("c_credit_buss_query_record",c_credit_buss_query_recordlList);
        machineRiskControlMap.put("c_car_verify_info",c_car_verify_info);
        machineRiskControlMap.put("c_car_traffic_insure_info",c_car_traffic_insure_info);
        machineRiskControlMap.put("c_car_peccancy_info",c_car_peccancy_info);
        machineRiskControlMap.put("c_car_driver_info",c_car_driver_info);
        machineRiskControlMap.put("c_car_buss_insure_info",c_car_buss_insure_info);
        machineRiskControlMap.put("c_car_transfer_info",c_car_transfer_infoList);
        machineRiskControlMap.put("c_car_mortgage_info",c_car_mortgage_infoList);
        machineRiskControlMap.put("c_car_insure_detail_info",c_car_insure_detail_infoList);
        JSONObject jSONObject=(JSONObject)JSONObject.toJSON(machineRiskControlMap);
        Map map =new HashMap();
        //先做准入判断,拿到200进行下一流程节点,否则驳回到面审提交之后的状态,既面审主管待处理
        map.put("nationality","1");
        if(((Map) jSONObject.get("c_cust_info")).get("cert_id")!="null"&&((Map) jSONObject.get("c_cust_info")).get("cert_id")!=""&&((Map) jSONObject.get("c_cust_info")).get("cert_id")!=null){
            int age=IdNOToAge(((Map) jSONObject.get("c_cust_info")).get("cert_id").toString());
            if(age<18){
                map.put("age","1");
            }else if(age>=18&&age<=65){
                map.put("age","2");
            }else{
                map.put("age","3");
            }
        }
        if(((Map) jSONObject.get("c_cust_info")).get("nation")!="null"&&((Map) jSONObject.get("c_cust_info")).get("nation")!=""&&((Map) jSONObject.get("c_cust_info")).get("nation")!=null){
            map.put("nation",((Map) jSONObject.get("c_cust_info")).get("nation")=="汉族"?"2":"1");
        }
        if(((Map) jSONObject.get("c_car_driver_info")).get("is_self")!="null"&&((Map) jSONObject.get("c_car_driver_info")).get("is_self")!=""&&((Map) jSONObject.get("c_car_driver_info")).get("is_self")!=null){
            map.put("driving_age",((Map) jSONObject.get("c_car_driver_info")).get("is_self")=="0"?"1":"2");
        }
        if(machineRiskControlCarTramsferImfo.size()>0){
            map.put("transfer_number",((List) jSONObject.get("c_car_transfer_info")).size()>3?"5":(((List) jSONObject.get("c_car_transfer_info")).size()+1)+"");
        }else{
            map.put("transfer_number","1");
        }
        if(machineRiskControlCarMortgageInfo.size()>0){
            map.put("pledge_number",((List) jSONObject.get("c_car_mortgage_info")).size()>3?"5":(((List) jSONObject.get("c_car_transfer_info")).size()+1)+"");
        }else{
            map.put("pledge_number","1");
        }
        Map<String,Object> map1 = new HashMap();
        Map<String,Object> map2 = new HashMap();
        map1= (Map) jSONObject.get("c_car_traffic_insure_info");
        map1.remove("car_id");
        map2.putAll(map1);
        for(String key : map2.keySet()){
            if("null".equals(map2.get(key))||map2.get(key)==null){
                map1.remove(key);
            }
        }

        map.put("ctal_insurance",map1.size()==0?"1":"2");
        Map<String,Object> map3 = new HashMap();
        Map<String,Object> map4 = new HashMap();
        map3=(Map) jSONObject.get("c_car_buss_insure_info");
        map3.remove("car_id");
        map4.putAll(map3);
        for(String key : map4.keySet()){
            if("null".equals(map4.get(key))||map4.get(key)==null){
                map3.remove(key);
            }
        }
        map.put("commercial_insurance",map3.size()==0?"1":"2");
        if(((Map) jSONObject.get("c_car_verify_info")).get("car_cond")!="null"&&((Map) jSONObject.get("c_car_verify_info")).get("car_cond")!=""&&((Map) jSONObject.get("c_car_verify_info")).get("car_cond")!=null){
            map.put("car_condition",((Map) jSONObject.get("c_car_verify_info")).get("car_cond")=="0"?"2":"1");
        }
        if(((Map) jSONObject.get("c_car_verify_info")).get("car_assessment_price")!="null"&&((Map) jSONObject.get("c_car_verify_info")).get("car_assessment_price")!=""&&((Map) jSONObject.get("c_car_verify_info")).get("car_assessment_price")!=null){
            String car_verify_info=((Map) jSONObject.get("c_car_verify_info")).get("car_assessment_price").toString().replace(".00","");
            if(Long.valueOf(car_verify_info)/10000<5){
                map.put("cur_eval_price","1");
            }else if(Long.valueOf(car_verify_info)/10000>=5&&Long.valueOf(car_verify_info)/10000<=10){
                map.put("cur_eval_price","2");
            } else if(Long.valueOf(car_verify_info)/10000>10&&Long.valueOf(car_verify_info)/10000<=30){
                map.put("cur_eval_price","3");
            }else{
                map.put("cur_eval_price","4");
            }
        }
        if(((Map)((List)machineRiskControlMap.get("c_credit_loan_detail")).get(0)).get("five_class_status").toString()!="null"
                &&!"".equals(((Map)((List)machineRiskControlMap.get("c_credit_loan_detail")).get(0)).get("five_class_status").toString())&&((Map)((List)machineRiskControlMap.get("c_credit_loan_detail")).get(0)).get("five_class_status").toString()!=null){
            map.put("credit_level",(Integer.valueOf(((Map)((List)machineRiskControlMap.get("c_credit_loan_detail")).get(0)).get("five_class_status").toString())+1)+"");
        }
        if(((Map) jSONObject.get("c_judicial_auth_info")).get("court_personal")!="null"
                &&((Map) jSONObject.get("c_judicial_auth_info")).get("court_personal")!=""&&((Map) jSONObject.get("c_judicial_auth_info")).get("court_personal")!=null){
            map.put("judicial_auth",(Integer.valueOf(((Map) jSONObject.get("c_judicial_auth_info")).get("court_personal").toString())+1)+"");
        }
        String result="";
        JSONObject jSONObject1=(JSONObject)JSONObject.toJSON(map);
        //准入接口返回结果：{"status":200}    或者  具体信息json格式
        //传输数据接口返回结果：成功：{"status":200}  失败： {"status":500}
        if(map.size()==12){
            //String admittanceUrl = "http://10.3.1.4:8089/ enterCarloan";//测试服务器环境
            //String admittanceUrl = "http://10.1.2.114:8089/enterCarloan";//刘的本地路径// 拼接好json串,调用准入接口
            String admittanceUrl ="http://10.1.2.114:8085/enterTrueOrFalse";//准入以此为准 测试地址
            //String admittanceUrl =" http://10.3.1.4:8085/enterTrueOrFalse";//上线地址
            result=invokeAntifraud(admittanceUrl,jSONObject1.toJSONString());
        }else{
            //驳回到面审主管,既面审提交状态

        }
        if("200".equals(((Map)JSON.parseObject(result)).get("status"))||true){//为了前期和大数据联调加的||true,后期去掉
            //String url = "http://10.3.1.4:8086/getcarloaninfo";//测试服务器环境
            //String url = "http://10.1.2.114:8086/getcarloaninfo";//刘的本地路径// 拼接好json串,调用反欺诈接口
            // 数据传输接口以此为准
            String url ="http://10.1.2.114:8086/getcarloaninfo";// 测试地址
            //String url ="http://10.3.1.4:8086/getcarloaninfo";//上线地址
            result=invokeAntifraud(url,jSONObject.toJSONString());
            if("200".equals(((Map)JSON.parseObject(result)).get("status"))||true){//为了前期和大数据联调加的||true,后期去掉
                //调用得分接口
            }else{
                // 驳回到面审主管,既面审提交状态
            }
        }else{
            // 驳回到面审主管,既面审提交状态
        }*/
        //处理机器风控流程
        return processProc(null, applyId, null, ProcessType.SUCCESS.getValue(), null);
    }
    //根据身份证号得到年龄
    public static int IdNOToAge(String IdNO){
        int leh = IdNO.length();
        String dates="";
        if (leh == 18) {
            dates = IdNO.substring(6, 10);
        }else{
            dates = IdNO.substring(6, 8);
            dates = "19"+dates;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String year=df.format(new Date());
        int u=Integer.parseInt(year)-Integer.parseInt(dates);
        return u;

    }
    private static String invokeAntifraud( String url,String jSONObject){

        String result = "";
        BufferedReader reader = null;
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            //设置常规参数
            conn.setRequestMethod("POST");
            //发送post请求必须的两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTf-8");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("accept", "application/json");
            conn.setRequestProperty("accept", "*/*");
            conn.disconnect();
            //获取URlConnection对应的输出流
            if (jSONObject != null && !TextUtils.isEmpty(jSONObject)) {
                byte[] writebytes = jSONObject.getBytes();
                // 设置文件长度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(jSONObject.getBytes());
                outwritestream.flush();
                outwritestream.close();
            }
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                result = reader.readLine();
            }
        }catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
           return result;
    }
    /**
     * 首次放款
     */
    private ResultVO<Object> PayToCustomer(Long applyId){

        //TODO 进行放款


        ResultVO ret = processProc(null, applyId, null, ProcessType.SUCCESS.getValue(), null);
        if(!ret.isSuccess()){
            return ret;
        }

        //TODO 前期费用扣取

        ret = processProc(null, applyId, null, ProcessType.SUCCESS.getValue(), null);
        if(!ret.isSuccess()){
            return ret;
        }

        //TODO 前台服务费扣取

        ret = processProc(null, applyId, null, ProcessType.SUCCESS.getValue(), null);
        if(!ret.isSuccess()){
            return ret;
        }

        return ret;
    }

    /**
     * 二次放款
     */
    private ResultVO<Object> PayToCustomerSecond(Long applyId){

        //TODO 进行二次放款


        ResultVO ret = processProc(null, applyId, null, ProcessType.SUCCESS.getValue(), null);
        if(!ret.isSuccess()){
            return ret;
        }

        //放款完成
        ret = processProc(null, applyId, null, ProcessType.SUCCESS.getValue(), null);
        if(!ret.isSuccess()){
            return ret;
        }

        return ret;
    }


    /**
     * 内勤资料录入提交（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    @Transactional
    public ResultVO<Object> nqDataSaveSubmit(Long applyId, Long operatorId, Integer result, String resultTip) {

        if(applyId == null || result == null){
            log.error("===>[nqDataSaveSubmit] param error, applyId={} result={}", applyId, result);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        if(result != ProcessType.SUCCESS.getValue()){
            log.error("===>[nqDataSaveSubmit] param error, result={}", result);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        ResultVO<Object> res=processProc(null, applyId, operatorId, result, null);
        if(res.getStatus()==0) {
        	ApplyInfo applyInfo = applyInfoService.selectById(applyId);
        	CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
        	//发短信通知面审
        	Map<String, Object> param = new HashMap<String, Object>();
            String custName = customerInfo.getName();
            //param.put("name", customerInfo.getName());
			List<SendMsgVo> mobileList = new ArrayList<>();

			boolean ret;
			String smsCode;
			//成功则通知面审
			mobileList = ParamConstants.getValue(ParamConstants.ROLE_MS);
            /**
            * ${operName} 您好！
             * ${businessName}中，您有一条单号为${applyCode},客户名称为${custName}的订单，需要您使用账号${account}登录系统处理，请知悉
            * */
			smsCode = SMSService.ALL_SUBMIT;
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",custName);
            //param.put("con", "内勤资料录入");//old code
			try{
				ret = smsService.sendMsgSMS(mobileList, smsCode, param);
				if(ret == false){
					log.error("===>>>申请单：{},通知面审，发送短信通知失败", applyInfo.getId());
				}else{
					log.info("===>>>申请单：{}, 通知面审，发送短信通知成功", applyInfo.getId());
				}
			}catch(Exception e){
				log.error("===>>>申请单：{}, 通知面审，发送短信通知异常 {}", applyInfo.getId(), e);
			}
        }
        return res;
    }

    /**
     * 验车师资料录入提交（验车师）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> ycSubmit(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || result == null){
            log.error("===>[ycSubmit] param error, applyId={} result={}", applyId, result);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        if(result != ProcessType.SUCCESS.getValue()){
            log.error("===>[ycSubmit] param error, result={}", result);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }

        ResultVO<Object> res=processProc(null, applyId, operatorId, result, null);
        if(res.getStatus()==0) {
        	ApplyInfo applyInfo = applyInfoService.selectById(applyId);
        	CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
        	//发短信通知面审
        	Map<String, Object> param = new HashMap<String, Object>();
            String custName = customerInfo.getName();
            //param.put("name", customerInfo.getName());
			List<SendMsgVo> mobileList = new ArrayList<>();

			boolean ret;
			String smsCode;
			//成功则通知面审
			mobileList = ParamConstants.getValue(ParamConstants.ROLE_MS);
            smsCode = SMSService.ALL_SUBMIT;
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",custName);
            param.put("con","验车师资料录入");
			try{
				ret = smsService.sendMsgSMS(mobileList, smsCode, param);
				if(ret == false){
					log.error("===>>>申请单：{},通知面审，发送短信通知失败", applyInfo.getId());
				}else{
					log.info("===>>>申请单：{}, 通知面审，发送短信通知成功", applyInfo.getId());
				}
			}catch(Exception e){
				log.error("===>>>申请单：{}, 通知面审，发送短信通知异常 {}", applyInfo.getId(), e);
			}
        }
        return res;
    }

    /**
     * 面审提交（面审）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> msSubmit(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || result == null){
            log.error("===>[msSubmit] param error, applyId={} result={}", applyId, result);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        if(result != ProcessType.SUCCESS.getValue()){
            log.error("===>[msSubmit] param error, result={}", result);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        //处理流程
        ResultVO<Object> ret = processProc(null, applyId, operatorId, result, resultTip);
        if(!ret.isSuccess()){
            log.error("===>[mszgSubmit] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
            return ret;
        }

        if(result != ProcessType.SUCCESS.getValue()){
            return ResultVO.build(ErrorCode.SUCCESS);
        }
        ApplyInfo applyInfo = applyInfoService.selectById(applyId);
        CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
        Map<String, Object> param = new HashMap<String, Object>();
        String custName = customerInfo.getName();
        //param.put("name", customerInfo.getName());
        List<SendMsgVo> mobileList = new ArrayList<>();

        boolean res;
        String smsCode;
        mobileList = ParamConstants.getValue(ParamConstants.ROLE_ZS);
        smsCode = SMSService.ALL_SUBMIT;
        param.put("businessName","车贷业务系统");
        param.put("applyCode",applyId);
        param.put("custName",custName);
        param.put("con","面审资料录入");
        try{
            res = smsService.sendMsgSMS(mobileList, smsCode, param);
            if(res == false){
                log.error("===>>>申请单：{},通知终审，发送短信通知失败", applyInfo.getId());
            }else{
                log.info("===>>>申请单：{}, 通知终审，发送短信通知成功", applyInfo.getId());
            }
        }catch(Exception e){
            log.error("===>>>申请单：{}, 通知终审，发送短信通知异常 {}", applyInfo.getId(), e);
        }
        return ret;
    }

    /**
     * 面审主管审核（面审主管）
     * @param applyId
     * @param operatorId
     * @param result
     * @param resultTip
     * @return
     */
    @Override
    public ResultVO<Object> mszgSubmit(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || result == null){
            log.error("===>[mszgSubmit] param error, applyId={} result={}", applyId, result);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        if(result != ProcessType.SUCCESS.getValue() && result != ProcessType.FAIL.getValue() && result != ProcessType.BACK.getValue()){
            log.error("===>[mszgSubmit] param error, result={}", result);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }

        try{

            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, result, resultTip);
            if(!ret.isSuccess()){
                log.error("===>[mszgSubmit] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }

            if(result != ProcessType.SUCCESS.getValue()){
                return ResultVO.build(ErrorCode.SUCCESS);
            }
            
            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
        	CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
        	//发短信通知面审
        	Map<String, Object> param = new HashMap<String, Object>();
            String custName = customerInfo.getName();
            //param.put("name", customerInfo.getName());
			List<SendMsgVo> mobileList = new ArrayList<>();

			boolean res;
			String smsCode;
			//成功则通知终审
			mobileList = ParamConstants.getValue(ParamConstants.ROLE_ZS);
            smsCode = SMSService.ALL_SUBMIT;
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",custName);
            param.put("con", "面审主管审核资料");
			try{
				res = smsService.sendMsgSMS(mobileList, smsCode, param);
				if(res == false){
					log.error("===>>>申请单：{},通知终审，发送短信通知失败", applyInfo.getId());
				}else{
					log.info("===>>>申请单：{}, 通知终审，发送短信通知成功", applyInfo.getId());
				}
			}catch(Exception e){
				log.error("===>>>申请单：{}, 通知终审，发送短信通知异常 {}", applyInfo.getId(), e);
			}
            
            //调用机器风控接口继续后续操作
            try {
				callAutoRisk(applyId);
			} catch (Exception e) {
				log.error("===>>>申请单：{}, 机器风控调用异常 {}", applyInfo.getId(), e);
				e.printStackTrace();
			}
			return ResultVO.build(ErrorCode.SUCCESS); 

        }catch(Exception e){
            log.error("===>[mszgSubmit] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }

    }

    /**
     * 终审审核（终审）
     * @param
     * @param
     * @param
     * @param
     * @return
     */
    @Override
    public ResultVO<Object> zsSubmit(Map<String ,Object> params) {
        Object applyIdObj = params.get("applyId");
        Object resultObj = params.get("judgementResult");

        if (ToolUtil.isEmpty(applyIdObj)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        if (ToolUtil.isEmpty(resultObj)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        Long applyId = Long.parseLong(applyIdObj.toString());
        Integer result = Integer.parseInt(resultObj.toString());
        String rejectionReason = params.get("rejectionReason").toString();
        String remark = params.get("remark").toString();

        if(applyId == null || result == null){
            log.error("===>[zsSubmit] param error, applyId={} result={}", applyId, result);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        if(result != ProcessType.SUCCESS.getValue() && result != ProcessType.FAIL.getValue() && result != ProcessType.BACK.getValue()){
            log.error("===>[zsSubmit] param error, result={}", result);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        ApplyInfo applyInfo = applyInfoService.selectById(applyId);
        if (ToolUtil.isEmpty(applyInfo)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        ShiroUser shiroUser = ShiroKit.getUser();
        Long operatorId=Long.valueOf(shiroUser.getId());

        //将信息存入到终审信息表中
        Map<String,Object> param = new HashMap<>();
        param.put("applyId",applyId);
        FinalJudgementInfo finalJudgementInfo = finalJudgementInfoService.selectByApplyId(applyId);
        if(null == finalJudgementInfo){
            finalJudgementInfo = new FinalJudgementInfo();
        }

        
        finalJudgementInfo.setApplyId(applyId);
        finalJudgementInfo.setCustId(applyInfo.getCustId());
        finalJudgementInfo.setJudgementResult(result);
        finalJudgementInfo.setAuditTime(new Date());
        finalJudgementInfo.setRemark(remark);
        String resultTip ="";
         if(result==2){//补充资料驳回到面审
            finalJudgementInfo.setRejectionReason(rejectionReason);
             finalJudgementInfoService.saveOrUpdate(finalJudgementInfo);
            //修改订单状态
            applyInfo.setStatus(1000);
            applyInfo.setStatusDesc("新增客户");
            applyInfoService.updateWithOutNull(applyInfo);
             resultTip=rejectionReason;

             Date now = new Date();

             // 写入借款操作员表
             ApplyOperator applyOperator = new ApplyOperator();
             applyOperator.setApplyId(applyId);
             applyOperator.setRoleId((long)shiroUser.getRoleList().get(0));
             applyOperator.setUserId(operatorId);
             applyOperator.setProcessNodeId(10009L);
             applyOperator.setCreateTime(now);
             applyOperator.setUpdateTime(now);
             applyOperator.setIsDeleted(0);
             applyOperatorDao.save(applyOperator);

             // 写入审批记录表
             MainApproveRecord record = new MainApproveRecord();

             record.setApplyId(applyId);
             record.setOperatorId(operatorId);
             record.setOperatorName(shiroUser==null?null:shiroUser.getName());
             record.setOperatorTime(now);
             record.setProcessNodeId(10009L);
             record.setProcessNodeDesc("终审驳回，补充资料");
             record.setAuditRemark(resultTip);
             record.setIsDeleted(0);
             record.setCreateTime(now);
             record.setUpdateTime(now);
             mainApproveRecordService.save(record);

        }else if(result==0){//流程通过
             finalJudgementInfo.setLoanPeriod(Integer.parseInt(params.get("loanPeriod").toString()));
             finalJudgementInfo.setLoanAmount(BigDecimal.valueOf(Long.parseLong(params.get("loanAmount").toString())));
             resultTip = finalJudgementInfo.getRemark();

             finalJudgementInfoService.saveOrUpdate(finalJudgementInfo);
             //处理流程
             ResultVO<Object> ret = processProc(null, applyId, operatorId, result, resultTip);
             if(!ret.isSuccess()){
                 log.error("===>[zsSubmit] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                 return ret;
             }
         }else{//拒绝
             finalJudgementInfo.setRejectionReason(rejectionReason);
             finalJudgementInfoService.saveOrUpdate(finalJudgementInfo);
             resultTip=rejectionReason;
             ResultVO<Object> ret = processProc(null, applyId, operatorId, result, resultTip);
             if(!ret.isSuccess()){
                 log.error("===>[zsSubmit] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                 return ret;
             }
         }

    	CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
		//param.put("name", customerInfo.getName());
		List<SendMsgVo> mobileList = new ArrayList<>();
        param.put("con", "终审审核");
        param.put("businessName","车贷业务系统");
        param.put("applyCode",applyId);
        param.put("custName",customerInfo.getName());
		boolean res;
		String smsCode;
        if(result==2||result==1) {//补充资料驳回到面审或者是拒绝
			mobileList = ParamConstants.getValue(ParamConstants.ROLE_MS);
            smsCode = SMSService.ALL_SUBMIT;
        }else {
            mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
            smsCode = SMSService.ALL_SUBMIT;
        }
        try{
			res = smsService.sendMsgSMS(mobileList, smsCode, param);
			if(res == false){
				log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
			}else{
				log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
			}
		}catch(Exception e){
			log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
		}
        return ResultVO.build(ErrorCode.SUCCESS);
    }

    /**
     * 签署合同（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> signContract(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null || result == null){
            log.error("===>[signContract] param error, applyId={} operatorId={} result={}", applyId, operatorId, result);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }

        try{
            //处理流程
            ResultVO<Object> ret = processProc(1, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[signContract] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            //param.put("name", customerInfo.getName());
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "内勤签订合同");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
            String smsCode = SMSService.ALL_SUBMIT;

             boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[signContract] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 安装GPS申请（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> gpsInstallApply(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[gpsInstallApply] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(0, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[gpsInstallApply] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            //param.put("name", customerInfo.getName());
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "内勤申请GPS安装");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_YCS);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[gpsInstallApply] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 安装GPS完成（验车师）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> gpsInstallComplete(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[gpsInstallComplete] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[gpsInstallComplete] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            // param.put("name", customerInfo.getName());
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "验车师GPS安装完成");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_MSZG);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[gpsInstallComplete] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * GPS安装完成确认（面审主管）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> gpsInstallConfirm(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[gpsInstallConfirm] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        
        if(result==2){//驳回直接驳回到内勤，状态改为 6000 ，内勤驳回
            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            applyInfo.setGpsInstallStatus(2);
            applyInfo.setUpdateTime(new Date());
            applyInfoService.updateWithOutNull(applyInfo);

            ShiroUser shiroUser = ShiroKit.getUser();
            Date now = new Date();
            // 写入借款操作员表
            ApplyOperator applyOperator = new ApplyOperator();
            applyOperator.setApplyId(applyId);
            applyOperator.setRoleId(3L);
            applyOperator.setUserId(operatorId);
            applyOperator.setProcessNodeId(10023L);
            applyOperator.setCreateTime(now);
            applyOperator.setUpdateTime(now);
            applyOperator.setIsDeleted(0);
            applyOperatorDao.save(applyOperator);

            // 写入审批记录表
            MainApproveRecord record = new MainApproveRecord();

            record.setApplyId(applyId);
            record.setOperatorId(operatorId);
            record.setOperatorName(shiroUser.getName());
            record.setOperatorTime(now);
            record.setProcessNodeId(0L);
            record.setProcessNodeDesc("面审主管驳回");
            record.setAuditRemark(resultTip);
            record.setIsDeleted(0);
            record.setCreateTime(now);
            record.setUpdateTime(now);
            mainApproveRecordService.save(record);

            return ResultVO.build(ErrorCode.SUCCESS);

        }
        
        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[gpsInstallConfirm] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            // param.put("name", customerInfo.getName());
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "面审主管GPS安装完成确认");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[gpsInstallConfirm] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 抵押申请（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> mortgageCarApply(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[mortgageCarApply] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
            //处理流程
        	
            //ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            /*if(!ret.isSuccess()){
                log.error("===>[mortgageCarApply] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }*/
        	

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            applyInfo.setStatus(7100);
            applyInfo.setStatusDesc("抵押申请");
            applyInfoService.updateWithOutNull(applyInfo);
            
            Date now=new Date();
            User user = null;
            if(operatorId != null){
                user = userMapper.selectById(operatorId);
            }
            
            // 写入操作员表
            ApplyOperator applyOperator = new ApplyOperator();
            applyOperator.setApplyId(applyId);
            applyOperator.setRoleId(8L);
            applyOperator.setUserId(operatorId);
            applyOperator.setProcessNodeId(10016L);
            applyOperator.setCreateTime(now);
            applyOperator.setUpdateTime(now);
            applyOperator.setIsDeleted(0);
            applyOperatorDao.save(applyOperator);

            // 写入审批记录表
            MainApproveRecord record = new MainApproveRecord();

            record.setApplyId(applyId);
            record.setOperatorId(operatorId);
            record.setOperatorName(user==null?null:user.getName());
            record.setOperatorTime(now);
            record.setProcessNodeId(10016L);
            record.setProcessNodeDesc("抵押申请");
            record.setAuditRemark(resultTip);
            record.setIsDeleted(0);
            record.setCreateTime(now);
            record.setUpdateTime(now);
            mainApproveRecordService.save(record);
            
            
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            //param.put("name", customerInfo.getName());
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "内勤提交抵押申请");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_DYZY);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[mortgageCarApply] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 确认抵押申请材料（抵押专员）
     * @param applyId
     * @param operatorId
     * @param result
     * @param resultTip
     * @return
     */
    @Override
    public ResultVO<Object> mortgageCarConfirmApply(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[mortgageCarConfirmApply] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        if(result != ProcessType.SUCCESS.getValue() && result != ProcessType.FAIL.getValue() && result != ProcessType.BACK.getValue()){
            log.error("===>[mortgageCarConfirmApply] param error, result={}", result);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, result, null);
            if(!ret.isSuccess()){
                log.error("===>[mortgageCarConfirmApply] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            //param.put("name", customerInfo.getName());
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "抵押专员确认抵押申请材料");

            List<SendMsgVo>  mobileList = new ArrayList<>();
            String smsCode ="" ;
            if(result==0){// 通过
                mobileList = ParamConstants.getValue(ParamConstants.ROLE_DYZY);
                smsCode = SMSService.ALL_SUBMIT;
            }else if(result==2){//驳回
                mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
                smsCode = SMSService.ALL_SUBMIT;
            }

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[mortgageCarConfirmApply] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 接收资料确认（抵押专员）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> mortgageCarConfirmPaper(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[mortgageCarConfirmPaper] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[mortgageCarConfirmPaper] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }
            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            //param.put("name", customerInfo.getName());
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "抵押专员接收资料确认");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_DYZY);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[mortgageCarConfirmPaper] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 抵押受理（抵押专员）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> mortgageCarBussHanding(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[mortgageCarBussHanding] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[mortgageCarBussHanding] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            //param.put("name", customerInfo.getName());
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "抵押专员抵押受理");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_DYZY);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[mortgageCarBussHanding] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 抵押办理完成（抵押专员）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> mortgageCarBussFinish(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[mortgageCarBussFinish] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[mortgageCarBussFinish] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            //param.put("name", customerInfo.getName());
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "抵押专员抵押办理完成");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[mortgageCarBussFinish] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 资料存留（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> dataKeep(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[dataKeep] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }

        if(result==2){//驳回直接驳回到内勤，状态改为 6000 ，内勤驳回
            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            applyInfo.setStatus(7100);
            applyInfo.setStatusDesc("抵押申请");
            applyInfo.setUpdateTime(new Date());
            applyInfoService.updateWithOutNull(applyInfo);

            ShiroUser shiroUser = ShiroKit.getUser();
            Date now = new Date();
            // 写入借款操作员表
            ApplyOperator applyOperator = new ApplyOperator();
            applyOperator.setApplyId(applyId);
            applyOperator.setRoleId(3L);
            applyOperator.setUserId(operatorId);
            applyOperator.setProcessNodeId(10023L);
            applyOperator.setCreateTime(now);
            applyOperator.setUpdateTime(now);
            applyOperator.setIsDeleted(0);
            applyOperatorDao.save(applyOperator);

            // 写入审批记录表
            MainApproveRecord record = new MainApproveRecord();

            record.setApplyId(applyId);
            record.setOperatorId(operatorId);
            record.setOperatorName(shiroUser.getName());
            record.setOperatorTime(now);
            record.setProcessNodeId(10023L);
            record.setProcessNodeDesc("内勤驳回");
            record.setAuditRemark(resultTip);
            record.setIsDeleted(0);
            record.setCreateTime(now);
            record.setUpdateTime(now);
            mainApproveRecordService.save(record);

            return ResultVO.build(ErrorCode.SUCCESS);

        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[dataKeep] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            //param.put("name", customerInfo.getName());
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "内勤资料留存");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQZG);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[dataKeep] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 放款申请（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public ResultVO<Object> payApply(Long applyId,
    		Long operatorId, Integer result,
    		String receptionDepart,String receptionManager,
    		String bankCardNo, String bankName,
    		String custMobile,String receptionAmount,
    		String isPerCharge,String isReplaceCost,
    		String resultTip) {
        if(applyId == null || operatorId == null ){
            log.error("===>[payApply] param error, applyId={} operatorId={} ", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{

            //创建放款记录
            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo = customerInfoService.selectById(applyInfo.getCustId());
            //TODO 获取合同信息
            ContractInfo contractNo = contractInfoService.selectByApplyId(applyId);
            if(contractNo == null){
                log.error("===>[payApply] can't find contract info with applyId={}", applyId);
                return ResultVO.build(ErrorCode.NO_CONTRACT);
            }

            //TODO 获取客户银行卡信息
           /* BankcardInfo bankcardInfo = bankcardInfoService.selectByCusId(customerInfo.getId());
            if(bankcardInfo == null){
                log.error("===>[payApply] can't find bankcard info with custId={}", customerInfo.getId());
                return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
            }*/
            //TODO 获取贷款最终审批信息
            FinalJudgementInfo finalJudgementInfo = finalJudgementInfoService.selectByApplyId(applyId);
            if(finalJudgementInfo == null){
                log.error("===>[payApply] can't find finalJudgement info with applyId={}", applyId);
                return ResultVO.build(ErrorCode.NO_FINALJUDGEMENTINFO);
            }

            BigDecimal totalAmount = finalJudgementInfo.getLoanAmount();
            Integer periodNum = finalJudgementInfo.getLoanPeriod();

            
            PayInfo entity= payInfoService.selectByApplyId(applyId);
            if(entity==null) {
            	entity=new PayInfo();
            }
            Date now = new Date();
            entity.setCustName(customerInfo.getName());
            entity.setDeptId(applyInfo.getDeptId());
            entity.setCustId(customerInfo.getId());
            entity.setApplyId(applyId);
            entity.setContractNo(contractNo.getContractNo());
            entity.setCustIdNo(customerInfo.getCertId());
            entity.setCustMobile(custMobile);
            entity.setBankCardNo(bankCardNo);
            entity.setBankName(bankName);
            entity.setReceptionDepart(receptionDepart);
            entity.setReceptionManager(receptionManager);
            entity.setPayedAmount(new BigDecimal(0));
            entity.setTotalAmount(totalAmount);
            entity.setNotPayAmount(totalAmount);
            entity.setPeriodNum(periodNum);
            entity.setPayStatus(PayApproveStatus.CREATE.getValue());
            entity.setPayStatusDesc(PayApproveStatus.CREATE.getDesc());
            entity.setReceptionAmount(new BigDecimal(receptionAmount));
            entity.setIsReplaceCost(Integer.valueOf(isReplaceCost));
            entity.setIsPerCharge(Integer.valueOf(isPerCharge));
            entity.setTransSource(applyInfo.getChannelId().toString());
            entity.setUpdateTime(now);
            entity.setCreateTime(now);
            entity.setIsDeleted(0);

            payInfoService.saveOrUpdate(entity);

            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[payApply] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
                return ret;
            }
            Map<String, Object> param = new HashMap<String, Object>();
            //param.put("name", customerInfo.getName());
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQZG);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }
            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[payApply] exception: e={}", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 放款内勤主管审核（内勤主管）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public ResultVO<Object> payNqzgAudit(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[payNqzgAudit] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }

        List<SendMsgVo>  mobileList = new ArrayList<>();
        String smsCode="";
        Map<String, Object> param = new HashMap<String, Object>();
        ApplyInfo applyInfo = applyInfoService.selectById(applyId);
        CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
        //param.put("name", customerInfo.getName());
        param.put("businessName","车贷业务系统");
        param.put("applyCode",applyId);
        param.put("custName",customerInfo.getName());
        param.put("con", "内勤主管放款审核");
        try{
            //处理流程
            if(result==0){//通过
                //获取放款总表信息
                PayInfo payInfo=payInfoService.selectByApplyId(applyId);
                if(payInfo.getPayStatus() != PayApproveStatus.CREATE.getValue()&&payInfo.getPayStatus() != PayApproveStatus.BUSS_MANAGER_APPROVE_FAIL.getValue()){
                    log.error("内勤主管审批 请求状态不正确 payId={},status={}", payInfo.getId(), payInfo.getPayStatus());
                    return ResultVO.build(ErrorCode.PAY_PROCESS_ERROR);
                }

                payInfo.setPayStatus(PayApproveStatus.NQZG_APPROVE.getValue());
                payInfo.setPayStatusDesc(PayApproveStatus.NQZG_APPROVE.getDesc());
                payInfoService.update(payInfo);

                ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
                if(!ret.isSuccess()){
                    log.error("===>[payNqzgAudit] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
                    return ret;
                }
                mobileList = ParamConstants.getValue(ParamConstants.ROLE_BUSS_MA);
                smsCode = SMSService.ALL_SUBMIT;
            }else if(result==2){//驳回
                applyInfo.setStatus(8000);
                applyInfo.setStatusDesc("内勤主管驳回");
                applyInfoService.updateWithOutNull(applyInfo);

                // 写入操作员表
                ApplyOperator applyOperator = new ApplyOperator();
                applyOperator.setApplyId(applyId);
                applyOperator.setRoleId(2L);
                applyOperator.setUserId(operatorId);
                applyOperator.setProcessNodeId(10025L);
                applyOperator.setCreateTime(new Date());
                applyOperator.setUpdateTime(new Date());
                applyOperator.setIsDeleted(0);
                applyOperatorDao.save(applyOperator);

                // 写入审批记录表
                MainApproveRecord record = new MainApproveRecord();

                User user = null;
                if(operatorId != null){
                    user = userMapper.selectById(operatorId);
                }

                record.setApplyId(applyId);
                record.setOperatorId(operatorId);
                record.setOperatorName(user==null?null:user.getName());
                record.setOperatorTime(new Date());
                record.setProcessNodeId(10025L);
                record.setProcessNodeDesc("请款内勤主管审核");
                record.setAuditRemark(resultTip);
                record.setIsDeleted(0);
                record.setCreateTime(new Date());
                record.setUpdateTime(new Date());
                mainApproveRecordService.save(record);

                mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
                smsCode = SMSService.ALL_SUBMIT;
            }

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            if(result==1){//拒绝
                ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
                if(!ret.isSuccess()){
                    log.error("===>[payNqzgAudit] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
                    return ret;
                }
            }
            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[payNqzgAudit] exception: e={}", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 放款业务经理审核（业务经理）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public ResultVO<Object> payBussAudit(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[payBussAudit] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }

        ApplyInfo applyInfo = applyInfoService.selectById(applyId);
        CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());

        try{
            PayInfo payInfo=payInfoService.selectByApplyId(applyId);
            //更新payInfo表状态
            if(result == 0){//0通过
                //获取放款总表信息
                if(payInfo.getPayStatus() != PayApproveStatus.NQZG_APPROVE.getValue()&&payInfo.getPayStatus() != PayApproveStatus.FIRST_FINANCE_APPROVE_FAIL.getValue()){
                    log.error("业务经理审批 请求状态不正确 payId={},status={}", payInfo.getId(), payInfo.getPayStatus());
                    return ResultVO.build(ErrorCode.PAY_PROCESS_ERROR);
                }

				payInfo.setPayStatus(PayApproveStatus.BUSS_MANAGER_APPROVE.getValue());
				payInfo.setPayStatusDesc(PayApproveStatus.BUSS_MANAGER_APPROVE.getDesc());

                //处理流程
                ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
                if(!ret.isSuccess()){
                    log.error("===>[payBussAudit] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
                    return ret;
                }

			}else{
                applyInfo.setStatus(8000);
                applyInfo.setStatusDesc("业务经理驳回");
                applyInfoService.updateWithOutNull(applyInfo);

                // 写入操作员表
                ApplyOperator applyOperator = new ApplyOperator();
                applyOperator.setApplyId(applyId);
                applyOperator.setRoleId(9L);
                applyOperator.setUserId(operatorId);
                applyOperator.setProcessNodeId(10027L);
                applyOperator.setCreateTime(new Date());
                applyOperator.setUpdateTime(new Date());
                applyOperator.setIsDeleted(0);
                applyOperatorDao.save(applyOperator);

                // 写入审批记录表
                MainApproveRecord record = new MainApproveRecord();

                User user = null;
                if(operatorId != null){
                    user = userMapper.selectById(operatorId);
                }

                record.setApplyId(applyId);
                record.setOperatorId(operatorId);
                record.setOperatorName(user==null?null:user.getName());
                record.setOperatorTime(new Date());
                record.setProcessNodeId(10027L);
                record.setProcessNodeDesc("请款业务经理审核");
                record.setAuditRemark(resultTip);
                record.setIsDeleted(0);
                record.setCreateTime(new Date());
                record.setUpdateTime(new Date());
                mainApproveRecordService.save(record);
				payInfo.setPayStatus(PayApproveStatus.BUSS_MANAGER_APPROVE_FAIL.getValue());
				payInfo.setPayStatusDesc(PayApproveStatus.BUSS_MANAGER_APPROVE_FAIL.getDesc());
			}
			payInfo.setBussManagerId(operatorId);
			payInfo.setUpdateTime(new Date());
			payInfoService.update(payInfo);
			//3 成功则短信通知财务  失败则短信通知内勤
			Map<String, Object> param = new HashMap<String, Object>();
			//param.put("name", payInfo.getCustName());
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "放款业务经理审核");
			List<SendMsgVo> mobileList = new ArrayList<>();

			boolean res;
			String smsCode;
			if(result == 0){
				//成功则通知财务
				mobileList = ParamConstants.getValue(ParamConstants.ROLE_FINA);
                smsCode = SMSService.ALL_SUBMIT;
			}else{
				//失败则通知业务员
				mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
                smsCode = SMSService.ALL_SUBMIT;

			}
			try{
				res = smsService.sendMsgSMS(mobileList, smsCode, param);
				if(res == false){
					log.error("===>>>申请单：{}, 业务经理审核，发送短信通知失败", payInfo.getId());
				}else{
					log.info("===>>>申请单：{}, 业务经理审核，发送短信通知成功", payInfo.getId());
				}
			}catch(Exception e){
				log.error("===>>>申请单：{}, 业务经理审核，发送短信通知异常 {}", payInfo.getId(), e);
			}
            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[payBussAudit] exception: e={}", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 放款财务首次审核（财务）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public ResultVO<Object> payFinFirstAudit(Long applyId, Long operatorId, String amount, Integer result, String resultTip) {
        ApplyInfo applyInfo = applyInfoService.selectById(applyId);
        CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
        if(result==2){//驳回
            applyInfo.setStatus(8000);
            applyInfo.setStatusDesc("财务驳回");
            applyInfoService.updateWithOutNull(applyInfo);

            // 写入操作员表
            ApplyOperator applyOperator = new ApplyOperator();
            applyOperator.setApplyId(applyId);
            applyOperator.setRoleId(11L);
            applyOperator.setUserId(operatorId);
            applyOperator.setProcessNodeId(10029L);
            applyOperator.setCreateTime(new Date());
            applyOperator.setUpdateTime(new Date());
            applyOperator.setIsDeleted(0);
            applyOperatorDao.save(applyOperator);

            // 写入审批记录表
            MainApproveRecord record = new MainApproveRecord();

            User user = null;
            if(operatorId != null){
                user = userMapper.selectById(operatorId);
            }

            record.setApplyId(applyId);
            record.setOperatorId(operatorId);
            record.setOperatorName(user==null?null:user.getName());
            record.setOperatorTime(new Date());
            record.setProcessNodeId(10029L);
            record.setProcessNodeDesc("请款财务首次审核");
            record.setAuditRemark(resultTip);
            record.setIsDeleted(0);
            record.setCreateTime(new Date());
            record.setUpdateTime(new Date());
            mainApproveRecordService.save(record);



            Map<String, Object> param = new HashMap<String, Object>();
            //param.put("name", customerInfo.getName());
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "请款财务首次审核");

           List mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
           String smsCode = SMSService.ALL_SUBMIT;


        try{
           boolean   res = smsService.sendMsgSMS(mobileList, smsCode, param);
            if(res == false){
                log.error("===>>>申请单：{}, 业务经理审核，发送短信通知失败", applyInfo.getId());
            }else{
                log.info("===>>>申请单：{}, 业务经理审核，发送短信通知成功", applyInfo.getId());
            }
        }catch(Exception e){
            log.error("===>>>申请单：{}, 业务经理审核，发送短信通知异常 {}", applyInfo.getId(), e);
        }
        return ResultVO.build(ErrorCode.SUCCESS);
        }





        if(applyId == null || operatorId == null){
            log.error("===>[payFinFirstAudit] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }

        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[payFinFirstAudit] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
                return ret;
            }

	        int numFlag = 0;		//0--表示第一次放款  1--表示第二次放款
			Date now = new Date();

			//获取放款总表信息
	    	PayInfo payInfo=payInfoService.selectByApplyId(applyId);
	    	if (payInfo == null) {
	    		log.error("找不到该单号{}的放款总表", applyId);
	    		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
				return ResultVO.build(ErrorCode.PAY_INFO_NOT_EXIST);
			}

	    	BigDecimal payMoney = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);


			if(payInfo.getPayedAmount().compareTo(BigDecimal.ZERO) > 0){
				numFlag = 1;
			}


			if(numFlag == 0){
				//TODO 这里暂定1500块
				if(payMoney.compareTo(new BigDecimal(1500)) < 0 || payMoney.compareTo(payInfo.getTotalAmount()) >= 0){
					log.error("首次放款金额不正确 amount={}", amount);
					 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
					return ResultVO.build(ErrorCode.PAY_MONEY_ERROR);
				}
			}else{
				if (payMoney.compareTo(payInfo.getNotPayAmount()) != 0) {
					log.error("二次放款输入金额不等于未放款金额 payId={} 输入金额={} 未放款金额={}", payInfo.getId(), payMoney, payInfo.getNotPayAmount());
					 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
					return ResultVO.build(ErrorCode.PAY_MONEY_ERROR);
				}
			}

			if ((payInfo.getPayStatus() != PayApproveStatus.BUSS_MANAGER_APPROVE.getValue()) &&
					(payInfo.getPayStatus() != PayApproveStatus.CONFIRM_FEE.getValue()) &&
					(payInfo.getPayStatus() != PayApproveStatus.FIRST_PAY_FAIL.getValue()) &&
					(payInfo.getPayStatus() != PayApproveStatus.SECOND_PAY_FAIL.getValue())) {
				log.error("放款状态不正确 payId={} status={}", payInfo.getId(), payInfo.getPayStatus());
				 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
				return ResultVO.build(ErrorCode.PAY_PROCESS_ERROR);
			}

			//查询放款详情表中是否存在支付中的信息
			PayDetailInfo paying = payDetailInfoService.selectByPayIdAndStatus(payInfo.getId(), PayStatus.PAYING.getValue());
			if (paying != null) {
				log.error("存在放款中的订单 payId={}", payInfo.getId());
				 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
				return ResultVO.build(ErrorCode.REPAYMENT_PAYING_EXIST);
			}

			if (result == 0) {
				if(numFlag == 0){
					payInfo.setPayStatus(PayApproveStatus.SECOND_FINANCE_APPROVE.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.FIRST_FINANCE_APPROVE.getDesc());
				}else{
					payInfo.setPayStatus(PayApproveStatus.SECOND_FINANCE_APPROVE.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.SECOND_FINANCE_APPROVE.getDesc());
				}
				payInfo.setApproveAmount(payMoney);
			} else {
				if(numFlag == 0){
					payInfo.setPayStatus(PayApproveStatus.FIRST_FINANCE_APPROVE_FAIL.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.FIRST_FINANCE_APPROVE_FAIL.getDesc());
				}else{
					payInfo.setPayStatus(PayApproveStatus.SECOND_FINANCE_APPROVE_FAIL.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.SECOND_FINANCE_APPROVE_FAIL.getDesc());
				}
				payInfo.setApproveAmount(new BigDecimal(0));
			}
			if(numFlag == 0) {
				payInfo.setFirstFinanceId(operatorId);
			}else{
				payInfo.setSecondFinanceId(operatorId);
			}
			payInfo.setUpdateTime(now);
			payInfoService.update(payInfo);
			
			//4 发送短信通知
			Map<String, Object> param = new HashMap<String, Object>();
            //param.put("name", payInfo.getCustName());
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "放款财务首次审核");
			List<SendMsgVo> mobileList = new ArrayList<>();

			boolean res;
			String smsCode ;
			if (result == 0) {
				//成功则通知财务主管
				mobileList = ParamConstants.getValue(ParamConstants.ROLE_FINA_MA);
				if(numFlag == 0){
					param.put("time", "一");
				}else{
					param.put("time", "二");
				}
                smsCode = SMSService.ALL_SUBMIT;
			} else {
				//失败则通知业务经理
				mobileList = ParamConstants.getValue(ParamConstants.ROLE_BUSS_MA);
				if(numFlag == 0){
					param.put("time", "一");
				}else{
					param.put("time", "二");
				}
                smsCode = SMSService.ALL_SUBMIT;
			}
			try {
				res = smsService.sendMsgSMS(mobileList, smsCode, param);
				if (res == false) {
					log.error("===>>>申请单：{}, 财务审核，发送短信通知失败", payInfo.getId());
				} else {
					log.info("===>>>申请单：{}, 财务审核，发送短信通知成功", payInfo.getId());
				}
			}catch (Exception e){
				log.error("===>>>申请单：{}, 财务审核，发送短信通知异常 {}", payInfo.getId(), e);
			}
			return ResultVO.build(ErrorCode.SUCCESS);
        }catch(Exception e){
            log.error("===>[payFinFirstAudit] exception: e={}", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 放款财务经理首次审核（财务经理）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public ResultVO<Object> payFinBussFirstAudit(Long applyId, Long operatorId, Integer result, String resultTip) {

        if(result==2){//驳回
            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            applyInfo.setStatus(8000);
            applyInfo.setStatusDesc("财务经理驳回");
            applyInfoService.updateWithOutNull(applyInfo);

            // 写入操作员表
            ApplyOperator applyOperator = new ApplyOperator();
            applyOperator.setApplyId(applyId);
            applyOperator.setRoleId(10L);
            applyOperator.setUserId(operatorId);
            applyOperator.setProcessNodeId(10031L);
            applyOperator.setCreateTime(new Date());
            applyOperator.setUpdateTime(new Date());
            applyOperator.setIsDeleted(0);
            applyOperatorDao.save(applyOperator);

            // 写入审批记录表
            MainApproveRecord record = new MainApproveRecord();

            User user = null;
            if(operatorId != null){
                user = userMapper.selectById(operatorId);
            }

            record.setApplyId(applyId);
            record.setOperatorId(operatorId);
            record.setOperatorName(user==null?null:user.getName());
            record.setOperatorTime(new Date());
            record.setProcessNodeId(10031L);
            record.setProcessNodeDesc("请款财务经理首次审核");
            record.setAuditRemark(resultTip);
            record.setIsDeleted(0);
            record.setCreateTime(new Date());
            record.setUpdateTime(new Date());
            mainApproveRecordService.save(record);

            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());

            Map<String, Object> param = new HashMap<String, Object>();
            //param.put("name", customerInfo.getName());
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "请款财务经理首次审核");

            List mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
            String smsCode = SMSService.ALL_SUBMIT;


            try{
                boolean   res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{}, 业务经理审核，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 业务经理审核，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 业务经理审核，发送短信通知异常 {}", applyInfo.getId(), e);
            }
            return ResultVO.build(ErrorCode.SUCCESS);
        }





    	int payFlag = 0;		//1--成功 2--失败 3--处理中
		int isFinished = 0;		//0--放款未完成 1--放款完成

    	if(applyId == null || operatorId == null){
            log.error("===>[payFinBussFirstAudit] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }

        //1.检查流程
        PayInfo payInfo=payInfoService.selectByApplyId(applyId);
        if((payInfo.getPayStatus() != PayApproveStatus.FIRST_FINANCE_APPROVE.getValue()) &&
				(payInfo.getPayStatus() != PayApproveStatus.SECOND_FINANCE_APPROVE.getValue())){
			log.error("请求状态不正确 payId={},status={}", payInfo.getId(), payInfo.getPayStatus());
			return ResultVO.build(ErrorCode.PAY_PROCESS_ERROR);
		}

        //2.检查首次放款金额
        BigDecimal payMoney = payInfo.getApproveAmount();
		if(payMoney.compareTo(payInfo.getNotPayAmount()) > 0){
			log.error("输入的金额 超过未放款金额 payId={} 输入金额={} 未放款金额={}", payInfo.getId(), payMoney, payInfo.getNotPayAmount());
			return ResultVO.build(ErrorCode.PAY_MONEY_ERROR);
		}

		//处理流程
        ResultVO<Object> ret = processProc(null, applyId, operatorId, result, null);
        if(!ret.isSuccess()){
            log.error("===>[payFinBussFirstAudit] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
            return ret;
        }

		//3.更新放款总表状态
		if(result == 0){
			//放款审批通过
			payInfo.setPayStatus(PayApproveStatus.FIRST_FINANCE_MANAGER_APPROVE.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.FIRST_FINANCE_MANAGER_APPROVE.getDesc());

		}else{
			//放款审批不通过
			payInfo.setPayStatus(PayApproveStatus.FIRST_FINANCE_MANAGER_APPROVE_FAIL.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.FIRST_FINANCE_MANAGER_APPROVE_FAIL.getDesc());
		}
		payInfo.setFirstFinanceManagerId(operatorId);
		payInfo.setUpdateTime(new Date());
		payInfoService.update(payInfo);

        if(result != ProcessType.SUCCESS.getValue() && result != ProcessType.FAIL.getValue() && result != ProcessType.BACK.getValue()){
            log.error("===>[payFinBussFirstAudit] param error, result={}", result);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        try{

            //4 短信通知
            //5.创建放款详细信息
            Date now = new Date();
            String sequenceNo = orderSequenceService.getTradeSequence();
			PayDetailInfo payDetailInfo = new PayDetailInfo();
			payDetailInfo.setPayId(payInfo.getId());
			payDetailInfo.setDeptId(payInfo.getDeptId());
			payDetailInfo.setPayNum(1);//第一次放款
			payDetailInfo.setCustMobile(payInfo.getCustMobile());
			payDetailInfo.setBankName(payInfo.getBankName());
			payDetailInfo.setBankCardNo(payInfo.getBankCardNo());
			payDetailInfo.setPayStatus(PayStatus.CREATE.getValue());
			payDetailInfo.setPayType(PayType.PAY_TYPE_SYS_PAY.getValue());
			payDetailInfo.setPayingNum(0);
			payDetailInfo.setSerialNo(sequenceNo);
			payDetailInfo.setOperatorId(operatorId);
			payDetailInfo.setOperatorDate(now);
			payDetailInfo.setCurPayAmount(payMoney);
			payDetailInfo.setCreateTime(now);
			payDetailInfo.setUpdateTime(now);
			payDetailInfo.setIsDeleted(0);
			payDetailInfoService.save(payDetailInfo);

			//6.调用payCenter 进行放款
			/*ResultVO<PayCenterPayResultVO> payResultVO = null;
			try{
				payResultVO = payCenterService.payMoney(sequenceNo, null, payInfo.getBankCardNo(),
						payInfo.getCustName(), payInfo.getCustMobile(), payInfo.getCustIdNo(), null, null,
						null, "id", payMoney.toString(), payInfo.getBankName());
			}catch(Exception e){
				log.error("[===>>>放款ID:{} 放款详情ID:{}，调用支付中心代付接口异常===],excp:{}", payInfo.getId(), payDetailInfo.getId(), e);
			}*/

			//更新放款详情表
			now = new Date();
			PayDetailInfo info = new PayDetailInfo();
			info.setId(payDetailInfo.getId());
			info.setUpdateTime(now);


			info.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
			payFlag = 1;
			/*if (payResultVO == null || payResultVO.getData() == null || !payResultVO.isSuccess()) {
				//失败
				info.setPayStatus(PayStatus.PAY_FAIL.getValue());
				payFlag = 2;
			}

			if (payResultVO.getData().getResultCode().equals(PayCenterCode.SUCCESS)) {
				// 代扣成功，更新支付状态
				info.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
				info.setPayChannel(payResultVO.getData().getPayPlatform());
				info.setPayCode(payResultVO.getData().getResultCode());
				info.setPayMsg(payResultVO.getData().getMessage());
				payFlag = 1;
			} else if (payResultVO.getData().getResultCode().equals(PayCenterCode.DOING)) {
				// 代扣处理中，更新支付状态为支付中
				info.setPayStatus(PayStatus.PAYING.getValue());
				info.setPayChannel(payResultVO.getData().getPayPlatform());
				info.setPayCode(payResultVO.getData().getResultCode());
				info.setPayMsg(payResultVO.getData().getMessage());
				payFlag = 3;
			} else {
				info.setPayStatus(PayStatus.PAY_FAIL.getValue());
				info.setPayChannel(payResultVO.getData().getPayPlatform());
				info.setPayCode(payResultVO.getData().getResultCode());
				info.setPayMsg(payResultVO.getData().getMessage());
				payFlag = 2;
			}*/
			payDetailInfoService.update(info);

			//更新总放款表状态
			if(payFlag == 1){
				payInfo.setNotPayAmount(payInfo.getNotPayAmount().subtract(payMoney));
				payInfo.setPayedAmount(payInfo.getPayedAmount().add(payMoney));
				payInfo.setPayStatus(PayApproveStatus.FIRST_PAY_SUCCESS.getValue());
				payInfo.setPayStatusDesc(PayApproveStatus.FIRST_PAY_SUCCESS.getDesc());
				if(payInfo.getNotPayAmount().compareTo(BigDecimal.ZERO) == 0){
					//放款完成
					payInfo.setPayStatus(PayApproveStatus.PAY_SUCCESS.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.PAY_SUCCESS.getDesc());
					isFinished = 1;//表示放款完成
				}
			}else if(payFlag == 2){
				payInfo.setPayStatus(PayApproveStatus.FIRST_PAY_FAIL.getValue());
				payInfo.setPayStatusDesc(PayApproveStatus.FIRST_PAY_FAIL.getDesc());
			}else{
				payInfo.setPayStatus(PayApproveStatus.FIRST_PAY_PAYING.getValue());
				payInfo.setPayStatusDesc(PayApproveStatus.FIRST_PAY_PAYING.getDesc());

			}
			payInfo.setUpdateTime(now);
			payInfoService.update(payInfo);

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            //param.put("name", customerInfo.getName());
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "放款财务经理首次审核");


            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_QTCW);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

			//生成还款计划 扣除前期费用
			ResultVO<Object> r = paySuccessOpt(payInfo.getId());
			return r;

        }catch(Exception e){
            log.error("===>[payFinBussFirstAudit] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    @Transactional
	public ResultVO<Object> paySuccessOpt(Long payId){
    	PayInfo payInfo=payInfoService.selectById(payId);
    	Date now = new Date();
    	log.info("对放款ID {} 生成还款计划...", payId);
    	RepaymentInfo repaymentInfo = repaymentInfoService.selectByPayId(payId);
		if(repaymentInfo != null){
			log.warn("放款ID {}  已经生成还款计划表...", payId);
		}else{
			log.info("对放款ID {} 生成还款计划...", payId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			repaymentInfoService.export(payId, payInfo.getContractNo(),payInfo.getIsPerCharge(), payInfo.getCustName(), payInfo.getCustIdNo(), payInfo.getCustMobile(),
					payInfo.getBankName(), payInfo.getBankCardNo(), payInfo.getTotalAmount().toString(), payInfo.getReceptionAmount().toString(), payInfo.getPeriodNum(),
					sdf.format(now), "19900101", 2);
		}

		// 进行前期费用扣款
		ResultVO<Map<String,String>> vo = repaymentPreFee(payId, RepaymentType.PAY_TYPE_PRE_FEE.getValue());
		if(vo.getStatus() != ErrorCode.SUCCESS.getErrCode()){
			log.error("前期扣款失败");
			return ResultVO.build(vo.getStatus(), vo.getMsg());
		}
		int repayFlag = Integer.parseInt(vo.getData().get("ret"));
		log.info("前期扣款结果为 repayFlag={}", repayFlag);

		now = new Date();
		if(repayFlag == 1){
			//成功
			payInfo.setPayStatus(PayApproveStatus.COST_SUCCESS.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.COST_SUCCESS.getDesc());
		}else if(repayFlag == 2){
			//失败
			payInfo.setPayStatus(PayApproveStatus.COST_FAIL.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.COST_FAIL.getDesc());
		}else{
			//处理中
			payInfo.setPayStatus(PayApproveStatus.COST_PAYING.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.COST_PAYING.getDesc());
		}
		payInfo.setUpdateTime(now);
		payInfoService.update(payInfo);

		if(repayFlag != 1){
			//没有扣取成功的话 就返回
			return ResultVO.build(ErrorCode.SUCCESS);
		}

		//扣取一次性手续费 如果有的话
		ResultVO<Object> r = repaymentCharge(payId);
		if(!r.isSuccess()){
			return r;
		}

		//这里表示前期费用扣取成功  继续进行前台手续费的扣除
		r = repaymentReceptionFee(payId);
		return r;
    }

    /**
	 * 进行前期费用扣款
	 * @param payId
	 * @return
	 */
	public ResultVO<Map<String,String>> repaymentPreFee(Long payId, Integer payType) {

		Map<String,String> ret = new HashMap<>();
		RepaymentInfo repaymentInfo = repaymentInfoService.selectByPayId(payId);
		if(repaymentInfo == null){
			log.error("未找到还款总表记录 payId={}", payId);
			return ResultVO.build(ErrorCode.REPAYMENT_INFO_NOT_EXIST);
		}

		// 创建自动划扣支付单
		String sequenceNo = orderSequenceService.getTradeSequence();
		Date now = new Date();

		RepaymentPayInfo repaymentPayInfo = new RepaymentPayInfo();
		repaymentPayInfo.setDeptId(repaymentInfo.getDeptId());
		repaymentPayInfo.setRepaymentId(repaymentInfo.getId());
		repaymentPayInfo.setRepaymentPlanId(null);
		repaymentPayInfo.setPayType(payType);	//标志成扣取前期费用
		repaymentPayInfo.setPayTypeDesc(RepaymentType.getEnumsByValue(payType).getDesc());
		repaymentPayInfo.setRepaymentPeriodNum(0);
		repaymentPayInfo.setSerialNo(sequenceNo);
		if(payType == RepaymentType.PAY_TYPE_RECEPTION_CHARGE_FEE.getValue()){
			repaymentPayInfo.setAmount(repaymentInfo.getReceptionAmount());
		}else if(payType == RepaymentType.PAY_TYPE_ONCE_CHARGE.getValue()){
			repaymentPayInfo.setAmount(repaymentInfo.getPredictCharge());
		}else{
			repaymentPayInfo.setAmount(repaymentInfo.getPreFee());
		}
		repaymentPayInfo.setPayStatus(PayStatus.PAYING.getValue());
		repaymentPayInfo.setPayingNum(0);
		repaymentPayInfo.setPayTime(now);
		repaymentPayInfo.setPayBank(repaymentInfo.getBankName());
		repaymentPayInfo.setPayCard(repaymentInfo.getBankCardNo());
		repaymentPayInfo.setBindingMobile(repaymentInfo.getCustMobile());
		repaymentPayInfo.setCreateTime(now);
		repaymentPayInfo.setUpdateTime(now);
		repaymentPayInfo.setIsDeleted(0);
		repaymentPayInfoService.save(repaymentPayInfo);

		/*ResultVO<PayCenterPayResultVO> payResultVO = null;
		try{
			if(payType == RepaymentType.PAY_TYPE_RECEPTION_CHARGE_FEE.getValue()) {
				payResultVO = payCenterService.deductMrlMoney(sequenceNo, null, repaymentInfo.getBankCardNo(),
						repaymentInfo.getCustName(), repaymentInfo.getCustMobile(), repaymentInfo.getCustIdNo(), null, null,
						null, "id", repaymentPayInfo.getAmount().toString(), repaymentInfo.getBankName());
			}else{
				payResultVO = payCenterService.deductMoney(sequenceNo, null, repaymentInfo.getBankCardNo(),
						repaymentInfo.getCustName(), repaymentInfo.getCustMobile(), repaymentInfo.getCustIdNo(), null, null,
						null, "id", repaymentPayInfo.getAmount().toString(), repaymentInfo.getBankName());
			}
		}catch(Exception e){
			log.error("[===>>>放款ID：{} 还款ID:{} 费用扣除，调用支付中心划扣接口异常===],excp:{}", payId, repaymentInfo.getId(), e);
			RepaymentPayInfo pay = new RepaymentPayInfo();
			pay.setId(repaymentPayInfo.getId());
			pay.setPayStatus(PayStatus.PAY_FAIL.getValue());
			pay.setUpdateTime(now);
			repaymentPayInfoService.update(pay);
			ret.put("ret", "2");
			ret.put("msg", "调用支付中心异常");
			return ResultVO.build(ErrorCode.SUCCESS, ret);
		}*/

		RepaymentPayInfo pay = new RepaymentPayInfo();
		pay.setId(repaymentPayInfo.getId());
		// 代扣成功，更新支付状态
		pay.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
		pay.setUpdateTime(now);
		repaymentPayInfoService.update(pay);
		ret.put("ret", "1");
		ret.put("msg", "成功");
		return ResultVO.build(ErrorCode.SUCCESS, ret);
		/*if (!(payResultVO != null && payResultVO.isSuccess())) {
			//失败
			pay.setPayStatus(PayStatus.PAY_FAIL.getValue());
			pay.setUpdateTime(now);
			repaymentPayInfoService.update(pay);
			ret.put("ret", "2");
			ret.put("msg", "调用支付中心异常");
			return ResultVO.build(ErrorCode.SUCCESS, ret);
		}*/

		/*if (payResultVO.getData().getResultCode().equals(PayCenterCode.SUCCESS)) {
			// 代扣成功，更新支付状态
			pay.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
			pay.setPayChannel(payResultVO.getData().getPayPlatform());
			pay.setPayCode(payResultVO.getData().getResultCode());
			pay.setPayMsg(payResultVO.getData().getMessage());
			pay.setUpdateTime(now);
			repaymentPayInfoService.update(pay);
			ret.put("ret", "1");
			ret.put("msg", "成功");
			return ResultVO.build(ErrorCode.SUCCESS, ret);
		} else if (payResultVO.getData().getResultCode().equals(PayCenterCode.DOING)) {
			// 代扣处理中，更新支付状态为支付中
			pay.setPayStatus(PayStatus.PAYING.getValue());
			pay.setPayChannel(payResultVO.getData().getPayPlatform());
			pay.setPayCode(payResultVO.getData().getResultCode());
			pay.setPayMsg(payResultVO.getData().getMessage());
			pay.setPayingNum(1);
			pay.setUpdateTime(now);
			repaymentPayInfoService.update(pay);
			ret.put("ret", "3");
			ret.put("msg", "处理中");
			return ResultVO.build(ErrorCode.SUCCESS, ret);
		} else {
			pay.setPayStatus(PayStatus.PAY_FAIL.getValue());
			pay.setPayChannel(payResultVO.getData().getPayPlatform());
			pay.setPayCode(payResultVO.getData().getResultCode());
			pay.setPayMsg(payResultVO.getData().getMessage());
			pay.setUpdateTime(now);
			repaymentPayInfoService.update(pay);
			ret.put("ret", "2");
			ret.put("msg", pay.getPayMsg());
			return ResultVO.build(ErrorCode.SUCCESS, ret);
		}*/
	}

	/**
	 * 收取一次性手续费
	 * @param payId
	 * @return
	 */
	@Transactional
	public ResultVO<Object> repaymentCharge(Long payId){
		Date now = new Date();
		PayInfo payInfo=payInfoService.selectById(payId);

		if(payInfo.getIsPerCharge() == 0){
			//这里如果是分期手续费的话，则直接返回
			return ResultVO.build(ErrorCode.SUCCESS);
		}

		ResultVO<Map<String,String>> vo = repaymentPreFee(payId, RepaymentType.PAY_TYPE_ONCE_CHARGE.getValue());
		if(vo.getStatus() != ErrorCode.SUCCESS.getErrCode()){
			log.error("一次性手续费扣款失败");
			return ResultVO.build(vo.getStatus(), vo.getMsg());
		}

		int repayFlag = Integer.parseInt(vo.getData().get("ret"));
		log.info("一次性手续费扣款结果为 repayFlag={}", repayFlag);

		if(repayFlag == 1){
			//成功
			payInfo.setPayStatus(PayApproveStatus.COST_ONCE_CHARGE_SUCCESS.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.COST_ONCE_CHARGE_SUCCESS.getDesc());
		}else if(repayFlag == 2){
			//失败
			payInfo.setPayStatus(PayApproveStatus.COST_ONCE_CHANGE_FAIL.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.COST_ONCE_CHANGE_FAIL.getDesc());
		}else{
			//处理中
			payInfo.setPayStatus(PayApproveStatus.COST_ONCE_CHANGE_PAYING.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.COST_ONCE_CHANGE_PAYING.getDesc());
		}
		payInfo.setUpdateTime(now);
		payInfoService.update(payInfo);

		//10 短信通知
		boolean ret;
		ArrayList<SendMsgVo> mobileList = new ArrayList<>();
		Map<String, Object> param = new HashMap<String, Object>();
        ApplyInfo applyInfo = applyInfoService.selectById(payInfo.getApplyId());
        CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
        param.put("businessName","车贷业务系统");
        param.put("applyCode",payInfo.getApplyId());
        param.put("custName",customerInfo.getName());
		//param.put("name", payInfo.getCustName());
		//List<String> m1 = ParamConstants.getValue(ParamConstants.ROLE_BUSS);
		List<SendMsgVo> m2 = ParamConstants.getValue(ParamConstants.ROLE_FINA);
		List<SendMsgVo> m3 = ParamConstants.getValue(ParamConstants.ROLE_FINA_MA);
		//List<String> m4 = ParamConstants.getValue(ParamConstants.ROLE_DEVE);
		//List<String> m5 = ParamConstants.getValue(ParamConstants.ROLE_FINA_CD);
		if (repayFlag == 1) {
			param.put("result", "成功");
			//mobileList.addAll(m1);
			mobileList.addAll(m2);
			mobileList.addAll(m3);
			//mobileList.addAll(m5);

		} else if(repayFlag == 2){
			param.put("result", "失败");
			//mobileList.addAll(m1);
			mobileList.addAll(m2);
			mobileList.addAll(m3);
			//mobileList.addAll(m4);
			//mobileList.addAll(m5);
		}
		else{
			param.put("result", "正在处理");
			//mobileList.addAll(m1);
			mobileList.addAll(m2);
			mobileList.addAll(m3);
			//mobileList.addAll(m5);
		}
		try {
			ret = smsService.sendMsgSMS(mobileList,SMSService.ALL_SUBMIT, param);
			if (ret == false) {
				log.error("===>>>申请单：{}, 收取一次性手续费，发送短信通知失败", payId);
			} else {
				log.info("===>>>申请单：{}, 收取一次性手续费，发送短信通知成功", payId);
			}
		}catch(Exception e){
			log.error("===>>>申请单：{}, 收取一次性手续费，发送短信通知异常 {}", payId, e);
		}

		return ResultVO.build(ErrorCode.SUCCESS);
	}

	/**
	 * 收取前台手续费
	 * @param payId
	 * @return
	 */
	@Transactional
	public ResultVO<Object> repaymentReceptionFee(Long payId){
		Date now = new Date();
		PayInfo payInfo=payInfoService.selectById(payId);

		if(payInfo.getIsReplaceCost() == 0){
			//这里如果是不进行代收前台手续费，则只更新状态即可返回
			payInfo.setPayStatus(PayApproveStatus.CONFIRM_FEE.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.CONFIRM_FEE.getDesc());
			payInfo.setUpdateTime(now);
			payInfoService.update(payInfo);
			return ResultVO.build(ErrorCode.SUCCESS);
		}

		ResultVO<Map<String,String>> vo = repaymentPreFee(payId, RepaymentType.PAY_TYPE_RECEPTION_CHARGE_FEE.getValue());
		if(vo.getStatus() != ErrorCode.SUCCESS.getErrCode()){
			log.error("前台手续费扣款失败");
			return ResultVO.build(vo.getStatus(), vo.getMsg());
		}

		int repayFlag = Integer.parseInt(vo.getData().get("ret"));
		log.info("前台手续费扣款结果为 repayFlag={}", repayFlag);

		if(repayFlag == 1){
			//成功
			payInfo.setPayStatus(PayApproveStatus.COST_CHARGE_SUCCESS.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.COST_CHARGE_SUCCESS.getDesc());
		}else if(repayFlag == 2){
			//失败
			payInfo.setPayStatus(PayApproveStatus.COST_CHARGE_FAIL.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.COST_CHARGE_FAIL.getDesc());
		}else{
			//处理中
			payInfo.setPayStatus(PayApproveStatus.COST_CHARGE_PAYING.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.COST_CHARGE_PAYING.getDesc());
		}
		payInfo.setUpdateTime(now);
		payInfoService.update(payInfo);

		//10 短信通知
		boolean ret;
		ArrayList<SendMsgVo> mobileList = new ArrayList<>();
		Map<String, Object> param = new HashMap<String, Object>();
        param.put("businessName","车贷业务系统");
        param.put("applyCode",payInfo.getApplyId());
        param.put("custName",payInfo.getCustName());
		//param.put("name", payInfo.getCustName());
		//List<String> m1 = ParamConstants.getValue(ParamConstants.ROLE_BUSS);
		List<SendMsgVo> m2 = ParamConstants.getValue(ParamConstants.ROLE_FINA);
		List<SendMsgVo> m3 = ParamConstants.getValue(ParamConstants.ROLE_FINA_MA);
		//List<String> m4 = ParamConstants.getValue(ParamConstants.ROLE_DEVE);
		//List<String> m5 = ParamConstants.getValue(ParamConstants.ROLE_FINA_CD);
		if (repayFlag == 1) {
			param.put("result", "成功");
			//mobileList.addAll(m1);
			mobileList.addAll(m2);
			mobileList.addAll(m3);
			//mobileList.addAll(m5);

		} else if(repayFlag == 2){
			param.put("result", "失败");
			//mobileList.addAll(m1);
			mobileList.addAll(m2);
			mobileList.addAll(m3);
			//mobileList.addAll(m4);
			//mobileList.addAll(m5);
		}
		else{
			param.put("result", "正在处理");
			//mobileList.addAll(m1);
			mobileList.addAll(m2);
			mobileList.addAll(m3);
			//mobileList.addAll(m5);
		}
		try {
			ret = smsService.sendMsgSMS(mobileList, SMSService.ALL_SUBMIT, param);
			if (ret == false) {
				log.error("===>>>申请单：{}, 收取前台手续费，发送短信通知失败", payId);
			} else {
				log.info("===>>>申请单：{}, 收取前台手续费，发送短信通知成功", payId);
			}
		}catch(Exception e){
			log.error("===>>>申请单：{}, 收取前台手续费，发送短信通知异常 {}", payId, e);
		}
		return ResultVO.build(ErrorCode.SUCCESS);
	}

    /**
     * 放款前台财务确认（前台财务）
     * @param applyId
     * @param operatorId
     * @return
     */
	@Transactional
    public ResultVO<Object> payReceptionConfirm(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[payReceptionConfirm] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[payReceptionConfirm] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }

	        Date now=new Date();

	        PayInfo payInfo=payInfoService.selectByApplyId(applyId);
	        //如果是扣除前台服务费失败，则手动创建扣款记录
	  		if(payInfo.getPayStatus() == PayApproveStatus.COST_CHARGE_FAIL.getValue()){
	  			RepaymentInfo repaymentInfo = repaymentInfoService.selectByPayId(payInfo.getId());
	  			if(repaymentInfo == null){
	  				log.error("没有对应payId={} 的还款总表信息!", payInfo.getId());
	  				return ResultVO.build(ErrorCode.REPAYMENT_INFO_NOT_EXIST);
	  			}

	  			RepaymentPayInfo repaymentPayInfo = new RepaymentPayInfo();
	  			repaymentPayInfo.setDeptId(repaymentInfo.getDeptId());
	  			repaymentPayInfo.setRepaymentId(repaymentInfo.getId());
	  			repaymentPayInfo.setPayType(RepaymentType.PAY_TYPE_RECEPTION_CHARGE_FEE_MANUAL.getValue());
	  			repaymentPayInfo.setPayTypeDesc(RepaymentType.PAY_TYPE_RECEPTION_CHARGE_FEE_MANUAL.getDesc());
	  			repaymentPayInfo.setAmount(payInfo.getReceptionAmount());
	  			repaymentPayInfo.setRepaymentPeriodNum(0);
	  			repaymentPayInfo.setBindingMobile(payInfo.getCustMobile());
	  			repaymentPayInfo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
	  			repaymentPayInfo.setPayingNum(0);
	  			repaymentPayInfo.setPayCode("0000");
	  			repaymentPayInfo.setPayMsg("成功");
	  			repaymentPayInfo.setPayTime(now);
	  			repaymentPayInfo.setPayChannel("BF002");
	  			repaymentPayInfo.setPayBank(payInfo.getBankName());
	  			repaymentPayInfo.setPayCard(payInfo.getBankCardNo());
	  			repaymentPayInfo.setCreateTime(now);
	  			repaymentPayInfo.setUpdateTime(now);
	  			repaymentPayInfo.setIsDeleted(0);
	  			repaymentPayInfo.setRemark("手工补录");
	  			repaymentPayInfoService.save(repaymentPayInfo);
	  		}

	  		payInfo.setPayStatus(PayApproveStatus.CONFIRM_FEE.getValue());
			payInfo.setPayStatusDesc(PayApproveStatus.CONFIRM_FEE.getDesc());
			payInfo.setSecondBussId(operatorId);
			payInfo.setUpdateTime(now);
			payInfoService.update(payInfo);
			//3 成功则短信通知财务
			Map<String, Object> param = new HashMap<String, Object>();
			//param.put("name", payInfo.getCustName());
            param.put("businessName","车贷业务系统");
            param.put("applyCode",payInfo.getApplyId());
            param.put("custName",payInfo.getCustName());
			List<SendMsgVo> mobileList = new ArrayList<>();

			boolean res;
			String smsCode;
			//成功则通知财务
			mobileList = ParamConstants.getValue(ParamConstants.ROLE_FINA);
            smsCode = SMSService.ALL_SUBMIT;
			try{
				res = smsService.sendMsgSMS(mobileList, smsCode, param);
				if(res == false){
					log.error("===>>>申请单：{}, 前台财务确认收取手续费，发送短信通知失败", payInfo.getId());
				}else{
					log.info("===>>>申请单：{}, 前台财务确认收取手续费，发送短信通知成功", payInfo.getId());
				}
			}catch(Exception e){
				log.error("===>>>申请单：{}, 前台财务确认收取手续费，发送短信通知异常 {}", payInfo.getId(), e);
			}
			
			return ResultVO.build(ErrorCode.SUCCESS);

        }catch(Exception e){
            log.error("===>[payReceptionConfirm] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 放款财务二次审核（财务）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Transactional
    public ResultVO<Object> payFinSecondAudit(Long applyId, Long operatorId,String amount, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[payFinSecondAudit] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[payFinSecondAudit] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
                return ret;
            }
	        int numFlag = 0;		//0--表示第一次放款  1--表示第二次放款
			Date now = new Date();

			//获取放款总表信息
	    	PayInfo payInfo=payInfoService.selectByApplyId(applyId);
	    	if (payInfo == null) {
	    		log.error("找不到该单号{}的放款总表", applyId);
				return ResultVO.build(ErrorCode.PAY_INFO_NOT_EXIST);
			}

	    	BigDecimal payMoney = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);


			if(payInfo.getPayedAmount().compareTo(BigDecimal.ZERO) > 0){
				numFlag = 1;
			}

			if(numFlag == 0){
				//TODO 这里暂定1500块
				if(payMoney.compareTo(new BigDecimal(1500)) < 0 || payMoney.compareTo(payInfo.getTotalAmount()) >= 0){
					log.error("首次放款金额不正确 amount={}", amount);
					return ResultVO.build(ErrorCode.PAY_MONEY_ERROR);
				}
			}else{
				if (payMoney.compareTo(payInfo.getNotPayAmount()) != 0) {
					log.error("二次放款输入金额不等于未放款金额 payId={} 输入金额={} 未放款金额={}", payInfo.getId(), payMoney, payInfo.getNotPayAmount());
					return ResultVO.build(ErrorCode.PAY_MONEY_ERROR);
				}
			}

			if ((payInfo.getPayStatus() != PayApproveStatus.BUSS_MANAGER_APPROVE.getValue()) &&
					(payInfo.getPayStatus() != PayApproveStatus.CONFIRM_FEE.getValue()) &&
					(payInfo.getPayStatus() != PayApproveStatus.FIRST_PAY_FAIL.getValue()) &&
					(payInfo.getPayStatus() != PayApproveStatus.SECOND_PAY_FAIL.getValue())) {
				log.error("放款状态不正确 payId={} status={}", payInfo.getId(), payInfo.getPayStatus());
				return ResultVO.build(ErrorCode.PAY_PROCESS_ERROR);
			}

			//查询放款详情表中是否存在支付中的信息
			PayDetailInfo paying = payDetailInfoService.selectByPayIdAndStatus(payInfo.getId(), PayStatus.PAYING.getValue());
			if (paying != null) {
				log.error("存在放款中的订单 payId={}", payInfo.getId());
				return ResultVO.build(ErrorCode.REPAYMENT_PAYING_EXIST);
			}

			if (result == 0) {
				if(numFlag == 0){
					payInfo.setPayStatus(PayApproveStatus.FIRST_FINANCE_APPROVE.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.FIRST_FINANCE_APPROVE.getDesc());
				}else{
					payInfo.setPayStatus(PayApproveStatus.SECOND_FINANCE_APPROVE.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.SECOND_FINANCE_APPROVE.getDesc());
				}
				payInfo.setApproveAmount(payMoney);
			} else {
				if(numFlag == 0){
					payInfo.setPayStatus(PayApproveStatus.FIRST_FINANCE_APPROVE_FAIL.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.FIRST_FINANCE_APPROVE_FAIL.getDesc());
				}else{
					payInfo.setPayStatus(PayApproveStatus.SECOND_FINANCE_APPROVE_FAIL.getValue());
					payInfo.setPayStatusDesc(PayApproveStatus.SECOND_FINANCE_APPROVE_FAIL.getDesc());
				}
				payInfo.setApproveAmount(new BigDecimal(0));
			}
			if(numFlag == 0) {
				payInfo.setFirstFinanceId(operatorId);
			}else{
				payInfo.setSecondFinanceId(operatorId);
			}
			payInfo.setUpdateTime(now);
			payInfoService.update(payInfo);
			Map<String, Object> param = new HashMap<String, Object>();
            param.put("businessName","车贷业务系统");
            param.put("applyCode",payInfo.getApplyId());
            param.put("custName",payInfo.getCustName());
			List<SendMsgVo> mobileList = new ArrayList<>();

			boolean res;
			String smsCode ;
			if (result == 0) {
				//成功则通知财务主管
				mobileList = ParamConstants.getValue(ParamConstants.ROLE_FINA_MA);
				if(numFlag == 0){
					param.put("time", "一");
				}else{
					param.put("time", "二");
				}
                smsCode = SMSService.ALL_SUBMIT;
			} else {
				//失败则通知业务经理
				mobileList = ParamConstants.getValue(ParamConstants.ROLE_BUSS_MA);
				if(numFlag == 0){
					param.put("time", "一");
				}else{
					param.put("time", "二");
				}
                smsCode = SMSService.ALL_SUBMIT;
			}
			try {
				res = smsService.sendMsgSMS(mobileList, smsCode, param);
				if (res == false) {
					log.error("===>>>申请单：{}, 财务审核，发送短信通知失败", payInfo.getId());
				} else {
					log.info("===>>>申请单：{}, 财务审核，发送短信通知成功", payInfo.getId());
				}
			}catch (Exception e){
				log.error("===>>>申请单：{}, 财务审核，发送短信通知异常 {}", payInfo.getId(), e);
			}
            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[payFinSecondAudit] exception: e={}", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 放款财务经理二次审核（财务经理）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Transactional
    public ResultVO<Object> payFinBussSecondAudit(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[payFinBussSecondAudit] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }

        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[payFinBussSecondAudit] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ret;
            }

        }catch(Exception e){
            log.error("===>[payFinBussSecondAudit] exception: e={}", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }

        int numFlag = 0;		//0--表示第一次放款  1--表示第二次放款
		int payFlag = 0;		//1--成功 2--失败 3--处理中
		int isFinished = 0;		//0--放款未完成 1--放款完成

		Date now = new Date();
		//1 检查流程
		PayInfo payInfo=payInfoService.selectByApplyId(applyId);
		if((payInfo.getPayStatus() != PayApproveStatus.FIRST_FINANCE_APPROVE.getValue()) &&
				(payInfo.getPayStatus() != PayApproveStatus.SECOND_FINANCE_APPROVE.getValue())){
			log.error("请求状态不正确 payId={},status={}", payInfo.getId(), payInfo.getPayStatus());
			return ResultVO.build(ErrorCode.PAY_PROCESS_ERROR);
		}

		//查询放款详情表中是否存在支付中的信息
		PayDetailInfo paying = payDetailInfoService.selectByPayIdAndStatus(payInfo.getId(), PayStatus.PAYING.getValue());
		if(paying != null){
			log.error("存在放款中的订单 payId={}", payInfo.getId());
			return ResultVO.build(ErrorCode.REPAYMENT_PAYING_EXIST);
		}

		BigDecimal payMoney = payInfo.getApproveAmount();
		if(payMoney.compareTo(payInfo.getNotPayAmount()) > 0){
			log.error("输入的金额 超过未放款金额 payId={} 输入金额={} 未放款金额={}", payInfo.getId(), payMoney, payInfo.getNotPayAmount());
			return ResultVO.build(ErrorCode.PAY_MONEY_ERROR);
		}

		if(payInfo.getPayedAmount().compareTo(BigDecimal.ZERO) > 0){
			numFlag = 1;
		}


		if(result == 0){
			//放款审批通过
			if(numFlag == 0){
				payInfo.setPayStatus(PayApproveStatus.FIRST_FINANCE_MANAGER_APPROVE.getValue());
				payInfo.setPayStatusDesc(PayApproveStatus.FIRST_FINANCE_MANAGER_APPROVE.getDesc());
			}else{
				payInfo.setPayStatus(PayApproveStatus.SECOND_FINANCE_MANAGER_APPROVE.getValue());
				payInfo.setPayStatusDesc(PayApproveStatus.SECOND_FINANCE_MANAGER_APPROVE.getDesc());
			}
		}else{
			//放款审批不通过
			if(numFlag == 0) {
				payInfo.setPayStatus(PayApproveStatus.FIRST_FINANCE_MANAGER_APPROVE_FAIL.getValue());
				payInfo.setPayStatusDesc(PayApproveStatus.FIRST_FINANCE_MANAGER_APPROVE_FAIL.getDesc());
			}else{
				payInfo.setPayStatus(PayApproveStatus.SECOND_FINANCE_MANAGER_APPROVE_FAIL.getValue());
				payInfo.setPayStatusDesc(PayApproveStatus.SECOND_FINANCE_MANAGER_APPROVE_FAIL.getDesc());
			}
		}
		if(numFlag == 0){
			payInfo.setFirstFinanceManagerId(operatorId);
		}else{
			payInfo.setSecondFinanceManagerId(operatorId);
		}
		payInfo.setUpdateTime(new Date());
		payInfoService.update(payInfo);

		//短信通知待开发
		boolean ret;
		String smsCode;
		List<SendMsgVo> mobileList = new ArrayList<>();
		Map<String, Object> param = new HashMap<String, Object>();
		//param.put("name", payInfo.getCustName());
        param.put("businessName","车贷业务系统");
        param.put("applyCode",payInfo.getApplyId());
        param.put("custName",payInfo.getCustName());
		if(numFlag == 0){
			param.put("time", "一");
		}else{
			param.put("time", "二");
		}

		if (result == 0) {
			// 成功
			List<SendMsgVo> mobileList1 = ParamConstants.getValue(ParamConstants.ROLE_BUSS_MA);
			List<SendMsgVo> mobileList2 = ParamConstants.getValue(ParamConstants.ROLE_FINA);
			mobileList.addAll(mobileList1);
			mobileList.addAll(mobileList2);
            smsCode = SMSService.ALL_SUBMIT;
		} else {
			//失败则通知业务员
			mobileList = ParamConstants.getValue(ParamConstants.ROLE_BUSS_MA);
            smsCode = SMSService.ALL_SUBMIT;
		}
		try {
			ret = smsService.sendMsgSMS(mobileList, smsCode, param);
			if (ret == false) {
				log.error("===>>>申请单：{}, 财务主管审核，发送短信通知失败", payInfo.getId());
			} else {
				log.info("===>>>申请单：{}, 财务主管审核，发送短信通知成功", payInfo.getId());
			}
		}catch (Exception e){
			log.error("===>>>申请单：{}, 财务主管审核，发送短信通知异常 {}", payInfo.getId(), e);
		}
		
		// 5 进行放款
		// 5.1 生成放款详情记录
		now = new Date();
		String sequenceNo = orderSequenceService.getTradeSequence();
		PayDetailInfo payDetailInfo = new PayDetailInfo();
		payDetailInfo.setPayId(payInfo.getId());
		payDetailInfo.setPayNum(numFlag == 0 ? 1 : 2);
		payDetailInfo.setCustMobile(payInfo.getCustMobile());
		payDetailInfo.setBankName(payInfo.getBankName());
		payDetailInfo.setBankCardNo(payInfo.getBankCardNo());
		payDetailInfo.setPayStatus(PayStatus.CREATE.getValue());
		payDetailInfo.setPayType(PayType.PAY_TYPE_SYS_PAY.getValue());
		payDetailInfo.setPayingNum(0);
		payDetailInfo.setSerialNo(sequenceNo);
		payDetailInfo.setOperatorId(operatorId);
		payDetailInfo.setOperatorDate(now);
		payDetailInfo.setCurPayAmount(payMoney);
		payDetailInfo.setCreateTime(now);
		payDetailInfo.setUpdateTime(now);
		payDetailInfo.setIsDeleted(0);
		payDetailInfoService.save(payDetailInfo);
		payFlag=1;
		payDetailInfo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
		// 5.2 调用payCenter 进行放款
		/*ResultVO<PayCenterPayResultVO> payResultVO = null;
		try{
			payResultVO = payCenterService.payMoney(sequenceNo, null, payInfo.getBankCardNo(),
					payInfo.getCustName(), payInfo.getCustMobile(), payInfo.getCustIdNo(), null, null,
					null, "id", payMoney.toString(), payInfo.getBankName());
		}catch(Exception e){
			log.error("[===>>>放款ID:{} 放款详情ID:{}，调用支付中心代付接口异常===],excp:{}", payInfo.getId(), payDetailInfo.getId(), e);
		}*/

		// 5.3 更新放款详情表
		/*if (payResultVO == null || payResultVO.getData() == null || !payResultVO.isSuccess()) {
			//失败
			payDetailInfo.setPayStatus(PayStatus.PAY_FAIL.getValue());
			payFlag = 2;
		}

		if (payResultVO.getData().getResultCode().equals(PayCenterCode.SUCCESS)) {
			// 代扣成功，更新支付状态
			payDetailInfo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
			payDetailInfo.setPayChannel(payResultVO.getData().getPayPlatform());
			payDetailInfo.setPayCode(payResultVO.getData().getResultCode());
			payDetailInfo.setPayMsg(payResultVO.getData().getMessage());
			payFlag = 1;
		} else if (payResultVO.getData().getResultCode().equals(PayCenterCode.DOING)) {
			// 代扣处理中，更新支付状态为支付中
			payDetailInfo.setPayStatus(PayStatus.PAYING.getValue());
			payDetailInfo.setPayChannel(payResultVO.getData().getPayPlatform());
			payDetailInfo.setPayCode(payResultVO.getData().getResultCode());
			payDetailInfo.setPayMsg(payResultVO.getData().getMessage());
			payFlag = 3;
		} else {
			payDetailInfo.setPayStatus(PayStatus.PAY_FAIL.getValue());
			payDetailInfo.setPayChannel(payResultVO.getData().getPayPlatform());
			payDetailInfo.setPayCode(payResultVO.getData().getResultCode());
			payDetailInfo.setPayMsg(payResultVO.getData().getMessage());
			payFlag = 2;
		}*/

		payDetailInfoService.update(payDetailInfo);

		//6 更新总放款表状态
		if(payFlag == 1){
			payInfo.setNotPayAmount(payInfo.getNotPayAmount().subtract(payMoney));
			payInfo.setPayedAmount(payInfo.getPayedAmount().add(payMoney));
			if(numFlag == 0){
				payInfo.setPayStatus(PayApproveStatus.FIRST_PAY_SUCCESS.getValue());
				payInfo.setPayStatusDesc(PayApproveStatus.FIRST_PAY_SUCCESS.getDesc());
			}
			else{
				payInfo.setPayStatus(PayApproveStatus.SECOND_PAY_SUCCESS.getValue());
				payInfo.setPayStatusDesc(PayApproveStatus.SECOND_PAY_SUCCESS.getDesc());
			}
			if(payInfo.getNotPayAmount().compareTo(BigDecimal.ZERO) == 0){
				//放款完成
				payInfo.setPayStatus(PayApproveStatus.PAY_SUCCESS.getValue());
				payInfo.setPayStatusDesc(PayApproveStatus.PAY_SUCCESS.getDesc());
				isFinished = 1;		//表示放款完成
			}
		}else if(payFlag == 2){
			if(numFlag == 0){
				payInfo.setPayStatus(PayApproveStatus.FIRST_PAY_FAIL.getValue());
				payInfo.setPayStatusDesc(PayApproveStatus.FIRST_PAY_FAIL.getDesc());
			}
			else{
				payInfo.setPayStatus(PayApproveStatus.SECOND_PAY_FAIL.getValue());
				payInfo.setPayStatusDesc(PayApproveStatus.SECOND_PAY_FAIL.getDesc());
			}
		}else{
			if(numFlag == 0){
				payInfo.setPayStatus(PayApproveStatus.FIRST_PAY_PAYING.getValue());
				payInfo.setPayStatusDesc(PayApproveStatus.FIRST_PAY_PAYING.getDesc());
			}
			else{
				payInfo.setPayStatus(PayApproveStatus.SECOND_PAY_PAYING.getValue());
				payInfo.setPayStatusDesc(PayApproveStatus.SECOND_PAY_PAYING.getDesc());
			}
		}
		payInfo.setUpdateTime(now);
		payInfoService.update(payInfo);

		//7 短信通知
		mobileList = new ArrayList<>();
		param = new HashMap<String, Object>();
        param.put("businessName","车贷业务系统");
        param.put("applyCode",payInfo.getApplyId());
        param.put("custName",payInfo.getCustName());
		if(numFlag == 0){
			param.put("time", "一");
		}else{
			param.put("time", "二");
		}
		if (payFlag == 1) {
			param.put("result", "成功");
			//添加手机列表
			//List<String> m1 = ParamConstants.getValue(ParamConstants.ROLE_BUSS);
			List<SendMsgVo> m2 = ParamConstants.getValue(ParamConstants.ROLE_FINA);
			List<SendMsgVo> m3 = ParamConstants.getValue(ParamConstants.ROLE_FINA_MA);
			//mobileList.addAll(m1);
			mobileList.addAll(m2);
			mobileList.addAll(m3);
		} else if(payFlag == 2){
			param.put("result", "失败");
			//List<String> m1 = ParamConstants.getValue(ParamConstants.ROLE_BUSS);
			List<SendMsgVo> m2 = ParamConstants.getValue(ParamConstants.ROLE_FINA);
			List<SendMsgVo> m3 = ParamConstants.getValue(ParamConstants.ROLE_FINA_MA);
			//List<String> m4 = ParamConstants.getValue(ParamConstants.ROLE_DEVE);
			//mobileList.addAll(m1);
			mobileList.addAll(m2);
			mobileList.addAll(m3);
			//mobileList.addAll(m4);
		}
		else{
			param.put("result", "正在处理");
			//List<String> m1 = ParamConstants.getValue(ParamConstants.ROLE_BUSS);
			List<SendMsgVo> m2 = ParamConstants.getValue(ParamConstants.ROLE_FINA);
			List<SendMsgVo> m3 = ParamConstants.getValue(ParamConstants.ROLE_FINA_MA);
			//mobileList.addAll(m1);
			mobileList.addAll(m2);
			mobileList.addAll(m3);
		}
		try{
			ret = smsService.sendMsgSMS(mobileList, SMSService.ALL_SUBMIT, param);
			if (ret == false) {
				log.error("===>>>申请单：{}, 放款结果，发送短信通知失败", payInfo.getId());
			} else {
				log.info("===>>>申请单：{}, 放款结果，发送短信通知成功", payInfo.getId());
			}
		}catch(Exception e){
			log.error("===>>>申请单：{}, 放款结果，发送短信通知异常 {}", payInfo.getId(), e);
		}

		return ResultVO.build(ErrorCode.SUCCESS);
    }

    /**
     * 提前还款业务申请（内勤）(沒用)
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> onceEarlyApply(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[onceEarlyApply] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[onceEarlyApply] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "内勤申请提前还款业务");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQZG);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[onceEarlyApply] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 提前还款内勤主管审核（内勤主管）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Transactional
    public ResultVO<Object> onceEarlyNqzgAudit(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[onceEarlyNqzgAudit] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
        	//PayInfo payInfo=payInfoService.selectByApplyId(applyId);
        	RepaymentInfo  repaymentInfo =repaymentInfoService.selectByApplyId(applyId);
        	OnceEarlyRepaymentRecord record = onceEarlyRepaymentRecordService.selectByRepaymentId(repaymentInfo.getId());
        	record.setCurStatus(15);record.setCurStatusDesc("内勤主管审核");
        	onceEarlyRepaymentRecordService.update(record);
        	
        	//处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[onceEarlyNqzgAudit] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                
                return ret;
            }
            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "提前还款内勤主管审核");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_BUSS_MA);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }
            

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[onceEarlyNqzgAudit] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 提前还款业务经理审核（业务经理）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> onceEarlyBussApprove(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[onceEarlyBussApprove] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
        	RepaymentInfo  repaymentInfo =repaymentInfoService.selectByApplyId(applyId);
        	OnceEarlyRepaymentRecord record = onceEarlyRepaymentRecordService.selectByRepaymentId(repaymentInfo.getId());
        	record.setCurStatus(20);record.setCurStatusDesc("业务经理审批");
        	
        	RepaymentPlanInfo planInfo =  repaymentPlanInfoDao.selectByPeriod(record.getRepaymentId(), record.getCurPeriodNum());
        	
        	if(result == 0){
        		repaymentInfo.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_BUSS_APPROVE.getValue());
        		repaymentInfo.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_BUSS_APPROVE.getDesc());
        		record.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_BUSS_APPROVE.getValue());
        		record.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_BUSS_APPROVE.getDesc());
			}else{
				repaymentInfo.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_BUSS_APPROVE_FAIL.getValue());
				repaymentInfo.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_BUSS_APPROVE_FAIL.getDesc());
				record.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_BUSS_APPROVE_FAIL.getValue());
				record.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_BUSS_APPROVE_FAIL.getDesc());

				if(planInfo.getIsLock() == 1){
					RepaymentPlanInfo tempPlan = new RepaymentPlanInfo();
					tempPlan.setId(planInfo.getId());

					tempPlan.setIsLock(0);
					tempPlan.setUpdateTime(new Date());
					repaymentPlanInfoDao.update(tempPlan);
				}
			}
        	repaymentInfoService.update(repaymentInfo);
        	onceEarlyRepaymentRecordService.update(record);
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[onceEarlyBussApprove] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con","提前还款业务经理审核");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_FINA);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[onceEarlyBussApprove] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 提前还款财务确认（财务）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Transactional
    public ResultVO<Object> onceEarlyFinConfirm(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[onceEarlyFinConfirm] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }

        if(result==2){//财务驳回到内勤
            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            if(applyInfo != null ){

                applyInfo.setStatus(9090);
                applyInfo.setStatusDesc("财务驳回");
                applyInfoService.updateWithOutNull(applyInfo);

                RepaymentInfo repaymentInfo = repaymentInfoService.selectByApplyId(applyId);
                repaymentInfo.setCurStatus(RepaymentApproveStatus.NO_BEGIN.getValue());
                repaymentInfo.setCurStatusDesc("申请提前还款");
                repaymentInfoService.saveOrUpdate(repaymentInfo);


                ShiroUser shiroUser = ShiroKit.getUser();
                Date now = new Date();

                // 写入借款操作员表
                ApplyOperator applyOperator = new ApplyOperator();
                applyOperator.setApplyId(applyId);
                applyOperator.setRoleId((long)shiroUser.getRoleList().get(0));
                applyOperator.setUserId(operatorId);
                applyOperator.setProcessNodeId(10056L);
                applyOperator.setCreateTime(now);
                applyOperator.setUpdateTime(now);
                applyOperator.setIsDeleted(0);
                applyOperatorDao.save(applyOperator);

                // 写入审批记录表
                MainApproveRecord record = new MainApproveRecord();

                record.setApplyId(applyId);
                record.setOperatorId(operatorId);
                record.setOperatorName(shiroUser==null?null:shiroUser.getName());
                record.setOperatorTime(now);
                record.setProcessNodeId(10056L);
                record.setProcessNodeDesc("提前还款财务驳回");
                record.setAuditRemark(resultTip);
                record.setIsDeleted(0);
                record.setCreateTime(now);
                record.setUpdateTime(now);
                mainApproveRecordService.save(record);

                CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("businessName","车贷业务系统");
                param.put("applyCode",applyId);
                param.put("custName",customerInfo.getName());
                param.put("con","提前还款财务审核");

                List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
                String smsCode = SMSService.ALL_SUBMIT;

                boolean res ;
                try{
                    res = smsService.sendMsgSMS(mobileList, smsCode, param);
                    if(res == false){
                        log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                    }else{
                        log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                    }
                }catch(Exception e){
                    log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
                }
            }
            return ResultVO.build(ErrorCode.SUCCESS);
        }

        try{
        	
        	RepaymentInfo  repaymentInfo =repaymentInfoService.selectByApplyId(applyId);
        	OnceEarlyRepaymentRecord record = onceEarlyRepaymentRecordService.selectByRepaymentId(repaymentInfo.getId());
        	record.setCurStatus(20);record.setCurStatusDesc("业务经理审批");
        	
        	RepaymentPlanInfo planInfo =  repaymentPlanInfoDao.selectByPeriod(record.getRepaymentId(), record.getCurPeriodNum());
        	
        	if(result == 0){
        		repaymentInfo.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FINANCE.getValue());
        		repaymentInfo.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FINANCE.getDesc());
        		record.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FINANCE.getValue());
        		record.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FINANCE.getDesc());
			}else{
				repaymentInfo.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FINANCE_FAIL.getValue());
				repaymentInfo.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FINANCE_FAIL.getDesc());
				record.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FINANCE_FAIL.getValue());
				record.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FINANCE_FAIL.getDesc());

				if(planInfo.getIsLock() == 1){
					RepaymentPlanInfo tempPlan = new RepaymentPlanInfo();
					tempPlan.setId(planInfo.getId());

					tempPlan.setIsLock(0);
					tempPlan.setUpdateTime(new Date());
					repaymentPlanInfoDao.update(tempPlan);
				}
			}
        	repaymentInfoService.update(repaymentInfo);
        	onceEarlyRepaymentRecordService.update(record);

            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[onceEarlyFinConfirm] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }
            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "提前还款财务确认");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_FINA_MA);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }


            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[onceEarlyFinConfirm] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 提前还款财务经理审核（财务经理）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Transactional
    public ResultVO<Object> onceEarlyFinBussConfirm(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[onceEarlyFinBussConfirm] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        if(result==2){//财务经理驳回到财务
            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            if(applyInfo != null ){

                applyInfo.setStatus(9320);
                applyInfo.setStatusDesc("财务经理驳回");
                applyInfoService.updateWithOutNull(applyInfo);

                ShiroUser shiroUser = ShiroKit.getUser();
                Date now = new Date();

                // 写入借款操作员表
                ApplyOperator applyOperator = new ApplyOperator();
                applyOperator.setApplyId(applyId);
                applyOperator.setRoleId((long)shiroUser.getRoleList().get(0));
                applyOperator.setUserId(operatorId);
                applyOperator.setProcessNodeId(10057L);
                applyOperator.setCreateTime(now);
                applyOperator.setUpdateTime(now);
                applyOperator.setIsDeleted(0);
                applyOperatorDao.save(applyOperator);

                // 写入审批记录表
                MainApproveRecord record = new MainApproveRecord();

                record.setApplyId(applyId);
                record.setOperatorId(operatorId);
                record.setOperatorName(shiroUser==null?null:shiroUser.getName());
                record.setOperatorTime(now);
                record.setProcessNodeId(10057L);
                record.setProcessNodeDesc("提前还款财务经理驳回");
                record.setAuditRemark(resultTip);
                record.setIsDeleted(0);
                record.setCreateTime(now);
                record.setUpdateTime(now);
                mainApproveRecordService.save(record);

                CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("businessName","车贷业务系统");
                param.put("applyCode",applyId);
                param.put("custName",customerInfo.getName());
                param.put("con","提前还款财务经理审核");

                List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
                String smsCode = SMSService.ALL_SUBMIT;

                boolean res ;
                try{
                    res = smsService.sendMsgSMS(mobileList, smsCode, param);
                    if(res == false){
                        log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                    }else{
                        log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                    }
                }catch(Exception e){
                    log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
                }
            }
            return ResultVO.build(ErrorCode.SUCCESS);
        }

        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[onceEarlyFinBussConfirm] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }
            
            RepaymentInfo  repaymentInfo =repaymentInfoService.selectByApplyId(applyId);
        	OnceEarlyRepaymentRecord record = onceEarlyRepaymentRecordService.selectByRepaymentId(repaymentInfo.getId());
        	RepaymentPlanInfo planInfo =  repaymentPlanInfoDao.selectByPeriod(record.getRepaymentId(), record.getCurPeriodNum());
        	if(result == 0){
        		repaymentInfo.setIsOnceEarlyRepayment(1);
        		repaymentInfo.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE.getValue());
        		repaymentInfo.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE.getDesc());
        		record.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE.getValue());
        		record.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE.getDesc());
			}else{
				repaymentInfo.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE_FAIL.getValue());
				repaymentInfo.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE_FAIL.getDesc());
				record.setCurStatus(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE_FAIL.getValue());
				record.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_ONCE_EARLY_FIN_BUSS_APPROVE_FAIL.getDesc());

				if(planInfo.getIsLock() == 1){
					RepaymentPlanInfo tempPlan = new RepaymentPlanInfo();
					tempPlan.setId(planInfo.getId());

					tempPlan.setIsLock(0);
					tempPlan.setUpdateTime(new Date());
					repaymentPlanInfoDao.update(tempPlan);
				}
			}
        	repaymentInfoService.update(repaymentInfo);
        	onceEarlyRepaymentRecordService.update(record);
        	
        	//4 解除锁定
			if(planInfo.getIsLock() == 1) {
				RepaymentPlanInfo tempPlan = new RepaymentPlanInfo();
				tempPlan.setId(planInfo.getId());

				tempPlan.setIsLock(0);
				tempPlan.setUpdateTime(new Date());
				repaymentPlanInfoDao.update(tempPlan);
			}
			

			//5 开始分账
			//5.1 当期还款  -- 对公转账
			ResultVO<Object> vo = repaymentTxService.manualDeductMoney(record.getRepaymentId(),
					record.getCurPeriodNum(), 2);
			if(!vo.isSuccess()){
				log.error("提前还款分账-- 当期还款错误 onceEarlyId={} repaymentId={}, period={} status={}",
						record.getId(), record.getRepaymentId(), record.getCurPeriodNum(), repaymentInfo.getCurStatus());
				return vo;
			}

			vo = repaymentTxService.allotPayUpdatePlanInfo(Long.parseLong(vo.getData().toString()), true);
			if(!vo.isSuccess()){
				log.error("提前还款分账-- 当期还款分配错误 onceEarlyId={} repaymentId={}, period={} status={}",
						record.getId(), record.getRepaymentId(), record.getCurPeriodNum(), repaymentInfo.getCurStatus());
				return vo;
			}
			
			//重新读取数据
			repaymentInfo = repaymentInfoService.selectByApplyId(applyId);

			BigDecimal capital1 = planInfo.getEndAmountLease();
			BigDecimal capital2 = record.getOnceRepaymentCapital().subtract(capital1);
			
			//5.2 当期剩余本金 -- 租赁剩余本金
			RepaymentPayInfo repaymentPayInfo = new RepaymentPayInfo();
			repaymentPayInfo.setRepaymentId(record.getRepaymentId());
			repaymentPayInfo.setPayType(RepaymentType.PAY_TYPE_ONCE_EARLY_CAPITAL.getValue());
			repaymentPayInfo.setPayTypeDesc(RepaymentType.PAY_TYPE_ONCE_EARLY_CAPITAL.getDesc());
			repaymentPayInfo.setPayingNum(0);
			repaymentPayInfo.setAmount(capital1);
			repaymentPayInfo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
			repaymentPayInfo.setPayTime(new Date());
			repaymentPayInfo.setPayBank(repaymentInfo.getBankName());
			repaymentPayInfo.setPayCard(repaymentInfo.getBankCardNo());
			repaymentPayInfo.setBindingMobile(repaymentInfo.getCustMobile());
			repaymentPayInfo.setCreateTime(new Date());
			repaymentPayInfo.setUpdateTime(new Date());
			repaymentPayInfo.setIsDeleted(0);
			repaymentPayInfoService.save(repaymentPayInfo);
			
			//5.3 当期剩余本金 -- 天津费用
			repaymentPayInfo = new RepaymentPayInfo();
			repaymentPayInfo.setRepaymentId(record.getRepaymentId());
			repaymentPayInfo.setPayType(RepaymentType.PAY_TYPE_ONCE_EARLY_INTEREST.getValue());
			repaymentPayInfo.setPayTypeDesc(RepaymentType.PAY_TYPE_ONCE_EARLY_INTEREST.getDesc());
			repaymentPayInfo.setPayingNum(0);
			repaymentPayInfo.setAmount(capital2);
			repaymentPayInfo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
			repaymentPayInfo.setPayTime(new Date());
			repaymentPayInfo.setPayBank(repaymentInfo.getBankName());
			repaymentPayInfo.setPayCard(repaymentInfo.getBankCardNo());
			repaymentPayInfo.setBindingMobile(repaymentInfo.getCustMobile());
			repaymentPayInfo.setCreateTime(new Date());
			repaymentPayInfo.setUpdateTime(new Date());
			repaymentPayInfo.setIsDeleted(0);
			repaymentPayInfoService.save(repaymentPayInfo);
			
			//5.4 当期剩余手续费
			if(record.getOnceRepaymentCharge().compareTo(new BigDecimal(0)) > 0){
				repaymentPayInfo = new RepaymentPayInfo();
				repaymentPayInfo.setRepaymentId(record.getRepaymentId());
				repaymentPayInfo.setPayType(RepaymentType.PAY_TYPE_ONCE_EARLY_CHARGE.getValue());
				repaymentPayInfo.setPayTypeDesc(RepaymentType.PAY_TYPE_ONCE_EARLY_CHARGE.getDesc());
				repaymentPayInfo.setPayingNum(0);
				repaymentPayInfo.setAmount(record.getOnceRepaymentCharge());
				repaymentPayInfo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
				repaymentPayInfo.setPayTime(new Date());
				repaymentPayInfo.setPayBank(repaymentInfo.getBankName());
				repaymentPayInfo.setPayCard(repaymentInfo.getBankCardNo());
				repaymentPayInfo.setBindingMobile(repaymentInfo.getCustMobile());
				repaymentPayInfo.setCreateTime(new Date());
				repaymentPayInfo.setUpdateTime(new Date());
				repaymentPayInfo.setIsDeleted(0);
				repaymentPayInfoService.save(repaymentPayInfo);
			}
			
			//5.5 当期剩违约金
			if(record.getOnceRepaymentBreach().compareTo(new BigDecimal(0)) > 0){
				repaymentPayInfo = new RepaymentPayInfo();
				repaymentPayInfo.setRepaymentId(record.getRepaymentId());
				repaymentPayInfo.setPayType(RepaymentType.PAY_TYPE_ONCE_EARLY_BREACH.getValue());
				repaymentPayInfo.setPayTypeDesc(RepaymentType.PAY_TYPE_ONCE_EARLY_BREACH.getDesc());
				repaymentPayInfo.setPayingNum(0);
				repaymentPayInfo.setAmount(record.getOnceRepaymentBreach());
				repaymentPayInfo.setPayStatus(PayStatus.PAY_SUCCESS.getValue());
				repaymentPayInfo.setPayTime(new Date());
				repaymentPayInfo.setPayBank(repaymentInfo.getBankName());
				repaymentPayInfo.setPayCard(repaymentInfo.getBankCardNo());
				repaymentPayInfo.setBindingMobile(repaymentInfo.getCustMobile());
				repaymentPayInfo.setCreateTime(new Date());
				repaymentPayInfo.setUpdateTime(new Date());
				repaymentPayInfo.setIsDeleted(0);
				repaymentPayInfoService.save(repaymentPayInfo);
			}
			
			//6 更改还款总表

			BigDecimal monthLeaseTotal = new BigDecimal(0);
			BigDecimal monthServiceTotal =  new BigDecimal(0);
			BigDecimal leaseCapitalTotal = new BigDecimal(0);		//租赁本金总计
			BigDecimal leaseInterestTotal = new BigDecimal(0);		//租赁利息总计

			List<RepaymentPlanInfo> planList = repaymentPlanInfoDao.listByRepaymentId(repaymentInfo.getId());
			for(int i=0;i<record.getCurPeriodNum();i++){
				leaseCapitalTotal = leaseCapitalTotal.add(planList.get(i).getCapitalLease());
				leaseInterestTotal = leaseInterestTotal.add(planList.get(i).getInterestLease());
				monthLeaseTotal = monthLeaseTotal.add(planList.get(i).getLeaseTotal());
				monthServiceTotal = monthServiceTotal.add(planList.get(i).getServiceTotal());
			}
			//分配租赁提前还款金额
			leaseCapitalTotal = leaseCapitalTotal.add(capital1);
			monthLeaseTotal = monthLeaseTotal.add(capital1);

			//分配天津氢诺提前还款金额
			monthServiceTotal = monthServiceTotal.add(capital2);
			monthServiceTotal = monthServiceTotal.add(record.getOnceRepaymentCharge());
			monthServiceTotal = monthServiceTotal.add(record.getOnceRepaymentBreach());
        	
			RepaymentInfo newInfo = new RepaymentInfo();
			newInfo.setId(repaymentInfo.getId());
			newInfo.setPredictInterest(repaymentInfo.getActualInterest());
			newInfo.setPredictServiceCharge(repaymentInfo.getActualServiceCharge());
			newInfo.setPredictAmount(repaymentInfo.getPredictCharge()
						.add(repaymentInfo.getPredictCapital())
						.add(newInfo.getPredictInterest())
						.add(newInfo.getPredictServiceCharge()));
			newInfo.setActualCharge(repaymentInfo.getActualCharge().add(record.getOnceRepaymentCharge()));
			newInfo.setActualCapital(repaymentInfo.getActualCapital().add(record.getOnceRepaymentCapital()));
			newInfo.setActualAmount(newInfo.getActualCharge()
							.add(newInfo.getActualCapital())
							.add(repaymentInfo.getActualInterest())
							.add(repaymentInfo.getActualServiceCharge()));
			newInfo.setLeaseCapitalTotal(leaseCapitalTotal);
			newInfo.setLeaseInterestTotal(leaseInterestTotal);
			newInfo.setMonthServiceTotal(monthServiceTotal);
			newInfo.setMonthLeaseTotal(monthLeaseTotal);
			log.info("repaymentId={} name={} predictAmount={} actualAmount={}", repaymentInfo.getId(), repaymentInfo.getCustName(),
						newInfo.getPredictAmount(), newInfo.getActualAmount());
			newInfo.setBreachAmount(record.getOnceRepaymentBreach());
			newInfo.setCurStatus(RepaymentApproveStatus.REPAYMENT_DONE.getValue());
			newInfo.setCurStatusDesc(RepaymentApproveStatus.REPAYMENT_DONE.getDesc());
			
			//7 添加还款变更记录
			RepaymentChangeRecord changeRecord = new RepaymentChangeRecord();
			changeRecord.setRepaymentId(record.getRepaymentId());
			changeRecord.setChangeType(RepaymentChangeType.CHANGE_TYPE_EARLY_PAY.getValue());
			changeRecord.setChangeDesc(RepaymentChangeType.CHANGE_TYPE_EARLY_PAY.getDesc());
			changeRecord.setOnceRepaymentCapital(record.getOnceRepaymentCapital());
			changeRecord.setOnceRepaymentCharge(record.getOnceRepaymentCharge());
			changeRecord.setOnceRepaymentBreach(record.getOnceRepaymentBreach());
			changeRecord.setOnceRepaymentTotal(record.getOnceRepaymentTotal());
			changeRecord.setCurPeriodNum(record.getCurPeriodNum());
			changeRecord.setCurPeriodAmount(record.getCurPeriodAmount());
			changeRecord.setOldAmount(repaymentInfo.getPredictAmount());
			changeRecord.setOldServiceCharge(repaymentInfo.getPredictServiceCharge());
			changeRecord.setOldInterest(repaymentInfo.getPredictInterest());
			changeRecord.setOldLeaseCapitalTotal(repaymentInfo.getLeaseCapitalTotal());
			changeRecord.setOldLeaseInterestTotal(repaymentInfo.getLeaseInterestTotal());
			changeRecord.setOldMonthLeaseTotal(repaymentInfo.getMonthLeaseTotal());
			changeRecord.setOldMonthServiceTotal(repaymentInfo.getMonthServiceTotal());
			changeRecord.setNewAmount(newInfo.getPredictAmount());
			changeRecord.setNewInterest(newInfo.getPredictInterest());
			changeRecord.setNewServiceCharge(newInfo.getPredictServiceCharge());
			changeRecord.setNewLeaseCapitalTotal(leaseCapitalTotal);
			changeRecord.setNewLeaseInterestTotal(leaseInterestTotal);
			changeRecord.setNewMonthLeaseTotal(monthLeaseTotal);
			changeRecord.setNewMonthServiceTotal(monthServiceTotal);
			changeRecord.setCreateTime(new Date());
			changeRecord.setUpdateTime(new Date());
			changeRecord.setIsDeleted(0);
			repaymentChangeRecordService.save(changeRecord);

			repaymentInfoService.update(newInfo);
			
			//8 删除剩余还款计划表
			planList = repaymentPlanInfoDao.listByRepaymentId(repaymentInfo.getId());
			for (RepaymentPlanInfo info : planList) {
				if(info.getPeriodNum() <= record.getCurPeriodNum()){
					continue;
				}

				RepaymentPlanInfo tempPlan = new RepaymentPlanInfo();
				tempPlan.setId(info.getId());
				tempPlan.setIsDeleted(1);
				tempPlan.setUpdateTime(new Date());
				repaymentPlanInfoDao.update(tempPlan);
			}
			
            //修改还款成功状态
            ApplyInfo applyInfoTemp = new ApplyInfo();
            ProcessNode node = processNodeDao.selectByName("还款完成");
            applyInfoTemp.setId(applyId);
            applyInfoTemp.setStatus(node.getProcessStatus());
            applyInfoTemp.setStatusDesc(node.getProcessStatusDesc());
            applyInfoTemp.setUpdateTime(new Date());
            applyInfoService.updateWithOutNull(applyInfoTemp);

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "提前还款财务经理审核");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }
            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[onceEarlyFinBussConfirm] exception: e={}", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 解押申请（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> detentionCarApply(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[detentionCarApply] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(1, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[detentionCarApply] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "内勤提交解压申请");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_FINA);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[detentionCarApply] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 解押结清确认（财务）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> detentionFinSettleConfirm(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[detentionFinSettleConfirm] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[detentionFinSettleConfirm] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "解押结清确认");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_DYZY);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[detentionFinSettleConfirm] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 解押申请材料确认（抵押专员）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> detentionConfirmApply(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[detentionConfirmApply] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }

        if(result==2){//财务驳回到内勤
            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            if(applyInfo != null ){

                applyInfo.setStatus(10000);
                applyInfo.setStatusDesc("抵押专员驳回");
                applyInfoService.updateWithOutNull(applyInfo);

                ShiroUser shiroUser = ShiroKit.getUser();
                Date now = new Date();

                // 写入借款操作员表
                ApplyOperator applyOperator = new ApplyOperator();
                applyOperator.setApplyId(applyId);
                applyOperator.setRoleId((long)shiroUser.getRoleList().get(0));
                applyOperator.setUserId(operatorId);
                applyOperator.setProcessNodeId(10061L);
                applyOperator.setCreateTime(now);
                applyOperator.setUpdateTime(now);
                applyOperator.setIsDeleted(0);
                applyOperatorDao.save(applyOperator);

                // 写入审批记录表
                MainApproveRecord record = new MainApproveRecord();

                record.setApplyId(applyId);
                record.setOperatorId(operatorId);
                record.setOperatorName(shiroUser==null?null:shiroUser.getName());
                record.setOperatorTime(now);
                record.setProcessNodeId(10061L);
                record.setProcessNodeDesc("抵押专员驳回");
                record.setAuditRemark(resultTip);
                record.setIsDeleted(0);
                record.setCreateTime(now);
                record.setUpdateTime(now);
                mainApproveRecordService.save(record);

                CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("businessName","车贷业务系统");
                param.put("applyCode",applyId);
                param.put("custName",customerInfo.getName());
                param.put("con","确认解押申请材料");

                List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
                String smsCode = SMSService.ALL_SUBMIT;

                boolean res ;
                try{
                    res = smsService.sendMsgSMS(mobileList, smsCode, param);
                    if(res == false){
                        log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                    }else{
                        log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                    }
                }catch(Exception e){
                    log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
                }
            }
            return ResultVO.build(ErrorCode.SUCCESS);
        }

        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[detentionConfirmApply] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }
            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "解押申请材料确认");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_DYZY);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }


            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[detentionConfirmApply] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 解押申请材料确认接收（抵押专员）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> detentionConfirmPaper(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[detentionConfirmPaper] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[detentionConfirmPaper] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }
            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "解押申请材料接收确认");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_DYZY);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[detentionConfirmPaper] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 解押已受理（抵押专员）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> detentionBussHanding(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[detentionBussHanding] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[detentionBussHanding] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }
            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "解押已受理");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_DYZY);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[detentionBussHanding] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 解押完成（抵押专员）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> detetionBussFinish(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[detetionBussFinish] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[detetionBussFinish] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "解押完成");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[detetionBussFinish] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * gps卸载申请（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> gpsUninstallApply(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[gpsUninstallApply] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(0, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[gpsUninstallApply] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "内勤提交GPS卸载申请");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_YCS);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[gpsUninstallApply] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * gps卸载完成（验车师）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> gpsUnistallFinish(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[gpsUnistallFinish] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[gpsUnistallFinish] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "验车师卸载GPS完成");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_MSZG);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }
            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[gpsUnistallFinish] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * gps卸载完成确认（面审主管）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> gpsUninstallConfirm(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[gpsUninstallConfirm] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[gpsUninstallConfirm] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }

            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "面审主管确认GPS卸载完成");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
            String smsCode = SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }
            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[gpsUninstallConfirm] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 资料移交（内勤）
     * @param applyId
     * @param operatorId
     * @return
     */
    @Override
    public ResultVO<Object> dataTranfser(Long applyId, Long operatorId, Integer result, String resultTip) {
        if(applyId == null || operatorId == null){
            log.error("===>[dataTranfser] param error, applyId={} operatorId={}", applyId, operatorId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
        try{
            //处理流程
            ResultVO<Object> ret = processProc(null, applyId, operatorId, ProcessType.SUCCESS.getValue(), null);
            if(!ret.isSuccess()){
                log.error("===>[dataTranfser] processProc error, error code={} msg={}", ret.getStatus(), ret.getMsg());
                return ret;
            }
            ApplyInfo applyInfo = applyInfoService.selectById(applyId);
            CustomerInfo customerInfo=customerInfoService.selectById(applyInfo.getCustId());
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("businessName","车贷业务系统");
            param.put("applyCode",applyId);
            param.put("custName",customerInfo.getName());
            param.put("con", "内勤资料移交");

            List<SendMsgVo>  mobileList = ParamConstants.getValue(ParamConstants.ROLE_NQ);
            String smsCode= SMSService.ALL_SUBMIT;

            boolean res ;
            try{
                res = smsService.sendMsgSMS(mobileList, smsCode, param);
                if(res == false){
                    log.error("===>>>申请单：{},通知，发送短信通知失败", applyInfo.getId());
                }else{
                    log.info("===>>>申请单：{}, 通知，发送短信通知成功", applyInfo.getId());
                }
            }catch(Exception e){
                log.error("===>>>申请单：{}, 通知，发送短信通知异常 {}", applyInfo.getId(), e);
            }

            return ResultVO.build(ErrorCode.SUCCESS);


        }catch(Exception e){
            log.error("===>[dataTranfser] exception: e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }


}
