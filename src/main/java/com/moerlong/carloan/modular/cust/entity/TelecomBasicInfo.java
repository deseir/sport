package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;
public class TelecomBasicInfo {
    /** 主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 类型 0--主借人 1--共借人 */
    private Integer type;
    /** 手机号 */
    private String mobile;
    /** 运营商认证task_id */
    private String auditTaskId;
    /** 运营商认证状态 2--认证中 3--认证完成 4--认证失败 5--认证失效 */
    private Integer auditStatus;
    /** 认证时间 */
    private Date auditTime;
    /** 过期时间 */
    private Date expireTime;
    /** 运营商认证结果 */
    private String auditResult;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
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
    public Long getApplyId() {
        return this.applyId;
    }
    public void setApplyId(Long applyId) {
        this.applyId=applyId;
    }
    public Integer getType() {
        return this.type;
    }
    public void setType(Integer type) {
        this.type=type;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile=mobile;
    }
    public String getAuditTaskId() {
        return this.auditTaskId;
    }
    public void setAuditTaskId(String auditTaskId) {
        this.auditTaskId=auditTaskId;
    }
    public Integer getAuditStatus() {
        return this.auditStatus;
    }
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus=auditStatus;
    }
    public Date getAuditTime() {
        return this.auditTime;
    }
    public void setAuditTime(Date auditTime) {
        this.auditTime=auditTime;
    }
    public Date getExpireTime() {
        return this.expireTime;
    }
    public void setExpireTime(Date expireTime) {
        this.expireTime=expireTime;
    }
    public String getAuditResult() {
        return this.auditResult;
    }
    public void setAuditResult(String auditResult) {
        this.auditResult=auditResult;
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

