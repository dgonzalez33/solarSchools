import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
                     case Cell.CELL_TYPE_FORMULA:
                    	 double roundUp = cell.getNumericCellValue();
                    	 roundUp = (double) Math.round(roundUp * 100) / 100;
                    	 values.add(roundUp);
                    	 break;
                     default:
                	 }
                 }
             }
         }
         return values;
	}
	
	public String getColumnName(String file, int columnNumber) throws IOException{
		 File myFile = new File(file);
         FileInputStream fis = new FileInputStream(myFile);
         LinkedList<Double> values = new LinkedList<Double>();
         XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
         XSSFSheet mySheet = myWorkBook.getSheetAt(0);
         Iterator<Row> rowIterator = mySheet.iterator();
         Row row = rowIterator.next();
         Iterator<Cell> cellIterator = row.cellIterator();
         while (cellIterator.hasNext()) {
        	 Cell cell = cellIterator.next();
             if(cell.getColumnIndex()==columnNumber){
             	return cell.getStringCellValue();
                	 }
              }
         return "Invalid Column";
	
	}
	public boolean isColNumeric(String file, int columnNumber) throws IOException{
		 File myFile = new File(file);
         FileInputStream fis = new FileInputStream(myFile);
         LinkedList<Double> values = new LinkedList<Double>();
         XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
         XSSFSheet mySheet = myWorkBook.getSheetAt(0);
         Iterator<Row> rowIterator = mySheet.iterator();
         rowIterator.next();
             Row row = rowIterator.next();
             Iterator<Cell> cellIterator = row.cellIterator();
             while (cellIterator.hasNext()) {
                 Cell cell = cellIterator.next();
                 if(cell.getColumnIndex()==columnNumber){
                	 switch (cell.getCellType()) {
                	 case Cell.CELL_TYPE_FORMULA:
                		 return true;
                     case Cell.CELL_TYPE_STRING:
                    	return false;
                     case Cell.CELL_TYPE_NUMERIC:
                    	return true;
                     default:
                	 }
                 }
             }
         return false;
	}
	public int getColCount(String file) throws IOException{
		File myFile = new File(file);
        FileInputStream fis = new FileInputStream(myFile);
        LinkedList<Double> values = new LinkedList<Double>();
        XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        Iterator<Row> rowIterator = mySheet.iterator();
        rowIterator.next();
        int count = 0;
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
            	count++;
                Cell cell = cellIterator.next();
            }
        return count;
	}
}