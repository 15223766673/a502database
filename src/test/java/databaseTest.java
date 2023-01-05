import org.junit.Test;

import java.io.*;
import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;

public class databaseTest {
    @Test
    public void addDataToDatabase() throws ClassNotFoundException, SQLException, IOException {
        //遍历文件夹

        int fileNum = 0, folderNum = 0;
        File file = new File("F:\\360MoveData\\Users\\Administrator\\Documents\\Tencent Files\\761702168\\FileRecv\\全球径流数据集\\全球径流数据集\\GRDC数据集");
        LinkedList<File> list = new LinkedList<>();

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
                        System.out.println(f.getName().substring(f.getName().length()-9));
                        //判断是月值数据还是日值数据
                        if(f.getName().substring(f.getName().length()-9).equals("Month.txt")){

                        }
                        while((line = br.readLine()) != null){
                            // 一行一行地处理...
                            System.out.println(line);
                        }
                        fileNum++;
                          return;



                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹数量:" + folderNum + ",文件数量:" + fileNum);


//        //加载驱动
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        //用户连接信息
//        String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&character=utf8&useSSL=false&serverTimezone=UTC";
//        String username = "tianzhou";
//        String password = "123456";
//        //连接数据库
//        Connection connection = DriverManager.getConnection(url,username,password);
//        //创建数据库操作对象
//        Statement statement = connection.createStatement();
//        //获取数据
//
//        //执行sql语句，返回结果
//        String sql = "select * from teacher";
//        ResultSet resultSet = statement.executeQuery(sql);
//        while (resultSet.next()){
//            System.out.println(resultSet.getInt("id"));
//        }
//
//        //结束
//
//        resultSet.close();
//        statement.close();
//        connection.close();
//
//
//        System.out.println("hhh");
    }
}
