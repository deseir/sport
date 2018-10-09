package com.moerlong.carloan.modular.car.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
/**
 * 车辆详细配置_多媒体配置表
 * */
public class CarMultimediaConfig {
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
    /**多媒体配置 0无 1标配 2选配*/
    private Integer dmtpz;
    /**gps 0无 1标配 2选配*/
    private Integer gps;
    /**定位互动服务 0无 1标配 2选配*/
    private Integer dwhdfw;
    /**中控彩色大屏 0有1无*/
    private Integer zkcsdp;
    /**蓝牙/车载电话 0有1无*/
    private Integer lydh;
    /**外接音源接口*/
    private String wjyyjk;
    /**多媒体系统(cd/dvd*/
    private String cdDvd;
    /**扬声器数量*/
    private String ysqsl;

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

    public Integer getDmtpz() {
        return dmtpz;
    }

    public void setDmtpz(Integer dmtpz) {
        this.dmtpz = dmtpz;
    }

    public Integer getGps() {
        return gps;
    }

    public void setGps(Integer gps) {
        this.gps = gps;
    }

    public Integer getDwhdfw() {
        return dwhdfw;
    }

    public void setDwhdfw(Integer dwhdfw) {
        this.dwhdfw = dwhdfw;
    }

    public Integer getZkcsdp() {
        return zkcsdp;
    }

    public void setZkcsdp(Integer zkcsdp) {
        this.zkcsdp = zkcsdp;
    }

    public Integer getLydh() {
        return lydh;
    }

    public void setLydh(Integer lydh) {
        this.lydh = lydh;
    }

    public String getWjyyjk() {
        return wjyyjk;
    }

    public void setWjyyjk(String wjyyjk) {
        this.wjyyjk = wjyyjk == null ? null : wjyyjk.trim();
    }

    public String getCdDvd() {
        return cdDvd;
    }

    public void setCdDvd(String cdDvd) {
        this.cdDvd = cdDvd == null ? null : cdDvd.trim();
    }

    public String getYsqsl() {
        return ysqsl;
    }

    public void setYsqsl(String ysqsl) {
        this.ysqsl = ysqsl == null ? null : ysqsl.trim();
    }
}