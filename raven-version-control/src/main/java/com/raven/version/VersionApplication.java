package com.raven.version;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class VersionApplication {

	public static void main(String[] args) {
		SpringApplication.run(VersionApplication.class, args);
	}

}
