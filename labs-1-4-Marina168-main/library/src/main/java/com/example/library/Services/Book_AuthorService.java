package com.example.library.Services;

import com.example.library.Entities.Book_Author;
import com.example.library.Repositories.BookRepository;
import com.example.library.Repositories.Book_AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class Book_AuthorService {

    @Autowired
    private Book_AuthorRepository book_authorRepository;

    @Autowired
    private BookRepository bookRepository;


    List<Integer> listAllAuthorsByBookId(Integer book_id)
    {
        System.out.println(book_authorRepository.findAuthorByBookId(book_id));

        return book_authorRepository.findAuthorByBookId(book_id);
    }

    public void addAuthorByBookId(Integer book_id, Integer author_id)
    {
        book_authorRepository.addAuthorByBookId(book_id,author_id);
    }
}


