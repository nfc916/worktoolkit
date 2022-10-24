package org.lanqiao.check;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 挑战 检测工具类
 *
 * @param <T>
 */
public class ChallengeCheckerUtil<T>  {
    static Class klass ;

    public static boolean chekckInitDatas() {
        //检测是否修改了变量名 ，是否在无参构造中创建对象并初始化长度为2，是否支持泛型
        Field field = null;
        Object[] objects = null;
        try {
            field = klass.getDeclaredField("datas");
            field.setAccessible(true);
       //     ArrayStack<String> stackString = new ArrayStack<String>();
      //      ArrayStack<Integer> stackInt = new ArrayStack<Integer>();
       //     objects = (Object[]) field.get(stackString);
            return field != null && objects != null && objects.length == 2;
        } catch (Exception exception) {

        }

        return false;
    }

    public static boolean checkSize() {
        //测试元素个数是否匹配
    /*    ArrayStack<String> stackString = new ArrayStack<String>();
        Field field = null;
        int srcsize = 0, destsize = 0;
        try {
            field = klass.getDeclaredField("eleSize");
            field.setAccessible(true);
            Object size = field.get(stackString);
            srcsize = stackString.size();
            field.set(stackString, Integer.parseInt(size.toString()) + 100);
            destsize = stackString.size();
            return (destsize - srcsize == 100);
        } catch (Exception exception) {

        }*/
        return false;
    }

    public static boolean checkIsEmpty() {
        //测试元素是否为空
        /*    ArrayStack<String> stackString = new ArrayStack<String>();
        boolean a = stackString.isEmpty();
        Field field = null;
        Field fieldsize = null;
        boolean b = false;
        try {
            field = klass.getDeclaredField("datas");
            fieldsize = klass.getDeclaredField("eleSize");
            field.setAccessible(true);
            fieldsize.setAccessible(true);
            field.set(stackString, new String[]{"1", "2", "3", "4", "5"});
            Object size = fieldsize.get(stackString);
            fieldsize.set(stackString, Integer.parseInt(size.toString()) + 5);
            b = stackString.isEmpty();
            return (!b && a);
        } catch (Exception exception) {

        }*/
        return false;
    }

    public static void main(String[] args) throws Exception {

        if (args != null && args.length > 0) {
            if (args[0].equalsIgnoreCase("1")) {
                System.out.println(chekckInitDatas());
            } else if (args[0].equalsIgnoreCase("2")) {
                System.out.println(checkSize());
            } else if (args[0].equalsIgnoreCase("3")) {
                System.out.println(checkIsEmpty());
            } else if (args[0].equalsIgnoreCase("4")) {
                checkIsUserAPI();
                System.out.println(checkPush());
            } else if (args[0].equalsIgnoreCase("5")) {
                System.out.println(checkPop());
            } else if (args[0].equalsIgnoreCase("6")) {
                System.out.println(checkTop());
            } else if (args[0].equalsIgnoreCase("7")) {
                checkIsUserAPI();
                System.out.println(checkSort());
            }
        }
    }


    private static void checkIsUserAPI() throws IOException {
        File file = new File("/home/shiyanlou/IdeaProjects/src/main/java/ArrayStack.java");
        BufferedReader br = null;
        if (file.exists()) {
            br = new BufferedReader(new FileReader("/home/shiyanlou/IdeaProjects/src/main/java/ArrayStack.java"));
        } else {
            br = new BufferedReader(new FileReader("/home/project/ArrayStack.java"));
        }
        String line = null;
        boolean isUseApi = false;
        boolean isimportApi = false;
        while ((line = br.readLine()) != null) {
            if ((line.replace(" ", "").contains("java.util")) && !line.replace(" ", "").startsWith("/")) {
                isimportApi = true;
            } else if ((line.replace(" ", "").contains("Collections.copy") ||line.replace(" ", "").contains("Collections.sort") ||line.replace(" ", "").contains("Arrays.sort") || line.replace(" ", "").contains("Arrays.copyOf")) && !line.replace(" ", "").startsWith("//")) {
                isUseApi = true;
            }
            if (isUseApi && isimportApi) {
                throw new RuntimeException("不得使用 JDK 相关 API");
            }

        }
    }

