package com.joseajcabul.ahorcado;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AhorcadoFinalProyectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AhorcadoFinalProyectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("funcionando api ");
	}
}
