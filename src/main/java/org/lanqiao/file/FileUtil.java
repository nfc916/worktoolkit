package org.lanqiao.file;

import org.lanqiao.sql.DbEnumChecker;

import java.io.IOException;
import java.util.Properties;

/**
 *  文件操作类：
 *          1 加载 properties 配置文件
 */
public class FileUtil {
    public static String [] canotStartWord ;
    public static String [] fieldTypes ;
    public static String [] canotEndWord ;
    static{
        // 读取库名  表名  字段名  全路径类名（含包名）
        Properties prop =new Properties();
        try {
            prop.load(DbEnumChecker.class.getClassLoader().getResourceAsStream("settings.properties"));
            canotStartWord=prop.getProperty("canotStartWord").trim().split(",");
            fieldTypes=prop.getProperty("fieldTypes").trim().split(",");
            canotEndWord=prop.getProperty("canotEndWord").trim().split(",");
        } catch (IOException e) {
            System.out.println("静态加载出错，修改 settings 配置文件");
        }
    }

}
