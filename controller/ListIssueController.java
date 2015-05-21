package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Developer;
import model.Employee;
import model.Project;
import view.ListIssue;
import view.NewIssue;

public class ListIssueController {
	private ListIssue listIssueView;

	public ListIssueController(Project currentProject, Employee currentEmployee) {
		listIssueView = new ListIssue(currentEmployee, currentProject);

		// Add new issue
		if (!(currentEmployee instanceof Developer)) {
			listIssueView.addAddButtonActionListerner(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new NewIssueController(currentProject, listIssueView);
				}
			});
		}

		// Show issue detail
		listIssueView.addViewButtonActionListerner(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		listIssueView.setVisible(true);
	}
}
