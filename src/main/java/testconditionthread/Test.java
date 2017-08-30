package main.java.testconditionthread;

import java.io.*;
import java.util.Random;

public class Test {
    private static final int count = 10000;
    private static final int threadGroupCount = 5;
    private static final String inputFile = "F:/testInput.txt";

    public static void generateTestFile() throws IOException {
        //用随机数生成10000个测试数据放到文件中
        PrintWriter pw = new PrintWriter(new FileWriter(new File(inputFile)),true);
        Random random = new Random();
        for(int i=0;i<count;i++){
            pw.write(Math.abs(random.nextInt())%count+",");
        }
        pw.flush();
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        generateTestFile();
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String str = reader.readLine();
        reader.close();
        String strs[] = str.split(",");
        System.out.println(strs.length+"===========");
        int index = 0;
        //为了简单，每个文件的输出数字个数相同
        int countForeachFile = count/threadGroupCount;
        for(int i=0;i<threadGroupCount;i++){
            int[] records = new int[countForeachFile];
            for(int j=0;j<countForeachFile;j++){
                records[j] = Integer.parseInt(strs[index]);
                index++;
            }

            PrintGroup group = new PrintGroup(records,i);
            group.startPrint();
        }
    }
}
