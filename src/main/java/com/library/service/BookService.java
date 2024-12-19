package com.library.service;

import com.library.dao.BookDAO;
import com.library.model.Book;

import java.util.List;

public class BookService {

    private final BookDAO bookDAO;

    // Assurez-vous d'initialiser bookDAO via le constructeur
    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    // Ajouter un livre
    public void addBook(Book book) {
        if (book != null) {
            bookDAO.addBook(book);
        } else {
            System.err.println("Book cannot be null");
        }
    }

    // Afficher tous les livres
    public void displayBooks() {
        List<Book> books = bookDAO.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books available");
        } else {
            books.forEach(book -> System.out.println(book.getId() + " - " + book.getTitle()));
        }
    }
    // Récupérer un livre par ID
    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }
}
