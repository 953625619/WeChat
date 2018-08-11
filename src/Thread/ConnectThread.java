package Thread;

import GUI.Frame.ClientFrame;
import Util.SocketUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectThread extends Thread {
    ClientFrame frame = ClientFrame.getInstance();
     public static Socket socket;
    public ConnectThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream),true);
            writer.println("2");
            writer.println(frame.field2.getText());
            new Thread(new ReceiveClientThread(socket)).start();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
