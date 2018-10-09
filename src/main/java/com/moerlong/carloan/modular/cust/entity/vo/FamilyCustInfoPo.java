package com.moerlong.carloan.modular.cust.entity.vo;

public class FamilyCustInfoPo {
    /**
     * 借款人ID
     */
    private Long custId;

    /**
     * 借款人身份证号
     */
    private String certId;

    /**
     * 与借款人的关系
     */
    private String relationship;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }
}
