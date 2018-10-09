package com.moerlong.carloan.modular.car.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 车辆详细配置_安全装备
 */
public class CarSafetyEquipment {
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
     * 安全装备 1标配 2选配 3无
     */
    private Integer carSafetyEquipment;
    /**
     * 主驾驶座安全气囊 0:无 1:有
     */
    private Integer carMainAirBag;
    /**
     * 副驾驶座安全气囊0:无 1:有
     */
    private Integer carFuAirBag;
    /**
     * 前排侧气囊0:无 1:有
     */
    private Integer carFrontAirBag;
    /**
     * 后排侧气囊0:无 1:有
     */
    private Integer carRearAirBag;
    /**
     * '前排头部气囊(气帘)0:无 1:有
     */
    private Integer carFrontHeadAirBag;
    /**
     * 后排头部气囊(气帘)0:无 1:有
     */
    private Integer carRearHeadAirBag;
    /**
     * 膝部气囊0:无 1:有
     */
    private Integer carKneeAirBag;
    /**
     * 胎压监测装置0:无 1:有
     */
    private Integer pressureMonitoring;
    /**
     * 零胎压继续行驶0:无 1:有
     */
    private Integer zeroTireRun;
    /**
     * 安全带未系提示0:无 1:有
     */
    private Integer seatBeltNotPrompted;
    /**
     * ISOFIX儿童座椅接口0:无 1:有
     */
    private Integer carIsofix;
    /**
     * ABS防抱死0:无 1:有
     */
    private Integer carAbs;
    /**
     * 制动力分配(EBD/CBC等)0:无 1:有
     */
    private Integer carEbd;
    /**
     * 刹车辅助(EBAB/BA等)0:无 1:有
     */
    private Integer carEbab;
    /**
     * 牵引力控制(ASR/TCS/TRC等)0:无 1:有
     */
    private Integer carAsr;
    /**
     * 车身稳定控制(ESC/ESP/DSC等)0:无 1:有
     */
    private Integer carEsc;
    /**
     * 并线辅助   0:无 1:有
     */
    private Integer parallelAuxiliary;
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

    public Integer getCarSafetyEquipment() {
        return carSafetyEquipment;
    }

    public void setCarSafetyEquipment(Integer carSafetyEquipment) {
        this.carSafetyEquipment = carSafetyEquipment;
    }

    public Integer getCarMainAirBag() {
        return carMainAirBag;
    }

    public void setCarMainAirBag(Integer carMainAirBag) {
        this.carMainAirBag = carMainAirBag;
    }

    public Integer getCarFuAirBag() {
        return carFuAirBag;
    }

    public void setCarFuAirBag(Integer carFuAirBag) {
        this.carFuAirBag = carFuAirBag;
    }

    public Integer getCarFrontAirBag() {
        return carFrontAirBag;
    }

    public void setCarFrontAirBag(Integer carFrontAirBag) {
        this.carFrontAirBag = carFrontAirBag;
    }

    public Integer getCarRearAirBag() {
        return carRearAirBag;
    }

    public void setCarRearAirBag(Integer carRearAirBag) {
        this.carRearAirBag = carRearAirBag;
    }

    public Integer getCarFrontHeadAirBag() {
        return carFrontHeadAirBag;
    }

    public void setCarFrontHeadAirBag(Integer carFrontHeadAirBag) {
        this.carFrontHeadAirBag = carFrontHeadAirBag;
    }

    public Integer getCarRearHeadAirBag() {
        return carRearHeadAirBag;
    }

    public void setCarRearHeadAirBag(Integer carRearHeadAirBag) {
        this.carRearHeadAirBag = carRearHeadAirBag;
    }

    public Integer getCarKneeAirBag() {
        return carKneeAirBag;
    }

    public void setCarKneeAirBag(Integer carKneeAirBag) {
        this.carKneeAirBag = carKneeAirBag;
    }

    public Integer getPressureMonitoring() {
        return pressureMonitoring;
    }

    public void setPressureMonitoring(Integer pressureMonitoring) {
        this.pressureMonitoring = pressureMonitoring;
    }

    public Integer getZeroTireRun() {
        return zeroTireRun;
    }

    public void setZeroTireRun(Integer zeroTireRun) {
        this.zeroTireRun = zeroTireRun;
    }

    public Integer getSeatBeltNotPrompted() {
        return seatBeltNotPrompted;
    }

    public void setSeatBeltNotPrompted(Integer seatBeltNotPrompted) {
        this.seatBeltNotPrompted = seatBeltNotPrompted;
    }

    public Integer getCarIsofix() {
        return carIsofix;
    }

    public void setCarIsofix(Integer carIsofix) {
        this.carIsofix = carIsofix;
    }

    public Integer getCarAbs() {
        return carAbs;
    }

    public void setCarAbs(Integer carAbs) {
        this.carAbs = carAbs;
    }

    public Integer getCarEbd() {
        return carEbd;
    }

    public void setCarEbd(Integer carEbd) {
        this.carEbd = carEbd;
    }

    public Integer getCarEbab() {
        return carEbab;
    }

    public void setCarEbab(Integer carEbab) {
        this.carEbab = carEbab;
    }

    public Integer getCarAsr() {
        return carAsr;
    }

    public void setCarAsr(Integer carAsr) {
        this.carAsr = carAsr;
    }

    public Integer getCarEsc() {
        return carEsc;
    }

    public void setCarEsc(Integer carEsc) {
        this.carEsc = carEsc;
    }

    public Integer getParallelAuxiliary() {
        return parallelAuxiliary;
    }

    public void setParallelAuxiliary(Integer parallelAuxiliary) {
        this.parallelAuxiliary = parallelAuxiliary;
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