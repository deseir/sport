package com.moerlong.carloan.modular.car.entity;
import java.math.*;
import java.util.*;
    public class CarPhotoInfo {
    /** 自增主键 */
    private Long id;
    /** 图片大类id */
    private Long bigClassId;
    /** 车辆id */
    private Long carId;
    /** 图片名称 */
    private String photoName;
    /** 图片地址 */
    private String photoUrl;
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

    public Long getBigClassId() {
        return bigClassId;
    }

    public void setBigClassId(Long bigClassId) {
        this.bigClassId = bigClassId;
    }

    public Long getCarId() {
        return this.carId;
    }
    public void setCarId(Long carId) {
        this.carId=carId;
    }
    public String getPhotoName() {
        return this.photoName;
    }
    public void setPhotoName(String photoName) {
        this.photoName=photoName;
    }
    public String getPhotoUrl() {
        return this.photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl=photoUrl;
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

    public CarPhotoInfo() { }

    public CarPhotoInfo(Long id, String photoName, String photoUrl, String remark,Integer isDeleted,Long bigClassId) {
            this.id = id;
            this.photoName = photoName;
            this.photoUrl = photoUrl;
            this.remark = remark;
            this.isDeleted = isDeleted;
            this.bigClassId = bigClassId;
        }
 }

