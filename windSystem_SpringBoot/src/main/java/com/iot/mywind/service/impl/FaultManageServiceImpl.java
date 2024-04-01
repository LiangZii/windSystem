package com.iot.mywind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iot.mywind.controller.State;
import com.iot.mywind.entity.BasicData;
import com.iot.mywind.entity.FaultManage;
import com.iot.mywind.exception.BusinessException;
import com.iot.mywind.mapper.FaultManageMapper;
import com.iot.mywind.service.FaultManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaultManageServiceImpl implements FaultManageService {

    @Autowired
    private FaultManageMapper faultManageMapper;


    @Override
    public boolean save(FaultManage faultManage) {
        FaultManage data = faultManageMapper.selectById(faultManage.getFid());
        if(data != null){
            throw new BusinessException(State.ERROR,"编号(主键)已存在");
        }
        int flag = faultManageMapper.insert(faultManage);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public boolean delete(String fid) {
        int flag = faultManageMapper.deleteById(fid);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public boolean update(FaultManage faultManage) {
        int flag = faultManageMapper.updateById(faultManage);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public List<FaultManage> selectByParam(String wname,String uid) {
        List<FaultManage> faultManages = null;
        LambdaQueryWrapper<FaultManage> lqw = new LambdaQueryWrapper<>();

        if(wname != null && uid != null){
            wname = "%" + wname + "%";
            faultManages = faultManageMapper.selectAllByNameAndId(wname, uid);
        }else if(wname != null && uid == null){
            wname = "%" + wname + "%";
            faultManages = faultManageMapper.selectAllByName(wname);
        }else if(wname == null && uid != null){
            lqw.eq(FaultManage::getUid,uid);
            faultManages = faultManageMapper.selectList(lqw);
        }

        if(faultManages == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return faultManages;
    }

    @Override
    public List<FaultManage> selectAll() {
        LambdaQueryWrapper<FaultManage> lqw = new LambdaQueryWrapper<>();
        List<FaultManage> faultManages = faultManageMapper.selectList(lqw);

        if(faultManages == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return faultManages;
    }
}
