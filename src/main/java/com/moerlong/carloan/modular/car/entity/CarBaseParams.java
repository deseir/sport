package com.moerlong.carloan.modular.car.entity;

import java.util.Date;

public class CarBaseParams {
    private Long id;

    private Long custId;

    private Long applyId;

    private Long carId;
    /**基本参数配置*/
    private String baseConfig;
    /**厂商*/
    private String productfactory;
    /**级别*/
    private String jibie;
    /**发动机*/
    private String engine;
    /**变速箱*/
    private String bsx;
    /** 长*宽*高 */
    private String ckg;
    /**车身型式*/
    private String csxs;
    /**最高速度*/
    private String highspeed;
    /**燃料形式*/
    private String rlxs;
    /**工信部综合油耗*/
    private String gxbzhyh;
    /**外部颜色*/
    private String outcolor;
    /**内饰颜色*/
    private String innercolor;
    /**创建时间*/
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

    public String getBaseConfig() {
        return baseConfig;
    }

    public void setBaseConfig(String baseConfig) {
        this.baseConfig = baseConfig == null ? null : baseConfig.trim();
    }

    public String getProductfactory() {
        return productfactory;
    }

    public void setProductfactory(String productfactory) {
        this.productfactory = productfactory == null ? null : productfactory.trim();
    }

    public String getJibie() {
        return jibie;
    }

    public void setJibie(String jibie) {
        this.jibie = jibie == null ? null : jibie.trim();
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine == null ? null : engine.trim();
    }

    public String getBsx() {
        return bsx;
    }

    public void setBsx(String bsx) {
        this.bsx = bsx == null ? null : bsx.trim();
    }

    public String getCkg() {
        return ckg;
    }

    public void setCkg(String ckg) {
        this.ckg = ckg == null ? null : ckg.trim();
    }

    public String getCsxs() {
        return csxs;
    }

    public void setCsxs(String csxs) {
        this.csxs = csxs == null ? null : csxs.trim();
    }

    public String getHighspeed() {
        return highspeed;
    }

    public void setHighspeed(String highspeed) {
        this.highspeed = highspeed == null ? null : highspeed.trim();
    }

    public String getRlxs() {
        return rlxs;
    }

    public void setRlxs(String rlxs) {
        this.rlxs = rlxs == null ? null : rlxs.trim();
    }

    public String getGxbzhyh() {
        return gxbzhyh;
    }

    public void setGxbzhyh(String gxbzhyh) {
        this.gxbzhyh = gxbzhyh == null ? null : gxbzhyh.trim();
    }

    public String getOutcolor() {
        return outcolor;
    }

    public void setOutcolor(String outcolor) {
        this.outcolor = outcolor == null ? null : outcolor.trim();
    }

    public String getInnercolor() {
        return innercolor;
    }

    public void setInnercolor(String innercolor) {
        this.innercolor = innercolor == null ? null : innercolor.trim();
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