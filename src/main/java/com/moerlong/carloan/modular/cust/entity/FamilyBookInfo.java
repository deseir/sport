package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;
public class FamilyBookInfo {
    /** 自增主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 申请人与户主关系 */
    private String relationship;
    /** 户主姓名 */
    private String masterName;
    /** 性别 0-女 1-男 */
    private Integer masterSex;
    /** 户主身份证号码 */
    private String certId;
    /** 户口本首页照片 */
    private String firstPagePhotoUrl;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 备注，说明 */
    private String remark;
    /** 是否删除 0-否 1-是 */
    private Integer isDeleted;
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
    public String getRelationship() {
        return this.relationship;
    }
    public void setRelationship(String relationship) {
        this.relationship=relationship;
    }
    public String getMasterName() {
        return this.masterName;
    }
    public void setMasterName(String masterName) {
        this.masterName=masterName;
    }
    public Integer getMasterSex() {
        return this.masterSex;
    }
    public void setMasterSex(Integer masterSex) {
        this.masterSex=masterSex;
    }
    public String getCertId() {
        return this.certId;
    }
    public void setCertId(String certId) {
        this.certId=certId;
    }
    public String getFirstPagePhotoUrl() {
        return this.firstPagePhotoUrl;
    }
    public void setFirstPagePhotoUrl(String firstPagePhotoUrl) {
        this.firstPagePhotoUrl=firstPagePhotoUrl;
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
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark=remark;
    }
    public Integer getIsDeleted() {
        return this.isDeleted;
    }
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted=isDeleted;
    }
}

