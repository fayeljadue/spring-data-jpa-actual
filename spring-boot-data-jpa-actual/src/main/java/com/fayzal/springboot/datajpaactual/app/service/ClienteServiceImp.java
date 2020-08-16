package com.fayzal.springboot.datajpaactual.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fayzal.springboot.datajpaactual.app.models.dao.IClienteDao;
import com.fayzal.springboot.datajpaactual.app.models.entity.Cliente;

@Service("ClienteServiceDaoNormal")
public class ClienteServiceImp implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return clienteDao.findAll();
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
		return clienteDao.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		clienteDao.delete(id);
	}

	@Override
	public Page<Cliente> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

}
