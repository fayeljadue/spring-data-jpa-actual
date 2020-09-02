package com.fayzal.springboot.datajpaactual.app.view.pdf;

import java.awt.Color;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.fayzal.springboot.datajpaactual.app.models.entity.Factura;
import com.fayzal.springboot.datajpaactual.app.models.entity.ItemFactura;
import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

//Se debe poner el nombre de la vista que renderiza, de tal forma que sea llamado cuando se desee renderizar como pdf
@Component("factura/ver")
public class FacturaPdfView extends AbstractPdfView {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private LocaleResolver localeResolver;
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Factura factura = (Factura) model.get("factura");
		
		Locale locale = localeResolver.resolveLocale(request);
		
		//con este metodo se puede obtener los mensaje sin tener que implementar las intefaces MessageSource, Local Resolver
		//Ya las maneja de forma interna
		MessageSourceAccessor message = getMessageSourceAccessor();
		
		PdfPTable tabla= new PdfPTable(1);
		tabla.setSpacingAfter(20);
		
		PdfPCell cell = null;
		
		cell = new PdfPCell(new Phrase(messageSource.getMessage("texto.factura.pdf.datosCliente", null, locale)));
		cell.setBackgroundColor(new Color(184,218,255));
		cell.setPadding(8f);
		tabla.addCell(cell);
		
		tabla.addCell(factura.getCliente().getNombre()+" "+ factura.getCliente().getApellido());
		tabla.addCell(factura.getCliente().getEmail());
		
		PdfPTable tabla2= new PdfPTable(1);
		tabla2.setSpacingAfter(20);
		//message.getMessage("texto.factura.pdf.datosFactura") metodo con el MessageSourceAccessor
		cell = new PdfPCell(new Phrase(message.getMessage("texto.factura.pdf.datosFactura")));
		cell.setBackgroundColor(new Color(195,230,203));
		cell.setPadding(8f);
		
		tabla2.addCell(cell);
		
		tabla2.addCell(messageSource.getMessage("texto.factura.pdf.folio", null, locale) +" "+ factura.getId());
		tabla2.addCell(messageSource.getMessage("texto.factura.pdf.descripcion", null, locale) +" " + factura.getDescripcion());
		tabla2.addCell(messageSource.getMessage("texto.factura.pdf.fecha", null, locale)+" "+ factura.getCreateAt());
		
		PdfPTable tabla3= new PdfPTable(4);
		tabla3.setWidths(new float[] {3.5f, 1f, 1f, 1f});
		tabla3.addCell(messageSource.getMessage("texto.factura.pdf.producto", null, locale));
		tabla3.addCell(messageSource.getMessage("texto.factura.pdf.precio", null, locale));
		tabla3.addCell(messageSource.getMessage("texto.factura.pdf.cantidad", null, locale));
		tabla3.addCell(messageSource.getMessage("texto.factura.pdf.total", null, locale));
		
		for(ItemFactura item:factura.getItems()) {
			tabla3.addCell(item.getProducto().getNombre());
			tabla3.addCell(item.getProducto().getPrecio().toString());
			
			cell = new PdfPCell(new Phrase(item.getCantidad().toString()));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			tabla3.addCell(cell);
			tabla3.addCell(item.calcularImporte().toString());
		}
		
		cell = new PdfPCell(new Phrase(messageSource.getMessage("texto.factura.pdf.total", null, locale)));
		cell.setColspan(3);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		tabla3.addCell(cell);
		tabla3.addCell(factura.getTotal().toString());
		
		document.add(tabla);
		document.add(tabla2);
		document.add(tabla3);
	}

}
