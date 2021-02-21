package com.alkaid.isa;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.alkaid.isa.*.mapper")
public class IsaApplication {
    private Logger LOGGER = LoggerFactory.getLogger(IsaApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(IsaApplication.class, args);
    }
}
