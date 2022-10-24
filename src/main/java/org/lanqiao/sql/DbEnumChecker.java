package org.lanqiao.sql;

import org.lanqiao.file.FileUtil;

public class DbEnumChecker {

    /**
     * 过滤字段名称的结尾是否合法
     * @param fieldName
     * @return
     */
    public static boolean fieldCheckerName(String fieldName) {
        boolean b =false;//默认合法结尾
        for(String str : FileUtil.canotEndWord){
            if(b=fieldName.trim().endsWith(str)){
                break;
            }
        }
        return !b;
    }

    /**
     * 过滤字段是否在指定列表中
     * @param FieldType
     * @return
     */
    public static boolean fieldCheckerType(String FieldType) {
        boolean b =false;
        for(String str : FileUtil.fieldTypes){
            if(b=FieldType.contains(str)){
                break;
            }
        }
        return b;
    }
    /**
     * 初次过滤非字段 类型 描述 的语句：包括 主键 外键 检测 索引等
     * @param fd
     * @return
     */
    public static boolean fieldCheckerFirst(String fd) {
        boolean b =false;
        for(String str : FileUtil.canotStartWord){
            if(b=fd.trim().startsWith(str)){
                break;
            }
        }
        return b;
    }


}
