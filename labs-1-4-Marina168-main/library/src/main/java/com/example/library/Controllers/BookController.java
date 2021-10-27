package com.example.library.Controllers;

import com.example.library.Entities.BookEntity;
import com.example.library.Exceptions.ResourceNotFoundException;
import com.example.library.Repositories.BookRepository;
import com.example.library.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/library")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookControllerAssembler bookAssembler;

    @Autowired
    public BookController(BookService bookService,BookControllerAssembler bookAssembler) {
        this.bookService = bookService;
        this.bookAssembler=bookAssembler;
    }

    @RequestMapping(value = "/show", method = {RequestMethod.GET})
    public ResponseEntity<String> getStatus() {
        System.out.println("Hello");
        return new ResponseEntity<String>("Hello world", HttpStatus.OK);
    }

    @RequestMapping(value="/all", method = { RequestMethod.GET })
    public ResponseEntity<?> getAllBooks() throws ParseException {
        return new ResponseEntity<> (bookService.findBooks(),HttpStatus.OK);
    }
    
    @GetMapping("/books/{id}")
    ResponseEntity<?> findBook(@PathVariable int id) throws ParseException {
        BookEntity book;
        try {
             book = bookService.BookById(id);

        } catch (Exception ex) {
            return new ResponseEntity<>("Book with id  "+ id + " was not found!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(EntityModel.of(book, linkTo(methodOn(BookController.class).findBook(id)).withSelfRel(),
                linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"),
                linkTo(methodOn(AuthorController.class).getAuthorByBookId(id)).withRel("/authors")), HttpStatus.OK);
    }

    @PostMapping("/books")
    ResponseEntity<?> addBook(@RequestBody BookEntity newBook) throws ParseException {
        BookEntity book;
        try {
            book =bookService.insert(newBook);
        }catch (Exception ex)
        {
            return  new ResponseEntity<>("The book has been created ",HttpStatus.CREATED);
        }
        return new  ResponseEntity<>(EntityModel.of(book,
                linkTo(methodOn(BookController.class).addBook(book)).withSelfRel(),
                linkTo(methodOn(BookController.class).getAllBooks()).withRel("all")),HttpStatus.OK);

    }

    @PutMapping("/{id}")

    ResponseEntity<?> updateBook(@PathVariable int id, @RequestBody BookEntity newBook) throws ParseException {
        BookEntity book;
        try {
            book =bookService.update(newBook, id);
        }catch (Exception ex)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new  ResponseEntity<>(EntityModel.of(book,
                linkTo(methodOn(BookController.class).updateBook(id,book)).withSelfRel(),
                linkTo(methodOn(BookController.class).getAllBooks()).withRel("all")),HttpStatus.OK);

    }
    
    /*@RequestMapping(value="/{id}", method = { RequestMethod.GET })
    public ResponseEntity<?> getBookById(@PathVariable("id") int bookId) {
     try {
         return new ResponseEntity<>(bookService.BookById(bookId), HttpStatus.OK);
     }catch (Exception ex)
     {
         return new ResponseEntity<>("The book with id" + bookId +" does not exist",HttpStatus.BAD_REQUEST);
     }*/


    /*@RequestMapping(value = "/books", method = RequestMethod.POST)
    public ResponseEntity<Integer> insertBook(@RequestBody BookEntity carte )
    {
        int bookID = bookService.insert(carte);
        return new ResponseEntity<>(bookID,HttpStatus.CREATED);
    }*/

   /* @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBook(@RequestBody BookEntity carte, @PathVariable int id)
    {
        BookEntity carteUpdate=bookService.update(carte,id);
        return new ResponseEntity<>(carteUpdate,HttpStatus.OK);
    }*/
    @RequestMapping(value="/{id}", method={RequestMethod.DELETE})
    @ResponseBody
    public ResponseEntity<?> deleteBook(@PathVariable int id) {
        try {
            bookService.deleteBookById(id);
        }catch (Exception ex)
        {
            return new ResponseEntity<>("Book with id" +id + "does not exist!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
