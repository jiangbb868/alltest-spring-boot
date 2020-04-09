package com.its.itone.sas.config.dao.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CiTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CiTypeExample() {
        oredCriteria = new ArrayList<Criteria>();
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andApplicationIsNull() {
            addCriterion("application is null");
            return (Criteria) this;
        }

        public Criteria andApplicationIsNotNull() {
            addCriterion("application is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationEqualTo(String value) {
            addCriterion("application =", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationNotEqualTo(String value) {
            addCriterion("application <>", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationGreaterThan(String value) {
            addCriterion("application >", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationGreaterThanOrEqualTo(String value) {
            addCriterion("application >=", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationLessThan(String value) {
            addCriterion("application <", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationLessThanOrEqualTo(String value) {
            addCriterion("application <=", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationLike(String value) {
            addCriterion("application like", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationNotLike(String value) {
            addCriterion("application not like", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationIn(List<String> values) {
            addCriterion("application in", values, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationNotIn(List<String> values) {
            addCriterion("application not in", values, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationBetween(String value1, String value2) {
            addCriterion("application between", value1, value2, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationNotBetween(String value1, String value2) {
            addCriterion("application not between", value1, value2, "application");
            return (Criteria) this;
        }

        public Criteria andDomainIsNull() {
            addCriterion("domain is null");
            return (Criteria) this;
        }

        public Criteria andDomainIsNotNull() {
            addCriterion("domain is not null");
            return (Criteria) this;
        }

        public Criteria andDomainEqualTo(String value) {
            addCriterion("domain =", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotEqualTo(String value) {
            addCriterion("domain <>", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainGreaterThan(String value) {
            addCriterion("domain >", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainGreaterThanOrEqualTo(String value) {
            addCriterion("domain >=", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLessThan(String value) {
            addCriterion("domain <", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLessThanOrEqualTo(String value) {
            addCriterion("domain <=", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainLike(String value) {
            addCriterion("domain like", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotLike(String value) {
            addCriterion("domain not like", value, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainIn(List<String> values) {
            addCriterion("domain in", values, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotIn(List<String> values) {
            addCriterion("domain not in", values, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainBetween(String value1, String value2) {
            addCriterion("domain between", value1, value2, "domain");
            return (Criteria) this;
        }

        public Criteria andDomainNotBetween(String value1, String value2) {
            addCriterion("domain not between", value1, value2, "domain");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDisplayNameIsNull() {
            addCriterion("display_name is null");
            return (Criteria) this;
        }

        public Criteria andDisplayNameIsNotNull() {
            addCriterion("display_name is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayNameEqualTo(String value) {
            addCriterion("display_name =", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameNotEqualTo(String value) {
            addCriterion("display_name <>", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameGreaterThan(String value) {
            addCriterion("display_name >", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameGreaterThanOrEqualTo(String value) {
            addCriterion("display_name >=", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameLessThan(String value) {
            addCriterion("display_name <", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameLessThanOrEqualTo(String value) {
            addCriterion("display_name <=", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameLike(String value) {
            addCriterion("display_name like", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameNotLike(String value) {
            addCriterion("display_name not like", value, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameIn(List<String> values) {
            addCriterion("display_name in", values, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameNotIn(List<String> values) {
            addCriterion("display_name not in", values, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameBetween(String value1, String value2) {
            addCriterion("display_name between", value1, value2, "displayName");
            return (Criteria) this;
        }

        public Criteria andDisplayNameNotBetween(String value1, String value2) {
            addCriterion("display_name not between", value1, value2, "displayName");
            return (Criteria) this;
        }

        public Criteria andFingerIsNull() {
            addCriterion("finger is null");
            return (Criteria) this;
        }

        public Criteria andFingerIsNotNull() {
            addCriterion("finger is not null");
            return (Criteria) this;
        }

        public Criteria andFingerEqualTo(String value) {
            addCriterion("finger =", value, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerNotEqualTo(String value) {
            addCriterion("finger <>", value, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerGreaterThan(String value) {
            addCriterion("finger >", value, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerGreaterThanOrEqualTo(String value) {
            addCriterion("finger >=", value, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerLessThan(String value) {
            addCriterion("finger <", value, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerLessThanOrEqualTo(String value) {
            addCriterion("finger <=", value, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerLike(String value) {
            addCriterion("finger like", value, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerNotLike(String value) {
            addCriterion("finger not like", value, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerIn(List<String> values) {
            addCriterion("finger in", values, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerNotIn(List<String> values) {
            addCriterion("finger not in", values, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerBetween(String value1, String value2) {
            addCriterion("finger between", value1, value2, "finger");
            return (Criteria) this;
        }

        public Criteria andFingerNotBetween(String value1, String value2) {
            addCriterion("finger not between", value1, value2, "finger");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andModifyUserIsNull() {
            addCriterion("modify_user is null");
            return (Criteria) this;
        }

        public Criteria andModifyUserIsNotNull() {
            addCriterion("modify_user is not null");
            return (Criteria) this;
        }

        public Criteria andModifyUserEqualTo(String value) {
            addCriterion("modify_user =", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserNotEqualTo(String value) {
            addCriterion("modify_user <>", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserGreaterThan(String value) {
            addCriterion("modify_user >", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserGreaterThanOrEqualTo(String value) {
            addCriterion("modify_user >=", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserLessThan(String value) {
            addCriterion("modify_user <", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserLessThanOrEqualTo(String value) {
            addCriterion("modify_user <=", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserLike(String value) {
            addCriterion("modify_user like", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserNotLike(String value) {
            addCriterion("modify_user not like", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserIn(List<String> values) {
            addCriterion("modify_user in", values, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserNotIn(List<String> values) {
            addCriterion("modify_user not in", values, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserBetween(String value1, String value2) {
            addCriterion("modify_user between", value1, value2, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserNotBetween(String value1, String value2) {
            addCriterion("modify_user not between", value1, value2, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andParentIsNull() {
            addCriterion("parent is null");
            return (Criteria) this;
        }

        public Criteria andParentIsNotNull() {
            addCriterion("parent is not null");
            return (Criteria) this;
        }

        public Criteria andParentEqualTo(String value) {
            addCriterion("parent =", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentNotEqualTo(String value) {
            addCriterion("parent <>", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentGreaterThan(String value) {
            addCriterion("parent >", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentGreaterThanOrEqualTo(String value) {
            addCriterion("parent >=", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentLessThan(String value) {
            addCriterion("parent <", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentLessThanOrEqualTo(String value) {
            addCriterion("parent <=", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentLike(String value) {
            addCriterion("parent like", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentNotLike(String value) {
            addCriterion("parent not like", value, "parent");
            return (Criteria) this;
        }

        public Criteria andParentIn(List<String> values) {
            addCriterion("parent in", values, "parent");
            return (Criteria) this;
        }

        public Criteria andParentNotIn(List<String> values) {
            addCriterion("parent not in", values, "parent");
            return (Criteria) this;
        }

        public Criteria andParentBetween(String value1, String value2) {
            addCriterion("parent between", value1, value2, "parent");
            return (Criteria) this;
        }

        public Criteria andParentNotBetween(String value1, String value2) {
            addCriterion("parent not between", value1, value2, "parent");
            return (Criteria) this;
        }

        public Criteria andTreeIdIsNull() {
            addCriterion("tree_id is null");
            return (Criteria) this;
        }

        public Criteria andTreeIdIsNotNull() {
            addCriterion("tree_id is not null");
            return (Criteria) this;
        }

        public Criteria andTreeIdEqualTo(Integer value) {
            addCriterion("tree_id =", value, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeIdNotEqualTo(Integer value) {
            addCriterion("tree_id <>", value, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeIdGreaterThan(Integer value) {
            addCriterion("tree_id >", value, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("tree_id >=", value, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeIdLessThan(Integer value) {
            addCriterion("tree_id <", value, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeIdLessThanOrEqualTo(Integer value) {
            addCriterion("tree_id <=", value, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeIdIn(List<Integer> values) {
            addCriterion("tree_id in", values, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeIdNotIn(List<Integer> values) {
            addCriterion("tree_id not in", values, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeIdBetween(Integer value1, Integer value2) {
            addCriterion("tree_id between", value1, value2, "treeId");
            return (Criteria) this;
        }

        public Criteria andTreeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("tree_id not between", value1, value2, "treeId");
            return (Criteria) this;
        }

        public Criteria andIdPathIsNull() {
            addCriterion("id_path is null");
            return (Criteria) this;
        }

        public Criteria andIdPathIsNotNull() {
            addCriterion("id_path is not null");
            return (Criteria) this;
        }

        public Criteria andIdPathEqualTo(Object value) {
            addCriterion("id_path =", value, "idPath");
            return (Criteria) this;
        }

        public Criteria andIdPathNotEqualTo(Object value) {
            addCriterion("id_path <>", value, "idPath");
            return (Criteria) this;
        }

        public Criteria andIdPathGreaterThan(Object value) {
            addCriterion("id_path >", value, "idPath");
            return (Criteria) this;
        }

        public Criteria andIdPathGreaterThanOrEqualTo(Object value) {
            addCriterion("id_path >=", value, "idPath");
            return (Criteria) this;
        }

        public Criteria andIdPathLessThan(Object value) {
            addCriterion("id_path <", value, "idPath");
            return (Criteria) this;
        }

        public Criteria andIdPathLessThanOrEqualTo(Object value) {
            addCriterion("id_path <=", value, "idPath");
            return (Criteria) this;
        }

        public Criteria andIdPathIn(List<Object> values) {
            addCriterion("id_path in", values, "idPath");
            return (Criteria) this;
        }

        public Criteria andIdPathNotIn(List<Object> values) {
            addCriterion("id_path not in", values, "idPath");
            return (Criteria) this;
        }

        public Criteria andIdPathBetween(Object value1, Object value2) {
            addCriterion("id_path between", value1, value2, "idPath");
            return (Criteria) this;
        }

        public Criteria andIdPathNotBetween(Object value1, Object value2) {
            addCriterion("id_path not between", value1, value2, "idPath");
            return (Criteria) this;
        }

        public Criteria andNamePathIsNull() {
            addCriterion("name_path is null");
            return (Criteria) this;
        }

        public Criteria andNamePathIsNotNull() {
            addCriterion("name_path is not null");
            return (Criteria) this;
        }

        public Criteria andNamePathEqualTo(String value) {
            addCriterion("name_path =", value, "namePath");
            return (Criteria) this;
        }

        public Criteria andNamePathNotEqualTo(String value) {
            addCriterion("name_path <>", value, "namePath");
            return (Criteria) this;
        }

        public Criteria andNamePathGreaterThan(String value) {
            addCriterion("name_path >", value, "namePath");
            return (Criteria) this;
        }

        public Criteria andNamePathGreaterThanOrEqualTo(String value) {
            addCriterion("name_path >=", value, "namePath");
            return (Criteria) this;
        }

        public Criteria andNamePathLessThan(String value) {
            addCriterion("name_path <", value, "namePath");
            return (Criteria) this;
        }

        public Criteria andNamePathLessThanOrEqualTo(String value) {
            addCriterion("name_path <=", value, "namePath");
            return (Criteria) this;
        }

        public Criteria andNamePathLike(String value) {
            addCriterion("name_path like", value, "namePath");
            return (Criteria) this;
        }

        public Criteria andNamePathNotLike(String value) {
            addCriterion("name_path not like", value, "namePath");
            return (Criteria) this;
        }

        public Criteria andNamePathIn(List<String> values) {
            addCriterion("name_path in", values, "namePath");
            return (Criteria) this;
        }

        public Criteria andNamePathNotIn(List<String> values) {
            addCriterion("name_path not in", values, "namePath");
            return (Criteria) this;
        }

        public Criteria andNamePathBetween(String value1, String value2) {
            addCriterion("name_path between", value1, value2, "namePath");
            return (Criteria) this;
        }

        public Criteria andNamePathNotBetween(String value1, String value2) {
            addCriterion("name_path not between", value1, value2, "namePath");
            return (Criteria) this;
        }

        public Criteria andSubsIsNull() {
            addCriterion("subs is null");
            return (Criteria) this;
        }

        public Criteria andSubsIsNotNull() {
            addCriterion("subs is not null");
            return (Criteria) this;
        }

        public Criteria andSubsEqualTo(Object value) {
            addCriterion("subs =", value, "subs");
            return (Criteria) this;
        }

        public Criteria andSubsNotEqualTo(Object value) {
            addCriterion("subs <>", value, "subs");
            return (Criteria) this;
        }

        public Criteria andSubsGreaterThan(Object value) {
            addCriterion("subs >", value, "subs");
            return (Criteria) this;
        }

        public Criteria andSubsGreaterThanOrEqualTo(Object value) {
            addCriterion("subs >=", value, "subs");
            return (Criteria) this;
        }

        public Criteria andSubsLessThan(Object value) {
            addCriterion("subs <", value, "subs");
            return (Criteria) this;
        }

        public Criteria andSubsLessThanOrEqualTo(Object value) {
            addCriterion("subs <=", value, "subs");
            return (Criteria) this;
        }

        public Criteria andSubsIn(List<Object> values) {
            addCriterion("subs in", values, "subs");
            return (Criteria) this;
        }

        public Criteria andSubsNotIn(List<Object> values) {
            addCriterion("subs not in", values, "subs");
            return (Criteria) this;
        }

        public Criteria andSubsBetween(Object value1, Object value2) {
            addCriterion("subs between", value1, value2, "subs");
            return (Criteria) this;
        }

        public Criteria andSubsNotBetween(Object value1, Object value2) {
            addCriterion("subs not between", value1, value2, "subs");
            return (Criteria) this;
        }

        public Criteria andAttributesIsNull() {
            addCriterion("attributes is null");
            return (Criteria) this;
        }

        public Criteria andAttributesIsNotNull() {
            addCriterion("attributes is not null");
            return (Criteria) this;
        }

        public Criteria andAttributesEqualTo(Object value) {
            addCriterion("attributes =", value, "attributes");
            return (Criteria) this;
        }

        public Criteria andAttributesNotEqualTo(Object value) {
            addCriterion("attributes <>", value, "attributes");
            return (Criteria) this;
        }

        public Criteria andAttributesGreaterThan(Object value) {
            addCriterion("attributes >", value, "attributes");
            return (Criteria) this;
        }

        public Criteria andAttributesGreaterThanOrEqualTo(Object value) {
            addCriterion("attributes >=", value, "attributes");
            return (Criteria) this;
        }

        public Criteria andAttributesLessThan(Object value) {
            addCriterion("attributes <", value, "attributes");
            return (Criteria) this;
        }

        public Criteria andAttributesLessThanOrEqualTo(Object value) {
            addCriterion("attributes <=", value, "attributes");
            return (Criteria) this;
        }

        public Criteria andAttributesIn(List<Object> values) {
            addCriterion("attributes in", values, "attributes");
            return (Criteria) this;
        }

        public Criteria andAttributesNotIn(List<Object> values) {
            addCriterion("attributes not in", values, "attributes");
            return (Criteria) this;
        }

        public Criteria andAttributesBetween(Object value1, Object value2) {
            addCriterion("attributes between", value1, value2, "attributes");
            return (Criteria) this;
        }

        public Criteria andAttributesNotBetween(Object value1, Object value2) {
            addCriterion("attributes not between", value1, value2, "attributes");
            return (Criteria) this;
        }

        public Criteria andCSearchIsNull() {
            addCriterion("c_search is null");
            return (Criteria) this;
        }

        public Criteria andCSearchIsNotNull() {
            addCriterion("c_search is not null");
            return (Criteria) this;
        }

        public Criteria andCSearchEqualTo(String value) {
            addCriterion("c_search =", value, "cSearch");
            return (Criteria) this;
        }

        public Criteria andCSearchNotEqualTo(String value) {
            addCriterion("c_search <>", value, "cSearch");
            return (Criteria) this;
        }

        public Criteria andCSearchGreaterThan(String value) {
            addCriterion("c_search >", value, "cSearch");
            return (Criteria) this;
        }

        public Criteria andCSearchGreaterThanOrEqualTo(String value) {
            addCriterion("c_search >=", value, "cSearch");
            return (Criteria) this;
        }

        public Criteria andCSearchLessThan(String value) {
            addCriterion("c_search <", value, "cSearch");
            return (Criteria) this;
        }

        public Criteria andCSearchLessThanOrEqualTo(String value) {
            addCriterion("c_search <=", value, "cSearch");
            return (Criteria) this;
        }

        public Criteria andCSearchLike(String value) {
            addCriterion("c_search like", value, "cSearch");
            return (Criteria) this;
        }

        public Criteria andCSearchNotLike(String value) {
            addCriterion("c_search not like", value, "cSearch");
            return (Criteria) this;
        }

        public Criteria andCSearchIn(List<String> values) {
            addCriterion("c_search in", values, "cSearch");
            return (Criteria) this;
        }

        public Criteria andCSearchNotIn(List<String> values) {
            addCriterion("c_search not in", values, "cSearch");
            return (Criteria) this;
        }

        public Criteria andCSearchBetween(String value1, String value2) {
            addCriterion("c_search between", value1, value2, "cSearch");
            return (Criteria) this;
        }

        public Criteria andCSearchNotBetween(String value1, String value2) {
            addCriterion("c_search not between", value1, value2, "cSearch");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(String value) {
            addCriterion("tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(String value) {
            addCriterion("tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(String value) {
            addCriterion("tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(String value) {
            addCriterion("tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(String value) {
            addCriterion("tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(String value) {
            addCriterion("tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLike(String value) {
            addCriterion("tag like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotLike(String value) {
            addCriterion("tag not like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<String> values) {
            addCriterion("tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<String> values) {
            addCriterion("tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(String value1, String value2) {
            addCriterion("tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(String value1, String value2) {
            addCriterion("tag not between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andIndexIsNull() {
            addCriterion("index is null");
            return (Criteria) this;
        }

        public Criteria andIndexIsNotNull() {
            addCriterion("index is not null");
            return (Criteria) this;
        }

        public Criteria andIndexEqualTo(Integer value) {
            addCriterion("index =", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexNotEqualTo(Integer value) {
            addCriterion("index <>", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexGreaterThan(Integer value) {
            addCriterion("index >", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("index >=", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexLessThan(Integer value) {
            addCriterion("index <", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexLessThanOrEqualTo(Integer value) {
            addCriterion("index <=", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexIn(List<Integer> values) {
            addCriterion("index in", values, "index");
            return (Criteria) this;
        }

        public Criteria andIndexNotIn(List<Integer> values) {
            addCriterion("index not in", values, "index");
            return (Criteria) this;
        }

        public Criteria andIndexBetween(Integer value1, Integer value2) {
            addCriterion("index between", value1, value2, "index");
            return (Criteria) this;
        }

        public Criteria andIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("index not between", value1, value2, "index");
            return (Criteria) this;
        }

        public Criteria andOldIdIsNull() {
            addCriterion("old_id is null");
            return (Criteria) this;
        }

        public Criteria andOldIdIsNotNull() {
            addCriterion("old_id is not null");
            return (Criteria) this;
        }

        public Criteria andOldIdEqualTo(Integer value) {
            addCriterion("old_id =", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdNotEqualTo(Integer value) {
            addCriterion("old_id <>", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdGreaterThan(Integer value) {
            addCriterion("old_id >", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("old_id >=", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdLessThan(Integer value) {
            addCriterion("old_id <", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdLessThanOrEqualTo(Integer value) {
            addCriterion("old_id <=", value, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdIn(List<Integer> values) {
            addCriterion("old_id in", values, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdNotIn(List<Integer> values) {
            addCriterion("old_id not in", values, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdBetween(Integer value1, Integer value2) {
            addCriterion("old_id between", value1, value2, "oldId");
            return (Criteria) this;
        }

        public Criteria andOldIdNotBetween(Integer value1, Integer value2) {
            addCriterion("old_id not between", value1, value2, "oldId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andIsActiveIsNull() {
            addCriterion("is_active is null");
            return (Criteria) this;
        }

        public Criteria andIsActiveIsNotNull() {
            addCriterion("is_active is not null");
            return (Criteria) this;
        }

        public Criteria andIsActiveEqualTo(Boolean value) {
            addCriterion("is_active =", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotEqualTo(Boolean value) {
            addCriterion("is_active <>", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveGreaterThan(Boolean value) {
            addCriterion("is_active >", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_active >=", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveLessThan(Boolean value) {
            addCriterion("is_active <", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveLessThanOrEqualTo(Boolean value) {
            addCriterion("is_active <=", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveIn(List<Boolean> values) {
            addCriterion("is_active in", values, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotIn(List<Boolean> values) {
            addCriterion("is_active not in", values, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveBetween(Boolean value1, Boolean value2) {
            addCriterion("is_active between", value1, value2, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_active not between", value1, value2, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsSystemIsNull() {
            addCriterion("is_system is null");
            return (Criteria) this;
        }

        public Criteria andIsSystemIsNotNull() {
            addCriterion("is_system is not null");
            return (Criteria) this;
        }

        public Criteria andIsSystemEqualTo(Boolean value) {
            addCriterion("is_system =", value, "isSystem");
            return (Criteria) this;
        }

        public Criteria andIsSystemNotEqualTo(Boolean value) {
            addCriterion("is_system <>", value, "isSystem");
            return (Criteria) this;
        }

        public Criteria andIsSystemGreaterThan(Boolean value) {
            addCriterion("is_system >", value, "isSystem");
            return (Criteria) this;
        }

        public Criteria andIsSystemGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_system >=", value, "isSystem");
            return (Criteria) this;
        }

        public Criteria andIsSystemLessThan(Boolean value) {
            addCriterion("is_system <", value, "isSystem");
            return (Criteria) this;
        }

        public Criteria andIsSystemLessThanOrEqualTo(Boolean value) {
            addCriterion("is_system <=", value, "isSystem");
            return (Criteria) this;
        }

        public Criteria andIsSystemIn(List<Boolean> values) {
            addCriterion("is_system in", values, "isSystem");
            return (Criteria) this;
        }

        public Criteria andIsSystemNotIn(List<Boolean> values) {
            addCriterion("is_system not in", values, "isSystem");
            return (Criteria) this;
        }

        public Criteria andIsSystemBetween(Boolean value1, Boolean value2) {
            addCriterion("is_system between", value1, value2, "isSystem");
            return (Criteria) this;
        }

        public Criteria andIsSystemNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_system not between", value1, value2, "isSystem");
            return (Criteria) this;
        }

        public Criteria andHoldsHistoryIsNull() {
            addCriterion("holds_history is null");
            return (Criteria) this;
        }

        public Criteria andHoldsHistoryIsNotNull() {
            addCriterion("holds_history is not null");
            return (Criteria) this;
        }

        public Criteria andHoldsHistoryEqualTo(Boolean value) {
            addCriterion("holds_history =", value, "holdsHistory");
            return (Criteria) this;
        }

        public Criteria andHoldsHistoryNotEqualTo(Boolean value) {
            addCriterion("holds_history <>", value, "holdsHistory");
            return (Criteria) this;
        }

        public Criteria andHoldsHistoryGreaterThan(Boolean value) {
            addCriterion("holds_history >", value, "holdsHistory");
            return (Criteria) this;
        }

        public Criteria andHoldsHistoryGreaterThanOrEqualTo(Boolean value) {
            addCriterion("holds_history >=", value, "holdsHistory");
            return (Criteria) this;
        }

        public Criteria andHoldsHistoryLessThan(Boolean value) {
            addCriterion("holds_history <", value, "holdsHistory");
            return (Criteria) this;
        }

        public Criteria andHoldsHistoryLessThanOrEqualTo(Boolean value) {
            addCriterion("holds_history <=", value, "holdsHistory");
            return (Criteria) this;
        }

        public Criteria andHoldsHistoryIn(List<Boolean> values) {
            addCriterion("holds_history in", values, "holdsHistory");
            return (Criteria) this;
        }

        public Criteria andHoldsHistoryNotIn(List<Boolean> values) {
            addCriterion("holds_history not in", values, "holdsHistory");
            return (Criteria) this;
        }

        public Criteria andHoldsHistoryBetween(Boolean value1, Boolean value2) {
            addCriterion("holds_history between", value1, value2, "holdsHistory");
            return (Criteria) this;
        }

        public Criteria andHoldsHistoryNotBetween(Boolean value1, Boolean value2) {
            addCriterion("holds_history not between", value1, value2, "holdsHistory");
            return (Criteria) this;
        }

        public Criteria andIsEnabledIsNull() {
            addCriterion("is_enabled is null");
            return (Criteria) this;
        }

        public Criteria andIsEnabledIsNotNull() {
            addCriterion("is_enabled is not null");
            return (Criteria) this;
        }

        public Criteria andIsEnabledEqualTo(Boolean value) {
            addCriterion("is_enabled =", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledNotEqualTo(Boolean value) {
            addCriterion("is_enabled <>", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledGreaterThan(Boolean value) {
            addCriterion("is_enabled >", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_enabled >=", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledLessThan(Boolean value) {
            addCriterion("is_enabled <", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledLessThanOrEqualTo(Boolean value) {
            addCriterion("is_enabled <=", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledIn(List<Boolean> values) {
            addCriterion("is_enabled in", values, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledNotIn(List<Boolean> values) {
            addCriterion("is_enabled not in", values, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledBetween(Boolean value1, Boolean value2) {
            addCriterion("is_enabled between", value1, value2, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_enabled not between", value1, value2, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableIsNull() {
            addCriterion("physical_table is null");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableIsNotNull() {
            addCriterion("physical_table is not null");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableEqualTo(String value) {
            addCriterion("physical_table =", value, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableNotEqualTo(String value) {
            addCriterion("physical_table <>", value, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableGreaterThan(String value) {
            addCriterion("physical_table >", value, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableGreaterThanOrEqualTo(String value) {
            addCriterion("physical_table >=", value, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableLessThan(String value) {
            addCriterion("physical_table <", value, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableLessThanOrEqualTo(String value) {
            addCriterion("physical_table <=", value, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableLike(String value) {
            addCriterion("physical_table like", value, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableNotLike(String value) {
            addCriterion("physical_table not like", value, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableIn(List<String> values) {
            addCriterion("physical_table in", values, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableNotIn(List<String> values) {
            addCriterion("physical_table not in", values, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableBetween(String value1, String value2) {
            addCriterion("physical_table between", value1, value2, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableNotBetween(String value1, String value2) {
            addCriterion("physical_table not between", value1, value2, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andSubsystemIsNull() {
            addCriterion("subsystem is null");
            return (Criteria) this;
        }

        public Criteria andSubsystemIsNotNull() {
            addCriterion("subsystem is not null");
            return (Criteria) this;
        }

        public Criteria andSubsystemEqualTo(String value) {
            addCriterion("subsystem =", value, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemNotEqualTo(String value) {
            addCriterion("subsystem <>", value, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemGreaterThan(String value) {
            addCriterion("subsystem >", value, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemGreaterThanOrEqualTo(String value) {
            addCriterion("subsystem >=", value, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemLessThan(String value) {
            addCriterion("subsystem <", value, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemLessThanOrEqualTo(String value) {
            addCriterion("subsystem <=", value, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemLike(String value) {
            addCriterion("subsystem like", value, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemNotLike(String value) {
            addCriterion("subsystem not like", value, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemIn(List<String> values) {
            addCriterion("subsystem in", values, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemNotIn(List<String> values) {
            addCriterion("subsystem not in", values, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemBetween(String value1, String value2) {
            addCriterion("subsystem between", value1, value2, "subsystem");
            return (Criteria) this;
        }

        public Criteria andSubsystemNotBetween(String value1, String value2) {
            addCriterion("subsystem not between", value1, value2, "subsystem");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTypeIsNull() {
            addCriterion("is_physical_type is null");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTypeIsNotNull() {
            addCriterion("is_physical_type is not null");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTypeEqualTo(Boolean value) {
            addCriterion("is_physical_type =", value, "isPhysicalType");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTypeNotEqualTo(Boolean value) {
            addCriterion("is_physical_type <>", value, "isPhysicalType");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTypeGreaterThan(Boolean value) {
            addCriterion("is_physical_type >", value, "isPhysicalType");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTypeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_physical_type >=", value, "isPhysicalType");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTypeLessThan(Boolean value) {
            addCriterion("is_physical_type <", value, "isPhysicalType");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTypeLessThanOrEqualTo(Boolean value) {
            addCriterion("is_physical_type <=", value, "isPhysicalType");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTypeIn(List<Boolean> values) {
            addCriterion("is_physical_type in", values, "isPhysicalType");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTypeNotIn(List<Boolean> values) {
            addCriterion("is_physical_type not in", values, "isPhysicalType");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTypeBetween(Boolean value1, Boolean value2) {
            addCriterion("is_physical_type between", value1, value2, "isPhysicalType");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTypeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_physical_type not between", value1, value2, "isPhysicalType");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTableIsNull() {
            addCriterion("is_physical_table is null");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTableIsNotNull() {
            addCriterion("is_physical_table is not null");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTableEqualTo(Boolean value) {
            addCriterion("is_physical_table =", value, "isPhysicalTable");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTableNotEqualTo(Boolean value) {
            addCriterion("is_physical_table <>", value, "isPhysicalTable");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTableGreaterThan(Boolean value) {
            addCriterion("is_physical_table >", value, "isPhysicalTable");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_physical_table >=", value, "isPhysicalTable");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTableLessThan(Boolean value) {
            addCriterion("is_physical_table <", value, "isPhysicalTable");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTableLessThanOrEqualTo(Boolean value) {
            addCriterion("is_physical_table <=", value, "isPhysicalTable");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTableIn(List<Boolean> values) {
            addCriterion("is_physical_table in", values, "isPhysicalTable");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTableNotIn(List<Boolean> values) {
            addCriterion("is_physical_table not in", values, "isPhysicalTable");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTableBetween(Boolean value1, Boolean value2) {
            addCriterion("is_physical_table between", value1, value2, "isPhysicalTable");
            return (Criteria) this;
        }

        public Criteria andIsPhysicalTableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_physical_table not between", value1, value2, "isPhysicalTable");
            return (Criteria) this;
        }

        public Criteria andIsTemplateIsNull() {
            addCriterion("is_template is null");
            return (Criteria) this;
        }

        public Criteria andIsTemplateIsNotNull() {
            addCriterion("is_template is not null");
            return (Criteria) this;
        }

        public Criteria andIsTemplateEqualTo(Boolean value) {
            addCriterion("is_template =", value, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsTemplateNotEqualTo(Boolean value) {
            addCriterion("is_template <>", value, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsTemplateGreaterThan(Boolean value) {
            addCriterion("is_template >", value, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsTemplateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_template >=", value, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsTemplateLessThan(Boolean value) {
            addCriterion("is_template <", value, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsTemplateLessThanOrEqualTo(Boolean value) {
            addCriterion("is_template <=", value, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsTemplateIn(List<Boolean> values) {
            addCriterion("is_template in", values, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsTemplateNotIn(List<Boolean> values) {
            addCriterion("is_template not in", values, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsTemplateBetween(Boolean value1, Boolean value2) {
            addCriterion("is_template between", value1, value2, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andIsTemplateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_template not between", value1, value2, "isTemplate");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andPreviousIsNull() {
            addCriterion("previous is null");
            return (Criteria) this;
        }

        public Criteria andPreviousIsNotNull() {
            addCriterion("previous is not null");
            return (Criteria) this;
        }

        public Criteria andPreviousEqualTo(String value) {
            addCriterion("previous =", value, "previous");
            return (Criteria) this;
        }

        public Criteria andPreviousNotEqualTo(String value) {
            addCriterion("previous <>", value, "previous");
            return (Criteria) this;
        }

        public Criteria andPreviousGreaterThan(String value) {
            addCriterion("previous >", value, "previous");
            return (Criteria) this;
        }

        public Criteria andPreviousGreaterThanOrEqualTo(String value) {
            addCriterion("previous >=", value, "previous");
            return (Criteria) this;
        }

        public Criteria andPreviousLessThan(String value) {
            addCriterion("previous <", value, "previous");
            return (Criteria) this;
        }

        public Criteria andPreviousLessThanOrEqualTo(String value) {
            addCriterion("previous <=", value, "previous");
            return (Criteria) this;
        }

        public Criteria andPreviousLike(String value) {
            addCriterion("previous like", value, "previous");
            return (Criteria) this;
        }

        public Criteria andPreviousNotLike(String value) {
            addCriterion("previous not like", value, "previous");
            return (Criteria) this;
        }

        public Criteria andPreviousIn(List<String> values) {
            addCriterion("previous in", values, "previous");
            return (Criteria) this;
        }

        public Criteria andPreviousNotIn(List<String> values) {
            addCriterion("previous not in", values, "previous");
            return (Criteria) this;
        }

        public Criteria andPreviousBetween(String value1, String value2) {
            addCriterion("previous between", value1, value2, "previous");
            return (Criteria) this;
        }

        public Criteria andPreviousNotBetween(String value1, String value2) {
            addCriterion("previous not between", value1, value2, "previous");
            return (Criteria) this;
        }

        public Criteria andIsTreeIsNull() {
            addCriterion("is_tree is null");
            return (Criteria) this;
        }

        public Criteria andIsTreeIsNotNull() {
            addCriterion("is_tree is not null");
            return (Criteria) this;
        }

        public Criteria andIsTreeEqualTo(Boolean value) {
            addCriterion("is_tree =", value, "isTree");
            return (Criteria) this;
        }

        public Criteria andIsTreeNotEqualTo(Boolean value) {
            addCriterion("is_tree <>", value, "isTree");
            return (Criteria) this;
        }

        public Criteria andIsTreeGreaterThan(Boolean value) {
            addCriterion("is_tree >", value, "isTree");
            return (Criteria) this;
        }

        public Criteria andIsTreeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_tree >=", value, "isTree");
            return (Criteria) this;
        }

        public Criteria andIsTreeLessThan(Boolean value) {
            addCriterion("is_tree <", value, "isTree");
            return (Criteria) this;
        }

        public Criteria andIsTreeLessThanOrEqualTo(Boolean value) {
            addCriterion("is_tree <=", value, "isTree");
            return (Criteria) this;
        }

        public Criteria andIsTreeIn(List<Boolean> values) {
            addCriterion("is_tree in", values, "isTree");
            return (Criteria) this;
        }

        public Criteria andIsTreeNotIn(List<Boolean> values) {
            addCriterion("is_tree not in", values, "isTree");
            return (Criteria) this;
        }

        public Criteria andIsTreeBetween(Boolean value1, Boolean value2) {
            addCriterion("is_tree between", value1, value2, "isTree");
            return (Criteria) this;
        }

        public Criteria andIsTreeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_tree not between", value1, value2, "isTree");
            return (Criteria) this;
        }

        public Criteria andDeletableIsNull() {
            addCriterion("deletable is null");
            return (Criteria) this;
        }

        public Criteria andDeletableIsNotNull() {
            addCriterion("deletable is not null");
            return (Criteria) this;
        }

        public Criteria andDeletableEqualTo(Boolean value) {
            addCriterion("deletable =", value, "deletable");
            return (Criteria) this;
        }

        public Criteria andDeletableNotEqualTo(Boolean value) {
            addCriterion("deletable <>", value, "deletable");
            return (Criteria) this;
        }

        public Criteria andDeletableGreaterThan(Boolean value) {
            addCriterion("deletable >", value, "deletable");
            return (Criteria) this;
        }

        public Criteria andDeletableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("deletable >=", value, "deletable");
            return (Criteria) this;
        }

        public Criteria andDeletableLessThan(Boolean value) {
            addCriterion("deletable <", value, "deletable");
            return (Criteria) this;
        }

        public Criteria andDeletableLessThanOrEqualTo(Boolean value) {
            addCriterion("deletable <=", value, "deletable");
            return (Criteria) this;
        }

        public Criteria andDeletableIn(List<Boolean> values) {
            addCriterion("deletable in", values, "deletable");
            return (Criteria) this;
        }

        public Criteria andDeletableNotIn(List<Boolean> values) {
            addCriterion("deletable not in", values, "deletable");
            return (Criteria) this;
        }

        public Criteria andDeletableBetween(Boolean value1, Boolean value2) {
            addCriterion("deletable between", value1, value2, "deletable");
            return (Criteria) this;
        }

        public Criteria andDeletableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("deletable not between", value1, value2, "deletable");
            return (Criteria) this;
        }

        public Criteria andSearchableIsNull() {
            addCriterion("searchable is null");
            return (Criteria) this;
        }

        public Criteria andSearchableIsNotNull() {
            addCriterion("searchable is not null");
            return (Criteria) this;
        }

        public Criteria andSearchableEqualTo(Boolean value) {
            addCriterion("searchable =", value, "searchable");
            return (Criteria) this;
        }

        public Criteria andSearchableNotEqualTo(Boolean value) {
            addCriterion("searchable <>", value, "searchable");
            return (Criteria) this;
        }

        public Criteria andSearchableGreaterThan(Boolean value) {
            addCriterion("searchable >", value, "searchable");
            return (Criteria) this;
        }

        public Criteria andSearchableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("searchable >=", value, "searchable");
            return (Criteria) this;
        }

        public Criteria andSearchableLessThan(Boolean value) {
            addCriterion("searchable <", value, "searchable");
            return (Criteria) this;
        }

        public Criteria andSearchableLessThanOrEqualTo(Boolean value) {
            addCriterion("searchable <=", value, "searchable");
            return (Criteria) this;
        }

        public Criteria andSearchableIn(List<Boolean> values) {
            addCriterion("searchable in", values, "searchable");
            return (Criteria) this;
        }

        public Criteria andSearchableNotIn(List<Boolean> values) {
            addCriterion("searchable not in", values, "searchable");
            return (Criteria) this;
        }

        public Criteria andSearchableBetween(Boolean value1, Boolean value2) {
            addCriterion("searchable between", value1, value2, "searchable");
            return (Criteria) this;
        }

        public Criteria andSearchableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("searchable not between", value1, value2, "searchable");
            return (Criteria) this;
        }

        public Criteria andExtSearchIsNull() {
            addCriterion("ext_search is null");
            return (Criteria) this;
        }

        public Criteria andExtSearchIsNotNull() {
            addCriterion("ext_search is not null");
            return (Criteria) this;
        }

        public Criteria andExtSearchEqualTo(String value) {
            addCriterion("ext_search =", value, "extSearch");
            return (Criteria) this;
        }

        public Criteria andExtSearchNotEqualTo(String value) {
            addCriterion("ext_search <>", value, "extSearch");
            return (Criteria) this;
        }

        public Criteria andExtSearchGreaterThan(String value) {
            addCriterion("ext_search >", value, "extSearch");
            return (Criteria) this;
        }

        public Criteria andExtSearchGreaterThanOrEqualTo(String value) {
            addCriterion("ext_search >=", value, "extSearch");
            return (Criteria) this;
        }

        public Criteria andExtSearchLessThan(String value) {
            addCriterion("ext_search <", value, "extSearch");
            return (Criteria) this;
        }

        public Criteria andExtSearchLessThanOrEqualTo(String value) {
            addCriterion("ext_search <=", value, "extSearch");
            return (Criteria) this;
        }

        public Criteria andExtSearchLike(String value) {
            addCriterion("ext_search like", value, "extSearch");
            return (Criteria) this;
        }

        public Criteria andExtSearchNotLike(String value) {
            addCriterion("ext_search not like", value, "extSearch");
            return (Criteria) this;
        }

        public Criteria andExtSearchIn(List<String> values) {
            addCriterion("ext_search in", values, "extSearch");
            return (Criteria) this;
        }

        public Criteria andExtSearchNotIn(List<String> values) {
            addCriterion("ext_search not in", values, "extSearch");
            return (Criteria) this;
        }

        public Criteria andExtSearchBetween(String value1, String value2) {
            addCriterion("ext_search between", value1, value2, "extSearch");
            return (Criteria) this;
        }

        public Criteria andExtSearchNotBetween(String value1, String value2) {
            addCriterion("ext_search not between", value1, value2, "extSearch");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinIsNull() {
            addCriterion("is_builtin is null");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinIsNotNull() {
            addCriterion("is_builtin is not null");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinEqualTo(Boolean value) {
            addCriterion("is_builtin =", value, "isBuiltin");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinNotEqualTo(Boolean value) {
            addCriterion("is_builtin <>", value, "isBuiltin");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinGreaterThan(Boolean value) {
            addCriterion("is_builtin >", value, "isBuiltin");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_builtin >=", value, "isBuiltin");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinLessThan(Boolean value) {
            addCriterion("is_builtin <", value, "isBuiltin");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinLessThanOrEqualTo(Boolean value) {
            addCriterion("is_builtin <=", value, "isBuiltin");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinIn(List<Boolean> values) {
            addCriterion("is_builtin in", values, "isBuiltin");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinNotIn(List<Boolean> values) {
            addCriterion("is_builtin not in", values, "isBuiltin");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinBetween(Boolean value1, Boolean value2) {
            addCriterion("is_builtin between", value1, value2, "isBuiltin");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_builtin not between", value1, value2, "isBuiltin");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinDataIsNull() {
            addCriterion("is_builtin_data is null");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinDataIsNotNull() {
            addCriterion("is_builtin_data is not null");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinDataEqualTo(Boolean value) {
            addCriterion("is_builtin_data =", value, "isBuiltinData");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinDataNotEqualTo(Boolean value) {
            addCriterion("is_builtin_data <>", value, "isBuiltinData");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinDataGreaterThan(Boolean value) {
            addCriterion("is_builtin_data >", value, "isBuiltinData");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinDataGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_builtin_data >=", value, "isBuiltinData");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinDataLessThan(Boolean value) {
            addCriterion("is_builtin_data <", value, "isBuiltinData");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinDataLessThanOrEqualTo(Boolean value) {
            addCriterion("is_builtin_data <=", value, "isBuiltinData");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinDataIn(List<Boolean> values) {
            addCriterion("is_builtin_data in", values, "isBuiltinData");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinDataNotIn(List<Boolean> values) {
            addCriterion("is_builtin_data not in", values, "isBuiltinData");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinDataBetween(Boolean value1, Boolean value2) {
            addCriterion("is_builtin_data between", value1, value2, "isBuiltinData");
            return (Criteria) this;
        }

        public Criteria andIsBuiltinDataNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_builtin_data not between", value1, value2, "isBuiltinData");
            return (Criteria) this;
        }

        public Criteria andDeleteModeIsNull() {
            addCriterion("delete_mode is null");
            return (Criteria) this;
        }

        public Criteria andDeleteModeIsNotNull() {
            addCriterion("delete_mode is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteModeEqualTo(String value) {
            addCriterion("delete_mode =", value, "deleteMode");
            return (Criteria) this;
        }

        public Criteria andDeleteModeNotEqualTo(String value) {
            addCriterion("delete_mode <>", value, "deleteMode");
            return (Criteria) this;
        }

        public Criteria andDeleteModeGreaterThan(String value) {
            addCriterion("delete_mode >", value, "deleteMode");
            return (Criteria) this;
        }

        public Criteria andDeleteModeGreaterThanOrEqualTo(String value) {
            addCriterion("delete_mode >=", value, "deleteMode");
            return (Criteria) this;
        }

        public Criteria andDeleteModeLessThan(String value) {
            addCriterion("delete_mode <", value, "deleteMode");
            return (Criteria) this;
        }

        public Criteria andDeleteModeLessThanOrEqualTo(String value) {
            addCriterion("delete_mode <=", value, "deleteMode");
            return (Criteria) this;
        }

        public Criteria andDeleteModeLike(String value) {
            addCriterion("delete_mode like", value, "deleteMode");
            return (Criteria) this;
        }

        public Criteria andDeleteModeNotLike(String value) {
            addCriterion("delete_mode not like", value, "deleteMode");
            return (Criteria) this;
        }

        public Criteria andDeleteModeIn(List<String> values) {
            addCriterion("delete_mode in", values, "deleteMode");
            return (Criteria) this;
        }

        public Criteria andDeleteModeNotIn(List<String> values) {
            addCriterion("delete_mode not in", values, "deleteMode");
            return (Criteria) this;
        }

        public Criteria andDeleteModeBetween(String value1, String value2) {
            addCriterion("delete_mode between", value1, value2, "deleteMode");
            return (Criteria) this;
        }

        public Criteria andDeleteModeNotBetween(String value1, String value2) {
            addCriterion("delete_mode not between", value1, value2, "deleteMode");
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