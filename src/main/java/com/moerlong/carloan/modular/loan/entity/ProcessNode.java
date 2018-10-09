package com.moerlong.carloan.modular.loan.entity;

import java.util.Date;

public class ProcessNode {

    /**
     * '自增主键'
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 流程名称
     */
    private String processName;

    /**
     * 流程对应的状态
     */
    private Integer processStatus;

    /**
     * 流程对应的状态描述
     */
    private String processStatusDesc;

    /**
     * 是否是驳回节点 0--否 1--是
     */
    private Integer isBack;

    /**
     * 是否是同步流程0--否 1--是
     */
    private Integer isSync;

    /**
     * 同步字段名称
     */
    private String syncFieldName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注，说明
     */
    private String remark;

    /**
     * 是否删除 0-否 1-是
     */
    private Integer isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public String getProcessStatusDesc() {
        return processStatusDesc;
    }

    public void setProcessStatusDesc(String processStatusDesc) {
        this.processStatusDesc = processStatusDesc;
    }

    public Integer getIsBack() {
        return isBack;
    }

    public void setIsBack(Integer isBack) {
        this.isBack = isBack;
    }

    public Integer getIsSync() {
        return isSync;
    }

    public void setIsSync(Integer isSync) {
        this.isSync = isSync;
    }

    public String getSyncFieldName() {
        return syncFieldName;
    }

    public void setSyncFieldName(String syncFieldName) {
        this.syncFieldName = syncFieldName;
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
}
