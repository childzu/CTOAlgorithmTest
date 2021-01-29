package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Scanner;

public class ProblemOnePointOne {
	
	private int inputs[][] = new int[6][7];
	private String output;
	private String inputFileName= "input1-1.txt";
	
	public ProblemOnePointOne() {
		this.output = "";
		this.readInputFile();
	}
	
	public String getOutput() {
		return output;
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
						this.inputs[i][j] = Integer.parseInt(col);
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
	
	public void printInput() {
		System.out.println("Input data");
		for(int i = 0; i < this.inputs.length; i++) {
			for(int j=0; j < this.inputs[i].length; j++) {
				if(j == 0) {
					System.out.print("[ " + this.inputs[i][j]);
				} else if (j == 6) {
					System.out.print(", " + this.inputs[i][j] + " ]");
				}
				else {
					System.out.print(", "+ this.inputs[i][j]);
				}
			}
			System.out.println();
		}
	}
	
	public void walking(int currentRow, int currentCol) {
		int row = currentRow;
		if(currentRow == 5 && currentCol == 7) {
			return;
		}
		if(currentCol%2 == 0) {
			while(row < 6) {
				//System.out.print(this.inputs[row][currentCol] + " ");
				this.output += this.inputs[row][currentCol] + ",";
				row++;
			}
			currentCol++;
			walking(row-1, currentCol);
		}
		else {
			while(row >= 0) {
				//System.out.print(this.inputs[row][currentCol] + " ");
				this.output += this.inputs[row][currentCol] + ",";
				row--;
			}
			currentCol++;
			walking(row+1, currentCol);
		}
		
	}

}
