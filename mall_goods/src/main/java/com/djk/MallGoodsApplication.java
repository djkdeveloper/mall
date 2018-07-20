package com.djk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MallGoodsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallGoodsApplication.class, args);
	}
}
