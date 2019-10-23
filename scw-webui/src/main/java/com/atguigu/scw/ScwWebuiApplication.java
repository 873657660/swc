package com.atguigu.scw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import feign.Retryer;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ScwWebuiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScwWebuiApplication.class, args);
	}

	//禁用Feign远程调用 重试
	@Bean
	public Retryer feignRetryer() {
		// return new Retryer.Default();
		return Retryer.NEVER_RETRY;
	}

}
