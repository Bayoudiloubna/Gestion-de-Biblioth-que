package com.library.service;

import com.library.dao.BorrowDAO;
import com.library.model.Book;
import com.library.model.Borrow;
import com.library.model.Student;

import java.util.Date;

public class BorrowService {

    private final BorrowDAO borrowDAO;
    private final StudentService studentService;
    private final BookService bookService;

    public BorrowService(BorrowDAO borrowDAO, StudentService studentService, BookService bookService) {
        this.borrowDAO = borrowDAO;
        this.studentService = studentService;
        this.bookService = bookService;
    }

    // Enregistrer un emprunt
    public void borrowBook(int studentId, int bookId, Date borrowDate, Date returnDate) {
        Student student = studentService.getStudentById(studentId);
        Book book = bookService.getBookById(bookId);

        if (student == null) {
            System.err.println("Student not found");
            return;
        }
        if (book == null) {
            System.err.println("Book not found");
            return;
        }

        Borrow borrow = new Borrow();
        borrow.setStudent(student);
        borrow.setBook(book);
        borrow.setBorrowDate(borrowDate);
        borrow.setReturnDate(returnDate);

        borrowDAO.borrowBook(borrow);
        System.out.println("Borrow recorded successfully");
    }
}
