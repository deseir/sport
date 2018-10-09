package com.moerlong.carloan.modular.car.entity;
import java.math.*;
import java.util.*;
public class CarTrafficInsureInfo {
    /** 主键，自增 */
    private Long id;
    /** 车辆id */
    private Long carId;
    /** 购买机构全称 */
    private String instFullName;
    /** 业务来源 */
    private String bussinessSource;
    /** 代理点名称 */
    private String proxyName;
    /** 保单号 */
    private String insureNumber;
    /** 被保人 */
    private String insurePerson;
    /** 保险起始日期 */
    private Date insureBeginTime;
    /** 保险到期日期 */
    private Date insureEndTime;
    /** 浮动比例 */
    private BigDecimal floatProp;
    /** 保费合计 */
    private BigDecimal totalAmount;
    /** 代收车船税 */
    private BigDecimal vehicleTax;
    /** 签约日期 */
    private Date signDate;
    /** 特别约定 */
    private String specialAgreement;
    /** 照片 */
    private String photoUrl;
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
    public Long getCarId() {
        return this.carId;
    }
    public void setCarId(Long carId) {
        this.carId=carId;
    }
    public String getInstFullName() {
        return this.instFullName;
    }
    public void setInstFullName(String instFullName) {
        this.instFullName=instFullName;
    }
    public String getBussinessSource() {
        return this.bussinessSource;
    }
    public void setBussinessSource(String bussinessSource) {
        this.bussinessSource=bussinessSource;
    }
    public String getProxyName() {
        return this.proxyName;
    }
    public void setProxyName(String proxyName) {
        this.proxyName=proxyName;
    }
    public String getInsureNumber() {
        return this.insureNumber;
    }
    public void setInsureNumber(String insureNumber) {
        this.insureNumber=insureNumber;
    }
    public String getInsurePerson() {
        return this.insurePerson;
    }
    public void setInsurePerson(String insurePerson) {
        this.insurePerson=insurePerson;
    }
    public Date getInsureBeginTime() {
        return this.insureBeginTime;
    }
    public void setInsureBeginTime(Date insureBeginTime) {
        this.insureBeginTime=insureBeginTime;
    }
    public Date getInsureEndTime() {
        return this.insureEndTime;
    }
    public void setInsureEndTime(Date insureEndTime) {
        this.insureEndTime=insureEndTime;
    }
    public BigDecimal getFloatProp() {
        return this.floatProp;
    }
    public void setFloatProp(BigDecimal floatProp) {
        this.floatProp=floatProp;
    }
    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount=totalAmount;
    }
    public BigDecimal getVehicleTax() {
        return this.vehicleTax;
    }
    public void setVehicleTax(BigDecimal vehicleTax) {
        this.vehicleTax=vehicleTax;
    }
    public Date getSignDate() {
        return this.signDate;
    }
    public void setSignDate(Date signDate) {
        this.signDate=signDate;
    }
    public String getSpecialAgreement() {
        return this.specialAgreement;
    }
    public void setSpecialAgreement(String specialAgreement) {
        this.specialAgreement=specialAgreement;
    }
    public String getPhotoUrl() {
        return this.photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl=photoUrl;
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

