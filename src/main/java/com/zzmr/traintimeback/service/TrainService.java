package com.zzmr.traintimeback.service;

import com.zzmr.traintimeback.entity.Train;
import com.zzmr.traintimeback.vo.StationTrainVo;
import com.zzmr.traintimeback.vo.TrainVo;

import java.util.List;

/**
 * @author zzmr
 * @create 2023-10-14 19:39
 */
public interface TrainService {
    Train getByNumber(String trainNumber);

    /**
     * 查询存在此区间的所有列车
     * @param start
     * @param end
     * @return
     */
    List<TrainVo> getTrainsByStations(String start, String end,String type);

    /**
     * 根据车站名称查询经过该车站的列车
     * @param station
     * @return
     */
    List<StationTrainVo> getTrainsByStation(String station,String type);
}
