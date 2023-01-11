package com.controller;

import com.pojo.Station;
import com.service.StationService;
import com.util.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        resp.setRtnMap(resultMap);
        return  resp;
    }
}
