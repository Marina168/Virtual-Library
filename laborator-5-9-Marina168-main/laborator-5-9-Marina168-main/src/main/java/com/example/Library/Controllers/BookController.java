package com.example.Library.Controllers;

import com.example.Library.Entities.Author;
import com.example.Library.Entities.Book;
import com.example.Library.Services.AuthorService;
import com.example.Library.Services.BookService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value="/api/library")
public class BookController {

    private final BookService bookService;

    private final AuthorService authorService;


    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping()
    public ResponseEntity<List<Book>> getBooks()
    {
        return new ResponseEntity<>(bookService.findBooks(), HttpStatus.OK);
    }
    @RequestMapping(value = "/all", method = {RequestMethod.GET})
    public ResponseEntity<?> getAllBooks() throws ParseException {
        return new ResponseEntity<>(bookService.findBooks(), HttpStatus.OK);
    }

    @PostMapping("/books")
    ResponseEntity<?> addBook(@RequestBody Book newBook) throws ParseException {
       int book;
        try {
            book = bookService.insert(newBook);
        } catch (Exception ex) {
            return new ResponseEntity<>("The book has not been created ", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book,HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateBook(@PathVariable int id, @RequestBody Book newBook) throws ParseException {
        Book book;
        try {
            book = bookService.update(newBook, id);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(EntityModel.of(book,
                linkTo(methodOn(BookController.class).updateBook(id, book)).withSelfRel(),
                linkTo(methodOn(BookController.class).getAllBooks()).withRel("all")), HttpStatus.OK);

    }

    @RequestMapping(value = "/book/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> getBookById(@PathVariable("id") int bookId) {
        try {
            return new ResponseEntity<>(bookService.BookById(bookId), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("The book with id" + bookId + " does not exist", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    @ResponseBody
    public ResponseEntity<?> deleteBook ( @PathVariable int id){
        try {
            bookService.deleteBookById(id);
        } catch (Exception ex) {
            return new ResponseEntity<>("Book with id" + id + "does not exist!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/book/{id_book}/author/{id_author}")
    public ResponseEntity<?> insertAuthorToBook(@PathVariable("id_book") int id_book, @PathVariable("id_author") int id_author)
    {
        Author author=authorService.AuthorById(id_author);
        bookService.addAuthorToBook(id_book,author);

        return new ResponseEntity("Author added successfully", HttpStatus.OK);
    }




}
