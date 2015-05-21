package controller;

import model.Employee;
import view.MainView;

public class MainViewController {
	private MainView mainView;
	private Employee currentEmployee;

	public MainViewController(Employee employee) {
		setCurrentEmployee(employee);

		mainView = new MainView(employee);
		mainView.setVisible(true);
	}

	public Employee getCurrentEmployee() {
		return currentEmployee;
	}

	public void setCurrentEmployee(Employee currentEmployee) {
		this.currentEmployee = currentEmployee;
	}
}
