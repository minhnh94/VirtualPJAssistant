package mainpkg;

import model.Manager;
import model.Project.PJ_STATUS;
import helper.DatabaseHelper;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager manager = new Manager(1, "YES", "FUCK", "012345", "000");
		DatabaseHelper.getInstance().addProject("Yes", "a desc", PJ_STATUS.ONGOING, "14/2/1212", "13/2/2222", manager);
	}
}
