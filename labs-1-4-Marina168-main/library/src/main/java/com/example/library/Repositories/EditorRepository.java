package com.example.library.Repositories;

import com.example.library.Entities.EditorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EditorRepository  extends JpaRepository<EditorEntity,Integer> {

}
