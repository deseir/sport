package com.moerlong.carloan.modular.car.entity;
import java.math.*;
import java.util.*;
public class CarGpsDetailInfo {
    /** 主键 */
    private Long id;
    /** 车辆id */
    private Long carId;
    /** 是否无线 0--否 1--是 2--其他*/
    private Integer isWiredless;
    /** gps串码 */
    private String gpsWiredNo;
    /** gps安装位置图片地址 */
    private String gpsPhotoUrl;
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
    public Long getCarId() {
        return this.carId;
    }
    public void setCarId(Long carId) {
        this.carId=carId;
    }
    public Integer getIsWiredless() {
        return this.isWiredless;
    }
    public void setIsWiredless(Integer isWiredless) {
        this.isWiredless=isWiredless;
    }
    public String getGpsWiredNo() {
        return this.gpsWiredNo;
    }
    public void setGpsWiredNo(String gpsWiredNo) {
        this.gpsWiredNo=gpsWiredNo;
    }
    public String getGpsPhotoUrl() {
        return this.gpsPhotoUrl;
    }
    public void setGpsPhotoUrl(String gpsPhotoUrl) {
        this.gpsPhotoUrl=gpsPhotoUrl;
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

    public CarGpsDetailInfo() {
    }

    public CarGpsDetailInfo(Long id, Integer isWiredless, String gpsWiredNo, String gpsPhotoUrl, String remark, Integer isDeleted) {
        this.id = id;
        this.isWiredless = isWiredless;
        this.gpsWiredNo = gpsWiredNo;
        this.gpsPhotoUrl = gpsPhotoUrl;
        this.remark = remark;
        this.isDeleted = isDeleted;
    }
}

