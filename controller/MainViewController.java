package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Employee;
import model.Manager;
import view.MainView;

public class MainViewController {
	private MainView mainView;
	private Employee currentEmployee;

	public MainViewController(Employee employee) {
		setCurrentEmployee(employee);

		mainView = new MainView(employee);
		mainView.addProjectListMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					System.out.println(mainView.getSelectedProjectIndex());
					if (currentEmployee instanceof Manager) {
						if (mainView.getSelectedProjectIndex() == 0) {
							new CreateNewProjectController(currentEmployee, mainView);
						} else {
							if (mainView.getSelectedProjectIndex() - 1 < currentEmployee.getOngoingProjects().size()) {
								new ProjectDetailController(currentEmployee, currentEmployee.getOngoingProjects().get(mainView.getSelectedProjectIndex() - 1), mainView);
							} else {
								new ProjectDetailController(currentEmployee, currentEmployee.getFinishedProjects().get(mainView.getSelectedProjectIndex()
										- 1
										- currentEmployee.getOngoingProjects().size()), mainView);
							}
						}
					} else {
						// TODO: Dont allow anyone other than manager to edit
						// the PJ description
						if (mainView.getSelectedProjectIndex() < currentEmployee.getOngoingProjects().size()) {
							new ProjectDetailController(currentEmployee, currentEmployee.getOngoingProjects().get(mainView.getSelectedProjectIndex()), mainView);
						} else {
							new ProjectDetailController(currentEmployee, currentEmployee.getFinishedProjects().get(mainView.getSelectedProjectIndex()
									- currentEmployee.getOngoingProjects().size()), mainView);
						}
					}
				}
			}
		});
		UserProfileController userProfileCtrl = new UserProfileController(employee, true);
		mainView.setPro5ButtonActionListerner(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userProfileCtrl.showProfileView();
			}
		});
		mainView.setVisible(true);
	}

	public Employee getCurrentEmployee() {
		return currentEmployee;
	}

	public void setCurrentEmployee(Employee currentEmployee) {
		this.currentEmployee = currentEmployee;
	}
}
