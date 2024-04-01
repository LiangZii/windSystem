package com.iot.mywind.mapper;

import com.iot.mywind.entity.BasicConsumeStatistic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BasicConsumeStatisticMapper {

    @Select("select * from viewparts3")
    List<BasicConsumeStatistic> selectAll();
}
