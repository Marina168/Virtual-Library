package com.example.library.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity

@Table(name="authors")
public class AuthorEntity implements  Serializable {

    @Id
    @Column(name="id_author",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="aname",nullable = false)
    private String aname;

    @Column(name="review",nullable = false)
    private byte review;


    /*@ManyToMany(mappedBy = "authors",fetch = FetchType.LAZY, targetEntity =BookEntity.class )
    @JsonBackReference
    private Set<BookEntity> books = new HashSet<BookEntity>();*/


    public AuthorEntity()
    {

    }
    public AuthorEntity(int id, String aname, byte review) {
        this.id = id;
        this.aname = aname;
        this.review = review;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public byte getReview() {
        return review;
    }

    public void setReview(byte review) {
        this.review = review;
    }


    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id=" + id +
                '}';
    }
}
