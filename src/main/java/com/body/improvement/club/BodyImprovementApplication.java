package com.body.improvement.club;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
@EnableSwagger2
public class BodyImprovementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BodyImprovementApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("Running python script");
			// Current working directory
			String cwd = System.getProperty("user.dir");
			System.out.println("Current working directory : " + cwd);
			String[] command = new String[]{"python", cwd + "/src/main/java/com/body/improvement/club/utility/transactions.py"};
			Process process = Runtime.getRuntime().exec(command);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String errorLine;
			while ((errorLine = errorReader.readLine()) != null) {
				System.out.println("Error: " + errorLine);
			}

			int exitStatus = process.waitFor();
			if (exitStatus == 0) {
				System.out.println("Python script ran successfully");
			} else {
				System.out.println("Python script failed");
			}
		};
	}

}
