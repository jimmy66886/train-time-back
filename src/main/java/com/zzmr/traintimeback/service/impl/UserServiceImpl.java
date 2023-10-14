package com.zzmr.traintimeback.service.impl;

import com.zzmr.traintimeback.entity.User;
import com.zzmr.traintimeback.exception.BaseException;
import com.zzmr.traintimeback.mapper.UserMapper;
import com.zzmr.traintimeback.service.UserService;
import com.zzmr.traintimeback.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author zzmr
 * @create 2023-10-14 8:21
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
    public void insert(User user) {
        // 插入创建时间
        user.setCreateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Override
    public UserVo login(User user) {
        String account = user.getAccount();
        User dataUser = userMapper.getByAccount(account);
        if (dataUser != null) {
            // 存在该用户
            if (dataUser.getPassword().equals(user.getPassword())) {
                // 密码正确
                return UserVo.builder().id(dataUser.getId()).account(dataUser.getAccount()).build();
            } else {
                throw new BaseException("账户或密码错误");
            }
        } else {
            throw new BaseException("账户不存在");
        }
    }
}
