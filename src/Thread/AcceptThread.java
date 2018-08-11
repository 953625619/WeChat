package Thread;

import GUI.Frame.ServerFrame;
import Util.SocketUtil;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class AcceptThread extends Thread {
    public static boolean flag = false;
    public static ArrayList<Socket> list = new ArrayList<>();
    public static ServerSocket serverSocket;
    int num = 1;
    ServerFrame serverFrame = ServerFrame.getInstance();

    public AcceptThread(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
       while (flag)
       {
           try {
                     Socket socket = serverSocket.accept();
                     new Thread(new ReceiveServerThread(socket)).start();
                     list.add(socket);
                   OutputStream outputStream = socket.getOutputStream();
                   PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream),true);
                   writer.println("4");
                   writer.println(serverFrame.field2.getText());


           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }

}
