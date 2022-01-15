package com.main.repositories;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(@NotBlank String username);
    Optional<User> getById(long id);  
    List<User> findAll();
}
