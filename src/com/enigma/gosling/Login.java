package com.enigma.gosling;

import com.enigma.gosling.config.DbConfig;
import com.enigma.gosling.service.ViewData;
import com.enigma.gosling.service.impl.ViewDataImpl;

import java.sql.*;
import java.util.Scanner;

public class Login {
    private final DbConfig dbConfig;

    public Login() {
        this.dbConfig = new DbConfig();
    }

    public void login() {
        System.out.println("Login");

        try (Connection conn = dbConfig.getConnection()) {
            Statement statement = conn.createStatement();
            ViewData view = new ViewDataImpl();

            // Create table m_user id, username, password
            // SQL injection example

            Scanner scanner = new Scanner(System.in);
            System.out.print("Input Username: ");
            String username = scanner.nextLine();
            System.out.print("Input Password: ");
            String password = scanner.nextLine();

            // Example injection
            // username : admin' OR '1'='1
            // password : admin' OR '1'='1

            String query = "SELECT username from m_user WHERE username = '" + username + "' AND password = '" + password + "'";
            System.out.println(query);

            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                System.out.println("Login Success");
                view.display();
            } else {
                System.out.println("Invalid login");
            }

            resultSet.close();
            statement.close();

            /**
             * PreparedStatement
             * is used to prevent SQL injection and execute SQL statement
             * .set -> method for filled prepared statement (?) from parameter
             * the rest is the same with Statement
             * */

        } catch (
                SQLException exception) {
            exception.printStackTrace();
            System.out.println("Connection failed: " + exception.getMessage());
        }
    }
}
