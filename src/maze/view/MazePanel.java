package maze.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.JPanel;

import maze.controller.Controller;
import maze.model.Cell;
import maze.model.MazeObserver;
import maze.model.MazeSolverObserver;

@SuppressWarnings("serial")
public class MazePanel extends JPanel implements MazeObserver, MazeSolverObserver{
	
	private int lineThickness = 8;
	private int nFil;
	private int nCol;
	
	private int xIni;
	private int yIni;
	
	private Color solColor;

	private List<List<Cell>> maze;
	private List<Cell> solution;
	private boolean solved;
	
	private int startPoint;
	private int finishPoint;

	public MazePanel(Controller c, int a, int b) {
		c.addMazeObserver(this);
		c.addMazeSolverObserver(this);
		this.maze = null;
		this.nFil = a;
		this.nCol = b;
		this.solved = false;
		this.solColor = Color.green;
		this.setSize(new Dimension(MainWindow.NFIL, MainWindow.NCOL));
		this.setVisible(false);
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    
		this.xIni = (this.getWidth() - nFil * 3 * 8) / 2;
		this.yIni = (this.getHeight() - nCol * 3 * 8) / 2;

		for(int x = 0; x <= MainWindow.NFIL * 3; x++) { //we paint the grid
			for(int y = 0; y <= MainWindow.NCOL * 3; y++) {
				if(x % 3 == 0 || y % 3 == 0) {
					g.setColor(Color.black);
					g.fillRect(x * lineThickness + xIni, y * lineThickness + yIni, lineThickness, lineThickness);
				}
				else {
					g.setColor(Color.white);
					g.fillRect(x * lineThickness + xIni, y * lineThickness + yIni, lineThickness, lineThickness);					
				}
			}	
		}
		
		walls(g); //we paint the walls of the maze	
		
		if(!solved) {
			startPoint(g, Color.white);
			finishPoint(g, Color.white);
		}
		else {
			startPoint(g, solColor);
			finishPoint(g, solColor);
			paintSolution(g, solColor);
		}
	}
	
	/*
	 * we paint the path that goes from the start point to the finish point
	 */
	private void paintSolution(Graphics g, Color c) {
		for(int i = 0; i < this.solution.size(); i++) {
			Cell ce = this.solution.get(i);
			
			int x = (ce.getCol() * 3 + 1) * lineThickness + xIni;
			int x2 = (ce.getCol() * 3 + 2) * lineThickness + xIni;
			int y = (ce.getRow() * 3 + 1) * lineThickness + yIni;
			int y2 = (ce.getRow() * 3 + 2) * lineThickness + yIni;
			
			g.setColor(c);
			g.fillRect(x, y, lineThickness, lineThickness);
			g.fillRect(x, y2, lineThickness, lineThickness);
			g.fillRect(x2, y, lineThickness, lineThickness);
			g.fillRect(x2, y2, lineThickness, lineThickness);
			
			if(!ce.getRightSide() && maze.get(ce.getRow()).get(ce.getCol() + 1).isPartOfSolution()) {
				int x3 = (ce.getCol() * 3 + 3) * lineThickness + xIni;
				
				g.fillRect(x3, y, lineThickness, lineThickness);
				g.fillRect(x3, y2, lineThickness, lineThickness);
			}
			if(!ce.getDownSide() && maze.get(ce.getRow() + 1).get(ce.getCol()).isPartOfSolution()) {
				int y3 = (ce.getRow() * 3 + 3) * lineThickness + yIni;
				
				g.fillRect(x, y3, lineThickness, lineThickness);
				g.fillRect(x2, y3, lineThickness, lineThickness);
			}
		}
	}
	
	private void startPoint(Graphics g, Color c) {
		int x1 = ((startPoint * 3) + 1) * lineThickness + xIni;
		int x2 = ((startPoint * 3) + 2) * lineThickness + xIni;
	
		g.setColor(c);
		g.clearRect(x1, yIni, lineThickness, lineThickness);
		g.clearRect(x2, yIni, lineThickness, lineThickness);
		
		if(!solved) {
			g.fillRect(x1, yIni, lineThickness, lineThickness);
			g.fillRect(x2, yIni, lineThickness, lineThickness);			
		}
		else {
			g.fillRect(x1, yIni, lineThickness, lineThickness);
			g.fillRect(x2, yIni, lineThickness, lineThickness);
		}
	}
	
	private void finishPoint(Graphics g, Color c) {
		int x1 = ((finishPoint * 3) + 1) * lineThickness + xIni;
		int x2 = ((finishPoint * 3) + 2) * lineThickness + xIni;
		int y = (MainWindow.NCOL * 3) * lineThickness + yIni;
		
		g.setColor(c);
		g.clearRect(x1, y, lineThickness, lineThickness);
		g.clearRect(x2, y, lineThickness, lineThickness);
		
		if(!solved) {
			g.fillRect(x1, y, lineThickness, lineThickness);
			g.fillRect(x2, y, lineThickness, lineThickness);			
		}
		else {
			g.fillRect(x1, y, lineThickness, lineThickness);
			g.fillRect(x2, y, lineThickness, lineThickness);	
		}
	}
	
	private void walls(Graphics g) {
			int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
			
			for(int i = 0; i < this.nFil; i++) {
				for(int j = 0; j < this.nCol; j++) {
					if(this.maze != null && this.maze.get(i).get(j).Visited()) {

						boolean right = this.maze.get(i).get(j).getRightSide();
						boolean down = this.maze.get(i).get(j).getDownSide();
						
						g.setColor(Color.white);
						if(down == false) { 
							x1 = (j * 3 + 1) * lineThickness + xIni;
							x2 = (j * 3 + 2) * lineThickness + xIni;
							y1 = (i * 3 + 3) * lineThickness + yIni;
							y2 = (i * 3 + 3) * lineThickness + yIni;
							
							g.clearRect(x1, y1, lineThickness, lineThickness);
							g.clearRect(x2, y2, lineThickness, lineThickness);
							g.fillRect(x1, y1, lineThickness, lineThickness);
							g.fillRect(x2, y2, lineThickness, lineThickness);
						}
						if(right == false) {
							x1 = (j * 3 + 3) * lineThickness + xIni;
							x2 = (j * 3 + 3) * lineThickness + xIni;
							y1 = (i * 3 + 1) * lineThickness + yIni;
							y2 = (i * 3 + 2) * lineThickness + yIni;
							
							g.clearRect(x1, y1, lineThickness, lineThickness);
							g.clearRect(x2, y2, lineThickness, lineThickness);
							g.fillRect(x1, y1, lineThickness, lineThickness);
							g.fillRect(x2, y2, lineThickness, lineThickness);
						}
					}
				}
			}
	}

	@Override
	public void updateGUI(List<List<Cell>> m, int startPoint, int finishPoint) {
		this.maze = m;
		this.startPoint = startPoint;
		this.finishPoint = finishPoint;
		
		repaint();
		this.setVisible(true);
	}

	@Override
	public void updateGUI(List<Cell> s, int tc, int vc, int cs) {
		this.solution = s;
		this.solved = true;
		repaint();
	}

	@Override
	public void clear() {
		this.solved = false;
		repaint();
	}
	

}
