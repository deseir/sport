package com.moerlong.carloan.modular.sport.entity;

import java.util.Date;

public class SCdssQt {
    private Integer id;

    private Integer prjid;

    private String prjtype;

    private String jsss;

    private String num;

    private String area;

    private String wz;

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

    public String getJsss() {
        return jsss;
    }

    public void setJsss(String jsss) {
        this.jsss = jsss == null ? null : jsss.trim();
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getWz() {
        return wz;
    }

    public void setWz(String wz) {
        this.wz = wz == null ? null : wz.trim();
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