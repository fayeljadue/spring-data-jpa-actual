package com.fayzal.springboot.datajpaactual.app.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(name = "error",required = false) String error,
			@RequestParam(name = "logout",required = false) String logout,
			Model model, Principal principal,RedirectAttributes flash) {
		
		//Si ya se encuentra logeada la persona, esta variable se pobla.
		
		if(principal != null){
			flash.addFlashAttribute("info","Ya se encuentra logeado");
			return "redirect:/";
		}
		
		if(error!=null) {
			model.addAttribute("error", "Nombre de usuario o contraseña invalida");
		}
		
		if(logout != null) {
			model.addAttribute("success", "Sesion cerrada correctamente");
		}
		
		return "login";
	}
	
}
