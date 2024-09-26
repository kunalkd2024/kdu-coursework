package com.caching;

import com.caching.assignment2.Assignment2Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = { Assignment2Application.class })
class CachingApplicationTest {

    @Test
    void contextLoads() {
        // This test method can be empty; it's just used to ensure that the application context loads successfully
    }
}