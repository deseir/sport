package com.moerlong.carloan.modular.car.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
/**
 * 车辆详细配置_内部配置表
 * */
public class CarInteriorCollocation {
    /***/
    private Long id;
    /**车辆id*/
    private Long carId;
    /**支付id*/
    private Long applyId;
    /**客户id*/
    private Long custId;
    /**是否删除 0-否 1-是*/
    private Integer isDelete;
    /**创建时间*/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;
    /**更新时间*/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date updateTime;
    /**内部配置 0无 1标配 2选配*/
    private Integer interiorCollocation;
    /**真皮方向盘 0无1有*/
    private Integer leatherSteeringWheel;
    /**方向盘电动调节 0前后调节 1上下调节 2上下+前后调节*/
    private Integer electricSteeringWheelAdjustment;
    /**方向盘换挡 0无 1标配 2选配*/
    private Integer steeringWheelShift;
    /**方向盘记忆 0无1有*/
    private Integer steeringWheelMemory;
    /**全液晶仪表盘  0无1有*/
    private Integer fullLcdPanel;
    /**方向盘调节 0有1无*/
    private Integer adjustableSteeringWheel;
    /**多功能方向盘  0无1有*/
    private Integer mfl;
    /**方向盘加热  0无1有*/
    private Integer heatedSteeringWheel;
    /**行车电脑显示屏 0有1无*/
    private Integer drivingComputerScreen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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

    public Integer getInteriorCollocation() {
        return interiorCollocation;
    }

    public void setInteriorCollocation(Integer interiorCollocation) {
        this.interiorCollocation = interiorCollocation;
    }

    public Integer getLeatherSteeringWheel() {
        return leatherSteeringWheel;
    }

    public void setLeatherSteeringWheel(Integer leatherSteeringWheel) {
        this.leatherSteeringWheel = leatherSteeringWheel;
    }

    public Integer getElectricSteeringWheelAdjustment() {
        return electricSteeringWheelAdjustment;
    }

    public void setElectricSteeringWheelAdjustment(Integer electricSteeringWheelAdjustment) {
        this.electricSteeringWheelAdjustment = electricSteeringWheelAdjustment;
    }

    public Integer getSteeringWheelShift() {
        return steeringWheelShift;
    }

    public void setSteeringWheelShift(Integer steeringWheelShift) {
        this.steeringWheelShift = steeringWheelShift;
    }

    public Integer getSteeringWheelMemory() {
        return steeringWheelMemory;
    }

    public void setSteeringWheelMemory(Integer steeringWheelMemory) {
        this.steeringWheelMemory = steeringWheelMemory;
    }

    public Integer getFullLcdPanel() {
        return fullLcdPanel;
    }

    public void setFullLcdPanel(Integer fullLcdPanel) {
        this.fullLcdPanel = fullLcdPanel;
    }

    public Integer getAdjustableSteeringWheel() {
        return adjustableSteeringWheel;
    }

    public void setAdjustableSteeringWheel(Integer adjustableSteeringWheel) {
        this.adjustableSteeringWheel = adjustableSteeringWheel;
    }

    public Integer getMfl() {
        return mfl;
    }

    public void setMfl(Integer mfl) {
        this.mfl = mfl;
    }

    public Integer getHeatedSteeringWheel() {
        return heatedSteeringWheel;
    }

    public void setHeatedSteeringWheel(Integer heatedSteeringWheel) {
        this.heatedSteeringWheel = heatedSteeringWheel;
    }

    public Integer getDrivingComputerScreen() {
        return drivingComputerScreen;
    }

    public void setDrivingComputerScreen(Integer drivingComputerScreen) {
        this.drivingComputerScreen = drivingComputerScreen;
    }
}