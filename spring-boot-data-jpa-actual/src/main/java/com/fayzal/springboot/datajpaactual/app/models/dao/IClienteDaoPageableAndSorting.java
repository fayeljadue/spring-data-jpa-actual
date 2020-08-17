package com.fayzal.springboot.datajpaactual.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fayzal.springboot.datajpaactual.app.models.entity.Cliente;

public interface IClienteDaoPageableAndSorting extends PagingAndSortingRepository<Cliente, Long>{
	
	@Query("select c from Cliente c left join fetch c.facturas where c.id=?1")
	public Cliente findClienteByIdWithFacturas(Long id);
}
