package com.fayzal.springboot.datajpaactual.app.view.json;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fayzal.springboot.datajpaactual.app.models.entity.Cliente;
import com.fayzal.springboot.datajpaactual.app.view.xml.ClienteList;

//Para json a diferencia de xml, no se necesita wrapper ni un bean en la config
//viene implicito en el trabajo con java Spring
@Component("listar.json")
public class ClienteListJsonView extends MappingJackson2JsonView {

	@Override
	protected Object filterModel(Map<String, Object> model) {
		
		model.remove("titulo");
		model.remove("page");
		
		Page<Cliente> clientes = (Page<Cliente>) model.get("clientes");
		
		model.remove("clientes");
		//Como clientes es paginable con clientes.getContent() me regresa la lista de ese paginable
		model.put("clientes", clientes.getContent());
		
		
		return super.filterModel(model);
	}
	
	

}
