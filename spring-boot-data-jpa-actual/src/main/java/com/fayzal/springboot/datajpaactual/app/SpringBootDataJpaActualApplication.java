package com.fayzal.springboot.datajpaactual.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fayzal.springboot.datajpaactual.app.service.IUploadService;

@SpringBootApplication
public class SpringBootDataJpaActualApplication implements CommandLineRunner {

	@Autowired
	private IUploadService uploadService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaActualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		uploadService.deleteAll();
		uploadService.init();
	}

}
