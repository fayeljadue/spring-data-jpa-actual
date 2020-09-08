package com.fayzal.springboot.datajpaactual.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fayzal.springboot.datajpaactual.app.service.IClienteService;
import com.fayzal.springboot.datajpaactual.app.view.xml.ClienteList;

@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {

	@Autowired
	@Qualifier("ClienteServiceDaoPaginableAndSort")
	private IClienteService clienteDao;
	
	@GetMapping(value = "/listar")
	public ClienteList listar() { 
		//Para que funciones como Json y como xml se debe realizar por medio de la clase wrapper,
		//si solo se desea con json se puede realizar de forma directa retornando nada mas la clase pojo
		//return clienteDao.findAll();
		return new ClienteList(clienteDao.findAll());	
	}
	
}
