package com.iot.mywind.service;

import com.iot.mywind.entity.NewPartsManage;

import java.util.List;

public interface NewPartsManageService {

    //    添加记录
    boolean save(NewPartsManage newPartsManage);

    //    删除记录
    boolean delete(String nid);

    //    修改记录
    boolean update(NewPartsManage newPartsManage);

    //    根据id查询
    NewPartsManage selectById(String nid);

    //    查询全部
    List<NewPartsManage> selectAll();
}
