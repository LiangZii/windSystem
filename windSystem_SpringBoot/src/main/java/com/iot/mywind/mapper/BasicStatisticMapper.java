package com.iot.mywind.mapper;

import com.iot.mywind.entity.BasicStatistic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BasicStatisticMapper {

    @Select("select * from viewbasic2")
    List<BasicStatistic> selectAll();
}
