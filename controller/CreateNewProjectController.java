package controller;

import helper.DatabaseHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import view.MainView;
import view.NewProject;
import model.Employee;
import model.Manager;
import model.Project;
import model.Project.PJ_STATUS;

public class CreateNewProjectController {
	private NewProject newProjectView;

	public CreateNewProjectController(Employee currentEmployee, MainView delegate) {
		newProjectView = new NewProject();
		newProjectView.addCreatePjButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String pjName = newProjectView.getPjNameFromTextField();
				String pjDesc = newProjectView.getDescriptionFromTextArea();

				// Get current datetime
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String openDay = dateFormat.format(new Date());
				String estimateDay = newProjectView.getEstimatedCloseDateAsString();

				// Create project
				Project newProject = new Project(0, pjName, pjDesc, openDay, "", estimateDay, PJ_STATUS.ONGOING, (Manager) currentEmployee);

				// Insert new PJ into db
				Manager manager = (Manager) currentEmployee;
				manager.addNewProject(newProject);

				// Add the manager as the employee of this
				// project. Method only accepts ArrayList, so we create one
				ArrayList<Employee> arrayList = new ArrayList<Employee>();
				arrayList.add(currentEmployee);
				newProject.setId(DatabaseHelper.getLastRowIdFromTable("Project"));
				newProject.addEmployeeToProject(arrayList);

				JOptionPane.showMessageDialog(null, "New project added successfully", "Add project success", JOptionPane.INFORMATION_MESSAGE);

				// Refresh the main view so it updates the recently added pj
				delegate.setListOfProject(currentEmployee.getOngoingProjects(), currentEmployee.getFinishedProjects());
			}
		});

		newProjectView.setVisible(true);
	}
}
