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
		this.pickupWalnuts(null, null);
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
	
	
	private void pickupWalnuts(ArrayList<Node> nodes, Node currentNode) {
		Node previousNode = currentNode;
		ArrayList<Node> allNodes = nodes;
		if(this.outputs.size() > this.MAX_WALNUTS-1) {
			return;
		}
		if(currentNode == null) {
			ArrayList<Node> childrens = this.getNodesFromLevel(1);
			allNodes = this.getChildrenNodesFromParent(childrens.get(0).getNodeName());
			for(int i = 0; i < allNodes.size(); i++) {
				for(int j = 0; j < this.MAX_WALNUTS_IN_HOLE; j++) {
					if(this.outputs.size() < this.MAX_WALNUTS) {
						this.NO_OUTPUT++;
						this.outputs.add(this.NO_OUTPUT + allNodes.get(i).getLabelToRoot());
						allNodes.get(i).setNoOfWalnuts(allNodes.get(i).getNoOfWalnuts() - 1);
					}
				}
			}
			this.pickupWalnuts(allNodes, allNodes.get(0));
		}
		else {
			ArrayList<Node> childrens = this.getChildrenNodesFromParent(currentNode.getNodeName());
			for(int i = 0; i < childrens.size(); i++) {
				ArrayList<Node> hasChild = this.getChildrenNodesFromParent(childrens.get(i).getNodeName());
				if(hasChild.size() == 0) {
					for(int j = 0; j < this.MAX_WALNUTS_IN_HOLE; j++) {
						if(this.outputs.size() < this.MAX_WALNUTS) {
							this.NO_OUTPUT++;
							this.outputs.add(this.NO_OUTPUT + childrens.get(i).getLabelToRoot());
							childrens.get(i).setNoOfWalnuts(childrens.get(i).getNoOfWalnuts() - 1);
						}
					}
				}
				else {
					if(i == 0) {
						for(int j = 0; j < this.MAX_WALNUTS_IN_HOLE; j++) {
							if(this.outputs.size() < this.MAX_WALNUTS) {
								this.NO_OUTPUT++;
								this.outputs.add(this.NO_OUTPUT + childrens.get(i).getLabelToRoot());
								childrens.get(i).setNoOfWalnuts(childrens.get(i).getNoOfWalnuts() - 1);
							}
						}
					}
				}
			}
			ArrayList<Node> temp = new ArrayList<>();
			for(int i = 0; i < allNodes.size(); i++) {
				if(!previousNode.getNodeName().equals(allNodes.get(i).getNodeName())){
					temp.add(allNodes.get(i));
				}
				else {
					temp.add(previousNode);
					for(int j = 0; j < childrens.size(); j++) {
						temp.add(childrens.get(j));
						if(j == 0) {
							currentNode = childrens.get(j);
						}
					}
					if(childrens.size() == 0) {
						currentNode = allNodes.get(i+1);
					}
				}
			}
			this.pickupWalnuts(temp, currentNode);
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
	
	private ArrayList<Node> getChildrenNodesFromParent(String name){
		ArrayList<Node> result = new ArrayList<>();
		for(Node node : this.listNode) {
			if(!node.isRoot() && node.getParent().getNodeName().equals(name)) {
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
