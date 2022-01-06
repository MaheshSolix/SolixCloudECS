package arca.common;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

	private String inputFile;
	private String sheetName;
	
	//Constructor
	public ReadExcel(String inputFile) {
		this.inputFile = inputFile;
	}
	
	public ReadExcel(String inputFile, String sheetName) {
		this.inputFile = inputFile;
		this.sheetName = sheetName;
	}
	public Object[][] readArray() {
		System.out.println("readArray");
		File inputWorkBook = new File(inputFile);
		Workbook w;
		Object[][] dataArray =null;
		
		try {
			w=Workbook.getWorkbook(inputWorkBook);
			Sheet sheet = w.getSheet(sheetName);
			System.out.println(sheetName);
			//set the size of array
			dataArray = new Object[sheet.getRows()-1][sheet.getColumns()];
			int ci = 0; //put them in right order
			int cj; //put them in right order
			
				for (int row=1; row < sheet.getRows() ; row++,ci++){
				//	System.out.println("row"+row);
					cj=0;
					for(int col=0;col < sheet.getColumns();col++,cj++){
						
						dataArray[ci][cj] = sheet.getCell(col, row).getContents();
						//System.out.println(dataArray[ci][cj]+"  ");
					
				}
			}
		} catch (BiffException e) {
			
			e.printStackTrace();
			System.out.println("readArray problem1");
		} catch (IOException e) {
			System.out.println("readArray problem2");
			e.printStackTrace();
		}
		
		
		
		return(dataArray);
	}
	
	// Read All contents
	public String readAll() throws IOException {
		
		File inputWorkbook = new File(inputFile);
		Workbook w;	
		String data = null;
		
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			
			//Sheet sheet = w.getSheet(0);
			Sheet sheet = w.getSheet(sheetName);
			System.out.println(w.getSheetNames());
	//sheet.findLabelCell(arg0)
			
	//		System.out.println(sheet);
	
			
				for (int row = 0; row < sheet.getRows(); row++){
					for (int col= 0; col < sheet.getColumns(); col++) {
					
					Cell cell = sheet.getCell(col, row);
					CellType type = cell.getType();
				
					if (type == CellType.LABEL) {
						
						if (data == null) {
							
							data = cell.getContents();
						}
						
						else 
							data = data + ',' +cell.getContents();
						
						
						
						
					}
					
					if (type == CellType.NUMBER) {
						
							if (data == null) {
								data = cell.getContents();
							}
							else 
								data = data + ',' + cell.getContents();
					} 
					
					
					
				} // for COL
				
			} //for ROW
		
				
		}		catch (BiffException e) {
			
			e.printStackTrace();			
		} //catch
					
		
		return data;
		} //readAll

	// Read row
		public String[] readRow(int rowNum) throws IOException {
			
			File inputWorkbook = new File(inputFile);
			Workbook w;	
		String[] data = null;
		
			try {
				w = Workbook.getWorkbook(inputWorkbook);
				
				//Sheet sheet = w.getSheet(0);
				Sheet sheet = w.getSheet(sheetName); 
				System.out.println("SHEET NAME IS:"+sheet);
				data = new String[sheet.getColumns()];
				
		//		System.out.println(sheet);
				
		
				for (int col= 0; col<sheet.getColumns(); col++) {
					
					
						
					//	Cell cell = sheet.getCell(col, rowNum);
						//CellType type = cell.getType();
						
					//	if (type == CellType.LABEL) {
							
						//	if (data == null) {
								
							//	data[col] = cell.getContents();
								data[col] = sheet.getCell(col, rowNum).getContents();
					//		}
							
					//		else 
					//			data = data + '`' +cell.getContents();
								
					//	}
						
					/*	if (type == CellType.NUMBER) {
							
							if (data == null) {
								
								data = cell.getContents();
							}
							
							else 
								data = data + '`' +cell.getContents();
							
						}
					*/	
					
				} //for int col
			} catch (BiffException e) {
				
				e.printStackTrace();			
			} //catch
						
		return data;
		} //readRow
		
		// Read Column
		public String[] readColumn(int colNum) throws IOException {
			
			File inputWorkbook = new File(inputFile);
			Workbook w;	
			String[] data = null;
			try {
				w = Workbook.getWorkbook(inputWorkbook);
				
				//Sheet sheet = w.getSheet(0);
				Sheet sheet = w.getSheet(sheetName);
				data = new String[sheet.getRows()];
		//		System.out.println(sheet);
		
				for (int row= 0; row<sheet.getRows(); row++) {
						
					//	Cell cell = sheet.getCell(colNum, row);
						data[row] = sheet.getCell(row, colNum).getContents();
//						data = cell.getContents();
						
					/*	CellType type = cell.getType();
						
						if (type == CellType.LABEL) {
							
							if (data == null) {
								
								data = cell.getContents();
							}
							
							else 
								data = data + ',' +cell.getContents();
							
						}
						
						if (type == CellType.NUMBER) {
							
							if (data == null) {
								
								data = cell.getContents();
							}
							
							else 
								data = data + ',' +cell.getContents();
							
						}
						*/
					
				} //for int x
			} catch (BiffException e) {
				
				e.printStackTrace();			
			} //catch
			return data;	
			} //readColumn
		
public String readCell(int colNum, int rowNum) throws IOException {
		
	File inputWorkbook = new File(inputFile);
	Workbook w;	
	String data = null;
		
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			
			//Sheet sheet = w.getSheet(0);
			Sheet sheet = w.getSheet(sheetName);
	//		System.out.println(sheet);
	
					
					Cell cell = sheet.getCell(colNum, rowNum);
					CellType type = cell.getType();
					
				//	System.out.println("I got a label " + sheet.findCell(data));
					
					if (type == CellType.LABEL) {
						
						if (data == null) {
							
							data = cell.getContents();
						}
						
												
					}
					
					if (type == CellType.NUMBER) {
						
						if (data == null) {
							
							data = cell.getContents();
						}
						
						else 
							data = data + ',' +cell.getContents();
						
					}
		
		} catch (BiffException e) {
			
			e.printStackTrace();			
		} //catch
		
		return data;			
		} //readCell


//Example usage 

}
