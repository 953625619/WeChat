package GUI.Frame;

import GUI.Listener.ServerListener;
import Util.CenterPanel;
import Util.GUIUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Vector;

public class ServerFrame extends JFrame {
    {
        GUIUtil.useLNF();
    }
    private static final ServerFrame frame = new ServerFrame();
    public static ServerFrame getInstance()
    {
        return frame;
    }

    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    public JTextField field1;
    public JTextField field2;
    public JTextField field3;
    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JList<String> list;
    public JTextArea area1;
    JScrollPane scrollPane1;
    JScrollPane scrollPane2;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    JPanel panel5;
    public Vector<String> vector = new Vector<>();

    private ServerFrame()
    {
        label1 = new JLabel("端口号：");
        label2 = new JLabel("服务器名称：");
        label3 = new JLabel("用户列表");
        label4 = new JLabel("聊天记录");
        field1 = new JTextField(5);
        field2 = new JTextField(5);
        field3 = new JTextField(20);
        button1 = new JButton("连接");
        button2 = new JButton("关闭");
        button3 = new JButton("发送");
        area1 = new JTextArea(5,7);
        area1.setLineWrap(true);
        area1.setWrapStyleWord(true);
        area1.setEditable(false);
        area1.setBorder(new TitledBorder("聊天记录"));
        list = new JList();
        scrollPane1 = new JScrollPane();
        scrollPane2 = new JScrollPane();
        scrollPane1.setViewportView(list);
        scrollPane2.setViewportView(area1);
        panel1 = new JPanel();
        panel2 = new JPanel(new BorderLayout());
        panel3 = new JPanel();
        panel4 = new JPanel(new BorderLayout());
        panel5 = new JPanel(new BorderLayout());
        panel1.add(label1);
        panel1.add(field1);
        panel1.add(label2);
        panel1.add(field2);
        panel1.add(button1);
        panel1.add(button2);
        panel4.add(label3,BorderLayout.NORTH);
        panel4.add(scrollPane1);
        panel5.add(scrollPane2,BorderLayout.CENTER);
        panel2.add(panel4,BorderLayout.WEST);
        panel2.add(panel5,BorderLayout.CENTER);
        panel3.add(field3);
        panel3.add(button3);

        this.add(panel1,BorderLayout.NORTH);
        this.add(panel2,BorderLayout.CENTER);
        this.add(panel3,BorderLayout.SOUTH);
        this.setBounds(200,200,500,400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        addListener();
    }

    public void addListener()
    {
        button1.addActionListener(new ServerListener());
        button2.addActionListener(new ServerListener());
        button3.addActionListener(new ServerListener());
    }

    public void update()
    {
        list.setListData(vector);
    }

    public static void main(String[] args) {
        getInstance();
    }
}
