import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class Map {
	private HashMap<String, PointofInterest> tableOfPOIs;

	public Map() {
		this.tableOfPOIs = new HashMap<String, PointofInterest>();
		this.populateMap();
	}
	
	
	
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
		Double lat = null;
		Double longit = null;
		Double rating = null;
		
		while (input.hasNextLine()) {
			
			if(count == 0) {
				
				name = input.nextLine();
				System.out.println("Name: " + name + count);
				
			} else if(count == 1) {
				
				type = input.nextLine();
				System.out.println("Type: " + type + count);
				
			} else if(count == 2) {
				
				lat = input.nextDouble();
				System.out.println("Lat: " + lat + count);
				
			} else if(count == 3) {
				
				longit = input.nextDouble();
				System.out.println("Long: " + longit + count);
				
			} else if(count == 4) {
				
				rating = input.nextDouble();
				System.out.println("Rate: " + rating + count);
				
				PointofInterest poi = new PointofInterest(name, type, lat, longit, rating);
				this.tableOfPOIs.put(poi.name, poi);
				System.out.println(this.tableOfPOIs.get(name).bs());
				count = -1;
			}
			count++;
		}

		input.close();
	}
	
}
