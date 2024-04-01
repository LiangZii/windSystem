package com.iot.mywind.controller;

import com.iot.mywind.entity.NewPartsManage;
import com.iot.mywind.service.NewPartsManageService;
import com.iot.mywind.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/newpartsmanages")
@CrossOrigin
public class NewPartsManageController {
    @Autowired
    private NewPartsManageService newPartsManageService;

    @PostMapping
    public JsonResult<Void> save(@RequestBody NewPartsManage newPartsManage){
        boolean flag = newPartsManageService.save(newPartsManage);

        return new JsonResult<>(State.OK,"添加成功");
    }

    @PutMapping
    public JsonResult<Void> update(@RequestBody NewPartsManage newPartsManage){
        boolean flag = newPartsManageService.update(newPartsManage);

        return new JsonResult<>(State.OK,"修改成功");
    }

    @DeleteMapping("/{nid}")
    public JsonResult<Void> delete(@PathVariable String nid){
        boolean flag = newPartsManageService.delete(nid);
        return new JsonResult<>(State.OK,"删除成功");
    }

    @GetMapping("/{nid}")
    public JsonResult<NewPartsManage> selectById(@PathVariable String nid) {

        NewPartsManage result = newPartsManageService.selectById(nid);
        return new JsonResult<>(State.OK, result, "查询成功");
    }

    @GetMapping
    public JsonResult<List<NewPartsManage>> selectAll(){
        List<NewPartsManage> result = newPartsManageService.selectAll();
        return new JsonResult<>(State.OK,result,"查询成功");
    }

}
