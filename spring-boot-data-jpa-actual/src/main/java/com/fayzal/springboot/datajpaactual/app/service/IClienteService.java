package com.fayzal.springboot.datajpaactual.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fayzal.springboot.datajpaactual.app.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable page);
	
	public void save(Cliente cliente);
	
	public Cliente findOne(Integer id);
	
	public void delete(Integer id);
	
}
