package com.iot.mywind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iot.mywind.controller.State;
import com.iot.mywind.entity.PreventMaintenance;
import com.iot.mywind.exception.BusinessException;
import com.iot.mywind.mapper.PreventMaintenanceMapper;
import com.iot.mywind.service.PreventMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreventMaintenanceServiceImpl implements PreventMaintenanceService {

    @Autowired
    private PreventMaintenanceMapper preventMaintenanceMapper;

    @Override
    public List<PreventMaintenance> selectAll() {
        LambdaQueryWrapper<PreventMaintenance> lqw = new LambdaQueryWrapper<>();
        List<PreventMaintenance> maintenances = preventMaintenanceMapper.selectList(lqw);

        if(maintenances == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return maintenances;
    }

    @Override
    public boolean save(PreventMaintenance preventMaintenance) {
        int flag = preventMaintenanceMapper.insert(preventMaintenance);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        int flag = preventMaintenanceMapper.deleteById(id);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public boolean update(PreventMaintenance preventMaintenance) {
        int flag = preventMaintenanceMapper.updateById(preventMaintenance);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public List<PreventMaintenance> selectByUid(String uid) {
        QueryWrapper<PreventMaintenance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        List<PreventMaintenance> preventMaintenances = preventMaintenanceMapper.selectList(queryWrapper);
        if(preventMaintenances == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return preventMaintenances;
    }
}
