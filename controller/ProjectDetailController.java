package controller;

import view.ProjectDetail;
import model.Employee;
import model.Manager;
import model.Project;

public class ProjectDetailController {
	private Employee currentEmployee;
	private Project currentProject;
	private ProjectDetail projectDetailView;

	public ProjectDetailController(Employee currentEmployee, Project currentProject) {
		this.currentEmployee = currentEmployee;
		this.currentProject = currentProject;
		projectDetailView = new ProjectDetail(currentProject, currentEmployee instanceof Manager);
		projectDetailView.setVisible(true);
	}
}
