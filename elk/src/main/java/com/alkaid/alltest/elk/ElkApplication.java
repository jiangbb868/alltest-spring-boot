package com.alkaid.alltest.elk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElkApplication {

    private static Logger logger = LoggerFactory.getLogger(ElkApplication.class);

    public static void main(String[] args) {
        logger.info("enter application main function");
        SpringApplication.run(ElkApplication.class, args);
    }
}
