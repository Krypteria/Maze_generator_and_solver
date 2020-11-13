package maze.launcher;

import java.util.Locale;

import javax.swing.SwingUtilities;

import maze.view.MainMenu;

public class Main {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.ENGLISH);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainMenu();
			}
		});
	}

}
