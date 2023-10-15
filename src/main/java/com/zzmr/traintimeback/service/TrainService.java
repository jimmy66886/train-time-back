package com.zzmr.traintimeback.service;

import com.zzmr.traintimeback.entity.Train;

/**
 * @author zzmr
 * @create 2023-10-14 19:39
 */
public interface TrainService {
    Train getByNumber(String trainNumber);
}
