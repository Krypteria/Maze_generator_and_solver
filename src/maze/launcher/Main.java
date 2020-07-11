package maze.launcher;

import javax.swing.SwingUtilities;

import maze.view.MainMenu;

public class Main {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainMenu();
			}
		});
	}

}
