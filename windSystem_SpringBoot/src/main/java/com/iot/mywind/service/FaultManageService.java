package com.iot.mywind.service;

import com.iot.mywind.entity.FaultManage;

import java.util.List;

public interface FaultManageService {

    //    添加记录
    boolean save(FaultManage faultManage);

    //    删除记录
    boolean delete(String fid);

    //    修改记录
    boolean update(FaultManage faultManage);

    //    按风机编号或风场名称查询
    List<FaultManage> selectByParam(String fid,String fname);

    //    查询全部
    List<FaultManage> selectAll();


}
