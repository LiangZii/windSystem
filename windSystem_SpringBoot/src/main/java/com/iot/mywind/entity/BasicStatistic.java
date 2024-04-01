package com.iot.mywind.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicStatistic {
    private String wname;
    private String number;
    private String count;
    private String allEnergy;
}
