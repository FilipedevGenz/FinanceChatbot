package com.filipedevgenz.mslisten;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MsListenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsListenApplication.class, args);
	}
	@Bean
	RestClient restClient() {
		return RestClient.create();
		}
	}

