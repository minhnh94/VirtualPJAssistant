package controller;

import helper.DatabaseHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import model.Developer;
import model.Employee;
import model.Issue;
import model.Manager;
import model.Issue.ISSUE_STATUS;
import model.Project;
import view.IssueDetail;
import view.ListIssue;
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

		mainView.setNotiLabelMouseAdapter(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListIssue listIssue = new ListIssue(currentEmployee, true);

				listIssue.addViewButtonActionListerner(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						IssueDetail issueDetail = new IssueDetail(listIssue.getSelectedIssue(), currentEmployee);

						Issue currentIssue = listIssue.getSelectedIssue();

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

								Project currentProject = DatabaseHelper.getProjectFromPjId(currentIssue.getIncludedProject().getId());
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

								Project currentProject = DatabaseHelper.getProjectFromPjId(currentIssue.getIncludedProject().getId());
								currentProject.editIssue(currentIssue);
								JOptionPane.showMessageDialog(null, "Sent successfully");
								listIssue.refreshTable(currentProject);
								issueDetail.dispose();
							}
						});

						issueDetail.setVisible(true);
					}
				});

				listIssue.setVisible(true);
			}
		});
	}

	public Employee getCurrentEmployee() {
		return currentEmployee;
	}

	public void setCurrentEmployee(Employee currentEmployee) {
		this.currentEmployee = currentEmployee;
	}
}
