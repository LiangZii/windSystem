package com.iot.mywind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iot.mywind.controller.State;
import com.iot.mywind.entity.BasicData;
import com.iot.mywind.exception.BusinessException;
import com.iot.mywind.mapper.BasicDataMapper;
import com.iot.mywind.service.BasicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicDataServiceImpl implements BasicDataService {

    @Autowired
    private BasicDataMapper basicDataMapper;

    @Override
    public boolean save(BasicData basicData) {
        BasicData data = basicDataMapper.selectById(basicData.getUid());
        if(data != null){
            throw new BusinessException(State.ERROR,"编号(主键)已存在");
        }
        int flag = basicDataMapper.insert(basicData);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public boolean delete(String uid) {
        int flag = basicDataMapper.deleteById(uid);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public boolean update(BasicData basicData) {
        int flag = basicDataMapper.updateById(basicData);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public List<BasicData> selectByName(String wname) {
        List<BasicData> basicDatas = basicDataMapper.selectByName(wname);
        if(basicDatas == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }
        
        return basicDatas;
    }

    @Override
    public List<BasicData> selectAll() {
        LambdaQueryWrapper<BasicData> lqw = new LambdaQueryWrapper<>();
        List<BasicData> basicDatas = basicDataMapper.selectList(lqw);

        if(basicDatas == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return basicDatas;
    }
}
