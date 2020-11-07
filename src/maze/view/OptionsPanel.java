package maze.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import maze.controller.Controller;

@SuppressWarnings("serial")
public class OptionsPanel extends JPanel{
	
	private Controller ctrl;
	
	private JLabel title;
	
	private JButton solve; 
	private JButton save; 
	private JButton load;
	
	public OptionsPanel(Controller c) {
		this.ctrl = c;
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout(5,5));
		
		this.title = new JLabel("Options");
		
		this.solve = new JButton("Solve");		
		this.solve.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ctrl.getSolution();
			}
			
		});
		
		this.save = new JButton("Save maze");
		this.save.addActionListener(new ActionListener() {

			private String file;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				do {
					this.file = JOptionPane.showInputDialog("Select a name for the file");
					if(this.file == null)
						break;
				}
				while(file.equals(""));
				
				if(file != null) {					
					try {
						ctrl.saveMaze(file);
						JOptionPane.showMessageDialog(null, "Maze saved successfully in Saves/" + this.file);
					} 
					catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(getParent(), "Invalid file name, the maze has not been saved", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});	
		
		this.load = new JButton("Load maze");
		this.load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(new File("Saves"));
				int sel = fileChooser.showOpenDialog(getParent());
				
				if(sel == JFileChooser.APPROVE_OPTION) {
					try {
						ctrl.loadMaze(fileChooser.getSelectedFile());
					} 
					catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(getParent(), "File not found", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(getParent(), "Invalid file selected", "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		

		JPanel p0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		p0.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		p0.add(this.title);
		p1.add(this.solve);
		p1.add(this.save);
		p1.add(this.load);
		
		p0.setMaximumSize(new Dimension(400,35));
		p1.setMaximumSize(new Dimension(400,35));

		JPanel sep1 = new JPanel();
		sep1.setMaximumSize(new Dimension(400,25));
		
		JPanel sep2 = new JPanel();
		sep2.setMaximumSize(new Dimension(400,25));

		
		mainPanel.add(p0);
		mainPanel.add(sep1);
		mainPanel.add(p1);
		
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));
		
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(new JPanel(), BorderLayout.PAGE_START);
		this.add(new JPanel(), BorderLayout.PAGE_END);
		this.add(new JPanel(), BorderLayout.LINE_START);
		this.add(new JPanel(), BorderLayout.LINE_END);
	}
}
