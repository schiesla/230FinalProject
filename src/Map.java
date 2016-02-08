import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class Map {
	private HashMap<String, PointofInterest> tableOfPOIs;

	public Map() {
		this.tableOfPOIs = new HashMap<>();
		this.populateMap();
	}
	
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
			
			if(count == 0) {
				
				name = input.nextLine();
				
			} else if(count == 1) {
				
				type = input.nextLine();
				
			} else if(count == 2) {
				
				lat = input.nextDouble();
				
			} else if(count == 3) {
				
				longit = input.nextDouble();
				
			} else if(count == 4) {
				
				rating = input.nextDouble();
				
				PointofInterest poi = new PointofInterest(name, type, lat, longit, rating);
				this.tableOfPOIs.put(poi.name, poi);
				count = -1;
				input.nextLine();
			}
			count++;
		}

		input.close();
		
		for(String key : this.tableOfPOIs.keySet()) {
		
			System.out.println(this.tableOfPOIs.get(key).toString());
		}
	}
	
}
