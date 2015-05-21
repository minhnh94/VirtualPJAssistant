package controller;

import helper.DatabaseHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Employee;
import view.EditUserProfile;
import view.UserDetail;

public class UserProfileController {

	private UserDetail view;
	private EditUserProfile editView;
	private Employee employee;
	
	public UserProfileController(Employee employee, boolean isOwner) {
		this.employee = employee;
		view = new UserDetail(employee, isOwner);
		editView = new EditUserProfile(employee);
		
		if(isOwner){
			editView.addConfirmButtonActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!editView.getPassWordField().equals((editView.getRePassWordField()))) {
						JOptionPane.showMessageDialog(null, "Confirm password and password do not match", "Register error", JOptionPane.INFORMATION_MESSAGE);
					} else {
						UserProfileController.this.employee.setPassword(editView.getPassWordField());
						UserProfileController.this.employee.setEmail(editView.getEmailField());
						UserProfileController.this.employee.setTel(editView.getTelField());
						DatabaseHelper.updateEmployeeInfo(UserProfileController.this.employee);
						JOptionPane.showMessageDialog(null, "Edit Information successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
						editView.dispose();
					}
				}
			});
			
			view.addEditProfileButtonActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					editView.setVisible(true);
					
				}
			});
		}
		
	}
	
	public void showProfileView(){
		view.setVisible(true);
	}

}
