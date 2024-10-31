package com.enigma.gosling.dao;

import com.enigma.gosling.entity.Student;

import java.util.List;

public interface StudentDao {
    void create(Student student);
    Student findById(String id);
    List<Student> getAll();
    void update(Student student);
    void deleteById(Student student);
}
