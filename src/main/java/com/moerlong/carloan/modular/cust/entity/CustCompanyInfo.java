package com.moerlong.carloan.modular.cust.entity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.*;
import java.util.*;
public class CustCompanyInfo {
    /** 主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 类型 0--主借人 1--共借人 */
    private Integer type;
    /** 企业名称 */
    private String companyName;
    /** 企业所属行业 */
    private String industry;
    /** 企业成立时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date foundTime;
    /**工商信息查询（企业）附件地址*/
    private String gongshangEnterprisePhoto;
    /** 全国法院被执行人查询（企业） 0--正常 1--异常 */
    private Integer courtEnterprise;
    /** 全国法院被执行人查询（企业）附件地址 */
    private String courtEnterprisePhoto;
    /** 中国执行信息公开网查询（企业） 0--正常 1--异常 */
    private Integer zhixingEnterprise;
    /** 中国执行信息公开网查询（企业）附件地址 */
    private String zhixingEnterprisePhoto;
    /** 风险信息网查询（企业） 0--正常 1--异常 */
    private Integer riskEnterprise;
    /** 风险信息网查询（企业） 附件地址 */
    private String riskEnterprisePhoto;
    /** 风险预警网查询（企业） 0--正常 1--异常 */
    private Integer warnEnterprise;
    /** 风险预警网查询（企业） 附件地址 */
    private String warnEnterprisePhoto;
    /** 工商注册企业状态 0--存续 1--吊销 2--注销 3--无法核实 */
    private Integer enterpriseStatus;
    /** 企业备注 */
    private String enterpriseRemark;
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
    public Integer getType() {
        return this.type;
    }
    public void setType(Integer type) {
        this.type=type;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName=companyName;
    }
    public String getIndustry() {
        return this.industry;
    }
    public void setIndustry(String industry) {
        this.industry=industry;
    }
    public Date getFoundTime() {
        return this.foundTime;
    }
    public void setFoundTime(Date foundTime) {
        this.foundTime=foundTime;
    }
    public Integer getCourtEnterprise() {
        return this.courtEnterprise;
    }
    public void setCourtEnterprise(Integer courtEnterprise) {
        this.courtEnterprise=courtEnterprise;
    }
    public String getCourtEnterprisePhoto() {
        return this.courtEnterprisePhoto;
    }
    public void setCourtEnterprisePhoto(String courtEnterprisePhoto) {
        this.courtEnterprisePhoto=courtEnterprisePhoto;
    }
    public Integer getZhixingEnterprise() {
        return this.zhixingEnterprise;
    }
    public void setZhixingEnterprise(Integer zhixingEnterprise) {
        this.zhixingEnterprise=zhixingEnterprise;
    }
    public String getZhixingEnterprisePhoto() {
        return this.zhixingEnterprisePhoto;
    }
    public void setZhixingEnterprisePhoto(String zhixingEnterprisePhoto) {
        this.zhixingEnterprisePhoto=zhixingEnterprisePhoto;
    }
    public String getGongshangEnterprisePhoto() {
        return gongshangEnterprisePhoto;
    }
    public void setGongshangEnterprisePhoto(String gongshangEnterprisePhoto) {
        this.gongshangEnterprisePhoto = gongshangEnterprisePhoto;
    }
    public Integer getRiskEnterprise() {
        return this.riskEnterprise;
    }
    public void setRiskEnterprise(Integer riskEnterprise) {
        this.riskEnterprise=riskEnterprise;
    }
    public String getRiskEnterprisePhoto() {
        return this.riskEnterprisePhoto;
    }
    public void setRiskEnterprisePhoto(String riskEnterprisePhoto) {
        this.riskEnterprisePhoto=riskEnterprisePhoto;
    }
    public Integer getWarnEnterprise() {
        return this.warnEnterprise;
    }
    public void setWarnEnterprise(Integer warnEnterprise) {
        this.warnEnterprise=warnEnterprise;
    }
    public String getWarnEnterprisePhoto() {
        return this.warnEnterprisePhoto;
    }
    public void setWarnEnterprisePhoto(String warnEnterprisePhoto) {
        this.warnEnterprisePhoto=warnEnterprisePhoto;
    }
    public Integer getEnterpriseStatus() {
        return this.enterpriseStatus;
    }
    public void setEnterpriseStatus(Integer enterpriseStatus) {
        this.enterpriseStatus=enterpriseStatus;
    }
    public String getEnterpriseRemark() {
        return this.enterpriseRemark;
    }
    public void setEnterpriseRemark(String enterpriseRemark) {
        this.enterpriseRemark=enterpriseRemark;
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

