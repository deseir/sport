package com.moerlong.carloan.modular.loan.entity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.*;
import java.util.*;
public class ApplyInfo {
    /** 自增主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /**
     * 部门id
     */
    private Long deptId;
    /** 客户渠道id */
    private Long channelId;
    /** 产品类型 0--抵押 1--质押 */
    private Integer productType;
    /** 申请额度 单位(元) */
    private BigDecimal applyAmount;
    /** 剩余额度 单位(元) */
    private BigDecimal leftAmount;
    /** 申请期限  12/24/36 */
    private Integer applyPeriod;
    /** 还款方式 0--等额本息 1--先息后本 */
    private Integer repaymentType;
    /** 还款用途 1--教育支出 2--医疗 4--生意周转 8--装修 16--其他（可多选） */
    private Integer loanUsage;
    /** 还款用途其他情况说明 */
    private String loanUsageOther;
    /** 0--未婚 1--离异 2--丧偶 3--已婚不知晓 4--已婚知晓不能签字 5--已婚知晓可签字 */
    private Integer partnerKnow;
    /** 主流程状态 */
    private Integer status;
    /** 主流程状态描述 */
    private String statusDesc;
    /** 内勤资料录入状态 0--未提交 1--已提交 */
    private Integer sfrzStatus;
    private Integer nqlrStatus;
    /** 验车师验车状态 0--未提交 1--已提交 */
    private Integer ycStatus;
    /** 安装gps流程状态  0--未开始 1--发起安装申请 2--安装gps成功 3--确认安装成功 */
    private Integer gpsInstallStatus;
    /** 拆卸gps流程状态 0--未开始 1--发起拆卸申请 2--拆卸gps成功 */
    private Integer gpsUninstallStatus;
    /** 创建时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /** 更新时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /** 备注，说明 */
    private String remark;
    /** 是否删除 0-未删除 ；1-已删除；2-已拒绝 */
    private Integer isDeleted;
    /**是否代收 0-是 1-否 */
    private Integer isCollection;
    /** 服务费方式 0-按百分比 1-按金额 */
    private Integer serviceItem;
    /** 金额 单位(元)*/
    private BigDecimal percent;
    /** '百分比(%) */
    private BigDecimal moneyAmount;
    /** 服务费(元) */
    private BigDecimal serviceCharge;
    /** 手续费分期 0-分期 1-全部 */
    private Integer feeInstallment;
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
    public Long getChannelId() {
        return this.channelId;
    }
    public void setChannelId(Long channelId) {
        this.channelId=channelId;
    }
    public Integer getProductType() {
        return this.productType;
    }
    public void setProductType(Integer productType) {
        this.productType=productType;
    }
    public BigDecimal getApplyAmount() {
        return this.applyAmount;
    }
    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount=applyAmount;
    }
    public Integer getApplyPeriod() {
        return this.applyPeriod;
    }
    public void setApplyPeriod(Integer applyPeriod) {
        this.applyPeriod=applyPeriod;
    }
    public Integer getRepaymentType() {
        return this.repaymentType;
    }
    public void setRepaymentType(Integer repaymentType) {
        this.repaymentType=repaymentType;
    }
    public Integer getLoanUsage() {
        return this.loanUsage;
    }
    public void setLoanUsage(Integer loanUsage) {
        this.loanUsage=loanUsage;
    }
    public String getLoanUsageOther() {
        return this.loanUsageOther;
    }
    public void setLoanUsageOther(String loanUsageOther) {
        this.loanUsageOther=loanUsageOther;
    }
    public Integer getPartnerKnow() {
        return this.partnerKnow;
    }
    public void setPartnerKnow(Integer partnerKnow) {
        this.partnerKnow=partnerKnow;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public BigDecimal getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(BigDecimal leftAmount) {
        this.leftAmount = leftAmount;
    }

    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status=status;
    }
    public String getStatusDesc() {
        return this.statusDesc;
    }
    public void setStatusDesc(String statusDesc) {
        this.statusDesc=statusDesc;
    }
    public Integer getNqlrStatus() {
        return this.nqlrStatus;
    }
    public void setNqlrStatus(Integer nqlrStatus) {
        this.nqlrStatus=nqlrStatus;
    }
    public Integer getYcStatus() {
        return this.ycStatus;
    }
    public void setYcStatus(Integer ycStatus) {
        this.ycStatus=ycStatus;
    }
    public Integer getGpsInstallStatus() {
        return this.gpsInstallStatus;
    }
    public void setGpsInstallStatus(Integer gpsInstallStatus) {
        this.gpsInstallStatus=gpsInstallStatus;
    }
    public Integer getGpsUninstallStatus() {
        return this.gpsUninstallStatus;
    }
    public void setGpsUninstallStatus(Integer gpsUninstallStatus) {
        this.gpsUninstallStatus=gpsUninstallStatus;
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
	public Integer getSfrzStatus() {
		return sfrzStatus;
	}
	public void setSfrzStatus(Integer sfrzStatus) {
		this.sfrzStatus = sfrzStatus;
	}

    public Integer getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(Integer isCollection) {
        this.isCollection = isCollection;
    }

    public Integer getServiceItem() {
        return serviceItem;
    }

    public void setServiceItem(Integer serviceItem) {
        this.serviceItem = serviceItem;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Integer getFeeInstallment() {
        return feeInstallment;
    }

    public void setFeeInstallment(Integer feeInstallment) {
        this.feeInstallment = feeInstallment;
    }
}

