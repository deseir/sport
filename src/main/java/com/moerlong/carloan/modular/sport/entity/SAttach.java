package com.moerlong.carloan.modular.sport.entity;

import java.util.Date;

public class SAttach {
    private Integer id;

    private Integer type;

    private Integer prjid;

    private Integer prjtype;

    private Integer cdsscdid;

    private Integer cdssjsljid;

    private Integer cdssqtid;

    private Integer qccdid;

    private Integer qcjsljid;

    private Integer qcqtid;

    private String picname;

    private String picurl;

    private Date createtime;

    private Date updatetime;

    private Integer isdelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPrjid() {
        return prjid;
    }

    public void setPrjid(Integer prjid) {
        this.prjid = prjid;
    }

    public Integer getPrjtype() {
        return prjtype;
    }

    public void setPrjtype(Integer prjtype) {
        this.prjtype = prjtype;
    }

    public Integer getCdsscdid() {
        return cdsscdid;
    }

    public void setCdsscdid(Integer cdsscdid) {
        this.cdsscdid = cdsscdid;
    }

    public Integer getCdssjsljid() {
        return cdssjsljid;
    }

    public void setCdssjsljid(Integer cdssjsljid) {
        this.cdssjsljid = cdssjsljid;
    }

    public Integer getCdssqtid() {
        return cdssqtid;
    }

    public void setCdssqtid(Integer cdssqtid) {
        this.cdssqtid = cdssqtid;
    }

    public Integer getQccdid() {
        return qccdid;
    }

    public void setQccdid(Integer qccdid) {
        this.qccdid = qccdid;
    }

    public Integer getQcjsljid() {
        return qcjsljid;
    }

    public void setQcjsljid(Integer qcjsljid) {
        this.qcjsljid = qcjsljid;
    }

    public Integer getQcqtid() {
        return qcqtid;
    }

    public void setQcqtid(Integer qcqtid) {
        this.qcqtid = qcqtid;
    }

    public String getPicname() {
        return picname;
    }

    public void setPicname(String picname) {
        this.picname = picname == null ? null : picname.trim();
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
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

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}