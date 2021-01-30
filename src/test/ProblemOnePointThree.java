package test;

import java.util.ArrayList;

public class ProblemOnePointThree {
	
	private final int MAX_ROW=6;
	private final int MAX_COL=7;
	private DataPoint inputs[][];
	private ArrayList<Route> output;
	private String inputFileName= "input1-3.txt";
	private int MAX_ROUTES;
	private int MIN_ROUTES;
	
	public ProblemOnePointThree() {
		this.inputs = new DataPoint[MAX_ROW][MAX_COL];
		this.output = new ArrayList<>();
		this.MIN_ROUTES = 0;
		this.MAX_ROUTES = 0;
		DataReaderFromFile.readDataFromFile(this.inputs, this.inputFileName, MAX_COL);
		DataReaderFromFile.printInput(this.inputs, MAX_ROW);
	}
	
	public void process(int startNumber, int findingNumber) {
		System.out.println("Process start with : " + startNumber + ", " + findingNumber);
		for(int row = 0; row < MAX_ROW; row++) {
			for(int col=0; col < MAX_COL; col++) {
				if(this.inputs[row][col].getNumber() == startNumber) {
					Route route1, route2;
					ArrayList<DataPoint> shortList, longList;
					
					route1 = new Route();
					route1.setDirection(Direction.EAST);
					shortList = this.findRoute(Direction.EAST, startNumber, findingNumber, row, col, true);
					route1.setRoutePoint(shortList);

					route2 = new Route();
					route2.setDirection(Direction.EAST);
					longList = this.findRoute(Direction.EAST, startNumber, findingNumber, row, col, false);
					route2.setRoutePoint(longList);
					
					if(this.checkShortestRoutesEqualLongestRoutes(shortList, longList)) {
						this.output.add(route1);
					}
					else {
						this.output.add(route1);
						this.output.add(route2);
					}
					
					route1 = new Route();
					route1.setDirection(Direction.SOUTH);
					shortList = this.findRoute(Direction.SOUTH, startNumber, findingNumber, row, col, true);
					route1.setRoutePoint(shortList);

					route2 = new Route();
					route2.setDirection(Direction.SOUTH);
					longList = this.findRoute(Direction.SOUTH, startNumber, findingNumber, row, col, false);
					route2.setRoutePoint(longList);

					if(this.checkShortestRoutesEqualLongestRoutes(shortList, longList)) {
						this.output.add(route1);
					}
					else {
						this.output.add(route1);
						this.output.add(route2);
					}
					
					route1 = new Route();
					route1.setDirection(Direction.WEST);
					shortList = this.findRoute(Direction.WEST, startNumber, findingNumber, row, col, true);
					route1.setRoutePoint(shortList);

					route2 = new Route();
					route2.setDirection(Direction.WEST);
					longList = this.findRoute(Direction.WEST, startNumber, findingNumber, row, col, false);
					route2.setRoutePoint(longList);

					if(this.checkShortestRoutesEqualLongestRoutes(shortList, longList)) {
						this.output.add(route1);
					}
					else {
						this.output.add(route1);
						this.output.add(route2);
					}
					
					route1 = new Route();
					route1.setDirection(Direction.NORTH);
					shortList = this.findRoute(Direction.NORTH, startNumber, findingNumber, row, col, true);
					route1.setRoutePoint(shortList);

					route2 = new Route();
					route2.setDirection(Direction.NORTH);
					longList = this.findRoute(Direction.NORTH, startNumber, findingNumber, row, col, false);
					route2.setRoutePoint(longList);

					if(this.checkShortestRoutesEqualLongestRoutes(shortList, longList)) {
						this.output.add(route1);
					}
					else {
						this.output.add(route1);
						this.output.add(route2);
					}
				}
			}
		}
		this.findShortestRoute();
		this.findLongestRoute();
		this.printOutput();
	}
	
	private boolean checkShortestRoutesEqualLongestRoutes(ArrayList<DataPoint> shortList, ArrayList<DataPoint> longList) {
		if( shortList != null && longList !=null && (shortList.size() != longList.size())) {
			return false;
		}
		else if (shortList == null && longList != null){
			return false;
		}
		else if (shortList != null && longList == null){
			return false;
		}
		else if (shortList == null && longList == null){
			return true;
		}
		else {
			boolean isSame = true;
			for(int i = 0; i < shortList.size(); i++) {
				if(shortList.get(i).getNumber() != longList.get(i).getNumber()) {
					isSame = false;
					break;
				}
			}
			return isSame;
		}
	}
	
