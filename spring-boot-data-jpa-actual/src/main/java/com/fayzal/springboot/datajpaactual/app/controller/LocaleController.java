package com.fayzal.springboot.datajpaactual.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Controlador encargado del cambio de idioma para la pagina, debido a que se tiene spring security se debe poner
//Dentro de las urls aceptadas anonimante
@Controller
public class LocaleController {

	@GetMapping("/locale")
	public String locale(HttpServletRequest request) {
		String ultimaUrl= request.getHeader("referer");
		return "redirect:".concat(ultimaUrl);
	}
	
}
