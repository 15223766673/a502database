package com.service;

import com.pojo.MonthStationData;
import com.pojo.Station;
import com.util.Resp;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface StationService {
    /*
      模糊查询当前输入对应的所有站点
     */
        List<Station> queryStationByInfo(String info);
        Resp queryMonthStationDataByIdAndPage(Integer stationId, Integer page) throws IOException;
        ResponseEntity<byte []> downloadMonthlyData(String stationId) throws IOException;

    Resp queryDayStationDataByIdAndPage(Integer stationId, Integer page) throws IOException;
    ResponseEntity<byte []> downloadDailyData(String stationId) throws IOException;

}
