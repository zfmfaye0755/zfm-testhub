package main.java.currenthashmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestCurrentHashMap {
    public static  void main(String[] args){
        int threadNumber = 1;
        System.out.println("单线程运行结果：");
        for(int i=0;i<5;i++){
            System.out.println("第"+(i+1)+"次运行结果："+testAdd(threadNumber));
        }

        threadNumber = 5;
        System.out.println("多线程运行结果:");
        for(int i=0;i<5;i++){
            System.out.println("第"+(i+1)+"次运行结果："+testAdd(threadNumber));
        }
    }

    public static int testAdd(int threadNumber){
        ConcurrentHashMap<Integer,Integer> map = new ConcurrentHashMap<Integer, Integer>();
        map.put(1,0);
        ExecutorService pool = Executors.newCachedThreadPool();
        for(int i=0;i<threadNumber;i++){
            pool.execute(new TestTask(map));
        }
        pool.shutdown();

        try{
            pool.awaitTermination(20, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return map.get(1);
    }
}
