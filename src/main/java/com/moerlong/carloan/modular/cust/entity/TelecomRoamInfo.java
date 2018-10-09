package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;
public class TelecomRoamInfo {
    /** 主键 */
    private Long id;
    /** 借款申请id */
    private Long applyId;
    /** 用户id */
    private Long custId;
    /** 类型 0--主借人 1--共借人 */
    private Integer type;
    /** 漫游地址 */
    private String location;
    /** 近三月漫游天数 */
    private Integer roamDayCnt3m;
    /** 近六月漫游天数 */
    private Integer roamDayCnt6m;
    /** 近3月最大连续漫游天数 */
    private Integer continueRoamCnt3m;
    /** 近6月最大连续漫游天数 */
    private Integer continueRoamCnt6m;
    /** 近3月连续漫游1天以上的次数 */
    private Integer maxRoamDayCnt3m;
    /** 近6月连续漫游1天以上的次数 */
    private Integer maxRoamDayCnt6m;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
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
    public Long getApplyId() {
        return this.applyId;
    }
    public void setApplyId(Long applyId) {
        this.applyId=applyId;
    }
    public Long getCustId() {
        return this.custId;
    }
    public void setCustId(Long custId) {
        this.custId=custId;
    }
    public Integer getType() {
        return this.type;
    }
    public void setType(Integer type) {
        this.type=type;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location=location;
    }
    public Integer getRoamDayCnt3m() {
        return this.roamDayCnt3m;
    }
    public void setRoamDayCnt3m(Integer roamDayCnt3m) {
        this.roamDayCnt3m=roamDayCnt3m;
    }
    public Integer getRoamDayCnt6m() {
        return this.roamDayCnt6m;
    }
    public void setRoamDayCnt6m(Integer roamDayCnt6m) {
        this.roamDayCnt6m=roamDayCnt6m;
    }
    public Integer getContinueRoamCnt3m() {
        return this.continueRoamCnt3m;
    }
    public void setContinueRoamCnt3m(Integer continueRoamCnt3m) {
        this.continueRoamCnt3m=continueRoamCnt3m;
    }
    public Integer getContinueRoamCnt6m() {
        return this.continueRoamCnt6m;
    }
    public void setContinueRoamCnt6m(Integer continueRoamCnt6m) {
        this.continueRoamCnt6m=continueRoamCnt6m;
    }
    public Integer getMaxRoamDayCnt3m() {
        return this.maxRoamDayCnt3m;
    }
    public void setMaxRoamDayCnt3m(Integer maxRoamDayCnt3m) {
        this.maxRoamDayCnt3m=maxRoamDayCnt3m;
    }
    public Integer getMaxRoamDayCnt6m() {
        return this.maxRoamDayCnt6m;
    }
    public void setMaxRoamDayCnt6m(Integer maxRoamDayCnt6m) {
        this.maxRoamDayCnt6m=maxRoamDayCnt6m;
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

