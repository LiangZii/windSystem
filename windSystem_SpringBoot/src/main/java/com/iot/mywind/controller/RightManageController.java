package com.iot.mywind.controller;

import com.iot.mywind.entity.RightManage;
import com.iot.mywind.service.RightManageService;
import com.iot.mywind.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rightmanages")
@CrossOrigin
public class RightManageController {
    @Autowired
    private RightManageService rightManageService;

    @PostMapping("/reg")
    public JsonResult<Void> reg(@RequestBody RightManage rightManage){
        rightManageService.reg(rightManage);

        return new JsonResult<>(State.OK,"注册成功");
    }

    @PostMapping("/login")
    public JsonResult<Void> login(@RequestBody RightManage rightManage){
        RightManage result = rightManageService.login(rightManage.getId(), rightManage.getPassword());
        return new JsonResult<>(State.OK,"登录成功：" + result.getName());
    }

    @PostMapping
    public JsonResult<Void> save(@RequestBody RightManage rightManage){
        boolean flag = rightManageService.save(rightManage);

        return new JsonResult<>(State.OK,"添加成功");
    }

    @PutMapping
    public JsonResult<Void> update(@RequestBody RightManage rightManage){
        boolean flag = rightManageService.update(rightManage);

        return new JsonResult<>(State.OK,"修改成功");
    }

    @DeleteMapping("/{uid}")
    public JsonResult<Void> delete(@PathVariable String uid){
        boolean flag = rightManageService.delete(uid);
        return new JsonResult<>(State.OK,"删除成功");
    }

    @GetMapping("/{id}")
    public JsonResult<RightManage> selectById(@PathVariable String id) {

        RightManage result = rightManageService.selectById(id);
        return new JsonResult<>(State.OK, result, "查询成功");
    }
    @GetMapping
    public JsonResult<List<RightManage>> selectAll() {
        List<RightManage> result = rightManageService.selectAll();

        return new JsonResult<>(State.OK, result, "查询成功");
    }

}
