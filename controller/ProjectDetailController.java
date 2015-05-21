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
				currentProject.setStatus(PJ_STATUS.FINISHED);

				// Get current datetime
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String closeDay = dateFormat.format(new Date());
				currentProject.setCloseDay(closeDay);

				DatabaseHelper.updateProjectInfo(currentProject);
				JOptionPane.showMessageDialog(null, "Project closed!");
				delegate.setListOfProject(currentEmployee.getOngoingProjects(), currentEmployee.getFinishedProjects());
				projectDetailView.dispose();
			}
		});

		projectDetailView.setVisible(true);
	}
}
