package maze.model;

import org.json.JSONObject;

public class Cell {
	
	private boolean visited;
	private boolean solved;
	private boolean partOfSolution;
	private int row;
	private int col;
	
	private boolean right = true;
	private boolean down = true;
	
	public Cell(int i, int j) {
		this.row = i;
		this.col = j;
		this.visited = false;
		this.solved = false;
		this.partOfSolution = false;
	}
	
	public Cell() {}

	public boolean Visited() {
		return this.visited;
	}
	
	public boolean solverVisited() {
		return this.solved;
	}
	
	public boolean isPartOfSolution() {
		return this.partOfSolution;
	}
	
	public void partOfSolution(boolean b) {
		partOfSolution = b;
	}
	public void setSolvedVisited(boolean b){
		this.solved = b;
	}
	
	public void setVisited(boolean b) {
		this.visited = b;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public void setRightSide(boolean b) {
		this.right = b;
	}
	
	public void setDownSide(boolean b) {
		this.down = b;
	}
	
	public boolean getRightSide() {
		return this.right;
	}
	
	public boolean getDownSide() {
		return this.down;
	}
	
	public TransferObject generateTransferObject() {
		TransferObject tObj = new TransferObject();
		JSONObject objectInfo = new JSONObject();
		
		objectInfo.put("row", this.row);
		objectInfo.put("col", this.col);
		objectInfo.put("right", this.right);
		objectInfo.put("down", this.down);
		objectInfo.put("visited", this.visited);
		
		tObj.setObjectInfo(objectInfo);
		
		return tObj;
	}
	
	public void setTransferObject(TransferObject o) {
		JSONObject objectInfo = o.getObjectInfo();
		
		this.row = objectInfo.getInt("row");
		this.col = objectInfo.getInt("col");
		this.right = objectInfo.getBoolean("right");
		this.down = objectInfo.getBoolean("down");
		this.visited = objectInfo.getBoolean("visited");
	}
}
