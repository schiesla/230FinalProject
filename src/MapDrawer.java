import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

import javax.swing.JPanel;


public class MapDrawer extends JPanel implements MouseListener {
	
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
	
	private static final double WIDTH = TOP_LEFT_CORNER.x - TOP_RIGHT_CORNER.x;
	private static final double HEIGHT = TOP_LEFT_CORNER.y - BOTTOM_LEFT_CORNER.y;
	private static final Dimension d = new Dimension();
	
	private static final double FRAME_MULTIPLIER = 100;
	private static final double MAP_RADIUS = 10;
	
	private static final double FRAME_WIDTH = WIDTH * FRAME_MULTIPLIER;
	private static final double FRAME_HEIGHT = HEIGHT * FRAME_MULTIPLIER;
	
	private HashMap<Shape, PointofInterest> shapes = new HashMap<Shape, PointofInterest>();
	
//	public static void main(String[] args) {
//		PointofInterest p1 = new PointofInterest("Vali Ski Resort", "Resort", 39.6391, 106.3738, 2.5);
//		PointofInterest p2 = new PointofInterest("Rocky Mountain National Park", "Park", 40.3333, 105.7089, 2.6);
//		PointofInterest p3 = new PointofInterest("Pikes Peak", "Park", 38.8405, 105.0442, 3.5);
//		PointofInterest p4 = new PointofInterest("Mount Evans", "Park", 39.5883, 105.6438, 4.1);
//		PointofInterest p5 = new PointofInterest("Canyons of the Ancients National Park", "Park", 37.3706, 109.0000, 1.9);
//		PointofInterest p6 = new PointofInterest("Copper Mountain", "Park", 39.5017, 106.1564, 3.1);
//		PointofInterest p7 = new PointofInterest("Denver", "City", 39.7392, 104.9903, 4.20);
//		HashMap<String, PointofInterest> map = new HashMap<String, PointofInterest>();
//		map.put("Vali Ski Resort", p1);
//		map.put("Rocky Mountain National Park", p2);
//		map.put("Pikes Peak", p3);
//		map.put("Mount Evans", p4);
//		map.put("Canyons of the Ancients National Park", p5);
//		map.put("Copper Mountain", p6);
//		map.put("Denver", p7);
//		JFrame frame = new JFrame();
//		MapDrawer md = new MapDrawer(frame, map);
//	}
	
	public MapDrawer(HashMap<String, PointofInterest> map) {
//		JButton hi = new JButton("test");
//		this.panel.add(hi);
		this.map = map;
		this.d.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setPreferredSize(this.d);
//		f.setSize(this.d);
//		f.add(this);
		this.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g) {
		System.out.println("its drawing");
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		this.drawMap(g2);
	}
	
	public void drawMap(Graphics2D g) {
		Rectangle2D.Double outline = new Rectangle2D.Double(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		g.draw(outline);
		g.translate(FRAME_WIDTH/2.0, FRAME_HEIGHT/2.0);
		g.rotate(Math.toRadians(180));
		for (String s : this.map.keySet()) {
			String type = this.map.get(s).getType();
			double lat = this.map.get(s).getLatitude();
			double longit = this.map.get(s).getLongitude();
			Point2D.Double point = new Point2D.Double(longit - CENTER_LONG, lat - CENTER_LAT);
			Rectangle2D.Double rect = new Rectangle2D.Double(point.x * FRAME_MULTIPLIER - MAP_RADIUS/2.0, point.y * FRAME_MULTIPLIER - MAP_RADIUS/2.0, MAP_RADIUS, MAP_RADIUS);
			this.shapes.put(rect, this.map.get(s));
			System.out.println(this.map.get(s).getName() + ": " + rect.x + " " + rect.y);
			if (type.equals("City"))
				g.setColor(Color.BLACK);
			if (type.equals("Resort"))
				g.setColor(Color.CYAN);
			if (type.equals("Park"))
				g.setColor(Color.GREEN);
			g.fill(rect);
		}
		g.rotate(-Math.toRadians(180));
		g.translate(-FRAME_WIDTH/2.0, -FRAME_HEIGHT/2.0);
	}
	
//	private Shape nearestPOI(Point2D point) {
//		for(Shape s : this.shapes.keySet()) {
//			double distance = point.distance(s.)
//		}
//	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Should print");
		System.out.println(e.getX() + "");
		System.out.println(e.getY() + "");
		for(Shape s : this.shapes.keySet()) {
			if (s.contains((e.getX() - CENTER_LONG) * FRAME_MULTIPLIER, (e.getY() - CENTER_LAT) * FRAME_MULTIPLIER)) {
				System.out.println(this.shapes.get(s).getName());
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub.
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub.
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub.
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub.
		
	}
}
