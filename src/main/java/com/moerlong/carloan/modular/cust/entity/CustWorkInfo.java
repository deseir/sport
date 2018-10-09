package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;
public class CustWorkInfo {
    /** 自增主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 收入来源 1--自雇有执照 2--自雇无执照 4--受薪 8--待业 16--自由职业者 */
    private Integer incomeType;
    /** 单位名称 如果收入来源是 1，2，4的话 */
    private String companyName;
    /** 单位性质 0--国有企业 1--国有控股企业 2--外资企业 3--合资企业 4--私营业务 5--事业单位/政府 */
    private Integer companyType;
    /** 单位地址 */
    private String companyAddress;
    /** 单位电话 */
    private String companyTel;
    /** 部门 */
    private String department;
    /** 职务 */
    private String job;
    /** 入职年限（单位：年） */
    private BigDecimal workAge;
    /** 月均收入（单位：万元） */
    private BigDecimal monthIncome;
    /** 配偶收入来源 1--自雇有执照 2--自雇无执照 4--受薪 8--待业 16--自由职业者 */
    private Integer spouseIncomeType;
    /** 配偶单位名称 如果收入来源是 1，2，4的话 */
    private String spouseCompanyName;
    /** 配偶单位性质 0--国有企业 1--国有控股企业 2--外资企业 3--合资企业 4--私营业务 5--事业单位/政府 */
    private Integer spouseCompanyType;
    /** 配偶单位地址 */
    private String spouseCompanyAddress;
    /** 配偶单位电话 */
    private String spouseCompanyTel;
    /** 配偶部门 */
    private String spouseDepartment;
    /** 配偶职务 */
    private String spouseJob;
    /** 配偶入职年限（单位：年） */
    private BigDecimal spouseWorkAge;
    /** 配偶月均收入（单位：万元） */
    private BigDecimal spouseMonthIncome;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
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
    public Long getCustId() {
        return this.custId;
    }
    public void setCustId(Long custId) {
        this.custId=custId;
    }
    public Integer getIncomeType() {
        return this.incomeType;
    }
    public void setIncomeType(Integer incomeType) {
        this.incomeType=incomeType;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName=companyName;
    }
    public Integer getCompanyType() {
        return this.companyType;
    }
    public void setCompanyType(Integer companyType) {
        this.companyType=companyType;
    }
    public String getCompanyAddress() {
        return this.companyAddress;
    }
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress=companyAddress;
    }
    public String getCompanyTel() {
        return this.companyTel;
    }
    public void setCompanyTel(String companyTel) {
        this.companyTel=companyTel;
    }
    public String getDepartment() {
        return this.department;
    }
    public void setDepartment(String department) {
        this.department=department;
    }
    public String getJob() {
        return this.job;
    }
    public void setJob(String job) {
        this.job=job;
    }
    public BigDecimal getWorkAge() {
        return this.workAge;
    }
    public void setWorkAge(BigDecimal workAge) {
        this.workAge=workAge;
    }
    public BigDecimal getMonthIncome() {
        return this.monthIncome;
    }
    public void setMonthIncome(BigDecimal monthIncome) {
        this.monthIncome=monthIncome;
    }
    public Integer getSpouseIncomeType() {
        return this.spouseIncomeType;
    }
    public void setSpouseIncomeType(Integer spouseIncomeType) {
        this.spouseIncomeType=spouseIncomeType;
    }
    public String getSpouseCompanyName() {
        return this.spouseCompanyName;
    }
    public void setSpouseCompanyName(String spouseCompanyName) {
        this.spouseCompanyName=spouseCompanyName;
    }
    public Integer getSpouseCompanyType() {
        return this.spouseCompanyType;
    }
    public void setSpouseCompanyType(Integer spouseCompanyType) {
        this.spouseCompanyType=spouseCompanyType;
    }
    public String getSpouseCompanyAddress() {
        return this.spouseCompanyAddress;
    }
    public void setSpouseCompanyAddress(String spouseCompanyAddress) {
        this.spouseCompanyAddress=spouseCompanyAddress;
    }
    public String getSpouseCompanyTel() {
        return this.spouseCompanyTel;
    }
    public void setSpouseCompanyTel(String spouseCompanyTel) {
        this.spouseCompanyTel=spouseCompanyTel;
    }
    public String getSpouseDepartment() {
        return this.spouseDepartment;
    }
    public void setSpouseDepartment(String spouseDepartment) {
        this.spouseDepartment=spouseDepartment;
    }
    public String getSpouseJob() {
        return this.spouseJob;
    }
    public void setSpouseJob(String spouseJob) {
        this.spouseJob=spouseJob;
    }
    public BigDecimal getSpouseWorkAge() {
        return this.spouseWorkAge;
    }
    public void setSpouseWorkAge(BigDecimal spouseWorkAge) {
        this.spouseWorkAge=spouseWorkAge;
    }
    public BigDecimal getSpouseMonthIncome() {
        return this.spouseMonthIncome;
    }
    public void setSpouseMonthIncome(BigDecimal spouseMonthIncome) {
        this.spouseMonthIncome=spouseMonthIncome;
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

