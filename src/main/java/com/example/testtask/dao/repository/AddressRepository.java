package com.example.testtask.dao.repository;

import com.example.testtask.dao.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
    boolean existsByZipcode(final String zipcode);

    Address findByZipcode(final String zipcode);
}
