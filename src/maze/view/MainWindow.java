package maze.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import maze.controller.Controller;

@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	
	public static int NROW;
	public static int NCOL;
	
	public static final int WIDTH = 870; 
	public static final int HEIGHT = 800;
	
	private Controller c;
	
	public MainWindow(int r, int h, Controller c) {
		NROW = r;
		NCOL=  h;
		this.c = c;
		initGUI();
		c.generateMaze();
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		DataPanel dataPanel = new DataPanel(c);
		MazePanel mazePanel = new MazePanel(c);
		ControlPanel controlPanel = new ControlPanel(c);
		
		mainPanel.add(dataPanel, BorderLayout.PAGE_START);
		mainPanel.add(mazePanel, BorderLayout.CENTER);
		mainPanel.add(controlPanel, BorderLayout.PAGE_END);
	
		this.add(mainPanel,BorderLayout.CENTER);
		
		this.setSize(new Dimension(WIDTH,HEIGHT));
		this.setTitle("Maze generator and solver");
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}


}
