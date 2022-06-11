package com.gsnotes.utils.export;

import java.io.IOException;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	private String[] columnNames;
	private String[][] data;
	public ExcelExporter(String[] columnNames, String[][] data, String sheetName) {
		
		this.columnNames = columnNames;
		this.data = data;
		workbook = new XSSFWorkbook();
		

		}

	public ExcelExporter(String[] columnNames, String[][] data, String sheetName,String[] columns1,String[] columns2) {
		
		workbook = new XSSFWorkbook();
		this.sheet = workbook.createSheet(sheetName);
		this.columnNames = columnNames;
		this.data = data;
			write2FirstRows(columns1, 0);
			write2FirstRows(columns2, 1);
		}
		
	

	public void write2FirstRows(String[] columns, int i) {

				Row row = sheet.createRow(i);

				CellStyle style = workbook.createCellStyle();
				XSSFFont font = workbook.createFont();
				font.setBold(true);
				font.setFontHeight(16);
				style.setFont(font);
				
				for (String it : columns) {
					createCell(row, (i++), it, style);
				}

		}
	
	private void writeHeaderLine() {

		

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		int i = 0;
		for (String it : columnNames) {
			createCell(row, (i++), it, style);
		}

	}
	

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	
	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (int i = 0; i < data.length; i++) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			for (int j = 0; j < data[i].length; j++) {
				createCell(row, columnCount++, data[i][j], style);
			}
		}

	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}
}
