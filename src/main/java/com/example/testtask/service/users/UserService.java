package com.example.testtask.service.users;

import com.example.testtask.dao.model.User;
import com.example.testtask.dao.repository.UserRepository;
import com.example.testtask.exception.InvalidResponseException;
import com.example.testtask.file.FileLogger;
import com.example.testtask.service.address.AddressService;
import com.example.testtask.service.company.CompanyService;
import com.example.testtask.service.dto.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private static final String USER_EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String RESPONSE_LOG_SEPARATOR = "-----------------------------------";
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final CompanyService companyService;
    private final AddressService addressService;
    private final FileLogger fileLogger;
    @Value("${request.base-url}")
    private String url;

    public ResponseEntity<Response[]> getUsers() {
        final ResponseEntity<Response[]> response = this.restTemplate.
                exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Response[].class);
        this.checkResponse(response);
        final List<User> users = Arrays.stream(Objects.requireNonNull(response.getBody()))
                .map(r -> User.builder()
                        .name(r.getName())
                        .username(r.getUsername())
                        .email(validateUserEmail(r.getEmail()))
                        .address(this.addressService.saveFromResponse(r))
                        .phoneNumber(r.getPhone())
                        .website(r.getWebsite())
                        .company(this.companyService.saveFromResponse(r))
                        .build()
                ).filter(o -> !this.userRepository.existsByEmail(o.getEmail()) && Objects.nonNull(o.getEmail()))
                .collect(Collectors.toList());
        logGetUsersQuery(response);
        userRepository.saveAll(users);
        return response;
    }

    private String validateUserEmail(final String email) {
        if (email.matches(USER_EMAIL_REGEX)) {
            return email.toLowerCase().trim();
        }
        return null;
    }

    private void logGetUsersQuery(final ResponseEntity<Response[]> response) {
        ObjectMapper mapper = new ObjectMapper();
        fileLogger.log(format("Query for url: %s, method %s with body %s, done on time: %s", url, HttpMethod.GET.name(),
                HttpEntity.EMPTY.getBody(), LocalDateTime.now()));
        try {
            fileLogger.log(format("Query response with status code %d and body: %n %s %n %s", response.getStatusCode().value(),
                    prettifyJsonString(response, mapper), RESPONSE_LOG_SEPARATOR));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String prettifyJsonString(final ResponseEntity<Response[]> response,
                                      final ObjectMapper mapper) throws JsonProcessingException {
        return mapper.writeValueAsString(response.getBody())
                .replace(",", ",\n")
                .replace("{", "\n{")
                .replace("}", "\n}");
    }

    private void checkResponse(final ResponseEntity<Response[]> response) {
        final HttpStatus statusCode = response.getStatusCode();
        final long count = userRepository.count();
        final int length = Objects.requireNonNull(response.getBody()).length;
        if (statusCode.isError()) {
            throw new InvalidResponseException(format("Invalid response because of status code %d", statusCode.value()));
        } else if (length != count) {
            log.error("User count is not equal to count of users in database");
        }
    }
}
