package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import model.Developer;
import model.Project;
import model.Tester;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

public class NewIssue extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> listOfDev;
	private JComboBox<String> listOfTest;
	private JComboBox<String> listOfPriority;
	private JTextArea messageTextArea;
	private ImageButton submitButton;

	public NewIssue(Project project) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setResizable(false);
		this.setTitle(CommonString.ISSTITLE);
		this.setBounds((dim.width-600)/2, 10, 600, 510);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		PatternPanel issPanel = new PatternPanel(CommonString.ISSBG);
		issPanel.setLayout(new BorderLayout());
		this.setContentPane(issPanel);
		
		JLabel issName = new JLabel(CommonString.CREATENEWISSUE, JLabel.CENTER);
		issName.setOpaque(false);
		issName.setPreferredSize(new Dimension(600, 50));
		issName.setFont(Theme.BIGGER_FONT);
		issName.setForeground(Theme.getColor(1));
		
		issPanel.add(issName, BorderLayout.NORTH);
		
		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setOpaque(false);
		bottomPanel.setPreferredSize(new Dimension(550, 70));
		issPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		submitButton = new ImageButton(CommonString.CREATEISS, CommonString.CREATEISSH);
		submitButton.setPreferredSize(new Dimension(200,50));
		bottomPanel.add(submitButton);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		issPanel.add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0, 0, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
		JLabel projectNameLabel = new JLabel(ObjectPropertyString.INPJ + ": ", JLabel.RIGHT);
		projectNameLabel.setForeground(Theme.getColor(1));
		projectNameLabel.setFont(Theme.SMALLER_FONT);
		projectNameLabel.setPreferredSize(new Dimension(200,30));
		GridBagConstraints gbc_projectNameLabel = new GridBagConstraints();
		gbc_projectNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_projectNameLabel.anchor = GridBagConstraints.EAST;
		gbc_projectNameLabel.gridx = 0;
		gbc_projectNameLabel.gridy = 0;
		centerPanel.add(projectNameLabel, gbc_projectNameLabel);
		
		JLabel projectName = new JLabel(project.getName());
		projectName.setForeground(Theme.getColor(1));
		projectName.setFont(Theme.SMALLER_FONT);
		projectName.setPreferredSize(new Dimension(380,30));
		GridBagConstraints gbc_projectName = new GridBagConstraints();
		gbc_projectName.insets = new Insets(0, 0, 5, 0);
		gbc_projectName.fill = GridBagConstraints.HORIZONTAL;
		gbc_projectName.gridx = 1;
		gbc_projectName.gridy = 0;
		centerPanel.add(projectName, gbc_projectName);
		
		JLabel devNameLabel = new JLabel(ObjectPropertyString.DEV + ": ", JLabel.RIGHT);
		devNameLabel.setForeground(Theme.getColor(1));
		devNameLabel.setFont(Theme.SMALLER_FONT);
		devNameLabel.setPreferredSize(new Dimension(200,30));
		GridBagConstraints gbc_devNameLabel = new GridBagConstraints();
		gbc_devNameLabel.anchor = GridBagConstraints.EAST;
		gbc_devNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_devNameLabel.gridx = 0;
		gbc_devNameLabel.gridy = 1;
		centerPanel.add(devNameLabel, gbc_devNameLabel);
		
		listOfDev = new JComboBox<String>();
		listOfDev.setForeground(Theme.getColor(1));
		listOfDev.setFont(Theme.SMALLER_FONT);
		listOfDev.setPreferredSize(new Dimension(380,30));
		GridBagConstraints gbc_listOfDev = new GridBagConstraints();
		gbc_listOfDev.insets = new Insets(0, 0, 5, 0);
		gbc_listOfDev.fill = GridBagConstraints.HORIZONTAL;
		gbc_listOfDev.gridx = 1;
		gbc_listOfDev.gridy = 1;
		centerPanel.add(listOfDev, gbc_listOfDev);
		
		JLabel testNameLabel = new JLabel(ObjectPropertyString.TEST + ": ", JLabel.RIGHT);
		testNameLabel.setForeground(Theme.getColor(1));
		testNameLabel.setFont(Theme.SMALLER_FONT);
		testNameLabel.setPreferredSize(new Dimension(200,30));
		GridBagConstraints gbc_testNameLabel = new GridBagConstraints();
		gbc_testNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_testNameLabel.anchor = GridBagConstraints.EAST;
		gbc_testNameLabel.gridx = 0;
		gbc_testNameLabel.gridy = 2;
		centerPanel.add(testNameLabel, gbc_testNameLabel);
		
		listOfTest = new JComboBox<String>();
		listOfTest.setForeground(Theme.getColor(1));
		listOfTest.setFont(Theme.SMALLER_FONT);
		listOfTest.setPreferredSize(new Dimension(380,30));
		GridBagConstraints gbc_listOfTest = new GridBagConstraints();
		gbc_listOfTest.insets = new Insets(0, 0, 5, 0);
		gbc_listOfTest.fill = GridBagConstraints.HORIZONTAL;
		gbc_listOfTest.gridx = 1;
		gbc_listOfTest.gridy = 2;
		centerPanel.add(listOfTest, gbc_listOfTest);
		
		JLabel priorityLabel = new JLabel(ObjectPropertyString.PRIORITY + ": ", JLabel.RIGHT);
		priorityLabel.setForeground(Theme.getColor(1));
		priorityLabel.setFont(Theme.SMALLER_FONT);
		priorityLabel.setPreferredSize(new Dimension(200,30));
		GridBagConstraints gbc_priorityLabel = new GridBagConstraints();
		gbc_priorityLabel.insets = new Insets(0, 0, 5, 5);
		gbc_priorityLabel.anchor = GridBagConstraints.EAST;
		gbc_priorityLabel.gridx = 0;
		gbc_priorityLabel.gridy = 3;
		centerPanel.add(priorityLabel, gbc_priorityLabel);
		
		listOfPriority = new JComboBox<String>();
		for(int i=1;i<=10;i++){
			listOfPriority.addItem(i + "");
		}
		listOfPriority.setForeground(Theme.getColor(1));
		listOfPriority.setFont(Theme.SMALLER_FONT);
		listOfPriority.setPreferredSize(new Dimension(380,30));
		GridBagConstraints gbc_listOfPriority = new GridBagConstraints();
		gbc_listOfPriority.insets = new Insets(0, 0, 5, 0);
		gbc_listOfPriority.fill = GridBagConstraints.HORIZONTAL;
		gbc_listOfPriority.gridx = 1;
		gbc_listOfPriority.gridy = 3;
		centerPanel.add(listOfPriority, gbc_listOfPriority);
		
		JLabel messageLabel = new JLabel(CommonString.NOIDUNG + ": ", JLabel.RIGHT);
		messageLabel.setForeground(Theme.getColor(1));
		messageLabel.setFont(Theme.SMALLER_FONT);
		messageLabel.setPreferredSize(new Dimension(200,30));
		GridBagConstraints gbc_messageLabel = new GridBagConstraints();
		gbc_messageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_messageLabel.anchor = GridBagConstraints.EAST;
		gbc_messageLabel.gridx = 0;
		gbc_messageLabel.gridy = 4;
		centerPanel.add(messageLabel, gbc_messageLabel);
		
		messageTextArea = new JTextArea();
		messageTextArea.setForeground(Theme.getColor(1));
		messageTextArea.setFont(Theme.SMALLER_FONT);
		messageTextArea.setLineWrap(true);
		messageTextArea.setWrapStyleWord(true);
//		messageTextArea.setPreferredSize(new Dimension(300,100));
		GridBagConstraints gbc_messageTextArea = new GridBagConstraints();
		gbc_messageTextArea.insets = new Insets(0, 0, 5, 0);
		gbc_messageTextArea.fill = GridBagConstraints.BOTH;
		gbc_messageTextArea.gridx = 1;
		gbc_messageTextArea.gridy = 4;
		centerPanel.add(messageTextArea, gbc_messageTextArea);
		
	}
	
	public void addListOfDev(ArrayList<Developer> list){
		if (listOfDev!=null) {
			for (Developer developer : list) {
				listOfDev.addItem(developer.getName());
			}
		}
	}
	
	public void addListOfTest(ArrayList<Tester> list){
		if (listOfDev!=null) {
			for (Tester tester : list) {
				listOfTest.addItem(tester.getName());
			}
		}
	}
	
	public int getSelectedDev(){
		return listOfDev.getSelectedIndex();
	}
	
	public int getSelectedTest(){
		return listOfTest.getSelectedIndex();
	}
	
	public int getSelectedPriority(){
		return listOfPriority.getSelectedIndex() + 1;
	}
	
	public String getMessage(){
		return messageTextArea.getText();
	}

}
