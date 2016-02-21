import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class Map {

	private HashMap<String, Map.PointofInterest> tableOfPOIs;
	private RedBlackTree ratings;
	public double navigationDist = 0;

	public Map() {

		this.tableOfPOIs = new HashMap<>();
		this.ratings = new RedBlackTree<>();
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
	 * Assigns neighbors to all POIs from the neighbor text files
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

		if (!this.tableOfPOIs.containsKey(name1) && !this.tableOfPOIs.containsKey(name2))
			return false;

		this.tableOfPOIs.get(name1).addConnection(this.tableOfPOIs.get(name2), distance);
		return true;
	}

	public LinkedList<PointofInterest> navigate(String to, String from) {
		LinkedList<PointofInterest> path = new LinkedList<PointofInterest>();
		PriorityQueue<PointofInterest> shortestDist = new PriorityQueue<PointofInterest>(
				new Comparator<PointofInterest>() {

					public int compare(PointofInterest pointOne, PointofInterest pointTwo) {
						if (pointOne.distToTravel > pointTwo.distToTravel)
							return 1;
						if (pointOne.distToTravel < pointTwo.distToTravel)
							return -1;
						return 0;
					}
				});

		shortestDist.add(this.tableOfPOIs.get(from));
		int count = 1;
		while (true) {

			if (shortestDist.size() != 0) {

				PointofInterest location = shortestDist.poll();

				if (location.getName().equals(to)) {
					path.add(location);
					break;
				}
				for (int i = 0; i < location.neighbors.size(); i++) {
					location.neighbors.get(i).getOtherPoint().distToTravel = location.neighbors.get(i).distance
							+ location.neighbors.get(i).getOtherPoint().straightLineDist;

					if (!shortestDist.contains(location) && !path.contains(location)) {

						shortestDist.add(location.neighbors.get(i).getOtherPoint());
					}
				}

				// figure out a check on the addition to the linked list.
				path.add(location);
				count++;
			}
		}

		for (int i = path.size() - 1; i > 1; i--) {

			if (!(path.get(i - 1).isNeighbor(path.get(i)))) {

				path.remove(i - 1);
			}
		}

		for (int i = path.size() - 1; i > 0; i--) {

			this.navigationDist += path.get(i - 1).DistToNeighbor(path.get(i));
		}

		System.out.println(path.toString());
		return path;
	}

	public ArrayList<PointofInterest> searchByRating(double rating) {

		ArrayList<PointofInterest> results = this.ratings.toArrayList();
		ArrayList<PointofInterest> returnList = new ArrayList<PointofInterest>();

		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getRating() >= rating) {
				returnList.add(results.get(i));
			}
		}

		return returnList;
	}

	public HashMap<String, LinkedList<Map.PointofInterest>> planTrip(double dist, String starting) {

		HashMap<String, LinkedList<Map.PointofInterest>> paths = new HashMap<>();
		Random rand = new Random();

		for (int i = 0; i < 10; i++) {

			int random = rand.nextInt(this.getTablePOIs().size() - 1);
			int count = -1;
			String name = starting;

			for (String key : this.getTablePOIs().keySet()) {

				name = key;

				if (count > random) {

					if (!name.equals(starting)) {

						break;

					}
				}

				count++;
			}

			LinkedList<Map.PointofInterest> tempPath = this.navigate(name, starting);

			if (Math.abs(this.navigationDist - dist) <= 100) {

				paths.put(String.valueOf(this.navigationDist), tempPath);
			}
			
			this.navigationDist = 0;
		}

		return paths;
	}

	public HashMap<String, Map.PointofInterest> getTablePOIs() {
		return this.tableOfPOIs;
	}
/**
 * 
 * Class that creates points of interest for all the places stored in the text files
 *
 * @author schiesla.
 *         Created Feb 21, 2016.
 */
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

		public PointofInterest(String name, String type, double latitude, double longitude, double rating) {
			this.name = name;
			this.type = type;
			this.straightLineDist = 0.0;
			this.rating = rating;
			this.lat = latitude;
			this.longit = longitude;
			this.radOfEarth = 3959;
			this.neighbors = new ArrayList<>();
		}

		public String getName() {
			return this.name;
		}

		public String getType() {
			return this.type;
		}

		public double getSLD() {
			return this.straightLineDist;
		}

		public double getRating() {
			return this.rating;
		}

		/**
		 * 
		 * Takes the lat and lon of two Points of Interest and calculates the
		 * distance in miles between the two places. Formula aquired from
		 * https://en.wikipedia.org/wiki/Haversine_formula
		 *
		 * @param neighbor
		 * @return distance in miles
		 */
		@SuppressWarnings("boxing")
		public double DistToNeighbor(PointofInterest neighbor) {
			double latTwo = ((neighbor.lat * 3.14) / 180.0);
			double latOne = ((this.lat * 3.14) / 180.0);
			double longTwo = ((neighbor.longit * 3.14) / 180.0);
			double longOne = ((this.longit * 3.14) / 180.0);
			double dlong = longTwo - longOne;
			double dlat = latTwo - latOne;
			double a = Math.pow((Math.sin(dlat / 2)), 2)
					+ Math.cos(latOne) * Math.cos(latTwo) * Math.pow(Math.sin(dlong / 2), 2);
			double d = 2 * this.radOfEarth * Math.asin(Math.sqrt(a));
			DecimalFormat df = new DecimalFormat("#.##");
			d = Double.valueOf(df.format(d));

			return d;
		}

		public void addConnection(PointofInterest point, double distance) {

			this.neighbors.add(new Connection(point, distance));
		}

		public boolean isNeighbor(PointofInterest point) {

			for (Connection con : this.neighbors) {

				if (con.otherPoint.name.equals(point.name)) {

					return true;
				}
			}

			return false;
		}

		public double getLatitude() {
			return this.lat;
		}

		public double getLongitude() {
			return this.longit;
		}

		public ArrayList<Connection> getNeighbors() {
			return this.neighbors;
		}

		@Override
		public String toString() {

			return this.getName();
		}

		public int compareTo(PointofInterest arg) {
			if (this.rating < arg.rating)
				return 1;
			if (this.rating > arg.rating)
				return -1;
			return 0;
		}

	}
/**
 * 
 * Points of interest have a list of connections which are the neighboring cities and the distance
 * to them.
 *
 * @author schiesla.
 *         Created Feb 21, 2016.
 */
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
}
