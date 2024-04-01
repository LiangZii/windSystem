package com.iot.mywind;

import com.iot.mywind.entity.BasicData;
import com.iot.mywind.service.BasicDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BasicDataTest {

    @Autowired
    private BasicDataService basicDataService;

    @Test
    public void selectByName(){
        List<BasicData> basicData = basicDataService.selectByName("察北风电场");
        System.out.println(basicData);
    }

    @Test
    public void save(){
        BasicData basicData = new BasicData("2","B1","华天","PM01","2021-09-01 12:23:23",21,"运行",null);
        boolean save = basicDataService.save(basicData);
        System.out.println(save);
    }

    @Test
    public void update(){
        BasicData basicData = new BasicData("2","B1","华天","PM03","2021-09-01 12:23:23",21,"运行",null);
        boolean save = basicDataService.update(basicData);
        System.out.println(save);
    }

}
