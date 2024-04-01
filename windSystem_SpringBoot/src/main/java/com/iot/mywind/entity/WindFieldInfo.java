package com.iot.mywind.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("windfieldinfo")
public class WindFieldInfo {
    @TableId(value = "wId")
    private String wId;     //风场编号
    private String wname;   //风场名称
    private String number;  //机组数量
    @TableField(value = "allEnergy")
    private Float allEnergy;   //风场发电量
}
