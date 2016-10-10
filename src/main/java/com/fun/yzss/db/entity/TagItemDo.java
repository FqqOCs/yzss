package com.fun.yzss.db.entity;

/**
 * Created by fanqq on 2016/10/10.
 */
public class TagItemDo extends DataObject {
    Long targetId;
    Long tagId;
    String type;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }
}
