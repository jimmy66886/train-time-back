package com.zzmr.traintimeback.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zzmr
 * @create 2023-10-14 7:58
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User implements Serializable {

    private Long id;

    private String account;

    private String password;

    private LocalDateTime createTime;

}
