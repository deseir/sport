package com.moerlong.carloan.modular.loan.entity;
import java.math.*;
import java.util.*;
public class RoleApply {
    /** 主键 */
    private Long id;
    /** 角色代码 */
    private String roleCode;
    /** 当前状态 */
    private Integer status;
    /** 是否可读 0--否 1--是 */
    private Integer readAble;
    /** 是否可编辑 0--否 1--是 */
    private Integer editAble;
    /** 是否可审批 */
    private Integer approveAble;
    /** 是否可补充附件 */
    private Integer supplementAble;
    /** 扩展权限1 */
    private Integer authExt1Able;
    /** 扩展权限2 */
    private Integer authExt2Able;
    /** 扩展权限3 */
    private Integer authExt3Able;
    /** 后续状态节点(json格式) */
    private String nextStatus;
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id=id;
    }
    public String getRoleCode() {
        return this.roleCode;
    }
    public void setRoleCode(String roleCode) {
        this.roleCode=roleCode;
    }
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status=status;
    }
    public Integer getReadAble() {
        return this.readAble;
    }
    public void setReadAble(Integer readAble) {
        this.readAble=readAble;
    }
    public Integer getEditAble() {
        return this.editAble;
    }
    public void setEditAble(Integer editAble) {
        this.editAble=editAble;
    }
    public Integer getApproveAble() {
        return this.approveAble;
    }
    public void setApproveAble(Integer approveAble) {
        this.approveAble=approveAble;
    }
    public Integer getSupplementAble() {
        return this.supplementAble;
    }
    public void setSupplementAble(Integer supplementAble) {
        this.supplementAble=supplementAble;
    }
    public Integer getAuthExt1Able() {
        return this.authExt1Able;
    }
    public void setAuthExt1Able(Integer authExt1Able) {
        this.authExt1Able=authExt1Able;
    }
    public Integer getAuthExt2Able() {
        return this.authExt2Able;
    }
    public void setAuthExt2Able(Integer authExt2Able) {
        this.authExt2Able=authExt2Able;
    }
    public Integer getAuthExt3Able() {
        return this.authExt3Able;
    }
    public void setAuthExt3Able(Integer authExt3Able) {
        this.authExt3Able=authExt3Able;
    }
    public String getNextStatus() {
        return this.nextStatus;
    }
    public void setNextStatus(String nextStatus) {
        this.nextStatus=nextStatus;
    }
}

