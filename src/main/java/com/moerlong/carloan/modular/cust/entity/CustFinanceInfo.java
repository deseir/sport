package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;
public class CustFinanceInfo {
    /** 主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 类型 0--主借人 1--共借人 */
    private Integer type;
    /** 财力证明类型 0--房产 1--车辆 */
    private Integer finType;
    private String finTypeName;
    /** 性质 0--安置房 1--商品房 2--自建房福利房 3--写字楼 4--商铺 */
    private Integer property;
    private String propertyName;
    /** 状态 0--全款 1--抵押 2--按揭 */
    private Integer status;
    private String statusName;
    /** 数量 */
    private Integer num;
    /**附件地址*/
    private String attachUrl;
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
    public Integer getFinType() {
        return this.finType;
    }
    public void setFinType(Integer finType) {
        this.finType=finType;
    }
    public Integer getProperty() {
        return this.property;
    }
    public void setProperty(Integer property) {
        this.property=property;
    }
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status=status;
    }
    public Integer getNum() {
        return this.num;
    }
    public void setNum(Integer num) {
        this.num=num;
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

    public String getFinTypeName() {
        return finTypeName;
    }

    public void setFinTypeName(String finTypeName) {
        this.finTypeName = finTypeName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
    }
}

