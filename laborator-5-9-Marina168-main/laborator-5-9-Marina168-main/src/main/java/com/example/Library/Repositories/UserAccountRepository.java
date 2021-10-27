package com.example.Library.Repositories;

import com.example.Library.Entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    @Query(value="Select p "+
            "FROM UserAccount p "+
            "WHERE p.username = :name " +
            "AND p.password= :password"

    )
    Optional<UserAccount> findByUsernamePassword(@Param("name") String name, @Param("password") String password);

    Optional<UserAccount> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);}
