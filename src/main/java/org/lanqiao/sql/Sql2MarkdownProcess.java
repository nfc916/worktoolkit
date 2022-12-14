package org.lanqiao.sql;

import org.lanqiao.terminal.CommanLine;
import org.lanqiao.terminal.LinuxCommandUtil;

import java.io.*;
import java.util.*;


/**
 *
 *  1 目的 解析 SQL 文件 生成 markdown 表格
 *   前置条件： 数据库已经存在 ，通过 mysqldump 导出语句保存成 sql 文件 【绝对路径】
 *   输出结果： 保存 字段名   字段类型  字段描述
 *   要求： 尽少依赖第三方
 *
 */
public class Sql2MarkdownProcess {

    public static String filePath ="/home/project/db.sql";
    public static String target ="/home/project/db.md";
    public static  CommanLine commanLine = new LinuxCommandUtil();

    public static void main(String[] args) {
        startBuild(args);
    }

    public static void startBuild(String[] args) {

        if(args!=null&&args.length>=2){
            String p ="";
            if(args.length>2){
                p =   " -p"+args[2];
            }
            //导出数据库内容成文件  数据库名，用户名，[密码]
            System.out.println("导出数据库内容成文件，数据库名："+args[0]+"，用户名："+args[1]+"，密码："+p);
            try {
                commanLine.invockCmdLines(new String[]{"mysqldump -u"+args[1]+p+" --opt -d "+args[0]+" > " +filePath});
            } catch (Exception e) {

            }
        }
        // 直接将文件转成 MarkDown
        //1 解析SQL语句
        //2 将内容写成 文件
        buildMarkdown(SqlParseUtil.doParseSQL(filePath));
        System.out.println("导出 MarkDown 成功！！");

    }

    private static void buildMarkdown(List<String> sqls) {
        String tableName="";
        String tableComment="";
        List<String []> tableFilds =null;
        try {
            BufferedWriter bw =new BufferedWriter(new FileWriter(target));
            bw.append("# 数据库的设计与创建\r\n");
            bw.append("## 数据库表设计\r\n");
            bw.append("根据需求分析，【待补充】\r\n");
            bw.append("实体间的关系如下:【换行后待补充】\r\n");
            bw.append("\r\n");
            bw.append("\r\n");
            bw.append("\r\n");
            bw.append("实体间的 ER 图如下图所示：【图待补充】\r\n");
            bw.append("\r\n");
            bw.append("根据系统的 ER 图，可以设计出【待补充】项目的数据库实体。根据以上实体，可以设计出详细的【待补充】项目的数据库表，具体如下:\r\n");
            bw.append("\r\n");
            for(String sql:sqls){
                //1  获取表名 表描述 字段名 字段类型 字段描述 【主键、非空、索引、描述】
                tableName = sql.substring(sql.indexOf("table")+5,sql.indexOf("(")).trim();//表名必然存在
                tableComment = getTableComment(sql.trim().replace(" ", ""));//表注释不一定存在
                tableFilds = getTableFields(sql.substring(sql.indexOf("(")+1,sql.lastIndexOf(")")));//字段列表必然存在在一对括号中
                bw.append("\r\n**表名:"+tableName +"("+(tableComment==null?"【待补充】":tableComment)+")**");
                bw.append("\r\n");
                bw.append("| 字段名称     | 数据类型      | 说明                                   |");
                bw.append("\r\n");
                bw.append("| ------------ | --------- | -------------------------------------- |");
                bw.append("\r\n");
                //2  获取字段名 字段类型 字段描述 【主键、非空、索引、描述】
                for(String [] field:tableFilds){
                    bw.append("| "+field[0]+" | "+field[1]+" | "+field[2]+" |");
                    bw.append("\r\n");
                }

                bw.append("\r\n");
            }
            bw.append("\r\n【可选】其中，【XX】表用来保存【XX】信息，为了更方便的实现对【XX】信息的查询，在设计【XX】表时，冗余了部分字段。\r\n");
            bw.append("\r\n【可选】同时为了更好的提升性能，表与表之前不建立物理外键，关联的逻辑外键在编码时需要注意。\r\n");
            bw.append("\r\n【可选】表中所有的 `modifyDate` 字段设置成 `timestamp` ,且根据时间戳自动更新。\r\n");
            bw.append("\r\n【可选】最终，【待补充】项目的数据库结构如图所示：\r\n");
            bw.append("\r\n");
            bw.append("[数据库素材下载地址](https://labfile.oss.aliyuncs.com/courses/课程号/名称.sql)");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *  获取表字段数组 必然存在，个数不确定 格式为：在一个完整的最外层括号中 ( 字段名 字段类型  字段描述 )
     * @param sql
     * @return
     */
    private static  List<String []> getTableFields(String sql) {
        List<String []> list =new ArrayList<String []>();
        String [] fds  = sql.split(",。");
        for(String fd:fds){
            if(DbEnumChecker.fieldCheckerFirst(fd)){
                continue;
            }
            String [] ds= new String [3];
            ds[0]= fd.trim().split(" ")[0].trim();
            ds[1]= fd.trim().split(" ")[1].trim();
            ds[2]= getTableFieldsComment(fd.replace(fd.trim().split(" ")[0], " ").replace(fd.trim().split(" ")[1], "").trim());
            if(DbEnumChecker.fieldCheckerName(ds[0])&& DbEnumChecker.fieldCheckerType(ds[1]))
                list.add(ds);
        }
        return list;
    }
    /**
     * 获取字段描述
     * @param fieldsComment
     * @return
     */
    private static String getTableFieldsComment(String fieldsComment) {
        String str =  fieldsComment.replace("unsigned", "").replaceAll("character.+comment", "").replace("'", "").replace("\"", "").replace("auto_increment", "主键自增");
        str = str.replace("default null", "默认为空").replaceAll("default.+comment", "").replace("comment", "").replace("default current_timestamp", "");
        return str.contains("not null")?str.replace("not null", "").trim()+" 不能为空":str.trim();
    }

    /**
     * 获取表描述 ，可能不存在 ，如果存在是 comment='表描述' 中的内容
     * @param sql 去掉了所有空格
     * @return  替换掉 '' 后的结果
     */
    private static String getTableComment(String sql) {
        if(sql.lastIndexOf("comment")>sql.lastIndexOf(")")){
            return  sql.substring(sql.lastIndexOf("comment")+9,sql.indexOf(";")).replace("'", "");
        }
        return null;
    }

}