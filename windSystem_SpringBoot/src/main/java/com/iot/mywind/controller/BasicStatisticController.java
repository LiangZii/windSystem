package com.iot.mywind.controller;

import com.iot.mywind.entity.BasicStatistic;
import com.iot.mywind.service.BasicStatisticService;
import com.iot.mywind.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/basicstatistics")
public class BasicStatisticController {
    @Autowired
    private BasicStatisticService basicStatisticService;

    @GetMapping
    public JsonResult<List<BasicStatistic>> selectAll(){
        List<BasicStatistic> result = basicStatisticService.selectAll();
        return new JsonResult<>(State.OK,result,"查询成功");
    }
}
