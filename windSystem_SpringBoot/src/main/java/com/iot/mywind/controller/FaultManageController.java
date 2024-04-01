package com.iot.mywind.controller;

import com.iot.mywind.entity.FaultManage;
import com.iot.mywind.service.FaultManageService;
import com.iot.mywind.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faultmanages")
@CrossOrigin
public class FaultManageController {

    @Autowired
    private FaultManageService faultManageService;

    @PostMapping
    public JsonResult<Void> save(@RequestBody FaultManage faultManage){
        boolean flag = faultManageService.save(faultManage);

        return new JsonResult<>(State.OK,"添加成功");
    }

    @PutMapping
    public JsonResult<Void> update(@RequestBody FaultManage faultManage){
        boolean flag = faultManageService.update(faultManage);

        return new JsonResult<>(State.OK,"修改成功");
    }

    @DeleteMapping("/{fid}")
    public JsonResult<Void> delete(@PathVariable String fid){
        boolean flag = faultManageService.delete(fid);
        return new JsonResult<>(State.OK,"删除成功");
    }

    @GetMapping("/select")
    public JsonResult<List<FaultManage>> selectById(@RequestParam(value = "id",required = false) String uid, @RequestParam(value = "name",required = false) String wname){
        System.out.println(uid);
        System.out.println(wname);
        List<FaultManage> faultManages = faultManageService.selectByParam(wname, uid);
        return new JsonResult<>(State.OK,faultManages,"查询成功");
    }


    @GetMapping
    public JsonResult<List<FaultManage>> selectAll(){
        List<FaultManage> faultManages = faultManageService.selectAll();
        return new JsonResult<>(State.OK,faultManages,"查询成功");
    }



}
