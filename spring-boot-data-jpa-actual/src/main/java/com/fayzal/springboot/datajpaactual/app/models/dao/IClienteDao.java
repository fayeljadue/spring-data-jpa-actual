package com.fayzal.springboot.datajpaactual.app.models.dao;

import java.util.List;

import com.fayzal.springboot.datajpaactual.app.models.entity.Cliente;

public interface IClienteDao {
	
	public List<Cliente> findAll();
	
	public void save(Cliente cliente);
	
	public Cliente findOne(Integer id);
	
	public void delete(Integer id);
}
