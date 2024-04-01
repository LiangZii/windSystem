package com.iot.mywind.service.impl;

import com.iot.mywind.controller.State;
import com.iot.mywind.entity.BasicConsumeStatistic;
import com.iot.mywind.exception.BusinessException;
import com.iot.mywind.mapper.BasicConsumeStatisticMapper;
import com.iot.mywind.service.BasicConsumeStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicConsumeStatisticServiceImpl implements BasicConsumeStatisticService {

    @Autowired
    private BasicConsumeStatisticMapper basicConsumeMapper;


    @Override
    public List<BasicConsumeStatistic> selectAll() {
        List<BasicConsumeStatistic> result = basicConsumeMapper.selectAll();

        if(result == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return result;
    }
}
