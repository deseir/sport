package com.moerlong.carloan.modular.sport.entity;

import java.util.Date;

public class SCdssQc {
    private Integer id;

    private Integer prjid;

    private Integer cdssid;

    private String xh;

    private String jssb;

    private String bh;

    private String provider;

    private String azsj;

    private String qcxz;

    private String sfbf;

    private String gzms;

    private String hcc;

    private String mfwhq;

    private String zqsyq;

    private Date createtime;

    private Integer isdelete;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrjid() {
        return prjid;
    }

    public void setPrjid(Integer prjid) {
        this.prjid = prjid;
    }

    public Integer getCdssid() {
        return cdssid;
    }

    public void setCdssid(Integer cdssid) {
        this.cdssid = cdssid;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh == null ? null : xh.trim();
    }

    public String getJssb() {
        return jssb;
    }

    public void setJssb(String jssb) {
        this.jssb = jssb == null ? null : jssb.trim();
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh == null ? null : bh.trim();
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

    public String getMfwhq() {
        return mfwhq;
    }

    public void setMfwhq(String mfwhq) {
        this.mfwhq = mfwhq == null ? null : mfwhq.trim();
    }

    public String getZqsyq() {
        return zqsyq;
    }

    public void setZqsyq(String zqsyq) {
        this.zqsyq = zqsyq == null ? null : zqsyq.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}