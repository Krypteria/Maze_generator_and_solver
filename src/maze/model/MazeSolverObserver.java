package maze.model;

import java.util.List;

public interface MazeSolverObserver {

	void updateGUI(List<Cell> s, int tc, int vc, int cs);
	void clear();
}
