package com.moerlong.carloan.modular.cust.entity.vo;

import com.moerlong.carloan.modular.cust.entity.FamilyBookSubInfo;

import java.util.List;
import java.util.Set;

public class FamilyBookInfoVo {
    private Long familyBookInfoId;
    private Long applyId;
    /** 申请人与户主关系 */
    private String relationship;
    /** 户主姓名 */
    private String masterName;
    /** 性别 0-女 1-男 */
    private Integer masterSex;
    /** 户主身份证号码 */
    private String certId;
    private List<FamilyBookSubInfo> familyBookSubInfolist;
    /** 户口本首页照片 */
    private String firstPagePhotoUrl;

    public Long getFamilyBookInfoId() {
        return familyBookInfoId;
    }

    public void setFamilyBookInfoId(Long familyBookInfoId) {
        this.familyBookInfoId = familyBookInfoId;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public Integer getMasterSex() {
        return masterSex;
    }

    public void setMasterSex(Integer masterSex) {
        this.masterSex = masterSex;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }
    public String getFirstPagePhotoUrl() {
        return firstPagePhotoUrl;
    }

    public void setFirstPagePhotoUrl(String firstPagePhotoUrl) {
        this.firstPagePhotoUrl = firstPagePhotoUrl;
    }

    public List<FamilyBookSubInfo> getFamilyBookSubInfolist() {
        return familyBookSubInfolist;
    }

    public void setFamilyBookSubInfolist(List<FamilyBookSubInfo> familyBookSubInfolist) {
        this.familyBookSubInfolist = familyBookSubInfolist;
    }
}
