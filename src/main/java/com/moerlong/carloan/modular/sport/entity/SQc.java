package com.moerlong.carloan.modular.sport.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SQc {
    private Integer id;

    private String deptid;

    private String deptname;

    private String deptpid;

    private String deptpname;

    private String jssb;

    private String qcbh;

    private String provider;

    private String azsj;

    private String qcxz;

    private String sfbf;

    private String gzms;

    private String hcc;

    private String mfwhqx;

    private String aqsyqx;

    private Date createtime;

    private Date updatetime;

    private Integer isdeleted;

    private String picUrl;

    private String dljd;

    private String dlwd;

    private BigDecimal amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid == null ? null : deptid.trim();
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    public String getDeptpid() {
        return deptpid;
    }

    public void setDeptpid(String deptpid) {
        this.deptpid = deptpid == null ? null : deptpid.trim();
    }

    public String getDeptpname() {
        return deptpname;
    }

    public void setDeptpname(String deptpname) {
        this.deptpname = deptpname == null ? null : deptpname.trim();
    }

    public String getJssb() {
        return jssb;
    }

    public void setJssb(String jssb) {
        this.jssb = jssb == null ? null : jssb.trim();
    }

    public String getQcbh() {
        return qcbh;
    }

    public void setQcbh(String qcbh) {
        this.qcbh = qcbh == null ? null : qcbh.trim();
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider == null ? null : provider.trim();
    }

    public String getAzsj() {
        return azsj;
    }

    public void setAzsj(String azsj) {
        this.azsj = azsj == null ? null : azsj.trim();
    }

    public String getQcxz() {
        return qcxz;
    }

    public void setQcxz(String qcxz) {
        this.qcxz = qcxz == null ? null : qcxz.trim();
    }

    public String getSfbf() {
        return sfbf;
    }

    public void setSfbf(String sfbf) {
        this.sfbf = sfbf == null ? null : sfbf.trim();
    }

    public String getGzms() {
        return gzms;
    }

    public void setGzms(String gzms) {
        this.gzms = gzms == null ? null : gzms.trim();
    }

    public String getHcc() {
        return hcc;
    }

    public void setHcc(String hcc) {
        this.hcc = hcc == null ? null : hcc.trim();
    }

    public String getMfwhqx() {
        return mfwhqx;
    }

    public void setMfwhqx(String mfwhqx) {
        this.mfwhqx = mfwhqx == null ? null : mfwhqx.trim();
    }

    public String getAqsyqx() {
        return aqsyqx;
    }

    public void setAqsyqx(String aqsyqx) {
        this.aqsyqx = aqsyqx == null ? null : aqsyqx.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Integer isdeleted) {
        this.isdeleted = isdeleted;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDljd() {
        return dljd;
    }

    public void setDljd(String dljd) {
        this.dljd = dljd;
    }

    public String getDlwd() {
        return dlwd;
    }

    public void setDlwd(String dlwd) {
        this.dlwd = dlwd;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}