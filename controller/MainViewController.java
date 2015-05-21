package controller;

import helper.DatabaseHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import model.Developer;
import model.Employee;
import model.Issue;
import model.Manager;
import model.Issue.ISSUE_STATUS;
import model.Project;
import view.IssueDetail;
import view.IssueHistory;
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

								// Append to file
								DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
								String history = "[" + df.format(new Date())
										+ "]\n";
								history += "Reporter: "
										+ currentIssue.getReporter().getName()
										+ "\n";
								history += "Assignee: "
										+ currentIssue.getAssignee().getName()
										+ "\n";
								history += "Content: "
										+ currentIssue.getDescription()
										+ "\n\n";
								DatabaseHelper.writeToIssueHistoryFile(currentIssue.getName(), history);

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

								// Append to file
								DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
								String history = "[" + df.format(new Date())
										+ "]\n";
								history += "Reporter: "
										+ currentIssue.getReporter().getName()
										+ "\n";
								history += "Assignee: "
										+ currentIssue.getAssignee().getName()
										+ "\n";
								history += "Content: "
										+ currentIssue.getDescription()
										+ "\n\n";
								DatabaseHelper.writeToIssueHistoryFile(currentIssue.getName(), history);

								JOptionPane.showMessageDialog(null, "Sent successfully");
								listIssue.refreshTable(currentEmployee, true);
								issueDetail.dispose();
							}
						});

						// Close issue button
						if (!(currentEmployee instanceof Developer)) {
							issueDetail.addCloseButtonActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									currentIssue.setStatus(ISSUE_STATUS.CLOSED);
									currentIssue.setUnread(false);
									Project currentProject = DatabaseHelper.getProjectFromPjId(currentIssue.getIncludedProject().getId());
									currentProject.editIssue(currentIssue);

									// Append to file
									DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
									String history = "["
											+ df.format(new Date()) + "]\n";
									history += "[Issue closed]";
									DatabaseHelper.writeToIssueHistoryFile(currentIssue.getName(), history);

									JOptionPane.showMessageDialog(null, "Issue solved, now being closed.");
									listIssue.refreshTable(currentEmployee, true);
									issueDetail.dispose();
								}
							});
						}

						issueDetail.setVisible(true);
					}
				});

				// History
				listIssue.addHistoryButtonActionListerner(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						File file = new File(listIssue.getSelectedIssue().getName()
								+ ".txt");
						FileInputStream fis = null;
						try {
							fis = new FileInputStream(file);
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "History not found, maybe it was created before history implementation.");
							return;
						}

						byte[] data = new byte[(int) file.length()];
						try {
							fis.read(data);
							fis.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						try {
							String content = new String(data, "UTF-8");
							new IssueHistory(listIssue.getSelectedIssue(), content).setVisible(true);
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
