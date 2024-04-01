package com.iot.mywind.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("preventmaintenance")
public class PreventMaintenance {
    private Integer id; //记录编号
    private String uid; //风机编号
    private String work;  //工作任务
    private String person;  //人员
    private String content;  //检修内容
    @TableField(value = "periodicRepair")
    private String periodicRepair;  //定期检修
    private Date time;  //时间
    private String comment;  //备注
}
