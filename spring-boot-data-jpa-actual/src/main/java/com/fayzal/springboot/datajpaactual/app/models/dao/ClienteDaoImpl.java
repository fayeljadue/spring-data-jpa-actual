package com.fayzal.springboot.datajpaactual.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.fayzal.springboot.datajpaactual.app.models.entity.Cliente;

@Repository
public class ClienteDaoImpl implements IClienteDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> findAll() {
		
		List<Cliente> clientes = em.createQuery("from Cliente").getResultList();
		
		return clientes;
	}

	@Override
	public void save(Cliente cliente) {
		
		if(cliente.getId()>0 && cliente.getId()!= null) {
			em.merge(cliente);
		}else {
		em.persist(cliente);
		}
	}

	@Override
	public Cliente findOne(Integer id) {
		return em.find(Cliente.class, id);
	}

	@Override
	public void delete(Integer id) {
		Cliente cliente = this.findOne(id);
		em.remove(cliente);
		return;
	}

}
