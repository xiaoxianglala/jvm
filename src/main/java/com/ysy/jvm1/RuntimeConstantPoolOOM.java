package com.ysy.jvm1;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RuntimeConstantPoolOOM
 * @Description -XX:PermSize=10M -XX:MaxPermSize=10M
 * @Author ysy
 * @Date 2020/7/10 13:10
 **/
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        //使用list保持这常量池的引用，避免full GC回收常量池的行为
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }
}
