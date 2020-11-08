package maze.view.buttons;
import maze.controller.Controller;

@SuppressWarnings("serial")
public class GenerateButton extends Buttons{

	private final static String imagePath_l = "icons/generateb_l.png";
	private final static String imagePath_g = "icons/generateb_g.png";
	private final static String tooltipText = "Generate a new maze";
	
	public GenerateButton(Controller c) {
		super(c, imagePath_l, imagePath_g, tooltipText);
	}
}
