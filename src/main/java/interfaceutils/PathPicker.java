package interfaceutils;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.SystemUtilities;

@SuppressWarnings("serial")
public class PathPicker extends JPanel {
	
	private JLabel label;
	private JButton explore_button;
	private JFileChooser fileChooser;
	private boolean onlyDir;
		
	private JTextField selectedPath_f;
	
	
	
	
	/**
	 * @wbp.parser.constructor
	 */
	public PathPicker(String tag){
		build(false,tag);
	}
	
	
	
	public PathPicker(boolean onlyDir, String tag){
		build(onlyDir,tag);
	}

	/**
	 * Create the panel.
	 */
	public void build (boolean onlyDir, String tag) {
		
		
		
		
		
		this.onlyDir = onlyDir;
		setLayout(new FlowLayout(FlowLayout.CENTER, 7, 7));
		
		setPreferredSize(new Dimension(532, 40));
		
		
		if (!tag.equalsIgnoreCase("")){
			label = new JLabel(tag);
			label.setFont(new Font("Arial", Font.PLAIN, 12));
			add(label);
		}
				
		selectedPath_f = new JTextField();
		selectedPath_f.setFont(new Font("Arial", Font.PLAIN, 12));
		selectedPath_f.setBounds(97, 15, 584, 28);
		add(selectedPath_f);
		selectedPath_f.setColumns(40);
		
		selectedPath_f.setText(System.getProperty("user.home"));
			
		explore_button = new JButton(SystemUtilities.getLabelProperty("explore_button"));
		explore_button.setFont(new Font("Arial", Font.PLAIN, 12));
		explore_button.setBounds(693, 16, 117, 29);
		add(explore_button);
		
		fileChooser =  new JFileChooser(System.getProperty("user.home"));
		fileChooser.setVisible(false);
		
		explore_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fileChooser.setVisible(true);
				
				if (PathPicker.this.onlyDir)
					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
							
				int choice = fileChooser.showOpenDialog(explore_button);
							
				if (choice == JFileChooser.APPROVE_OPTION) {
					selectedPath_f.setText(fileChooser.getSelectedFile().getPath());
                    
				}
			}
		});
		
		

	}
	
	public void setPath (String nPath){
		selectedPath_f.setText(nPath);
	}
	
	public String getSelectedPath (){
		return selectedPath_f.getText();
	}
	
	public void setSize(int with, int lenght){
		
		setMaximumSize(new Dimension(with,lenght));
		setMinimumSize(new Dimension(with,lenght));
		
	}

}
