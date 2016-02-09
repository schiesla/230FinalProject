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
	
//	private static final double CENTER_LAT = 39.0000;
//	private static final double CENTER_LONG = 105.5000;
	private static final double WIDTH = TOP_LEFT_CORNER.x - BOTTOM_RIGHT_CORNER.x;
	private static final double HEIGHT = TOP_LEFT_CORNER.y - BOTTOM_RIGHT_CORNER.y;
	private static final Dimension d = new Dimension();
	
	public static void main(String[] args) {
		PointofInterest p = new PointofInterest("Vali Ski Resort", "Resort", 106.3738, 39.6391, 2.5);
		HashMap<String, PointofInterest> map = new HashMap<String, PointofInterest>();
		map.put("Vali Ski Resort", p);
		MapDrawer md = new MapDrawer(map);
	}
	
	public MapDrawer(HashMap<String, PointofInterest> map) {
		this.map = map;
		JFrame f = new JFrame();
		this.d.setSize(WIDTH, HEIGHT);
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
		g.translate(WIDTH/2, HEIGHT/2);
		for (String s : this.map.keySet()) {
			double lat = this.map.get(s).getLatitude();
			double longit = this.map.get(s).getLongitude();
			Point2D.Double point = new Point2D.Double(longit - WIDTH/2, lat - HEIGHT/2);
			Rectangle2D.Double rect = new Rectangle2D.Double(point.x, point.y, 3, 3);
			g.setColor(Color.BLACK);
			g.fill(rect);
		}
	}
}
