import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
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
	public Map mapFile;
	private JPanel graph;
	
	public GUI(Map mapFile) {
		this.mapFile = mapFile;
//		this.graph = new MapDrawer(this.mapFile.getTablePOIs());
		this.setUpPanels();
		
	}
	private void setUpPanels(){
		this.graph.setBackground(Color.GRAY);
		this.add(this.graph);
		JPanel t = this.topPanel();
		JPanel b = this.bottomPanel();
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, this.graph, b);
		sp.setOneTouchExpandable(true);
		sp.setDividerLocation(600);
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
	
	private JPanel bottomPanel(){
		GridBagLayout layout = new GridBagLayout();
		JPanel r = new JPanel(layout);
		JTextField waypoint = new JTextField();
		JButton wpButton = new JButton("Add Waypoint");
		ActionListener wpListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Waypoint");
			}
		};
		wpButton.addActionListener(wpListener);
		JButton search = new JButton("Search by Rating");
		ActionListener searchListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Search");		
			}	
		};
		search.addActionListener(searchListener);
		JPanel b = new JPanel(layout);
		JLabel to = new JLabel("Destination:");
		JLabel from = new JLabel("Location:");
		JTextField toTxt = new JTextField();
		JTextField fromTxt = new JTextField();
		JButton route = new JButton("Find Route!");
		ActionListener routeButton = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Route: " + toTxt.getText());
				if(!toTxt.getText().equals("") && !fromTxt.getText().equals("") && !toTxt.getText().equals("choose a place") && !fromTxt.getText().equals("choose a place")){
					String to = toTxt.getText();
					String from = fromTxt.getText();
					for(String n: GUI.this.mapFile.getTablePOIs().keySet()){
						GUI.this.mapFile.getTablePOIs().get(n).straightLineDist =
						GUI.this.mapFile.getTablePOIs().get(n).DistToNeighbor(GUI.this.mapFile.getTablePOIs().get(to));
					}
					GUI.this.mapFile.navigate(to, from);
				}
				else if(toTxt.getText().equals("") && !fromTxt.getText().equals("")){
					toTxt.setText("choose a place");
				}
				else if(!toTxt.getText().equals("") && fromTxt.getText().equals("")){
					fromTxt.setText("choose a place");
				}
				else{
					toTxt.setText("choose a place");
					fromTxt.setText("choose a place");
				}
			}	
		};
		route.addActionListener(routeButton);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		b.add(waypoint, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		b.add(wpButton, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		b.add(search, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		b.add(from, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.ipadx = 100;
		b.add(fromTxt, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 0;
		b.add(to, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		c.ipadx = 100;
		b.add(toTxt, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		c.ipadx = 0;
		b.add(route, c);
		this.add(b, BorderLayout.SOUTH);
		return b;
		
	}
	
}
