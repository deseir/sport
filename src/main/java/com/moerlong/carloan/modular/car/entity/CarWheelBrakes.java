package com.moerlong.carloan.modular.car.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 车辆详细配置_车轮制动
 */
public class CarWheelBrakes {
	/**
	 * 主键
	 */
    private Long id;
    /**
	 * 订单id
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
	 * 车轮制动 1标配 2选配 3无
	 */
    private Integer whellBrakes;
    /**
	 * 前制动器类型
	 */
    private String frontBrakeType;
    /**
	 * 后制动器类型
	 */
    private String rearBrakeType;
    /**
	 * 驻车制动类型
	 */
    private String parkingBraking;
    /**
	 * 前轮胎规格
	 */
    private String frontTyreSize;
    /**
	 * 后轮胎规格
	 */
    private String rearTyreSize;
    /**
	 * 备胎规格
	 */
    private String spareTire;
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

    public Integer getWhellBrakes() {
        return whellBrakes;
    }

    public void setWhellBrakes(Integer whellBrakes) {
        this.whellBrakes = whellBrakes;
    }

    public String getFrontBrakeType() {
        return frontBrakeType;
    }

    public void setFrontBrakeType(String frontBrakeType) {
        this.frontBrakeType = frontBrakeType == null ? null : frontBrakeType.trim();
    }

    public String getRearBrakeType() {
        return rearBrakeType;
    }

    public void setRearBrakeType(String rearBrakeType) {
        this.rearBrakeType = rearBrakeType == null ? null : rearBrakeType.trim();
    }

    public String getParkingBraking() {
        return parkingBraking;
    }

    public void setParkingBraking(String parkingBraking) {
        this.parkingBraking = parkingBraking == null ? null : parkingBraking.trim();
    }

    public String getFrontTyreSize() {
        return frontTyreSize;
    }

    public void setFrontTyreSize(String frontTyreSize) {
        this.frontTyreSize = frontTyreSize == null ? null : frontTyreSize.trim();
    }

    public String getRearTyreSize() {
        return rearTyreSize;
    }

    public void setRearTyreSize(String rearTyreSize) {
        this.rearTyreSize = rearTyreSize == null ? null : rearTyreSize.trim();
    }

    public String getSpareTire() {
        return spareTire;
    }

    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire == null ? null : spareTire.trim();
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