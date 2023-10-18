package com.zzmr.traintimeback.service.impl;

import com.zzmr.traintimeback.entity.Train;
import com.zzmr.traintimeback.mapper.TrainMapper;
import com.zzmr.traintimeback.service.TrainService;
import com.zzmr.traintimeback.vo.NumberTrainVo;
import com.zzmr.traintimeback.vo.StationTrainVo;
import com.zzmr.traintimeback.vo.TrainVo;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
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
    public NumberTrainVo getByNumber(String trainNumber) {
        Train train = trainMapper.getByNumber(trainNumber);
        NumberTrainVo numberTrainVo = new NumberTrainVo();
        BeanUtils.copyProperties(train, numberTrainVo);


        // 封装dockingTime - 遍历整个 numberTrainVo的到达时间和发车时间
        String routeTimeA = numberTrainVo.getRouteTimeA();
        String routeTimeE = numberTrainVo.getRouteTimeE();

        List<String> routeA = Arrays.asList(routeTimeA.split(","));
        List<String> routeB = Arrays.asList(routeTimeE.split(","));

        // dockingTime字段
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < routeA.size(); i++) {
            String arrival = routeA.get(i);
            String start = routeB.get(i);

            if ("---".equals(arrival)) {
                // 这个到达时间是---，说明这个就是始发站，那么停靠时间就是---
                sb.append("---,");
                continue;
            }

            // 最后一个不需要逗号
            if ("---".equals(start)) {
                sb.append("---");
                continue;
            }

            // 不是---，说明不是始发站也不是终点站，正常计算时间
            LocalTime arrivalTime = dateFormat(arrival);
            LocalTime startTime = dateFormat(start);
            // 停靠时间就等于发车时间-到达时间

            // 得到两个时间的区间
            Duration between = Duration.between(arrivalTime, startTime);
            long minutes = between.toMinutes();
            // 由于高铁的停靠时间不会超过1小时，所以这里直接将得到的分钟数据存入sb即可
            sb.append(minutes + ",");

        }

        numberTrainVo.setDockingTimes(sb.toString());
        System.out.println(numberTrainVo);
        return numberTrainVo;
    }

    /**
     * 查询存在此区间的所有列车
     *
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<TrainVo> getTrainsByStations(String start, String end, String type) {

        List<Train> allTrains = trainMapper.getAllTrainsByType(type);
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

        // 检查结果
        System.out.println("=====");
        trainVos.forEach(System.out::println);

        return trainVos;
    }

    /**
     * 根据车站名称查询经过该车站的列车
     *
     * @param station
     * @return
     */
    @Override
    public List<StationTrainVo> getTrainsByStation(String station, String type) {

        List<StationTrainVo> stationTrainVos = new ArrayList<>();
        List<Train> trains = trainMapper.getAllTrainsByType(type);

        for (Train train : trains) {
            List<String> routeList = Arrays.asList(train.getRouteSite().split(","));
            if (routeList.contains(station)) {

                // 途径车站包含该车站
                int idx_station = routeList.indexOf(station);

                StationTrainVo stationTrainVo = new StationTrainVo();
                BeanUtils.copyProperties(train, stationTrainVo);
                // 补全字段 stationArrivalTime stationStartTime
                // 到达车站时间
                stationTrainVo.setStationArrivalTime(train.getRouteTimeA().split(",")[idx_station]);
                // 发车时间
                stationTrainVo.setStationStartTime(train.getRouteTimeE().split(",")[idx_station]);
                stationTrainVo.setStationName(station);
                stationTrainVos.add(stationTrainVo);
            }
        }
        return stationTrainVos;
    }

    /**
     * 获取所有的列车
     *
     * @return
     */
    @Override
    public List<Train> getAllTrains() {
        List<Train> allTrains = trainMapper.getAllTrains();
        return allTrains;
    }

    @Override
    public Train getTrainById(Long id) {
        return trainMapper.getById(id);
    }

    /**
     * 更新列车信息
     *
     * @param train
     */
    @Override
    public void update(Train train) {
        trainMapper.update(train);
    }

    /**
     * 车次模糊搜索
     *
     * @param trainNumber
     * @return
     */
    @Override
    public List<Train> getByNumberLike(String trainNumber) {
        List<Train> trains = trainMapper.getByNumberLike(trainNumber);
        return trains;
    }

    /**
     * 添加列车信息
     *
     * @param train
     */
    @Override
    public void add(Train train) {
        trainMapper.insert(train);
    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        trainMapper.removeById(id);
    }

    private static void comTime(Train train, int idxStart, int idxEnd, TrainVo trainVo) {

        String t1 = train.getRouteTimeE().split(",")[idxStart];
        String t2 = train.getRouteTimeA().split(",")[idxEnd];

        /**
         * departureTime---始发时间
         * getArrivalTIme---到达时间
         */

        if (t1.equals("---")) {
            // 则表示出发站是始发站 将始发时间赋值给出发时间
            t1 = train.getDepartureTime().toString();
            trainVo.setStartTime(train.getDepartureTime());
            // 判断终点站
            if (t2.equals("---")) {
                t2 = train.getArrivalTime().toString();
                trainVo.setEndTime(train.getArrivalTime());
            } else {
                trainVo.setEndTime(train.getArrivalTime());
            }
        } else {
            if (t2.equals("---")) {
                t2 = train.getArrivalTime().toString();
                trainVo.setEndTime(train.getArrivalTime());
            } else {
                trainVo.setEndTime(dateFormat(t2));
            }
            trainVo.setStartTime(dateFormat(t1));
        }

        // 经过上面的过滤，已经没有---了吧
        LocalTime tm1 = dateFormat(t1);
        LocalTime tm2 = dateFormat(t2);
        Duration between = Duration.between(tm1, tm2);
        long minutes = between.toMinutes();
        int tmpTime = 0;
        while (minutes > 60) {
            tmpTime++;
            minutes -= 60;
        }
        trainVo.setTmpTime(tmpTime + "小时" + minutes + "分钟");
        System.out.println("哈哈哈啊哈哈");
    }

    // 根据传入的日期字符串得到日期格式的数据
    private static LocalTime dateFormat(String t1) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); // 指定时间格式
        LocalTime localTime = LocalTime.parse(t1, formatter); // 使用parse方法进行转换
        return localTime;
    }
}
