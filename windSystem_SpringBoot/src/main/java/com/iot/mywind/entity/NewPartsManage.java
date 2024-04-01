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
@TableName("newpartsmanage")
public class NewPartsManage {
    @TableId(value = "nid")
    private String nid;     //新件编号
    @TableField(value = "materialCode")
    private String materialCode;    //厂家物料编码
    @TableField(value = "SPAcode")
    private String SPAcode;     //SPA物料编码
    @TableField(value = "stationCode")
    private String stationCode; //场站物料代码
    private String nname;   //新件名称
    private String modle;   //规格型号
    private String num;     //数量
    private String unit;    //单位
    private Float unitprice;    //单价
    private String intime;    //入库时间
    private String outtime;   //出库时间
//    @TableField(value = "useId")
//    private String useId;   //使用备件风机号
}
