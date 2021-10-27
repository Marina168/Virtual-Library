package com.example.library.Entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class EditorEntity extends UserEntity {

    public EditorEntity(){};

    public EditorEntity(int userid,String username, String password, String role )
    {
        super(userid,username,password,role);
        this.setRole("Editor");
    }
}
