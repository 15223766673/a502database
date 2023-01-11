import org.junit.Test;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedList;

public class databaseTest {
    @Test
    public void addDataToDatabase() throws ClassNotFoundException, SQLException, IOException, ParseException {




        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //用户连接信息
        String url = "jdbc:mysql://localhost:3306/waterdatabase?serverTimezone=UTC";
        String username = "root";
        String password = "123456";
        //连接数据库
        Connection connection = DriverManager.getConnection(url,username,password);
        //创建数据库操作对象
//        Statement statement = connection.createStatement();
        //获取数据

//        //执行sql语句，返回结果
//        String sql = "select * from teacher";
//        ResultSet resultSet = statement.executeQuery(sql);
//        while (resultSet.next()){
//            System.out.println(resultSet.getInt("id"));
//        }
        //遍历文件夹

        int fileNum = 0, folderNum = 0;
        File file = new File("D:\\QQ文档\\761702168\\FileRecv\\全球径流数据集\\GRDC数据集");
        LinkedList<File> list = new LinkedList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (file.exists()) {
            if (null == file.listFiles()) {
                return;
            }
            list.addAll(Arrays.asList(file.listFiles()));
            while (!list.isEmpty()) {
                File[] files = list.removeFirst().listFiles();
                if (null == files) {
                    continue;
                }
                for (File f : files) {
                    if (f.isDirectory()) {
                        System.out.println("文件夹:" + f.getAbsolutePath());
                        list.push(f);  //与广度优先遍历的唯一区别点：是往 List 末尾还是头部添加元素
                        folderNum++;
                    } else {
                        System.out.println("文件:" + f.getAbsolutePath());
                        System.out.println("文件:" + f.getName());
                        FileReader fr = new FileReader(f);
                        BufferedReader br = new BufferedReader(fr);
                        String line;
                        int num=0;
                        String fileTypeName = f.getName().substring(f.getName().length()-9);
                        Integer hydrometricStationId=null;
                        String riverName=null;
                        String hydrometricStationName=null;
                        Float stationArea=null;
                        String countryName=null;
                        Float latitude=null;
                        Float longitude=null;
                        Float altitude=null;
                        String ownerOfOriginalData=null;
                        Integer startYear=null;
                        Integer endYear=null;
                        Integer timeOfDuration=null;
                        String dataType=null;
                        java.sql.Date lastUpdate=null;
                        Integer lines=null;
                        Integer delete=0;
                        //每月数据
                        java.sql.Date date=null;
                        Float original=null;
                        Float calculated=null;
                        Integer flag=null;
                        Integer dataDelete=0;
                        //每日数据
                        Float volumeOfRunoff;
                        //判断是月值数据还是日值数据
                        if(fileTypeName.equals("Month.txt"))
                        {
                            dataType = "monthly";
                            while((line = br.readLine()) != null){
                                // 一行一行地处理...

                                num++;
                                //System.out.println("第"+num+"行"+line);
                                if(num==9)
                                {
                                    hydrometricStationId= Integer.parseInt(line.substring(25));
                                }
                                else if(num==10)
                                {
                                    riverName = line.substring(25);
                                }
                                else if(num==11)
                                {
                                    hydrometricStationName = line.substring(25);
                                }
                                else if(num==12)
                                {
                                    countryName = line.substring(25);
                                }
                                else if (num==13) {
                                    latitude = Float.parseFloat(line.substring(23));
                                }
                                else if(num==14)
                                {
                                    longitude = Float.parseFloat(line.substring(23));
                                }
                                else if(num==15)
                                {
                                    if(line.substring(25).trim().isEmpty())
                                    {
                                        stationArea = new Float("-999.0");
                                    }
                                    else{
                                        stationArea = Float.parseFloat(line.substring(25));
                                    }

                                }
                                else if(num==16)
                                {
                                    altitude = Float.parseFloat(line.substring(27));
                                }
                                else if(num==19)
                                {
                                    ownerOfOriginalData = line.substring(26);
                                }
                                else if(num==25)
                                {
                                    startYear = Integer.parseInt(line.substring(25,29));
                                    endYear = Integer.parseInt(line.substring(32));
                                }
                                else if(num==26)
                                {
                                    timeOfDuration = Integer.parseInt(line.substring(25));
                                }
                                else if(num==27)
                                {
                                    java.util.Date tempDate = format.parse(line.substring(25));
                                    lastUpdate = new Date(tempDate.getTime());
                                }
                                else if(num==37)
                                {
                                    lines = new Integer(line.substring(14));
                                }
                                else if(num==39)
                                {

                                    //插入数据库
                                    String string = "insert into grdc_month_station (hydrometric_station_id,river_name,hydrometric_station_name,station_area,country_name,latitude,longitude,altitude,owner_of_original_data,start_year,end_year,time_of_duration,data_type,last_update,file_url,data_lines,deleted)" +
                                            "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                                    PreparedStatement preparedStatement = connection.prepareStatement(string);
                                    preparedStatement.setInt(1,hydrometricStationId);
                                    preparedStatement.setString(2,riverName);
                                    preparedStatement.setString(3,hydrometricStationName);
                                    preparedStatement.setFloat(4,stationArea);
                                    preparedStatement.setString(5,countryName);
                                    preparedStatement.setFloat(6,latitude);
                                    preparedStatement.setFloat(7,longitude);
                                    preparedStatement.setFloat(8,altitude);
                                    preparedStatement.setString(9,ownerOfOriginalData);
                                    preparedStatement.setInt(10,startYear);
                                    preparedStatement.setInt(11,endYear);
                                    preparedStatement.setInt(12,timeOfDuration);
                                    preparedStatement.setString(13,dataType);
                                    preparedStatement.setDate(14,lastUpdate);
                                    preparedStatement.setString(15,f.getAbsolutePath());
                                    preparedStatement.setInt(16,lines);
                                    preparedStatement.setInt(17,delete);
                                    //System.out.println(preparedStatement);
                                    preparedStatement.executeUpdate();
                                    preparedStatement.close();
                                }
//                                else if(num>39)
//                                {
//                                    //插入每月数据
//                                    String dateString = line.substring(0,10);
//                                    java.util.Date tempDate = format.parse(dateString);
//                                    date = new java.sql.Date(tempDate.getTime());
//                                    original = new Float(line.substring(17,28).trim());
//                                    calculated = new Float(line.substring(29,40).trim());
//                                    flag = new Integer(line.substring(41).trim());
//                                    //插入数据库
//                                    String string = "insert into monthly_volume_of_runoff (hydrometric_station_id,month_date,original,calculated,flag,deleted)" +
//                                            "values (?,?,?,?,?,?)";
//                                    PreparedStatement preparedStatement = connection.prepareStatement(string);
//                                    preparedStatement.setInt(1,hydrometricStationId);
//                                    preparedStatement.setDate(2,date);
//                                    preparedStatement.setFloat(3,original);
//                                    preparedStatement.setFloat(4,calculated);
//                                    preparedStatement.setInt(5,flag);
//                                    preparedStatement.setInt(6,dataDelete);
//                                    preparedStatement.executeUpdate();
//                                    preparedStatement.close();
//                                }

                            }
                        }
                        else if(fileTypeName.equals("y.Cmd.txt"))
                        {
                            dataType = "daily";
                            while((line = br.readLine()) != null){
                                // 一行一行地处理...
                                //
                                num++;
                                //System.out.println("第"+num+"行"+line);
                                if(num==9)
                                {
                                    hydrometricStationId= Integer.parseInt(line.substring(25));
                                }
                                else if(num==10)
                                {
                                    riverName = line.substring(25);
                                }
                                else if(num==11)
                                {
                                    hydrometricStationName = line.substring(25);
                                }
                                else if(num==12)
                                {
                                    countryName = line.substring(25);
                                }
                                else if (num==13) {
                                    latitude = Float.parseFloat(line.substring(23));
                                }
                                else if(num==14)
                                {
                                    longitude = Float.parseFloat(line.substring(23));
                                }
                                else if(num==15)
                                {
                                    if(line.substring(25).trim().isEmpty())
                                    {
                                        stationArea = new Float("-999.0");
                                    }
                                    else{
                                        stationArea = Float.parseFloat(line.substring(28));
                                    }

                                }
                                else if(num==16)
                                {
                                    altitude = Float.parseFloat(line.substring(27));
                                }
                                else if(num==19)
                                {
                                    ownerOfOriginalData = line.substring(26);
                                }
                                else if(num==25)
                                {
                                    if(line.substring(27).trim().isEmpty())
                                    {
                                        startYear=-99;
                                        endYear=-99;
                                    }
                                    else {
                                        startYear = Integer.parseInt(line.substring(25,29));
                                        endYear = Integer.parseInt(line.substring(35,39));
                                    }

                                }
                                else if(num==26)
                                {
                                    if(line.substring(17).trim().isEmpty())
                                    {
                                        timeOfDuration = -99;
                                    }
                                    else {
                                        timeOfDuration = Integer.parseInt(line.substring(25));
                                    }


                                }
                                else if(num==27)
                                {
                                    java.util.Date tempDate = format.parse(line.substring(25));
                                    lastUpdate = new Date(tempDate.getTime());
                                }
                                else if(num==35){
                                    lines = new Integer(line.substring(14));
                                }
                                else if(num==37)
                                {
                                    //插入数据库
                                    String string = "insert into grdc_day_station (hydrometric_station_id,river_name,hydrometric_station_name,station_area,country_name,latitude,longitude,altitude,owner_of_original_data,start_year,end_year,time_of_duration,data_type,last_update,file_url,data_lines,deleted)" +
                                            "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                                    PreparedStatement preparedStatement = connection.prepareStatement(string);
                                    preparedStatement.setInt(1,hydrometricStationId);
                                    preparedStatement.setString(2,riverName);
                                    preparedStatement.setString(3,hydrometricStationName);
                                    preparedStatement.setFloat(4,stationArea);
                                    preparedStatement.setString(5,countryName);
                                    preparedStatement.setFloat(6,latitude);
                                    preparedStatement.setFloat(7,longitude);
                                    preparedStatement.setFloat(8,altitude);
                                    preparedStatement.setString(9,ownerOfOriginalData);
                                    preparedStatement.setInt(10,startYear);
                                    preparedStatement.setInt(11,endYear);
                                    preparedStatement.setInt(12,timeOfDuration);
                                    preparedStatement.setString(13,dataType);
                                    preparedStatement.setDate(14,lastUpdate);
                                    preparedStatement.setString(15,f.getAbsolutePath());
                                    preparedStatement.setInt(16,lines);
                                    preparedStatement.setInt(17,delete);
                                    preparedStatement.executeUpdate();
                                    preparedStatement.close();
                                }
//                                else if(num>37)
//                                {
//                                    //插入每日数据
//                                    String dateString = line.substring(0,10);
//                                    java.util.Date tempDate = format.parse(dateString);
//                                    date = new java.sql.Date(tempDate.getTime());
//                                   volumeOfRunoff = new Float(line.substring(17).trim());
//                                    //插入数据库
//                                    String string = "insert into daily_volume_of_runoff (hydrometric_station_id,month_date,volume_of_runoff,deleted)" +
//                                            "values (?,?,?,?)";
//                                    PreparedStatement preparedStatement = connection.prepareStatement(string);
//                                    preparedStatement.setInt(1,hydrometricStationId);
//                                    preparedStatement.setDate(2,date);
//                                    preparedStatement.setFloat(3,volumeOfRunoff);
//                                    preparedStatement.setFloat(4,dataDelete);
//                                    preparedStatement.executeUpdate();
//                                    preparedStatement.close();
//                                }

                            }
                        }

                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹数量:" + folderNum + ",文件数量:" + fileNum);

        //结束
        connection.close();


    }
}
