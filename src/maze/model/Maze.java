package maze.model;

import java.util.ArrayList;
import java.util.List;

public class Maze {

	private int NFIL;
	private int NCOL;

	private int start;
	private int finish;
	
	private List<List<Cell>> maze;
	private MazeGenerator generator;
	private MazeSolver solver;

	public Maze(int f, int c) {
		this.NFIL = f;
		this.NCOL = c;	
		this.maze = new ArrayList<List<Cell>>();
		this.generator = new MazeGenerator(NFIL, NCOL);
		this.solver = new MazeSolver(NFIL, NCOL);
	}
	
	
	public void generate() {
		start = this.generator.generateStartPoint();
		finish = this.generator.generateFinishPoint();
		maze = this.generator.generate();
	}
	
	public void regenerate() {
		start = this.generator.generateStartPoint();
		finish = this.generator.generateFinishPoint();
		generator.clear();
		solver.clear();
		
		maze = this.generator.generate();
	}
	
	public void solve() {
		solver.solve(maze, start, finish);
	}
	
	public void addMazeObserver(MazeObserver o) {
		generator.addObserver(o);
	}
	
	public void addMazeSolverObserver(MazeSolverObserver o) {
		solver.addObserver(o);		
	}
}
