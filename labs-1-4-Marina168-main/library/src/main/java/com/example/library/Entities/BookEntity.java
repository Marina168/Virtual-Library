package com.example.library.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Setter
@Getter
@Table(name="books")
public class BookEntity implements  Serializable{

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
    private LocalDate published;

    @Column(name="summary")
    private String summary;


   /* @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "id_book"), inverseJoinColumns = @JoinColumn(name = "id_author"))
    @JsonBackReference
    private Set<AuthorEntity> authors = new HashSet<AuthorEntity>();

    public void addAuthor(AuthorEntity author){
        this.authors.add(author);
        author.getBooks().add(this);

    }

    public void removeAuthor(AuthorEntity author){
        this.authors.remove(author);
        author.getBooks().remove(this);

    }*/
    public BookEntity()
    {

    }

    public BookEntity(int id, String title, String publisher, String genre, LocalDate published, String summary) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.genre = genre;
        this.published = published;
        this.summary = summary;

    }

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

    public LocalDate getPublished() {
        return published;
    }

    public void setPublished(LocalDate published) {
        this.published = published;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

}
