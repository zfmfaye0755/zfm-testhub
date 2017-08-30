package main.java.SocketTestPag;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args){
        Socket socket = null;
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;

        try{
            socket = new Socket("localhost",2000);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            printWriter = new PrintWriter(socket.getOutputStream(),true);

            //向服务器发送数据
            printWriter.println("Hello 我是发送给服务器的");
            String s = null;
            while (true){

                s = bufferedReader.readLine();
                if(s!=null){
                    break;
                }


            }
            System.out.println(s);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(bufferedReader!= null){
                    bufferedReader.close();
                }
                if(printWriter!=null){
                    printWriter.close();
                }

                if(socket != null){
                    socket.close();
                }

            }catch (Exception w){
                w.printStackTrace();
            }
        }

    }
}
