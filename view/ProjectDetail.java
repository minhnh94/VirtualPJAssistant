package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.WindowConstants;

import model.Employee;
import model.Project;
import model.Project.PJ_STATUS;

public class ProjectDetail extends JDialog {

	private static final long serialVersionUID = 1L;

	private JList<JPanel> devJList;
	private DefaultListModel<JPanel> devListModel;
	private PanelListCellRenderer devRenderer;

	private JList<JPanel> testJList;
	private DefaultListModel<JPanel> testListModel;
	private PanelListCellRenderer testRenderer;

	private ImageButton viewIssuesButton;
	private ImageButton closeButton;
	private ImageButton addDevButton;
	private ImageButton addTestButton;

	public ProjectDetail(Project p, boolean isManager) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		this.setResizable(false);
		this.setTitle(p.getName());
		this.setBounds((dim.width - 900) / 2, 10, 900, 510);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		PatternPanel pjPanel = new PatternPanel(CommonString.ISSBG);
		pjPanel.setLayout(new BorderLayout());
		this.setContentPane(pjPanel);

		JLabel pjName = new JLabel(p.getName(), JLabel.CENTER);
		pjName.setOpaque(false);
		pjName.setPreferredSize(new Dimension(600, 50));
		pjName.setFont(Theme.BIGGER_FONT);
		pjName.setForeground(Theme.getColor(1));

