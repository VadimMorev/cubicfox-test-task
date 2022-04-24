package com.example.testtask.dao.repository;

import com.example.testtask.dao.model.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    Optional<Company> findByName(final String name);
}
