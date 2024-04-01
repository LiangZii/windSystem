package com.iot.mywind.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("maintenance")
public class Maintenance {
    private String fid;     //故障编号
    private String grade;   //维修等级
    private String category;    //维修类别
    @TableId(value = "mid")
    private String mid;     //工作票编号
    private String action;  //维修动作
    private String object;  //维修对象
    private String date;      //维修时间
    private String redate;  //下次维修时间
    private String repairman;   //维修员
}
