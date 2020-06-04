package com.ysy.thread;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName MyTest6
 * @Description TODO
 * @Author ysy
 * @Date 2020/6/4 15:28
 **/
public class MyTest6 {
    public static double getPrice(final String ticker) throws IOException {
        final URL url = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker);

        final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        final String discardHeader = reader.readLine();
        final String data = reader.readLine();
        final String[] dataItems = data.split(",");
        final double priceIsTheLastValue = Double.valueOf(dataItems[dataItems.length - 1]);
        return priceIsTheLastValue;
    }
}

abstract class AbstarctNAV{
    public static Map<String,Integer> readTickers() throws IOException {
        final BufferedReader reader = new BufferedReader(new FileReader("E:\\workspace\\space2\\zjky\\ysy-jvm\\src\\main\\resources\\stock.txt"));

        final Map<String,Integer> stocks = new HashMap<String, Integer>();

        String stockInfo = null;
        while ((stockInfo = reader.readLine()) != null) {
            final String[] stockInfoData = stockInfo.split(",");
            final String stockTicker = stockInfoData[0];
            final Integer quantity = Integer.valueOf(stockInfoData[1]);

            stocks.put(stockTicker,quantity);
        }

        return stocks;
    }

    public void timeAndComputeValue() throws IOException, ExecutionException, InterruptedException {
        final Long start = System.nanoTime();

        final Map<String,Integer> stocks = readTickers();
        final double nav = computeNetAsxsetValue(stocks);

        final Long end = System.nanoTime();

        final String value = new DecimalFormat("$##,##0.00").format(nav);
        System.out.println("Your net asset value is " + value);
        System.out.println("Time (seconds) taken " + (end - start)/1.0e9);
    }

    protected abstract double computeNetAsxsetValue(Map<String, Integer> stocks) throws IOException, InterruptedException, ExecutionException;
}

/**
 * @Author ysy
 * @Description //并发执行，提高速度
 * @Date 2020/6/4 17:18
 * @Param
 * @return
 **/
class ConcurrentNAV extends AbstarctNAV{

    @Override
    protected double computeNetAsxsetValue(Map<String, Integer> stocks) throws IOException, InterruptedException, ExecutionException {
        //获得计算机的核心处理器数
        final int numberOfCores = Runtime.getRuntime().availableProcessors();
        //阻塞系数
        final double blockingCoefficient = 0.9;
        //计算需要线程的大小
        final int poolSize = (int) (numberOfCores / (1- blockingCoefficient));

        System.out.println("Number of Cores available is " + numberOfCores);
        System.out.println("Pool size is " + poolSize);
        final List<Callable<Double>> partitions = new ArrayList<Callable<Double>>();

        //创建线程任务，存入list集合中
        for (String ticker : stocks.keySet()) {
            partitions.add(new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    return stocks.get(ticker) * MyTest6.getPrice(ticker);
                }
            });
        }

        //创建线程池
        final ExecutorService executorPool = Executors.newFixedThreadPool(poolSize);
        //并发的开始执行每个任务
        final List<Future<Double>> valueOfStocks = executorPool.invokeAll(partitions,10000, TimeUnit.SECONDS);

        double netAssetValue = 0.0;

        for (Future<Double> valueOfAStock : valueOfStocks) {
            netAssetValue += valueOfAStock.get();
        }

        executorPool.shutdown();
        return netAssetValue;
    }
}

class SequentialNAV extends AbstarctNAV{

    @Override
    protected double computeNetAsxsetValue(Map<String, Integer> stocks) throws IOException {
        double netAssetValue = 0.0;
        for (String ticker : stocks.keySet()) {
            netAssetValue += stocks.get(ticker) * MyTest6.getPrice(ticker);
        }
        return netAssetValue;
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        new ConcurrentNAV().timeAndComputeValue();
    }
}


