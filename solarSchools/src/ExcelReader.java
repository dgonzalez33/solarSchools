import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader {
	public ExcelReader(){
	}

	public LinkedList<Double> getColumn(String file, int columnNumber) throws IOException{
		 File myFile = new File(file);
         FileInputStream fis = new FileInputStream(myFile);
         LinkedList<Double> values = new LinkedList<Double>();
         // Finds the workbook instance for XLSX file
         XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
        
         // Return first sheet from the XLSX workbook
         XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        
         // Get iterator to all the rows in current sheet
         Iterator<Row> rowIterator = mySheet.iterator();
        
         // Traversing over each row of XLSX file
         while (rowIterator.hasNext()) {
             Row row = rowIterator.next();

             // For each row, iterate through each columns
             Iterator<Cell> cellIterator = row.cellIterator();
             while (cellIterator.hasNext()) {
                 Cell cell = cellIterator.next();     
                 if(cell.getColumnIndex()==columnNumber){
                	 switch (cell.getCellType()) {
                     case Cell.CELL_TYPE_STRING:
                    	// System.out.println(cell.getStringCellValue()+"S at column Number "+columnNumber);
                         break;
                     case Cell.CELL_TYPE_NUMERIC:
                    	// System.out.println(cell.getNumericCellValue()+"N at column Number "+columnNumber);
                    	 values.add(cell.getNumericCellValue());
                         break;
                     default:
                	 }
                 }
             }
         }
         return values;
	}
//	public LinkedList<Double> getColumn(String file, int columnNumber) {
//		LinkedList<Double> values = new LinkedList<Double>();
//		try {
//		    POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
//		    HSSFWorkbook wb = new HSSFWorkbook(fs);
//		    HSSFSheet sheet = wb.getSheetAt(0);
//		    HSSFRow row;
//		    HSSFCell cell;
//
//		    int rows; // No of rows
//		    rows = sheet.getPhysicalNumberOfRows();
//
//		    int cols = 0; // No of columns
//		    int tmp = 0;
//
//		    // This trick ensures that we get the data properly even if it doesn't start from first few rows
//		    for(int i = 0; i < 10 || i < rows; i++) {
//		        row = sheet.getRow(i);
//		        if(row != null) {
//		            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
//		            if(tmp > cols) cols = tmp;
//		        }
//		    }
//		    for(Row r : sheet) {
//		    	   Cell c = r.getCell(columnNumber);
//		    	   if(c != null) {
//		    	      if(c.getCellType() == Cell.CELL_TYPE_NUMERIC) {
//		    	         values.add(c.getNumericCellValue());
//		    	      } else if(c.getCellType() == Cell.CELL_TYPE_FORMULA && c.getCachedFormulaResultType() == Cell.CELL_TYPE_NUMERIC) {
//		    	         values.add(c.getNumericCellValue());
//		    	      }
//		    	   }
//		    	}
//		   
//		    
//		} catch(Exception ioe) {
//		    ioe.printStackTrace();
//		}
//		return values;
//	}
	
}
