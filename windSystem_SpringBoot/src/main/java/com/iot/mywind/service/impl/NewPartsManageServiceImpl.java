package com.iot.mywind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iot.mywind.controller.State;
import com.iot.mywind.entity.FaultManage;
import com.iot.mywind.entity.NewPartsManage;
import com.iot.mywind.exception.BusinessException;
import com.iot.mywind.mapper.NewPartsManageMapper;
import com.iot.mywind.service.NewPartsManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewPartsManageServiceImpl implements NewPartsManageService {

    @Autowired
    private NewPartsManageMapper newPartsManageMapper;

    @Override
    public boolean save(NewPartsManage newPartsManage) {
        NewPartsManage data = newPartsManageMapper.selectById(newPartsManage.getNid());
        if(data != null){
            throw new BusinessException(State.ERROR,"编号(主键)已存在");
        }
        int flag = newPartsManageMapper.insert(newPartsManage);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public boolean delete(String nid) {
        int flag = newPartsManageMapper.deleteById(nid);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public boolean update(NewPartsManage newPartsManage) {
        int flag = newPartsManageMapper.updateById(newPartsManage);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public NewPartsManage selectById(String nid) {
        LambdaQueryWrapper<NewPartsManage> lqw = new LambdaQueryWrapper<>();
        NewPartsManage result = newPartsManageMapper.selectById(nid);

        if(result == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return result;
    }

    @Override
    public List<NewPartsManage> selectAll() {
        LambdaQueryWrapper<NewPartsManage> lqw = new LambdaQueryWrapper<>();
        List<NewPartsManage> result = newPartsManageMapper.selectList(lqw);

        if(result == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return result;
    }
}
