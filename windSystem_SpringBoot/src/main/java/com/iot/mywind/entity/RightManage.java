package com.iot.mywind.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("rightmanage")
public class RightManage {
    private String id;          //用户名（工号）
    private String password;    //密码
    private String limitation;       //模块权限
    private String name;        //姓名
    private String role;        //角色
    private String comment;     //备注


}
