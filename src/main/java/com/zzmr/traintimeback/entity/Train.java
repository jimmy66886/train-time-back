package com.zzmr.traintimeback.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * @author zzmr
 * @create 2023-10-14 19:25
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Train implements Serializable {

    private Long id;

    private String trainNumber;

    private String type;

    private String departureStation;

    private String terminal;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    private String costTime;

    // 途径车站字符串
    private String routeSite;

    // 到达时间字符串
    private String routeTimeA;

    // 发车时间字符串
    private String routeTimeE;

}
