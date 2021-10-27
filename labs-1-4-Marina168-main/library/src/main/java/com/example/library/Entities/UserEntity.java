package com.example.library.Entities;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserEntity implements Serializable
{

    @Id
    @Column(name="id_user", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="role",nullable = false)
    private String role;

    public UserEntity()
    {

    }
    public UserEntity(String username, String password, String role)
    {
        this.username=username;
        this.password=password;
        this.role=role;
    }
    public UserEntity(int id,String username, String password, String role)
    {
        this.id=id;
        this.username=username;
        this.password=password;
        this.role=role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
