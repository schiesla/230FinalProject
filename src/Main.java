public class Main {
/**
 * 
 * Where the program starts, creates a new map and makes the map gui that takes the map.
 *
 * @param args
 */
	public static void main(String[] args) {
		System.out.println("Begin!");
		Map map = new Map();
		MapGUI guiTrial = new MapGUI(map);
		guiTrial.setVisible(true);
	}
}