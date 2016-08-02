import java.util.LinkedList;


public class backWater {
	private LinkedList<Double> costList;
	private LinkedList<Double> capList;
	private LinkedList<Double> prodList;
	  Quantitative analysis;

	public backWater(String filePath){
		costList = excelReader(filePath,9);
		capList = excelReader(filePath,10);
		prodList = excelReader(filePath,11);
		analysis = new Quantitative();
		
	}
	
	  public LinkedList<Double> excelReader(String filePath, int columnNumber){
		  ExcelReader excel = new ExcelReader();
		  return excel.getColumn(filePath, columnNumber);
	  }
	  
	 
	  
}
