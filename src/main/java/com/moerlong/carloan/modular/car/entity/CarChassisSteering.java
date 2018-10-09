package com.moerlong.carloan.modular.car.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 车辆详细配置_底盘转向
 */
public class CarChassisSteering {
	/**
	 * 主键
	 */
    private Long id;
    /**
     * 订单ID
     */
    private Long applyId;
    /**
     * 客户id
     */
    private Long custId;
    /**
     * 车辆id
     */
    private Long carId;
    /**
     * 底盘转向 1标配 2选配 3无
     */
    private String carChassisSteering;
    /**
     * 驱动方式
     */
    private String drivingModel;
    /**
     * 前悬架类型
     */
    private String frontSuspension;
    /**
     * 后悬架类型
     */
    private String rearSuspension;
    /**
     * 助力类型
     */
    private String powerSteering;
    /**
     * 车体结构
     */
    private String carBodyStructure;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date updateTime;
    /**
     * 是否删除 0--否 1--是
     */
    private Integer isDelete;
    /**
     * 备注说明
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarChassisSteering() {
        return carChassisSteering;
    }

    public void setCarChassisSteering(String carChassisSteering) {
        this.carChassisSteering = carChassisSteering == null ? null : carChassisSteering.trim();
    }

    public String getDrivingModel() {
        return drivingModel;
    }

    public void setDrivingModel(String drivingModel) {
        this.drivingModel = drivingModel == null ? null : drivingModel.trim();
    }

    public String getFrontSuspension() {
        return frontSuspension;
    }

    public void setFrontSuspension(String frontSuspension) {
        this.frontSuspension = frontSuspension == null ? null : frontSuspension.trim();
    }

    public String getRearSuspension() {
        return rearSuspension;
    }

    public void setRearSuspension(String rearSuspension) {
        this.rearSuspension = rearSuspension == null ? null : rearSuspension.trim();
    }

    public String getPowerSteering() {
        return powerSteering;
    }

    public void setPowerSteering(String powerSteering) {
        this.powerSteering = powerSteering == null ? null : powerSteering.trim();
    }

    public String getCarBodyStructure() {
        return carBodyStructure;
    }

    public void setCarBodyStructure(String carBodyStructure) {
        this.carBodyStructure = carBodyStructure == null ? null : carBodyStructure.trim();
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}