package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;
public class GongjieInfo {
    /** 自增主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 姓名 */
    private String name;
    /** 手机号 */
    private String mobile;
    /** 性别 0-女 1-男 */
    private Integer sex;
    /** 身份证号码 */
    private String certId;
    /** 身份证正面图片地址 */
    private String idFrontPhotoUrl;
    /** 身份证背面图片地址 */
    private String idBackPhotoUrl;
    /** 婚姻状况 0--已婚 1--未婚 2--再婚 3--离异 4--丧偶 */
    private Integer marryStatus;
    /** 与主借人关系 0-配偶 1-父母 2-子女 3-亲属 4-股东 5-朋友 */
    private Integer relation;
    /** 居住地址 */
    private String liveAddress;
    /** 职业性质 1--自雇2--受薪 3--自由职业 */
    private Integer occupationType;
    /** 单位名称 如果收入来源是 1，2，4的话 */
    private String companyName;
    /** 单位性质 0--国有企业 1--国有控股企业 2--外资企业 3--合资企业 4--私营业务 5--事业单位/政府 */
    private Integer companyType;
    /** 单位地址 */
    private String companyAddress;
    /** 单位电话 */
    private String companyTel;
    /** 部门 */
    private String department;
    /** 职务 */
    private String job;
    /** 月均收入（单位：元） */
    private BigDecimal monthIncome;
    /** 单位网查信息附件 */
    private String companyAttachUrl;
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
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile=mobile;
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
    public Integer getMarryStatus() {
        return this.marryStatus;
    }
    public void setMarryStatus(Integer marryStatus) {
        this.marryStatus=marryStatus;
    }
    public Integer getRelation() {
        return this.relation;
    }
    public void setRelation(Integer relation) {
        this.relation=relation;
    }
    public String getLiveAddress() {
        return this.liveAddress;
    }
    public void setLiveAddress(String liveAddress) {
        this.liveAddress=liveAddress;
    }
    public Integer getOccupationType() {
        return this.occupationType;
    }
    public void setOccupationType(Integer occupationType) {
        this.occupationType=occupationType;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName=companyName;
    }
    public Integer getCompanyType() {
        return this.companyType;
    }
    public void setCompanyType(Integer companyType) {
        this.companyType=companyType;
    }
    public String getCompanyAddress() {
        return this.companyAddress;
    }
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress=companyAddress;
    }
    public String getCompanyTel() {
        return this.companyTel;
    }
    public void setCompanyTel(String companyTel) {
        this.companyTel=companyTel;
    }
    public String getDepartment() {
        return this.department;
    }
    public void setDepartment(String department) {
        this.department=department;
    }
    public String getJob() {
        return this.job;
    }
    public void setJob(String job) {
        this.job=job;
    }
    public BigDecimal getMonthIncome() {
        return this.monthIncome;
    }
    public void setMonthIncome(BigDecimal monthIncome) {
        this.monthIncome=monthIncome;
    }
    public String getCompanyAttachUrl() {
        return this.companyAttachUrl;
    }
    public void setCompanyAttachUrl(String companyAttachUrl) {
        this.companyAttachUrl=companyAttachUrl;
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

