package com.service.impl;

import com.mapper.StationMapper;
import com.pojo.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements com.service.StationService {

    @Autowired
    private StationMapper stationMapper;

    @Override
    public List<Station> queryStationByInfo(String info) {
        //修改info以支持模糊查询
        info = "%"+info+"%";
        System.out.println(info);
        List<Station> resultList = stationMapper.queryStationByMonthInfo(info);
        return resultList;
    }
}
