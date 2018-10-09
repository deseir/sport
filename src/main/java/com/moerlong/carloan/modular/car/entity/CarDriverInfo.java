package com.moerlong.carloan.modular.car.entity;
import java.math.*;
import java.util.*;
public class CarDriverInfo {
    /** 自增主键 */
    private Long id;
    /** 用户id */
    private Long userId;
    /** 车辆id */
    private Long carId;
    /** 行驶证年检有效期 */
    private Date vehicleValidDate;
    /** 行驶证正面照片 */
    private String vehicleFrontPhoto;
    /** 行驶证背面照片 */
    private String vehicleBackPhoto;
    /** 是否有驾驶证 0--无 1--有 */
    private Integer isDriverLic;
    /** 驾驶证档案编号 */
    private String driverNo;
    /** 初次领取驾驶证时间 */
    private Date firstDriverDate;
    /** 准驾车型 */
    private String permitType;
    /** 驾驶证有效期开始时间 */
    private Date driverBeginDate;
    /** 驾驶证有效期结束时间 */
    private Date driverEndDate;
    /** 是否申请人持有 0--否 1--是 */
    private Integer isSelf;
    /** 持有人与申请人关系 */
    private String driverRelation;
    /** 持有人备注 */
    private String driverRemark;
    /** 驾驶证正面照片 */
    private String driverFrontPhoto;
    /** 驾驶证背面照片 */
    private String driverBackPhoto;
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
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId=userId;
    }
    public Long getCarId() {
        return this.carId;
    }
    public void setCarId(Long carId) {
        this.carId=carId;
    }
    public Date getVehicleValidDate() {
        return this.vehicleValidDate;
    }
    public void setVehicleValidDate(Date vehicleValidDate) {
        this.vehicleValidDate=vehicleValidDate;
    }
    public String getVehicleFrontPhoto() {
        return this.vehicleFrontPhoto;
    }
    public void setVehicleFrontPhoto(String vehicleFrontPhoto) {
        this.vehicleFrontPhoto=vehicleFrontPhoto;
    }
    public String getVehicleBackPhoto() {
        return this.vehicleBackPhoto;
    }
    public void setVehicleBackPhoto(String vehicleBackPhoto) {
        this.vehicleBackPhoto=vehicleBackPhoto;
    }
    public Integer getIsDriverLic() {
        return this.isDriverLic;
    }
    public void setIsDriverLic(Integer isDriverLic) {
        this.isDriverLic=isDriverLic;
    }
    public String getDriverNo() {
        return this.driverNo;
    }
    public void setDriverNo(String driverNo) {
        this.driverNo=driverNo;
    }
    public Date getFirstDriverDate() {
        return this.firstDriverDate;
    }
    public void setFirstDriverDate(Date firstDriverDate) {
        this.firstDriverDate=firstDriverDate;
    }
    public String getPermitType() {
        return this.permitType;
    }
    public void setPermitType(String permitType) {
        this.permitType=permitType;
    }
    public Date getDriverBeginDate() {
        return this.driverBeginDate;
    }
    public void setDriverBeginDate(Date driverBeginDate) {
        this.driverBeginDate=driverBeginDate;
    }
    public Date getDriverEndDate() {
        return this.driverEndDate;
    }
    public void setDriverEndDate(Date driverEndDate) {
        this.driverEndDate=driverEndDate;
    }
    public Integer getIsSelf() {
        return this.isSelf;
    }
    public void setIsSelf(Integer isSelf) {
        this.isSelf=isSelf;
    }
    public String getDriverRelation() {
        return this.driverRelation;
    }
    public void setDriverRelation(String driverRelation) {
        this.driverRelation=driverRelation;
    }
    public String getDriverRemark() {
        return this.driverRemark;
    }
    public void setDriverRemark(String driverRemark) {
        this.driverRemark=driverRemark;
    }
    public String getDriverFrontPhoto() {
        return this.driverFrontPhoto;
    }
    public void setDriverFrontPhoto(String driverFrontPhoto) {
        this.driverFrontPhoto=driverFrontPhoto;
    }
    public String getDriverBackPhoto() {
        return this.driverBackPhoto;
    }
    public void setDriverBackPhoto(String driverBackPhoto) {
        this.driverBackPhoto=driverBackPhoto;
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

