package com.example.library.Services;

import com.example.library.Entities.BookEntity;
import com.example.library.Entities.UserEntity;
import com.example.library.Exceptions.ResourceNotFoundException;
import com.example.library.Repositories.UserRepository;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    private UserRepository userRepository;
    public UserService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    public UserEntity findUserByEmail(String username) {

        Optional<UserEntity> user = userRepository.findByUsername(username);

        if (!user.isPresent()) {
            LOGGER.error("User with email{} was not found ", username);
            throw new ResourceNotFoundException(user.get().getId());
        }

        return user.get();
    }

}
