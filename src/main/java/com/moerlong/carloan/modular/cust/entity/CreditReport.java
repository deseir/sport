package com.moerlong.carloan.modular.cust.entity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.*;
import java.util.*;
public class CreditReport {
    /** 主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 类型 0--主借人 1--共借人 */
    private Integer type;
    /** 征信报告采集时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date collectionDate;
    /** 个人住房贷款笔数 (信用提示模块) */
    private Integer houseLoanNum;
    /** 个人商用房贷款笔数 (信用提示模块) */
    private Integer houseBussLoanNum;
    /** 其他贷款笔数 (信用提示模块) */
    private Integer otherLoanNum;
    /** 首笔贷款发放月份 yyyymm (信用提示模块) */
    //@DateTimeFormat(pattern = "yyyy-MM")
    private String firstLoanStartDate;
    /** 贷记卡账户数 (信用提示模块) */
    private Integer creditCardNum;
    /** 首张借记卡发卡月份 yyyymm (信用提示模块) */
    //@DateTimeFormat(pattern = "yyyy-MM")
    private String firstCreditCardStartDate;
    /** 准贷记卡账户数 (信用提示模块) */
    private Integer semiCreditCardNum;
    /** 首张准贷记卡发卡月份 yyyymm (信用提示模块) */
    //@DateTimeFormat(pattern = "yyyy-MM")
    private String firstSemiCreditCardStartDate;
    /** 本人声明数量 (信用提示模块) */
    private Integer selfDeclareNum;
    /** 异议标注数量 (信用提示模块) */
    private Integer objectionNum;
    /** 贷款逾期笔数 (逾期违约模块) */
    private Integer loanOverdueNum;
    /** 贷款逾期月份数 (逾期违约模块) */
    private Integer loanOverdueMonthNum;
    /** 贷款逾期单月最高逾期总额 (逾期违约模块) */
    private BigDecimal loanMaxAmount;
    /** 最长逾期月数 (逾期违约模块) */
    private Integer loanMaxMonthNum;
    /** 贷记卡逾期账户数 (逾期违约模块) */
    private Integer cardOverdueNum;
    /** 贷记卡逾期月份数 (逾期违约模块) */
    private Integer cardMonthNum;
    /** 贷记卡单月最高逾期总额 (逾期违约模块) */
    private BigDecimal cardOverdueMaxAmount;
    /** 贷记卡最长逾期月份数 (逾期违约模块) */
    private Integer cardMaxMonthNum;
    /** 准贷记卡60天以上透支账户数 (逾期违约模块) */
    private Integer semiCardOverdueNum;
    /** 准贷记卡60天以上透支月份数 (逾期违约模块) */
    private Integer semiCardMonthNum;
    /** 准贷记卡60天以上透支单月最高透支金额 (逾期违约模块) */
    private Integer semiCardMaxAmount;
    /** 准贷记卡60天以上透支最长透支月数 (逾期违约模块) */
    private Integer semiCardMaxMonthNum;
    /** 贷款法人机构数 (授信概要模块) */
    private Integer loanLegalOrgNum;
    /** 贷款机构数 (授信概要模块) */
    private Integer loanOrgNum;
    /** 贷款笔数 (授信概要模块) */
    private Integer loanNum;
    /** 合同总金额 (授信概要模块) */
    private BigDecimal loanTotalAmount;
    /** 贷款余额 (授信概要模块) */
    private BigDecimal loanLeftAmount;
    /** 总月还款 (授信概要模块) */
    private BigDecimal loanTotalMonthAmount;
    /** 发卡法人机构数 (授信概要模块) */
    private Integer cardLegalOrgNum;
    /** 发卡机构数 (授信概要模块) */
    private Integer cardOrgNum;
    /** 账户数 (授信概要模块) */
    private Integer cardAccountNum;
    /** 授信总额 (授信概要模块) */
    private BigDecimal cardTotalAmount;
    /** 单家行最高授信额 (授信概要模块) */
    private BigDecimal cardMaxAmount;
    /** 单家行最低授信额 (授信概要模块) */
    private BigDecimal cardMinAmount;
    /** 已用额度 (授信概要模块) */
    private BigDecimal cardUsedAmount;
    /** 近6个月平均使用额度 (授信概要模块) */
    private BigDecimal cardAvgAmount;
    /** 近两个月征信历史查询次数 (根据记录表 自动算出) */
    private Integer historyQueryNum;
    /** 信用卡月还款（信用卡已用额度/10) */
    private BigDecimal cardMonthAmount;
    /** 信用贷款月还款金额 (信用贷款月还款+信用卡月还款) */
    private BigDecimal creditLoanMonthAmount;
    /** 征信报告附件地址 */
    private String reportAttachUrl;
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
    public Date getCollectionDate() {
        return this.collectionDate;
    }
    public void setCollectionDate(Date collectionDate) {
        this.collectionDate=collectionDate;
    }
    public Integer getHouseLoanNum() {
        return this.houseLoanNum;
    }
    public void setHouseLoanNum(Integer houseLoanNum) {
        this.houseLoanNum=houseLoanNum;
    }
    public Integer getHouseBussLoanNum() {
        return this.houseBussLoanNum;
    }
    public void setHouseBussLoanNum(Integer houseBussLoanNum) {
        this.houseBussLoanNum=houseBussLoanNum;
    }
    public Integer getOtherLoanNum() {
        return this.otherLoanNum;
    }
    public void setOtherLoanNum(Integer otherLoanNum) {
        this.otherLoanNum=otherLoanNum;
    }
    public Integer getCreditCardNum() {
        return this.creditCardNum;
    }
    public void setCreditCardNum(Integer creditCardNum) {
        this.creditCardNum=creditCardNum;
    }

