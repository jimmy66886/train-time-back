package com.zzmr.traintimeback.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zzmr
 * @create 2023-10-17 15:04
 */
@Component
public class B {

    @Autowired
    private A a;

}