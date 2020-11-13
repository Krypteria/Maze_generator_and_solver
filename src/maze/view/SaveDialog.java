package maze.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SaveDialog extends JDialog{
	
	private JTextArea dialog;
	private JTextField name;
	
	private JButton accept;
	private JButton cancel;
	
	private int status;
	
	public SaveDialog(MainWindow p) {
		super(p , true);
		initGUI();
	}
	
	private void initGUI() {
		this.name = new JTextField();
		this.name.setPreferredSize(new Dimension(200,20));
		this.name.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		this.accept = new JButton("Accept");
		this.cancel = new JButton("Cancel");
		
		this.accept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				status = 1;
				setVisible(false);
			}
			
		});
		
		this.cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				status = 0;
				setVisible(false);
			}
			
		});
		
		dialog = new JTextArea("write the name with which you want to save the maze");
		dialog.setEditable(false);
		dialog.setBackground(null);
		dialog.setFont(dialog.getFont().deriveFont(Font.BOLD, 12f));
		
		JPanel mainPanel = new JPanel(new GridLayout(3, 1));
		
		JPanel p0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		p0.add(this.dialog);
		p1.add(this.name);
		p2.add(this.accept);
		p2.add(this.cancel);
		
		mainPanel.add(p0);
		mainPanel.add(p1);
		mainPanel.add(p2);
		
		this.add(mainPanel);
		this.setTitle("Save");
		this.setPreferredSize(new Dimension(400, 200));
		this.pack();
		this.setVisible(false);
	}
	
	public int open() {
		this.setVisible(true);
		return status;
	}
	
	public String getName() {
		return this.name.getText();
	}

}
