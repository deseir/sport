package com.moerlong.carloan.modular.car.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 车辆详细配置_变速箱配置
 */
public class CarGearBox {
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
	 * 变速箱配置（1:标配2:选配3：无）'
	 */
    private Integer carGearBox;
    /**
	 * 档位个数
	 */
    private Integer carGearNum;
    /**
	 * 变速箱类型
	 */
    private String carGearType;
    /**
	 * 变速箱描述
	 */
    private String carGearDesc;
    /**
	 * 是否删除 0--否 1--是
	 */
    private Integer isDelete;
    /**
	 * 创建时间
	 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
	 * 更新时间
	 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date updateTime;
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

    public Integer getCarGearBox() {
        return carGearBox;
    }

    public void setCarGearBox(Integer carGearBox) {
        this.carGearBox = carGearBox;
    }

    public Integer getCarGearNum() {
        return carGearNum;
    }

    public void setCarGearNum(Integer carGearNum) {
        this.carGearNum = carGearNum;
    }

    public String getCarGearType() {
        return carGearType;
    }

    public void setCarGearType(String carGearType) {
        this.carGearType = carGearType == null ? null : carGearType.trim();
    }

    public String getCarGearDesc() {
        return carGearDesc;
    }

    public void setCarGearDesc(String carGearDesc) {
        this.carGearDesc = carGearDesc == null ? null : carGearDesc.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}