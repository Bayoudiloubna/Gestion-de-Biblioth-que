package com.library.dao;

import com.library.model.Book;
import com.library.model.Borrow;
import com.library.model.Student;
import com.library.util.DbConnection;

import java.sql.*;

public class BorrowDAO {

    // Enregistrer un emprunt
    public void borrowBook(Borrow borrow) {
        String query = "INSERT INTO borrows (id, student_id, book_id, borrow_date, return_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, borrow.getId());
            statement.setInt(2, borrow.getStudent().getId());
            statement.setInt(3, borrow.getBook().getId());
            statement.setDate(4, new Date(borrow.getBorrowDate().getTime()));
            statement.setDate(5, new Date(borrow.getReturnDate().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error borrowing book: " + e.getMessage());
        }
    }
    // Récupérer l'emprunt d'un étudiant par son ID
    public Borrow getBorrowByStudentId(int studentId) {
        String query = "SELECT b.id AS borrow_id, b.student_id, b.book_id, b.borrow_date, b.return_date, " +
                "s.id AS student_id, s.name AS student_name, " +
                "bk.id AS book_id, bk.title AS book_title, bk.author AS book_author " +
                "FROM borrows b " +
                "JOIN students s ON b.student_id = s.id " +
                "JOIN books bk ON b.book_id = bk.id " +
                "WHERE b.student_id = ?";

        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Création de l'objet Borrow
                int borrowId = resultSet.getInt("borrow_id");
                int bookId = resultSet.getInt("book_id");
                String bookTitle = resultSet.getString("book_title");
                String bookAuthor = resultSet.getString("book_author");
                Book book = new Book(bookId, bookTitle, bookAuthor, "", "", 0); // Vous pouvez personnaliser l'objet Book

                String studentName = resultSet.getString("student_name");
                Student student = new Student(studentId, studentName);

                // Retour de l'emprunt
                return new Borrow(borrowId, student, book, resultSet.getDate("borrow_date"), resultSet.getDate("return_date"));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching borrow: " + e.getMessage());
        }
        return null; // Si aucun emprunt trouvé
    }
}
