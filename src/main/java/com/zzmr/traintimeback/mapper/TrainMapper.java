package com.zzmr.traintimeback.mapper;

import com.zzmr.traintimeback.entity.Train;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zzmr
 * @create 2023-10-14 19:28
 */
@Mapper
public interface TrainMapper {

    @Select("select * from train;")
    public List<Train> getAllTrains();

    @Select("select * from train where type=#{type};")
    public List<Train> getAllTrainsByType(String type);

    @Select("select * from train where train_number = #{trainNumber};")
    Train getByNumber(String trainNumber);

    @Select("select * from train where id = #{id};")
    Train getById(Long id);

    @Update("update train set train_number = #{trainNumber},type=#{type},departure_station=#{departureStation}," +
            "terminal=#{terminal},departure_time=#{departureTime},arrival_time=#{arrivalTime},cost_time=#{costTime}," +
            "route_site=#{routeSite},route_time_a=#{routeTimeA},route_time_e=#{routeTimeE} where id = #{id}")
    void update(Train train);

    List<Train> getByNumberLike(String trainNumber);

    void insert(Train train);

    @Delete("delete from train where id = #{id}")
    void removeById(Long id);
}
