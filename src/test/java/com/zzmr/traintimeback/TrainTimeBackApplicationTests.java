package com.zzmr.traintimeback;

import com.zzmr.traintimeback.entity.Train;
import com.zzmr.traintimeback.mapper.TrainMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class TrainTimeBackApplicationTests {

    @Resource
    private TrainMapper trainMapper;

    @DisplayName("测试train表")
    @Test
    void contextLoads() {
        List<Train> trainList = trainMapper.test();
        // 得到途径车站
        for (Train train : trainList) {
            String routeSite = train.getRouteSite();
            // 获取途径站
            String[] routes = routeSite.split(",");
            for (String route : routes) {
                System.out.println(route);
            }
            System.out.println("====");
        }
    }

}
