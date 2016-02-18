import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MapGUI extends javax.swing.JFrame {

	private javax.swing.JButton AddWaypointButton;
	private javax.swing.JLabel DestinationLabel;
	private javax.swing.JTextField DestinationTextInput;
	private javax.swing.JButton FindRouteButton;
	private javax.swing.JRadioButton GetResultByTime;
	private javax.swing.JLabel LocationLabel;
	private javax.swing.JTextField LocationTextInput;
	private javax.swing.JPanel Navigation;
	private javax.swing.JLabel Path;
	private javax.swing.JButton SearchByRatingButton;
	private javax.swing.JPanel Title;
	private javax.swing.JLabel TitleText;
	private javax.swing.JLabel TravelDistTime;
	private javax.swing.JButton TripPlanner;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSplitPane jSplitPane3;
	private javax.swing.JTextField ratingTextInput;
	private javax.swing.JTextField tripPlannerText;
	private javax.swing.JTextField waypointTextInput;
	private javax.swing.JButton zoomIn;
	private javax.swing.JButton zoomOut;

	private boolean displayDistance = true;
	private boolean showPaths = false;
	private Map guiMap;
	private MapDrawer Map;

	public MapGUI(Map map) {
		this.guiMap = map;
		initComponents();
		this.LocationTextInput.requestFocus();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		Title = new javax.swing.JPanel();
		TitleText = new javax.swing.JLabel();
		jSplitPane3 = new javax.swing.JSplitPane();
		SearchByRatingButton = new javax.swing.JButton();
		AddWaypointButton = new javax.swing.JButton();
		ratingTextInput = new javax.swing.JTextField();
		zoomIn = new javax.swing.JButton();
		zoomOut = new javax.swing.JButton();
		waypointTextInput = new javax.swing.JTextField();
		TripPlanner = new javax.swing.JButton();
		tripPlannerText = new javax.swing.JTextField();
		Navigation = new javax.swing.JPanel();
		LocationLabel = new javax.swing.JLabel();
		DestinationLabel = new javax.swing.JLabel();
		GetResultByTime = new javax.swing.JRadioButton();
		FindRouteButton = new javax.swing.JButton();
		LocationTextInput = new javax.swing.JTextField();
		DestinationTextInput = new javax.swing.JTextField();
		TravelDistTime = new javax.swing.JLabel();
		Path = new javax.swing.JLabel();
		jSeparator1 = new javax.swing.JSeparator();
		Map = new MapDrawer(this.guiMap.getTablePOIs(), LocationTextInput, DestinationTextInput, waypointTextInput);

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Map");
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setMinimumSize(new java.awt.Dimension(710, 610));
		setPreferredSize(new java.awt.Dimension(1076, 825));
		setResizable(false);
		setSize(new java.awt.Dimension(1076, 825));
		getContentPane().setLayout(null);

		Title.setBackground(new java.awt.Color(51, 153, 255));
		Title.setMinimumSize(new java.awt.Dimension(700, 60));
		Title.setPreferredSize(new java.awt.Dimension(1050, 60));

		TitleText.setBackground(new java.awt.Color(255, 255, 255));
		TitleText.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 28)); // NOI18N
		TitleText.setForeground(new java.awt.Color(0, 102, 0));
		TitleText.setText("MapBrothers Colorado Navigation");
		TitleText.setToolTipText("");

		javax.swing.GroupLayout TitleLayout = new javax.swing.GroupLayout(Title);
		Title.setLayout(TitleLayout);
		TitleLayout.setHorizontalGroup(TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TitleLayout.createSequentialGroup()
						.addContainerGap(327, Short.MAX_VALUE).addComponent(TitleText).addGap(306, 306, 306)));
		TitleLayout.setVerticalGroup(TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(TitleLayout.createSequentialGroup().addContainerGap().addComponent(TitleText)
						.addContainerGap(25, Short.MAX_VALUE)));

		getContentPane().add(Title);
		Title.setBounds(0, 0, 1070, 70);

		jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

		Map.setMaximumSize(new java.awt.Dimension(1050, 600));
		Map.setMinimumSize(new java.awt.Dimension(700, 400));
		Map.setPreferredSize(new java.awt.Dimension(1050, 600));

		SearchByRatingButton.setBackground(new java.awt.Color(0, 204, 0));
		SearchByRatingButton.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
		SearchByRatingButton.setForeground(new java.awt.Color(0, 102, 0));
		SearchByRatingButton.setText("Search Rating");
		SearchByRatingButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				SearchByRatingButtonActionPerformed(evt);
			}
		});

		AddWaypointButton.setBackground(new java.awt.Color(0, 204, 0));
		AddWaypointButton.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
		AddWaypointButton.setForeground(new java.awt.Color(0, 102, 0));
		AddWaypointButton.setText("Add Waypoint");
		AddWaypointButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				AddWaypointButtonActionPerformed(evt);
			}
		});

		ratingTextInput.setText("rating");

		zoomIn.setText("+");
		zoomIn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				zoomInActionPerformed(evt);
			}
		});

		zoomOut.setText("-");
		zoomOut.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				zoomOutActionPerformed(evt);
			}
		});

		waypointTextInput.setText("waypoint");

		TripPlanner.setForeground(new java.awt.Color(0, 102, 0));
		TripPlanner.setText("Trip Planner");
		TripPlanner.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				TripPlannerActionPerformed(evt);
			}
		});

		tripPlannerText.setText("dist");

		javax.swing.GroupLayout MapLayout = new javax.swing.GroupLayout(Map);
		Map.setLayout(MapLayout);
		MapLayout
				.setHorizontalGroup(
						MapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(
										MapLayout.createSequentialGroup().addContainerGap(953, Short.MAX_VALUE)
												.addGroup(MapLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(tripPlannerText)
														.addComponent(ratingTextInput,
																javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(SearchByRatingButton, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(AddWaypointButton, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(zoomOut, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(zoomIn, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addComponent(waypointTextInput).addComponent(TripPlanner,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)).addContainerGap()));
		MapLayout.setVerticalGroup(MapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(MapLayout.createSequentialGroup().addContainerGap(329, Short.MAX_VALUE)
						.addComponent(tripPlannerText, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(TripPlanner)
						.addGap(18, 18, 18).addComponent(zoomIn)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(zoomOut)
						.addGap(13, 13, 13)
						.addComponent(waypointTextInput, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(AddWaypointButton, javax.swing.GroupLayout.PREFERRED_SIZE, 19,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(13, 13, 13)
						.addComponent(ratingTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(SearchByRatingButton).addGap(20, 20, 20)));

		jSplitPane3.setTopComponent(Map);

		Navigation.setBackground(new java.awt.Color(51, 153, 255));
		Navigation.setMinimumSize(new java.awt.Dimension(700, 100));
		Navigation.setPreferredSize(new java.awt.Dimension(1050, 105));

		LocationLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		LocationLabel.setText("Location:");

		DestinationLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		DestinationLabel.setText("Destination");

		GetResultByTime.setBackground(new java.awt.Color(51, 153, 255));
		GetResultByTime.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		GetResultByTime.setText("Time (h)");
		GetResultByTime.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				GetResultByTimeActionPerformed(evt);
			}
		});

		FindRouteButton.setBackground(new java.awt.Color(239, 77, 55));
		FindRouteButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		FindRouteButton.setForeground(new java.awt.Color(204, 0, 0));
		FindRouteButton.setText("Find Route");
		FindRouteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				FindRouteButtonActionPerformed(evt);
			}
		});

		LocationTextInput.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		LocationTextInput.setText("choose a place");
	
		DestinationTextInput.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		DestinationTextInput.setText("choose a place");

		TravelDistTime.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
		TravelDistTime.setForeground(new java.awt.Color(0, 51, 0));
		TravelDistTime.setText("Travel Ditance:");

		Path.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
		Path.setForeground(new java.awt.Color(0, 51, 0));
		Path.setText("Path:");

		javax.swing.GroupLayout NavigationLayout = new javax.swing.GroupLayout(Navigation);
		Navigation.setLayout(NavigationLayout);
		NavigationLayout.setHorizontalGroup(NavigationLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(NavigationLayout.createSequentialGroup().addContainerGap()
						.addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(NavigationLayout.createSequentialGroup().addComponent(LocationLabel)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(LocationTextInput))
								.addGroup(
										NavigationLayout.createSequentialGroup().addComponent(DestinationLabel)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(DestinationTextInput))
								.addGroup(NavigationLayout.createSequentialGroup().addComponent(GetResultByTime)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(FindRouteButton)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(TravelDistTime)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(Path).addGap(0, 750, Short.MAX_VALUE)))
						.addContainerGap()));

		NavigationLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
				new java.awt.Component[] { DestinationLabel, GetResultByTime, LocationLabel });

		NavigationLayout.setVerticalGroup(NavigationLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(NavigationLayout.createSequentialGroup().addContainerGap()
						.addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(LocationLabel).addComponent(LocationTextInput,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(DestinationLabel).addComponent(DestinationTextInput,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(GetResultByTime).addComponent(FindRouteButton)
								.addComponent(TravelDistTime).addComponent(Path))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jSplitPane3.setRightComponent(Navigation);

		getContentPane().add(jSplitPane3);
		jSplitPane3.setBounds(0, 78, 1070, 930);
		getContentPane().add(jSeparator1);
		jSeparator1.setBounds(3, 70, 1070, 10);

		pack();
	}

	private void FindRouteButtonActionPerformed(java.awt.event.ActionEvent evt) {

		if (!DestinationTextInput.getText().equals("") && !LocationTextInput.getText().equals("")
				&& !DestinationTextInput.getText().equals("choose a place")
				&& !LocationTextInput.getText().equals("choose a place")) {

			String to = DestinationTextInput.getText();
			String from = LocationTextInput.getText();

			if (MapGUI.this.guiMap.getTablePOIs().get(to) != null
					&& MapGUI.this.guiMap.getTablePOIs().get(from) != null) {

				for (String n : MapGUI.this.guiMap.getTablePOIs().keySet()) {

					MapGUI.this.guiMap.getTablePOIs().get(n).straightLineDist = MapGUI.this.guiMap.getTablePOIs().get(n)
							.DistToNeighbor(MapGUI.this.guiMap.getTablePOIs().get(to));
				}
			}

			Path.setText("Path: " + this.guiMap.navigate(to, from).toString());

			if (this.displayDistance) {

				String distance = String.format("%.2f", this.guiMap.navigationDist);
				TravelDistTime.setText("Travel Distance: " + distance + " mi");

			} else {

				double hours = this.guiMap.navigationDist / 55.0;

				int hoursDisplay = (int) (hours / 1);
				double mins = ((hours % 1) * 60);
				String minutes = String.format("%.0f", mins);

				TravelDistTime.setText("Travel Time: " + hoursDisplay + " hr " + minutes + " min");
			}

			this.Map.setRouteButtonPressed(this.guiMap.navigate(to, from));
			this.Map.repaint();
			this.guiMap.navigationDist = 0;

		} else if (DestinationTextInput.getText().equals("") && !LocationTextInput.getText().equals("")) {

			DestinationTextInput.setText("choose a place");

		} else if (!DestinationTextInput.getText().equals("") && LocationTextInput.getText().equals("")) {

			LocationTextInput.setText("choose a place");

		} else {

			DestinationTextInput.setText("choose a place");
			LocationTextInput.setText("choose a place");
		}
	}

	private void GetResultByTimeActionPerformed(java.awt.event.ActionEvent evt) {

		this.displayDistance = !this.displayDistance;
	}

	private void AddWaypointButtonActionPerformed(java.awt.event.ActionEvent evt) {

		if (!DestinationTextInput.getText().equals("") && !LocationTextInput.getText().equals("")
				&& !waypointTextInput.getText().equals("") && !waypointTextInput.getText().equals("waypoint")
				&& !DestinationTextInput.getText().equals("choose a place")
				&& !LocationTextInput.getText().equals("choose a place")) {

			String to = DestinationTextInput.getText();
			String from = LocationTextInput.getText();
			String waypoint = waypointTextInput.getText();

			if (MapGUI.this.guiMap.getTablePOIs().get(waypoint) != null
					&& MapGUI.this.guiMap.getTablePOIs().get(from) != null) {

				for (String n : MapGUI.this.guiMap.getTablePOIs().keySet()) {

					MapGUI.this.guiMap.getTablePOIs().get(n).straightLineDist = MapGUI.this.guiMap.getTablePOIs().get(n)
							.DistToNeighbor(MapGUI.this.guiMap.getTablePOIs().get(waypoint));
				}
			}

			LinkedList<Map.PointofInterest> path1 = this.guiMap.navigate(waypoint, from);
			path1.remove(this.guiMap.getTablePOIs().get(waypoint));
			double dist1 = this.guiMap.navigationDist;

			if (MapGUI.this.guiMap.getTablePOIs().get(to) != null
					&& MapGUI.this.guiMap.getTablePOIs().get(waypoint) != null) {

				for (String n : MapGUI.this.guiMap.getTablePOIs().keySet()) {

					MapGUI.this.guiMap.getTablePOIs().get(n).straightLineDist = MapGUI.this.guiMap.getTablePOIs().get(n)
							.DistToNeighbor(MapGUI.this.guiMap.getTablePOIs().get(to));
				}
			}

			this.guiMap.navigationDist = 0;
			LinkedList<Map.PointofInterest> path2 = this.guiMap.navigate(to, waypoint);
			double dist2 = this.guiMap.navigationDist;

			for (Map.PointofInterest poi : path2) {

				path1.add(poi);
			}

			this.guiMap.navigationDist = dist1 + dist2;
			Path.setText("Path: " + path1.toString());

			if (this.displayDistance) {

				String distance = String.format("%.2f", this.guiMap.navigationDist);
				TravelDistTime.setText("Travel Distance: " + distance + " mi");

			} else {

				double hours = this.guiMap.navigationDist / 55.0;

				int hoursDisplay = (int) (hours / 1);
				double mins = ((hours % 1) * 60);
				String minutes = String.format("%.0f", mins);

				TravelDistTime.setText("Travel Time: " + hoursDisplay + " hr " + minutes + " min");
			}

			this.Map.setRouteButtonPressed(path1);
			this.Map.repaint();
			this.guiMap.navigationDist = 0;

			System.out.println(path1);

		} else if (DestinationTextInput.getText().equals("") && !LocationTextInput.getText().equals("")) {

			DestinationTextInput.setText("choose a place");

		} else if (!DestinationTextInput.getText().equals("") && LocationTextInput.getText().equals("")) {

			LocationTextInput.setText("choose a place");

		} else {

			DestinationTextInput.setText("choose a place");
			LocationTextInput.setText("choose a place");
		}
	}

	private void SearchByRatingButtonActionPerformed(java.awt.event.ActionEvent evt) {

		ArrayList<Map.PointofInterest> results = this.guiMap
				.searchByRating(Double.parseDouble(ratingTextInput.getText()));

		ResultsForRating resultsTable = new ResultsForRating();
		for (int i = 0; i < results.size(); i++) {
			resultsTable.add(results.get(i).getName(), results.get(i).getRating());
		}
		resultsTable.setVisible(true);
	}

	private void zoomInActionPerformed(java.awt.event.ActionEvent evt) {
		this.Map.zoomIn();
	}

	private void zoomOutActionPerformed(java.awt.event.ActionEvent evt) {
		this.Map.zoomOut();
	}

	private void TripPlannerActionPerformed(java.awt.event.ActionEvent evt) {

		HashMap<String, LinkedList<Map.PointofInterest>> paths = this.guiMap
				.planTrip(Double.parseDouble(tripPlannerText.getText()), LocationTextInput.getText());
		
		TripPlannerFrame plannerResults = new TripPlannerFrame();
		
		for (String dist : paths.keySet()) {
			
			plannerResults.add(dist, paths.get(dist).getLast().getName());
		}
		
		plannerResults.setVisible(true);
	}

}
