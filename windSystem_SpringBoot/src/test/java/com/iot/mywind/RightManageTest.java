package com.iot.mywind;

import com.iot.mywind.entity.RightManage;
import com.iot.mywind.service.RightManageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RightManageTest {

    @Autowired
    private RightManageService rightManageService;

    @Test
    public void login(){
        RightManage rightManage = rightManageService.login("10001", "123456");
        System.out.println(rightManage);
    }

}
