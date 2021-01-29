package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ProblemOnePointTwo {
	
	private DataOnePointTwo inputs[][];
	private ArrayList<DataOnePointTwo> output;
	private String inputFileName= "input1-2.txt";
	
	public ProblemOnePointTwo() {
		this.inputs = new DataOnePointTwo[6][7];
		this.output = new ArrayList<>();
		this.readInputFile();
		this.printInput();
	}
	
	private void readInputFile() {
		System.out.println("Input file");
		try {
			File file = new File(Paths.get(this.getClass().getResource(inputFileName).toURI()).toString());
			Scanner fileReader = new Scanner(file);
			while (fileReader.hasNextLine()) {
				String data = fileReader.nextLine();
				System.out.println(data);
				String splitData[] = data.split("\\],\\[");
				int i= 0;
				for (String dataSlit : splitData) {
					String row = dataSlit.replace("[", "").replace("]", "");
					int j = 0;
					for (String col : row.split(",")) {
						DataOnePointTwo dataPoint = new DataOnePointTwo();
						dataPoint.setNumber(Integer.parseInt(col));
						dataPoint.setWalking(false);
						this.inputs[i][j] = dataPoint;
						j++;
					}
					i++;
				}
			}
			fileReader.close();
	    } catch (FileNotFoundException | URISyntaxException e) {
	    	System.out.println("An error occurred on read input file.");
	    	e.printStackTrace();
	    }
	}
	
	private void printInput() {
		System.out.println("Input data");
		for(int i = 0; i < this.inputs.length; i++) {
			for(int j=0; j < this.inputs[i].length; j++) {
				if(j == 0) {
					System.out.print("[ " + this.inputs[i][j].getNumber());
				} else if (j == 6) {
					System.out.print(", " + this.inputs[i][j].getNumber() + " ]");
				}
				else {
					System.out.print(", "+ this.inputs[i][j].getNumber());
				}
			}
			System.out.println();
		}
	}
	
	public void process(int startRow, int startCol) {
		this.setGroupNodeNotProcess(startRow-1, startCol-1);
		this.walking(startRow-1, startCol-1, "R");
		this.printOutput();
	}
	
	private void setGroupNodeNotProcess(int row, int col) {
		System.out.println("Set group nodes not process");
		for(int i = 0; i <= row; i++) {
			for(int j = 0; j < 7; j++) {
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
	
	private int getMinMax(String direction, int rowCol) {
		int minMax = 0;
		if(direction.equals("R")) {
			for(int col= 0; col<7; col++) {
				if(!this.inputs[rowCol][col].isWalking()) {
					minMax = col;
				}
			}
		} else if(direction.equals("B")) {
			for(int row= 0; row<6; row++) {
				if(!this.inputs[row][rowCol].isWalking()) {
					minMax = row;
				}
			}
		} else if(direction.equals("L")) {
			for(int col= 0; col < 6; col++) {
				if(!this.inputs[rowCol][col].isWalking()) {
					minMax = col;
					break;
				}
			}
		} else { // direct = T
			for(int row= 0; row < 6; row++) {
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
	
	private void walking(int currentRow, int currentCol, String direction) {
		int row = currentRow;
		int col = currentCol;
		if(this.getAllNodeIsWalk()) {
			return;
		}
		if(direction.equals("R")) {
			int maxCol = this.getMinMax("R", row);
			while(col <= maxCol) {
				//System.out.print(this.inputs[row][col].getNumber() + ", ");
				this.output.add(this.inputs[row][col]);
				this.inputs[row][col].setWalking(true);
				col++;
			}
			walking(row+1, col-1, "B");
		} else if(direction.equals("B")) {
			int maxRow = this.getMinMax("B", col);
			while(row <= maxRow) {
				//System.out.print(this.inputs[row][col].getNumber() + ", ");
				this.output.add(this.inputs[row][col]);
				this.inputs[row][col].setWalking(true);
				row++;
			}
			walking(row-1, col-1, "L");
		} else if(direction.equals("L")) {
			int minCol = this.getMinMax("L", row);
			while(col >= minCol) {
				//System.out.print(this.inputs[row][col].getNumber() + ", ");
				this.output.add(this.inputs[row][col]);
				this.inputs[row][col].setWalking(true);
				col--;
			}
			walking(row-1, col+1, "T");
		} else {
			int minRow = this.getMinMax("T", col);
			while(row >= minRow) {
				//System.out.print(this.inputs[row][col].getNumber() + ", ");
				this.output.add(this.inputs[row][col]);
				this.inputs[row][col].setWalking(true);
				row--;
			}
			walking(row+1, col+1, "R");
		}
		
	}
	
	private void printOutput() {
		String output="";
		for(DataOnePointTwo data: this.output) {
			output += data.getNumber() + ",";
		}
		System.out.println("Output");
		System.out.println(output.substring(0, output.lastIndexOf(",")));
		System.out.println("Finished problem 1.2");
		System.out.println("====================");
	}

}
