package com.moerlong.carloan.modular.car.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 车辆详细配置_玻璃/后玻璃
 * */
public class CarBoliConfig {
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
    /**玻璃/后玻璃 0无 1标配 2选配*/
    private Integer blHbl;
    /**前电动车窗0无1有*/
    private Integer qDdcc;
    /**后电动车窗0无1有*/
    private Integer hDdcc;
    /**车窗防夹手0无1有*/
    private Integer ccfjs;
    /**后视镜电动调节0无1有*/
    private Integer hsjddtj;
    /**后视镜加热0无1有*/
    private Integer hsjjr;
    /**内后视镜自动防眩目0无1有*/
    private Integer nHsjzdfxm;
    /**外后视镜自动防眩目0无1有*/
    private Integer wHsjzdfxm;
    /**后视镜电动折叠0无1有*/
    private Integer hsjddzd;
    /**遮阳板化妆镜0无1有*/
    private Integer zybhzj;
    /**后雨刷0无1有*/
    private Integer hys;
    /**感应雨刷0无1有*/
    private Integer gyys;

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

    public Integer getBlHbl() {
        return blHbl;
    }

    public void setBlHbl(Integer blHbl) {
        this.blHbl = blHbl;
    }

    public Integer getqDdcc() {
        return qDdcc;
    }

    public void setqDdcc(Integer qDdcc) {
        this.qDdcc = qDdcc;
    }

    public Integer gethDdcc() {
        return hDdcc;
    }

    public void sethDdcc(Integer hDdcc) {
        this.hDdcc = hDdcc;
    }

    public Integer getCcfjs() {
        return ccfjs;
    }

    public void setCcfjs(Integer ccfjs) {
        this.ccfjs = ccfjs;
    }

    public Integer getHsjddtj() {
        return hsjddtj;
    }

    public void setHsjddtj(Integer hsjddtj) {
        this.hsjddtj = hsjddtj;
    }

    public Integer getHsjjr() {
        return hsjjr;
    }

    public void setHsjjr(Integer hsjjr) {
        this.hsjjr = hsjjr;
    }

    public Integer getnHsjzdfxm() {
        return nHsjzdfxm;
    }

    public void setnHsjzdfxm(Integer nHsjzdfxm) {
        this.nHsjzdfxm = nHsjzdfxm;
    }

    public Integer getwHsjzdfxm() {
        return wHsjzdfxm;
    }

    public void setwHsjzdfxm(Integer wHsjzdfxm) {
        this.wHsjzdfxm = wHsjzdfxm;
    }

    public Integer getHsjddzd() {
        return hsjddzd;
    }

    public void setHsjddzd(Integer hsjddzd) {
        this.hsjddzd = hsjddzd;
    }

    public Integer getZybhzj() {
        return zybhzj;
    }

    public void setZybhzj(Integer zybhzj) {
        this.zybhzj = zybhzj;
    }

    public Integer getHys() {
        return hys;
    }

    public void setHys(Integer hys) {
        this.hys = hys;
    }

    public Integer getGyys() {
        return gyys;
    }

    public void setGyys(Integer gyys) {
        this.gyys = gyys;
    }
}