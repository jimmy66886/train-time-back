package com.zzmr.traintimeback.exception;

/**
 * @author zzmr
 * @create 2023-10-14 9:03
 */
/**
 * 业务异常
 */
public class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(String msg) {
        super(msg);
    }

}
