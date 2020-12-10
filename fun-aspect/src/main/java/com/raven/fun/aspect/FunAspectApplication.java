package com.raven.fun.aspect;

import com.raven.fun.aspect.annontation.EnableSimpleLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableSimpleLog
public class FunAspectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FunAspectApplication.class, args);
	}

}
