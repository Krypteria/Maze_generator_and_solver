package maze.model;

import java.util.ArrayList;
import java.util.List;

public class MazeSolver {
	
	private int start;
	private int finish;
	private int nVisited;
	
	private List<List<Cell>> maze;
	private List<Cell> solution;
	private List<MazeSolverObserver> observers;
	
	public MazeSolver() {
		this.nVisited = 0;
		solution = new ArrayList<Cell>();
		this.observers = new ArrayList<MazeSolverObserver>();
	}
	
	public void addObserver(MazeSolverObserver o){
		this.observers.add(o);
	}
	
	public void clear() {
		if(this.maze != null) {
			this.maze.clear();
			this.solution.clear();
			this.nVisited = 0;	
			
			for(MazeSolverObserver o: this.observers) {
				o.clear();
			}
		}
	}
	
	public void solve(List<List<Cell>> m, int a, int b) {
		this.maze = m;
		this.start = a;
		this.finish = b;
		
		recursiveSolution(maze.get(0).get(start));
		
		for(MazeSolverObserver o: this.observers) {
			o.updateGUI(this.solution, Maze.NROW * Maze.NCOL, nVisited, this.solution.size());
		}
	}
	
	/*
	 * in order to solve the maze we use a recursive divide and conquer solution, it works of the following way:
	 * 
	 * we starts in the cell which is down the start point
	 * we check the near cells and we move to the first we can travel (we can travel from one cell to another if the cell that we 
	 * want to visit don't have a wall between the actual cell and her)
	 * 
	 * we also must check the solverVisited boolean of the cells in order to avoid visiting the same cell twice
	 */
	private boolean recursiveSolution(Cell act) {
		act.setSolvedVisited(true);
		nVisited++;
		
		if(act.getRow() == Maze.NROW - 1 && act.getCol() == finish) { //if is the correct path
			solution.add(act);
			act.partOfSolution(true);
			return true;
		}
		else {
		
			if(!act.getRightSide() && !maze.get(act.getRow()).get(act.getCol() + 1).solverVisited()) { //right cell and it returns true
				if(recursiveSolution(maze.get(act.getRow()).get(act.getCol() + 1))) {
					solution.add(act);
					act.partOfSolution(true);
					return true;
				}
			}
			if(!act.getDownSide() && !maze.get(act.getRow() + 1).get(act.getCol()).solverVisited()) { //down cell and it returns true
				if(recursiveSolution(maze.get(act.getRow() + 1).get(act.getCol()))) {
					solution.add(act);
					act.partOfSolution(true);
					return true;
				}	
			}
			if(act.getCol() != 0 && !maze.get(act.getRow()).get(act.getCol() - 1).getRightSide() && !maze.get(act.getRow()).get(act.getCol() - 1).solverVisited()) { //left cell and it returns true
				if(recursiveSolution(maze.get(act.getRow()).get(act.getCol() - 1))){
					solution.add(act);
					act.partOfSolution(true);
					return true;
				}
			}
			if(act.getRow() != 0 && !maze.get(act.getRow() - 1).get(act.getCol()).getDownSide() && !maze.get(act.getRow() - 1).get(act.getCol()).solverVisited()) { //up cell and it returns true
				if(recursiveSolution(maze.get(act.getRow() - 1).get(act.getCol()))) {
					solution.add(act);
					act.partOfSolution(true);
					return true;
				}
			}
			
			return false;
		}
		
	}
}
