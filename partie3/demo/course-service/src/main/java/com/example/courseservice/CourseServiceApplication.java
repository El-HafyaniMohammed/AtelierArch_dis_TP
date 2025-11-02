package com.example.courseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.courseservice")
public class CourseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseServiceApplication.class, args);
	}

}