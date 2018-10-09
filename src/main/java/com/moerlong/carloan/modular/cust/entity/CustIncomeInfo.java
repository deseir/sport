package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;
public class CustIncomeInfo {
    /** 主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 认收方案类别 0--按揭房贷方案 1--抵押房贷方案 2--全款房方案 3--寿险保单方案 4--他行车贷方案 5--流水结息方案 6--公积金方案 7--打卡工资方案 */
    private Integer incomeType;
    /** 收入金额 */
    private BigDecimal incomeAmount;
    /** 收入认定金额 （收入金额*对应方案类别的系数) */
    private BigDecimal incomeConfirmAmount;
    /** 拟定可贷金额 */
    private BigDecimal loanAmount;
    /** dti 收入负债比 = 收入认定金额/(征信认定的负债+本次拟贷款月供) */
    private BigDecimal dti;
    /** 附件地址 */
    private String attachUrl;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 是否删除 0--否 1--是 */
    private Integer isDeleted;
    /** 备注，说明 */
    private String remark;
    /** 征信认定的负债 需要查询央行征信表*/
    private BigDecimal creditLoanMonthAmount;
    //本次拟贷款本金
    private BigDecimal applyAmount;
    //本次拟贷款期限
    private Integer applyPeriod;

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
    public Integer getIncomeType() {
        return this.incomeType;
    }
    public void setIncomeType(Integer incomeType) {
        this.incomeType=incomeType;
    }
    public BigDecimal getIncomeAmount() {
        return this.incomeAmount;
    }
    public void setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount=incomeAmount;
    }
    public BigDecimal getIncomeConfirmAmount() {
        return this.incomeConfirmAmount;
    }
    public void setIncomeConfirmAmount(BigDecimal incomeConfirmAmount) {
        this.incomeConfirmAmount=incomeConfirmAmount;
    }
    public BigDecimal getLoanAmount() {
        return this.loanAmount;
    }
    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount=loanAmount;
    }
    public BigDecimal getDti() {
        return this.dti;
    }
    public void setDti(BigDecimal dti) {
        this.dti=dti;
    }
    public String getAttachUrl() {
        return this.attachUrl;
    }
    public void setAttachUrl(String attachUrl) {
        this.attachUrl=attachUrl;
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

    public BigDecimal getCreditLoanMonthAmount() {
        return creditLoanMonthAmount;
    }

    public void setCreditLoanMonthAmount(BigDecimal creditLoanMonthAmount) {
        this.creditLoanMonthAmount = creditLoanMonthAmount;
    }

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public Integer getApplyPeriod() {
        return applyPeriod;
    }

    public void setApplyPeriod(Integer applyPeriod) {
        this.applyPeriod = applyPeriod;
    }
}

