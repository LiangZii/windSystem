package com.iot.mywind.controller;

import com.iot.mywind.entity.PersonManage;
import com.iot.mywind.service.PersonManageService;
import com.iot.mywind.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personmanages")
@CrossOrigin
public class PersonManageController {

    @Autowired
    private PersonManageService personManageService;

    @GetMapping
    public JsonResult<List<PersonManage>> selectAll(){

        List<PersonManage> personManages = personManageService.selectAll();
        return new JsonResult<>(State.OK,personManages,"查询成功");
    }

    @PostMapping
    public JsonResult<Void> save(@RequestBody PersonManage personManage){
        boolean flag = personManageService.save(personManage);

        return new JsonResult<>(State.OK,"添加成功");
    }

    @PutMapping
    public JsonResult<Void> update(@RequestBody PersonManage personManage){
        boolean flag = personManageService.update(personManage);

        return new JsonResult<>(State.OK,"修改成功");
    }

    @DeleteMapping("/{pid}")
    public JsonResult<Void> delete(@PathVariable String pid){
        boolean flag = personManageService.delete(pid);
        return new JsonResult<>(State.OK,"删除成功");
    }

    @GetMapping("/{name}")
    public JsonResult<List<PersonManage>> selectByName(@PathVariable String name) {

        List<PersonManage> result = personManageService.selectByName(name);
        return new JsonResult<>(State.OK, result, "查询成功");
    }
}
