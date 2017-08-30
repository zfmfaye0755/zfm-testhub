package main.java.classloaderdemo;

import javax.print.DocFlavor;

public class TestRuntime{
    public static void main(String[] args){
        //得到JVM中的空闲内存量（单位是字节）
        System.out.println(Runtime.getRuntime().freeMemory());

        //得到JVM内存总量（单位是字节）
        System.out.println(Runtime.getRuntime().totalMemory());

        //JVM试图使用的最大内存量
        System.out.println(Runtime.getRuntime().maxMemory());

        //可用处理器的数目
        System.out.println(Runtime.getRuntime().availableProcessors());

    }
}
