package problem.one;

public class ProblemOnePointOne {
	private final int MAX_ROW=6;
	private final int MAX_COL=7;
	private DataPoint inputs[][];
	private String output;
	private String inputFileName= "input1-1.txt";
	
	public ProblemOnePointOne() {
		this.inputs = new DataPoint[MAX_ROW][MAX_COL];
		this.output = "";
		DataReaderFromFile.readDataFromFile(this.inputs, this.inputFileName, MAX_COL);
		DataReaderFromFile.printInput(this.inputs, MAX_ROW);
	}
	
	public void process(int startRow, int startCol) {
		System.out.println("Process start with : " + startRow + ", " + startCol);
		this.walking(startRow -1, startCol -1);
		this.printOutput();
	}

	private void walking(int currentRow, int currentCol) {
		int row = currentRow;
		if(currentRow == 5 && currentCol == 7) {
			return;
		}
		if(currentCol%2 == 0) {
			while(row < 6) {
				//System.out.print(this.inputs[row][currentCol] + " ");
				this.output += this.inputs[row][currentCol].getNumber() + ",";
				row++;
			}
			currentCol++;
			walking(row-1, currentCol);
		}
		else {
			while(row >= 0) {
				//System.out.print(this.inputs[row][currentCol] + " ");
				this.output += this.inputs[row][currentCol].getNumber() + ",";
				row--;
			}
			currentCol++;
			walking(row+1, currentCol);
		}
		
	}

	private void printOutput() {
		System.out.println("Output");
		System.out.println(this.output.substring(0, this.output.lastIndexOf(",")));
		System.out.println("Finished problem 1.1");
		System.out.println("====================");
	}

}
