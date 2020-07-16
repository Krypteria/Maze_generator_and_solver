package maze.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import maze.controller.Controller;
import maze.model.Cell;
import maze.model.MazeSolverObserver;

@SuppressWarnings("serial")
public class DataPanel extends JPanel implements MazeSolverObserver{
	
	private JLabel nTotalCells;
	private JLabel nVisitedCells;
	private JLabel nCellsInSolution;
	
	public DataPanel(Controller c) {
		c.addMazeSolverObserver(this);
		initGUI();
	}
	
	
	private void initGUI() {
		this.setLayout(new GridLayout(1,2));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		
		this.nTotalCells = new JLabel("" + MainWindow.NROW * MainWindow.NCOL);
		this.nVisitedCells = new JLabel("" + 0);
		this.nCellsInSolution = new JLabel("" + 0);
		
		JLabel nTotalLabel = new JLabel("Number of total Cells in the maze: ");
		JLabel nVisitedLabel = new JLabel("Number of visited Cells: ");
		JLabel nSolutionLabel = new JLabel("Number of Cells in the path: ");
		
		JButton logo = new JButton(new ImageIcon("icons/logo2.png"));	
		logo.setOpaque(false);
		logo.setContentAreaFilled(false);
		logo.setBorderPainted(true);
		logo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		
		JSeparator sep1 = new JSeparator();
		sep1.setForeground(Color.BLACK);
		sep1.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		
		JSeparator sep2 = new JSeparator();
		sep2.setForeground(Color.BLACK);
		sep2.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
		infoPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK));
		
		JPanel logoPanel = new JPanel();
		logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JPanel info1 = new JPanel();
		info1.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel info2 = new JPanel();
		info2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel info3 = new JPanel();
		info3.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		info1.add(nTotalLabel);
		info1.add(this.nTotalCells);
		
		info2.add(nVisitedLabel);
		info2.add(this.nVisitedCells);
		
		info3.add(nSolutionLabel);
		info3.add(this.nCellsInSolution);
		
		infoPanel.add(info1);
		infoPanel.add(Box.createRigidArea(new Dimension(2,2)));
		infoPanel.add(sep1);
		infoPanel.add(Box.createRigidArea(new Dimension(2,2)));
		infoPanel.add(info2);
		infoPanel.add(Box.createRigidArea(new Dimension(2,2)));
		infoPanel.add(sep2);
		infoPanel.add(Box.createRigidArea(new Dimension(2,2)));
		infoPanel.add(info3);
		
		logoPanel.add(logo);
		
		this.add(infoPanel);
		this.add(logoPanel);
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
