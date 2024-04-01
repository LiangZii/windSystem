package com.iot.mywind.controller;

import com.iot.mywind.entity.BasicConsumeStatistic;
import com.iot.mywind.entity.BasicStatistic;
import com.iot.mywind.service.BasicConsumeStatisticService;
import com.iot.mywind.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/basicconsumestatistics")
public class BasicConsumeStatisticController {

    @Autowired
    private BasicConsumeStatisticService service;

    @GetMapping
    public JsonResult<List<BasicConsumeStatistic>> selectAll(){
        List<BasicConsumeStatistic> result = service.selectAll();
        return new JsonResult<>(State.OK,result,"查询成功");
    }

}
