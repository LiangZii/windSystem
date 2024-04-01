package com.iot.mywind.service.impl;

import com.iot.mywind.controller.State;
import com.iot.mywind.entity.BasicStatistic;
import com.iot.mywind.exception.BusinessException;
import com.iot.mywind.mapper.BasicStatisticMapper;
import com.iot.mywind.service.BasicStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicStatisticServiceImpl implements BasicStatisticService {

    @Autowired
    private BasicStatisticMapper basicStatisticMapper;

    @Override
    public List<BasicStatistic> selectAll() {
        List<BasicStatistic> result = basicStatisticMapper.selectAll();

        if(result == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return result;
    }
}
