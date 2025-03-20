package com.example.springbatchstudy;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableBatchProcessing
@SpringBootApplication
public class SpringBatchStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchStudyApplication.class, args);
	}

}
