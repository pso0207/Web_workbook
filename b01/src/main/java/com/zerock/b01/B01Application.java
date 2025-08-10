package com.zerock.b01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing  // AudingEntitiyListener 활성화 해줌
public class B01Application {

	public static void main(String[] args) {
		SpringApplication.run(B01Application.class, args);
	}

}
