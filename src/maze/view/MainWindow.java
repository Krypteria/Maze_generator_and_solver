package maze.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import maze.controller.Controller;

@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	
	public static int NROW;
	public static int NCOL;
	
	public static final int WIDTH = 1370; 
	public static final int HEIGHT = 820;
	
	private Controller c;
	
	public MainWindow(int r, int h, Controller c) {
		NROW = r;
		NCOL=  h;
		this.c = c;
		initGUI();
		c.generateMaze();
	}
	
	private void initGUI() {		
		JPanel mainPanel = new JPanel(new BorderLayout(5,5));
		//mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		this.setContentPane(mainPanel);
		
		JPanel modelView = new JPanel(new GridLayout(1, 2));
		
		// ---------------------------------------------
		JPanel interactivePanel = new JPanel();
		interactivePanel.setLayout(new BoxLayout(interactivePanel, BoxLayout.PAGE_AXIS));
		
		int x = 1000, y = 1000;
		JPanel generate = createViewPanel(new GeneratePanel(c), x, y);
		JPanel data = createViewPanel(new DataPanel(c), x, y);
		JPanel options = createViewPanel(new OptionsPanel(c), x, y);
		JPanel logo = createViewPanel(new LogoPanel(), x, y);
		
		interactivePanel.add(generate);
		interactivePanel.add(data);
		interactivePanel.add(options);
		interactivePanel.add(logo);
		interactivePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK));

		
		JPanel interactivePanelWrap = new JPanel(new BorderLayout(5, 5));
		
		JPanel s1 = new JPanel();
		JPanel s2 = new JPanel();
		JPanel s3 = new JPanel();
		JPanel s4 = new JPanel();
		
		interactivePanelWrap.add(s1, BorderLayout.LINE_START);
		interactivePanelWrap.add(s2, BorderLayout.LINE_END);
		interactivePanelWrap.add(s3, BorderLayout.PAGE_START);
		interactivePanelWrap.add(s4, BorderLayout.PAGE_END);
				
		interactivePanelWrap.add(interactivePanel, BorderLayout.CENTER);

		//DataPanel dataPanel = new DataPanel(c);
		MazePanel mazePanel = new MazePanel(c);
		
		// ---------------------------------------------
		
		modelView.add(interactivePanelWrap);
		modelView.add(mazePanel);
		
		mainPanel.add(modelView, BorderLayout.CENTER);
		mainPanel.setVisible(true);
		//ControlPanel controlPanel = new ControlPanel(c);
		
		//mainPanel.add(dataPanel, BorderLayout.PAGE_START);
		//mainPanel.add(controlPanel, BorderLayout.PAGE_END);
			
		this.setSize(new Dimension(WIDTH,HEIGHT));
		this.setTitle("Maze generator and solver");
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private JPanel createViewPanel(JComponent c, int x, int y) {
		JPanel p = new JPanel(new BorderLayout());
		
		p.add(c, BorderLayout.CENTER);
		p.setPreferredSize(new Dimension(x,y));
		p.setMaximumSize(new Dimension(x, y));
		
		return p;
	}	

}
