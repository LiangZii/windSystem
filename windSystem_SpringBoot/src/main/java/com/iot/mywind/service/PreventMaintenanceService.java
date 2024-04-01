package com.iot.mywind.service;

import com.iot.mywind.entity.PreventMaintenance;

import java.util.List;

public interface PreventMaintenanceService {

//    查询全部
    List<PreventMaintenance> selectAll();

    //    按风机编号uid查询
    List<PreventMaintenance> selectByUid(String wname);

    //    添加记录
    boolean save(PreventMaintenance preventMaintenance);

    //    删除记录
    boolean delete(String id);

    //    修改记录
    boolean update(PreventMaintenance preventMaintenance);

}
