package GUI.Listener;

import GUI.Frame.ServerFrame;
import Util.GUIUtil;
import Util.SocketUtil;
import Thread.AcceptThread;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ServerFrame serverFrame = ServerFrame.getInstance();
        JButton button = (JButton) e.getSource();

        if (button == serverFrame.button1) {
            if(!(GUIUtil.checkEmpty(serverFrame.field1,"端口")||GUIUtil.checkEmpty(serverFrame.field2,"名字")))
                return;
            AcceptThread acceptThread = new AcceptThread(SocketUtil.getServerSocket(serverFrame.field1.getText(), serverFrame.field2.getText()));
            AcceptThread.flag = true;
            new Thread(acceptThread).start();
            serverFrame.button1.setEnabled(false);
            serverFrame.button2.setEnabled(true);
            serverFrame.vector.add(serverFrame.field2.getText());
            serverFrame.update();
        }
        if (button == serverFrame.button2) {
            AcceptThread.flag = false;
            for (Socket socket :
                    AcceptThread.list) {
                try {
                    OutputStream outputStream = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream),true);
                    writer.println("1");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            AcceptThread.list.clear();
            try {
                AcceptThread.serverSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "服务器已关闭");
            serverFrame.vector.clear();
            serverFrame.update();
            serverFrame.field3.setText("");
            serverFrame.button2.setEnabled(false);
            serverFrame.button1.setEnabled(true);
        }
        if (button == serverFrame.button3) {
            SocketUtil.sendByServer(serverFrame);
        }
    }
}