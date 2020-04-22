package com.its.itone.sas.config.dao.pojo;

import java.util.Date;

public class SasDimension {
    private String id;

    private String type;

    private String application;

    private String domain;

    private String code;

    private String name;

    private String displayName;

    private String finger;

    private String status;

    private String modifyUser;

    private Date modifyTime;

    private String parent;

    private Integer treeId;

    private Object idPath;

    private String namePath;

    private Object subs;

    private Object attributes;

    private String cSearch;

    private String tag;

    private Integer index;

    private Integer oldId;

    private String createUser;

    private Date createTime;

    private String dimensionObject;

    private String dimensionAttribute;

    private String primaryKey;

    private String displayAttribute;

    private String dataSource;

    private String dimensionType;

    private String dimensionConfig;

    private String describe;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application == null ? null : application.trim();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    public String getFinger() {
        return finger;
    }

    public void setFinger(String finger) {
        this.finger = finger == null ? null : finger.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser == null ? null : modifyUser.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent == null ? null : parent.trim();
    }

    public Integer getTreeId() {
        return treeId;
    }

    public void setTreeId(Integer treeId) {
        this.treeId = treeId;
    }

    public Object getIdPath() {
        return idPath;
    }

    public void setIdPath(Object idPath) {
        this.idPath = idPath;
    }

    public String getNamePath() {
        return namePath;
    }

    public void setNamePath(String namePath) {
        this.namePath = namePath == null ? null : namePath.trim();
    }

    public Object getSubs() {
        return subs;
    }

    public void setSubs(Object subs) {
        this.subs = subs;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public String getcSearch() {
        return cSearch;
    }

    public void setcSearch(String cSearch) {
        this.cSearch = cSearch == null ? null : cSearch.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getOldId() {
        return oldId;
    }

    public void setOldId(Integer oldId) {
        this.oldId = oldId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDimensionObject() {
        return dimensionObject;
    }

    public void setDimensionObject(String dimensionObject) {
        this.dimensionObject = dimensionObject == null ? null : dimensionObject.trim();
    }

    public String getDimensionAttribute() {
        return dimensionAttribute;
    }

    public void setDimensionAttribute(String dimensionAttribute) {
        this.dimensionAttribute = dimensionAttribute == null ? null : dimensionAttribute.trim();
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey == null ? null : primaryKey.trim();
    }

    public String getDisplayAttribute() {
        return displayAttribute;
    }

    public void setDisplayAttribute(String displayAttribute) {
        this.displayAttribute = displayAttribute == null ? null : displayAttribute.trim();
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    public String getDimensionType() {
        return dimensionType;
    }

    public void setDimensionType(String dimensionType) {
        this.dimensionType = dimensionType == null ? null : dimensionType.trim();
    }

    public String getDimensionConfig() {
        return dimensionConfig;
    }

    public void setDimensionConfig(String dimensionConfig) {
        this.dimensionConfig = dimensionConfig == null ? null : dimensionConfig.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }
}