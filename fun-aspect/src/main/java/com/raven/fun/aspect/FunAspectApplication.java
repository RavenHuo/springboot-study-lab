package com.raven.fun.aspect;

import com.raven.fun.aspect.annontation.EnableSimpleLog;
import com.raven.springboot.annotation.EnableOptimisticHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSimpleLog
@EnableOptimisticHandler
public class FunAspectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FunAspectApplication.class, args);
	}

}
