package com.iot.mywind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iot.mywind.controller.State;
import com.iot.mywind.entity.FaultStatistic;
import com.iot.mywind.entity.WindFieldInfo;
import com.iot.mywind.exception.BusinessException;
import com.iot.mywind.mapper.FaultStatisticMapper;
import com.iot.mywind.service.FaultStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaultStatisticServiceImpl implements FaultStatisticService {

    @Autowired
    private FaultStatisticMapper faultStatisticMapper;


    @Override
    public List<FaultStatistic> selectAll() {
        List<FaultStatistic> result = faultStatisticMapper.selectAll();

        if(result == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return result;
    }
}
