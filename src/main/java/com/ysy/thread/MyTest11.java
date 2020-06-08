package com.ysy.thread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @ClassName MyTest11
 * @Description 使用Fork-Join
 * @Author ysy
 * @Date 2020/6/8 16:12
 **/
public class MyTest11 {

    private final static ForkJoinPool forkJoinPool = new ForkJoinPool();

    private static class FileSizeFinder extends RecursiveTask<Long>{
        final File file;

        public  FileSizeFinder(final File file){
            this.file = file;
        }

        @Override
        protected Long compute() {
            long size = 0;
            if (file.isFile()){
                size += file.length();
            }else {
                final File[] children = file.listFiles();
                if (children != null){
                    List<ForkJoinTask<Long>> tasks = new ArrayList<ForkJoinTask<Long>>();
                    for (File child : children) {
                        if (child.isFile()){
                            size += child.length();
                        }else {
                            tasks.add(new FileSizeFinder(child));
                        }
                    }
                    for (ForkJoinTask<Long> task : invokeAll(tasks)) {
                        size += task.join();
                    }
                }
            }
            return size;
        }
    }

    public static void main(String[] args) {

        final long start = System.nanoTime();
        final long total = forkJoinPool.invoke(new MyTest11.FileSizeFinder(new File("E:\\workspace\\space2")));
        final long end = System.nanoTime();
        System.out.println("Total size: " + total);
        System.out.println("Time size: " + (end - start)/1.0e9);
    }
}
