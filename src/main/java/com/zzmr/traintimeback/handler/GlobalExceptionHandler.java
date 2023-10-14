package com.zzmr.traintimeback.handler;

/**
 * @author zzmr
 * @create 2023-10-14 8:53
 */

import com.zzmr.traintimeback.exception.BaseException;
import com.zzmr.traintimeback.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.sql.SQLIntegrityConstraintViolationException;


@RestControllerAdvice
@Slf4j
@CrossOrigin
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex) {
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    // 特定异常处理-唯一键约束
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        // Duplicate entry 'admin' for key 'employee.idx_username'
        String message = ex.getMessage();
        if (message.contains("Duplicate entry")) {
            String[] messageArr = message.split(" ");
            String repeatName = messageArr[2];
            // return Result.error(repeatName + "该用户名已存");
            log.info("唯一键问题: {}", repeatName);
            return Result.error(repeatName + "该手机号已被注册");
        } else {
            return Result.error("未知异常");
        }
    }

}
