package com.enigma.gosling.view;

import com.enigma.gosling.dao.StudentDao;
import com.enigma.gosling.entity.Student;
import com.enigma.gosling.service.StudentService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StudentView {

    private final StudentService studentService;

    public StudentView(StudentService studentService) {
        this.studentService = studentService;
    }

    public void mainMenu() {
        while (true) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
            int input = displayMenu();
            switch (input) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    System.out.println("Update");
                    updateStudentData();
                    promptEnterKey();
                    break;
                case 3:
                    System.out.println("Delete Student By ID");
                    deleteStudent();
                    promptEnterKey();
                    break;
                case 4:
                    System.out.println("View");
                    getAllStudent();
                    promptEnterKey();
                    break;
                case 5:
                    System.out.println("Find Student");
                    findStudent();
                    promptEnterKey();
                    break;
                case 6:
                    return;
                default:
                    break;
            }
        }
    }

    public void promptEnterKey() {
        System.out.println("Press \"ENTER\" to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int displayMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. View Student");
        System.out.println("5. Find Student");
        System.out.println("6. Exit");
        System.out.print("Input Menu: ");
        int input = scanner.nextInt();
        return input;
    }

    public void updateStudentData() {
        System.out.println("Update Student");
        System.out.println("-".repeat(20));
        try {
            studentService.updateStudent();
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println("-".repeat(20));
    }

    public void addStudent() {
        System.out.println("Add new Student");
        System.out.println("-".repeat(20));
        try {
            studentService.addStudent();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findStudent() {
        try {
            Student student = studentService.findStudent();
            System.out.println("-".repeat(20));
            System.out.println("Data Student");
            System.out.println("-".repeat(20));
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Gender: " + student.getGender());
            System.out.println("-".repeat(20));
            System.out.println();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllStudent() {
        try {
            List<Student> students = studentService.findAllStudent();

            System.out.println("-".repeat(40));
            System.out.printf("%-5s | %-10s | %-10s\n", "ID", "Name", "Gender");
            System.out.println("-".repeat(40));
            for (Student student : students) {
                System.out.printf("%-5s | %-10s | %-10s\n", student.getId(), student.getName(), student.getGender());
            }
            System.out.println("-".repeat(40));
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void deleteStudent(){
        System.out.println("Delete Student by ID");
        System.out.println("-".repeat(40));
        try{
            Student student = studentService.deleteStudentById();
            System.out.println("Success delete " + student.getName());
        }catch (RuntimeException exception){
            System.out.println("Failed to delete student");
            System.out.println(exception.getMessage());
        }
    }
}
