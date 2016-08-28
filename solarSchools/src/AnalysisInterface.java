import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class AnalysisInterface {
	JPanel number;
	JPanel yearSelect;
	JPanel cost;
	JPanel capacity;
	JPanel production;
	JPanel numPanels;
	JPanel efficiency;
	JPanel analysisInt;
	JButton start;
	public AnalysisInterface(){
		initializeButtons();
		createInterface();
	}
	
	public JPanel getPanel(){
		return analysisInt;
	}
	private void createInterface(){
		JPanel topPan = new JPanel();
		JPanel botPan = new JPanel();
		JPanel vertBox = new JPanel();
		JPanel startPan = new JPanel();
		startPan.add(start);
		vertBox.setLayout(new BoxLayout(vertBox, BoxLayout.Y_AXIS));
		topPan.add(number);
		topPan.add(yearSelect);
		topPan.add(cost);
		topPan.add(capacity);
		botPan.add(production);
		botPan.add(numPanels);
		botPan.add(efficiency);
		vertBox.add(topPan);
		vertBox.add(botPan);
		vertBox.add(startPan);
		analysisInt.add(vertBox);
	}
	private void initializeButtons(){
		number = new NumericalPanel("#");
		yearSelect = new NumericalPanel("Year");
		cost = new NumericalPanel("Cost");
		capacity = new NumericalPanel("Capacity");
		production = new NumericalPanel("Production");
		numPanels = new NumericalPanel("Panels");
		efficiency = new NumericalPanel("Efficiency");
		analysisInt = new JPanel();
		start = new JButton("Start analysis");
	}
	public JButton getStart(){
		return start;
	}
	public int getRadioStatus(JPanel panel){
		return 0;
	}
}
