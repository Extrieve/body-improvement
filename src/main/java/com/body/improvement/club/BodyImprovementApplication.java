package com.body.improvement.club;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BodyImprovementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BodyImprovementApplication.class, args);
	}

}
