import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class MapDrawer extends JPanel implements MouseListener {
	
	private HashMap<String, Map.PointofInterest> map;
	
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
	private static final double MAP_RADIUS = FRAME_MULTIPLIER / 15;
	
	private static final double FRAME_WIDTH = WIDTH * FRAME_MULTIPLIER;
	private static final double FRAME_HEIGHT = HEIGHT * FRAME_MULTIPLIER;
	
	private JTextField to;
	private JTextField from;
	
	private HashMap<Shape, Map.PointofInterest> shapes = new HashMap<Shape, Map.PointofInterest>();
	private HashMap<Color, Map.Connection> connections = new HashMap<>();
		
	public MapDrawer(HashMap<String, Map.PointofInterest> map, JTextField to, JTextField from) {

		this.map = map;
//		this.d.setSize(FRAME_WIDTH, FRAME_HEIGHT);
//		setPreferredSize(this.d);
		this.to = to;
		this.from = from;
		
		this.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g) {
		System.out.println("its drawing");
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		this.drawMap(g2);
	}
	
	public void drawMap(Graphics2D g) {
//		Rectangle2D.Double outline = new Rectangle2D.Double(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
//		g.draw(outline);
		g.translate(FRAME_WIDTH/2.0, FRAME_HEIGHT/2.0);
		for (String s : this.map.keySet()) {
			String type = this.map.get(s).getType();
			double lat = this.map.get(s).getLatitude();
			double longit = this.map.get(s).getLongitude();
			Point2D.Double point = new Point2D.Double(longit - CENTER_LONG, lat - CENTER_LAT);
			Rectangle2D.Double rect = new Rectangle2D.Double(point.x * -FRAME_MULTIPLIER - MAP_RADIUS/2.0, point.y * -FRAME_MULTIPLIER - MAP_RADIUS/2.0, MAP_RADIUS, MAP_RADIUS);
			this.shapes.put(rect, this.map.get(s));
//			System.out.println(this.map.get(s).getName() + ": " + rect.x + " " + rect.y);
			if (type.equals("City"))
				g.setColor(Color.BLACK);
			if (type.equals("Resort"))
				g.setColor(Color.CYAN);
			if (type.equals("Park"))
				g.setColor(Color.GREEN);
			g.fill(rect);
			float labelX = (float)(point.x * -FRAME_MULTIPLIER + MAP_RADIUS/2.0);
			float labelY = (float)(point.y * -FRAME_MULTIPLIER + MAP_RADIUS);
			g.setColor(Color.BLACK);
			g.drawString(this.map.get(s).getName(), labelX, labelY);
			
			for(Map.Connection conn : this.map.get(s).neighbors) {
				
				double latLine = conn.getOtherPoint().getLatitude();
				double longitLine = conn.getOtherPoint().getLongitude();
				Point2D.Double pointLine = new Point2D.Double(longitLine - CENTER_LONG, latLine - CENTER_LAT);
				Line2D.Double path = new Line2D.Double(point.getX() * -FRAME_MULTIPLIER, point.getY() * -FRAME_MULTIPLIER, pointLine.getX() * -FRAME_MULTIPLIER, pointLine.getY() * -FRAME_MULTIPLIER);
				g.draw(path);
			}
		}
		
		g.translate(-FRAME_WIDTH/2.0, -FRAME_HEIGHT/2.0);
	}
	
//	private Shape nearestPOI(Point2D point) {
//		for(Shape s : this.shapes.keySet()) {
//			double distance = point.distance(s.)
//		}
//	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println("Should print");
		double mouseX = e.getX() - FRAME_WIDTH/2.0 - MAP_RADIUS/2.0;
		double mouseY = e.getY() - FRAME_HEIGHT/2.0 - MAP_RADIUS/2.0;
		for(Shape s : this.shapes.keySet()) {
			double exs = Math.pow(mouseX - s.getBounds2D().getX(), 2);
			double whys = Math.pow(mouseY - s.getBounds2D().getY(), 2);
			double distForm = Math.sqrt(exs + whys);
			if (distForm < MAP_RADIUS/2.0) {
				System.out.println(this.shapes.get(s).getName());
				if (this.to.hasFocus()) {
					this.to.setText(this.shapes.get(s).getName());
					this.from.requestFocus();
				}
				if (this.from.hasFocus())
					this.from.setText(this.shapes.get(s).getName());
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
