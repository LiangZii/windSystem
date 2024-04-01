package com.iot.mywind.entity;

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
@TableName("basicdata")
public class BasicData {
    @TableField(value = "wId")
    private String wId;     //风场编号
    @TableId(value = "uid")
    private String uid;     //机组编号
    private String manufacturer;    //生产厂商
    private String modle;   //设备型号
    private String date;  //安装日期
    private Integer time;   //使用年限
    private String state;   //设备转态
    @TableField(value = "unitEnergy")
    private Float unitEnergy;   //机组发电量
}