    public static boolean checkPush() {

         /*   ArrayStack<String> stackString = new ArrayStack<String>();

        Field field = null;
        Field fieldsize = null;
        boolean b = false;
        try {
            field = klass.getDeclaredField("datas");
            fieldsize = klass.getDeclaredField("eleSize");
            field.setAccessible(true);
            fieldsize.setAccessible(true);
            field.set(stackString, new String[2]);
            for (int i = 1; i <= 10; i++) {
                klass.getMethod("push", Object.class).invoke(stackString, i + "");
            }
            Object size = fieldsize.get(stackString);
            Object[] objs = (Object[]) field.get(stackString);
            return size.toString().equals("10") && objs.length == 16;
        } catch (Exception exception) {

        }*/
        return false;

    }

    public static boolean checkPop() {
          /*  ArrayStack<String> stackString = new ArrayStack<String>();

        Field field = null;
        Field fieldsize = null;
        boolean b = false;
        try {
            field = klass.getDeclaredField("datas");
            fieldsize = klass.getDeclaredField("eleSize");
            field.setAccessible(true);
            fieldsize.setAccessible(true);
            field.set(stackString, new String[]{"1", "2", "3", "4", "5", null, null, null});
            Object size = fieldsize.get(stackString);
            fieldsize.set(stackString, Integer.parseInt(size.toString()) + 5);
            if (checkIsEmpty()) {
                for (int i = 5; i > 0; i--) {
                    Object obj = klass.getMethod("pop").invoke(stackString);
                    size = fieldsize.get(stackString);
                    if (!obj.toString().equals("" + (i)) || !size.toString().equals("" + (i - 1))) {
                        return false;
                    }
                }
                size = fieldsize.get(stackString);
                Object[] objs = (Object[]) field.get(stackString);
                return size.toString().equals("0") && objs.length == 8;
            } else {
                boolean a = false;
                boolean bb = false;
                for (int i = 5; i > 0; i--) {
                    Object obj = klass.getMethod("pop").invoke(stackString);
                    size = fieldsize.get(stackString);
                    if (obj != null) {
                        if (obj.toString().equals("" + (i)) && size.toString().equals("" + (i - 1))) {
                            a = true;
                        }
                    } else {
                        bb = true;
                    }
                }
                return a && bb;
            }

        } catch (Exception exception) {

        }*/
        return false;

    }

    public static boolean checkTop() {
         /*   ArrayStack<String> stackString = new ArrayStack<String>();

        Field field = null;
        Field fieldsize = null;
        boolean b = false;
        try {
            field = klass.getDeclaredField("datas");
            fieldsize = klass.getDeclaredField("eleSize");
            field.setAccessible(true);
            fieldsize.setAccessible(true);
            field.set(stackString, new String[]{"1", "2", "3", "4", "5", null, null, null});
            Object size = fieldsize.get(stackString);
            fieldsize.set(stackString, Integer.parseInt(size.toString()) + 5);
            Object obj = klass.getMethod("top").invoke(stackString);
            return obj.toString().equals("5");
        } catch (Exception exception) {

        }*/
        return false;
    }

    public static boolean checkSort() {
        //TODO  排序功能
         /*   ArrayStack<String> stackString = new ArrayStack<String>();
        stackString.push("到");
        stackString.push("aa");
        stackString.push("d");
        stackString.push("1");
        stackString.push("12");
        ArrayStack<Integer> stackInt = new ArrayStack<Integer>();
        stackInt.push(650);
        stackInt.push(189);
        stackInt.push(486);
        stackInt.push(154);
        stackInt.push(499);
        ArrayStack<Integer> stackInt2 = new ArrayStack<Integer>();
        String str =getSortResult(stackString) + getSortResult(stackInt) + getSortResult(stackInt2);
        return str.equals("truetruefalse");*/
         return false;
    }

    public static String getSortResult(Object stack) {
        Field field;
        Object[] objects = null;
        String a = null;
        String b = null;
        try {
            field = klass.getDeclaredField("datas");
            field.setAccessible(true);
            objects = (Object[]) field.get(stack);
            a = Arrays.toString(objects);
            klass.getMethod("sort").invoke(stack);
            objects = (Object[]) field.get(stack);
            b = Arrays.toString(objects);
         //   Arrays.sort(objects, 0, stack.size());
        } catch (Exception exception) {

        }
        return (!a.equalsIgnoreCase(b) && objects[0].hashCode() < objects[1].hashCode() && objects[1].hashCode() < objects[2].hashCode() && objects[2].hashCode() < objects[3].hashCode()) + "";
    }
}