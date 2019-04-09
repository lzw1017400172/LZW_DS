package com.lzw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by LZW on 2019/4/9.
 */
@SpringBootApplication
@EnableEurekaClient
public class LzwUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(LzwUploadApplication.class, args);
    }
}
