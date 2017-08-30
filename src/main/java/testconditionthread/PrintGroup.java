package main.java.testconditionthread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintGroup {
    //这个线程组输出数字的个数
    private static volatile int count = 0;//目前已经输出的个数
    private Lock lock = new ReentrantLock();//这个线程组的一个锁
    private Condition oddLock = lock.newCondition();
    private Condition evenLock = lock.newCondition();
    //这个线程组需要输出的数字数组
    private int records[];
    //这个线程组需要把数字输出到同一个文件，因此，共享一个writer
    //由于任意时刻只会有一个线程写文件，因此，不需要同步
    private PrintWriter writer;
    //记录输出奇数所在的数组下标
    private volatile int oddIndex = 0;
    //记录输出偶数所在的数组下标
    private volatile int evenIndex = 0;
    //输出奇数的线程
    private OddPrintThread oddPrintThread;
    private EvenPrintThread evenPrintThread;

    private volatile boolean first = true;

    private int[] result = new int[2000];
    //当前写到文件数组的下标
    private int index = 0;

    public PrintGroup(int[] records,int id) throws IOException {
        this.records =records;
        this.writer = new PrintWriter(new FileWriter(new File("F:/output" + id+".txt")),true);
    }

    public void startPrint(){
        oddPrintThread = new OddPrintThread();
        evenPrintThread = new EvenPrintThread();
        oddPrintThread.start();
        evenPrintThread.start();

    }

    private class OddPrintThread extends Thread{
        public void run(){
            while(true){
                try {
                    lock.lock();
                    if (first) {
                        //第一次运行时，需要等待打印偶数的线程先执行
                        first = false;
                        evenLock.await();
                    }

                    for(int i=0;i<10;){
                        //数组中的偶数和奇数都打印完
                        if(oddIndex >= records.length&& evenIndex >= records.length){
                            writer.flush();
                            writer.close();
                            return;
                        }
                        //如果所有的奇数都打印完了，则不打印奇数，让打印偶数的线程有机会运行
                        if(oddIndex>=records.length){
                            break;
                        }
                        //把奇数输出到文件，并计数
                        if(records[oddIndex]%2==1){
                            i++;
                            writer.print(records[oddIndex]+" ");
                            result[index++] = records[oddIndex];
                            writer.flush();
                            addCount();
                        }
                        oddIndex++;
                    }
                    //打印完10个奇数后，通知打印偶数的线程开始运行
                    oddLock.signal();
                    //接着等待打印偶数的线程结束
                    evenLock.await();

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    oddLock.signal();
                    lock.unlock();
                }
            }
        }
    }

    private class EvenPrintThread extends Thread{

        public void run(){
                while (true) {
                    try{
                    //等待打印奇数的线程先运行。如果这个线程先运行调用evenLock.signal();
                    while (first) {
                        Thread.sleep(1);
                    }
                    lock.lock();
                    for(int i=0;i<10;){
                        if(oddIndex>=records.length&&evenIndex>=records.length){
                            String s="";
                            for(int k=0;k<2000;k++){

                                    s+=(result[k]+"");
                                }
                                writer.flush();
                                return;
                            }

                            if(evenIndex >= records.length){
                                break;
                            }

                            if(records[evenIndex]%2 == 0){
                                i++;
                                writer.print(records[evenIndex]+"");
                                result[index++] = records[evenIndex];
                                writer.flush();
                                addCount();
                            }
                            evenIndex++;
                        }
                        evenLock.signal();
                        oddLock.await();

                    }catch (Exception w){
                        w.printStackTrace();
                    }finally {
                        evenLock.signal();
                        lock.unlock();
                    }
                }

        }
    }

    private synchronized static void addCount(){
        count++;
        if(count%1000==0){
            System.out.println("已完成： "+count);
            if(count==10000){
                System.out.println("Done");
            }
        }
    }

}
