import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;


public class StatsWork {
	
	public StatsWork(){
		
	}
	public double correlation(LinkedList<Double> xs, LinkedList<Double> ys) {

	    double sx = 0.0;
	    double sy = 0.0;
	    double sxx = 0.0;
	    double syy = 0.0;
	    double sxy = 0.0;

	    int n = Math.min(xs.size(),ys.size());

	    for(int i = 0; i < n; ++i) {
	      double x = xs.get(i);
	      double y = ys.get(i);

	      sx += x;
	      sy += y;
	      sxx += x * x;
	      syy += y * y;
	      sxy += x * y;
	    }

	    // covariation
	    double cov = sxy / n - sx * sy / n / n;
	    // standard error of x
	    double sigmax = Math.sqrt(sxx / n -  sx * sx / n / n);
	    // standard error of y
	    double sigmay = Math.sqrt(syy / n -  sy * sy / n / n);

	    // correlation is just a normalized covariation
	    return round(cov / sigmax / sigmay, 4);
	  }
	
	public double stdDev(LinkedList<Double> column){
		double mean = getMean(column);
		int count = column.size();
		double numerator = 0;
		for(int i=0; i<count;i++){
			numerator += Math.pow(column.get(i)-mean,2);
		}
		double variation = numerator/ (count-1);
		return Math.sqrt(variation);
	}
	public double getMean(LinkedList<Double> column){
		int count=column.size();
		double total=0;
		for(int i=0; i<count;i++){
			total += column.get(i);
		}
		return ( total / count );
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
}
