package com.fun.yzss.db.dao;

import com.fun.yzss.db.entity.UserDo;
import com.fun.yzss.exception.DalException;

import javax.annotation.Resource;

/**
 * Created by fanqq on 2016/9/23.
 */
public class UserDao {
    @Resource
    public UserDo findByName(String name) throws DalException, Exception {
        return null;
    }
}
