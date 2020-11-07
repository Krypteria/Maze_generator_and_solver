package maze.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import maze.controller.Controller;
import maze.model.Cell;
import maze.model.MazeSolverObserver;

@SuppressWarnings("serial")
public class DataPanel extends JPanel implements MazeSolverObserver{

	private JLabel title;

	private JLabel nTotalCells;
	private JLabel nVisitedCells;
	private JLabel nCellsInSolution;
	
	private JLabel nTotalInfo;
	private JLabel nVisitedInfo;
	private JLabel nSolutionInfo;
	
	public DataPanel(Controller c) {
		c.addMazeSolverObserver(this);
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout(5,5));
		
		this.title = new JLabel("Data Panel");
		
		this.nTotalCells = new JLabel("" + MainWindow.NROW * MainWindow.NCOL);
		this.nVisitedCells = new JLabel("" + 0);
		this.nCellsInSolution = new JLabel("" + 0);
		
		this.nTotalInfo= new JLabel("Number of total Cells in the maze: ");
		this.nVisitedInfo= new JLabel("Number of visited Cells: ");
		this.nSolutionInfo = new JLabel("Number of Cells in the path: ");
	
		JPanel p0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		p0.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		p0.add(this.title);
		p1.add(this.nTotalInfo);
		p1.add(this.nTotalCells);
		p2.add(this.nVisitedInfo);
		p2.add(this.nVisitedCells);
		p3.add(this.nSolutionInfo);
		p3.add(this.nCellsInSolution);
		
		int x = 400, y = 35;
		
		p0.setMaximumSize(new Dimension(x,y));
		p1.setMaximumSize(new Dimension(x,y));
		p2.setMaximumSize(new Dimension(x,y));
		p3.setMaximumSize(new Dimension(x,y));
		
		JPanel sep1 = new JPanel();
		sep1.setMaximumSize(new Dimension(x,y));
		
		JPanel sep2 = new JPanel();
		sep2.setMaximumSize(new Dimension(x,y));
		
		mainPanel.add(p0);
		mainPanel.add(sep1);
		mainPanel.add(p1);
		mainPanel.add(p2);
		mainPanel.add(p3);
		
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));
		
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(new JPanel(), BorderLayout.PAGE_START);
		this.add(new JPanel(), BorderLayout.PAGE_END);
		this.add(new JPanel(), BorderLayout.LINE_START);
		this.add(new JPanel(), BorderLayout.LINE_END);
	}
	
	@Override
	public void updateGUI(List<Cell> s, int tc, int vc, int cs) {
		this.nTotalCells.setText("" + tc);
		this.nVisitedCells.setText("" + vc);
		this.nCellsInSolution.setText("" + cs);
	}


	@Override
	public void clear() {
		this.nTotalCells.setText("" + 0);
		this.nVisitedCells.setText("" +  0);
		this.nCellsInSolution.setText("" + 0);
	}
}
