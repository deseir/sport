package com.moerlong.carloan.modular.paybackMgr.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepaymentInfoExample {
    protected String join;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RepaymentInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public String getJoin() {
        return join;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andJoinWhere(String whereSql) {
            addCriterion(whereSql);
            return (Criteria) this;
        }

        public Criteria andIdIsNull() {
            addCriterion("c_repayment_info.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("c_repayment_info.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("c_repayment_info.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("c_repayment_info.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("c_repayment_info.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("c_repayment_info.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("c_repayment_info.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("c_repayment_info.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("c_repayment_info.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("c_repayment_info.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(String sql) {
            addCriterion("c_repayment_info.id in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(String sql) {
            addCriterion("c_repayment_info.id not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("c_repayment_info.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("c_repayment_info.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNull() {
            addCriterion("c_repayment_info.cust_id is null");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNotNull() {
            addCriterion("c_repayment_info.cust_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustIdEqualTo(Long value) {
            addCriterion("c_repayment_info.cust_id =", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotEqualTo(Long value) {
            addCriterion("c_repayment_info.cust_id <>", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThan(Long value) {
            addCriterion("c_repayment_info.cust_id >", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThanOrEqualTo(Long value) {
            addCriterion("c_repayment_info.cust_id >=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThan(Long value) {
            addCriterion("c_repayment_info.cust_id <", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThanOrEqualTo(Long value) {
            addCriterion("c_repayment_info.cust_id <=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdIn(List<Long> values) {
            addCriterion("c_repayment_info.cust_id in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotIn(List<Long> values) {
            addCriterion("c_repayment_info.cust_id not in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdIn(String sql) {
            addCriterion("c_repayment_info.cust_id in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andCustIdNotIn(String sql) {
            addCriterion("c_repayment_info.cust_id not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andCustIdBetween(Long value1, Long value2) {
            addCriterion("c_repayment_info.cust_id between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotBetween(Long value1, Long value2) {
            addCriterion("c_repayment_info.cust_id not between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andApplyIdIsNull() {
            addCriterion("c_repayment_info.apply_id is null");
            return (Criteria) this;
        }

        public Criteria andApplyIdIsNotNull() {
            addCriterion("c_repayment_info.apply_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplyIdEqualTo(Long value) {
            addCriterion("c_repayment_info.apply_id =", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotEqualTo(Long value) {
            addCriterion("c_repayment_info.apply_id <>", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThan(Long value) {
            addCriterion("c_repayment_info.apply_id >", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("c_repayment_info.apply_id >=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThan(Long value) {
            addCriterion("c_repayment_info.apply_id <", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThanOrEqualTo(Long value) {
            addCriterion("c_repayment_info.apply_id <=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdIn(List<Long> values) {
            addCriterion("c_repayment_info.apply_id in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotIn(List<Long> values) {
            addCriterion("c_repayment_info.apply_id not in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdIn(String sql) {
            addCriterion("c_repayment_info.apply_id in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotIn(String sql) {
            addCriterion("c_repayment_info.apply_id not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andApplyIdBetween(Long value1, Long value2) {
            addCriterion("c_repayment_info.apply_id between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotBetween(Long value1, Long value2) {
            addCriterion("c_repayment_info.apply_id not between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andContractNoIsNull() {
            addCriterion("c_repayment_info.contract_no is null");
            return (Criteria) this;
        }

        public Criteria andContractNoIsNotNull() {
            addCriterion("c_repayment_info.contract_no is not null");
            return (Criteria) this;
        }

        public Criteria andContractNoEqualTo(String value) {
            addCriterion("c_repayment_info.contract_no =", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotEqualTo(String value) {
            addCriterion("c_repayment_info.contract_no <>", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoGreaterThan(String value) {
            addCriterion("c_repayment_info.contract_no >", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoGreaterThanOrEqualTo(String value) {
            addCriterion("c_repayment_info.contract_no >=", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLessThan(String value) {
            addCriterion("c_repayment_info.contract_no <", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLessThanOrEqualTo(String value) {
            addCriterion("c_repayment_info.contract_no <=", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLike(String value) {
            addCriterion("c_repayment_info.contract_no like", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotLike(String value) {
            addCriterion("c_repayment_info.contract_no not like", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoIn(List<String> values) {
            addCriterion("c_repayment_info.contract_no in", values, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotIn(List<String> values) {
            addCriterion("c_repayment_info.contract_no not in", values, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoIn(String sql) {
            addCriterion("c_repayment_info.contract_no in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andContractNoNotIn(String sql) {
            addCriterion("c_repayment_info.contract_no not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andContractNoBetween(String value1, String value2) {
            addCriterion("c_repayment_info.contract_no between", value1, value2, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotBetween(String value1, String value2) {
            addCriterion("c_repayment_info.contract_no not between", value1, value2, "contractNo");
            return (Criteria) this;
        }

        public Criteria andPayIdIsNull() {
            addCriterion("c_repayment_info.pay_id is null");
            return (Criteria) this;
        }

        public Criteria andPayIdIsNotNull() {
            addCriterion("c_repayment_info.pay_id is not null");
            return (Criteria) this;
        }

        public Criteria andPayIdEqualTo(Long value) {
            addCriterion("c_repayment_info.pay_id =", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdNotEqualTo(Long value) {
            addCriterion("c_repayment_info.pay_id <>", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdGreaterThan(Long value) {
            addCriterion("c_repayment_info.pay_id >", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdGreaterThanOrEqualTo(Long value) {
            addCriterion("c_repayment_info.pay_id >=", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdLessThan(Long value) {
            addCriterion("c_repayment_info.pay_id <", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdLessThanOrEqualTo(Long value) {
            addCriterion("c_repayment_info.pay_id <=", value, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdIn(List<Long> values) {
            addCriterion("c_repayment_info.pay_id in", values, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdNotIn(List<Long> values) {
            addCriterion("c_repayment_info.pay_id not in", values, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdIn(String sql) {
            addCriterion("c_repayment_info.pay_id in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andPayIdNotIn(String sql) {
            addCriterion("c_repayment_info.pay_id not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andPayIdBetween(Long value1, Long value2) {
            addCriterion("c_repayment_info.pay_id between", value1, value2, "payId");
            return (Criteria) this;
        }

        public Criteria andPayIdNotBetween(Long value1, Long value2) {
            addCriterion("c_repayment_info.pay_id not between", value1, value2, "payId");
            return (Criteria) this;
        }

        public Criteria andCustNameIsNull() {
            addCriterion("c_repayment_info.cust_name is null");
            return (Criteria) this;
        }

        public Criteria andCustNameIsNotNull() {
            addCriterion("c_repayment_info.cust_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustNameEqualTo(String value) {
            addCriterion("c_repayment_info.cust_name =", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotEqualTo(String value) {
            addCriterion("c_repayment_info.cust_name <>", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameGreaterThan(String value) {
            addCriterion("c_repayment_info.cust_name >", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameGreaterThanOrEqualTo(String value) {
            addCriterion("c_repayment_info.cust_name >=", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameLessThan(String value) {
            addCriterion("c_repayment_info.cust_name <", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameLessThanOrEqualTo(String value) {
            addCriterion("c_repayment_info.cust_name <=", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameLike(String value) {
            addCriterion("c_repayment_info.cust_name like", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotLike(String value) {
            addCriterion("c_repayment_info.cust_name not like", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameIn(List<String> values) {
            addCriterion("c_repayment_info.cust_name in", values, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotIn(List<String> values) {
            addCriterion("c_repayment_info.cust_name not in", values, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameIn(String sql) {
            addCriterion("c_repayment_info.cust_name in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andCustNameNotIn(String sql) {
            addCriterion("c_repayment_info.cust_name not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andCustNameBetween(String value1, String value2) {
            addCriterion("c_repayment_info.cust_name between", value1, value2, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotBetween(String value1, String value2) {
            addCriterion("c_repayment_info.cust_name not between", value1, value2, "custName");
            return (Criteria) this;
        }

        public Criteria andCustMobileIsNull() {
            addCriterion("c_repayment_info.cust_mobile is null");
            return (Criteria) this;
        }

        public Criteria andCustMobileIsNotNull() {
            addCriterion("c_repayment_info.cust_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andCustMobileEqualTo(String value) {
            addCriterion("c_repayment_info.cust_mobile =", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileNotEqualTo(String value) {
            addCriterion("c_repayment_info.cust_mobile <>", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileGreaterThan(String value) {
            addCriterion("c_repayment_info.cust_mobile >", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileGreaterThanOrEqualTo(String value) {
            addCriterion("c_repayment_info.cust_mobile >=", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileLessThan(String value) {
            addCriterion("c_repayment_info.cust_mobile <", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileLessThanOrEqualTo(String value) {
            addCriterion("c_repayment_info.cust_mobile <=", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileLike(String value) {
            addCriterion("c_repayment_info.cust_mobile like", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileNotLike(String value) {
            addCriterion("c_repayment_info.cust_mobile not like", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileIn(List<String> values) {
            addCriterion("c_repayment_info.cust_mobile in", values, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileNotIn(List<String> values) {
            addCriterion("c_repayment_info.cust_mobile not in", values, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileIn(String sql) {
            addCriterion("c_repayment_info.cust_mobile in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andCustMobileNotIn(String sql) {
            addCriterion("c_repayment_info.cust_mobile not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andCustMobileBetween(String value1, String value2) {
            addCriterion("c_repayment_info.cust_mobile between", value1, value2, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileNotBetween(String value1, String value2) {
            addCriterion("c_repayment_info.cust_mobile not between", value1, value2, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustIdNoIsNull() {
            addCriterion("c_repayment_info.cust_id_no is null");
            return (Criteria) this;
        }

        public Criteria andCustIdNoIsNotNull() {
            addCriterion("c_repayment_info.cust_id_no is not null");
            return (Criteria) this;
        }

        public Criteria andCustIdNoEqualTo(String value) {
            addCriterion("c_repayment_info.cust_id_no =", value, "custIdNo");
            return (Criteria) this;
        }

        public Criteria andCustIdNoNotEqualTo(String value) {
            addCriterion("c_repayment_info.cust_id_no <>", value, "custIdNo");
            return (Criteria) this;
        }

        public Criteria andCustIdNoGreaterThan(String value) {
            addCriterion("c_repayment_info.cust_id_no >", value, "custIdNo");
            return (Criteria) this;
        }

        public Criteria andCustIdNoGreaterThanOrEqualTo(String value) {
            addCriterion("c_repayment_info.cust_id_no >=", value, "custIdNo");
            return (Criteria) this;
        }

        public Criteria andCustIdNoLessThan(String value) {
            addCriterion("c_repayment_info.cust_id_no <", value, "custIdNo");
            return (Criteria) this;
        }

        public Criteria andCustIdNoLessThanOrEqualTo(String value) {
            addCriterion("c_repayment_info.cust_id_no <=", value, "custIdNo");
            return (Criteria) this;
        }

        public Criteria andCustIdNoLike(String value) {
            addCriterion("c_repayment_info.cust_id_no like", value, "custIdNo");
            return (Criteria) this;
        }

        public Criteria andCustIdNoNotLike(String value) {
            addCriterion("c_repayment_info.cust_id_no not like", value, "custIdNo");
            return (Criteria) this;
        }

        public Criteria andCustIdNoIn(List<String> values) {
            addCriterion("c_repayment_info.cust_id_no in", values, "custIdNo");
            return (Criteria) this;
        }

        public Criteria andCustIdNoNotIn(List<String> values) {
            addCriterion("c_repayment_info.cust_id_no not in", values, "custIdNo");
            return (Criteria) this;
        }

        public Criteria andCustIdNoIn(String sql) {
            addCriterion("c_repayment_info.cust_id_no in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andCustIdNoNotIn(String sql) {
            addCriterion("c_repayment_info.cust_id_no not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andCustIdNoBetween(String value1, String value2) {
            addCriterion("c_repayment_info.cust_id_no between", value1, value2, "custIdNo");
            return (Criteria) this;
        }

        public Criteria andCustIdNoNotBetween(String value1, String value2) {
            addCriterion("c_repayment_info.cust_id_no not between", value1, value2, "custIdNo");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("c_repayment_info.bank_name is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("c_repayment_info.bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("c_repayment_info.bank_name =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("c_repayment_info.bank_name <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("c_repayment_info.bank_name >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("c_repayment_info.bank_name >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("c_repayment_info.bank_name <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("c_repayment_info.bank_name <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("c_repayment_info.bank_name like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("c_repayment_info.bank_name not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("c_repayment_info.bank_name in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("c_repayment_info.bank_name not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(String sql) {
            addCriterion("c_repayment_info.bank_name in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(String sql) {
            addCriterion("c_repayment_info.bank_name not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("c_repayment_info.bank_name between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("c_repayment_info.bank_name not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankCardNoIsNull() {
            addCriterion("c_repayment_info.bank_card_no is null");
            return (Criteria) this;
        }

        public Criteria andBankCardNoIsNotNull() {
            addCriterion("c_repayment_info.bank_card_no is not null");
            return (Criteria) this;
        }

        public Criteria andBankCardNoEqualTo(String value) {
            addCriterion("c_repayment_info.bank_card_no =", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoNotEqualTo(String value) {
            addCriterion("c_repayment_info.bank_card_no <>", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoGreaterThan(String value) {
            addCriterion("c_repayment_info.bank_card_no >", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("c_repayment_info.bank_card_no >=", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoLessThan(String value) {
            addCriterion("c_repayment_info.bank_card_no <", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoLessThanOrEqualTo(String value) {
            addCriterion("c_repayment_info.bank_card_no <=", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoLike(String value) {
            addCriterion("c_repayment_info.bank_card_no like", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoNotLike(String value) {
            addCriterion("c_repayment_info.bank_card_no not like", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoIn(List<String> values) {
            addCriterion("c_repayment_info.bank_card_no in", values, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoNotIn(List<String> values) {
            addCriterion("c_repayment_info.bank_card_no not in", values, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoIn(String sql) {
            addCriterion("c_repayment_info.bank_card_no in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andBankCardNoNotIn(String sql) {
            addCriterion("c_repayment_info.bank_card_no not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andBankCardNoBetween(String value1, String value2) {
            addCriterion("c_repayment_info.bank_card_no between", value1, value2, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoNotBetween(String value1, String value2) {
            addCriterion("c_repayment_info.bank_card_no not between", value1, value2, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andLoanAmountIsNull() {
            addCriterion("c_repayment_info.loan_amount is null");
            return (Criteria) this;
        }

        public Criteria andLoanAmountIsNotNull() {
            addCriterion("c_repayment_info.loan_amount is not null");
            return (Criteria) this;
        }

        public Criteria andLoanAmountEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.loan_amount =", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.loan_amount <>", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.loan_amount >", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.loan_amount >=", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.loan_amount <", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.loan_amount <=", value, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.loan_amount in", values, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.loan_amount not in", values, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountIn(String sql) {
            addCriterion("c_repayment_info.loan_amount in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andLoanAmountNotIn(String sql) {
            addCriterion("c_repayment_info.loan_amount not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andLoanAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.loan_amount between", value1, value2, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.loan_amount not between", value1, value2, "loanAmount");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodIsNull() {
            addCriterion("c_repayment_info.loan_period is null");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodIsNotNull() {
            addCriterion("c_repayment_info.loan_period is not null");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodEqualTo(Integer value) {
            addCriterion("c_repayment_info.loan_period =", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodNotEqualTo(Integer value) {
            addCriterion("c_repayment_info.loan_period <>", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodGreaterThan(Integer value) {
            addCriterion("c_repayment_info.loan_period >", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("c_repayment_info.loan_period >=", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLessThan(Integer value) {
            addCriterion("c_repayment_info.loan_period <", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("c_repayment_info.loan_period <=", value, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodIn(List<Integer> values) {
            addCriterion("c_repayment_info.loan_period in", values, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodNotIn(List<Integer> values) {
            addCriterion("c_repayment_info.loan_period not in", values, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodIn(String sql) {
            addCriterion("c_repayment_info.loan_period in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodNotIn(String sql) {
            addCriterion("c_repayment_info.loan_period not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodBetween(Integer value1, Integer value2) {
            addCriterion("c_repayment_info.loan_period between", value1, value2, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andLoanPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("c_repayment_info.loan_period not between", value1, value2, "loanPeriod");
            return (Criteria) this;
        }

        public Criteria andYearRateIsNull() {
            addCriterion("c_repayment_info.year_rate is null");
            return (Criteria) this;
        }

        public Criteria andYearRateIsNotNull() {
            addCriterion("c_repayment_info.year_rate is not null");
            return (Criteria) this;
        }

        public Criteria andYearRateEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.year_rate =", value, "yearRate");
            return (Criteria) this;
        }

        public Criteria andYearRateNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.year_rate <>", value, "yearRate");
            return (Criteria) this;
        }

        public Criteria andYearRateGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.year_rate >", value, "yearRate");
            return (Criteria) this;
        }

        public Criteria andYearRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.year_rate >=", value, "yearRate");
            return (Criteria) this;
        }

        public Criteria andYearRateLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.year_rate <", value, "yearRate");
            return (Criteria) this;
        }

        public Criteria andYearRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.year_rate <=", value, "yearRate");
            return (Criteria) this;
        }

        public Criteria andYearRateIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.year_rate in", values, "yearRate");
            return (Criteria) this;
        }

        public Criteria andYearRateNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.year_rate not in", values, "yearRate");
            return (Criteria) this;
        }

        public Criteria andYearRateIn(String sql) {
            addCriterion("c_repayment_info.year_rate in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andYearRateNotIn(String sql) {
            addCriterion("c_repayment_info.year_rate not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andYearRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.year_rate between", value1, value2, "yearRate");
            return (Criteria) this;
        }

        public Criteria andYearRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.year_rate not between", value1, value2, "yearRate");
            return (Criteria) this;
        }

        public Criteria andMonthRateIsNull() {
            addCriterion("c_repayment_info.month_rate is null");
            return (Criteria) this;
        }

        public Criteria andMonthRateIsNotNull() {
            addCriterion("c_repayment_info.month_rate is not null");
            return (Criteria) this;
        }

        public Criteria andMonthRateEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.month_rate =", value, "monthRate");
            return (Criteria) this;
        }

        public Criteria andMonthRateNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.month_rate <>", value, "monthRate");
            return (Criteria) this;
        }

        public Criteria andMonthRateGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.month_rate >", value, "monthRate");
            return (Criteria) this;
        }

        public Criteria andMonthRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.month_rate >=", value, "monthRate");
            return (Criteria) this;
        }

        public Criteria andMonthRateLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.month_rate <", value, "monthRate");
            return (Criteria) this;
        }

        public Criteria andMonthRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.month_rate <=", value, "monthRate");
            return (Criteria) this;
        }

        public Criteria andMonthRateIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.month_rate in", values, "monthRate");
            return (Criteria) this;
        }

        public Criteria andMonthRateNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.month_rate not in", values, "monthRate");
            return (Criteria) this;
        }

        public Criteria andMonthRateIn(String sql) {
            addCriterion("c_repayment_info.month_rate in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andMonthRateNotIn(String sql) {
            addCriterion("c_repayment_info.month_rate not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andMonthRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.month_rate between", value1, value2, "monthRate");
            return (Criteria) this;
        }

        public Criteria andMonthRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.month_rate not between", value1, value2, "monthRate");
            return (Criteria) this;
        }

        public Criteria andPreServiceRateIsNull() {
            addCriterion("c_repayment_info.pre_service_rate is null");
            return (Criteria) this;
        }

        public Criteria andPreServiceRateIsNotNull() {
            addCriterion("c_repayment_info.pre_service_rate is not null");
            return (Criteria) this;
        }

        public Criteria andPreServiceRateEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.pre_service_rate =", value, "preServiceRate");
            return (Criteria) this;
        }

        public Criteria andPreServiceRateNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.pre_service_rate <>", value, "preServiceRate");
            return (Criteria) this;
        }

        public Criteria andPreServiceRateGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.pre_service_rate >", value, "preServiceRate");
            return (Criteria) this;
        }

        public Criteria andPreServiceRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.pre_service_rate >=", value, "preServiceRate");
            return (Criteria) this;
        }

        public Criteria andPreServiceRateLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.pre_service_rate <", value, "preServiceRate");
            return (Criteria) this;
        }

        public Criteria andPreServiceRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.pre_service_rate <=", value, "preServiceRate");
            return (Criteria) this;
        }

        public Criteria andPreServiceRateIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.pre_service_rate in", values, "preServiceRate");
            return (Criteria) this;
        }

        public Criteria andPreServiceRateNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.pre_service_rate not in", values, "preServiceRate");
            return (Criteria) this;
        }

        public Criteria andPreServiceRateIn(String sql) {
            addCriterion("c_repayment_info.pre_service_rate in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andPreServiceRateNotIn(String sql) {
            addCriterion("c_repayment_info.pre_service_rate not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andPreServiceRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.pre_service_rate between", value1, value2, "preServiceRate");
            return (Criteria) this;
        }

        public Criteria andPreServiceRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.pre_service_rate not between", value1, value2, "preServiceRate");
            return (Criteria) this;
        }

        public Criteria andOnceRepaymentRateIsNull() {
            addCriterion("c_repayment_info.once_repayment_rate is null");
            return (Criteria) this;
        }

        public Criteria andOnceRepaymentRateIsNotNull() {
            addCriterion("c_repayment_info.once_repayment_rate is not null");
            return (Criteria) this;
        }

        public Criteria andOnceRepaymentRateEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.once_repayment_rate =", value, "onceRepaymentRate");
            return (Criteria) this;
        }

        public Criteria andOnceRepaymentRateNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.once_repayment_rate <>", value, "onceRepaymentRate");
            return (Criteria) this;
        }

        public Criteria andOnceRepaymentRateGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.once_repayment_rate >", value, "onceRepaymentRate");
            return (Criteria) this;
        }

        public Criteria andOnceRepaymentRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.once_repayment_rate >=", value, "onceRepaymentRate");
            return (Criteria) this;
        }

        public Criteria andOnceRepaymentRateLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.once_repayment_rate <", value, "onceRepaymentRate");
            return (Criteria) this;
        }

        public Criteria andOnceRepaymentRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.once_repayment_rate <=", value, "onceRepaymentRate");
            return (Criteria) this;
        }

        public Criteria andOnceRepaymentRateIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.once_repayment_rate in", values, "onceRepaymentRate");
            return (Criteria) this;
        }

        public Criteria andOnceRepaymentRateNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.once_repayment_rate not in", values, "onceRepaymentRate");
            return (Criteria) this;
        }

        public Criteria andOnceRepaymentRateIn(String sql) {
            addCriterion("c_repayment_info.once_repayment_rate in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andOnceRepaymentRateNotIn(String sql) {
            addCriterion("c_repayment_info.once_repayment_rate not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andOnceRepaymentRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.once_repayment_rate between", value1, value2, "onceRepaymentRate");
            return (Criteria) this;
        }

        public Criteria andOnceRepaymentRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.once_repayment_rate not between", value1, value2, "onceRepaymentRate");
            return (Criteria) this;
        }

        public Criteria andPreFeeIsNull() {
            addCriterion("c_repayment_info.pre_fee is null");
            return (Criteria) this;
        }

        public Criteria andPreFeeIsNotNull() {
            addCriterion("c_repayment_info.pre_fee is not null");
            return (Criteria) this;
        }

        public Criteria andPreFeeEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.pre_fee =", value, "preFee");
            return (Criteria) this;
        }

        public Criteria andPreFeeNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.pre_fee <>", value, "preFee");
            return (Criteria) this;
        }

        public Criteria andPreFeeGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.pre_fee >", value, "preFee");
            return (Criteria) this;
        }

        public Criteria andPreFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.pre_fee >=", value, "preFee");
            return (Criteria) this;
        }

        public Criteria andPreFeeLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.pre_fee <", value, "preFee");
            return (Criteria) this;
        }

        public Criteria andPreFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.pre_fee <=", value, "preFee");
            return (Criteria) this;
        }

        public Criteria andPreFeeIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.pre_fee in", values, "preFee");
            return (Criteria) this;
        }

        public Criteria andPreFeeNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.pre_fee not in", values, "preFee");
            return (Criteria) this;
        }

        public Criteria andPreFeeIn(String sql) {
            addCriterion("c_repayment_info.pre_fee in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andPreFeeNotIn(String sql) {
            addCriterion("c_repayment_info.pre_fee not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andPreFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.pre_fee between", value1, value2, "preFee");
            return (Criteria) this;
        }

        public Criteria andPreFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.pre_fee not between", value1, value2, "preFee");
            return (Criteria) this;
        }

        public Criteria andReceptionAmountIsNull() {
            addCriterion("c_repayment_info.reception_amount is null");
            return (Criteria) this;
        }

        public Criteria andReceptionAmountIsNotNull() {
            addCriterion("c_repayment_info.reception_amount is not null");
            return (Criteria) this;
        }

        public Criteria andReceptionAmountEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.reception_amount =", value, "receptionAmount");
            return (Criteria) this;
        }

        public Criteria andReceptionAmountNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.reception_amount <>", value, "receptionAmount");
            return (Criteria) this;
        }

        public Criteria andReceptionAmountGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.reception_amount >", value, "receptionAmount");
            return (Criteria) this;
        }

        public Criteria andReceptionAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.reception_amount >=", value, "receptionAmount");
            return (Criteria) this;
        }

        public Criteria andReceptionAmountLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.reception_amount <", value, "receptionAmount");
            return (Criteria) this;
        }

        public Criteria andReceptionAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.reception_amount <=", value, "receptionAmount");
            return (Criteria) this;
        }

        public Criteria andReceptionAmountIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.reception_amount in", values, "receptionAmount");
            return (Criteria) this;
        }

        public Criteria andReceptionAmountNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.reception_amount not in", values, "receptionAmount");
            return (Criteria) this;
        }

        public Criteria andReceptionAmountIn(String sql) {
            addCriterion("c_repayment_info.reception_amount in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andReceptionAmountNotIn(String sql) {
            addCriterion("c_repayment_info.reception_amount not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andReceptionAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.reception_amount between", value1, value2, "receptionAmount");
            return (Criteria) this;
        }

        public Criteria andReceptionAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.reception_amount not between", value1, value2, "receptionAmount");
            return (Criteria) this;
        }

        public Criteria andOtherFeeIsNull() {
            addCriterion("c_repayment_info.other_fee is null");
            return (Criteria) this;
        }

        public Criteria andOtherFeeIsNotNull() {
            addCriterion("c_repayment_info.other_fee is not null");
            return (Criteria) this;
        }

        public Criteria andOtherFeeEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.other_fee =", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.other_fee <>", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.other_fee >", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.other_fee >=", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.other_fee <", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.other_fee <=", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.other_fee in", values, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.other_fee not in", values, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeIn(String sql) {
            addCriterion("c_repayment_info.other_fee in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andOtherFeeNotIn(String sql) {
            addCriterion("c_repayment_info.other_fee not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andOtherFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.other_fee between", value1, value2, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.other_fee not between", value1, value2, "otherFee");
            return (Criteria) this;
        }

        public Criteria andBreachAmountIsNull() {
            addCriterion("c_repayment_info.breach_amount is null");
            return (Criteria) this;
        }

        public Criteria andBreachAmountIsNotNull() {
            addCriterion("c_repayment_info.breach_amount is not null");
            return (Criteria) this;
        }

        public Criteria andBreachAmountEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.breach_amount =", value, "breachAmount");
            return (Criteria) this;
        }

        public Criteria andBreachAmountNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.breach_amount <>", value, "breachAmount");
            return (Criteria) this;
        }

        public Criteria andBreachAmountGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.breach_amount >", value, "breachAmount");
            return (Criteria) this;
        }

        public Criteria andBreachAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.breach_amount >=", value, "breachAmount");
            return (Criteria) this;
        }

        public Criteria andBreachAmountLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.breach_amount <", value, "breachAmount");
            return (Criteria) this;
        }

        public Criteria andBreachAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.breach_amount <=", value, "breachAmount");
            return (Criteria) this;
        }

        public Criteria andBreachAmountIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.breach_amount in", values, "breachAmount");
            return (Criteria) this;
        }

        public Criteria andBreachAmountNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.breach_amount not in", values, "breachAmount");
            return (Criteria) this;
        }

        public Criteria andBreachAmountIn(String sql) {
            addCriterion("c_repayment_info.breach_amount in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andBreachAmountNotIn(String sql) {
            addCriterion("c_repayment_info.breach_amount not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andBreachAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.breach_amount between", value1, value2, "breachAmount");
            return (Criteria) this;
        }

        public Criteria andBreachAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.breach_amount not between", value1, value2, "breachAmount");
            return (Criteria) this;
        }

        public Criteria andMonthLeaseTotalIsNull() {
            addCriterion("c_repayment_info.month_lease_total is null");
            return (Criteria) this;
        }

        public Criteria andMonthLeaseTotalIsNotNull() {
            addCriterion("c_repayment_info.month_lease_total is not null");
            return (Criteria) this;
        }

        public Criteria andMonthLeaseTotalEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.month_lease_total =", value, "monthLeaseTotal");
            return (Criteria) this;
        }

        public Criteria andMonthLeaseTotalNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.month_lease_total <>", value, "monthLeaseTotal");
            return (Criteria) this;
        }

        public Criteria andMonthLeaseTotalGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.month_lease_total >", value, "monthLeaseTotal");
            return (Criteria) this;
        }

        public Criteria andMonthLeaseTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.month_lease_total >=", value, "monthLeaseTotal");
            return (Criteria) this;
        }

        public Criteria andMonthLeaseTotalLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.month_lease_total <", value, "monthLeaseTotal");
            return (Criteria) this;
        }

        public Criteria andMonthLeaseTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.month_lease_total <=", value, "monthLeaseTotal");
            return (Criteria) this;
        }

        public Criteria andMonthLeaseTotalIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.month_lease_total in", values, "monthLeaseTotal");
            return (Criteria) this;
        }

        public Criteria andMonthLeaseTotalNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.month_lease_total not in", values, "monthLeaseTotal");
            return (Criteria) this;
        }

        public Criteria andMonthLeaseTotalIn(String sql) {
            addCriterion("c_repayment_info.month_lease_total in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andMonthLeaseTotalNotIn(String sql) {
            addCriterion("c_repayment_info.month_lease_total not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andMonthLeaseTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.month_lease_total between", value1, value2, "monthLeaseTotal");
            return (Criteria) this;
        }

        public Criteria andMonthLeaseTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.month_lease_total not between", value1, value2, "monthLeaseTotal");
            return (Criteria) this;
        }

        public Criteria andMonthServiceTotalIsNull() {
            addCriterion("c_repayment_info.month_service_total is null");
            return (Criteria) this;
        }

        public Criteria andMonthServiceTotalIsNotNull() {
            addCriterion("c_repayment_info.month_service_total is not null");
            return (Criteria) this;
        }

        public Criteria andMonthServiceTotalEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.month_service_total =", value, "monthServiceTotal");
            return (Criteria) this;
        }

        public Criteria andMonthServiceTotalNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.month_service_total <>", value, "monthServiceTotal");
            return (Criteria) this;
        }

        public Criteria andMonthServiceTotalGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.month_service_total >", value, "monthServiceTotal");
            return (Criteria) this;
        }

        public Criteria andMonthServiceTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.month_service_total >=", value, "monthServiceTotal");
            return (Criteria) this;
        }

        public Criteria andMonthServiceTotalLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.month_service_total <", value, "monthServiceTotal");
            return (Criteria) this;
        }

        public Criteria andMonthServiceTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.month_service_total <=", value, "monthServiceTotal");
            return (Criteria) this;
        }

        public Criteria andMonthServiceTotalIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.month_service_total in", values, "monthServiceTotal");
            return (Criteria) this;
        }

        public Criteria andMonthServiceTotalNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.month_service_total not in", values, "monthServiceTotal");
            return (Criteria) this;
        }

        public Criteria andMonthServiceTotalIn(String sql) {
            addCriterion("c_repayment_info.month_service_total in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andMonthServiceTotalNotIn(String sql) {
            addCriterion("c_repayment_info.month_service_total not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andMonthServiceTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.month_service_total between", value1, value2, "monthServiceTotal");
            return (Criteria) this;
        }

        public Criteria andMonthServiceTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.month_service_total not between", value1, value2, "monthServiceTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseCapitalTotalIsNull() {
            addCriterion("c_repayment_info.lease_capital_total is null");
            return (Criteria) this;
        }

        public Criteria andLeaseCapitalTotalIsNotNull() {
            addCriterion("c_repayment_info.lease_capital_total is not null");
            return (Criteria) this;
        }

        public Criteria andLeaseCapitalTotalEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.lease_capital_total =", value, "leaseCapitalTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseCapitalTotalNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.lease_capital_total <>", value, "leaseCapitalTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseCapitalTotalGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.lease_capital_total >", value, "leaseCapitalTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseCapitalTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.lease_capital_total >=", value, "leaseCapitalTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseCapitalTotalLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.lease_capital_total <", value, "leaseCapitalTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseCapitalTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.lease_capital_total <=", value, "leaseCapitalTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseCapitalTotalIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.lease_capital_total in", values, "leaseCapitalTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseCapitalTotalNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.lease_capital_total not in", values, "leaseCapitalTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseCapitalTotalIn(String sql) {
            addCriterion("c_repayment_info.lease_capital_total in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andLeaseCapitalTotalNotIn(String sql) {
            addCriterion("c_repayment_info.lease_capital_total not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andLeaseCapitalTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.lease_capital_total between", value1, value2, "leaseCapitalTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseCapitalTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.lease_capital_total not between", value1, value2, "leaseCapitalTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseInterestTotalIsNull() {
            addCriterion("c_repayment_info.lease_interest_total is null");
            return (Criteria) this;
        }

        public Criteria andLeaseInterestTotalIsNotNull() {
            addCriterion("c_repayment_info.lease_interest_total is not null");
            return (Criteria) this;
        }

        public Criteria andLeaseInterestTotalEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.lease_interest_total =", value, "leaseInterestTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseInterestTotalNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.lease_interest_total <>", value, "leaseInterestTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseInterestTotalGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.lease_interest_total >", value, "leaseInterestTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseInterestTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.lease_interest_total >=", value, "leaseInterestTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseInterestTotalLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.lease_interest_total <", value, "leaseInterestTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseInterestTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.lease_interest_total <=", value, "leaseInterestTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseInterestTotalIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.lease_interest_total in", values, "leaseInterestTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseInterestTotalNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.lease_interest_total not in", values, "leaseInterestTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseInterestTotalIn(String sql) {
            addCriterion("c_repayment_info.lease_interest_total in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andLeaseInterestTotalNotIn(String sql) {
            addCriterion("c_repayment_info.lease_interest_total not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andLeaseInterestTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.lease_interest_total between", value1, value2, "leaseInterestTotal");
            return (Criteria) this;
        }

        public Criteria andLeaseInterestTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.lease_interest_total not between", value1, value2, "leaseInterestTotal");
            return (Criteria) this;
        }

        public Criteria andInterestBeginTimeIsNull() {
            addCriterion("c_repayment_info.interest_begin_time is null");
            return (Criteria) this;
        }

        public Criteria andInterestBeginTimeIsNotNull() {
            addCriterion("c_repayment_info.interest_begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andInterestBeginTimeEqualTo(Date value) {
            addCriterion("c_repayment_info.interest_begin_time =", value, "interestBeginTime");
            return (Criteria) this;
        }

        public Criteria andInterestBeginTimeNotEqualTo(Date value) {
            addCriterion("c_repayment_info.interest_begin_time <>", value, "interestBeginTime");
            return (Criteria) this;
        }

        public Criteria andInterestBeginTimeGreaterThan(Date value) {
            addCriterion("c_repayment_info.interest_begin_time >", value, "interestBeginTime");
            return (Criteria) this;
        }

        public Criteria andInterestBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("c_repayment_info.interest_begin_time >=", value, "interestBeginTime");
            return (Criteria) this;
        }

        public Criteria andInterestBeginTimeLessThan(Date value) {
            addCriterion("c_repayment_info.interest_begin_time <", value, "interestBeginTime");
            return (Criteria) this;
        }

        public Criteria andInterestBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("c_repayment_info.interest_begin_time <=", value, "interestBeginTime");
            return (Criteria) this;
        }

        public Criteria andInterestBeginTimeIn(List<Date> values) {
            addCriterion("c_repayment_info.interest_begin_time in", values, "interestBeginTime");
            return (Criteria) this;
        }

        public Criteria andInterestBeginTimeNotIn(List<Date> values) {
            addCriterion("c_repayment_info.interest_begin_time not in", values, "interestBeginTime");
            return (Criteria) this;
        }

        public Criteria andInterestBeginTimeIn(String sql) {
            addCriterion("c_repayment_info.interest_begin_time in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andInterestBeginTimeNotIn(String sql) {
            addCriterion("c_repayment_info.interest_begin_time not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andInterestBeginTimeBetween(Date value1, Date value2) {
            addCriterion("c_repayment_info.interest_begin_time between", value1, value2, "interestBeginTime");
            return (Criteria) this;
        }

        public Criteria andInterestBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("c_repayment_info.interest_begin_time not between", value1, value2, "interestBeginTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeIsNull() {
            addCriterion("c_repayment_info.interest_end_time is null");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeIsNotNull() {
            addCriterion("c_repayment_info.interest_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeEqualTo(Date value) {
            addCriterion("c_repayment_info.interest_end_time =", value, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeNotEqualTo(Date value) {
            addCriterion("c_repayment_info.interest_end_time <>", value, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeGreaterThan(Date value) {
            addCriterion("c_repayment_info.interest_end_time >", value, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("c_repayment_info.interest_end_time >=", value, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeLessThan(Date value) {
            addCriterion("c_repayment_info.interest_end_time <", value, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("c_repayment_info.interest_end_time <=", value, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeIn(List<Date> values) {
            addCriterion("c_repayment_info.interest_end_time in", values, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeNotIn(List<Date> values) {
            addCriterion("c_repayment_info.interest_end_time not in", values, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeIn(String sql) {
            addCriterion("c_repayment_info.interest_end_time in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeNotIn(String sql) {
            addCriterion("c_repayment_info.interest_end_time not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeBetween(Date value1, Date value2) {
            addCriterion("c_repayment_info.interest_end_time between", value1, value2, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andInterestEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("c_repayment_info.interest_end_time not between", value1, value2, "interestEndTime");
            return (Criteria) this;
        }

        public Criteria andLoanDaysNumIsNull() {
            addCriterion("c_repayment_info.loan_days_num is null");
            return (Criteria) this;
        }

        public Criteria andLoanDaysNumIsNotNull() {
            addCriterion("c_repayment_info.loan_days_num is not null");
            return (Criteria) this;
        }

        public Criteria andLoanDaysNumEqualTo(Integer value) {
            addCriterion("c_repayment_info.loan_days_num =", value, "loanDaysNum");
            return (Criteria) this;
        }

        public Criteria andLoanDaysNumNotEqualTo(Integer value) {
            addCriterion("c_repayment_info.loan_days_num <>", value, "loanDaysNum");
            return (Criteria) this;
        }

        public Criteria andLoanDaysNumGreaterThan(Integer value) {
            addCriterion("c_repayment_info.loan_days_num >", value, "loanDaysNum");
            return (Criteria) this;
        }

        public Criteria andLoanDaysNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("c_repayment_info.loan_days_num >=", value, "loanDaysNum");
            return (Criteria) this;
        }

        public Criteria andLoanDaysNumLessThan(Integer value) {
            addCriterion("c_repayment_info.loan_days_num <", value, "loanDaysNum");
            return (Criteria) this;
        }

        public Criteria andLoanDaysNumLessThanOrEqualTo(Integer value) {
            addCriterion("c_repayment_info.loan_days_num <=", value, "loanDaysNum");
            return (Criteria) this;
        }

        public Criteria andLoanDaysNumIn(List<Integer> values) {
            addCriterion("c_repayment_info.loan_days_num in", values, "loanDaysNum");
            return (Criteria) this;
        }

        public Criteria andLoanDaysNumNotIn(List<Integer> values) {
            addCriterion("c_repayment_info.loan_days_num not in", values, "loanDaysNum");
            return (Criteria) this;
        }

        public Criteria andLoanDaysNumIn(String sql) {
            addCriterion("c_repayment_info.loan_days_num in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andLoanDaysNumNotIn(String sql) {
            addCriterion("c_repayment_info.loan_days_num not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andLoanDaysNumBetween(Integer value1, Integer value2) {
            addCriterion("c_repayment_info.loan_days_num between", value1, value2, "loanDaysNum");
            return (Criteria) this;
        }

        public Criteria andLoanDaysNumNotBetween(Integer value1, Integer value2) {
            addCriterion("c_repayment_info.loan_days_num not between", value1, value2, "loanDaysNum");
            return (Criteria) this;
        }

        public Criteria andPredictAmountIsNull() {
            addCriterion("c_repayment_info.predict_amount is null");
            return (Criteria) this;
        }

        public Criteria andPredictAmountIsNotNull() {
            addCriterion("c_repayment_info.predict_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPredictAmountEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_amount =", value, "predictAmount");
            return (Criteria) this;
        }

        public Criteria andPredictAmountNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_amount <>", value, "predictAmount");
            return (Criteria) this;
        }

        public Criteria andPredictAmountGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.predict_amount >", value, "predictAmount");
            return (Criteria) this;
        }

        public Criteria andPredictAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_amount >=", value, "predictAmount");
            return (Criteria) this;
        }

        public Criteria andPredictAmountLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.predict_amount <", value, "predictAmount");
            return (Criteria) this;
        }

        public Criteria andPredictAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_amount <=", value, "predictAmount");
            return (Criteria) this;
        }

        public Criteria andPredictAmountIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.predict_amount in", values, "predictAmount");
            return (Criteria) this;
        }

        public Criteria andPredictAmountNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.predict_amount not in", values, "predictAmount");
            return (Criteria) this;
        }

        public Criteria andPredictAmountIn(String sql) {
            addCriterion("c_repayment_info.predict_amount in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andPredictAmountNotIn(String sql) {
            addCriterion("c_repayment_info.predict_amount not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andPredictAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.predict_amount between", value1, value2, "predictAmount");
            return (Criteria) this;
        }

        public Criteria andPredictAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.predict_amount not between", value1, value2, "predictAmount");
            return (Criteria) this;
        }

        public Criteria andPredictCapitalIsNull() {
            addCriterion("c_repayment_info.predict_capital is null");
            return (Criteria) this;
        }

        public Criteria andPredictCapitalIsNotNull() {
            addCriterion("c_repayment_info.predict_capital is not null");
            return (Criteria) this;
        }

        public Criteria andPredictCapitalEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_capital =", value, "predictCapital");
            return (Criteria) this;
        }

        public Criteria andPredictCapitalNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_capital <>", value, "predictCapital");
            return (Criteria) this;
        }

        public Criteria andPredictCapitalGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.predict_capital >", value, "predictCapital");
            return (Criteria) this;
        }

        public Criteria andPredictCapitalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_capital >=", value, "predictCapital");
            return (Criteria) this;
        }

        public Criteria andPredictCapitalLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.predict_capital <", value, "predictCapital");
            return (Criteria) this;
        }

        public Criteria andPredictCapitalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_capital <=", value, "predictCapital");
            return (Criteria) this;
        }

        public Criteria andPredictCapitalIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.predict_capital in", values, "predictCapital");
            return (Criteria) this;
        }

        public Criteria andPredictCapitalNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.predict_capital not in", values, "predictCapital");
            return (Criteria) this;
        }

        public Criteria andPredictCapitalIn(String sql) {
            addCriterion("c_repayment_info.predict_capital in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andPredictCapitalNotIn(String sql) {
            addCriterion("c_repayment_info.predict_capital not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andPredictCapitalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.predict_capital between", value1, value2, "predictCapital");
            return (Criteria) this;
        }

        public Criteria andPredictCapitalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.predict_capital not between", value1, value2, "predictCapital");
            return (Criteria) this;
        }

        public Criteria andPredictInterestIsNull() {
            addCriterion("c_repayment_info.predict_interest is null");
            return (Criteria) this;
        }

        public Criteria andPredictInterestIsNotNull() {
            addCriterion("c_repayment_info.predict_interest is not null");
            return (Criteria) this;
        }

        public Criteria andPredictInterestEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_interest =", value, "predictInterest");
            return (Criteria) this;
        }

        public Criteria andPredictInterestNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_interest <>", value, "predictInterest");
            return (Criteria) this;
        }

        public Criteria andPredictInterestGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.predict_interest >", value, "predictInterest");
            return (Criteria) this;
        }

        public Criteria andPredictInterestGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_interest >=", value, "predictInterest");
            return (Criteria) this;
        }

        public Criteria andPredictInterestLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.predict_interest <", value, "predictInterest");
            return (Criteria) this;
        }

        public Criteria andPredictInterestLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_interest <=", value, "predictInterest");
            return (Criteria) this;
        }

        public Criteria andPredictInterestIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.predict_interest in", values, "predictInterest");
            return (Criteria) this;
        }

        public Criteria andPredictInterestNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.predict_interest not in", values, "predictInterest");
            return (Criteria) this;
        }

        public Criteria andPredictInterestIn(String sql) {
            addCriterion("c_repayment_info.predict_interest in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andPredictInterestNotIn(String sql) {
            addCriterion("c_repayment_info.predict_interest not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andPredictInterestBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.predict_interest between", value1, value2, "predictInterest");
            return (Criteria) this;
        }

        public Criteria andPredictInterestNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.predict_interest not between", value1, value2, "predictInterest");
            return (Criteria) this;
        }

        public Criteria andPredictServiceChargeIsNull() {
            addCriterion("c_repayment_info.predict_service_charge is null");
            return (Criteria) this;
        }

        public Criteria andPredictServiceChargeIsNotNull() {
            addCriterion("c_repayment_info.predict_service_charge is not null");
            return (Criteria) this;
        }

        public Criteria andPredictServiceChargeEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_service_charge =", value, "predictServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPredictServiceChargeNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_service_charge <>", value, "predictServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPredictServiceChargeGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.predict_service_charge >", value, "predictServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPredictServiceChargeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_service_charge >=", value, "predictServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPredictServiceChargeLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.predict_service_charge <", value, "predictServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPredictServiceChargeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_service_charge <=", value, "predictServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPredictServiceChargeIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.predict_service_charge in", values, "predictServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPredictServiceChargeNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.predict_service_charge not in", values, "predictServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPredictServiceChargeIn(String sql) {
            addCriterion("c_repayment_info.predict_service_charge in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andPredictServiceChargeNotIn(String sql) {
            addCriterion("c_repayment_info.predict_service_charge not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andPredictServiceChargeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.predict_service_charge between", value1, value2, "predictServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPredictServiceChargeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.predict_service_charge not between", value1, value2, "predictServiceCharge");
            return (Criteria) this;
        }

        public Criteria andPredictChargeIsNull() {
            addCriterion("c_repayment_info.predict_charge is null");
            return (Criteria) this;
        }

        public Criteria andPredictChargeIsNotNull() {
            addCriterion("c_repayment_info.predict_charge is not null");
            return (Criteria) this;
        }

        public Criteria andPredictChargeEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_charge =", value, "predictCharge");
            return (Criteria) this;
        }

        public Criteria andPredictChargeNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_charge <>", value, "predictCharge");
            return (Criteria) this;
        }

        public Criteria andPredictChargeGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.predict_charge >", value, "predictCharge");
            return (Criteria) this;
        }

        public Criteria andPredictChargeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_charge >=", value, "predictCharge");
            return (Criteria) this;
        }

        public Criteria andPredictChargeLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.predict_charge <", value, "predictCharge");
            return (Criteria) this;
        }

        public Criteria andPredictChargeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_charge <=", value, "predictCharge");
            return (Criteria) this;
        }

        public Criteria andPredictChargeIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.predict_charge in", values, "predictCharge");
            return (Criteria) this;
        }

        public Criteria andPredictChargeNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.predict_charge not in", values, "predictCharge");
            return (Criteria) this;
        }

        public Criteria andPredictChargeIn(String sql) {
            addCriterion("c_repayment_info.predict_charge in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andPredictChargeNotIn(String sql) {
            addCriterion("c_repayment_info.predict_charge not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andPredictChargeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.predict_charge between", value1, value2, "predictCharge");
            return (Criteria) this;
        }

        public Criteria andPredictChargeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.predict_charge not between", value1, value2, "predictCharge");
            return (Criteria) this;
        }

        public Criteria andPredictPenaltyIsNull() {
            addCriterion("c_repayment_info.predict_penalty is null");
            return (Criteria) this;
        }

        public Criteria andPredictPenaltyIsNotNull() {
            addCriterion("c_repayment_info.predict_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andPredictPenaltyEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_penalty =", value, "predictPenalty");
            return (Criteria) this;
        }

        public Criteria andPredictPenaltyNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_penalty <>", value, "predictPenalty");
            return (Criteria) this;
        }

        public Criteria andPredictPenaltyGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.predict_penalty >", value, "predictPenalty");
            return (Criteria) this;
        }

        public Criteria andPredictPenaltyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_penalty >=", value, "predictPenalty");
            return (Criteria) this;
        }

        public Criteria andPredictPenaltyLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.predict_penalty <", value, "predictPenalty");
            return (Criteria) this;
        }

        public Criteria andPredictPenaltyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.predict_penalty <=", value, "predictPenalty");
            return (Criteria) this;
        }

        public Criteria andPredictPenaltyIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.predict_penalty in", values, "predictPenalty");
            return (Criteria) this;
        }

        public Criteria andPredictPenaltyNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.predict_penalty not in", values, "predictPenalty");
            return (Criteria) this;
        }

        public Criteria andPredictPenaltyIn(String sql) {
            addCriterion("c_repayment_info.predict_penalty in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andPredictPenaltyNotIn(String sql) {
            addCriterion("c_repayment_info.predict_penalty not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andPredictPenaltyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.predict_penalty between", value1, value2, "predictPenalty");
            return (Criteria) this;
        }

        public Criteria andPredictPenaltyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.predict_penalty not between", value1, value2, "predictPenalty");
            return (Criteria) this;
        }

        public Criteria andActualAmountIsNull() {
            addCriterion("c_repayment_info.actual_amount is null");
            return (Criteria) this;
        }

        public Criteria andActualAmountIsNotNull() {
            addCriterion("c_repayment_info.actual_amount is not null");
            return (Criteria) this;
        }

        public Criteria andActualAmountEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_amount =", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_amount <>", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.actual_amount >", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_amount >=", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.actual_amount <", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_amount <=", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.actual_amount in", values, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.actual_amount not in", values, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountIn(String sql) {
            addCriterion("c_repayment_info.actual_amount in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotIn(String sql) {
            addCriterion("c_repayment_info.actual_amount not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andActualAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.actual_amount between", value1, value2, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.actual_amount not between", value1, value2, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualCapitalIsNull() {
            addCriterion("c_repayment_info.actual_capital is null");
            return (Criteria) this;
        }

        public Criteria andActualCapitalIsNotNull() {
            addCriterion("c_repayment_info.actual_capital is not null");
            return (Criteria) this;
        }

        public Criteria andActualCapitalEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_capital =", value, "actualCapital");
            return (Criteria) this;
        }

        public Criteria andActualCapitalNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_capital <>", value, "actualCapital");
            return (Criteria) this;
        }

        public Criteria andActualCapitalGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.actual_capital >", value, "actualCapital");
            return (Criteria) this;
        }

        public Criteria andActualCapitalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_capital >=", value, "actualCapital");
            return (Criteria) this;
        }

        public Criteria andActualCapitalLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.actual_capital <", value, "actualCapital");
            return (Criteria) this;
        }

        public Criteria andActualCapitalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_capital <=", value, "actualCapital");
            return (Criteria) this;
        }

        public Criteria andActualCapitalIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.actual_capital in", values, "actualCapital");
            return (Criteria) this;
        }

        public Criteria andActualCapitalNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.actual_capital not in", values, "actualCapital");
            return (Criteria) this;
        }

        public Criteria andActualCapitalIn(String sql) {
            addCriterion("c_repayment_info.actual_capital in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andActualCapitalNotIn(String sql) {
            addCriterion("c_repayment_info.actual_capital not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andActualCapitalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.actual_capital between", value1, value2, "actualCapital");
            return (Criteria) this;
        }

        public Criteria andActualCapitalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.actual_capital not between", value1, value2, "actualCapital");
            return (Criteria) this;
        }

        public Criteria andActualInterestIsNull() {
            addCriterion("c_repayment_info.actual_interest is null");
            return (Criteria) this;
        }

        public Criteria andActualInterestIsNotNull() {
            addCriterion("c_repayment_info.actual_interest is not null");
            return (Criteria) this;
        }

        public Criteria andActualInterestEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_interest =", value, "actualInterest");
            return (Criteria) this;
        }

        public Criteria andActualInterestNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_interest <>", value, "actualInterest");
            return (Criteria) this;
        }

        public Criteria andActualInterestGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.actual_interest >", value, "actualInterest");
            return (Criteria) this;
        }

        public Criteria andActualInterestGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_interest >=", value, "actualInterest");
            return (Criteria) this;
        }

        public Criteria andActualInterestLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.actual_interest <", value, "actualInterest");
            return (Criteria) this;
        }

        public Criteria andActualInterestLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_interest <=", value, "actualInterest");
            return (Criteria) this;
        }

        public Criteria andActualInterestIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.actual_interest in", values, "actualInterest");
            return (Criteria) this;
        }

        public Criteria andActualInterestNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.actual_interest not in", values, "actualInterest");
            return (Criteria) this;
        }

        public Criteria andActualInterestIn(String sql) {
            addCriterion("c_repayment_info.actual_interest in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andActualInterestNotIn(String sql) {
            addCriterion("c_repayment_info.actual_interest not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andActualInterestBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.actual_interest between", value1, value2, "actualInterest");
            return (Criteria) this;
        }

        public Criteria andActualInterestNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.actual_interest not between", value1, value2, "actualInterest");
            return (Criteria) this;
        }

        public Criteria andActualServiceChargeIsNull() {
            addCriterion("c_repayment_info.actual_service_charge is null");
            return (Criteria) this;
        }

        public Criteria andActualServiceChargeIsNotNull() {
            addCriterion("c_repayment_info.actual_service_charge is not null");
            return (Criteria) this;
        }

        public Criteria andActualServiceChargeEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_service_charge =", value, "actualServiceCharge");
            return (Criteria) this;
        }

        public Criteria andActualServiceChargeNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_service_charge <>", value, "actualServiceCharge");
            return (Criteria) this;
        }

        public Criteria andActualServiceChargeGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.actual_service_charge >", value, "actualServiceCharge");
            return (Criteria) this;
        }

        public Criteria andActualServiceChargeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_service_charge >=", value, "actualServiceCharge");
            return (Criteria) this;
        }

        public Criteria andActualServiceChargeLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.actual_service_charge <", value, "actualServiceCharge");
            return (Criteria) this;
        }

        public Criteria andActualServiceChargeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_service_charge <=", value, "actualServiceCharge");
            return (Criteria) this;
        }

        public Criteria andActualServiceChargeIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.actual_service_charge in", values, "actualServiceCharge");
            return (Criteria) this;
        }

        public Criteria andActualServiceChargeNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.actual_service_charge not in", values, "actualServiceCharge");
            return (Criteria) this;
        }

        public Criteria andActualServiceChargeIn(String sql) {
            addCriterion("c_repayment_info.actual_service_charge in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andActualServiceChargeNotIn(String sql) {
            addCriterion("c_repayment_info.actual_service_charge not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andActualServiceChargeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.actual_service_charge between", value1, value2, "actualServiceCharge");
            return (Criteria) this;
        }

        public Criteria andActualServiceChargeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.actual_service_charge not between", value1, value2, "actualServiceCharge");
            return (Criteria) this;
        }

        public Criteria andActualChargeIsNull() {
            addCriterion("c_repayment_info.actual_charge is null");
            return (Criteria) this;
        }

        public Criteria andActualChargeIsNotNull() {
            addCriterion("c_repayment_info.actual_charge is not null");
            return (Criteria) this;
        }

        public Criteria andActualChargeEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_charge =", value, "actualCharge");
            return (Criteria) this;
        }

        public Criteria andActualChargeNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_charge <>", value, "actualCharge");
            return (Criteria) this;
        }

        public Criteria andActualChargeGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.actual_charge >", value, "actualCharge");
            return (Criteria) this;
        }

        public Criteria andActualChargeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_charge >=", value, "actualCharge");
            return (Criteria) this;
        }

        public Criteria andActualChargeLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.actual_charge <", value, "actualCharge");
            return (Criteria) this;
        }

        public Criteria andActualChargeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_charge <=", value, "actualCharge");
            return (Criteria) this;
        }

        public Criteria andActualChargeIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.actual_charge in", values, "actualCharge");
            return (Criteria) this;
        }

        public Criteria andActualChargeNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.actual_charge not in", values, "actualCharge");
            return (Criteria) this;
        }

        public Criteria andActualChargeIn(String sql) {
            addCriterion("c_repayment_info.actual_charge in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andActualChargeNotIn(String sql) {
            addCriterion("c_repayment_info.actual_charge not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andActualChargeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.actual_charge between", value1, value2, "actualCharge");
            return (Criteria) this;
        }

        public Criteria andActualChargeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.actual_charge not between", value1, value2, "actualCharge");
            return (Criteria) this;
        }

        public Criteria andActualPenaltyIsNull() {
            addCriterion("c_repayment_info.actual_penalty is null");
            return (Criteria) this;
        }

        public Criteria andActualPenaltyIsNotNull() {
            addCriterion("c_repayment_info.actual_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andActualPenaltyEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_penalty =", value, "actualPenalty");
            return (Criteria) this;
        }

        public Criteria andActualPenaltyNotEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_penalty <>", value, "actualPenalty");
            return (Criteria) this;
        }

        public Criteria andActualPenaltyGreaterThan(BigDecimal value) {
            addCriterion("c_repayment_info.actual_penalty >", value, "actualPenalty");
            return (Criteria) this;
        }

        public Criteria andActualPenaltyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_penalty >=", value, "actualPenalty");
            return (Criteria) this;
        }

        public Criteria andActualPenaltyLessThan(BigDecimal value) {
            addCriterion("c_repayment_info.actual_penalty <", value, "actualPenalty");
            return (Criteria) this;
        }

        public Criteria andActualPenaltyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("c_repayment_info.actual_penalty <=", value, "actualPenalty");
            return (Criteria) this;
        }

        public Criteria andActualPenaltyIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.actual_penalty in", values, "actualPenalty");
            return (Criteria) this;
        }

        public Criteria andActualPenaltyNotIn(List<BigDecimal> values) {
            addCriterion("c_repayment_info.actual_penalty not in", values, "actualPenalty");
            return (Criteria) this;
        }

        public Criteria andActualPenaltyIn(String sql) {
            addCriterion("c_repayment_info.actual_penalty in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andActualPenaltyNotIn(String sql) {
            addCriterion("c_repayment_info.actual_penalty not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andActualPenaltyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.actual_penalty between", value1, value2, "actualPenalty");
            return (Criteria) this;
        }

        public Criteria andActualPenaltyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("c_repayment_info.actual_penalty not between", value1, value2, "actualPenalty");
            return (Criteria) this;
        }

        public Criteria andCurStatusIsNull() {
            addCriterion("c_repayment_info.cur_status is null");
            return (Criteria) this;
        }

        public Criteria andCurStatusIsNotNull() {
            addCriterion("c_repayment_info.cur_status is not null");
            return (Criteria) this;
        }

        public Criteria andCurStatusEqualTo(Integer value) {
            addCriterion("c_repayment_info.cur_status =", value, "curStatus");
            return (Criteria) this;
        }

        public Criteria andCurStatusNotEqualTo(Integer value) {
            addCriterion("c_repayment_info.cur_status <>", value, "curStatus");
            return (Criteria) this;
        }

        public Criteria andCurStatusGreaterThan(Integer value) {
            addCriterion("c_repayment_info.cur_status >", value, "curStatus");
            return (Criteria) this;
        }

        public Criteria andCurStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("c_repayment_info.cur_status >=", value, "curStatus");
            return (Criteria) this;
        }

        public Criteria andCurStatusLessThan(Integer value) {
            addCriterion("c_repayment_info.cur_status <", value, "curStatus");
            return (Criteria) this;
        }

        public Criteria andCurStatusLessThanOrEqualTo(Integer value) {
            addCriterion("c_repayment_info.cur_status <=", value, "curStatus");
            return (Criteria) this;
        }

        public Criteria andCurStatusIn(List<Integer> values) {
            addCriterion("c_repayment_info.cur_status in", values, "curStatus");
            return (Criteria) this;
        }

        public Criteria andCurStatusNotIn(List<Integer> values) {
            addCriterion("c_repayment_info.cur_status not in", values, "curStatus");
            return (Criteria) this;
        }

        public Criteria andCurStatusIn(String sql) {
            addCriterion("c_repayment_info.cur_status in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andCurStatusNotIn(String sql) {
            addCriterion("c_repayment_info.cur_status not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andCurStatusBetween(Integer value1, Integer value2) {
            addCriterion("c_repayment_info.cur_status between", value1, value2, "curStatus");
            return (Criteria) this;
        }

        public Criteria andCurStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("c_repayment_info.cur_status not between", value1, value2, "curStatus");
            return (Criteria) this;
        }

        public Criteria andCurStatusDescIsNull() {
            addCriterion("c_repayment_info.cur_status_desc is null");
            return (Criteria) this;
        }

        public Criteria andCurStatusDescIsNotNull() {
            addCriterion("c_repayment_info.cur_status_desc is not null");
            return (Criteria) this;
        }

        public Criteria andCurStatusDescEqualTo(String value) {
            addCriterion("c_repayment_info.cur_status_desc =", value, "curStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCurStatusDescNotEqualTo(String value) {
            addCriterion("c_repayment_info.cur_status_desc <>", value, "curStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCurStatusDescGreaterThan(String value) {
            addCriterion("c_repayment_info.cur_status_desc >", value, "curStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCurStatusDescGreaterThanOrEqualTo(String value) {
            addCriterion("c_repayment_info.cur_status_desc >=", value, "curStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCurStatusDescLessThan(String value) {
            addCriterion("c_repayment_info.cur_status_desc <", value, "curStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCurStatusDescLessThanOrEqualTo(String value) {
            addCriterion("c_repayment_info.cur_status_desc <=", value, "curStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCurStatusDescLike(String value) {
            addCriterion("c_repayment_info.cur_status_desc like", value, "curStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCurStatusDescNotLike(String value) {
            addCriterion("c_repayment_info.cur_status_desc not like", value, "curStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCurStatusDescIn(List<String> values) {
            addCriterion("c_repayment_info.cur_status_desc in", values, "curStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCurStatusDescNotIn(List<String> values) {
            addCriterion("c_repayment_info.cur_status_desc not in", values, "curStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCurStatusDescIn(String sql) {
            addCriterion("c_repayment_info.cur_status_desc in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andCurStatusDescNotIn(String sql) {
            addCriterion("c_repayment_info.cur_status_desc not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andCurStatusDescBetween(String value1, String value2) {
            addCriterion("c_repayment_info.cur_status_desc between", value1, value2, "curStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCurStatusDescNotBetween(String value1, String value2) {
            addCriterion("c_repayment_info.cur_status_desc not between", value1, value2, "curStatusDesc");
            return (Criteria) this;
        }

        public Criteria andIsOnceEarlyRepaymentIsNull() {
            addCriterion("c_repayment_info.is_once_early_repayment is null");
            return (Criteria) this;
        }

        public Criteria andIsOnceEarlyRepaymentIsNotNull() {
            addCriterion("c_repayment_info.is_once_early_repayment is not null");
            return (Criteria) this;
        }

        public Criteria andIsOnceEarlyRepaymentEqualTo(Integer value) {
            addCriterion("c_repayment_info.is_once_early_repayment =", value, "isOnceEarlyRepayment");
            return (Criteria) this;
        }

        public Criteria andIsOnceEarlyRepaymentNotEqualTo(Integer value) {
            addCriterion("c_repayment_info.is_once_early_repayment <>", value, "isOnceEarlyRepayment");
            return (Criteria) this;
        }

        public Criteria andIsOnceEarlyRepaymentGreaterThan(Integer value) {
            addCriterion("c_repayment_info.is_once_early_repayment >", value, "isOnceEarlyRepayment");
            return (Criteria) this;
        }

        public Criteria andIsOnceEarlyRepaymentGreaterThanOrEqualTo(Integer value) {
            addCriterion("c_repayment_info.is_once_early_repayment >=", value, "isOnceEarlyRepayment");
            return (Criteria) this;
        }

        public Criteria andIsOnceEarlyRepaymentLessThan(Integer value) {
            addCriterion("c_repayment_info.is_once_early_repayment <", value, "isOnceEarlyRepayment");
            return (Criteria) this;
        }

        public Criteria andIsOnceEarlyRepaymentLessThanOrEqualTo(Integer value) {
            addCriterion("c_repayment_info.is_once_early_repayment <=", value, "isOnceEarlyRepayment");
            return (Criteria) this;
        }

        public Criteria andIsOnceEarlyRepaymentIn(List<Integer> values) {
            addCriterion("c_repayment_info.is_once_early_repayment in", values, "isOnceEarlyRepayment");
            return (Criteria) this;
        }

        public Criteria andIsOnceEarlyRepaymentNotIn(List<Integer> values) {
            addCriterion("c_repayment_info.is_once_early_repayment not in", values, "isOnceEarlyRepayment");
            return (Criteria) this;
        }

        public Criteria andIsOnceEarlyRepaymentIn(String sql) {
            addCriterion("c_repayment_info.is_once_early_repayment in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andIsOnceEarlyRepaymentNotIn(String sql) {
            addCriterion("c_repayment_info.is_once_early_repayment not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andIsOnceEarlyRepaymentBetween(Integer value1, Integer value2) {
            addCriterion("c_repayment_info.is_once_early_repayment between", value1, value2, "isOnceEarlyRepayment");
            return (Criteria) this;
        }

        public Criteria andIsOnceEarlyRepaymentNotBetween(Integer value1, Integer value2) {
            addCriterion("c_repayment_info.is_once_early_repayment not between", value1, value2, "isOnceEarlyRepayment");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesIsNull() {
            addCriterion("c_repayment_info.overdue_times is null");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesIsNotNull() {
            addCriterion("c_repayment_info.overdue_times is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesEqualTo(Integer value) {
            addCriterion("c_repayment_info.overdue_times =", value, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesNotEqualTo(Integer value) {
            addCriterion("c_repayment_info.overdue_times <>", value, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesGreaterThan(Integer value) {
            addCriterion("c_repayment_info.overdue_times >", value, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("c_repayment_info.overdue_times >=", value, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesLessThan(Integer value) {
            addCriterion("c_repayment_info.overdue_times <", value, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesLessThanOrEqualTo(Integer value) {
            addCriterion("c_repayment_info.overdue_times <=", value, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesIn(List<Integer> values) {
            addCriterion("c_repayment_info.overdue_times in", values, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesNotIn(List<Integer> values) {
            addCriterion("c_repayment_info.overdue_times not in", values, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesIn(String sql) {
            addCriterion("c_repayment_info.overdue_times in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesNotIn(String sql) {
            addCriterion("c_repayment_info.overdue_times not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesBetween(Integer value1, Integer value2) {
            addCriterion("c_repayment_info.overdue_times between", value1, value2, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andOverdueTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("c_repayment_info.overdue_times not between", value1, value2, "overdueTimes");
            return (Criteria) this;
        }

        public Criteria andCurrentPeriodNumIsNull() {
            addCriterion("c_repayment_info.current_period_num is null");
            return (Criteria) this;
        }

        public Criteria andCurrentPeriodNumIsNotNull() {
            addCriterion("c_repayment_info.current_period_num is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentPeriodNumEqualTo(Integer value) {
            addCriterion("c_repayment_info.current_period_num =", value, "currentPeriodNum");
            return (Criteria) this;
        }

        public Criteria andCurrentPeriodNumNotEqualTo(Integer value) {
            addCriterion("c_repayment_info.current_period_num <>", value, "currentPeriodNum");
            return (Criteria) this;
        }

        public Criteria andCurrentPeriodNumGreaterThan(Integer value) {
            addCriterion("c_repayment_info.current_period_num >", value, "currentPeriodNum");
            return (Criteria) this;
        }

        public Criteria andCurrentPeriodNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("c_repayment_info.current_period_num >=", value, "currentPeriodNum");
            return (Criteria) this;
        }

        public Criteria andCurrentPeriodNumLessThan(Integer value) {
            addCriterion("c_repayment_info.current_period_num <", value, "currentPeriodNum");
            return (Criteria) this;
        }

        public Criteria andCurrentPeriodNumLessThanOrEqualTo(Integer value) {
            addCriterion("c_repayment_info.current_period_num <=", value, "currentPeriodNum");
            return (Criteria) this;
        }

        public Criteria andCurrentPeriodNumIn(List<Integer> values) {
            addCriterion("c_repayment_info.current_period_num in", values, "currentPeriodNum");
            return (Criteria) this;
        }

        public Criteria andCurrentPeriodNumNotIn(List<Integer> values) {
            addCriterion("c_repayment_info.current_period_num not in", values, "currentPeriodNum");
            return (Criteria) this;
        }

        public Criteria andCurrentPeriodNumIn(String sql) {
            addCriterion("c_repayment_info.current_period_num in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andCurrentPeriodNumNotIn(String sql) {
            addCriterion("c_repayment_info.current_period_num not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andCurrentPeriodNumBetween(Integer value1, Integer value2) {
            addCriterion("c_repayment_info.current_period_num between", value1, value2, "currentPeriodNum");
            return (Criteria) this;
        }

        public Criteria andCurrentPeriodNumNotBetween(Integer value1, Integer value2) {
            addCriterion("c_repayment_info.current_period_num not between", value1, value2, "currentPeriodNum");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("c_repayment_info.version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("c_repayment_info.version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("c_repayment_info.version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("c_repayment_info.version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("c_repayment_info.version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("c_repayment_info.version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("c_repayment_info.version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("c_repayment_info.version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("c_repayment_info.version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("c_repayment_info.version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(String sql) {
            addCriterion("c_repayment_info.version in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(String sql) {
            addCriterion("c_repayment_info.version not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("c_repayment_info.version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("c_repayment_info.version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("c_repayment_info.create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("c_repayment_info.create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("c_repayment_info.create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("c_repayment_info.create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("c_repayment_info.create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("c_repayment_info.create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("c_repayment_info.create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("c_repayment_info.create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("c_repayment_info.create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("c_repayment_info.create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(String sql) {
            addCriterion("c_repayment_info.create_time in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(String sql) {
            addCriterion("c_repayment_info.create_time not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("c_repayment_info.create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("c_repayment_info.create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("c_repayment_info.update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("c_repayment_info.update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("c_repayment_info.update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("c_repayment_info.update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("c_repayment_info.update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("c_repayment_info.update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("c_repayment_info.update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("c_repayment_info.update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("c_repayment_info.update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("c_repayment_info.update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(String sql) {
            addCriterion("c_repayment_info.update_time in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(String sql) {
            addCriterion("c_repayment_info.update_time not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("c_repayment_info.update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("c_repayment_info.update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("c_repayment_info.is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("c_repayment_info.is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Integer value) {
            addCriterion("c_repayment_info.is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Integer value) {
            addCriterion("c_repayment_info.is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Integer value) {
            addCriterion("c_repayment_info.is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Integer value) {
            addCriterion("c_repayment_info.is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Integer value) {
            addCriterion("c_repayment_info.is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Integer value) {
            addCriterion("c_repayment_info.is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Integer> values) {
            addCriterion("c_repayment_info.is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Integer> values) {
            addCriterion("c_repayment_info.is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(String sql) {
            addCriterion("c_repayment_info.is_deleted in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(String sql) {
            addCriterion("c_repayment_info.is_deleted not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Integer value1, Integer value2) {
            addCriterion("c_repayment_info.is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Integer value1, Integer value2) {
            addCriterion("c_repayment_info.is_deleted not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("c_repayment_info.remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("c_repayment_info.remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("c_repayment_info.remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("c_repayment_info.remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("c_repayment_info.remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("c_repayment_info.remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("c_repayment_info.remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("c_repayment_info.remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("c_repayment_info.remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("c_repayment_info.remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("c_repayment_info.remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("c_repayment_info.remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(String sql) {
            addCriterion("c_repayment_info.remark in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(String sql) {
            addCriterion("c_repayment_info.remark not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("c_repayment_info.remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("c_repayment_info.remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}