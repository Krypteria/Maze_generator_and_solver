package maze.view.buttons;
import maze.controller.Controller;

@SuppressWarnings("serial")
public class SolveButton extends Buttons{

	private final static String imagePath_l = "icons/solveb_l.png";
	private final static String imagePath_g = "icons/solveb_g.png";
	private final static String tooltipText = "Solve the maze";
	
	public SolveButton(Controller c) {
		super(c, imagePath_l, imagePath_g, tooltipText);
	}
}