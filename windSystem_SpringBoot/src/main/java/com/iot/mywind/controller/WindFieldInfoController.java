package com.iot.mywind.controller;

import com.iot.mywind.entity.WindFieldInfo;
import com.iot.mywind.service.WindFieldInfoService;
import com.iot.mywind.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/windfieldinfos")
@CrossOrigin
public class WindFieldInfoController {

    @Autowired
    private WindFieldInfoService windFieldInfoService;

    @GetMapping("/{wname}")
    public JsonResult<List<WindFieldInfo>> selectByName(@PathVariable String wname){

        List<WindFieldInfo> windFieldInfos = windFieldInfoService.selectByName(wname);

        return new JsonResult<>(State.OK,windFieldInfos,"查询成功");
    }

    @GetMapping
    public JsonResult<List<WindFieldInfo>> selectAll(){
        List<WindFieldInfo> windFieldInfos = windFieldInfoService.selectAll();

        return new JsonResult<>(State.OK,windFieldInfos,"查询成功");
    }

    @PostMapping
    public JsonResult<Void> save(@RequestBody WindFieldInfo windFieldInfo){
        boolean flag = windFieldInfoService.save(windFieldInfo);

        return new JsonResult<>(State.OK,"添加成功");
    }

    @PutMapping
    public JsonResult<Void> update(@RequestBody WindFieldInfo windFieldInfo){
        boolean flag = windFieldInfoService.update(windFieldInfo);

        return new JsonResult<>(State.OK,"修改成功");
    }

    @DeleteMapping("/{wid}")
    public JsonResult<Void> delete(@PathVariable String wid){
        boolean flag = windFieldInfoService.delete(wid);
        return new JsonResult<>(State.OK,"删除成功");
    }


}
