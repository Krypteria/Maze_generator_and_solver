package maze.view.buttons;
import maze.controller.Controller;

@SuppressWarnings("serial")
public class LoadButton extends Buttons{

	private final static String imagePath_l = "icons/loadb_l.png";
	private final static String imagePath_g = "icons/loadb_g.png";
	private final static String tooltipText = "Load the maze you select";
	
	public LoadButton(Controller c) {
		super(c, imagePath_l, imagePath_g, tooltipText);
	}
}
