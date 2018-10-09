package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;
public class IdAuthInfo {
    /** 主键，自增 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 类型 0--主借人 1--共借人 */
    private Integer type;
    /** 公安部预留用户图片id */
    private String gonganPhotolId;
    /** 是否通过认证 */
    private Integer isIdAuth;
    /** 身份证正面图片地址 */
    private String idFrontPhotoUrl;
    /** 身份证背面图片地址 */
    private String idBackPhotoUrl;
    /** 手持身份证照片地址 */
    private String holdIdentifyPhoto;
    /** 认证时间 */
    private Date authTime;
    /** 姓名 */
    private String userName;
    /** 身份证号码 */
    private String idNumber;
    /** 民族 */
    private String nation;
    /** 住址 */
    private String address;
    /** 发证机关 */
    private String signOrgaization;
    /** 有效期 */
    private String validityPeriod;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 是否删除 0-否 1-是 */
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
    public String getGonganPhotolId() {
        return this.gonganPhotolId;
    }
    public void setGonganPhotolId(String gonganPhotolId) {
        this.gonganPhotolId=gonganPhotolId;
    }
    public Integer getIsIdAuth() {
        return this.isIdAuth;
    }
    public void setIsIdAuth(Integer isIdAuth) {
        this.isIdAuth=isIdAuth;
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
    public String getHoldIdentifyPhoto() {
        return this.holdIdentifyPhoto;
    }
    public void setHoldIdentifyPhoto(String holdIdentifyPhoto) {
        this.holdIdentifyPhoto=holdIdentifyPhoto;
    }
    public Date getAuthTime() {
        return this.authTime;
    }
    public void setAuthTime(Date authTime) {
        this.authTime=authTime;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName=userName;
    }
    public String getIdNumber() {
        return this.idNumber;
    }
    public void setIdNumber(String idNumber) {
        this.idNumber=idNumber;
    }
    public String getNation() {
        return this.nation;
    }
    public void setNation(String nation) {
        this.nation=nation;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address=address;
    }
    public String getSignOrgaization() {
        return this.signOrgaization;
    }
    public void setSignOrgaization(String signOrgaization) {
        this.signOrgaization=signOrgaization;
    }
    public String getValidityPeriod() {
        return this.validityPeriod;
    }
    public void setValidityPeriod(String validityPeriod) {
        this.validityPeriod=validityPeriod;
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

