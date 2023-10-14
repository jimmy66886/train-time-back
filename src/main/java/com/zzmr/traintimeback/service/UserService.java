package com.zzmr.traintimeback.service;

import com.zzmr.traintimeback.entity.User;
import com.zzmr.traintimeback.vo.UserVo;

import java.util.List;

/**
 * @author zzmr
 * @create 2023-10-14 8:21
 */
public interface UserService {

    /**
     * 用户注册
     *
     * @param user
     */
    void insert(User user);

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    UserVo login(User user);
}
