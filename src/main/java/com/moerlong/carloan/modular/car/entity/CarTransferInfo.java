package com.moerlong.carloan.modular.car.entity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.*;
import java.util.*;
public class CarTransferInfo {
    /** 自增主键 */
    private Long id;
    /** 车辆id */
    private Long carId;
    /** 转移登记姓名 */
    private String name;
    /** 转移登记身份证号 */
    private String certId;
    /** 获得方式 */
    private String getType;
    /** 转移登记日期 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date regDate;
    /** 创建时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;
    /** 更新时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
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
    public Long getCarId() {
        return this.carId;
    }
    public void setCarId(Long carId) {
        this.carId=carId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public String getCertId() {
        return this.certId;
    }
    public void setCertId(String certId) {
        this.certId=certId;
    }
    public String getGetType() {
        return this.getType;
    }
    public void setGetType(String getType) {
        this.getType=getType;
    }
    public Date getRegDate() {
        return this.regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate=regDate;
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

