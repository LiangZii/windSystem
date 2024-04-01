package com.iot.mywind.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iot.mywind.entity.BasicData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface BasicDataMapper extends BaseMapper<BasicData> {

    @Select("select basicdata.wId,uid,manufacturer,modle,date,time,state,unitEnergy from basicdata,windfieldinfo where basicdata.wId=windfieldinfo.wId and windfieldinfo.wname = #{wname}")
    List<BasicData> selectByName(String wname);
}
