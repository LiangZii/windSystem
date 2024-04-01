package com.iot.mywind;

import com.iot.mywind.entity.PreventMaintenance;
import com.iot.mywind.service.PreventMaintenanceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PreventMaintenanceTest {

    @Autowired
    private PreventMaintenanceService preventMaintenanceService;

    @Test
    public void selectAll(){
        List<PreventMaintenance> preventMaintenances = preventMaintenanceService.selectAll();
        System.out.println(preventMaintenances);
    }

}
