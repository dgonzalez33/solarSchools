import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;


public class HierarchyGraph {
	public HierarchyGraph(String filePath){
		createGraphFile(filePath,"pdf");
	}
	private void createGraphFile(String filePath, String type){
		GraphViz graph = createGraphViz(filePath);
		String fileName = "analysis";
		String repesentationType= "dot";// can be changed to neato, fdp, sfdp, twopi, circo.
		File out = new File(fileName + "." + type);    // change export location here
		graph.writeGraphToFile( graph.getGraph(graph.getDotSource(), type, repesentationType), out );
		try {
			Desktop.getDesktop().open(out);
		} catch (IOException e) {
			System.out.println("Couldnt open file");
			e.printStackTrace();
		}
	}
	private GraphViz createGraphViz(String filePath){
		GraphViz graph = new GraphViz();
		Quantitative analysis = new Quantitative();
		graph.addln(graph.start_unDirectionalGraph());
		LinkedList<Double> costList;
		LinkedList<Double> capList;
		LinkedList<Double> prodList;
		try{
			costList = excelReader(filePath,8);
			capList = excelReader(filePath,9);
			prodList = excelReader(filePath,10);
		}
		catch(IOException e){
			System.out.println(":/");
			graph.addln(graph.end_graph());
			graph.increaseDpi();   // 106 dpi from java api
			return graph;
		}
//		graph.addln("{ rank=same, \"Cost\", \"Capacity\", \"Production\" }");
		System.out.println("here");
		addNodesToGraph(graph,"Cost",Double.toString(analysis.correlation(costList, capList)));
		addNodesToGraph(graph,"Cost",Double.toString(analysis.correlation(costList, prodList)));
		addNodesToGraph(graph,"Capacity",Double.toString(analysis.correlation(costList, capList)));
		addNodesToGraph(graph,"Capacity",Double.toString(analysis.correlation(prodList, capList)));
		addNodesToGraph(graph,"Production",Double.toString(analysis.correlation(costList, prodList)));
		addNodesToGraph(graph,"Production",Double.toString(analysis.correlation(capList, prodList)));
		graph.addln(graph.end_graph());
		System.out.println(graph.getDotSource());//testing purposes
		graph.increaseDpi();   // 106 dpi from java api
		return graph;
	}

	private GraphViz addNodesToGraph(GraphViz graph, String sourceName, String targetName){
		if(targetName!=null){
			graph.addln("\""+sourceName+"\""+"--"+"\""+targetName+"\"");
		}
		else{
			graph.addln("\"''+sourceName+\"");
		}
		return graph;
	}
	  public LinkedList<Double> excelReader(String filePath, int columnNumber) throws IOException{
		  ExcelReader excel = new ExcelReader();
		  return excel.getColumn(filePath, columnNumber);
	  }
	/*public org.w3c.dom.Element getXmlRoot(String filePath){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.out.println("Couldnt start docment builder");
		}
		org.w3c.dom.Document document = null;
		try {
			document = builder.parse(new File(filePath));
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		return document.getDocumentElement();
	}*/


}

