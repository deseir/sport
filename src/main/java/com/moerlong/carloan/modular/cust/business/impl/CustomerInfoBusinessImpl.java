package com.moerlong.carloan.modular.cust.business.impl;

import com.moerlong.carloan.common.persistence.dao.UserMapper;
import com.moerlong.carloan.common.persistence.model.User;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.core.util.Convert;
import com.moerlong.carloan.modular.cust.business.CustomerInfoBusiness;
import com.moerlong.carloan.modular.cust.entity.CustomerInfo;
import com.moerlong.carloan.modular.cust.entity.vo.CustAddHistoryInfoVo;
import com.moerlong.carloan.modular.cust.entity.vo.FamilyCustInfoPo;
import com.moerlong.carloan.modular.cust.service.CustomerInfoService;
import com.moerlong.carloan.modular.cust.service.FamilyBookSubInfoService;
import com.moerlong.carloan.modular.loan.dao.ApplyOperatorDao;
import com.moerlong.carloan.modular.loan.dao.ProcessNodeDao;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.entity.ApplyOperator;
import com.moerlong.carloan.modular.loan.entity.MainApproveRecord;
import com.moerlong.carloan.modular.loan.entity.ProcessNode;
import com.moerlong.carloan.modular.loan.entity.constants.LoanConts;
import com.moerlong.carloan.modular.loan.entity.enums.MainStatus;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import com.moerlong.carloan.modular.loan.service.MainApproveRecordService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CustomerInfoBusinessImpl implements CustomerInfoBusiness{

    private final Logger log = LoggerFactory.getLogger(CustomerInfoBusinessImpl.class);

    @Autowired
    private CustomerInfoService customerInfoService;
    @Autowired
    private ApplyInfoService applyInfoService;
    @Autowired
    private MainApproveRecordService mainApproveRecordService;
    @Autowired
    private FamilyBookSubInfoService familyBookSubInfoService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ProcessNodeDao processNodeDao;
    @Resource
    private ApplyOperatorDao applyOperatorDao;

    /**
     * 新增客户历史查询
     * @param name
     * @param idcard
     * @return
     */
    @Override
    @Transactional
    public Object searchCustHistory(String name, String idcard) {

        try{
            Map<String, Object> ret = new HashMap<>();
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            BigDecimal totalAmount = new BigDecimal(0);
            boolean applyFlag = true;          //是否可贷款
            BigDecimal leftAmount = new BigDecimal(0);  //剩余可贷款金额
            String refuseMsg = null;
            int days = 0;

            boolean repaymentFlag = false;          //是否有还款中的信息
            boolean protectFlag = false;            //是否有申请中的信息
            boolean finishFlag = true;              //是否全部都是完成信息


            if(StringUtils.isBlank(name) || StringUtils.isBlank(idcard)){
                log.error("===>[searchCustHistory] param is null, name={} idcard={}", name, idcard);
                return ResultVO.build(ErrorCode.PARAM_EMPTY);
            }

            //查询出订单信息(个人的和家庭的）
            List<CustAddHistoryInfoVo> totalList = new LinkedList<>();
            List<CustAddHistoryInfoVo> selfList = customerInfoService.searchCustHistory(idcard);
            List<CustAddHistoryInfoVo> familyList = new LinkedList<>();

            List<FamilyCustInfoPo> familyBookSublist = familyBookSubInfoService.selectCustByCertId(idcard);
            for (FamilyCustInfoPo fa : familyBookSublist) {
                if(!fa.getCertId().equals(idcard)){
                    //去掉客户自己
                    List<CustAddHistoryInfoVo> sub = customerInfoService.searchCustHistory(fa.getCertId());
                    for (CustAddHistoryInfoVo vo : sub) {
                        vo.setRelationship(fa.getRelationship());
                    }
                    familyList.addAll(sub);
                }
            }

            totalList.addAll(selfList);
            totalList.addAll(familyList);
            ret.put("list", totalList);

            //对于个人信息
            for (CustAddHistoryInfoVo infoVo : selfList) {
                infoVo.setRelationship("本人");
                if (infoVo.getStatus() < MainStatus.SIGN_CONTRACT.getValue()&&infoVo.getIsDeleted()!=1) {
                    protectFlag = true;
                    Date applyDate = sdf.parse(sdf.format(infoVo.getLoanDate()));
                    Date nowDate = sdf.parse(sdf.format(new Date()));
                    days = (int)((nowDate.getTime() - applyDate.getTime())/(1000*3600*24));
                }

                if(infoVo.getStatus() != MainStatus.FINISH.getValue()&&infoVo.getIsDeleted()!=1){
                    finishFlag = false;
                }
            }
            if(protectFlag == true){
                if(days <= LoanConts.MAX_CHANNEL_PROTECT_DAYS){
                    applyFlag = false;
                    refuseMsg = "已有渠道申请，渠道保护生效中，剩余保护时间为:"+(LoanConts.MAX_CHANNEL_PROTECT_DAYS-days)+"天";
                    ret.put("leftAmount", 0);
                    ret.put("applyFlag", applyFlag);
                    ret.put("refuseMsg", refuseMsg);
                    return ResultVO.build(ErrorCode.SUCCESS, ret);
                }
            }
            if(finishFlag == false){
                //自己的贷款正在办理汇总
                applyFlag = false;
                refuseMsg = "有贷款记录正在办理中";
                ret.put("leftAmount", 0);
                ret.put("applyFlag", applyFlag);
                ret.put("refuseMsg", refuseMsg);
                return ResultVO.build(ErrorCode.SUCCESS, ret);
            }

            //对于家庭信息
            for (CustAddHistoryInfoVo infoVo : familyList) {
                //如果有借款记录，且是还款中
                if(infoVo.getStatus() >= MainStatus.PAY_SUCCESS.getValue() && infoVo.getStatus() != MainStatus.FINISH.getValue()){
                    repaymentFlag = true;
                    totalAmount = totalAmount.add(infoVo.getLeftCapital());
                }
            }

            leftAmount = new BigDecimal(LoanConts.MAX_APPLY_AMOUNT).subtract(totalAmount);
            if(repaymentFlag == true){
                //有还款中的
                if(leftAmount.compareTo(new BigDecimal(LoanConts.MIN_APPLY_AMOUNT)) < 0){
                    applyFlag = false;
                    refuseMsg = "以家庭为单位剩余可贷金额不足5万，剩余可贷金额:"+leftAmount;
                    ret.put("leftAmount", leftAmount);
                    ret.put("applyFlag", applyFlag);
                    ret.put("refuseMsg", refuseMsg);
                    return ResultVO.build(ErrorCode.SUCCESS, ret);
                }else if(leftAmount.compareTo(new BigDecimal(LoanConts.MIN_APPLY_AMOUNT)) > 0&&finishFlag==true){
                    //有且已完成还清
                    applyFlag = true;
                    refuseMsg = "申请人贷款已结清。可申请本次剩余贷款金额为:"+leftAmount;
                    ret.put("leftAmount", leftAmount);
                    ret.put("applyFlag", applyFlag);
                    ret.put("refuseMsg", refuseMsg);
                    return ResultVO.build(ErrorCode.SUCCESS, ret);
                }else if(leftAmount.compareTo(new BigDecimal(LoanConts.MIN_APPLY_AMOUNT)) > 0&&finishFlag==false){
                    //有且未还完但有剩余金额
                    applyFlag = true;
                    refuseMsg = "申请人家庭在我司有贷款记录且还款中，本次受理最高可贷金额为:"+leftAmount;
                    ret.put("leftAmount", leftAmount);
                    ret.put("applyFlag", applyFlag);
                    ret.put("refuseMsg", refuseMsg);
                    return ResultVO.build(ErrorCode.SUCCESS, ret);
                }
            }

            ret.put("applyFlag", applyFlag);
            ret.put("leftAmount", leftAmount);
            ret.put("refuseMsg", "可申请办理贷款");

            return ResultVO.build(ErrorCode.SUCCESS, ret);

        }catch(Exception e){
            log.error("===>[searchCustHistory] exception e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 新增客户
     * @param name
     * @param idcard
     * @return
     */
    @Override
    @Transactional
    public Object custAddInfo(Long operatorId, String operatorName, String name, String idcard,BigDecimal leftAmount){

        try {

            if(StringUtils.isBlank(name) || StringUtils.isBlank(idcard)){
                log.error("===>[custAddInfo] param is null, name={} idcard={}", name, idcard);
                return ResultVO.build(ErrorCode.PARAM_EMPTY);
            }
            Date now = new Date();

            //拿到该操作员的所属部门
            User user = userMapper.selectById(operatorId);

            //对于可申请办理贷款的新增客户信息
            CustomerInfo customerInfo = new CustomerInfo();
            customerInfo.setName(name);
            customerInfo.setCertId(idcard);
            customerInfo.setCreateTime(now);
            customerInfo.setUpdateTime(now);
            customerInfo.setIsDeleted(0);
            customerInfoService.save(customerInfo);

            //新增订单信息
            ApplyInfo applyInfo = new ApplyInfo();
            applyInfo.setCustId(customerInfo.getId());
            applyInfo.setStatus(MainStatus.CREATE.getValue());
            applyInfo.setStatusDesc(MainStatus.CREATE.getDesc());
            applyInfo.setDeptId(Long.valueOf(Convert.toStrArray(",", user.getDeptid())[0]));
            applyInfo.setCreateTime(now);
            applyInfo.setLeftAmount(leftAmount);
            applyInfo.setUpdateTime(now);
            applyInfo.setIsDeleted(0);
            applyInfo.setNqlrStatus(1);
            applyInfo.setYcStatus(1);
            applyInfoService.save(applyInfo);

            ProcessNode processNode = processNodeDao.selectByStatus(applyInfo.getStatus());

            // 写入借款操作员表
            ApplyOperator applyOperator = new ApplyOperator();
            applyOperator.setApplyId(applyInfo.getId());
            applyOperator.setRoleId(processNode.getRoleId());
            applyOperator.setUserId(operatorId);
            applyOperator.setProcessNodeId(processNode.getId());
            applyOperator.setCreateTime(now);
            applyOperator.setUpdateTime(now);
            applyOperator.setIsDeleted(0);
            applyOperatorDao.save(applyOperator);

            //添加主流程审批记录
            MainApproveRecord record = new MainApproveRecord();
            record.setApplyId(applyInfo.getId());
            record.setOperatorId(operatorId);
            record.setOperatorName(operatorName);
            record.setOperatorTime(now);
            record.setProcessNodeId(processNode.getId());
            record.setProcessNodeDesc(processNode.getProcessStatusDesc());
            record.setCreateTime(now);
            record.setUpdateTime(now);
            record.setIsDeleted(0);
            mainApproveRecordService.save(record);
        }catch(Exception e){
            log.error("===>[custAddInfo] exception e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }

        return ResultVO.build(ErrorCode.SUCCESS);
    }
}
