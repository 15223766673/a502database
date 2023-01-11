package com.mapper;

import com.pojo.Station;

import java.util.List;

public interface StationMapper {
    List<Station> queryStationByMonthInfo(String info);
}
