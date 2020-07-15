package maze.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import maze.controller.Controller;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel {

	private Controller c;
	
	private JButton generate;
	private JButton solve; 
	private JButton save; 
	private JButton load;

	private JLabel heightInfo;
	private JLabel widthInfo;
	
	private JTextArea height;
	private JTextArea width;
	
	public ControlPanel(Controller c) {
		this.c = c;
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
		
		generate = new JButton("Generate");
		solve = new JButton("Solve");
		save = new JButton("Save maze");
		load = new JButton("Load maze");
		
		this.heightInfo = new JLabel("Height: ");
		this.widthInfo = new JLabel("Width: ");
		
		this.height = new JTextArea("" + MainWindow.NROW);
		this.width = new JTextArea("" + MainWindow.NCOL);
		
		this.height.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		this.width.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		this.height.setPreferredSize(new Dimension(60,20));
		this.width.setPreferredSize(new Dimension(60,20));
		
		generate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainWindow.NROW = Integer.parseInt(height.getText());
				MainWindow.NCOL = Integer.parseInt(width.getText()); 
				c.regenerateMaze(MainWindow.NROW, MainWindow.NCOL);
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
		
		this.add(heightInfo);
		this.add(height);
		this.add(widthInfo);
		this.add(width);
		this.add(generate);
		this.add(solve);
		this.add(save);
		this.add(load);
	}
}
