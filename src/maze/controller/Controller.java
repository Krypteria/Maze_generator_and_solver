package maze.controller;

import java.io.File;
import java.io.FileNotFoundException;

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
		this.maze.clear();
		this.maze.generate();
	}
	
	public void getSolution() {
		this.maze.solve();
	}
	
	public void saveMaze(String fileName) throws FileNotFoundException {
		this.maze.saveMaze(fileName);
	}
	
	public void loadMaze(File file) throws FileNotFoundException {
		this.maze.clear();
		this.maze.loadMaze(file);
	}
	
	public void addMazeObserver(MazeObserver o) {
		this.maze.addMazeObserver(o);
	}
	
	public void addMazeSolverObserver(MazeSolverObserver o) {
		this.maze.addMazeSolverObserver(o);
	}

}
