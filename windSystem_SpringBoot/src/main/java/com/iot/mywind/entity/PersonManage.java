package com.iot.mywind.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("personmanage")
public class PersonManage {
    @TableId(value = "pid")
    private Integer pid;    //记录编号
    private String name;    //姓名
    private Integer id;     //工号
    private String role;    //角色
    private String content; //维修内容
    private Date time;  //时间
    private String comment; //备注
    private String mid; //工作票编号

}
