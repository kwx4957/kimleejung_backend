package com.capstone.kimleejung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaAuditing
public class KimleejungApplication {

	public static void main(String[] args) {
		SpringApplication.run(KimleejungApplication.class, args);
	}

}
