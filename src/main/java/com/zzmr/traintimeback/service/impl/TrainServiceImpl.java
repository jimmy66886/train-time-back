package com.zzmr.traintimeback.service.impl;

import com.zzmr.traintimeback.entity.Train;
import com.zzmr.traintimeback.mapper.TrainMapper;
import com.zzmr.traintimeback.service.TrainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zzmr
 * @create 2023-10-14 19:39
 */
@Service
public class TrainServiceImpl implements TrainService {

    @Resource
    private TrainMapper trainMapper;

    @Override
    public Train getByNumber(String trainNumber) {
        Train train = trainMapper.getByNumber(trainNumber);
        return train;
    }
}
