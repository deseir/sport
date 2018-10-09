package com.moerlong.carloan.modular.loan.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class LoanApplyInfoVo {
    private Long applyId;

    private String custName;

    private String custIdNo;

    private BigDecimal totalAmount;

    private Integer periodNum;
    
    private String status;

    private String statusDesc;
    
    private Integer sfrzStatus;

    private Integer nqlrStatus;

    private Integer ycStatus;

    private Integer gpsInstallStatus;

    private Integer gpsUninstallStatus;

    private String interfaceAddress;

    private String showAddress;

    private String nextNodeId;

    private String nextNodeName;

    private String nextNodeRole;

    private List<String> nextNodeRoleNames;

    private Long deptId;


    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustIdNo() {
        return custIdNo;
    }

    public void setCustIdNo(String custIdNo) {
        this.custIdNo = custIdNo;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(Integer periodNum) {
        this.periodNum = periodNum;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getNqlrStatus() {
        return nqlrStatus;
    }

    public void setNqlrStatus(Integer nqlrStatus) {
        this.nqlrStatus = nqlrStatus;
    }

    public Integer getYcStatus() {
        return ycStatus;
    }

    public void setYcStatus(Integer ycStatus) {
        this.ycStatus = ycStatus;
    }

    public Integer getGpsInstallStatus() {
        return gpsInstallStatus;
    }

    public void setGpsInstallStatus(Integer gpsInstallStatus) {
        this.gpsInstallStatus = gpsInstallStatus;
    }

    public Integer getGpsUninstallStatus() {
        return gpsUninstallStatus;
    }

    public void setGpsUninstallStatus(Integer gpsUninstallStatus) {
        this.gpsUninstallStatus = gpsUninstallStatus;
    }

    public String getInterfaceAddress() {
        return interfaceAddress;
    }

    public void setInterfaceAddress(String interfaceAddress) {
        this.interfaceAddress = interfaceAddress;
    }

    public String getShowAddress() {
        return showAddress;
    }

    public void setShowAddress(String showAddress) {
        this.showAddress = showAddress;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSfrzStatus() {
		return sfrzStatus;
	}

	public void setSfrzStatus(Integer sfrzStatus) {
		this.sfrzStatus = sfrzStatus;
	}

    public String getNextNodeId() {
        return nextNodeId;
    }

    public void setNextNodeId(String nextNodeId) {
        this.nextNodeId = nextNodeId;
    }

    public String getNextNodeName() {
        return nextNodeName;
    }

    public void setNextNodeName(String nextNodeName) {
        this.nextNodeName = nextNodeName;
    }

    public String getNextNodeRole() {
        return nextNodeRole;
    }

    public void setNextNodeRole(String nextNodeRole) {
        this.nextNodeRole = nextNodeRole;
    }

    public List<String> getNextNodeRoleNames() {
        return nextNodeRoleNames;
    }

    public void setNextNodeRoleNames(List<String> nextNodeRoleNames) {
        this.nextNodeRoleNames = nextNodeRoleNames;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
