package com.zzmr.traintimeback.mapper;

import com.zzmr.traintimeback.entity.Train;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zzmr
 * @create 2023-10-14 19:28
 */
@Mapper
public interface TrainMapper {

    @Select("select * from train;")
    public List<Train> test();

    @Select("select * from train where train_number = #{trainNumber};")
    Train getByNumber(String trainNumber);
}
