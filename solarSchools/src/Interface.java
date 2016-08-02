import java.awt.Dimension;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class Interface extends JFrame implements ActionListener{
	ButtonInterface analysisButs;
	String excelPath;
	public Interface(){
		super("Dashboard");
		addFeatures();
		setSize(850,550);
		setResizable(false);
	    setVisible(true);	
	}
	public void addFeatures(){
		analysisButs = new ButtonInterface();
		add(analysisButs.getPicturePanel());
		analysisButs.getStartAnal().addActionListener(this);
		analysisButs.getReadExcel().addActionListener(this);
	}
	public static void main(String[] args){
		new Interface();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == analysisButs.getReadExcel()) {
			  JFileChooser chooser = new JFileChooser();
			  chooser.setCurrentDirectory(new java.io.File("."));
			  chooser.setDialogTitle("Select Excel File");
			  FileNameExtensionFilter filter = new FileNameExtensionFilter("EXCEL FILES", "xlsx", "excel");
			  chooser.setFileFilter(filter);
		  
			  if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				  System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
				  System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
				  
				  excelPath=chooser.getSelectedFile().getPath();
			  } else {
				  System.out.println("No Selection ");
			  }
		  }
		if(e.getSource()==analysisButs.getStartAnal()){
			if(excelPath==null){
				JOptionPane.showMessageDialog(null, "No excel selected");
				return;
			}
			HierarchyGraph graph = new HierarchyGraph(excelPath);
		}
	}
	
}
