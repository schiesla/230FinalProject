import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GUI implements MouseListener {
	private Map mapFile;
	private JFrame frame;
	private MapDrawer graph;
	
	public GUI(Map mapFile) {
		this.mapFile = mapFile;
		this.frame = new JFrame();
		this.graph = new MapDrawer(this.frame, this.mapFile.getTablePOIs());
		this.setUpPanels();
		
	}
	private void setUpPanels(){
		JPanel t = new JPanel();
		JLabel title = new JLabel("Map Brothers Colorado Navigation");
		title.setFont(new Font("Bauhaus 93", Font.PLAIN, 32));
		t.add(title);
		JPanel b = new JPanel();
		b.setLayout(new GridLayout(3,3,10,2));
		JLabel to = new JLabel("Destination:");
		JLabel from = new JLabel("Location:");
		JTextField toTxt = new JTextField();
		JTextField fromTxt = new JTextField();
		JButton route = new JButton("Find Route!");
		b.add(from);
		b.add(fromTxt);
		b.add(to);
		b.add(toTxt);
		b.add(route);
		JPanel r = new JPanel();
		JTextField waypoint = new JTextField();
		JButton wpButton = new JButton("Add Waypoint");
		JButton search = new JButton("Search by Rating");
		r.add(waypoint);
		r.add(wpButton);
		r.add(search);
		this.frame.add(t, BorderLayout.NORTH);
		this.frame.add(b, BorderLayout.SOUTH);
		this.frame.add(r, BorderLayout.EAST);
		this.frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub.
		
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
