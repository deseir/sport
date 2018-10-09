package com.moerlong.carloan.modular.car.entity;
import java.math.*;
import java.util.*;
public class CarInsureDetailInfo {
    /** 主键，自增 */
    private Long id;
    /** 保险id */
    private Long insureId;
    /** 险种 0--车辆损失险 1--第三者责任险 2--车上人员责任险：司机 3--车上人员责任险：乘客 4--车身划痕险 5--涉水险 6--自燃损失险 7--玻璃单独破碎险（国产） 8--玻璃单独破碎险（进口） */
    private Integer type;
    /** 是否有不计免赔 0--否 1--是 */
    private Integer isNoDeduct;
    /** 最高赔付金额 */
    private BigDecimal maxPayAmount;
    /** 保费金额 */
    private BigDecimal amount;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 是否删除 0-否 1-是 */
    private Integer isDeleted;
    /** 备注，说明 */
    private String remark;
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id=id;
    }
    public Long getInsureId() {
        return this.insureId;
    }
    public void setInsureId(Long insureId) {
        this.insureId=insureId;
    }
    public Integer getType() {
        return this.type;
    }
    public void setType(Integer type) {
        this.type=type;
    }
    public Integer getIsNoDeduct() {
        return this.isNoDeduct;
    }
    public void setIsNoDeduct(Integer isNoDeduct) {
        this.isNoDeduct=isNoDeduct;
    }
    public BigDecimal getMaxPayAmount() {
        return this.maxPayAmount;
    }
    public void setMaxPayAmount(BigDecimal maxPayAmount) {
        this.maxPayAmount=maxPayAmount;
    }
    public BigDecimal getAmount() {
        return this.amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount=amount;
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

