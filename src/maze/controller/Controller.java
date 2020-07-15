package maze.controller;

import java.io.File;
import java.io.FileNotFoundException;

import maze.model.Maze;
import maze.model.MazeObserver;
import maze.model.MazeSolverObserver;

public class Controller {

	private Maze maze;
	
	public Controller(int r, int c) {
		maze = new Maze(r, c);
	}
	
	public void generateMaze() {
		this.maze.generate();
	}
	
	public void regenerateMaze(int r, int c) {
		Maze.NROW = r;
		Maze.NCOL = c;
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
