package maze.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import maze.controller.Controller;

@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	
	static int NFIL;
	static int NCOL;
	
	private Controller c;
	private MazePanel mazePanel;
	
	
	public MainWindow(int a, int h, Controller c) {
		NFIL = a;
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
		
		mazePanel = new MazePanel(c,NFIL,NCOL);
		
		JPanel inf = new JPanel();
		inf.setLayout(new FlowLayout(FlowLayout.CENTER));
		inf.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
		
		
		DataPanel sup = new DataPanel(c);
		sup.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		
		JButton generate = new JButton("Generate");
		JButton solve = new JButton("Solve");
		
		generate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.regenerateMaze();
			}
			
		});
		
		solve.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.getSolution();
			}
			
		});
		
		
		inf.add(generate);
		inf.add(solve);
		
		mainPanel.add(sup, BorderLayout.PAGE_START);
		mainPanel.add(mazePanel, BorderLayout.CENTER);
		mainPanel.add(inf, BorderLayout.PAGE_END);
		
		this.add(mainPanel,BorderLayout.CENTER);
		
		this.setSize(new Dimension(800,870));
		this.setTitle("Maze generator");
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}


}
