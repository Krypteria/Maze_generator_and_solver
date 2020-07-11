package maze.controller;

import maze.model.Maze;
import maze.model.MazeObserver;
import maze.model.MazeSolverObserver;

public class Controller {

	private Maze maze;
	private int nR;
	private int nC;
	
	public Controller(int a, int h) {
		this.nR = a;
		this.nC = h;
		maze = new Maze(nR, nC);
	}
	
	public void generateMaze() {
		this.maze.generate();
	}
	
	public void regenerateMaze() {
		this.maze.regenerate();
	}
	
	public void getSolution() {
		this.maze.solve();
	}
	
	public void addMazeObserver(MazeObserver o) {
		this.maze.addMazeObserver(o);
	}
	
	public void addMazeSolverObserver(MazeSolverObserver o) {
		this.maze.addMazeSolverObserver(o);
	}

}
