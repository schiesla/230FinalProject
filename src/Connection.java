
public class Connection {
	
	private final PointofInterest point1;
	private final PointofInterest point2;
	private double distance;
	
	public Connection(PointofInterest p1, PointofInterest p2) {
		
		this.point1 = p1;
		this.point2 = p2;
		this.distance = this.point1.DistToNeighbor(this.point2);
	}
	
	public PointofInterest getP1() {
		
		return this.point1;
	}
	
	public PointofInterest getP2() {
		
		return this.point2;
	}
	
	public double getDistance() {
		
		return this.distance;
	}
	
	@Override
	public String toString() {
		
		return "(" + this.point1 + ":" + this.point2 + " " + this.distance + ")";
	}

}
