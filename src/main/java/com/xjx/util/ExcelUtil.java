package com.xjx.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtil {
	public static Cell getCell(Row row, int index) {
		Cell cell = row.getCell(index);
		if (cell == null) {
			cell = row.createCell(index);
		}
		return cell;
	}

	public static Row getRow(Sheet sheet, int index) {
		Row row = sheet.getRow(index);
		if (row == null) {
			row = sheet.createRow(index);
		}
		return row;
	}

	public static String getCellValue(Cell cell) {
		if (cell == null)
			return "";
		String value;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			value = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				value = cell.getDateCellValue().toString();
			} else {
				value = String.valueOf((int) cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			value = cell.getCellFormula();
			break;
		case Cell.CELL_TYPE_BLANK:
			value = "";
			break;
		default:
			value = "";
		}
		return value;
	}

	public static void setCellValue(Cell cell, String value) throws Exception {
		if (cell == null)
			return;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			cell.setCellValue(value);
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				cell.setCellValue(value);
			} else {
				double doubleValue;
				if (value.endsWith("%")) {
					doubleValue = Double.parseDouble(value.substring(0,
							value.length() - 1)) / 100;
					if (doubleValue == 0) {
						cell.setCellValue("");
					} else {
						cell.setCellValue(doubleValue);
					}
				} else if (value.equals("")) {
					cell.setCellValue("");
				} else {
					try {
						doubleValue = Double.parseDouble(value);
					} catch (NumberFormatException e) {
						throw new Exception("excelģ���е�Ԫ��ĸ�ʽΪ��ֵ,����ֵ[" + value
								+ "]������ֵ,�޷���䵽excel��", e);
					}
					if (doubleValue == 0) {
						cell.setCellValue("");
					} else {
						cell.setCellValue(doubleValue);
					}
				}
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			cell.setCellValue(value);
			break;
		case Cell.CELL_TYPE_FORMULA:
			break;
		case Cell.CELL_TYPE_BLANK:
			cell.setCellValue(value);
			break;
		default:
			cell.setCellValue(value);
		}
	}
	
	public static void deleteSheet(Workbook wb, String sheetName) {
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			String name = wb.getSheetName(i);
			if (name.equalsIgnoreCase(sheetName)) {
				wb.removeSheetAt(i);
			}
		}
	}
}
