package com.library;

import com.library.dao.StudentDAO;
import com.library.model.Student;
import com.library.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    private StudentService studentService;
    private StudentDAO studentDAO;

    @BeforeEach
    public void setUp() {
        studentDAO = new StudentDAO(); // Assurez-vous d'avoir une instance correcte de DAO
        studentService = new StudentService(studentDAO);
    }

    @Test
    public void testAddStudent() {
        Student student = new Student(3, "Noha");
        studentService.addStudent(student);
        Student fetchedStudent = studentService.getStudentById(3);
        assertNotNull(fetchedStudent);
        assertEquals("Noha", fetchedStudent.getName());
    }

    @Test
    public void testGetStudentById() {
        Student student = new Student(4, "Kati");
        studentService.addStudent(student);
        Student fetchedStudent = studentService.getStudentById(4);
        assertNotNull(fetchedStudent);
        assertEquals("Kati", fetchedStudent.getName());
    }
}
