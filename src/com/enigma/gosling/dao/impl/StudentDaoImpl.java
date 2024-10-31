package com.enigma.gosling.dao.impl;

import com.enigma.gosling.entity.Student;
import com.enigma.gosling.dao.StudentDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private final Connection connection;

    public StudentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Student student) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO students (name, gender) VALUES (?, ?)")) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getGender());
            /** executeUpdate untuk memodifikasi data */
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Student findById(String id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT id, name, gender FROM students WHERE id = ?")) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Student(id, resultSet.getString("name"), resultSet.getString("gender"));
                }
                return null;
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Student> getAll() {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT id, name, gender FROM students")){
            List<Student> students = new ArrayList<>();

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    students.add(new Student(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getString("gender")));
                }
                return students;
            }
        }catch (SQLException exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void update(Student student) {
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE students SET name = ?, gender = ? WHERE id = ?")){
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getGender());
            preparedStatement.setString(3, student.getId());

            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void deleteById(Student student) {
        try(PreparedStatement preparedStatement =
                connection.prepareStatement("DELETE FROM students WHERE id = ?")){
            preparedStatement.setString(1, student.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            throw new RuntimeException(exception);
        }
    }
}
