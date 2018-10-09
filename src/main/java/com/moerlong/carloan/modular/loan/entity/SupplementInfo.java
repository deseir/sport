package com.moerlong.carloan.modular.loan.entity;
import java.math.*;
import java.util.*;
public class SupplementInfo {
    /** 主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 补充材料说明 */
    private String supplementDesc;
    /** 发起人id */
    private Long initiator;
    /** 指定人id */
    private Long nominee;
    /** 补充材料附件地址 */
    private String supplementAttachUrl;
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
    public Long getApplyId() {
        return this.applyId;
    }
    public void setApplyId(Long applyId) {
        this.applyId=applyId;
    }
    public String getSupplementDesc() {
        return this.supplementDesc;
    }
    public void setSupplementDesc(String supplementDesc) {
        this.supplementDesc=supplementDesc;
    }
    public Long getInitiator() {
        return this.initiator;
    }
    public void setInitiator(Long initiator) {
        this.initiator=initiator;
    }
    public Long getNominee() {
        return this.nominee;
    }
    public void setNominee(Long nominee) {
        this.nominee=nominee;
    }
    public String getSupplementAttachUrl() {
        return this.supplementAttachUrl;
    }
    public void setSupplementAttachUrl(String supplementAttachUrl) {
        this.supplementAttachUrl=supplementAttachUrl;
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

