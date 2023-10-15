package com.zzmr.traintimeback.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * @author zzmr
 * @create 2023-10-14 20:35
 * 用于站站查询的VO
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TrainVo implements Serializable {

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

    // 出发站 -- 相当于用户输入的第一个车站
    private String startStation;

    // 到达站 -- 相当于用户输入的第二个车站
    private String arrivalStation;

    // 此车站开车时间
    private LocalTime startTime;

    // 到达目标车站时间
    private LocalTime endTime;

    // 区间用时
    private String tmpTime;

    // 总用时
    private String costTime;

}
