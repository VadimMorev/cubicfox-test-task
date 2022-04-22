package com.example.testtask.dao.repository;

import com.example.testtask.dao.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(final String email);
}
