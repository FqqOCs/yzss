package com.fun.yzss.db.entity;

/**
 * Created by fanqq on 2016/10/10.
 */
public class TagDo extends DataObject {
    String name;
    Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
