package com.iot.mywind.service;

import com.iot.mywind.entity.WindFieldInfo;

import java.util.List;

public interface WindFieldInfoService {

//    按风场名称查询
    List<WindFieldInfo> selectByName(String wname);

//    查询全部风场
    List<WindFieldInfo> selectAll();

    //    添加记录
    boolean save(WindFieldInfo windFieldInfo);

    //    删除记录
    boolean delete(String wId);

    //    修改记录
    boolean update(WindFieldInfo windFieldInfo);

}
