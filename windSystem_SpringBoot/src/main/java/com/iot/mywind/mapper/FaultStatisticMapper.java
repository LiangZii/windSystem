package com.iot.mywind.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iot.mywind.entity.FaultStatistic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FaultStatisticMapper extends BaseMapper<FaultStatistic> {

    @Select("SELECT * FROM viewfault1")
    List<FaultStatistic> selectAll();

}
