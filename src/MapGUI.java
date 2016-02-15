public class MapGUI extends javax.swing.JFrame {

	private javax.swing.JButton AddWaypointButton;
	private javax.swing.JLabel DestinationLabel;
	private javax.swing.JTextField DestinationTextInput;
	private javax.swing.JButton FindRouteButton;
	private javax.swing.JRadioButton GetResultByTime;
	private javax.swing.JLabel LocationLabel;
	private javax.swing.JTextField LocationTextInput;
	private javax.swing.JPanel Map;
	private javax.swing.JPanel Navigation;
	private javax.swing.JLabel Path;
	private javax.swing.JSpinner RatingSpinner;
	private javax.swing.JButton SearchByRatingButton;
	private javax.swing.JPanel Title;
	private javax.swing.JLabel TitleText;
	private javax.swing.JLabel TravelDistTime;
	private javax.swing.JComboBox<String> WaypointDropDown;
	private javax.swing.JSplitPane jSplitPane3;

	private boolean displayDistance = true;
	private Map guiMap;

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
		RatingSpinner = new javax.swing.JSpinner();
		WaypointDropDown = new javax.swing.JComboBox<>();
		Navigation = new javax.swing.JPanel();
		LocationLabel = new javax.swing.JLabel();
		DestinationLabel = new javax.swing.JLabel();
		GetResultByTime = new javax.swing.JRadioButton();
		FindRouteButton = new javax.swing.JButton();
		LocationTextInput = new javax.swing.JTextField();
		DestinationTextInput = new javax.swing.JTextField();
		TravelDistTime = new javax.swing.JLabel();
		Path = new javax.swing.JLabel();
		Map = new MapDrawer(this.guiMap.getTablePOIs(), LocationTextInput, DestinationTextInput);

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Map");
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setMinimumSize(new java.awt.Dimension(705, 602));
		setPreferredSize(new java.awt.Dimension(705, 602));
		setResizable(false);

		Title.setBackground(new java.awt.Color(0, 102, 255));

		TitleText.setBackground(new java.awt.Color(255, 255, 255));
		TitleText.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
		TitleText.setForeground(new java.awt.Color(239, 77, 55));
		TitleText.setText("MapBrothers Colorado Navigation");
		TitleText.setToolTipText("");

		javax.swing.GroupLayout TitleLayout = new javax.swing.GroupLayout(Title);
		Title.setLayout(TitleLayout);
		TitleLayout.setHorizontalGroup(TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(TitleLayout.createSequentialGroup().addGap(157, 157, 157).addComponent(TitleText)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		TitleLayout.setVerticalGroup(TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(TitleLayout.createSequentialGroup().addContainerGap().addComponent(TitleText)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

		Map.setMaximumSize(new java.awt.Dimension(700, 400));
		Map.setMinimumSize(new java.awt.Dimension(700, 400));
		Map.setPreferredSize(new java.awt.Dimension(700, 400));

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

		RatingSpinner.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N

		WaypointDropDown.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
		WaypointDropDown.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		javax.swing.GroupLayout MapLayout = new javax.swing.GroupLayout(Map);
		Map.setLayout(MapLayout);
		MapLayout.setHorizontalGroup(MapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
				.addGroup(MapLayout.createSequentialGroup().addContainerGap(595, Short.MAX_VALUE)
						.addGroup(MapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(SearchByRatingButton, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(AddWaypointButton, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addComponent(RatingSpinner, javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(WaypointDropDown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		MapLayout.setVerticalGroup(MapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				MapLayout.createSequentialGroup().addContainerGap(276, Short.MAX_VALUE)
						.addComponent(WaypointDropDown, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(AddWaypointButton, javax.swing.GroupLayout.PREFERRED_SIZE, 19,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(RatingSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(SearchByRatingButton).addGap(20, 20, 20)));

		jSplitPane3.setTopComponent(Map);

		Navigation.setBackground(new java.awt.Color(51, 102, 255));

		LocationLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		LocationLabel.setText("Location:");

		DestinationLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		DestinationLabel.setText("Destination");

		GetResultByTime.setBackground(new java.awt.Color(51, 102, 255));
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
		LocationTextInput.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				LocationTextInputActionPerformed(evt);
			}
		});

		DestinationTextInput.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		DestinationTextInput.setText("choose a place");

		TravelDistTime.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
		TravelDistTime.setForeground(new java.awt.Color(204, 0, 0));
		TravelDistTime.setText("Travel Ditance:");

		Path.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
		Path.setForeground(new java.awt.Color(204, 0, 0));
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
										.addComponent(TravelDistTime).addGap(8, 8, 8).addComponent(Path)
										.addGap(0, 364, Short.MAX_VALUE)))
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

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jSplitPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 700,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jSplitPane3, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));

		pack();
	}

	private void LocationTextInputActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
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

				TravelDistTime.setText("Travel Distance: " + this.guiMap.navigationDist + " mi");

			} else {

				TravelDistTime.setText("Travel Time: " + this.guiMap.navigationDist / 50 + " h");
			}

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

	private void WaypointTextInputActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void AddWaypointButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void SearchByRatingButtonActionPerformed(java.awt.event.ActionEvent evt) {

		ResultsForRating resultsTable = new ResultsForRating();
		resultsTable.setVisible(true);
	}
}
