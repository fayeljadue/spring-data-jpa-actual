package com.fayzal.springboot.datajpaactual.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.fayzal.springboot.datajpaactual.app.models.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long>{

}