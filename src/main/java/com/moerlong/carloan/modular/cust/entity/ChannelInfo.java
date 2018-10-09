package com.moerlong.carloan.modular.cust.entity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.*;
import java.util.*;
public class ChannelInfo {
    /** 自增主键 */
    private Long id;
    /** 渠道名称 */
    private String channelName;
    /** 所在城市区域 */
    private String city;
    /** 渠道联系地址 */
    private String address;
    /** 返佣比例 */
    private BigDecimal fanyongRate;
    /** 返佣账户户名 */
    private String accountName;
    /** 返佣开户行信息 */
    private String accountBank;
    /** 返佣账户卡号 */
    private String accountCardno;
    /** 对接人名称 */
    private String joinPerson;
    /** 对接人联系方式 */
    private String joinMobile;
    /** 创建人id */
    private Long createUserId;
    /** 创建人姓名 */
    private String createUserName;
    /** 与渠道对接业务人员姓名 */
    private String bussName;
    /** 创建时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /** 更新时间 */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
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
    public String getChannelName() {
        return this.channelName;
    }
    public void setChannelName(String channelName) {
        this.channelName=channelName;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city=city;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address=address;
    }
    public BigDecimal getFanyongRate() {
        return this.fanyongRate;
    }
    public void setFanyongRate(BigDecimal fanyongRate) {
        this.fanyongRate=fanyongRate;
    }
    public String getAccountName() {
        return this.accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName=accountName;
    }
    public String getAccountBank() {
        return this.accountBank;
    }
    public void setAccountBank(String accountBank) {
        this.accountBank=accountBank;
    }
    public String getAccountCardno() {
        return this.accountCardno;
    }
    public void setAccountCardno(String accountCardno) {
        this.accountCardno=accountCardno;
    }
    public String getJoinPerson() {
        return this.joinPerson;
    }
    public void setJoinPerson(String joinPerson) {
        this.joinPerson=joinPerson;
    }
    public String getJoinMobile() {
        return this.joinMobile;
    }
    public void setJoinMobile(String joinMobile) {
        this.joinMobile=joinMobile;
    }
    public Long getCreateUserId() {
        return this.createUserId;
    }
    public void setCreateUserId(Long createUserId) {
        this.createUserId=createUserId;
    }
    public String getCreateUserName() {
        return this.createUserName;
    }
    public void setCreateUserName(String createUserName) {
        this.createUserName=createUserName;
    }
    public String getBussName() {
        return this.bussName;
    }
    public void setBussName(String bussName) {
        this.bussName=bussName;
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

