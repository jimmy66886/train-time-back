package com.zzmr.traintimeback.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zzmr
 * @create 2023-10-14 9:27
 * 用户登录返回VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {

    private Long id;

    private String account;

    // 令牌
    private String token;

}
