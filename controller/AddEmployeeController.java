package controller;

import helper.DatabaseHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Developer;
import model.Employee;
import model.Project;
import model.Tester;
import view.AddEmployee;
import view.ProjectDetail;

public class AddEmployeeController {
	private AddEmployee addEmployeeView;

	public AddEmployeeController(boolean isAddingDev, Project currentProject, ProjectDetail delegate) {
		addEmployeeView = new AddEmployee(isAddingDev);

		// Adding confirm button action
		addEmployeeView.addConfirmButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isAddingDev) {
					Developer addedDev = DatabaseHelper.getAllDevelopersFromDatabase().get(addEmployeeView.getSelectedListIndex());
					ArrayList<Employee> arrayList = new ArrayList<Employee>();
					arrayList.add(addedDev);
					try {
						currentProject.addEmployeeToProject(arrayList);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
						return;
					}

					// Refresh the previous view
					delegate.setListOfDev(currentProject.getCurrentDevs());
					delegate.setListOfTest(currentProject.getCurrentTesters());

					JOptionPane.showMessageDialog(null, "Add developer successfully!");
				} else {
					Tester addedTester = DatabaseHelper.getAllTestersFromDatabase().get(addEmployeeView.getSelectedListIndex());
					ArrayList<Employee> arrayList = new ArrayList<Employee>();
					arrayList.add(addedTester);
					try {
						currentProject.addEmployeeToProject(arrayList);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
						return;
					}

					// Refresh the previous view
					delegate.setListOfDev(currentProject.getCurrentDevs());
					delegate.setListOfTest(currentProject.getCurrentTesters());

					JOptionPane.showMessageDialog(null, "Add tester successfully!");
				}
			}
		});

		addEmployeeView.setVisible(true);
	}
}
