package com.iot.mywind;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iot.mywind.controller.State;
import com.iot.mywind.entity.BasicData;
import com.iot.mywind.entity.WindFieldInfo;
import com.iot.mywind.exception.BusinessException;
import com.iot.mywind.mapper.WindFieldInfoMapper;
import com.iot.mywind.service.WindFieldInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WindFieldInfoTest {

    @Autowired
    private WindFieldInfoMapper windFieldInfoMapper;

    @Autowired
    private WindFieldInfoService windFieldInfoService;

    @Test
    public void selectByname(){
        List<WindFieldInfo> windFieldInfo = windFieldInfoService.selectByName("浙江");
        System.out.println(windFieldInfo);
    }

    @Test
    public void deleteByWid(){
        boolean delete = windFieldInfoService.delete("9");
    }

    @Test
    public void update(){
        WindFieldInfo windFieldInfo = new WindFieldInfo("9","河南风电场","11",280.00f);
        boolean save = windFieldInfoService.update(windFieldInfo);
        System.out.println(save);
    }

}
