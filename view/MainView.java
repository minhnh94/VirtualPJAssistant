package view;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.WindowConstants;
import javax.swing.JLabel;

import model.Employee;
import model.Manager;
import model.Project;
import model.Project.PJ_STATUS;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel notiLabel;
	private ImageButton pro5Button;
	private JScrollPane projectPane;
	private JList<JPanel> projectList;
	private DefaultListModel<JPanel> projectListModel;
	private PanelListCellRenderer projectRenderer;
	private ImageButton addButton;
	
	private boolean isManager;
	
	public MainView(Employee employee) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(CommonString.MAINICON)));
		this.setResizable(false);
		this.setTitle(CommonString.TITLE);
		this.setBounds((dim.width-600)/2, 10, 600, 410);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		ImagePanel mainPanel = new ImagePanel(CommonString.MAINBG);
		mainPanel.setLayout(new BorderLayout());
		
		this.setContentPane(mainPanel);
		
		isManager = (employee instanceof Manager);
		
		JPanel notiPanel = new JPanel();
		notiPanel.setOpaque(false);
		notiPanel.setLayout(new BorderLayout());
		mainPanel.add(notiPanel, BorderLayout.NORTH);
		
		String hello1 = CommonString.XINCHAO + ", " + employee.getName() + "!";
		String hello2 = CommonString.BANCO + " " + employee.getNewAssignedIssueAsNotification().size()
				+ " " + CommonString.THONGBAOMOI;
		
		JPanel leftNotiPanel = new JPanel(new FlowLayout());
		leftNotiPanel.setPreferredSize(new Dimension(370, 100));
		leftNotiPanel.setOpaque(false);
		notiPanel.add(leftNotiPanel, BorderLayout.CENTER);
		
		JLabel nameLabel = new JLabel(hello1, JLabel.CENTER);
		nameLabel.setFont(Theme.SMALLER_FONT);
		nameLabel.setOpaque(false);
		nameLabel.setForeground(Theme.getColor(0));
		leftNotiPanel.add(nameLabel);
		
		notiLabel = new JLabel(hello2, JLabel.CENTER);
		notiLabel.setFont(Theme.SMALLER_FONT);
		notiLabel.setOpaque(false);
		notiLabel.setForeground(Theme.getColor(0));
		leftNotiPanel.add(notiLabel);
		
		ImageLabel notiPic = new ImageLabel(CommonString.NOTIPIC, 100, 100);
		notiPanel.add(notiPic, BorderLayout.EAST);
		
		pro5Button = new ImageButton(CommonString.PRO5, CommonString.PRO5);
		pro5Button.setPreferredSize(new Dimension(100, 100));
		notiPanel.add(pro5Button, BorderLayout.WEST);
		
		projectPane = new JScrollPane();
		projectPane.setOpaque(false);
		projectPane.setBounds(10, 330, 750, 170);
		mainPanel.add(projectPane, BorderLayout.CENTER);
		
		projectList = new JList<JPanel>();
		projectList.setOpaque(false);
		projectListModel = new DefaultListModel<JPanel>();
		projectList.setModel(projectListModel);
		projectList.setLayoutOrientation(JList.VERTICAL_WRAP);
		projectList.setOpaque(false);
		projectList.setVisibleRowCount(2);
		projectPane.setViewportView(projectList);
		projectPane.getViewport().setOpaque(false);
		
		addButton = new ImageButton(CommonString.ADDBTT, CommonString.ADDBTTH);
		addButton.setOpaque(false);
		
		setListOfProject(employee.getOngoingProjects(), employee.getFinishedProjects());
//		setListOfTestProject();

	}
	
	private JProgressBar getProgressBar(int value){
		JProgressBar progressBar = new JProgressBar();
		int tempValue = (int) Math.floor(value/10);
		switch (tempValue) {
			case 0:
				progressBar.setForeground(Theme.getProbarColor(0));
				break;
			case 1:
				progressBar.setForeground(Theme.getProbarColor(1));
				break;
			case 2:
				progressBar.setForeground(Theme.getProbarColor(2));
				break;
			case 3:
				progressBar.setForeground(Theme.getProbarColor(3));
				break;
			case 4:
				progressBar.setForeground(Theme.getProbarColor(4));
				break;
			case 5:
				progressBar.setForeground(Theme.getProbarColor(5));
				break;
			case 6:
				progressBar.setForeground(Theme.getProbarColor(6));
				break;
			case 7:
				progressBar.setForeground(Theme.getProbarColor(7));
				break;
			case 8:
				progressBar.setForeground(Theme.getProbarColor(8));
				break;
			case 9:
				progressBar.setForeground(Theme.getProbarColor(9));
				break;
			default:
				progressBar.setForeground(Theme.getProbarColor(9));
				break;
		}
		progressBar.setValue(value);
		progressBar.setStringPainted(true);
		progressBar.setString(value + "%");
		return progressBar;
	}
	
	private JPanel getProjectPanel(Project project) {
		JPanel projectPanel = new JPanel();
		projectPanel.setPreferredSize(new Dimension(275, 100));
		projectPanel.setOpaque(false);
		projectPanel.setLayout(null);

		int projectStatus = 2;
		if(project.getStatus()==PJ_STATUS.ONGOING){
			if(project.isUnreadIssueAvailable()){
				projectStatus=1;
			} else {
				projectStatus=0;
			}
		}
		
		if(projectStatus==1){
			JLabel unreadLabel = new JLabel(project.getTotalUnreadIssue() + "", JLabel.CENTER); //khi nao co so unread issues cua pj do thi them vao sau
			unreadLabel.setForeground(Theme.getColor(0));
			unreadLabel.setBounds(10, 10, 35, 35);
			unreadLabel.setOpaque(false);
			unreadLabel.setFont(Theme.SMALLER_FONT);
			projectPanel.add(unreadLabel);
			
			ImageLabel unreadImage = new ImageLabel(CommonString.UNREADBADGE, 35, 35);
			unreadImage.setBounds(10, 10, 35, 35);
			unreadImage.setOpaque(false);
			projectPanel.add(unreadImage);
		}
		
		ImageLabel folderLabel = new ImageLabel(CommonString.FOLDERICON2[projectStatus], 100, 81);
		folderLabel.setBounds(20, 10, 100, 81);
		projectPanel.add(folderLabel);
		
		JLabel progressLabel = new JLabel(project.getName(), JLabel.CENTER);
		progressLabel.setBounds(120, 15, 150, 30);
		progressLabel.setOpaque(false);
		progressLabel.setFont(Theme.SMALLER_FONT);
		progressLabel.setForeground(Theme.getColor(0));
		projectPanel.add(progressLabel);
		
		int value = project.getProjectProgress();
		
		JProgressBar progressBar = getProgressBar(value);
		progressBar.setBounds(120, 50, 150, 20);
		projectPanel.add(progressBar);
		
		return projectPanel;
	}
	
	public void setListOfProject(List<Project> projects1, List<Project> projects2) {
		projectListModel.clear();
		projectRenderer = new PanelListCellRenderer();
		projectList.setCellRenderer(projectRenderer);
		
		if(isManager){
			addButton.setBounds(0, 10, 270, 100);
			JPanel addPanel = new JPanel();
			addPanel.setOpaque(false);
			addPanel.add(addButton);
			projectListModel.addElement(addPanel);
		}
		
		for (Project project : projects1) {
			projectListModel.addElement(getProjectPanel(project));
		}
		for (Project project : projects2) {
			projectListModel.addElement(getProjectPanel(project));
		}
	}
	
