package com.iot.mywind.service;

import com.iot.mywind.entity.OldPartsManage;

import java.util.List;

public interface OldPartsManageService {
    //    添加记录
    boolean save(OldPartsManage oldPartsManage);

    //    删除记录
    boolean delete(String oid);

    //    修改记录
    boolean update(OldPartsManage oldPartsManage);

    //    根据id查询
    OldPartsManage selectById(String oid);

    //    查询全部
    List<OldPartsManage> selectAll();
}
