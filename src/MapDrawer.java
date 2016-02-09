import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JFrame;


public class MapDrawer extends JComponent {
	
	private HashMap<String, PointofInterest> map;
	private static final double CENTER_LAT = 39.0000;
	private static final double CENTER_LONG = 105.5000;
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 1000;
	
	public MapDrawer(HashMap<String, PointofInterest> map) {
		this.map = map;
		JFrame f = new JFrame();
		f.setSize(WIDTH, HEIGHT);
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
			Point2D.Double point = new Point2D.Double(longit - CENTER_LONG, lat - CENTER_LAT);
			Rectangle2D.Double rect = new Rectangle2D.Double(point.x, point.y, 3, 3);
			g.setColor(Color.BLACK);
			g.fill(rect);
		}
	}
}
