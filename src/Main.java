public class Main {

	public static void main(String[] args) {
		System.out.println("Begin!");
		Map map = new Map();
		GUI gui = new GUI(map);
//		GUITrial guiTrial = new GUITrial(map);
//		guiTrial.setVisible(true);
		
		for(String name : map.getTablePOIs().keySet()) {
			
			System.out.println(map.getTablePOIs().get(name).getName());
			
			for(Map.Connection con : map.getTablePOIs().get(name).neighbors) {
				
				System.out.println(con.toString());
			}
		}
	}
}
