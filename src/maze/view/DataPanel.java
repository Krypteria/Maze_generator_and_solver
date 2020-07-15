package maze.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		this.setLayout(new GridLayout(2,1));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		
		this.nTotalCells = new JLabel("" + 0);
		this.nVisitedCells = new JLabel("" + 0);
		this.nCellsInSolution = new JLabel("" + 0);
		
		JPanel sup = new JPanel();
		sup.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel nTotalLabel = new JLabel("Number of total Cells in the maze: ");
		
		sup.add(nTotalLabel);
		sup.add(this.nTotalCells);
		
		JPanel inf = new JPanel();
		inf.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel nVisitedLabel = new JLabel("Number of visited Cells: ");
		JLabel nSolutionLabel = new JLabel("Number of Cells in the path: ");
		
		inf.add(nVisitedLabel);
		inf.add(this.nVisitedCells);
		inf.add(nSolutionLabel);
		inf.add(this.nCellsInSolution);
		
		this.add(sup);
		this.add(inf);
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
