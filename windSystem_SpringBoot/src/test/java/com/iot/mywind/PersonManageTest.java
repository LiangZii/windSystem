package com.iot.mywind;

import com.iot.mywind.entity.PersonManage;
import com.iot.mywind.mapper.PersonManageMapper;
import com.iot.mywind.service.PersonManageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PersonManageTest {

    @Autowired
    private PersonManageMapper personManageMapper;

    @Autowired
    private PersonManageService personManageService;

    @Test
    public void selectByname(){
        List<PersonManage> personManages = personManageService.selectByName("ÕÅÈý");
        System.out.println(personManages);
    }

    @Test
    public void deleteById(){
        boolean delete = personManageService.delete("3");
    }

    @Test
    public void selectAll(){
        List<PersonManage> result = personManageService.selectAll();
        result.forEach(System.out::println);

    }

}
