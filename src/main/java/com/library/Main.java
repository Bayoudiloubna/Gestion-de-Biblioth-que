package com.library;

import com.library.dao.*;
import com.library.model.*;
import com.library.service.*;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Initialisation des DAO
        StudentDAO studentDAO = new StudentDAO();
        BookDAO bookDAO = new BookDAO();
        BorrowDAO borrowDAO = new BorrowDAO();

        // Initialisation des services
        StudentService studentService = new StudentService(studentDAO);
        BookService bookService = new BookService(bookDAO);
        BorrowService borrowService = new BorrowService(borrowDAO, studentService, bookService);

        /* Création et ajout des étudiants
        System.out.println("Ajout des étudiants :");
        Student student1 = new Student(1, "Alice");
        Student student2 = new Student(2, "Bob");
        studentService.addStudent(student1);
        studentService.addStudent(student2);*/

        // Affichage des étudiants
        System.out.println("\nListe des étudiants :");
        studentService.displayStudents();

        // Création et ajout des livres
        System.out.println("\nAjout des livres :");
        Book book1 = new Book(1, "1984", "George Orwell", "Secker", "123456789",1949);
        Book book2 = new Book(2, "To Kill a Mockingbird", "Harper Lee", "J.B.",  "987654321",1960);
        bookService.addBook(book1);
        bookService.addBook(book2);

        // Affichage des livres
        System.out.println("\nListe des livres :");
        bookService.displayBooks();

        // Enregistrer un emprunt
        System.out.println("\nEnregistrement d'un emprunt :");
        Date borrowDate = new Date();
        Date returnDate = new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000); // Date de retour dans 7 jours
        borrowService.borrowBook(1, 1, borrowDate, returnDate);

        System.out.println("\nProgramme terminé.");
    }
}
