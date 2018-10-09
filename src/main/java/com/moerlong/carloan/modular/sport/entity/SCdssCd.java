package com.moerlong.carloan.modular.sport.entity;

import java.util.Date;

public class SCdssCd {
    private Integer id;

    private Integer prjid;

    private String prjtype;

    private String cdname;

    private String chang;

    private String kuan;

    private String area;

    private String dimian;

    private String cddbqk;

    private String beizhu;

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

    public String getCdname() {
        return cdname;
    }

    public void setCdname(String cdname) {
        this.cdname = cdname == null ? null : cdname.trim();
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

    public String getDimian() {
        return dimian;
    }

    public void setDimian(String dimian) {
        this.dimian = dimian == null ? null : dimian.trim();
    }

    public String getCddbqk() {
        return cddbqk;
    }

    public void setCddbqk(String cddbqk) {
        this.cddbqk = cddbqk == null ? null : cddbqk.trim();
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu == null ? null : beizhu.trim();
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