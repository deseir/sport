package com.moerlong.carloan.modular.car.entity.vo;

import com.moerlong.carloan.modular.car.entity.CarBussMortgageInfo;
import com.moerlong.carloan.modular.car.entity.CarDetentionInfo;

import java.util.Date;

public class CarDetentionInfoVo {
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

    private String registerPhotoUrl5;
    private String registerPhotoUrl6;
    private String registerPhotoUrl7;
    private String registerPhotoUrl8;

    public CarDetentionInfoVo() {
    }

    public CarDetentionInfoVo(CarDetentionInfo carDetentionInfo,String registerPhotoUrl5, String registerPhotoUrl6, String registerPhotoUrl7, String registerPhotoUrl8) {
        this.id = carDetentionInfo.getId();
        this.custId = carDetentionInfo.getCustId();
        this.applyId = carDetentionInfo.getApplyId();
        this.carId = carDetentionInfo.getCarId();
        this.custName = carDetentionInfo.getCustName();
        this.custSex = carDetentionInfo.getCustSex();
        this.custIdNo = carDetentionInfo.getCustIdNo();
        this.isSettle = carDetentionInfo.getIsSettle();
        this.settleAttachUrl = carDetentionInfo.getSettleAttachUrl();
        this.settleDate = carDetentionInfo.getSettleDate();
        this.settleConfirem = carDetentionInfo.getSettleConfirem();
        this.proxyBookUrl = carDetentionInfo.getProxyBookUrl();
        this.certPhotoUrl = carDetentionInfo.getCertPhotoUrl();
        this.recvConfirm = carDetentionInfo.getRecvConfirm();
        this.recvConfirmDate = carDetentionInfo.getRecvConfirmDate();
        this.refuseReason = carDetentionInfo.getRefuseReason();
        this.acceptConfirm = carDetentionInfo.getAcceptConfirm();
        this.acceptConfirmDate = carDetentionInfo.getAcceptConfirmDate();
        this.billAttachUrl = carDetentionInfo.getBillAttachUrl();
        this.completeConfirm = carDetentionInfo.getCompleteConfirm();
        this.createTime = carDetentionInfo.getCreateTime();
        this.updateTime = carDetentionInfo.getUpdateTime();
        this.isDeleted = carDetentionInfo.getIsDeleted();
        this.remark = carDetentionInfo.getRemark();
        this.registerPhotoUrl5 = registerPhotoUrl5;
        this.registerPhotoUrl6 = registerPhotoUrl6;
        this.registerPhotoUrl7 = registerPhotoUrl7;
        this.registerPhotoUrl8 = registerPhotoUrl8;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Integer getCustSex() {
        return custSex;
    }

    public void setCustSex(Integer custSex) {
        this.custSex = custSex;
    }

    public String getCustIdNo() {
        return custIdNo;
    }

    public void setCustIdNo(String custIdNo) {
        this.custIdNo = custIdNo;
    }

    public Integer getIsSettle() {
        return isSettle;
    }

    public void setIsSettle(Integer isSettle) {
        this.isSettle = isSettle;
    }

    public String getSettleAttachUrl() {
        return settleAttachUrl;
    }

    public void setSettleAttachUrl(String settleAttachUrl) {
        this.settleAttachUrl = settleAttachUrl;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public Integer getSettleConfirem() {
        return settleConfirem;
    }

    public void setSettleConfirem(Integer settleConfirem) {
        this.settleConfirem = settleConfirem;
    }

    public String getProxyBookUrl() {
        return proxyBookUrl;
    }

    public void setProxyBookUrl(String proxyBookUrl) {
        this.proxyBookUrl = proxyBookUrl;
    }

    public String getCertPhotoUrl() {
        return certPhotoUrl;
    }

    public void setCertPhotoUrl(String certPhotoUrl) {
        this.certPhotoUrl = certPhotoUrl;
    }

    public Integer getRecvConfirm() {
        return recvConfirm;
    }

    public void setRecvConfirm(Integer recvConfirm) {
        this.recvConfirm = recvConfirm;
    }

    public Date getRecvConfirmDate() {
        return recvConfirmDate;
    }

    public void setRecvConfirmDate(Date recvConfirmDate) {
        this.recvConfirmDate = recvConfirmDate;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public Integer getAcceptConfirm() {
        return acceptConfirm;
    }

    public void setAcceptConfirm(Integer acceptConfirm) {
        this.acceptConfirm = acceptConfirm;
    }

    public Date getAcceptConfirmDate() {
        return acceptConfirmDate;
    }

    public void setAcceptConfirmDate(Date acceptConfirmDate) {
        this.acceptConfirmDate = acceptConfirmDate;
    }

    public String getBillAttachUrl() {
        return billAttachUrl;
    }

    public void setBillAttachUrl(String billAttachUrl) {
        this.billAttachUrl = billAttachUrl;
    }

    public Integer getCompleteConfirm() {
        return completeConfirm;
    }

    public void setCompleteConfirm(Integer completeConfirm) {
        this.completeConfirm = completeConfirm;
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

    public String getRegisterPhotoUrl5() {
        return registerPhotoUrl5;
    }

    public void setRegisterPhotoUrl5(String registerPhotoUrl5) {
        this.registerPhotoUrl5 = registerPhotoUrl5;
    }

    public String getRegisterPhotoUrl6() {
        return registerPhotoUrl6;
    }

    public void setRegisterPhotoUrl6(String registerPhotoUrl6) {
        this.registerPhotoUrl6 = registerPhotoUrl6;
    }

    public String getRegisterPhotoUrl7() {
        return registerPhotoUrl7;
    }

    public void setRegisterPhotoUrl7(String registerPhotoUrl7) {
        this.registerPhotoUrl7 = registerPhotoUrl7;
    }

    public String getRegisterPhotoUrl8() {
        return registerPhotoUrl8;
    }

    public void setRegisterPhotoUrl8(String registerPhotoUrl8) {
        this.registerPhotoUrl8 = registerPhotoUrl8;
    }
}
