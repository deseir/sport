package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;
public class LivenessAuthInfo {
    /** 主键 自增 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 类型 0--主借人 1--共借人 */
    private Integer type;
    /** 是否认证 0--否 1--是 */
    private Integer isLivenessAuth;
    /** 活体眨眼图片地址 */
    private String livenessBlinkPhotoUrl;
    /** 活体上下点头图片地址 */
    private String livenessNodPhotoUrl;
    /** 活体张嘴图片地址 */
    private String livenessMouthPhotoUrl;
    /** 活体左右摇头图片地址 */
    private String livenessYawPhotoUrl;
    /**  */
    private String livenessFileUrl;
    /**  */
    private Date livenessAuthTime;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 是否删除 0-否 1-是 */
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
    public Integer getType() {
        return this.type;
    }
    public void setType(Integer type) {
        this.type=type;
    }
    public Integer getIsLivenessAuth() {
        return this.isLivenessAuth;
    }
    public void setIsLivenessAuth(Integer isLivenessAuth) {
        this.isLivenessAuth=isLivenessAuth;
    }
    public String getLivenessBlinkPhotoUrl() {
        return this.livenessBlinkPhotoUrl;
    }
    public void setLivenessBlinkPhotoUrl(String livenessBlinkPhotoUrl) {
        this.livenessBlinkPhotoUrl=livenessBlinkPhotoUrl;
    }
    public String getLivenessNodPhotoUrl() {
        return this.livenessNodPhotoUrl;
    }
    public void setLivenessNodPhotoUrl(String livenessNodPhotoUrl) {
        this.livenessNodPhotoUrl=livenessNodPhotoUrl;
    }
    public String getLivenessMouthPhotoUrl() {
        return this.livenessMouthPhotoUrl;
    }
    public void setLivenessMouthPhotoUrl(String livenessMouthPhotoUrl) {
        this.livenessMouthPhotoUrl=livenessMouthPhotoUrl;
    }
    public String getLivenessYawPhotoUrl() {
        return this.livenessYawPhotoUrl;
    }
    public void setLivenessYawPhotoUrl(String livenessYawPhotoUrl) {
        this.livenessYawPhotoUrl=livenessYawPhotoUrl;
    }
    public String getLivenessFileUrl() {
        return this.livenessFileUrl;
    }
    public void setLivenessFileUrl(String livenessFileUrl) {
        this.livenessFileUrl=livenessFileUrl;
    }
    public Date getLivenessAuthTime() {
        return this.livenessAuthTime;
    }
    public void setLivenessAuthTime(Date livenessAuthTime) {
        this.livenessAuthTime=livenessAuthTime;
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

