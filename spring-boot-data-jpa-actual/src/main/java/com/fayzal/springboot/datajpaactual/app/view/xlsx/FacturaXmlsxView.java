package com.fayzal.springboot.datajpaactual.app.view.xlsx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.fayzal.springboot.datajpaactual.app.models.entity.Factura;
import com.fayzal.springboot.datajpaactual.app.models.entity.ItemFactura;

@Component("factura/ver.xlsx")
public class FacturaXmlsxView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		MessageSourceAccessor message = getMessageSourceAccessor();
		
		response.setHeader("Content-Disposition", "attachment; filename=\"factura_view.xlsx\"");
		
		Factura factura = (Factura) model.get("factura");
		Sheet sheet = workbook.createSheet();
		
		//Declaracion de estilos para las celdas
		CellStyle theaderStyle = workbook.createCellStyle();
		theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
		theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
		theaderStyle.setBorderRight(BorderStyle.MEDIUM);
		theaderStyle.setBorderTop(BorderStyle.MEDIUM);
		theaderStyle.setFillForegroundColor(IndexedColors.GOLD.index);
		theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		CellStyle tbodyStyle = workbook.createCellStyle();
		tbodyStyle.setBorderBottom(BorderStyle.THIN);
		tbodyStyle.setBorderLeft(BorderStyle.THIN);
		tbodyStyle.setBorderRight(BorderStyle.THIN);
		tbodyStyle.setBorderTop(BorderStyle.THIN);
		
		
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue(message.getMessage("texto.factura.pdf.datosCliente"));
		cell.setCellStyle(theaderStyle);
		
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue(factura.getCliente().getNombre()+" "+factura.getCliente().getApellido());
		cell.setCellStyle(tbodyStyle);
		
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue(factura.getCliente().getEmail());
		cell.setCellStyle(tbodyStyle);
		
		row = sheet.createRow(4);
		cell = row.createCell(0);
		cell.setCellValue(message.getMessage("texto.factura.pdf.datosFactura"));
		cell.setCellStyle(theaderStyle);
		
		row = sheet.createRow(5);
		cell = row.createCell(0);
		cell.setCellValue(message.getMessage("texto.factura.pdf.folio"));
		cell.setCellStyle(tbodyStyle);
		
		row = sheet.createRow(6);
		cell = row.createCell(0);
		cell.setCellValue(message.getMessage("texto.factura.pdf.folio")+" "+factura.getDescripcion());
		cell.setCellStyle(tbodyStyle);
		
		row = sheet.createRow(7);
		cell = row.createCell(0);
		cell.setCellValue(message.getMessage("texto.factura.pdf.fecha")+" "+factura.getCreateAt());
		cell.setCellStyle(tbodyStyle);
		
		Row header = sheet.createRow(9);
		header.createCell(0).setCellValue(message.getMessage("texto.factura.pdf.producto"));
		header.createCell(1).setCellValue(message.getMessage("texto.factura.pdf.precio"));
		header.createCell(2).setCellValue(message.getMessage("texto.factura.pdf.cantidad"));
		header.createCell(3).setCellValue(message.getMessage("texto.factura.pdf.total"));
		
		header.getCell(0).setCellStyle(theaderStyle);
		header.getCell(1).setCellStyle(theaderStyle);
		header.getCell(2).setCellStyle(theaderStyle);
		header.getCell(3).setCellStyle(theaderStyle);
		
		int rownum=10;
		for(ItemFactura item: factura.getItems()) {
			Row fila=sheet.createRow(rownum ++);
			
			cell = fila.createCell(0);
			cell.setCellValue(item.getProducto().getNombre());
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(1);
			cell.setCellValue(item.getProducto().getPrecio());
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(2);
			cell.setCellValue(item.getCantidad());
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(3);
			cell.setCellValue(item.calcularImporte());
			cell.setCellStyle(tbodyStyle);
		}
		Row filaTotal = sheet.createRow(rownum);
		
		cell = filaTotal.createCell(2);
		cell.setCellValue(message.getMessage("texto.factura.pdf.total"));
		cell.setCellStyle(tbodyStyle);
		
		cell = filaTotal.createCell(3);
		cell.setCellValue(factura.getTotal());
		cell.setCellStyle(tbodyStyle);
	}

}
