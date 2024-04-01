package com.iot.mywind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iot.mywind.controller.State;
import com.iot.mywind.entity.FaultManage;
import com.iot.mywind.entity.Maintenance;
import com.iot.mywind.exception.BusinessException;
import com.iot.mywind.mapper.MaintenanceMapper;
import com.iot.mywind.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    private MaintenanceMapper maintenanceMapper;

    @Override
    public boolean save(Maintenance maintenance) {
        Maintenance data = maintenanceMapper.selectById(maintenance.getMid());
        if(data != null){
            throw new BusinessException(State.ERROR,"编号(主键)已存在");
        }
        int flag = maintenanceMapper.insert(maintenance);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public boolean delete(String mid) {
        int flag = maintenanceMapper.deleteById(mid);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public boolean update(Maintenance maintenance) {
        int flag = maintenanceMapper.updateById(maintenance);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public List<Maintenance> selectByName(String repairman) {
        LambdaQueryWrapper<Maintenance> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Maintenance::getRepairman,repairman);
        List<Maintenance> maintenances = maintenanceMapper.selectList(lqw);

        if(maintenances == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return maintenances;
    }

    @Override
    public List<Maintenance> selectAll() {
        LambdaQueryWrapper<Maintenance> lqw = new LambdaQueryWrapper<>();
        List<Maintenance> maintenances = maintenanceMapper.selectList(lqw);

        if(maintenances == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return maintenances;
    }
}
