package com.example.Library.Services;


import com.example.Library.Entities.Author;
import com.example.Library.Entities.Book;
import com.example.Library.Repositories.BookRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findBooks() {
        return bookRepository.findAll();
    }

    public Book BookById(int id) throws Exception{
        Optional<Book> book =  bookRepository.findById(id);
        if (book.isPresent()) {
            return  book.get();
        }else
        {
            throw  new Exception("The book with id "+ id+" does  not exist." );
        }

    }
    public int insert(Book carte)
    {
        Book book =bookRepository.save(carte);
        LOGGER.debug("Book with id {} was  inserted in db", book.getId());
        return book.getId();
    }

    public Book update(Book newBook, int id)
    {
        Optional<Book> updatedBook =bookRepository.findById(id);
        if(!updatedBook.isPresent())
        {
            LOGGER.error("Book with id {} was not found",id);
            throw new ResourceNotFoundException(Book.class.getSimpleName());
        }
        newBook.setId(id);
        return bookRepository.save(newBook);


    }
    public void  deleteBookById(int id) throws  Exception
    {
        Optional <Book> book = bookRepository.findById(id);
        if(book.isPresent())
        {
            bookRepository.deleteById(id);
        }else
        {
            throw new Exception("The book with id "+ id + "does  not exist!");
        }

    }

    public void addAuthorToBook(int id, Author author)
    {
        Optional<Book> book = bookRepository.findById(id);
        book.get().addAuthor(author);
        bookRepository.save(book.get());
    }

    public void deleteAuthorFromBook(int id, Author author)
    {
        Optional<Book> book = bookRepository.findById(id);
        book.get().removeAuthor(author);
        bookRepository.save(book.get());
    }
}
