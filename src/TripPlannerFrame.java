import javax.swing.DefaultListModel;

public class TripPlannerFrame extends javax.swing.JFrame {

	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	private javax.swing.JList resultsList;
	private javax.swing.JPanel resultsPanel;
	private javax.swing.JLabel titleLabel;
	private DefaultListModel model;
	private int tripCount;

	public TripPlannerFrame() {
		this.model = new DefaultListModel();
		this.tripCount = 0;
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        resultsPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        resultsList = new javax.swing.JList(this.model);

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		resultsPanel.setBackground(new java.awt.Color(0, 153, 255));
		resultsPanel.setFont(new java.awt.Font("Bodoni MT Black", 0, 18));
		resultsPanel.setPreferredSize(new java.awt.Dimension(290, 350));

		titleLabel.setFont(new java.awt.Font("Tahoma", 0, 16));
		titleLabel.setForeground(new java.awt.Color(0, 102, 0));
		titleLabel.setText("Destinations within given range:");

		javax.swing.GroupLayout resultsPanelLayout = new javax.swing.GroupLayout(resultsPanel);
		resultsPanel.setLayout(resultsPanelLayout);
		resultsPanelLayout.setHorizontalGroup(resultsPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(resultsPanelLayout.createSequentialGroup().addGap(34, 34, 34)
						.addGroup(resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(resultsList, javax.swing.GroupLayout.PREFERRED_SIZE, 218,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 258,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		resultsPanelLayout.setVerticalGroup(resultsPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultsPanelLayout.createSequentialGroup()
						.addContainerGap().addComponent(titleLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(resultsList,
								javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(39, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				resultsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(resultsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE));

		pack();
	}

	public void add(String dist, String name) {

		String result = name + ": " + String.format("%.2f", Double.parseDouble(dist)) + " mi";
		System.out.println(result);
		this.model.add(this.tripCount, result);
		this.tripCount++;
	}
}