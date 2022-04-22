package com.example.testtask.dao.repository;

import com.example.testtask.dao.model.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    boolean existsByName(final String name);

    Company findByName(final String name);
}
