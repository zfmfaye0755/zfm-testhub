package main.java.hashmaptestdemo;

import java.util.*;

public class Test {

    public static void tranversel(Map<String,String> map){
        for(Map.Entry<String,String>entry:map.entrySet()){
            System.out.println(entry.getKey() + ","+entry.getValue());
        }
    }

    public static void tranversel2(Map<String,String> map){
        Iterator<Map.Entry<String,String>> iterator =map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,String> entry = iterator.next();
            System.out.println(entry.getKey() + "," + entry.getValue());
        }
    }

    public static void tranversel3(Map<String,String> map){
        for(String key:map.keySet()){
            System.out.println(key+","+map.get(key));
        }
    }

    public static void tranversel4(Map<String ,String> map){
        Set<Map.Entry<String,String>>  entrySet = map.entrySet();
        for(Map.Entry<String,String>entry : entrySet){
            System.out.println(entry.getKey()+"," + entry.getValue());
        }
    }

    public static void main(String[] args){
        Map<String,String> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");

        tranversel(map);
        tranversel2(map);
        tranversel3(map);
        tranversel4(map);
    }
}
