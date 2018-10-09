package com.moerlong.carloan.modular.cust.entity.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MarryInfoVo {
    private  Long marryInfoId;
    /** 婚姻状况 0--已婚 1--未婚 2--再婚 3--离异 4--丧偶 */
    private Integer marryStatus;
    private Long applyId;
    /** 配偶性别 0-女 1-男（如果是已婚，再婚，丧偶） */
    private Integer spouseSex;
    /** 婚姻登记日期（如果是已婚，再婚，丧偶） */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date marryDate;
    /** 配偶身份证有效期起始日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date spousevalidateBegin;
    /** 配偶身份证有效期结束日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date spousevalidateEnd;
    /** 配偶姓名（如果是已婚，再婚，丧偶） */
    private String spouseName;
    /** 配偶身份证号码（如果是已婚，再婚，丧偶） */
    private String spouseCertId;
    /** 配偶身份证正面图片地址 */
    private String spouseidFrontPhotoUrl;
    /** 配偶身份证背面图片地址 */
    private String spouseidBackPhotoUrl;
    /** 配偶签发机关 */
    private String spouseSignOrg;
    /** 结婚证照片地址 */
    private String marryPhotoUrl;
    private Integer divorceSex ;
    /** 离婚登记日期（如果是离异） */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date divorceDate;
    /** 原配偶身份证号码（如果是离异） */
    private String divorceCertId;
    /** 原配偶名称（如果是离异） */
    private String divorceName;
    /** 离婚证照片(如果是离异) */
    private String divorcePhotoUrl;
    /** 死亡证明照片地址(如果是丧偶) */
    private String deathCertPhotoUrl;
    /** 配偶手机号 */
    private String spousePhone;
    public Integer getDivorceSex() {
        return divorceSex;
    }

    public void setDivorceSex(Integer divorceSex) {
        this.divorceSex = divorceSex;
    }

    public Date getDivorceDate() {
        return divorceDate;
    }

    public void setDivorceDate(Date divorceDate) {
        this.divorceDate = divorceDate;
    }

    public String getDivorceCertId() {
        return divorceCertId;
    }

    public void setDivorceCertId(String divorceCertId) {
        this.divorceCertId = divorceCertId;
    }

    public String getDivorceName() {
        return divorceName;
    }

    public void setDivorceName(String divorceName) {
        this.divorceName = divorceName;
    }

    public String getDivorcePhotoUrl() {
        return divorcePhotoUrl;
    }

    public void setDivorcePhotoUrl(String divorcePhotoUrl) {
        this.divorcePhotoUrl = divorcePhotoUrl;
    }

    public String getDeathCertPhotoUrl() {
        return deathCertPhotoUrl;
    }

    public void setDeathCertPhotoUrl(String deathCertPhotoUrl) {
        this.deathCertPhotoUrl = deathCertPhotoUrl;
    }

    public Long getMarryInfoId() {
        return marryInfoId;
    }

    public void setMarryInfoId(Long marryInfoId) {
        this.marryInfoId = marryInfoId;
    }

    public Integer getMarryStatus() {
        return marryStatus;
    }

    public void setMarryStatus(Integer marryStatus) {
        this.marryStatus = marryStatus;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Integer getSpouseSex() {
        return spouseSex;
    }

    public void setSpouseSex(Integer spouseSex) {
        this.spouseSex = spouseSex;
    }

    public Date getMarryDate() {
        return marryDate;
    }

    public void setMarryDate(Date marryDate) {
        this.marryDate = marryDate;
    }

    public Date getSpousevalidateBegin() {
        return spousevalidateBegin;
    }

    public void setSpousevalidateBegin(Date spousevalidateBegin) {
        this.spousevalidateBegin = spousevalidateBegin;
    }

    public Date getSpousevalidateEnd() {
        return spousevalidateEnd;
    }

    public void setSpousevalidateEnd(Date spousevalidateEnd) {
        this.spousevalidateEnd = spousevalidateEnd;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getSpouseCertId() {
        return spouseCertId;
    }

    public void setSpouseCertId(String spouseCertId) {
        this.spouseCertId = spouseCertId;
    }

    public String getSpouseidFrontPhotoUrl() {
        return spouseidFrontPhotoUrl;
    }

    public void setSpouseidFrontPhotoUrl(String spouseidFrontPhotoUrl) {
        this.spouseidFrontPhotoUrl = spouseidFrontPhotoUrl;
    }

    public String getSpouseidBackPhotoUrl() {
        return spouseidBackPhotoUrl;
    }

    public void setSpouseidBackPhotoUrl(String spouseidBackPhotoUrl) {
        this.spouseidBackPhotoUrl = spouseidBackPhotoUrl;
    }

    public String getSpouseSignOrg() {
        return spouseSignOrg;
    }

    public void setSpouseSignOrg(String spouseSignOrg) {
        this.spouseSignOrg = spouseSignOrg;
    }

    public String getMarryPhotoUrl() {
        return marryPhotoUrl;
    }

    public void setMarryPhotoUrl(String marryPhotoUrl) {
        this.marryPhotoUrl = marryPhotoUrl;
    }

    public String getSpousePhone() {
        return spousePhone;
    }

    public void setSpousePhone(String spousePhone) {
        this.spousePhone = spousePhone;
    }
}
