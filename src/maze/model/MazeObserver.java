package maze.model;

import java.util.List;

public interface MazeObserver {

	void updateGUI(List<List<Cell>> maze, int startPoint, int finishPoint, int nrows, int ncols);
	
	
}
