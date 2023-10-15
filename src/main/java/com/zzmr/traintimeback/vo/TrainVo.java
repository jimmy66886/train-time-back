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

    // 出发站 -- 相当于用户输入的第一个车站
    private String startStation;

    // 到达站 -- 相当于用户输入的第二个车站
    private String arrivalStation;

    // 发车时间
    private LocalTime departureTime;

    // 到达时间
    private LocalTime arrivalTime;

    private String costTime;

}
