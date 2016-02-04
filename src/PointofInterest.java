import java.util.ArrayList;


public class PointofInterest {
	String name;
	String type;
	double straightLineDist;
	double rating;
	ArrayList<PointofInterest> neighbors;	
	
	public PointofInterest(String name, String type){
		this.name = name;
		this.type = type;
		this.straightLineDist = 0.0;
		this.rating = 0.0;
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
	
	public ArrayList<PointofInterest> getNeighbors(){
		return this.neighbors;
	}
}
