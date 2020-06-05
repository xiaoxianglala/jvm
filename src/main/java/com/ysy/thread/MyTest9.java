package com.ysy.thread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName MyTest9
 * @Description 避免死锁的并发计算
 * @Author ysy
 * @Date 2020/6/5 17:16
 **/
public class MyTest9 {

    static class SubDirectoriesAndSize{
        final public long size;
        final public List<File> subDirectories;
        public SubDirectoriesAndSize(final long totalSize,final List<File> theSubDirs){
            this.size = totalSize;
            this.subDirectories = theSubDirs;
        }
    }

    private SubDirectoriesAndSize getTotalAndSubDirs(final File file){
        long total = 0;
        final List<File> subDirectories = new ArrayList<File>();
        if (file.isDirectory()) {
            final File[] children = file.listFiles();
            if (children != null){
                for (File child : children) {
                    if (child.isFile()){
                        total += child.length();
                    }else {
                        subDirectories.add(child);
                    }
                }
            }
        }
        return new SubDirectoriesAndSize(total,subDirectories);
    }

    private long getTotalSizeOfFilesInDir(final File file) throws InterruptedException, ExecutionException, TimeoutException {
        final ExecutorService service = Executors.newFixedThreadPool(100);
        try {
            long total = 0;
            final List<File> directories = new ArrayList<File>();
            directories.add(file);
            while (!directories.isEmpty()){
                final List<Future<SubDirectoriesAndSize>> partialResults = new ArrayList<Future<SubDirectoriesAndSize>>();
                for (File directory : directories) {
                    partialResults.add(service.submit(new Callable<SubDirectoriesAndSize>() {
                        @Override
                        public SubDirectoriesAndSize call() throws Exception {
                            return getTotalAndSubDirs(directory);
                        }
                    }));
                }
                directories.clear();
                for (Future<SubDirectoriesAndSize> partialResult : partialResults) {
                    final SubDirectoriesAndSize subDirectoriesAndSize = partialResult.get(100, TimeUnit.SECONDS);
                    directories.addAll(subDirectoriesAndSize.subDirectories);
                    total += subDirectoriesAndSize.size;
                }
            }
            return total;
        }finally {
            service.shutdown();
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        final long start = System.nanoTime();
        final long total = new MyTest9().getTotalSizeOfFilesInDir(new File("E:\\workspace\\space2"));
        final long end = System.nanoTime();
        System.out.println("Total Size: " + total);
        System.out.println("Time Taken: " + (end - start)/1.0e9);
    }
}
