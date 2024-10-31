package com.enigma.gosling.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConfig {
    private String url;
    private String username;
    private String password;

    public DbConfig() {
        String host = "localhost";
        String port = "5432";
        String database = "gosling";

        this.url = String.format("jdbc:postgresql://%s:%s/%s", host, port, database);
        this.username = "postgres";
        this.password = "root";
    }

    public Connection getConnection(){
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Database connected");

            return DriverManager.getConnection(url, username, password);
        }catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Connection failed: " + exception.getMessage());
        }
        return null;
    }
}
