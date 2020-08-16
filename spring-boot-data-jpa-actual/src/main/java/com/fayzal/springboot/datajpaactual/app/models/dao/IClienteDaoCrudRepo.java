package com.fayzal.springboot.datajpaactual.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.fayzal.springboot.datajpaactual.app.models.entity.Cliente;

public interface IClienteDaoCrudRepo extends CrudRepository<Cliente, Long> {

}
