package com.iot.mywind.service;

import com.iot.mywind.entity.BasicData;

import java.util.List;

public interface BasicDataService {

//    添加记录
    boolean save(BasicData basicData);

//    删除记录
    boolean delete(String uid);

//    修改记录
    boolean update(BasicData basicData);

//    根据id查询
    List<BasicData> selectByName(String wname);

//    查询全部
    List<BasicData> selectAll();

}
