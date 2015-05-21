package view;

import java.util.ArrayList;

import model.Employee;
import model.Issue;
import model.Manager;
import model.Project;
import model.Project.PJ_STATUS;

public class TestComponent {

	public static void main(String[] args) {
		Employee e = new Employee(001, "Minh Minh Minh Minh Minh Minh", "1234abcd", "0972043693", "test@yahoo.com");
		Manager m = new Manager(000, "minh", "1111", "222", "jbjbj@ggg");
		Project p = new Project(000, "Pj1", "blah blah", "01", "02", "03", PJ_STATUS.ONGOING, m);
		ArrayList<Project> projects = new ArrayList<Project>();
		projects.add(p);
		Issue i1 = new Issue(0003, p, "iss1", "nay thi mot cai des dai nay blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah", m, e, Issue.ISSUE_STATUS.NEW, 5, true);
		Issue i2 = new Issue(0002, p, "iss2", "fuck", m, e, Issue.ISSUE_STATUS.NEW, 5, true);
		new ListIssue(e, false).setVisible(true);
		new Register().setVisible(true);
		new IssueDetail(i1, e).setVisible(true);
		new NewIssue(p).setVisible(true);
		new ProjectDetail(p, false).setVisible(true);
		new AddEmployee(false).setVisible(true);
		new NewProject().setVisible(true);
		new UserDetail(e, true).setVisible(true);
		new EditUserProfile(e).setVisible(true);
	}

}
