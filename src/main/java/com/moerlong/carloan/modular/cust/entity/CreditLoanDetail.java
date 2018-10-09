package com.moerlong.carloan.modular.cust.entity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.*;
import java.util.*;
public class CreditLoanDetail {
    /** 主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 类型 0--主借人 1--共借人 */
    private Integer type;
    /** 贷款机构 */
    private String loanOrg;
    /** 贷款金额 */
    private BigDecimal loanAmount;
    /** 贷款类型 0--抵押担保 1--信用免担保 2--组合认证 */
    private Integer loanType;
    private String loanTypeName;
    /** 贷款期数 */
    private Integer loanPeriod;
    /** 贷款开始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loanBeginTime;
    /** 贷款到期时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loanEndTime;
    /** 账户状态 0--正常 1--逾期 */
    private Integer accountStatus;
    private String accountStatusName;
    /** 五级分类 0--正常 1--关注 2--次级  3--可疑 4--损失 */
    private Integer fiveClassStatus;
    private String fiveClassStatusName;
    /** 本金金额 */
    private BigDecimal capitalAmount;
    /** 剩余期数 */
    private Integer leftPeriod;
    /** 本月应还款金额 */
    private BigDecimal curMonthPredictAmount;
    /** 本次应还款日 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date curMonthDate;
    /** 本月实还款金额 */
    private BigDecimal curMonthActuralAmount;
    /** 最近一次还款日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastRepaymentDatge;
    /** 当前逾期期数 */
    private Integer curOverdueNum;
    /** 当前逾期金额 */
    private BigDecimal curOverdueAmount;
    /** 逾期31-60天未还本金 */
    private BigDecimal overdueM2Capital;
    /** 逾期61-90天未还本金 */
    private BigDecimal overdueM3Capital;
    /** 逾期91-180天未还本金 */
    private BigDecimal overdueM45Capital;
    /** 逾期180天以上未还本金 */
    private BigDecimal overdueM6Capital;
    /** 两年前还款记录情况（文字描述） */
    private String repaymentInfo;
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
    public String getLoanOrg() {
        return this.loanOrg;
    }
    public void setLoanOrg(String loanOrg) {
        this.loanOrg=loanOrg;
    }
    public BigDecimal getLoanAmount() {
        return this.loanAmount;
    }
    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount=loanAmount;
    }
    public Integer getLoanType() {
        return this.loanType;
    }
    public void setLoanType(Integer loanType) {
        this.loanType=loanType;
    }
    public Integer getLoanPeriod() {
        return this.loanPeriod;
    }
    public void setLoanPeriod(Integer loanPeriod) {
        this.loanPeriod=loanPeriod;
    }
    public Date getLoanBeginTime() {
        return this.loanBeginTime;
    }
    public void setLoanBeginTime(Date loanBeginTime) {
        this.loanBeginTime=loanBeginTime;
    }
    public Date getLoanEndTime() {
        return this.loanEndTime;
    }
    public void setLoanEndTime(Date loanEndTime) {
        this.loanEndTime=loanEndTime;
    }
    public Integer getAccountStatus() {
        return this.accountStatus;
    }
    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus=accountStatus;
    }
    public Integer getFiveClassStatus() {
        return this.fiveClassStatus;
    }
    public void setFiveClassStatus(Integer fiveClassStatus) {
        this.fiveClassStatus=fiveClassStatus;
    }
    public BigDecimal getCapitalAmount() {
        return this.capitalAmount;
    }
    public void setCapitalAmount(BigDecimal capitalAmount) {
        this.capitalAmount=capitalAmount;
    }
    public Integer getLeftPeriod() {
        return this.leftPeriod;
    }
    public void setLeftPeriod(Integer leftPeriod) {
        this.leftPeriod=leftPeriod;
    }
    public BigDecimal getCurMonthPredictAmount() {
        return this.curMonthPredictAmount;
    }
    public void setCurMonthPredictAmount(BigDecimal curMonthPredictAmount) {
        this.curMonthPredictAmount=curMonthPredictAmount;
    }
    public Date getCurMonthDate() {
        return this.curMonthDate;
    }
    public void setCurMonthDate(Date curMonthDate) {
        this.curMonthDate=curMonthDate;
    }
    public BigDecimal getCurMonthActuralAmount() {
        return this.curMonthActuralAmount;
    }
    public void setCurMonthActuralAmount(BigDecimal curMonthActuralAmount) {
        this.curMonthActuralAmount=curMonthActuralAmount;
    }
    public Date getLastRepaymentDatge() {
        return this.lastRepaymentDatge;
    }
    public void setLastRepaymentDatge(Date lastRepaymentDatge) {
        this.lastRepaymentDatge=lastRepaymentDatge;
    }
    public Integer getCurOverdueNum() {
        return this.curOverdueNum;
    }
    public void setCurOverdueNum(Integer curOverdueNum) {
        this.curOverdueNum=curOverdueNum;
    }
    public BigDecimal getCurOverdueAmount() {
        return this.curOverdueAmount;
    }
    public void setCurOverdueAmount(BigDecimal curOverdueAmount) {
        this.curOverdueAmount=curOverdueAmount;
    }
    public BigDecimal getOverdueM2Capital() {
        return this.overdueM2Capital;
    }
    public void setOverdueM2Capital(BigDecimal overdueM2Capital) {
        this.overdueM2Capital=overdueM2Capital;
    }
    public BigDecimal getOverdueM3Capital() {
        return this.overdueM3Capital;
    }
    public void setOverdueM3Capital(BigDecimal overdueM3Capital) {
        this.overdueM3Capital=overdueM3Capital;
    }
    public BigDecimal getOverdueM45Capital() {
        return this.overdueM45Capital;
    }
    public void setOverdueM45Capital(BigDecimal overdueM45Capital) {
        this.overdueM45Capital=overdueM45Capital;
    }
    public BigDecimal getOverdueM6Capital() {
        return this.overdueM6Capital;
    }
    public void setOverdueM6Capital(BigDecimal overdueM6Capital) {
        this.overdueM6Capital=overdueM6Capital;
    }
    public String getRepaymentInfo() {
        return this.repaymentInfo;
    }
    public void setRepaymentInfo(String repaymentInfo) {
        this.repaymentInfo=repaymentInfo;
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

    public String getLoanTypeName() {
        return loanTypeName;
    }

    public void setLoanTypeName(String loanTypeName) {
        this.loanTypeName = loanTypeName;
    }

    public String getAccountStatusName() {
        return accountStatusName;
    }

    public void setAccountStatusName(String accountStatusName) {
        this.accountStatusName = accountStatusName;
    }

    public String getFiveClassStatusName() {
        return fiveClassStatusName;
    }

    public void setFiveClassStatusName(String fiveClassStatusName) {
        this.fiveClassStatusName = fiveClassStatusName;
    }
}

