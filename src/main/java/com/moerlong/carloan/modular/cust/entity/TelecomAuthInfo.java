package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;
public class TelecomAuthInfo {
    /** 主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 类型 0--主借人 1--共借人 */
    private Integer type;
    /** 是否实名认证 0--否 1--是 */
    private Integer isRealAuth;
    /** 手机归属地 */
    private String nativePlace;
    /** 入网时长（单位：月） */
    private Integer inTime;
    /** 近三个月通话活跃天数(单位：天) */
    private Integer active3m;
    /** 近六个月通话活跃天数(单位：天) */
    private Integer active6m;
    /** 魔蝎账单详情附件地址 */
    private String billDetailUrl;
    /** 魔蝎报告附件地址 */
    private String reportUrl;
    /** 兼容报告附件地址 */
    private String compatibleReportUrl;
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
    public Integer getIsRealAuth() {
        return this.isRealAuth;
    }
    public void setIsRealAuth(Integer isRealAuth) {
        this.isRealAuth=isRealAuth;
    }
    public String getNativePlace() {
        return this.nativePlace;
    }
    public void setNativePlace(String nativePlace) {
        this.nativePlace=nativePlace;
    }
    public Integer getInTime() {
        return this.inTime;
    }
    public void setInTime(Integer inTime) {
        this.inTime=inTime;
    }
    public Integer getActive3m() {
        return this.active3m;
    }
    public void setActive3m(Integer active3m) {
        this.active3m=active3m;
    }
    public Integer getActive6m() {
        return this.active6m;
    }
    public void setActive6m(Integer active6m) {
        this.active6m=active6m;
    }
    public String getBillDetailUrl() {
        return this.billDetailUrl;
    }
    public void setBillDetailUrl(String billDetailUrl) {
        this.billDetailUrl=billDetailUrl;
    }
    public String getReportUrl() {
        return this.reportUrl;
    }
    public void setReportUrl(String reportUrl) {
        this.reportUrl=reportUrl;
    }
    public String getCompatibleReportUrl() {
        return this.compatibleReportUrl;
    }
    public void setCompatibleReportUrl(String compatibleReportUrl) {
        this.compatibleReportUrl=compatibleReportUrl;
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

