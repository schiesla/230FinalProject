import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
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
		this.graph.setBackground(Color.GRAY);
		this.add(this.graph);
		JPanel t = this.topPanel();
		JPanel b = this.bottomPanel();
		JPanel r = this.rightPanel();
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.graph, r);
		sp.setOneTouchExpandable(true);
		sp.setDividerLocation(800);
		this.add(sp);
		this.setSize(1000, 800);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private JPanel topPanel() {
		JPanel t = new JPanel();
		JLabel title = new JLabel("Map Brothers Colorado Navigation");
		title.setFont(new Font("Bauhaus 93", Font.PLAIN, 32));
		title.setForeground(Color.GREEN);
		t.add(title);
		this.add(t, BorderLayout.NORTH);
		return t;
		
	}
	private JPanel rightPanel() {
		JPanel r = new JPanel();
//		r.setLayout(new GridLayout(3,1,0, 10));
		JTextField waypoint = new JTextField();
		JButton wpButton = new JButton("Add Waypoint");
		wpButton.setSize(100, 50);
		JButton search = new JButton("Search by Rating");
		r.add(waypoint);
		r.add(wpButton);
		r.add(search);
		this.add(r, BorderLayout.EAST);
		return r;
		
	}
	private JPanel bottomPanel(){
		JPanel b = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		b.setLayout(layout);
		JLabel to = new JLabel("Destination:");
		JLabel from = new JLabel("Location:");
		JTextField toTxt = new JTextField();
		JTextField fromTxt = new JTextField();
		JButton route = new JButton("Find Route!");
		ActionListener routeButton = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Route");
				
			}
			
		};
		route.addActionListener(routeButton);
		b.add(from, layout);
		b.add(fromTxt, layout);
		b.add(to, layout);
		b.add(toTxt, layout);
		b.add(route, layout);
		this.add(b, BorderLayout.SOUTH);
		return b;
		
	}
	
}
