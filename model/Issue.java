package model;

public class Issue {

	public enum ISSUE_STATUS {
		NEW, CHECKING, CLOSED
	};

	private int id;
	private Project includedProject;
	private String name;
	private String description;
	private Employee reporter;
	private Employee assignee;
	private ISSUE_STATUS status;
	private int priority;
	private boolean isUnread;

	public Issue(int id, Project includedProject, String name, String description, Employee reporter, Employee assignee, ISSUE_STATUS status, int priority, boolean isUnread) {
		this.setId(id);
		this.setIncludedProject(includedProject);
		this.setName(name);
		this.setDescription(description);
		this.setReporter(reporter);
		this.setAssignee(assignee);
		this.setStatus(status);
		this.setPriority(priority);
		this.setUnread(isUnread);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Project getIncludedProject() {
		return includedProject;
	}

	public void setIncludedProject(Project includedProject) {
		this.includedProject = includedProject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getReporter() {
		return reporter;
	}

	public void setReporter(Employee reporter) {
		this.reporter = reporter;
	}

	public Employee getAssignee() {
		return assignee;
	}

	public void setAssignee(Employee assignee) {
		this.assignee = assignee;
	}

	public ISSUE_STATUS getStatus() {
		return status;
	}

	public void setStatus(ISSUE_STATUS status) {
		this.status = status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isUnread() {
		return isUnread;
	}

	public void setUnread(boolean isUnread) {
		this.isUnread = isUnread;
	}
}
