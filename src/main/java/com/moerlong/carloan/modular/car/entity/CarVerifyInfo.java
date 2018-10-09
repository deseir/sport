package com.moerlong.carloan.modular.car.entity;
import java.math.*;
import java.util.*;
public class CarVerifyInfo {
    /** 自增主键 */
    private Long id;
    /** 车辆id */
    private Long carId;
    /** 车况反馈 */
    private String carCond;
    /** 车况描述意见 */
    private String suggestion;
    /** 配置表附件地址 */
    private String configTablePhoto;
    /** 维修保养状况附件地址 */
    private String maintainPhoto;
    /** 二手车市场评估价格 */
    private BigDecimal carAssessmentPrice;
    /** 漆面状况 */
    private String qmzk;
    /** 内饰状况 */
    private String nszk;
    /** 工况状况 */
    private String gkzk;
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
    public Long getCarId() {
        return this.carId;
    }
    public void setCarId(Long carId) {
        this.carId=carId;
    }
    public String getCarCond() {
        return this.carCond;
    }
    public void setCarCond(String carCond) {
        this.carCond=carCond;
    }
    public String getSuggestion() {
        return this.suggestion;
    }
    public void setSuggestion(String suggestion) {
        this.suggestion=suggestion;
    }
    public String getConfigTablePhoto() {
        return this.configTablePhoto;
    }
    public void setConfigTablePhoto(String configTablePhoto) {
        this.configTablePhoto=configTablePhoto;
    }
    public String getMaintainPhoto() {
        return this.maintainPhoto;
    }
    public void setMaintainPhoto(String maintainPhoto) {
        this.maintainPhoto=maintainPhoto;
    }
    public BigDecimal getCarAssessmentPrice() {
        return this.carAssessmentPrice;
    }
    public void setCarAssessmentPrice(BigDecimal carAssessmentPrice) {
        this.carAssessmentPrice=carAssessmentPrice;
    }
    public String getQmzk() {
        return qmzk;
    }

    public void setQmzk(String qmzk) {
        this.qmzk = qmzk;
    }

    public String getNszk() {
        return nszk;
    }

    public void setNszk(String nszk) {
        this.nszk = nszk;
    }

    public String getGkzk() {
        return gkzk;
    }

    public void setGkzk(String gkzk) {
        this.gkzk = gkzk;
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

