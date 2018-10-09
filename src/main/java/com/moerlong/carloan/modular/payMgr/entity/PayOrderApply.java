package com.moerlong.carloan.modular.payMgr.entity;

import java.math.BigDecimal;
import java.util.Date;

/***
 * 代付订单审核申请
 */
public class PayOrderApply {

    /****/

    private Long id;


    /**用户id**/

    private String userUuid;

    /**用户名称**/

    private String userName;

    /**用户部门ID**/

    private String deptId;

    /**审核批次号**/

    private String batchNo;


    /**卡号**/

    private String accNo;


    /**卡用户名**/

    private String accName;


    /**代付金额**/

    private BigDecimal amount;


    /**手机号**/

    private String mobile;


    /**开户省**/

    private String openProvince;


    /**开户市**/

    private String openCity;


    /**支行名称**/

    private String bankBranch;


    /****/

    private String idType;


    /**身份证**/

    private String idNo;


    /**代付状态**/

    private String state;


    /**银行名**/

    private String bankName;


    /**货币**/

    private String cny;


    /**备注**/

    private String remark;


    /**部门主管ID**/

    private String puserUuid;

    /**父级用户名*/

    private String puserName;

    /****/

    private Date createTime;


    /****/

    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCny() {
        return cny;
    }

    public void setCny(String cny) {
        this.cny = cny;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPuserUuid() {
        return puserUuid;
    }

    public void setPuserUuid(String puserUuid) {
        this.puserUuid = puserUuid;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getPuserName() {
        return puserName;
    }

    public void setPuserName(String puserName) {
        this.puserName = puserName;
    }
}
