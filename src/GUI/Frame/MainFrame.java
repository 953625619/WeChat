package GUI.Frame;

import GUI.Listener.MainListener;
import Util.GUIUtil;
import entity.Config;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    {
        GUIUtil.useLNF();
    }
    public static String username;
    public Config config = new Config();
    private static final MainFrame frame = new MainFrame();
    public static MainFrame getInstance()
    {
        return frame;
    }

    JLabel label1;
    JLabel label2;
    public JTextField field1;
    public JTextField field2;
    public JButton button3;
    public JButton button4;
    JPanel panel1;
    JPanel panel2;
    JPanel panel4;
    private MainFrame()
    {
        label1 = new JLabel("用户名:");
        label2 = new JLabel("密码  :");
        field1 = new JTextField(5);
        field2 = new JTextField(5);
         button3 = new JButton("确定");
         button4 = new JButton("取消");
         panel1 = new JPanel();
         panel2 = new JPanel();
         panel4 = new JPanel();
         panel1.add(label1);
         panel1.add(field1);
         panel2.add(label2);
         panel2.add(field2);
         panel4.add(button3);
         panel4.add(button4);

         addListener();

         this.setLayout(new GridLayout(4,1));
        this.add(panel1);
        this.add(panel2);
        this.add(panel4);
        this.setBounds(300,300,300,200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);


    }

    public void addListener()
    {
        button3.addActionListener(new MainListener());
        button4.addActionListener(new MainListener());
    }
    public static void main(String[] args) {
        getInstance();
    }
}
