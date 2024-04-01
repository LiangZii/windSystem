package com.iot.mywind.controller;

import com.iot.mywind.entity.FaultStatistic;
import com.iot.mywind.service.FaultStatisticService;
import com.iot.mywind.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/faultstatistics")
public class FaultStatisticController {

    @Autowired
    private FaultStatisticService faultStatisticService;

    @GetMapping
    public JsonResult<List<FaultStatistic>> selectAll(){
        List<FaultStatistic> result = faultStatisticService.selectAll();
        return new JsonResult<>(State.OK,result,"查询成功");
    }

}
