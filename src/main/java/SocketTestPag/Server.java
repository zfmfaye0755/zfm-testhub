package main.java.SocketTestPag;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        BufferedReader bufferedReader = null;

        PrintWriter printWriter = null;

        ServerSocket serverSocket = null;

        try{

            serverSocket = new ServerSocket(2000);
            Socket socket  = serverSocket.accept();

            //获取输入流；
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //获取输出流
            printWriter = new PrintWriter(socket.getOutputStream(),true);

            String s = bufferedReader.readLine();//获取接收的数据
            printWriter.println(s);//发送相同的数据给客户端

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if(printWriter != null){
                    printWriter.close();
                }

                if(serverSocket != null){
                    serverSocket.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
