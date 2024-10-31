package com.enigma.gosling;

import com.enigma.gosling.config.DbConfig;
import com.enigma.gosling.dao.StudentDao;
import com.enigma.gosling.dao.ViewData;
import com.enigma.gosling.dao.impl.StudentDaoImpl;
import com.enigma.gosling.dao.impl.ViewDataImpl;
import com.enigma.gosling.service.StudentService;
import com.enigma.gosling.view.StudentView;

import java.io.IOException;
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
            /**try (Statement statement = conn.createStatement()) {
             // Statement statement = conn.createStatement();
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
             System.out.println("result: " + resultSet);
             if (resultSet.next()) {
             System.out.println("Login Success");
             view.display();
             } else {
             System.out.println("Invalid login");
             }
             } catch (SQLException exception) {
             System.out.println(exception.getMessage());
             }*/


            /**
             * PreparedStatement
             * is used to prevent SQL injection and execute SQL statement
             * .set -> method for filled prepared statement (?) from parameter
             * the rest is the same with Statement
             *
             * PreparedStatement adalah versi lebih aman dari Statement karena menggunakan
             * parameter yang terikat (bound parameters).
             *
             * Dengan PreparedStatement, Anda menulis query SQL dengan placeholder (tanda ?)
             * di mana nilai parameter akan dimasukkan.
             *
             * Keamanan Lebih Baik: PreparedStatement melindungi dari SQL injection karena query dan data dipisahkan.
             * Pengguna tidak dapat memodifikasi struktur query, karena parameter input hanya diisi sebagai data,
             * bukan bagian dari query.
             *
             * Efisiensi: PreparedStatement juga lebih efisien untuk query berulang karena
             * database dapat membuat rencana eksekusi (execution plan) sekali saja dan menggunakannya kembali.
             * */

            String sqlLogin = "SELECT username from m_user WHERE username = ? AND password = ?";

            try (PreparedStatement preparedStatement = conn.prepareStatement(sqlLogin)) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Input Username: ");
                String username = scanner.nextLine();
                System.out.print("Input Password: ");
                String password = scanner.nextLine();

                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    System.out.println("Login Success");
                    ViewData view = new ViewDataImpl();
                    view.display();

                    StudentDao studentDao = new StudentDaoImpl(conn);
                    StudentService studentService = new StudentService(studentDao);
                    StudentView studentView = new StudentView(studentService);

                    studentView.mainMenu();
                } else {
                    System.out.println("Invalid login");
                }

                resultSet.close();
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }

            // Insert data to students
            // INSERT INTO students (?, ?) VALUES (?, ?)

            // Delete data
            // DELETE FROM students WHERE id = ?

            // Update students data
            // Update students SET name = ? WHERE id = ?

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("Connection failed: " + exception.getMessage());
        }
    }
}
