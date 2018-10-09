package com.moerlong.carloan.common.persistence.model;

import com.moerlong.carloan.modular.paybackMgr.entity.CostOrderApply;
import com.moerlong.carloan.util.CalculateNumberUtils;
import com.moerlong.carloan.util.IDGenerator;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by zhaoyuchong on 2017/4/19.
 */
public class CommonElement {
    private String batchNo;//批次号，前端订单唯一标识
    private String userUuid;    //	用户accountId，可为空
    private String accNo;    //银行卡号
    private String accName;    //	用户名称
    private String mobile;    //	用户手机号
    private String idNo;    //	用户身份证号
    private String openProvince;//	客户开户行所在省
    private String openCity; //	客户开户行所在市
    private String bankBranch;    //	客户开户支行名称
    private String bankName;    //	银行名称
    private String idType;//	用户证件类型，默认值为id
    private String smsCode;    //	请求验证码值为空
    private String amount;    //	支付金额
    private String remark;    //	备注
    private String sn;
    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getOpenProvince() {
        return openProvince;
    }

    public void setOpenProvince(String openProvince) {
        this.openProvince = openProvince;
    }

    public String getOpenCity() {
        return openCity;
    }

    public void setOpenCity(String openCity) {
        this.openCity = openCity;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * 获取用户的年龄.
     */
    private int getUserAge() {
        return CalculateNumberUtils.getUserAge(this.idNo);
    }

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

    public static CommonElement getCommonElement(CostOrderApply costOrderApply) {

        String bankAccount = costOrderApply.getAccNo();
        String bankAccountName = costOrderApply.getAccName();
        String tradeAmount = String.valueOf(costOrderApply.getAmount());
        String idNum = costOrderApply.getIdNo();
        String custTel = costOrderApply.getMobile();
        String bankName = costOrderApply.getBankName();
        String userUuid = "payfront"+costOrderApply.getUserUuid();
        String batchNo = costOrderApply.getBatchNo();
        String remark=costOrderApply.getRemark();

        CommonElement commonElement = new CommonElement();
        if (StringUtils.isBlank(batchNo)) {
            batchNo = IDGenerator.getNumUUID();
            commonElement.setBatchNo(batchNo);
        } else {
            commonElement.setBatchNo(batchNo);
        }
        if (!StringUtils.isBlank(bankAccount)) {
            commonElement.setAccNo(bankAccount);
        }
        if (!StringUtils.isBlank(bankAccountName)) {
            commonElement.setAccName(bankAccountName);
        }

        if (!StringUtils.isBlank(custTel)) {
            commonElement.setMobile(custTel);
        }
        if (!StringUtils.isBlank(userUuid)) {
            commonElement.setUserUuid(userUuid);
        }

        if (!StringUtils.isBlank(idNum)) {
            commonElement.setIdNo(idNum);
        }

        if (!StringUtils.isBlank(tradeAmount)) {
            commonElement.setAmount(tradeAmount);
        }
        if (!StringUtils.isBlank(bankName)) {
            commonElement.setBankName(bankName);
        }
        if (!StringUtils.isBlank(bankName)) {
            commonElement.setRemark(remark);
        }

        commonElement.setIdType("id");

        return commonElement;
    }

}
