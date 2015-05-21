package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import model.Developer;
import model.Employee;
import model.Issue;
import model.Project;
import model.Issue.ISSUE_STATUS;

public class ListIssue extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private PatternPanel issPanel;
	private ImageButton addButton;
	private ImageButton viewButton;
	private ImageButton toNewButton;
	private MainTableModel tbmodel;

	public ListIssue(Employee employee, boolean readingNew) {
		creatTable();
		if (readingNew) {
			tbmodel.setIssues(employee.getNewAssignedIssueAsNotification());
		} else {
			tbmodel.setIssues(employee.getAssignedIssue());
		}
		tbmodel.fireTableDataChanged();
		createButtons(employee instanceof Developer, !readingNew);
	}

	public ListIssue(Employee employee, Project project) {
		creatTable();
		tbmodel.setIssues(project.getTotalIssue());
		tbmodel.fireTableDataChanged();
		createButtons(employee instanceof Developer, true);
	}

	public void refreshTable(Employee employee, boolean readingNew) {
		if (readingNew) {
			tbmodel.setIssues(employee.getNewAssignedIssueAsNotification());
		} else {
			tbmodel.setIssues(employee.getAssignedIssue());
		}
		tbmodel.fireTableDataChanged();
	}

	public void refreshTable(Project project) {
		tbmodel.setIssues(project.getTotalIssue());
		tbmodel.fireTableDataChanged();
	}

	private void creatTable() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		this.setResizable(false);
		this.setTitle(CommonString.ISSTITLE);
		this.setBounds((dim.width - 800) / 2, 10, 800, 410);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		issPanel = new PatternPanel(CommonString.ISSBG);
		issPanel.setLayout(new BorderLayout());
		this.setContentPane(issPanel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		issPanel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setFont(Theme.SMALLER_FONT);
		table.setForeground(Theme.getColor(1));
		table.setOpaque(false);
		// table.setBackground(bgColor);
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
		scrollPane.getViewport().setOpaque(false);

		tbmodel = new MainTableModel();
		table.setModel(tbmodel);
	}

	private void createButtons(boolean isDev, boolean toNew) {
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setOpaque(false);
		issPanel.add(buttonPanel, BorderLayout.SOUTH);

		if (!isDev) {
			addButton = new ImageButton(CommonString.ADDISS, CommonString.ADDISSH);
			addButton.setPreferredSize(new Dimension(200, 50));
			buttonPanel.add(addButton);
		}

		viewButton = new ImageButton(CommonString.DETAIL, CommonString.DETAILH);
		viewButton.setPreferredSize(new Dimension(200, 50));
		buttonPanel.add(viewButton);

		if (toNew) {
			toNewButton = new ImageButton(CommonString.TONEW, CommonString.TONEWH);
			toNewButton.setPreferredSize(new Dimension(200, 50));
			buttonPanel.add(toNewButton);
		} else {
			toNewButton = new ImageButton(CommonString.ALLISS, CommonString.ALLISSH);
			toNewButton.setPreferredSize(new Dimension(200, 50));
			buttonPanel.add(toNewButton);
		}

	}

	public void setIssues(List<Issue> issues) {
		MainTableModel tableModel = (MainTableModel) table.getModel();
		tableModel.setIssues(issues);
	}

	class MainTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;
		private List<Issue> issues;

		public MainTableModel() {
			super();
			issues = new ArrayList<Issue>();
		}

		private final String[] HEADER_NAME = new String[] { ObjectPropertyString.STT, ObjectPropertyString.ISSNAME, ObjectPropertyString.INPJ, ObjectPropertyString.DEV, ObjectPropertyString.TEST, ObjectPropertyString.STATUS };

		@Override
		public String getColumnName(int column) {
			return column >= 0 && column < HEADER_NAME.length ? HEADER_NAME[column] : super.getColumnName(column);
		}

		@Override
		public int getColumnCount() {
			return HEADER_NAME.length;
		}

		@Override
		public int getRowCount() {
			return issues != null ? issues.size() : super.getRowCount();
		}

		@Override
		public Object getValueAt(int row, int column) {
			if (issues != null && 0 <= row && row < issues.size()) {
				Issue issue = issues.get(row);
				if (issue != null) {
					switch (column) {
					case 0:
						return row + 1;
					case 1:
						return issue.getName();
					case 2:
						return issue.getIncludedProject().getName();
					case 3:
						return issue.getAssignee().getName();
					case 4:
						return issue.getReporter().getName();
					case 5:
						int statusInt;
						if (issue.getStatus() == ISSUE_STATUS.NEW) {
							statusInt = 0;
						} else if (issue.getStatus() == ISSUE_STATUS.CHECKING) {
							statusInt = 1;
						} else
							statusInt = 2;
						return ObjectPropertyString.ISSSTATUS[statusInt];
					default:
						return null;
					}
				}
			}
			return getValueAt(row, column);
		}

		public void setIssues(List<Issue> issues) {
			this.issues = issues;
			fireTableDataChanged();
		}

		public Issue getIssue(int index) {
			if (issues != null && 0 <= index && index <= issues.size())
				return issues.get(index);

			return null;
		}
	}

	public void addAddButtonActionListerner(ActionListener listener) {
		addButton.addActionListener(listener);
	}

	public void addViewButtonActionListerner(ActionListener listener) {
		viewButton.addActionListener(listener);
	}

	public void addToNewButtonActionListerner(ActionListener listener) {
		toNewButton.addActionListener(listener);
	}

}
