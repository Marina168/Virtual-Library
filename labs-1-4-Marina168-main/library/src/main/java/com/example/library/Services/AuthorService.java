package com.example.library.Services;

import com.example.library.Controllers.AuthorController;
import com.example.library.Entities.AuthorEntity;
import com.example.library.Entities.BookEntity;
import com.example.library.Exceptions.ResourceNotFoundException;
import com.example.library.Repositories.AuthorRepository;
import com.example.library.Repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private AuthorRepository authorRepository;
    private AuthorEntity authorEntity;
    private BookRepository bookRepository;

    public AuthorService (AuthorRepository authorRepository, BookRepository bookRepository)
    {
        this.authorRepository=authorRepository;
        this.bookRepository=bookRepository;
    }

    public Iterable<AuthorEntity>  findAuthors(){return authorRepository.findAll();};

    public AuthorEntity AuthorById(int id) {
        Optional<AuthorEntity> author =  authorRepository.findById(id);
        System.out.println( authorRepository.findById(id));
        if (!author.isPresent()) {
            LOGGER.error("Author with id {} was not fond ", id);
            throw new ResourceNotFoundException(id);
        }
        return  author.get();
    }

    public int  insertAuthor(AuthorEntity author)
    {
        AuthorEntity nauthor =authorRepository.save(author);
        LOGGER.error("Author with id {} was inserted in db", nauthor.getId());
        return nauthor.getId();
    }
   /* public AuthorEntity addBook( int author_id, int book_id)
    {
        Optional<AuthorEntity> author = authorRepository.findById(author_id);
        Optional<BookEntity> book = bookRepository.findById(book_id);
        author.get().addBook(book.get());
        authorRepository.save(author.get());
        LOGGER.error("Book with {id} was added successfully to author with {id}",book_id,author_id);
        return author.get();
    }*/

    public AuthorEntity update(AuthorEntity newAuthor, int id)
    {
        Optional<AuthorEntity> updatedAuthor =authorRepository.findById(id);
        if(!updatedAuthor.isPresent())
        {
            LOGGER.error("Author  with id {} was not found",id);
            throw new ResourceNotFoundException( id);
        }

        newAuthor.setId(id);
        return authorRepository.save(newAuthor);
    }
   /* public AuthorEntity deleteBook( int author_id, int book_id)
    {
        Optional<AuthorEntity> author = authorRepository.findById(author_id);
        Optional<BookEntity> book = bookRepository.findById(book_id);
        author.get().removeBook(book.get());
        authorRepository.save(author.get());
        LOGGER.error("Book with {id} was not found for author  {id}",book_id,author_id);
        return author.get();
    }*/


}
