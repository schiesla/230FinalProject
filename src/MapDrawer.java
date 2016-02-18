import java.awt.BasicStroke;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MapDrawer extends JPanel implements MouseListener {

	private HashMap<String, Map.PointofInterest> map;

	private static final double TOP_LEFT_CORNER_LAT = 41.0007;
	private static final double TOP_LEFT_CORNER_LONG = 109.0501;
	private static final Point2D.Double TOP_LEFT_CORNER = new Point2D.Double(TOP_LEFT_CORNER_LONG, TOP_LEFT_CORNER_LAT);

	private static final double TOP_RIGHT_CORNER_LAT = 41.0024;
	private static final double TOP_RIGHT_CORNER_LONG = 102.0517;
	private static final Point2D.Double TOP_RIGHT_CORNER = new Point2D.Double(TOP_RIGHT_CORNER_LONG,
			TOP_RIGHT_CORNER_LAT);

	private static final double BOTTOM_LEFT_CORNER_LAT = 36.9991;
	private static final double BOTTOM_LEFT_CORNER_LONG = 109.0452;
	private static final Point2D.Double BOTTOM_LEFT_CORNER = new Point2D.Double(BOTTOM_LEFT_CORNER_LONG,
			BOTTOM_LEFT_CORNER_LAT);

	private static final double BOTTOM_RIGHT_CORNER_LAT = 36.9930;
	private static final double BOTTOM_RIGHT_CORNER_LONG = 102.0421;
	private static final Point2D.Double BOTTOM_RIGHT_CORNER = new Point2D.Double(BOTTOM_RIGHT_CORNER_LONG,
			BOTTOM_RIGHT_CORNER_LAT);

	private static final double CENTER_LAT = (TOP_LEFT_CORNER_LAT + BOTTOM_RIGHT_CORNER_LAT) / 2.0;
	private static final double CENTER_LONG = (TOP_LEFT_CORNER_LONG + BOTTOM_RIGHT_CORNER_LONG) / 2.0;

	private static final double WIDTH = TOP_LEFT_CORNER.x - TOP_RIGHT_CORNER.x;
	private static final double HEIGHT = TOP_LEFT_CORNER.y - BOTTOM_LEFT_CORNER.y;
	private static final Dimension d = new Dimension();

	private static double FRAME_MULTIPLIER = 100;
	private static final double MAP_RADIUS = FRAME_MULTIPLIER / 4.5;

	private static final double FRAME_WIDTH = WIDTH * FRAME_MULTIPLIER;
	private static final double FRAME_HEIGHT = HEIGHT * FRAME_MULTIPLIER;

	private JTextField to;
	private JTextField from;
	private JTextField waypoint;
	private boolean routeButtonPressed;
	private LinkedList path;
	
	private double lastMouseX;
	private double lastMouseY;
	
	private double panelCenterX = FRAME_WIDTH/2.0;
	private double panelCenterY = FRAME_HEIGHT/2.0;
	
	private int zoomCount = 0;

	private HashMap<Shape, Map.PointofInterest> shapes = new HashMap<Shape, Map.PointofInterest>();
	private HashMap<Color, Map.Connection> connections = new HashMap<>();

	public MapDrawer(HashMap<String, Map.PointofInterest> map, JTextField to, JTextField from, JTextField waypoint) {

		this.map = map;
		this.setBackground(new java.awt.Color(255, 255, 255));
		this.to = to;
		this.from = from;
		this.waypoint = waypoint;
		this.routeButtonPressed = false;

		this.addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		System.out.println("");
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		BufferedImage outside = null;
		try {
			outside = ImageIO.read(new File("NOT COLORADO!!!.png"));
		} catch (IOException exception2) {
			exception2.printStackTrace();
		}
		
		g2.drawImage(outside, (int) (-(250 + FRAME_WIDTH/2.0) + this.panelCenterX - (this.zoomCount * 6)), 
				(int) (-(175 + FRAME_HEIGHT/2.0) + this.panelCenterY - (this.zoomCount * 3.7)), 
				(int) (1200 - (100 - this.FRAME_MULTIPLIER - this.zoomCount * 11)),
				(int) (750 - (100 - this.FRAME_MULTIPLIER - this.zoomCount * 6.2)), this);

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("MapBackground.png"));
		} catch (IOException exception1) {
			exception1.printStackTrace();
		}
		
		g2.drawImage(image, (int) (-(FRAME_WIDTH/2.0) + this.panelCenterX - (this.zoomCount * 3.5)), 
				(int) (-(FRAME_HEIGHT/2.0) + this.panelCenterY - (this.zoomCount * 2)), 
				(int) (FRAME_WIDTH - (100 - this.FRAME_MULTIPLIER - this.zoomCount * 6)), 
				(int) (FRAME_HEIGHT - (100 - this.FRAME_MULTIPLIER - this.zoomCount * 2.8)), this);
		g2.drawRect((int) (-(FRAME_WIDTH/2.0) + this.panelCenterX - (this.zoomCount * 3.5)), 
				(int) (-(FRAME_HEIGHT/2.0) + this.panelCenterY - (this.zoomCount * 2)), 
				(int) (FRAME_WIDTH - (100 - this.FRAME_MULTIPLIER - this.zoomCount * 6)), 
				(int) (FRAME_HEIGHT - (100 - this.FRAME_MULTIPLIER - this.zoomCount * 2.8)));
		
		try {
			this.drawMap(g2);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	public void setRouteButtonPressed(LinkedList path) {
		this.path = path;
		this.routeButtonPressed = true;
	}

	public void drawMap(Graphics2D g) throws IOException {
		
		g.translate(this.panelCenterX, this.panelCenterY);
		
		for (String s : this.map.keySet()) {

			String type = this.map.get(s).getType();
			double lat = this.map.get(s).getLatitude();
			double longit = this.map.get(s).getLongitude();
			Point2D.Double point = new Point2D.Double(longit - CENTER_LONG, lat - CENTER_LAT);
			
			for (Map.Connection conn : this.map.get(s).neighbors) {

				double latLine = conn.getOtherPoint().getLatitude();
				double longitLine = conn.getOtherPoint().getLongitude();
				Point2D.Double pointLine = new Point2D.Double(longitLine - CENTER_LONG, latLine - CENTER_LAT);
				Line2D.Double path = new Line2D.Double(point.getX() * -FRAME_MULTIPLIER,
						point.getY() * -FRAME_MULTIPLIER, pointLine.getX() * -FRAME_MULTIPLIER,
						pointLine.getY() * -FRAME_MULTIPLIER);
				if (this.routeButtonPressed && this.path.contains(this.map.get(s)) && this.path.contains(conn.getOtherPoint())) {
					g.setColor(Color.RED);
					g.setStroke(new BasicStroke(2));
					g.draw(path);
					g.setColor(Color.BLACK);
				} else {
					g.setStroke(new BasicStroke(0));
					g.draw(path);
				}
			}

			Rectangle2D.Double rect = new Rectangle2D.Double(point.x * -FRAME_MULTIPLIER - MAP_RADIUS / 2.0,
					point.y * -FRAME_MULTIPLIER - MAP_RADIUS / 2.0, MAP_RADIUS, MAP_RADIUS);
			this.shapes.put(rect, this.map.get(s));

			BufferedImage image = null;

			if (type.equals("City")) {
				image = ImageIO.read(new File("CitySymbol.png"));
			}

			if (type.equals("Resort")) {
				image = ImageIO.read(new File("ResortSymbol.png"));
			}

			if (type.equals("Park")) {
				image = ImageIO.read(new File("ParkSymbol.png"));
			}

			g.drawImage(image, (int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight(),
					Color.BLACK, this);

			float labelX = (float) (point.x * -FRAME_MULTIPLIER + MAP_RADIUS/2.0);
			float labelY = (float) (point.y * -FRAME_MULTIPLIER);
			g.setColor(Color.BLACK);
			g.drawString(this.map.get(s).getName(), labelX, labelY);
		}

		g.translate(-this.panelCenterX, -this.panelCenterY);
		System.out.println("Panel: " + this.panelCenterX + " " + this.panelCenterY);
	}
	
	public void zoomIn() {
		this.FRAME_MULTIPLIER = this.FRAME_MULTIPLIER + 8;
		this.zoomCount = this.zoomCount + 8;
		repaint();
	}
	
	public void zoomOut() {
		this.FRAME_MULTIPLIER = this.FRAME_MULTIPLIER - 8;
		this.zoomCount = this.zoomCount - 8;
		repaint();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		double mouseX = e.getX() - this.panelCenterX - MAP_RADIUS / 2.0;
		double mouseY = e.getY() - this.panelCenterY - MAP_RADIUS / 2.0;
		System.out.println("Mouse: " + mouseX + " " + mouseY);
		for (Shape s : this.shapes.keySet()) {
			double exs = Math.pow(mouseX - s.getBounds2D().getX(), 2);
			double whys = Math.pow(mouseY - s.getBounds2D().getY(), 2);
			double distForm = Math.sqrt(exs + whys);
			if (distForm < MAP_RADIUS / 2.0) {
				System.out.println(this.shapes.get(s).getName());
				if (this.to.hasFocus()) {
					this.to.setText(this.shapes.get(s).getName());
					this.from.requestFocus();
				}
				if (this.from.hasFocus()) {
					
					this.from.setText(this.shapes.get(s).getName());
					this.waypoint.requestFocus();
				}
				
				if(this.waypoint.hasFocus())
					this.waypoint.setText(this.shapes.get(s).getName());
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
	public void mousePressed(MouseEvent e) {
		this.lastMouseX = e.getX() - this.panelCenterX - MAP_RADIUS / 2.0;
		this.lastMouseY = e.getY() - this.panelCenterY - MAP_RADIUS / 2.0;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		double xDiff = (e.getX() - this.panelCenterX - MAP_RADIUS / 2.0) - this.lastMouseX;
		double yDiff = (e.getY() - this.panelCenterY - MAP_RADIUS / 2.0) - this.lastMouseY;
		this.panelCenterX = this.panelCenterX + xDiff;
		this.panelCenterY = this.panelCenterY + yDiff;
		this.lastMouseX = e.getX() - this.panelCenterX - MAP_RADIUS / 2.0;
		this.lastMouseY = e.getY() - this.panelCenterY - MAP_RADIUS / 2.0;
		repaint();
	}
}