	private ArrayList<DataPoint> findRoute(Direction direction, int startNumber, int findingNumber, int rowIdx, int colIdx, boolean isShort) {
		ArrayList<DataPoint> routes = new ArrayList<>();
		if(direction.equals(Direction.EAST)) {
			boolean isFound = false;
			int idx=0;
			for(int col=colIdx; col < MAX_COL; col++) {
				if(findingNumber == this.inputs[rowIdx][col].getNumber()) {
					isFound = true;
					idx=col;
					if(isShort) {
						break;
					}
				}
			}
			
			if(isFound) {
				for(int col=colIdx; col <= idx; col++) {
					DataPoint dataPoint = this.inputs[rowIdx][col];
					routes.add(dataPoint);
				}
			} 
			else {
				routes = null;
			} 
		}
		else if(direction.equals(Direction.SOUTH)) {
			boolean isFound = false;
			int idx=0;
			for(int row=rowIdx; row < MAX_ROW; row++) {
				if(findingNumber == this.inputs[row][colIdx].getNumber()) {
					isFound = true;
					idx = row;
					if(isShort) {
						break;
					}
				}
			}
			
			if(isFound){
				for(int row=rowIdx; row <= idx; row++) {
					DataPoint dataPoint = this.inputs[row][colIdx];
					routes.add(dataPoint);
				}
			}
			else {
				routes = null;
			}
		}
		else if(direction.equals(Direction.WEST)) {
			boolean isFound = false;
			int idx=0;
			for(int col=colIdx; col >= 0; col--) {
				if(findingNumber == this.inputs[rowIdx][col].getNumber()) {
					isFound = true;
					idx = col;
					if(isShort) {
						break;
					}
				}
			}
			
			if(isFound) {
				for(int col=colIdx; col >= idx; col--) {
					DataPoint dataPoint = this.inputs[rowIdx][col];
					routes.add(dataPoint);
				}
			}
			else {
				routes = null;
			}
		}
		else {
			boolean isFound = false;
			int idx = 0;
			for(int row=rowIdx; row >= 0; row--) {
				if(findingNumber == this.inputs[row][colIdx].getNumber()) {
					isFound = true;
					idx = row;
					if(isShort) {
						break;
					}
				}
			}
			
			if(isFound){
				for(int row=rowIdx; row >= idx; row--) {
					DataPoint dataPoint = this.inputs[row][colIdx];
					routes.add(dataPoint);
				}
			}
			else {
				routes = null;
			}
		}
		return routes;
	}
	
	private void findShortestRoute() {
		for(Route route: this.output) {
			if( route.getRoutePoint() != null && route.getRoutePoint().size() > 0) {
				this.MIN_ROUTES = route.getRoutePoint().size();
				break;
			}
		}
		for(Route route: this.output) {
			if( route.getRoutePoint() != null && route.getRoutePoint().size() > 0 && route.getRoutePoint().size() < this.MIN_ROUTES) {
				this.MIN_ROUTES = route.getRoutePoint().size();
			}
		}
	}
	
	private void findLongestRoute() {
		for(Route route: this.output) {
			if(route.getRoutePoint() != null && route.getRoutePoint().size() > 0) {
				this.MAX_ROUTES = route.getRoutePoint().size();
				break;
			}
		}
		for(Route route: this.output) {
			if(route.getRoutePoint() != null && route.getRoutePoint().size() > 0 && route.getRoutePoint().size() > this.MAX_ROUTES) {
				this.MAX_ROUTES = route.getRoutePoint().size();
			}
		}
	}
	
	private void printOutput() {
		System.out.println("Output");
		if(this.MIN_ROUTES == 0 && this.MAX_ROUTES == 0) {
			System.out.println("NO ROUTE");
		}
		else {
			for(Route route: this.output) {
				if(route.getRoutePoint() != null) {
					String dataOutput= "";
					dataOutput += route.getDirection().getDirection() + " ";
					for(DataPoint dataPoint : route.getRoutePoint()) {
						dataOutput += dataPoint.getNumber() + ",";
					}
					dataOutput = dataOutput.substring(0, dataOutput.lastIndexOf(","));
					if(route.getRoutePoint().size() == this.MIN_ROUTES) {
						dataOutput += " " + "SHORTEST";
					}
					if (route.getRoutePoint().size() == this.MAX_ROUTES) {
						dataOutput += " " + "LONGEST";
					}
					System.out.println(dataOutput);
				}
			}
		}
		
		System.out.println("Finished problem 1.3");
		System.out.println("====================");
	}

}
