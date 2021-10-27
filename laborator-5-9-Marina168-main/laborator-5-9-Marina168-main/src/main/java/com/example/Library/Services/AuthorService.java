package com.example.Library.Services;
import com.example.Library.Entities.Author;
import com.example.Library.Entities.Book;
import com.example.Library.Repositories.AuthorRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Iterable<Author>  findAuthors(){return authorRepository.findAll();};

    public Author AuthorById(int id) {
        Optional<Author> author =  authorRepository.findById(id);
        System.out.println( authorRepository.findById(id));
        if (!author.isPresent()) {
            LOGGER.error("Author with id {} was not fond ", id);
            throw new ResourceNotFoundException(Author.class.getSimpleName());
        }
        return  author.get();
    }

    public int  insertAuthor(Author author)
    {
        Author nauthor =authorRepository.save(author);
        LOGGER.error("Author with id {} was inserted in db", nauthor.getId());
        return nauthor.getId();
    }
   /* public Author addBook( int author_id, int book_id)
    {
        Optional<Author> author = authorRepository.findById(author_id);
        Optional<BookEntity> book = bookRepository.findById(book_id);
        author.get().addBook(book.get());
        authorRepository.save(author.get());
        LOGGER.error("Book with {id} was added successfully to author with {id}",book_id,author_id);
        return author.get();
    }*/

    public Author update(Author newAuthor, int id)
    {
        Optional<Author> updatedAuthor =authorRepository.findById(id);
        if(!updatedAuthor.isPresent())
        {
            LOGGER.error("Author  with id {} was not found",id);
            throw new ResourceNotFoundException(Author.class.getSimpleName());
        }

        newAuthor.setId(id);
        return authorRepository.save(newAuthor);
    }

    public void  deleteAuthorById(int id) throws  Exception
    {
        Optional <Author> author = authorRepository.findById(id);
        if(author.isPresent())
        {
            authorRepository.deleteById(id);
        }else
        {
            throw new Exception("The author with id "+ id + "does  not exist!");
        }

    }

    public Integer authorExist(String name ,Integer review)
    {
        List<Author> authorList =  authorRepository.findAll();

        for (Author a: authorList)
        {
            if(a.getName().equals(name) && a.getReview()==review)
            {
                return  a.getId();
            }
        }
        return  -1;

    }

}
