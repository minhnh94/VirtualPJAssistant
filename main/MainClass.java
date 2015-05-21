package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.LoginController;

public class MainClass {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		new LoginController();
	}

}
