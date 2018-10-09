package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;
public class MarryInfo {
    /** 自增主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 婚姻状况 0--已婚 1--未婚 2--再婚 3--离异 4--丧偶 */
    private Integer marryStatus;
    /** 婚姻登记日期（如果是已婚，再婚，丧偶） */
    private Date marryDate;
    /** 配偶姓名（如果是已婚，再婚，丧偶） */
    private String spouseName;
    /** 配偶性别 0-女 1-男（如果是已婚，再婚，丧偶） */
    private Integer spouseSex;
    /** 配偶身份证号码（如果是已婚，再婚，丧偶） */
    private String spouseCertId;
    /** 配偶身份证有效期起始日期 */
    private Date validateBegin;
    /** 配偶身份证有效期结束日期 */
    private Date validateEnd;
    /** 配偶签发机关 */
    private String signOrg;
    /** 配偶身份证正面图片地址 */
    private String idFrontPhotoUrl;
    /** 配偶身份证背面图片地址 */
    private String idBackPhotoUrl;
    /** 结婚证照片地址 */
    private String marryPhotoUrl;
    /** 离婚登记日期（如果是离异） */
    private Date divorceDate;
    /** 原配偶名称（如果是离异） */
    private String divorceName;
    /** 原配偶性别 0-女 1-男（如果是离异） */
    private Integer divorceSex;
    /** 原配偶身份证号码（如果是离异） */
    private String divorceCertId;
    /** 离婚证照片(如果是离异) */
    private String divorcePhotoUrl;
    /** 死亡证明照片地址(如果是丧偶) */
    private String deathCertPhotoUrl;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 备注，说明 */
    private String remark;
    /** 是否删除 0-否 1-是 */
    private Integer isDeleted;
    /** 配偶手机号 */
    private String spousePhone;
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
    public Integer getMarryStatus() {
        return this.marryStatus;
    }
    public void setMarryStatus(Integer marryStatus) {
        this.marryStatus=marryStatus;
    }
    public Date getMarryDate() {
        return this.marryDate;
    }
    public void setMarryDate(Date marryDate) {
        this.marryDate=marryDate;
    }
    public String getSpouseName() {
        return this.spouseName;
    }
    public void setSpouseName(String spouseName) {
        this.spouseName=spouseName;
    }
    public Integer getSpouseSex() {
        return this.spouseSex;
    }
    public void setSpouseSex(Integer spouseSex) {
        this.spouseSex=spouseSex;
    }
    public String getSpouseCertId() {
        return this.spouseCertId;
    }
    public void setSpouseCertId(String spouseCertId) {
        this.spouseCertId=spouseCertId;
    }
    public Date getValidateBegin() {
        return this.validateBegin;
    }
    public void setValidateBegin(Date validateBegin) {
        this.validateBegin=validateBegin;
    }
    public Date getValidateEnd() {
        return this.validateEnd;
    }
    public void setValidateEnd(Date validateEnd) {
        this.validateEnd=validateEnd;
    }
    public String getSignOrg() {
        return this.signOrg;
    }
    public void setSignOrg(String signOrg) {
        this.signOrg=signOrg;
    }
    public String getIdFrontPhotoUrl() {
        return this.idFrontPhotoUrl;
    }
    public void setIdFrontPhotoUrl(String idFrontPhotoUrl) {
        this.idFrontPhotoUrl=idFrontPhotoUrl;
    }
    public String getIdBackPhotoUrl() {
        return this.idBackPhotoUrl;
    }
    public void setIdBackPhotoUrl(String idBackPhotoUrl) {
        this.idBackPhotoUrl=idBackPhotoUrl;
    }
    public String getMarryPhotoUrl() {
        return this.marryPhotoUrl;
    }
    public void setMarryPhotoUrl(String marryPhotoUrl) {
        this.marryPhotoUrl=marryPhotoUrl;
    }
    public Date getDivorceDate() {
        return this.divorceDate;
    }
    public void setDivorceDate(Date divorceDate) {
        this.divorceDate=divorceDate;
    }
    public String getDivorceName() {
        return this.divorceName;
    }
    public void setDivorceName(String divorceName) {
        this.divorceName=divorceName;
    }
    public Integer getDivorceSex() {
        return this.divorceSex;
    }
    public void setDivorceSex(Integer divorceSex) {
        this.divorceSex=divorceSex;
    }
    public String getDivorceCertId() {
        return this.divorceCertId;
    }
    public void setDivorceCertId(String divorceCertId) {
        this.divorceCertId=divorceCertId;
    }
    public String getDivorcePhotoUrl() {
        return this.divorcePhotoUrl;
    }
    public void setDivorcePhotoUrl(String divorcePhotoUrl) {
        this.divorcePhotoUrl=divorcePhotoUrl;
    }
    public String getDeathCertPhotoUrl() {
        return this.deathCertPhotoUrl;
    }
    public void setDeathCertPhotoUrl(String deathCertPhotoUrl) {
        this.deathCertPhotoUrl=deathCertPhotoUrl;
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

    public String getSpousePhone() {
        return spousePhone;
    }

    public void setSpousePhone(String spousePhone) {
        this.spousePhone = spousePhone;
    }
}

