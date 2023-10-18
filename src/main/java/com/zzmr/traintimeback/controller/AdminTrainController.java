package com.zzmr.traintimeback.controller;

import com.zzmr.traintimeback.entity.Train;
import com.zzmr.traintimeback.entity.User;
import com.zzmr.traintimeback.exception.BaseException;
import com.zzmr.traintimeback.result.Result;
import com.zzmr.traintimeback.service.TrainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * @author zzmr
 * @create 2023-10-16 13:11
 */
@RestController
@Slf4j
@Api(tags = "列车管理相关接口")
@CrossOrigin
@RequestMapping("/admin/train")
public class AdminTrainController {

    @Resource
    private TrainService trainService;

    /**
     * 管理员登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public Result login(@RequestBody User user) {
        // 管理员账号设置为root 010203
        if (!"root".equals(user.getAccount()) || !"010203".equals(user.getPassword())) {
            throw new BaseException("账号或密码错误");
        }
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除列车信息")
    public Result deleteTrainById(@PathVariable Long id) {
        trainService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/getAllTrain")
    @ApiOperation("获取所有列车信息")
    public Result getAllTrain() {
        List<Train> trainList = trainService.getAllTrains();
        return Result.success(trainList);
    }

    @GetMapping("/getById/{id}")
    @ApiOperation("根据id获取列车信息")
    public Result getById(@PathVariable Long id) {
        Train train = trainService.getTrainById(id);
        return Result.success(train);
    }

    @PostMapping("/update")
    @ApiOperation("更改列车信息")
    public Result update(@RequestBody Train train) {
        trainService.update(train);
        return Result.success();
    }

    @PostMapping("/add")
    @ApiOperation("添加列车信息")
    public Result add(@RequestBody Train train) {
        trainService.add(train);
        return Result.success();
    }


}
