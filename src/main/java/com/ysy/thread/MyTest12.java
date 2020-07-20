package com.ysy.thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName MyTest12
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/8 16:42
 **/
public class MyTest12 {

    private static void useMap(final Map<String,Integer> scores){
        scores.put("Fred",12);
        scores.put("Sara",16);

        try {
            for (String key : scores.keySet()) {
                System.out.println(key + " score " + scores.get(key));
                scores.put("Joe",11);
            }
        }catch (Exception e){
            System.out.println("Failed: " + e);
        }

        System.out.println("Number of elements in the map: " + scores.keySet().size());
    }

    public static void main(String[] args) {
        System.out.println("Using Plain Vanilla HashMap");
        useMap(new HashMap<String, Integer>());

        System.out.println("Using Synchronized HashMap");
        useMap(Collections.synchronizedMap(new HashMap<String,Integer>()));

        System.out.println("Using Concurrent HashMap");
        useMap(new ConcurrentHashMap<String, Integer>());
    }
}
