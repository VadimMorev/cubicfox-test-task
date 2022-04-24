package com.example.testtask.service.address;

import com.example.testtask.dao.model.Address;
import com.example.testtask.dao.repository.AddressRepository;
import com.example.testtask.exception.ResourceNotFoundException;
import com.example.testtask.service.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository repository;

    public Address saveFromResponse(final Response response) {
        final com.example.testtask.service.dto.Address address = response.getAddress();
        if (repository.findByZipcode(address.getZipcode()).isEmpty()) {
            return this.repository.save(
                    Address.builder()
                            .street(address.getStreet())
                            .suite(address.getSuite())
                            .city(address.getCity())
                            .zipcode(address.getZipcode())
                            .geoLat(Double.valueOf(address.getGeo().getLat()))
                            .geoLng(Double.valueOf(address.getGeo().getLng()))
                            .build());
        }
        return repository.findByZipcode(address.getZipcode())
                .orElseThrow(() -> new ResourceNotFoundException(Address.class, address.getZipcode()));
    }
}
