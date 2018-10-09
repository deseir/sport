package com.moerlong.carloan.modular.loan.entity;
import java.math.*;
import java.util.*;
public class DataKeepInfo {
    /** 主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 车辆id */
    private Long carId;
    /** 纸质资料份数 */
    private Integer paperDataNum;
    /** 纸质合同份数 */
    private Integer contractNum;
    /** 是否存留抵押登记证书 0--否 1--是 */
    private Integer isCarRegister;
    /** 是否存有客户备用钥匙 0--否 1--是 */
    private Integer isCarKey;
    /** 存档时间 */
    private Date keepDate;
    /** 归还时间 */
    private Date unkeepDate;
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
    public Long getCarId() {
        return this.carId;
    }
    public void setCarId(Long carId) {
        this.carId=carId;
    }
    public Integer getPaperDataNum() {
        return this.paperDataNum;
    }
    public void setPaperDataNum(Integer paperDataNum) {
        this.paperDataNum=paperDataNum;
    }
    public Integer getContractNum() {
        return this.contractNum;
    }
    public void setContractNum(Integer contractNum) {
        this.contractNum=contractNum;
    }
    public Integer getIsCarRegister() {
        return this.isCarRegister;
    }
    public void setIsCarRegister(Integer isCarRegister) {
        this.isCarRegister=isCarRegister;
    }
    public Integer getIsCarKey() {
        return this.isCarKey;
    }
    public void setIsCarKey(Integer isCarKey) {
        this.isCarKey=isCarKey;
    }
    public Date getKeepDate() {
        return this.keepDate;
    }
    public void setKeepDate(Date keepDate) {
        this.keepDate=keepDate;
    }
    public Date getUnkeepDate() {
        return this.unkeepDate;
    }
    public void setUnkeepDate(Date unkeepDate) {
        this.unkeepDate=unkeepDate;
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

