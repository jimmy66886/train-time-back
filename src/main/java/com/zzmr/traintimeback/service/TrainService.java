package com.zzmr.traintimeback.service;

import com.zzmr.traintimeback.entity.Train;
import com.zzmr.traintimeback.vo.NumberTrainVo;
import com.zzmr.traintimeback.vo.StationTrainVo;
import com.zzmr.traintimeback.vo.TrainVo;

import java.util.List;

/**
 * @author zzmr
 * @create 2023-10-14 19:39
 */
public interface TrainService {
    NumberTrainVo getByNumber(String trainNumber);

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

    /**
     * 获取所有的列车
     * @return
     */
    List<Train> getAllTrains();

    /**
     * 根据id获取列车信息
     * @param id
     * @return
     */
    Train getTrainById(Long id);

    /**
     * 更新列车信息
     * @param train
     */
    void update(Train train);

    /**
     * 根据车次模糊搜索
     * @param trainNumber
     * @return
     */
    List<Train> getByNumberLike(String trainNumber);

    /**
     * 添加列车信息
     * @param train
     */
    void add(Train train);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Long id);
}
