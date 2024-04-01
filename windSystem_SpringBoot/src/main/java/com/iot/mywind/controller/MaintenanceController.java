package com.iot.mywind.controller;

import com.iot.mywind.entity.Maintenance;
import com.iot.mywind.service.MaintenanceService;
import com.iot.mywind.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@CrossOrigin
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @PostMapping
    public JsonResult<Void> save(@RequestBody Maintenance maintenance){
        boolean flag = maintenanceService.save(maintenance);

        return new JsonResult<>(State.OK,"添加成功");
    }

    @PutMapping
    public JsonResult<Void> update(@RequestBody Maintenance maintenance){
        boolean flag = maintenanceService.update(maintenance);

        return new JsonResult<>(State.OK,"修改成功");
    }

    @DeleteMapping("/{mid}")
    public JsonResult<Void> delete(@PathVariable String mid){
        boolean flag = maintenanceService.delete(mid);
        return new JsonResult<>(State.OK,"删除成功");
    }

    @GetMapping("/repairman")
    public JsonResult<List<Maintenance>> selectById(@RequestParam String name) {
        List<Maintenance> result = maintenanceService.selectByName(name);
        return new JsonResult<>(State.OK, result, "查询成功");
    }

    @GetMapping
    public JsonResult<List<Maintenance>> selectAll(){
        List<Maintenance> maintenances = maintenanceService.selectAll();
        return new JsonResult<>(State.OK,maintenances,"查询成功");
    }

}
