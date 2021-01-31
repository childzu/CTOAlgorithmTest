package problem.one;

import java.util.ArrayList;

public class Route {
	private Direction direction;
	private ArrayList<DataPoint> routePoint;
	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public ArrayList<DataPoint> getRoutePoint() {
		return routePoint;
	}
	public void setRoutePoint(ArrayList<DataPoint> routePoint) {
		this.routePoint = routePoint;
	}
	
}
