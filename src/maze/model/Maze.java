package maze.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Maze {

	private int NFIL;
	private int NCOL;

	private int start;
	private int finish;
	
	private List<List<Cell>> maze;
	private MazeGenerator generator;
	private MazeSolver solver;
	
	private MazeObserver observer;

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
		
		observer.updateGUI(maze, start, finish);
	}
	
	public void solve() {
		solver.solve(maze, start, finish);
	}
	
	public void clear() {
		generator.clear();
		solver.clear();
		maze.clear();
	}
	
	public void saveMaze(String fileName) throws FileNotFoundException {
		MazeDAO Dao = new MazeDAO();
		Dao.saveMaze(fileName, this.generateTranferObject());
	}
	
	public void loadMaze(File file) throws FileNotFoundException {
		MazeDAO dao = new MazeDAO();
		this.setTrasferObject(dao.loadMaze(file));
		
		observer.updateGUI(maze, start, finish);
	}
	
	public void addMazeObserver(MazeObserver o) {
		this.observer = o;
	}
	
	public void addMazeSolverObserver(MazeSolverObserver o) {
		solver.addObserver(o);		
	}
	
	private TransferObject generateTranferObject() { //queda guardar el start y el finish point
		TransferObject tObj = new TransferObject();
		JSONArray infoArray = new JSONArray();
		JSONObject objectInfo = new JSONObject();
		
		for(int i = 0; i < NFIL; i++) {
			for(int j = 0; j < NCOL; j++) {
				infoArray.put(maze.get(i).get(j).generateTransferObject().getObjectInfo());
			}
		}
		
		objectInfo.put("nRow", this.NFIL);
		objectInfo.put("nCol", this.NCOL);
		objectInfo.put("startPoint", this.start);
		objectInfo.put("finishPoint", this.finish);
		objectInfo.put("maze", infoArray);
		
		tObj.setObjectInfo(objectInfo);
		
		
		return tObj;
	}
	
	private void setTrasferObject(TransferObject o) {
		JSONArray infoArray = new JSONArray();
		JSONObject objectInfo = o.getObjectInfo();
	
		this.NFIL = objectInfo.getInt("nRow");
		this.NCOL = objectInfo.getInt("nCol");
		
		this.start = objectInfo.getInt("startPoint");
		this.finish = objectInfo.getInt("finishPoint");
		
		
		infoArray = objectInfo.getJSONArray("maze");
		int index = 0;
		
		for(int i = 0; i < this.NFIL; i++) {
			
			List<Cell> listCell = new ArrayList<Cell>();
			
			for(int j = 0; j < this.NCOL; j++) {
				TransferObject celltObj = new TransferObject();
				JSONObject cellInfo = infoArray.getJSONObject(index);
				
				celltObj.setObjectInfo(cellInfo);
				Cell cell = new Cell();
				cell.setTransferObject(celltObj);
				listCell.add(cell); 
				index++;
			}
			
			maze.add(listCell);
		}
	}
}
