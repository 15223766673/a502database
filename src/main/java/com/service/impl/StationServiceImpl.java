package com.service.impl;

import com.mapper.StationMapper;
import com.pojo.DayStationData;
import com.pojo.MonthStationData;
import com.pojo.Station;
import com.util.Resp;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StationServiceImpl implements com.service.StationService {

    @Autowired
    private StationMapper stationMapper;

    @Override
    public List<Station> queryStationByInfo(String info) {
        //修改info以支持模糊查询
        info = "%"+info+"%";
        System.out.println(info);
        List<Station> monthList = stationMapper.queryStationByMonthInfo(info);
        List<Station> dayList = stationMapper.queryStationByDayInfo(info);
        for(int i = 0;i<dayList.size();i++)
        {
            monthList.add(dayList.get(i));
        }
        return monthList;
    }

    @Override
    public Resp queryMonthStationDataByIdAndPage(Integer stationId, Integer page) throws IOException {
        Resp<Object> resp = new Resp<>();
        String info = "%"+stationId+"%";
        List<Station> station = stationMapper.queryStationByMonthInfo(info);
        String fileUrl = station.get(0).getFileUrl();
        File file = new File(fileUrl);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int num=0;
        //计算起始行和末尾行
        int start = 40+(page-1)*10;
        int end = start+10;
        List<MonthStationData> resultList = new ArrayList<>();
        Integer pages=null;
        while ((line = br.readLine())!=null)
        {

            num++;
            //System.out.println("第"+num+"行"+line);
            if(num==37)
            {
                pages = new Integer(line.substring(14));
                pages = (pages+9)/10;
            }
            else if(num>=start&&num<end)
            {
                MonthStationData monthStationData = new MonthStationData();
                monthStationData.setMonthDate(line.substring(0,10));
                monthStationData.setOriginal(line.substring(17,28));
                monthStationData.setCalculated(line.substring(29,40));
                monthStationData.setFlag(line.substring(41));
                resultList.add(monthStationData);
            }
            if(num>=end){
                break;
            }
        }
        resp.setMessage("请求站点页码数据成功");
        resp.setStatus("200");
        Map<String,Object> map =new HashMap<>();
        map.put("stationDatas",resultList);
        map.put("totalPage",pages);
        map.put("currentPage",page);
        map.put("dataType","monthly");
        map.put("stationId",stationId);
        resp.setRtnMap(map);
        return resp;
    }
    public ResponseEntity<byte []> downloadMonthlyData(String stationId) throws IOException {
        //获取文件地址
        String info = "%"+stationId+"%";
        List<Station> stationList  =  stationMapper.queryStationByMonthInfo(info);
        String fileUrl = stationList.get(0).getFileUrl();
        File filePath = new File(fileUrl);

        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，并解决中文名称乱码问题
        String downloadFileName = new String((stationId+".txt").getBytes("UTF-8"),"iso-8859-1");
        //通知浏览器以attachment（下载方式）打开
        headers.setContentDispositionFormData("attachment", downloadFileName);
        //applicatin/octet-stream: 二进制流数据（最常见的文件下载）
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filePath), headers, HttpStatus.CREATED);
    }

    public Resp queryDayStationDataByIdAndPage(Integer stationId, Integer page) throws IOException {
        Resp<Object> resp = new Resp<>();
        String info = "%"+stationId+"%";
        List<Station> station = stationMapper.queryStationByDayInfo(info);
        String fileUrl = station.get(0).getFileUrl();
        File file = new File(fileUrl);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int num=0;
        //计算起始行和末尾行
        int start = 38+(page-1)*10;
        int end = start+10;
        List<DayStationData> resultList = new ArrayList<>();
        Integer pages=null;
        while ((line = br.readLine())!=null)
        {

            num++;
            System.out.println("第"+num+"行"+line);
            if(num==35)
            {
                pages = new Integer(line.substring(14));
                pages = (pages+9)/10;
            }
            else if(num>=start&&num<end)
            {
                DayStationData dayStationData = new DayStationData();
                dayStationData.setDayDate(line.substring(0,10));
                dayStationData.setValue(line.substring(17,28));

                resultList.add(dayStationData);
            }
            if(num>=end){
                break;
            }
        }
        resp.setMessage("请求站点页码数据成功");
        resp.setStatus("200");
        Map<String,Object> map =new HashMap<>();
        map.put("stationDatas",resultList);
        map.put("totalPage",pages);
        map.put("currentPage",page);
        map.put("dataType","daily");
        map.put("stationId",stationId);
        resp.setRtnMap(map);
        return resp;
    }
    public ResponseEntity<byte []> downloadDailyData(String stationId) throws IOException {
        //获取文件地址
        String info = "%"+stationId+"%";
        List<Station> stationList  =  stationMapper.queryStationByDayInfo(info);
        String fileUrl = stationList.get(0).getFileUrl();
        File filePath = new File(fileUrl);

        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，并解决中文名称乱码问题
        String downloadFileName = new String((stationId+".txt").getBytes("UTF-8"),"iso-8859-1");
        //通知浏览器以attachment（下载方式）打开
        headers.setContentDispositionFormData("attachment", downloadFileName);
        //applicatin/octet-stream: 二进制流数据（最常见的文件下载）
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filePath), headers, HttpStatus.CREATED);
    }
}
