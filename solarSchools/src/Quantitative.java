import java.util.LinkedList;


public class Quantitative {
	public Quantitative(){
		
	}
	
	public double getMin(LinkedList<Double> column){
		
		double min = column.get(0);
		for(int i=1;i<column.size();i++){
			if(min>column.get(i)){
				min = column.get(i);
			}
		}
		return min;
		
	}
	public double getMax(LinkedList<Double> column){
		
		double max = column.get(0);
		for(int i=1;i<column.size();i++){
			if(max<column.get(i)){
				max = column.get(i);
			}
		}
		return max;
		
	}
	public double getMidpoint(LinkedList<Double> column){
		return (getMax(column) + getMin(column)) / 2;
	}
}
