package com.iot.mywind.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaultStatistic {
    private String fcode;
    private String category;
    private String count;
    private String yearTime;
}
