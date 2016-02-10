import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class Map {
	private HashMap<String, PointofInterest> tableOfPOIs;
	private TreeSet<PointofInterest> ratings;
	private HashMap<Integer, Connection> connections;

	public Map() {
		this.tableOfPOIs = new HashMap<>();
		this.ratings = new TreeSet<>(new POIComparator());
		this.connections = new HashMap<>();
		this.populateMap();
		this.populateNeighbors();
		
		for(Integer key : this.connections.keySet()) {
			
			System.out.println(this.connections.get(key).toString());
		}
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
				temp.neighbors.add(this.tableOfPOIs.get(name));
				int hashKey = this.hashCode(temp.getName() + name);
						
				if (!(this.connections.containsKey(hashKey))) {

					Connection con = new Connection(temp, this.tableOfPOIs.get(name));

					this.connections.put(hashKey, con);
				}
			}

			input.close();
		}
	}
	
	public int hashCode(String key) {
		
		int val = 0;
		
		for(char c : key.toCharArray()) {
			
			val += c;
		}
		
		val %= key.length();
		
		return val;
		
	}
	public HashMap<String, PointofInterest> getTablePOIs(){
		return this.tableOfPOIs;
	}
}
