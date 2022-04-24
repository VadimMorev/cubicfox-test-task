package com.example.testtask.dao.repository;

import com.example.testtask.dao.model.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Long> {

    Optional<Address> findByZipcode(final String zipcode);
}
