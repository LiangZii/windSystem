package com.iot.mywind.controller;

import com.iot.mywind.entity.BasicData;
import com.iot.mywind.service.BasicDataService;
import com.iot.mywind.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/basicdatas")
@CrossOrigin
public class BasicDataController {

    @Autowired
    private BasicDataService basicDataService;

    @PostMapping
    public JsonResult<Void> save(@RequestBody BasicData basicData){
        boolean flag = basicDataService.save(basicData);

        return new JsonResult<>(State.OK,"添加成功");
    }

    @PutMapping
    public JsonResult<Void> update(@RequestBody BasicData basicData){
        boolean flag = basicDataService.update(basicData);

        return new JsonResult<>(State.OK,"修改成功");
    }

    @DeleteMapping("/{uid}")
    public JsonResult<Void> delete(@PathVariable String uid){
        boolean flag = basicDataService.delete(uid);
        return new JsonResult<>(State.OK,"删除成功");
    }

    @GetMapping("/{wname}")
    public JsonResult<List<BasicData>> selectByName(@PathVariable String wname) {
        List<BasicData> result = basicDataService.selectByName(wname);
        return new JsonResult<>(State.OK, result, "查询成功");
    }

    @GetMapping
    public JsonResult<List<BasicData>> selectAll() {
        List<BasicData> result = basicDataService.selectAll();

        return new JsonResult<>(State.OK, result, "查询成功");
    }

}
