package com.moerlong.carloan.modular.car.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 车辆详细配置_操作配置
 */
public class CarOperationConfig {
	/**
	 * 主键
	 */
    private Long id;
    /**
	 * 订单id
	 */
    private Long applyId;
    /**
	 * 客户id
	 */
    private Long custId;
    /**
	 * 车辆id
	 */
    private Long carId;
    /**
     * 操作配置  1标配 2选配 3无
     */
    private Integer carOperationConfiguration;
    /**
     * 前驻车雷达0:无 1:有
     */
    private Integer carFrontParkingRadar;
    /**
     * 后驻车雷达0:无 1:有
     */
    private Integer carRearParkingRadar;
    /**
     * 倒车视频影像0:无 1:有
     */
    private Integer carReversingVideo;
    /**
     * 全景摄像头0:无 1:有
     */
    private Integer carPanoramicCamera;
    /**
     * 定速巡航0:无 1:有
     */
    private Integer carCruiseControl;
    /**
     * 自适应巡航0:无 1:有
     */
    private Integer carAdaptiveCruise;
    /**
     * 自动泊车入位0:无 1:有
     */
    private Integer carAutoMaticPark;
    /**
     * 发动机启停技术0:无 1:有
     */
    private Integer carMotorStartEnd;
    /**
     * 上坡辅助0:无 1:有
     */
    private Integer carHillStartAssist;
    /**
     * 自动驻车0:无 1:有
     */
    private Integer carAutoParking;
    /**
     * 陡坡缓降0:无 1:有
     */
    private Integer carHdc;
    /**
     * 可变悬架0:无 1:有
     */
    private Integer carVariableSupension;
    /**
     * 空气悬架0:无 1:有
     */
    private Integer carAirSupension;
    /**
     * 可变转向比0:无 1:有
     */
    private Integer carVariableSteering;
    /**
     * 整体主动转向系统0:无 1:有
     */
    private Integer carActiveDynamic;

    /**
	 * 创建时间
	 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
	 * 修改时间
	 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date updateTime;
    /**
	 * 是否删除 0--否 1--是
	 */
    private Integer isDelete;
    /**
	 * 备注说明
	 */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Integer getCarOperationConfiguration() {
        return carOperationConfiguration;
    }

    public void setCarOperationConfiguration(Integer carOperationConfiguration) {
        this.carOperationConfiguration = carOperationConfiguration;
    }

    public Integer getCarFrontParkingRadar() {
        return carFrontParkingRadar;
    }

    public void setCarFrontParkingRadar(Integer carFrontParkingRadar) {
        this.carFrontParkingRadar = carFrontParkingRadar;
    }

    public Integer getCarRearParkingRadar() {
        return carRearParkingRadar;
    }

    public void setCarRearParkingRadar(Integer carRearParkingRadar) {
        this.carRearParkingRadar = carRearParkingRadar;
    }

    public Integer getCarReversingVideo() {
        return carReversingVideo;
    }

    public void setCarReversingVideo(Integer carReversingVideo) {
        this.carReversingVideo = carReversingVideo;
    }

    public Integer getCarPanoramicCamera() {
        return carPanoramicCamera;
    }

    public void setCarPanoramicCamera(Integer carPanoramicCamera) {
        this.carPanoramicCamera = carPanoramicCamera;
    }

    public Integer getCarCruiseControl() {
        return carCruiseControl;
    }

    public void setCarCruiseControl(Integer carCruiseControl) {
        this.carCruiseControl = carCruiseControl;
    }

    public Integer getCarAdaptiveCruise() {
        return carAdaptiveCruise;
    }

    public void setCarAdaptiveCruise(Integer carAdaptiveCruise) {
        this.carAdaptiveCruise = carAdaptiveCruise;
    }

    public Integer getCarAutoMaticPark() {
        return carAutoMaticPark;
    }

    public void setCarAutoMaticPark(Integer carAutoMaticPark) {
        this.carAutoMaticPark = carAutoMaticPark;
    }

    public Integer getCarMotorStartEnd() {
        return carMotorStartEnd;
    }

    public void setCarMotorStartEnd(Integer carMotorStartEnd) {
        this.carMotorStartEnd = carMotorStartEnd;
    }

    public Integer getCarHillStartAssist() {
        return carHillStartAssist;
    }

    public void setCarHillStartAssist(Integer carHillStartAssist) {
        this.carHillStartAssist = carHillStartAssist;
    }

    public Integer getCarAutoParking() {
        return carAutoParking;
    }

    public void setCarAutoParking(Integer carAutoParking) {
        this.carAutoParking = carAutoParking;
    }

    public Integer getCarHdc() {
        return carHdc;
    }

    public void setCarHdc(Integer carHdc) {
        this.carHdc = carHdc;
    }

    public Integer getCarVariableSupension() {
        return carVariableSupension;
    }

    public void setCarVariableSupension(Integer carVariableSupension) {
        this.carVariableSupension = carVariableSupension;
    }

    public Integer getCarAirSupension() {
        return carAirSupension;
    }

    public void setCarAirSupension(Integer carAirSupension) {
        this.carAirSupension = carAirSupension;
    }

    public Integer getCarVariableSteering() {
        return carVariableSteering;
    }

    public void setCarVariableSteering(Integer carVariableSteering) {
        this.carVariableSteering = carVariableSteering;
    }

    public Integer getCarActiveDynamic() {
        return carActiveDynamic;
    }

    public void setCarActiveDynamic(Integer carActiveDynamic) {
        this.carActiveDynamic = carActiveDynamic;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}