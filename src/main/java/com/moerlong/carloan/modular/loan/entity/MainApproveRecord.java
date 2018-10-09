package com.moerlong.carloan.modular.loan.entity;
import java.math.*;
import java.util.*;
public class MainApproveRecord {
    /** 主键 */
    private Long id;
    /** 订单id */
    private Long applyId;
    /** 操作员id */
    private Long operatorId;
    /** 操作员姓名 */
    private String operatorName;
    /** 操作时间 */
    private Date operatorTime;

    private Long processNodeId;

    private String processNodeDesc;
    /** 审批意见 */
    private String auditRemark;
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
    public Long getApplyId() {
        return this.applyId;
    }
    public void setApplyId(Long applyId) {
        this.applyId=applyId;
    }
    public Long getOperatorId() {
        return this.operatorId;
    }
    public void setOperatorId(Long operatorId) {
        this.operatorId=operatorId;
    }
    public String getOperatorName() {
        return this.operatorName;
    }
    public void setOperatorName(String operatorName) {
        this.operatorName=operatorName;
    }
    public Date getOperatorTime() {
        return this.operatorTime;
    }
    public void setOperatorTime(Date operatorTime) {
        this.operatorTime=operatorTime;
    }

    public Long getProcessNodeId() {
        return processNodeId;
    }

    public void setProcessNodeId(Long processNodeId) {
        this.processNodeId = processNodeId;
    }

    public String getProcessNodeDesc() {
        return processNodeDesc;
    }

    public void setProcessNodeDesc(String processNodeDesc) {
        this.processNodeDesc = processNodeDesc;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
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

