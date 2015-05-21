package controller;

import helper.DatabaseHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import view.MainView;
import view.ProjectDetail;
import model.Developer;
import model.Employee;
import model.Manager;
import model.Project;
import model.Project.PJ_STATUS;
import model.Tester;

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

		// Delete test and dev by double clicking
		projectDetailView.addDevListMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Developer selectedDev = (Developer) currentProject.getCurrentDevs().get(projectDetailView.getSelectedDevList());
					int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove developer \""
							+ selectedDev.getName() + "\" from project?");
					if (selectedOption == 0) {
						ArrayList<Employee> arrayList = new ArrayList<Employee>();
						arrayList.add(selectedDev);
						currentProject.removeEmployeeFromProject(arrayList);
						projectDetailView.setListOfDev(currentProject.getCurrentDevs());
					}
				}
			}
		});
		projectDetailView.addTestListMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Tester selectedTester = (Tester) currentProject.getCurrentTesters().get(projectDetailView.getSelectedTesterList());
					int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove tester \""
							+ selectedTester.getName() + "\" from project?");
					if (selectedOption == 0) {
						ArrayList<Employee> arrayList = new ArrayList<Employee>();
						arrayList.add(selectedTester);
						currentProject.removeEmployeeFromProject(arrayList);
						projectDetailView.setListOfTest(currentProject.getCurrentTesters());
					}
				}
			}
		});

		// Add view issue action
		projectDetailView.addViewButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		projectDetailView.setVisible(true);
	}
}
