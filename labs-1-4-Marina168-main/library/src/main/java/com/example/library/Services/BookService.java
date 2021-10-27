package com.example.library.Services;

import com.example.library.Entities.AuthorEntity;
import com.example.library.Entities.BookEntity;
import com.example.library.Exceptions.ResourceNotFoundException;
import com.example.library.Repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);
    @Autowired
    private BookRepository bookRepository;
    private BookEntity book;

    public List<BookEntity> findBooks() {
        return bookRepository.findAll();
    }

    public BookEntity BookById(int id) throws Exception{
        Optional<BookEntity> book =  bookRepository.findById(id);
        if (book.isPresent()) {
            return  book.get();
        }else
        {
            throw  new Exception("The book with id "+ id+" does  not exist." );
        }

    }
    public BookEntity insert(BookEntity carte)
    {
        BookEntity book =bookRepository.save(carte);
        LOGGER.debug("Book with id {} was  inserted in db", book.getId());
        return book;
    }

    public BookEntity update(BookEntity newBook, int id)
    {
        Optional<BookEntity> updatedBook =bookRepository.findById(id);
        if(!updatedBook.isPresent())
        {
            LOGGER.error("Book with id {} was not found",id);
            throw new ResourceNotFoundException( id);
        }

        newBook.setId(id);
        return bookRepository.save(newBook);


    }
    public void  deleteBookById(int id) throws  Exception
    {
        Optional <BookEntity> book = bookRepository.findById(id);
        if(book.isPresent())
        {
            bookRepository.deleteById(id);
        }else
        {
            throw new Exception("The book with id "+ id + "does  not exist!");
        }

    }


}
