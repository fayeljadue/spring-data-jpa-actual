package com.fayzal.springboot.datajpaactual.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fayzal.springboot.datajpaactual.app.models.entity.Cliente;

public interface IClienteDaoPageableAndSorting extends PagingAndSortingRepository<Cliente, Integer>{
	
	

}
