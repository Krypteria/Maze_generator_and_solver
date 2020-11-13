package maze.view.buttons;
import maze.controller.Controller;

@SuppressWarnings("serial")
public class SaveButton extends Buttons{

	private final static String imagePath_l = "icons/saveb_l.png";
	private final static String imagePath_g = "icons/saveb_g.png";
	private final static String tooltipText = "Saves the maze with a name";
	
	public SaveButton(Controller c) {
		super(c, imagePath_l, imagePath_g, tooltipText);
	}
}
