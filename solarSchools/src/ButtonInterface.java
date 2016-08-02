import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ButtonInterface {
	JPanel analysisSelection = new JPanel();
	JButton readExcel = new JButton("Select Excel");
	JTextField cost = new JTextField("$999");
	JTextField capacity = new JTextField("100");
	JTextField production = new JTextField("100");
	JButton startAnal = new JButton("Start Analysis");
	JPanel picturePanel;

	public ButtonInterface(){
		initializeButtons();
	}
	public void initializeButtons(){
		analysisSelection = new JPanel();
		readExcel = new JButton("Select Excel");
		cost = new JTextField("$999");
		capacity = new JTextField("100");
		production = new JTextField("100");
		startAnal = new JButton("Start Analysis");
	    BufferedImage Img = null;
		try {
			Img = ImageIO.read(new File("us_map.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
   		JLabel picLabel = new JLabel(new ImageIcon(Img));
   		picturePanel = new JPanel();
		analysisSelection.add(readExcel);
		analysisSelection.add(new JLabel("Minimum cost:"));
		analysisSelection.add(cost);
		analysisSelection.add(new JLabel("Minimum capacity:"));
		analysisSelection.add(capacity);
		analysisSelection.add(new JLabel("Minimum production value:"));
		analysisSelection.add(production);
		analysisSelection.add(startAnal);
		picturePanel.add(analysisSelection);
		picturePanel.add(picLabel);
	}
		public JPanel getPicturePanel(){
			return picturePanel;
		}
		public JButton getStartAnal(){
			return startAnal;
		}
		public JButton getReadExcel(){
			return readExcel;
		}
}
