package com.iot.mywind.service;

import com.iot.mywind.entity.Maintenance;

import java.util.List;

public interface MaintenanceService {

    //    添加记录
    boolean save(Maintenance maintenance);

    //    删除记录
    boolean delete(String mid);

    //    修改记录
    boolean update(Maintenance maintenance);

    //    按维修员搜索
    List<Maintenance> selectByName(String repairman);

    //    查询全部
    List<Maintenance> selectAll();


}
