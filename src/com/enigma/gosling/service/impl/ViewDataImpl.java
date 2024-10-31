package com.enigma.gosling.service.impl;

import com.enigma.gosling.config.DbConfig;
import com.enigma.gosling.service.ViewData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewDataImpl implements ViewData {
    private final DbConfig dbConfig;

    public ViewDataImpl() {
        this.dbConfig = new DbConfig();
    }

    public void display() {
        try (Connection conn = dbConfig.getConnection()) {
            /** 01 */
            String sql = "SELECT * FROM students";

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.printf("%-4s | %-20s\n", "ID", "Name");
            System.out.println("-".repeat(30));
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                System.out.printf("%-4s | %-20s\n", id, name);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
