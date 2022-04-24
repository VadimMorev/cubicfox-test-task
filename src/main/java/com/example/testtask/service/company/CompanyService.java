package com.example.testtask.service.company;

import com.example.testtask.dao.model.Company;
import com.example.testtask.dao.repository.CompanyRepository;
import com.example.testtask.exception.ResourceNotFoundException;
import com.example.testtask.service.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository repository;

    public Company saveFromResponse(final Response response) {
        final com.example.testtask.service.dto.Company company = response.getCompany();
        if (repository.findByName(company.getName()).isEmpty()) {
            return this.repository.save(
                    Company.builder()
                            .name(company.getName())
                            .catchPhrase(company.getCatchPhrase())
                            .bs(company.getBs())
                            .build());
        }
        return repository.findByName(company.getName())
                .orElseThrow(() -> new ResourceNotFoundException(Company.class, company.getName()));
    }
}
