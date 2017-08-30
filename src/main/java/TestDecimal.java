package main.java;

import java.math.BigDecimal;

public class TestDecimal {

    public static void main(String[] args){
        double d1 = 2.15;
        double d2 = 1.10;

        System.out.println("double 类型运行结果" + (d1 - d2));


        BigDecimal bd1 = new BigDecimal("2.15");//字符串类型的构造方法才有用
        BigDecimal bd2 = new BigDecimal("1.10");

        System.out.println("BigDecimal 类型的运行结果" + bd1.subtract(bd2));


        BigDecimal bd3= new BigDecimal(2.15);
        BigDecimal bd4= new BigDecimal(1.10);

        System.out.println("BigDecimal不用String类型的构造方法的结果：" + bd3.subtract(bd4));
    }
}
