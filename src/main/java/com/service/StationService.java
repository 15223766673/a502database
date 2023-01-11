package com.service;

import com.pojo.Station;

import java.util.List;

public interface StationService {
    /*
      模糊查询当前输入对应的所有站点
     */
        List<Station> queryStationByInfo(String info);
}
