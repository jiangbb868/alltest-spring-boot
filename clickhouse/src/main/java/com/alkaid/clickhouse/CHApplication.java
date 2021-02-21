package com.alkaid.clickhouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.alkaid.*.mapper")
public class CHApplication {
    public static void main(String[] args) {
        SpringApplication.run(CHApplication.class, args);
    }
}
