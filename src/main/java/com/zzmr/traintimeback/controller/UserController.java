
package com.zzmr.traintimeback.controller;

import com.zzmr.traintimeback.constant.JwtClaimsConstant;
import com.zzmr.traintimeback.entity.User;

import com.zzmr.traintimeback.properties.JwtProperties;
import com.zzmr.traintimeback.result.Result;
import com.zzmr.traintimeback.service.UserService;
import com.zzmr.traintimeback.utils.JwtUtil;
import com.zzmr.traintimeback.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * @author zzmr
 * @create 2023-10-14 7:50
 */
@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = "用户相关接口")
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @GetMapping("/testToken")
    public Result testToken() {
        return Result.success("test");
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        UserVo userVo = userService.login(user);

        // 登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, userVo.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        System.out.println("token为：" + token);
        userVo.setToken(token);
        return Result.success(userVo);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        userService.insert(user);
        return Result.success();
    }

}
