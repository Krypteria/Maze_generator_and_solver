package maze.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import maze.controller.Controller;

@SuppressWarnings("serial")
public class GeneratePanel extends JPanel{
	
	private Controller ctrl;
	
	private JLabel title;
	
	private JLabel heightInfo;
	private JLabel widthInfo;
	
	private JTextArea height;
	private JTextArea width;
	
	private JButton generate;
	
	public GeneratePanel(Controller c) {
		this.ctrl = c;
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout(5,5));
		
		this.title = new JLabel("Generate Panel");
		
		this.heightInfo = new JLabel("height : ");
		this.widthInfo = new JLabel(" width : ");
		
		this.height = new JTextArea();
		this.width = new JTextArea();
		
		this.height.setPreferredSize(new Dimension(70,20));
		this.width.setPreferredSize(new Dimension(70,20));
		
		this.height.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		this.width.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		this.generate = new JButton("Generate");
		generate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {	
				if(height.getText().matches("\\d+") && width.getText().matches("\\d+")) {
					if(Integer.parseInt(height.getText()) > 30 || Integer.parseInt(width.getText()) > 30) {
						JOptionPane.showMessageDialog(null, "30 is the maximum value allowed", "Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(Integer.parseInt(height.getText()) < 2 || Integer.parseInt(width.getText()) < 2) {
						JOptionPane.showMessageDialog(null, "2 is the minimum value allowed", "Error",JOptionPane.ERROR_MESSAGE);
					}
					else {
						MainWindow.NROW = Integer.parseInt(height.getText());
						MainWindow.NCOL = Integer.parseInt(width.getText()); 
						ctrl.regenerateMaze(MainWindow.NROW, MainWindow.NCOL);					
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "height and width must be numbers", "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});

		JPanel p0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		p0.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		p0.add(this.title);
		p1.add(this.heightInfo);
		p1.add(this.height);
		p2.add(this.widthInfo);
		p2.add(this.width);
		p3.add(this.generate);

		p0.setMaximumSize(new Dimension(400,35));
		p1.setMaximumSize(new Dimension(400,35));
		p2.setMaximumSize(new Dimension(400,35));
		p3.setMaximumSize(new Dimension(400,35));
		
		JPanel sep1 = new JPanel();
		sep1.setMaximumSize(new Dimension(400,25));
		
		JPanel sep2 = new JPanel();
		sep2.setMaximumSize(new Dimension(400,25));
		
		JPanel sep3 = new JPanel();
		sep3.setMaximumSize(new Dimension(400,25));
		
		mainPanel.add(p0);
		mainPanel.add(sep2);
		mainPanel.add(p1);
		mainPanel.add(p2);
		mainPanel.add(sep3);
		mainPanel.add(p3);
		
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 4));
		
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(new JPanel(), BorderLayout.PAGE_START);
		this.add(new JPanel(), BorderLayout.PAGE_END);
		this.add(new JPanel(), BorderLayout.LINE_START);
		this.add(new JPanel(), BorderLayout.LINE_END);
	}

}
