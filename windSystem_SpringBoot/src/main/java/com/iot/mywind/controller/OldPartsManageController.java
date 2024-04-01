package com.iot.mywind.controller;

import com.iot.mywind.entity.OldPartsManage;
import com.iot.mywind.service.OldPartsManageService;
import com.iot.mywind.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oldpartsmanages")
@CrossOrigin
public class OldPartsManageController {
    @Autowired
    private OldPartsManageService oldPartsManageService;

    @PostMapping
    public JsonResult<Void> save(@RequestBody OldPartsManage oldPartsManage){
        boolean flag = oldPartsManageService.save(oldPartsManage);

        return new JsonResult<>(State.OK,"添加成功");
    }

    @PutMapping
    public JsonResult<Void> update(@RequestBody OldPartsManage oldPartsManage){
        boolean flag = oldPartsManageService.update(oldPartsManage);

        return new JsonResult<>(State.OK,"修改成功");
    }

    @DeleteMapping("/{oid}")
    public JsonResult<Void> delete(@PathVariable String oid){
        boolean flag = oldPartsManageService.delete(oid);
        return new JsonResult<>(State.OK,"删除成功");
    }

    @GetMapping("/{oid}")
    public JsonResult<OldPartsManage> selectById(@PathVariable String oid) {

        OldPartsManage result = oldPartsManageService.selectById(oid);
        return new JsonResult<>(State.OK, result, "查询成功");
    }

    @GetMapping
    public JsonResult<List<OldPartsManage>> selectAll(){
        List<OldPartsManage> result = oldPartsManageService.selectAll();
        return new JsonResult<>(State.OK,result,"查询成功");
    }


}
