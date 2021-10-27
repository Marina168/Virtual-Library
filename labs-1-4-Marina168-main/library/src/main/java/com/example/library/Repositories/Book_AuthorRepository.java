package com.example.library.Repositories;

import com.example.library.Entities.Book_Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface Book_AuthorRepository extends JpaRepository<Book_Author,Integer> {

    @Query(value="SELECT id_author FROM book_author WHERE id_book=:id_book", nativeQuery = true)
    List<Integer> findAuthorByBookId(Integer id_book);

    @Modifying
    @Transactional
    @Query(value="INSERT into book_author (id_book,id_author) values ( :id_book, :id_author)",nativeQuery = true)
    void addAuthorByBookId(Integer id_book, Integer id_author);
}
