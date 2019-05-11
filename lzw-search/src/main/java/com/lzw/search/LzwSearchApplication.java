package com.lzw.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)		//核心注解，包含三个 启动注解， configrutiond等
@EnableDiscoveryClient		//注册为eureka client
@EnableFeignClients			//开启feign通信
public class LzwSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(LzwSearchApplication.class, args);
	}

}
