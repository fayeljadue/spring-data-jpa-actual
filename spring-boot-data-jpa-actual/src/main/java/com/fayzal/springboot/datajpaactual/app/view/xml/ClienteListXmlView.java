package com.fayzal.springboot.datajpaactual.app.view.xml;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.fayzal.springboot.datajpaactual.app.models.entity.Cliente;

@Component("listar.xml")
public class ClienteListXmlView  extends MarshallingView{

	//Invoca el Bean declarado en la clase MvcConfig que realiza el proceso de conversion de objetos a XML
	@Autowired
	public ClienteListXmlView(Jaxb2Marshaller marshaller) {
		super(marshaller);
	}
	
	//Se debe tener en cuenta la modificacion que se debio realizar en la clase factura acerca de la relacion del
	//modelo entre la factura y el cliente debido a que genera ciclo en la conversion a XML

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		model.remove("titulo");
		model.remove("page");
		
		Page<Cliente> clientes = (Page<Cliente>) model.get("clientes");
		
		model.remove("clientes");
		
		model.put("clientes", new ClienteList(clientes.getContent()));
		
		super.renderMergedOutputModel(model, request, response);
	}
	
}
