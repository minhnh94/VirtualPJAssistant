package view;

import helper.DatabaseHelper;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.ListCellRenderer;
import javax.swing.WindowConstants;

import model.Developer;
import model.Employee;
import model.Tester;

public class AddEmployee extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private JList<JPanel> employeeList;
	private DefaultListModel<JPanel> employeeListModel;
	private PanelListCellRenderer employeeRenderer;
	
	private ImageButton confirmButton;

	public AddEmployee(boolean isAddingDev) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setResizable(false);
		this.setTitle(CommonString.ADDEMPLOYEE);
		this.setBounds((dim.width-400)/2, 10, 400, 510);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		PatternPanel pjPanel = new PatternPanel(CommonString.ISSBG);
		pjPanel.setLayout(new BorderLayout());
		this.setContentPane(pjPanel);
		
		String title;
		if(isAddingDev){
			title = CommonString.ADD + " " + ObjectPropertyString.DEV2;
		} else {
			title = CommonString.ADD + " " + ObjectPropertyString.TEST2;
		}
		
		JLabel pjName = new JLabel(title, JLabel.CENTER);
		pjName.setOpaque(false);
		pjName.setPreferredSize(new Dimension(380, 50));
		pjName.setFont(Theme.BIGGER_FONT);
		pjName.setForeground(Theme.getColor(1));
		
		pjPanel.add(pjName, BorderLayout.NORTH);
		
		JScrollPane employeeScrollPane = new JScrollPane();
		employeeScrollPane.setOpaque(false);
		pjPanel.add(employeeScrollPane, BorderLayout.CENTER);
		
		employeeListModel = new DefaultListModel<JPanel>();
		
		employeeList = new JList<JPanel>();
		employeeList.setOpaque(false);
		employeeList.setModel(employeeListModel);
		employeeList.setLayoutOrientation(JList.VERTICAL_WRAP);
		
		employeeScrollPane.setViewportView(employeeList);
		employeeScrollPane.getViewport().setOpaque(false);
		pjPanel.add(employeeScrollPane, BorderLayout.CENTER);
		
		if(isAddingDev){
			setListOfDev(DatabaseHelper.getAllDevelopersFromDatabase());
		} else {
			setListOfTest(DatabaseHelper.getAllTestersFromDatabase());
		}
		
		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setOpaque(false);
		pjPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		confirmButton = new ImageButton(CommonString.CONFIRM, CommonString.CONFIRMH);
		confirmButton.setPreferredSize(new Dimension(200, 50));
		bottomPanel.add(confirmButton);
		
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
		employeePanel.setPreferredSize(new Dimension(375, 60));
		employeePanel.setOpaque(false);
		employeePanel.setLayout(new FlowLayout());
		
		JLabel nameLabel = new JLabel(employee.getName(), JLabel.CENTER);
		nameLabel.setFont(Theme.SMALLER_FONT);
		nameLabel.setForeground(Theme.getColor(1));
		nameLabel.setPreferredSize(new Dimension(375,25));
		nameLabel.setOpaque(false);
		employeePanel.add(nameLabel);
		
		JLabel emailLabel = new JLabel(employee.getEmail(), JLabel.CENTER);
		emailLabel.setFont(Theme.EXTRA_SMALLER_FONT);
		emailLabel.setForeground(Theme.getColor(1));
		emailLabel.setPreferredSize(new Dimension(375,25));
		emailLabel.setOpaque(false);
		employeePanel.add(emailLabel);
		
		return employeePanel;
	}
	
//	public void setListOfEmployee(ArrayList<Employee> employees) {
//		employeeListModel.clear();
//		employeeRenderer = new PanelListCellRenderer();
//		employeeList.setCellRenderer(employeeRenderer);
//		
//		for (Employee employee : employees) {
//			employeeListModel.addElement(getEmployeePanel(employee));
//		}
//	}
	
	public void setListOfDev(ArrayList<Developer> developers) {
		employeeListModel.clear();
		employeeRenderer = new PanelListCellRenderer();
		employeeList.setCellRenderer(employeeRenderer);
		
		for (Developer developer : developers) {
			employeeListModel.addElement(getEmployeePanel(developer));
		}
	}
	
	public void setListOfTest(ArrayList<Tester> testers) {
		employeeListModel.clear();
		employeeRenderer = new PanelListCellRenderer();
		employeeList.setCellRenderer(employeeRenderer);
		
		for (Tester tester : testers) {
			employeeListModel.addElement(getEmployeePanel(tester));
		}
	}
	
	public void addEmployeeListMouseListener(MouseListener listener){
		employeeList.addMouseListener(listener);
	}
	
	public int getSelectedListIndex(){
		return employeeList.getSelectedIndex();
	}
	
	public void addConfirmButtonActionListener(ActionListener listener){
		confirmButton.addActionListener(listener);
	}

}
