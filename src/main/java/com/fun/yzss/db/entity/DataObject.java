package com.fun.yzss.db.entity;

import com.fun.yzss.util.ObjectConvert;

import java.lang.reflect.Field;

/**
 * Created by fanqq on 2016/9/23.
 */
public abstract class DataObject {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Object getByName(String name) throws Exception {
        Field field = this.getClass().getDeclaredField(name);
        field.setAccessible(true);
        return field.get(this);
    }

    public void setByName(String name, Object obj) throws Exception {
        Field field = this.getClass().getDeclaredField(name);
        field.setAccessible(true);
        field.set(this, ObjectConvert.convert(obj, field.getType()));
    }
}
