package com.ysy.thread;

import java.beans.FeatureDescriptor;
import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName MyTest10
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/8 15:26
 **/
public class MyTest10 {

    private ExecutorService service;
    final private AtomicLong pendingFileVistis = new AtomicLong();
    final private AtomicLong totalSize = new AtomicLong();
    final private CountDownLatch latch = new CountDownLatch(1);

    private void updateTotalSizeOfFilesInDir(final File file){
        long fileSize = 0;
        if (file.isFile()){
            fileSize = file.length();
        }else {
            final File[] children = file.listFiles();
            if (children != null){
                for (File child : children) {
                    if (child != null){
                        fileSize += child.length();
                    }else {
                        pendingFileVistis.incrementAndGet();
                        service.execute(new Runnable() {
                            @Override
                            public void run() {
                                updateTotalSizeOfFilesInDir(child);
                            }
                        });
                    }
                }
            }
        }
        totalSize.addAndGet(fileSize);
        if (pendingFileVistis.decrementAndGet() == 0) latch.countDown();
    }

    private long getTotalSizeOfFile(final String fileName) throws InterruptedException {
        service = Executors.newFixedThreadPool(100);
        pendingFileVistis.incrementAndGet();

        try {
            updateTotalSizeOfFilesInDir(new File(fileName));
            latch.await(100, TimeUnit.SECONDS);
            return totalSize.longValue();
        } finally {
            service.shutdown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final long start = System.nanoTime();
        final long total = new MyTest10().getTotalSizeOfFile("E:\\workspace\\space2");
        final long end = System.nanoTime();
        System.out.println("Total Size: " + total);
        System.out.println("Time Taken: " + (end - start)/1.0e9);
    }
}
