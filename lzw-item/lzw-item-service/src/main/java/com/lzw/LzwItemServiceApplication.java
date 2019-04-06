package com.lzw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.lzw.item.mapper")
public class LzwItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LzwItemServiceApplication.class, args);
	}
}
