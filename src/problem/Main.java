package problem;

import problem.one.ProblemOnePointOne;
import problem.one.ProblemOnePointThree;
import problem.one.ProblemOnePointTwo;
import problem.two.ProblemTwo;

public class Main {
	public static void main(String[] args) {
		System.out.println("Problem 1.1");
		ProblemOnePointOne probleOnePointOne = new ProblemOnePointOne();
		probleOnePointOne.process(1, 1);
		
		System.out.println("Problem 1.2");
		ProblemOnePointTwo probleOnePointTwo = new ProblemOnePointTwo();
		probleOnePointTwo.process(2, 2);
		
		System.out.println("Problem 1.3");
		ProblemOnePointThree probleOnePointThree = new ProblemOnePointThree();
		probleOnePointThree.process(2, 8);
		
		System.out.println("Problem 2");
		ProblemTwo problemTwo = new ProblemTwo();
		problemTwo.process();
	}
}
