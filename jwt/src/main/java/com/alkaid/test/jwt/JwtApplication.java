package com.alkaid.test.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtApplication {

    private Logger logger = LoggerFactory.getLogger(JwtApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }
}
