package com.moerlong.carloan.modular.loan.entity;

import java.util.Date;

public class ApplyOperator {

    /**
     *自增主键
     */
    private Long id;

    /**
     *借款单ID
     */
    private Long applyId;

    /**
     *角色ID
     */
    private Long roleId;

    /**
     *用户ID
     */
    private Long userId;

    /**
     *流程节点ID
     */
    private Long processNodeId;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *更新时间
     */
    private Date updateTime;

    /**
     *备注，说明
     */
    private String remark;

    /**
     *是否删除 0-否 1-是
     */
    private Integer isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProcessNodeId() {
        return processNodeId;
    }

    public void setProcessNodeId(Long processNodeId) {
        this.processNodeId = processNodeId;
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
