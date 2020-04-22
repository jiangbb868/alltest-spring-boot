package com.its.itone.sas.config.dao.pojo;

import java.util.Date;

public class SasDimRelation {
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

    private Integer serialNo;

    private Integer dimNumber;

    private Integer dimRelationId;

    private String sourceDim;

    private String destinationDim;

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

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public Integer getDimNumber() {
        return dimNumber;
    }

    public void setDimNumber(Integer dimNumber) {
        this.dimNumber = dimNumber;
    }

    public Integer getDimRelationId() {
        return dimRelationId;
    }

    public void setDimRelationId(Integer dimRelationId) {
        this.dimRelationId = dimRelationId;
    }

    public String getSourceDim() {
        return sourceDim;
    }

    public void setSourceDim(String sourceDim) {
        this.sourceDim = sourceDim == null ? null : sourceDim.trim();
    }

    public String getDestinationDim() {
        return destinationDim;
    }

    public void setDestinationDim(String destinationDim) {
        this.destinationDim = destinationDim == null ? null : destinationDim.trim();
    }
}