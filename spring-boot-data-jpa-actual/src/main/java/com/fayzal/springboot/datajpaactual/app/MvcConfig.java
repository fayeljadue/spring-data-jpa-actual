package com.fayzal.springboot.datajpaactual.app;

import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		/*Configuracion de directorio externo al proyecto
		 * Esto es para el Metodo 2
		registry.addResourceHandler("/uploads/**").
		addResourceLocations("file:/C:/Temp/uploads/");*/
		
		/* Metodo 3: Configuracion de directorio interno personalizado al proyecto pero distinto al static
		
		String recursos = Paths.get("uploads").toAbsolutePath().toUri().toString();
		
		registry.addResourceHandler("/uploads/**").
		addResourceLocations(recursos);*/
	}
	
	
	
	
}
