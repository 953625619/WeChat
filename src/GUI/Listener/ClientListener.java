package GUI.Listener;

import GUI.Frame.ClientFrame;
import GUI.Frame.ServerFrame;
import Thread.*;
import Util.GUIUtil;
import Util.SocketUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ClientFrame frame = ClientFrame.getInstance();
        JButton button = (JButton)e.getSource();
        if(button == frame.button1)
        {
            if(!(GUIUtil.checkEmpty(frame.field1,"端口")||GUIUtil.checkEmpty(frame.field2,"名字")||GUIUtil.checkEmpty(frame.field4,"IP地址")))
                return;
            Socket socket  = SocketUtil.getSocket(frame.field4.getText(),frame.field1.getText(),frame.field2.getText());
            ConnectThread  connectThread = new ConnectThread(socket);
            new Thread(connectThread).start();
            frame.button1.setEnabled(false);
            frame.button2.setEnabled(true);
        }
         if (button == frame.button2)
        {
            try {
                OutputStream outputStream = ConnectThread.socket.getOutputStream();
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream),true);
                writer.println("1");
                writer.println(frame.field2.getText());
                ConnectThread.socket.close();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"客户端已关闭");
            frame.vector.clear();
            frame.update();
            frame.field3.setText("");
            frame.button2.setEnabled(false);
            frame.button1.setEnabled(true);

        }
        if(button == frame.button3)
        {

            SocketUtil.sendByClient(ConnectThread.socket,frame);
        }
    }
}
