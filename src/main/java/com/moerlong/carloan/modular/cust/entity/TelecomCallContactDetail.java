package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;
public class TelecomCallContactDetail {
    /** 序号id */
    private Long id;
    /** 借款申请id */
    private Long applyId;
    /** 用户id */
    private Long custId;
    /** 类型 0--主借人 1--共借人 */
    private Integer type;
    /** 联系人号码归属地 */
    private String city;
    /** 联系人号码 */
    private String peerNum;
    /** 是否是紧急联系人 0--否 1--是 */
    private Integer isEmergency;
    /** 与联系人关系 */
    private String pRelation;
    /** 号码类型 */
    private String groupName;
    /** 号码标识 */
    private String companyName;
    /** 近一周通话次数 */
    private String callCnt1w;
    /** 近一月通话次数 */
    private String callCnt1m;
    /** 近三月通话次数 */
    private String callCnt3m;
    /** 近六月通话次数 */
    private String callCnt6m;
    /** 近三月通话时长(秒) */
    private String callTime3m;
    /** 近六月通话时长(秒) */
    private String callTime6m;
    /** 近三月主叫次数 */
    private String dialCnt3m;
    /** 近六月主叫次数 */
    private String dialCnt6m;
    /** 近三月主叫时长(秒) */
    private String dialTime3m;
    /** 近六月主叫时长(秒) */
    private String dialTime6m;
    /** 近三月被叫次数 */
    private String dialedCnt3m;
    /** 近六月被叫次数 */
    private String dialedCnt6m;
    /** 近三月被叫时长(秒) */
    private String dialedTime3m;
    /** 近六月被叫时长(秒) */
    private String dialedTime6m;
    /** 近三月早晨通话次数 */
    private String callCntMorning3m;
    /** 近六月早晨通话次数 */
    private String callCntMorning6m;
    /** 近三月中午通话次数 */
    private String callCntNoon3m;
    /** 近六月中午通话次数 */
    private String callCntNoon6m;
    /** 近三月下午通话次数 */
    private String callCntAfternoon3m;
    /** 近六月下午通话次数 */
    private String callCntAfternoon6m;
    /** 近三月晚上通话次数 */
    private String callCntEvening3m;
    /** 近六月晚上通话次数 */
    private String callCntEvening6m;
    /** 近三月凌晨通话次数 */
    private String callCntNight3m;
    /** 近六月凌晨通话次数 */
    private String callCntNight6m;
    /** 近三月工作日通话次数 */
    private String callCntWeekday3m;
    /** 近六月工作日通话次数 */
    private String callCntWeekday6m;
    /** 近三月周末通话次数 */
    private String callCntWeekend3m;
    /** 近六月周末通话次数 */
    private String callCntWeekend6m;
    /** 近三月节假日通话次数 */
    private String callCntHoliday3m;
    /** 近六月节假日通话次数 */
    private String callCntHoliday6m;
    /** 近三月是否有全天联系 */
    private String callIfWholeDay3m;
    /** 近六月是否有全天联系 */
    private String callIfWholeDay6m;
    /** 第一次通话时间 */
    private String transStart;
    /** 最近一次通话时间 */
    private String transEnd;
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
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city=city;
    }
    public String getPeerNum() {
        return this.peerNum;
    }
    public void setPeerNum(String peerNum) {
        this.peerNum=peerNum;
    }
    public Integer getIsEmergency() {
        return this.isEmergency;
    }
    public void setIsEmergency(Integer isEmergency) {
        this.isEmergency=isEmergency;
    }
    public String getPRelation() {
        return this.pRelation;
    }
    public void setPRelation(String pRelation) {
        this.pRelation=pRelation;
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
    public String getCallCnt1w() {
        return this.callCnt1w;
    }
    public void setCallCnt1w(String callCnt1w) {
        this.callCnt1w=callCnt1w;
    }
    public String getCallCnt1m() {
        return this.callCnt1m;
    }
    public void setCallCnt1m(String callCnt1m) {
        this.callCnt1m=callCnt1m;
    }
    public String getCallCnt3m() {
        return this.callCnt3m;
    }
    public void setCallCnt3m(String callCnt3m) {
        this.callCnt3m=callCnt3m;
    }
    public String getCallCnt6m() {
        return this.callCnt6m;
    }
    public void setCallCnt6m(String callCnt6m) {
        this.callCnt6m=callCnt6m;
    }
    public String getCallTime3m() {
        return this.callTime3m;
    }
    public void setCallTime3m(String callTime3m) {
        this.callTime3m=callTime3m;
    }
    public String getCallTime6m() {
        return this.callTime6m;
    }
    public void setCallTime6m(String callTime6m) {
        this.callTime6m=callTime6m;
    }
    public String getDialCnt3m() {
        return this.dialCnt3m;
    }
    public void setDialCnt3m(String dialCnt3m) {
        this.dialCnt3m=dialCnt3m;
    }
    public String getDialCnt6m() {
        return this.dialCnt6m;
    }
    public void setDialCnt6m(String dialCnt6m) {
        this.dialCnt6m=dialCnt6m;
    }
    public String getDialTime3m() {
        return this.dialTime3m;
    }
    public void setDialTime3m(String dialTime3m) {
        this.dialTime3m=dialTime3m;
    }
    public String getDialTime6m() {
        return this.dialTime6m;
    }
    public void setDialTime6m(String dialTime6m) {
        this.dialTime6m=dialTime6m;
    }
    public String getDialedCnt3m() {
        return this.dialedCnt3m;
    }
    public void setDialedCnt3m(String dialedCnt3m) {
        this.dialedCnt3m=dialedCnt3m;
    }
    public String getDialedCnt6m() {
        return this.dialedCnt6m;
    }
    public void setDialedCnt6m(String dialedCnt6m) {
        this.dialedCnt6m=dialedCnt6m;
    }
    public String getDialedTime3m() {
        return this.dialedTime3m;
    }
    public void setDialedTime3m(String dialedTime3m) {
        this.dialedTime3m=dialedTime3m;
    }
    public String getDialedTime6m() {
        return this.dialedTime6m;
    }
    public void setDialedTime6m(String dialedTime6m) {
        this.dialedTime6m=dialedTime6m;
    }
    public String getCallCntMorning3m() {
        return this.callCntMorning3m;
    }
    public void setCallCntMorning3m(String callCntMorning3m) {
        this.callCntMorning3m=callCntMorning3m;
    }
    public String getCallCntMorning6m() {
        return this.callCntMorning6m;
    }
    public void setCallCntMorning6m(String callCntMorning6m) {
        this.callCntMorning6m=callCntMorning6m;
    }
    public String getCallCntNoon3m() {
        return this.callCntNoon3m;
    }
    public void setCallCntNoon3m(String callCntNoon3m) {
        this.callCntNoon3m=callCntNoon3m;
    }
    public String getCallCntNoon6m() {
        return this.callCntNoon6m;
    }
    public void setCallCntNoon6m(String callCntNoon6m) {
        this.callCntNoon6m=callCntNoon6m;
    }
    public String getCallCntAfternoon3m() {
        return this.callCntAfternoon3m;
    }
    public void setCallCntAfternoon3m(String callCntAfternoon3m) {
        this.callCntAfternoon3m=callCntAfternoon3m;
    }
    public String getCallCntAfternoon6m() {
        return this.callCntAfternoon6m;
    }
    public void setCallCntAfternoon6m(String callCntAfternoon6m) {
        this.callCntAfternoon6m=callCntAfternoon6m;
    }
    public String getCallCntEvening3m() {
        return this.callCntEvening3m;
    }
    public void setCallCntEvening3m(String callCntEvening3m) {
        this.callCntEvening3m=callCntEvening3m;
    }
    public String getCallCntEvening6m() {
        return this.callCntEvening6m;
    }
    public void setCallCntEvening6m(String callCntEvening6m) {
        this.callCntEvening6m=callCntEvening6m;
    }
    public String getCallCntNight3m() {
        return this.callCntNight3m;
    }
    public void setCallCntNight3m(String callCntNight3m) {
        this.callCntNight3m=callCntNight3m;
    }
    public String getCallCntNight6m() {
        return this.callCntNight6m;
    }
    public void setCallCntNight6m(String callCntNight6m) {
        this.callCntNight6m=callCntNight6m;
    }
    public String getCallCntWeekday3m() {
        return this.callCntWeekday3m;
    }
    public void setCallCntWeekday3m(String callCntWeekday3m) {
        this.callCntWeekday3m=callCntWeekday3m;
    }
    public String getCallCntWeekday6m() {
        return this.callCntWeekday6m;
    }
    public void setCallCntWeekday6m(String callCntWeekday6m) {
        this.callCntWeekday6m=callCntWeekday6m;
    }
    public String getCallCntWeekend3m() {
        return this.callCntWeekend3m;
    }
    public void setCallCntWeekend3m(String callCntWeekend3m) {
        this.callCntWeekend3m=callCntWeekend3m;
    }
    public String getCallCntWeekend6m() {
        return this.callCntWeekend6m;
    }
    public void setCallCntWeekend6m(String callCntWeekend6m) {
        this.callCntWeekend6m=callCntWeekend6m;
    }
    public String getCallCntHoliday3m() {
        return this.callCntHoliday3m;
    }
    public void setCallCntHoliday3m(String callCntHoliday3m) {
        this.callCntHoliday3m=callCntHoliday3m;
    }
    public String getCallCntHoliday6m() {
        return this.callCntHoliday6m;
    }
    public void setCallCntHoliday6m(String callCntHoliday6m) {
        this.callCntHoliday6m=callCntHoliday6m;
    }
    public String getCallIfWholeDay3m() {
        return this.callIfWholeDay3m;
    }
    public void setCallIfWholeDay3m(String callIfWholeDay3m) {
        this.callIfWholeDay3m=callIfWholeDay3m;
    }
    public String getCallIfWholeDay6m() {
        return this.callIfWholeDay6m;
    }
    public void setCallIfWholeDay6m(String callIfWholeDay6m) {
        this.callIfWholeDay6m=callIfWholeDay6m;
    }
    public String getTransStart() {
        return this.transStart;
    }
    public void setTransStart(String transStart) {
        this.transStart=transStart;
    }
    public String getTransEnd() {
        return this.transEnd;
    }
    public void setTransEnd(String transEnd) {
        this.transEnd=transEnd;
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

