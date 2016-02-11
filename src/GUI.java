import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GUI extends JFrame {
	private Map mapFile;
	private JPanel graph;
	
	public GUI(Map mapFile) {
		this.mapFile = mapFile;
		this.graph = new MapDrawer(this.mapFile.getTablePOIs());
		this.setUpPanels();
		
	}
	private void setUpPanels(){
		JPanel t = new JPanel();
		JLabel title = new JLabel("Map Brothers Colorado Navigation");
		title.setFont(new Font("Bauhaus 93", Font.PLAIN, 32));
		t.add(title);
//		JPanel c = new JPanel();
//		this.graph = new MapDrawer(c, this.mapFile.getTablePOIs());
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
//		this.frame.add(c, BorderLayout.CENTER);
		this.add(t, BorderLayout.NORTH);
		this.add(b, BorderLayout.SOUTH);
		this.add(r, BorderLayout.EAST);
//		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, c, r);
//		sp.setOneTouchExpandable(true);
//		sp.setDividerLocation(600);
//		this.frame.add(sp);
		this.setSize(1000, 800);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	
	
}
