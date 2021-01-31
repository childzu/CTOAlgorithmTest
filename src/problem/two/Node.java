package problem.two;

public class Node {
	
	private String nodeName;
	private int level;
	private Node parent;
	private boolean isRoot;
	private int noOfWalnuts;
	private String labelToRoot;
	
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public boolean isRoot() {
		return isRoot;
	}
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	public int getNoOfWalnuts() {
		return noOfWalnuts;
	}
	public void setNoOfWalnuts(int noOfWalnuts) {
		this.noOfWalnuts = noOfWalnuts;
	}
	public String getLabelToRoot() {
		return labelToRoot;
	}
	public void setLabelToRoot(String labelToRoot) {
		this.labelToRoot = labelToRoot;
	}
	
}
