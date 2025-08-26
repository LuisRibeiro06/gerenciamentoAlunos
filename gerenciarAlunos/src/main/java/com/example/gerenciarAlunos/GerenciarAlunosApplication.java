package com.example.gerenciarAlunos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GerenciarAlunosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciarAlunosApplication.class, args);
	}

}
