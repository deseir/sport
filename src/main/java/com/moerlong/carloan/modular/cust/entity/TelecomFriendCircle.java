package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;
public class TelecomFriendCircle {
    /** 朋友圈id */
    private Long id;
    /** 借款申请id */
    private Long applyId;
    /** 用户id */
    private Long custId;
    /** 类型 0--主借人 1--共借人 */
    private Integer type;
    /** 对方号码 */
    private String peerNumber;
    /** 联系人号码归属地 */
    private String peerNumLoc;
    /** 号码类型 */
    private String groupName;
    /** 号码标识 */
    private String companyName;
    /** 通话次数 */
    private String callCnt;
    /** 通话时长(秒) */
    private String callTime;
    /** 主叫次数 */
    private String dialCnt;
    /** 被叫次数 */
    private String dialedCnt;
    /** 主叫时长(秒) */
    private String dialTime;
    /** 被叫时长(秒) */
    private String dialedTime;
    /** 6月top3或者3月top3  peer_num_top3_3m或者peer_num_top3_6m */
    private String keyTop;
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
    public String getPeerNumber() {
        return this.peerNumber;
    }
    public void setPeerNumber(String peerNumber) {
        this.peerNumber=peerNumber;
    }
    public String getPeerNumLoc() {
        return this.peerNumLoc;
    }
    public void setPeerNumLoc(String peerNumLoc) {
        this.peerNumLoc=peerNumLoc;
    }
    public String getGroupName() {
        return this.groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName=groupName;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName=companyName;
    }
    public String getCallCnt() {
        return this.callCnt;
    }
    public void setCallCnt(String callCnt) {
        this.callCnt=callCnt;
    }
    public String getCallTime() {
        return this.callTime;
    }
    public void setCallTime(String callTime) {
        this.callTime=callTime;
    }
    public String getDialCnt() {
        return this.dialCnt;
    }
    public void setDialCnt(String dialCnt) {
        this.dialCnt=dialCnt;
    }
    public String getDialedCnt() {
        return this.dialedCnt;
    }
    public void setDialedCnt(String dialedCnt) {
        this.dialedCnt=dialedCnt;
    }
    public String getDialTime() {
        return this.dialTime;
    }
    public void setDialTime(String dialTime) {
        this.dialTime=dialTime;
    }
    public String getDialedTime() {
        return this.dialedTime;
    }
    public void setDialedTime(String dialedTime) {
        this.dialedTime=dialedTime;
    }
    public String getKeyTop() {
        return this.keyTop;
    }
    public void setKeyTop(String keyTop) {
        this.keyTop=keyTop;
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

