package com.fayzal.springboot.datajpaactual.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fayzal.springboot.datajpaactual.app.service.IUploadService;

@SpringBootApplication
public class SpringBootDataJpaActualApplication implements CommandLineRunner {

	@Autowired
	private IUploadService uploadService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaActualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		uploadService.deleteAll();
		uploadService.init();
		//Se crean los password para agregar a los usuarios
		String password = "1234";
		
		for(int i=0;i<2;i++) {
			// Se hace el proceso de codificacion de estos para mostrarlos por pantalla
			String passwordEncode = this.passwordEncoder.encode(password);
			System.out.println(passwordEncode);
		}
		 
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