		pjPanel.add(pjName, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setOpaque(false);
		pjPanel.add(centerPanel, BorderLayout.CENTER);

		JPanel topCenterPanel = new JPanel(new FlowLayout());
		topCenterPanel.setOpaque(false);
		centerPanel.add(topCenterPanel, BorderLayout.NORTH);

		ImageLabel folderLabel = new ImageLabel(CommonString.COLORFULFOLDER, 200, 137);
		folderLabel.setOpaque(false);
		topCenterPanel.add(folderLabel);

		JTextArea messageTextArea = new JTextArea();
		messageTextArea.setForeground(Theme.getColor(1));
		messageTextArea.setPreferredSize(new Dimension(670, 137));
		messageTextArea.setFont(Theme.SMALLER_FONT);
		messageTextArea.setLineWrap(true);
		messageTextArea.setWrapStyleWord(true);
		messageTextArea.setText(p.getDescription());
		topCenterPanel.add(messageTextArea);

		JPanel leftCenterPanel = new JPanel();
		leftCenterPanel.setOpaque(false);
		centerPanel.add(leftCenterPanel, BorderLayout.WEST);
		GridBagLayout gbl_leftCenterPanel = new GridBagLayout();
		gbl_leftCenterPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_leftCenterPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_leftCenterPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_leftCenterPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		leftCenterPanel.setLayout(gbl_leftCenterPanel);

		JLabel pmNameLabel = new JLabel(ObjectPropertyString.PM + ": ", JLabel.RIGHT);
		pmNameLabel.setForeground(Theme.getColor(1));
		pmNameLabel.setFont(Theme.SMALLER_FONT);
		pmNameLabel.setPreferredSize(new Dimension(150, 30));
		GridBagConstraints gbc_pmNameLabel = new GridBagConstraints();
		gbc_pmNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_pmNameLabel.anchor = GridBagConstraints.EAST;
		gbc_pmNameLabel.gridx = 0;
		gbc_pmNameLabel.gridy = 0;
		leftCenterPanel.add(pmNameLabel, gbc_pmNameLabel);

		JLabel pmName = new JLabel(p.getCurrentManager().getName());
		pmName.setForeground(Theme.getColor(1));
		pmName.setFont(Theme.SMALLER_FONT);
		pmName.setPreferredSize(new Dimension(150, 30));
		GridBagConstraints gbc_pmName = new GridBagConstraints();
		gbc_pmName.insets = new Insets(0, 0, 5, 0);
		gbc_pmName.fill = GridBagConstraints.HORIZONTAL;
		gbc_pmName.gridx = 1;
		gbc_pmName.gridy = 0;
		leftCenterPanel.add(pmName, gbc_pmName);

		JLabel openAtLabel = new JLabel(CommonString.OPENAT + ": ", JLabel.RIGHT);
		openAtLabel.setForeground(Theme.getColor(1));
		openAtLabel.setFont(Theme.SMALLER_FONT);
		openAtLabel.setPreferredSize(new Dimension(150, 30));
		GridBagConstraints gbc_openAtLabel = new GridBagConstraints();
		gbc_openAtLabel.anchor = GridBagConstraints.EAST;
		gbc_openAtLabel.insets = new Insets(0, 0, 5, 5);
		gbc_openAtLabel.gridx = 0;
		gbc_openAtLabel.gridy = 1;
		leftCenterPanel.add(openAtLabel, gbc_openAtLabel);

		JLabel openDay = new JLabel(p.getOpenDay());
		openDay.setForeground(Theme.getColor(1));
		openDay.setFont(Theme.SMALLER_FONT);
		openDay.setPreferredSize(new Dimension(150, 30));
		GridBagConstraints gbc_openDay = new GridBagConstraints();
		gbc_openDay.insets = new Insets(0, 0, 5, 0);
		gbc_openDay.fill = GridBagConstraints.HORIZONTAL;
		gbc_openDay.gridx = 1;
		gbc_openDay.gridy = 1;
		leftCenterPanel.add(openDay, gbc_openDay);

		JLabel estimateAtLabel = new JLabel(CommonString.ESTIMATEAT + ": ", JLabel.RIGHT);
		estimateAtLabel.setForeground(Theme.getColor(1));
		estimateAtLabel.setFont(Theme.SMALLER_FONT);
		estimateAtLabel.setPreferredSize(new Dimension(150, 30));
		GridBagConstraints gbc_estimateAtLabel = new GridBagConstraints();
		gbc_estimateAtLabel.insets = new Insets(0, 0, 5, 5);
		gbc_estimateAtLabel.anchor = GridBagConstraints.EAST;
		gbc_estimateAtLabel.gridx = 0;
		gbc_estimateAtLabel.gridy = 2;
		leftCenterPanel.add(estimateAtLabel, gbc_estimateAtLabel);

		JLabel estimateDay = new JLabel(p.getEstimateDay());
		estimateDay.setForeground(Theme.getColor(1));
		estimateDay.setFont(Theme.SMALLER_FONT);
		estimateDay.setPreferredSize(new Dimension(150, 30));
		GridBagConstraints gbc_estimateDay = new GridBagConstraints();
		gbc_estimateDay.insets = new Insets(0, 0, 5, 0);
		gbc_estimateDay.fill = GridBagConstraints.HORIZONTAL;
		gbc_estimateDay.gridx = 1;
		gbc_estimateDay.gridy = 2;
		leftCenterPanel.add(estimateDay, gbc_estimateDay);

		JLabel closeDayLabel = new JLabel(CommonString.CLOSEAT + ": ", JLabel.RIGHT);
		closeDayLabel.setForeground(Theme.getColor(1));
		closeDayLabel.setFont(Theme.SMALLER_FONT);
		closeDayLabel.setPreferredSize(new Dimension(150, 30));
		GridBagConstraints gbc_closeDayLabel = new GridBagConstraints();
		gbc_closeDayLabel.insets = new Insets(0, 0, 5, 5);
		gbc_closeDayLabel.anchor = GridBagConstraints.EAST;
		gbc_closeDayLabel.gridx = 0;
		gbc_closeDayLabel.gridy = 3;
		leftCenterPanel.add(closeDayLabel, gbc_closeDayLabel);

		JLabel closeDay = new JLabel(p.getCloseDay());
		closeDay.setForeground(Theme.getColor(1));
		closeDay.setFont(Theme.SMALLER_FONT);
		closeDay.setPreferredSize(new Dimension(150, 30));
		GridBagConstraints gbc_closeDay = new GridBagConstraints();
		gbc_closeDay.insets = new Insets(0, 0, 5, 0);
		gbc_closeDay.fill = GridBagConstraints.HORIZONTAL;
		gbc_closeDay.gridx = 1;
		gbc_closeDay.gridy = 3;
		leftCenterPanel.add(closeDay, gbc_closeDay);

		JPanel centerCenterPanel = new JPanel(new BorderLayout());
		centerCenterPanel.setOpaque(false);
		centerPanel.add(centerCenterPanel, BorderLayout.CENTER);

		JScrollPane devScrollPane = new JScrollPane();
		devScrollPane.setOpaque(false);
		devScrollPane.setPreferredSize(new Dimension(300, 300));
		centerCenterPanel.add(devScrollPane, BorderLayout.CENTER);

		devListModel = new DefaultListModel<JPanel>();

		devJList = new JList<JPanel>();
		devJList.setOpaque(false);
		devJList.setModel(devListModel);
		devJList.setLayoutOrientation(JList.VERTICAL_WRAP);

		devScrollPane.setViewportView(devJList);
		devScrollPane.getViewport().setOpaque(false);
		centerCenterPanel.add(devScrollPane, BorderLayout.CENTER);

		JLabel devListLabel = new JLabel(ObjectPropertyString.DEV2, JLabel.CENTER);
		devListLabel.setForeground(Theme.getColor(1));
		devListLabel.setFont(Theme.SMALLER_FONT);
		devListLabel.setPreferredSize(new Dimension(300, 30));
		centerCenterPanel.add(devListLabel, BorderLayout.NORTH);

		JPanel rightCenterPanel = new JPanel(new BorderLayout());
		rightCenterPanel.setOpaque(false);
		centerPanel.add(rightCenterPanel, BorderLayout.EAST);

		JScrollPane testScrollPane = new JScrollPane();
		testScrollPane.setOpaque(false);
		testScrollPane.setPreferredSize(new Dimension(300, 300));
		rightCenterPanel.add(testScrollPane, BorderLayout.CENTER);

		testListModel = new DefaultListModel<JPanel>();

		testJList = new JList<JPanel>();
		testJList.setOpaque(false);
		testJList.setModel(testListModel);
		testJList.setLayoutOrientation(JList.VERTICAL_WRAP);

		testScrollPane.setViewportView(testJList);
		testScrollPane.getViewport().setOpaque(false);
		rightCenterPanel.add(testScrollPane, BorderLayout.CENTER);

		JLabel testListLabel = new JLabel(ObjectPropertyString.TEST2, JLabel.CENTER);
		testListLabel.setForeground(Theme.getColor(1));
		testListLabel.setFont(Theme.SMALLER_FONT);
		testListLabel.setPreferredSize(new Dimension(300, 30));
		rightCenterPanel.add(testListLabel, BorderLayout.NORTH);

		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setOpaque(false);
		pjPanel.add(bottomPanel, BorderLayout.SOUTH);

		viewIssuesButton = new ImageButton(CommonString.VIEWISSLIST, CommonString.VIEWISSLISTH);
		viewIssuesButton.setPreferredSize(new Dimension(200, 50));
		bottomPanel.add(viewIssuesButton);

		if (isManager) {
			if (p.getStatus() == PJ_STATUS.FINISHED) {
				closeButton = new ImageButton(CommonString.REOPENPJ, CommonString.REOPENPJH);
				closeButton.setPreferredSize(new Dimension(200, 50));
				bottomPanel.add(closeButton);
			} else {
				closeButton = new ImageButton(CommonString.CLOSEPJ, CommonString.CLOSEPJH);
				closeButton.setPreferredSize(new Dimension(200, 50));
				bottomPanel.add(closeButton);
			}
			addDevButton = new ImageButton(CommonString.ADDDEV, CommonString.ADDDEVH);
			addDevButton.setPreferredSize(new Dimension(200, 50));
			bottomPanel.add(addDevButton);

			addTestButton = new ImageButton(CommonString.ADDTEST, CommonString.ADDTESTH);
			addTestButton.setPreferredSize(new Dimension(200, 50));
			bottomPanel.add(addTestButton);
		}

		setListOfDev(p.getCurrentDevs());
		setListOfTest(p.getCurrentTesters());
	}

