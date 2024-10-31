package com.enigma.gosling.service;

import com.enigma.gosling.dao.StudentDao;
import com.enigma.gosling.entity.Student;

import java.util.List;
import java.util.Scanner;

public class StudentService {
    private final StudentDao studentDao;
    Scanner scanner = new Scanner(System.in);

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void addStudent(){
        System.out.print("Input Name: ");
        String name = scanner.nextLine();
        System.out.print("Input Gender: ");
        String gender = scanner.nextLine();
        Student newStudent = new Student(null, name, gender);
        studentDao.create(newStudent);
    }

    public void updateStudent(){
        System.out.print("Input ID: ");
        String id = scanner.nextLine();

        // Looking for student
        Student student = studentDao.findById(id);

        if (student == null) throw  new RuntimeException();

        System.out.print("Input new name: ");
        String name = scanner.nextLine();

        if (!name.isEmpty()){
            student.setName(name);
        }

        System.out.print("Input new gender: ");
        String gender = scanner.nextLine();

        if (!gender.isEmpty()){
            student.setGender(gender);
        }

        studentDao.update(student);
    }

    public Student findStudent() {
        System.out.print("Input id: ");
        String id = scanner.nextLine();
        return studentDao.findById(id);
    }

    public List<Student> findAllStudent(){
        return studentDao.getAll();
    }

    public Student deleteStudentById(){
        System.out.print("Input Student ID: ");
        String id = scanner.nextLine();

        // Check student id exist
        Student student = studentDao.findById(id);
        if (student == null) throw new RuntimeException();

        studentDao.deleteById(student);
        return student;
    }

}
