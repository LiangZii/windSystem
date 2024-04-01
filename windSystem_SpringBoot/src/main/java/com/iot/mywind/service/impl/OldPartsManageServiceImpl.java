package com.iot.mywind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iot.mywind.controller.State;
import com.iot.mywind.entity.NewPartsManage;
import com.iot.mywind.entity.OldPartsManage;
import com.iot.mywind.exception.BusinessException;
import com.iot.mywind.mapper.OldPartsManageMapper;
import com.iot.mywind.service.OldPartsManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OldPartsManageServiceImpl implements OldPartsManageService {

    @Autowired
    private OldPartsManageMapper oldPartsManageMapper;

    @Override
    public boolean save(OldPartsManage oldPartsManage) {
        OldPartsManage data = oldPartsManageMapper.selectById(oldPartsManage.getOid());
        if(data != null){
            throw new BusinessException(State.ERROR,"编号(主键)已存在");
        }
        int flag = oldPartsManageMapper.insert(oldPartsManage);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public boolean delete(String oid) {
        int flag = oldPartsManageMapper.deleteById(oid);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public boolean update(OldPartsManage oldPartsManage) {
        int flag = oldPartsManageMapper.updateById(oldPartsManage);
        if(flag != 1){
            throw new BusinessException(State.ERROR,"错误，请重试");
        }
        return true;
    }

    @Override
    public OldPartsManage selectById(String oid) {
        LambdaQueryWrapper<NewPartsManage> lqw = new LambdaQueryWrapper<>();
        OldPartsManage result = oldPartsManageMapper.selectById(oid);

        if(result == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return result;
    }

    @Override
    public List<OldPartsManage> selectAll() {
        LambdaQueryWrapper<OldPartsManage> lqw = new LambdaQueryWrapper<>();
        List<OldPartsManage> result = oldPartsManageMapper.selectList(lqw);

        if(result == null){
            throw new BusinessException(State.ERROR,"查询错误，请重试");
        }

        return result;
    }
}
