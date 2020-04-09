package com.its.itone.sas.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SasConfigApplication {
    private Logger LOGGER = LoggerFactory.getLogger(SasConfigApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SasConfigApplication.class, args);
    }
}
