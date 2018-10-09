package com.moerlong.carloan.modular.sport.entity;

import java.util.Date;

public class SPrjBase {
    private Integer id;

    private String prjtype;

    private String local;

    private String prjname;

    private String place;

    private String area;

    private String begintime;

    private String endtime;

    private String provider;

    private String jsfa;

    private String dljd;

    private String dlwd;

    private String qjpic1;

    private String qjpic2;

    private String qjpic3;

    private Date createtime;

    private Date updatetime;

    private Integer isdelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrjtype() {
        return prjtype;
    }

    public void setPrjtype(String prjtype) {
        this.prjtype = prjtype == null ? null : prjtype.trim();
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local == null ? null : local.trim();
    }

    public String getPrjname() {
        return prjname;
    }

    public void setPrjname(String prjname) {
        this.prjname = prjname == null ? null : prjname.trim();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime == null ? null : begintime.trim();
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime == null ? null : endtime.trim();
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider == null ? null : provider.trim();
    }

    public String getJsfa() {
        return jsfa;
    }

    public void setJsfa(String jsfa) {
        this.jsfa = jsfa == null ? null : jsfa.trim();
    }

    public String getDljd() {
        return dljd;
    }

    public void setDljd(String dljd) {
        this.dljd = dljd == null ? null : dljd.trim();
    }

    public String getDlwd() {
        return dlwd;
    }

    public void setDlwd(String dlwd) {
        this.dlwd = dlwd == null ? null : dlwd.trim();
    }

    public String getQjpic1() {
        return qjpic1;
    }

    public void setQjpic1(String qjpic1) {
        this.qjpic1 = qjpic1 == null ? null : qjpic1.trim();
    }

    public String getQjpic2() {
        return qjpic2;
    }

    public void setQjpic2(String qjpic2) {
        this.qjpic2 = qjpic2 == null ? null : qjpic2.trim();
    }

    public String getQjpic3() {
        return qjpic3;
    }

    public void setQjpic3(String qjpic3) {
        this.qjpic3 = qjpic3 == null ? null : qjpic3.trim();
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