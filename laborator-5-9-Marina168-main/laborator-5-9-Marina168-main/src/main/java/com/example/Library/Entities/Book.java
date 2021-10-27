package com.example.Library.Entities;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Book implements Serializable {

    @Id
    @Column(name="id_book",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="publisher",nullable = false)
    private String publisher;

    @Column(name="genre",nullable = false)
    private String genre;

    @Column(name="published",nullable = false)
    private Integer published;

    @Column(name="summary", length = 3000)
    private String summary;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(name="authors")
    private List<Author> authors;

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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Book(){};

    public Book(String title, String publisher, String genre, Integer published, String summary, List<Author> authors) {
        this.title = title;
        this.publisher = publisher;
        this.genre = genre;
        this.published = published;
        this.summary = summary;
        this.authors=authors;
    }

    public void addAuthor(Author author)
    {
        authors.add(author);
    }
    public void removeAuthor(Author author)
    {
        authors.remove(author);
    }

    /* @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "id_book"), inverseJoinColumns = @JoinColumn(name = "id_author"))
    @JsonBackReference
    private Set<AuthorEntity> authors = new HashSet<AuthorEntity>();*/


}
