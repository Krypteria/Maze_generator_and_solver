package maze.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import maze.controller.Controller;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel {

	private Controller c;
	
	public ControlPanel(Controller c) {
		this.c = c;
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
		
		JButton generate = new JButton("Generate");
		JButton solve = new JButton("Solve");
		JButton save = new JButton("Save maze");
		JButton load = new JButton("Load maze");
		
		generate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.regenerateMaze();
			}
			
		});
		
		solve.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.getSolution();
			}
			
		});
		
		save.addActionListener(new ActionListener() {

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
						c.saveMaze(file);
						JOptionPane.showMessageDialog(null, "Maze saved successfully in Saves/" + this.file);
					} 
					catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(getParent(), "Invalid file name, the maze has not been saved", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});	
		
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(new File("Saves"));
				int sel = fileChooser.showOpenDialog(getParent());
				
				if(sel == JFileChooser.APPROVE_OPTION) {
					try {
						c.loadMaze(fileChooser.getSelectedFile());
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
		
		
		this.add(generate);
		this.add(solve);
		this.add(save);
		this.add(load);
	}
}
