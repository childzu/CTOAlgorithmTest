package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Scanner;

public class ProblemOnePointTwo {
	
	private int inputs[][];
	private String output;
	private String inputFileName= "input1-2.txt";
	
	public ProblemOnePointTwo() {
		this.inputs = new int[6][7];
		this.output = "";
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
	
	private void printInput() {
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

}
