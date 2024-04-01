package com.iot.mywind.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("faultmanage")
public class FaultManage {
    @TableId(value = "fid")
    private String fid; //故障编号

    private String uid; //机组编号

    private String fname;   //故障名称
    private String description; //故障描述
    private String fcode;   //故障代码
    private String category;    //故障类别
    @TableField(value = "reportTime")
    private String reportTime;    //故障报出时间
    @TableField(value = "handleTime")
    private String handleTime;    //开始处理时间
    @TableField(value = "resetTime")
    private String resetTime; //复位运行时间
    @TableField(value = "firstPosition")
    private String firstPosition;   //故障位置一级
    @TableField(value = "secondPosition")
    private String secondPosition;  //故障位置二级
    @TableField(value = "thirdPosition")
    private String thirdPosition;   //故障位置三级
    @TableField(value = "checkItem")
    private String checkItem;   //排查项目
    private String reason;  //故障原因
}
