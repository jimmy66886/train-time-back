package com.zzmr.traintimeback.service.impl;

import com.zzmr.traintimeback.entity.Train;
import com.zzmr.traintimeback.mapper.TrainMapper;
import com.zzmr.traintimeback.service.TrainService;
import com.zzmr.traintimeback.vo.TrainVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * 查询存在此区间的所有列车
     *
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<TrainVo> getTrainsByStations(String start, String end) {

        List<Train> allTrains = trainMapper.getAllTrains();
        List<TrainVo> trainVos = new ArrayList<>();

        // 对每个列车进行操作
        for (Train train : allTrains) {
            String routeSite = train.getRouteSite();
            // 得到该列车行车区间内的所有车站，并转换为List集合
            List<String> siteList = Arrays.asList(routeSite.split(","));
            // 先判断start和end是否都存在于区间内
            if (siteList.contains(start) && siteList.contains(end)) {
                // 都包含，判断start的下标是否小于end，只有小于end那么这列车才符合要求
                int idxStart = siteList.indexOf(start);
                int idxEnd = siteList.indexOf(end);
                if (idxStart < idxEnd) {
                    TrainVo trainVo = new TrainVo();
                    // 符合条件,拷贝对象，填充字段
                    BeanUtils.copyProperties(train, trainVo);
                    trainVo.setStartStation(start);
                    trainVo.setArrivalStation(end);

                    comTime(train, idxStart, idxEnd, trainVo);

                    // 将对象存到集合中
                    trainVos.add(trainVo);

                }
            }
        }
        return trainVos;
    }

    private static void comTime(Train train, int idxStart, int idxEnd, TrainVo trainVo) {

        String t1 = train.getRouteTimeA().split(",")[idxStart];
        String t2 = train.getRouteTimeA().split(",")[idxEnd];

        if (t1.equals("---")) {
            // 则表示出发站是始发站 将始发时间赋值给出发时间
            t1 = train.getDepartureTime().toString();
            trainVo.setStartTime(train.getDepartureTime());
            // 获取t1对应的出发时间
            // 判断终点站
            if (t2.equals("---")) {
                t2 = train.getRouteTimeA().toString();
                trainVo.setEndTime(train.getArrivalTime());
            } else {
                trainVo.setEndTime(dateFormat(t2));
            }
        } else {
            trainVo.setStartTime(dateFormat(t1));
        }

        // 经过上面的过滤，已经没有---了吧
        LocalTime tm1 = dateFormat(t1);
        LocalTime tm2 = dateFormat(t2);
        Duration between = Duration.between(tm1, tm2);
        long minutes = between.toMinutes();
        StringBuilder tmpTime = new StringBuilder();
        if (minutes % 60 > 0) {
            tmpTime.append("1");
            minutes %= 60;
        }
        trainVo.setTmpTime(tmpTime + "小时" + minutes + "分钟");
    }

    // 根据传入的日期字符串得到日期格式的数据
    private static LocalTime dateFormat(String t1) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); // 指定时间格式
        LocalTime localTime = LocalTime.parse(t1, formatter); // 使用parse方法进行转换
        return localTime;
    }
}
