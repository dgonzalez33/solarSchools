import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class NumericalPanel extends JPanel{
	JRadioButton layer1;
	JRadioButton layer2;

	public NumericalPanel(String name){
		layer1 = new JRadioButton("Layer 1");
		layer1.setMnemonic(KeyEvent.VK_B);
		layer1.setSelected(true);
		layer2 = new JRadioButton("Layer 2");
		layer2.setMnemonic(KeyEvent.VK_B);
		layer2.setSelected(true);
		add(getPanel(name));
	}
	public JPanel getPanel(String name){
		JPanel panel =  new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel(name));
        panel.add(layer1);
        panel.add(layer2);
        return panel;

	}
	
}
