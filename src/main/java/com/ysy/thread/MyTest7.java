package com.ysy.thread;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName MyTest7
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/4 17:50
 **/
public class MyTest7 extends AbstarctPrimeFinder {
    @Override
    protected long countPrimes(int number) {
        return countPrimesInRange(1, number);
    }

    public static void main(String[] args) {
        new MyTest7().timeAndCompute(10000000);
    }
}

abstract class AbstarctPrimeFinder {
    //判断一个数是否是=素数
    public boolean isPrime(final int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    //统计一个范围内所有素数的个数
    public int countPrimesInRange(final int lower, final int upper) {
        int total = 0;

        for (int i = lower; i <= upper; i++) {
            if (isPrime(i)) {
                total++;
            }
        }
        return total;
    }

    public void timeAndCompute(final int number) {
        final long start = System.nanoTime();

        final long numberOfPrimes = countPrimes(number);

        final long end = System.nanoTime();

        System.out.printf("Number of primes under %d is %d\n", number, numberOfPrimes);
        System.out.println("time (seconds) taken is " + (end - start) / 1.0e9);
    }

    protected abstract long countPrimes(int number);
}


/*
 * @Author ysy
 * @Description 并发计算素数个数
 * @Date 2020/6/4 18:23
 * @Param
 * @return
 **/
class ConcurrentPrimeFinder extends AbstarctPrimeFinder {

    private final int poolSize;
    private final int numberOfParts;

    public ConcurrentPrimeFinder(final int thePoolSize, final int theNumberOfParts) {
        this.poolSize = thePoolSize;
        this.numberOfParts = theNumberOfParts;
    }

    @Override
    protected long countPrimes(int number) {
        int count = 0;
        try {
            final List<Callable<Integer>> partitions = new ArrayList<Callable<Integer>>();
            final int chunksPerPartiton = number / numberOfParts;
            //创建两个任务，然后并发执行这两个任务
            for (int i = 0; i < numberOfParts; i++) {
                final int lower = (i * chunksPerPartiton) + 1;
                final int upper = (i == numberOfParts - 1) ? number : lower + chunksPerPartiton - 1;
                partitions.add(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return countPrimesInRange(lower, upper);
                    }
                });
            }
            final ExecutorService executorPool = Executors.newFixedThreadPool(poolSize);
            final List<Future<Integer>> resultFromParts = executorPool.invokeAll(partitions, 10000, TimeUnit.SECONDS);
            executorPool.shutdown();

            for (Future<Integer> resultFromPart : resultFromParts) {
                count += resultFromPart.get();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return count;
    }

    public static void main(final String[] args) {
        new ConcurrentPrimeFinder(2, 2).timeAndCompute(10000000);
    }
}


