package com.ysy.jvm1;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName HeapOOM
 * @Description -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError （堆固定大小为20M,在出现OOM之后，生成快照，以便分析）
 *              直接在后台输入jvisualvm，打开java visualvm工具，将快照信息导入到软禁
 * @Author ysy
 * @Date 2020/7/10 11:27
 **/
public class HeapOOM {

    static class OOMObjtct{

    }

    public static void main(String[] args) {
        List<OOMObjtct> lists = new ArrayList<OOMObjtct>();

        while (true){
            //一直创建对象，存入list中，对象一直被使用，一直可达
            lists.add(new OOMObjtct());
        }
    }
}
