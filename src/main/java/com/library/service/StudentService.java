package com.library.service;

import com.library.dao.StudentDAO;
import com.library.model.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private final StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void addStudent(Student student) {
        try {
            studentDAO.addStudent(student);
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }

    public void displayStudents() {
        try {
            List<Student> students = studentDAO.getAllStudents();
            for (Student student : students) {
                System.out.println(student.getId() + " - " + student.getName());
            }
        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
        }
    }

    // Récupérer un étudiant par ID
    public Student getStudentById(int id) {
        Student student = studentDAO.getStudentById(id);
        if (student == null) {
            System.err.println("Student with ID " + id + " not found");
        }
        return student;
    }
}
