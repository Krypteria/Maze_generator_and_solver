package maze.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class MazeGenerator {
	
	private int NFILM; 
	private int NCOLM;
	
	private int startPoint;
	private int finishPoint;
	private int cellsVisited;
	
	private List<List<Cell>> maze;
	private Stack<Cell> stack;

	private Random rand;

	public MazeGenerator(int f, int c) {
		this.NFILM = f; 
		this.NCOLM = c;
		this.cellsVisited = 0;
		this.maze = new ArrayList<List<Cell>>();
		this.stack = new Stack<Cell>();
		this.rand = new Random();
	}
	
	public void clear() {
		this.maze.clear();
		this.stack.clear();
		this.cellsVisited = 0;
	}
	
	public List<List<Cell>> generate() {
		generateGrid(); 
		generateMaze(this.maze.get(0).get(this.startPoint));
		generateRandomPassages();
		
		return this.maze;
	}
	
	/* 
	 * generates an initial canvas for start the generation of the maze
	 */
	private void generateGrid() {
		for(int i = 0; i < NFILM; i++) {
			maze.add(new ArrayList<Cell>(NCOLM));
			for(int j = 0; j < NCOLM; j++) {
				maze.get(i).add(j, new Cell(i,j));
			}
		}
	}
	
	/*
	 * when we have the maze generated we add some random passages in order to
	 * aggregate more variability in the way you can solve it
	 */
	private void generateRandomPassages() {
		for(int i = 0; i < NFILM * 2; i++) {
			
			int x = rand.nextInt(NFILM - 1) + 0;
			int y = rand.nextInt(NCOLM - 1) + 0;
			
			if(rand.nextInt(20) + 0 > 10 && maze.get(x).get(y).getRightSide()) {
				maze.get(x).get(y).setDownSide(false);
			}
			else if(rand.nextInt(20) + 0 > 10 && maze.get(x).get(y).getDownSide()){
				maze.get(x).get(y).setRightSide(false);
			}	
		}
	}
	
	public int generateStartPoint(){
		this.startPoint = rand.nextInt(NFILM);
		return this.startPoint;
	}
	
	public int generateFinishPoint() {
		this.finishPoint = rand.nextInt(NFILM);
		return finishPoint;
	}
		
	/*
	 * the maze is generated using a recursive solution with backtracking
	 * it works in the following way:
	 * 
	 * we travel through the cells of the grid, during this phase we put the cells we visited in 
	 * the stack in order to come back later there using backtracking
	 * 
	 * each cell has 2 walls, down wall and right wall, when we move from a cell to another we 
	 * put the wall we pass for into false and these wall will not be painted in the final result
	 * 
	 * when we reach a death point we come back using backtrack in the stack and we repeat the process until
	 * we visit all the cells in the grid
	 */
	private void generateMaze(Cell act) {
		List<Cell> posibilities;
		
		cellVisited(act);
 		
		if(this.cellsVisited < NFILM * NCOLM) {	
 					
			posibilities = getNextAvalaibles(act.getRow(), act.getCol());

			if(posibilities.size() != 0) {				
				Random rand = new Random();
				
				int index = rand.nextInt(521) % posibilities.size();
				int rowNext = posibilities.get(index).getRow();
				int colNext = posibilities.get(index).getCol();
				
				if(act.getRow() == rowNext) {
					if(act.getCol() + 1 == colNext) { //going right, we enter by the right wall of act
						act.setRightSide(false);
					}
					else { //going left, we enter by the right panel of next
						this.maze.get(rowNext).get(colNext).setRightSide(false);
					}
				}
				else if(act.getCol() == colNext) {
					if(act.getRow() + 1 == rowNext) { //going down, we enter by the down wall of act
						act.setDownSide(false);
					}
					else { //going up, we enter by the down wall of next
						this.maze.get(rowNext).get(colNext).setDownSide(false);
					}
				}
				
				generateMaze(this.maze.get(rowNext).get(colNext));
			}	
			else {
				if(!stack.empty()) {
					stack.pop();
					generateMaze(stack.peek());
				}
			}
		}
	} 
	
	/*
	 * when we visit a cell we push it into the stack and we set his visited boolean to true
	 * we also increase the int cellsVisited 
	 */
	private void cellVisited(Cell act) {
		if(!act.Visited()) {
			this.stack.push(act);
			act.setVisited(true);
			this.cellsVisited += 1;			
		}
	}
	
	/*
	 * this method return a list of possible cells to visit
	 * we check the visited boolean of the near cells in order to put them into the list
	 */
	private List<Cell> getNextAvalaibles(int f, int c) {
		List<Cell> nextAvalaibles = new ArrayList<Cell>();
		
		if(c - 1 >= 0 && !maze.get(f).get(c - 1).Visited()) { //left
			nextAvalaibles.add(maze.get(f).get(c - 1));
		}
		if(c + 1 < NCOLM && !maze.get(f).get(c + 1).Visited()) { //right
			nextAvalaibles.add(maze.get(f).get(c + 1));
		}
		if(f - 1 >= 0 && !maze.get(f - 1).get(c).Visited()) { //up
			nextAvalaibles.add(maze.get(f - 1).get(c));
		}
		if(f + 1 < NFILM && !maze.get(f + 1).get(c).Visited()) { //down
			nextAvalaibles.add(maze.get(f + 1).get(c));
		}
		
		return nextAvalaibles;
	}

}
