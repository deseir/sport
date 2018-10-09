package com.moerlong.carloan.modular.car.entity.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 验车师页面初始化车辆信息
 */
public class InitCarVerifyVo {

    /** 主键 (修改验车信息时候用)*/
    private Long id;
    /** 客户id */
    private Long custId;
    /** 客户名称 */
    private String custName;
    /** 性别 */
    private String sex;
    /** 车辆通俗配置名称 */
    private String carConfigName;
    /** 车牌号 */
    private String carNum;

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
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCarConfigName() {
        return carConfigName;
    }

    public void setCarConfigName(String carConfigName) {
        this.carConfigName = carConfigName;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCarCond() {
        return carCond;
    }

    public void setCarCond(String carCond) {
        this.carCond = carCond;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getConfigTablePhoto() {
        return configTablePhoto;
    }

    public void setConfigTablePhoto(String configTablePhoto) {
        this.configTablePhoto = configTablePhoto;
    }

    public String getMaintainPhoto() {
        return maintainPhoto;
    }

    public void setMaintainPhoto(String maintainPhoto) {
        this.maintainPhoto = maintainPhoto;
    }

    public BigDecimal getCarAssessmentPrice() {
        return carAssessmentPrice;
    }

    public void setCarAssessmentPrice(BigDecimal carAssessmentPrice) {
        this.carAssessmentPrice = carAssessmentPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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
}
