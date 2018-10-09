package com.moerlong.carloan.modular.car.entity;
import java.math.*;
import java.util.*;
public class CarDetentionInfo {
    /** 主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 车辆id */
    private Long carId;
    /** 姓名 */
    private String custName;
    /** 性别 0--女 1--男 */
    private Integer custSex;
    /** 身份证号 */
    private String custIdNo;
    /** 财务是否结清 0--否 1--是 */
    private Integer isSettle;
    /** 财务结清附件地址 */
    private String settleAttachUrl;
    /** 财务结清时间 */
    private Date settleDate;
    /** 内勤结清确认 0--否 1--确认 */
    private Integer settleConfirem;
    /** 委托书（客户签字授权）照片地址 */
    private String proxyBookUrl;
    /** 身份证复印件照片地址 */
    private String certPhotoUrl;
    /** 接收资料确认 0--否 1--是 2--驳回 */
    private Integer recvConfirm;
    /** 接收资料确认时间 */
    private Date recvConfirmDate;
    /** 驳回原因 */
    private String refuseReason;
    /** 解押是否受理 0--否 1--是 */
    private Integer acceptConfirm;
    /** 解押受理时间 */
    private Date acceptConfirmDate;
    /** 解押受理小票附件 */
    private String billAttachUrl;
    /** 解押业务是否完成 0--否 1--是 */
    private Integer completeConfirm;
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
    public String getCustName() {
        return this.custName;
    }
    public void setCustName(String custName) {
        this.custName=custName;
    }
    public Integer getCustSex() {
        return this.custSex;
    }
    public void setCustSex(Integer custSex) {
        this.custSex=custSex;
    }
    public String getCustIdNo() {
        return this.custIdNo;
    }
    public void setCustIdNo(String custIdNo) {
        this.custIdNo=custIdNo;
    }
    public Integer getIsSettle() {
        return this.isSettle;
    }
    public void setIsSettle(Integer isSettle) {
        this.isSettle=isSettle;
    }
    public String getSettleAttachUrl() {
        return this.settleAttachUrl;
    }
    public void setSettleAttachUrl(String settleAttachUrl) {
        this.settleAttachUrl=settleAttachUrl;
    }
    public Date getSettleDate() {
        return this.settleDate;
    }
    public void setSettleDate(Date settleDate) {
        this.settleDate=settleDate;
    }
    public Integer getSettleConfirem() {
        return this.settleConfirem;
    }
    public void setSettleConfirem(Integer settleConfirem) {
        this.settleConfirem=settleConfirem;
    }
    public String getProxyBookUrl() {
        return this.proxyBookUrl;
    }
    public void setProxyBookUrl(String proxyBookUrl) {
        this.proxyBookUrl=proxyBookUrl;
    }
    public String getCertPhotoUrl() {
        return this.certPhotoUrl;
    }
    public void setCertPhotoUrl(String certPhotoUrl) {
        this.certPhotoUrl=certPhotoUrl;
    }
    public Integer getRecvConfirm() {
        return this.recvConfirm;
    }
    public void setRecvConfirm(Integer recvConfirm) {
        this.recvConfirm=recvConfirm;
    }
    public Date getRecvConfirmDate() {
        return this.recvConfirmDate;
    }
    public void setRecvConfirmDate(Date recvConfirmDate) {
        this.recvConfirmDate=recvConfirmDate;
    }
    public String getRefuseReason() {
        return this.refuseReason;
    }
    public void setRefuseReason(String refuseReason) {
        this.refuseReason=refuseReason;
    }
    public Integer getAcceptConfirm() {
        return this.acceptConfirm;
    }
    public void setAcceptConfirm(Integer acceptConfirm) {
        this.acceptConfirm=acceptConfirm;
    }
    public Date getAcceptConfirmDate() {
        return this.acceptConfirmDate;
    }
    public void setAcceptConfirmDate(Date acceptConfirmDate) {
        this.acceptConfirmDate=acceptConfirmDate;
    }
    public String getBillAttachUrl() {
        return this.billAttachUrl;
    }
    public void setBillAttachUrl(String billAttachUrl) {
        this.billAttachUrl=billAttachUrl;
    }
    public Integer getCompleteConfirm() {
        return this.completeConfirm;
    }
    public void setCompleteConfirm(Integer completeConfirm) {
        this.completeConfirm=completeConfirm;
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

