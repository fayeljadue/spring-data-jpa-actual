package com.fayzal.springboot.datajpaactual.app.view.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fayzal.springboot.datajpaactual.app.models.entity.Cliente;
//Esta clase es la que funciona como el wrapper de cambio de objeto a estructura xml


//Anotacion que permite darle la etiqueta al nodo principal del archivo de XML
@XmlRootElement(name="clientes")
public class ClienteList {

	//Los elementos a mapear
	@XmlElement(name="cliente")
	public List<Cliente> clientes;

	public ClienteList(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public ClienteList() {
		
	}

	public List<Cliente> getClientes() {
		return clientes;
	}
	
}
