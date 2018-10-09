package com.moerlong.carloan.modular.payMgr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Date;

public class PayDetailInfo {

    private Long id;

    private Long payId;
    
    private Long deptId;

    private Integer payNum;

    private String custMobile;

    private BigDecimal curPayAmount;

    private String bankName;

    private String bankCardNo;

    private Integer payStatus;

    private Integer payType;

    private Integer payingNum;

    private String serialNo;

    private String payCode;

    private String payMsg;

    private String payChannel;

    private Long operatorId;

    private String operatorName;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operatorDate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    private String remark;

    private Integer isDeleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public Integer getPayNum() {
        return payNum;
    }

    public void setPayNum(Integer payNum) {
        this.payNum = payNum;
    }

    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile;
    }

    public BigDecimal getCurPayAmount() {
        return curPayAmount;
    }

    public void setCurPayAmount(BigDecimal curPayAmount) {
        this.curPayAmount = curPayAmount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayingNum() {
        return payingNum;
    }

    public void setPayingNum(Integer payingNum) {
        this.payingNum = payingNum;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Date getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(Date operatorDate) {
        this.operatorDate = operatorDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayMsg() {
        return payMsg;
    }

    public void setPayMsg(String payMsg) {
        this.payMsg = payMsg;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }
    
    public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	@Override
    public String toString() {
        return "PayDetailInfo{" +
                "id=" + id +
                ", payId=" + payId +
                ", payNum=" + payNum +
                ", custMobile='" + custMobile + '\'' +
                ", curPayAmount=" + curPayAmount +
                ", bankName='" + bankName + '\'' +
                ", bankCardNo='" + bankCardNo + '\'' +
                ", payStatus=" + payStatus +
                ", payType=" + payType +
                ", payingNum=" + payingNum +
                ", serialNo='" + serialNo + '\'' +
                ", payCode='" + payCode + '\'' +
                ", payMsg='" + payMsg + '\'' +
                ", payChannel='" + payChannel + '\'' +
                ", operatorId=" + operatorId +
                ", operatorName='" + operatorName + '\'' +
                ", operatorDate=" + operatorDate +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
