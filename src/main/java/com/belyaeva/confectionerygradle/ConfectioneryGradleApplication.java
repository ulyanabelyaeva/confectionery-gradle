package com.belyaeva.confectionerygradle;

import com.belyaeva.confectionerygradle.services.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ConfectioneryGradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfectioneryGradleApplication.class, args);
	}
}