    public Integer getSemiCreditCardNum() {
        return this.semiCreditCardNum;
    }
    public void setSemiCreditCardNum(Integer semiCreditCardNum) {
        this.semiCreditCardNum=semiCreditCardNum;
    }

    public String getFirstLoanStartDate() {
        return firstLoanStartDate;
    }

    public void setFirstLoanStartDate(String firstLoanStartDate) {
        this.firstLoanStartDate = firstLoanStartDate;
    }

    public String getFirstCreditCardStartDate() {
        return firstCreditCardStartDate;
    }

    public void setFirstCreditCardStartDate(String firstCreditCardStartDate) {
        this.firstCreditCardStartDate = firstCreditCardStartDate;
    }

    public String getFirstSemiCreditCardStartDate() {
        return firstSemiCreditCardStartDate;
    }

    public void setFirstSemiCreditCardStartDate(String firstSemiCreditCardStartDate) {
        this.firstSemiCreditCardStartDate = firstSemiCreditCardStartDate;
    }

    public Integer getSelfDeclareNum() {
        return this.selfDeclareNum;
    }
    public void setSelfDeclareNum(Integer selfDeclareNum) {
        this.selfDeclareNum=selfDeclareNum;
    }
    public Integer getObjectionNum() {
        return this.objectionNum;
    }
    public void setObjectionNum(Integer objectionNum) {
        this.objectionNum=objectionNum;
    }
    public Integer getLoanOverdueNum() {
        return this.loanOverdueNum;
    }
    public void setLoanOverdueNum(Integer loanOverdueNum) {
        this.loanOverdueNum=loanOverdueNum;
    }
    public Integer getLoanOverdueMonthNum() {
        return this.loanOverdueMonthNum;
    }
    public void setLoanOverdueMonthNum(Integer loanOverdueMonthNum) {
        this.loanOverdueMonthNum=loanOverdueMonthNum;
    }
    public BigDecimal getLoanMaxAmount() {
        return this.loanMaxAmount;
    }
    public void setLoanMaxAmount(BigDecimal loanMaxAmount) {
        this.loanMaxAmount=loanMaxAmount;
    }
    public Integer getLoanMaxMonthNum() {
        return this.loanMaxMonthNum;
    }
    public void setLoanMaxMonthNum(Integer loanMaxMonthNum) {
        this.loanMaxMonthNum=loanMaxMonthNum;
    }
    public Integer getCardOverdueNum() {
        return this.cardOverdueNum;
    }
    public void setCardOverdueNum(Integer cardOverdueNum) {
        this.cardOverdueNum=cardOverdueNum;
    }
    public Integer getCardMonthNum() {
        return this.cardMonthNum;
    }
    public void setCardMonthNum(Integer cardMonthNum) {
        this.cardMonthNum=cardMonthNum;
    }
    public BigDecimal getCardOverdueMaxAmount() {
        return this.cardOverdueMaxAmount;
    }
    public void setCardOverdueMaxAmount(BigDecimal cardOverdueMaxAmount) {
        this.cardOverdueMaxAmount=cardOverdueMaxAmount;
    }
    public Integer getCardMaxMonthNum() {
        return this.cardMaxMonthNum;
    }
    public void setCardMaxMonthNum(Integer cardMaxMonthNum) {
        this.cardMaxMonthNum=cardMaxMonthNum;
    }
    public Integer getSemiCardOverdueNum() {
        return this.semiCardOverdueNum;
    }
    public void setSemiCardOverdueNum(Integer semiCardOverdueNum) {
        this.semiCardOverdueNum=semiCardOverdueNum;
    }
    public Integer getSemiCardMonthNum() {
        return this.semiCardMonthNum;
    }
    public void setSemiCardMonthNum(Integer semiCardMonthNum) {
        this.semiCardMonthNum=semiCardMonthNum;
    }
    public Integer getSemiCardMaxAmount() {
        return this.semiCardMaxAmount;
    }
    public void setSemiCardMaxAmount(Integer semiCardMaxAmount) {
        this.semiCardMaxAmount=semiCardMaxAmount;
    }
    public Integer getSemiCardMaxMonthNum() {
        return this.semiCardMaxMonthNum;
    }
    public void setSemiCardMaxMonthNum(Integer semiCardMaxMonthNum) {
        this.semiCardMaxMonthNum=semiCardMaxMonthNum;
    }
    public Integer getLoanLegalOrgNum() {
        return this.loanLegalOrgNum;
    }
    public void setLoanLegalOrgNum(Integer loanLegalOrgNum) {
        this.loanLegalOrgNum=loanLegalOrgNum;
    }
    public Integer getLoanOrgNum() {
        return this.loanOrgNum;
    }
    public void setLoanOrgNum(Integer loanOrgNum) {
        this.loanOrgNum=loanOrgNum;
    }
    public Integer getLoanNum() {
        return this.loanNum;
    }
    public void setLoanNum(Integer loanNum) {
        this.loanNum=loanNum;
    }
    public BigDecimal getLoanTotalAmount() {
        return this.loanTotalAmount;
    }
    public void setLoanTotalAmount(BigDecimal loanTotalAmount) {
        this.loanTotalAmount=loanTotalAmount;
    }
    public BigDecimal getLoanLeftAmount() {
        return this.loanLeftAmount;
    }
    public void setLoanLeftAmount(BigDecimal loanLeftAmount) {
        this.loanLeftAmount=loanLeftAmount;
    }
    public BigDecimal getLoanTotalMonthAmount() {
        return this.loanTotalMonthAmount;
    }
    public void setLoanTotalMonthAmount(BigDecimal loanTotalMonthAmount) {
        this.loanTotalMonthAmount=loanTotalMonthAmount;
    }
    public Integer getCardLegalOrgNum() {
        return this.cardLegalOrgNum;
    }
    public void setCardLegalOrgNum(Integer cardLegalOrgNum) {
        this.cardLegalOrgNum=cardLegalOrgNum;
    }
    public Integer getCardOrgNum() {
        return this.cardOrgNum;
    }
    public void setCardOrgNum(Integer cardOrgNum) {
        this.cardOrgNum=cardOrgNum;
    }
    public Integer getCardAccountNum() {
        return this.cardAccountNum;
    }
    public void setCardAccountNum(Integer cardAccountNum) {
        this.cardAccountNum=cardAccountNum;
    }
    public BigDecimal getCardTotalAmount() {
        return this.cardTotalAmount;
    }
    public void setCardTotalAmount(BigDecimal cardTotalAmount) {
        this.cardTotalAmount=cardTotalAmount;
    }
    public BigDecimal getCardMaxAmount() {
        return this.cardMaxAmount;
    }
    public void setCardMaxAmount(BigDecimal cardMaxAmount) {
        this.cardMaxAmount=cardMaxAmount;
    }
    public BigDecimal getCardMinAmount() {
        return this.cardMinAmount;
    }
    public void setCardMinAmount(BigDecimal cardMinAmount) {
        this.cardMinAmount=cardMinAmount;
    }
    public BigDecimal getCardUsedAmount() {
        return this.cardUsedAmount;
    }
    public void setCardUsedAmount(BigDecimal cardUsedAmount) {
        this.cardUsedAmount=cardUsedAmount;
    }
    public BigDecimal getCardAvgAmount() {
        return this.cardAvgAmount;
    }
    public void setCardAvgAmount(BigDecimal cardAvgAmount) {
        this.cardAvgAmount=cardAvgAmount;
    }
    public Integer getHistoryQueryNum() {
        return this.historyQueryNum;
    }
    public void setHistoryQueryNum(Integer historyQueryNum) {
        this.historyQueryNum=historyQueryNum;
    }
    public BigDecimal getCardMonthAmount() {
        return this.cardMonthAmount;
    }
    public void setCardMonthAmount(BigDecimal cardMonthAmount) {
        this.cardMonthAmount=cardMonthAmount;
    }
    public BigDecimal getCreditLoanMonthAmount() {
        return this.creditLoanMonthAmount;
    }
    public void setCreditLoanMonthAmount(BigDecimal creditLoanMonthAmount) {
        this.creditLoanMonthAmount=creditLoanMonthAmount;
    }
    public String getReportAttachUrl() {
        return this.reportAttachUrl;
    }
    public void setReportAttachUrl(String reportAttachUrl) {
        this.reportAttachUrl=reportAttachUrl;
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

