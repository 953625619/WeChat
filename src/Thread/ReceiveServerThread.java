package Thread;

import GUI.Frame.ServerFrame;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class ReceiveServerThread extends Thread {

    Socket socket;
    public ReceiveServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ServerFrame serverFrame = ServerFrame.getInstance();
            InputStream inputStream =  socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String name="";
            while (true)
            {   String tem ="";
                if((tem = reader.readLine())!=null) {
                    String text = reader.readLine();
                    if (tem.equals("1")) {
                        name = text;
                        serverFrame.vector.remove(name);
                        serverFrame.update();
                        AcceptThread.list.remove(socket);
                        if (socket != null)
                            socket.close();
                        JOptionPane.showMessageDialog(null, name + "已退出");
                        for (Socket socket:
                             AcceptThread.list) {
                            OutputStream outputStream = socket.getOutputStream();
                            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream),true);
                            writer.println("5");
                            writer.println(name);
                        }
                        continue;
                    }
                    if (tem.equals("2")) {
                        name = text;
                        serverFrame.vector.add(name);
                        serverFrame.update();
                        JOptionPane.showMessageDialog(null, name + "已连接");
                        for (Socket socket :
                                AcceptThread.list) {
                            try {
                                OutputStream outputStream = socket.getOutputStream();
                                PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream),true);
                                writer.println("4");
                                writer.println(name);
                                for (String s:
                                     serverFrame.vector) {
                                    writer.println("4");
                                    writer.println(s);
                                }

                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                    if (tem.equals("3")) {
                        name = text;
                        text = reader.readLine();
                        String content =name+"对大家说："+"\r\n"+text+"\r\n";
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                serverFrame.area1.append(content);
                            }
                        });
                        for (Socket socket :
                                AcceptThread.list) {
                            OutputStream outputStream = socket.getOutputStream();
                            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream),true);
                            writer.println("2");
                            writer.println(name);
                            writer.println(text);
                        }
                    }
                    if (tem.equals("4")) {
                        name = reader.readLine();
                        text = reader.readLine();
                        String content =name+"对你说："+"\r\n"+text+"\r\n";
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                serverFrame.area1.append(content);
                            }
                        });
                    }
                    if (tem.equals("5")) {
                        String target = text;
                        name = reader.readLine();
                        text = reader.readLine();
                        for (Socket socket :
                                AcceptThread.list) {
                            OutputStream outputStream = socket.getOutputStream();
                            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream),true);
                            writer.println("3");

                            writer.println(target);

                            writer.println(name);

                            writer.println(text);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
