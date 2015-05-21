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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import model.Employee;

public class EditUserProfile extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JPasswordField password;
	private JPasswordField reTypePassword;
	private JTextField email;
	private JTextField tel;
	
	private ImageButton confirmButton;

	public EditUserProfile(Employee e) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setResizable(false);
		this.setTitle(e.getName());
		this.setBounds((dim.width-520)/2, 10, 520, 270);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		PatternPanel userPanel = new PatternPanel(CommonString.ISSBG);
		userPanel.setLayout(new BorderLayout());
		this.setContentPane(userPanel);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		userPanel.add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0, 0, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		
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
		
		JLabel passwordLabel = new JLabel(CommonString.PASSWORD + ": ", JLabel.RIGHT);
		passwordLabel.setForeground(Theme.getColor(1));
		passwordLabel.setFont(Theme.SMALLER_FONT);
		passwordLabel.setPreferredSize(new Dimension(150,30));
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.anchor = GridBagConstraints.EAST;
		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 1;
		centerPanel.add(passwordLabel, gbc_passwordLabel);
		
		password = new JPasswordField(e.getPassword());
		password.setForeground(Theme.getColor(1));
		password.setFont(Theme.SMALLER_FONT);
		password.setPreferredSize(new Dimension(250,30));
		GridBagConstraints gbc_password = new GridBagConstraints();
		gbc_password.insets = new Insets(0, 0, 5, 0);
		gbc_password.fill = GridBagConstraints.HORIZONTAL;
		gbc_password.gridx = 1;
		gbc_password.gridy = 1;
		centerPanel.add(password, gbc_password);
		
		JLabel reTypePasswordLabel = new JLabel(CommonString.REPASSWORD + ": ", JLabel.RIGHT);
		reTypePasswordLabel.setForeground(Theme.getColor(1));
		reTypePasswordLabel.setFont(Theme.SMALLER_FONT);
		reTypePasswordLabel.setPreferredSize(new Dimension(150,30));
		GridBagConstraints gbc_reTypePasswordLabel = new GridBagConstraints();
		gbc_reTypePasswordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_reTypePasswordLabel.anchor = GridBagConstraints.EAST;
		gbc_reTypePasswordLabel.gridx = 0;
		gbc_reTypePasswordLabel.gridy = 2;
		centerPanel.add(reTypePasswordLabel, gbc_reTypePasswordLabel);
		
		reTypePassword = new JPasswordField(e.getPassword());
		reTypePassword.setForeground(Theme.getColor(1));
		reTypePassword.setFont(Theme.SMALLER_FONT);
		reTypePassword.setPreferredSize(new Dimension(250,30));
		GridBagConstraints gbc_reTypePassword = new GridBagConstraints();
		gbc_reTypePassword.insets = new Insets(0, 0, 5, 0);
		gbc_reTypePassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_reTypePassword.gridx = 1;
		gbc_reTypePassword.gridy = 2;
		centerPanel.add(reTypePassword, gbc_reTypePassword);
		
		JLabel emailLabel = new JLabel(CommonString.USERNAME + ": ", JLabel.RIGHT);
		emailLabel.setForeground(Theme.getColor(1));
		emailLabel.setFont(Theme.SMALLER_FONT);
		emailLabel.setPreferredSize(new Dimension(150,30));
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.anchor = GridBagConstraints.EAST;
		gbc_emailLabel.gridx = 0;
		gbc_emailLabel.gridy = 3;
		centerPanel.add(emailLabel, gbc_emailLabel);
		
		email = new JTextField(e.getEmail());
		email.setForeground(Theme.getColor(1));
		email.setFont(Theme.SMALLER_FONT);
		email.setPreferredSize(new Dimension(250,30));
		GridBagConstraints gbc_email = new GridBagConstraints();
		gbc_email.insets = new Insets(0, 0, 5, 0);
		gbc_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_email.gridx = 1;
		gbc_email.gridy = 3;
		centerPanel.add(email, gbc_email);
		
		JLabel telLabel = new JLabel(CommonString.USERNAME + ": ", JLabel.RIGHT);
		telLabel.setForeground(Theme.getColor(1));
		telLabel.setFont(Theme.SMALLER_FONT);
		telLabel.setPreferredSize(new Dimension(150,30));
		GridBagConstraints gbc_telLabel = new GridBagConstraints();
		gbc_telLabel.insets = new Insets(0, 0, 5, 5);
		gbc_telLabel.anchor = GridBagConstraints.EAST;
		gbc_telLabel.gridx = 0;
		gbc_telLabel.gridy = 0;
		centerPanel.add(telLabel, gbc_telLabel);
		
		tel = new JTextField(e.getTel());
		tel.setForeground(Theme.getColor(1));
		tel.setFont(Theme.SMALLER_FONT);
		tel.setPreferredSize(new Dimension(250,30));
		GridBagConstraints gbc_tel = new GridBagConstraints();
		gbc_tel.insets = new Insets(0, 0, 5, 0);
		gbc_tel.fill = GridBagConstraints.HORIZONTAL;
		gbc_tel.gridx = 1;
		gbc_tel.gridy = 0;
		centerPanel.add(tel, gbc_tel);
		
		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setOpaque(false);
		userPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		confirmButton = new ImageButton(CommonString.CONFIRM, CommonString.CONFIRMH);
		confirmButton.setPreferredSize(new Dimension(200, 50));
		bottomPanel.add(confirmButton);
	}
	
	public String getPassWordField(){
		String s = "";
		char c[] = password.getPassword();
		for (int i = 0; i < c.length; i++) {
			s += c[i];
		}
		return s;
	}
	
	public String getRePassWordField(){
		String s = "";
		char c[] = reTypePassword.getPassword();
		for (int i = 0; i < c.length; i++) {
			s += c[i];
		}
		return s;
	}
	
	public String getEmailField(){
		return email.getText();
	}
	
	public String getTelField(){
		return tel.getText();
	}

	public void addConfirmButtonActionListener(ActionListener listener){
		confirmButton.addActionListener(listener);
	}
}
