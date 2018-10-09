package com.moerlong.carloan.modular.car.entity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.*;
import java.util.*;
public class CarGpsInfo {
    /** 主键 */
    private Long id;
    /** 车辆id */
    private Long carId;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 车辆gps安装时间 yyyymmdd */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date gpsInstallDate;
    /** 人车合影图片地址 */
    private String groupPhotoUrl;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 是否删除 0--否 1--是 */
    private Integer isDeleted;
    /** 备注，说明 */
    private String remark;

    public CarGpsInfo() {
    }

    public CarGpsInfo(Long id, Long carId, Long custId, Long applyId, Date gpsInstallDate, String groupPhotoUrl, String remark) {
        this.id = id;
        this.carId = carId;
        this.custId = custId;
        this.applyId = applyId;
        this.gpsInstallDate = gpsInstallDate;
        this.groupPhotoUrl = groupPhotoUrl;
        this.remark = remark;
    }
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
    public Date getGpsInstallDate() {
        return this.gpsInstallDate;
    }
    public void setGpsInstallDate(Date gpsInstallDate) {
        this.gpsInstallDate=gpsInstallDate;
    }
    public String getGroupPhotoUrl() {
        return this.groupPhotoUrl;
    }
    public void setGroupPhotoUrl(String groupPhotoUrl) {
        this.groupPhotoUrl=groupPhotoUrl;
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

