import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedList;

public class newDataBaseTest {
    @Test
    public void test()
    {




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
                                fileNum++;
                            }
                        }
                    }
                } else {
                    System.out.println("文件不存在!");
                }
                System.out.println("文件夹数量:" + folderNum + ",文件数量:" + fileNum);



            }



}
