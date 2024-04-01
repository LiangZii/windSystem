package com.iot.mywind.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iot.mywind.entity.FaultManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FaultManageMapper extends BaseMapper<FaultManage> {

    @Select("SELECT * from faultmanage,basicdata,windfieldinfo WHERE basicdata.wId = windfieldinfo.wId AND basicdata.uid = faultmanage.uid AND faultmanage.uid = #{fid} AND windfieldinfo.wname LIKE #{wname}")
    List<FaultManage> selectAllByNameAndId(String wname,String fid);

    @Select("SELECT * from faultmanage WHERE uid IN (SELECT uid FROM basicdata,windfieldinfo WHERE basicdata.wId = windfieldinfo.wId AND windfieldinfo.wname LIKE #{wname})")
    List<FaultManage> selectAllByName(String wname);
}
