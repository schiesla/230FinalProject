import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class GUITrial extends javax.swing.JFrame {
	
    private JButton AddWaypointButton;
    private JLabel DestinationLabel;
    private JTextField DestinationTextInput;
    private JButton FindRouteButton;
    private JRadioButton GetResultByTime;
    private JLabel LocationLabel;
    private JTextField LocationTextInput;
    private JPanel Map;
    private JPanel Navigation;
    private JTextField RatingTextInput;
    private JButton SearchByRatingButton;
    private JPanel Title;
    private JLabel TitleText;
    private JTextField WaypointTextInput;
    private JPopupMenu jPopupMenu1;
    private JSplitPane jSplitPane3;
    
    public Map guiMap;

    public GUITrial(Map map) {
        this.guiMap = map;
    	initComponents();
    }

    @SuppressWarnings("unchecked")                          
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        Title = new javax.swing.JPanel();
        TitleText = new javax.swing.JLabel();
        jSplitPane3 = new javax.swing.JSplitPane();
        Map = new javax.swing.JPanel();
        SearchByRatingButton = new javax.swing.JButton();
        AddWaypointButton = new javax.swing.JButton();
        WaypointTextInput = new javax.swing.JTextField();
        RatingTextInput = new javax.swing.JTextField();
        Navigation = new javax.swing.JPanel();
        LocationLabel = new javax.swing.JLabel();
        DestinationLabel = new javax.swing.JLabel();
        GetResultByTime = new javax.swing.JRadioButton();
        FindRouteButton = new javax.swing.JButton();
        LocationTextInput = new javax.swing.JTextField();
        DestinationTextInput = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Map");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(905, 810));
        setResizable(false);

        Title.setBackground(new java.awt.Color(0, 102, 255));

        TitleText.setBackground(new java.awt.Color(255, 255, 255));
        TitleText.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        TitleText.setForeground(new java.awt.Color(239, 77, 55));
        TitleText.setText("                                 MapBrothers Colorado Navigation");
        TitleText.setToolTipText("");

        javax.swing.GroupLayout TitleLayout = new javax.swing.GroupLayout(Title);
        Title.setLayout(TitleLayout);
        TitleLayout.setHorizontalGroup(
            TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitleLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(TitleText, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TitleLayout.setVerticalGroup(
            TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TitleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TitleText)
                .addContainerGap())
        );

        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        Map.setMaximumSize(new java.awt.Dimension(890, 600));
        Map.setMinimumSize(new java.awt.Dimension(890, 600));
        Map.setPreferredSize(new java.awt.Dimension(890, 600));

        SearchByRatingButton.setBackground(new java.awt.Color(0, 204, 0));
        SearchByRatingButton.setForeground(new java.awt.Color(0, 102, 0));
        SearchByRatingButton.setText("Search by Rating");
        SearchByRatingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchByRatingButtonActionPerformed(evt);
            }
        });

        AddWaypointButton.setBackground(new java.awt.Color(0, 204, 0));
        AddWaypointButton.setForeground(new java.awt.Color(0, 102, 0));
        AddWaypointButton.setText("Add Waypoint");
        AddWaypointButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddWaypointButtonActionPerformed(evt);
            }
        });

        WaypointTextInput.setBackground(new java.awt.Color(0, 102, 0));
        WaypointTextInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WaypointTextInputActionPerformed(evt);
            }
        });

        RatingTextInput.setBackground(new java.awt.Color(0, 102, 0));

        javax.swing.GroupLayout MapLayout = new javax.swing.GroupLayout(Map);
        Map.setLayout(MapLayout);
        MapLayout.setHorizontalGroup(
            MapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MapLayout.createSequentialGroup()
                .addContainerGap(749, Short.MAX_VALUE)
                .addGroup(MapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RatingTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(MapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(AddWaypointButton, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(WaypointTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SearchByRatingButton))))
                .addContainerGap())
        );

        MapLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {AddWaypointButton, RatingTextInput, SearchByRatingButton, WaypointTextInput});

        MapLayout.setVerticalGroup(
            MapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MapLayout.createSequentialGroup()
                .addContainerGap(472, Short.MAX_VALUE)
                .addComponent(AddWaypointButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WaypointTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SearchByRatingButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RatingTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSplitPane3.setTopComponent(Map);

        Navigation.setBackground(new java.awt.Color(51, 102, 255));

        LocationLabel.setText("Location:");

        DestinationLabel.setText("Destination");

        GetResultByTime.setBackground(new java.awt.Color(51, 102, 255));
        GetResultByTime.setText("Time (h)");
        GetResultByTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GetResultByTimeActionPerformed(evt);
            }
        });

        FindRouteButton.setBackground(new java.awt.Color(239, 77, 55));
        FindRouteButton.setForeground(new java.awt.Color(204, 0, 0));
        FindRouteButton.setText("Find Route !");
        FindRouteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FindRouteButtonActionPerformed(evt);
            }
        });

        LocationTextInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocationTextInputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NavigationLayout = new javax.swing.GroupLayout(Navigation);
        Navigation.setLayout(NavigationLayout);
        NavigationLayout.setHorizontalGroup(
            NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NavigationLayout.createSequentialGroup()
                        .addComponent(LocationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LocationTextInput, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE))
                    .addGroup(NavigationLayout.createSequentialGroup()
                        .addComponent(DestinationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DestinationTextInput))
                    .addGroup(NavigationLayout.createSequentialGroup()
                        .addComponent(GetResultByTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FindRouteButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        NavigationLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {DestinationLabel, GetResultByTime, LocationLabel});

        NavigationLayout.setVerticalGroup(
            NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LocationLabel)
                    .addComponent(LocationTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DestinationLabel)
                    .addComponent(DestinationTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GetResultByTime)
                    .addComponent(FindRouteButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane3.setRightComponent(Navigation);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSplitPane3)
                    .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane3))
        );
        
        pack();
    }                       

    private void LocationTextInputActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void FindRouteButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void GetResultByTimeActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
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