//	private JPanel getTestProjectPanel(int stt, String name, int vl, int unread){
//		JPanel projectPanel = new JPanel();
//		projectPanel.setPreferredSize(new Dimension(275, 100));
//		projectPanel.setOpaque(false);
//		projectPanel.setLayout(null);
//		
//		int projectStatus = stt;
//		
//		if(stt==1){
//			JLabel unreadLabel = new JLabel(unread + "", JLabel.CENTER);
//			unreadLabel.setForeground(Theme.getColor(0));
//			unreadLabel.setBounds(10, 10, 35, 35);
//			unreadLabel.setOpaque(false);
//			unreadLabel.setFont(Theme.SMALLER_FONT);
//			projectPanel.add(unreadLabel);
//			
//			ImageLabel unreadImage = new ImageLabel(CommonString.UNREADBADGE, 35, 35);
//			unreadImage.setBounds(10, 10, 35, 35);
//			unreadImage.setOpaque(false);
//			projectPanel.add(unreadImage);
//		}
//		
//		ImageLabel folderLabel = new ImageLabel(CommonString.FOLDERICON2[projectStatus], 100, 81);
//		folderLabel.setBounds(20, 10, 100, 81);
//		projectPanel.add(folderLabel);
//		
//		JLabel progressLabel = new JLabel(name, JLabel.CENTER);
//		progressLabel.setBounds(120, 15, 150, 30);
//		progressLabel.setOpaque(false);
//		progressLabel.setFont(Theme.SMALLER_FONT);
//		progressLabel.setForeground(Theme.getColor(0));
//		projectPanel.add(progressLabel);
//		
//		int value = vl;
//		
//		JProgressBar progressBar = getProgressBar(value);
//		progressBar.setBounds(120, 50, 150, 20);
//		projectPanel.add(progressBar);
//		
//		return projectPanel;
//	}
//	
//	private void setListOfTestProject() {
//		projectListModel.clear();
//		projectRenderer = new PanelListCellRenderer();
//		projectList.setCellRenderer(projectRenderer);
//		Random ran = new Random();
//		
//		for (int i = 0; i < 100; i++) {
//			int stt = ran.nextInt(3);
//			int vl = ran.nextInt(101);
//			int un = ran.nextInt(5);
//			projectListModel.addElement(getTestProjectPanel(stt, "Project " + i, vl, un));
//		}
//
//		if(isManager){
//			addButton.setBounds(0, 10, 270, 100);
//			JPanel addPanel = new JPanel();
//			addPanel.setOpaque(false);
//			addPanel.add(addButton);
//			projectListModel.addElement(addPanel);
//		}
//	}
	
	class PanelListCellRenderer implements ListCellRenderer<JPanel> {

		@Override
		public Component getListCellRendererComponent(JList<? extends JPanel> list, JPanel value, int index, boolean isSelected, boolean cellHasFocus) {
			if (value != null) {
				if (isSelected) {
					value.setOpaque(true);
					value.setBackground(Theme.getColor(1));
				} else {
					value.setOpaque(false);
				}
				return value;
			} else {
				return new JLabel("???");
			}
		}
	}
	
	public void setPro5ButtonActionListerner(ActionListener listener){
		pro5Button.addActionListener(listener);
	}
	
	public void setNotiLabelMouseAdapter(MouseAdapter adapter){
		notiLabel.addMouseListener(adapter);
	}
	
	public int getSelectedProjectIndex(){
		return projectList.getSelectedIndex();
	}
	
	public void addProjectListMouseListener(MouseListener listener){
		projectList.addMouseListener(listener);
	}
}
