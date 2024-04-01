package com.iot.mywind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iot.mywind.controller.State;
import com.iot.mywind.entity.PersonManage;
import com.iot.mywind.exception.BusinessException;
import com.iot.mywind.mapper.PersonManageMapper;
import com.iot.mywind.service.PersonManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonManageServiceImpl implements PersonManageService {
    @Autowired
    private PersonManageMapper personManageMapper;


    @Override
    public List<PersonManage> selectAll() {
        LambdaQueryWrapper<PersonManage> lqw = new LambdaQueryWrapper<>();
        List<PersonManage> result = personManageMapper.selectList(lqw);

        if(result == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return result;
    }

    @Override
    public boolean save(PersonManage personManage) {
        int flag = personManageMapper.insert(personManage);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public boolean delete(String pid) {
        int flag = personManageMapper.deleteById(pid);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public boolean update(PersonManage personManage) {
        int flag = personManageMapper.updateById(personManage);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public List<PersonManage> selectByName(String name) {
        QueryWrapper<PersonManage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        List<PersonManage> personManages = personManageMapper.selectList(queryWrapper);
        if(personManages == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return personManages;
    }
}
