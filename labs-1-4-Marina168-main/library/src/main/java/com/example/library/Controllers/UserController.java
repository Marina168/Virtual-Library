package com.example.library.Controllers;

import com.example.library.Entities.BookEntity;
import com.example.library.Entities.UserEntity;
import com.example.library.Services.BookService;
import com.example.library.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/{email}", method = { RequestMethod.GET })
        public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {
            UserEntity user = userService.findUserByEmail(email);
            return new ResponseEntity<>(user,HttpStatus.OK);



    }

}
