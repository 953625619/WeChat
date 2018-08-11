package DAO;

import GUI.Frame.MainFrame;
import Util.SQLUtil;
import entity.Config;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConfigDAO  {
    public boolean login(String user,String pass)
    {
        try(Connection connection = SQLUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("" +
                    "select * from config where user = ? and pass = ?"))
        {
            statement.setString(1,user);
            statement.setString(2,pass);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
