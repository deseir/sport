package com.moerlong.carloan.modular.car.entity;
import java.math.*;
import java.util.*;
public class CarPeccancyInfo {
    /** 自增主键 */
    private Long id;
    /** 车辆id */
    private Long carId;
    /** 累计违章次数 */
    private Integer totalNum;
    /** 累计违章罚款 */
    private BigDecimal totalMoney;
    /** 累计扣分 */
    private Integer totalValue;
    /** 累计扣12分次数 */
    private Integer totalFullNum;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 备注，说明 */
    private String remark;
    /** 是否删除 0-否 1-是 */
    private Integer isDeleted;
    /** 违章附件1 */
    private String violationAttachmentPhotol;
    /** 违章附件2 */
    private String violationAttachmentPhotol2;
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id=id;
    }
    public Long getCarId() {
        return this.carId;
    }
    public void setCarId(Long carId) {
        this.carId=carId;
    }
    public Integer getTotalNum() {
        return this.totalNum;
    }
    public void setTotalNum(Integer totalNum) {
        this.totalNum=totalNum;
    }
    public BigDecimal getTotalMoney() {
        return this.totalMoney;
    }
    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney=totalMoney;
    }
    public Integer getTotalValue() {
        return this.totalValue;
    }
    public void setTotalValue(Integer totalValue) {
        this.totalValue=totalValue;
    }
    public Integer getTotalFullNum() {
        return this.totalFullNum;
    }
    public void setTotalFullNum(Integer totalFullNum) {
        this.totalFullNum=totalFullNum;
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

    public String getViolationAttachmentPhotol() {
        return violationAttachmentPhotol;
    }

    public void setViolationAttachmentPhotol(String violationAttachmentPhotol) {
        this.violationAttachmentPhotol = violationAttachmentPhotol;
    }

    public String getViolationAttachmentPhotol2() {
        return violationAttachmentPhotol2;
    }

    public void setViolationAttachmentPhotol2(String violationAttachmentPhotol2) {
        this.violationAttachmentPhotol2 = violationAttachmentPhotol2;
    }
}

