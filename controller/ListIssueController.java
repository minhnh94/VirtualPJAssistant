package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Developer;
import model.Employee;
import model.Issue;
import model.Issue.ISSUE_STATUS;
import model.Manager;
import model.Project;
import view.IssueDetail;
import view.ListIssue;

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
				IssueDetail issueDetail = new IssueDetail(listIssueView.getSelectedIssue(), currentEmployee);

				Issue currentIssue = listIssueView.getSelectedIssue();

				String currentMessage = issueDetail.getMessage();

				// Reload button action
				issueDetail.addReloadButtonActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						issueDetail.setMessage(currentMessage);
					}
				});

				// Save message to db
				issueDetail.addSaveButtonActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// Protect user from editing others' issue
						if (currentIssue.getAssignee().getId() != currentEmployee.getId()
								&& currentIssue.getReporter().getId() != currentEmployee.getId()
								&& !(currentEmployee instanceof Manager)) {
							JOptionPane.showMessageDialog(null, "You are not allowed to edit issues that's not yours.");
							return;
						}
						currentIssue.setDescription(issueDetail.getMessage());
						currentIssue.setUnread(true);
						currentProject.editIssue(currentIssue);

						JOptionPane.showMessageDialog(null, "Message saved to database.");
					}
				});

				// Send button action
				issueDetail.addSendButtonActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// Protect user from editing others' issue
						if (currentIssue.getAssignee().getId() != currentEmployee.getId()
								&& currentIssue.getReporter().getId() != currentEmployee.getId()
								&& !(currentEmployee instanceof Manager)) {
							JOptionPane.showMessageDialog(null, "You are not allowed to edit issues that's not yours.");
							return;
						}
						currentIssue.setDescription(issueDetail.getMessage());
						currentIssue.setUnread(true);

						if (currentEmployee instanceof Developer) {
							currentIssue.setStatus(ISSUE_STATUS.CHECKING);
						} else {
							currentIssue.setStatus(ISSUE_STATUS.NEW);
						}

						if (currentEmployee.getId() == currentIssue.getAssignee().getId()) {
							Employee temp = currentIssue.getAssignee();
							currentIssue.setAssignee(currentIssue.getReporter());
							currentIssue.setReporter(temp);
						}

						currentProject.editIssue(currentIssue);
						JOptionPane.showMessageDialog(null, "Sent successfully");
						listIssueView.refreshTable(currentProject);
						issueDetail.dispose();
					}
				});

				issueDetail.setVisible(true);

			}
		});

		listIssueView.setVisible(true);
	}
}
