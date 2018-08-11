package GUI.Frame;

import GUI.Listener.ClientListener;
import Util.GUIUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Vector;


public class ClientFrame extends JFrame{
    {
        GUIUtil.useLNF();
    }
    private static final ClientFrame frame = new ClientFrame();
    public static ClientFrame getInstance()
    {
        return frame;
    }

    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    public JTextField field1;
    public JTextField field2;
    public JTextField field4;
    public JTextField field3;
    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JTextArea area1;
    public JList<String> list;
    JScrollPane scrollPane1;
    JScrollPane scrollPane2;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    JPanel panel5;
    public Vector<String> vector = new Vector<>();

    private ClientFrame()
    {
        label1 = new JLabel("端口号：");
        label2 = new JLabel("客户端名称：");
        label3 = new JLabel("IP地址：");
        label4 = new JLabel("用户列表");
        field1 = new JTextField(5);
        field2 = new JTextField(5);
        field2.setText(MainFrame.username);
        field3 = new JTextField(20);
        field4 = new JTextField(5);
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
        scrollPane1.setViewportView(list);
        scrollPane2 = new JScrollPane();

        scrollPane2.setViewportView(area1);
        panel1 = new JPanel();
        panel2 = new JPanel(new BorderLayout());
        panel3 = new JPanel();
        panel4 = new JPanel(new BorderLayout());
        panel5 = new JPanel(new BorderLayout());
        panel1 = new JPanel();
        panel2 = new JPanel(new BorderLayout());
        panel3 = new JPanel();
        panel4 = new JPanel(new BorderLayout());
        panel5 = new JPanel(new BorderLayout());
        panel1.add(label3);
        panel1.add(field4);
        panel1.add(label1);
        panel1.add(field1);
        panel1.add(label2);
        panel1.add(field2);
        panel1.add(button1);
        panel1.add(button2);
        panel4.add(label4,BorderLayout.NORTH);
        panel4.add(scrollPane1);
        panel5.add(scrollPane2,BorderLayout.CENTER);
        panel2.add(panel4,BorderLayout.WEST);
        panel2.add(panel5,BorderLayout.CENTER);
        panel3.add(field3);
        panel3.add(button3);

        this.add(panel1,BorderLayout.NORTH);
        this.add(panel2,BorderLayout.CENTER);
        this.add(panel3,BorderLayout.SOUTH);
        this.setBounds(750,200,600,400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        addListener();
    }

    public void addListener()
    {
        button1.addActionListener(new ClientListener());
        button2.addActionListener(new ClientListener());
        button3.addActionListener(new ClientListener());
    }

    public void update()
    {
        list.setListData(vector);
    }

    public static void main(String[] args) {
        getInstance();
    }
}
