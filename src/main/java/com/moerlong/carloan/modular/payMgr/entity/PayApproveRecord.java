package com.moerlong.carloan.modular.payMgr.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PayApproveRecord {

    private Long id;

    private Long payId;

    private Long operatorId;

    private String operatorName;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operatorTime;

    private Integer operatorResult;

    private String operatorTip;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;

    private String remark;

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

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public Integer getOperatorResult() {
        return operatorResult;
    }

    public void setOperatorResult(Integer operatorResult) {
        this.operatorResult = operatorResult;
    }

    public String getOperatorTip() {
        return operatorTip;
    }

    public void setOperatorTip(String operatorTip) {
        this.operatorTip = operatorTip;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public String toString() {
        return "PayApproveRecord{" +
                "id=" + id +
                ", payId=" + payId +
                ", operatorId=" + operatorId +
                ", operatorTime=" + operatorTime +
                ", operatorResult=" + operatorResult +
                ", operatorTip='" + operatorTip + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                ", remark='" + remark + '\'' +
                '}';
    }
}
