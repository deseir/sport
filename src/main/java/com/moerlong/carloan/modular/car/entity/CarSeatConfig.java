package com.moerlong.carloan.modular.car.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
/**
 * 车辆详细配置_座椅配置表
 * */
public class CarSeatConfig {
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
    /**座椅配置0无 1标配 2选配*/
    private Integer zypz;
    /**座椅材质*/
    private String zycz;
    /**运动风格座椅 0无 1标配 2选配*/
    private Integer ydfgzy;
    /**座椅高低调节 0无1有*/
    private Integer zygdtj;
    /**腰部支撑调节 0无1有*/
    private Integer ybzctj;
    /**肩部支撑调节 0无 1标配 2选配*/
    private Integer jbzctj;
    /**主驾驶座电动调节 0无1有*/
    private Integer zjszddtj;
    /**副驾驶座电动调节 0无1有*/
    private Integer fjszddtj;
    /**后排座电动调节 0无1有*/
    private Integer hpzddtj;

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

    public Integer getZypz() {
        return zypz;
    }

    public void setZypz(Integer zypz) {
        this.zypz = zypz;
    }

    public String getZycz() {
        return zycz;
    }

    public void setZycz(String zycz) {
        this.zycz = zycz == null ? null : zycz.trim();
    }

    public Integer getYdfgzy() {
        return ydfgzy;
    }

    public void setYdfgzy(Integer ydfgzy) {
        this.ydfgzy = ydfgzy;
    }

    public Integer getZygdtj() {
        return zygdtj;
    }

    public void setZygdtj(Integer zygdtj) {
        this.zygdtj = zygdtj;
    }

    public Integer getYbzctj() {
        return ybzctj;
    }

    public void setYbzctj(Integer ybzctj) {
        this.ybzctj = ybzctj;
    }

    public Integer getJbzctj() {
        return jbzctj;
    }

    public void setJbzctj(Integer jbzctj) {
        this.jbzctj = jbzctj;
    }

    public Integer getZjszddtj() {
        return zjszddtj;
    }

    public void setZjszddtj(Integer zjszddtj) {
        this.zjszddtj = zjszddtj;
    }

    public Integer getFjszddtj() {
        return fjszddtj;
    }

    public void setFjszddtj(Integer fjszddtj) {
        this.fjszddtj = fjszddtj;
    }

    public Integer getHpzddtj() {
        return hpzddtj;
    }

    public void setHpzddtj(Integer hpzddtj) {
        this.hpzddtj = hpzddtj;
    }
}