import java.util.ArrayList;


public class PointofInterest {
	String name;
	String type;
	double straightLineDist;
	double rating;
	double lat;
	double longit;
	ArrayList<PointofInterest> neighbors;	
	
	public PointofInterest(String name, String type, double latitude, double longitude, double rating){
		this.name = name;
		this.type = type;
		this.straightLineDist = 0.0;
		this.rating = rating;
		this.lat = latitude;
		this.longit = longitude;
		this.neighbors = new ArrayList<PointofInterest>();	
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getType(){
		return this.type;
	}
	
	public double getSLD(){
		return this.straightLineDist;
	}
	
	public double getRating(){
		return this.rating;
	}
	
	public double getLatitude(){
		return this.lat;
	}
	
	public double getLongitude(){
		return this.longit;
	}
	public ArrayList<PointofInterest> getNeighbors(){
		return this.neighbors;
	}
	
	public String bs() {
		return this.name + " " + this.type + " " + this.lat + " " + this.longit + " " + this.rating;
	}
}
