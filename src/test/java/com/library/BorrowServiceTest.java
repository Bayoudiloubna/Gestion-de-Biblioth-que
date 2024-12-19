package com.library;

import com.library.dao.BookDAO;
import com.library.dao.BorrowDAO;
import com.library.dao.StudentDAO;
import com.library.model.Book;
import com.library.model.Borrow;
import com.library.model.Student;
import com.library.service.BookService;
import com.library.service.BorrowService;
import com.library.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BorrowServiceTest {
    private BorrowService borrowService;
    private BorrowDAO borrowDAO;
    private BookService bookService;
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        borrowDAO = new BorrowDAO();
        StudentDAO studentDAO = new StudentDAO();
        BookDAO bookDAO = new BookDAO();
        studentService = new StudentService(studentDAO);
        bookService = new BookService(bookDAO);
        borrowService = new BorrowService(borrowDAO, studentService, bookService);

        // Créer un étudiant et un livre avant de tester l'emprunt
        Student student = new Student(5, "David");
        Book book = new Book(7, " la boite a merveille", "George Orwell", "123456789", "Secker & Warburg", 1949);

        studentService.addStudent(student);  // Ajouter l'étudiant dans la base de données
        bookService.addBook(book);  // Ajouter le livre dans la base de données
    }

    @Test
    void testBorrowBook() {
        int studentId = 5;
        int bookId = 7;
        Date borrowDate = new Date();
        Date returnDate = new Date(borrowDate.getTime() + (7 * 24 * 60 * 60 * 1000)); // Emprunt d'une semaine

        // Effectuer l'emprunt
        borrowService.borrowBook(studentId, bookId, borrowDate, returnDate);

        // Vérifiez que l'emprunt a été correctement enregistré
        Borrow borrow = borrowDAO.getBorrowByStudentId(studentId);
        assertNotNull(borrow);
        assertEquals(studentId, borrow.getStudent().getId());
        assertEquals(bookId, borrow.getBook().getId());
    }
}
