package com.moerlong.carloan.modular.car.entity;

import java.util.Date;

public class CarBodyConfig {
    private Long id;

    private Long custId;

    private Long applyId;

    private Long carId;

    /**车身配置 1-标配 2-选配 3-无 **/
    private String carbodyConfig;

    /**轴距**/
    private String zj;

    /**前轮距**/
    private String qlj;

    /**后轮距**/
    private String hlj;

    /**最小离地间隙**/
    private String minldjx;

    /**整车质量**/
    private String zczl;

    /**车身结构**/
    private String csjg;

    /**邮箱容积**/
    private String yxrl;

    /**行李箱容积**/
    private String xlxrl;

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

    public String getCarbodyConfig() {
        return carbodyConfig;
    }

    public void setCarbodyConfig(String carbodyConfig) {
        this.carbodyConfig = carbodyConfig == null ? null : carbodyConfig.trim();
    }

    public String getZj() {
        return zj;
    }

    public void setZj(String zj) {
        this.zj = zj == null ? null : zj.trim();
    }

    public String getQlj() {
        return qlj;
    }

    public void setQlj(String qlj) {
        this.qlj = qlj == null ? null : qlj.trim();
    }

    public String getHlj() {
        return hlj;
    }

    public void setHlj(String hlj) {
        this.hlj = hlj == null ? null : hlj.trim();
    }

    public String getMinldjx() {
        return minldjx;
    }

    public void setMinldjx(String minldjx) {
        this.minldjx = minldjx == null ? null : minldjx.trim();
    }

    public String getZczl() {
        return zczl;
    }

    public void setZczl(String zczl) {
        this.zczl = zczl == null ? null : zczl.trim();
    }

    public String getCsjg() {
        return csjg;
    }

    public void setCsjg(String csjg) {
        this.csjg = csjg == null ? null : csjg.trim();
    }

    public String getYxrl() {
        return yxrl;
    }

    public void setYxrl(String yxrl) {
        this.yxrl = yxrl == null ? null : yxrl.trim();
    }

    public String getXlxrl() {
        return xlxrl;
    }

    public void setXlxrl(String xlxrl) {
        this.xlxrl = xlxrl == null ? null : xlxrl.trim();
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