import java.io.File;
import java.util.HashMap;
import java.util.Scanner;


public class Map {
	private HashMap tableOfPOIs;

	public Map() {
		this.populateMap();
	}
	
	
	
	private void populateMap() {
		
		Scanner input = new Scanner((Readable) new File("text"));
		while (input.hasNextLine()) {
			String row = input.nextLine();
			for (int gridX = 0; gridX < row.length(); gridX++) {
				if (row.charAt(gridX) == 'C') {
					//
				}
				if (row.charAt(gridX) == 'M') {
					//
				}
			}
			// increase y
		}

		input.close();
	}
	
}
