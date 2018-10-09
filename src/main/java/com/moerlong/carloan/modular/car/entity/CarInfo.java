package com.moerlong.carloan.modular.car.entity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.*;
import java.util.*;
public class CarInfo {
    /** 自增主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 车牌号 */
    private String carNum;
    /** 车辆通俗配置名称 */
    private String carConfigName;
    /** 车辆类型 小型轿车 小型越野客车 */
    private String carType;
    /** 车辆品牌 */
    private String carBrand;
    /** 车辆型号 */
    private String carModel;
    /** 车辆识别号 */
    private String vin;
    /** 发动机号 */
    private String engineNo;
    /** 车身颜色 */
    private String carColor;
    /** 国产/进口  0-国产  1-进口 */
    private Integer carImportType;
    /** 燃料种类 0-汽油  1-柴油  2-油电混合  3-纯电动 4--其他 */
    private Integer fuelType;
    /** 排量 （单位：ml) */
    private Integer displacement;
    /** 制造厂名称 */
    private String manufacturer;
    /** 使用性质 0-非运营   1-运营   2-营转非 */
    private Integer carUsage;
    /** 行驶里程数 （单位：公里) */
    private Integer mileage;
    /** 车辆获得方式 0-自购  2-赠与 */
    private Integer getType;
    /** 出厂日期 yyyymmdd */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date productDate;
    /** 首次上牌日期 yyyymmdd */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date firstLicDate;
    /** 本次上牌日期 yyyymmdd */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date currentLicDate;
    /** 车辆登记证书1-2页照片地址 */
    private String registerPhotoUrl1;
    /** 车辆登记证书3-4页照片地址 */
    private String registerPhotoUrl2;
    /** 车辆登记证书5-6页照片地址 */
    private String registerPhotoUrl3;
    /** 车辆登记证书7-8页照片地址 */
    private String registerPhotoUrl4;
    /** 车辆所在地省id */
    private String carLocationId;
    /** '车辆所在地省name */
    private String carLocationName;
    /** 车辆所在地城市id */
    private String carCityId;
    /** 车辆所在地城市name */
    private String carCityName;
    /** 车系id */
    private String carSeriesId;
    /** 车系name */
    private String carSeriesName;
    /** 车型id */
    private String carModelId;
    /** 车型name */
    private String carModelName;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 备注，说明 */
    private String remark;
    /** 是否删除 0-否 1-是 */
    private Integer isDeleted;
    /** 登记证书车辆型号 */
    private String registrationCarType;
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
    public String getCarNum() {
        return this.carNum;
    }
    public void setCarNum(String carNum) {
        this.carNum=carNum;
    }
    public String getCarConfigName() {
        return this.carConfigName;
    }
    public void setCarConfigName(String carConfigName) {
        this.carConfigName=carConfigName;
    }
    public String getCarType() {
        return this.carType;
    }
    public void setCarType(String carType) {
        this.carType=carType;
    }
    public String getCarBrand() {
        return this.carBrand;
    }
    public void setCarBrand(String carBrand) {
        this.carBrand=carBrand;
    }
    public String getCarModel() {
        return this.carModel;
    }
    public void setCarModel(String carModel) {
        this.carModel=carModel;
    }
    public String getVin() {
        return this.vin;
    }
    public void setVin(String vin) {
        this.vin=vin;
    }
    public String getEngineNo() {
        return this.engineNo;
    }
    public void setEngineNo(String engineNo) {
        this.engineNo=engineNo;
    }
    public String getCarColor() {
        return this.carColor;
    }
    public void setCarColor(String carColor) {
        this.carColor=carColor;
    }
    public Integer getCarImportType() {
        return this.carImportType;
    }
    public void setCarImportType(Integer carImportType) {
        this.carImportType=carImportType;
    }
    public Integer getFuelType() {
        return this.fuelType;
    }
    public void setFuelType(Integer fuelType) {
        this.fuelType=fuelType;
    }
    public Integer getDisplacement() {
        return this.displacement;
    }
    public void setDisplacement(Integer displacement) {
        this.displacement=displacement;
    }
    public String getManufacturer() {
        return this.manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer=manufacturer;
    }
    public Integer getCarUsage() {
        return this.carUsage;
    }
    public void setCarUsage(Integer carUsage) {
        this.carUsage=carUsage;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getGetType() {
        return this.getType;
    }
    public void setGetType(Integer getType) {
        this.getType=getType;
    }
    public Date getProductDate() {
        return this.productDate;
    }
    public void setProductDate(Date productDate) {
        this.productDate=productDate;
    }
    public Date getFirstLicDate() {
        return this.firstLicDate;
    }
    public void setFirstLicDate(Date firstLicDate) {
        this.firstLicDate=firstLicDate;
    }
    public Date getCurrentLicDate() {
        return this.currentLicDate;
    }
    public void setCurrentLicDate(Date currentLicDate) {
        this.currentLicDate=currentLicDate;
    }
    public String getRegisterPhotoUrl1() {
        return this.registerPhotoUrl1;
    }
    public void setRegisterPhotoUrl1(String registerPhotoUrl1) {
        this.registerPhotoUrl1=registerPhotoUrl1;
    }
    public String getRegisterPhotoUrl2() {
        return this.registerPhotoUrl2;
    }
    public void setRegisterPhotoUrl2(String registerPhotoUrl2) {
        this.registerPhotoUrl2=registerPhotoUrl2;
    }
    public String getRegisterPhotoUrl3() {
        return this.registerPhotoUrl3;
    }
    public void setRegisterPhotoUrl3(String registerPhotoUrl3) {
        this.registerPhotoUrl3=registerPhotoUrl3;
    }
    public String getRegisterPhotoUrl4() {
        return this.registerPhotoUrl4;
    }
    public void setRegisterPhotoUrl4(String registerPhotoUrl4) {
        this.registerPhotoUrl4=registerPhotoUrl4;
    }

    public String getCarLocationId() {
        return carLocationId;
    }

    public void setCarLocationId(String carLocationId) {
        this.carLocationId = carLocationId;
    }

    public String getCarLocationName() {
        return carLocationName;
    }

    public void setCarLocationName(String carLocationName) {
        this.carLocationName = carLocationName;
    }

    public String getCarCityId() {
        return carCityId;
    }

    public void setCarCityId(String carCityId) {
        this.carCityId = carCityId;
    }

    public String getCarCityName() {
        return carCityName;
    }

    public void setCarCityName(String carCityName) {
        this.carCityName = carCityName;
    }

    public String getCarSeriesId() {
        return carSeriesId;
    }

    public void setCarSeriesId(String carSeriesId) {
        this.carSeriesId = carSeriesId;
    }

    public String getCarSeriesName() {
        return carSeriesName;
    }

    public void setCarSeriesName(String carSeriesName) {
        this.carSeriesName = carSeriesName;
    }

    public String getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(String carModelId) {
        this.carModelId = carModelId;
    }

    public String getCarModelName() {
        return carModelName;
    }

    public void setCarModelName(String carModelName) {
        this.carModelName = carModelName;
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

    public String getRegistrationCarType() {
        return registrationCarType;
    }

    public void setRegistrationCarType(String registrationCarType) {
        this.registrationCarType = registrationCarType;
    }
}