	class PanelListCellRenderer implements ListCellRenderer<JPanel> {

		@Override
		public Component getListCellRendererComponent(JList<? extends JPanel> list, JPanel value, int index, boolean isSelected, boolean cellHasFocus) {
			if (value != null) {
				if (isSelected) {
					value.setOpaque(true);
					value.setBackground(Theme.getColor(3));
				} else {
					value.setOpaque(false);
				}
				return value;
			} else {
				return new JLabel("???");
			}
		}
	}

	private JPanel getEmployeePanel(Employee employee) {
		JPanel employeePanel = new JPanel();
		employeePanel.setPreferredSize(new Dimension(270, 60));
		employeePanel.setOpaque(false);
		employeePanel.setLayout(new FlowLayout());

		JLabel nameLabel = new JLabel(employee.getName());
		nameLabel.setFont(Theme.SMALLER_FONT);
		nameLabel.setForeground(Theme.getColor(1));
		nameLabel.setPreferredSize(new Dimension(270, 25));
		nameLabel.setOpaque(false);
		employeePanel.add(nameLabel);

		JLabel emailLabel = new JLabel(employee.getEmail());
		emailLabel.setFont(Theme.EXTRA_SMALLER_FONT);
		emailLabel.setForeground(Theme.getColor(1));
		emailLabel.setPreferredSize(new Dimension(270, 25));
		emailLabel.setOpaque(false);
		employeePanel.add(emailLabel);

		return employeePanel;
	}

	public void setListOfDev(ArrayList<Employee> developers) {
		devListModel.clear();
		devRenderer = new PanelListCellRenderer();
		devJList.setCellRenderer(devRenderer);

		for (Employee developer : developers) {
			devListModel.addElement(getEmployeePanel(developer));
		}
	}

	public void setListOfTest(ArrayList<Employee> testers) {
		testListModel.clear();
		testRenderer = new PanelListCellRenderer();
		testJList.setCellRenderer(testRenderer);

		for (Employee tester : testers) {
			testListModel.addElement(getEmployeePanel(tester));
		}
	}

	public void addViewButtonActionListener(ActionListener listener) {
		viewIssuesButton.addActionListener(listener);
	}

	public void addCloseButtonActionListener(ActionListener listener) {
		closeButton.addActionListener(listener);
	}

	public void addAddDevButtonActionListener(ActionListener listener) {
		addDevButton.addActionListener(listener);
	}

	public void addAddTestButtonActionListener(ActionListener listener) {
		addTestButton.addActionListener(listener);
	}

	public void addDevListMouseListener(MouseListener listener) {
		devJList.addMouseListener(listener);
	}

	public void addTestListMouseListener(MouseListener listener) {
		testJList.addMouseListener(listener);
	}

	public int getSelectedDevList() {
		return devJList.getSelectedIndex();
	}

	public int getSelectedTesterList() {
		return testJList.getSelectedIndex();
	}

}
