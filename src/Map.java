import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Map {
	
	private HashMap<String, Map.PointofInterest> tableOfPOIs;
//	private TreeSet<Map.PointofInterest> ratings;
	private RedBlackTree ratings;
	public double navigationDist = 0;
//	private HashMap<Integer, Connection> connections;

	public Map() {
		
		this.tableOfPOIs = new HashMap<>();
		this.ratings = new RedBlackTree<>();
//		this.ratings = new TreeSet<>(new POIComparator());
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
				this.ratings.insert(poi);
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
	private void populateNeighbors() {

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
	
	private boolean addConnection(String name1, String name2, double distance) {
		
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
	
	public LinkedList<PointofInterest> navigate(String to, String from){
		LinkedList<PointofInterest> path = new LinkedList<PointofInterest>();
		PriorityQueue<PointofInterest> shortestDist = new PriorityQueue<PointofInterest>(new Comparator<PointofInterest>() {
			
			public int compare(PointofInterest pointOne, PointofInterest pointTwo) {
				if(pointOne.distToTravel > pointTwo.distToTravel) return 1;
				if(pointOne.distToTravel < pointTwo.distToTravel) return -1;
				return 0;
			}	
		});
		
		shortestDist.add(this.tableOfPOIs.get(from));
		int count = 1;
		while(true){
			PointofInterest location = shortestDist.poll();
			
			if(location.getName().equals(to)){
				path.add(location);
				break;
			}
			for(int i = 0; i < location.neighbors.size(); i++){
				location.neighbors.get(i).getOtherPoint().distToTravel = location.neighbors.get(i).distance + location.neighbors.get(i).getOtherPoint().straightLineDist; 
				
				if(!shortestDist.contains(location) && !path.contains(location)) {
					
					shortestDist.add(location.neighbors.get(i).getOtherPoint());
				}
			}
			
			//figure out a check on the addition to the linked list.
			path.add(location);
			count++;
		}
		
		for(int i = path.size() - 1; i > 1; i--) {
			
			if(!(path.get(i - 1).isNeighbor(path.get(i)))) {
				
				path.remove(i - 1);
			}
		}
		
		for(int i = path.size() - 1; i > 0; i--) {
			
			this.navigationDist += path.get(i - 1).DistToNeighbor(path.get(i));
		}
		
		System.out.println(path.toString());
		return path;
	}
	
	public ArrayList<PointofInterest> searchByRating(double rating) {
		
		ArrayList<PointofInterest>  results = this.ratings.toArrayList();
		ArrayList<PointofInterest>  returnList = new ArrayList<PointofInterest>();
		 
		for(int i = 0; i < results.size(); i++){
			if(results.get(i).getRating() >= rating){
				returnList.add(results.get(i));
			}
		}
		 
		
//		if(this.ratings.find(rating) == null) {
//			
//			this.ratings.add(new PointofInterest("ratingTemp", "kernell", 0, 0, rating));
//		
//		} else {
//		
//			PointofInterest start = this.ratings.find(rating);
//			this.ratings.add(start);
//		}
//			
//		if(start.rightChild == null) {
//			
//			return results;
//		}
//		
//		start = start.rightChild;
//
//		while(true) {
//			
//			if(start.rating >= rating) {
//				
//				ressults.add(start);
//				
//				if(start.rightChild == null) {
//					
//					break;
//				}
//				
//				start = start.rightChild;
//			}
//		}
		
		return returnList;
	}
	
//	public static void main(String[] args) {
//		
//		Map m = new Map();
//		PointofInterest valiSki = m.tableOfPOIs.get("Vali Ski Resort");
//		PointofInterest aspen = m.tableOfPOIs.get("Aspen");
//		PointofInterest boulder = m.tableOfPOIs.get("Boulder");
//		PointofInterest coloradoSprings = m.tableOfPOIs.get("Colorado Springs");
//		PointofInterest pikesPeak = m.tableOfPOIs.get("Pikes Peak");
//		PointofInterest cheyenneMountain = m.tableOfPOIs.get("Cheyenne Mountain Resort");
//		
//		valiSki.addConnection(aspen, valiSki.DistToNeighbor(aspen));
//		valiSki.addConnection(boulder, valiSki.DistToNeighbor(boulder)); // Vali Ski Resort has two neighbors, Aspen and Boulder
//		aspen.addConnection(valiSki, aspen.DistToNeighbor(valiSki));
//		boulder.addConnection(valiSki, boulder.DistToNeighbor(valiSki));
//		
//		cheyenneMountain.addConnection(aspen, cheyenneMountain.DistToNeighbor(aspen));
//		cheyenneMountain.addConnection(pikesPeak, cheyenneMountain.DistToNeighbor(pikesPeak));
//		cheyenneMountain.addConnection(coloradoSprings, cheyenneMountain.DistToNeighbor(coloradoSprings)); // Chey Montains has three neighbors, Aspen, PP, and CS
//		aspen.addConnection(cheyenneMountain, aspen.DistToNeighbor(cheyenneMountain));
//		pikesPeak.addConnection(cheyenneMountain, pikesPeak.DistToNeighbor(cheyenneMountain));
//		coloradoSprings.addConnection(cheyenneMountain, coloradoSprings.DistToNeighbor(cheyenneMountain));
//
//		boulder.addConnection(coloradoSprings, boulder.DistToNeighbor(coloradoSprings)); // Boulder and CS are neighbors
//		coloradoSprings.addConnection(boulder, coloradoSprings.DistToNeighbor(boulder));
//
//		LinkedList<PointofInterest> path = m.navigate("Boulder", "Vali Ski Resort");
//		System.out.println(path.toString());
//	}
	
	private String getDistanceTime() {
		
		//implement dist/time on top of A*
		return "";
	}
	
	public HashMap<String, Map.PointofInterest> getTablePOIs(){
		return this.tableOfPOIs;
	}
	

	public class PointofInterest implements Comparable<PointofInterest> {
		private String name;
		private String type;
		public double straightLineDist;
		private double distToTravel;
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
		
		public boolean isNeighbor(PointofInterest point) {
			
			for(Connection con : this.neighbors) {
				
				if(con.otherPoint.name.equals(point.name)) {
					
					return true;
				}
			}
			
			return false;
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

		
		public int compareTo(PointofInterest arg) {
			if(this.rating < arg.rating) return 1;
			if(this.rating > arg.rating) return -1;
			return 0;
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

	
//	public class POIComparator  implements Comparator<PointofInterest> {
//
//		public POIComparator(){
//			super();
//		}
//
//		@Override
//		public int compare(PointofInterest poi1, PointofInterest poi2) {
//			
//			if(poi1.getRating() > poi2.getRating()) return 1;
//			else if(poi1.getRating() < poi2.getRating()) return -1;
//			else return( poi1.equals(poi2)? 0 : 1);
//		}
//	}

}

