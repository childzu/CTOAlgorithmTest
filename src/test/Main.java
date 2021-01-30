package test;

public class Main {

	public static void main(String[] args) {
		System.out.println("Problem 1.1");
		ProblemOnePointOne probleOne = new ProblemOnePointOne();
		probleOne.process(1, 1);
		
		System.out.println("Problem 1.2");
		ProblemOnePointTwo probleTwo = new ProblemOnePointTwo();
		probleTwo.process(2, 2);
		
		System.out.println("Problem 1.3");
		ProblemOnePointThree probleThree = new ProblemOnePointThree();
		probleThree.process(5, 1);
	}

}
