package com.moerlong.carloan.modular.cust.entity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.*;
import java.util.*;
public class ContractInfo {
    /** 主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 合同编号 */
    private String contractNo;
    /** 合同标题1 */
    private String contractTitle1;
    /** 合同地址1 */
    private String contractUrl1;
    /** 合同标题2 */
    private String contractTitle2;
    /** 合同地址2 */
    private String contractUrl2;
    /** 合同标题3 */
    private String contractTitle3;
    /** 合同地址3 */
    private String contractUrl3;
    /** 合同标题4 */
    private String contractTitle4;
    /** 合同地址4 */
    private String contractUrl4;
    /** 合同标题5 */
    private String contractTitle5;
    /** 合同地址5 */
    private String contractUrl5;
    /** 合同标题6 */
    private String contractTitle6;
    /** 合同地址6 */
    private String contractUrl6;
    /** 线下合同签订时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date contractSignDate;
    /** 合同签署现场照片地址 */
    private String contractSceneUrl;
    /**现场签约照片*/
    private String xianChangQianYueUrl;
    /**合同附件*/
    private String heTongFuJianUrl;
    /**抵押已受理照片*/
    private String diYaShouLiUrl;
    /** 合同签订附件地址 */
    private String contractAttachUrl;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 是否删除 0--否 1--是 */
    private Integer isDeleted;
    /** 备注，说明 */
    private String remark;
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id=id;
    }
    public Long getCustId() {
        return this.custId;
    }
    public void setCustId(Long custId) {
        this.custId=custId;
    }
    public Long getApplyId() {
        return this.applyId;
    }
    public void setApplyId(Long applyId) {
        this.applyId=applyId;
    }
    public String getContractNo() {
        return this.contractNo;
    }
    public void setContractNo(String contractNo) {
        this.contractNo=contractNo;
    }
    public String getContractTitle1() {
        return this.contractTitle1;
    }
    public void setContractTitle1(String contractTitle1) {
        this.contractTitle1=contractTitle1;
    }
    public String getXianChangQianYueUrl() {
        return xianChangQianYueUrl;
    }
    public void setXianChangQianYueUrl(String xianChangQianYueUrl) {
        this.xianChangQianYueUrl = xianChangQianYueUrl;
    }
    public String getHeTongFuJianUrl() {
        return heTongFuJianUrl;
    }
    public void setHeTongFuJianUrl(String heTongFuJianUrl) {
        this.heTongFuJianUrl = heTongFuJianUrl;
    }
    public String getDiYaShouLiUrl() {
        return diYaShouLiUrl;
    }
    public void setDiYaShouLiUrl(String diYaShouLiUrl) {
        this.diYaShouLiUrl = diYaShouLiUrl;
    }
    public String getContractUrl1() {
        return this.contractUrl1;
    }
    public void setContractUrl1(String contractUrl1) {
        this.contractUrl1=contractUrl1;
    }
    public String getContractTitle2() {
        return this.contractTitle2;
    }
    public void setContractTitle2(String contractTitle2) {
        this.contractTitle2=contractTitle2;
    }
    public String getContractUrl2() {
        return this.contractUrl2;
    }
    public void setContractUrl2(String contractUrl2) {
        this.contractUrl2=contractUrl2;
    }
    public String getContractTitle3() {
        return this.contractTitle3;
    }
    public void setContractTitle3(String contractTitle3) {
        this.contractTitle3=contractTitle3;
    }
    public String getContractUrl3() {
        return this.contractUrl3;
    }
    public void setContractUrl3(String contractUrl3) {
        this.contractUrl3=contractUrl3;
    }
    public String getContractTitle4() {
        return this.contractTitle4;
    }
    public void setContractTitle4(String contractTitle4) {
        this.contractTitle4=contractTitle4;
    }
    public String getContractUrl4() {
        return this.contractUrl4;
    }
    public void setContractUrl4(String contractUrl4) {
        this.contractUrl4=contractUrl4;
    }
    public String getContractTitle5() {
        return this.contractTitle5;
    }
    public void setContractTitle5(String contractTitle5) {
        this.contractTitle5=contractTitle5;
    }
    public String getContractUrl5() {
        return this.contractUrl5;
    }
    public void setContractUrl5(String contractUrl5) {
        this.contractUrl5=contractUrl5;
    }
    public String getContractTitle6() {
        return this.contractTitle6;
    }
    public void setContractTitle6(String contractTitle6) {
        this.contractTitle6=contractTitle6;
    }
    public String getContractUrl6() {
        return this.contractUrl6;
    }
    public void setContractUrl6(String contractUrl6) {
        this.contractUrl6=contractUrl6;
    }
    public Date getContractSignDate() {
        return this.contractSignDate;
    }
    public void setContractSignDate(Date contractSignDate) {
        this.contractSignDate=contractSignDate;
    }
    public String getContractSceneUrl() {
        return this.contractSceneUrl;
    }
    public void setContractSceneUrl(String contractSceneUrl) {
        this.contractSceneUrl=contractSceneUrl;
    }
    public String getContractAttachUrl() {
        return this.contractAttachUrl;
    }
    public void setContractAttachUrl(String contractAttachUrl) {
        this.contractAttachUrl=contractAttachUrl;
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
    public Integer getIsDeleted() {
        return this.isDeleted;
    }
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted=isDeleted;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark=remark;
    }
}

