package controller;

import helper.DatabaseHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import view.MainView;
import view.ProjectDetail;
import model.Employee;
import model.Manager;
import model.Project;
import model.Project.PJ_STATUS;

public class ProjectDetailController {
	private Employee currentEmployee;
	private Project currentProject;
	private ProjectDetail projectDetailView;

	public ProjectDetailController(Employee currentEmployee, Project currentProject, MainView delegate) {
		this.currentEmployee = currentEmployee;
		this.currentProject = currentProject;
		projectDetailView = new ProjectDetail(currentProject, currentEmployee instanceof Manager);

		// Setup the detail view
		projectDetailView.addCloseButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentProject.getStatus() == PJ_STATUS.ONGOING) {
					currentProject.setStatus(PJ_STATUS.FINISHED);

					// Get current datetime
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String closeDay = dateFormat.format(new Date());
					currentProject.setCloseDay(closeDay);

					DatabaseHelper.updateProjectInfo(currentProject);
					JOptionPane.showMessageDialog(null, "Project closed!");
					delegate.setListOfProject(currentEmployee.getOngoingProjects(), currentEmployee.getFinishedProjects());
					projectDetailView.dispose();
				} else {
					currentProject.setStatus(PJ_STATUS.ONGOING);

					currentProject.setCloseDay("");

					DatabaseHelper.updateProjectInfo(currentProject);
					JOptionPane.showMessageDialog(null, "Project reopened!");
					delegate.setListOfProject(currentEmployee.getOngoingProjects(), currentEmployee.getFinishedProjects());
					projectDetailView.dispose();
				}
			}
		});

		// Setup add dev button action
		projectDetailView.addAddDevButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentProject.getStatus() == PJ_STATUS.FINISHED) {
					JOptionPane.showMessageDialog(null, "This project was already finished. Reopen it to continue developing.");
					return;
				}

				new AddEmployeeController(true, currentProject, projectDetailView);
			}
		});

		// Setup add test button action
		projectDetailView.addAddTestButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentProject.getStatus() == PJ_STATUS.FINISHED) {
					JOptionPane.showMessageDialog(null, "This project was already finished. Reopen it to continue developing.");
					return;
				}

				new AddEmployeeController(false, currentProject, projectDetailView);
			}
		});

		// TODO: syso here
		System.out.println(currentProject.getCloseDay());
		projectDetailView.setVisible(true);
	}
}
