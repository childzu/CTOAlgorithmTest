package problem.two;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ProblemTwo {
	
	private ArrayList<Node> listNode;
	private int MAX_WALNUTS;
	private int MAX_WALNUTS_IN_HOLE;
	private String rawInput;
	private String inputFileName="input2.txt";
	private ArrayList<String> outputs;
	private int NO_OUTPUT;
	private int MAX_LEVEL;
	
	public ProblemTwo() {
		this.outputs = new ArrayList<>();
		this.listNode = new ArrayList<>();
		this.readDataFromFile(this.inputFileName);
	}
	
	private void readDataFromFile(String fileName){
		System.out.println("Input file");
		try {
			File file = new File(Paths.get(this.getClass().getResource(fileName).toURI()).toString());
			Scanner fileReader = new Scanner(file);
			while (fileReader.hasNextLine()) {
				String data = fileReader.nextLine();
				System.out.println(data);
				if(data.length() > 0) {
					String splitData[] = data.split(",");
					this.MAX_WALNUTS = Integer.parseInt(splitData[0]);
					this.MAX_WALNUTS_IN_HOLE = Integer.parseInt(splitData[1]);
					this.rawInput = splitData[2];
				}
			}
			fileReader.close();
	    } catch (FileNotFoundException | URISyntaxException e) {
	    	System.out.println("An error occurred on read input file.");
	    	e.printStackTrace();
	    }
	}
	
	public void process() {
		this.createListNode();
		this.getMaxLevel();
		this.pickupWalnuts(2);
		this.printOutput();
	}
	
	private void createListNode() {
		boolean isResetRoot=false;
		int idx = 0;
		int nodeLevel = 0;
		int parentNodeLevel = 0;
		String label;
		Node node;
		while(idx < this.rawInput.length()) {
			label = String.valueOf(this.rawInput.charAt(idx));
			if(!label.equals(")")) {
				node = this.createNode(label, nodeLevel , parentNodeLevel);
				this.listNode.add(node);
				if(idx == 0) {
					nodeLevel++;
					parentNodeLevel++;
				}
				if(idx !=0 && !String.valueOf(this.rawInput.charAt(idx-1)).equals(")")) {
					nodeLevel++;
					parentNodeLevel++;
				}
				else {
					if(idx !=0 && String.valueOf(this.rawInput.charAt(idx-1)).equals(")") && isResetRoot) {
						nodeLevel++;
						parentNodeLevel++;
					}
					if(nodeLevel == 1 && parentNodeLevel == 1 && idx != 0) {
						isResetRoot = true;
						nodeLevel = 2;
						parentNodeLevel = 2;
					}
				}
			}
			else {
				nodeLevel--;
				parentNodeLevel--;
			}
			idx++;
		}
	}
	
	private Node createNode(String label, int nodeLevel, int parentLevel) {
		Node node = new Node();
		node.setNodeName(label);
		node.setLevel(nodeLevel+1);
		node.setNoOfWalnuts(this.MAX_WALNUTS_IN_HOLE);
		Node parentNode = this.getParentNode(parentLevel);
		if(parentNode == null) {
			node.setRoot(true);
			node.setLabelToRoot(label);
		}
		else {
			node.setLabelToRoot(parentNode.getLabelToRoot() + label);
		}
		node.setParent(parentNode);
		return node;
	}
	
	private Node getParentNode(int level) {
		if(level == 0)
			return null;
		Node parentNode = null;
		for(Node node: this.listNode) {
			if(node.getLevel() == level) {
				parentNode = node;
			}
		}
		return parentNode;
	}
	
	private void pickupWalnuts(int startLevel) {
		this.NO_OUTPUT = 1;
		for(int level = startLevel; level <= this.MAX_LEVEL; level++) {
			ArrayList<Node> nodes = this.getNodesFromLevel(level);
			for(Node node: nodes) {
				for(int i=1; i <= this.MAX_WALNUTS_IN_HOLE; i++) {
					if(this.outputs.size() < this.MAX_WALNUTS) {
						this.outputs.add(this.NO_OUTPUT + node.getLabelToRoot());
						node.setNoOfWalnuts(node.getNoOfWalnuts() - 1);
						this.NO_OUTPUT++;
					}
				}
			}
		}
	}
	
	private ArrayList<Node> getNodesFromLevel(int level){
		ArrayList<Node> result = new ArrayList<>();
		for(Node node : this.listNode) {
			if(node.getLevel() == level) {
				result.add(node);
			}
		}
		return result;
	}
	
	private void getMaxLevel() {
		for(Node node : this.listNode) {
			if(node.getLevel() > this.MAX_LEVEL) {
				this.MAX_LEVEL = node.getLevel();
			}
		}
	}
	
	private void printOutput() {
		String output="";
		for(String data: this.outputs) {
			output += data + " ";
		}
		System.out.println("Output");
		System.out.println(output);
		System.out.println("Finished problem 2");
		System.out.println("====================");
	}


}
