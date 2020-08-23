package com.fayzal.springboot.datajpaactual.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.fayzal.springboot.datajpaactual.app.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	public Usuario findByUsername(String username);
	
}
