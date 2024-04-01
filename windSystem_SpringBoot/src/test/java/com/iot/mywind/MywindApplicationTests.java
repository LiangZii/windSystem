package com.iot.mywind;

import com.iot.mywind.entity.RightManage;
import com.iot.mywind.mapper.RightManageMapper;
import com.iot.mywind.service.RightManageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MywindApplicationTests {

    @Autowired
    private RightManageService rightManageService;

    @Autowired
    private RightManageMapper rightManageMapper;

    @Test
    public void rightManegeSelectById(){
        RightManage rightManage = rightManageMapper.selectById(10001);
        System.out.println(rightManage);
    }

    @Test
    public void rightManegeReg(){
        RightManage rightManage = new RightManage("123","123","123","123","123","123");
        rightManageService.reg(rightManage);
    }



}
