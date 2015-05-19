package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Register extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JTextField nameTextField;
	private JPasswordField passTextField;
	private JPasswordField repassTextField;
	private JTextField telTextField;
	private JTextField emailTextField;
	private JComboBox<String> accComboBox;
	private ImageButton regokButton;

	public Register() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setResizable(false);
		this.setTitle(CommonString.REGTITLE);
		this.setBounds((dim.width-300)/2, 10, 300, 570);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		ImagePanel mainPanel = new ImagePanel(CommonString.REGBG);
		
		this.setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		ImageLabel nameLabel = new ImageLabel(CommonString.REGNAMELABEL, 235, 30);
		nameLabel.setBounds(37, 60, 235, 30);
		mainPanel.add(nameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(37, 90, 235, 30);
		nameTextField.setHorizontalAlignment(JTextField.RIGHT);
		mainPanel.add(nameTextField);
		
		ImageLabel passLabel = new ImageLabel(CommonString.REGPASSLABEL, 235, 30);
		passLabel.setBounds(37, 120, 235, 30);
		mainPanel.add(passLabel);
		
		passTextField = new JPasswordField();
		passTextField.setBounds(37, 150, 235, 30);
		passTextField.setHorizontalAlignment(JTextField.RIGHT);
		mainPanel.add(passTextField);
		
		ImageLabel repassLabel = new ImageLabel(CommonString.REGREPASSLABEL, 235, 30);
		repassLabel.setBounds(37, 180, 235, 30);
		mainPanel.add(repassLabel);
		
		repassTextField = new JPasswordField();
		repassTextField.setBounds(37, 210, 235, 30);
		repassTextField.setHorizontalAlignment(JTextField.RIGHT);
		mainPanel.add(repassTextField);
		
		ImageLabel telLabel = new ImageLabel(CommonString.REGTELLABEL, 235, 30);
		telLabel.setBounds(37, 240, 235, 30);
		mainPanel.add(telLabel);
		
		telTextField = new JTextField();
		telTextField.setBounds(37, 270, 235, 30);
		telTextField.setHorizontalAlignment(JTextField.RIGHT);
		mainPanel.add(telTextField);
		
		ImageLabel emailLabel = new ImageLabel(CommonString.REGEMAILLABEL, 235, 30);
		emailLabel.setBounds(37, 300, 235, 30);
		mainPanel.add(emailLabel);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(37, 330, 235, 30);
		emailTextField.setHorizontalAlignment(JTextField.RIGHT);
		mainPanel.add(emailTextField);
		
		ImageLabel accLabel = new ImageLabel(CommonString.REGACCLABEL, 235, 30);
		accLabel.setBounds(37, 360, 235, 30);
		mainPanel.add(accLabel);
		
		accComboBox = new JComboBox<String>();
		accComboBox.setBounds(37, 390, 235, 30);
		accComboBox.addItem(CommonString.ACCTYPE0);
		accComboBox.addItem(CommonString.ACCTYPE1);
		accComboBox.addItem(CommonString.ACCTYPE2);
		mainPanel.add(accComboBox);
		
		regokButton = new ImageButton(CommonString.REGBTT, CommonString.REGBTTH);
		regokButton.setBounds(93, 425, 115, 50);
		mainPanel.add(regokButton);
		
	}
	
	public String getName(){
		if(nameTextField.getText()==null){
			return "";
		} else {
			return nameTextField.getText();
		}
	}
	
	public String getPass(){
		char[] pwd = passTextField.getPassword();
		String result ="";
		for(int i=0;i<pwd.length;i++){
			result = result+pwd[i];
		}
		return result;
	}
	
	public String getRePass(){
		char[] pwd = repassTextField.getPassword();
		String result ="";
		for(int i=0;i<pwd.length;i++){
			result = result+pwd[i];
		}
		return result;
	}
	
	public String getTel(){
		if(telTextField.getText()==null){
			return "";
		} else {
			return telTextField.getText();
		}
	}
	
	public String getEmail(){
		if(emailTextField.getText()==null){
			return "";
		} else {
			return emailTextField.getText();
		}
	}
	
	public int getAccountType(){
		return accComboBox.getSelectedIndex();
	}
	
	public void setRegokButtonActionListener(ActionListener listener){
		regokButton.addActionListener(listener);
	}
}