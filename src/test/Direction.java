package test;

public enum Direction {
	
	EAST ("E"),
	SOUTH ("S"),
	WEST ("W"),
	NORTH ("N");
	
	private final String direction;
	
	Direction(String direction) {
		this.direction = direction;
	}

	public String getDirection() {
		return direction;
	}
	
}
