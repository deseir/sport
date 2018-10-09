package com.moerlong.carloan.modular.car.entity;
import java.math.*;
import java.util.*;
public class CarBussMortgageInfo {
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
    /** 车辆登记证书1-2页照片地址 */
    private String registerPhotoUrl1;
    /** 车辆登记证书3-4页照片地址 */
    private String registerPhotoUrl2;
    /** 车辆登记证书5-6页照片地址 */
    private String registerPhotoUrl3;
    /** 车辆登记证书7-8页照片地址 */
    private String registerPhotoUrl4;
    /** 委托书（客户签字授权）照片地址 */
    private String proxyBookUrl;
    /** 抵押合同(车管所）照片地址 */
    private String mortgageContractUrl;
    /** 身份证复印件照片地址 */
    private String certPhotoUrl;
    /** 接收资料确认 0--否 1--是 2--驳回 */
    private Integer recvConfirm;
    /** 接收资料确认时间 */
    private Date recvConfirmDate;
    /** 驳回原因 */
    private String refuseReason;
    /** 抵押是否受理 0--否 1--是 */
    private Integer acceptConfirm;
    /** 抵押受理时间 */
    private Date acceptConfirmDate;
    /** 抵押受理小票附件 */
    private String billAttachUrl;
    /** 抵押业务是否完成 0--否 1--是 */
    private Integer completeConfirm;
    /** 抵押完成后车辆登记证书1-2页照片地址 */
    private String registerPhotoUrl5;
    /** 抵押完成后车辆登记证书3-4页照片地址 */
    private String registerPhotoUrl6;
    /** 抵押完成后车辆登记证书5-6页照片地址 */
    private String registerPhotoUrl7;
    /** 抵押完成后车辆登记证书7-8页照片地址 */
    private String registerPhotoUrl8;
    /** 抵押业务完成时间 */
    private Date completeConfirmDate;
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
    public String getRegisterPhotoUrl1() {
        return this.registerPhotoUrl1;
    }
    public void setRegisterPhotoUrl1(String registerPhotoUrl1) {
        this.registerPhotoUrl1=registerPhotoUrl1;
    }
    public String getRegisterPhotoUrl2() {
        return this.registerPhotoUrl2;
    }
    public void setRegisterPhotoUrl2(String registerPhotoUrl2) {
        this.registerPhotoUrl2=registerPhotoUrl2;
    }
    public String getRegisterPhotoUrl3() {
        return this.registerPhotoUrl3;
    }
    public void setRegisterPhotoUrl3(String registerPhotoUrl3) {
        this.registerPhotoUrl3=registerPhotoUrl3;
    }
    public String getRegisterPhotoUrl4() {
        return this.registerPhotoUrl4;
    }
    public void setRegisterPhotoUrl4(String registerPhotoUrl4) {
        this.registerPhotoUrl4=registerPhotoUrl4;
    }
    public String getProxyBookUrl() {
        return this.proxyBookUrl;
    }
    public void setProxyBookUrl(String proxyBookUrl) {
        this.proxyBookUrl=proxyBookUrl;
    }
    public String getMortgageContractUrl() {
        return this.mortgageContractUrl;
    }
    public void setMortgageContractUrl(String mortgageContractUrl) {
        this.mortgageContractUrl=mortgageContractUrl;
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
    public String getRegisterPhotoUrl5() {
        return this.registerPhotoUrl5;
    }
    public void setRegisterPhotoUrl5(String registerPhotoUrl5) {
        this.registerPhotoUrl5=registerPhotoUrl5;
    }
    public String getRegisterPhotoUrl6() {
        return this.registerPhotoUrl6;
    }
    public void setRegisterPhotoUrl6(String registerPhotoUrl6) {
        this.registerPhotoUrl6=registerPhotoUrl6;
    }
    public String getRegisterPhotoUrl7() {
        return this.registerPhotoUrl7;
    }
    public void setRegisterPhotoUrl7(String registerPhotoUrl7) {
        this.registerPhotoUrl7=registerPhotoUrl7;
    }
    public String getRegisterPhotoUrl8() {
        return this.registerPhotoUrl8;
    }
    public void setRegisterPhotoUrl8(String registerPhotoUrl8) {
        this.registerPhotoUrl8=registerPhotoUrl8;
    }
    public Date getCompleteConfirmDate() {
        return this.completeConfirmDate;
    }
    public void setCompleteConfirmDate(Date completeConfirmDate) {
        this.completeConfirmDate=completeConfirmDate;
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

