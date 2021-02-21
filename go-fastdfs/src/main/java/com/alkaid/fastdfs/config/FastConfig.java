package com.alkaid.fastdfs.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class FastConfig {

    @Value("${fastdfs.url}")
    private String url;

}
