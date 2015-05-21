package controller;

import helper.DatabaseHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.LogIn;
import view.Register;
import model.Employee;
import model.LoginException;

public class LoginController {
	private Employee currentLoginEmployee;
	private LogIn loginView;
	private Register registerView;

	public LoginController() {
		loginView = new LogIn();

		loginView.setLogInButtonListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = loginView.getName();
				String password = loginView.getPass();
				try {
					currentLoginEmployee = DatabaseHelper.loginEmployee(username, password);
					loginView.dispose();
					new MainViewController(currentLoginEmployee);
				} catch (LoginException e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "Login error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		loginView.setRegButtonListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				registerView = new Register();

				registerView.setRegokButtonActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (!registerView.getPass().equals(registerView.getRePass())) {
							JOptionPane.showMessageDialog(null, "Confirm password and password do not match", "Register error", JOptionPane.INFORMATION_MESSAGE);
						} else if (registerView.getName().equals("")
								|| registerView.getPass().equals("")) {
							JOptionPane.showMessageDialog(null, "Fill in all the mandatory (*) fields", "Register error", JOptionPane.INFORMATION_MESSAGE);
						} else {
							boolean isRegisteredSuccessfully = DatabaseHelper.registerEmployee(registerView.getName(), registerView.getPass(), registerView.getTel(), registerView.getEmail(), registerView.getAccountType());
							if (isRegisteredSuccessfully) {
								JOptionPane.showMessageDialog(null, "Register successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
								registerView.dispose();
							} else {
								JOptionPane.showMessageDialog(null, "This username exists. Please choose others", "Register error", JOptionPane.INFORMATION_MESSAGE);
							}

						}
					}
				});

				registerView.setVisible(true);
			}
		});

		loginView.setVisible(true);
	}
}
