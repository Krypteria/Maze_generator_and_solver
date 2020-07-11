package maze.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import maze.controller.Controller;

@SuppressWarnings("serial")
public class MainMenu extends JFrame {

	private JLabel heightInfo;
	private JLabel widthInfo;
	
	private JTextArea height;
	private JTextArea width;
	
	private JButton generate;
	private JButton cancel;
	
	public MainMenu() {
		initGUI();
	}
	
	private void initGUI() {
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3,1));
		
		this.heightInfo = new JLabel("     height : ");
		this.widthInfo = new JLabel("width : ");
		
		this.height = new JTextArea();
		this.width = new JTextArea();
		
		this.height.setPreferredSize(new Dimension(60,20));
		this.width.setPreferredSize(new Dimension(60,20));
		
		this.height.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		this.width.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		JLabel info = new JLabel("(max supported value: 30)");
		
		JPanel options = new JPanel();
		options.setLayout(new GridLayout(4,1));
		
		JPanel options1 = new JPanel();
		options1.setLayout(new FlowLayout(FlowLayout.CENTER));
		options1.setMaximumSize(new Dimension(400,25));
		
		JPanel options2 = new JPanel();
		options2.setLayout(new FlowLayout(FlowLayout.CENTER));
		options2.setMaximumSize(new Dimension(400,25));
		
		JPanel options3 = new JPanel();
		options3.setLayout(new FlowLayout(FlowLayout.CENTER));
		options1.add(heightInfo);
		options1.add(height);
		options2.add(widthInfo);
		options2.add(width);
		
		options3.add(info);
		
		
		options.add(options1);
		options.add(options2);
		options.add(Box.createRigidArea(new Dimension(15,15)));
		options.add(options3);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		this.generate = new JButton("Generate");
		this.generate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(height.getText().matches("\\d+") && width.getText().matches("\\d+")) {
					if(Integer.parseInt(height.getText()) > 30 || Integer.parseInt(width.getText()) > 30) {
						JOptionPane.showMessageDialog(null, "30 is the maximum value allowed", "Error",JOptionPane.ERROR_MESSAGE);
					}
					else {
						new MainWindow(Integer.parseInt(height.getText()), Integer.parseInt(width.getText()),new Controller(Integer.parseInt(height.getText()), Integer.parseInt(width.getText()))); 
						setVisible(false);						
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "height and width must be numbers", "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		this.cancel = new JButton("Cancel");
		this.cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
			
		});
		
		buttons.add(generate);
		buttons.add(cancel);
		
		JPanel logoP = new JPanel();
		logoP.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton logo = new JButton(new ImageIcon("icons/logo.png"));	
		logo.setOpaque(false);
		logo.setContentAreaFilled(false);
		logo.setBorderPainted(false);
		
		logoP.add(logo);
		
		mainPanel.add(logoP);
		mainPanel.add(options);
		mainPanel.add(buttons);
		
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		this.add(mainPanel);
		this.setVisible(true);
		this.setTitle("Maze Generator and Solver");
		this.setSize(new Dimension(400,400));
		this.setMinimumSize(new Dimension(400,400));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
	}
}
