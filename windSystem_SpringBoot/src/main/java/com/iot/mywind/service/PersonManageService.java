package com.iot.mywind.service;

import com.iot.mywind.entity.PersonManage;

import java.util.List;

public interface PersonManageService {
    //    查询全部
    List<PersonManage> selectAll();

    //    按姓名查询
    List<PersonManage> selectByName(String name);

    //    添加记录
    boolean save(PersonManage personManage);

    //    删除记录
    boolean delete(String pid);

    //    修改记录
    boolean update(PersonManage personManage);
}
