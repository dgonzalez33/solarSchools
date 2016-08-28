import java.awt.Desktop;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;


public class HierarchyGraph {
	int identifier;
	public HierarchyGraph(String filePath){
		identifier = 0;
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
		StatsWork analysis = new StatsWork();
		graph.addln(graph.start_unDirectionalGraph());
		ExcelReader excel = new ExcelReader();
		try{
			graph.addln("splines=line;");
			for(int i = 0; i < excel.getColCount(filePath); i++){
				System.out.println(excel.getColumnName(filePath, i));
				if(excel.isColNumeric(filePath, i)){
					LinkedList<Double> colList = excel.getColumn(filePath,i);
					double[] colArray = listToArray(colList);
					colArray = Arrays.stream(colArray).distinct().toArray();
					Arrays.sort(colArray);
					String colName = excel.getColumnName(filePath , i);
					graph.addln("subgraph \""+colName+"\"{ ");
					firstLayer(graph, colName, colArray);
					secondLayer(graph, colArray);
					graph.addln("}");
					}
				identifier++;
			}
		}
		catch(IOException e){
			System.out.println(":/");
			graph.addln(graph.end_graph());
			graph.increaseDpi();   // 106 dpi from java api
			return graph;
		}
//		graph.addln("{ rank=same, \"Cost\", \"Capacity\", \"Production\" }");
		
		graph.addln(graph.end_graph());
		//System.out.println(graph.getDotSource());//testing purposes
		graph.increaseDpi();   // 106 dpi from java api
		return graph;
	}
	private void firstLayer(GraphViz graph, String source, double[] column){
		addNodesToGraph(graph, source, Double.toString(column[0]) );
		double mid = ( column[0] + column[column.length-1] ) / 2;
		addNodesToGraph(graph, source, Double.toString(mid) );
		addNodesToGraph(graph, source, Double.toString(column[column.length-1]) );

	}
	
	private void secondLayer(GraphViz graph, double[] column){
		
		for(int i = 1; i<4 && i<column.length; i++){
			addNodesToGraph(graph, Double.toString(column[0]), Double.toString(column[i]) );
			double mid = ( column[0] + column[column.length-1] ) / 2;
			addNodesToGraph(graph, Double.toString(mid),Double.toString(column[((column.length-1) / 2) + i]));
			addNodesToGraph(graph, Double.toString(column[column.length-1]), Double.toString(column[column.length-5+i]) );

		}
	}
	private void addNodesToGraph(GraphViz graph, String sourceName, String targetName){
		if(targetName!=null){
			graph.addln("\""+sourceName+""+identifier+"\""+"--"+"\""+targetName+""+identifier+"\"");
			graph.addln("\""+targetName+""+identifier+"\""+"[label=\""+targetName+"\"]");
			graph.addln("\""+sourceName+""+identifier+"\""+"[label=\""+sourceName+"\"]");
		}
		else{
			graph.addln("\""+sourceName+""+identifier+"\"");
			graph.addln("\""+sourceName+""+identifier+"\""+"[label=\""+sourceName+"\"]");
		}
	}
	private double[] listToArray(LinkedList<Double> column){
		double[] array = new double[column.size()];
		for(int i = 0; i<array.length;i++){
			array[i] = column.get(i);
		}
		return array;
	}
}

