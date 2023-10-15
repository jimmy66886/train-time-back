package com.zzmr.traintimeback.controller;

import com.zzmr.traintimeback.entity.Train;
import com.zzmr.traintimeback.result.Result;
import com.zzmr.traintimeback.service.TrainService;
import com.zzmr.traintimeback.vo.TrainVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zzmr
 * @create 2023-10-14 19:38
 */
@RestController
@Slf4j
@Log4j
@Api(tags = "列车相关接口")
@CrossOrigin
@RequestMapping("/train")
public class TrainController {

    @Resource
    private TrainService trainService;

    /**
     * 车次查询，返回的是一列列车的基本信息
     *
     * @param trainNumber
     * @return
     */
    @ApiOperation("根据车次查询列车信息")
    @GetMapping("/getByNumber/{trainNumber}")
    public Result getTrainByNumber(@PathVariable String trainNumber) {
        Train train = trainService.getByNumber(trainNumber);
        return Result.success(train);
    }

    /**
     * 站站查询-通过query参数传递
     *
     * @param start 用户输入的第一个车站
     * @param end   用户输入的第二个车站
     * @return
     */
    @ApiOperation("站站查询")
    @GetMapping("/getByStations")
    public Result getTrainsByStations(String start, String end) {
        List<TrainVo> trainVoList = trainService.getTrainsByStations(start, end);
        return Result.success(trainVoList);
    }

}
