package test;

public class RunTest {

	public static void main(String[] args) {
		System.out.println("Problem 1.1");
		ProblemOnePointOne probleOne = new ProblemOnePointOne();
		probleOne.printInput();
		probleOne.walking(0, 0);
		System.out.println("Output");
		System.out.println(probleOne.getOutput().substring(0, probleOne.getOutput().lastIndexOf(",")));
		System.out.println("Finished problem 1.1");
	}

}
