package com.moerlong.carloan.modular.car.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
/**
 * 车辆详细配置_冰箱、空调表
 * */
public class CarKontiaoConfig {
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
    /**冰箱、空调 0无 1标配 2选配*/
    private Integer bxKt;
    /**空调控制方式0无 1自动 2手动*/
    private Integer ktkzfs;
    /**后排独立空调0无 1自动 2手动*/
    private Integer hpdlkt;
    /**温度分区控制0无1有*/
    private Integer wdfqkz;
    /**后座出风口0无1有*/
    private Integer hzcfk;
    /**车内空气调节/花粉过滤0无1有*/
    private Integer cnkqtjHfgl;
    /**车载冰箱0无1有*/
    private Integer czbx;

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

    public Integer getBxKt() {
        return bxKt;
    }

    public void setBxKt(Integer bxKt) {
        this.bxKt = bxKt;
    }

    public Integer getKtkzfs() {
        return ktkzfs;
    }

    public void setKtkzfs(Integer ktkzfs) {
        this.ktkzfs = ktkzfs;
    }

    public Integer getHpdlkt() {
        return hpdlkt;
    }

    public void setHpdlkt(Integer hpdlkt) {
        this.hpdlkt = hpdlkt;
    }

    public Integer getWdfqkz() {
        return wdfqkz;
    }

    public void setWdfqkz(Integer wdfqkz) {
        this.wdfqkz = wdfqkz;
    }

    public Integer getHzcfk() {
        return hzcfk;
    }

    public void setHzcfk(Integer hzcfk) {
        this.hzcfk = hzcfk;
    }

    public Integer getCnkqtjHfgl() {
        return cnkqtjHfgl;
    }

    public void setCnkqtjHfgl(Integer cnkqtjHfgl) {
        this.cnkqtjHfgl = cnkqtjHfgl;
    }

    public Integer getCzbx() {
        return czbx;
    }

    public void setCzbx(Integer czbx) {
        this.czbx = czbx;
    }
}