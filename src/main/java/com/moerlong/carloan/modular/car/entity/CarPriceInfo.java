package com.moerlong.carloan.modular.car.entity;
import java.math.*;
import java.util.*;
public class CarPriceInfo {
    /** 主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 车300收车价 */
    private BigDecimal che300Price;
    /** 车300同行交易价(批发价)*/
    private BigDecimal che300ThPrice;
    /** 车300评估价附件地址 */
    private String che300AttachUrl;
    /** 精真估评估价 */
    private BigDecimal jingzhenguPrice;
    /** 精真估评估价附件地址 */
    private String jingzhenguAttachUrl;
    /** 裸车价 */
    private BigDecimal nakePrice;
    /** 折旧基数  (车龄月数 申请年月日减首次上户年月日 不足1月的按1月算）*/
    private Integer depreciationBase;
    /** 折旧系数 0--6‰(千分之6) 每月包括按揭房、抵押房、全款房、寿险保单、公积金  1--8‰（千分之8) 每月包括流水结息、他行车贷、打卡工资； */
    private BigDecimal depreciationRatio;
    /** 授信成数 */
    private BigDecimal creditRatio;
    /** 氢诺拟可贷金额（=裸车价*折旧基数*折旧系数*授信成数) */
    private BigDecimal tsingnuoPrice;
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
    public BigDecimal getChe300Price() {
        return this.che300Price;
    }
    public void setChe300Price(BigDecimal che300Price) {
        this.che300Price=che300Price;
    }
    public String getChe300AttachUrl() {
        return this.che300AttachUrl;
    }
    public void setChe300AttachUrl(String che300AttachUrl) {
        this.che300AttachUrl=che300AttachUrl;
    }
    public BigDecimal getJingzhenguPrice() {
        return this.jingzhenguPrice;
    }
    public void setJingzhenguPrice(BigDecimal jingzhenguPrice) {
        this.jingzhenguPrice=jingzhenguPrice;
    }
    public String getJingzhenguAttachUrl() {
        return this.jingzhenguAttachUrl;
    }
    public void setJingzhenguAttachUrl(String jingzhenguAttachUrl) {
        this.jingzhenguAttachUrl=jingzhenguAttachUrl;
    }
    public BigDecimal getNakePrice() {
        return this.nakePrice;
    }
    public void setNakePrice(BigDecimal nakePrice) {
        this.nakePrice=nakePrice;
    }
    public Integer getDepreciationBase() {
        return this.depreciationBase;
    }
    public void setDepreciationBase(Integer depreciationBase) {
        this.depreciationBase=depreciationBase;
    }
    public BigDecimal getDepreciationRatio() {
        return this.depreciationRatio;
    }
    public void setDepreciationRatio(BigDecimal depreciationRatio) {
        this.depreciationRatio=depreciationRatio;
    }
    public BigDecimal getCreditRatio() {
        return this.creditRatio;
    }
    public void setCreditRatio(BigDecimal creditRatio) {
        this.creditRatio=creditRatio;
    }
    public BigDecimal getTsingnuoPrice() {
        return this.tsingnuoPrice;
    }
    public void setTsingnuoPrice(BigDecimal tsingnuoPrice) {
        this.tsingnuoPrice=tsingnuoPrice;
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

    public BigDecimal getChe300ThPrice() {
        return che300ThPrice;
    }

    public void setChe300ThPrice(BigDecimal che300ThPrice) {
        this.che300ThPrice = che300ThPrice;
    }
}

