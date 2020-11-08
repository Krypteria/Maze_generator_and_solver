package maze.view.buttons;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import maze.controller.Controller;

@SuppressWarnings("serial")
public abstract class Buttons extends JButton implements MouseListener {
	
	protected Controller ctrl;
	private String imagePath_l;
	private String imagePath_g;
	private String tooltipText;
	
	public Buttons(Controller c, String l, String g, String tt) {
		this.ctrl = c;
		this.imagePath_l = l;
		this.imagePath_g = g;
		this.tooltipText = tt;
		initGUI();
	}

	private void initGUI() {
		this.setIcon(new ImageIcon(imagePath_l));
		this.setToolTipText(this.tooltipText);
		this.setPreferredSize(new Dimension(50,50));
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.addMouseListener(this);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.setIcon(new ImageIcon(imagePath_g));
		this.updateUI();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.setIcon(new ImageIcon(imagePath_l));
		this.updateUI();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
