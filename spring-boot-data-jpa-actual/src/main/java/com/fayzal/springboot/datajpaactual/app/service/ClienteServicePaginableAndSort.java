package com.fayzal.springboot.datajpaactual.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fayzal.springboot.datajpaactual.app.models.dao.IClienteDaoPageableAndSorting;
import com.fayzal.springboot.datajpaactual.app.models.entity.Cliente;

@Service("ClienteServiceDaoPaginableAndSort")
public class ClienteServicePaginableAndSort implements IClienteService {

	@Autowired
	private IClienteDaoPageableAndSorting clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);

	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Integer id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable page) {
		
		return clienteDao.findAll(page);
	}

}
