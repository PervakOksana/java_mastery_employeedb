package com.mastery.java.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mastery.java.task.swagger.SwaggerConfig;


@SpringBootApplication
public class JavaMasteryEmployeedbJPAApplication {


	public static void main(String[] args) {
		
		SpringApplication.run(JavaMasteryEmployeedbJPAApplication.class, args);
		
	}

}
