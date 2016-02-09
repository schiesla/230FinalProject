import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;


public class PointofInterest implements TreeNode {
	private String name;
	private String type;
	private double straightLineDist;
	private double rating;
	public double lat;
	public double longit;
	public double radOfEarth;
	public ArrayList<PointofInterest> neighbors;	
	
	public PointofInterest(String name, String type, double latitude, double longitude, double rating){
		this.name = name;
		this.type = type;
		this.straightLineDist = 0.0;
		this.rating = rating;
		this.lat = latitude;
		this.longit = longitude;
		this.radOfEarth = 3959;
		this.neighbors = new ArrayList<>();	
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
	/**
	 * 
	 * takes the lat and lon of two Points of Interest and calculates the distance in miles 
	 * between the two places. Formula aquired from https://en.wikipedia.org/wiki/Haversine_formula
	 *
	 * @param neighbor
	 * @return distance in miles
	 */
	public double DistToNeighbor(PointofInterest neighbor){
		double latTwo = ((neighbor.lat * 3.14)/180.0);
		double latOne = ((this.lat * 3.14)/180.0);
		double longTwo = ((neighbor.longit * 3.14)/180.0);
		double longOne = ((this.longit * 3.14)/180.0);
		double dlong = longTwo - longOne;
		double dlat =  latTwo - latOne;
		double a = Math.pow((Math.sin(dlat/2)), 2) + Math.cos(latOne) * Math.cos(latTwo) * Math.pow(Math.sin(dlong/2), 2);
		double d = 2 * this.radOfEarth * Math.asin(Math.sqrt(a));
		return d;
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
	
	@Override
	public String toString() {
		
		return this.getName() + " " + this.getRating();
	}

	@Override
	public Enumeration children() {
		return null;
	}

	@Override
	public boolean getAllowsChildren() {
		return false;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return null;
	}

	@Override
	public int getChildCount() {
		return 0;
	}

	@Override
	public int getIndex(TreeNode node) {
		return 0;
	}

	@Override
	public TreeNode getParent() {
		return null;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}
}
