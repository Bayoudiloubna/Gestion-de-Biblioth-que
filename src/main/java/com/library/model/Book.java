package com.library.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private String isbn;  // Ajout de l'ISBN

    // Constructeur par défaut
    public Book() {}

    // Constructeur complet
    public Book(String title, String author, String publisher, int year, String isbn) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.isbn = isbn;
    }

    // Constructeur additionnel
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(int id, String title, String author, String isbn, String publisher, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.year = year;
    }
    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getIsbn() {  // Getter pour ISBN
        return isbn;
    }

    public void setIsbn(String isbn) {  // Setter pour ISBN
        this.isbn = isbn;
    }

    public boolean isPresent() {
        return true;
    }
}
