package com.fayzal.springboot.datajpaactual.app;

import java.nio.file.Paths;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

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
	//Sirve para registrar un viewController, solo renderiza una vista en especifico
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/error_403").setViewName("errores/error_403");
	}
	
	//Se encarga del adpatador en el cual se guarda el locate, puede ser por http, session o con cookies.
	//en este caso se usa por Session, se encarga de tomar el idioma
	@Bean
	public LocaleResolver localeResolver() {
		
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("es","ES"));
		return localeResolver;
	}
	
	//Se ejecuata antes del request y permite cambiar los valores, o identificarlos en la peticion
	//con el parametro lang,cada vez que se pase por url por get el parametro lang, se ejecuta y realiza el cambio
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		localeInterceptor.setParamName("lang");
		return localeInterceptor;
	}
	
	//Registra el interceptor en la aplicacion.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	//Bean para la configuracion de realizar el proceso de marshall a las clases que se necesiten,
	//los serializa a xml, necesita de esto para poder realizar la conversion, se debe configurar Wrapper ClienteList
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		//En marca la clase wrapper de conversion
		marshaller.setClassesToBeBound(new Class[] {com.fayzal.springboot.datajpaactual.app.view.xml.ClienteList.class});
		return marshaller;
		
	}
	
}
