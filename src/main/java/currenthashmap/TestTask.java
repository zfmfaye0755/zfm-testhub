package main.java.currenthashmap;

import java.util.concurrent.ConcurrentHashMap;

public class TestTask implements Runnable{
    private ConcurrentHashMap<Integer,Integer> map;
    public TestTask(ConcurrentHashMap<Integer,Integer> map){
        this.map = map;
    }

    @Override
    public void run() {

        for(int i=0;i<100;i++){
            map.put(1,map.get(1)+1);
        }
    }
}
