package com.qa.utility;

	import java.io.FileInputStream;
	import java.io.IOException;

	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

		public class Xcelclass {
			
			public static FileInputStream fileloc;
			public static XSSFWorkbook wBook;
		    public static XSSFSheet xSheet;
		    public static XSSFRow row;
		    public static XSSFCell cell;
		   
		    
		    public static int getRowCount(String xFile, String xSheetName) throws IOException {
		    	
		    	
		    fileloc= new FileInputStream(xFile);
		    wBook=new XSSFWorkbook(fileloc);
		    xSheet=wBook.getSheet(xSheetName);
		    int rowCount=xSheet.getLastRowNum();
		    return rowCount;
		    	
		    }
		    public static int getCellCount(String xFile, String xSheetName, int rowCount) throws IOException {
		    	
		    	fileloc= new FileInputStream(xFile);
		        wBook=new XSSFWorkbook(fileloc);
		        xSheet=wBook.getSheet(xSheetName);
		        row=xSheet.getRow(rowCount);
		        int CellCount=row.getLastCellNum();
		        return CellCount;
		    }
		    	
		public static String getCellData(String xFile, String xSheetName, int rowNum,int CellNum) throws IOException {
		    	
		    	fileloc= new FileInputStream(xFile);
		        wBook=new XSSFWorkbook(fileloc);
		        xSheet=wBook.getSheet(xSheetName);
		        row=xSheet.getRow(rowNum);
		        cell=row.getCell(CellNum);
		     
		        DataFormatter format=new DataFormatter();
		        String cellData=format.formatCellValue(cell);
		        
		        fileloc.close();
		        wBook.close();
		        
		        return cellData;
		}
	}


