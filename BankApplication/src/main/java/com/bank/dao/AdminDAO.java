package com.bank.dao;

import com.bank.model.Admin;
import com.bank.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    public Admin getByUsernameAndPassword(String username, String password) throws SQLException, ClassNotFoundException {
        Admin admin = null;
        String query = "SELECT * FROM Admins WHERE username = ? AND password = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                admin = new Admin(resultSet.getString("username"), resultSet.getString("password"));
            }
        }
        return admin;
    }
}
