package com.fayzal.springboot.datajpaactual.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {

	private String url;
	private Page<T> page;
	private int totalPaginas;
	private int numElementosPorPagina;
	private List<PageItem> paginas;
	
	private int paginaActual;
	public PageRender(String url, Page<T> page) {
		
		this.url = url;
		this.page = page;
		numElementosPorPagina = page.getSize();
		totalPaginas = page.getTotalPages();
		paginaActual = page.getNumber() +1;
		
		int desde,hasta;
		if(totalPaginas <= numElementosPorPagina) {
			desde = 1;
			hasta = totalPaginas;
		}else {
			if(paginaActual <=numElementosPorPagina/2) {
				desde=1;
				hasta=numElementosPorPagina;
			}else if(paginaActual >= totalPaginas-numElementosPorPagina/2) {
				desde= totalPaginas - numElementosPorPagina + 1;
				hasta= numElementosPorPagina;
			}else {
				desde = paginaActual - numElementosPorPagina/2;
				hasta = numElementosPorPagina;
			}
		}
		//System.out.println("Desde: "+desde+" , Hasta: "+hasta);
		
		paginas = new ArrayList<PageItem>();
		for(int i=0;i<hasta;i++) {
			paginas.add(new PageItem(desde+i, paginaActual == desde+i));
		}
		
	}
	public String getUrl() {
		return url;
	}
	public int getTotalPaginas() {
		return totalPaginas;
	}
	public List<PageItem> getPaginas() {
		return paginas;
	}
	public int getPaginaActual() {
		return paginaActual;
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean hasNext() {
		return page.hasNext();
	}
	
	public boolean hasPrevious() {
		return page.hasPrevious();
	}
}
