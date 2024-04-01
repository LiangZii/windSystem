package com.iot.mywind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iot.mywind.controller.State;
import com.iot.mywind.entity.RightManage;
import com.iot.mywind.exception.BusinessException;
import com.iot.mywind.mapper.RightManageMapper;
import com.iot.mywind.service.RightManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RightManageServiceImpl implements RightManageService {

    @Autowired
    private RightManageMapper rightManageMapper;

    @Override
    public void reg(RightManage rightManage) {
        RightManage result = rightManageMapper.selectById(rightManage.getId());
        if(result != null){
//            用户ID已存在
            throw new BusinessException(State.ERROR,"用户名(工号)已存在");
        }

        Integer rows = rightManageMapper.insert(rightManage);
        if(rows != 1){
            throw new BusinessException(State.ERROR,"注册失败，请重试");
        }

    }

    @Override
    public RightManage login(String id, String password) {
        LambdaQueryWrapper<RightManage> lqw = new LambdaQueryWrapper<>();
        lqw.eq(RightManage::getId,id);
        lqw.eq(RightManage::getPassword,password);
        RightManage rightManage = rightManageMapper.selectOne(lqw);
        if (rightManage == null){
            throw new BusinessException(State.ERROR,"用户不存在或密码错误");
        }
        return rightManage;
    }

    @Override
    public boolean save(RightManage rightManage) {
        int flag = rightManageMapper.insert(rightManage);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public boolean delete(String uid) {
        int flag = rightManageMapper.deleteById(uid);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public boolean update(RightManage rightManage) {
        int flag = rightManageMapper.updateById(rightManage);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public RightManage selectById(String id) {
        LambdaQueryWrapper<RightManage> lqw = new LambdaQueryWrapper<>();
        RightManage result = rightManageMapper.selectById(id);

        if(result == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return result;
    }

    @Override
    public List<RightManage> selectAll() {
        LambdaQueryWrapper<RightManage> lqw = new LambdaQueryWrapper<>();
        List<RightManage> rightManages = rightManageMapper.selectList(lqw);

        if(rightManages == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return rightManages;
    }
}
