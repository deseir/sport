package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;
public class FamilyBookSubInfo {
    /** 自增主键 */
    private Long id;
    /** 户口本id */
    private Long bookId;
    /** 申请人与关联人关系 */
    private String relationship;
    /** 关联人姓名 */
    private String name;
    /** 关联人性别 0-女 1-男 */
    private Integer sex;
    /** 关联人身份证号码 */
    private String certId;
    /** 关联人户口页照片 */
    private String bookPhotoUrl;
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
    public Long getBookId() {
        return this.bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId=bookId;
    }
    public String getRelationship() {
        return this.relationship;
    }
    public void setRelationship(String relationship) {
        this.relationship=relationship;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public Integer getSex() {
        return this.sex;
    }
    public void setSex(Integer sex) {
        this.sex=sex;
    }
    public String getCertId() {
        return this.certId;
    }
    public void setCertId(String certId) {
        this.certId=certId;
    }
    public String getBookPhotoUrl() {
        return this.bookPhotoUrl;
    }
    public void setBookPhotoUrl(String bookPhotoUrl) {
        this.bookPhotoUrl=bookPhotoUrl;
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

