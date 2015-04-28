package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JLabel;

public class LogIn extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField nameTextField;
	private JPasswordField passTextField;
	private ImageButton logInButton;
	private ImageButton regButton;
	
	
	public LogIn() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setResizable(false);
		this.setTitle(CommonString.TITLE);
		this.setBounds((dim.width-600)/2, 10, 600, 430);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		ImagePanel mainPanel = new ImagePanel(CommonString.LOGINBG);
		
		this.setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		ImageLabel nameLabel = new ImageLabel(CommonString.NAMELABEL, 235, 30);
		nameLabel.setBounds(320, 100, 235, 30);
		mainPanel.add(nameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(320, 135,235, 30);
		nameTextField.setHorizontalAlignment(JTextField.RIGHT);
		mainPanel.add(nameTextField);
		
		ImageLabel passLabel = new ImageLabel(CommonString.PASSLABEL, 235, 30);
		passLabel.setBounds(320, 170, 235, 30);
		mainPanel.add(passLabel);
		
		passTextField = new JPasswordField();
		passTextField.setBounds(320, 205, 235, 30);
		passTextField.setHorizontalAlignment(JTextField.RIGHT);
		mainPanel.add(passTextField);
		
		logInButton = new ImageButton(CommonString.LOGINBTT, CommonString.LOGINBTTH);
		logInButton.setBounds(320, 250, 115, 50);
		mainPanel.add(logInButton);
		
		regButton = new ImageButton(CommonString.REGBTT, CommonString.REGBTTH);
		regButton.setBounds(440, 250, 115, 50);
		mainPanel.add(regButton);
	}
	
	public String getName(){
		return nameTextField.getText();
	}
	
	public String getPass(){
		char[] pwd = passTextField.getPassword();
		String result ="";
		for(int i=0;i<pwd.length;i++){
			result = result+pwd[i];
		}
		return result;
	}
	
	public void setLogInButtonListerner(ActionListener listerner){
		logInButton.addActionListener(listerner);
	}
	
	public void setRegButtonListerner(ActionListener listerner){
		regButton.addActionListener(listerner);
	}
}
