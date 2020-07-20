package com.ysy.jvm1;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OOMObject
 * @Description TODO
 * @Author ysy
 * @Date 2020/7/14 12:19
 **/
public class OOMObject {
    static class Test{
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<Test> list = new ArrayList<Test>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new Test());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }
}


