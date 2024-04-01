package com.iot.mywind.service;


import com.iot.mywind.entity.RightManage;

import java.util.List;

public interface RightManageService {

    void reg(RightManage rightManage);

    RightManage login(String id,String password);

    //    查询全部
    List<RightManage> selectAll();

    //    添加记录
    boolean save(RightManage rightManage);

    //    删除记录
    boolean delete(String id);

    //    修改记录
    boolean update(RightManage rightManage);

    //    根据id查询
    RightManage selectById(String id);
}
