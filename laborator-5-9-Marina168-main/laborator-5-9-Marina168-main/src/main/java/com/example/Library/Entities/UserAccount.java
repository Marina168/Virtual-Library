package com.example.Library.Entities;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class UserAccount implements Serializable {
    @Id
    @Column(name="id_author",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name="password",nullable=false)
    @Size(min = 4, max = 128)
    private String password;

    @Column(name = "email", length = 50)
    @Size(min = 4, max = 50)
    private String email;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = {@JoinColumn(name = "USER_NAME", referencedColumnName = "username")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_NAME", referencedColumnName = "name")})
    private Set<Role> roles = new HashSet<>();

    public UserAccount() {
    }

    public UserAccount(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles=roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount user = (UserAccount) o;
        return id==user.id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

