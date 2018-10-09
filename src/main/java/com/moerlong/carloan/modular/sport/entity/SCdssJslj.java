package com.moerlong.carloan.modular.sport.entity;

import java.util.Date;

public class SCdssJslj {
    private Integer id;

    private Integer prjid;

    private String prjtype;

    private String mc;

    private String chang;

    private String kuan;

    private String area;

    private String dbqk;

    private String cllx;

    private String pic;

    private Date createtime;

    private Date updatetime;

    private Integer isdelete;

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

    public String getPrjtype() {
        return prjtype;
    }

    public void setPrjtype(String prjtype) {
        this.prjtype = prjtype == null ? null : prjtype.trim();
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc == null ? null : mc.trim();
    }

    public String getChang() {
        return chang;
    }

    public void setChang(String chang) {
        this.chang = chang == null ? null : chang.trim();
    }

    public String getKuan() {
        return kuan;
    }

    public void setKuan(String kuan) {
        this.kuan = kuan == null ? null : kuan.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getDbqk() {
        return dbqk;
    }

    public void setDbqk(String dbqk) {
        this.dbqk = dbqk == null ? null : dbqk.trim();
    }

    public String getCllx() {
        return cllx;
    }

    public void setCllx(String cllx) {
        this.cllx = cllx == null ? null : cllx.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
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