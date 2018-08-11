package Util;

import GUI.Frame.ClientFrame;
import GUI.Frame.ServerFrame;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import Thread.AcceptThread;

public class SocketUtil {
    public static ServerSocket getServerSocket(String s,String name)  {
        ServerSocket socket = null;
        try{
            int port = Integer.parseInt(s);
            socket = new ServerSocket(port);
            JOptionPane.showMessageDialog(null,name+"启动成功");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,name+"启动失败");
        }
        return socket;
    }

    public static Socket getSocket(String ip,String s,String name)  {
        Socket socket = null;
        try {
            int port = Integer.parseInt(s);
            socket = new Socket(ip,port);
            JOptionPane.showMessageDialog(null,name+"已上线!");
        } catch (UnknownHostException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"连接失败");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"连接失败");
        }
        return socket;
    }

    public static void sendByClient(Socket socket, ClientFrame frame)
    {
        String text,name,target;
        name = frame.field2.getText();
        text = frame.field3.getText();
        target = frame.list.getSelectedValue();
        try {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream),true);
            if(frame.list.getSelectedIndex()==0)
            {
                writer.println("3");
                writer.println(name);
                writer.println(text);
                String content = "你对大家说：" + "\r\n" + text + "\r\n";
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        frame.area1.append(content);
                    }
                });
            }
            else
                {
                writer.println("5");
                writer.println(target);
                writer.println(name);
                writer.println(text);
                    String content = "你对"+target+"说：" + "\r\n" + text + "\r\n";
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            frame.area1.append(content);
                        }
                    });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendByServer(ServerFrame frame)
    {
        ArrayList<Socket> list = AcceptThread.list;
        String name,text,target;
        name = frame.field2.getText();
        text = frame.field3.getText();
        target = frame.list.getSelectedValue();
        if (target==null||target.equals(frame.field2.getText()))
        {
            String content =  "你对大家说：" + "\r\n" + text + "\r\n";
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.area1.append(content);
                }
            });
        }
        else
        {
            String content = "你对"+target+"说：" + "\r\n" + text + "\r\n";
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.area1.append(content);
                }
            });
        }
        for (Socket socket:
             list) {
            try {
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream),true);

                 if (target==null||target.equals(frame.field2.getText()))
                {
                writer.println("2");
                writer.println(name);
                writer.println(text);
                }
                else
                {
                    writer.println("3");
                    writer.println(target);
                    writer.println(name);
                    writer.println(text);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
