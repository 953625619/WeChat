package Thread;

import GUI.Frame.ClientFrame;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveClientThread extends Thread {
    public static Socket socket;
    public ReceiveClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
        ClientFrame clientFrame = ClientFrame.getInstance();
        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String name;
            while (true)
            {
                String tem = "";
                if((tem = reader.readLine())!=null)
                {

                    String text = reader.readLine();
                if(tem.equals("1"))
                {
                    JOptionPane.showMessageDialog(null,"服务器已关闭");
                }
                if(tem.equals("2"))
                {   name = text;
                    text = reader.readLine();
                    if(name.equals(clientFrame.field2.getText()))
                        continue;
                    String content =name+"对大家说："+"\r\n"+text+"\r\n";
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            clientFrame.area1.append(content);
                        }
                    });
                }
                if(tem.equals("3"))
                {    String target = text;
                    String myname = clientFrame.field2.getText();
                    if(target.equals(myname))
                    {
                        name = reader.readLine();
                        text = reader.readLine();
                        String content =name+"对你说："+"\r\n"+text+"\r\n";
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                clientFrame.area1.append(content);
                            }
                        });
                    }
                }
                if (tem.equals("4"))
                {
                    name = text;
                    if(clientFrame.field2.getText().equals(name)||clientFrame.vector.contains(name))
                        continue;
                    clientFrame.vector.add(name);
                    clientFrame.update();
                    clientFrame.list.setSelectedIndex(0);
                }
                if(tem.equals("5"))
                {
                    clientFrame.vector.remove(text);
                    clientFrame.update();
                }
             }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
