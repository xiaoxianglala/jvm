package com.ysy.extthread;

/**
 * @ClassName Test01
 * @Description TODO
 * @Author ysy
 * @Date 2020/7/20 10:16
 **/
public class Test01 {

    public static void test1(int b){
        b = 1 + b;
        System.out.println(b + "+++");
    }

    public static void test2(int[] b){
        b[0] = b[0] + 1;
        System.out.println(b[0] + "***");
    }

    public static void main(String[] args) {
        int a = 1;
        Test01.test1(1);
        System.out.println(a+"--");
        int[] c = {1,2};
        test2(c);
        System.out.println(c[0]+"===");
    }
}
