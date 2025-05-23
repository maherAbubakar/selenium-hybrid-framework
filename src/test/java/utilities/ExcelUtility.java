package utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public FileInputStream fileInputStream;
	public FileOutputStream fileOutputStream;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;   
	String path;
	
	public ExcelUtility(String path)
	{
		this.path=path;
	}
		
	public int getRowCount(String sheetName) throws IOException 
	{
		fileInputStream=new FileInputStream(path);
		workbook=new XSSFWorkbook(fileInputStream);
		sheet=workbook.getSheet(sheetName);
		int rowCount=sheet.getLastRowNum();
		workbook.close();
		fileInputStream.close();
		return rowCount;
	}
	
	public int getCellCount(String sheetName,int rowNum) throws IOException
	{
		fileInputStream=new FileInputStream(path);
		workbook=new XSSFWorkbook(fileInputStream);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		int cellCount=row.getLastCellNum();
		workbook.close();
		fileInputStream.close();
		return cellCount;
	}
	
	
	public String getCellData(String sheetName,int rowNum,int colNum) throws IOException
	{
		fileInputStream=new FileInputStream(path);
		workbook=new XSSFWorkbook(fileInputStream);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try{
		data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fileInputStream.close();
		return data;
	}
	
	public void setCellData(String sheetName,int rowNum,int colNum,String data) throws IOException
	{
		File xlfile=new File(path);
		if(!xlfile.exists())    // If file not exists then create new file
		{
		workbook=new XSSFWorkbook();
			fileOutputStream=new FileOutputStream(path);
		workbook.write(fileOutputStream);
		}

		fileInputStream=new FileInputStream(path);
		workbook=new XSSFWorkbook(fileInputStream);
			
		if(workbook.getSheetIndex(sheetName)==-1) // If sheet not exists then create new Sheet
			workbook.createSheet(sheetName);
		sheet=workbook.getSheet(sheetName);
					
		if(sheet.getRow(rowNum)==null)   // If row not exists then create new Row
				sheet.createRow(rowNum);
		row=sheet.getRow(rowNum);
		
		cell=row.createCell(colNum);
		cell.setCellValue(data);
		fileOutputStream=new FileOutputStream(path);
		workbook.write(fileOutputStream);
		workbook.close();
		fileInputStream.close();
		fileOutputStream.close();
	}
	
	
	public void fillGreenColor(String sheetName,int rowNum,int colNum) throws IOException
	{
		fileInputStream=new FileInputStream(path);
		workbook=new XSSFWorkbook(fileInputStream);
		sheet=workbook.getSheet(sheetName);
		
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		
		style=workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
				
		cell.setCellStyle(style);
		workbook.write(fileOutputStream);
		workbook.close();
		fileInputStream.close();
		fileOutputStream.close();
	}
	
	
	public void fillRedColor(String sheetName,int rowNum,int colNum) throws IOException
	{
		fileInputStream=new FileInputStream(path);
		workbook=new XSSFWorkbook(fileInputStream);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		
		style=workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		
		cell.setCellStyle(style);		
		workbook.write(fileOutputStream);
		workbook.close();
		fileInputStream.close();
		fileOutputStream.close();
	}
	
}


