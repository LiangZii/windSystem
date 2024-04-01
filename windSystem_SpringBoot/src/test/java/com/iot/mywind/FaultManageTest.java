package com.iot.mywind;

import com.iot.mywind.entity.FaultManage;
import com.iot.mywind.service.FaultManageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FaultManageTest {

    @Autowired
    private FaultManageService faultManageService;

    @Test
    public void selectByParam(){
        List<FaultManage> faultManages = faultManageService.selectByParam(null, "年");
        System.out.println(faultManages);
    }

    @Test
    public void delete(){
        faultManageService.delete("3");
    }

}
