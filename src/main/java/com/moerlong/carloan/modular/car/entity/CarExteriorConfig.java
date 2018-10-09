package com.moerlong.carloan.modular.car.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
/**
 * 车辆详细配置_外部配置表
 * */
public class CarExteriorConfig {
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
    /**外部配置 0无 1标配 2选配*/
    private Integer exteriorSpecification;
    /**电动天窗 0无1有*/
    private Integer powerSunroof;
    /**运动外观套件 0无1有*/
    private Integer sportAppearanceKit;
    /**电动吸合门 0无1有*/
    private Integer electricOperatedClosingDoor;
    /**电动后备箱 0无1有*/
    private Integer electricTrunk;
    /**车顶行李架 0无1有*/
    private Integer roofRack;
    /**车内中控锁 0无1有*/
    private Integer internallyOperatedcentralDoorLocking;
    /**无钥匙启动系统 0无1有*/
    private Integer keylessStartSystem;
    /**全景天窗 0无1有*/
    private Integer panoramaSunroof;
    /**铝合金轮圈 0无1有*/
    private Integer aluminumAlloyRim;
    /**侧滑门 0无1有*/
    private Integer sideslip;
    /**感应后备箱 0无1有*/
    private Integer inductionTrunk;
    /**发动机电子防盗 0无1有*/
    private Integer emmo;
    /**遥控钥匙 0无1有*/
    private Integer remoteKey;
    /**无钥匙进入系统 0无1有*/
    private Integer wysjrxt;

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

    public Integer getExteriorSpecification() {
        return exteriorSpecification;
    }

    public void setExteriorSpecification(Integer exteriorSpecification) {
        this.exteriorSpecification = exteriorSpecification;
    }

    public Integer getPowerSunroof() {
        return powerSunroof;
    }

    public void setPowerSunroof(Integer powerSunroof) {
        this.powerSunroof = powerSunroof;
    }

    public Integer getSportAppearanceKit() {
        return sportAppearanceKit;
    }

    public void setSportAppearanceKit(Integer sportAppearanceKit) {
        this.sportAppearanceKit = sportAppearanceKit;
    }

    public Integer getElectricOperatedClosingDoor() {
        return electricOperatedClosingDoor;
    }

    public void setElectricOperatedClosingDoor(Integer electricOperatedClosingDoor) {
        this.electricOperatedClosingDoor = electricOperatedClosingDoor;
    }

    public Integer getElectricTrunk() {
        return electricTrunk;
    }

    public void setElectricTrunk(Integer electricTrunk) {
        this.electricTrunk = electricTrunk;
    }

    public Integer getRoofRack() {
        return roofRack;
    }

    public void setRoofRack(Integer roofRack) {
        this.roofRack = roofRack;
    }

    public Integer getInternallyOperatedcentralDoorLocking() {
        return internallyOperatedcentralDoorLocking;
    }

    public void setInternallyOperatedcentralDoorLocking(Integer internallyOperatedcentralDoorLocking) {
        this.internallyOperatedcentralDoorLocking = internallyOperatedcentralDoorLocking;
    }

    public Integer getKeylessStartSystem() {
        return keylessStartSystem;
    }

    public void setKeylessStartSystem(Integer keylessStartSystem) {
        this.keylessStartSystem = keylessStartSystem;
    }

    public Integer getPanoramaSunroof() {
        return panoramaSunroof;
    }

    public void setPanoramaSunroof(Integer panoramaSunroof) {
        this.panoramaSunroof = panoramaSunroof;
    }

    public Integer getAluminumAlloyRim() {
        return aluminumAlloyRim;
    }

    public void setAluminumAlloyRim(Integer aluminumAlloyRim) {
        this.aluminumAlloyRim = aluminumAlloyRim;
    }

    public Integer getSideslip() {
        return sideslip;
    }

    public void setSideslip(Integer sideslip) {
        this.sideslip = sideslip;
    }

    public Integer getInductionTrunk() {
        return inductionTrunk;
    }

    public void setInductionTrunk(Integer inductionTrunk) {
        this.inductionTrunk = inductionTrunk;
    }

    public Integer getEmmo() {
        return emmo;
    }

    public void setEmmo(Integer emmo) {
        this.emmo = emmo;
    }

    public Integer getRemoteKey() {
        return remoteKey;
    }

    public void setRemoteKey(Integer remoteKey) {
        this.remoteKey = remoteKey;
    }

    public Integer getWysjrxt() {
        return wysjrxt;
    }

    public void setWysjrxt(Integer wysjrxt) {
        this.wysjrxt = wysjrxt;
    }
}