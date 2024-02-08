package com.project.dasuri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DasuriApplication {
	public static void main(String[] args) {

		SpringApplication.run(DasuriApplication.class, args);

	}

}
