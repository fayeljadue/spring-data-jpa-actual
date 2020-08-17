package com.fayzal.springboot.datajpaactual.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fayzal.springboot.datajpaactual.app.models.dao.IClienteDao;
import com.fayzal.springboot.datajpaactual.app.models.dao.IFacturaDao;
import com.fayzal.springboot.datajpaactual.app.models.dao.IProductoDao;
import com.fayzal.springboot.datajpaactual.app.models.entity.Cliente;
import com.fayzal.springboot.datajpaactual.app.models.entity.Factura;
import com.fayzal.springboot.datajpaactual.app.models.entity.Producto;

@Service("ClienteServiceDaoNormal")
public class ClienteServiceImp implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired 
	private IFacturaDao facturaDao;
	
	@Autowired
	private IProductoDao productoDao;
	
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
	public Cliente findOne(Long id) {
		// TODO Auto-generated method stub
		return clienteDao.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		clienteDao.delete(id);
	}

	@Override
	public Page<Cliente> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String term) {
		return productoDao.findByNombreLikeIgnoreCase("%" + term + "%");
	}

	@Override
	@Transactional
	public void saveFactura(Factura factura) {
		facturaDao.save(factura);
	}

	@Override
	@Transactional(readOnly=true)
	public Producto findProductoById(Long id) {
		// TODO Auto-generated method stub
		return productoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Factura findFacturaById(Long id) {
		// TODO Auto-generated method stub
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteFactura(Long id) {
		facturaDao.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Factura findByIdWithClienteWithItemFacturaWithProducto(Long id) {
		return facturaDao.findByIdWithClienteWithItemFacturaWithProducto(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Cliente findClienteByIdWithFacturas(Long id) {
		// TODO Auto-generated method stub
		return clienteDao.findClienteByIdWithFacturas(id);
	}

}
