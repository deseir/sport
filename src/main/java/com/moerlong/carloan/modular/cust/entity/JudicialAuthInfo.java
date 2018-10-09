package com.moerlong.carloan.modular.cust.entity;
import java.util.Date;
public class JudicialAuthInfo {
    /** 自增主键 */
    private Long id;
    /** 客户id */
    private Long custId;
    /** 借款id */
    private Long applyId;
    /** 类型 0--主借人 1--共借人 */
    private Integer type;
    /** 全国法院被执行人查询（个人） 0--正常 1--异常 */
    private Integer courtPersonal;
    /** 全国法院被执行人查询（个人）附件地址 */
    private String courtPersonalPhoto;
    /** 中国执行信息公开网查询（个人） 0--正常 1--异常 */
    private Integer zhixingPersonal;
    /** 中国执行信息公开网查询（个人）附件地址 */
    private String zhixingPersonalPhoto;
    /** 风险信息网查询（个人） 0--正常 1--异常 */
    private Integer riskPersonal;
    /** 风险信息网查询（个人） 附件地址 */
    private String riskPersonalPhoto;
    /** 风险预警网查询（个人） 0--正常 1--异常 */
    private Integer warnPersonal;
    /** 风险预警网查询（个人） 附件地址 */
    private String warnPersonalPhoto;
    /** 个人备注 */
    private String personalRemark;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 备注，说明 */
    private String remark;
    /** 是否删除 0-否 1-是 */
    private Integer isDeleted;
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
    public Integer getCourtPersonal() {
        return this.courtPersonal;
    }
    public void setCourtPersonal(Integer courtPersonal) {
        this.courtPersonal=courtPersonal;
    }
    public String getCourtPersonalPhoto() {
        return this.courtPersonalPhoto;
    }
    public void setCourtPersonalPhoto(String courtPersonalPhoto) {
        this.courtPersonalPhoto=courtPersonalPhoto;
    }
    public Integer getZhixingPersonal() {
        return this.zhixingPersonal;
    }
    public void setZhixingPersonal(Integer zhixingPersonal) {
        this.zhixingPersonal=zhixingPersonal;
    }
    public String getZhixingPersonalPhoto() {
        return this.zhixingPersonalPhoto;
    }
    public void setZhixingPersonalPhoto(String zhixingPersonalPhoto) {
        this.zhixingPersonalPhoto=zhixingPersonalPhoto;
    }
    public Integer getRiskPersonal() {
        return this.riskPersonal;
    }
    public void setRiskPersonal(Integer riskPersonal) {
        this.riskPersonal=riskPersonal;
    }
    public String getRiskPersonalPhoto() {
        return this.riskPersonalPhoto;
    }
    public void setRiskPersonalPhoto(String riskPersonalPhoto) {
        this.riskPersonalPhoto=riskPersonalPhoto;
    }
    public Integer getWarnPersonal() {
        return this.warnPersonal;
    }
    public void setWarnPersonal(Integer warnPersonal) {
        this.warnPersonal=warnPersonal;
    }
    public String getWarnPersonalPhoto() {
        return this.warnPersonalPhoto;
    }
    public void setWarnPersonalPhoto(String warnPersonalPhoto) {
        this.warnPersonalPhoto=warnPersonalPhoto;
    }
    public String getPersonalRemark() {
        return this.personalRemark;
    }
    public void setPersonalRemark(String personalRemark) {
        this.personalRemark=personalRemark;
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
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark=remark;
    }
    public Integer getIsDeleted() {
        return this.isDeleted;
    }
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted=isDeleted;
    }
}

