package com.zzmr.traintimeback.vo;

import com.zzmr.traintimeback.entity.Train;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * @author zzmr
 * @create 2023-10-18 10:53
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NumberTrainVo {
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

    // 停靠时间
    private String dockingTimes;

    // 发车时间字符串
    private String routeTimeE;
}
