package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import model.Developer;
import model.Employee;
import model.Tester;

public class UserDetail extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private ImageButton editpro5;

	public UserDetail(Employee e, boolean isOwner) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setResizable(false);
		this.setTitle(e.getName());
		if(isOwner){
			this.setBounds((dim.width-650)/2, 10, 650, 300);
		} else {
			this.setBounds((dim.width-650)/2, 10, 650, 230);
		}
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		PatternPanel userPanel = new PatternPanel(CommonString.ISSBG);
		userPanel.setLayout(new BorderLayout());
		this.setContentPane(userPanel);
		
		JPanel leftPanel = new JPanel(new FlowLayout());
		leftPanel.setOpaque(false);
		userPanel.add(leftPanel, BorderLayout.WEST);
		
		int type = 0;
		if(e instanceof Developer){
			type = 1;
		}
		if(e instanceof Tester){
			type = 2;
		}
		
		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		userPanel.add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0, 0, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		
		JLabel eNameLabel = new JLabel(CommonString.USERNAME + ": ", JLabel.RIGHT);
		eNameLabel.setForeground(Theme.getColor(1));
		eNameLabel.setFont(Theme.SMALLER_FONT);
		eNameLabel.setPreferredSize(new Dimension(150,30));
		GridBagConstraints gbc_eNameLabel = new GridBagConstraints();
		gbc_eNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_eNameLabel.anchor = GridBagConstraints.EAST;
		gbc_eNameLabel.gridx = 0;
		gbc_eNameLabel.gridy = 0;
		centerPanel.add(eNameLabel, gbc_eNameLabel);
		
		JLabel eName = new JLabel(e.getName());
		eName.setForeground(Theme.getColor(1));
		eName.setFont(Theme.SMALLER_FONT);
		eName.setPreferredSize(new Dimension(250,30));
		GridBagConstraints gbc_eName = new GridBagConstraints();
		gbc_eName.insets = new Insets(0, 0, 5, 0);
		gbc_eName.fill = GridBagConstraints.HORIZONTAL;
		gbc_eName.gridx = 1;
		gbc_eName.gridy = 0;
		centerPanel.add(eName, gbc_eName);
		
		JLabel jobLabel = new JLabel(CommonString.JOB + ": ", JLabel.RIGHT);
		jobLabel.setForeground(Theme.getColor(1));
		jobLabel.setFont(Theme.SMALLER_FONT);
		jobLabel.setPreferredSize(new Dimension(150,30));
		GridBagConstraints gbc_jobLabel = new GridBagConstraints();
		gbc_jobLabel.insets = new Insets(0, 0, 5, 5);
		gbc_jobLabel.anchor = GridBagConstraints.EAST;
		gbc_jobLabel.gridx = 0;
		gbc_jobLabel.gridy = 1;
		centerPanel.add(jobLabel, gbc_jobLabel);
		
		JLabel job = new JLabel("");
		if(type==0){
			job.setText(ObjectPropertyString.PM);
		} else {
			if(type==1){
				job.setText(ObjectPropertyString.DEV);
			} else {
				job.setText(ObjectPropertyString.TEST);
			}
		}
		
		job.setForeground(Theme.getColor(1));
		job.setFont(Theme.SMALLER_FONT);
		job.setPreferredSize(new Dimension(250,30));
		GridBagConstraints gbc_job = new GridBagConstraints();
		gbc_job.insets = new Insets(0, 0, 5, 0);
		gbc_job.fill = GridBagConstraints.HORIZONTAL;
		gbc_job.gridx = 1;
		gbc_job.gridy = 1;
		centerPanel.add(job, gbc_job);
		
		JLabel emailLabel = new JLabel("Email: ", JLabel.RIGHT);
		emailLabel.setForeground(Theme.getColor(1));
		emailLabel.setFont(Theme.SMALLER_FONT);
		emailLabel.setPreferredSize(new Dimension(150,30));
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.anchor = GridBagConstraints.EAST;
		gbc_emailLabel.gridx = 0;
		gbc_emailLabel.gridy = 2;
		centerPanel.add(emailLabel, gbc_emailLabel);
		
		JLabel email = new JLabel(e.getEmail());
		email.setForeground(Theme.getColor(1));
		email.setFont(Theme.SMALLER_FONT);
		email.setPreferredSize(new Dimension(250,30));
		GridBagConstraints gbc_email = new GridBagConstraints();
		gbc_email.insets = new Insets(0, 0, 5, 0);
		gbc_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_email.gridx = 1;
		gbc_email.gridy = 2;
		centerPanel.add(email, gbc_email);
		
		JLabel telLabel = new JLabel(CommonString.TEL + ": ", JLabel.RIGHT);
		telLabel.setForeground(Theme.getColor(1));
		telLabel.setFont(Theme.SMALLER_FONT);
		telLabel.setPreferredSize(new Dimension(150,30));
		GridBagConstraints gbc_telLabel = new GridBagConstraints();
		gbc_telLabel.insets = new Insets(0, 0, 5, 5);
		gbc_telLabel.anchor = GridBagConstraints.EAST;
		gbc_telLabel.gridx = 0;
		gbc_telLabel.gridy = 3;
		centerPanel.add(telLabel, gbc_telLabel);
		
		JLabel tel = new JLabel(e.getTel());
		tel.setForeground(Theme.getColor(1));
		tel.setFont(Theme.SMALLER_FONT);
		tel.setPreferredSize(new Dimension(250,30));
		GridBagConstraints gbc_tel = new GridBagConstraints();
		gbc_tel.insets = new Insets(0, 0, 5, 0);
		gbc_tel.fill = GridBagConstraints.HORIZONTAL;
		gbc_tel.gridx = 1;
		gbc_tel.gridy = 3;
		centerPanel.add(tel, gbc_tel);
		
		ImageLabel icon = new ImageLabel(CommonString.EMICON[type],200, 200);
		icon.setOpaque(false);
		leftPanel.add(icon);
		
		editpro5 = new ImageButton(CommonString.EDITPR5, CommonString.EDITPR5H);
		editpro5.setPreferredSize(new Dimension(200, 50));
		if(isOwner){
			JPanel bottomPanel = new JPanel(new FlowLayout());
			bottomPanel.setOpaque(false);
			userPanel.add(bottomPanel, BorderLayout.SOUTH);
			
			bottomPanel.add(editpro5);
		}
		
	}
	
	public void addEditProfileButtonActionListener(ActionListener listener){
		editpro5.addActionListener(listener);
	}

}
