package problem.one;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Scanner;

public class DataReaderFromFile {
	
	public static void readDataFromFile(DataPoint[][] inputs, String fileName, int maxCol){
		System.out.println("Input file");
		try {
			File file = new File(Paths.get(DataReaderFromFile.class.getResource(fileName).toURI()).toString());
			Scanner fileReader = new Scanner(file);
			while (fileReader.hasNextLine()) {
				String data = fileReader.nextLine();
				System.out.println(data);
				if(data.length() > 2 && data.substring(0, 2).equals("[[")) {
					data = data.substring(data.indexOf("[")+1, data.lastIndexOf("]"));
					String splitData[] = data.split("\\],\\[");
					int i= 0;
					for (String dataSlit : splitData) {
						String tokenString = dataSlit.replace("[", "").replace("]", "");
						int j = 0;
						String cols[] = tokenString.split(",");
						if(cols.length == maxCol) {
							for (String col : cols) {
								DataPoint dataPoint = new DataPoint();
								dataPoint.setNumber(Integer.parseInt(col));
								dataPoint.setWalking(false);
								inputs[i][j] = dataPoint;
								j++;
							}
						}
						i++;
					}
				}
			}
			fileReader.close();
	    } catch (FileNotFoundException | URISyntaxException e) {
	    	System.out.println("An error occurred on read input file.");
	    	e.printStackTrace();
	    }
		
	}
	
	public static void printInput(DataPoint[][] inputs, int maxRow) {
		System.out.println("Input data");
		for(int i = 0; i < inputs.length; i++) {
			for(int j=0; j < inputs[i].length; j++) {
				if(j == 0) {
					System.out.print("[ " + inputs[i][j].getNumber());
				} else if (j == maxRow) {
					System.out.print(", " + inputs[i][j].getNumber() + " ]");
				}
				else {
					System.out.print(", "+ inputs[i][j].getNumber());
				}
			}
			System.out.println();
		}
	}

}
