package controller;

import helper.DatabaseHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import model.Employee;
import model.Issue;
import model.Project;
import model.Issue.ISSUE_STATUS;
import view.ListIssue;
import view.NewIssue;

public class NewIssueController {
	private NewIssue newIssueView;

	public NewIssueController(Project currentProject, ListIssue delegate) {
		newIssueView = new NewIssue(currentProject);
		newIssueView.addListOfDev(currentProject.getCurrentDevs());
		newIssueView.addListOfTest(currentProject.getCurrentTesters());

		// Submit button action
		newIssueView.addSubmitButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = newIssueView.getIssueName();
				String desc = newIssueView.getMessage();
				Employee reporter = currentProject.getCurrentTesters().get(newIssueView.getSelectedTest());
				Employee assignee = currentProject.getCurrentDevs().get(newIssueView.getSelectedDev());
				int priority = newIssueView.getSelectedPriority();

				if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "Issue must have a name");
					return;
				}

				Issue issueCreated = new Issue(0, currentProject, name, desc, reporter, assignee, ISSUE_STATUS.NEW, priority, true);
				currentProject.createIssue(issueCreated);

				// Make content to create history
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				String history = "[" + df.format(new Date()) + "]\n";
				history += "Reporter: " + reporter.getName() + "\n";
				history += "Assignee: " + assignee.getName() + "\n";
				history += "Content: " + desc + "\n\n";
				DatabaseHelper.writeToIssueHistoryFile(name, history);

				JOptionPane.showMessageDialog(null, "Issue created successfully.");
				delegate.refreshTable(currentProject);
				newIssueView.dispose();
			}
		});

		newIssueView.setVisible(true);
	}
}
