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
import maze.view.buttons.LoadButton;
import maze.view.buttons.SaveButton;
import maze.view.buttons.SolveButton;

@SuppressWarnings("serial")
public class OptionsPanel extends JPanel{
	
	private Controller ctrl;
	
	private JLabel title;
	
	private JButton solve; 
	private JButton save; 
	private JButton load;
	
	private MainWindow parent;
	
	public OptionsPanel(Controller c, MainWindow p) {
		this.ctrl = c;
		this.parent = p;
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout(5,5));
		
		this.title = new JLabel("Options");
		
		this.solve = new SolveButton(ctrl);
		this.solve.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ctrl.getSolution();
			}
			
		});
		
		this.save = new SaveButton(ctrl);
		this.save.addActionListener(new ActionListener() {

			private String file;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				SaveDialog dialog = new SaveDialog(parent);
				dialog.setLocationRelativeTo(getParent());
				
				int returnValue = dialog.open();

				if(returnValue == 1) {
					this.file = dialog.getName();
				}
					
				if(file != null && file.length() != 0) {					
					try {
						ctrl.saveMaze(file);
						JOptionPane.showMessageDialog(getParent(), "Maze saved successfully in Saves/" + this.file, "Save", JOptionPane.INFORMATION_MESSAGE);
					} 
					catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(getParent(), "Invalid file name, the maze has not been saved", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else if (file != null && file.length() == 0) {
					JOptionPane.showMessageDialog(getParent(), "Invalid file name, the maze has not been saved", "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});	
		
		this.load = new LoadButton(ctrl);
		this.load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(new File("Saves"));
				fileChooser.setDialogTitle("Load");
				
				int sel = fileChooser.showOpenDialog(getParent());
				
				if(sel == JFileChooser.APPROVE_OPTION) {
					try {
						ctrl.loadMaze(fileChooser.getSelectedFile());
					} 
					catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(getParent(), "Invalid file selected", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else if (sel != JFileChooser.CANCEL_OPTION) {
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
		
		int x = 400, y = 35;
		
		p0.setMaximumSize(new Dimension(x,y));
		p1.setMaximumSize(new Dimension(x,60));

		JPanel sep1 = new JPanel();
		sep1.setMaximumSize(new Dimension(x,y));
		
		JPanel sep2 = new JPanel();
		sep2.setMaximumSize(new Dimension(x,y));

		
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
