package com.iot.mywind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iot.mywind.controller.State;
import com.iot.mywind.entity.WindFieldInfo;
import com.iot.mywind.exception.BusinessException;
import com.iot.mywind.mapper.WindFieldInfoMapper;
import com.iot.mywind.service.WindFieldInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WindFieldInfoServiceImpl implements WindFieldInfoService {

    @Autowired
    private WindFieldInfoMapper windFieldInfoMapper;

    @Override
    public List<WindFieldInfo> selectByName(String wname) {

        LambdaQueryWrapper<WindFieldInfo> lqw = new LambdaQueryWrapper<>();
        lqw.like(WindFieldInfo::getWname,wname);
        List<WindFieldInfo> windFieldInfos = windFieldInfoMapper.selectList(lqw);

        if(windFieldInfos == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return windFieldInfos;
    }

    @Override
    public List<WindFieldInfo> selectAll() {
        LambdaQueryWrapper<WindFieldInfo> lqw = new LambdaQueryWrapper<>();
        List<WindFieldInfo> windFieldInfos = windFieldInfoMapper.selectList(lqw);

        if(windFieldInfos == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return windFieldInfos;
    }

    @Override
    public boolean save(WindFieldInfo windFieldInfo) {
        int flag = windFieldInfoMapper.insert(windFieldInfo);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public boolean delete(String wId) {
        int flag = windFieldInfoMapper.deleteById(wId);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public boolean update(WindFieldInfo windFieldInfo) {
        int flag = windFieldInfoMapper.updateById(windFieldInfo);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }
}
