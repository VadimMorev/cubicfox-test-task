package com.example.testtask;

import com.example.testtask.service.users.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestTaskApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(userService);
    }

}
