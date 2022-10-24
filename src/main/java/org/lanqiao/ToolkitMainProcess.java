package org.lanqiao;

import org.lanqiao.sql.Sql2MarkdownProcess;

/**
 *  工具集入口：
 *      1  SQL 文件转基础 MarkDown : Sql2MarkdownProcess.
 */
public class ToolkitMainProcess {

    /**
     *  args 规则
     *       1 标识位，代表功能选项   必选
     *       2 该功能选项的第一个参数 可选
     *       3 该功能选项的第二个参数 可选
     *       4 该功能选项的第三个参数 ...
     * @param args
     */
    public static void main(String[] args) {
        if(args.length==0){
            System.out.println("必须给定相应功能的标识参数");
            return;
        }
        switch (args[0]){
            case "1":
                Sql2MarkdownProcess.startBuild(args);
                break;
            case "2":
                break;
            default:
                break;
        }



    }

}
