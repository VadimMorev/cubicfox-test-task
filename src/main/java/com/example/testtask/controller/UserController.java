package com.example.testtask.controller;

import com.example.testtask.service.dto.Response;
import com.example.testtask.service.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Response[]> getUsers() {
        ResponseEntity<Response[]> users = userService.getUsers();
        return ResponseEntity.ok(users.getBody());
    }
}
