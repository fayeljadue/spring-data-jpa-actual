package com.fayzal.springboot.datajpaactual.app.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fayzal.springboot.datajpaactual.app.models.dao.IUsuarioDao;
import com.fayzal.springboot.datajpaactual.app.models.entity.Role;
import com.fayzal.springboot.datajpaactual.app.models.entity.Usuario;

@Service
//Implementa el userDetailsService con el fin de tener los metodos para trabajar con spring security
public class JpaUserDatailsService implements UserDetailsService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IUsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario==null) {
			log.error("Error login: no existe el usuario: "+ username + "en la base de datos");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(Role rol:usuario.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(rol.getAuthority()));
		}
		
		if(authorities.isEmpty()) {
			log.info("Error login: usuario "+username+"no tiene roles asginados!");
			throw new UsernameNotFoundException("Error login: usuario '" + username + "'no tiene roles asignados");
		}
		
		return new User(usuario.getUsername(),usuario.getPassword(),usuario.getEnable(),true,true,true,authorities);
	}

}
