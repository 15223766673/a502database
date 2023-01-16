package com.controller;

import com.pojo.MonthStationData;
import com.pojo.Station;
import com.service.StationService;
import com.util.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StationController {

    @Autowired
    private StationService stationService;
    @RequestMapping("/query/station")
    @ResponseBody
    public Resp<Object> queryStationByInfo(@RequestBody Map map)
    {


        Resp<Object> resp =new Resp();
        List<Station> resultStation = stationService.queryStationByInfo((String) map.get("info"));
        Map<String,Object> resultMap = new HashMap<>();
        resp.setMessage("查询站点成功");
        resp.setStatus("200");
        resultMap.put("stations",resultStation);
        resp.setRtnMap(resultMap);
        return  resp;
    }
    @RequestMapping(value = "/queryStationInfo/{stationName}")
    @ResponseBody
    public Resp<Object> queryStationByName(@PathVariable(value = "stationName") String stationName)
    {
        Resp<Object> resp =new Resp();
        List<Station> resultStation = stationService.queryStationByInfo(stationName);
        Map<String,Object> resultMap = new HashMap<>();
        resp.setMessage("查询站点成功");
        resp.setStatus("200");
        resultMap.put("stations",resultStation);
        resultMap.put("dataType","monthly");
        resp.setRtnMap(resultMap);
        return  resp;
    }
    @RequestMapping(value = "/station/monthly/{stationId}/{page}")
    @ResponseBody
    public Resp<Object> queryMonthStationDataByIdAndPage(@PathVariable(value = "stationId")Integer stationId,
                                             @PathVariable(value = "page")Integer page) throws IOException {

        Resp<Object> resultStation = stationService.queryMonthStationDataByIdAndPage(stationId,page);

        return  resultStation;
    }
    @RequestMapping(value = "/download/monthly/{stationId}")
    public ResponseEntity<byte[]> downloadMonthlyData(@PathVariable(value = "stationId") String stationId) throws IOException {
        ResponseEntity<byte []> resultStation = stationService.downloadMonthlyData(stationId);
        return  resultStation;
    }
    @RequestMapping(value = "/station/daily/{stationId}/{page}")
    @ResponseBody
    public Resp<Object> queryDayStationDataByIdAndPage(@PathVariable(value = "stationId")Integer stationId,
                                                         @PathVariable(value = "page")Integer page) throws IOException {

        Resp<Object> resultStation = stationService.queryDayStationDataByIdAndPage(stationId,page);

        return  resultStation;
    }
    @RequestMapping(value = "/download/daily/{stationId}")
    public ResponseEntity<byte[]> downloadDailyData(@PathVariable(value = "stationId") String stationId) throws IOException {
        ResponseEntity<byte []> resultStation = stationService.downloadDailyData(stationId);
        return  resultStation;
    }
}
