package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
public class CustomerInfo {
    /** 自增主键 */
    private Long id;
    /** 姓名 */
    private String name;
    /** 手机号 */
    private String mobile;
    /** 性别 0-女 1-男 */
    private Integer sex;
    /** 民族 */
    private String nation;
    /** 出生年月日 yyyymmdd */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;
    /** 身份证号码 */
    private String certId;
    /** 身份证有效期起始日期 */
    private Date validateBegin;
    /** 身份证有效期结束日期 */
    private Date validateEnd;
    /** 签发机关 */
    private String signOrg;
    /** 教育程度 0--文盲 1--小学 2--初中 3--高中 4--专科 5--本科 6--硕士 7--博士 */
    private Integer education;
    /** 身份证正面图片地址 */
    private String idFrontPhotoUrl;
    /** 身份证背面图片地址 */
    private String idBackPhotoUrl;
    /** 居住证明材料图片地址 */
    private String proofOfResidence;
    /** 子女人数 */
    private Integer childNum;
    /** 子女情况 1--无子女 2--有子女未成年 4--有子女已成年（可多选） */
    private Integer childAdult;
    /** 居住地址 */
    private String liveAddress;
    /** 居住地性质  1--自有商品房全款 2--自有商业房按揭 4--自有商业房全款已抵押 8--自有自建房 16--自有安置房 32--单位宿舍 64--租用 128--父母房产 256--亲属房产（可多选） */
    private Integer liveType;
    /** 合住情况 1--单人居住 2--配偶合住 4--父母合住 8--亲属合住 16--子女合住 32--同事合住 64--朋友合住（可多选） */
    private Integer togetherLive;
    /** 配偶姓名（如果是已婚，再婚） */
    private String spouseName;
    /** 配偶手机号 */
    private String spousePhone;
    /** 紧急联系人1姓名 */
    private String contractName1;
    /** 紧急联系人1电话 */
    private String contractPhone1;
    /** 关系1 */
    private String contractRelation1;
    /** 紧急联系人2姓名 */
    private String contractName2;
    /** 紧急联系人2电话 */
    private String contractPhone2;
    /** 关系2 */
    private String contractRelation2;
    /** 紧急联系人3姓名 */
    private String contractName3;
    /** 紧急联系人3电话 */
    private String contractPhone3;
    /** 关系3 */
    private String contractRelation3;
    /** 创建时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /** 更新时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /** 备注，说明 */
    private String remark;
    /** 是否删除 0-否 1-是 */
    private Integer isDeleted;

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id=id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile=mobile;
    }
    public Integer getSex() {
        return this.sex;
    }
    public void setSex(Integer sex) {
        this.sex=sex;
    }
    public String getNation() {
        return this.nation;
    }
    public void setNation(String nation) {
        this.nation=nation;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCertId() {
        return this.certId;
    }
    public void setCertId(String certId) {
        this.certId=certId;
    }
    public Date getValidateBegin() {
        return this.validateBegin;
    }
    public void setValidateBegin(Date validateBegin) {
        this.validateBegin=validateBegin;
    }
    public Date getValidateEnd() {
        return this.validateEnd;
    }
    public void setValidateEnd(Date validateEnd) {
        this.validateEnd=validateEnd;
    }
    public String getSignOrg() {
        return this.signOrg;
    }
    public void setSignOrg(String signOrg) {
        this.signOrg=signOrg;
    }
    public Integer getEducation() {
        return this.education;
    }
    public void setEducation(Integer education) {
        this.education=education;
    }
    public String getIdFrontPhotoUrl() {
        return this.idFrontPhotoUrl;
    }
    public void setIdFrontPhotoUrl(String idFrontPhotoUrl) {
        this.idFrontPhotoUrl=idFrontPhotoUrl;
    }
    public String getIdBackPhotoUrl() {
        return this.idBackPhotoUrl;
    }
    public void setIdBackPhotoUrl(String idBackPhotoUrl) {
        this.idBackPhotoUrl=idBackPhotoUrl;
    }
    public String getProofOfResidence() {
        return proofOfResidence;
    }
    public void setProofOfResidence(String proofOfResidence) {
        this.proofOfResidence = proofOfResidence;
    }
    public Integer getChildNum() {
        return this.childNum;
    }
    public void setChildNum(Integer childNum) {
        this.childNum=childNum;
    }
    public Integer getChildAdult() {
        return this.childAdult;
    }
    public void setChildAdult(Integer childAdult) {
        this.childAdult=childAdult;
    }
    public String getLiveAddress() {
        return liveAddress;
    }
    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }
    public Integer getLiveType() {
        return this.liveType;
    }
    public void setLiveType(Integer liveType) {
        this.liveType=liveType;
    }
    public Integer getTogetherLive() {
        return this.togetherLive;
    }
    public void setTogetherLive(Integer togetherLive) {
        this.togetherLive=togetherLive;
    }
    public String getSpouseName() {
        return this.spouseName;
    }
    public void setSpouseName(String spouseName) {
        this.spouseName=spouseName;
    }
    public String getSpousePhone() {
        return this.spousePhone;
    }
    public void setSpousePhone(String spousePhone) {
        this.spousePhone=spousePhone;
    }
    public String getContractName1() {
        return this.contractName1;
    }
    public void setContractName1(String contractName1) {
        this.contractName1=contractName1;
    }
    public String getContractPhone1() {
        return this.contractPhone1;
    }
    public void setContractPhone1(String contractPhone1) {
        this.contractPhone1=contractPhone1;
    }
    public String getContractRelation1() {
        return this.contractRelation1;
    }
    public void setContractRelation1(String contractRelation1) {
        this.contractRelation1=contractRelation1;
    }
    public String getContractName2() {
        return this.contractName2;
    }
    public void setContractName2(String contractName2) {
        this.contractName2=contractName2;
    }
    public String getContractPhone2() {
        return this.contractPhone2;
    }
    public void setContractPhone2(String contractPhone2) {
        this.contractPhone2=contractPhone2;
    }
    public String getContractRelation2() {
        return this.contractRelation2;
    }
    public void setContractRelation2(String contractRelation2) {
        this.contractRelation2=contractRelation2;
    }
    public String getContractName3() {
        return this.contractName3;
    }
    public void setContractName3(String contractName3) {
        this.contractName3=contractName3;
    }
    public String getContractPhone3() {
        return this.contractPhone3;
    }
    public void setContractPhone3(String contractPhone3) {
        this.contractPhone3=contractPhone3;
    }
    public String getContractRelation3() {
        return this.contractRelation3;
    }
    public void setContractRelation3(String contractRelation3) {
        this.contractRelation3=contractRelation3;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime=createTime;
    }
    public Date getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime=updateTime;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark=remark;
    }
    public Integer getIsDeleted() {
        return this.isDeleted;
    }
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted=isDeleted;
    }
}

