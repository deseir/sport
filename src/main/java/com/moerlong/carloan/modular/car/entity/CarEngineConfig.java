package com.moerlong.carloan.modular.car.entity;

import java.util.Date;

public class CarEngineConfig {
    private Long id;

    private Long custId;

    private Long applyId;

    private Long carId;

    /**发动机配置**/
    private String carengineConfig;

    /**发动机型号**/
    private String fdjxh;
    /**排量**/
    private String pailiang;

    /**进气形式**/
    private String jqxs;

    /**气缸排列形式**/
    private String qgplxs;

    /**气缸个数**/
    private String qggs;

    /**每缸气门数**/
    private String mgqms;

    /**配气机构**/
    private String pqjg;

    /**缸径**/
    private String gangjing;

    /**最大马力**/
    private String maxml;

    /**最大功率**/
    private String maxgl;

    /**最大功率转速**/
    private String maxglzs;

    /**最大扭转**/
    private String maxnz;

    /**最大扭转转速**/
    private String maxnzzs;

    /**发动机特有技术**/
    private String fdjtyjs;

    /**燃料形式**/
    private String rlxs;

    /**燃油标号**/
    private String rybh;

    /**供油方式**/
    private String gyfs;

    /**缸盖材料**/
    private String ggcl;

    /**缸体材料**/
    private String gtcl;

    /**环保材料**/
    private String hbcl;

    private Date createTime;

    private Date updateTime;

    private String remark;

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

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarengineConfig() {
        return carengineConfig;
    }

    public void setCarengineConfig(String carengineConfig) {
        this.carengineConfig = carengineConfig == null ? null : carengineConfig.trim();
    }

    public String getFdjxh() {
        return fdjxh;
    }

    public void setFdjxh(String fdjxh) {
        this.fdjxh = fdjxh == null ? null : fdjxh.trim();
    }

    public String getPailiang() {
        return pailiang;
    }

    public void setPailiang(String pailiang) {
        this.pailiang = pailiang == null ? null : pailiang.trim();
    }

    public String getJqxs() {
        return jqxs;
    }

    public void setJqxs(String jqxs) {
        this.jqxs = jqxs == null ? null : jqxs.trim();
    }

    public String getQgplxs() {
        return qgplxs;
    }

    public void setQgplxs(String qgplxs) {
        this.qgplxs = qgplxs == null ? null : qgplxs.trim();
    }

    public String getQggs() {
        return qggs;
    }

    public void setQggs(String qggs) {
        this.qggs = qggs == null ? null : qggs.trim();
    }

    public String getMgqms() {
        return mgqms;
    }

    public void setMgqms(String mgqms) {
        this.mgqms = mgqms == null ? null : mgqms.trim();
    }

    public String getPqjg() {
        return pqjg;
    }

    public void setPqjg(String pqjg) {
        this.pqjg = pqjg == null ? null : pqjg.trim();
    }

    public String getGangjing() {
        return gangjing;
    }

    public void setGangjing(String gangjing) {
        this.gangjing = gangjing == null ? null : gangjing.trim();
    }

    public String getMaxml() {
        return maxml;
    }

    public void setMaxml(String maxml) {
        this.maxml = maxml == null ? null : maxml.trim();
    }

    public String getMaxgl() {
        return maxgl;
    }

    public void setMaxgl(String maxgl) {
        this.maxgl = maxgl == null ? null : maxgl.trim();
    }

    public String getMaxglzs() {
        return maxglzs;
    }

    public void setMaxglzs(String maxglzs) {
        this.maxglzs = maxglzs == null ? null : maxglzs.trim();
    }

    public String getMaxnz() {
        return maxnz;
    }

    public void setMaxnz(String maxnz) {
        this.maxnz = maxnz == null ? null : maxnz.trim();
    }

    public String getMaxnzzs() {
        return maxnzzs;
    }

    public void setMaxnzzs(String maxnzzs) {
        this.maxnzzs = maxnzzs == null ? null : maxnzzs.trim();
    }

    public String getFdjtyjs() {
        return fdjtyjs;
    }

    public void setFdjtyjs(String fdjtyjs) {
        this.fdjtyjs = fdjtyjs == null ? null : fdjtyjs.trim();
    }

    public String getRlxs() {
        return rlxs;
    }

    public void setRlxs(String rlxs) {
        this.rlxs = rlxs == null ? null : rlxs.trim();
    }

    public String getRybh() {
        return rybh;
    }

    public void setRybh(String rybh) {
        this.rybh = rybh == null ? null : rybh.trim();
    }

    public String getGyfs() {
        return gyfs;
    }

    public void setGyfs(String gyfs) {
        this.gyfs = gyfs == null ? null : gyfs.trim();
    }

    public String getGgcl() {
        return ggcl;
    }

    public void setGgcl(String ggcl) {
        this.ggcl = ggcl == null ? null : ggcl.trim();
    }

    public String getGtcl() {
        return gtcl;
    }

    public void setGtcl(String gtcl) {
        this.gtcl = gtcl == null ? null : gtcl.trim();
    }

    public String getHbcl() {
        return hbcl;
    }

    public void setHbcl(String hbcl) {
        this.hbcl = hbcl == null ? null : hbcl.trim();
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
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}