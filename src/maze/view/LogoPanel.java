package maze.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LogoPanel extends JPanel{


	public LogoPanel() {
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout(5,5));
		
		JPanel logoP = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JButton logo = new JButton(new ImageIcon("icons/logo.png"));	
		logo.setOpaque(false);
		logo.setContentAreaFilled(false);
		logo.setBorderPainted(false);
		
		JPanel sep1 = new JPanel();
		sep1.setMaximumSize(new Dimension(400,60));

		logoP.add(sep1);
		logoP.add(logo);
		
		this.add(logoP, BorderLayout.CENTER);
		this.add(new JPanel(), BorderLayout.PAGE_START);
		this.add(new JPanel(), BorderLayout.PAGE_END);
		this.add(new JPanel(), BorderLayout.LINE_START);
		this.add(new JPanel(), BorderLayout.LINE_END);
	}
}
