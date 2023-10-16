package com.zzmr.traintimeback.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * @author zzmr
 * @create 2023-10-15 23:14
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StationTrainVo {

    private Long id;

    private String trainNumber;

    private String type;

    // 始发站
    private String departureStation;

    private String terminal;

    // 始发时间
    private LocalTime departureTime;

    // 终点站到达时间
    private LocalTime arrivalTime;

    // 该车站名称
    private String stationName;

    // 到达该车站的时间
    private String stationArrivalTime;

    // 该车站的发车时间
    private String stationStartTime;

}
