package com.moerlong.carloan.modular.cust.entity;
import java.math.*;
import java.util.*;
public class TelecomCallRiskAnalysis {
    /** 运营商风险分析表自增id */
    private Long id;
    /** 借款申请id */
    private Long applyId;
    /** 用户id */
    private Long custId;
    /** 类型 0--主借人 1--共借人 */
    private Integer type;
    /** 风险项 */
    private String analysisItem;
    /** 风险项描述 */
    private String analysisDesc;
    /** 近一月通话次数 */
    private Integer callCnt1m;
    /** 近三月通话次数 */
    private Integer callCnt3m;
    /** 近六月通话次数 */
    private Integer callCnt6m;
    /** 近三月平均通话次数 */
    private Integer avgCallCnt3m;
    /** 近六月平均通话次数 */
    private Integer avgCallCnt6m;
    /** 近一月通话时长(秒) */
    private Integer callTime1m;
    /** 近三月通话时长(秒) */
    private Integer callTime3m;
    /** 近六月通话时长(秒) */
    private Integer callTime6m;
    /** 近三月平均通话时长(秒) */
    private Integer avgCallTime3m;
    /** 近六月平均通话时长(秒) */
    private Integer avgCallTime6m;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
    private Date updateTime;
    /** 是否删除 0--否 1--是 */
    private Integer isDeleted;
    /** 备注，说明 */
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAnalysisItem() {
        return analysisItem;
    }

    public void setAnalysisItem(String analysisItem) {
        this.analysisItem = analysisItem;
    }

    public String getAnalysisDesc() {
        return analysisDesc;
    }

    public void setAnalysisDesc(String analysisDesc) {
        this.analysisDesc = analysisDesc;
    }

    public Integer getCallCnt1m() {
        return callCnt1m;
    }

    public void setCallCnt1m(Integer callCnt1m) {
        this.callCnt1m = callCnt1m;
    }

    public Integer getCallCnt3m() {
        return callCnt3m;
    }

    public void setCallCnt3m(Integer callCnt3m) {
        this.callCnt3m = callCnt3m;
    }

    public Integer getCallCnt6m() {
        return callCnt6m;
    }

    public void setCallCnt6m(Integer callCnt6m) {
        this.callCnt6m = callCnt6m;
    }

    public Integer getAvgCallCnt3m() {
        return avgCallCnt3m;
    }

    public void setAvgCallCnt3m(Integer avgCallCnt3m) {
        this.avgCallCnt3m = avgCallCnt3m;
    }

    public Integer getAvgCallCnt6m() {
        return avgCallCnt6m;
    }

    public void setAvgCallCnt6m(Integer avgCallCnt6m) {
        this.avgCallCnt6m = avgCallCnt6m;
    }

    public Integer getCallTime1m() {
        return callTime1m;
    }

    public void setCallTime1m(Integer callTime1m) {
        this.callTime1m = callTime1m;
    }

    public Integer getCallTime3m() {
        return callTime3m;
    }

    public void setCallTime3m(Integer callTime3m) {
        this.callTime3m = callTime3m;
    }

    public Integer getCallTime6m() {
        return callTime6m;
    }

    public void setCallTime6m(Integer callTime6m) {
        this.callTime6m = callTime6m;
    }

    public Integer getAvgCallTime3m() {
        return avgCallTime3m;
    }

    public void setAvgCallTime3m(Integer avgCallTime3m) {
        this.avgCallTime3m = avgCallTime3m;
    }

    public Integer getAvgCallTime6m() {
        return avgCallTime6m;
    }

    public void setAvgCallTime6m(Integer avgCallTime6m) {
        this.avgCallTime6m = avgCallTime6m;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

