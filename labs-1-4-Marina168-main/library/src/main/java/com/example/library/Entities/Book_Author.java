package com.example.library.Entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="book_author")
public class Book_Author implements Serializable {


    @Id
    @Column(name="id_entry",nullable = false)
    private int id_entry;

    @Column(name="id_book",nullable = false)
    private int id_book;


    @Column(name="id_author",nullable = false)
    private int id_author;

    public Book_Author()
    {

    }

    public Book_Author(int id_entry, int id_book, int id_author) {
        this.id_entry = id_entry;
        this.id_book = id_book;
        this.id_author = id_author;
    }

    public int getId_entry() {
        return id_entry;
    }

    public void setId_entry(int id_entry) {
        this.id_entry = id_entry;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    @Override
    public String toString() {
        return "Book_Author{" +
                "id_entry=" + id_entry +
                ", id_book=" + id_book +
                ", id_author=" + id_author +
                '}';
    }
}

