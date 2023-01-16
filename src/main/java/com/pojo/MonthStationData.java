package com.pojo;

import lombok.Data;

@Data
public class MonthStationData {
    //private Integer hydrometricStationId;
    private String monthDate;
    private String original;
    private String calculated;
    private String flag;
}
