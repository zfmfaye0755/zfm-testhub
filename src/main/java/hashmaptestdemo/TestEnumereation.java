package main.java.hashmaptestdemo;

import java.util.*;

public class TestEnumereation {

    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        Iterator<String> iterable = list.iterator();

        while (iterable.hasNext()){
            if(iterable.next().equals("b")){
                iterable.remove();
            }
        }

        Enumeration<String> enumeration = Collections.enumeration(list);

        while(enumeration.hasMoreElements()){
            System.out.println(enumeration.nextElement());
        }
    }



}
