package problem.one;

import java.util.ArrayList;

public class ProblemOnePointTwo {
	private final int MAX_ROW=6;
	private final int MAX_COL=7;
	private DataPoint inputs[][];
	private ArrayList<DataPoint> output;
	private String inputFileName= "input1-2.txt";
	
	public ProblemOnePointTwo() {
		this.inputs = new DataPoint[MAX_ROW][MAX_COL];
		this.output = new ArrayList<>();
		DataReaderFromFile.readDataFromFile(this.inputs, this.inputFileName, MAX_COL);
		DataReaderFromFile.printInput(this.inputs, MAX_ROW);
	}
	
	public void process(int startRow, int startCol) {
		System.out.println("Process start with : " + startRow + ", " + startCol);
		this.setGroupNodeNotProcess(startRow-1, startCol-1);
		if(startRow == MAX_ROW && startCol == MAX_COL) {
			System.out.println("Improper starting row & col");
		}
		else if(startCol == MAX_COL) {
			this.walking(startRow-1, startCol-1, Direction.SOUTH);
		} 
		else if(startRow == MAX_ROW) {
			this.walking(startRow-1, startCol-1, Direction.NORTH);
		} 
		else {
			this.walking(startRow-1, startCol-1, Direction.EAST);
		}
		this.printOutput();
	}
	
	private void setGroupNodeNotProcess(int row, int col) {
		//System.out.println("Set group nodes not process");
		for(int i = 0; i <= row; i++) {
			for(int j = 0; j < MAX_COL; j++) {
				if(i == row && j > col) {
					return;
				}
				else {
					this.inputs[i][j].setWalking(true);
					// System.out.print(this.inputs[i][j].getNumber() + ", ");
				}
			}
		}
	}
	
	private int getMinMax(Direction direction, int rowCol) {
		int minMax = 0;
		if(direction.equals(Direction.EAST)) {
			for(int col= 0; col < MAX_COL; col++) {
				if(!this.inputs[rowCol][col].isWalking()) {
					minMax = col;
				}
			}
		} else if(direction.equals(Direction.SOUTH)) {
			for(int row= 0; row < MAX_ROW; row++) {
				if(!this.inputs[row][rowCol].isWalking()) {
					minMax = row;
				}
			}
		} else if(direction.equals(Direction.WEST)) {
			for(int col= 0; col < MAX_ROW; col++) {
				if(!this.inputs[rowCol][col].isWalking()) {
					minMax = col;
					break;
				}
			}
		} else { // direct = NORTH
			for(int row= 0; row < MAX_ROW; row++) {
				if(!this.inputs[row][rowCol].isWalking()) {
					minMax = row;
					break;
				}
			}
		}
		return minMax;
	}
	
	private boolean getAllNodeIsWalk() {
		for(int i = 0; i < this.inputs.length; i++) {
			for(int j=0; j < this.inputs[i].length; j++) {
				if(! this.inputs[i][j].isWalking()) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void walking(int currentRow, int currentCol, Direction direction) {
		int row = currentRow;
		int col = currentCol;
		if(this.getAllNodeIsWalk()) {
			return;
		}
		if(direction.equals(Direction.EAST)) {
			int maxCol = this.getMinMax(Direction.EAST, row);
			while(col <= maxCol) {
				//System.out.print(this.inputs[row][col].getNumber() + ", ");
				this.output.add(this.inputs[row][col]);
				this.inputs[row][col].setWalking(true);
				col++;
			}
			walking(row+1, col-1, Direction.SOUTH);
		} else if(direction.equals(Direction.SOUTH)) {
			int maxRow = this.getMinMax(Direction.SOUTH, col);
			while(row <= maxRow) {
				//System.out.print(this.inputs[row][col].getNumber() + ", ");
				this.output.add(this.inputs[row][col]);
				this.inputs[row][col].setWalking(true);
				row++;
			}
			walking(row-1, col-1, Direction.WEST);
		} else if(direction.equals(Direction.WEST)) {
			int minCol = this.getMinMax(Direction.WEST, row);
			while(col >= minCol) {
				//System.out.print(this.inputs[row][col].getNumber() + ", ");
				this.output.add(this.inputs[row][col]);
				this.inputs[row][col].setWalking(true);
				col--;
			}
			walking(row-1, col+1, Direction.NORTH);
		} else {
			int minRow = this.getMinMax(Direction.NORTH, col);
			while(row >= minRow) {
				//System.out.print(this.inputs[row][col].getNumber() + ", ");
				this.output.add(this.inputs[row][col]);
				this.inputs[row][col].setWalking(true);
				row--;
			}
			walking(row+1, col+1, Direction.EAST);
		}
		
	}
	
	private void printOutput() {
		String output="";
		for(DataPoint data: this.output) {
			output += data.getNumber() + ",";
		}
		System.out.println("Output");
		System.out.println(output.length()> 0 ? output.substring(0, output.lastIndexOf(",")) : "No data");
		System.out.println("Finished problem 1.2");
		System.out.println("====================");
	}

}

