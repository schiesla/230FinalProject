import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JFrame;


public class MapDrawer extends JComponent {
	
	private HashMap<String, PointofInterest> map;
	
	private static final double TOP_LEFT_CORNER_LAT = 41.0007;
	private static final double TOP_LEFT_CORNER_LONG = 109.0501;
	private static final Point2D.Double TOP_LEFT_CORNER = new Point2D.Double(TOP_LEFT_CORNER_LONG, TOP_LEFT_CORNER_LAT);
	
	private static final double TOP_RIGHT_CORNER_LAT = 41.0024;
	private static final double TOP_RIGHT_CORNER_LONG = 102.0517;
	private static final Point2D.Double TOP_RIGHT_CORNER = new Point2D.Double(TOP_RIGHT_CORNER_LONG, TOP_RIGHT_CORNER_LAT);
	
	private static final double BOTTOM_LEFT_CORNER_LAT = 36.9991;
	private static final double BOTTOM_LEFT_CORNER_LONG = 109.0452;
	private static final Point2D.Double BOTTOM_LEFT_CORNER = new Point2D.Double(BOTTOM_LEFT_CORNER_LONG, BOTTOM_LEFT_CORNER_LAT);
	
	private static final double BOTTOM_RIGHT_CORNER_LAT = 36.9930;
	private static final double BOTTOM_RIGHT_CORNER_LONG = 102.0421;
	private static final Point2D.Double BOTTOM_RIGHT_CORNER = new Point2D.Double(BOTTOM_RIGHT_CORNER_LONG, BOTTOM_RIGHT_CORNER_LAT);
	
	private static final double CENTER_LAT = (TOP_LEFT_CORNER_LAT + BOTTOM_RIGHT_CORNER_LAT)/2.0;
	private static final double CENTER_LONG = (TOP_LEFT_CORNER_LONG + BOTTOM_RIGHT_CORNER_LONG)/2.0;
	
	private static final double WIDTH = TOP_LEFT_CORNER.x - BOTTOM_RIGHT_CORNER.x;
	private static final double HEIGHT = TOP_LEFT_CORNER.y - BOTTOM_RIGHT_CORNER.y;
	private static final Dimension d = new Dimension();
	
	private static final double FRAME_MULTIPLIER = 100;
	private static final double MAP_RADIUS = 10;
	
	private static final double FRAME_WIDTH = WIDTH * FRAME_MULTIPLIER;
	private static final double FRAME_HEIGHT = HEIGHT * FRAME_MULTIPLIER;
	
	public static void main(String[] args) {
		PointofInterest p1 = new PointofInterest("Vali Ski Resort", "Resort", 39.6391, 106.3738, 2.5);
		PointofInterest p2 = new PointofInterest("Rocky Mountain National Park", "Park", 40.3333, 105.7089, 2.6);
		HashMap<String, PointofInterest> map = new HashMap<String, PointofInterest>();
		map.put("Vali Ski Resort", p1);
		map.put("Rocky Mountain National Park", p2);
		JFrame frame = new JFrame();
		MapDrawer md = new MapDrawer(frame, map);
	}
	
	public MapDrawer(JFrame frame, HashMap<String, PointofInterest> map) {
		this.map = map;
		JFrame f = new JFrame();
		this.d.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		f.setSize(this.d);
		f.add(this);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		this.drawMap(g2);
	}
	
	public void drawMap(Graphics2D g) {
		g.translate(FRAME_WIDTH/2.0, FRAME_HEIGHT/2.0);
		for (String s : this.map.keySet()) {
			double lat = this.map.get(s).getLatitude();
			double longit = this.map.get(s).getLongitude();
			Point2D.Double point = new Point2D.Double(longit - CENTER_LONG, lat - CENTER_LAT);
			Rectangle2D.Double rect = new Rectangle2D.Double(point.x * FRAME_MULTIPLIER - MAP_RADIUS/2.0, point.y * FRAME_MULTIPLIER - MAP_RADIUS/2.0, MAP_RADIUS, MAP_RADIUS);
			g.setColor(Color.BLACK);
			g.fill(rect);
		}
	}
}
