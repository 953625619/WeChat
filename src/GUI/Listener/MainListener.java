package GUI.Listener;

import DAO.ConfigDAO;
import GUI.Frame.ClientFrame;
import GUI.Frame.MainFrame;
import Util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame frame = MainFrame.getInstance();
        JButton button = (JButton)e.getSource();
        if(button == frame.button4)
            frame.dispose();
        else if(button == frame.button3)
        {
            ConfigDAO dao = new ConfigDAO();

            if(!(GUIUtil.checkEmpty(frame.field1,"用户名")&&GUIUtil.checkEmpty(frame.field2,"密码")))
                return;
            String user = frame.field1.getText();
            String pass = frame.field2.getText();
            frame.config.setUser(user);
            frame.config.setPass(pass);
            boolean flag = dao.login(user,pass);
            if(flag)
            {
                MainFrame.username=user;
                frame.dispose();
               ClientFrame.getInstance();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"账号或密码错误，请重新输入","提示",JOptionPane.ERROR_MESSAGE);
                frame.field1.grabFocus();
            }
        }
    }
}
