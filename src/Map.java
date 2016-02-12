import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.tree.TreeNode;

public class Map {
	
	private HashMap<String, Map.PointofInterest> tableOfPOIs;
	private TreeSet<Map.PointofInterest> ratings;
//	private HashMap<Integer, Connection> connections;

	public Map() {
		
		this.tableOfPOIs = new HashMap<>();
		this.ratings = new TreeSet<>(new POIComparator());
//		this.connections = new HashMap<>();
		this.populateMap();
		this.populateNeighbors();
	}

	/**
	 * 
	 * Reads text file with all POIs data and populates the table with POIs and
	 * the tree that sorts POIs by their rating.
	 *
	 */
	@SuppressWarnings("resource")
	private void populateMap() {
		Scanner input = new Scanner(System.in);
		try {
			input = new Scanner(new File("POIs"));
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		int count = 0;
		String name = null;
		String type = null;
		double lat = 0;
		double longit = 0;
		double rating = 0;

		while (input.hasNextLine()) {

			if (count == 0) {

				name = input.nextLine();

			} else if (count == 1) {

				type = input.nextLine();

			} else if (count == 2) {

				lat = input.nextDouble();

			} else if (count == 3) {

				longit = input.nextDouble();

			} else if (count == 4) {

				rating = input.nextDouble();

				PointofInterest poi = new PointofInterest(name, type, lat, longit, rating);
				
				this.tableOfPOIs.put(poi.getName(), poi);
				this.ratings.add(poi);
				count = -1;
				input.nextLine();
			}
			
			count++;
		}

		input.close();
	}

	/**
	 * 
	 * Assigns neighbors to all POIs
	 *
	 */
	@SuppressWarnings("resource")
	public void populateNeighbors() {

		for (String key : this.tableOfPOIs.keySet()) {

			PointofInterest temp = this.tableOfPOIs.get(key);

			Scanner input = new Scanner(System.in);
			try {
				input = new Scanner(new File(temp.getName() + " neighbors"));
			} catch (FileNotFoundException exception) {
				exception.printStackTrace();
			}

			while (input.hasNextLine()) {

				String name = input.nextLine();
				
				this.addConnection(temp.getName(), name, temp.DistToNeighbor(this.tableOfPOIs.get(name)));
			}

			input.close();
		}
	}
	
	public boolean addConnection(String name1, String name2, double distance) {
		
		if(!this.tableOfPOIs.containsKey(name1) && !this.tableOfPOIs.containsKey(name2)) return false;
		
		this.tableOfPOIs.get(name1).addConnection(this.tableOfPOIs.get(name2), distance);
		return true;
	}
	
//	public int hashCode(String key) {
//		
//		int val = 0;
//		
//		for(char c : key.toCharArray()) {
//			
//			val += c;
//		}
//		
//		val %= key.length();
//		
//		return val;
//		
//	}
	
	public HashMap<String, Map.PointofInterest> getTablePOIs(){
		return this.tableOfPOIs;
	}
	
	public class PointofInterest implements TreeNode {
		private String name;
		private String type;
		private double straightLineDist;
		private double rating;
		public double lat;
		public double longit;
		public double radOfEarth;
		public ArrayList<Connection> neighbors;	
		
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
		 * Takes the lat and lon of two Points of Interest and calculates the distance in miles 
		 * between the two places. Formula aquired from https://en.wikipedia.org/wiki/Haversine_formula
		 *
		 * @param neighbor
		 * @return distance in miles
		 */
		@SuppressWarnings("boxing")
		public double DistToNeighbor(PointofInterest neighbor){
			double latTwo = ((neighbor.lat * 3.14)/180.0);
			double latOne = ((this.lat * 3.14)/180.0);
			double longTwo = ((neighbor.longit * 3.14)/180.0);
			double longOne = ((this.longit * 3.14)/180.0);
			double dlong = longTwo - longOne;
			double dlat =  latTwo - latOne;
			double a = Math.pow((Math.sin(dlat/2)), 2) + Math.cos(latOne) * Math.cos(latTwo) * Math.pow(Math.sin(dlong/2), 2);
			double d = 2 * this.radOfEarth * Math.asin(Math.sqrt(a));
			DecimalFormat df = new DecimalFormat("#.##");
			d = Double.valueOf(df.format(d));
			
			return d;
		}
		
		public void addConnection(PointofInterest point, double distance) {
			
			this.neighbors.add(new Connection(point, distance));
		}
		
		public double getLatitude(){
			return this.lat;
		}
		
		public double getLongitude(){
			return this.longit;
		}
		public ArrayList<Connection> getNeighbors(){
			return this.neighbors;
		}
		
		@Override
		public String toString() {
			
			return this.getName();
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
	
	public class Connection {
		
		private PointofInterest otherPoint;
		private double distance;
		
		public Connection(PointofInterest p1, double d) {
			
			this.otherPoint = p1;
			this.distance = d;
		}
		
		public PointofInterest getOtherPoint() {
			
			return this.otherPoint;
		}
		
		public double getDistance() {
			
			return this.distance;
		}
		
		public String toString() {
			
			return this.otherPoint.getName() + " : " + this.distance;
		}
	}
	
	public class POIComparator  implements Comparator<PointofInterest> {

		public POIComparator(){
			super();
		}

		@Override
		public int compare(PointofInterest poi1, PointofInterest poi2) {
			
			if(poi1.getRating() > poi2.getRating()) return 1;
			else if(poi1.getRating() < poi2.getRating()) return -1;
			else return( poi1.equals(poi2)? 0 : 1);
		}
	}
}
