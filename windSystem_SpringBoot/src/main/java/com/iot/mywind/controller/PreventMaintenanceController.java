package com.iot.mywind.controller;

import com.iot.mywind.entity.PreventMaintenance;
import com.iot.mywind.service.PreventMaintenanceService;
import com.iot.mywind.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preventmaintenances")
@CrossOrigin
public class PreventMaintenanceController {

    @Autowired
    private PreventMaintenanceService preventMaintenanceService;

    @GetMapping
    public JsonResult<List<PreventMaintenance>> selectAll(){

        List<PreventMaintenance> maintenances = preventMaintenanceService.selectAll();
        return new JsonResult<>(State.OK,maintenances,"查询成功");
    }

    @PostMapping
    public JsonResult<Void> save(@RequestBody PreventMaintenance preventMaintenance){
        boolean flag = preventMaintenanceService.save(preventMaintenance);

        return new JsonResult<>(State.OK,"添加成功");
    }

    @PutMapping
    public JsonResult<Void> update(@RequestBody PreventMaintenance preventMaintenance){
        boolean flag = preventMaintenanceService.update(preventMaintenance);

        return new JsonResult<>(State.OK,"修改成功");
    }

    @DeleteMapping("/{id}")
    public JsonResult<Void> delete(@PathVariable String id){
        boolean flag = preventMaintenanceService.delete(id);
        return new JsonResult<>(State.OK,"删除成功");
    }

    @GetMapping("/{uid}")
    public JsonResult<List<PreventMaintenance>> selectByUid(@PathVariable String uid) {

        List<PreventMaintenance> result = preventMaintenanceService.selectByUid(uid);
        return new JsonResult<>(State.OK, result, "查询成功");
    }

}
