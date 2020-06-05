package com.ysy.thread;


import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName MyTest8
 * @Description 计算给定文件夹下的文件数
 * @Author ysy
 * @Date 2020/6/5 16:07
 **/
public class MyTest8 {

    private long getTotalSizeFilesInDir(final File file){
        if (file.isFile()) {
            return file.length();
        }

        final File[] children = file.listFiles();
        long total = 0;
        if (children != null) {
            for (File child : children) {
                //递归计算
                total += getTotalSizeFilesInDir(child);
            }
        }
        return total;
    }

    public static void main(String[] args) {
        final long start = System.nanoTime();
        final long total = new MyTest8().getTotalSizeFilesInDir(new File("E:\\workspace\\space2\\zjky\\ysy-jvm"));
        final long end = System.nanoTime();
        System.out.println("Total Size: " + total);
        System.out.println("Time taken: " + (end - start)/1.0e9);
    }
}

class NaivelyConcurrentTotalFileSize{
    private long getTotalSizeOfFilesDir(final ExecutorService service,File file) throws InterruptedException, ExecutionException, TimeoutException {
        if (file.isFile()){
            return file.length();
        }

        long total = 0;
        final File[] children = file.listFiles();
        if (children != null) {
            final List<Future<Long>> partialTotalFutures = new ArrayList<Future<Long>>();
            for (File child : children) {
                partialTotalFutures.add(service.submit(new Callable<Long>() {
                    @Override
                    public Long call() throws Exception {
                        return getTotalSizeOfFilesDir(service,child);
                    }
                }));
            }
            for (Future<Long> partialTotalFuture : partialTotalFutures) {
                total += partialTotalFuture.get(100, TimeUnit.SECONDS);
            }
        }
        return total;
    }

    private long getTotalSizeOfFile(final String fileName) throws InterruptedException, ExecutionException, TimeoutException {
        final ExecutorService service = Executors.newFixedThreadPool(100);
        try {
            return getTotalSizeOfFilesDir(service,new File(fileName));
        }finally {
            service.shutdown();
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        final long start = System.nanoTime();
        final long total = new NaivelyConcurrentTotalFileSize().getTotalSizeOfFile("E:\\workspace\\space2\\zjky\\ysy-jvm\\src");
        final long end = System.nanoTime();
        System.out.println("Total Size: " + total);
        System.out.println("Time Taken: " + (end - start)/1.0e9);
    }
}
