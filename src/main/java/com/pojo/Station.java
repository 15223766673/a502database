package com.pojo;

import com.controller.PageController;
import lombok.Data;

@Data
public class Station {
    private Integer hydrometricStationId;
    private String riverName;
    private String hydrometricStationName;
    private Float stationArea;
    private String countryName;
    private Float latitude;
    private Float longitude;
    private Float altitude;
    private String ownerOfOriginalData;
    private Integer startYear;
    private Integer endYear;
    private Integer timeOfDuration;
    private String dataType;
    private java.sql.Date lastUpdate;
    private String fileUrl;
    private Integer dataLines;

}
