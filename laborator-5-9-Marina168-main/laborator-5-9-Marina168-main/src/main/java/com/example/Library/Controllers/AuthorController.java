package com.example.Library.Controllers;


import com.example.Library.Entities.Author;
import com.example.Library.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/library/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    

    @RequestMapping(value="/all", method = { RequestMethod.GET })
    public Iterable<Author> getAllAuthors() {
        Iterable<Author> list = authorService.findAuthors();
        return list;
    }
   /* @RequestMapping(value="/{id}", method = { RequestMethod.GET })
    public ResponseEntity<?> getAuthorByBookId(@PathVariable("id") int book_id) {
        Author author= authorRepository.findAuthorByBookId(book_id);
        return new ResponseEntity<>(author,HttpStatus.OK);

    }*/

    /*@RequestMapping(value="/{id}", method = { RequestMethod.GET })
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") int authorId) {
        Author author = authorService.AuthorById(authorId);
        return new ResponseEntity<>(author,HttpStatus.OK);

    }*/


    @RequestMapping(value = "/author", method = RequestMethod.POST)
    public ResponseEntity<Integer> insertAuthor(@RequestBody Author author )
    {
        int authorID = authorService.insertAuthor(author);
        return new ResponseEntity<Integer>(authorID,HttpStatus.CREATED);
    }
  /*  @RequestMapping(value = "/add/{author_id}/book", method = RequestMethod.POST)
    public ResponseEntity<Author> insertAuthor (@PathVariable("author_id") int authorId, @RequestBody int bookId)
    {
        Author author = authorService.addBook(authorId,bookId);
        return new ResponseEntity<>(author,HttpStatus.CREATED);
    }*/

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAuthor(@RequestBody Author author, @PathVariable int id)
    {
        Author authorUpdate=authorService.update(author,id);
        return new ResponseEntity<>(authorUpdate,HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    @ResponseBody
    public ResponseEntity<?> deleteBook ( @PathVariable int id){
        try {
           authorService.deleteAuthorById(id);
        } catch (Exception ex) {
            return new ResponseEntity<>("Author with id " + id + " does not exist!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("The author was deleted.",HttpStatus.NO_CONTENT);
    }

}
