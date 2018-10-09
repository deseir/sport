package com.moerlong.carloan.modular.car.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
/**
 * 车辆详细配置_灯光配置表
 * */
public class CarLightConfig {
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
    /**灯光配置 0无 1标配 2选配*/
    private Integer dgpz;
    /**远光灯*/
    private String ygd;
    /**近光灯*/
    private String jgd;
    /**LED日间行车灯0无1有*/
    private Integer ledrxd;
    /**自适应远近光0无1有*/
    private Integer zsyyjg;
    /**自动头灯0无1有*/
    private Integer zdtd;
    /**转向辅助灯0无1有*/
    private Integer zxfzd;
    /**转向头灯0无1有*/
    private Integer zxtd;
    /**前雾灯0无1有*/
    private Integer qwd;
    /**大灯高度可调0无1有*/
    private Integer ddgdkt;
    /**大灯清洗装置0无1有*/
    private Integer ddqxzz;

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

    public Integer getDgpz() {
        return dgpz;
    }

    public void setDgpz(Integer dgpz) {
        this.dgpz = dgpz;
    }

    public String getYgd() {
        return ygd;
    }

    public void setYgd(String ygd) {
        this.ygd = ygd == null ? null : ygd.trim();
    }

    public String getJgd() {
        return jgd;
    }

    public void setJgd(String jgd) {
        this.jgd = jgd == null ? null : jgd.trim();
    }

    public Integer getLedrxd() {
        return ledrxd;
    }

    public void setLedrxd(Integer ledrxd) {
        this.ledrxd = ledrxd;
    }

    public Integer getZsyyjg() {
        return zsyyjg;
    }

    public void setZsyyjg(Integer zsyyjg) {
        this.zsyyjg = zsyyjg;
    }

    public Integer getZdtd() {
        return zdtd;
    }

    public void setZdtd(Integer zdtd) {
        this.zdtd = zdtd;
    }

    public Integer getZxfzd() {
        return zxfzd;
    }

    public void setZxfzd(Integer zxfzd) {
        this.zxfzd = zxfzd;
    }

    public Integer getZxtd() {
        return zxtd;
    }

    public void setZxtd(Integer zxtd) {
        this.zxtd = zxtd;
    }

    public Integer getQwd() {
        return qwd;
    }

    public void setQwd(Integer qwd) {
        this.qwd = qwd;
    }

    public Integer getDdgdkt() {
        return ddgdkt;
    }

    public void setDdgdkt(Integer ddgdkt) {
        this.ddgdkt = ddgdkt;
    }

    public Integer getDdqxzz() {
        return ddqxzz;
    }

    public void setDdqxzz(Integer ddqxzz) {
        this.ddqxzz = ddqxzz;
    }
}