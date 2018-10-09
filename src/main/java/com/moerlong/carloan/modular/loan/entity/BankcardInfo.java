package com.moerlong.carloan.modular.loan.entity;
import java.math.*;
import java.util.*;
public class BankcardInfo {
    /** 主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 银行卡号 */
    private String cardNo;
    /** 银行名 */
    private String bankName;
    /** 开户省市 */
    private String provinceCity;
    /** 开户支行 */
    private String bankSubbranch;
    /** 银行卡状态 0--失败 1--成功 2--创建 3--发送验证码成功 4--发送验证码失败 5--验证失败 */
    private Integer cardStatus;
    /** 绑卡时间 */
    private Date bindingTime;
    /** 绑卡描述 */
    private String bindingDesc;
    /** 流水号 */
    private String sequenceNo;
    /** 是否是默认银行卡 */
    private Integer isDefaultCard;
    /** 银行预留手机号 */
    private String bindingMobile;
    /** 支付平台 */
    private String payPlatform;
    /** 支付平台返回码 */
    private String resultCode;
    /** 支付平台返回消息 */
    private String resultMsg;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 是否删除 0--否 1--是 */
    private Integer isDeleted;
    /** 备注，说明 */
    private String remark;
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id=id;
    }
    public Long getCustId() {
        return this.custId;
    }
    public void setCustId(Long custId) {
        this.custId=custId;
    }
    public String getCardNo() {
        return this.cardNo;
    }
    public void setCardNo(String cardNo) {
        this.cardNo=cardNo;
    }
    public String getBankName() {
        return this.bankName;
    }
    public void setBankName(String bankName) {
        this.bankName=bankName;
    }
    public String getProvinceCity() {
        return this.provinceCity;
    }
    public void setProvinceCity(String provinceCity) {
        this.provinceCity=provinceCity;
    }
    public String getBankSubbranch() {
        return this.bankSubbranch;
    }
    public void setBankSubbranch(String bankSubbranch) {
        this.bankSubbranch=bankSubbranch;
    }
    public Integer getCardStatus() {
        return this.cardStatus;
    }
    public void setCardStatus(Integer cardStatus) {
        this.cardStatus=cardStatus;
    }
    public Date getBindingTime() {
        return this.bindingTime;
    }
    public void setBindingTime(Date bindingTime) {
        this.bindingTime=bindingTime;
    }
    public String getBindingDesc() {
        return this.bindingDesc;
    }
    public void setBindingDesc(String bindingDesc) {
        this.bindingDesc=bindingDesc;
    }
    public String getSequenceNo() {
        return this.sequenceNo;
    }
    public void setSequenceNo(String sequenceNo) {
        this.sequenceNo=sequenceNo;
    }
    public Integer getIsDefaultCard() {
        return this.isDefaultCard;
    }
    public void setIsDefaultCard(Integer isDefaultCard) {
        this.isDefaultCard=isDefaultCard;
    }
    public String getBindingMobile() {
        return this.bindingMobile;
    }
    public void setBindingMobile(String bindingMobile) {
        this.bindingMobile=bindingMobile;
    }
    public String getPayPlatform() {
        return this.payPlatform;
    }
    public void setPayPlatform(String payPlatform) {
        this.payPlatform=payPlatform;
    }
    public String getResultCode() {
        return this.resultCode;
    }
    public void setResultCode(String resultCode) {
        this.resultCode=resultCode;
    }
    public String getResultMsg() {
        return this.resultMsg;
    }
    public void setResultMsg(String resultMsg) {
        this.resultMsg=resultMsg;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime=createTime;
    }
    public Date getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime=updateTime;
    }
    public Integer getIsDeleted() {
        return this.isDeleted;
    }
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted=isDeleted;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark=remark;
    }
}

